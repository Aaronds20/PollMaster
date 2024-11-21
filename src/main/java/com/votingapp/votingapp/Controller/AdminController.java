package com.votingapp.votingapp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Model.Vote;
import com.votingapp.votingapp.Service.VoteService;
import com.votingapp.votingapp.Util.Pager;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VoteService voteService;
    
    @GetMapping("/candidatelist")
    public String GetCanditateCount(@RequestParam(defaultValue = "0") int page,Model model,HttpSession httpSession){
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        Page<Vote> canditates = voteService.findAllByCandidate(page);
        Pager pager = new Pager(canditates);
        model.addAttribute("pager", pager);
        model.addAttribute("User", loggedInUser);
        return "candidatelist";
    }
}
