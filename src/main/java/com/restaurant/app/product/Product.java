/*
 * @project ResturantApp
 * @fileName Product
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 12 2021 12:22 PM
 */
package com.restaurant.app.product;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    String productName;
    String productPrice;
    String productCategory;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    Boolean status;

    public Product() {
    }

    public Product(Long id, String productName, String productPrice, String productCategory, String image, Boolean status) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.image = image;
        this.status = status;
    }

    public Product(String productId, String productName, String productCategory, String productPrice) {
        this.id = Long.valueOf(productId);
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
