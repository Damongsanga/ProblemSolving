SELECT i.REST_ID, i.REST_NAME, i.FOOD_TYPE, i.FAVORITES, i.ADDRESS, ROUND(AVG(r.REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO i INNER JOIN REST_REVIEW r on i.REST_ID = r.REST_ID
GROUP BY i.REST_ID
HAVING SUBSTR(i.ADDRESS, 1, 2) = "서울" 
ORDER BY SCORE DESC, i.FAVORITES DESC
