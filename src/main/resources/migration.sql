DROP TABLE IF EXISTS
    timekeeping.category,
    timekeeping.activity,
    timekeeping.user,
    timekeeping.request;

CREATE TABLE timekeeping.category
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE timekeeping.activity
(
    id          BIGSERIAL PRIMARY KEY,
    category_id BIGINT REFERENCES timekeeping.category (id) on delete cascade,
    name        VARCHAR(50) NOT NULL
);

CREATE TABLE timekeeping.user
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(50),
    lastname VARCHAR(50),
    email    VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role     VARCHAR(10) NOT NULL
);

CREATE TABLE timekeeping.request
(
    id          BIGSERIAL PRIMARY KEY,
    activity_id BIGINT REFERENCES timekeeping.activity (id) on delete cascade,
    user_id     BIGINT REFERENCES timekeeping.user (id) on delete cascade,
    duration    INT,
    status      VARCHAR(10) NOT NULL,
    type        VARCHAR(10) NOT NULL
);

INSERT INTO timekeeping.user (name, lastname, email, password, role)
VALUES ('user', 'user', 'user@gmail.com', 'user', 'USER'),
       ('admin', 'admin', 'admin@gmail.com', 'admin', 'ADMIN');

INSERT INTO timekeeping.category (name)
VALUES ('Sport'),
       ('Studying'),
       ('Rest'),
       ('Work');

INSERT INTO timekeeping.activity (category_id, name)
VALUES (1, 'Football'),
       (1, 'Hokey'),
       (1, 'Skating'),
       (2, 'Science'),
       (2, 'Mathematics'),
       (3, 'Reading'),
       (3, 'Watching movies'),
       (3, 'Time with friends'),
       (4, 'Job');