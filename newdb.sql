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
-- Table structure for table `apply_job`
--

DROP TABLE IF EXISTS `apply_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apply_job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL,
  `candidate_user_id` int NOT NULL,
  `created_date` datetime NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_app_job_idx` (`job_id`),
  KEY `fk_app_canduser_idx` (`candidate_user_id`),
  CONSTRAINT `fk_app_canduser` FOREIGN KEY (`candidate_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_app_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply_job`
--

LOCK TABLES `apply_job` WRITE;
/*!40000 ALTER TABLE `apply_job` DISABLE KEYS */;
INSERT INTO `apply_job` VALUES (7,5,129,'2022-05-05 13:19:02','APPROVED'),(9,12,129,'2022-05-02 13:19:02','PENDING'),(11,6,137,'2022-05-05 13:19:02','APPROVED'),(12,12,128,'2022-05-05 13:19:02','CANCELLED'),(14,11,129,'2022-09-19 01:01:35','PENDING'),(15,6,128,'2022-10-12 10:45:18','BLOCKED'),(17,11,137,'2022-10-12 11:30:22','PENDING'),(18,5,128,'2022-05-15 13:19:02','APPROVED'),(19,5,137,'2022-09-19 19:29:12','PENDING'),(20,5,146,'2022-05-05 03:09:43','PENDING'),(21,5,147,'2022-09-19 12:09:02','PENDING'),(22,5,148,'2022-09-19 13:19:02','PENDING');
/*!40000 ALTER TABLE `apply_job` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (1,10,NULL,NULL),(39,3,'link','cv ne hehe'),(40,NULL,NULL,NULL),(41,10,'all properties','all properties'),(42,190901,'test add lang skill2',NULL),(44,0,NULL,NULL),(45,0,NULL,NULL),(46,0,NULL,NULL),(47,10,'ungvien4 link','https://res.cloudinary.com/nhn1909/image/upload/v1665760711/jcbieum3pg4dykcmeuvh.jpg'),(48,0,NULL,NULL),(49,0,NULL,NULL);
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `candidate_id` int NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `created_date` datetime NOT NULL,
  `is_available` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cmt_comp_idx` (`company_id`),
  KEY `fk_cmt_cand_idx` (`candidate_id`),
  CONSTRAINT `fk_cmt_cand` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`),
  CONSTRAINT `fk_cmt_comp` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,22,41,'comment ne` ma\' uiiii','2022-09-29 16:36:01',1),(2,21,41,'cmt comp_id = 21 uv1','2022-09-29 16:36:01',1),(3,21,42,'cmt comp_id = 21 uv2','2022-10-11 16:36:01',1),(4,22,42,'comment 22 42','2022-10-07 15:38:57',1),(5,22,41,'128cand comment 136comp','2022-10-10 23:44:18',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `company_size` int DEFAULT '0',
  `contact_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `contact_tel` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `contact_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `contact_address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `introduction` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `founded_year` int DEFAULT '0',
  `headquarters` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `link` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (21,'BMW Group Update',10000,'company updt1','company updt1','company updt1','company updt1','company updt1',0,'company updt1','company updt1'),(22,'Audi AG Aktiengesellschaft - Motor Vehicle Manufacturing',10001,'AUDI AG',NULL,NULL,'NSU Straße 1, Neckarsulm, Baden-Wuerttemberg 74148, DE','#WeAreProgress ++ Progress is in our DNA. It’s not just in our cars, but also in us. The focus at Audi is on us – the people – and we are shaping the future of mobility together. With our inner drive. With the aim to continuously improve. With our mindset, courage and confidence. Because progress develops in the mind – and in the heart!',0,'Auto-Union-Straße 1, Ingolstadt, Bayern 85045, DE','http://www.audi.com');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_industry`
--

DROP TABLE IF EXISTS `company_industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_industry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NOT NULL,
  `industry_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comind_com_idx` (`company_id`),
  KEY `fk_comind_ind_idx` (`industry_id`),
  CONSTRAINT `fk_comind_com` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `fk_comind_ind` FOREIGN KEY (`industry_id`) REFERENCES `industry` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_industry`
--

LOCK TABLES `company_industry` WRITE;
/*!40000 ALTER TABLE `company_industry` DISABLE KEYS */;
INSERT INTO `company_industry` VALUES (7,22,15),(12,21,18),(13,21,15);
/*!40000 ALTER TABLE `company_industry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `industry`
--

DROP TABLE IF EXISTS `industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `industry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `industry`
--

LOCK TABLES `industry` WRITE;
/*!40000 ALTER TABLE `industry` DISABLE KEYS */;
INSERT INTO `industry` VALUES (1,'Aerospace'),(2,'Transport '),(3,'Computer '),(4,'Telecommunication '),(5,'Agriculture '),(6,'Construction '),(7,'Education '),(8,'Pharmaceutical '),(9,'Food '),(10,'Health care'),(11,'Hospitality '),(12,'Entertainment '),(13,'News Media'),(14,'Energy '),(15,'Manufacturing '),(16,'Music '),(17,'Mining '),(18,'Electronics ');
/*!40000 ALTER TABLE `industry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_company_id` int NOT NULL,
  `title` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `is_available` tinyint NOT NULL DEFAULT '1',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `date_published` datetime DEFAULT NULL,
  `job_start_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `no_of_vacancies` int DEFAULT '0',
  `salary` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `address` longtext COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `province_id` int NOT NULL,
  `position_id` int NOT NULL,
  `category_id` int NOT NULL,
  `job_type_id` int NOT NULL,
  `apply_job_counter` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_job_cate_idx` (`category_id`),
  KEY `fk_job_pos_idx` (`position_id`),
  KEY `fk_job_usercomp_idx` (`user_company_id`),
  KEY `fk_job_jtype_idx` (`job_type_id`),
  KEY `fk_job_province_idx` (`province_id`),
  CONSTRAINT `fk_job_cate` FOREIGN KEY (`category_id`) REFERENCES `job_category` (`id`),
  CONSTRAINT `fk_job_jtype` FOREIGN KEY (`job_type_id`) REFERENCES `job_type` (`id`),
  CONSTRAINT `fk_job_pos` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `fk_job_province` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`),
  CONSTRAINT `fk_job_usercomp` FOREIGN KEY (`user_company_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,134,'Java Developer (All Levels) ',1,'We\'re looking for 10 Java Developers (all levels: Senior, Junior, Fresher).',NULL,NULL,NULL,10,'11000000','address',1,8,2,1,0),(3,134,'Telesales Mảng Tài Chính (Nhận Sinh Viên Mới Ra Trường - Không Yêu Cầu Kinh Nghiệm)',1,'Cơ hội cho các bạn Sinh viên mới ra trường muốn thử sức lĩnh vực tài chính, gia tăng thu nhập và học hỏi kinh nghiệm ở môi trường chuyên nghiệp:',NULL,NULL,'2022-10-10 23:26:04',10,'10000000','14F tòa nhà Pico Plaza, số 20 Cộng Hoà, Phường 12, Quận Tân Bình',79,8,1,1,0),(4,134,'test tiep ne',0,NULL,'2022-09-22 16:54:44',NULL,'2022-09-22 16:54:44',0,'50000000','address',15,3,2,1,0),(5,134,'Nhân Viên Digital Marketing Thu Nhập Upto 20 Triệu',1,'Cơ hội cho các bạn Sinh viên mới ra trường muốn thử sức lĩnh vực tài chính, gia tăng thu nhập và học hỏi kinh nghiệm ở môi trường chuyên nghiệp:','2022-09-22 21:37:26',NULL,'2022-10-15 00:42:14',40,'20000000','',79,1,27,1,6),(6,136,'test tiep ne33',1,NULL,'2022-09-22 21:56:25',NULL,'2022-09-22 21:56:25',0,'40000000','address',75,1,3,1,2),(7,136,'company job updt',0,'company job updt','2022-09-22 21:58:13',NULL,'2022-09-22 21:58:13',1021,'999999','thị trấn Ea Súp, huyện Ea Súp',66,4,4,4,2),(8,136,'loi 500',1,'loi 500','2022-09-22 22:01:56',NULL,'2022-09-22 22:01:56',0,'40000000','address',1,3,5,3,0),(9,136,'loi 500 lan nua xem',0,'loi 500','2022-09-22 22:10:35',NULL,'2022-09-22 22:10:35',0,'40000000','address',79,1,2,1,0),(10,134,'loi 415 lan nua xem',1,'loi 415','2022-10-04 00:19:07',NULL,'2022-10-04 00:19:07',0,'25000000','address',56,1,12,1,0),(11,134,'loi 415 lan nua xem 1',0,'loi 415','2022-10-04 08:26:36',NULL,'2022-10-04 08:26:36',0,'25000000','address',79,1,12,1,1),(12,136,'loi 500 lan nua xem',1,'loi 500','2022-10-04 13:57:16',NULL,'2022-10-04 13:57:16',200,'25000000','address',79,1,12,1,0),(41,134,'Sale BMW',1,'Bán xe BMW i8','2022-10-15 00:17:38',NULL,NULL,100,'42000000','Quận 7',79,3,7,3,0),(43,134,'Sale BMW',1,'Bán xe BMW Q7','2022-10-15 00:25:23',NULL,NULL,100,'42000000','Quận 7',79,3,7,3,0);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_category`
--

DROP TABLE IF EXISTS `job_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_category`
--

LOCK TABLES `job_category` WRITE;
/*!40000 ALTER TABLE `job_category` DISABLE KEYS */;
INSERT INTO `job_category` VALUES (1,'Kinh doanh / Bán hàng',NULL),(2,'Biên / Phiên dịch',NULL),(3,'Bưu chính - Viễn thông',NULL),(4,'Bảo hiểm',NULL),(5,'Bất động sản',NULL),(6,'Chứng khoán / Vàng / Ngoại tệ',NULL),(7,'Công nghệ cao',NULL),(8,'Cơ khí / Chế tạo / Tự động hoá',NULL),(9,'Du lịch',NULL),(10,'Dầu khí / Hoá chất',NULL),(11,'Dệt may / Da giày',NULL),(12,'Dịch vụ khách hàng',NULL),(13,'Điện tử viễn thông',NULL),(14,'Điện / Điện tử / Điện lạnh',NULL),(15,'Giáo dục / Đào tạo',NULL),(16,'Hoá học / Sinh học',NULL),(17,'Hoạch định / Dự án',NULL),(18,'Hàng gia dụng',NULL),(19,'Hàng hải',NULL),(20,'Hàng không',NULL),(21,'Hành chính / Văn phòng',NULL),(22,'In ấn / Xuất bản',NULL),(23,'IT Phần cứng / Mạng',NULL),(24,'IT Phần mềm',NULL),(25,'Nhà hàng / Khách sạn',NULL),(26,'Kế toán / Kiểm toán',NULL),(27,'Marketing / Truyền thông / Quảng cáo',NULL),(28,'Môi trường / Xử lý chất thải',NULL),(29,'Mỹ phẩm / Trang sức',NULL),(30,'Mỹ thuật / Nghệ thuật / Điện ảnh',NULL);
/*!40000 ALTER TABLE `job_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_tag`
--

DROP TABLE IF EXISTS `job_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL,
  `tag_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jobtag_job_idx` (`job_id`),
  KEY `fk_jobtag_tag_idx` (`tag_id`),
  CONSTRAINT `fk_jobtag_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_jobtag_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_tag`
--

LOCK TABLES `job_tag` WRITE;
/*!40000 ALTER TABLE `job_tag` DISABLE KEYS */;
INSERT INTO `job_tag` VALUES (1,1,1),(2,1,3),(3,1,5),(4,1,7),(5,1,12),(103,7,1),(104,7,6),(105,7,7),(200,3,1),(201,3,4),(202,3,6),(203,3,12),(204,3,13),(205,41,5),(206,41,6),(207,41,7),(211,43,5),(212,43,6),(213,43,7),(222,5,1),(223,5,2),(224,5,4),(225,5,6);
/*!40000 ALTER TABLE `job_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_type`
--

DROP TABLE IF EXISTS `job_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_type`
--

LOCK TABLES `job_type` WRITE;
/*!40000 ALTER TABLE `job_type` DISABLE KEYS */;
INSERT INTO `job_type` VALUES (1,'Full-time',NULL),(2,'Contract',NULL),(3,'Part-time',NULL),(4,'Internship',NULL),(5,'Teporary',NULL);
/*!40000 ALTER TABLE `job_type` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (24,'Tiếng Nga','',42),(25,'Tiếng Pháp','',42),(26,'Tiếng Hàn','',42),(46,'Tieng Viet','tieng me de',41),(47,'Tieng Anh','hoc ngu lam',41),(50,'Tieng Viet','tieng me de',47),(51,'Tieng Anh','hoc ngu lam',47);
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Nhân viên',NULL),(2,'Trưởng nhóm',NULL),(3,'Trưởng / Phó phòng',NULL),(4,'Quản lý / Giám sát',NULL),(5,'Trưởng chi nhánh',NULL),(6,'Phó giám đốc',NULL),(7,'Giám đốc',NULL),(8,'Thực tập sinh',NULL);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `province` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `name_en` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `full_name_en` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `code_name` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (1,'Hà Nội','Ha Noi','Thành phố Hà Nội','Ha Noi City','ha_noi'),(2,'Hà Giang','Ha Giang','Tỉnh Hà Giang','Ha Giang Province','ha_giang'),(4,'Cao Bằng','Cao Bang','Tỉnh Cao Bằng','Cao Bang Province','cao_bang'),(6,'Bắc Kạn','Bac Kan','Tỉnh Bắc Kạn','Bac Kan Province','bac_kan'),(8,'Tuyên Quang','Tuyen Quang','Tỉnh Tuyên Quang','Tuyen Quang Province','tuyen_quang'),(10,'Lào Cai','Lao Cai','Tỉnh Lào Cai','Lao Cai Province','lao_cai'),(11,'Điện Biên','Dien Bien','Tỉnh Điện Biên','Dien Bien Province','dien_bien'),(12,'Lai Châu','Lai Chau','Tỉnh Lai Châu','Lai Chau Province','lai_chau'),(14,'Sơn La','Son La','Tỉnh Sơn La','Son La Province','son_la'),(15,'Yên Bái','Yen Bai','Tỉnh Yên Bái','Yen Bai Province','yen_bai'),(17,'Hoà Bình','Hoa Binh','Tỉnh Hoà Bình','Hoa Binh Province','hoa_binh'),(19,'Thái Nguyên','Thai Nguyen','Tỉnh Thái Nguyên','Thai Nguyen Province','thai_nguyen'),(20,'Lạng Sơn','Lang Son','Tỉnh Lạng Sơn','Lang Son Province','lang_son'),(22,'Quảng Ninh','Quang Ninh','Tỉnh Quảng Ninh','Quang Ninh Province','quang_ninh'),(24,'Bắc Giang','Bac Giang','Tỉnh Bắc Giang','Bac Giang Province','bac_giang'),(25,'Phú Thọ','Phu Tho','Tỉnh Phú Thọ','Phu Tho Province','phu_tho'),(26,'Vĩnh Phúc','Vinh Phuc','Tỉnh Vĩnh Phúc','Vinh Phuc Province','vinh_phuc'),(27,'Bắc Ninh','Bac Ninh','Tỉnh Bắc Ninh','Bac Ninh Province','bac_ninh'),(30,'Hải Dương','Hai Duong','Tỉnh Hải Dương','Hai Duong Province','hai_duong'),(31,'Hải Phòng','Hai Phong','Thành phố Hải Phòng','Hai Phong City','hai_phong'),(33,'Hưng Yên','Hung Yen','Tỉnh Hưng Yên','Hung Yen Province','hung_yen'),(34,'Thái Bình','Thai Binh','Tỉnh Thái Bình','Thai Binh Province','thai_binh'),(35,'Hà Nam','Ha Nam','Tỉnh Hà Nam','Ha Nam Province','ha_nam'),(36,'Nam Định','Nam Dinh','Tỉnh Nam Định','Nam Dinh Province','nam_dinh'),(37,'Ninh Bình','Ninh Binh','Tỉnh Ninh Bình','Ninh Binh Province','ninh_binh'),(38,'Thanh Hóa','Thanh Hoa','Tỉnh Thanh Hóa','Thanh Hoa Province','thanh_hoa'),(40,'Nghệ An','Nghe An','Tỉnh Nghệ An','Nghe An Province','nghe_an'),(42,'Hà Tĩnh','Ha Tinh','Tỉnh Hà Tĩnh','Ha Tinh Province','ha_tinh'),(44,'Quảng Bình','Quang Binh','Tỉnh Quảng Bình','Quang Binh Province','quang_binh'),(45,'Quảng Trị','Quang Tri','Tỉnh Quảng Trị','Quang Tri Province','quang_tri'),(46,'Thừa Thiên Huế','Thua Thien Hue','Tỉnh Thừa Thiên Huế','Thua Thien Hue Province','thua_thien_hue'),(48,'Đà Nẵng','Da Nang','Thành phố Đà Nẵng','Da Nang City','da_nang'),(49,'Quảng Nam','Quang Nam','Tỉnh Quảng Nam','Quang Nam Province','quang_nam'),(51,'Quảng Ngãi','Quang Ngai','Tỉnh Quảng Ngãi','Quang Ngai Province','quang_ngai'),(52,'Bình Định','Binh Dinh','Tỉnh Bình Định','Binh Dinh Province','binh_dinh'),(54,'Phú Yên','Phu Yen','Tỉnh Phú Yên','Phu Yen Province','phu_yen'),(56,'Khánh Hòa','Khanh Hoa','Tỉnh Khánh Hòa','Khanh Hoa Province','khanh_hoa'),(58,'Ninh Thuận','Ninh Thuan','Tỉnh Ninh Thuận','Ninh Thuan Province','ninh_thuan'),(60,'Bình Thuận','Binh Thuan','Tỉnh Bình Thuận','Binh Thuan Province','binh_thuan'),(62,'Kon Tum','Kon Tum','Tỉnh Kon Tum','Kon Tum Province','kon_tum'),(64,'Gia Lai','Gia Lai','Tỉnh Gia Lai','Gia Lai Province','gia_lai'),(66,'Đắk Lắk','Dak Lak','Tỉnh Đắk Lắk','Dak Lak Province','dak_lak'),(67,'Đắk Nông','Dak Nong','Tỉnh Đắk Nông','Dak Nong Province','dak_nong'),(68,'Lâm Đồng','Lam Dong','Tỉnh Lâm Đồng','Lam Dong Province','lam_dong'),(70,'Bình Phước','Binh Phuoc','Tỉnh Bình Phước','Binh Phuoc Province','binh_phuoc'),(72,'Tây Ninh','Tay Ninh','Tỉnh Tây Ninh','Tay Ninh Province','tay_ninh'),(74,'Bình Dương','Binh Duong','Tỉnh Bình Dương','Binh Duong Province','binh_duong'),(75,'Đồng Nai','Dong Nai','Tỉnh Đồng Nai','Dong Nai Province','dong_nai'),(77,'Bà Rịa - Vũng Tàu','Ba Ria - Vung Tau','Tỉnh Bà Rịa - Vũng Tàu','Ba Ria - Vung Tau Province','ba_ria_vung_tau'),(79,'Hồ Chí Minh','Ho Chi Minh','Thành phố Hồ Chí Minh','Ho Chi Minh City','ho_chi_minh'),(80,'Long An','Long An','Tỉnh Long An','Long An Province','long_an'),(82,'Tiền Giang','Tien Giang','Tỉnh Tiền Giang','Tien Giang Province','tien_giang'),(83,'Bến Tre','Ben Tre','Tỉnh Bến Tre','Ben Tre Province','ben_tre'),(84,'Trà Vinh','Tra Vinh','Tỉnh Trà Vinh','Tra Vinh Province','tra_vinh'),(86,'Vĩnh Long','Vinh Long','Tỉnh Vĩnh Long','Vinh Long Province','vinh_long'),(87,'Đồng Tháp','Dong Thap','Tỉnh Đồng Tháp','Dong Thap Province','dong_thap'),(89,'An Giang','An Giang','Tỉnh An Giang','An Giang Province','an_giang'),(91,'Kiên Giang','Kien Giang','Tỉnh Kiên Giang','Kien Giang Province','kien_giang'),(92,'Cần Thơ','Can Tho','Thành phố Cần Thơ','Can Tho City','can_tho'),(93,'Hậu Giang','Hau Giang','Tỉnh Hậu Giang','Hau Giang Province','hau_giang'),(94,'Sóc Trăng','Soc Trang','Tỉnh Sóc Trăng','Soc Trang Province','soc_trang'),(95,'Bạc Liêu','Bac Lieu','Tỉnh Bạc Liêu','Bac Lieu Province','bac_lieu'),(96,'Cà Mau','Ca Mau','Tỉnh Cà Mau','Ca Mau Province','ca_mau');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` VALUES (15,'Hoc ngu nhat lop',41),(16,'Hoc ngu cap huyen',41),(17,'Hoc ngu cap tinh',41),(21,'Hoc ngu nhat lop',47),(22,'Hoc ngu cap huyen',47),(23,'Hoc ngu cap tinh',47);
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
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `link` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reference_candidate_idx` (`candidate_id`),
  CONSTRAINT `fk_reference_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
INSERT INTO `reference` VALUES (20,'Facebook','fb.com/huhu',41),(21,'Instagram','ig.com/huhu',41),(24,'Facebook','fb.com/huhu',47),(25,'Instagram','ig.com/huhu',47);
/*!40000 ALTER TABLE `reference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirement`
--

DROP TABLE IF EXISTS `requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requirement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `job_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_req_job_idx` (`job_id`),
  CONSTRAINT `fk_req_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirement`
--

LOCK TABLES `requirement` WRITE;
/*!40000 ALTER TABLE `requirement` DISABLE KEYS */;
INSERT INTO `requirement` VALUES (56,'khong qqqqq',7),(109,'Chăm sóc Khách hàng',3),(110,'Tiếp nhận, hoàn thành hồ sơ vay, hướng dẫn thủ tục giải ngân cho khách hàng',3),(111,'Tư vấn làm hồ sơ vay tín chấp cho khách hàng, không cần thu hồi nợ',3),(112,'Data khách hàng đăng ký vay có sẵn',3),(113,'update',3),(114,'Biết về xe oto',41),(115,'Am hiểu về lịch sử của BMW',41),(118,'Biết về xe Audi Q Series',43),(119,'Am hiểu về lịch sử của BMW',43),(131,'Quản trị nội dung và các hoạt động trên website',5),(132,'Nghiên cứu nhu cầu của các đối tượng khách hàng để xây dựng kế hoạch marketing phù hợp.',5),(133,'Req Update',5),(134,'Triển khai các chiến lược đã được phê duyệt',5),(135,'Thống kê, đo lường và báo cáo kết quả đạt được được các chiến lược đã triển khai',5),(136,'Phối hợp với các phòng ban liên quan để đảm bảo tiến độ công việc',5);
/*!40000 ALTER TABLE `requirement` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` VALUES (1,'Java','Master',42),(10,'Dev óp','Mát tờ',41),(11,'Tét tơ','rớt môn',41),(14,'Dev óp','Mát tờ',47),(15,'Tét tơ','rớt môn',47);
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'java'),(2,'javascript'),(3,'js'),(4,'backend'),(5,'be'),(6,'frontend'),(7,'fe'),(8,'mysql'),(9,'sql'),(10,'intern'),(11,'reactjs'),(12,'spring'),(13,'springboot'),(14,'python'),(15,'flask'),(16,'django');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talent`
--

LOCK TABLES `talent` WRITE;
/*!40000 ALTER TABLE `talent` DISABLE KEYS */;
INSERT INTO `talent` VALUES (1,'Đàn',42),(2,'Đấm nhau',42),(3,'Võ mồm',42),(10,'Xiếc',41),(11,'Hát',41),(12,'Học ngu',41),(16,'Xiếc',47),(17,'Hát',47),(18,'Học ngu',47);
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
  `candidate_id` int DEFAULT NULL,
  `company_id` int DEFAULT NULL,
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oq7f070s2fgswrf8v0kwou0qd` (`candidate_id`),
  UNIQUE KEY `UK_ggbcixcrmm7q2anlgt4jm9htm` (`company_id`),
  KEY `fk_user_candidate_idx` (`candidate_id`),
  KEY `fk_user_employer_idx` (`company_id`),
  CONSTRAINT `fk_user_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,NULL,NULL,'admin','$2a$10$hZnZYzt2JVptfVGAOK59POfuGAT/Ba1f5YI8dDY/qQGfuUDFB8CKu','https://res.cloudinary.com/dxorabap0/image/upload/v1659263863/nelb30hnkj3iljs45vqb.jpg','ADMIN',1,'Nguyễn Hoàng Nam','admin@gmail.com','014324325','2001-09-19',0,'quận Bình Thạnh, thành phố Hồ Chí Minh','2022-05-05 13:19:02'),(128,41,NULL,'ungvien1','$2a$12$SnrpM8T0.RaIV26UtpVbdud2cnPPiFPxudUwSA6/ewaTTwrkKk4eW','https://res.cloudinary.com/nhn1909/image/upload/v1651837336/v0uuuznv4ncflu3i8itl.webp','CANDIDATE',1,'ungvien1','nguyenhoangn023@gmail.com',NULL,NULL,1,NULL,NULL),(129,42,NULL,'ungvien2','$2a$12$qI7rd2J91n4qTfdXq9iHzeTtszwP16Gv9PJDkh4l1HkSzqN2o/sVq','https://res.cloudinary.com/nhn1909/image/upload/v1651888409/ly3ymky09bwbqhxzuvso.jpg','CANDIDATE',0,'ungvien2','ungvien2@gmail.com',NULL,NULL,0,NULL,NULL),(134,NULL,21,'bmw','$2a$10$mVM7SYjwyP1.Ay/w11gOX.SzAQDsb3uwaAQVZ39kZMBSzqfAOq.nq','https://res.cloudinary.com/nhn1909/image/upload/v1651908635/jfvfqvmwniucfe28lcez.jpg','COMPANY',0,NULL,'company@gmail.com',NULL,NULL,0,NULL,NULL),(136,NULL,22,'audi','$2a$12$pilX/gx1.tPviGdIj1X4ouSo3.2nswjEX8lDwvHoMcVxMZVouFBjy','https://res.cloudinary.com/nhn1909/image/upload/v1651837322/tslkce327ci4n33bakug.jpg','COMPANY',0,'Audi Automotive manufacture','audi@gmail.com','01234567','2001-09-19',0,'Thành phố Hồ Chí Minh','2022-09-23 00:22:56'),(137,46,NULL,'ungvien3','$2a$10$ss0i7xM1Ux8Pfa6C2yEM3.E/dJJIbe95wK/TX0kxQ2GN0bpE3xXeW','https://res.cloudinary.com/nhn1909/image/upload/v1665305687/ervwexpzqr6gdoyxneeh.jpg','CANDIDATE',1,'update img1','update1 email','update1 phone','2000-05-11',1,'update img1','2022-10-07 11:09:02'),(146,47,NULL,'ungvien4','$2a$10$cpCdGksuwrWmK10b146Q/OGlQwXLpHRK9fskXr0hA/gOWdlTXvxCW','https://res.cloudinary.com/nhn1909/image/upload/v1665681659/wxrl9km2jzflyn2gtivl.jpg','CANDIDATE',1,'ungvien4 fullnameU','ungvien4 emailU','ungvien4 phoneU','2001-09-09',1,'ungvien4 img1U','2022-10-13 22:19:36'),(147,48,NULL,'ungvien5','$2a$10$O4zm4ilIF7fkbIOp50T7UOTu6YErOKrFRGEca3xhGdr14IAHDtVEG','https://res.cloudinary.com/nhn1909/image/upload/v1665764409/vx019dcu1vchcmlybz2p.jpg','CANDIDATE',1,'ungvien5 check fullnameU','ungvien5@gmail.com','01122667788','2001-09-01',1,'ungvien5qq img1U','2022-10-13 22:19:54'),(148,49,NULL,'ungvien6','$2a$10$aK5UQynWBys.AG94l2tP1.ZjiV2N8d6JVaU8.3GO57jmOJJaYp8Qy','https://res.cloudinary.com/nhn1909/image/upload/v1665763667/llvt6gz7iojeaovonrcb.jpg','CANDIDATE',1,'ungvien6 check fullnameU','ungvien6@gmail.com','01122334455','2001-09-09',1,'ungvien6qq img1U','2022-10-13 22:20:05');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_experience`
--

LOCK TABLES `work_experience` WRITE;
/*!40000 ALTER TABLE `work_experience` DISABLE KEYS */;
INSERT INTO `work_experience` VALUES (3,NULL,NULL,'TMA','Intern',1),(4,NULL,NULL,'Viettel','Fresher',1),(5,NULL,NULL,'TMA Solution','Product Manager',42),(6,NULL,NULL,'CityNow','Senior',42);
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

-- Dump completed on 2022-10-15  2:06:19
