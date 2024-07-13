package com.ayush.loans.mapper;

import com.ayush.loans.dto.LoansDto;
import com.ayush.loans.enity.Loans;

public class LoansMapper {

	private LoansMapper() {

	}

	public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
		loans.setMobileNumber(loansDto.getMobileNumber());
		loans.setLoanNumber(loansDto.getLoanNumber());
		loans.setTotalLoan(loansDto.getTotalLoan());
		loans.setLoanType(loansDto.getLoanType());
		loans.setAmountPaid(loansDto.getAmountPaid());
		loans.setOutstandingAmount(loansDto.getOutstandingAmount());

		return loans;
	}

	public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
		loansDto.setMobileNumber(loans.getMobileNumber());
		loansDto.setLoanNumber(loans.getLoanNumber());
		loansDto.setTotalLoan(loans.getTotalLoan());
		loansDto.setLoanType(loans.getLoanType());
		loansDto.setAmountPaid(loans.getAmountPaid());
		loansDto.setOutstandingAmount(loans.getOutstandingAmount());

		return loansDto;
	}
}
