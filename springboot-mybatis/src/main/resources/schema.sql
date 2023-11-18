DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id    INT GENERATED ALWAYS AS IDENTITY,
    name  varchar(100) NOT NULL,
    email varchar(100),
    PRIMARY KEY (id)
);

