package com.restaurant.app.product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    void saveProduct(Product product);

    Optional<Product> findById(Long id);

    String deleteById(Long id) ;

    String updateById(Product product);
}
