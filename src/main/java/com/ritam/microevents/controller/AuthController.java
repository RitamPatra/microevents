package com.ritam.microevents.controller;

import com.ritam.microevents.model.UserAccount;
import com.ritam.microevents.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserRepository userRepo;
    public AuthController(UserRepository userRepo) { this.userRepo = userRepo; }

    @GetMapping("/login")
    public String loginForm() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        UserAccount user = userRepo.findByUsername(username).orElseGet(() -> {
            UserAccount u = new UserAccount(username, password); // only for dev version
            return userRepo.save(u);
        });

        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid password");
            return "login";
        }

        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        return "redirect:/events";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

