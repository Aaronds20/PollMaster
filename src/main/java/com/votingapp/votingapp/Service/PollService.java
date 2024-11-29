package com.votingapp.votingapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingapp.votingapp.Model.Option;
import com.votingapp.votingapp.Model.Poll;
import com.votingapp.votingapp.Repository.PollRepository;



@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public void SavePoll(Poll poll) {
        for (Option option : poll.getOptions()) { 
            option.setPoll(poll); 
        } 
        pollRepository.save(poll);
    }
    
}
