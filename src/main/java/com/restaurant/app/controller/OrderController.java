/*
 * @project ResturantApp
 * @fileName OrderController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 04 01 2022 02:39 PM
 */
package com.restaurant.app.controller;

import java.util.List;
import com.restaurant.app.utility.Counter;
import com.restaurant.app.model.Order;
import com.restaurant.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/orders")
    public  String index(Model model, HttpSession session){
        List<Order> orderList=orderService.findAll();
        String userName= String.valueOf(session.getAttribute("userName"));
        model.addAttribute("orders",orderList);
        model.addAttribute("counter",new Counter());
        model.addAttribute("userName",userName);
        return "orders";
    }  
}
