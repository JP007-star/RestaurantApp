/*
 * @project restaurantApp
 * @fileName MainController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 24 12 2021 07:04 PM
 */
package com.restaurant.app.controller;

import com.restaurant.app.model.Cart;
import com.restaurant.app.model.Order;
import com.restaurant.app.model.Product;
import com.restaurant.app.service.CartService;
import com.restaurant.app.service.OrderService;
import com.restaurant.app.service.ProductService;
import com.restaurant.app.model.User;
import com.restaurant.app.dao.UserRegistrationDto;
import com.restaurant.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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


    public MainController(UserService userService) {
        super();
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/confirmOrder")
    public ResponseEntity<?> confirmOrder(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        List<Cart> cartList=cartService.findAll();
        ArrayList<String> productIdsList=new ArrayList<>();
        ArrayList<String> quantitiesList=new ArrayList<>();
        ArrayList<String> pricesList=new ArrayList<>();
        ArrayList<String> totalsList=new ArrayList<>();
        for (Cart cartItems:
             cartList) {
            productIdsList.add(String.valueOf(cartItems.getProductId()));
            quantitiesList.add(String.valueOf(cartItems.getProductQuantity()));
            pricesList.add(cartItems.getProductPrice());
            totalsList.add(String.valueOf(cartItems.getTotalPrice()));

        }
        grandTotal=calculateGrandTotal();
        Order order=new Order(productIdsList.toString(),quantitiesList.toString(),pricesList.toString(),totalsList.toString(),grandTotal);
        orderService.save(order);
        cartService.deleteAll();
        return ResponseEntity.ok("success");

    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        Long productId=Long.parseLong(request.getParameter("productId"));
        Product product=productService.findById(productId).orElse(null);
        System.out.println(product);
        System.out.println(productId);
        Cart cart=new Cart(productId,product.getProductName(),product.getProductPrice(),product.getProductCategory(),product.getImage(),product.getStatus(),1,Double.parseDouble(product.getProductPrice()));
        cartService.save(cart);
        return ResponseEntity.ok("success");
    }
    @PostMapping("/deleteToCart")
    public ResponseEntity<?> deleteToCart(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        Long productId=Long.parseLong(request.getParameter("cartId"));
        cartService.deleteById(productId);
        return ResponseEntity.ok("success");
    }
    @PostMapping("/addQuantityToCart")
    public ResponseEntity<?> addQuantityToCart(HttpServletRequest httpServletRequest,Model model) {
        Long cartId = Long.parseLong(httpServletRequest.getParameter("cartId"));
        Cart productInCart= cartService.findById(cartId).orElse(null);
        System.out.println(productInCart);
        Product productInDb=productService.findById(productInCart.getProductId()).orElse(null);
        int quantityOfProductInDb=productInDb.getQuantity();
        int quantityOfCart=productInCart.getProductQuantity();
        System.out.println(productInCart);
        productInDb.setQuantity(quantityOfProductInDb-1);
        productInCart.setProductQuantity(quantityOfCart+1);
        int newQuantityOfCart=productInCart.getProductQuantity();
        double totalPrice=newQuantityOfCart*Double.parseDouble(productInDb.getProductPrice());
        productInCart.setTotalPrice(totalPrice);
        productService.save(productInDb);
        cartService.save(productInCart);
        grandTotal=calculateGrandTotal();
        return ResponseEntity.ok(grandTotal);
    }
    @PostMapping("/removeQuantityToCart")
    public ResponseEntity<?> removeQuantityToCart(HttpServletRequest httpServletRequest,Model model) {
        Long cartId = Long.parseLong(httpServletRequest.getParameter("cartId"));
        Cart productInCart= cartService.findById(cartId).orElse(null);
        System.out.println(productInCart);
        Product productInDb=productService.findById(productInCart.getProductId()).orElse(null);
        int quantityOfProductInDb=productInDb.getQuantity();
        int quantityOfCart=productInCart.getProductQuantity();
        System.out.println(productInCart);
        productInDb.setQuantity(quantityOfProductInDb+1);
        productInCart.setProductQuantity(quantityOfCart-1);
        int newQuantityOfCart=productInCart.getProductQuantity();
        double totalPrice=newQuantityOfCart*Double.parseDouble(productInDb.getProductPrice());
        productInCart.setTotalPrice(totalPrice);
        productService.save(productInDb);
        cartService.save(productInCart);
        grandTotal=calculateGrandTotal();
        return ResponseEntity.ok(grandTotal);
    }


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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user=userService.loadByEmailId(login);
        session.setAttribute("userName", user.getFirstName());
        String userName= String.valueOf(session.getAttribute("userName"));
        List<Product> productList=productService.findAll();
        model.addAttribute("products",productList);
        model.addAttribute("userName",userName);
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        System.out.println(registrationDto);
        return "redirect:/login?success";
    }
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @GetMapping("/cart")
    public String cartPage(Model model) {
        List<Cart> cartList=cartService.findAll();
        long cartCount=cartService.count();
        grandTotal=calculateGrandTotal();
        model.addAttribute("products",cartList);
        model.addAttribute("cartCount",cartCount);
        model.addAttribute("grandTotal",grandTotal);
        return "cart";
    }

    @GetMapping("/payment")
    public String paymentPage(Model model) {
        List<Cart> cartList=cartService.findAll();
        long cartCount=cartService.count();
        grandTotal=calculateGrandTotal();
        model.addAttribute("products",cartList);
        model.addAttribute("cartCount",cartCount);
        model.addAttribute("grandTotal",grandTotal);
        return "payment";
    }

    @GetMapping("/confirm")
    public String confirmation() {
        return "success";
    }




}
