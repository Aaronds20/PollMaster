package com.pollmaster.pollmaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pollmaster.pollmaster.Model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll,Long> {

    
} 
