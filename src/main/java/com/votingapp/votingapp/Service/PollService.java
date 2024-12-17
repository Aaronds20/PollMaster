package com.votingapp.votingapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingapp.votingapp.Model.Options;
import com.votingapp.votingapp.Model.Poll;
import com.votingapp.votingapp.Model.Vote;
import com.votingapp.votingapp.Repository.PollRepository;
import com.votingapp.votingapp.Repository.VoteRepository;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteRepository voteRepository;

    public void SavePoll(Poll poll) {
        for (Options option : poll.getOptions()) {
            option.setPoll(poll);
        }
        pollRepository.save(poll);
    }

    public List<Poll> findAllByPoll() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(long id) {
        Optional<Poll> poll = pollRepository.findById(id);
        return poll;
    }

    public boolean hasUserVoted(Long pollId, Long userId) {
        Optional<Vote> vote = voteRepository.findByUserAndPoll(userId, pollId);
        return vote.isPresent();
    }

    // private int subtractPageByOne(int page){
    // return (page < 1) ? 0 : page - 1;
    // }
}
