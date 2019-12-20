package com.microservice.dbholder.repository;

import com.microservice.dbholder.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RuleRepo extends JpaRepository<Rule,Integer> {

        Rule findById(String Id);
}
