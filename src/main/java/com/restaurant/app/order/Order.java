package com.restaurant.app.order;

import javax.persistence.*;

@Entity
public class Order{
    private Long orderId;
    private Long productIds;
    private Long quantities;
    private int total;
    private Long prices;

    public Order(){

    }

    public Order(Long orderId,Long productIds,Long quantities,int total,Long prices){
        this.orderId=orderId;
        this.productIds=productIds;
        this.quantities=quantities;
        this.total=total;
        this.prices=prices;
    }

    public Long getorderid(){
        return orderId;
    }

    public void setorderid(Long orderId){
        this.orderId=orderId;
    }

    public Long getproductid(){
        return productIds;
    }

    public void setproductid(Long productIds){
        this.productIds=productIds;
    }

    public Long getquantities(){
        return quantities;
    } 

    public void setquantities(Long quantities){
        this.quantities=quantities;
    } 

    public int gettotal(){
        return total;
    }

    public void settotal(int total){
        this.total=total;
    }

    public Long getprices(){
        return prices;
    }

    public void setprices(Long prices){
        this.prices=prices;
    }
}