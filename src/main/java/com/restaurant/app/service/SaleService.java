/*
 * @project ResturantApp
 * @fileName SaleService
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 10 01 2022 02:59 PM
 */
package com.restaurant.app.service;

import com.restaurant.app.model.Sale;
import com.restaurant.app.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class SaleService implements SaleRepository {
    @Autowired
    SaleRepository saleRepository;
    @Override
    public List<Sale> findAll() {
        return null;
    }

    @Override
    public List<Sale> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Sale> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Sale> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Sale entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sale> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Sale> S save(S entity) {
        return saleRepository.save(entity);
    }

    @Override
    public <S extends Sale> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Sale> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Sale> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Sale> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Sale> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Sale getOne(Long aLong) {
        return null;
    }

    @Override
    public Sale getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Sale> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Sale> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sale> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Sale> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sale> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sale> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Sale, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Integer orderQuantityDetails(String productName,String day) {
        return saleRepository.orderQuantityDetails(productName,day);
    }
}
