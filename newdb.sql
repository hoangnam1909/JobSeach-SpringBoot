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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply_job`
--

LOCK TABLES `apply_job` WRITE;
/*!40000 ALTER TABLE `apply_job` DISABLE KEYS */;
INSERT INTO `apply_job` VALUES (7,5,129,'2022-05-05 13:19:02','APPROVED'),(9,12,129,'2022-05-02 13:19:02','PENDING'),(11,6,137,'2022-05-05 13:19:02','APPROVED'),(12,12,128,'2022-05-05 13:19:02','CANCELLED'),(14,11,129,'2022-09-19 01:01:35','PENDING'),(15,6,128,'2022-10-12 10:45:18','BLOCKED'),(17,11,137,'2022-10-12 11:30:22','PENDING'),(18,5,128,'2022-05-15 13:19:02','APPROVED'),(19,5,137,'2022-09-19 19:29:12','PENDING'),(20,5,146,'2022-05-05 03:09:43','PENDING'),(21,5,147,'2022-09-19 12:09:02','CANCELLED'),(22,5,148,'2022-09-19 13:19:02','PENDING'),(24,3,147,'2022-10-16 02:19:45','PENDING');
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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (41,10,'all properties',NULL),(42,190901,'test add lang skill2',NULL),(46,5,'ungvien3 LinkedIn updatebody','https://res.cloudinary.com/nhn1909/image/upload/v1666768512/t91jwobrjrgzfcis9vgn.jpg'),(47,10,'ungvien4 link','https://res.cloudinary.com/nhn1909/image/upload/v1665760711/jcbieum3pg4dykcmeuvh.jpg'),(48,7,'ungvien5 LinkedIn updatebody','https://res.cloudinary.com/nhn1909/image/upload/v1666621276/d3fsyv7zn1c3bokirfqu.png'),(49,0,NULL,NULL),(52,0,NULL,NULL),(54,0,NULL,NULL),(55,0,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,22,41,'comment ne` ma\' uiiii','2022-09-29 16:36:01',1),(2,21,41,'cmt comp_id = 21 uv1','2022-09-29 16:36:01',1),(3,21,42,'cmt comp_id = 21 uv2','2022-10-11 16:36:01',1),(4,22,42,'comment 22 42','2022-10-07 15:38:57',1),(5,22,41,'128cand comment 136comp','2022-10-10 23:44:18',0),(6,21,48,'ungvien5 comment bmw','2022-10-16 01:35:49',1);
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
  `contact_tel` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `contact_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `contact_address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `introduction` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `headquarters` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `founded_year` date DEFAULT NULL,
  `link` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (21,'BMW Group Update',10000,'company updt1','company updt1','company updt1','company updt1','company updt1','1984-01-01','company updt1'),(22,'Audi AG Aktiengesellschaft - Motor Vehicle Manufacturing',10001,NULL,NULL,'NSU Straße 1, Neckarsulm, Baden-Wuerttemberg 74148, DE','#WeAreProgress ++ Progress is in our DNA. It’s not just in our cars, but also in us. The focus at Audi is on us – the people – and we are shaping the future of mobility together. With our inner drive. With the aim to continuously improve. With our mindset, courage and confidence. Because progress develops in the mind – and in the heart!','Auto-Union-Straße 1, Ingolstadt, Bayern 85045, DE','1958-01-01','http://www.audi.com'),(23,'M-Service (MOMO)',499,'02854147667','hotro@momo.vn','Lầu 6, Toà nhà Phú Mỹ Hưng, số 8 Hoàng Văn Thái, khu phố 1, Phường Tân Phú, Quận 7, Thành phố Hồ Chí Minh','Công ty Cổ phần Dịch vụ Di Động Trực tuyến (viết tắt M_Service) là công FinTech được thành lập từ 2007. M_Service hoạt động chính trong lĩnh vực thanh toán trên di động (mobile payment) dưới thương hiệu MoMo. Công ty đã được Ngân hàng Nhà nước Việt Nam cấp phép cung cấp dịch vụ Ví điện tử và Dịch vụ chuyển tiền, thu hộ/chi hộ…','Tầng 6, tòa nhà Mercury, số 444 Hoàng Hoa Thám, Phường Thụy Khuê, Quận Tây Hồ, Thành phố Hà Nội','1865-01-01','https://momo.vn'),(24,'Intel Corporation',10000,'02854147667','ApplyAssistance@intel.com','Lot I2, D1 Road, Saigon Hi-Tech Park District 9','Tập đoàn Intel thành lập năm 1968 tại Santa Clara, California, Hoa Kỳ, là nhà sản xuất các sản phẩm như chip vi xử lý cho máy tính, bo mạch chủ, ổ nhớ flash, card mạng và các thiết bị máy tính khác.','Santa Clara, California, Hoa Kỳ','1968-01-01','https://www.intel.vn'),(25,'FPT Software',10000,'02437689048','recruitment@fsoft.com.vn','FPT Cau Giay Building, Duy Tan Street, Dich Vong Hau Ward, Cau Giay District, Hanoi City, Vietnam','FPT Software là công ty thành viên thuộc Tập đoàn FPT. Được thành lập từ năm 1999, FPT Software hiện là công ty chuyên cung cấp các dịch vụ và giải pháp phần mềm cho các khách hàng quốc tế, với hơn 20.000 nhân viên, hiện diện tại 27 quốc gia trên toàn cầu. Nhiều năm liền, FPT Software được bình chọn là Nhà Tuyển dụng được yêu thích nhất và nằm trong TOP các công ty có môi trường làm việc tốt nhất châu Á.','FPT Tower, No.10 Pham Van Bach Street, Dich Vong Ward, Cau Giay District, Hanoi City, Vietnam','2001-01-01','https://www.fpt-software.com/'),(26,'TikTok',5000,'','business-servicesupport@tiktok.com','Tầng 6, 29A Nguyễn Đình Chiểu, Phường Đa Kao, Quận 1, TP Hồ Chí Minh. Danh sách công ty tại Quận 1, TP Hồ Chí Minh, Việt Nam','TikTok is the leading destination for short-form mobile video. Our mission is to inspire creativity and bring joy. TikTok has global offices including Los Angeles, New York, London, Paris, Berlin, Dubai, Mumbai, Singapore, Jakarta, Seoul, and Tokyo.','Santa Monica Blvd, Los Angeles, California US','2014-01-01','https://www.tiktok.com/');
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_industry`
--

LOCK TABLES `company_industry` WRITE;
/*!40000 ALTER TABLE `company_industry` DISABLE KEYS */;
INSERT INTO `company_industry` VALUES (7,22,15),(12,21,18),(13,21,15),(14,23,4),(15,23,24),(16,23,25),(17,24,18),(18,24,4),(19,24,23),(20,24,25),(21,24,11),(22,24,12),(23,25,23),(24,25,25),(25,25,12),(26,26,26),(27,26,12);
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
  `name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `industry`
--

LOCK TABLES `industry` WRITE;
/*!40000 ALTER TABLE `industry` DISABLE KEYS */;
INSERT INTO `industry` VALUES (1,'Utilities'),(2,'Arts, Entertainment and Recreation'),(3,'Finance and Insurance'),(4,'Professional, Scientific and Technical Services'),(5,'Accommodation and Food Services'),(6,'Construction'),(7,'Agriculture, Forestry, Fishing and Hunting'),(8,'Transportation and Warehousing'),(9,'Other Services (except Public Administration)'),(10,'Mining'),(11,'Administration, Business Support and Waste Management Services'),(12,'Information'),(13,'Educational Services'),(14,'Retail Trade'),(15,'Healthcare and Social Assistance'),(16,'Real Estate and Rental and Leasing'),(17,'Wholesale Trade'),(18,'Manufacturing'),(19,'Retail Market Reports'),(20,'Business Franchises'),(21,'Industrial Machinery, Gas and Chemicals'),(22,'Life Sciences'),(23,'Specialist Engineering, Infrastructure and Contractors'),(24,'Advisory and Financial Services'),(25,'Technology'),(26,'Online Retail'),(27,'Consumer Goods and Services');
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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,134,'Java Developer (All Levels) ',1,'We\'re looking for 10 Java Developers (all levels: Senior, Junior, Fresher).',NULL,NULL,NULL,10,'11000000','address',1,8,2,1,0),(3,134,'Telesales Mảng Tài Chính (Nhận Sinh Viên Mới Ra Trường - Không Yêu Cầu Kinh Nghiệm)',1,'Cơ hội cho các bạn Sinh viên mới ra trường muốn thử sức lĩnh vực tài chính, gia tăng thu nhập và học hỏi kinh nghiệm ở môi trường chuyên nghiệp:',NULL,NULL,'2022-10-10 23:26:04',10,'10000000','14F tòa nhà Pico Plaza, số 20 Cộng Hoà, Phường 12, Quận Tân Bình',79,8,1,1,0),(4,134,'test tiep ne',0,NULL,'2022-09-22 16:54:44',NULL,'2022-09-22 16:54:44',0,'50000000','address',15,3,2,1,0),(5,134,'Nhân Viên Digital Marketing Thu Nhập Upto 20 Triệu',1,'Cơ hội cho các bạn Sinh viên mới ra trường muốn thử sức lĩnh vực tài chính, gia tăng thu nhập và học hỏi kinh nghiệm ở môi trường chuyên nghiệp:','2022-09-22 21:37:26',NULL,'2022-10-15 00:42:14',40,'20000000','',79,1,27,1,6),(6,136,'test tiep ne33',1,NULL,'2022-09-22 21:56:25',NULL,'2022-09-22 21:56:25',0,'40000000','address',75,1,3,1,2),(7,136,'company job updt',0,'company job updt','2022-09-22 21:58:13',NULL,'2022-09-22 21:58:13',1021,'999999','thị trấn Ea Súp, huyện Ea Súp',66,4,4,4,2),(8,136,'loi 500',1,'loi 500','2022-09-22 22:01:56',NULL,'2022-09-22 22:01:56',0,'40000000','address',1,3,5,3,0),(9,136,'loi 500 lan nua xem',0,'loi 500','2022-09-22 22:10:35',NULL,'2022-09-22 22:10:35',0,'40000000','address',79,1,2,1,0),(10,134,'loi 415 lan nua xem',1,'loi 415','2022-10-04 00:19:07',NULL,'2022-10-04 00:19:07',0,'25000000','address',56,1,12,1,0),(11,134,'loi 415 lan nua xem 1',0,'loi 415','2022-10-04 08:26:36',NULL,'2022-10-04 08:26:36',0,'25000000','address',79,1,12,1,1),(12,136,'Sales Administrator - Audi',0,'Do you want to develop your career working for one of the largest, most progressive motor retailers in the UK? If you are a meticulous professional with experience of sales administration, preferably within the automotive industry, then Milton Keynes Audi are looking for you!','2022-10-04 13:57:16',NULL,'2022-10-26 13:29:04',1,'200000000','',1,1,27,1,2),(41,134,'Sale BMW',0,'Bán xe BMW i8','2022-10-15 00:17:38',NULL,NULL,100,'42000000','Quận 7',79,3,7,3,0),(43,134,'Sale BMW',1,'Bán xe BMW Q7','2022-10-15 00:25:23',NULL,NULL,100,'42000000','Quận 7',79,3,7,3,0),(44,134,'Chuyên Viên Marketing - Xe Ô Tô Cao Cấp Bmw (Quận 7)',1,'Tham mưu về các hoạt động marketing dòng xe BMW nhằm gia tăng nhận biết thương hiệu, sản phẩm, chương trình bán hàng tại thị trường được giao quản lý. Phối hợp tổ chức nghiên cứu thị trường, thương hiệu, phân khúc sản phẩm cạnh tranh theo từng dòng xe. Tổ chức lập kế hoạch, kiểm soát triển khai và đánh giá hiệu quả hoạt động marketing bán hàng các thương hiệu BMW. Quản trị ngân sách marketing các thương hiệu xe BMW tại Công ty. Kiểm soát tuân thủ tiêu chuẩn nhận diện theo tiêu chuẩn thương hiệu BMW. Kiểm soát tuân thủ các quy định, định hướng nội dung truyền thông, marketing từ Công ty Phân phối.','2022-10-20 21:48:45',NULL,NULL,1,'14000000','314 Nguyễn Văn Linh, Phường Bình Thuận, Quận 7',79,1,1,1,0),(45,134,'Nhân Viên Kinh Doanh - Tư Vấn Bán Hàng Xe Ô Tô BMW - Làm Việc Tại Long Biên, Hà Nội',1,'Thực hiện chỉ tiêu bán hàng được giao; khai thác, tìm kiếm, chăm sóc và phát triển mối quan hệ khách hàng. Thực hiện các hoạt động marketing bán hàng tại thị trường phụ trách. Tuân thủ các chính sách, quy định về bán hàng và chăm sóc khách hàng. Thường xuyên cập nhật, nâng cao kiến thức sản phẩm, kỹ năng bán hàng, marketing và chăm sóc khách hàng.','2022-10-20 22:00:20',NULL,NULL,10,'25000000','105A Lý Sơn, Ngọc Thụy, Long Biên',1,1,1,1,0),(46,134,'Trợ Lý Marketing Xe Ô Tô BMW - Làm Việc Tại Long Biên',1,'Tham mưu cho BLĐ các chương trình marketing, CSKH và các chương trình quan hệ công chúng, kiểm soát thông tin, phối hợp xử lý thông tin trên báo chí, mạng xã hội có liên quan. Tổ chức triển khai các hoạt động marketing, CSKH chung của THACO AUTO tại Chi nhánh. Tổ chức triển khai các hoạt động truyền thông nội bộ, hoạt động đoàn thể (Đảng, Công đoàn, Đoàn Thanh niên) tại Chi nhánh. Quản lý kế hoạch và đánh giá hiệu quả marketing, CSKH tại Chi nhánh. Biên soạn các nội dung truyền thông Chi nhánh, kiểm soát các nội dung marketing truyền thông (bán hàng, dịch vụ) trước khi phát hành trên các kênh truyền thông thuộc Chi nhánh quản lý. Kiểm soát tính tuân thủ các quy định về truyền thông, quản lý nhận diện, AVP và quà tặng của Chi nhánh. Tổ chức đào tạo nội bộ về nghiệp vụ marketing, CSKH, truyền thông tại Chi nhánh.','2022-10-20 22:06:26',NULL,NULL,2,'14000000','1A Ngô Gia Tự, Đức Giang, Long Biên hoặc 541 Nguyễn Văn Cừ, Gia Thụy, Long Biên',1,2,27,1,0),(47,134,'Nhân Viên Kinh Doanh - Tư Vấn Ô Tô BMW - Làm Việc Tại Long Biên',1,'Thực hiện chỉ tiêu bán xe được giao; khai thác, tìm kiếm, chăm sóc và phát triển mối quan hệ khách hàng. Thực hiện các hoạt động Marketing bán hàng tại thị trường phụ trách. Thường xuyên cập nhật, nâng cao kiến thức sản phẩm, kỹ năng bán hàng, Marketing và chăm sóc khách hàng.','2022-10-20 22:10:06',NULL,NULL,2,'30000000','105A Lý Sơn, Phường Ngọc Thụy, Long Biên',1,1,1,1,0),(48,134,'Nhân Viên Bán Hàng Xe BMW (Quận 7)',1,'Thực hiện chỉ tiêu bán hàng của cá nhân theo thương hiệu xe BMW. Tìm kiếm, chăm sóc, phát triển mối quan hệ với khách hàng; Tham gia thực hiện các hoạt động marketing tại thị trường được giao. Tuân thủ các chính sách, quy định về bán hàng, theo dõi thực hiện Hợp đồng Bán hàng. Trao dồi kiến thức, rèn luyện nâng cao khả năng tư vấn sản phẩm, kỹ năng bán hàng, trình độ Anh ngữ và ứng dụng công nghệ thông tin.','2022-10-20 22:12:12',NULL,NULL,2,'20000000','314 Nguyễn Văn Linh, Phường Bình Thuận, Quận 7',79,1,1,1,0),(49,149,'Nhân Viên Tiếp Nhận Kênh Chat',1,'Tiếp nhận phản ánh của Khách hàng từ kênh Social; Tư vấn, cung cấp và giới thiệu cho Khách hàng các thông tin về sản phẩm, dịch vụ tiện ích của Công ty trong phạm vi được yêu cầu; Giải đáp những thắc mắc, khiếu nại của khách hàng khi sử dụng dịch vụ; Theo dõi lỗi phát sinh để kịp thời báo ngay cho Trưởng nhóm; Những công việc phát sinh theo yêu cầu của Trưởng nhóm.','2022-10-20 22:22:45',NULL,NULL,3,'','',79,1,12,1,0),(50,149,'Nhân Viên Chăm Sóc Khách Hàng',1,'Xử lý tất cả phản ánh của Khách hàng khi trải nghiệm dịch vụ của Ví điện tử MoMo. Mang đến sự hài lòng, cải thiện trải nghiệm khách hàng để khách hàng có thể quay lại tiếp tục sử dụng dịch vụ. Đồng thời nhận thêm nhiều nhiệm vụ khác hàng ngày để giải quyết lượng case tồn nhằm đảm bảo chất lượng chăm sóc khách hàng và trải nghiệm khách hàng tốt nhất. Tham gia xử lý các loại ticket khác từ các kênh tiếp nhận khác nhau của CSKH MoMo (app,call, email, chat,…) trong thời gian cam kết đảm bảo hoàn thành 100% công việc. Nỗ lực giải quyết phản ánh trong thời gian cam kết nhằm mang đến trải nghiệm tốt cho Khách hàng qua chất lượng xử lý trên từng phản ticket mà nhân viên tiếp nhận giải quyết. Nỗ lực mang đến cho khách hàng chất lượng dịch vụ chính xác, mang lại sự hài lòng cho khách hàng khi gặp sự cố khi sử dụng dịch vụ.','2022-10-20 22:24:55',NULL,NULL,1,'','',79,1,12,1,0),(51,149,'Nhân Viên Đi Thị Trường/Nhân Viên Vận Hành Thị Trường',1,'Đi thị trường (chiếm 95% thời gian làm việc), gặp gỡ và làm việc trực tiếp với đối tác; Lập kế hoạch công việc ngày/ tuần/tháng nhằm hoàn thành KPI đề ra của công ty; Kiểm tra quy trình thanh toán tại cửa hàng; Kiểm tra hiện trạng POSM và thay thế nếu hư hỏng; Nghiệm thu các chương trình khuyến mãi đang diễn ra; Chụp hình POSM và lưu trữ; Tiếp nhận những phản hồi và chuyển thông tin cho các bên liên quan để hỗ trợ cho đối tác; Cung cấp và thu hồi thiết bị thanh toán cho cửa hàng; Báo cáo kết quả công việc cuối ngày; Các việc phát sinh khác được quản lý trực tiếp yêu cầu.','2022-10-20 22:28:03',NULL,NULL,10,'13000000','',79,1,17,1,0),(52,149,'Java Developer',1,'Developing and enhancement large scale systems using Java technologies: Collection/Insurance feature, Loan/Saving/Investing feature, Mobile App Platform, Delivery Services. Contribute in all phases of the development lifecycle. Write well designed, testable, efficient code. Ensure designs are in compliance with specifications. Identify bottlenecks and bugs, and devise solutions to these problems. Researching and developing new technologies, focus on mobile payments. Communicate with other Divisions in support production issue and bug fixing. Identify major areas of systems improvement and drive results, by being able to quickly translate new ideas into solid implementations. Lead in discussions with business teams regarding implementation and design of business processes. Provide leadership to the business in data quality analysis. Provide ad hoc support and advocacy to business subject specialists. Application support with a heavy emphasis on problem identification, diagnosis and resolution. Supporting line manager in the executive management. Providing technical guide, proper instruction to low level members. Other assignments from line manager.','2022-10-20 22:31:27',NULL,NULL,10,'','Lầu 6, Toà nhà Phú Mỹ Hưng, 08 Hoàng Văn Thái, P. Tân Phú, Q.7, Tp. Hồ Chí Minh, Quận 7',79,1,24,1,0),(53,149,'Customer Experience Executive',1,'Monitor of transaction channels and quality for each specific project; Participate in solving problems encountered during the project implementation process of Department; Statistics encountered problems of each project (processes, regulations, systems...); Report problems encountered during the monitoring period affecting the project’s OKR or seek advice from the Customer Experience (CX) department; Update new information from the CX department according to each project.','2022-10-20 22:34:57',NULL,NULL,1,'','',79,1,24,1,0),(54,149,'Field Operations Associate Manager',1,'Xây dựng kế hoạch vận hành thị trường cho nhân viên theo chu kỳ tuần/tháng/quý và năm; Làm việc với Trưởng nhóm vận hành thị trường để phân bổ KPI theo chu kỳ ngày/tháng/quý/năm cho nhân viên; Xử lý kết quả báo cáo của nhân viên vận hành thị trường gửi về theo tiến độ hàng ngày và báo cáo cho cấp trên hoặc các bộ phận liên quan khi được yêu cầu; Làm việc với Trưởng nhóm vận hành thị trường để lập kế hoạch đánh giá định kỳ hàng tháng/quý/năm cho đội ngũ vận hành thị trường; Nghiệm thu, lưu trữ hình ảnh POSM các đối tác, hình ảnh của những chương trình khuyến mãi; Theo dõi kiểm soát các cửa hàng/đơn vị kinh doanh buôn bán chưa đảm bảo chất lượng và tiến độ trang bị POSM, từ đó đưaa ra hướng xử lý kịp thời; Phối hợp với các bộ phận liên quan để hoàn tất việc hỗ trợ/xử lý sự cố tại các cửa hàng/đơn vị kinh doanh buôn bán; Kiểm tra ngẫu nhiên và tiến hành kiểm tra thị trường thực tế để đảm bảo chất lượng công việc của nhân viên; Đề xuất các giải pháp cải thiện tiến độ công việc và hỗ trợ đội ngũ trong việc xử lý các báo cáo định kỳ; Thực hiện các công việc khác theo sự chỉ đạo của quản lý.','2022-10-20 22:43:33',NULL,NULL,1,'','',79,3,38,1,0),(55,151,'Fresher Automation Test Engineer',1,'Trong những năm gần đây, Automation Testing (kiểm thử tự động) đang là xu hướng đang phát triển mạnh mẽ của ngành phần mềm. Với rất nhiều ưu điểm như: độ chính xác cao, tốc độ và hiệu suất lớn, đồng thời khả năng lặp lại và tái sử dụng tốt, Automation Test đang được sử dụng ở hầu hết các dự án có quy mô lớn giúp tiết kiệm chi phí kiểm thử và thời gian. Automation Test Engineer sẽ viết code, tạo các test script và tool để kiểm thử tự động các phần mềm. Được đánh giá là một ngành “hot nhưng hiếm”, thậm chí, còn có nhận định rằng tìm Automation Engineer còn khó hơn tìm lập trình viên. Do vậy khi tự động hóa đang trở thành cuộc đua toàn cầu khốc liệt, Automation Tester sẽ  có nhiều cơ hội công việc với mức lương vô cùng cạnh tranh, trung bình cao hơn từ 20 - 30% so với Manual Test hoặc Developer có cùng số năm kinh nghiệm. Nhiều Developer và Manual Tester đã nâng cấp sự nghiệp của mình để trở thành Automation Engineer.','2022-10-20 22:47:31',NULL,NULL,30,'15000000','FPT Building, 17 Duy Tân, Cầu Giấy',1,1,24,1,0),(56,151,'Senior Data Engineer - Offer Up To $3000',1,'Work in a big project with many senior/expert people. Join training on job if you need more training in project. Can be promoted to Team leader/Project Manager with excellent performance. Have chance to onsite short or long time for training and working. Work directly with customer in Singapore/US','2022-10-20 22:49:51',NULL,NULL,2,'','FPT Building, 17 Duy Tân, Cầu Giấy',1,1,24,1,0),(57,151,'Senior Tester',1,'Tham gia dự án có nền tảng là một hệ thống bảo trì và nâng cấp các tính năng mới cho quản lý tài sản cũng như hỗ trợ quá trình kiểm kê định kì. Nền tảng này cung cấp giải pháp quản lý tài sản khác nhau như PC, Mobile, USIM, ESIM, Máy in, thiết bị IoT,… Cho phép họ thực hiện các yêu cầu của người quản lý, trình quản lý thiết bị, v.v','2022-10-20 22:51:51',NULL,NULL,5,'','Làng Công nghệ số 3 và 4, Khu Phần mềm, Khu Công nghệ Cao Hòa Lạc, Km 29, Đại Lộ Thăng Long, Xã Tân Xã, huyện Thạch Thất',1,1,24,1,0),(58,151,'Middle .Net Developer',1,'Daily tasks you will take care: Conception and implementation of IT solutions in the Waterfall team. Developing backend services to retrieve information from customer’s resources. Developing web-based application to call backend services, get and display data. Work directly with customers to collect requirement, spec,…','2022-10-20 22:55:12',NULL,NULL,5,'','FPT Building, 17 Duy Tan, Cau Giay',1,1,24,1,0),(59,151,'Admin Intern',1,'Hỗ trợ team EX Design trong các task admin, bao gồm: Book phòng họp, request logistics (nếu cần). Sắp xếp lịch họp và gửi meeting request. Xử lý hồ sơ thanh toán. Một số công việc khác theo yêu cầu của mentor.','2022-10-20 22:57:05',NULL,NULL,1,'','',1,1,24,1,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_category`
--

LOCK TABLES `job_category` WRITE;
/*!40000 ALTER TABLE `job_category` DISABLE KEYS */;
INSERT INTO `job_category` VALUES (1,'Kinh doanh / Bán hàng',NULL),(2,'Biên / Phiên dịch',NULL),(3,'Bưu chính - Viễn thông',NULL),(4,'Bảo hiểm',NULL),(5,'Bất động sản',NULL),(6,'Chứng khoán / Vàng / Ngoại tệ',NULL),(7,'Công nghệ cao',NULL),(8,'Cơ khí / Chế tạo / Tự động hoá',NULL),(9,'Du lịch',NULL),(10,'Dầu khí / Hoá chất',NULL),(11,'Dệt may / Da giày',NULL),(12,'Dịch vụ khách hàng',NULL),(13,'Điện tử viễn thông',NULL),(14,'Điện / Điện tử / Điện lạnh',NULL),(15,'Giáo dục / Đào tạo',NULL),(16,'Hoá học / Sinh học',NULL),(17,'Hoạch định / Dự án',NULL),(18,'Hàng gia dụng',NULL),(19,'Hàng hải',NULL),(20,'Hàng không',NULL),(21,'Hành chính / Văn phòng',NULL),(22,'In ấn / Xuất bản',NULL),(23,'IT Phần cứng / Mạng',NULL),(24,'IT Phần mềm',NULL),(25,'Nhà hàng / Khách sạn',NULL),(26,'Kế toán / Kiểm toán',NULL),(27,'Marketing / Truyền thông / Quảng cáo',NULL),(28,'Môi trường / Xử lý chất thải',NULL),(29,'Mỹ phẩm / Trang sức',NULL),(30,'Mỹ thuật / Nghệ thuật / Điện ảnh',NULL),(31,'Ngân hàng / Tài chính',NULL),(32,'Ngành nghề khác',NULL),(33,'NGO / Phi chính phủ / Phi lợi nhuận',NULL),(34,'Nhân sự',NULL),(35,'Nông / Lâm / Ngư nghiệp',NULL),(36,'Phi chính phủ / Phi lợi nhuận',NULL),(37,'Quản lý chất lượng (QA/QC)',NULL),(38,'Quản lý điều hành',NULL),(39,'Sản phẩm công nghiệp',NULL),(40,'Sản xuất',NULL),(41,'Spa / Làm đẹp',NULL),(42,'Tài chính / Đầu tư',NULL),(43,'Thiết kế đồ họa',NULL),(44,'Thiết kế nội thất',NULL),(45,'Thời trang',NULL),(46,'Thư ký / Trợ lý',NULL),(47,'Thực phẩm / Đồ uống',NULL),(48,'Tổ chức sự kiện / Quà tặng',NULL),(49,'Tư vấn',NULL),(50,'Vận tải / Kho vận',NULL),(51,'Xây dựng',NULL),(52,'Xuất nhập khẩu',NULL),(53,'Y tế / Dược',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=285 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_tag`
--

LOCK TABLES `job_tag` WRITE;
/*!40000 ALTER TABLE `job_tag` DISABLE KEYS */;
INSERT INTO `job_tag` VALUES (1,1,1),(2,1,3),(3,1,5),(4,1,7),(5,1,12),(103,7,1),(104,7,6),(105,7,7),(200,3,1),(201,3,4),(202,3,6),(203,3,12),(204,3,13),(205,41,5),(206,41,6),(207,41,7),(211,43,5),(212,43,6),(213,43,7),(222,5,1),(223,5,2),(224,5,4),(225,5,6),(226,44,1),(227,44,5),(228,44,6),(229,45,1),(230,45,4),(231,45,12),(232,45,15),(233,46,1),(234,46,4),(235,46,11),(236,46,12),(237,47,1),(238,47,2),(239,47,8),(240,48,1),(241,48,2),(242,48,8),(243,49,1),(244,49,2),(245,49,8),(246,50,1),(247,50,2),(248,50,8),(249,51,1),(250,51,2),(251,51,8),(252,52,1),(253,52,2),(254,52,8),(255,53,1),(256,53,2),(257,53,8),(258,54,1),(259,54,2),(260,54,8),(261,55,1),(262,55,2),(263,55,8),(282,12,2),(283,12,4),(284,12,6);
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (24,'Tiếng Nga','',42),(25,'Tiếng Pháp','',42),(26,'Tiếng Hàn','',42),(46,'Tieng Viet','tieng me de',41),(47,'Tieng Anh','hoc ngu lam',41),(50,'Tieng Viet','tieng me de',47),(51,'Tieng Anh','hoc ngu lam',47),(58,'Tiếng Việt','Tiếng mẹ đẻ',48),(59,'Tiếng Anh','hoc ngu lam updatebody',48),(61,'Tiếng Việt','Tiếng mẹ đẻ',46);
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` VALUES (15,'Hoc ngu nhat lop',41),(16,'Hoc ngu cap huyen',41),(17,'Hoc ngu cap tinh',41),(21,'Hoc ngu nhat lop',47),(22,'Hoc ngu cap huyen',47),(23,'Hoc ngu cap tinh',47),(30,'GPA Đại học trên 3.6',48),(31,'Sinh viên 5 tốt updatebody',48),(34,'GPA Đại học trên 3.2',46),(35,'Sinh viên 5 tốt',46),(36,'Học bổng đầu vào',46);
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
INSERT INTO `reference` VALUES (20,'Facebook','fb.com/huhu',41),(21,'Instagram','ig.com/huhu',41),(24,'Facebook','fb.com/huhu',47),(25,'Instagram','ig.com/huhu',47),(32,'Facebook','fb.com/ungvien5',48),(33,'Instagram','ig.com/ungvien5',48),(35,'Facebook','fb.com/ungvien3',46),(36,'Youtube','youtube.com/ungvien3',46);
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
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirement`
--

LOCK TABLES `requirement` WRITE;
/*!40000 ALTER TABLE `requirement` DISABLE KEYS */;
INSERT INTO `requirement` VALUES (56,'khong qqqqq',7),(109,'Chăm sóc Khách hàng',3),(110,'Tiếp nhận, hoàn thành hồ sơ vay, hướng dẫn thủ tục giải ngân cho khách hàng',3),(111,'Tư vấn làm hồ sơ vay tín chấp cho khách hàng, không cần thu hồi nợ',3),(112,'Data khách hàng đăng ký vay có sẵn',3),(113,'update',3),(114,'Biết về xe oto',41),(115,'Am hiểu về lịch sử của BMW',41),(118,'Biết về xe Audi Q Series',43),(119,'Am hiểu về lịch sử của BMW',43),(131,'Quản trị nội dung và các hoạt động trên website',5),(132,'Nghiên cứu nhu cầu của các đối tượng khách hàng để xây dựng kế hoạch marketing phù hợp.',5),(133,'Req Update',5),(134,'Triển khai các chiến lược đã được phê duyệt',5),(135,'Thống kê, đo lường và báo cáo kết quả đạt được được các chiến lược đã triển khai',5),(136,'Phối hợp với các phòng ban liên quan để đảm bảo tiến độ công việc',5),(137,'Tiếng Anh chuyên ngành và giao tiếp tốt.',44),(138,'Tốt nghiệp Đại học chuyên ngành Marketing, Truyền thông, QTKD...',44),(139,'Có kinh nghiệm ít nhất 1 năm trong lĩnh vực Marketing, nắm vững kiến thức Marketing.',44),(140,'Biết về các kĩ thuật phân tích dữ liệu trên mạng xã hội và website như WebTrends, quản trị tốt các công cụ Marketing.',44),(141,'Am hiểu và biết cách quản trị Fanpage, website, youtube và các kênh truyền thông online khác.',44),(142,'Ứng viên có kinh nghiệm bán hàng từ 1 năm trở lên.',45),(143,'Đam mê xe ô tô, có kiến thức tổng quan về sản phẩm, thị trường xe du lịch.',45),(144,'Tốt nghiệp Trung cấp, Cao đẳng trở lên các chuyên ngành: Công nghệ kỹ thuật ô tô, cơ khí động lực, cơ khí ô tô, QTKD, marketing, Du lịch/ Nhà Hàng/ Khách sạn, CNTT, TC-NH, Kế toán hoặc các khối ngành liên quan đến khối kinh tế.',45),(145,'Có tố chất bán hàng, kỹ năng giao tiếp – đàm phán, thiết lập và phát triển mối quan hệ khách hàng.',45),(146,'Ưu tiên ứng viên đã có GPLX Ô tô.',45),(147,'Tốt nghiệp Đại học (Ưu tiên chuyên ngành marketing, QTKD).',46),(148,'Có khả năng hoạch định kế hoạch, mục tiêu trung hạn và ngắn hạn.',46),(149,'Trình độ ngoại ngữ: Anh văn. Có khả năng đọc hiểu các tài liệu tiếng Anh.',46),(150,'Kinh nghiệm làm việc trên 3 năm ở vị trí tương đương (Ưu tiên trong lĩnh vực ô tô).',46),(151,'Thành thạo tin học văn phòng.',46),(152,'- Có kỹ năng giao tiếp, tư vấn khách hàng. Ưu tiên ứng viên đã có bằng lái xe B2/B1.',47),(153,'Tốt nghiệp Trung cấp trở lên các chuyên ngành QTKD, Marketing, TC-NH, Công nghệ Ô tô, Du lịch, Khối ngành Kinh tế,…; Có kinh nghiệm bán hàng 6 tháng trở lên.',47),(154,'Nam / Nữ, Tuổi từ 22 – 30',47),(155,'Giấy phép lái xe hạng B2 (Bắt buộc) và khả năng giao tiếp tiếng Anh.',48),(156,'Đam mê xe ô tô, có kiến thức tổng quan về sản phẩm, thị trường xe du lịch.',48),(157,'Có kỹ năng bán hàng, kỹ năng giao tiếp – đàm phán, thiết lập và phát triển mối quan hệ khách hàng.',48),(158,'Có ít nhất 1 năm kinh nghiệm ở vị trí bán hàng. Ưu tiên ứng viên có kinh nghiệm bán hàng xe ô tô',48),(159,'Tốt nghiệp Trung cấp trở lên các chuyên ngành: Ô tô, cơ khí động lực, QTKD, marketing, quản trị nhà hàng – khách sạn, du lịch, ngôn ngữ Anh hoặc các ngành Kinh tế liên quan.',48),(160,'Có thể làm việc theo ca;',49),(161,'Tốt nghiệp PTTH;',49),(162,'Giọng nói rõ ràng, mạch lạc;',49),(163,'Thích nghi với áp lực công việc, trách nhiệm và kỷ luật tốt;',49),(164,'Kỹ năng giao tiếp, thuyết phục khách hàng;',49),(165,'Kỹ năng phân tích và xử lý tình huống;',49),(166,'Cần có kinh nghiệm làm việc trong lĩnh vực chăm sóc khách hàng, đã từng làm việc tại Call Center là một lợi thế;',49),(167,'Kiên nhẫn, cẩn thận, có tinh thần học hỏi;',49),(168,'Kỹ năng lắng nghe và truyền đạt thông tin;',49),(169,'Tinh thần đồng đội cao.',49),(170,'Tính cách hòa đồng, kiên nhẫn và dễ tạo thiện cảm.',50),(171,'Có khả năng thấu hiểu, lắng nghe và giao tiếp hiệu quả với khách hàng.',50),(172,'Có kinh nghiệm CSKH từ 6 tháng trở lên.',50),(173,'Sử dụng thành thạo tin học văn phòng.',50),(174,'Có khả năng xử lý các khiếu nại và giải quyết các vấn đề phát sinh của khách hàng.',50),(175,'Sức khỏe tốt, chấp nhận đi thị trường (chiếm 95% thời gian làm việc);',51),(176,'Học vấn từ trung cấp trở lên;',51),(177,'Thái độ tốt, nhã nhặn, chịu khó và có tinh thần chăm sóc khách hàng tốt.',51),(178,'Kỹ năng tư vấn đối tác, giao tiếp tốt, xây dựng mối quan hệ tốt với đối tác;',51),(179,'Chuyên môn: Có ít nhất 1 năm kinh nghiệm lĩnh vực thị trường, thanh toán điện tử, các ngành nghề liên quan;',51),(180,'Thành thạo vi tính văn phòng cơ bản;',51),(181,'Kỹ năng làm việc nhóm và độc lập;',51),(182,'Có hiểu biết với công nghệ di động, thanh toán offline, Mobile apps, Ví điện tử…, là 1 lợi thế;',51),(183,'Be familiar with RDBMS such as MySQL/PostgreSQL or NoSQL databases such as Cassandra, MongoDB is a plus.',52),(184,'Experience with ReactJS is a plus.',52),(185,'Knowledge of multiple front-end languages and libraries such as HTML/ CSS, JavaScript, jQuery.',52),(186,'2+ years experience at server-side languages such as Java.',52),(187,'Be familiar with Git.',52),(188,'Experience with web servers, caching solution such as Redis.',52),(189,'Good knowledge of data structures, system design and algorithms.',52),(190,'Experience with Spring framework (Spring Bean, Spring AOP, Spring Data, Spring MVC), Spring Boot, Hibernate.',52),(191,'Have Degree in Computer Science or related fields.',52),(192,'Bachelor degree in related field preferably.',53),(193,'Participated in small / large projects of the department.',53),(194,'Ability to analyze database is an advantage.',53),(195,'Minimum 2 years of experience or more in equivalent positions',53),(196,'Good communication skills, problem-solving skills and analytical skills.',53),(197,'Tư duy logic, rõ ràng;',54),(198,'Kinh nghiệm tối thiểu: 4 – 5 năm về chăm sóc thị trường, ngành hàng F&B, CVS, Siêu thị, … và 2 – 3 năm quản lý team (50 – 70 nhân viên);',54),(199,'Có Kiến thức chuyên môn về ví điện tử, máy POS, hệ thống thanh toán tại cửa hàng là một lợi thế;',54),(200,'Kỹ năng tư duy, sáng tạo;',54),(201,'Có kinh nghiệm làm việc với dữ liệu;',54),(202,'Kỹ năng lãnh đạo, giám sát, đánh giá hiệu quả công việc;',54),(203,'Kỹ năng phân tích dữ liệu, giải quyết vấn đề;',54),(204,'Thành thạo ngoại ngữ là một lợi thế;',54),(205,'Kỹ năng giao tiếp, trình bày tốt;',54),(206,'Kỹ năng quản lý, giao việc;',54),(207,'Sức khỏe tốt;',54),(208,'Kinh nghiệm cần thiết: Có 3 năm kinh nghiệm giám sát khu vực, quản lý chất lượng/ đánh giá nội bộ hoặc tương đương;',54),(209,'Thành thạo tin học văn phòng;',54),(210,'Bằng cấp: tốt nghiệp Đại Học;',54),(211,'Có kinh nghiệm, thành thạo trong việc làm báo cáo và đề xuất để cải thiện kết quả phòng ban;',54),(212,'Cẩn thận, tỉ mỉ;',54),(213,'Có kiến thức cơ bản về Java core, OOP hoặc có kiến thức tốt về kiểm thử phần mềm (Manual Testing).',55),(214,'Ham học hỏi, máu lửa, nhiệt huyết, sẵn sàng chinh chiến đối đầu với thử thách khó khăn tại các dự án phần mềm lớn. Tuân thủ kỷ luật & có trách nhiệm với công việc.',55),(215,'Ưu tiến ứng viên đã có kiến thức cơ bản về Automation Testing hoặc đã sử dụng/tìm hiểu về một trong các Framework: Selenium, Appium, TestNG, Cucumber…',55),(216,'Ưu tiên ứng viên có khả năng đọc hiểu tiếng Anh tốt.',55),(217,'Có thể tham gia đào tạo/làm việc Full-time từ thứ 2 - thứ 6.',55),(218,'Having from 3-year experience in Python (worked with libraries/framework such as pandas, numpy, flask; django is a plus)',56),(219,'Experience working with scripting language (Bash, Powershell).',56),(220,'Adequate knowledge of Relational Database system. SQL is mandatory.',56),(221,'English communication skills (Be able to communicate directly with customers)',56),(222,'Solid experience working with Public Cloud Provider (prefer AWS/Azure). Have knowledge about cloud services such as AWS Lambda, AWS DynamoDB, EC2, S3, SQS, SNS,…',56),(223,'Knowledge about NoSQL is a plus',56),(224,'Good base knowledge about data engineering (Have hands-on experience with data engineering including data ingestion, data transformation and data validation)',56),(225,'Có khả năng/ kinh nghiệm làm leader',57),(226,'Có kĩ năng phân tích và báo cáo',57),(227,'Có kĩ năng làm việc nhóm',57),(228,'Nhiệt tình, năng động, có tâm huyết với công việc và mong muốn làm việc lâu dài với công ty.',57),(229,'Có base IT, có kinh nghiệm làm việc với Database, SQL',57),(230,'Có kiến thức / kinh nghiệm test, process test, tạo test case, log bug',57),(231,'Support for learning and certificate examination.',58),(232,'Attractive offer, plus annual compensation and performance bonus.',58),(233,'“FPT care” health insurance provided by PTI and is exclusive for FPT employees.',58),(234,'International, dynamic, friendly working environment',58),(235,'Annual leave, working conditions follow Vietnam labor laws.',58),(236,'Salary review 1 time/year or on excellent performance',58),(237,'Other allowances: transportation allowance, lunch allowance, working on-site allowance, etc.',58),(238,'Company shuttle buses provide convenient way of transportation for all employees.',58),(239,'Opportunity to work directly with customers.',58),(240,'Attractive salary. Performance based award up to $1800.',58),(241,'Opportunity to learn and work with high standard of quality software development',58),(242,'Annual Summer Vacation: follows company’s policy and starts from May every year',58),(243,'Have chance to build long term relationships with colleagues via multiple projects.',58),(244,'Ngoan và chịu khó',59),(245,'Có hứng thú về Content Creative, Social Media, Event Planner...',59),(246,'Tiếng Anh tốt (không yêu cầu phải giao tiếp)',59),(264,'Strong organisational, communication and interpersonal skills.',12),(265,'A working knowledge of commercial database systems.',12),(266,'Excellent administration, typing and data entry skills.',12);
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
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_skill_candidate_idx` (`candidate_id`),
  CONSTRAINT `fk_skill_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` VALUES (1,'Java','Master',42),(10,'Dev óp','Mát tờ',41),(11,'Tét tơ','rớt môn',41),(14,'Dev óp','Mát tờ',47),(15,'Tét tơ','rớt môn',47),(22,'Back-end Java','Master',48),(23,'Front-end ReactJS','Beginner',48),(25,'Back-end Java','Master',46);
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
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_talent_candidate_idx` (`candidate_id`),
  CONSTRAINT `fk_talent_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talent`
--

LOCK TABLES `talent` WRITE;
/*!40000 ALTER TABLE `talent` DISABLE KEYS */;
INSERT INTO `talent` VALUES (1,'Đàn',42),(2,'Đấm nhau',42),(3,'Võ mồm',42),(10,'Xiếc',41),(11,'Hát',41),(12,'Học ngu',41),(16,'Xiếc',47),(17,'Hát',47),(18,'Học ngu',47),(22,'Chơi CSGO giỏi updatebody',48),(24,'Chơi cầu lông giỏi',46);
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
  `address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `joined_date` datetime DEFAULT NULL,
  `refresh_token` varchar(50) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `reset_password_token` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `refresh_token_UNIQUE` (`refresh_token`),
  UNIQUE KEY `UK_oq7f070s2fgswrf8v0kwou0qd` (`candidate_id`),
  UNIQUE KEY `UK_ggbcixcrmm7q2anlgt4jm9htm` (`company_id`),
  KEY `fk_user_candidate_idx` (`candidate_id`),
  KEY `fk_user_employer_idx` (`company_id`),
  CONSTRAINT `fk_user_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,NULL,NULL,'admin','$2a$10$hZnZYzt2JVptfVGAOK59POfuGAT/Ba1f5YI8dDY/qQGfuUDFB8CKu','https://res.cloudinary.com/dxorabap0/image/upload/v1659263863/nelb30hnkj3iljs45vqb.jpg','ADMIN',1,'Nguyễn Hoàng Nam','nguyenhoangn023@gmail.com','014324325','2001-09-19',0,'quận Bình Thạnh, thành phố Hồ Chí Minh','2022-05-05 13:19:02','246111c5-2599-4944-bc6f-c40e20a3dc53',NULL),(128,41,NULL,'ungvien8','$2a$10$iZD92COHYJBWRwX6te.deeESKJ4wVx8PMa4jwPhKYH0Vgcp/aFPdK','https://res.cloudinary.com/nhn1909/image/upload/v1666431249/zecxigoii1i37s5ehhk0.jpg','CANDIDATE',1,'Ứng viên 8','ungvien8@gmail.com','0484329391','1970-01-01',0,'Ứng viên 8 địa chỉ',NULL,'804ee331-58ea-4bc5-b18f-ebe99120a39e',NULL),(129,42,NULL,'ungvien2','$2a$12$qI7rd2J91n4qTfdXq9iHzeTtszwP16Gv9PJDkh4l1HkSzqN2o/sVq','https://res.cloudinary.com/nhn1909/image/upload/v1666760613/fjxsehcfhgy87hhsoicx.jpg','CANDIDATE',1,'Ứng viên 2 update body','ungvien2.updatebody@gmail.com','0547657333','2022-10-07',0,'Địa chỉ của ứng viên 2 update body đây',NULL,'ecca2910-d08b-4bc4-bca6-fda4b903325e',NULL),(134,NULL,21,'bmw','$2a$10$mVM7SYjwyP1.Ay/w11gOX.SzAQDsb3uwaAQVZ39kZMBSzqfAOq.nq','https://res.cloudinary.com/nhn1909/image/upload/v1651908635/jfvfqvmwniucfe28lcez.jpg','COMPANY',1,NULL,'company@gmail.com',NULL,NULL,0,NULL,NULL,'41f9a200-85ae-4046-9720-84361a9427ea',NULL),(136,NULL,22,'audi','$2a$12$pilX/gx1.tPviGdIj1X4ouSo3.2nswjEX8lDwvHoMcVxMZVouFBjy','https://res.cloudinary.com/nhn1909/image/upload/v1651837322/tslkce327ci4n33bakug.jpg','COMPANY',1,'Audi Automotive manufacture','audi@gmail.com','01234567','2001-09-19',0,'Thành phố Hồ Chí Minh','2022-09-23 00:22:56','c40a02ae-229a-4241-83e8-0a927611a30c',NULL),(137,46,NULL,'ungvien3','$2a$10$ss0i7xM1Ux8Pfa6C2yEM3.E/dJJIbe95wK/TX0kxQ2GN0bpE3xXeW','https://res.cloudinary.com/nhn1909/image/upload/v1665305687/ervwexpzqr6gdoyxneeh.jpg','CANDIDATE',1,'update img1','update1 email','update1 phone','2000-05-11',1,'update img1','2022-10-07 11:09:02','1eff8875-469a-4d7d-a3e9-e338752dd9c7',NULL),(146,47,NULL,'ungvien4','$2a$10$cpCdGksuwrWmK10b146Q/OGlQwXLpHRK9fskXr0hA/gOWdlTXvxCW','https://res.cloudinary.com/nhn1909/image/upload/v1665681659/wxrl9km2jzflyn2gtivl.jpg','CANDIDATE',1,'ungvien4 fullnameU','ungvien4 emailU','ungvien4 phoneU','2001-09-09',1,'ungvien4 img1U','2022-10-13 22:19:36','4ce38f1e-a7e5-4462-985a-910ef3ea9a1e',NULL),(147,48,NULL,'ungvien5','$2a$10$O4zm4ilIF7fkbIOp50T7UOTu6YErOKrFRGEca3xhGdr14IAHDtVEG','https://res.cloudinary.com/nhn1909/image/upload/v1665764409/vx019dcu1vchcmlybz2p.jpg','CANDIDATE',1,'ungvien5 check fullnameU','ungvien5@gmail.com','01122667788','2001-09-01',1,'ungvien5qq img1U','2022-10-13 22:19:54','877d6313-a326-48e2-8a91-e770606e1f94',NULL),(148,49,NULL,'ungvien6','$2a$10$aK5UQynWBys.AG94l2tP1.ZjiV2N8d6JVaU8.3GO57jmOJJaYp8Qy','https://res.cloudinary.com/nhn1909/image/upload/v1665763667/llvt6gz7iojeaovonrcb.jpg','CANDIDATE',1,'ungvien6 check fullnameU','ungvien6@gmail.com','01122334455','2001-09-09',1,'ungvien6qq img1U','2022-10-13 22:20:05','1c2471a6-7326-4a41-904a-0c5257529792',NULL),(149,NULL,23,'momo','$2a$10$SSy9rLaiejiZy1yNb1Xj5uU6ye4n9BuHd2hWCo9zE/1T3NPV28sR6','https://res.cloudinary.com/nhn1909/image/upload/v1666168826/ngeau7hurdmrnamfvgpu.png','COMPANY',1,'momo company','momo@gmail.com','02854147667','1970-01-01',1,'Lầu 6, Toà nhà Phú Mỹ Hưng, số 8 Hoàng Văn Thái, khu phố 1, Phường Tân Phú, Quận 7, Thành phố Hồ Chí Minh','2022-10-19 15:32:07','9f67d156-3ba9-4edb-800e-bcdfa2f9f887',NULL),(150,NULL,24,'intel','$2a$10$wEMBufn1tSeh2LBp7MeavO.0H03w..QW8GI3dWoHCZx9ebIYQbwaS','https://res.cloudinary.com/nhn1909/image/upload/v1666170593/w1tt9qwlgqiefweg5e9j.png','COMPANY',1,'Intel employer','intel.employer1@gmail.com','0321843386','1970-01-01',1,'','2022-10-19 16:01:19','cc6cd85a-8680-4d9e-878f-42b076aaddbb',NULL),(151,NULL,25,'fpt','$2a$10$mWvtSQ3OC3fNPaRXIZbJqO0wDMgjNBvrvpWf0CWw69j6FgE2OWH3y','https://res.cloudinary.com/nhn1909/image/upload/v1666171508/jd0wa8ct3spcghxhfeoc.jpg','COMPANY',1,'Fpt employer','fpt.employer1@gmail.com','0321483243','1970-01-01',0,'','2022-10-19 16:19:13','9b915344-e3f7-4a04-a2bb-92df826cb8ec',NULL),(152,52,NULL,'ungvien7','$2a$10$135gveVKVuTE9nY9mY30UeEROVLtdZO4DsvYtgXSrC.S4gI/zgbee','https://res.cloudinary.com/nhn1909/image/upload/v1666364414/mde5wf9l1nk8yhqc7vty.jpg','CANDIDATE',1,'Ứng viên 7','ungvien7@gmail.com','0485729391','1970-01-01',1,'Ứng viên 7 địa chỉ','2022-10-21 22:00:14','a6ece829-7c09-4c6e-931b-4ea9878d2901',NULL),(154,NULL,26,'tiktok','$2a$10$dv8btLkxaO5528t/3AAgMujFU7xYY6pE8BjXUMtQ8//ZUO09J1XDa','https://res.cloudinary.com/nhn1909/image/upload/v1666365626/b83cmdqqw9qgqwcz6ivp.jpg','COMPANY',1,'companyAdminTest','companyAdminTest@gmail.com','0485722391','1970-01-01',0,'companyAdminTest địa chỉ','2022-10-21 22:20:26','8ff354bd-2aba-44c3-bf09-4e659225d7c9',NULL),(155,54,NULL,'ungvien9','$2a$10$Fpmlaqy4jlnudJPOv6yxDu0HkWuT.kcgg5E2ekvViTclxJy9HWtLq','https://res.cloudinary.com/nhn1909/image/upload/v1666774524/plmtwy8bwrif3nqocmit.jpg','CANDIDATE',1,'Ứng viên 9 update','ungvien9update@gmail.com','05473237333','2022-10-07',1,'Địa chỉ của ứng viên 9update','2022-10-26 15:43:37','f7afa489-6f77-4226-b987-9fdb962ad776',NULL),(156,55,NULL,'ungvien10','$2a$10$oFvOlGsGzvF1KRZqOMp4gefaiJWBcj0PLPidVGdpPaTHareH1TjHW','https://res.cloudinary.com/nhn1909/image/upload/v1642074622/tb-avatar-none_r1c2ye.jpg','CANDIDATE',1,NULL,'ungvien10@gmail.com',NULL,NULL,0,NULL,'2022-10-27 22:46:01','9420984b-008c-4dc1-a903-e87a7c4235af',NULL);
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
  CONSTRAINT `fk_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_experience`
--

LOCK TABLES `work_experience` WRITE;
/*!40000 ALTER TABLE `work_experience` DISABLE KEYS */;
INSERT INTO `work_experience` VALUES (5,NULL,NULL,'TMA Solution','Product Manager',42),(6,NULL,NULL,'CityNow','Senior',42),(7,'1970-01-02','1970-01-07','Proplayer CSGO','IGL',48),(9,'1970-05-23','1986-12-10','Proplayer CSGO','IGL',46);
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

-- Dump completed on 2022-10-28  2:35:59
