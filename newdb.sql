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
-- Table structure for table `applying_job`
--

DROP TABLE IF EXISTS `applying_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applying_job` (
  `job_id` int NOT NULL,
  `candidate_user_id` int NOT NULL,
  `created_date` datetime NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`job_id`,`candidate_user_id`),
  KEY `fk_user_candidate_id_idx` (`candidate_user_id`),
  CONSTRAINT `fk_app_job_id` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `fk_user_candidate_id` FOREIGN KEY (`candidate_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applying_job`
--

LOCK TABLES `applying_job` WRITE;
/*!40000 ALTER TABLE `applying_job` DISABLE KEYS */;
INSERT INTO `applying_job` VALUES (1,128,'2022-09-18 16:14:35','PENDING'),(3,128,'2022-09-18 16:14:35','PENDING'),(4,128,'2022-09-18 16:14:35','PENDING'),(4,129,'2022-09-18 16:14:35','PENDING'),(5,128,'2022-09-18 16:14:35','PENDING'),(5,129,'2022-09-18 16:14:35','PENDING');
/*!40000 ALTER TABLE `applying_job` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (1,10,NULL,NULL),(39,3,'link','cv ne hehe'),(40,NULL,NULL,NULL),(41,2222222,'candidate ungvien1 update lan2',NULL),(42,190901,'test add lang skill2',NULL),(44,0,NULL,NULL),(45,0,NULL,NULL),(46,0,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,22,41,'comment ne` ma\' uiiii','2022-09-29 16:36:01',1),(2,21,41,'cmt comp_id = 21 uv1','2022-09-29 16:36:01',1),(3,21,42,'cmt comp_id = 21 uv2','2022-09-29 16:36:01',1),(4,22,42,'comment 22 42','2022-10-07 15:38:57',1);
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
INSERT INTO `company` VALUES (21,'BMW Group',10001,'Bayerische Motoren Werke AG','','','Petuelring 130Munich, DE','With its four brands BMW, MINI, Rolls-Royce and BMW Motorrad, the BMW Group is the world’s leading premium manufacturer of automobiles and motorcycles and also provides premium financial and mobility services. The BMW Group production network comprises 31 production and assembly facilities in 15 countries; the company has a global sales network in more than 140 countries.',0,'Munich','https://www.bmwgroup.com/en.html'),(22,'Audi AG Aktiengesellschaft - Motor Vehicle Manufacturing',10001,'AUDI AG',NULL,NULL,'NSU Straße 1, Neckarsulm, Baden-Wuerttemberg 74148, DE','#WeAreProgress ++ Progress is in our DNA. It’s not just in our cars, but also in us. The focus at Audi is on us – the people – and we are shaping the future of mobility together. With our inner drive. With the aim to continuously improve. With our mindset, courage and confidence. Because progress develops in the mind – and in the heart!',0,'Auto-Union-Straße 1, Ingolstadt, Bayern 85045, DE','http://www.audi.com');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_industry`
--

LOCK TABLES `company_industry` WRITE;
/*!40000 ALTER TABLE `company_industry` DISABLE KEYS */;
INSERT INTO `company_industry` VALUES (1,21,1),(2,21,2),(3,21,15),(4,21,3);
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
  `title` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
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
  `user_company_id` int NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'Java Developer (All Levels) ','We\'re looking for 10 Java Developers (all levels: Senior, Junior, Fresher).',NULL,NULL,NULL,10,'11000000','address',1,8,2,1,134),(3,'job update','loi 500',NULL,NULL,NULL,0,'40000000','address',2,1,12,1,134),(4,'test tiep ne',NULL,'2022-09-22 16:54:44',NULL,'2022-09-22 16:54:44',0,'50000000','address',15,3,2,1,134),(5,'test tiep ne2',NULL,'2022-09-22 21:37:26',NULL,'2022-09-22 21:37:26',0,'50000000','address',44,2,3,2,134),(6,'test tiep ne33',NULL,'2022-09-22 21:56:25',NULL,'2022-09-22 21:56:25',0,'40000000','address',75,1,3,1,136),(7,'job update done','loi 500 done','2022-09-22 21:58:13',NULL,'2022-09-22 21:58:13',1909,'25000000','address',36,3,10,3,136),(8,'loi 500','loi 500','2022-09-22 22:01:56',NULL,'2022-09-22 22:01:56',0,'40000000','address',1,3,5,3,136),(9,'loi 500 lan nua xem','loi 500','2022-09-22 22:10:35',NULL,'2022-09-22 22:10:35',0,'40000000','address',79,1,2,1,136),(10,'loi 415 lan nua xem','loi 415','2022-10-04 00:19:07',NULL,'2022-10-04 00:19:07',0,'25000000','address',56,1,12,1,134),(11,'loi 415 lan nua xem 1','loi 415','2022-10-04 08:26:36',NULL,'2022-10-04 08:26:36',0,'25000000','address',79,1,12,1,134),(12,'loi 500 lan nua xem','loi 500','2022-10-04 13:57:16',NULL,'2022-10-04 13:57:16',200,'25000000','address',79,1,12,1,136),(13,'test requirements','requirements','2022-10-04 14:07:00',NULL,'2022-10-04 14:07:00',230,'25000000','address',14,1,12,1,136);
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
  CONSTRAINT `fk_jobtag_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `fk_jobtag_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_tag`
--

LOCK TABLES `job_tag` WRITE;
/*!40000 ALTER TABLE `job_tag` DISABLE KEYS */;
INSERT INTO `job_tag` VALUES (1,1,1),(2,1,3),(3,1,5),(4,1,7),(5,1,12),(54,7,4),(55,7,5),(56,7,6);
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
-- Table structure for table `requirement`
--

DROP TABLE IF EXISTS `requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requirement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `job_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_req_job_idx` (`job_id`),
  CONSTRAINT `fk_req_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirement`
--

LOCK TABLES `requirement` WRITE;
/*!40000 ALTER TABLE `requirement` DISABLE KEYS */;
INSERT INTO `requirement` VALUES (35,'req 1 nene',7),(36,'req 2 nene',7),(37,'req 3 nene',7);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talent`
--

LOCK TABLES `talent` WRITE;
/*!40000 ALTER TABLE `talent` DISABLE KEYS */;
INSERT INTO `talent` VALUES (1,'Đàn',42),(2,'Đấm nhau',42),(3,'Võ mồm',42);
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
  `company_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oq7f070s2fgswrf8v0kwou0qd` (`candidate_id`),
  UNIQUE KEY `UK_ggbcixcrmm7q2anlgt4jm9htm` (`company_id`),
  KEY `fk_user_candidate_idx` (`candidate_id`),
  KEY `fk_user_employer_idx` (`company_id`),
  CONSTRAINT `fk_user_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'admin','$2a$10$hZnZYzt2JVptfVGAOK59POfuGAT/Ba1f5YI8dDY/qQGfuUDFB8CKu','https://res.cloudinary.com/dxorabap0/image/upload/v1659263863/nelb30hnkj3iljs45vqb.jpg','ADMIN',1,'Nguyễn Hoàng Nam','admin@gmail.com','014324325','2001-09-19',0,'quận Bình Thạnh, thành phố Hồ Chí Minh','2022-05-05 13:19:02',NULL,NULL),(128,'ungvien1','$2a$12$SnrpM8T0.RaIV26UtpVbdud2cnPPiFPxudUwSA6/ewaTTwrkKk4eW','avatar uv1','CANDIDATE',1,'ungvien1','nguyenhoangn023@gmail.com',NULL,NULL,1,NULL,NULL,41,NULL),(129,'ungvien2','$2a$12$qI7rd2J91n4qTfdXq9iHzeTtszwP16Gv9PJDkh4l1HkSzqN2o/sVq','avatar uv2','CANDIDATE',0,'ungvien2','ungvien2@gmail.com',NULL,NULL,0,NULL,NULL,42,NULL),(134,'company1','$2a$12$yRMyqmkqVqM6w.y.q3Cjfur04XTX.mbfBFc44jYu1.LiC0WcFTfAe','avatar comp1','COMPANY',0,NULL,'company@gmail.com',NULL,NULL,0,NULL,NULL,NULL,21),(136,'audi','$2a$12$pilX/gx1.tPviGdIj1X4ouSo3.2nswjEX8lDwvHoMcVxMZVouFBjy','deo co','COMPANY',0,'Audi Automotive manufacture','audi@gmail.com','01234567','2001-09-19',0,'Thành phố Hồ Chí Minh','2022-09-23 00:22:56',NULL,22),(137,'ungvien3','$2a$10$ss0i7xM1Ux8Pfa6C2yEM3.E/dJJIbe95wK/TX0kxQ2GN0bpE3xXeW',NULL,'CANDIDATE',1,NULL,'ungvien2@ou.edu.vn',NULL,NULL,0,NULL,'2022-10-07 11:09:02',46,NULL),(138,'ungvien4','ungvien4','https://res.cloudinary.com/nhn1909/image/upload/v1665162109/pllbrvwa6kddf8tugiwe.jpg','CANDIDATE',1,NULL,'ungvien4@gmail.com',NULL,NULL,0,NULL,'2022-10-08 00:01:49',NULL,NULL),(139,'ungvien5','ungvien5','https://res.cloudinary.com/nhn1909/image/upload/v1665162306/ma90kivoaapn9aj331h8.jpg','CANDIDATE',1,NULL,'ungvien5@gmail.com',NULL,NULL,0,NULL,'2022-10-08 00:05:06',NULL,NULL),(140,'ungvien5','ungvien5',NULL,'CANDIDATE',1,NULL,'ungvien5@gmail.com',NULL,NULL,0,NULL,'2022-10-08 00:05:25',NULL,NULL),(141,'ungvien6','ungvien6','https://res.cloudinary.com/nhn1909/image/upload/v1665162377/mjluilfr6eyf2m1f1ohz.jpg','CANDIDATE',1,NULL,'ungvien6@gmail.com',NULL,NULL,0,NULL,'2022-10-08 00:06:17',NULL,NULL),(142,'ungvien7','ungvien7','https://res.cloudinary.com/nhn1909/image/upload/v1642074622/tb-avatar-none_r1c2ye.jpg','CANDIDATE',1,NULL,'ungvien7@gmail.com',NULL,NULL,0,NULL,'2022-10-08 00:06:41',NULL,NULL),(143,'ungvien8','ungvien8','https://res.cloudinary.com/nhn1909/image/upload/v1665162491/ehvuymd8txxwtpudqcae.jpg','CANDIDATE',1,NULL,'ungvien8@gmail.com',NULL,NULL,0,NULL,'2022-10-08 00:08:11',NULL,NULL),(144,'ungvien8','ungvien8','https://res.cloudinary.com/nhn1909/image/upload/v1642074622/tb-avatar-none_r1c2ye.jpg','CANDIDATE',1,NULL,'ungvien8@gmail.com',NULL,NULL,0,NULL,'2022-10-08 00:08:23',NULL,NULL),(145,'ungvien8','ungvien8','https://res.cloudinary.com/nhn1909/image/upload/v1642074622/tb-avatar-none_r1c2ye.jpg','CANDIDATE',1,NULL,'ungvien8@gmail.com',NULL,NULL,0,NULL,'2022-10-08 00:09:12',NULL,NULL);
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

-- Dump completed on 2022-10-08 16:44:20
