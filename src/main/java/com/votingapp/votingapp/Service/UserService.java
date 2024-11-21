package com.votingapp.votingapp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Model.Vote;
import com.votingapp.votingapp.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bEncoder) {
        this.userRepository = userRepository;
        this.bEncoder = bEncoder;
    }

    public void SaveUser(User user){
        user.setEmail(user.getEmail());
        user.setPassword(bEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        user.setPhoneno(user.getPhoneno());
        user.setRole("ROLE_USER");
        userRepository.saveAndFlush(user);
    }

    public User GetUser(String email){
        User user = userRepository.loadUserByUserName(email);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void UpdateUser(User loggedInUser, Vote vote) {
       loggedInUser.setVote(vote);
       userRepository.saveAndFlush(loggedInUser);
    }

    public User GetUserById(int id){
        User user = userRepository.findById(id);
        return user;
    }

}
