USE bsa_monitoring;

CREATE TABLE IF NOT EXISTS `part` (
                                      `part_id` BIGINT NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(255) NOT NULL COMMENT '부품 이름',
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

CREATE TABLE IF NOT EXISTS `order_sequence` (
                                                `sequence_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `sequence` INT NOT NULL,
                                                `order_date` DATE NOT NULL,
                                                PRIMARY KEY (`sequence_id`),
    INDEX `idx_order_date` (`order_date`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `process_part` (
                                `process_part_id` BIGINT NOT NULL AUTO_INCREMENT,
                                `process_id` BIGINT NOT NULL,
                                `product_id` BIGINT NOT NULL,
                                `part_id` BIGINT NOT NULL,
                                `last_warehousing_date` DATETIME NOT NULL,
                                `current_quantity` INT NOT NULL,
                                `minimum_required_quantity` INT NOT NULL,
                                PRIMARY KEY (`process_part_id`),
                                CONSTRAINT `fk_process_part_process` FOREIGN KEY (`process_id`) REFERENCES `process` (`process_id`),
                                CONSTRAINT `fk_process_part_part` FOREIGN KEY (`part_id`) REFERENCES `part` (`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;