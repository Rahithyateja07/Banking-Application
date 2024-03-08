package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

}
