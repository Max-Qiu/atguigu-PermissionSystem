
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `permissions_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `permissions_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_log_login` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户账号',
  `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录IP',
  `status` tinyint unsigned DEFAULT '0' COMMENT '登录状态 0成功 1失败',
  `msg` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '提示信息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_log_operation` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模块标题',
  `business_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法名称',
  `request_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式',
  `operator_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人员',
  `oper_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求URL',
  `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主机地址',
  `oper_param` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求参数',
  `json_result` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态 0正常 1异常',
  `error_msg` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '错误消息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int unsigned NOT NULL COMMENT '所属上级',
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '类型 0:目录 1:菜单 2:按钮',
  `path` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `component` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `perms` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
  `sort_value` int unsigned DEFAULT NULL COMMENT '排序',
  `enable` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用 0:否 1:是',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0:否 1:是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_menu` VALUES (2,0,'系统管理',0,'system','Layout',NULL,'el-icon-s-tools',1,1,0,'2021-05-31 18:05:37','2022-06-09 09:23:24');
INSERT INTO `sys_menu` VALUES (3,2,'用户管理',1,'sysUser','system/sysUser/list',NULL,'el-icon-s-custom',1,1,0,'2021-05-31 18:05:37','2022-11-21 14:48:43');
INSERT INTO `sys_menu` VALUES (4,2,'角色管理',1,'sysRole','system/sysRole/list',NULL,'el-icon-user-solid',2,1,0,'2021-05-31 18:05:37','2022-11-21 14:48:43');
INSERT INTO `sys_menu` VALUES (5,2,'菜单管理',1,'sysMenu','system/sysMenu/list',NULL,'el-icon-s-unfold',3,1,0,'2021-05-31 18:05:37','2022-11-21 14:48:43');
INSERT INTO `sys_menu` VALUES (6,3,'查看',2,NULL,NULL,'bnt.sysUser.list',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (7,3,'添加',2,NULL,NULL,'bnt.sysUser.add',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (8,3,'修改',2,NULL,NULL,'bnt.sysUser.update',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (9,3,'删除',2,NULL,NULL,'bnt.sysUser.remove',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (10,4,'查看',2,NULL,NULL,'bnt.sysRole.list',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (11,4,'添加',2,NULL,NULL,'bnt.sysRole.add',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (12,4,'修改',2,NULL,NULL,'bnt.sysRole.update',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (13,4,'删除',2,NULL,NULL,'bnt.sysRole.remove',NULL,1,1,0,'2021-05-31 18:05:37','2022-11-22 11:31:45');
INSERT INTO `sys_menu` VALUES (14,5,'查看',2,NULL,NULL,'bnt.sysMenu.list',NULL,1,1,0,'2021-05-31 18:05:37','2022-11-22 11:31:45');
INSERT INTO `sys_menu` VALUES (15,5,'添加',2,NULL,NULL,'bnt.sysMenu.add',NULL,1,1,0,'2021-05-31 18:05:37','2022-11-22 11:31:45');
INSERT INTO `sys_menu` VALUES (16,5,'修改',2,NULL,NULL,'bnt.sysMenu.update',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (17,5,'删除',2,NULL,NULL,'bnt.sysMenu.remove',NULL,1,1,0,'2021-05-31 18:05:37','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (18,3,'分配角色',2,NULL,NULL,'bnt.sysUser.assignRole',NULL,1,1,0,'2022-05-23 17:14:32','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (19,4,'分配权限',2,'assignAuth','system/sysRole/assignAuth','bnt.sysRole.assignAuth',NULL,1,1,0,'2022-05-23 17:18:14','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (30,34,'操作日志',1,'sysOperLog','system/sysOperLog/list',NULL,'el-icon-document-remove',7,1,0,'2022-05-26 16:09:59','2022-11-21 14:48:49');
INSERT INTO `sys_menu` VALUES (31,30,'查看',2,NULL,NULL,'bnt.sysOperLog.list',NULL,1,1,0,'2022-05-26 16:10:17','2022-06-09 09:22:38');
INSERT INTO `sys_menu` VALUES (32,34,'登录日志',1,'sysLoginLog','system/sysLoginLog/list',NULL,'el-icon-s-goods',8,1,0,'2022-05-26 16:36:13','2022-11-21 14:48:50');
INSERT INTO `sys_menu` VALUES (33,32,'查看',2,NULL,NULL,'bnt.sysLoginLog.list',NULL,1,1,0,'2022-05-26 16:36:31','2022-06-09 09:36:36');
INSERT INTO `sys_menu` VALUES (34,2,'日志管理',0,'log','ParentView',NULL,'el-icon-tickets',6,1,0,'2022-05-31 13:23:07','2022-11-21 14:48:52');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0:否 1:是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_role` VALUES (1,'系统管理员','SYSTEM',0,'2021-05-31 18:09:18','2022-11-23 17:46:18');
INSERT INTO `sys_role` VALUES (2,'普通管理员','COMMON',0,'2021-06-01 08:38:40','2022-11-23 17:46:18');
INSERT INTO `sys_role` VALUES (3,'用户管理员','yhgly',0,'2022-06-08 17:39:04','2022-11-23 17:46:18');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int unsigned NOT NULL COMMENT '角色ID',
  `menu_id` int unsigned NOT NULL COMMENT '菜单ID',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0:否 1:是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色与菜单';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机',
  `head_url` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像地址',
  `enable` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用 0:否 1:是',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0:否 1:是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$IuvsoQkidW7CCpvPSaWYB.S2UtClcGsu/VVJ/vJGVbn9iDeHoaGrC','admin','15099909888',NULL,1,0,'2021-05-31 18:08:43','2022-11-23 23:23:08');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int unsigned NOT NULL COMMENT '用户ID',
  `role_id` int unsigned NOT NULL COMMENT '角色ID',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0:否 1:是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_admin_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户与角色';
/*!40101 SET character_set_client = @saved_cs_client */;
