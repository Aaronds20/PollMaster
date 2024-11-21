package com.votingapp.votingapp.Util;

import org.springframework.data.domain.Page;
import com.votingapp.votingapp.Model.Vote;

public class Pager {
     private final Page<Vote> votes;

    public Pager(Page<Vote> votes) {
        this.votes = votes;
    }

    public int getPageIndex() {
        return votes.getNumber() + 1;
    }

    public int getPageSize() {
        return votes.getSize();
    }

    public boolean hasNext() {
        return votes.hasNext();
    }

    public boolean hasPrevious() {
        return votes.hasPrevious();
    }

    public int getTotalPages() {
        return votes.getTotalPages();
    }

    public long getTotalElements() {
        return votes.getTotalElements();
    }

    public Page<Vote> getVotes() {
        return votes;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

}
