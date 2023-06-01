-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cet6
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `aid` int NOT NULL,
  `pid` int DEFAULT NULL,
  `uuid` varchar(36) DEFAULT NULL,
  `eid` int DEFAULT NULL,
  `aobjective` varchar(100) DEFAULT NULL,
  `asubjective` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `answer_examination_eid_fk` (`eid`),
  KEY `answer_user_uuid_fk` (`uuid`),
  KEY `answer_paper_pid_fk` (`pid`),
  CONSTRAINT `answer_examination_eid_fk` FOREIGN KEY (`eid`) REFERENCES `examination` (`eid`),
  CONSTRAINT `answer_paper_pid_fk` FOREIGN KEY (`pid`) REFERENCES `paper` (`pid`),
  CONSTRAINT `answer_user_uuid_fk` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blank`
--

DROP TABLE IF EXISTS `blank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blank` (
  `bid` int NOT NULL,
  `qid` int DEFAULT NULL,
  `bcontext` varchar(100) DEFAULT NULL,
  `banswer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `blank_question_qid_fk` (`qid`),
  CONSTRAINT `blank_question_qid_fk` FOREIGN KEY (`qid`) REFERENCES `question` (`qid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blank`
--

LOCK TABLES `blank` WRITE;
/*!40000 ALTER TABLE `blank` DISABLE KEYS */;
/*!40000 ALTER TABLE `blank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `cid` int NOT NULL,
  `cuniversity` varchar(20) DEFAULT NULL,
  `cclassroom` varchar(30) DEFAULT NULL,
  `copen` datetime DEFAULT NULL,
  `cclose` datetime DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examination`
--

DROP TABLE IF EXISTS `examination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examination` (
  `eid` int NOT NULL,
  `cid` int DEFAULT NULL,
  `ebegin` datetime DEFAULT NULL,
  `eend` datetime DEFAULT NULL,
  `esubject` tinyint DEFAULT NULL,
  `estate` tinyint DEFAULT NULL,
  PRIMARY KEY (`eid`),
  KEY `examination_classroom_cid_fk` (`cid`),
  CONSTRAINT `examination_classroom_cid_fk` FOREIGN KEY (`cid`) REFERENCES `classroom` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examination`
--

LOCK TABLES `examination` WRITE;
/*!40000 ALTER TABLE `examination` DISABLE KEYS */;
/*!40000 ALTER TABLE `examination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee` (
  `fid` int NOT NULL,
  `fstate` tinyint DEFAULT NULL,
  `uuid` varchar(36) DEFAULT NULL,
  `fgenerated` datetime DEFAULT NULL,
  `fpaid` datetime DEFAULT NULL,
  `fsubject` tinyint DEFAULT NULL,
  `famount` float DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `fee_user_uuid_fk` (`uuid`),
  CONSTRAINT `fee_user_uuid_fk` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `gid` int NOT NULL,
  `uuid` varchar(36) DEFAULT NULL,
  `eid` int DEFAULT NULL,
  `gdication` float DEFAULT NULL,
  `greading` float DEFAULT NULL,
  `gwriting` float DEFAULT NULL,
  `gtotal` float DEFAULT NULL,
  PRIMARY KEY (`gid`),
  KEY `grade_examination_eid_fk` (`eid`),
  KEY `grade_user_uuid_fk` (`uuid`),
  CONSTRAINT `grade_examination_eid_fk` FOREIGN KEY (`eid`) REFERENCES `examination` (`eid`),
  CONSTRAINT `grade_user_uuid_fk` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `join`
--

DROP TABLE IF EXISTS `join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `join` (
  `jid` int NOT NULL,
  `jstate` tinyint DEFAULT NULL,
  `fid` int DEFAULT NULL,
  `uuid` varchar(36) DEFAULT NULL,
  `eid` int DEFAULT NULL,
  PRIMARY KEY (`jid`),
  KEY `join_examination_eid_fk` (`eid`),
  KEY `join_fee_fid_fk` (`fid`),
  KEY `join_user_uuid_fk` (`uuid`),
  CONSTRAINT `join_examination_eid_fk` FOREIGN KEY (`eid`) REFERENCES `examination` (`eid`),
  CONSTRAINT `join_fee_fid_fk` FOREIGN KEY (`fid`) REFERENCES `fee` (`fid`),
  CONSTRAINT `join_user_uuid_fk` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `join`
--

LOCK TABLES `join` WRITE;
/*!40000 ALTER TABLE `join` DISABLE KEYS */;
/*!40000 ALTER TABLE `join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option`
--

DROP TABLE IF EXISTS `option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `option` (
  `oid` int NOT NULL,
  `qid` int DEFAULT NULL,
  `ocontent` varchar(100) DEFAULT NULL,
  `oisright` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `option_question_qid_fk` (`qid`),
  CONSTRAINT `option_question_qid_fk` FOREIGN KEY (`qid`) REFERENCES `question` (`qid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option`
--

LOCK TABLES `option` WRITE;
/*!40000 ALTER TABLE `option` DISABLE KEYS */;
/*!40000 ALTER TABLE `option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paper` (
  `pid` int NOT NULL,
  `pquestions` varchar(100) DEFAULT NULL,
  `pscores` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `qid` int NOT NULL,
  `qtype` tinyint DEFAULT NULL,
  `qdifficulty` tinyint DEFAULT NULL,
  `qstem` text,
  `qnum` tinyint DEFAULT NULL,
  `qbelong` int DEFAULT NULL,
  PRIMARY KEY (`qid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record` (
  `rid` int NOT NULL,
  `uuid` varchar(36) DEFAULT NULL,
  `ranswer` text,
  `eid` int DEFAULT NULL,
  `rscore` float DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `record_examination_eid_fk` (`eid`),
  KEY `record_user_uuid_fk` (`uuid`),
  CONSTRAINT `record_examination_eid_fk` FOREIGN KEY (`eid`) REFERENCES `examination` (`eid`),
  CONSTRAINT `record_user_uuid_fk` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `uuid` varchar(30) NOT NULL,
  `suniversity` varchar(20) DEFAULT NULL,
  `scollege` varchar(20) DEFAULT NULL,
  `smajority` varchar(20) DEFAULT NULL,
  `sno` int DEFAULT NULL,
  `sclass` varchar(20) DEFAULT NULL,
  `sgrade` year DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  CONSTRAINT `student_user_uuid_fk` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `uuid` varchar(36) NOT NULL,
  `tuniversity` varchar(20) DEFAULT NULL,
  `tcollege` varchar(20) DEFAULT NULL,
  `tno` int DEFAULT NULL,
  `ttitle` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  CONSTRAINT `teacher_user_uuid_fk` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uuid` varchar(36) NOT NULL,
  `uname` varchar(20) DEFAULT NULL,
  `usex` tinyint(1) DEFAULT NULL,
  `ubirth` date DEFAULT NULL,
  `utel` varchar(20) DEFAULT NULL,
  `umail` varchar(20) DEFAULT NULL,
  `udoctype` tinyint DEFAULT NULL,
  `udocno` varchar(20) DEFAULT NULL,
  `upwd` varchar(20) DEFAULT NULL,
  `ustate` tinyint DEFAULT NULL,
  `utype` tinyint DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-01  1:04:17
