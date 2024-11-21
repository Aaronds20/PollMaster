package com.votingapp.votingapp.Model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "candidate_id")
    private int id;

    @Column(name = "candidate_name", nullable = false)
    private String candidateName;

    @Column(name = "vote_count", nullable = false)
    private int count;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<User> users;
}
