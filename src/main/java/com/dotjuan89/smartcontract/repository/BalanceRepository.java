package com.dotjuan89.smartcontract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dotjuan89.smartcontract.entity.BalanceEntity;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, Integer> {
    
}
