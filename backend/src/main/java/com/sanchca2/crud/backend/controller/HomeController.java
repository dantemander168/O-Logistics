package com.sanchca2.crud.backend.controller;

import com.sanchca2.crud.backend.entity.AppUser;
import com.sanchca2.crud.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        AppUser user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            model.addAttribute("username", user.getName());
        } else {
            model.addAttribute("username", "Anonymous");
        }

        return "home";
    }
}
