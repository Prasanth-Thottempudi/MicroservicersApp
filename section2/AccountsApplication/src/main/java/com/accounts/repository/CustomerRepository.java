package com.accounts.repository;

import com.accounts.entity.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

	Optional<Customer> findByMobileNumber(String mobileNumber);
}
