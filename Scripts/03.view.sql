select user(), database();

drop view vw_all;


create view vw_all
as
select b.bookno, b.booktitle, b.isRented, b.count, b.rentalrange,
r.rentalno, r.rentaldate, r.userreturndate, r.delaydate,
bc.bookcategory, bc.categoryname,
u.userno, u.username, u.userbirth, u.account, u.tel, u.phone, u.address
from book b left join rentalstatus r on b.bookno = r.bookno left join bookcategory bc on b.bookcategory = bc.bookcategory left join user u on r.userno = u.userno ;

select * from vw_all where rentalno is not null order by rentalno;

select * from vw_all where bookno = '40001-1';

select r.rentalno, r.bookno, r.userno, r.rentaldate, r.userreturndate, r.delaydate from rentalstatus;
select bc.bookcategory, bc.categoryname from bookcategory;
select u.userno, u.username, u.userbirth, u.account, u.tel, u.phone, u.address from user;

update rentalstatus 
   set userreturndate = curdate()
 where rentalno = 1;

update book set isRented = 1 where bookno = '40001-1';

insert into rentalstatus values(null, '40001-1', 12005, curdate(), null, default);

update book
   set isRented = 0
 where bookno = '40001-1';

select * from book;

select rentalno, bookno, booktitle, rentaldate, userreturndate, delaydate
 from vw_all where userno = 12001;

select rentalno, bookno, userno, rentaldate, userreturndate, delaydate from rentalstatus;

select rentalno, bookno, booktitle, categoryname, userno, rentaldate, userreturndate, delaydate
 from vw_all where bookno = '40001-1' and userreturndate is null;
 
select * from book;

select left(bookno, 5), booktitle, count(case when isRented = 1 then 1 end) as canRent
  from book 
 where bookno like '40001%'
 group by left(bookno, 5);

select left(bookno, 5), booktitle, count(case when isRented = 1 then 1 end) as canRent
  from book 
 group by left(bookno, 5);

select left(bookno, 5), booktitle, count(case when isRented = 1 then 1 end) as canRent
  from vw_all
 where categoryname = '수학'
 group by left(bookno, 5);

select bookno, booktitle, isRented
  from vw_all
 where bookno like '%4001%'
 group by bookno;

desc manager;

select left(bookno, 5) as bookno, booktitle, count(case when isRented = 1 then 1 end) as canRent 
from book group by left(bookno, 5);

select * from book;