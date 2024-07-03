SELECT  id, fish_name, length
FROM  FISH_INFO fi LEFT JOIN FISH_NAME_INFO fni ON fi.fish_type = fni.fish_type
WHERE (fi.fish_type, fi.length) in (
            SELECT fish_type, MAX(length) AS length
            FROM FISH_INFO
            GROUP BY fish_type
        )
ORDER BY id;