SELECT o.PRODUCT_ID, PRODUCT_NAME, SUM(PRICE * AMOUNT) AS TOTAL_SALES
FROM FOOD_ORDER o JOIN FOOD_PRODUCT p USING(PRODUCT_ID)
WHERE SUBSTR(PRODUCE_DATE, 1, 7) = "2022-05"
GROUP BY o.PRODUCT_ID
ORDER BY TOTAL_SALES DESC, PRODUCT_ID ASC;