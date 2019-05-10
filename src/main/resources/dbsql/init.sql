
INSERT INTO `organization` (`id`,`org_name`,`level`,`parent`,`path`,`isdelete`,`create_time`,`update_time`) VALUES (1,'root',0,NULL,NULL,0,NULL,NULL);

INSERT INTO `role` (`id`,`role`,`description`,`create_time`,`update_time`,`isdelete`) VALUES (1,'superadmin','超级系统管理员','2018-06-05 13:07:11','2018-06-05 13:07:11',0);
INSERT INTO `role` (`id`,`role`,`description`,`create_time`,`update_time`,`isdelete`) VALUES (2,'admin','系统管理员','2018-04-09 19:35:11','2018-04-09 19:35:13',0);
INSERT INTO `role` (`id`,`role`,`description`,`create_time`,`update_time`,`isdelete`) VALUES (3,'producer','内容管理员','2018-06-05 13:07:11','2018-06-05 13:07:11',0);
INSERT INTO `role` (`id`,`role`,`description`,`create_time`,`update_time`,`isdelete`) VALUES (4,'auditor','审核管理员','2018-06-05 13:07:11','2018-06-05 13:07:11',0);

INSERT INTO `user` (`id`,`username`,`name`,`password`,`salt`,`state`,`email`,`create_time`,`update_time`,`organization_id`,`isdeleted`) VALUES (1,'supadmin','超级系统管理员','123456',NULL,1,'','2018-06-07 11:02:48',NULL,1,0);

INSERT INTO `user_role` (`id`, `uid`, `role_id`) VALUES (1, 1, 1);




INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (1, '权限1', 'menu', NULL, 'allprogram:*', 0, 0, 'am-icon-table', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (2, '权限2', 'menu', '#/resource/resource', 'resource:*', 1, 0, 'am-icon-folder-open-o', '2018-8-10 14:23:07', '2018-8-10 14:23:07');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (3, '权限3', 'menu', '#/program/program', 'program:edit', 1, 0, 'am-icon-pencil', '2018-8-10 14:23:07', '2018-9-12 09:57:52');
INSERT INTO `permission` (`id`, `name`, `resource_type`, `url`, `permission_string`, `parent_id`, `isdelete`, `logo`, `create_time`, `update_time`) VALUES (4, '权限4', 'menu', '#/program/programInfo', 'program:view', 1, 0, 'am-icon-list', '2018-8-10 14:23:07', '2018-8-10 14:23:07');



INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (1,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (2,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (3,1,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (4,1,0);

INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (1,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (2,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (2,2,0);
INSERT INTO `role_permission` (`permission_id`,`role_id`,`isdelete`) VALUES (4,2,0);
