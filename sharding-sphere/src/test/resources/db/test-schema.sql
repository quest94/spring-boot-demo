DROP TABLE IF EXISTS `test_data_1`;
CREATE TABLE `test_data_1`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `test_long`    BIGINT NULL DEFAULT NULL,
    `test_str`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` datetime(0) NULL DEFAULT NULL,
    `update_time` datetime(0) NULL DEFAULT NULL,
    `version`     INT NULL DEFAULT 0,
    `test_enum`   INT NULL DEFAULT NULL,
    `tenant_id`   BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8;

DROP TABLE IF EXISTS `test_data_2`;
CREATE TABLE `test_data_2`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `test_long`    BIGINT NULL DEFAULT NULL,
    `test_str`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` datetime(0) NULL DEFAULT NULL,
    `update_time` datetime(0) NULL DEFAULT NULL,
    `version`     INT NULL DEFAULT 0,
    `test_enum`   INT NULL DEFAULT NULL,
    `tenant_id`   BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8;

