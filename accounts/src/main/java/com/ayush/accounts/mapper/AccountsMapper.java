package com.ayush.accounts.mapper;

import com.ayush.accounts.dto.AccountsDto;
import com.ayush.accounts.entity.Accounts;

public class AccountsMapper {

	private AccountsMapper() {

	}

	public static AccountsDto mapToAccountsDto(
		Accounts accounts,
		AccountsDto accountsDto
	) {
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());

		return accountsDto;
	}

	public static Accounts mapToAccounts(
		Accounts accounts,
		AccountsDto accountsDto
	) {
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());

		return accounts;
	}
}
