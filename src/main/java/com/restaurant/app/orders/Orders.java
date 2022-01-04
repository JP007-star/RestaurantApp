package com.restaurant.app.product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name="Order")

public class orders{
    @Id
    private long order_id;
    private long product_id;
    private int quantiy;
    private long total;

    public orders(){
    }

    public orders(long order_id, long product_id, int quantity, long total){
        this.order_id=order_id;
        this.product_id=product_id;
        this.quantity=quantity;
        this.total=total;
    }

    public void getorder_id(){
        return order_id;
    }

    public void setorder_id(long order_id){
        return this.order_id=order_id;
    }

    public void getproduct_id(){
        return product_id;
    }

    public void setproduct_id(long product_id){
        return this.product_id=product_id;
    }

    public void getquantity(){
        return quantity;
    }

    public void setquantity(int quantity){
        return this.quantity=quantity;
    }
    public void gettotal(){
        return total;
    }

    public void settotal(long total){
        return this.total=total;
    }

}