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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
       /// System.out.println(product);
        productService.save(product);
        return "redirect:/admin/product/products";
    }
    @PostMapping("/addP")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              @RequestParam("productName") String productName,
                              @RequestParam("productCategory") String productCategory,
                              @RequestParam("productPrice") String productPrice,
                              @RequestParam("status") String status)
    {
        productService.saveProductToDB(file, productName,productCategory,productPrice,status);
        return "redirect:/admin/product/products";
    }
    @GetMapping("/products")
    public String fetchProducts(Model model){
      List<Product> productList=productService.findAll();
        model.addAttribute("products",productList);
        model.addAttribute("counter",new Counter());
        return "products";
    }

    @PostMapping("/editProduct")
    public ResponseEntity<?> fetchProduct(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        Long productId=Long.parseLong(request.getParameter("productId"));
        System.out.println(productId);
        Optional<Product> product=productService.findById(productId);
        System.out.println(product);
        Optional<Product> result;
        if(product==null) {
            result=null;
        }
        else {
            result = product;
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/updateProduct")
    public String updateProduct(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        String productId=request.getParameter("productId");
        String productName=request.getParameter("productName");
        String productCategory=request.getParameter("productCategory");
        String productPrice=request.getParameter("productPrice");
        Product product=new Product(productId,productName,productCategory,productPrice);
        System.out.println(productId);
        String msg=productService.updateById(product);
        System.out.println(msg);
        String result;
        if(msg==null) {
            result=null;
        }
        else {
            result = msg;
        }
        return "redirect:/admin/product/products";
    }
    @PostMapping("/deleteproduct")
    public String deleteProduct(HttpServletRequest request)throws NumberFormatException {
        Long productId=Long.parseLong(request.getParameter("productId"));
        productService.deleteById(productId);
        return "redirect:/admin/product/products";
    }


}
