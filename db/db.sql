-- MySQL dump 10.13  Distrib 5.7.44, for osx10.18 (x86_64)
--
-- Host: 127.0.0.1    Database: my_app
-- ------------------------------------------------------
-- Server version	5.7.44-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统账号ID',
                            `username` varchar(64) NOT NULL COMMENT '用户名',
                            `password` varchar(64) NOT NULL COMMENT '密码',
                            `salt` varchar(32) NOT NULL COMMENT '盐值',
                            `staff_id` bigint(20) NOT NULL COMMENT '职工ID',
                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                            `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统账号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'admin','$2a$10$kgXXh1WUaK2/b9HAXNxxeuVPqTCr/xjnU9jXc5TmyRmXuh4dtPFN.','$2a$10$kgXXh1WUaK2/b9HAXNxxeu',1,'2024-04-07 16:33:38','2024-04-07 16:33:40',0);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统部门ID',
                               `title` varchar(32) NOT NULL COMMENT '部门标题',
                               `parent_id` int(10) NOT NULL DEFAULT '0' COMMENT '上级部门ID',
                               `path` varchar(128) DEFAULT NULL COMMENT '上级部门路径',
                               `level` int(10) NOT NULL DEFAULT '1' COMMENT '层级',
                               `sort` int(10) NOT NULL DEFAULT '0' COMMENT '排序',
                               `about` varchar(256) NOT NULL DEFAULT '' COMMENT '关于',
                               `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='系统部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'总部',0,NULL,1,0,'总部',1,'2024-04-12 21:54:05',1,'2024-04-12 21:54:08',0),(2,'技术部',1,'1',2,0,'集团技术部门，负责产研，996支持其他部门',1,'2024-04-12 21:57:05',1,'2024-04-12 21:57:08',0);
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统权限ID',
                               `title` varchar(32) NOT NULL COMMENT '权限标题',
                               `code` varchar(32) NOT NULL COMMENT '编码',
                               `about` varchar(256) NOT NULL DEFAULT '' COMMENT '关于',
                               `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='系统权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'职工列表','sys:staff:list','查看职工列表',1,'2024-04-12 21:51:48',1,'2024-04-12 21:51:52',0),(2,'职工详情','sys:staff:info','查看职工详情',1,'2024-04-13 12:56:50',1,'2024-04-13 12:56:53',0);
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permissions` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统角色权限ID',
                                    `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                    `permission_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
                                    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES (1,1,1,'2024-04-12 21:52:55','2024-04-12 21:52:58',0),(2,1,2,'2024-04-13 12:57:19','2024-04-13 12:57:22',0);
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统角色ID',
                         `title` varchar(32) NOT NULL COMMENT '角色标题',
                         `code` varchar(32) NOT NULL COMMENT '编码',
                         `about` varchar(256) NOT NULL DEFAULT '' COMMENT '关于',
                         `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
                         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
                         `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                         `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'系统管理员','sys_admin','系统超级管理员',1,'2024-04-12 21:52:27',1,'2024-04-12 21:52:31',0);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staffs`
--

DROP TABLE IF EXISTS `staffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staffs` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统职工ID',
                          `nickname` varchar(20) DEFAULT NULL COMMENT '职工昵称',
                          `real_name` varchar(20) NOT NULL COMMENT '职工真名',
                          `email` varchar(128) NOT NULL COMMENT '职工邮箱',
                          `avatar` varchar(128) DEFAULT NULL COMMENT '职工头像',
                          `gender` tinyint(4) DEFAULT '0' COMMENT '性别 0:男性 1:女性 2:未知',
                          `status` tinyint(4) DEFAULT '0' COMMENT '在职状态 0:离职 1:在职',
                          `role_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                          `department_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                          `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
                          `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                          `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统职工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staffs`
--

LOCK TABLES `staffs` WRITE;
/*!40000 ALTER TABLE `staffs` DISABLE KEYS */;
INSERT INTO `staffs` VALUES (1,'超级管理员','张晨','405954049@qq.com',NULL,0,1,1,2,0,'2024-04-07 16:30:37',0,'2024-04-07 16:30:43',0);
/*!40000 ALTER TABLE `staffs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-13 16:47:49