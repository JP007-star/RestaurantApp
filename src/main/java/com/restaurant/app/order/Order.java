package com.restaurant.app.order;

import javax.persistence.*;

@Entity
@Table(name="orders",schema = "targetSchemaName")
public class Order{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer orderId;
    @Column(name = "product_ids")
    String productIds;
    @Column(name = "quantities")
    String  quantities;
    @Column(name = "prices")
    String prices;
    @Column(name = "total")
    int total;

    public Order(){

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = Math.toIntExact(orderId);
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

    public Order(Integer orderId, String productIds, String quantities, String prices, int total) {
        this.orderId = Math.toIntExact(orderId);
        this.productIds = productIds;
        this.quantities = quantities;
        this.prices = prices;
        this.total = total;
    }
}