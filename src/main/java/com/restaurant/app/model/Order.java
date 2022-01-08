package com.restaurant.app.model;

import javax.persistence.*;

@Entity
@Table(name="orders",schema = "targetSchemaName")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    public Integer orderId;
    @Column(name = "product_ids")
    public String productIds;
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

    public Order(){

    }

    public Order(Integer orderId, String productIds, String productNames, String quantities, String prices, String total, String shippingAddress, String country, String state, int zip, Double grandTotal) {
        this.orderId = orderId;
        this.productIds = productIds;
        this.productNames = productNames;
        this.quantities = quantities;
        this.prices = prices;
        this.total = total;
        this.shippingAddress = shippingAddress;
        this.country = country;
        this.state = state;
        this.zip = zip;
        this.grandTotal = grandTotal;
    }

    public Order(String toString, String address, String country, String state, String zip, Double grandTotal) {
        this.productNames = toString;
        this.shippingAddress = address;
        this.country = country;
        this.state = state;
        this.zip = Integer.valueOf(zip);
        this.grandTotal = grandTotal;
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
                ", shippingAddress='" + shippingAddress + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", grandTotal=" + grandTotal +
                '}';
    }
}