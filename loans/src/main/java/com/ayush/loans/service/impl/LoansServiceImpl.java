package com.ayush.loans.service.impl;

import com.ayush.loans.constants.LoansConstants;
import com.ayush.loans.dto.LoansDto;
import com.ayush.loans.enity.Loans;
import com.ayush.loans.exceptions.LoanAlreadyExistsException;
import com.ayush.loans.exceptions.ResourceNotFoundException;
import com.ayush.loans.mapper.LoansMapper;
import com.ayush.loans.repository.LoansRepository;
import com.ayush.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

	private LoansRepository loansRepository;

	@Override
	public void createLoan(String mobileNumber) {

		Optional<Loans> loansOptional = loansRepository.findByMobileNumber(mobileNumber);

		if (loansOptional.isPresent()) {
			throw new LoanAlreadyExistsException("Loan already exists with registered mobile number: " + mobileNumber);
		} else {
			loansRepository.save(createNewLoan(mobileNumber));
		}
	}

	private Loans createNewLoan(String mobileNumber) {
		Loans newLoan = new Loans();
		long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
		newLoan.setLoanNumber(Long.toString(randomLoanNumber));
		newLoan.setMobileNumber(mobileNumber);
		newLoan.setLoanType(LoansConstants.HOME_LOAN);
		newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
		newLoan.setAmountPaid(0);
		newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
		return newLoan;
	}

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber)
			.orElseThrow(
				() -> new ResourceNotFoundException("loans", "mobileNumber", mobileNumber)
			);
		return LoansMapper.mapToLoansDto(loans, new LoansDto());
	}

	@Override
	public boolean updateLoan(LoansDto loansDto) {
		Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
			.orElseThrow(
				() -> new ResourceNotFoundException(
					"Loan", "loan number", loansDto.getMobileNumber()
				)
			);
		LoansMapper.mapToLoans(loansDto, loans);
		loansRepository.save(loans);
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
			() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
		);
		loansRepository.deleteById(loans.getLoanId());
		return true;
	}
}
