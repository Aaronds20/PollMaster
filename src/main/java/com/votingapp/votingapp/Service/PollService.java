package com.votingapp.votingapp.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Poll getPollById(Long pollId) {
        return pollRepository.findById(pollId).orElseThrow(() -> new RuntimeException("Poll not found"));
    }

    public boolean hasUserVoted(Long pollId, Long userId) {
        Optional<Vote> vote = voteRepository.findByUserAndPoll(userId, pollId);
        return vote.isPresent();
    }

    public Map<String, Integer> getPollResults(Long pollId) {
        Poll poll = getPollById(pollId);
        Map<String, Integer> results = new HashMap<>();
        for (Options option : poll.getOptions()) {
            results.put(option.getText(), option.getVotes().size());
        }
        return results;
    }

    // private int subtractPageByOne(int page){
    // return (page < 1) ? 0 : page - 1;
    // }
    // public Poll createPollWithOptions(String question,List<String> optionTexts) {
    //     Poll poll = new Poll();
    //     poll.setQuestion(question);
    //     for (String text : optionTexts) {
    //         Options option = new Options();
    //         option.setText(text);
    //         option.setPoll(poll);
    //         poll.getOptions().add(option);
    //     }
    //     return pollRepository.save(poll);
    // }
}
