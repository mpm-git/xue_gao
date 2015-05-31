/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : xue_gao

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2015-05-31 16:30:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `price` double NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', null, '雪糕1', '2', '1', '../res/img/goods/1.jpg');
INSERT INTO `goods` VALUES ('2', null, '雪糕2', '1', '3', '../res/img/goods/2.jpg');
INSERT INTO `goods` VALUES ('3', null, '雪糕3', '2', '4', '../res/img/goods/3.jpg');
INSERT INTO `goods` VALUES ('4', null, '冰饮1', '1', '2', '../res/img/goods/5.jpg');
INSERT INTO `goods` VALUES ('5', null, '冰饮2', '2', '3', '../res/img/goods/4.jpg');
INSERT INTO `goods` VALUES ('6', null, '冰饮3', '2', '3', '../res/img/goods/6.jpg');
INSERT INTO `goods` VALUES ('7', null, '杯系列1', '2', '3', '../res/img/goods/7.jpg');
INSERT INTO `goods` VALUES ('8', null, '杯系列2', '2', '3', '../res/img/goods/8.jpg');
INSERT INTO `goods` VALUES ('9', null, '杯系列3', '2', '3', '../res/img/goods/9.jpg');
INSERT INTO `goods` VALUES ('10', null, '杯系列4', '2', '3', '../res/img/goods/10.jpg');

-- ----------------------------
-- Table structure for `kind`
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pid` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `goodid` int(11) NOT NULL,
  `goodtype` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kind
-- ----------------------------
INSERT INTO `kind` VALUES ('14', null, null, '1', '雪糕系列', '0', '0', null, '0', '0');
INSERT INTO `kind` VALUES ('15', null, null, '2', '雪糕系列', '1', '1', null, '1', '0');
INSERT INTO `kind` VALUES ('16', null, null, '2', '雪糕系列', '1', '2', null, '2', '0');
INSERT INTO `kind` VALUES ('17', null, null, '2', '雪糕系列', '1', '3', null, '3', '0');
INSERT INTO `kind` VALUES ('18', null, null, '1', '冰饮系列', '0', '0', null, '0', '1');
INSERT INTO `kind` VALUES ('19', null, null, '2', '冰饮系列', '1', '1', null, '4', '1');
INSERT INTO `kind` VALUES ('20', null, null, '2', '冰饮系列', '1', '2', null, '5', '1');
INSERT INTO `kind` VALUES ('21', null, null, '2', '冰饮系列', '1', '3', null, '6', '1');
INSERT INTO `kind` VALUES ('22', null, null, '1', '杯系列', '0', '1', null, '0', '2');
INSERT INTO `kind` VALUES ('23', null, null, '2', '杯系列', '1', '1', null, '7', '2');
INSERT INTO `kind` VALUES ('24', null, null, '2', '杯系列', '1', '2', null, '8', '2');
INSERT INTO `kind` VALUES ('25', null, null, '2', '杯系列', '1', '3', null, '9', '2');
INSERT INTO `kind` VALUES ('26', null, null, '2', '杯系列', '1', '3', null, '10', '2');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `createTime` bigint(20) NOT NULL,
  `deadTime` bigint(20) NOT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `updateTime` bigint(20) NOT NULL,
  `usertId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for `order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodId` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `orderSerialNumber` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `shopping_cart`
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodId` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `price` double NOT NULL,
  `serialNumber` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
