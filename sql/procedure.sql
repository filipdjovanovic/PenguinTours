USE agency;

DELIMITER $$
CREATE PROCEDURE update_arrangement_status()
BEGIN
  UPDATE arrangement a1
  SET a1.status = "Nedostupan"
  WHERE a1.id IN (
    SELECT aid
    FROM
    (SELECT p.arrangement_id as aid
    FROM 
    program p
    JOIN arrangement a
    on p.arrangement_id = a.id
    GROUP BY p.arrangement_id
    HAVING min(p.date) < CURDATE()) AS T);
END $$
DELIMITER ;

DELIMITER $$
CREATE EVENT Every24Hours
ON SCHEDULE
EVERY 24 HOUR
STARTS '2023-02-15 00:00:00'
DO
BEGIN
  CALL update_arrangement_status();
END $$
DELIMITER ;
