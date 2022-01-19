/*
 * @project restaurantApp
 * @fileName UserServiceImpl
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 24 12 2021 07:35 PM
 */
package com.restaurant.app.dao;

import com.restaurant.app.model.User;
import com.restaurant.app.repository.UserRepository;
import com.restaurant.app.model.Role;
import com.restaurant.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    //This function is used to save the user details
    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),registrationDto.getPhoneNo(),registrationDto.isStatus(), passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }
    //This function is used to find all users
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    //This function is used to find user by id
    public Optional<User> findById(Long id) throws  UsernameNotFoundException{
        Optional<User> user = userRepository.findById(id);
        return user;
    }
    //This function is used to delete user by id
    @Override
    public String deleteById(Long id) throws NumberFormatException{
        userRepository.deleteById(id);
        return "success";
    }
    //This function is used to update user id
    @Override
    public String updateById(User user){
        User user1=userRepository.findById(user.getId()).orElse(null);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNo(user.getPhoneNo());
        user1.setEmail(user.getEmail());
        user1.setStatus(user.isStatus());
        userRepository.save(user1);
        return "success";
    }
    //This function is used to get the user by email id
    @Override
    public User loadByEmailId(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email);
    }
    //This function is used to get the user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
    //This function is used to map the roles
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
