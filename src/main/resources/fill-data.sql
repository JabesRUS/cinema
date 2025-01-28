INSERT INTO place (name)
SELECT concat(letter, num)
FROM (VALUES ('A'), ('B')) AS letters(letter),
     generate_series(1,5) AS num;