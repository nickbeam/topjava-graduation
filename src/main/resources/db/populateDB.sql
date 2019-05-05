DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, registered, enabled) VALUES
  ('User', 'user@yandex.ru', '{noop}password', now, true),
  ('Admin', 'admin@gmail.com', '{noop}admin', now, true);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name)
VALUES ('Spirit'),
       ('Seventh sky'),
       ('Five corners');

INSERT INTO dishes (date, name, price, restaurant_id) VALUES
    (today, 'Fish', 350, 100002),
    (today, 'BBQ', 450, 100002),
    (today, 'Roast beef', 200, 100003),
    (today, 'Steak', 500, 100003),
    (today, 'Stew', 300, 100004),
    (today, 'Apple Pie', 50, 100004);

INSERT INTO votes (date, restaurant_id, user_id) VALUES
    (today, 100002, 100000),
    (today, 100004, 100001);
