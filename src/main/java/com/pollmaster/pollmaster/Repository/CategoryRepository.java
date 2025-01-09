package com.pollmaster.pollmaster.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pollmaster.pollmaster.Model.Category;
import com.pollmaster.pollmaster.Model.Poll;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    @Query("SELECT p FROM Poll p WHERE p.category.id = :categoryId")
    Poll findPollByCategoryId(@Param("categoryId") Long categoryId);
    
}
