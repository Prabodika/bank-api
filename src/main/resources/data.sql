INSERT INTO transaction_type(type) VALUES ('deposit');
INSERT INTO transaction_type(type) VALUES ('withdrawal');

INSERT INTO account_type(type) VALUES ('single');
INSERT INTO account_type(type) VALUES ('join');

INSERT INTO account(account_number,name,account_type) VALUES ('1234','Jhon',1);
INSERT INTO account(account_number,name,account_type) VALUES ('4321','Tom',1);


INSERT INTO account_transaction(amount,date,description,account_id,transaction_type) VALUES ( 500,TO_DATE('2019/07/20', 'YYYY/MM/DD'),'intial deposit',1,1);
INSERT INTO account_transaction(amount,date,description,account_id,transaction_type) VALUES ( 500,TO_DATE('2019/07/20', 'YYYY/MM/DD'),'intial deposit',2,1);


