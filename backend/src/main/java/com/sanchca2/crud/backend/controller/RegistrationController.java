package com.sanchca2.crud.backend.controller;

import com.sanchca2.crud.backend.entity.AppUser;
import com.sanchca2.crud.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        System.out.println("Reached the register page");
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute AppUser user) {
        System.out.println("Reached the PostMapping");
        log.info("Registering user with email: {}", user.getEmail());
        if (user.getEmail() == null || user.getPassword() == null || user.getName() == null) {
            log.error("User data is incomplete: {}", user);
            return "register";
        }
        try {
            userService.save(user); // Save the new user to the database
            log.info("User registration completed for email: {}", user.getEmail());
        } catch (Exception e) {
            System.out.print(e);
            log.error("Error saving user: {}", e.getMessage());
        }
        System.out.println("Moving to login");
        return "redirect:/login";
    }

}
