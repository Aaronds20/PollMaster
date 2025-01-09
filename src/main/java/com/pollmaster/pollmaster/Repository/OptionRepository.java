package com.pollmaster.pollmaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pollmaster.pollmaster.Model.Options;

@Repository
public interface OptionRepository extends JpaRepository<Options,Long>{
    
}
