package com.lifestyle.mvc;

import com.lifestyle.dto.LoginRequest;
import com.lifestyle.dto.RegisterRequest;
import com.lifestyle.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lifestyle.service.AuthService;

@Controller
public class AuthController {
    private final AuthService auth;
    public AuthController(AuthService auth){ this.auth = auth; }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginRequest") LoginRequest req,
                        BindingResult result, HttpSession session, Model model){
        if(result.hasErrors()) return "auth/login";
        try{
            User u = auth.login(req.getEmail(), req.getPassword());
            session.setAttribute("userId", u.getId());
            session.setAttribute("fullName", u.getFullName());
            session.setAttribute("role", u.getRole().name());
            return "redirect:/";
        } catch (Exception ex){
            model.addAttribute("error", "Invalid credentials");
            return "auth/login";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("registerRequest", new RegisterRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerRequest") RegisterRequest req,
                           BindingResult result, Model model){
        if(result.hasErrors()) return "auth/register";
        try{
            auth.register(req);
            model.addAttribute("msg", "Registration successful. Please login.");
            return "auth/login";
        } catch (Exception ex){
            model.addAttribute("error", ex.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}