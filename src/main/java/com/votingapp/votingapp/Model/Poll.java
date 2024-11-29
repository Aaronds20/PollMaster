package com.votingapp.votingapp.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poll_id")
    private int id;

    @Column(name = "question", nullable = false)
    @NotBlank(message = "*Please provide a question")
    private String question;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

}