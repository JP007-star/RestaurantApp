package com.restaurant.app.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class OrderService implements  OrderRepository{
    @Autowired
    public OrderRepository orderRepository;
    @Override
    public List<Orders> findAll() {
       return  OrderRepository.findAll();
    }

    @Override
    public List<Orders> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Orders> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Orders> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        OrderRepository.deleteById(aLong);

    }

    @Override
    public void delete(Orders entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Product> S save(S entity) {
        return OrderRepository.save(entity);
    }

    @Override
    public <S extends Orders> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Orders> findById(Long id) throws UsernameNotFoundException {
            Optional<Orders> orders = OrderRepository.findById(id);
            return Orders;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Orders> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Orders> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Orders getOne(Long aLong) {
        return null;
    }

    @Override
    public Orders getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Orders> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Orders> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Orders> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Orders> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Orders> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Orders> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Orders, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    public String updateById(Orders orders){
        Orders order1=OrderRepository.findById(orders.getorder_id()).orElse(null);
        order1.setorder_id(orders.getorder_id());
        order1.setproduct_id(orders.getproduct_id());
        order1.setquantity(orders.getquantity());
        order1.settotal(orders.gettotal());
        OrderRepository.save(order1);
        return "success";
    }
}