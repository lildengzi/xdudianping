/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3307_local
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3307
 Source Schema         : dianping

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 24/02/2024 17:14:22
*/
CREATE DATABASE  IF NOT EXISTS `dianping` ;
USE `dianping`;
use dianping;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_dish
-- ----------------------------
DROP TABLE IF EXISTS `biz_dish`;
CREATE TABLE `biz_dish`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `store_id` bigint(0) NULL DEFAULT NULL COMMENT '店铺id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品图片',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '菜品描述',
  `ad_recommend` int(0) NULL DEFAULT 0 COMMENT '广告推荐',
  `view_size` int(0) NULL DEFAULT 0 COMMENT '点击数',
  `comment_size` int(0) NULL DEFAULT NULL COMMENT '评价数',
  `score` decimal(10, 2) NULL DEFAULT NULL COMMENT '评分数',
  `price` int(0) NULL DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_dish
-- ----------------------------
INSERT INTO `biz_dish` VALUES (1, 1, '黄骨鱼粉', '/upload/655ef3fe45014de1a44b1e078f8d1f76.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>黄骨鱼粉xxxxxxx</p>\n<p>xxxxxxx</p>\n<p>xxxx</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 1, 12, 1, 3.00, 48);
INSERT INTO `biz_dish` VALUES (2, 1, '酸菜鱼炖粉', '/upload/d77c8aaf3aa945b2b0c9f4ee3b5e90ba.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>酸菜鱼炖粉xxxxxx</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 1, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (3, 1, '香辣鱼粉', '/upload/2c488b2442044b1186d6ff20212739cd.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>香辣鱼粉</p>\n<p>香辣鱼粉</p>\n<p>香辣鱼粉</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 0, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (4, 1, '香辣鱼头粉', '/upload/b74ed3d7c1484ff3973f34790646f02c.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>香辣鱼头粉</p>\n<p>香辣鱼头粉</p>\n<p>香辣鱼头粉</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 1, 6, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (5, 1, '鱼杂粉', '/upload/1b41a638555248c9886214c703bfc13a.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>鱼杂粉</p>\n<p>鱼杂粉</p>\n<p>鱼杂粉</p>\n<p>鱼杂粉</p>\n<p>鱼杂粉</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 1, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (6, 2, '鸡公煲', '/upload/bdfc2ab8af7246b5994bb8aea19bc5e9.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>鸡公煲</p>\n<p>鸡公煲</p>\n<p>鸡公煲</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 0, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (7, 2, '牛肉煲', '/upload/6198766a3cc840f1975eac5b3a15597f.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>牛肉煲</p>\n<p>牛肉煲</p>\n<p>牛肉煲</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 1, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (8, 2, '牛蛙煲', '/upload/1547b46a4f184ae988c65376638a44fe.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>牛蛙煲</p>\n<p>牛蛙煲</p>\n<p>牛蛙煲</p>\n<p>牛蛙煲</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 0, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (9, 2, '虾煲', '/upload/4f31a0e36b6b4b888dccd60d311d24a7.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>虾煲</p>\n<p>虾煲</p>\n<p>虾煲</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 1, 2, 2, 1.00, 48);
INSERT INTO `biz_dish` VALUES (10, 3, '红烧牛仔骨', '/upload/ce4e034420504c8b8d63f01bb7b2bc3c.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>红烧牛仔骨</p>\n<p>红烧牛仔骨</p>\n<p>红烧牛仔骨</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 1, 3, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (11, 3, '三杯鸡', '/upload/431b32917e924f29a15ba07b60e462d2.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>三杯鸡</p>\n<p>三杯鸡</p>\n<p>三杯鸡</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 1, 29, 5, 3.80, 48);
INSERT INTO `biz_dish` VALUES (12, 3, '蒜蓉虾', '/upload/5b3d0311905d46fb99789298251f425c.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>蒜蓉虾</p>\n<p>蒜蓉虾</p>\n<p>蒜蓉虾</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 1, 3, 2, 4.00, 48);
INSERT INTO `biz_dish` VALUES (13, 3, '糖醋排骨', '/upload/17171a0b986f4a1aa1e4814d8aefb2be.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>糖醋排骨</p>\n<p>糖醋排骨</p>\n<p>糖醋排骨</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 1, 11, 1, 5.00, 48);
INSERT INTO `biz_dish` VALUES (14, 3, '铁板牛肉', '/upload/4fef1c0f1c824858a9fa5b1b1d47a746.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>铁板牛肉</p>\n<p>铁板牛肉</p>\n<p>铁板牛肉</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 0, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (15, 4, '荷包蛋炒肉', '/upload/c38e14668c9c48948f6b353b3ea1e0b4.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>荷包蛋炒肉</p>\n<p>荷包蛋炒肉</p>\n<p>荷包蛋炒肉</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 0, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (17, 4, '茄子炒蛋', '/upload/cc75be2c35734dc5a5a1d9e86d656464.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>茄子炒蛋</p>\n<p>茄子炒蛋</p>\n<p>茄子炒蛋</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 0, 0, 0.00, 48);
INSERT INTO `biz_dish` VALUES (18, 1, '水煮肉片', '/upload/ba5e65ea029e4ad49254564ea299939d.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>水煮肉片</p>\n<p>水煮肉片</p>\n<p>水煮肉片</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 1, 2, 1, 4.00, 48);
INSERT INTO `biz_dish` VALUES (19, 19, '雪花牛排', '/upload/c023c2a6a4474c118df37602bcb8356d.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>雪花牛排</p>\n<p>雪花牛排</p>\n<p>雪花牛排</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 3, 1, 5.00, 48);
INSERT INTO `biz_dish` VALUES (20, 20, '巴蜀麻辣香锅', '/upload/f34de755dcbd4b04ab5ca30bd384a3cf.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>巴蜀麻辣香锅</p>\n<p>巴蜀麻辣香锅</p>\n<p>巴蜀麻辣香锅</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 0, 1, 4.00, 48);
INSERT INTO `biz_dish` VALUES (21, 20, '酱香芝麻香锅', '/upload/1ed672b9498a4e1a9e8913fe01af54b5.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>酱香芝麻香锅</p>\n<p>酱香芝麻香锅</p>\n<p>酱香芝麻香锅</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 3, 3, 4.50, 48);
INSERT INTO `biz_dish` VALUES (22, 20, '素香锅', '/upload/678e2fe0ea464ff28ab886743b45f6b9.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>素香锅</p>\n<p>素香锅</p>\n<p>素香锅</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', 0, 22, 3, 4.50, 48);

-- ----------------------------
-- Table structure for biz_dish_comment
-- ----------------------------
DROP TABLE IF EXISTS `biz_dish_comment`;
CREATE TABLE `biz_dish_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `dish_id` bigint(0) NULL DEFAULT NULL COMMENT '菜品id',
  `store_id` bigint(0) NULL DEFAULT NULL COMMENT '店铺id',
  `score` int(0) NULL DEFAULT NULL COMMENT '菜品评分',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论描述',
  `pid` bigint(0) NULL DEFAULT 0 COMMENT '父评论id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_dish_comment
-- ----------------------------
INSERT INTO `biz_dish_comment` VALUES (1, 40, 11, 3, 5, '最爱麻吉的三杯鸡，耶！！！', 0, '2024-03-05 18:05:14', 1);
INSERT INTO `biz_dish_comment` VALUES (2, 40, 12, 3, 4, '味道还可以！', 0, '2024-03-05 18:05:41', 1);
INSERT INTO `biz_dish_comment` VALUES (3, 40, 9, 2, 1, '很难吃！！！', 0, '2024-03-05 18:06:30', 2);
INSERT INTO `biz_dish_comment` VALUES (4, 40, 1, 1, 3, '一般般！！', 0, '2024-03-05 18:06:41', 1);
INSERT INTO `biz_dish_comment` VALUES (5, 40, 22, 20, 5, '很好吃！！！', 0, '2024-03-05 18:48:19', 2);
INSERT INTO `biz_dish_comment` VALUES (6, 40, 21, 20, 4, '非常不错！！', 0, '2024-03-05 18:48:28', 2);
INSERT INTO `biz_dish_comment` VALUES (7, 40, 20, 20, 4, '挺好吃的！', 0, '2024-03-05 18:48:39', 1);
INSERT INTO `biz_dish_comment` VALUES (8, 1, 22, 20, 4, '很好', 0, '2024-03-05 18:56:22', 1);
INSERT INTO `biz_dish_comment` VALUES (9, 1, 21, 20, 5, '不错', 0, '2024-03-05 18:56:35', 1);
INSERT INTO `biz_dish_comment` VALUES (10, 40, 19, 19, 5, '好吃！！', 0, '2024-03-05 19:02:44', 1);
INSERT INTO `biz_dish_comment` VALUES (11, 40, 18, 1, 4, '美味！！', 0, '2024-03-05 19:02:52', 1);
INSERT INTO `biz_dish_comment` VALUES (12, 40, 13, 3, 5, '非常好吃', 0, '2024-03-05 19:03:05', 1);
INSERT INTO `biz_dish_comment` VALUES (13, 40, 11, 3, 4, '好吃不贵', 0, '2024-03-05 19:03:14', 1);
INSERT INTO `biz_dish_comment` VALUES (14, 40, 12, 3, 4, '很好吃！', 0, '2024-03-05 19:03:27', 1);
INSERT INTO `biz_dish_comment` VALUES (15, 1, 22, 20, NULL, '欢迎再来！', 5, '2024-03-05 19:21:03', 1);
INSERT INTO `biz_dish_comment` VALUES (16, 1, 9, 2, NULL, '我们一定改进', 3, '2024-03-05 19:21:56', 1);
INSERT INTO `biz_dish_comment` VALUES (17, 1, 21, 20, NULL, '谢谢！', 6, '2024-03-05 19:22:18', 1);
INSERT INTO `biz_dish_comment` VALUES (18, 41, 5, 1, 4, '还不错', 0, '2024-03-05 19:43:59', 0);
INSERT INTO `biz_dish_comment` VALUES (19, 41, 4, 1, 4, '挺好吃', 0, '2024-03-05 19:44:08', 0);
INSERT INTO `biz_dish_comment` VALUES (20, 40, 11, 3, 3, '哈哈！！', 0, '2024-03-05 19:57:11', 0);
INSERT INTO `biz_dish_comment` VALUES (21, 40, 11, 3, 4, '22222221', 0, '2023-05-04 21:02:58', 1);
INSERT INTO `biz_dish_comment` VALUES (22, 40, 11, 3, 3, '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>222</p>\n</body>\n</html>', 0, '2024-02-18 15:58:47', 0);
INSERT INTO `biz_dish_comment` VALUES (23, 40, 11, 3, 3, '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>222222222222</p>\n</body>\n</html>', 0, '2024-02-18 15:59:16', 1);
INSERT INTO `biz_dish_comment` VALUES (24, 40, 11, 3, 3, '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p><img src=\"http://localhost:8080/upload/d14686580f244cd48cb164db3bba054a.png\" alt=\"\" width=\"760\" height=\"300\" /></p>\n<p>11111111112222</p>\n</body>\n</html>', 0, '2024-02-18 16:02:22', 1);

-- ----------------------------
-- Table structure for biz_dish_notice
-- ----------------------------
DROP TABLE IF EXISTS `biz_dish_notice`;
CREATE TABLE `biz_dish_notice`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `store_id` bigint(0) NULL DEFAULT NULL COMMENT '店铺id',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品图片',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '菜品描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_dish_notice
-- ----------------------------
INSERT INTO `biz_dish_notice` VALUES (1, 1, '特价菜品', '特价茄子炒蛋', '/upload/c33aac9f0cd0408a9921eb9166882d24.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>特价茄子炒蛋</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', '2024-03-05 17:54:19');
INSERT INTO `biz_dish_notice` VALUES (2, 1, '优惠套餐', '优惠套餐荷包蛋炒肉', '/upload/993b06e960d04e64b0179aaf63f4a91d.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>优惠套餐荷包蛋炒肉</p>\n<p>优惠套餐荷包蛋炒肉</p>\n<p>优惠套餐荷包蛋炒肉</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', '2024-03-05 17:54:51');
INSERT INTO `biz_dish_notice` VALUES (3, 20, '优惠套餐', '自选套餐三荤两素', '/upload/96b41a98f941450bb7ccd8d7a256aada.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>自选套餐三荤两素</p>\n<p>自选套餐三荤两素</p>\n<p>自选套餐三荤两素</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', '2024-03-05 18:02:23');
INSERT INTO `biz_dish_notice` VALUES (4, 20, '优惠套餐', '自选套餐四荤三素', '/upload/9a02832f5c2a482c944460b1c6c2847a.png', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>自选套餐四荤三素</p>\n<p>自选套餐四荤三素</p>\n<p>自选套餐四荤三素</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', '2024-03-05 18:02:44');

-- ----------------------------
-- Table structure for biz_mark
-- ----------------------------
DROP TABLE IF EXISTS `biz_mark`;
CREATE TABLE `biz_mark`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `main_type` int(0) NULL DEFAULT NULL COMMENT '业务类型：1店铺，2菜品，3笔记',
  `main_id` bigint(0) NULL DEFAULT NULL COMMENT '业务id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `main_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收藏的名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_mark，
-- ----------------------------
INSERT INTO `biz_mark` VALUES (1, 1, 2, 19, '2024-03-05 17:57:40', '雪花牛排');
INSERT INTO `biz_mark` VALUES (2, 1, 1, 20, '2024-03-05 18:00:53', '等等香锅');
INSERT INTO `biz_mark` VALUES (3, 40, 1, 1, '2024-03-05 18:04:03', '三和鱼粉');
INSERT INTO `biz_mark` VALUES (4, 40, 1, 2, '2024-03-05 18:04:10', '炒鸡好吃煲');
INSERT INTO `biz_mark` VALUES (5, 40, 1, 3, '2024-03-05 18:04:34', '台湾麻吉');
INSERT INTO `biz_mark` VALUES (6, 41, 1, 16, '2024-03-05 18:07:43', '汉堡王');
INSERT INTO `biz_mark` VALUES (7, 41, 1, 11, '2024-03-05 18:07:56', '重庆小面');
INSERT INTO `biz_mark` VALUES (8, 40, 1, 14, '2024-03-05 18:43:27', '肯德基');
INSERT INTO `biz_mark` VALUES (9, 40, 2, 22, '2024-03-05 18:48:14', '素香锅');
INSERT INTO `biz_mark` VALUES (10, 40, 1, 20, '2024-03-05 18:50:32', '等等香锅');
INSERT INTO `biz_mark` VALUES (11, 40, 1, 16, '2024-03-05 19:58:29', '汉堡王');
INSERT INTO `biz_mark` VALUES (16, 1, 3, 2, '2024-02-20 16:52:24', '台湾麻吉很好吃');
INSERT INTO `biz_mark` VALUES (17, 41, 3, 3, '2024-02-24 16:53:48', '笔记测试1111111111');

-- ----------------------------
-- Table structure for biz_note
-- ----------------------------
DROP TABLE IF EXISTS `biz_note`;
CREATE TABLE `biz_note`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `like_size` int(0) NULL DEFAULT NULL COMMENT '点赞数',
  `comment_size` int(0) NULL DEFAULT NULL COMMENT '评论数',
  `mark_size` int(0) NULL DEFAULT NULL COMMENT '收藏数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_note
-- ----------------------------
INSERT INTO `biz_note` VALUES (1, 40, '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>台湾麻吉很好吃</p>\n<p>必点 糖醋排骨、三杯鸡</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', '2024-03-05 18:44:15', '台湾麻吉很好吃', 1, 1, 1);
INSERT INTO `biz_note` VALUES (2, 41, '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>台湾麻吉很好吃</p>\n<p>必点 糖醋排骨、三杯鸡</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>\n</body>\n</html>', '2024-03-05 18:44:15', '台湾麻吉很好吃啊2', 1, 11, 1);
INSERT INTO `biz_note` VALUES (3, 41, '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p>笔记测试1111111111</p>\n<p>笔记测试1111111111</p>\n<p>笔记测试1111111111</p>\n</body>\n</html>', '2024-02-20 20:31:00', '笔记测试1111111111', 0, 1, 1);

-- ----------------------------
-- Table structure for biz_note_comment
-- ----------------------------
DROP TABLE IF EXISTS `biz_note_comment`;
CREATE TABLE `biz_note_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `note_id` bigint(0) NULL DEFAULT NULL COMMENT '店铺id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论描述',
  `pid` bigint(0) NULL DEFAULT 0 COMMENT '父评论id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_note_comment
-- ----------------------------
INSERT INTO `biz_note_comment` VALUES (25, 1, 2, '222222222222', 0, '2024-02-19 21:29:49');
INSERT INTO `biz_note_comment` VALUES (26, 1, 2, '33333333333', 0, '2024-02-19 21:36:29');
INSERT INTO `biz_note_comment` VALUES (27, 1, 2, '22222222', 26, '2024-02-19 22:11:27');
INSERT INTO `biz_note_comment` VALUES (29, 1, 2, '333333333', 0, '2024-02-19 22:21:42');
INSERT INTO `biz_note_comment` VALUES (30, 1, 2, '32111', 0, '2024-02-19 22:23:38');
INSERT INTO `biz_note_comment` VALUES (31, 1, 2, '444444', 30, '2024-02-19 22:23:43');
INSERT INTO `biz_note_comment` VALUES (32, 1, 2, '4323', 30, '2024-02-19 22:23:47');
INSERT INTO `biz_note_comment` VALUES (33, 1, 2, '1111', 30, '2024-02-19 22:23:57');
INSERT INTO `biz_note_comment` VALUES (34, 1, 2, '111', 30, '2024-02-19 22:24:01');
INSERT INTO `biz_note_comment` VALUES (35, 1, 2, '333333333', 29, '2024-02-19 22:24:41');
INSERT INTO `biz_note_comment` VALUES (36, 41, 2, '22222', 0, '2024-02-24 16:53:27');
INSERT INTO `biz_note_comment` VALUES (37, 41, 3, '22222', 0, '2024-02-24 16:53:42');
INSERT INTO `biz_note_comment` VALUES (38, 41, 1, '啊啊啊', 0, '2024-02-24 17:04:37');

-- ----------------------------
-- Table structure for biz_note_like
-- ----------------------------
DROP TABLE IF EXISTS `biz_note_like`;
CREATE TABLE `biz_note_like`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `note_id` bigint(0) NULL DEFAULT NULL COMMENT '店铺id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '笔记点赞记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_note_like
-- ----------------------------
INSERT INTO `biz_note_like` VALUES (32, 1, 2, '2024-02-20 16:47:17');

-- ----------------------------
-- Table structure for biz_store
-- ----------------------------
DROP TABLE IF EXISTS `biz_store`;
CREATE TABLE `biz_store`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺图片',
  `business_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `business_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人电话',
  `business_user_id` bigint(0) NULL DEFAULT NULL COMMENT '负责人的账号id',
  `ad_recommend` int(0) NULL DEFAULT 0 COMMENT '营销推荐，1推荐，0不推荐',
  `view_size` int(0) NULL DEFAULT NULL COMMENT '点击数',
  `comment_size` int(0) NULL DEFAULT NULL COMMENT '评价数',
  `score` decimal(10, 2) NULL DEFAULT NULL COMMENT '分数',
  `environment_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '环境分数',
  `taste_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '口味分数',
  `service_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '服务分数',
  `address` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `avg_price` int(0) NULL DEFAULT NULL COMMENT '人均消费金额',
  `canteen_id` bigint(20) NOT NULL COMMENT '所属食堂ID',
  `floor_number` int(11) DEFAULT '1' COMMENT '所在楼层',
  `window_number` varchar(50) DEFAULT NULL COMMENT '窗口编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_store
-- ----------------------------
INSERT INTO `biz_store` VALUES (1, '海棠食堂档口1', '/upload/dangkou/dangkou1.jpg', '李朋朋', '13991396973', 11, 1, 135, 2, 5.00, 4.00, 4.00, 5.00, '海棠餐厅一楼', 46, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (2, '海棠食堂档口2', '/upload/dangkou/dangkou2.jpg', '李朋朋', '13991396973', 12, 1, 24, 2, 5.00, 5.00, 5.00, 5.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (3, '海棠食堂档口3', '/upload/dangkou/dangkou3.jpg', '李朋朋', '13991396973', 13, 1, 47, 6, 4.00, 4.50, 3.75, 4.25, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (4, '海棠食堂档口4', '/upload/dangkou/dangkou4.jpg', '李朋朋', '13991396973', 14, 0, 41, 0, 0.00, 0.00, 0.00, 0.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (5, '海棠食堂档口5', '/upload/dangkou/dangkou5.jpg', '李朋朋', '13991396973', 15, 0, 50, 0, 0.00, 0.00, 0.00, 0.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (6, '海棠食堂档口6', '/upload/dangkou/dangkou6.jpg', '李朋朋', '13991396973', 16, 1, 64, 0, 0.00, 0.00, 0.00, 0.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (7, '海棠食堂档口7', '/upload/dangkou/dangkou7.jpg', '李朋朋', '13991396973', 17, 0, 70, 0, 0.00, 0.00, 0.00, 0.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (8, '海棠食堂档口8', '/upload/dangkou/dangkou8.jpg', '李朋朋', '13991396973', 18, 0, 80, 0, 0.00, 0.00, 0.00, 0.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (9, '海棠食堂档口9', '/upload/dangkou/dangkou9.jpg', '李朋朋', '13991396973', 19, 0, 90, 0, 0.00, 0.00, 0.00, 0.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (10, '海棠食堂档口10', '/upload/dangkou/dangkou10.jpg', '李朋朋', '13991396973', 20, 0, 103, 0, 0.00, 0.00, 0.00, 0.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (11, '海棠食堂档口11', '/upload/dangkou/dangkou11.jpg', '李朋朋', '13991396973', 21, 0, 111, 1, 4.00, 5.00, 5.00, 4.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (12, '海棠食堂档口12', '/upload/dangkou/dangkou12.jpg', '李朋朋', '13991396973', 22, 0, 20, 1, 3.00, 2.00, 3.00, 2.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (13, '海棠食堂档口13', '/upload/dangkou/dangkou13.jpg', '李朋朋', '13991396973', 23, 0, 30, 0, 0.00, 0.00, 0.00, 0.00, '海棠餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (14, '海棠食堂档口14', '/upload/dangkou/dangkou14.jpg', '李朋朋', '13991396973', 24, 1, 47, 1, 5.00, 5.00, 4.00, 4.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (15, '海棠食堂档口15', '/upload/dangkou/dangkou15.jpg', '李朋朋', '13991396973', 25, 0, 50, 1, 2.00, 3.00, 1.00, 2.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (16, '海棠食堂档口16', '/upload/dangkou/dangkou16.jpg', '李朋朋', '13991396973', 26, 0, 64, 1, 5.00, 5.00, 5.00, 5.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (17, '海棠食堂档口17', '/upload/dangkou/dangkou17.jpg', '李朋朋', '13991396973', 27, 0, 70, 2, 5.00, 4.00, 4.00, 3.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (18, '海棠食堂档口18', '/upload/dangkou/dangkou18.jpg', '李朋朋', '13991396973', 28, 0, 182, 1, 4.00, 5.00, 5.00, 4.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (19, '海棠食堂档口19', '/upload/dangkou/dangkou19.jpg', '李朋朋', '13991396973', 29, 0, 291, 1, 4.00, 4.00, 4.00, 5.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (20, '海棠食堂档口20', '/upload/dangkou/dangkou20.jpg', '李朋朋', '13991396973', 30, 1, 348, 3, 5.00, 4.50, 4.50, 4.00, '海棠餐厅一楼', 100, 1, 1, SUBSTRING_INDEX(name, '档口', -1));

-- 丁香食堂档口数据
INSERT INTO `biz_store` VALUES (23, '丁香食堂档口1', '/upload/dingxiang/dingxiang1.jpg', '李朋朋', '13991396973', 51, 1, 135, 2, 5.00, 4.00, 4.00, 5.00, '丁香餐厅一楼', 46, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (24, '丁香食堂档口2', '/upload/dingxiang/dingxiang2.jpg', '李朋朋', '13991396973', 52, 1, 24, 2, 5.00, 5.00, 5.00, 5.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (25, '丁香食堂档口3', '/upload/dingxiang/dingxiang3.jpg', '李朋朋', '13991396973', 53, 1, 47, 6, 4.00, 4.50, 3.75, 4.25, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (26, '丁香食堂档口4', '/upload/dingxiang/dingxiang4.jpg', '李朋朋', '13991396973', 54, 0, 41, 0, 0.00, 0.00, 0.00, 0.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (27, '丁香食堂档口5', '/upload/dingxiang/dingxiang5.jpg', '李朋朋', '13991396973', 55, 0, 50, 0, 0.00, 0.00, 0.00, 0.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (28, '丁香食堂档口6', '/upload/dingxiang/dingxiang6.jpg', '李朋朋', '13991396973', 56, 1, 64, 0, 0.00, 0.00, 0.00, 0.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (29, '丁香食堂档口7', '/upload/dingxiang/dingxiang7.jpg', '李朋朋', '13991396973', 57, 0, 70, 0, 0.00, 0.00, 0.00, 0.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (30, '丁香食堂档口8', '/upload/dingxiang/dingxiang8.jpg', '李朋朋', '13991396973', 58, 0, 80, 0, 0.00, 0.00, 0.00, 0.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (31, '丁香食堂档口9', '/upload/dingxiang/dingxiang9.jpg', '李朋朋', '13991396973', 59, 0, 90, 0, 0.00, 0.00, 0.00, 0.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (32, '丁香食堂档口10', '/upload/dingxiang/dingxiang10.jpg', '李朋朋', '13991396973', 60, 0, 103, 0, 0.00, 0.00, 0.00, 0.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (33, '丁香食堂档口11', '/upload/dingxiang/dingxiang11.jpg', '李朋朋', '13991396973', 61, 0, 111, 1, 4.00, 5.00, 5.00, 4.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (34, '丁香食堂档口12', '/upload/dingxiang/dingxiang12.jpg', '李朋朋', '13991396973', 62, 0, 20, 1, 3.00, 2.00, 3.00, 2.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (35, '丁香食堂档口13', '/upload/dingxiang/dingxiang13.jpg', '李朋朋', '13991396973', 63, 0, 30, 0, 0.00, 0.00, 0.00, 0.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (36, '丁香食堂档口14', '/upload/dingxiang/dingxiang14.jpg', '李朋朋', '13991396973', 64, 1, 47, 1, 5.00, 5.00, 4.00, 4.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (37, '丁香食堂档口15', '/upload/dingxiang/dingxiang15.jpg', '李朋朋', '13991396973', 65, 0, 50, 1, 2.00, 3.00, 1.00, 2.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (38, '丁香食堂档口16', '/upload/dingxiang/dingxiang16.jpg', '李朋朋', '13991396973', 66, 0, 64, 1, 5.00, 5.00, 5.00, 5.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (39, '丁香食堂档口17', '/upload/dingxiang/dingxiang17.jpg', '李朋朋', '13991396973', 67, 0, 70, 2, 5.00, 4.00, 4.00, 3.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (40, '丁香食堂档口18', '/upload/dingxiang/dingxiang18.jpg', '李朋朋', '13991396973', 68, 0, 182, 1, 4.00, 5.00, 5.00, 4.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (41, '丁香食堂档口19', '/upload/dingxiang/dingxiang19.jpg', '李朋朋', '13991396973', 69, 0, 291, 1, 4.00, 4.00, 4.00, 5.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (42, '丁香食堂档口20', '/upload/dingxiang/dingxiang20.jpg', '李朋朋', '13991396973', 70, 1, 348, 3, 5.00, 4.50, 4.50, 4.00, '丁香餐厅一楼', 100, 2, 1, SUBSTRING_INDEX(name, '档口', -1));

-- 竹园食堂档口数据
INSERT INTO `biz_store` VALUES (43, '竹园食堂档口1', '/upload/zhuyuandangkou/dangkou1.jpg', '李朋朋', '13991396973', 71, 1, 135, 2, 5.00, 4.00, 4.00, 5.00, '竹园餐厅一楼', 46, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (44, '竹园食堂档口2', '/upload/zhuyuandangkou/dangkou2.jpg', '李朋朋', '13991396973', 72, 1, 24, 2, 5.00, 5.00, 5.00, 5.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (45, '竹园食堂档口3', '/upload/zhuyuandangkou/dangkou3.jpg', '李朋朋', '13991396973', 73, 1, 47, 6, 4.00, 4.50, 3.75, 4.25, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (46, '竹园食堂档口4', '/upload/zhuyuandangkou/dangkou4.jpg', '李朋朋', '13991396973', 74, 0, 41, 0, 0.00, 0.00, 0.00, 0.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (47, '竹园食堂档口5', '/upload/zhuyuandangkou/dangkou5.jpg', '李朋朋', '13991396973', 75, 0, 50, 0, 0.00, 0.00, 0.00, 0.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (48, '竹园食堂档口6', '/upload/zhuyuandangkou/dangkou6.jpg', '李朋朋', '13991396973', 76, 1, 64, 0, 0.00, 0.00, 0.00, 0.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (49, '竹园食堂档口7', '/upload/zhuyuandangkou/dangkou7.jpg', '李朋朋', '13991396973', 77, 0, 70, 0, 0.00, 0.00, 0.00, 0.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (50, '竹园食堂档口8', '/upload/zhuyuandangkou/dangkou8.jpg', '李朋朋', '13991396973', 78, 0, 80, 0, 0.00, 0.00, 0.00, 0.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (51, '竹园食堂档口9', '/upload/zhuyuandangkou/dangkou9.jpg', '李朋朋', '13991396973', 79, 0, 90, 0, 0.00, 0.00, 0.00, 0.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (52, '竹园食堂档口10', '/uploadzhuyuandangkou/dangkou10.jpg', '李朋朋', '13991396973', 80, 0, 103, 0, 0.00, 0.00, 0.00, 0.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (53, '竹园食堂档口11', '/uploadzhuyuandangkou/dangkou11.jpg', '李朋朋', '13991396973', 81, 0, 111, 1, 4.00, 5.00, 5.00, 4.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (54, '竹园食堂档口12', '/uploadzhuyuandangkou/dangkou12.jpg', '李朋朋', '13991396973', 82, 0, 20, 1, 3.00, 2.00, 3.00, 2.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (55, '竹园食堂档口13', '/uploadzhuyuandangkou/dangkou13.jpg', '李朋朋', '13991396973', 83, 0, 30, 0, 0.00, 0.00, 0.00, 0.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (56, '竹园食堂档口14', '/uploadzhuyuandangkou/dangkou14.jpg', '李朋朋', '13991396973', 84, 1, 47, 1, 5.00, 5.00, 4.00, 4.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (57, '竹园食堂档口15', '/uploadzhuyuandangkou/dangkou15.jpg', '李朋朋', '13991396973', 85, 0, 50, 1, 2.00, 3.00, 1.00, 2.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (58, '竹园食堂档口16', '/uploadzhuyuandangkou/dangkou16.jpg', '李朋朋', '13991396973', 86, 0, 64, 1, 5.00, 5.00, 5.00, 5.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (59, '竹园食堂档口17', '/uploadzhuyuandangkou/dangkou17.jpg', '李朋朋', '13991396973', 87, 0, 70, 2, 5.00, 4.00, 4.00, 3.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (60, '竹园食堂档口18', '/uploadzhuyuandangkou/dangkou18.jpg', '李朋朋', '13991396973', 88, 0, 182, 1, 4.00, 5.00, 5.00, 4.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (61, '竹园食堂档口19', '/uploadzhuyuandangkou/dangkou19.jpg', '李朋朋', '13991396973', 89, 0, 291, 1, 4.00, 4.00, 4.00, 5.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));
INSERT INTO `biz_store` VALUES (62, '竹园食堂档口20', '/uploadzhuyuandangkou/dangkou20.jpg', '李朋朋', '13991396973', 90, 1, 348, 3, 5.00, 4.50, 4.50, 4.00, '竹园餐厅一楼', 100, 3, 1, SUBSTRING_INDEX(name, '档口', -1));

-- ----------------------------
-- Table structure for biz_store_comment
-- ----------------------------
DROP TABLE IF EXISTS `biz_store_comment`;
CREATE TABLE `biz_store_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `store_id` bigint(0) NULL DEFAULT NULL COMMENT '店铺id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论描述',
  `pid` bigint(0) NULL DEFAULT 0 COMMENT '父评论id',
  `score` int(0) NULL DEFAULT NULL COMMENT '店铺评分',
  `service_score` int(0) NULL DEFAULT NULL COMMENT '服务评分',
  `environment_score` int(0) NULL DEFAULT NULL COMMENT '环境评分',
  `taste_score` int(0) NULL DEFAULT NULL COMMENT '口味评分',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_store_comment
-- ----------------------------
INSERT INTO `biz_store_comment` VALUES (1, 40, 1, '这家店很好，是目前我吃过最好吃的鱼粉店', 0, 5, 5, 4, 4, 2, '2024-03-05 18:03:42');
INSERT INTO `biz_store_comment` VALUES (2, 40, 2, '最爱吃他们家的肉蟹煲', 0, 5, 5, 5, 5, 2, '2024-03-05 18:04:26');
INSERT INTO `biz_store_comment` VALUES (3, 40, 3, '味道一级棒', 0, 5, 5, 4, 5, 1, '2024-03-05 18:04:55');
INSERT INTO `biz_store_comment` VALUES (4, 41, 18, '酸菜鱼不错\n这家店环境很差', 0, 4, 4, 5, 5, 1, '2024-03-05 18:07:16');
INSERT INTO `biz_store_comment` VALUES (5, 41, 15, '超级难吃！！', 0, 2, 2, 3, 1, 1, '2024-03-05 18:07:36');
INSERT INTO `biz_store_comment` VALUES (6, 41, 16, '很好吃！！！', 0, 5, 5, 5, 5, 1, '2024-03-05 18:07:51');
INSERT INTO `biz_store_comment` VALUES (7, 41, 11, '还不错哦！', 0, 4, 4, 5, 5, 1, '2024-03-05 18:08:06');
INSERT INTO `biz_store_comment` VALUES (8, 40, 14, '还不错！', 0, 5, 4, 5, 4, 1, '2024-03-05 18:43:19');
INSERT INTO `biz_store_comment` VALUES (9, 40, 20, '非常好吃，极力推荐！！', 0, 5, 4, 4, 5, 1, '2024-03-05 18:50:47');
INSERT INTO `biz_store_comment` VALUES (10, 40, 20, '非常好吃，极力推荐啊啊啊！！', 0, 5, 4, 5, 4, 2, '2024-03-05 18:50:55');
INSERT INTO `biz_store_comment` VALUES (11, 40, 19, '好吃不贵！', 0, 4, 5, 4, 4, 1, '2024-03-05 18:51:13');
INSERT INTO `biz_store_comment` VALUES (12, 40, 17, '还可以！', 0, 5, 3, 4, 4, 2, '2024-03-05 18:55:17');
INSERT INTO `biz_store_comment` VALUES (13, 1, 20, '谢谢支持！', 10, NULL, NULL, NULL, NULL, 1, '2024-03-05 19:19:01');
INSERT INTO `biz_store_comment` VALUES (14, 1, 17, '谢谢支持！', 12, NULL, NULL, NULL, NULL, 1, '2024-03-05 19:20:42');
INSERT INTO `biz_store_comment` VALUES (15, 1, 1, '谢谢支持！', 1, NULL, NULL, NULL, NULL, 1, '2024-03-05 19:20:50');
INSERT INTO `biz_store_comment` VALUES (16, 1, 2, '谢谢支持！', 2, NULL, NULL, NULL, NULL, 1, '2024-03-05 19:20:54');
INSERT INTO `biz_store_comment` VALUES (17, 41, 13, '垃圾，垃圾', 0, 1, 1, 1, 1, 3, '2024-03-05 19:40:28');
INSERT INTO `biz_store_comment` VALUES (18, 41, 12, '非常不好', 0, 3, 2, 2, 3, 1, '2024-03-05 19:40:45');
INSERT INTO `biz_store_comment` VALUES (19, 41, 14, '难吃！', 0, 1, 3, 3, 2, 0, '2024-03-05 19:40:59');
INSERT INTO `biz_store_comment` VALUES (20, 40, 3, '不错@@@@@@@', 0, 4, 4, 5, 3, 2, '2023-05-04 21:02:42');
INSERT INTO `biz_store_comment` VALUES (21, 40, 14, '22222111111111aaa', 0, 2, 3, 3, 2, 0, '2023-05-05 22:31:24');
INSERT INTO `biz_store_comment` VALUES (22, 1, 3, '测试时是', 20, NULL, NULL, NULL, NULL, 1, '2023-05-06 22:53:31');
INSERT INTO `biz_store_comment` VALUES (23, 1, 3, '<p>222222222</p>', 0, 3, 4, 4, 4, 1, '2024-02-18 17:20:26');
INSERT INTO `biz_store_comment` VALUES (24, 1, 3, '<p>2222</p>\n<p><img src=\"http://localhost:8080/upload/f16f2929cda34091ae1b8f6a94e1ad88.png\" alt=\"\" width=\"436\" height=\"172\" /></p>', 0, 4, 4, 5, 3, 2, '2024-02-18 17:20:47');
INSERT INTO `biz_store_comment` VALUES (25, 1, 3, '22222222', 24, NULL, NULL, NULL, NULL, 1, '2024-02-19 18:56:09');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'URL',
  `resource_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
  `pid` bigint(0) NOT NULL COMMENT '上级ID',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `sort` double(10, 2) NULL DEFAULT 1.00 COMMENT '排序号',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '_self' COMMENT '菜单是否新页面打开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 381 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '首页', '/admin', 'menu', 0, 'fa fa-dashboard', '2023-10-15 20:22:36', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (6, '获得侧边栏菜单', '/admin/currentMenus', 'button', 1, '', '2023-10-15 20:22:36', 6.00, '_self');
INSERT INTO `sys_permission` VALUES (70, '用户管理', '/admin/user', 'menu', 0, 'fa fa-users', '2023-10-15 20:22:36', 90.00, '_self');
INSERT INTO `sys_permission` VALUES (73, '用户保存', '/admin/user/save', 'button', 70, '', '2023-10-15 20:22:36', 73.00, '_self');
INSERT INTO `sys_permission` VALUES (74, '删除用户', '/admin/user/delete', 'button', 70, '', '2023-10-15 20:22:36', 74.00, '_self');
INSERT INTO `sys_permission` VALUES (75, '批量删除用户', '/admin/user/batchDelete', 'button', 70, '', '2023-10-15 20:22:36', 75.00, '_self');
INSERT INTO `sys_permission` VALUES (76, '编辑用户', '/admin/user/edit', 'button', 70, '', '2023-10-15 20:22:36', 76.00, '_self');
INSERT INTO `sys_permission` VALUES (82, '保存个人信息', '/admin/user/profile/save', 'button', 120, '', '2023-10-15 20:22:36', 82.00, '_self');
INSERT INTO `sys_permission` VALUES (83, '修改密码', '/admin/user/changePass', 'button', 120, '', '2023-10-15 20:22:36', 83.00, '_self');
INSERT INTO `sys_permission` VALUES (91, '角色管理', '/admin/role', 'menu', 345, 'fa fa-snowflake-o', '2023-10-15 20:22:36', 91.00, '_self');
INSERT INTO `sys_permission` VALUES (92, '保存角色', '/admin/role/save', 'button', 91, '', '2023-10-15 20:22:36', 92.00, '_self');
INSERT INTO `sys_permission` VALUES (93, '编辑角色', '/admin/role/edit', 'page', 91, '', '2023-10-15 20:22:36', 93.00, '_self');
INSERT INTO `sys_permission` VALUES (94, '删除角色', '/admin/role/delete', 'button', 91, '', '2023-10-15 20:22:36', 94.00, '_self');
INSERT INTO `sys_permission` VALUES (95, '权限管理', '/admin/permission', 'menu', 345, 'fa fa-podcast', '2023-10-15 20:22:36', 95.00, '_self');
INSERT INTO `sys_permission` VALUES (96, '保存权限', '/admin/permission/save', 'button', 95, '', '2023-10-15 20:22:36', 96.00, '_self');
INSERT INTO `sys_permission` VALUES (97, '编辑权限', '/admin/permission/edit', 'page', 95, '', '2023-10-15 20:22:36', 97.00, '_self');
INSERT INTO `sys_permission` VALUES (98, '删除权限', '/admin/permission/delete', 'button', 95, '', '2023-10-15 20:22:36', 98.00, '_self');
INSERT INTO `sys_permission` VALUES (106, '获得当前登录用户信息接口', '/admin/currentUser', 'button', 1, '', '2023-02-04 10:26:13', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (110, '添加权限', '/admin/permission/new', 'menu', 95, 'fa fa-circle-o', '2023-02-07 23:14:11', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (111, '添加角色', '/admin/role/new', 'menu', 91, 'fa fa-circle-o', '2023-02-07 23:19:05', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (120, '个人信息', '/admin/user/profile', 'page', 0, 'fa fa-user', '2023-02-07 23:38:51', 90.00, '_self');
INSERT INTO `sys_permission` VALUES (127, '角色列表', '/admin/role', 'menu', 91, 'fa fa-circle-o', '2023-02-08 19:20:54', 0.00, '_self');
INSERT INTO `sys_permission` VALUES (128, '权限列表', '/admin/permission', 'menu', 95, 'fa fa-circle-o', '2023-02-08 19:21:16', 0.00, '_self');
INSERT INTO `sys_permission` VALUES (146, '文件上传', '/admin/file/upload', 'button', 1, '', '2023-03-08 17:53:01', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (169, '获得当前登录用户角色接口', '/admin/currentRole', 'button', 1, '', '2023-04-07 23:02:23', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (171, '用户资料', '/admin/user/info', 'page', 1, '', '2023-10-15 09:59:06', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (200, '用户文件上传', '/admin/file/upload', 'button', 120, '', '2023-11-18 09:59:26', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (317, '添加员工', '/admin/user/new', 'button', 70, '', '2023-01-11 14:09:28', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (345, '系统设置', '/admin/setting', 'menu', 0, 'fa fa-cog fa-fw', '2023-04-19 00:01:13', 100.00, '_self');
INSERT INTO `sys_permission` VALUES (365, '店铺管理', '/admin/store', 'menu', 0, '', '2024-02-26 13:12:49', 10.00, '_self');
INSERT INTO `sys_permission` VALUES (366, '评价管理', '#', 'menu', 0, '', '2024-02-26 13:13:16', 19.00, '_self');
INSERT INTO `sys_permission` VALUES (367, '店铺评价', '/admin/storeComment', 'menu', 366, '', '2024-02-26 13:14:11', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (368, '菜品评价', '/admin/dishComment', 'menu', 366, '', '2024-02-26 13:14:50', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (369, '笔记管理', '/admin/note', 'menu', 0, '', '2024-02-26 13:16:07', 17.00, '_self');
INSERT INTO `sys_permission` VALUES (370, '收藏管理', '/admin/mark', 'menu', 0, '', '2024-02-26 13:16:33', 16.00, '_self');
INSERT INTO `sys_permission` VALUES (371, '菜品预告', '/admin/dishNotice', 'menu', 0, '', '2024-02-26 13:21:22', 20.00, '_self');
INSERT INTO `sys_permission` VALUES (372, '榜单管理', '#', 'menu', 0, '', '2024-02-26 13:22:08', 22.00, '_self');
INSERT INTO `sys_permission` VALUES (373, '店铺榜', '/admin/rank/store', 'menu', 372, '', '2024-02-26 13:22:47', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (375, '菜品管理', '/admin/dish', 'menu', 0, '', '2024-02-26 13:38:14', 12.00, '_self');
INSERT INTO `sys_permission` VALUES (376, '营销管理', '#', 'menu', 0, '', '2024-02-27 11:08:40', 22.00, '_self');
INSERT INTO `sys_permission` VALUES (377, '推荐菜品', '/admin/ad/dish', 'menu', 376, '', '2024-02-27 11:36:53', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (378, '优质店铺', '/admin/ad/store', 'menu', 376, '', '2024-02-27 11:37:16', 1.00, '_self');
INSERT INTO `sys_permission` VALUES (379, '菜品榜', '/admin/rank/dish', 'menu', 372, '', '2024-02-27 12:11:18', 3.00, '_self');
INSERT INTO `sys_permission` VALUES (380, '笔记评论管理', '/admin/noteComment', 'menu', 0, '', '2024-02-20 17:56:41', 18.00, '_self');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `level` int(0) NOT NULL COMMENT '等级',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_register_default` int(0) NOT NULL DEFAULT 0 COMMENT '是否默认角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '管理员', 999, '2023-02-05 18:54:23', 0);
INSERT INTO `sys_role` VALUES (2, 'business', '商家', 100, '2023-03-16 19:05:57', 0);
INSERT INTO `sys_role` VALUES (3, 'customer', '顾客', 1, '2024-02-26 13:41:04', 1);

-- ----------------------------
-- Table structure for sys_role_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_ref`;
CREATE TABLE `sys_role_permission_ref`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(0) NOT NULL COMMENT '权限ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_ref_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_permission_ref_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4493 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission_ref
-- ----------------------------
INSERT INTO `sys_role_permission_ref` VALUES (2, 1, '2025-05-25 10:18:12', 4287);
INSERT INTO `sys_role_permission_ref` VALUES (2, 106, '2025-05-25 10:18:12', 4288);
INSERT INTO `sys_role_permission_ref` VALUES (2, 146, '2025-05-25 10:18:12', 4289);
INSERT INTO `sys_role_permission_ref` VALUES (2, 169, '2025-05-25 10:18:12', 4290);
INSERT INTO `sys_role_permission_ref` VALUES (2, 171, '2025-05-25 10:18:12', 4291);
INSERT INTO `sys_role_permission_ref` VALUES (2, 6, '2025-05-25 10:18:12', 4292);
INSERT INTO `sys_role_permission_ref` VALUES (2, 365, '2025-05-25 10:18:12', 4293);
INSERT INTO `sys_role_permission_ref` VALUES (2, 375, '2025-05-25 10:18:12', 4294);
INSERT INTO `sys_role_permission_ref` VALUES (2, 366, '2025-05-25 10:18:12', 4295);
INSERT INTO `sys_role_permission_ref` VALUES (2, 367, '2025-05-25 10:18:12', 4296);
INSERT INTO `sys_role_permission_ref` VALUES (2, 368, '2025-05-25 10:18:12', 4297);
INSERT INTO `sys_role_permission_ref` VALUES (2, 371, '2025-05-25 10:18:12', 4298);
INSERT INTO `sys_role_permission_ref` VALUES (2, 120, '2025-05-25 10:18:12', 4299);
INSERT INTO `sys_role_permission_ref` VALUES (2, 200, '2025-05-25 10:18:12', 4300);
INSERT INTO `sys_role_permission_ref` VALUES (2, 82, '2025-05-25 10:18:12', 4301);
INSERT INTO `sys_role_permission_ref` VALUES (2, 83, '2025-05-25 10:18:12', 4302);
INSERT INTO `sys_role_permission_ref` VALUES (1, 1, '2025-05-25 17:56:54', 4433);
INSERT INTO `sys_role_permission_ref` VALUES (1, 106, '2025-05-25 17:56:54', 4434);
INSERT INTO `sys_role_permission_ref` VALUES (1, 171, '2025-05-25 17:56:54', 4435);
INSERT INTO `sys_role_permission_ref` VALUES (1, 169, '2025-05-25 17:56:54', 4436);
INSERT INTO `sys_role_permission_ref` VALUES (1, 146, '2025-05-25 17:56:54', 4437);
INSERT INTO `sys_role_permission_ref` VALUES (1, 6, '2025-05-25 17:56:54', 4438);
INSERT INTO `sys_role_permission_ref` VALUES (1, 365, '2025-05-25 17:56:54', 4439);
INSERT INTO `sys_role_permission_ref` VALUES (1, 375, '2025-05-25 17:56:54', 4440);
INSERT INTO `sys_role_permission_ref` VALUES (1, 370, '2025-05-25 17:56:54', 4441);
INSERT INTO `sys_role_permission_ref` VALUES (1, 369, '2025-05-25 17:56:54', 4442);
INSERT INTO `sys_role_permission_ref` VALUES (1, 380, '2025-05-25 17:56:54', 4443);
INSERT INTO `sys_role_permission_ref` VALUES (1, 366, '2025-05-25 17:56:54', 4444);
INSERT INTO `sys_role_permission_ref` VALUES (1, 368, '2025-05-25 17:56:54', 4445);
INSERT INTO `sys_role_permission_ref` VALUES (1, 367, '2025-05-25 17:56:54', 4446);
INSERT INTO `sys_role_permission_ref` VALUES (1, 371, '2025-05-25 17:56:54', 4447);
INSERT INTO `sys_role_permission_ref` VALUES (1, 372, '2025-05-25 17:56:54', 4448);
INSERT INTO `sys_role_permission_ref` VALUES (1, 373, '2025-05-25 17:56:54', 4449);
INSERT INTO `sys_role_permission_ref` VALUES (1, 379, '2025-05-25 17:56:54', 4450);
INSERT INTO `sys_role_permission_ref` VALUES (1, 376, '2025-05-25 17:56:54', 4451);
INSERT INTO `sys_role_permission_ref` VALUES (1, 378, '2025-05-25 17:56:54', 4452);
INSERT INTO `sys_role_permission_ref` VALUES (1, 377, '2025-05-25 17:56:54', 4453);
INSERT INTO `sys_role_permission_ref` VALUES (1, 70, '2025-05-25 17:56:54', 4454);
INSERT INTO `sys_role_permission_ref` VALUES (1, 317, '2025-05-25 17:56:54', 4455);
INSERT INTO `sys_role_permission_ref` VALUES (1, 73, '2025-05-25 17:56:54', 4456);
INSERT INTO `sys_role_permission_ref` VALUES (1, 74, '2025-05-25 17:56:54', 4457);
INSERT INTO `sys_role_permission_ref` VALUES (1, 75, '2025-05-25 17:56:54', 4458);
INSERT INTO `sys_role_permission_ref` VALUES (1, 76, '2025-05-25 17:56:54', 4459);
INSERT INTO `sys_role_permission_ref` VALUES (1, 120, '2025-05-25 17:56:54', 4460);
INSERT INTO `sys_role_permission_ref` VALUES (1, 200, '2025-05-25 17:56:54', 4461);
INSERT INTO `sys_role_permission_ref` VALUES (1, 82, '2025-05-25 17:56:54', 4462);
INSERT INTO `sys_role_permission_ref` VALUES (1, 83, '2025-05-25 17:56:54', 4463);
INSERT INTO `sys_role_permission_ref` VALUES (1, 345, '2025-05-25 17:56:54', 4464);
INSERT INTO `sys_role_permission_ref` VALUES (1, 91, '2025-05-25 17:56:54', 4465);
INSERT INTO `sys_role_permission_ref` VALUES (1, 127, '2025-05-25 17:56:54', 4466);
INSERT INTO `sys_role_permission_ref` VALUES (1, 111, '2025-05-25 17:56:54', 4467);
INSERT INTO `sys_role_permission_ref` VALUES (1, 92, '2025-05-25 17:56:54', 4468);
INSERT INTO `sys_role_permission_ref` VALUES (1, 93, '2025-05-25 17:56:54', 4469);
INSERT INTO `sys_role_permission_ref` VALUES (1, 94, '2025-05-25 17:56:54', 4470);
INSERT INTO `sys_role_permission_ref` VALUES (1, 95, '2025-05-25 17:56:54', 4471);
INSERT INTO `sys_role_permission_ref` VALUES (1, 128, '2025-05-25 17:56:54', 4472);
INSERT INTO `sys_role_permission_ref` VALUES (1, 110, '2025-05-25 17:56:54', 4473);
INSERT INTO `sys_role_permission_ref` VALUES (1, 96, '2025-05-25 17:56:54', 4474);
INSERT INTO `sys_role_permission_ref` VALUES (1, 97, '2025-05-25 17:56:54', 4475);
INSERT INTO `sys_role_permission_ref` VALUES (1, 98, '2025-05-25 17:56:54', 4476);
INSERT INTO `sys_role_permission_ref` VALUES (3, 1, '2025-05-25 17:57:09', 4477);
INSERT INTO `sys_role_permission_ref` VALUES (3, 106, '2025-05-25 17:57:09', 4478);
INSERT INTO `sys_role_permission_ref` VALUES (3, 171, '2025-05-25 17:57:09', 4479);
INSERT INTO `sys_role_permission_ref` VALUES (3, 169, '2025-05-25 17:57:09', 4480);
INSERT INTO `sys_role_permission_ref` VALUES (3, 146, '2025-05-25 17:57:09', 4481);
INSERT INTO `sys_role_permission_ref` VALUES (3, 6, '2025-05-25 17:57:09', 4482);
INSERT INTO `sys_role_permission_ref` VALUES (3, 370, '2025-05-25 17:57:09', 4483);
INSERT INTO `sys_role_permission_ref` VALUES (3, 369, '2025-05-25 17:57:09', 4484);
INSERT INTO `sys_role_permission_ref` VALUES (3, 380, '2025-05-25 17:57:09', 4485);
INSERT INTO `sys_role_permission_ref` VALUES (3, 366, '2025-05-25 17:57:09', 4486);
INSERT INTO `sys_role_permission_ref` VALUES (3, 368, '2025-05-25 17:57:09', 4487);
INSERT INTO `sys_role_permission_ref` VALUES (3, 367, '2025-05-25 17:57:09', 4488);
INSERT INTO `sys_role_permission_ref` VALUES (3, 120, '2025-05-25 17:57:09', 4489);
INSERT INTO `sys_role_permission_ref` VALUES (3, 200, '2025-05-25 17:57:09', 4490);
INSERT INTO `sys_role_permission_ref` VALUES (3, 82, '2025-05-25 17:57:09', 4491);
INSERT INTO `sys_role_permission_ref` VALUES (3, 83, '2025-05-25 17:57:09', 4492);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_display_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `user_pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `role_id` bigint(0) NOT NULL COMMENT '角色iD',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '13800001111', 'gly@qq.com', '/static/images/avatar/36.jpeg', '管理员', 'admin', '123456', '2025-05-25 15:22:47', 1);
INSERT INTO `sys_user` VALUES (11, '13991396973', 'lipengpeng1@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng1', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (12, '13991396973', 'lipengpeng2@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng2', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (13, '13991396973', 'lipengpeng3@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng3', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (14, '13991396973', 'lipengpeng4@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng4', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (15, '13991396973', 'lipengpeng5@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng5', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (16, '13991396973', 'lipengpeng6@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng6', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (17, '13991396973', 'lipengpeng7@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng7', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (18, '13991396973', 'lipengpeng8@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng8', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (19, '13991396973', 'lipengpeng9@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng9', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (20, '13991396973', 'lipengpeng10@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng10', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (21, '13991396973', 'lipengpeng11@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng11', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (22, '13991396973', 'lipengpeng12@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng12', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (23, '13991396973', 'lipengpeng13@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng13', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (24, '13991396973', 'lipengpeng14@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng14', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (25, '13991396973', 'lipengpeng15@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng15', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (26, '13991396973', 'lipengpeng16@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng16', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (27, '13991396973', 'lipengpeng17@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng17', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (28, '13991396973', 'lipengpeng18@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng18', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (29, '13991396973', 'lipengpeng19@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng19', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (30, '13991396973', 'lipengpeng20@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'lipengpeng20', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (32, '13700001171', '', '/static/images/avatar/2.jpeg', '李白', 'libai', '123456', '2025-05-25 14:28:55', 3);
INSERT INTO `sys_user` VALUES (33, '13700001181', '', '/static/images/avatar/2.jpeg', '杜甫', 'dufu', '123456', '2025-05-25 14:28:55', 3);
INSERT INTO `sys_user` VALUES (34, '13700001191', '', '/static/images/avatar/2.jpeg', '白居易', 'baijuyi', '123456', '2025-05-25 14:28:55', 3);
INSERT INTO `sys_user` VALUES (36, '13700001101', '', '/static/images/avatar/2.jpeg', '杜牧', 'dumu', '123456', '2025-05-25 14:29:03', 3);
INSERT INTO `sys_user` VALUES (37, '13700001331', '', '/static/images/avatar/2.jpeg', '王昌龄', 'wangchangling', '123456', '2025-05-25 14:29:03', 3);
INSERT INTO `sys_user` VALUES (40, '13512341234', 'zs@qq.com', '/static/images/avatar/31.jpeg', '张三', 'zhangsan', '123456', '2025-05-25 14:08:30', 3);
INSERT INTO `sys_user` VALUES (41, '15811111111', 'lisi@qq.com', '/static/images/avatar/8.jpeg', '李四', 'lisi', '123456', '2025-05-25 14:40:26', 3);
INSERT INTO `sys_user` VALUES (42, '13412223322', 'abs@qq.com', '/static/images/avatar/8.jpeg', 'ABCD', 'abcd', '123456', '2025-05-25 14:05:28', 3);

-- 丁香食堂档口用户
INSERT INTO `sys_user` VALUES (31, '13991396973', 'dingxiang1@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang1', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (32, '13991396973', 'dingxiang2@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang2', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (33, '13991396973', 'dingxiang3@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang3', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (34, '13991396973', 'dingxiang4@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang4', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (35, '13991396973', 'dingxiang5@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang5', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (36, '13991396973', 'dingxiang6@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang6', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (37, '13991396973', 'dingxiang7@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang7', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (38, '13991396973', 'dingxiang8@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang8', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (39, '13991396973', 'dingxiang9@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang9', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (40, '13991396973', 'dingxiang10@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang10', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (41, '13991396973', 'dingxiang11@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang11', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (42, '13991396973', 'dingxiang12@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang12', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (43, '13991396973', 'dingxiang13@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang13', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (44, '13991396973', 'dingxiang14@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang14', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (45, '13991396973', 'dingxiang15@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang15', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (46, '13991396973', 'dingxiang16@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang16', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (47, '13991396973', 'dingxiang17@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang17', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (48, '13991396973', 'dingxiang18@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang18', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (49, '13991396973', 'dingxiang19@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang19', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (50, '13991396973', 'dingxiang20@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'dingxiang20', '123456', '2025-05-25 14:25:15', 2);

-- 竹园食堂档口用户
INSERT INTO `sys_user` VALUES (51, '13991396973', 'zhuyuan1@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan1', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (52, '13991396973', 'zhuyuan2@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan2', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (53, '13991396973', 'zhuyuan3@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan3', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (54, '13991396973', 'zhuyuan4@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan4', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (55, '13991396973', 'zhuyuan5@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan5', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (56, '13991396973', 'zhuyuan6@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan6', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (57, '13991396973', 'zhuyuan7@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan7', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (58, '13991396973', 'zhuyuan8@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan8', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (59, '13991396973', 'zhuyuan9@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan9', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (60, '13991396973', 'zhuyuan10@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan10', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (61, '13991396973', 'zhuyuan11@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan11', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (62, '13991396973', 'zhuyuan12@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan12', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (63, '13991396973', 'zhuyuan13@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan13', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (64, '13991396973', 'zhuyuan14@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan14', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (65, '13991396973', 'zhuyuan15@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan15', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (66, '13991396973', 'zhuyuan16@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan16', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (67, '13991396973', 'zhuyuan17@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan17', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (68, '13991396973', 'zhuyuan18@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan18', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (69, '13991396973', 'zhuyuan19@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan19', '123456', '2025-05-25 14:25:15', 2);
INSERT INTO `sys_user` VALUES (70, '13991396973', 'zhuyuan20@qq.com', '/static/images/avatar/1.jpeg', '李朋朋', 'zhuyuan20', '123456', '2025-05-25 14:25:15', 2);

-- 食堂主表
CREATE TABLE `biz_canteen` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '食堂ID',
    `name` varchar(50) NOT NULL COMMENT '食堂名称',
    `photo` varchar(255) DEFAULT NULL COMMENT '食堂图片',
    `description` text COMMENT '食堂简介',
    `business_time` varchar(100) DEFAULT NULL COMMENT '营业时间',
    `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
    `floor_count` int(11) DEFAULT '1' COMMENT '楼层数',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `view_size` int(11) DEFAULT '0' COMMENT '访问量',
    `status` int(11) DEFAULT '1' COMMENT '状态：1营业，0休息',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食堂信息表';

-- 插入三个食堂的基本信息
INSERT INTO `biz_canteen` VALUES 
(1, '海棠餐厅', '/upload/canteen/haitang.jpg', '海棠餐厅是一栋两层的独立食堂，位于西区海棠园，环境优美，提供多样化的餐饮选择...', '07:00-22:00', '西区海棠园', 2, '2025-05-25 10:00:00', 0, 1),
(2, '丁香餐厅', '/upload/canteen/dingxiang.jpg', '丁香餐厅是一栋两层的独立食堂，位于东区丁香园，特色美食，就餐环境舒适...', '07:00-22:00', '东区丁香园', 2, '2025-05-25 10:00:00', 0, 1),
(3, '竹园餐厅', '/upload/canteen/zhuyuan.jpg', '竹园餐厅是一栋三层的独立食堂，位于南区竹园，风格清新，视野开阔...', '07:00-22:00', '南区竹园', 3, '2025-05-25 10:00:00', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
