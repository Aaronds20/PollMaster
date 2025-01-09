package com.pollmaster.pollmaster.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pollmaster.pollmaster.Model.Category;
import com.pollmaster.pollmaster.Model.Options;
import com.pollmaster.pollmaster.Model.Poll;
import com.pollmaster.pollmaster.Model.Vote;
import com.pollmaster.pollmaster.Repository.CategoryRepository;
import com.pollmaster.pollmaster.Repository.PollRepository;
import com.pollmaster.pollmaster.Repository.VoteRepository;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private CategoryRepository categoryRepository;

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

    public boolean hasUserVoted(Long pollId, Long userId, Long categoryId) {
        Optional<Vote> vote = voteRepository.findByUserAndPollAndCategory(userId, pollId, categoryId);
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

    public List<Category> getAllCategories() { 
        return categoryRepository.findAll(); 
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
