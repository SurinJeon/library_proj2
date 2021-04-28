select rentalno, bookno, booktitle, bookcategory, categoryname, userno, rentaldate, userreturndate, delaydate from vw_all 
where bookno = '40001-1' and userreturndate is null;

select * from rentalstatus;

select rentalno, bookno, booktitle, bookcategory, categoryname, userno, rentaldate, userreturndate, delaydate from vw_all 
where bookno = '40001-1' and userreturndate is null;

update rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno
 set r.delaydate = datediff(curdate(),date(r.rentaldate + b.rentalrange))
 where r.userreturndate is null;

select date_add(r.rentaldate, interval b.rentalrange day) from rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno;

select DATEDIFF(curdate(), date_add(r.rentaldate, interval b.rentalrange day))
from  rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno
where b.bookno = '40001-1';

select * from rentalstatus;

update rentalstatus r left join book b on r.bookno = b.bookno set r.delaydate = DATEDIFF(r.userreturndate ,date(r.rentaldate + b.rentalrange)) where r.userreturndate is not null;

select max(userno) from user;
select curdate();
 
select bookno, booktitle, categoryname, rentaldate, date(rentaldate + rentalrange) as returndate, userreturndate, delaydate from vw_all where userno = 12001 order by rentalno asc;
select rentalno, userno, username, rentaldate, date(rentaldate + rentalrange) as returndate, userreturndate, delaydate from vw_all where bookno = '40005-1' order by rentalno asc;

select * from rentalstatus;

-- 연체도서 수
select count(bookno) from vw_all where isRented = 0 and delaydate > 0 and userreturndate is null;

-- 대출 가능 도서 수
select count(bookno) from book where isRented = 1;

-- 전체 회원 수
select count(userno) from user;

-- 최빈값
select left(bookno, 5), count(*) as c from vw_all where rentaldate between date(20210401) and date(20210430) group by left(bookno, 5) having c>0; 

select bookno, count(*) as c from vw_all where rentaldate between date(20210401) and date(20210430) group by bookno having c>0; 

select * from vw_all;
select * from vw_all order by rentalno asc;


-- 도서코드
select categoryname from bookcategory where bookcategory = 1;
select bookcategory, categoryname from bookcategory;

-- 연장기능
select userno, bookno, rentalrange * 2 from vw_all where userno = 12001;

-- 블랙리스트
select userno, case when delaydate > 0 then 1 end as 'delay' from vw_all;
