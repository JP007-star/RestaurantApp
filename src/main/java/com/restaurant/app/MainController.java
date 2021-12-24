/*
 * @project restaurantApp
 * @fileName MainController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 24 12 2021 07:04 PM
 */
package com.restaurant.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // This Controller function is for loading the reservation page
    @GetMapping("/index")
    public String reservation(Model model){
        return "index";
    }
}
