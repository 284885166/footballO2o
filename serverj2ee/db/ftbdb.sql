/*
Navicat MySQL Data Transfer

Source Server         : iotdb
Source Server Version : 50519
Source Host           : localhost:3311
Source Database       : ftbdb

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-04-08 17:44:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '8281A3CEB45DD5A42A95057A135F37E0');
