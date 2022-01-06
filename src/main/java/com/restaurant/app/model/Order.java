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
    @Column(name = "quantities")
    public String  quantities;
    @Column(name = "prices")
    public String prices;
    @Column(name = "totals")
    public String totals;
    public Double grandTotal;

    public Order(){

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

    public String getTotal() {
        return totals;
    }

    public void setTotal(String totals) {
        this.totals = totals;
    }

    public Order(String productIds, String quantities, String prices, String totals,Double grandTotal) {
        this.productIds = productIds;
        this.quantities = quantities;
        this.prices = prices;
        this.totals = totals;
        this.grandTotal=grandTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productIds='" + productIds + '\'' +
                ", quantities='" + quantities + '\'' +
                ", prices='" + prices + '\'' +
                ", totals='" + totals + '\'' +
                ", grandTotal=" + grandTotal +
                '}';
    }
}