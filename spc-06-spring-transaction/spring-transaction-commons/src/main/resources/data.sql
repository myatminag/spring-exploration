INSERT INTO account VALUES ('0001', 'Thidar', '0911112222', 300000, 1);
INSERT INTO account VALUES ('0002', 'Nilar', '0922223333', 200000, 1);

INSERT INTO balance_history (account_number, version, last_amount, debit, trx_amount, remark) VALUES ('0001', 1, 0, true, 300000, 'Initialized');
INSERT INTO balance_history (account_number, version, last_amount, debit, trx_amount, remark) VALUES ('0002', 1, 0, true, 200000, 'Initialized');