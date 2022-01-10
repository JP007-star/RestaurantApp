/*
 * @project ResturantApp
 * @fileName Sales
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 10 01 2022 02:50 PM
 */
package com.restaurant.app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long saleId;
    String productId;
    String productName;
    private int quantity;
    public LocalDateTime orderDate;

    public Sale() {
    }

    public Sale(String productId, String productName, int quantity, LocalDateTime orderDate) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }



    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
