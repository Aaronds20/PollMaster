package com.votingapp.votingapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votingapp.votingapp.Model.Options;

@Repository
public interface OptionRepository extends JpaRepository<Options,Long>{
    
}
