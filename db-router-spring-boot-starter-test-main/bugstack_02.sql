/*
 Navicat Premium Data Transfer

 Source Server         : Local-MySQL
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : bugstack_02

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 05/10/2021 16:03:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for USER_01
-- ----------------------------
DROP TABLE IF EXISTS `USER_01`;
CREATE TABLE `USER_01` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `userId` varchar(9) DEFAULT NULL COMMENT '用户ID',
  `userNickName` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `userHead` varchar(16) DEFAULT NULL COMMENT '用户头像',
  `userPassword` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of USER_01
-- ----------------------------
BEGIN;
INSERT INTO `USER_01` VALUES (1, '184172133', '小傅哥', '01_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_01` VALUES (2, '980765512', '铁锤', '02_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_01` VALUES (3, '796542178', '团团', '03_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_01` VALUES (4, '523088136', '哈尼克兔', '04_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_01` VALUES (5, '123456001', '比丘卡', '05_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for USER_02
-- ----------------------------
DROP TABLE IF EXISTS `USER_02`;
CREATE TABLE `USER_02` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `userId` varchar(9) DEFAULT NULL COMMENT '用户ID',
  `userNickName` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `userHead` varchar(16) DEFAULT NULL COMMENT '用户头像',
  `userPassword` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of USER_02
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for USER_03
-- ----------------------------
DROP TABLE IF EXISTS `USER_03`;
CREATE TABLE `USER_03` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `userId` varchar(9) DEFAULT NULL COMMENT '用户ID',
  `userNickName` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `userHead` varchar(16) DEFAULT NULL COMMENT '用户头像',
  `userPassword` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of USER_03
-- ----------------------------
BEGIN;
INSERT INTO `USER_03` VALUES (1, '184172133', '小傅哥', '01_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_03` VALUES (2, '980765512', '铁锤', '02_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_03` VALUES (3, '796542178', '团团', '03_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_03` VALUES (4, '523088136', '哈尼克兔', '04_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_03` VALUES (5, '123456001', '比丘卡', '05_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
INSERT INTO `USER_03` VALUES (13, '980765512', '小傅哥', '01_50', '123456', '2021-10-05 15:56:05', '2021-10-05 15:56:05');
COMMIT;

-- ----------------------------
-- Table structure for USER_04
-- ----------------------------
DROP TABLE IF EXISTS `USER_04`;
CREATE TABLE `USER_04` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `userId` varchar(9) DEFAULT NULL COMMENT '用户ID',
  `userNickName` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `userHead` varchar(16) DEFAULT NULL COMMENT '用户头像',
  `userPassword` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of USER_04
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
