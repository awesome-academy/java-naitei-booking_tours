package com.naite.bookingTour.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naite.bookingTour.model.User;
import com.naite.bookingTour.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private UserRepository userRepository;

    @GetMapping("/login")
	public String showLoginPage(Model model) {
		// Check if the model contains any flash attributes with the "error" key
		if (model.containsAttribute("error")) {
			// Retrieve the error message and add it to the model for rendering in the login
			// page
			String errorMessage = (String) model.getAttribute("error");
			model.addAttribute("errorMessage", errorMessage);
		}
		return "login";
	}

	@PostMapping("/checklogin")
	public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		// Perform authentication logic here (e.g., check username and password against
		// the database)
		User user = authenticate(username, password);

		if (user != null) {
			// Create a session and store user information
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			if (user.isAdmin()) {
				// Redirect to admin page 
				return "redirect:/admin";
			}
			else {
				// Redirect to the dashboard page
				return "redirect:/"; 
			}
		} else {
			redirectAttributes.addFlashAttribute("error", "Invalid username or password");
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// Invalidate the session and clear session attributes
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/login"; // Redirect to the login page after logout
	}

	private User authenticate(String username, String password) {
	  Optional<User> user = userRepository.authenticate(username,password);
	  if (user.isPresent()) {
		  return user.get();
	  }
	  else {
		  return null;
	  }
  }
}

