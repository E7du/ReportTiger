SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for report_tiger_app_secret_keys
-- ----------------------------
DROP TABLE IF EXISTS `report_tiger_app_secret_keys`;
CREATE TABLE `report_tiger_app_secret_keys` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `package_name` varchar(50) NOT NULL COMMENT 'ios bundleID,Android包名,也就是bundle的原始数据',
  `bundle` varchar(32) NOT NULL COMMENT 'ios bundle,Android package name md5',
  `appkey` varchar(32) NOT NULL COMMENT 'appkey',
  `secretkey` varchar(32) NOT NULL COMMENT '安全密钥key',
  `secretkeysalt` varchar(32) NOT NULL COMMENT '安全密钥key的salt',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bundle` (`bundle`),
  UNIQUE KEY `package_name` (`package_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端授权';

-- ----------------------------
-- Records of report_tiger_app_secret_keys
-- ----------------------------

-- ----------------------------
-- Table structure for report_tiger_device_token
-- ----------------------------
DROP TABLE IF EXISTS `report_tiger_device_token`;
CREATE TABLE `report_tiger_device_token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '索引',
  `uuid` varchar(32) NOT NULL COMMENT '设备uuid的md5',
  `token` varchar(32) NOT NULL COMMENT '授权token',
  `timeout` bigint(10) unsigned NOT NULL COMMENT 'token过期时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`,`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备授权token记录';

-- ----------------------------
-- Records of report_tiger_device_token
-- ----------------------------

-- ----------------------------
-- Table structure for report_tiger_mapping_info
-- ----------------------------
DROP TABLE IF EXISTS `report_tiger_mapping_info`;
CREATE TABLE `report_tiger_mapping_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '索引(Java中使用BigInteger）',
  `appid` bigint(20) unsigned DEFAULT NULL COMMENT 'stats_app_secret_keys的id',
  `code` varchar(20) NOT NULL COMMENT '助记码,表名',
  `intro` varchar(50) NOT NULL COMMENT '描述',
  `cdate` bigint(10) unsigned NOT NULL COMMENT '创建时间create date(Java中使用BigInteger)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='统计信息关系映射关系，记录每一个统计项与具体表的关联关系';

-- ----------------------------
-- Records of report_tiger_mapping_info
-- ----------------------------
