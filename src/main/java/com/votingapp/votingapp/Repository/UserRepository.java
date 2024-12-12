package com.votingapp.votingapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.votingapp.votingapp.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    
    @Query("select u from User u where u.email =:email")
	public User loadUserByUserName(@Param("email") String email);

    public User findById(int id);

    public Optional<User> findByEmail(String email);


}

