package com.spring.trx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trx.BusinessException;
import com.spring.trx.dto.AmountUpdateForm;
import com.spring.trx.dto.BalanceHistoryForm;
import com.spring.trx.dto.TransferForm;
import com.spring.trx.repo.AccountRepo;
import com.spring.trx.repo.BalanceHistoryRepo;
import com.spring.trx.repo.TransferRepo;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private BalanceHistoryRepo historyRepo;
	@Autowired
	private TransferRepo transferRepo;

	@Override
	public int transfer(TransferForm form) {

		// Get Account From Information
		var fromAccount = accountRepo.findByAccountId(form.accountFrom())
				.orElseThrow(() -> new BusinessException("Invalid account number : %s".formatted(form.accountFrom())));

		// Check Amount to Transfer
		if (fromAccount.amount() < form.amount()) {
			throw new BusinessException("%s has no enough amout to transfer.".formatted(form.accountFrom()));
		}

		// Initiate Transfer Transaction
		var trxId = transferRepo.initiate(form);

		// Create Account From Balance History
		var fromHistory = BalanceHistoryForm.builder().accountNumber(fromAccount.accountNumber())
				.nextVersion(fromAccount.nextVersion()).trxId(trxId).trxAmount(form.amount()).isDebit(true)
				.lastAmount(fromAccount.amount()).remark(form.remark()).build();

		historyRepo.create(fromHistory);

		// Update Account Amount
		var fromAccountUpdateForm = AmountUpdateForm.builder().accountNumber(fromAccount.accountNumber())
				.nextVersion(fromAccount.nextVersion()).updatedAmount(fromAccount.amount() - form.amount()).build();

		accountRepo.updateAmount(fromAccountUpdateForm);

		// Get Account To Information
		var toAccount = accountRepo.findByAccountId(form.accountTo())
				.orElseThrow(() -> new BusinessException("Invalid account number : %s".formatted(form.accountTo())));

		// Create Account To Balance History
		var toHistory = BalanceHistoryForm.builder().accountNumber(toAccount.accountNumber())
				.nextVersion(toAccount.nextVersion()).trxId(trxId).trxAmount(form.amount()).isDebit(false)
				.lastAmount(toAccount.amount()).remark(form.remark()).build();

		historyRepo.create(toHistory);

		// Update Account To Amount
		var toAccountUpdateForm = AmountUpdateForm.builder().accountNumber(toAccount.accountNumber())
				.nextVersion(toAccount.nextVersion()).updatedAmount(toAccount.amount() + form.amount()).build();

		accountRepo.updateAmount(toAccountUpdateForm);

		// Update Transfer Transaction Status
		transferRepo.updateStatus(trxId, "Success");

		return trxId;
	}

}
