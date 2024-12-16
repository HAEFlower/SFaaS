USE bsa_monitoring;

CREATE TABLE IF NOT EXISTS `part` (
                                      `part_id` BIGINT NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(255) NOT NULL COMMENT '부품 이름',
    `big_category` VARCHAR(50) NOT NULL COMMENT '대분류',
    `small_category` VARCHAR(50) NOT NULL COMMENT '소분류',
    `description` TEXT COMMENT '비고',
    `last_warehousing_date` DATETIME NULL COMMENT '최근 입고일',
    `current_quantity` BIGINT NOT NULL DEFAULT 0 COMMENT '현재 수량',
    `minimum_quantity_2p144s` BIGINT NOT NULL DEFAULT 0 COMMENT '2P144S 최소 필요수량',
    `minimum_quantity_2p180s` BIGINT NOT NULL DEFAULT 0 COMMENT '2P180S 최소 필요수량',
    `minimum_quantity_2p192s` BIGINT NOT NULL DEFAULT 0 COMMENT '2P192S 최소 필요수량',
    `minimum_quantity_3p144s` BIGINT NOT NULL DEFAULT 0 COMMENT '3P144S 최소 필요수량',
    `part_type` VARCHAR(20) NOT NULL COMMENT '소모성 구분',
    PRIMARY KEY (`part_id`)
    );

CREATE TABLE IF NOT EXISTS `production_line` (
                                                 `production_line_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                 `daily_target` BIGINT NULL,
                                                 `monthly_target` BIGINT NULL,
                                                 `production_start_time` DATETIME NOT NULL,
                                                 `production_end_time` DATETIME NULL,
                                                 `status` ENUM('RUNNING', 'STOPPED', 'MAINTENANCE') NOT NULL,
    `product` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`production_line_id`),
    INDEX `idx_product` (`product`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `process` (
                                         `process_id` BIGINT NOT NULL,
                                         `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`process_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `production_line_process` (
                                                         `production_line_process_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                         `production_line_id` BIGINT NOT NULL,
                                                         `process_id` BIGINT NOT NULL,
                                                         `base_exec_time` INT NOT NULL DEFAULT 0,
                                                         `status` ENUM('RUNNING', 'STOPPED', 'MAINTENANCE') NOT NULL,
    PRIMARY KEY (`production_line_process_id`),
    CONSTRAINT `fk_plp_production_line__id` FOREIGN KEY (`production_line_id`)
    REFERENCES `production_line` (`production_line_id`),
    CONSTRAINT `fk_plp_process_id` FOREIGN KEY (`process_id`)
    REFERENCES `process` (`process_id`),
    INDEX `idx_production_line_process` (`production_line_id`, `process_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;