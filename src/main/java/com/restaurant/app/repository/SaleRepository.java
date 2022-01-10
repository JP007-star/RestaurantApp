/*
 * @project ResturantApp
 * @fileName SaleRepository
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 10 01 2022 02:59 PM
 */
package com.restaurant.app.repository;

import com.restaurant.app.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.TreeMap;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    @Query(value = "select sum(quantity)  from sales where product_name= :productName and dayname(order_date) = :day", nativeQuery = true)
    public Integer orderQuantityDetails(@Param("productName") String productName,@Param("day")String day);
}
