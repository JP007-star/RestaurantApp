/*
 * @project ResturantApp
 * @fileName CartRepository
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 06 01 2022 10:44 AM
 */
package com.restaurant.app.repository;

import com.restaurant.app.model.Cart;
import com.restaurant.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByProductId(Long productId);
}
