/*
 * @project ResturantApp
 * @fileName NotificationRepository
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 01 2022 10:47 PM
 */
package com.restaurant.app.repository;

import com.restaurant.app.model.Notification;
import com.restaurant.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query(value = "select * from notification where  notification_role='ROLE_USER'", nativeQuery = true)
    public  List<Notification> notificationForUsers();
    @Query(value = "select * from notification where  notification_role='ROLE_ADMIN'", nativeQuery = true)
    public  List<Notification> notificationForAdmin();
}
