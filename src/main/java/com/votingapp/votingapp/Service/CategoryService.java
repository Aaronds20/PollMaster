package com.votingapp.votingapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingapp.votingapp.Model.Category;
import com.votingapp.votingapp.Model.Poll;
import com.votingapp.votingapp.Repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Poll getPollByCategoryId(Long categoryId) {
        return categoryRepository.findPollByCategoryId(categoryId);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
