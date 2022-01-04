/*
 * @project ResturantApp
 * @fileName OrderController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 12 2021 12:34 PM
 */
package com.restaurant.app.orders;

import com.restaurant.app.config.Counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/save")
    public String saveOrders(@ModelAttribute("orders") Orders orders) {
       /// System.out.println(order);
        OrderService.save(orders);
        return "redirect:/admin/orders/orders";
    }
    @GetMapping("/orders")
    public String fetchOrders(Model model){
      List<Orders> ordersList=OrderService.findAll();
        model.addAttribute("orders",ordersList);
        model.addAttribute("counter",new Counter());
        return "orders";
    }

    @PostMapping("/editorders")
    public ResponseEntity<?> fetchProduct(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        Long order_id=Long.parseLong(request.getParameter("order_id"));
        System.out.println(order_id);
        Optional<Orders> orders=OrderService.findById(order_id);
        System.out.println(orders);
        Optional<Orders> result;
        if(Orders==null) {
            result=null;
        }
        else {
            result = orders;
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/updateorders")
    public String updateorders(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        String order_id=request.getParameter("order_id");
        String product_id=request.getParameter("product_id");
        String quantity=request.getParameter("quantity");
        String total=request.getParameter("total");
        Orders orders=new Orders(order_id,product_id,quantity,total);
        System.out.println(order_id);
        String msg=OrderService.updateById(orders);
        System.out.println(msg);
        String result;
        if(msg==null) {
            result=null;
        }
        else {
            result = msg;
        }
        return "redirect:/admin/orders/orders";
    }
    @PostMapping("/deleteorders")
    public String deleteProduct(HttpServletRequest request)throws NumberFormatException {
        Long order_id=Long.parseLong(request.getParameter("order_id"));
        OrderService.deleteById(order_id);
        return "redirect:/admin/orders/orders";
    }
}