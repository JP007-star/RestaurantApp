package com.restaurant.app.controller;


import com.restaurant.app.model.Notification;
import com.restaurant.app.service.CartService;
import com.restaurant.app.service.NotificationService;
import com.restaurant.app.utility.Counter;
import com.restaurant.app.dao.UserRegistrationDto;
import com.restaurant.app.model.User;
import com.restaurant.app.service.UserService;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;


@Controller
@RequestMapping("/admin/user")
public class UserRegistrationController {

	private UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	NotificationService notificationService;

	private static final String TOPIC = "Kafka_restApp_User_activity_1";
	private final String PARTITION1="1" ;
	private final String PARTITION2="2" ;
	private final String PARTITION3="3" ;

	private final List<String> messages1 = new ArrayList<>();
	private final List<String> messages2 = new ArrayList<>();
	private final List<String> messages3 = new ArrayList<>();


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
		return "redirect:/admin/user/users";
	}

	@GetMapping("/users")
	public String fetchUsers(Model model, HttpSession session) {
		List<User> userList = userService.findAll();
		long cartCount = cartService.count();
		long notificationCount=notificationService.notificationCountForAdmin();
		String userName = String.valueOf(session.getAttribute("userName"));
		model.addAttribute("cartCount", cartCount);
		model.addAttribute("notificationCount", notificationCount);
		model.addAttribute("users", userList);
		model.addAttribute("counter", new Counter());
		model.addAttribute("userName", userName);
		return "users";
	}

	@PostMapping("/editUser")
	public ResponseEntity<?> fetchUser(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
		Long userId = Long.parseLong(request.getParameter("userId"));
		System.out.println(userId);
		Optional<User> user = userService.findById(userId);
		System.out.println(user);
		Optional<User> result;
		if (user == null) {
			result = null;
		} else {
			result = user;
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/updateUser")
	public String updateUser(HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
		String userId = request.getParameter("userId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNo = request.getParameter("phoneNo");
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		User user = new User(userId, firstName, lastName, email, phoneNo, status);
		System.out.println(user);
		String msg = userService.updateById(user);
		System.out.println(msg);
		String result;
		if (msg == null) {
			result = null;
		} else {
			result = msg;
		}
		return "redirect:/admin/user/users";
	}

	@PostMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request) throws NumberFormatException {
		Long userId = Long.parseLong(request.getParameter("userId"));
		String msg = userService.deleteById(userId);
		return "redirect:/admin/user/users";
	}

	@GetMapping("/userActivity")
	public ResponseEntity<List<String>> userActivity(HttpServletRequest request) {
//		PARTITION= request.getParameter("userId");
		String userId= request.getParameter("userId");
		boolean areEqual=userId.equals(PARTITION1);
		if(areEqual) {
			System.out.println(userId+"equal");
			return ResponseEntity.ok(messages1);
		} else if(userId.equals(PARTITION2)) {
			System.out.println(userId + "equal");
			return ResponseEntity.ok(messages2);
		}else{
			System.out.println(userId+"not equal");
			return ResponseEntity.ok(messages3);
		}
	}
	@PostMapping("/fetchNotification")
	public ResponseEntity<?> fetchNotification(){
		List<Notification> notificationList=notificationService.notificationForAdmin();
		return ResponseEntity.ok(notificationList);
	}

//	@KafkaListener(containerFactory = "kafkaListenerContainerFactory", groupId = "group_id", topicPartitions = @TopicPartition(topic = TOPIC,partitionOffsets = @PartitionOffset(partition = PARTITION1,initialOffset = "0")))
//	public void consume1(String message) {
//		System.out.println(message);
//		synchronized (messages1) {
//			messages1.add(String.valueOf(message));
//		}
//	}
//	@KafkaListener(containerFactory = "kafkaListenerContainerFactory", groupId = "group_id", topicPartitions = @TopicPartition(topic = TOPIC, partitionOffsets = @PartitionOffset(partition = PARTITION2,initialOffset = "0")))
//	public void consume2(String message) {
//		System.out.println(message);
//		synchronized (messages2) {
//			messages2.add(String.valueOf(message));
//		}
//	}
//	@KafkaListener(containerFactory = "kafkaListenerContainerFactory", groupId = "group_id", topicPartitions = @TopicPartition(topic = TOPIC, partitionOffsets = @PartitionOffset(partition = PARTITION3,initialOffset = "0")))
//	public void consume3(String message) {
//		System.out.println(message);
//		synchronized (messages3) {
//			messages3.add(String.valueOf(message));
//		}
//	}
}


