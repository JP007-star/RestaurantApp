package com.restaurant.app.user;


import com.restaurant.app.user.UserRegistrationDto;
import com.restaurant.app.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class UserRegistrationController {

	private UserService userService;

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
	
	@PostMapping("save")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		System.out.println(registrationDto);
		return "redirect:/admin/users?success";
	}
	@GetMapping("/users")
	public String fetchUsers(Model model){
		List<User> userList=userService.findAll();
		model.addAttribute("users",userList);
		return "users";
	}
	@GetMapping("/editUser")
	public String fetchUser(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
		Long userId=Long.parseLong(request.getParameter("userId"));
		System.out.println(userId);
		Optional<User> user=userService.findById(userId);
		System.out.println(user);
		model.addAttribute("user",user);
		return "redirect:/registration/users/?success";
	}
	@PostMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request)throws NumberFormatException {
		Long userId=Long.parseLong(request.getParameter("userId"));
		String msg=userService.deleteById(userId);
		return "redirect:/admin/users?success";
	}



}
