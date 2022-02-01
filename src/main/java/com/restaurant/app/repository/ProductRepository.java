/*
 * @project ResturantApp
 * @fileName ProductRepository
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 29 12 2021 12:28 PM
 */
package com.restaurant.app.repository;

import com.restaurant.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    @Query(value = "select quantity from  products", nativeQuery = true)
    public List<String> findAllQuantity();
    @Query(value = "select product_name from  products", nativeQuery = true)
    public List<String> findAllProductName();
    @Query(value = "select * from  products where status=1", nativeQuery = true)
    public List<Product> findAllActiveProduct();
    @Query(value = "select * from  products where status=1 and product_category='special launch'", nativeQuery = true)
    public List<Product> findAllActiveDisplayProduct();
    @Query(value = "select * from  products where status=1 and product_category='Main course'", nativeQuery = true)
    public List<Product> findAllActiveMainCourse();
    @Query(value = "select * from  products where status=1 and product_category='Snacks'", nativeQuery = true)
    public List<Product> findAllActiveSnacks();
    @Query(value = "select * from  products where status=1 and product_category='Milkshake'", nativeQuery = true)
    public List<Product> findAllActiveMilkshake();
    @Query(value = "select * from  products where status=1 and product_category='Juice'", nativeQuery = true)
    public List<Product> findAllActiveJuice();
    @Query(value = "select * from  products where status=1 and product_category='Soup'", nativeQuery = true)
    public List<Product> findAllActiveSoup();
    @Query(value = "select * from  products where status=1 and product_name like :productName%", nativeQuery = true)
    public List<Product> findAllActiveProductByName(@Param("productName")String productName);

}
