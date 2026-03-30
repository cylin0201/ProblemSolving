select 
    U.USER_ID, 
    U.NICKNAME, 
    concat(U.CITY, ' ', U.STREET_ADDRESS1,' ', U.STREET_ADDRESS2) as '전체주소',
    concat(left(U.TLNO, 3), '-', substring(U.TLNO, 4, 4), '-', right(U.TLNO, 4)) as '전화번호'
from USED_GOODS_BOARD B join USED_GOODS_USER U on B.WRITER_ID = U.USER_ID
group by U.USER_ID
having count(B.BOARD_ID) >= 3
order by U.USER_ID desc;