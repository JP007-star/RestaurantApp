/*
 * @project ResturantApp
 * @fileName Dish
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 28 12 2021 06:28 PM
 */
package com.restaurant.app.dishes;

public class Dish {
    Long id;
    String dishName;
    String price;
    String category;
    boolean status;

    public Dish(Long id, String dishName, String price, String category, boolean status) {
        this.id = id;
        this.dishName = dishName;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
