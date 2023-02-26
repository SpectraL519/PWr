-- MySQL dump 10.19  Distrib 10.3.37-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: lab3
-- ------------------------------------------------------
-- Server version	10.3.37-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Ludzie`
--

DROP TABLE IF EXISTS `Ludzie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ludzie` (
  `ludzie_id` int(11) NOT NULL AUTO_INCREMENT,
  `PESEL` char(11) DEFAULT NULL,
  `imie` varchar(30) DEFAULT NULL,
  `nazwisko` varchar(30) DEFAULT NULL,
  `data_urodzenia` date DEFAULT NULL,
  `plec` enum('K','M') DEFAULT NULL,
  PRIMARY KEY (`ludzie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ludzie`
--

LOCK TABLES `Ludzie` WRITE;
/*!40000 ALTER TABLE `Ludzie` DISABLE KEYS */;
INSERT INTO `Ludzie` VALUES (1,'22242687324','child_name_0','child_surname_0','2022-04-26','K'),(2,'05272591757','child_name_1','child_surname_1','2005-07-25','M'),(3,'17250539071','child_name_2','child_surname_2','2017-05-05','M'),(4,'14321358803','child_name_3','child_surname_3','2014-12-13','K'),(5,'09320410487','child_name_4','child_surname_4','2009-12-04','K'),(6,'67082092503','adult_name_0','adult_surname_0','1967-08-20','K'),(7,'89080197370','adult_name_1','adult_surname_1','1989-08-01','M'),(8,'96020452826','adult_name_2','adult_surname_2','1996-02-04','K'),(9,'85103042476','adult_name_3','adult_surname_3','1985-10-30','M'),(10,'00281702745','adult_name_4','adult_surname_4','2000-08-17','K'),(11,'01250210739','adult_name_5','adult_surname_5','2001-05-02','M'),(12,'83072522025','adult_name_6','adult_surname_6','1983-07-25','K'),(13,'95102193257','adult_name_7','adult_surname_7','1995-10-21','M'),(14,'93011411615','adult_name_8','adult_surname_8','1993-01-14','M'),(15,'97052734081','adult_name_9','adult_surname_9','1997-05-27','K'),(16,'81062210901','adult_name_10','adult_surname_10','1981-06-22','K'),(17,'88110642268','adult_name_11','adult_surname_11','1988-11-06','K'),(18,'82010509360','adult_name_12','adult_surname_12','1982-01-05','K'),(19,'00302834402','adult_name_13','adult_surname_13','2000-10-28','K'),(20,'99060118022','adult_name_14','adult_surname_14','1999-06-01','K'),(21,'80042160924','adult_name_15','adult_surname_15','1980-04-21','K'),(22,'68021417580','adult_name_16','adult_surname_16','1968-02-14','K'),(23,'03321754528','adult_name_17','adult_surname_17','2003-12-17','K'),(24,'64030110524','adult_name_18','adult_surname_18','1964-03-01','K'),(25,'63040202821','adult_name_19','adult_surname_19','1963-04-02','K'),(26,'78021930170','adult_name_20','adult_surname_20','1978-02-19','M'),(27,'72010135566','adult_name_21','adult_surname_21','1972-01-01','K'),(28,'72072236506','adult_name_22','adult_surname_22','1972-07-22','K'),(29,'81111104331','adult_name_23','adult_surname_23','1981-11-11','M'),(30,'03242853027','adult_name_24','adult_surname_24','2003-04-28','K'),(31,'86071995784','adult_name_25','adult_surname_25','1986-07-19','K'),(32,'63010816339','adult_name_26','adult_surname_26','1963-01-08','M'),(33,'84060675608','adult_name_27','adult_surname_27','1984-06-06','K'),(34,'77012097609','adult_name_28','adult_surname_28','1977-01-20','K'),(35,'02231846372','adult_name_29','adult_surname_29','2002-03-18','M'),(36,'91121105424','adult_name_30','adult_surname_30','1991-12-11','K'),(37,'84033075433','adult_name_31','adult_surname_31','1984-03-30','M'),(38,'70110566808','adult_name_32','adult_surname_32','1970-11-05','K'),(39,'98090565424','adult_name_33','adult_surname_33','1998-09-05','K'),(40,'78080361199','adult_name_34','adult_surname_34','1978-08-03','M'),(41,'94090902072','adult_name_35','adult_surname_35','1994-09-09','M'),(42,'89010522344','adult_name_36','adult_surname_36','1989-01-05','K'),(43,'64021495850','adult_name_37','adult_surname_37','1964-02-14','M'),(44,'95112397232','adult_name_38','adult_surname_38','1995-11-23','M'),(45,'69051084160','adult_name_39','adult_surname_39','1969-05-10','K'),(46,'03282042700','adult_name_40','adult_surname_40','2003-08-20','K'),(47,'82111076105','adult_name_41','adult_surname_41','1982-11-10','K'),(48,'90090571984','adult_name_42','adult_surname_42','1990-09-05','K'),(49,'94112236550','adult_name_43','adult_surname_43','1994-11-22','M'),(50,'84082536181','adult_name_44','adult_surname_44','1984-08-25','K'),(51,'15051712637','elderly_name_0','elderly_surname_0','1915-05-17','M'),(52,'37112490775','elderly_name_1','elderly_surname_1','1937-11-24','M'),(53,'57101923626','elderly_name_2','elderly_surname_2','1957-10-19','K'),(54,'19101931328','elderly_name_3','elderly_surname_3','1919-10-19','K'),(55,'33010717947','elderly_name_4','elderly_surname_4','1933-01-07','K');
/*!40000 ALTER TABLE `Ludzie` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER peselValidation
    BEFORE INSERT 
    ON Ludzie
    FOR EACH ROW

    BEGIN
        IF CHAR_LENGTH(NEW.PESEL) <> 11 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid length of PESEL';
        END IF;

        IF (NEW.PESEL NOT REGEXP '^[0-9]+$') THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid format of PESEL - must be numeric';
        END IF;

        IF (LEFT(RIGHT(NEW.PESEL, 2), 1) % 2 = 0 AND NEW.plec = 'M') 
        OR (LEFT(RIGHT(NEW.PESEL, 2), 1) % 2 <> 0 AND NEW.plec = 'K') THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid sex indication in PESEL';
        END IF;

        IF (LEFT(NEW.PESEL, 2) <> RIGHT(YEAR(NEW.data_urodzenia), 2)) THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid year indication in PESEL';
        END IF;

        IF (YEAR(NEW.data_urodzenia) > 1999) THEN
            IF (RIGHT(LEFT(NEW.PESEL, 4), 2) <> (MONTH(NEW.data_urodzenia) + 20)) THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid month indication in PESEL';
            END IF;
        ELSEIF (YEAR(NEW.data_urodzenia) < 1900) THEN
            IF (RIGHT(LEFT(NEW.PESEL, 4), 2) <> (MONTH(NEW.data_urodzenia) + 80)) THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid month indication in PESEL';
            END IF;
        ELSE
            IF (RIGHT(LEFT(NEW.PESEL, 4), 2) <> (MONTH(NEW.data_urodzenia))) THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid month indication in PESEL';
            END IF;
        END IF;

        IF (RIGHT(LEFT(NEW.PESEL, 6), 2) <> DAY(NEW.data_urodzenia)) THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid day indication in PESEL';
        END IF;

        IF (RIGHT(NEW.PESEL, 1) <> getControlDigit(NEW.PESEL)) THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid control digit in PESEL';
        END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Pracownicy`
--

DROP TABLE IF EXISTS `Pracownicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pracownicy` (
  `ludzie_id` int(11) NOT NULL CHECK (`ludzie_id` >= 0),
  `zawod_id` int(11) NOT NULL CHECK (`zawod_id` >= 0),
  `pensja` float DEFAULT NULL CHECK (`pensja` >= 0),
  PRIMARY KEY (`ludzie_id`,`zawod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pracownicy`
--

LOCK TABLES `Pracownicy` WRITE;
/*!40000 ALTER TABLE `Pracownicy` DISABLE KEYS */;
INSERT INTO `Pracownicy` VALUES (6,3,8926.52),(7,3,9298.76),(8,3,6926.75),(9,4,6247.69),(10,2,3035.36),(11,2,3433.46),(12,4,13121.7),(13,1,11489),(14,1,19047.5),(15,3,30064.6),(16,4,28888.2),(17,4,19016),(18,2,2704.82),(19,4,27410.4),(20,1,17957.2),(21,4,17286),(22,4,21165.8),(23,2,2508.86),(24,2,3900.91),(25,2,3816.55),(26,3,8278.19),(27,1,24543.8),(28,4,26001),(29,3,15568.8),(30,1,25551.1),(31,2,4226.68),(32,1,16076.4),(33,3,33517.2),(34,3,25591.4),(35,3,40646.1),(36,4,19882.8),(37,1,21234.4),(38,2,3382.8),(39,2,2403.2),(40,2,4038.87),(41,3,27774.2),(42,2,3238.57),(43,2,2379.86),(44,4,4938.26),(45,4,26515.7),(46,2,3785.5),(47,4,9542.21),(48,4,14306.6),(49,1,11176.9),(50,3,17999.1),(51,1,24542.2),(52,1,17116.8),(53,1,19557.7),(54,1,21003.1),(55,2,2540.68);
/*!40000 ALTER TABLE `Pracownicy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Zawody`
--

DROP TABLE IF EXISTS `Zawody`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Zawody` (
  `zawod_id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) DEFAULT NULL,
  `pensja_min` float DEFAULT NULL CHECK (`pensja_min` >= 0 and `pensja_min` < `pensja_max`),
  `pensja_max` float DEFAULT NULL CHECK (`pensja_max` >= 0),
  PRIMARY KEY (`zawod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Zawody`
--

LOCK TABLES `Zawody` WRITE;
/*!40000 ALTER TABLE `Zawody` DISABLE KEYS */;
INSERT INTO `Zawody` VALUES (1,'polityk',8000,30000),(2,'nauczyciel',2000,5000),(3,'lekarz',6000,50000),(4,'informatyk',4500,40000);
/*!40000 ALTER TABLE `Zawody` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-04 22:26:16
