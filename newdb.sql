CREATE DATABASE  IF NOT EXISTS `jobsearchingnew` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `jobsearchingnew`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: jobsearchingnew
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `years_exp` int DEFAULT NULL,
  `linkedin` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `cv` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (1,10,NULL,NULL),(39,3,'link','cv ne hehe'),(40,NULL,NULL,NULL),(41,2222222,'candidate ungvien1 update lan2',NULL),(42,190901,'test add lang skill2',NULL);
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `location` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `contact` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `website` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `majoring` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` VALUES (1,'Microsoft','Microsoft là một tập đoàn đa quốc gia của Hoa Kỳ','Redmond, Washington, Hoa Kỳ','support.microsoft.com/contactus','https://www.microsoft.com/vi-vn','software operating system'),(2,'Công ty cổ phần Hạ tầng Viễn thông CMC Telecom','CMC Telecom là một trong 8 công ty thành viên của Tập đoàn Công nghệ CMC. Với gần 9 năm xây dựng, phát triển, CMC Telecom là công ty hạ tầng viễn thông nằm trong TOP 4 các công ty viễn thông hàng đầu Việt Nam. CMC Telecom là nhà cung cấp đầu tiên áp dụng công nghệ GPON và sở hữu hạ tầng truyền dẫn 100% cáp quang.','Tầng 11 tòa nhà CMC, Đường Duy Tân, Q. Cầu Giấy, TP. Hà Nội','Tầng 11 tòa nhà CMC, Đường Duy Tân, Q. Cầu Giấy, TP. Hà Nội','https://cmctelecom.vn/','telecom'),(3,'Công ty TNHH Phương Nam Vina','Công ty Phương Nam Vina là doanh nghiệp uy tín với hơn 10 năm kinh nghiệm hoạt động trong lĩnh vực phần mềm website, thiết kế đồ họa, quảng cáo trực tuyến, thương mại điện tử. Đến với công ty chúng tôi, bạn sẽ được làm việc trong môi trường chuyên nghiệp, năng động, công việc ổn định, thu nhập cao và có cơ hội thăng tiến.','190 Bạch Đằng, Phường 24, Quận Bình Thạnh, TPHCM','190 Bạch Đằng, Phường 24, Quận Bình Thạnh, TPHCM','https://phuongnamvina.com','cntt it'),(4,'GrabDelivery','Grab là một công ty công nghệ có trụ sở tại Singapore cung cấp các dịch vụ vận chuyển và đi lại bằng xe hơi tại Singapore và các quốc gia Đông Nam Á khác như Malaysia, Indonesia, Philippines, Việt Nam, Thái Lan, Myanmar và Campuchia. Wikipedia','Tòa nhà Mapletree Business Center, Số 1060 Đại lộ Nguyễn Văn Linh, Phường Tân Phong, Quận 7, Thành phố Hồ Chí Minh, Việt Nam','https://www.grab.com/vn/','https://www.grab.com/vn/','GrabTaxi; GrabCar; GrabBike; GrabHitch; Grab Food; GrabExpress; GrabPay;'),(5,'Samsung Việt Nam','Samsung đi theo triết lý kinh doanh đơn giản: cống hiến tài năng và công nghệ của mình để tạo ra các sản phẩm và dịch vụ vượt trội đóng góp cho một xã hội toàn cầu tốt đẹp hơn. Để đạt được điều này, Samsung hết sức coi trọng con người và công nghệ của mình.','Số 2, đường Hải Triều, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh','+84-2839157310','https://www.samsung.com/vn/','phone tablet laptop cntt it'),(6,'SpaceX','Tập đoàn Công nghệ Khai phá Không gian, viết tắt theo tiếng Anh SpaceX, là một công ty tư nhân Mỹ chuyên sản xuất tên lửa đẩy và tàu vũ trụ có trụ sở tại Hawthorne, California. Công ty được thành lập năm 2002 bởi Elon Musk, một trong những doanh nhân đã sáng lập công ty PayPal và Tesla Motors.','Hawthorne, California, Hoa Kỳ','https://twitter.com/SpaceX','https://www.spacex.com/','SpaceX Starship and Super Heavy Booster rocket SpaceX Starship prototype and Super Heavy Booster Falcon 9 rocket launching Starlink L-23 payload'),(7,'TikTok','TikTok là nền tảng video âm nhạc và mạng xã hội của Trung Quốc được ra mắt vào năm 2017, dành cho các thị trường bên ngoài Trung Quốc. bởi Trương Nhất Minh, người sáng lập của ByteDance.','Trung Quốc','ByteDance','https://www.tiktok.com/vi-VN/','media video clip tiktok short dance'),(12,'Shopee Việt Nam','Ra mắt năm 2015, nền tảng thương mại Shopee được xây dựng nhằm cung cấp cho người dùng những trải nghiệm dễ dàng, an toàn và nhanh chóng khi mua sắm trực tuyến thông qua hệ thống hỗ trợ thanh toán và vận hành vững mạnh.\r\nChúng tôi có niềm tin mạnh mẽ rằng trải nghiệm mua sắm trực tuyến phải đơn giản, dễ dàng và mang đến cảm xúc vui thích. Niềm tin này truyền cảm hứng và thúc đẩy chúng tôi mỗi ngày tại Shopee.','số 29 đường Liễu Giai, Phường Ngọc Khánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam','cskh@hotro.shopee.vn','https://shopee.vn/','tmdt'),(13,'Vietnam Airlines','Hãng hàng không Quốc gia Việt Nam là hãng hàng không quốc gia của nước Cộng hòa xã hội chủ nghĩa Việt Nam và là thành phần nòng cốt của Tổng công ty Hàng không Việt Nam','Số 200 Nguyễn Sơn, P.Bồ Đề, Q.Long Biên, Hà Nội','(+84-24) 38272289','https://www.vietnamairlines.com','aviation'),(14,'KFC Việt Nam','KFC là cụm từ viết tắt của Kentucky Fried Chicken - Gà Rán Kentucky, một trong các thương hiệu thuộc Tập đoàn Yum Brands Inc (Hoa Kỳ). KFC chuyên về các sản phẩm gà rán và nướng, với các món ăn kèm theo và các loại sandwiches chế biến từ thịt gà tươi. Hiện nay đang có hơn 20.000 nhà hàng KFC tại 109 quốc gia và vùng lãnh thổ trên toàn thế giới.','Số 292 Bà Triệu, P. Lê Đại Hành, Q. Hai Bà Trưng, TP. Hà Nội','lienhe@kfcvietnam.com.vn','https://kfcvietnam.com.vn/vi/','fastfood');
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_language_candidate` (`candidate_id`),
  CONSTRAINT `fk_language_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (24,'Tiếng Nga','',42),(25,'Tiếng Pháp','',42),(26,'Tiếng Hàn','',42),(36,'Tiếng cc','4 kỹ năng',41),(37,'Tiếng Đức','4 kỹ năng',41),(38,'Tiếng Khóc','4 kỹ năng',41),(39,'Tiếng Lmao','4 kỹ năng',41);
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_qualification_candidate_idx` (`candidate_id`),
  CONSTRAINT `fk_qualification_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` VALUES (7,'nene 750',41),(8,'9.0',41);
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reference`
--

DROP TABLE IF EXISTS `reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reference` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `link` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reference_candidate_idx` (`candidate_id`),
  CONSTRAINT `fk_reference_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
INSERT INTO `reference` VALUES (13,'Facebook','n/a',41),(14,'Youtube','n/a',41),(15,'Instagram','n/a',41);
/*!40000 ALTER TABLE `reference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `level` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_skill_candidate_idx` (`candidate_id`),
  CONSTRAINT `fk_skill_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` VALUES (1,'Java','Master',42),(2,'Java','Master',41),(3,'C#','Beginner',41),(4,'Python','Senior',41),(5,'Spring','Master',41);
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `talent`
--

DROP TABLE IF EXISTS `talent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `talent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_talent_candidate_idx` (`candidate_id`),
  CONSTRAINT `fk_talent_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talent`
--

LOCK TABLES `talent` WRITE;
/*!40000 ALTER TABLE `talent` DISABLE KEYS */;
/*!40000 ALTER TABLE `talent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `avatar` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `active` tinyint DEFAULT '0',
  `full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` tinyint DEFAULT '0',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `joined_date` datetime DEFAULT NULL,
  `candidate_id` int DEFAULT NULL,
  `employer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oq7f070s2fgswrf8v0kwou0qd` (`candidate_id`),
  UNIQUE KEY `UK_ggbcixcrmm7q2anlgt4jm9htm` (`employer_id`),
  KEY `fk_user_candidate_idx` (`candidate_id`),
  KEY `fk_user_employer_idx` (`employer_id`),
  CONSTRAINT `fk_user_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_employer` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'admin','$2a$10$hZnZYzt2JVptfVGAOK59POfuGAT/Ba1f5YI8dDY/qQGfuUDFB8CKu','https://res.cloudinary.com/dxorabap0/image/upload/v1659263863/nelb30hnkj3iljs45vqb.jpg','ADMIN',1,'Nguyễn Hoàng Nam','admin@gmail.com','014324325','2001-09-19',0,'quận Bình Thạnh, thành phố Hồ Chí Minh','2022-05-05 13:19:02',NULL,NULL),(16,'tle115','$2a$10$PZO.QW.444UwAX/Z1QegMOE5e7SksgM32EKqeJUKDR/kSNSvSL6te','https://res.cloudinary.com/dxorabap0/image/upload/v1651835885/tad3sft0wskhbwbnjmuf.png','EMP',1,'Lê Thị Thanh Thuỳ','lethuy01091997@gmail.com','0123456764','2000-05-11',1,'Bắc Kạn','2022-05-07 01:57:52',NULL,4),(34,'nhatuyendung1','$2a$10$Ou5IFFxv2Xhq4Iqy9DP24O01OYIl03yXXQQ.5/sXhiqQhev/IbCRK','https://res.cloudinary.com/dxorabap0/image/upload/v1651837183/izbrvjvf4wnbqybhu5cd.png','EMP',1,'Nhà Tuyển Dụng 1','nhatuyendung1@gmail.com','0543753475','1995-08-06',0,'Hưng Yên','2022-08-05 14:51:37',NULL,2),(51,'nhatuyendungtest','$2a$10$nQXlbN/6wowMEyB7a6j7NOs2arFqvMHOVYJu5nQFPs3EgTLGdPU82','https://res.cloudinary.com/dxorabap0/image/upload/v1651837322/tslkce327ci4n33bakug.jpg','EMP',1,'Nhatuyendungtest','nhatuyendungtest@gmail.com','053874543','1995-09-08',0,'nhatuyendungtest','2022-05-05 13:19:02',NULL,1),(62,'nhatuyendung','$2a$10$JOVb93BfJMvHrJunn7aJdeZjVrChfDmuEOTMTUIrVR2r0eJv.3zJW','https://res.cloudinary.com/dxorabap0/image/upload/v1651837336/v0uuuznv4ncflu3i8itl.webp','EMP',1,'Nhatuyendung','nhatuyendung@gmail.com','057834785','2005-07-08',0,'nhatuyendung','2022-05-07 01:57:52',NULL,5),(66,'spacex_elonmusk','$2a$10$mAaju0LrTWt4zkDwXjTUc.CBcBveFFpvr/4J2U9cfrxzDw17VAn86','https://res.cloudinary.com/dxorabap0/image/upload/v1651888409/ly3ymky09bwbqhxzuvso.jpg','EMP',1,'Elon Musk','spacex_elon@gmail.com','017437534','2002-03-14',0,'Hawthorne, California, Hoa Kỳ','2022-08-05 14:51:37',NULL,6),(90,'phuongnamvina','$2a$10$6kBhM.G1/BzBLIpLuhYUzu.hxjsmhIvM3xSlxqGjZGBS2CU0.lsuO','https://res.cloudinary.com/dxorabap0/image/upload/v1659242419/mmatellaxbrd17dolfor.webp','EMP',1,'Phương Nam Vina','phuongnamvina@gmail.com','075894753458','1984-08-11',0,'Thành phố Hồ Chí Minh','2022-05-07 01:57:52',NULL,3),(94,'shopeevn','$2a$10$n0ixQdpRi5xMTkEHY0DWJOyDyz6AAgcCWr9p1EkjBLwx7C5Gb8PnC','https://res.cloudinary.com/dxorabap0/image/upload/v1659251638/gdo9ekyv5lqouvhv2fws.jpg','EMP',1,'Shopee','shopeevn@gmail.com','19001221','1996-08-08',1,'số 29 đường Liễu Giai, Phường Ngọc Khánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam','2022-08-05 14:51:37',NULL,12),(101,'vnairlines','$2a$10$1hg9xuoGhYUQ/Fu.nQwx1eDTV9W9MM8bsz2tiScijRQP9Egoq6dUG','https://res.cloudinary.com/dxorabap0/image/upload/v1659288633/edwy5okja3rjmzav5hvu.jpg','EMP',1,'Vietnam Airlines','vnairlines@gmail.com','0478623432','1976-08-08',1,'Số 200 Nguyễn Sơn, P.Bồ Đề, Q.Long Biên, Hà Nội','2022-08-05 14:51:37',NULL,13),(102,'kfcvietnam','$2a$10$bnIbO8kkK3bMN9luP96BYufVfWZrhWM08iBpsVu5FTU.PuqTDrsHe','https://res.cloudinary.com/dxorabap0/image/upload/v1659544737/rnjukd7vhmeyf64mfeep.jpg','EMP',1,'KFC VIỆT NAM','kfcvietnam@gmail.com','02838489828','1998-10-29',0,'Số 292 Bà Triệu, P. Lê Đại Hành, Q. Hai Bà Trưng, TP. Hà Nội','2022-05-07 01:57:52',NULL,14),(128,'ungvien1','$2a$10$bnIbO8kkK3bMN9luP96BYufVfWZrhWM08iBpsVu5FTU.PuqTDrsHe',NULL,'CAN',1,NULL,NULL,NULL,NULL,1,NULL,NULL,41,NULL),(129,'ungvien2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,42,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_experience`
--

DROP TABLE IF EXISTS `work_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_experience` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `content` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `position` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_candidate_id_idx` (`candidate_id`),
  CONSTRAINT `fk_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_experience`
--

LOCK TABLES `work_experience` WRITE;
/*!40000 ALTER TABLE `work_experience` DISABLE KEYS */;
INSERT INTO `work_experience` VALUES (3,NULL,NULL,'TMA','Intern',1),(4,NULL,NULL,'Viettel','Fresher',1);
/*!40000 ALTER TABLE `work_experience` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-18 15:41:17
