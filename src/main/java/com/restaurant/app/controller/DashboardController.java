/*
 * @project ResturantApp
 * @fileName DashboardController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 04 01 2022 02:24 PM
 */
package com.restaurant.app.controller;

import com.restaurant.app.service.ProductService;
import com.restaurant.app.dao.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {
    @Autowired
    ProductService productService;
    @Autowired
    UserServiceImpl userService;
    @GetMapping
    public  String index(Model model){
        long productCount=productService.count();
        long userCount=userService.userRepository.count();
        model.addAttribute("productCount",productCount);
        model.addAttribute("userCount",userCount);
        return "dashboard";
    }
}
