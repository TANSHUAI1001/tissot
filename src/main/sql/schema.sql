 create table activity(
`activity_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '秒杀活动id',
`name` VARCHAR(120) NOT NULL COMMENT '秒杀活动名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` TIMESTAMP NOT NULL COMMENT '开启时间',
`end_time`  TIMESTAMP NOT NULL COMMENT '结束时间',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

PRIMARY KEY (activity_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='库存';

-- 初始化数据
INSERT INTO activity(name,number,start_time,end_time)
values
('1000元秒杀iPad',100,'2018-12-31 00:00:00','2019-01-01 00:00:00'),
('1000元秒杀iPhone7',200,'2018-12-31 00:00:00','2019-01-01 00:00:00'),
('1000元秒杀红米note',300,'2018-12-31 00:00:00','2019-01-01 00:00:00'),
('1000元秒杀macBook pro',10,'2018-12-31 00:00:00','2019-01-01 00:00:00'),
('1000元秒杀小米5',400,'2018-12-31 00:00:00','2019-01-01 00:00:00');

-- 明细表
-- 用户相关信息
CREATE TABLE success_record(
`activity_id` BIGINT NOT NULL COMMENT '商品id',
`user_phone` BIGINT NOT NULL COMMENT '用户手机号',
`state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标示：-1 无效；0 成功；1 已付款',
`create_time` timestamp NOT NULL COMMENT '创建时间',

PRIMARY KEY(activity_id,user_phone),
KEY idx_create_time(create_time)

)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='秒杀明细表';


-- 可查看表的创建过程以及注释
# show create table activity\G

