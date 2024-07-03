SELECT t.FOOD_TYPE, t.REST_ID, t.REST_NAME, t.FAVORITES
FROM (
    SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES, MAX(FAVORITES) OVER(PARTITION BY FOOD_TYPE) AS MAX_FAVORITES
    FROM REST_INFO
) t
WHERE t.FAVORITES = t.MAX_FAVORITES
ORDER BY t.FOOD_TYPE DESC;
