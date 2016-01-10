DROP TABLE CUSTOMER IF EXISTS;

CREATE TABLE CUSTOMER (	ID BIGINT IDENTITY PRIMARY KEY,
						CUSTOMERNO VARCHAR(15) NOT NULL,
						NAME VARCHAR(50),
                        ADDRESS VARCHAR(50),
                       	CITY VARCHAR(50),
                       	STATE VARCHAR(20),
                       	ZIPCODE VARCHAR(10),
                       	PHONE VARCHAR(15),
                       	EMAIL VARCHAR(30),
                        UNIQUE(CUSTOMERNO));
