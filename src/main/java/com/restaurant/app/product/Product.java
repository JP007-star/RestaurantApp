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
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long productId;
    String productName;
    String productPrice;
    String productCategory;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    Boolean status;

    public Product() {
    }

    public Product(Long productId, String productName, String productPrice, String productCategory, String image, Boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.image = image;
        this.status = status;
    }

    public Product(String productId, String productName, String productCategory, String productPrice) {
        this.productId = Long.valueOf(productId);
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
