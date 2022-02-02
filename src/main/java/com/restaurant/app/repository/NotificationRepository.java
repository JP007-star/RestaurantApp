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
    @Query(value = "select * from notification  where  notification_role='ROLE_USER' and user_id= :user_id", nativeQuery = true)
    public  List<Notification> notificationForUsers(@Param("user_id") Long userId);

    @Query(value = "select * from notification where  notification_role='ROLE_ADMIN'", nativeQuery = true)
    public  List<Notification> notificationForAdmin();

    @Query(value = "select count(*) from notification where  notification_role='ROLE_USER' and user_id= :user_id", nativeQuery = true)
    public  long notificationCountForUsers(@Param("user_id") Long userId);

    @Query(value = "select count(*) from notification where  notification_role='ROLE_ADMIN'", nativeQuery = true)
    public  long notificationCountForAdmin();

}
