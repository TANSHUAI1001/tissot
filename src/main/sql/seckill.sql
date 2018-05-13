-- 秒杀执行的存储过程

DELIMITER $$

-- row_count() 返回上一条修改类型的sql影响行数(delete, insert, update)
CREATE PROCEDURE `shop`.`execute_seckill`(
in v_sekill_id bigint, in v_phone bigint, in v_kill_time TIMESTAMP,
out r_result int
)
  BEGIN
    DECLARE  insert_count int DEFAULT 0;
    START TRANSACTION;
    INSERT ignore INTO success_record(activity_id,user_phone,create_time)
    VALUES (v_sekill_id,v_phone,v_kill_time);
    SELECT ROW_COUNT() INTO insert_count;
    IF(insert_count = 0) THEN
      ROLLBACK;
      SET r_result = -1;
    ELSEIF (insert_count < 0) THEN
      ROLLBACK;
      SET r_result = -2;
    ELSE
      update activity set number = number  - 1 where activity_id = v_sekill_id
      AND end_time > v_kill_time
      AND start_time < v_kill_time
      AND number > 0;
      SELECT ROW_COUNT() INTO insert_count;
      IF(insert_count = 0) THEN
        ROLLBACK ;
        SET r_result = 0;
      ELSEIF (insert_count < 0) THEN
        ROLLBACK ;
        SET r_result = -2;
      ELSE
        COMMIT ;
        SET r_result = 1;
      END IF;
    END IF;
  END ;
$$

DELIMITER ;

set @r_result = -3;
call execute_seckill(1003,13567890787,now(),@r_result);

SELECT @r_result;

-- show create procedure execute_seckill\G
-- QPS 6000左右