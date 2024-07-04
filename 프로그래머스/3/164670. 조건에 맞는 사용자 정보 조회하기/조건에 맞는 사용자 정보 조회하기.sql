-- 코드를 입력하세요
SELECT USER_ID, NICKNAME, CONCAT(u.CITY, " ", u.STREET_ADDRESS1," ", u.STREET_ADDRESS2) AS 전체주소, CONCAT(substr(TLNO,1,3), '-', substr(TLNO,4,4), '-', substr(TLNO,8,4)) AS 전화번호
FROM USED_GOODS_BOARD b JOIN USED_GOODS_USER u ON b.WRITER_ID = u.USER_ID
GROUP BY USER_ID
HAVING COUNT(USER_ID) >= 3
ORDER BY USER_ID DESC;
