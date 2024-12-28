package com.accounts.service;

import com.accounts.dto.AccountsDto;
import com.accounts.dto.CustomerDto;

public interface IAccountService {


	void createAccount(CustomerDto customerDto);
	
}
