/*
 * @project restaurantApp
 * @fileName MainController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 24 12 2021 07:04 PM
 */
package com.restaurant.app.controller;

import com.restaurant.app.model.Cart;
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


        return ResponseEntity.ok("success");

    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        Long productId=Long.parseLong(request.getParameter("productId"));
        Product product=productService.findById(productId).orElse(null);
        System.out.println(product);
        System.out.println(productId);
        Cart cart=new Cart(productId,product.getProductName(),product.getProductPrice(),product.getProductCategory(),product.getImage(),product.getStatus(),1);
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
        productService.save(productInDb);
         cartService.save(productInCart);
         return ResponseEntity.ok("success");
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
        productService.save(productInDb);
        cartService.save(productInCart);
        return ResponseEntity.ok("success");
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
        model.addAttribute("products",cartList);
        model.addAttribute("cartCount",cartCount);
        return "cart";
    }

    @GetMapping("/payment")
    public String paymentPage(Model model) {
        List<Cart> cartList=cartService.findAll();
        long cartCount=cartService.count();
        model.addAttribute("products",cartList);
        model.addAttribute("cartCount",cartCount);
        return "payment";
    }

    @GetMapping("/confirm")
    public String confirmation() {
        return "success";
    }




}
