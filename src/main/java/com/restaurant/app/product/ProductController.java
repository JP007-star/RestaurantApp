/*
 * @project ResturantApp
 * @fileName ProductController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 12 2021 12:34 PM
 */
package com.restaurant.app.product;

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
    public String updateProduct(@RequestParam("file") MultipartFile file,
                                @RequestParam("productId") String productId,
                               @RequestParam("productName") String productName,
                               @RequestParam("productCategory") String productCategory,
                               @RequestParam("productPrice") String productPrice,
                               @RequestParam("status") String status){
        Product product=new Product(file,productId,productName,productCategory,productPrice,status);
        productService.updateById(Long.valueOf(productId),file,productName,productCategory,productPrice,status);
        product.setProductName(productName);
        product.setProductCategory(productCategory);
        product.setProductPrice(productPrice);
        return "redirect:/admin/product/products";
    }
    @PostMapping("/deleteproduct")
    public String deleteProduct(HttpServletRequest request)throws NumberFormatException {
        Long productId=Long.parseLong(request.getParameter("productId"));
        productService.deleteById(productId);
        return "redirect:/admin/product/products";
    }


}
