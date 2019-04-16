
INSERT INTO `organization` (`id`,`org_name`,`level`,`parent`,`path`,`isdelete`,`create_time`,`update_time`) VALUES (1,'root',0,NULL,NULL,0,NULL,NULL);

INSERT INTO `role` (`id`,`role`,`description`,`create_time`,`update_time`,`isdelete`) VALUES (1,'superadmin','超级系统管理员','2018-06-05 13:07:11','2018-06-05 13:07:11',0);
INSERT INTO `role` (`id`,`role`,`description`,`create_time`,`update_time`,`isdelete`) VALUES (2,'admin','系统管理员','2018-04-09 19:35:11','2018-04-09 19:35:13',0);
INSERT INTO `role` (`id`,`role`,`description`,`create_time`,`update_time`,`isdelete`) VALUES (3,'producer','内容管理员','2018-06-05 13:07:11','2018-06-05 13:07:11',0);
INSERT INTO `role` (`id`,`role`,`description`,`create_time`,`update_time`,`isdelete`) VALUES (4,'auditor','审核管理员','2018-06-05 13:07:11','2018-06-05 13:07:11',0);

INSERT INTO `user` (`id`,`username`,`name`,`password`,`salt`,`state`,`email`,`create_time`,`update_time`,`organization_id`,`isdeleted`) VALUES (1,'supadmin','超级系统管理员','123456',NULL,1,'','2018-06-07 11:02:48',NULL,1,0);

INSERT INTO `user_role` (`id`, `uid`, `role_id`) VALUES (1, 1, 1);




INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (1, '内容管理', 'menu', NULL, 'allprogram:*', 0, 0, 'am-icon-table', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (2, '素材管理', 'menu', '#/resource/resource', 'resource:*', 1, 0, 'am-icon-folder-open-o', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (3, '节目制作', 'menu', '#/program/program', 'program:edit', 1, 0, 'am-icon-pencil', '2018-8-10 14:23:07', '2018-9-12 09:57:52');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (4, '节目信息', 'menu', '#/program/programInfo', 'program:view', 1, 0, 'am-icon-list', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (5, '计划信息', 'menu', '#/playlist/planInfo', 'playlist:*', 1, 0, 'am-icon-calendar', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (6, '计划审核', 'menu', '#/playlist/planCheck', 'program:audit', 1, 0, 'am-icon-calendar-check-o', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (8, '应用市场', 'menu', '#/mall/appStore', 'applet:view', 0, 0, 'am-icon-shopping-bag', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (9, '终端管理', 'menu', '#/device/deviceInfo', 'device:*', 0, 0, 'am-icon-desktop', '2018-8-10 14:23:07', '2018-8-15 10:53:42');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (10, '模板管理', 'menu', '#/mall/templateStore', 'allprogram:*', 0, 0, 'am-icon-th-large', '2018-8-10 14:23:07', '2018-10-17 17:41:49');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (11, '应用管理', 'menu', '#/system/applet', NULL, 0, 0, 'am-icon-cube', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (12, '系统管理', 'menu', NULL, 'system:*', 0, 0, 'am-icon-cog', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (13, '用户管理', 'menu', '#/system/user', 'user:*', 12, 0, 'am-icon-user-plus', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (14, '角色管理', 'menu', '#/system/role', 'role:*', 12, 0, 'am-icon-male', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (15, '机构管理', 'menu', '#/system/organization', 'org:*', 12, 0, 'am-icon-building', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (16, '升级管理', 'menu', '#/system/apks', NULL, 12, 0, 'am-icon-android', '2018-8-10 14:23:07', '2018-8-31 14:01:08');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (17, '接口日志', 'menu', '#/system/interfaceLogs', 'syslog:*', 12, 0, 'am-icon-file', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (18, '系统日志', 'menu', '#/system/logs', 'syslog:*', 12, 0, 'am-icon-file', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (19, '标签管理', 'menu', '#/system/tags', 'tags:*', 12, 0, 'am-icon-tags', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (20, '订单管理', 'menu', '#mall/orders', 'order:*', 0, 0, 'am-icon-file-text-o', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (21, '命令日志', 'menu', '#system/cmdLogs', 'syslog:*', 12, 0, 'am-icon-file', '2018-10-25 11:27:13', '2018-10-25 11:27:13');



INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (1,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (2,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (3,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (4,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (5,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (6,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (7,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (8,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (9,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (10,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (11,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (12,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (13,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (14,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (15,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (16,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (17,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (18,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (19,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (20,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (21,1,0);

INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (1,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (2,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (2,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (4,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (5,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (9,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (10,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (11,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (12,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (13,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (14,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (15,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (16,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (18,2,0);
