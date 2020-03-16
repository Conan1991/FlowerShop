CREATE SEQUENCE flowers_sequence
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2000
    CACHE 1;

CREATE SEQUENCE orders_sequence
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2000
    CACHE 1;

CREATE TABLE FLOWERS_USERS
(
    ID       int         NOT NULL PRIMARY KEY,
    LOGIN    varchar(45) NOT NULL UNIQUE,
    PASSWORD varchar     NOT NULL,
    FIO      varchar(100),
    ADDRESS  varchar,
    BALANCE  int         NOT NULL DEFAULT 2000,
    DISCOUNT int                  DEFAULT 5
);

CREATE TABLE ADMINISTRATOR
(
    LOGIN    varchar DEFAULT 'admin',
    PASSWORD varchar DEFAULT 'admin123'
);

CREATE TABLE FLOWERS
(
    NAME   varchar(45) PRIMARY KEY,
    PRICE  int NOT NULL,
    AMOUNT int
);

CREATE TABLE CART
(
    LOGIN     varchar(45) NOT NULL,
    NAME      varchar(45),
    ORDERED   int DEFAULT 0,
    SUM_PRICE DECIMAL,
    PRIMARY KEY (LOGIN, NAME),
    FOREIGN KEY (NAME) REFERENCES FLOWERS (NAME),
    FOREIGN KEY (LOGIN) REFERENCES FLOWERS_USERS (LOGIN)
);

CREATE TABLE ORDERS
(
    ID        INT,
    LOGIN     varchar(45),
    TOTAL     DECIMAL,
    OPENDATE  DATETIME default CURRENT_TIMESTAMP,
    CLOSEDATE DATETIME,
    STATUS    VARCHAR
        CONSTRAINT STATUS_IN CHECK (STATUS IN ('CREATED', 'CLOSED', 'PAID')),
    PRIMARY KEY (ID),
    FOREIGN KEY (LOGIN) REFERENCES FLOWERS_USERS (LOGIN)
);

/* ALTER TABLE ORDERS ADD CONSTRAINT DF_ORDERS DEFAULT GETDATE() FOR OPENDATE; */
/* alter table FLOWERS_USERS add constraint DF_temp_x default 5 for DISCOUNT; */

INSERT INTO ADMINISTRATOR DEFAULT
VALUES;

insert into FLOWERS_USERS
values (FLOWERS_SEQUENCE.NEXTVAL, 'Faruk', '1234', 'Valentin Faruk Sergeevich', 'Moskow, Kasan st, d.45, kv.46',
        DEFAULT, 3);

insert into orders
values (2, 'Maria', CURRENT_DATE, NULL, 'CREATED');

insert into FLOWERS
values ('chamomile', 23, 1278);
insert into FLOWERS
values ('rose', 250, 100);
insert into FLOWERS
values ('tulip', 500, 4);
insert into FLOWERS
values ('mimosa', 43, 12);
insert into FLOWERS
values ('buttercup', 99, 2);
insert into FLOWERS
values ('dandelion', 67, 67);
insert into FLOWERS
values ('forget-me-not', 150, 100);

insert into orders(id, login, opendate) values(2, 'Maria',CURRENT_TIMESTAMP);




