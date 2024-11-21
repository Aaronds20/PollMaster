package com.votingapp.votingapp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Model.Vote;
import com.votingapp.votingapp.Service.UserService;
import com.votingapp.votingapp.Service.VoteService;
import com.votingapp.votingapp.Util.Pager;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/user")
public class VoteController {
    
    @Autowired
    private VoteService voteService;
    @Autowired
    private UserService userService;

    @GetMapping("/votepage")
    public String GetCanditateList(@RequestParam(defaultValue = "0") int page,Model model,HttpSession httpSession){
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        if (loggedInUser.getVote()==null) {
        Page<Vote> canditates = voteService.findAllByCandidate(page);
        Pager pager = new Pager(canditates);
        model.addAttribute("pager", pager);
        return "votepage";
        }
        model.addAttribute("AlreadyVoted", "You have already Voted");
        return "votepage";
    }

    @PostMapping("/updatevote")
    public String UpdateVote(@RequestParam("candidate") int candidate_id,
    @RequestParam(defaultValue = "0") int page,Model model,HttpSession httpSession){
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        if (loggedInUser.getVote()==null) {
            Vote vote = voteService.getCanditateById(candidate_id);
            userService.UpdateUser(loggedInUser,vote);
            voteService.UpdateVote(vote);
            model.addAttribute("success", "Voted Succesfully");
            return "votepage";
        }else{
            model.addAttribute("AlreadyVoted", "You have already Voted");
            return "votepage";
        }
    }

    @GetMapping("/userdetails")
        public String getUserDetails(Model model,HttpSession httpSession){
            User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
            model.addAttribute("user", loggedInUser);
            return "userdetails";    
        }

    @GetMapping("/updateuserdetails/{id}")
    public String updateUser(@PathVariable("id") int id,Model model){
        User user = userService.GetUserById(id);
        model.addAttribute("user", user);
        return "edit_userform";
    }

    @PostMapping("/updateuserdetails/{id}")
    public String updateDetails(@PathVariable("id") int id,
    @Valid User user,BindingResult result,Model model,HttpSession httpSession){
        if (user.getC_password() == null || user.getC_password().isEmpty()) {
            result.rejectValue("c_password", "error.c_password", "Please fill in the confirm password field.");
            return "edit_userform";
        }
        if(!user.getPassword().equals(user.getC_password())){
            model.addAttribute("InvalidPassword",  "Password did not Match!");
            return "edit_userform";
        }
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("exist_email",  "There is already a user registered with the email provided");
            return "edit_userform";
        }
        if (!result.hasErrors()) {
            User exist_user = userService.GetUserById(id);
            exist_user.setUsername(user.getUsername());
            exist_user.setEmail(user.getEmail());
            exist_user.setPhoneno(user.getPhoneno());
            exist_user.setPassword(user.getPassword());
            userService.SaveUser(exist_user);
            model.addAttribute("updateMessage", "User has been registered successfully");
            httpSession.setAttribute("loggedInUser", exist_user);
            return "userdetails";
        }else{
            return "edit_userform";
        } 
        }


}

