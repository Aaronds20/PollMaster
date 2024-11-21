package com.votingapp.votingapp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Service.UserService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute("RegisterData", new User());
        return "register";
    }

    @PostMapping("/register")
    public String signup(@Valid @ModelAttribute("RegisterData") User user,BindingResult result,Model model){
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("exist_email",  "There is already a user registered with the email provided");
            return "register";
        }
        
        if (!result.hasErrors()) {
            userService.SaveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
             return "login";
        }else{
            return "register";
        } 
        }


}
