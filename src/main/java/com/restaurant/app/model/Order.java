package com.restaurant.app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="orders",schema = "targetSchemaName")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    public Integer orderId;
    @Column(name = "product_ids")
    public String productIds;
    @Column(name = "user_name")
    public String userName;
    @Column(name = "product_names")
    public String productNames;
    @Column(name = "quantities")
    public String  quantities;
    @Column(name = "prices")
    public String prices;
    @Column(name = "totals")
    public String total;
    public String shippingAddress;
    public String country;
    public String state;
    public int zip;
    public Double grandTotal;
    public LocalDate orderDate;

    public Order(){

    }


    public Order(Integer orderId, String productIds, String userName, String productNames, String quantities, String prices, String total, String shippingAddress, String country, String state, int zip, Double grandTotal, LocalDate orderDate) {
        this.orderId = orderId;
        this.productIds = productIds;
        this.userName = userName;
        this.productNames = productNames;
        this.quantities = quantities;
        this.prices = prices;
        this.total = total;
        this.shippingAddress = shippingAddress;
        this.country = country;
        this.state = state;
        this.zip = zip;
        this.grandTotal = grandTotal;
        this.orderDate = orderDate;
    }


    public Order(String toString,String userId, String toString1, String toString2, String toString3, String toString4, String address, String country, String state, String zip, Double grandTotal, LocalDate orderDate) {
        this.productIds = toString;
        this.userName=userId;
        this.productNames = toString1;
        this.quantities = toString2;
        this.prices = toString3;
        this.total = toString4;
        this.shippingAddress = address;
        this.country = country;
        this.state = state;
        this.zip = Integer.valueOf(zip);
        this.grandTotal = grandTotal;
        this.orderDate = orderDate;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public String getQuantities() {
        return quantities;
    }

    public void setQuantities(String quantities) {
        this.quantities = quantities;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productIds='" + productIds + '\'' +
                ", userId='" + userName + '\'' +
                ", productNames='" + productNames + '\'' +
                ", quantities='" + quantities + '\'' +
                ", prices='" + prices + '\'' +
                ", total='" + total + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", grandTotal=" + grandTotal +
                ", orderDate=" + orderDate +
                '}';
    }
}