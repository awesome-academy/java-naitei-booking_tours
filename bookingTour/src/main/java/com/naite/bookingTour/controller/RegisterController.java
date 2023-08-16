package com.naite.bookingTour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.naite.bookingTour.model.User;
import com.naite.bookingTour.service.UserManagementService;

import jakarta.validation.Valid;

import java.util.Optional;

@Controller
public class RegisterController {
    @Autowired
    private final UserManagementService userManagementService;

    public RegisterController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        Optional<User> existingUserEmail = userManagementService.findUserByEmail(user.getEmail());

        if (existingUserEmail.isPresent()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (!user.isPasswordMatching()) {
            result.rejectValue("repeatPassword", null, "Passwords do not match");
        }

        if (!user.isPasswordEnoughLength()) {
            result.rejectValue("password", null, "Password must be at least 6 characters long");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userManagementService.saveUser(user);
        return "redirect:/register?success";
    }
}
