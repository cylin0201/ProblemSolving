select distinct(rc.CAR_ID)
from CAR_RENTAL_COMPANY_CAR rc join CAR_RENTAL_COMPANY_RENTAL_HISTORY rh 
on rc.CAR_ID = rh.CAR_ID
where CAR_TYPE = '세단' and date_format(START_DATE, '%m') = 10
order by rc.CAR_ID desc;