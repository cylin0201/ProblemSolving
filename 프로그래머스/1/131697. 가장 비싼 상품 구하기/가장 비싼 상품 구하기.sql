select PRICE as MAX_PRICE
from (select PRICE from PRODUCT order by PRICE DESC)
where rownum = 1;