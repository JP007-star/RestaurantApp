/*
 * @project ResturantApp
 * @fileName Notification
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 01 2022 09:59 PM
 */
package com.restaurant.app.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  notificationId;
    private Long userId;
    private String  notificationHeading;
    private String  notificationSubHeading;
    private LocalDateTime notificationDateTime;
    private String  notificationRole;
    private boolean notificationSeen;

    public Notification() {
    }

    public Notification(String head, String sub, LocalDateTime orderDate, Boolean valueOf, String role_admin) {
        this.notificationHeading = head;
        this.notificationSubHeading = sub;
        this.notificationDateTime = orderDate;
        this.notificationSeen = valueOf;
        this.notificationRole = role_admin;
    }


    public Notification(String notificationHeading, String notificationSubHeading, LocalDateTime notificationDateTime, boolean notificationSeen, String notificationRole, Long userId) {
        this.notificationHeading = notificationHeading;
        this.notificationSubHeading = notificationSubHeading;
        this.notificationDateTime = notificationDateTime;
        this.notificationSeen = notificationSeen;
        this.notificationRole = notificationRole;
        this.userId=userId;
    }
    public String getNotificationRole() {
        return notificationRole;
    }

    public void setNotificationRole(String notificationRole) {
        this.notificationRole = notificationRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationHeading() {
        return notificationHeading;
    }

    public void setNotificationHeading(String notificationHeading) {
        this.notificationHeading = notificationHeading;
    }

    public String getNotificationSubHeading() {
        return notificationSubHeading;
    }

    public void setNotificationSubHeading(String notificationSubHeading) {
        this.notificationSubHeading = notificationSubHeading;
    }

    public LocalDateTime getNotificationDateTime() {
        return notificationDateTime;
    }

    public void setNotificationDateTime(LocalDateTime notificationDateTime) {
        this.notificationDateTime = notificationDateTime;
    }

    public boolean isNotificationSeen() {
        return notificationSeen;
    }

    public void setNotificationSeen(boolean notificationSeen) {
        this.notificationSeen = notificationSeen;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", notificationHeading='" + notificationHeading + '\'' +
                ", notificationSubHeading='" + notificationSubHeading + '\'' +
                ", notificationDateTime=" + notificationDateTime +
                ", notificationSeen=" + notificationSeen +
                '}';
    }
}
