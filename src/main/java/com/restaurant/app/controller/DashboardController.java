/*
 * @project ResturantApp
 * @fileName DashboardController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 04 01 2022 02:24 PM
 */
package com.restaurant.app.controller;

import com.google.gson.Gson;
import com.restaurant.app.model.Order;
import com.restaurant.app.service.CartService;
import com.restaurant.app.service.OrderService;
import com.restaurant.app.service.ProductService;
import com.restaurant.app.dao.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin/dashboard/")
public class DashboardController {
    @Autowired
    ProductService productService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    Double totalRevenue=0.0;
    @GetMapping("/")
    public  String index(Model model) throws JSONException {
        long cartCount=cartService.count();
        long productCount=productService.count();
        List<String> productQuantityStockList=productService.findAllQuantity();
        List<String> productNameStockList=productService.findAllProductName();
        long userCount=userService.userRepository.count();
        long orderCount=orderService.count();
        JSONArray productArray = new JSONArray();
        JSONArray mJSONArray = new JSONArray();

        for(int value : getThreeValues())
        {
            mJSONArray.put(value);
        }
        for(String productName : productNameStockList) {
            JSONObject data= new JSONObject();
            data.put("name", productName);
            data.put("data", mJSONArray);
            productArray.put(data);
        }
        System.out.println(productArray);
        model.addAttribute("productCount",productCount);
        model.addAttribute("userCount",userCount);
        model.addAttribute("cartCount",cartCount);
        model.addAttribute("orderCount",orderCount);
        model.addAttribute("totalRevenue",revenueCalculator());
        model.addAttribute("productQuantityStockList",productQuantityStockList);
        model.addAttribute("productNameStockList",productNameStockList);
        model.addAttribute("productArray",productArray);
        return "dashboard";
    }
    public int[] getThreeValues() {
        Random r = new Random();
        return new int[] { r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100), r.nextInt(100), r.nextInt(100) };
    }


    public Double revenueCalculator(){
        totalRevenue=0.0;
        List<Order> orderList=orderService.findAll();
        for (Order orderItems:
             orderList) {
            totalRevenue+=orderItems.getGrandTotal();
        }
        return totalRevenue;
    }
}
