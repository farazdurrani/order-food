CREATE TABLE `admin` (
 `admin_id` int(11) NOT NULL AUTO_INCREMENT,
 `username` varchar(255) DEFAULT NULL,
 `password` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`admin_id`)
) 

CREATE TABLE `customer` (
 `customer_id` int(11) NOT NULL AUTO_INCREMENT,
 `fullname` varchar(255) DEFAULT NULL,
 `username` varchar(255) DEFAULT NULL,
 `password` varchar(255) DEFAULT NULL,
 `signedin` tinyint(1) NOT NULL DEFAULT '0',
 PRIMARY KEY (`customer_id`)
)

CREATE TABLE `customer_menu` (
 `customer_id` int(11) NOT NULL,
 `menu_id` int(11) NOT NULL,
 PRIMARY KEY (`customer_id`,`menu_id`),
 UNIQUE KEY `menu_id_UNIQUE` (`menu_id`),
 KEY `fk_customer` (`customer_id`),
 KEY `fk_menu` (`menu_id`),
 CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
 CONSTRAINT `fk_menu` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`)
)

CREATE TABLE `menus` (
 `menu_id` int(11) NOT NULL AUTO_INCREMENT,
 `day` varchar(45) NOT NULL,
 `name` varchar(45) NOT NULL,
 `price` int(11) NOT NULL,
 `open` tinyint(1) NOT NULL DEFAULT '1',
 `orderDate` date DEFAULT NULL,
 `single_order_id` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`menu_id`),
 KEY `single_order_id` (`single_order_id`),
 CONSTRAINT `menus_ibfk_1` FOREIGN KEY (`single_order_id`) REFERENCES `singleorders` (`single_order_id`)
) 

CREATE TABLE `menusforshow` (
 `menu_id` int(11) NOT NULL AUTO_INCREMENT,
 `day` varchar(45) NOT NULL,
 `name` varchar(45) NOT NULL,
 `price` int(11) NOT NULL,
 PRIMARY KEY (`menu_id`)
) 

CREATE TABLE `singleorders` (
 `useless_id` int(11) NOT NULL AUTO_INCREMENT,
 `single_order_id` bigint(20) NOT NULL,
 `person_name` varchar(255) NOT NULL,
 PRIMARY KEY (`useless_id`),
 KEY `single_order_id` (`single_order_id`)
)

CREATE TABLE `singleorder_menu` (
 `useless_id` int(11) NOT NULL,
 `menu_id` int(11) NOT NULL,
 PRIMARY KEY (`useless_id`,`menu_id`),
 UNIQUE KEY `menu_id_UNIQUE` (`menu_id`),
 KEY `fk_singleorder` (`useless_id`),
 KEY `fk_menuXXX` (`menu_id`),
 CONSTRAINT `fk_menuXXX` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`),
 CONSTRAINT `fk_singleorder` FOREIGN KEY (`useless_id`) REFERENCES `singleorders` (`useless_id`)
)