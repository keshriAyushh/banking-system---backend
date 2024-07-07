package com.ayush.accounts.service;

import com.ayush.accounts.dto.CustomerDto;

public interface IAccountsService {

	/**
	 * @param customerDto - CustomerDto Object
	 */

	void createAccount(CustomerDto customerDto);
	CustomerDto fetchAccount(String mobileNumber);
	boolean updateAccount(CustomerDto customerDto);
}
