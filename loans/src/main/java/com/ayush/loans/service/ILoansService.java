package com.ayush.loans.service;

import com.ayush.loans.dto.LoansDto;
import com.ayush.loans.enity.Loans;

import java.util.Optional;

public interface ILoansService {
	void createLoan(String mobileNumber);
	LoansDto fetchLoan(String mobileNumber);
	boolean updateLoan(LoansDto loansDto);
	boolean deleteLoan(String mobileNumber);
}
