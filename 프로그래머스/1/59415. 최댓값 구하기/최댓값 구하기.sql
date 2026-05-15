select DATETIME as 시간
from (select * from ANIMAL_INS order by DATETIME desc)
where rownum = 1;