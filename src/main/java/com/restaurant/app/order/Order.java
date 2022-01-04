package com.restaurant.app.order;

import javax.persistence.*;

@Entity
@Table(name="order")
public class Order{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long orderId;
    @Column(name = "product_ids")
    private String productIds;
    @Column(name = "quantities")
    private String  quantities;
    @Column(name = "prices")
    private String prices;
    @Column(name = "total")
    private int total;

    public Order(){

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Order(Long orderId, String productIds, String quantities, String prices, int total) {
        this.orderId = orderId;
        this.productIds = productIds;
        this.quantities = quantities;
        this.prices = prices;
        this.total = total;
    }
}