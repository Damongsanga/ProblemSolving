SELECT COUNT(USER_ID) AS USERS
FROM USER_INFO
WHERE JOINED BETWEEN "2021-01-01" AND "2021-12-31"
AND AGE BETWEEN 20 AND 29;