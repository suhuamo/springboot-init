/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : init

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 08/04/2024 10:36:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `name` varchar(255)  NOT NULL COMMENT '名称',
                           `password` varchar(255) NOT NULL COMMENT '密码',
                           `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
                           `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                           `telephone` varchar(255) DEFAULT NULL COMMENT '电话号码',
                           `sex` tinyint(3) unsigned NOT NULL COMMENT '性别，0-男；1-女',
                           `type` tinyint(3) unsigned NOT NULL COMMENT '类型，0-管理员；1-普通用户',
                           `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
                           `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
                           `delete_flag` tinyint(3) unsigned DEFAULT 0 COMMENT '逻辑删除标识（0：未删除；1：已删除）',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '123456', NULL, '1008611@qq.com', '10086', 0, 0, '2024-07-07 13:14:52', NULL, 0);
INSERT INTO `t_user` VALUES (2, '小花', '123456', NULL, '1000012@qq.com', '10000' ,1, 1, '2024-07-07 13:14:53', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
