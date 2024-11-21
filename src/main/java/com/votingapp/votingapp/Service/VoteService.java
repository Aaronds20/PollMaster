package com.votingapp.votingapp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.votingapp.votingapp.Model.Vote;
import com.votingapp.votingapp.Repository.VoteRepository;

@Service
public class VoteService {
    
    @Autowired
    private VoteRepository voteRepository;

    public void UpdateVote(Vote vote){
        vote.setCount(vote.getCount()+1);
        voteRepository.saveAndFlush(vote);
    }

    public Page<Vote> findAllByCandidate(int page) {
        return voteRepository.findAll(PageRequest.of(subtractPageByOne(page), 4));
    }
    
    public Vote getCanditateById(int id){
        Vote vote = voteRepository.findById(id);
        return vote;
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }


}
