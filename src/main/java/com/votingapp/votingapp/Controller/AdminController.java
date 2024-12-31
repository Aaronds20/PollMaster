package com.votingapp.votingapp.Controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.votingapp.votingapp.Model.Poll;
import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Service.PollService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PollService PollService;

    @GetMapping("/polllist")
    public String GetPollList(Model model, HttpSession httpSession) {
        // User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        List<Poll> pollsPage = PollService.findAllByPoll();
        model.addAttribute("Polls", pollsPage);
        return "polllist";
    }

    @GetMapping("/polls/{pollId}/results")
    public String viewPollResults(@PathVariable Long pollId, Model model) {
        Poll poll = PollService.getPollById(pollId);
        Map<String, Integer> results = PollService.getPollResults(pollId);
        model.addAttribute("poll", poll);
        model.addAttribute("results", results);
        return "pollresult";
    }

    @GetMapping("/newpoll")
    public String getAddPollForm(Model model, HttpSession httpSession) {
        model.addAttribute("PollData", new Poll());
        return "addnewpoll";
    }
    
    @PostMapping("/addpoll")
    public String addNewPoll(@Valid @ModelAttribute("PollData") Poll poll, BindingResult result, 
    Model model,RedirectAttributes redirectAttributes) {
        for (int i = 0; i < poll.getOptions().size(); i++) {
            if (poll.getOptions().get(i).getText().isEmpty()) {
                model.addAttribute("emptyoption", "*Option cannot be empty");
                return "addnewpoll";
            }
        }
        if (poll.getOptions().size() < 2) {
           model.addAttribute("lessoption", "*Please provide at least two options.");
            return "addnewpoll";
        }
        if (!result.hasErrors()) {
            PollService.SavePoll(poll);
            redirectAttributes.addFlashAttribute("success", "Poll has been added successfully");
            return "redirect:/admin/polllist";
        } else {
            return "addnewpoll";
        }
    }

    @GetMapping("/admindetails")
    public String getAdminDetails(Model model, HttpSession httpSession) {
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);
        return "admindetails";
    }

}
