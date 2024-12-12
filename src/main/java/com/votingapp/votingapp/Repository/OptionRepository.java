package com.votingapp.votingapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votingapp.votingapp.Model.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option,Long>{
    
}
