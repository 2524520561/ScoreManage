/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.17-log : Database - trainpro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`trainpro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `trainpro`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `cou_id` varchar(50) NOT NULL,
  `cou_name` varchar(100) NOT NULL,
  PRIMARY KEY (`cou_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`cou_id`,`cou_name`) values 
('1113','c语言');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `cou_id` varchar(50) DEFAULT NULL,
  `stu_id` varchar(50) DEFAULT NULL,
  `score` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cou_id` (`cou_id`),
  KEY `stu_id` (`stu_id`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`cou_id`) REFERENCES `course` (`cou_id`),
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`id`,`cou_id`,`stu_id`,`score`) values 
(8,'1113','fgh',43);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `name` varchar(50) DEFAULT NULL,
  `sex` int(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `stu_id` varchar(100) NOT NULL,
  `status` int(10) DEFAULT NULL,
  `depart` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`name`,`sex`,`phone`,`stu_id`,`status`,`depart`) values 
('1',1,'18888888888','1',1,'1'),
('张志强',1,'sdg','1511421239',1,'dsf'),
('孙万',1,'fsd','asd',1,'1'),
('hrthrthdbsga',1,'fsdvxdhbre','fgh',0,'bdfhtdhuery'),
('abfdhytdg',1,'sdfgsd','sdf',1,'sdf');

/*Table structure for table `teacher_info` */

DROP TABLE IF EXISTS `teacher_info`;

CREATE TABLE `teacher_info` (
  `tea_id` int(11) NOT NULL AUTO_INCREMENT,
  `tea_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `account` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT '123456',
  `status` int(11) DEFAULT NULL,
  `tea_tel` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tea_card` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tea_coll` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tea_sex` int(11) DEFAULT NULL,
  `tea_img` varchar(255) COLLATE utf8_bin DEFAULT '1.jpg',
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `teacher_info` */

insert  into `teacher_info`(`tea_id`,`tea_name`,`account`,`password`,`status`,`tea_tel`,`tea_card`,`tea_coll`,`tea_sex`,`tea_img`) values 
(1,'士大夫','123456','123456',NULL,NULL,NULL,NULL,NULL,'1.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
