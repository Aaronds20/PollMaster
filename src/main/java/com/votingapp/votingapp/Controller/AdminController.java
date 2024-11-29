package com.votingapp.votingapp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.votingapp.votingapp.Model.Poll;
import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Model.Vote;
import com.votingapp.votingapp.Service.PollService;
import com.votingapp.votingapp.Service.VoteService;
import com.votingapp.votingapp.Util.Pager;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VoteService VoteService;
    private PollService PollService;
    
    @GetMapping("/polllist")
    public String GetPollCount(@RequestParam(defaultValue = "0") int page,Model model,HttpSession httpSession){
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        Page<Vote> canditates = VoteService.findAllByPollPage(page);
        Pager pager = new Pager(canditates);
        model.addAttribute("pager", pager);
        model.addAttribute("User", loggedInUser);
        return "polllist";
    }

    @GetMapping("/newpoll")
    public String getAddPollForm(Model model,HttpSession httpSession) {
        User loggedInUser = (User)httpSession.getAttribute("loggedInUser");
        model.addAttribute("PollData", new Poll());
        model.addAttribute("User", loggedInUser);
        return "addnewpoll";
    }

    @PostMapping("/addpoll")
    public String AddnewPoll(@Valid @ModelAttribute("PollData") Poll poll,BindingResult result,Model model) {
        if (!result.hasErrors()) {
            PollService.SavePoll(poll);
            model.addAttribute("success", "Poll has been added successfully");
             return "polllist";
        }else{
            return "addnewpoll";
        } 
    }
    
    
}
