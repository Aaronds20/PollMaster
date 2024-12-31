package com.votingapp.votingapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votingapp.votingapp.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
