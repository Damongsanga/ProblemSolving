select flavor from (
    select flavor
    from (
        select * from FIRST_HALF
        union
        select * from JULY 
    ) a
    group by flavor
    order by sum(total_order) desc
    limit 3
) b