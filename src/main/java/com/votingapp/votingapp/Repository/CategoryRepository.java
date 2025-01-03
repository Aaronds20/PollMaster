package com.votingapp.votingapp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.votingapp.votingapp.Model.Category;
import com.votingapp.votingapp.Model.Poll;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    @Query("SELECT p FROM Poll p WHERE p.category.id = :categoryId")
    Poll findPollByCategoryId(@Param("categoryId") Long categoryId);
    
}
