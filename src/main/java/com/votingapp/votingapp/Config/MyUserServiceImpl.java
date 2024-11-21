package com.votingapp.votingapp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Repository.UserRepository;

public class MyUserServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.loadUserByUserName(email);
       if (user == null) {
        throw new UsernameNotFoundException("Could not find user");
    }
     
    return new UserDetailsImpl(user);
    }
    
}
