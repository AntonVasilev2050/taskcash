CREATE TABLE users (
                       user_Id serial PRIMARY KEY,
                       first_name varchar(50) not null,
                       last_name varchar(50),
                       patronymic varchar(50),
                       birthday date,
                       passport_id integer,
                       salary int
);

INSERT INTO users VALUES (1, 'User01Name', 'U01LastN', 'U01Patronymic', '2001/02/19', 1234567890, 1500),
                         (2, 'User02Name', 'U02LastN', 'U02Patronymic', '2002/02/20', 1234567690),
                         (3, 'User01Name', 'U03LastN', 'U03Patronymic', '1997/05/29', 1234567800, 2100);
select * from users order by user_Id;