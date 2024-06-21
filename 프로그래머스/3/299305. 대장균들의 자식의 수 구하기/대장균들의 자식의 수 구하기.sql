SELECT a.ID, count(b.ID) as CHILD_COUNT
FROM ECOLI_DATA a LEFT OUTER JOIN ECOLI_DATA b ON a.ID = b.PARENT_ID
GROUP BY a.ID
ORDER BY a.ID ASC