/*
Navicat MySQL Data Transfer

Source Server         :
Source Server Version : 50721
Source Host           :
Source Database       :

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-11-23 18:30:35
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `organization`
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id`  int(20) NOT NULL AUTO_INCREMENT ,
  `org_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名' ,
  `level`  int(10) NULL DEFAULT NULL COMMENT '机构级别' ,
  `parent`  int(20) NULL DEFAULT NULL COMMENT '机构父层级' ,
  `path`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '层级的全路径' ,
  `isdelete`  int(10) NULL DEFAULT 0 COMMENT '标记删除' ,
  `create_time`  timestamp NULL DEFAULT NULL ,
  `update_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT ,
  `name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
  `resource_type`  enum('menu','button') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `url`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `permission_string`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '**权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view' ,
  `parent_id`  bigint(20) NULL DEFAULT NULL COMMENT '父编号' ,
  `isdelete`  int(11) NULL DEFAULT 0 COMMENT '0未删除/1已删除' ,
  `logo`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图片的样式类' ,
  `create_time`  timestamp NULL DEFAULT NULL ,
  `update_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;
-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT ,
  `role`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
  `description`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
  `isdelete`  int(11) NULL DEFAULT 0 COMMENT '0未删除/1已删除' ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT ,
  `permission_id`  bigint(20) NOT NULL ,
  `role_id`  bigint(20) NOT NULL ,
  `isdelete`  int(20) NULL DEFAULT 0 COMMENT '0未删除/1已删除' ,
  `create_time`  timestamp NULL DEFAULT NULL ,
  `update_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`),
  INDEX `permissionId` (`permission_id`) USING BTREE ,
  INDEX `roleId` (`role_id`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`  bigint(11) NOT NULL AUTO_INCREMENT ,
  `username`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
  `name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
  `password`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
  `salt`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `state`  int(8) UNSIGNED NOT NULL ,
  `email`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `organization_id`  bigint(20) NULL DEFAULT NULL COMMENT '用户对应的机构id' ,
  `isdeleted`  int(20) NULL DEFAULT 0 COMMENT '0未删除/1已删除' ,
  `create_time`  timestamp NULL DEFAULT NULL ,
  `update_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT ,
  `uid`  bigint(20) NOT NULL ,
  `role_id`  bigint(20) NOT NULL ,
  `create_time`  timestamp NULL DEFAULT NULL ,
  `update_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  INDEX `uid` (`uid`) USING BTREE ,
  INDEX `roleId` (`role_id`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;