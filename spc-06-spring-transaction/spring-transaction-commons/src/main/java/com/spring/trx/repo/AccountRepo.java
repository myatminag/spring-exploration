package com.spring.trx.repo;

import java.util.Optional;

import com.spring.trx.dto.AccountInfo;
import com.spring.trx.dto.AmountUpdateForm;

public interface AccountRepo {

	Optional<AccountInfo> findByAccountId(String accountNumber);

	void updateAmount(AmountUpdateForm fromAmountForm);

}
