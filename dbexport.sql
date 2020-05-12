-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: CS4350_Lab4
-- ------------------------------------------------------
-- Server version	8.0.20-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ActualTripStopInfo`
--

DROP TABLE IF EXISTS `ActualTripStopInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ActualTripStopInfo` (
  `TripNumber` int NOT NULL,
  `Date` date NOT NULL,
  `ScheduledStartTime` time NOT NULL,
  `StopNumber` int NOT NULL,
  `ScheduledArrivalTime` time DEFAULT NULL,
  `ActualStartTime` time DEFAULT NULL,
  `ActualArrivalTime` time DEFAULT NULL,
  `NumberOfPassengerIn` int DEFAULT NULL,
  `NumberOfPassengerOut` int DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`Date`,`ScheduledStartTime`,`StopNumber`),
  KEY `StopNumber` (`StopNumber`),
  CONSTRAINT `ActualTripStopInfo_ibfk_1` FOREIGN KEY (`TripNumber`) REFERENCES `Trip` (`TripNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ActualTripStopInfo_ibfk_2` FOREIGN KEY (`StopNumber`) REFERENCES `Stop` (`StopNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActualTripStopInfo`
--

LOCK TABLES `ActualTripStopInfo` WRITE;
/*!40000 ALTER TABLE `ActualTripStopInfo` DISABLE KEYS */;
INSERT INTO `ActualTripStopInfo` VALUES (1,'2020-05-17','06:00:00',5,'08:30:00','06:00:00','08:35:00',17,14),(2,'2020-05-17','06:00:00',4,'07:00:00','06:15:00','07:00:00',29,23),(3,'2020-05-17','06:00:00',1,'06:30:00','06:30:00','07:10:00',22,18),(4,'2020-05-17','06:00:00',6,'07:00:00','06:05:00','07:15:00',28,3),(5,'2020-05-17','06:00:00',3,'08:00:00','06:00:00','07:50:00',10,9),(6,'2020-05-17','06:00:00',2,'06:30:00','06:30:00','07:00:00',30,20);
/*!40000 ALTER TABLE `ActualTripStopInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bus`
--

DROP TABLE IF EXISTS `Bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bus` (
  `BusID` int NOT NULL,
  `Model` varchar(10) DEFAULT NULL,
  `Year` year DEFAULT NULL,
  PRIMARY KEY (`BusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bus`
--

LOCK TABLES `Bus` WRITE;
/*!40000 ALTER TABLE `Bus` DISABLE KEYS */;
INSERT INTO `Bus` VALUES (1,'A',2010),(2,'S',2019),(3,'A',2015),(4,'S',2020);
/*!40000 ALTER TABLE `Bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Driver`
--

DROP TABLE IF EXISTS `Driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Driver` (
  `DriverName` varchar(30) NOT NULL,
  `DriverTelephoneNumber` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`DriverName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Driver`
--

LOCK TABLES `Driver` WRITE;
/*!40000 ALTER TABLE `Driver` DISABLE KEYS */;
INSERT INTO `Driver` VALUES ('Bin','6268888001'),('John','6268888002'),('Kollar','6268888005'),('Max','6268888004'),('Min','6268888003'),('Tom','6268888006');
/*!40000 ALTER TABLE `Driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stop`
--

DROP TABLE IF EXISTS `Stop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Stop` (
  `StopNumber` int NOT NULL,
  `StopAddress` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`StopNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stop`
--

LOCK TABLES `Stop` WRITE;
/*!40000 ALTER TABLE `Stop` DISABLE KEYS */;
INSERT INTO `Stop` VALUES (1,'Building A'),(2,'Building B'),(3,'Building C'),(4,'Building D'),(5,'Building E'),(6,'Building F');
/*!40000 ALTER TABLE `Stop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trip`
--

DROP TABLE IF EXISTS `Trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Trip` (
  `TripNumber` int NOT NULL,
  `StartLocationName` varchar(20) DEFAULT NULL,
  `DestinationName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trip`
--

LOCK TABLES `Trip` WRITE;
/*!40000 ALTER TABLE `Trip` DISABLE KEYS */;
INSERT INTO `Trip` VALUES (1,'Pomona','Pasadena'),(2,'Pomona','LA'),(3,'LA','Pomona'),(4,'LA','Pasadena'),(5,'Pasadena','Pomona'),(6,'Pasadena','LA');
/*!40000 ALTER TABLE `Trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TripOffering`
--

DROP TABLE IF EXISTS `TripOffering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TripOffering` (
  `TripNumber` int NOT NULL,
  `Date` date NOT NULL,
  `ScheduledStartTime` time NOT NULL,
  `ScheduledArrivalTime` time NOT NULL,
  `DriverName` varchar(30) DEFAULT NULL,
  `BusID` int DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`Date`,`ScheduledStartTime`),
  KEY `DriverName` (`DriverName`),
  KEY `BusID` (`BusID`),
  CONSTRAINT `TripOffering_ibfk_1` FOREIGN KEY (`TripNumber`) REFERENCES `Trip` (`TripNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TripOffering_ibfk_2` FOREIGN KEY (`DriverName`) REFERENCES `Driver` (`DriverName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TripOffering_ibfk_3` FOREIGN KEY (`BusID`) REFERENCES `Bus` (`BusID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TripOffering`
--

LOCK TABLES `TripOffering` WRITE;
/*!40000 ALTER TABLE `TripOffering` DISABLE KEYS */;
INSERT INTO `TripOffering` VALUES (1,'2020-05-17','06:00:00','08:30:00','Max',4),(1,'2020-05-18','06:00:00','08:30:00','Max',4),(1,'2020-05-19','06:00:00','08:30:00','Max',4),(1,'2020-05-20','06:00:00','08:30:00','Max',4),(1,'2020-05-21','06:00:00','08:30:00','Max',4),(1,'2020-05-22','06:00:00','08:30:00','Max',4),(1,'2020-05-23','06:00:00','08:30:00','Max',4),(2,'2020-05-17','06:00:00','07:00:00','Min',1),(3,'2020-05-17','06:00:00','06:30:00','John',4),(4,'2020-05-17','06:00:00','07:00:00','Tom',2),(5,'2020-05-17','06:00:00','08:00:00','Bin',3),(6,'2020-05-17','06:00:00','06:30:00','Kollar',3);
/*!40000 ALTER TABLE `TripOffering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TripStopInfo`
--

DROP TABLE IF EXISTS `TripStopInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TripStopInfo` (
  `TripNumber` int NOT NULL,
  `StopNumber` int NOT NULL,
  `SequenceNumber` int DEFAULT NULL,
  `DrivingTime` time DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`StopNumber`),
  KEY `StopNumber` (`StopNumber`),
  CONSTRAINT `TripStopInfo_ibfk_1` FOREIGN KEY (`TripNumber`) REFERENCES `Trip` (`TripNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TripStopInfo_ibfk_2` FOREIGN KEY (`StopNumber`) REFERENCES `Stop` (`StopNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TripStopInfo`
--

LOCK TABLES `TripStopInfo` WRITE;
/*!40000 ALTER TABLE `TripStopInfo` DISABLE KEYS */;
INSERT INTO `TripStopInfo` VALUES (1,5,1,'02:30:00'),(2,4,1,'01:00:00'),(3,1,2,'00:30:00'),(4,6,2,'01:00:00'),(5,3,3,'02:00:00'),(6,2,3,'00:30:00');
/*!40000 ALTER TABLE `TripStopInfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-11 20:03:59
