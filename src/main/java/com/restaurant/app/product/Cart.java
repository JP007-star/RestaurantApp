/*
 * @project ResturantApp
 * @fileName Cart
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 04 01 2022 03:44 PM
 */
package com.restaurant.app.product;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Cart {
    Product product;
}
