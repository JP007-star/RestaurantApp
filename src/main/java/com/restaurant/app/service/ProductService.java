package com.restaurant.app.service;

import com.restaurant.app.model.Product;
import com.restaurant.app.repository.CartRepository;
import com.restaurant.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductService implements ProductRepository {
    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public CartRepository cartRepository;
    @Override
    public List<Product> findAll() {
       return  productRepository.findAll();
    }

    public String saveProductToDB(MultipartFile file,String productId,String productName,String productCategory,String productPrice,int quantity,Boolean status)
    {
        Product p = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setProductId(productId);
        p.setProductName(productName);
        p.setProductCategory(productCategory);
        p.setProductPrice(productPrice);
        p.setQuantity(quantity);
        p.setStatus(status);
        productRepository.save(p);
        return "product added with Id:"+p.getProductId()+"Added product details:"+p;
    }

    public String updateById(Product product){
        Product product1=productRepository.findById(product.getProductId()).orElse(null);
        product1.setProductName(product.getProductName());
        product1.setProductCategory(product.getProductCategory());
        product1.setProductPrice(product.getProductPrice());
        product1.setQuantity(product.getQuantity());
        product1.setStatus(product.getStatus());
        productRepository.save(product1);
        return "Update success for Id:"+product.getProductId()+"Updated details:"+product1;
    }
    public String updateStatus(Product product){
        Product product1=productRepository.findById(product.getProductId()).orElse(null);
        product1.setStatus(product.getStatus());
        productRepository.save(product1);
        return "Update success for Id:"+product.getProductId()+"Updated details:"+product1;
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(String s) {
        productRepository.deleteById(s);
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Product> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }



    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Product> findById(String s) {
        Optional<Product> product = productRepository.findById(s);
        return product;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }


    @Override
    public void flush() {

    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Product getOne(String s) {
        return null;
    }

    @Override
    public Product getById(String s) {
        return null;
    }


    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        return productRepository.count();
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<String> findAllQuantity() {
        return productRepository.findAllQuantity();
    }

    @Override
    public List<String> findAllProductName() {
        return productRepository.findAllProductName();
    }

    @Override
    public List<Product> findAllActiveProduct() {
        return productRepository.findAllActiveProduct();
    }

    @Override
    public List<Product> findAllActiveDisplayProduct() {
        return productRepository.findAllActiveDisplayProduct();
    }

    @Override
    public List<Product> findAllActiveMainCourse() {
        return null;
    }

    @Override
    public List<Product> findAllActiveSnacks() {
        return null;
    }

    @Override
    public List<Product> findAllActiveMilkshake() {
        return null;
    }

    @Override
    public List<Product> findAllActiveJuice() {
        return null;
    }

    @Override
    public List<Product> findAllActiveSoup() {
        return null;
    }

    @Override
    public List<Product> findAllActiveProductByName(String productName) {
        return productRepository.findAllActiveProductByName(productName);
    }
}
