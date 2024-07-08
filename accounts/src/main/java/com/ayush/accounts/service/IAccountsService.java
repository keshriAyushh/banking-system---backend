package com.ayush.accounts.service;

import com.ayush.accounts.dto.CustomerDto;

public interface IAccountsService {

	/**
	 * @param customerDto - CustomerDto Object
	 */

	void createAccount(CustomerDto customerDto);

	/**
	 * @param mobileNumber
	 * @return customerDto
	 */
	CustomerDto fetchAccount(String mobileNumber);

	/**
	 *
	 * @param customerDto
	 * @return boolean
	 */
	boolean updateAccount(CustomerDto customerDto);

	/**
	 * @param mobileNumber
	 * @return boolean
	 */
	boolean deleteAccount(String mobileNumber);
}
