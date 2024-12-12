package com.votingapp.votingapp.Util;

import org.springframework.data.domain.Page;

import com.votingapp.votingapp.Model.Poll;

public class Pager {
     private final Page<Poll> polls;

    public Pager(Page<Poll> polls) {
        this.polls = polls;
    }

    public int getPageIndex() {
        return polls.getNumber() + 1;
    }

    public int getPageSize() {
        return polls.getSize();
    }

    public boolean hasNext() {
        return polls.hasNext();
    }

    public boolean hasPrevious() {
        return polls.hasPrevious();
    }

    public int getTotalPages() {
        return polls.getTotalPages();
    }

    public long getTotalElements() {
        return polls.getTotalElements();
    }

    public Page<Poll> getPoll() {
        return polls;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

}
