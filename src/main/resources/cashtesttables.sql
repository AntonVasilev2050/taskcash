CREATE TABLE goods
(
    product_id   serial PRIMARY KEY,
    product_name varchar(50) not null,
    product_cost int
);
INSERT INTO goods
VALUES (1, 'Product1', 1500),
       (2, 'Product2', 1900),
       (3, 'Product3', 300);

CREATE TABLE checks
(
    check_id     serial PRIMARY KEY,
    check_date   date,
    check_time time,
    check_amount int
);
INSERT INTO checks
VALUES (1, '2022-01-18', '15:00', 1500),
       (2, '2022-01-19', '01:00', 40000),
       (3, '2022-01-20', '09:00', 600);


CREATE TABLE checklines
(
    checkline_id serial PRIMARY KEY,
    product_id   int,
    check_id     int,
    line_number  int,
    quantity     int,
    line_amount  int
);

CREATE TABLE cart
(
    product_id   serial PRIMARY KEY,
    product_name varchar(50) not null,
    product_cost int,
    product_quantity int
);