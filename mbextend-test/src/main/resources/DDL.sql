-- test.china_region definition

CREATE TABLE `china_region` (
    `id` int NOT NULL AUTO_INCREMENT,
    `pid` int DEFAULT NULL,
    `region_code` varchar(9) DEFAULT NULL,
    `region_name` varchar(32) DEFAULT NULL,
    `type` tinyint DEFAULT NULL,
    `post_code` varchar(10) DEFAULT NULL,
    `longitude` varchar(12) DEFAULT NULL,
    `latitude` varchar(12) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- test.`member` definition

CREATE TABLE `member` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `username` varchar(100) DEFAULT NULL,
    `phone` varchar(11) DEFAULT NULL,
    `birthday` datetime DEFAULT NULL,
    `gender` set('M','F') DEFAULT NULL,
    `money` decimal(10,0) DEFAULT NULL,
    `address_code` varchar(9) DEFAULT NULL,
    `address_name` varchar(64) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- test.`order` definition

CREATE TABLE `order` (
    `id` varchar(32) NOT NULL,
    `member_id` bigint DEFAULT NULL,
    `status` tinyint DEFAULT NULL,
    `total_price` decimal(10,0) DEFAULT NULL,
    `create_time` datetime DEFAULT NULL,
    `address_name` varchar(64) DEFAULT NULL,
    `address_code` varchar(9) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `order_member_id_IDX` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- test.order_item definition

CREATE TABLE `order_item` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `order_id` varchar(32) DEFAULT NULL,
    `product_id` bigint DEFAULT NULL,
    `number` int DEFAULT NULL,
    `price` decimal(10,0) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- test.product definition

CREATE TABLE `product` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `category_id` bigint DEFAULT NULL,
    `name` varchar(100) DEFAULT NULL,
    `price` decimal(10,0) DEFAULT NULL,
    `stock` int DEFAULT NULL,
    `status` tinyint DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- test.product_category definition

CREATE TABLE `product_category` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `parent_id` bigint DEFAULT NULL,
    `name` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;