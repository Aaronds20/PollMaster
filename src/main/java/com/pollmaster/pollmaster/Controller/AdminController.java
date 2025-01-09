package com.pollmaster.pollmaster.Controller;

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

import com.pollmaster.pollmaster.Model.Category;
import com.pollmaster.pollmaster.Model.Poll;
import com.pollmaster.pollmaster.Model.User;
import com.pollmaster.pollmaster.Service.CategoryService;
import com.pollmaster.pollmaster.Service.PollService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PollService PollService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/polllist")
    public String GetPollList(Model model, HttpSession httpSession) {
        // User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        List<Category> Categories = categoryService.getAllCategories();
        model.addAttribute("Categories", Categories);
        return "polllist";
    }

    @GetMapping("/polls/{c_id}/results")
    public String viewPollResults(@PathVariable Long c_id, Model model,RedirectAttributes redirectAttributes) {
        Poll poll = categoryService.getPollByCategoryId(c_id);
        if (poll == null) {
            redirectAttributes.addFlashAttribute("error", "No poll found for this category");
            return "redirect:/admin/polllist";   
        }
        Category category = categoryService.getCategoryById(c_id);
        Map<String, Integer> results = PollService.getPollResults(poll.getId());
        model.addAttribute("category", category);
        model.addAttribute("poll", poll);
        model.addAttribute("results", results);
        return "pollresult";
    }

    @GetMapping("/newpoll")
    public String getAddPollForm(Model model, HttpSession httpSession) {
        List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("Categories", allCategories);
        model.addAttribute("PollData", new Poll());
        return "addnewpoll";
    }
    
    @PostMapping("/addpoll")
    public String addNewPoll(@Valid @ModelAttribute("PollData") Poll poll, BindingResult result, 
    Model model,RedirectAttributes redirectAttributes) {
        for (int i = 0; i < poll.getOptions().size(); i++) {
            if (poll.getOptions().get(i).getText().isEmpty()) {
                List<Category> allCategories = categoryService.getAllCategories();
                model.addAttribute("Categories", allCategories);
                model.addAttribute("emptyoption", "*Option cannot be empty");
                return "addnewpoll";
            }
        }
        if (poll.getOptions().size() < 2) {
            List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("Categories", allCategories);
           model.addAttribute("lessoption", "*Please provide at least two options.");
            return "addnewpoll";
        }
        if (!result.hasErrors()) {
            PollService.SavePoll(poll);
            redirectAttributes.addFlashAttribute("success", "Poll has been added successfully");
            return "redirect:/admin/polllist";
        } else {
            List<Category> allCategories = categoryService.getAllCategories();
            model.addAttribute("Categories", allCategories);
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
