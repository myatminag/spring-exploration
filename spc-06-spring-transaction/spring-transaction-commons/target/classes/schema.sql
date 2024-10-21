SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS account;

CREATE TABLE account (
	account_number VARCHAR(4) PRIMARY KEY,
	account_name VARCHAR(40) NOT NULL,
	phone VARCHAR(12) NOT NULL,
	amount INT NOT NULL,
	version INT NOT NULL
);

DROP TABLE IF EXISTS balance_history;

CREATE TABLE balance_history (
	account_number VARCHAR(4),
	version INT NOT NULL,
	last_amount INT NOT NULL,
	trx_id INT,
	debit BOOLEAN NOT NULL,
	trx_amount INT NOT NULL,
	remark VARCHAR(255),
	FOREIGN KEY (account_number) REFERENCES account(account_number),
	PRIMARY KEY (account_number, version)
);

DROP TABLE IF EXISTS transfer;

CREATE TABLE transfer (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    account_from VARCHAR(4) NOT NULL, 
    account_to VARCHAR(4) NOT NULL, 
    amount INT NOT NULL, 
    status VARCHAR(10) NULL DEFAULT 'Initiate', 
    transfer_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, 
    remark VARCHAR(255), 
    FOREIGN KEY (account_from) REFERENCES account(account_number), 
    FOREIGN KEY (account_to) REFERENCES account(account_number)
);

SET FOREIGN_KEY_CHECKS = 1;