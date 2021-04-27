select rentalno, bookno, booktitle, bookcategory, categoryname, userno, rentaldate, userreturndate, delaydate from vw_all 
where bookno = '40001-1' and userreturndate is null;

select * from rentalstatus;

select rentalno, bookno, booktitle, bookcategory, categoryname, userno, rentaldate, userreturndate, delaydate from vw_all 
where bookno = '40001-1' and userreturndate is null;

update rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno
 set r.delaydate = datediff(curdate(),date(r.rentaldate + b.rentalrange))
 where r.userreturndate is null;

select  DATEDIFF(curdate(),date(r.rentaldate + b.rentalrange))
from  rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno
where b.bookno = '40001-1';

select * from rentalstatus;

update rentalstatus r left join book b on r.bookno = b.bookno set r.delaydate = DATEDIFF(r.userreturndate ,date(r.rentaldate + b.rentalrange)) where r.userreturndate is not null;

select max(userno) from user;
 
select bookno, booktitle, categoryname, rentaldate, date(rentaldate + rentalrange) as returndate, userreturndate, delaydate from vw_all where userno = 12001 order by rentalno asc;
select rentalno, userno, username, rentaldate, date(rentaldate + rentalrange) as returndate, userreturndate, delaydate from vw_all where left(bookno, 5) = '40001' order by rentalno asc;

select * from rentalstatus;

-- 트랜잭션... // 아직 연체하지 않았고, 


