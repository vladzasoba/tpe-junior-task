insert into customer(first_name, last_name, age)
values ('John', 'Doe', 39),
('Jane', 'Doe', 34),
('Max', 'Mustermann', 46),
('Anna', 'Muster', 47);

insert into account (customer_id, amount)
values (1, 24786.00),
(1, 1023.00),
(1, 13.00),
(2, 12353.00),
(3, 42411.00),
(3, 130.00),
(3, 893.00),
(4, 16540.00);

insert into atransaction (tx_date, src_account_id, dst_account_id, amount, transaction_type)
values ('2017-04-23', 1, 4, 3650.00, 'Transfer'),
('2017-04-28', 2, NULL, 450.95, 'Charge'),
('2017-04-28', 2, NULL, 34.36, 'Charge'),
('2017-04-28', 2, NULL, 871.93, 'Deposit'),
('2017-04-28', 3, NULL, 204.79, 'Deposit'),
('2017-04-28', 4, NULL, 2204.00, 'Charge'),
('2017-04-28', 4, 8, 150.00, 'Transfer'),
('2017-04-28', 5, NULL, 3450.00, 'Deposit'),
('2017-04-28', 5, 6, 4300.00, 'Transfer'),
('2017-04-28', 8, 3, 5600.00, 'Transfer');