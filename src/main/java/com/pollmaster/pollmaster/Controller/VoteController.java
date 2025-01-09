package com.pollmaster.pollmaster.Controller;

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

import com.pollmaster.pollmaster.Model.Category;
import com.pollmaster.pollmaster.Model.Poll;
import com.pollmaster.pollmaster.Model.User;
import com.pollmaster.pollmaster.Service.CategoryService;
import com.pollmaster.pollmaster.Service.PollService;
import com.pollmaster.pollmaster.Service.VoteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class VoteController {

    @Autowired
    private VoteService voteService;
    @Autowired
    private PollService pollService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/pollspage")
    public String GetPollList(Model model, HttpSession httpSession) {
        // User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        List<Category> Categories = categoryService.getAllCategories();
        model.addAttribute("Categories", Categories);
        return "pollspage";
    }

    @GetMapping("/getpoll/{c_id}")
public String getPoll(@PathVariable Long c_id, Model model, HttpSession httpSession,RedirectAttributes redirectAttributes) {
    User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
    Poll poll = categoryService.getPollByCategoryId(c_id);
    if (poll == null) {
        redirectAttributes.addFlashAttribute("error", "No poll found for this category");
        return "redirect:/user/pollspage";   
    }
    boolean hasVoted = pollService.hasUserVoted(poll.getId(), loggedInUser.getId(),c_id);
    if (!hasVoted) {
        Category category = categoryService.getCategoryById(c_id);
        model.addAttribute("category", category);
        model.addAttribute("poll", poll);
        return "votepage";
    }
    model.addAttribute("message", "You have already voted");
    return "votepage";
}


    @PostMapping("/polls/{pollId}/vote")
    public String UpdateVote(@PathVariable Long pollId, @RequestParam(required = false) Long optionId, @RequestParam(required = false) Long categoryId, 
    Model model,HttpSession httpSession,RedirectAttributes redirectAttributes) {
        if (optionId == null) {
            Category category = categoryService.getCategoryById(categoryId);
            Poll poll = pollService.getPollById(pollId);
            model.addAttribute("poll", poll);
            model.addAttribute("category", category);
            model.addAttribute("errorMessage", "*Please select an option before submitting.");
            return "votepage"; // Return to the voting page
        }
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        voteService.UpdateVote(categoryId,optionId, loggedInUser);
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
