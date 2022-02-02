/*
 * @project ResturantApp
 * @fileName NotificationService
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 01 2022 10:47 PM
 */
package com.restaurant.app.service;

import com.restaurant.app.model.Notification;
import com.restaurant.app.model.Product;
import com.restaurant.app.repository.NotificationRepository;
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
public class NotificationService implements NotificationRepository {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Notification> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Notification> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return notificationRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Notification entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Notification> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Notification> S save(S entity) {
       return  notificationRepository.save(entity);

    }

    @Override
    public <S extends Notification> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Notification> findById(Long aLong) {
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
    public <S extends Notification> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Notification> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Notification> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Notification getOne(Long aLong) {
        return null;
    }

    @Override
    public Notification getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Notification> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Notification> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Notification> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Notification> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Notification> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Notification> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Notification, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


    @Override
    public List<Notification> notificationForUsers(Long userId) {
        return notificationRepository.notificationForUsers(userId);
    }

    @Override
    public List<Notification> notificationForAdmin() {
        return notificationRepository.notificationForAdmin();
    }

    @Override
    public long notificationCountForUsers(Long userId) {
        return notificationRepository.notificationCountForUsers(userId);
    }

    @Override
    public long notificationCountForAdmin() {
        return notificationRepository.notificationCountForAdmin();
    }



}
