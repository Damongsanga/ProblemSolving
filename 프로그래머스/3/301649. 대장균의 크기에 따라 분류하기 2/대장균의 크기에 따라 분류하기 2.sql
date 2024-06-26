SELECT ID, CASE 
    WHEN RANKING <= (CNT / 4) * 1 THEN 'CRITICAL'
    WHEN RANKING <= (CNT / 4) * 2 THEN 'HIGH'
    WHEN RANKING <= (CNT / 4) * 3 THEN 'MEDIUM'
    ELSE 'LOW'
END AS COLONY_NAME 
FROM (SELECT ID, RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) AS 'RANKING' FROM ECOLI_DATA) A INNER JOIN (SELECT COUNT(*) AS 'CNT' FROM ECOLI_DATA) B
ORDER BY ID ASC;