package com.ayush.accounts.service.impl;

import com.ayush.accounts.constants.AccountsConstants;
import com.ayush.accounts.dto.AccountsDto;
import com.ayush.accounts.dto.CustomerDto;
import com.ayush.accounts.entity.Accounts;
import com.ayush.accounts.entity.Customer;
import com.ayush.accounts.exception.CustomerAlreadyExistsException;
import com.ayush.accounts.exception.ResourceNotFoundException;
import com.ayush.accounts.mapper.AccountsMapper;
import com.ayush.accounts.mapper.CustomerMapper;
import com.ayush.accounts.repository.AccountsRepository;
import com.ayush.accounts.repository.CustomerRepository;
import com.ayush.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
				"Customer already registered with given mobile number: " + customer.getMobileNumber()
			);
		}
		Customer savedCustomer = customerRepository.save(customer);
		accountsRepository.save(createNewAccount(savedCustomer));
	}

	/**
	 * @param customer - Customer Object
	 * @return the new account details
	 */
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());

		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccount;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
			.orElseThrow(
				() -> new ResourceNotFoundException(
					"Customer", "mobileNumber", mobileNumber
				)
			);
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
			.orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
			);
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());

		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();

		if (accountsDto != null) {
			Accounts accounts = accountsRepository.findById(customerDto.getAccountsDto().getAccountNumber())
				.orElseThrow(
					() -> new ResourceNotFoundException("Account", "Account Number", accountsDto.getAccountNumber().toString())
				);

			AccountsMapper.mapToAccounts(accounts, accountsDto);
			accounts = accountsRepository.save(accounts);

			Long customerId = accounts.getCustomerId();

			Customer customer = customerRepository.findById(customerId)
				.orElseThrow(
					() -> new ResourceNotFoundException(
						"Customer", "Customer ID", customerId.toString()
					)
				);

			CustomerMapper.mapToCustomer(customer, customerDto);

			customerRepository.save(customer);

			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
			.orElseThrow(
				() -> new ResourceNotFoundException(
					"Customer",
					"mobile number",
					mobileNumber
				)
			);

		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		;
		return true;
	}
}