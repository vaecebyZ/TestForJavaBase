/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shopping

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-01-26 11:43:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for invoice
-- ----------------------------
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `cpname` varchar(255) DEFAULT NULL,
  `cpnum` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `bankname` varchar(255) DEFAULT NULL,
  `bankid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of invoice
-- ----------------------------
INSERT INTO `invoice` VALUES ('33', '1', '重庆咕鸽科技有限公司', '236685774', '开曼群岛', '8583244516', 'sanka', '262739323');
INSERT INTO `invoice` VALUES ('34', '1', '光圈科技', '27635192', '开曼群岛', '8583242345', 'sanka', '262739323');
INSERT INTO `invoice` VALUES ('35', '0', 'sanka', '7263784', '', '', '', '');
INSERT INTO `invoice` VALUES ('36', '0', '测试数据1', '232321', '', '', '', '');
