package com.votingapp.votingapp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Model.Vote;
import com.votingapp.votingapp.Service.UserService;
import com.votingapp.votingapp.Service.VoteService;
import com.votingapp.votingapp.Util.Pager;

import jakarta.servlet.http.HttpSession;



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
        Page<Vote> canditates = voteService.findAllByPollPage(page);
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
            Vote vote = voteService.getPollById(candidate_id);
            userService.UpdateUser(loggedInUser,vote);
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

   
}

