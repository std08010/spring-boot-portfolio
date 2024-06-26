CREATE TABLE POSTS
(
    ID INT NOT NULL DEFAULT nextval('posts_id_seq'),
    TITLE varchar(50) NOT NULL,
    DESCRIPTION varchar(500) NOT NULL,
    BODY TEXT DEFAULT NULL,
    SLUG varchar(60) DEFAULT NULL,
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