package com.microservice.dbholder.repository;

import com.microservice.dbholder.entity.Rule;
import com.microservice.dbholder.entity.TypeOfTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepo  extends JpaRepository<TypeOfTransaction,Integer> {
}
