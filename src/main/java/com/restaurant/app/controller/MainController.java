/*
 * @project restaurantApp
 * @fileName MainController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 24 12 2021 07:04 PM
 */
package com.restaurant.app.controller;

import com.restaurant.app.model.*;
import com.restaurant.app.service.*;
import com.restaurant.app.dao.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    Double grandTotal=0.0;
    @Autowired
    private SaleService saleService;
    LocalDateTime orderDate= LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "Kafka_restApp_User";
    private String key;

    public MainController(UserService userService) {
        super();
        this.userService = userService;
    }

    //this function will render login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

   //This function is used to confirm the order
    @PostMapping("/confirmOrder")
    public ResponseEntity<?> confirmOrder(HttpServletRequest request, Model model,HttpSession session) throws SQLException, ClassNotFoundException {
        String address=request.getParameter("address");
        String country=request.getParameter("country");
        String state=request.getParameter("state");
        String zip=request.getParameter("zip");
        List<Cart> cartList=cartService.findAll();
        ArrayList<String> productIdsList=new ArrayList<>();
        ArrayList<String> productNamesList=new ArrayList<>();
        ArrayList<String> quantitiesList=new ArrayList<>();
        ArrayList<String> pricesList=new ArrayList<>();
        ArrayList<String> totalsList=new ArrayList<>();
        System.out.println(cartList);
        for (Cart cartItems:
             cartList) {
            productIdsList.add(cartItems.getProductId());
            productNamesList.add(String.valueOf(cartItems.getProductName()));
            quantitiesList.add(String.valueOf(cartItems.getProductQuantity()));
            pricesList.add(cartItems.getProductPrice());
            totalsList.add(String.valueOf(cartItems.getTotalPrice()));
            Sale sale=new Sale(cartItems.getProductId(),cartItems.getProductName(),cartItems.getProductQuantity(),orderDate);
            saleService.save(sale);
        }

        String userName= String.valueOf(session.getAttribute("userName"));
        String orderId="OR00"+updateCounter();
        grandTotal=calculateGrandTotal();
        Order order=new Order(productIdsList.toString(),orderId,userName,productNamesList.toString(),quantitiesList.toString(),pricesList.toString(),totalsList.toString(),address,country,state,zip,grandTotal,orderDate);
        orderService.save(order);
        cartService.deleteAll();
        String msg="Order confirmed with orderId:"+orderId+"\n";
        String p1= String.valueOf(session.getAttribute("userId"));
        kafkaTemplate.send(TOPIC, Integer.valueOf(p1),key,msg);
        return ResponseEntity.ok(orderId);

    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(HttpServletRequest request, Model model,HttpSession session) throws SQLException, ClassNotFoundException {
        String productId=request.getParameter("productId");
        Product product=productService.findById(productId).orElse(null);
        System.out.println(product);
        System.out.println(productId);
        Cart cart=new Cart(productId,product.getProductName(),product.getProductPrice(),product.getProductCategory(),product.getImage(),product.getStatus(),1,Double.parseDouble(product.getProductPrice()));
        cartService.save(cart);
        String cartCount= String.valueOf(cartService.count());
        String result="";
        if(cartCount!=null){
            result=cartCount;}
        else {
            result="It is already there in cart";}
        String msg=product.getProductName()+""+" added to cart\n";
        String p1= String.valueOf(session.getAttribute("userId"));
        kafkaTemplate.send(TOPIC, Integer.valueOf(p1),key,msg);
        return ResponseEntity.ok(cartCount);
    }
    @PostMapping("/deleteToCart")
    public ResponseEntity<?> deleteToCart(HttpServletRequest request, Model model,HttpSession session) throws SQLException, ClassNotFoundException {
        Long productId=Long.parseLong(request.getParameter("cartId"));
        Cart cart =  cartService.findById(productId).orElse(null);
        String msg=cart.getProductName()+""+" deleted from cart \n";
        String p1= String.valueOf(session.getAttribute("userId"));
        kafkaTemplate.send(TOPIC, Integer.valueOf(p1),key,msg);
        cartService.deleteById(productId);
        return ResponseEntity.ok("success");
    }
    @PostMapping("/addQuantityToCart")
    public ResponseEntity<?> addQuantityToCart(HttpServletRequest httpServletRequest,Model model,HttpSession session) {
        Long cartId = Long.parseLong(httpServletRequest.getParameter("cartId"));
        Cart productInCart= cartService.findById(cartId).orElse(null);
        System.out.println(productInCart);
        Product productInDb=productService.findById(productInCart.getProductId()).orElse(null);
        int quantityOfProductInDb=productInDb.getQuantity();
        int quantityOfCart=productInCart.getProductQuantity();
        System.out.println(productInCart);
        if(quantityOfCart >=1 && quantityOfProductInDb >=1 ) {
            productInDb.setQuantity(quantityOfProductInDb - 1);
            productInCart.setProductQuantity(quantityOfCart + 1);
            int newQuantityOfCart=productInCart.getProductQuantity();
            double totalPrice=newQuantityOfCart*Double.parseDouble(productInDb.getProductPrice());
            productInCart.setTotalPrice(totalPrice);
            productService.save(productInDb);
            cartService.save(productInCart);
            String msg=productInCart.getProductName()+""+" Quantity increased in cart\n";
            String p1= String.valueOf(session.getAttribute("userId"));
            kafkaTemplate.send(TOPIC, Integer.valueOf(p1),key,msg);
            grandTotal=calculateGrandTotal();
            return ResponseEntity.ok(grandTotal);
        }
        grandTotal=calculateGrandTotal();
        return ResponseEntity.ok(grandTotal);

    }
    @PostMapping("/removeQuantityToCart")
    public ResponseEntity<?> removeQuantityToCart(HttpServletRequest httpServletRequest,Model model,HttpSession session) {
        Long cartId = Long.parseLong(httpServletRequest.getParameter("cartId"));
        Cart productInCart= cartService.findById(cartId).orElse(null);
        System.out.println(productInCart);
        Product productInDb=productService.findById(productInCart.getProductId()).orElse(null);
        int quantityOfProductInDb=productInDb.getQuantity();
        int quantityOfCart=productInCart.getProductQuantity();
        System.out.println(productInCart);
        if(quantityOfCart >=1 && quantityOfProductInDb >=1 ) {
            productInDb.setQuantity(quantityOfProductInDb + 1);
            productInCart.setProductQuantity(quantityOfCart - 1);
            int newQuantityOfCart = productInCart.getProductQuantity();
            double totalPrice = newQuantityOfCart * Double.parseDouble(productInDb.getProductPrice());
            productInCart.setTotalPrice(totalPrice);
            productService.save(productInDb);
            cartService.save(productInCart);
            String msg=productInCart.getProductName()+""+" Quantity decreased in cart\n";
            String p1= String.valueOf(session.getAttribute("userId"));
            kafkaTemplate.send(TOPIC, Integer.valueOf(p1),key,msg);
            grandTotal = calculateGrandTotal();
            return ResponseEntity.ok(grandTotal);
        }
        grandTotal = calculateGrandTotal();
        return ResponseEntity.ok(grandTotal);
    }

    //This controller function is for generating orderBill as PDF
    @GetMapping("/billGenerator")
    public void  billGenerator(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException {
        BillGenerator billGenerator=new BillGenerator();
        String orderId=request.getParameter("orderId");
        Order order=orderService.findById(orderId).orElse(null);
        String userId=String.valueOf(session.getAttribute("userId"));
        System.out.println(userId);
        User user=userService.findById(Long.parseLong(userId)).orElse(null);
        String msg="Bill generated for order:"+orderId+"\n";
        String p1= String.valueOf(session.getAttribute("userId"));
        kafkaTemplate.send(TOPIC, Integer.valueOf(p1),key,msg);
        billGenerator.generateBill(response,order,user);
    }

   //This function is used to calculate GrandTotal
    public Double calculateGrandTotal(){
        List<Cart> cartList= cartService.findAll();
        grandTotal=0.0;
        for (Cart cartItems:cartList) {
            grandTotal+= cartItems.getTotalPrice();
        }
        return grandTotal;
    }


    // This Controller function is for loading the reservation page
    @GetMapping("/")
    public String reservation(Model model, HttpSession session){
        long cartCount=cartService.count();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user=userService.loadByEmailId(login);
        session.setAttribute("userName", user.getFirstName());
        session.setAttribute("userId", user.getId());
        String userName= String.valueOf(session.getAttribute("userName"));
        List<Product> productList=productService.findAllActiveProduct();
        model.addAttribute("cartCount",cartCount);
        model.addAttribute("products",productList);
        model.addAttribute("userName",userName);
        String msg="User logged in : Name:"+userName+""+"Email:"+user.getEmail()+"\n";
        String p1= String.valueOf(session.getAttribute("userId"));
        kafkaTemplate.send(TOPIC, Integer.valueOf(p1),key,msg);
        return "index";
    }

    //This function is used to register a user
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto registrationDto,HttpSession session) {
        userService.save(registrationDto);
        System.out.println(registrationDto);
        return "redirect:/login?success";
    }
    @GetMapping("/registration")
    public String registerNewUser() {
        return "registration";
    }


    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    //this function will render cart page
    @GetMapping("/cart")
    public String cartPage(Model model,HttpSession session) {
        List<Cart> cartList=cartService.findAll();
        long cartCount=cartService.count();
        grandTotal=calculateGrandTotal();
        String userName= String.valueOf(session.getAttribute("userName"));
        model.addAttribute("products",cartList);
        model.addAttribute("cartCount",cartCount);
        model.addAttribute("grandTotal",grandTotal);
        model.addAttribute("userName",userName);
        return "cart";
    }

    //this function will render cart page
    @GetMapping("/payment")
    public String paymentPage(Model model,HttpSession session) {
        List<Cart> cartList=cartService.findAll();
        long cartCount=cartService.count();
        grandTotal=calculateGrandTotal();
        String userName= String.valueOf(session.getAttribute("userName"));
        model.addAttribute("products",cartList);
        model.addAttribute("cartCount",cartCount);
        model.addAttribute("grandTotal",grandTotal);
        model.addAttribute("userName",userName);
        return "payment";
    }

    //this function will render confirmation page
    @GetMapping("/confirm")
    public String confirmation() {
        return "success";
    }

    //This function is used to update counter
    public static int updateCounter()
    {
        String counterFileName="ordercounter.txt";
        int counter=99;
        File counterFile=new File(counterFileName);
        if(counterFile.isFile())
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(counterFileName)))
            {
                counter=Integer.parseInt(reader.readLine());
            }
            catch(IOException e)
            {
                e.printStackTrace();
                return 0;
            }
        }
        try(FileWriter writer = new FileWriter(counterFileName))
        {
            writer.write(String.valueOf(++counter));
        } catch(IOException e){
            e.printStackTrace();
            return 0;
        }
        return counter;
    }


}
