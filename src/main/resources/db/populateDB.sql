DELETE
FROM user_roles;
DELETE
FROM meals;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (description, calories, date_time, user_id)
VALUES ('Breakfast', 1000, '2020-04-30', 100000),
       ('Launch', 1500, '2020-03-30', 100000),
       ('Dinner', 1000, '2020-06-30', 100000),
       ('Admin Launch', 1300, '2010-11-23', 100000)
