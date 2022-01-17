/*
 * @project ResturantApp
 * @fileName ProductController
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 12 2021 12:34 PM
 */
package com.restaurant.app.controller;

import com.restaurant.app.service.CartService;
import com.restaurant.app.utility.Counter;

import com.restaurant.app.model.Product;
import com.restaurant.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    CartService cartService;

    @Autowired
    private KafkaTemplate<Object, String> kafkaTemplate;
    private static final String TOPIC = "Kafka_restApp_admin_activity";
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
       productService.save(product);
        return "redirect:/admin/product/products";
    }
    @PostMapping("/addProduct")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              @RequestParam("productName") String productName,
                              @RequestParam("productCategory") String productCategory,
                              @RequestParam("productPrice") String productPrice,
                              @RequestParam("quantity") int quantity,
                              @RequestParam("status") Boolean status)
    {
        String productId="PR00"+updateCounter();
        kafkaTemplate.send(TOPIC,productService.saveProductToDB(file, productId.toUpperCase(), productName,productCategory,productPrice,quantity,status));

        return "redirect:/admin/product/products";
    }
    @GetMapping("/products")
    public String fetchProducts(Model model, HttpSession session){
      List<Product> productList=productService.findAll();
        String userName= String.valueOf(session.getAttribute("userName"));
        long cartCount=cartService.count();
        model.addAttribute("cartCount",cartCount);
        model.addAttribute("products",productList);
        model.addAttribute("counter",new Counter());
        model.addAttribute("userName",userName);
        return "products";
    }

    @PostMapping("/editProduct")
    public ResponseEntity<?> fetchProduct(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
        String productId=request.getParameter("productId");
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
        String quantity=request.getParameter("quantity");
        boolean  status= Boolean.parseBoolean(request.getParameter("status"));
        Product product=new Product(productId,productName,productCategory,productPrice,quantity,status);
        System.out.println(productId);
        String msg=productService.updateById(product);
        System.out.println(msg);
        kafkaTemplate.send(TOPIC,msg);
        String result;
        if(msg==null) {
            result=null;
        }
        else {
            result = msg;
        }
        return "redirect:/admin/product/products";
    }
    @PostMapping("/deleteProduct")
    public String deleteProduct(HttpServletRequest request)throws NumberFormatException {
        String productId=request.getParameter("productId");
        productService.deleteById((productId));
        return "redirect:/admin/product/products";
    }

   //This function is used to update counter
    public static int updateCounter()
   {
        String counterFileName="counter.txt";
        int counter=99;
        File counterFile=new File(counterFileName);
       if(counterFile.isFile())
       {
            try (BufferedReader reader = new BufferedReader(new FileReader(counterFileName)))
            {
                counter=Integer.parseInt(reader.readLine());
            }
            catch(IOException e)
           {
                e.printStackTrace();
               return 0;
           }
        }
       try(FileWriter writer = new FileWriter(counterFileName))
       {
            writer.write(String.valueOf(++counter));
       } catch(IOException e){
            e.printStackTrace();
            return 0;
       }
       return counter;
   }

}
