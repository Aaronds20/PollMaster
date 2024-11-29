package com.votingapp.votingapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votingapp.votingapp.Model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll,Integer> {

    
} 
