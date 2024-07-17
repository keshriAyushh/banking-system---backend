package com.ayush.accounts.mapper;

import com.ayush.accounts.dto.CustomerDto;
import com.ayush.accounts.entity.Customer;

public class CustomerMapper {

	private CustomerMapper() {

	}

	public static CustomerDto mapToCustomerDto(
		Customer customer,
		CustomerDto customerDto
	) {
		customerDto.setEmail(customer.getEmail());
		customerDto.setName(customer.getName());
		customerDto.setMobileNumber(customer.getMobileNumber());

		return customerDto;
	}

	public static Customer mapToCustomer(
		Customer customer,
		CustomerDto customerDto
	) {
		customer.setEmail(customerDto.getEmail());
		customer.setName(customerDto.getName());
		customer.setMobileNumber(customerDto.getMobileNumber());

		return customer;
	}
}
