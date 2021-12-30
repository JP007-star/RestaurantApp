package com.restaurant.app.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Product saveProduct(Product product);

    Optional<Product> findById(Long id);

    String deleteById(Long id) ;

    String updateById(String product);
}
