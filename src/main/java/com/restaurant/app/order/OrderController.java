/*
 * @project ResturantApp
 * @fileName OrderController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 04 01 2022 02:39 PM
 */
package com.restaurant.app.order;

import com.restaurant.app.config.Counter;

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
    @GetMapping("/orders")
    public  String index(Model model, HttpSession session){
        List<Order> orderList=orderService.findAll();
        String userName= String.valueOf(session.getAttribute("userName"));
        model.addAttribute("orders",orderList);
        model.addAttribute("counter",new Counter());
        model.addAttribute("userName",userName);
        return "orders";
    } 
    @PostMapping("/addorder")
    public String saveProduct(@RequestParam("orderId") Integer orderId,
                              @RequestParam("productIds") String productIds,
                              @RequestParam("quantities") String quantities,
                              @RequestParam("prices") String prices,
                              @RequestParam("total") String total,
                              @RequestParam("status") String status)
    {
        orderService.save(orderId,productIds,quantities,prices,total,status);
        return "redirect:/admin/order/orders";
    } 
    @PostMapping("/deleteorder")
    public String deleteOrder(HttpServletRequest request)throws NumberFormatException {
        Integer orderId=Integer.parseInteger(request.getParameter("orderId"));
        orderService.deleteById(orderId);
        return "redirect:/admin/order/orders";
    }
}
