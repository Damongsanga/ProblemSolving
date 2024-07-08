# SELECT i.ITEM_ID,	ITEM_NAME,	RARITY
# FROM ITEM_INFO i LEFT JOIN ITEM_TREE t ON i.ITEM_ID = t.PARENT_ITEM_ID
# WHERE PARENT_ITEM_ID IS NULL
# ORDER BY ITEM_ID DESC;

select item_id, item_name, rarity
from item_info a
where not exists (select 1 from item_tree where a.item_id = parent_item_id)
order by item_id desc