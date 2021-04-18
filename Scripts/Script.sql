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


 

