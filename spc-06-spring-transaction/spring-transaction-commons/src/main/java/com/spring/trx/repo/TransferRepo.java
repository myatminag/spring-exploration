package com.spring.trx.repo;

import com.spring.trx.dto.TransferForm;

public interface TransferRepo {

	int initiate(TransferForm form);

	void updateStatus(int trxId, String string);

}
