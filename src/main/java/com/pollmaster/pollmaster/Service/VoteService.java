package com.pollmaster.pollmaster.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pollmaster.pollmaster.Model.Options;
import com.pollmaster.pollmaster.Model.User;
import com.pollmaster.pollmaster.Model.Vote;
import com.pollmaster.pollmaster.Repository.OptionRepository;
import com.pollmaster.pollmaster.Repository.VoteRepository;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private OptionRepository optionRepository;


    public void UpdateVote(Long categoryId, Long optionId, User loggedInUser) {
        Options option = optionRepository.findById(optionId).orElseThrow(() -> new RuntimeException("Option not found"));
        Vote vote = new Vote();
        vote.setOption(option);
        vote.setUser(loggedInUser);
        vote.setCategory(option.getPoll().getCategory());
        voteRepository.save(vote);
    }

}
