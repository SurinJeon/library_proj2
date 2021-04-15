-- 도서관리
DROP SCHEMA IF EXISTS library2;

-- 도서관리
CREATE SCHEMA library2;

-- 회원정보
CREATE TABLE library2.user (
	userno    INTEGER(5)  NOT NULL COMMENT '회원번호', -- 회원번호
	username  VARCHAR(50) NOT NULL COMMENT '회원이름', -- 회원이름
	userbirth DATE        NULL     COMMENT '회원생년월일', -- 회원생년월일
	account   VARCHAR(40) NULL     COMMENT '계정', -- 계정
	tel       VARCHAR(13) NULL     COMMENT '전화번호', -- 전화번호
	phone     VARCHAR(13) NULL     COMMENT '휴대전화', -- 휴대전화
	address   VARCHAR(20) NULL     COMMENT '주소' -- 주소
)
COMMENT '회원정보';

-- 회원정보
ALTER TABLE library2.user
	ADD CONSTRAINT PK_user -- 회원정보 기본키
		PRIMARY KEY (
			userno -- 회원번호
		);

-- 도서정보
CREATE TABLE library2.book (
	bookno       VARCHAR(10) NOT NULL COMMENT '도서번호', -- 도서번호
	booktitle    VARCHAR(40) NOT NULL COMMENT '도서제목', -- 도서제목
	isRented     TINYINT(1)  NULL     DEFAULT 1 COMMENT 'true(1): 대출불가능 / false(0): 대출가능', -- 대출여부
	bookcategory INTEGER(1)  NULL     COMMENT '도서구분', -- 도서구분
	count        INTEGER(5)  NULL     COMMENT '권수', -- 권수
	rentalrange  INTEGER(5)  NULL     COMMENT '대여기간' -- 대여기간
)
COMMENT '도서정보';

-- 도서정보
ALTER TABLE library2.book
	ADD CONSTRAINT PK_book -- 도서정보 기본키
		PRIMARY KEY (
			bookno -- 도서번호
		);

-- 대출반납내역
CREATE TABLE library2.rentalstatus (
	rentalno       INTEGER(5)  NOT NULL COMMENT '대여번호', -- 대여번호
	bookno         VARCHAR(10) NULL     COMMENT '도서번호', -- 도서번호
	userno         INTEGER(5)  NULL     COMMENT '회원번호', -- 회원번호
	rentaldate     DATE        NULL     COMMENT '대여일', -- 대여일
	userreturndate DATE        NULL     COMMENT '회원반납일', -- 회원반납일
	delaydate      INTEGER(10) NULL     DEFAULT 0 COMMENT '연체일' -- 연체일
)
COMMENT '대출반납내역';

-- 대출반납내역
ALTER TABLE library2.rentalstatus
	ADD CONSTRAINT PK_rentalstatus -- 대출반납내역 기본키
		PRIMARY KEY (
			rentalno -- 대여번호
		);

-- 도서구분
CREATE TABLE library2.bookcategory (
	bookcategory INTEGER(1)  NOT NULL COMMENT '도서구분', -- 도서구분
	categoryname VARCHAR(20) NULL     COMMENT '구분이름' -- 구분이름
)
COMMENT '도서구분';

-- 도서구분
ALTER TABLE library2.bookcategory
	ADD CONSTRAINT PK_bookcategory -- 도서구분 기본키
		PRIMARY KEY (
			bookcategory -- 도서구분
		);


-- 관리자
CREATE TABLE library2.manager (
	mngaccount VARCHAR(40) NOT NULL COMMENT '관리자계정', -- 관리자계정
	passwd     VARCHAR(20) NOT NULL COMMENT '비밀번호' -- 비밀번호
)
COMMENT '관리자';

-- 관리자
ALTER TABLE library2.manager
	ADD CONSTRAINT PK_manager -- 관리자 기본키
		PRIMARY KEY (
			mngaccount -- 관리자계정
		);

-- 도서정보
ALTER TABLE library2.book
	ADD CONSTRAINT FK_bookcategory_TO_book -- 도서구분 -> 도서정보
		FOREIGN KEY (
			bookcategory -- 도서구분
		)
		REFERENCES library2.bookcategory ( -- 도서구분
			bookcategory -- 도서구분
		);

-- 대출반납내역
ALTER TABLE library2.rentalstatus
	ADD CONSTRAINT FK_book_TO_rentalstatus -- 도서정보 -> 대출반납내역
		FOREIGN KEY (
			bookno -- 도서번호
		)
		REFERENCES library2.book ( -- 도서정보
			bookno -- 도서번호
		);

-- 대출반납내역
ALTER TABLE library2.rentalstatus
	ADD CONSTRAINT FK_user_TO_rentalstatus -- 회원정보 -> 대출반납내역
		FOREIGN KEY (
			userno -- 회원번호
		)
		REFERENCES library2.user ( -- 회원정보
			userno -- 회원번호
		);
	
alter table library2.rentalstatus modify rentalno int not null auto_increment;

grant all
   on library2.*
   to 'user_library2'@'localhost' identified by 'rootroot';