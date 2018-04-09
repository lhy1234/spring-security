/*
Navicat MySQL Data Transfer

Source Server         : 我的机器
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : imooc-demo

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2018-04-09 14:25:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for imooc_userconnection
-- ----------------------------
DROP TABLE IF EXISTS `imooc_userconnection`;
CREATE TABLE `imooc_userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL DEFAULT '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of imooc_userconnection
-- ----------------------------

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(100) DEFAULT NULL COMMENT '权限url',
  `type` varchar(20) DEFAULT NULL COMMENT '权限类型 menu/button',
  `order_num` int(1) DEFAULT NULL COMMENT '排序规则',
  `is_leaf` int(1) DEFAULT '0' COMMENT '是否是叶子节点 0 否  1 是',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `permission_code` varchar(100) DEFAULT NULL COMMENT '权限字符串',
  `parent_ids` varchar(20) DEFAULT NULL COMMENT '父id列表',
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('1', '系统设置', null, 'menu', '1', '0', '0', null, '/0', null, null);
INSERT INTO `tb_permission` VALUES ('2', '销售管理', '/user/*', 'menu', '2', '0', '0', 'sale:*', '/0', null, null);
INSERT INTO `tb_permission` VALUES ('11', '角色管理', 'role/toListPage', 'menu', '1', '0', '1', 'role:toListPage', '/0/1', null, null);
INSERT INTO `tb_permission` VALUES ('12', '用户管理', 'user/list', 'menu', '2', '0', '1', null, '/0/1', null, null);
INSERT INTO `tb_permission` VALUES ('13', '个人信息', 'user/info', 'menu', '3', '0', '1', null, null, null, null);
INSERT INTO `tb_permission` VALUES ('21', '销售计划', 'sale/plan', 'menu', '1', '0', '2', null, '/0/2', null, null);
INSERT INTO `tb_permission` VALUES ('22', '销售预测', 'sale/pre', 'menu', '2', '1', '2', null, '/0/2', null, null);
INSERT INTO `tb_permission` VALUES ('23', '机会管理', 'sale/chance', 'menu', '3', '1', '2', null, '/0/2', null, null);
INSERT INTO `tb_permission` VALUES ('111', '角色添加页面', 'role/toAdd', 'button', null, '1', '11', 'role:toAdd', '/0/1/11', null, null);
INSERT INTO `tb_permission` VALUES ('112', '角色增加', 'role/add', 'button', null, '1', '11', 'role:add', null, null, null);
INSERT INTO `tb_permission` VALUES ('113', '角色修改页面', 'role/toUpdate', 'button', null, '1', '11', 'role/toUpdate', null, null, null);
INSERT INTO `tb_permission` VALUES ('114', '角色删除', 'role/delete', 'button', null, '1', '11', 'role:delete', null, null, null);
INSERT INTO `tb_permission` VALUES ('115', '角色列表', 'role/list', 'menu', null, '0', null, 'role:list', null, null, null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `available` int(1) DEFAULT '1' COMMENT '是否可用 0-不可用  1-可用',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `update_date` date DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'admin', '超级管理员', '1', null, null);
INSERT INTO `tb_role` VALUES ('2', 'salemgr', '销售经理', '1', null, null);
INSERT INTO `tb_role` VALUES ('3', '权健自然养生堂', 'bhhh', '1', '2017-04-29', '2017-04-29');
INSERT INTO `tb_role` VALUES ('4', '牛背啊你', '牛背啊啊你', '1', '2017-04-30', '2017-04-30');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色表id 外键',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限表id 外键',
  PRIMARY KEY (`id`),
  KEY `fk_rp_ref_per_id` (`permission_id`),
  KEY `fk_rp_ref_role_id` (`role_id`),
  CONSTRAINT `fk_rp_ref_per_id` FOREIGN KEY (`permission_id`) REFERENCES `tb_permission` (`id`),
  CONSTRAINT `fk_rp_ref_role_id` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES ('1', '2', '2');
INSERT INTO `tb_role_permission` VALUES ('2', '2', '21');
INSERT INTO `tb_role_permission` VALUES ('3', '2', '22');
INSERT INTO `tb_role_permission` VALUES ('4', '2', '23');
INSERT INTO `tb_role_permission` VALUES ('5', '1', '1');
INSERT INTO `tb_role_permission` VALUES ('6', '1', '2');
INSERT INTO `tb_role_permission` VALUES ('7', '1', '11');
INSERT INTO `tb_role_permission` VALUES ('8', '1', '12');
INSERT INTO `tb_role_permission` VALUES ('9', '1', '13');
INSERT INTO `tb_role_permission` VALUES ('10', '1', '21');
INSERT INTO `tb_role_permission` VALUES ('11', '1', '22');
INSERT INTO `tb_role_permission` VALUES ('12', '1', '23');
INSERT INTO `tb_role_permission` VALUES ('13', '1', '111');
INSERT INTO `tb_role_permission` VALUES ('14', '1', '112');
INSERT INTO `tb_role_permission` VALUES ('15', '1', '113');
INSERT INTO `tb_role_permission` VALUES ('16', '1', '114');
INSERT INTO `tb_role_permission` VALUES ('17', '1', '115');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(50) NOT NULL COMMENT '盐',
  `email` varchar(20) DEFAULT NULL COMMENT '电子邮箱',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `update_date` date DEFAULT NULL COMMENT '修改日期',
  `locked` int(1) DEFAULT '0' COMMENT '是否锁定0未锁定 1锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'user', '$2a$10$Mwc9wMXw73W5Ga/mv8KCQOUZC.0i0Pm7svkXDJ07pEeXYWT86FP3W', 'lhy', null, null, null, null, null);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '外键 用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '外键 角色id',
  PRIMARY KEY (`id`),
  KEY `fk_ur_ref_user_id` (`user_id`),
  KEY `fk_ur_ref_role_id` (`role_id`),
  CONSTRAINT `fk_ur_ref_role_id` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`),
  CONSTRAINT `fk_ur_ref_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '2');
INSERT INTO `tb_user_role` VALUES ('2', '1', '1');
INSERT INTO `tb_user_role` VALUES ('3', '1', '4');
