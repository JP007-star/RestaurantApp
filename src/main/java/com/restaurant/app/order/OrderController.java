/*
 * @project ResturantApp
 * @fileName OrderController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 04 01 2022 02:39 PM
 */
package com.restaurant.app.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @GetMapping("/orders")
    public  String index(){
        return "order";
    }
}
