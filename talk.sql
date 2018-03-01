/*
Navicat MySQL Data Transfer

Source Server         : mysqltest
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : talk

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-06-24 14:43:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `user_name` varchar(255) DEFAULT NULL,
  `friend_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('admin', 'xiaohong');
INSERT INTO `friend` VALUES ('admin', 'xiaoming');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_name` varchar(20) NOT NULL,
  `user_pass` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'db5d888a0480461f4fb978746d1baf34', '2017-06-22 16:17:57', '2017-06-20 16:18:01');
INSERT INTO `user` VALUES ('xiaohong', 'db5d888a0480461f4fb978746d1baf34', '2017-06-23 11:49:20', null);
INSERT INTO `user` VALUES ('xiaoming', 'db5d888a0480461f4fb978746d1baf34', '2017-06-23 11:46:18', null);
