package com.votingapp.votingapp.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.votingapp.votingapp.Model.Poll;
import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Service.PollService;
import com.votingapp.votingapp.Service.VoteService;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/user")
public class VoteController {
    
    @Autowired
    private VoteService voteService;
    @Autowired
    private PollService pollService;

    @GetMapping("/votepage")
    public String GetPollList(Model model,HttpSession httpSession){
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        if (loggedInUser.getVote()==null) {
        List<Poll> pollsPage = pollService.findAllByPoll();
        model.addAttribute("Polls", pollsPage);
        return "votepage";
        }
        model.addAttribute("AlreadyVoted", "You have already Voted");
        return "votepage";
    }

    @PostMapping("/updatevote")
    public String UpdateVote(@RequestParam Long optionId,Model model,HttpSession httpSession){
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        if (loggedInUser.getVote()==null) {
            voteService.UpdateVote(optionId,loggedInUser);
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

