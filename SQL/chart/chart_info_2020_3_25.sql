/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.17 : Database - book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `book`;

/*Table structure for table `chart_info` */

DROP TABLE IF EXISTS `chart_info`;

CREATE TABLE `chart_info` (
  `chart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图表id',
  `book_id` char(17) DEFAULT NULL COMMENT '对应书的id',
  `chapter` varchar(20) DEFAULT NULL COMMENT '章',
  `section` varchar(20) DEFAULT NULL COMMENT '节',
  `paragraph` varchar(20) DEFAULT NULL COMMENT '段落',
  `knowledge` varchar(20) DEFAULT NULL COMMENT '知识点',
  `exercise` varchar(20) DEFAULT NULL COMMENT '习题',
  `position` varchar(10) DEFAULT NULL COMMENT '图表位置',
  `chart_name` varchar(256) DEFAULT NULL COMMENT '图表名',
  `update_time` varchar(256) DEFAULT NULL COMMENT '更新时间',
  `update_person` varchar(256) DEFAULT NULL COMMENT '更新人',
  `data_type` varchar(256) DEFAULT NULL COMMENT '内容类型,1:图片,2:表格',
  PRIMARY KEY (`chart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
