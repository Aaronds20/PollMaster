package com.votingapp.votingapp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private long id;

    @ManyToOne
    // @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    // @JoinColumn(name = "option_id")
    private Options option;
}
