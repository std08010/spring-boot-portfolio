DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT GENERATED ALWAYS AS IDENTITY,
                       name varchar(100) NOT NULL,
                       email varchar(100),
                       PRIMARY KEY (id)
);

delete from users;
insert into users(id, name, email) OVERRIDING SYSTEM VALUE values(1,'Siva','siva@gmail.com');
insert into users(id, name, email) OVERRIDING SYSTEM VALUE values(2,'Prasad','prasad@gmail.com');
insert into users(id, name, email) OVERRIDING SYSTEM VALUE values(3,'Reddy','reddy@gmail.com');