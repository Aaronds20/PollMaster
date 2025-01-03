package com.votingapp.votingapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingapp.votingapp.Model.Options;
import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Model.Vote;
import com.votingapp.votingapp.Repository.OptionRepository;
import com.votingapp.votingapp.Repository.VoteRepository;

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
