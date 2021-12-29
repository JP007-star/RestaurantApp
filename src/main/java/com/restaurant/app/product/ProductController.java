/*
 * @project ResturantApp
 * @fileName ProductController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 12 2021 12:34 PM
 */
package com.restaurant.app.product;

import com.restaurant.app.config.Counter;
import com.restaurant.app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public String fetchProducts(Model model){
        Iterable<Product> productList=productService.findAll();
        model.addAttribute("products",productList);
        model.addAttribute("counter",new Counter());
        return "products";
    }
}
