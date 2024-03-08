package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Integer> {

	boolean existsByCustomerIDAndPassword(int customerId, String password);

	Optional<Accounts> findByCustomerID(int customerId);

	Optional<Accounts> findByAccountNumber(int accountNumber);

	List<Accounts> findByBalanceLessThan(double amount);

	List<Accounts> findByBalanceGreaterThan(double amount);
	

}
