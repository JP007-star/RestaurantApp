/*
 * @project restaurantApp
 * @fileName UserService
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 24 12 2021 07:37 PM
 */
package com.restaurant.app.service;

import com.restaurant.app.dao.UserRegistrationDto;
import com.restaurant.app.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    List<User> findAll();
    Optional<User> findById(Long id);

    String deleteById(Long id) ;

    String updateById(User user);

    User loadByEmailId(String email) throws UsernameNotFoundException;

}
