SELECT * FROM TB_DAILY_CALORIES tdc ;
SELECT * FROM TB_DAILY_CALORIES_EXCEDEED tdce ;
SELECT * FROM TB_DAILY_EXERCISE tde ;
SELECT * FROM TB_MONTH_CALORIES_GOAL tmcg ;

TRUNCATE  TABLE TB_DAILY_CALORIES;
TRUNCATE TABLE TB_DAILY_CALORIES_EXCEDEED;
TRUNCATE TABLE TB_MONTH_CALORIES_GOAL;

CREATE TABLE TB_USER_LOGIN(
	CD_LOGIN NUMBER(12, 0),
	DS_LOGIN VARCHAR2(20) NOT NULL CHECK (TRIM(DS_LOGIN) IS NOT NULL),
	DS_PASSWORD VARCHAR2(255) NOT NULL,
	CONSTRAINT PK_TB_USER_LOGIN PRIMARY KEY(CD_LOGIN)
);

CREATE TABLE TB_USER(
	CD_USER NUMBER(12, 0),
	CD_LOGIN NUMBER(12, 0),
	NM_USER VARCHAR(50) NOT NULL CHECK (TRIM(NM_USER) IS NOT NULL),
	VL_AGE NUMBER(12,0),
	VL_WEIGHT NUMBER(12,0),
	VL_HEIGHT NUMBER(12,0),
	DS_GENDER VARCHAR2(1) CHECK (DS_GENDER IN ('f', 'm', 'o', 'F', 'M', 'O')),
	CONSTRAINT FK_TB_USER_LOGIN FOREIGN KEY(CD_LOGIN) 
  	REFERENCES TB_USER_LOGIN(CD_LOGIN)
);

ALTER TABLE TB_USER
ADD CONSTRAINT "PK_TB_USER" PRIMARY KEY(CD_USER);

CREATE TABLE TB_STREAK(
	CD_STREAK NUMBER(12, 0),
	VL_STREAK NUMBER(12, 0),
	CD_USER NUMBER(12, 0) UNIQUE NOT NULL,
	CONSTRAINT PK_TB_STREAK PRIMARY KEY(CD_STREAK),
	CONSTRAINT FK_TB_USER FOREIGN KEY(CD_USER) 
  	REFERENCES TB_USER(CD_USER)
);

ALTER TABLE TB_STREAK
ADD (DT_LAST_UPDATE DATE);

ALTER TABLE TB_DAILY_CALORIES
ADD (CD_USER NUMBER(12, 0) NOT NULL);

ALTER TABLE TB_DAILY_CALORIES
ADD CONSTRAINT "FK_TB_USER_1" FOREIGN KEY(CD_USER)
REFERENCES TB_USER(CD_USER);

ALTER TABLE TB_DAILY_CALORIES_EXCEDEED
RENAME  COLUMN CD_DAILY to CD_DAILY_CAL;

ALTER TABLE TB_DAILY_CALORIES_EXCEDEED
ADD CONSTRAINT "FK_TB__DAILY_CALORIES_EXCEDEED" FOREIGN KEY(CD_DAILY_CAL)
REFERENCES TB_DAILY_CALORIES(CD_DAILY_CAL);

ALTER TABLE TB_DAILY_CALORIES_EXCEDEED
DROP CONSTRAINT FK_TB_USER_2; 

ALTER TABLE TB_DAILY_EXERCISE
ADD (CD_USER NUMBER(12, 0) NOT NULL);

ALTER TABLE TB_DAILY_EXERCISE
ADD CONSTRAINT "FK_TB_USER_3" FOREIGN KEY(CD_USER)
REFERENCES TB_USER(CD_USER);

ALTER TABLE TB_MONTH_CALORIES_GOAL
ADD (CD_USER NUMBER(12, 0) NOT NULL);

ALTER TABLE TB_MONTH_CALORIES_GOAL
ADD CONSTRAINT "FK_TB_USER_4" FOREIGN KEY(CD_USER)
REFERENCES TB_USER(CD_USER);

ALTER TABLE TB_USER_LOGIN
ADD FL_ACTIVE NUMBER(1, 0) DEFAULT 1;

CREATE SEQUENCE SQ_CD_LOGIN
      INCREMENT BY 1
      START WITH 1
      NOMAXVALUE
      NOCYCLE
      CACHE 10;

CREATE SEQUENCE SQ_CD_USER
      INCREMENT BY 1
      START WITH 1
      NOMAXVALUE
      NOCYCLE
      CACHE 10;

CREATE SEQUENCE SQ_CD_STREAK
      INCREMENT BY 1
      START WITH 1
      NOMAXVALUE
      NOCYCLE
      CACHE 10;