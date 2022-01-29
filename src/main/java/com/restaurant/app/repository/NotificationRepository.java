/*
 * @project ResturantApp
 * @fileName NotificationRepository
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 01 2022 10:47 PM
 */
package com.restaurant.app.repository;

import com.restaurant.app.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
