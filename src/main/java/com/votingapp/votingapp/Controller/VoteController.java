package com.votingapp.votingapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/pollspage")
    public String GetPollList(Model model, HttpSession httpSession) {
        // User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        List<Poll> pollsPage = pollService.findAllByPoll();
        model.addAttribute("Polls", pollsPage);
        return "pollspage";
    }

    @GetMapping("/getpoll/{id}")
public String getPoll(@PathVariable Long id, Model model, HttpSession httpSession) {
    User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
    boolean hasVoted = pollService.hasUserVoted(id, loggedInUser.getId());
    if (!hasVoted) {
        Poll poll = pollService.getPollById(id);
        model.addAttribute("poll", poll);
        return "votepage";
    }
    model.addAttribute("message", "You have already voted");
    return "votepage";
}


    @PostMapping("/polls/{pollId}/vote")
    public String UpdateVote(@PathVariable Long pollId, @RequestParam(required = false) Long optionId, 
    Model model,HttpSession httpSession,RedirectAttributes redirectAttributes) {
        if (optionId == null) {
            Poll poll = pollService.getPollById(pollId);
            model.addAttribute("poll", poll);
            model.addAttribute("errorMessage", "*Please select an option before submitting.");
            return "votepage"; // Return to the voting page
        }
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        voteService.UpdateVote(optionId, loggedInUser);
        redirectAttributes.addFlashAttribute("successMessage", "Voted Succesfully");
        return "redirect:/user/pollspage";
    }

    @GetMapping("/userdetails")
    public String getUserDetails(Model model, HttpSession httpSession) {
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);
        return "userdetails";
    }

}
