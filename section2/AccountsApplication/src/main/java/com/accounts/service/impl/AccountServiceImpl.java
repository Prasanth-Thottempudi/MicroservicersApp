package com.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.accounts.constants.AccountsConstants;
import com.accounts.dto.AccountsDto;
import com.accounts.dto.CustomerDto;
import com.accounts.entity.Accounts;
import com.accounts.entity.Customer;
import com.accounts.mapper.CustomerMapper;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
	
	private CustomerRepository customerRepository;
	private AccountsRepository accountsRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		
		 Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
	        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
	        if(optionalCustomer.isPresent()) {
//	            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
//	                    +customerDto.getMobileNumber());
	        	throw new IllegalArgumentException("already present");
	        }
	        customer.setCreatedAt(LocalDateTime.now());
	        customer.setCreatedBy("admin");
	        Customer savedCustomer = customerRepository.save(customer);
//	        accountsRepository.save(createNewAccount(savedCustomer));
	}
	
	private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

}
