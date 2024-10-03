package com.spring.jdbc.dao;

import com.spring.jdbc.dto.AccountDTO;
import com.spring.jdbc.dto.AccountForm;

public interface AccountDAO {

	int create(AccountForm form);

	long count();

	AccountDTO findById(int id);
}
