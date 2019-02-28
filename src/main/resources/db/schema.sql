DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id VARCHAR(32) NOT NULL COMMENT '唯一标示',
	code VARCHAR(30) NOT NULL COMMENT '编码',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '名称',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	status CHAR(1) DEFAULT '1' COMMENT '状态 1启用 0 停用',
	gmt_create DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	gmt_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;