package com.votingapp.votingapp.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.votingapp.votingapp.Model.Vote;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
   @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.option.poll.id = :pollId") 
   Optional<Vote> findByUserAndPoll(@Param("userId") Long userId, @Param("pollId") Long pollId);
}

