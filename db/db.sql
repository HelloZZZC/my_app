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
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uni_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统账号';
/*!40101 SET character_set_client = @saved_cs_client */;

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
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                    `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
                         `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                         `operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
                         `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                         `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
                          `phone` varchar(11) NOT NULL COMMENT '手机号',
                          `avatar` varchar(128) DEFAULT NULL COMMENT '职工头像',
                          `gender` tinyint(4) DEFAULT '0' COMMENT '性别 0:男性 1:女性 2:未知',
                          `status` tinyint(4) DEFAULT '0' COMMENT '在职状态 0:离职 1:在职',
                          `role_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                          `department_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                          `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `operator_id` bigint(20) DEFAULT NULL COMMENT '最后操作人ID',
                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                          `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统职工表';
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-26  0:05:57