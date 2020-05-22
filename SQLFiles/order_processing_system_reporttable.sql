-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: order_processing_system
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `reporttable`
--

DROP TABLE IF EXISTS `reporttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reporttable` (
  `bookId` int NOT NULL,
  `buyingDate` datetime NOT NULL,
  `userName` varchar(45) NOT NULL,
  `Quantity` int NOT NULL,
  PRIMARY KEY (`bookId`,`buyingDate`,`userName`),
  CONSTRAINT `BookIdfor` FOREIGN KEY (`bookId`) REFERENCES `book` (`ISBN`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporttable`
--

LOCK TABLES `reporttable` WRITE;
/*!40000 ALTER TABLE `reporttable` DISABLE KEYS */;
INSERT INTO `reporttable` VALUES (1,'2020-04-20 10:10:10','ahemd',3),(1,'2020-05-02 10:10:10','asdf',4),(1,'2020-05-02 10:10:10','hgfs',4),(1,'2020-05-02 10:10:10','mofggstafa',4),(1,'2020-05-02 10:10:10','mosdfstafa',4),(1,'2020-05-02 10:10:10','mostafa',4),(1,'2020-05-02 10:10:10','mostghafa',4),(1,'2020-05-10 10:10:10','mostafa',3),(1,'2020-05-15 10:10:10','ahemd',3),(1,'2020-05-22 00:28:35','mostafa',3),(2,'2020-03-01 10:10:10','ahemd',3),(2,'2020-04-12 10:10:10','mostafa',10),(2,'2020-05-16 10:10:10','ahemd',6),(3,'2020-02-25 10:10:10','ahemd',3),(3,'2020-05-10 10:10:10','mostafa',3);
/*!40000 ALTER TABLE `reporttable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-22  1:16:46
