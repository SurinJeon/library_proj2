select rentalno, bookno, booktitle, bookcategory, categoryname, userno, rentaldate, userreturndate, delaydate from vw_all 
where bookno = '40001-1' and userreturndate is null;

select * from rentalstatus;