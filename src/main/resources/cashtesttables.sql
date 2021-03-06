CREATE TABLE goods
(
    product_id   serial PRIMARY KEY,
    product_name varchar(50) not null,
    product_cost int
);
INSERT INTO goods
VALUES (1, 'Tea black', 200),
       (2, 'Coffee Americano', 120),
       (3, 'Latte coffee', 150),
       (4, 'Product2', 1900),
       (5, 'Sugar', 30),
       (6, 'Sugar super sweet', 130),
       (7, 'Tea green', 200),
       (8, 'Tea red', 210),
       (9, 'Fresh water', 100),
       (10, 'Coffee espresso', 70);

CREATE TABLE checks
(
    check_id     serial PRIMARY KEY,
    check_date   date,
    check_time time,
    check_amount int
);

CREATE TABLE checklines
(
    checkline_id serial PRIMARY KEY,
    product_id   int,
    check_id     int,
    line_number  int,
    quantity     int,
    line_amount  int
);
