package com.microservice.dbholder.repository;

import com.microservice.dbholder.entity.Rule;
import com.microservice.dbholder.entity.SourceOfTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepo extends JpaRepository<SourceOfTransaction,Integer> {
}
