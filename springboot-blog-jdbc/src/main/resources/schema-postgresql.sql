DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS POSTS;

--Postgres Enums do not work with spring data jdbc because they are saved as strings
--in the database and there is a conflict. So use varchar.
--DROP TYPE IF EXISTS post_status_enum;
--CREATE TYPE post_status_enum AS ENUM('DRAFT', 'PUBLISHED');

DROP SEQUENCE IF EXISTS posts_id_seq;
CREATE SEQUENCE posts_id_seq;
CREATE TABLE POSTS
(
    ID INT NOT NULL DEFAULT nextval('posts_id_seq'),
    TITLE varchar(50) NOT NULL,
    DESCRIPTION varchar(500) NOT NULL,
    BODY TEXT DEFAULT NULL,
    SLUG varchar(60) DEFAULT NULL,
    --POST_STATUS post_status_enum  DEFAULT NULL,
    POST_STATUS varchar(20)  DEFAULT NULL,
    CREATED_ON timestamp DEFAULT NULL,
    UPDATED_ON timestamp DEFAULT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE COMMENTS
(
    ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    POST_ID int NOT NULL,
    TITLE varchar(200) NOT NULL,
    AUTHOR_NAME varchar(200) NOT NULL,
    BODY TEXT DEFAULT NULL,
    CREATED_ON timestamp DEFAULT NULL,
    UPDATED_ON timestamp DEFAULT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (POST_ID) REFERENCES POSTS(ID)
);