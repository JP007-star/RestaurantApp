/*
 * @project ResturantApp
 * @fileName OrderController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 04 01 2022 02:39 PM
 */
package com.restaurant.app.controller;

import java.util.List;

import com.restaurant.app.model.Notification;
import com.restaurant.app.model.Product;
import com.restaurant.app.service.CartService;
import com.restaurant.app.service.NotificationService;
import com.restaurant.app.utility.Counter;
import com.restaurant.app.model.Order;
import com.restaurant.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @Autowired
    NotificationService notificationService;

    //This function is used to display all orders
    @GetMapping("/orders")
    public String index(Model model, HttpSession session) {
        List<Order> orderList = orderService.findAll();
        long cartCount=cartService.count();
        long notificationCount=notificationService.count();
        System.out.println(orderList);
        String userName = String.valueOf(session.getAttribute("userName"));
        model.addAttribute("cartCount",cartCount);
        model.addAttribute("notificationCount",notificationCount);
        model.addAttribute("orders", orderList);
        model.addAttribute("counter", new Counter());
        model.addAttribute("userName", userName);
        return "orders";
    }
    @PostMapping("/fetchNotification")
    public ResponseEntity<?> fetchNotification(){
        List<Notification> notificationList=notificationService.notificationForAdmin();
        return ResponseEntity.ok(notificationList);
    }

}