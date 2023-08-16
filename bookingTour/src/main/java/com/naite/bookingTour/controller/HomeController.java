package com.naite.bookingTour.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.naite.bookingTour.model.User;
import com.naite.bookingTour.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository; // Assuming you have a UserRepository to retrieve the user's full name

	@GetMapping("/")
	public String showDashboard(HttpServletRequest request, Model model) {
		// Check if the user is authenticated (session has the username attribute)
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			return "home"; // Redirect to the login page if not authenticated
		}

		Optional<User> userOptional = userRepository.findByUsername((String) session.getAttribute("username")); // Retrieve the user's full name from the service or repository
		User user = userOptional.get();
		model.addAttribute("fullname", user.getFullname());

		boolean isAdmin = user.getRole() == User.Role.ADMIN;
		model.addAttribute("isAdmin", isAdmin);

		return isAdmin ? "admin/landing_page" : "home";
	}


}