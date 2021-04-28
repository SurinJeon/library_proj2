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
select rentalno, userno, username, rentaldate, date(rentaldate + rentalrange) as returndate, userreturndate, delaydate from vw_all where left(bookno, 5) = '40001' order by rentalno asc;

select * from rentalstatus;

-- 연체도서 수
select count(bookno) from vw_all where isRented = 0 and delaydate > 0 and userreturndate is null;

-- 대출 가능 도서 수
select count(bookno) from book where isRented = 1;

-- 전체 회원 수
select count(userno) from user;





