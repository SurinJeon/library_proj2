select user(), database();

show tables;

desc user;
desc rentalstatus;

drop table rentalstatus;

drop table user;

drop table book;

CREATE TABLE library2.book (
	bookno       VARCHAR(10) NOT NULL COMMENT '도서번호', -- 도서번호
	booktitle    VARCHAR(40) NOT NULL COMMENT '도서제목', -- 도서제목
	isRented     TINYINT(1)  NULL     DEFAULT 1 COMMENT 'true(1): 대출불가능 / false(0): 대출가능', -- 대출여부
	bookcategory INTEGER(1)  NULL     COMMENT '도서구분', -- 도서구분
	count        INTEGER(5)  NULL     COMMENT '권수', -- 권수
	rentalrange  INTEGER(5)  NULL     COMMENT '대여기간' -- 대여기간
)

-- 도서정보
ALTER TABLE library2.book
	ADD CONSTRAINT PK_book -- 도서정보 기본키
		PRIMARY KEY (
			bookno -- 도서번호
		);

	
ALTER TABLE library2.book
	ADD CONSTRAINT FK_bookcategory_TO_book -- 도서구분 -> 도서정보
		FOREIGN KEY (
			bookcategory -- 도서구분
		)
		REFERENCES library2.bookcategory ( -- 도서구분
			bookcategory -- 도서구분
		);
	
CREATE TABLE library2.user (
	userno    INTEGER(5)  NOT NULL COMMENT '회원번호', -- 회원번호
	username  VARCHAR(50) NOT NULL COMMENT '회원이름', -- 회원이름
	userbirth DATE        NULL     COMMENT '회원생년월일', -- 회원생년월일
	account   VARCHAR(40) NULL     COMMENT '계정', -- 계정
	tel       VARCHAR(13) NULL     COMMENT '전화번호', -- 전화번호
	phone     VARCHAR(13) NULL     COMMENT '휴대전화', -- 휴대전화
	address   VARCHAR(20) NULL     COMMENT '주소', -- 주소
	isBlackList TINYINT(1)  NULL     DEFAULT 0 COMMENT 'true(1): 블랙리스트O / false(0): 블랙리스트X' -- 블랙리스트
)

-- 회원정보
ALTER TABLE library2.user
	ADD CONSTRAINT PK_user -- 회원정보 기본키
		PRIMARY KEY (
			userno -- 회원번호
		);


CREATE TABLE library2.rentalstatus (
	rentalno       INTEGER(5)  NOT NULL COMMENT '대여번호', -- 대여번호
	bookno         VARCHAR(10) NULL     COMMENT '도서번호', -- 도서번호
	userno         INTEGER(5)  NULL     COMMENT '회원번호', -- 회원번호
	rentaldate     DATE        NULL     COMMENT '대여일', -- 대여일
	userreturndate DATE        NULL     COMMENT '회원반납일', -- 회원반납일
	delaydate      INTEGER(10) NULL     DEFAULT 0 COMMENT '연체일' -- 연체일
)

ALTER TABLE library2.rentalstatus
	ADD CONSTRAINT PK_rentalstatus -- 대출반납내역 기본키
		PRIMARY KEY (
			rentalno -- 대여번호
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


select * from user;
select * from rentalstatus;

-- insert user
insert into user
values (12001, '홍길동', 19070405, 'kildong@abc.com', '042-421-1739', '010-9741-5821', '대전', default(isBlackList));

insert into user
values (12002, '김연수', 19850301, 'yeonsu@abc.com', '064-446-8695', '010-4568-5581', '제주', default(isBlackList)),
       (12003, '김지원', 19860708, 'jiwonkim@abc.com', '053-548-7898', '010-9111-5556', '대구', default(isBlackList)),
       (12004, '문희원', 19800108, 'happymoon@abc.com', '052-221-1231', '010-7777-7777', '울산', default(isBlackList)),
       (12005, '유일한', 19790205, 'number_1@abc.com', '051-111-2222', '010-4566-8886', '부산', default(isBlackList)),
       (12006, '김동수', 19811123, 'kds1123@abc.com', '02-668-8887', '010-1231-1211', '서울', default(isBlackList)),
       (12007, '배진태', 19820707, 'jinjin77@abc.com', '044-500-7333', '010-7877-7777', '세종', default(isBlackList)),
       (12008, '류은수', 19830301, 'eunsuryu@abc.com', '062-233-1122', '010-7444-1474', '광주', default(isBlackList)),
       (12009, '김동철', 19870426, 'eastiron@abc.com', '061-887-4454', '010-8525-1235', '순천', default(isBlackList)),
       (12010, '최홍석', 19900405, 'redstone_choi@abc.com', '054-555-7897', '010-3214-6547', '포항', default(isBlackList)),
       (12011, '김동수', 19830108, 'dong_water@abc.com', '043-529-8457', '010-9566-4228', '제천', default(isBlackList));
       
-- insert book << 이건 dml에서 sql문 실행시키기(길어서 안 가져옴ㅎㅎ)

-- insert rentalstatus
-- case1. blacklist << 연체일수 100일 이상
insert into rentalstatus
values (null, '40001-1', 12001, 20201231, null, default(delaydate));

update book
  set isRented = 0
 where bookno = '40001-1'; -- 완료
 
-- case2. blacklist << 연체 권수 10권 이상 << 작성필요!
insert into rentalstatus
values (null, '40001-2', 12004, 20210307, null, default(delaydate)),
       (null, '40002-1', 12002, 20210308, null, default(delaydate));
      
update book
   set isRented = 0
 where bookno = '40001-2';

update book
   set isRented = 0
 where bookno = '40002-1'; -- 완료
 

-- 반납 함
update rentalstatus set userreturndate = 20210315 where rentalno = 2;

update book
   set isRented = 1
 where bookno = '40001-2';

-- 12002 연체 5권 잡는지 테스트
insert into rentalstatus 
values (null, '40003-1', 12002, 20210420, null, default(delaydate));

update book
   set isRented = 0
 where bookno = '40003-1';

insert into rentalstatus 
values (null, '40004-1', 12002, 20210420, null, default(delaydate));

update book
   set isRented = 0
 where bookno = '40004-1';


insert into rentalstatus 
values (null, '40005-1', 12002, 20210425, null, default(delaydate));

update book
   set isRented = 0
 where bookno = '40005-1';

insert into rentalstatus 
values (null, '40006-1', 12002, 20210419, null, default(delaydate));

update book
   set isRented = 0
 where bookno = '40006-1';

insert into rentalstatus 
values (null, '40007-1', 12002, 20210419, null, default(delaydate));

update book
   set isRented = 0
 where bookno = '40007-1';

