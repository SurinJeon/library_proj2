show tables;

-- insert 순서
-- 1. bookcategory
-- 2. book
-- 3. user
-- 4. manager
-- 5. rentalstatus

drop table manager; 
drop table rentalstatus; 
drop table bookcategory; 
drop table book;
drop table user; 

select * from rentalstatus;

select * from book;

-- bookcategory
desc bookcategory;

insert into bookcategory
values (1, '수학'), (2, '전자'), (3, '컴퓨터'), (4, '통계');

select * from bookcategory;

-- book
desc book;

insert into book 
values ('40001-1', 'The elements of statistical learning', default(isRented) , 1, 1, 3),
	   ('40001-2', 'The elements of statistical learning', default(isRented), 1, 1, 3),
	   ('40001-3', 'The elements of statistical learning', default(isRented), 1, 1, 3),
	   ('40001-4', 'The elements of statistical learning', default(isRented), 1, 1, 3),
	   ('40001-5', 'The elements of statistical learning', default(isRented), 1, 1, 3),
	   ('40002-1', 'Computer vision : a modern approach', default(isRented), 3, 1, 5),
	   ('40002-2', 'Computer vision : a modern approach', default(isRented), 3, 1, 5),
	   ('40002-3', 'Computer vision : a modern approach', default(isRented), 3, 1, 5),
	   ('40002-4', 'Computer vision : a modern approach', default(isRented), 3, 1, 5),
	   ('40002-5', 'Computer vision : a modern approach', default(isRented), 3, 1, 5),
	   ('40003-1', 'MATLAB for engineers', default(isRented), 2, 1, 3),
	   ('40003-2', 'MATLAB for engineers', default(isRented), 2, 1, 3),
	   ('40003-3', 'MATLAB for engineers', default(isRented), 2, 1, 3),
	   ('40003-4', 'MATLAB for engineers', default(isRented), 2, 1, 3),
	   ('40003-5', 'MATLAB for engineers', default(isRented), 2, 1, 3);
	  
insert into book
values ('40004-1', 'CUDA by example', default(isRented), 1, 1, 3),
	   ('40004-2', 'CUDA by example', default(isRented), 1, 1, 3),
	   ('40004-3', 'CUDA by example', default(isRented), 1, 1, 3),
	   ('40004-4', 'CUDA by example', default(isRented), 1, 1, 3),
	   ('40004-5', 'CUDA by example', default(isRented), 1, 1, 3);
	  
insert into book
values ('40005-1', 'An introduction to 3D computer vision', default(isRented), 3, 1, 5),
	   ('40005-2', 'An introduction to 3D computer vision', default(isRented), 3, 1, 5),
	   ('40005-3', 'An introduction to 3D computer vision', default(isRented), 3, 1, 5),
	   ('40005-4', 'An introduction to 3D computer vision', default(isRented), 3, 1, 5),
	   ('40005-5', 'An introduction to 3D computer vision', default(isRented), 3, 1, 5);
	  
insert into book
values ('40006-1', 'Numerical methods', default(isRented), 1, 1, 3),
	   ('40006-2', 'Numerical methods', default(isRented), 1, 1, 3),
	   ('40006-3', 'Numerical methods', default(isRented), 1, 1, 3),
	   ('40006-4', 'Numerical methods', default(isRented), 1, 1, 3),
	   ('40006-5', 'Numerical methods', default(isRented), 1, 1, 3);
	  
insert into book
values ('40007-1', 'Image-based modeling', default(isRented), 3, 1, 5),
       ('40007-2', 'Image-based modeling', default(isRented), 3, 1, 5),
       ('40007-3', 'Image-based modeling', default(isRented), 3, 1, 5),
       ('40007-4', 'Image-based modeling', default(isRented), 3, 1, 5),
       ('40007-5', 'Image-based modeling', default(isRented), 3, 1, 5);	  
	  
insert into book
values ('40008-1', 'Machine learning', default(isRented), 3, 1, 5),
	   ('40008-2', 'Machine learning', default(isRented), 3, 1, 5),
	   ('40008-3', 'Machine learning', default(isRented), 3, 1, 5),
	   ('40008-4', 'Machine learning', default(isRented), 3, 1, 5),
	   ('40008-5', 'Machine learning', default(isRented), 3, 1, 5);
	  
insert into book
values ('40009-1', 'Probabilistic robotics', default(isRented), 4, 1, 5),
	   ('40009-2', 'Probabilistic robotics', default(isRented), 4, 1, 5),	  
	   ('40009-3', 'Probabilistic robotics', default(isRented), 4, 1, 5),	  
	   ('40009-4', 'Probabilistic robotics', default(isRented), 4, 1, 5),	  
	   ('40009-5', 'Probabilistic robotics', default(isRented), 4, 1, 5);	  
	  
insert into book
values ('40010-1', 'Pattern recognition and machine learning', default(isRented), 3, 1, 5),
	   ('40010-2', 'Pattern recognition and machine learning', default(isRented), 3, 1, 5),
	   ('40010-3', 'Pattern recognition and machine learning', default(isRented), 3, 1, 5),
	   ('40010-4', 'Pattern recognition and machine learning', default(isRented), 3, 1, 5),
	   ('40010-5', 'Pattern recognition and machine learning', default(isRented), 3, 1, 5);
	  
select * from book;
	  
-- 구분자로 자르기
delete from book where (select substring_index(bookno, '-', 1)) = '400010';

-- user
desc user;

select * from user;

insert into user
values (12001, '홍길동', 19070405, 'kildong@abc.com', '042-421-1739', '010-9741-5821', '대전');

insert into user
values (12002, '김연수', 19850301, 'yeonsu@abc.com', '064-446-8695', '010-4568-5581', '제주'),
       (12003, '김지원', 19860708, 'jiwonkim@abc.com', '053-548-7898', '010-9111-5556', '대구'),
       (12004, '문희원', 19800108, 'happymoon@abc.com', '052-221-1231', '010-7777-7777', '울산'),
       (12005, '유일한', 19790205, 'number_1@abc.com', '051-111-2222', '010-4566-8886', '부산'),
       (12006, '김동수', 19811123, 'kds1123@abc.com', '02-668-8887', '010-1231-1211', '서울'),
       (12007, '배진태', 19820707, 'jinjin77@abc.com', '044-500-7333', '010-7877-7777', '세종'),
       (12008, '류은수', 19830301, 'eunsuryu@abc.com', '062-233-1122', '010-7444-1474', '광주'),
       (12009, '김동철', 19870426, 'eastiron@abc.com', '061-887-4454', '010-8525-1235', '순천'),
       (12010, '최홍석', 19900405, 'redstone_choi@abc.com', '054-555-7897', '010-3214-6547', '포항'),
       (12011, '김동수', 19830108, 'dong_water@abc.com', '043-529-8457', '010-9566-4228', '제천');

      
-- manager
desc manager;

insert into manager
values ('manager_1@lib.go.kr', '11223344'),
       ('manager_2@lib.go.kr', '11223344'),
       ('manager_3@lib.go.kr', '11223344');
      
insert into manager     
values('aaa', 'aaa');
       
-- rentalstatus
desc rentalstatus;
delete from rentalstatus;
select * from rentalstatus;

insert into rentalstatus
values (null, '40001-1', 12001, 20210301, null, default(delaydate));

update book
  set isRented = 0
 where bookno = '40001-1';

insert into rentalstatus
values (null, '40001-2', 12004, 20210307, null, default(delaydate)),
       (null, '40002-1', 12002, 20210308, null, default(delaydate));
      
update book
   set isRented = 0
 where bookno = '40001-2';

update book
   set isRented = 0
 where bookno = '40002-1';

select * from rentalstatus;
select * from book;
select * from user;
select * from bookcategory;
select * from manager;

