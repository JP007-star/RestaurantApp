package com.restaurant.app.controller;


import com.restaurant.app.service.CartService;
import com.restaurant.app.utility.Counter;
import com.restaurant.app.dao.UserRegistrationDto;
import com.restaurant.app.model.User;
import com.restaurant.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserRegistrationController {

	private UserService userService;
	@Autowired
	CartService cartService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	//This function is used to save the user details
	@PostMapping("save")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		System.out.println(registrationDto);
		return "redirect:/admin/user/users";
	}
	//This function is used to fetch users
	@GetMapping("/users")
	public String fetchUsers(Model model, HttpSession session){
		List<User> userList=userService.findAll();
		long cartCount=cartService.count();
		String userName = String.valueOf(session.getAttribute("userName"));
		model.addAttribute("cartCount",cartCount);
		model.addAttribute("users",userList);
		model.addAttribute("counter",new Counter());
		model.addAttribute("userName",userName);
		return "users";
	}
	//This function is used to edit users details
	@PostMapping("/editUser")
	public  ResponseEntity<?> fetchUser(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
		Long userId=Long.parseLong(request.getParameter("userId"));
		System.out.println(userId);
		Optional<User> user=userService.findById(userId);
		System.out.println(user);
		Optional<User> result;
		if(user==null) {
			result=null;
		}
		else {
			result = user;
		}
		return ResponseEntity.ok(result);
	}
	//This function is used to update user
	@PostMapping("/updateUser")
	public  String updateUser(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
		String userId=request.getParameter("userId");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String  phoneNo=request.getParameter("phoneNo");
		boolean  status= Boolean.parseBoolean(request.getParameter("status"));
		User user =new User(userId,firstName,lastName,email,phoneNo,status);
		System.out.println(user);
		String msg=userService.updateById(user);
		System.out.println(msg);
		String result;
		if(msg==null) {
			result=null;
		}
		else {
			result = msg;
		}
		return "redirect:/admin/user/users";
	}
	//This function is used to delete user
	@PostMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request)throws NumberFormatException {
		Long userId=Long.parseLong(request.getParameter("userId"));
		String msg=userService.deleteById(userId);
		return "redirect:/admin/user/users";
	}



}
