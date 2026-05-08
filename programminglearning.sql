/*
 Navicat Premium Dump SQL

 Source Server         : ProgrammingLearning
 Source Server Type    : MySQL
 Source Server Version : 80043 (8.0.43)
 Source Host           : localhost:3306
 Source Schema         : programminglearning

 Target Server Type    : MySQL
 Target Server Version : 80043 (8.0.43)
 File Encoding         : 65001

 Date: 08/05/2026 16:09:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appuser
-- ----------------------------
DROP TABLE IF EXISTS `appuser`;
CREATE TABLE `appuser`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `Email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `RoleType` int NULL DEFAULT NULL COMMENT '角色',
  `PhoneNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `ImageUrls` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `Birth` datetime NULL DEFAULT NULL COMMENT '出生年月',
  `Name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `UserName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of appuser
-- ----------------------------
INSERT INTO `appuser` VALUES (1, '2026-01-29 22:52:23', 0, 'admin', '873648162@qq.com', 1, '18576547426', 'http://localhost:7245/1776169197573/preview.jpg', '2026-04-15 00:00:00', '管理员', 'admin');
INSERT INTO `appuser` VALUES (2, '2026-01-29 22:52:23', 1, 'zb123', 'z16680140694@163.com', 2, '13812345678', 'http://localhost:7245/1776172468629/preview.jpg', '2026-04-01 00:00:00', '李华', 'lihua');
INSERT INTO `appuser` VALUES (3, '2026-01-29 22:52:23', 1, 'T002123456', NULL, 2, '13912345678', 'http://localhost:7245/1735794376202/41.jpg', NULL, '王强', 'T002');
INSERT INTO `appuser` VALUES (4, '2026-01-29 22:52:23', 1, 'T003123456', NULL, 2, '13712345678', 'http://localhost:7245/1735794410558/11.jpg', NULL, '赵敏', 'T003');
INSERT INTO `appuser` VALUES (5, '2026-01-29 22:52:23', 1, 'T004123456', NULL, 2, '13612345678', 'http://localhost:7245/1735794419913/16.jpg', NULL, '陈杰', 'T004');
INSERT INTO `appuser` VALUES (6, '2026-01-29 22:52:23', 1, 'T005123456', NULL, 2, '13512345678', 'http://localhost:7245/1735794424353/15.jpg', NULL, '刘洋', 'T005');
INSERT INTO `appuser` VALUES (7, '2026-01-29 22:52:23', 1, 'T006123456', NULL, 2, '13412345678', 'http://localhost:7245/1735794436083/11.jpg', NULL, '孙莉', 'T006');
INSERT INTO `appuser` VALUES (8, '2026-01-29 22:52:23', 1, 'T007123456', NULL, 2, '13312345678', 'http://localhost:7245/1735794444494/3.jpg', NULL, '周峰', 'T007');
INSERT INTO `appuser` VALUES (9, '2026-01-29 22:52:23', 1, 'T008123456', NULL, 2, '13212345678', 'http://localhost:7245/1735794449426/61.jpg', NULL, '吴芳', 'T008');
INSERT INTO `appuser` VALUES (10, '2026-01-29 22:52:23', 1, 'T009123456', NULL, 2, '13112345678', 'http://localhost:7245/1735794452911/41.jpg', NULL, '郑磊', 'T009');
INSERT INTO `appuser` VALUES (11, '2026-01-29 22:52:23', 1, 'T010123456', NULL, 2, '13012345678', 'http://localhost:7245/1735794462195/101.jpg', NULL, '张伟', 'T010');
INSERT INTO `appuser` VALUES (12, '2026-01-29 22:52:23', 1, '20230001123456', NULL, 2, '13812345678', 'http://localhost:7245/1735794753658/12.jpg', NULL, '张伟', '20230001');
INSERT INTO `appuser` VALUES (13, '2026-01-29 22:52:23', 1, '20230002123456', NULL, 2, '13912345678', 'http://localhost:7245/1735794759564/39.jpg', NULL, '李娜', '20230002');
INSERT INTO `appuser` VALUES (14, '2026-01-29 22:52:23', 1, '20230003123456', NULL, 2, '13712345678', 'http://localhost:7245/1735794767775/2.jpg', NULL, '王强', '20230003');
INSERT INTO `appuser` VALUES (15, '2026-01-29 22:52:23', 1, '20230004123456', NULL, 2, '13612345678', 'http://localhost:7245/1735794776628/33.jpg', NULL, '赵敏', '20230004');
INSERT INTO `appuser` VALUES (16, '2026-01-29 22:52:23', 1, '20230005123456', NULL, 2, '13512345678', 'http://localhost:7245/1735794782776/21.jpg', NULL, '陈杰', '20230005');
INSERT INTO `appuser` VALUES (17, '2026-01-29 22:52:23', 1, '20230006123456', NULL, 2, '13412345678', 'http://localhost:7245/1735794787252/11.jpg', NULL, '刘洋', '20230006');
INSERT INTO `appuser` VALUES (18, '2026-01-29 22:52:23', 1, '20230007123456', NULL, 2, '13312345678', 'http://localhost:7245/1735794791274/52.jpg', NULL, '孙莉', '20230007');
INSERT INTO `appuser` VALUES (19, '2026-01-29 22:52:23', 1, '20230008123456', NULL, 2, '13212345678', 'http://localhost:7245/1735794795630/53.jpg', NULL, '周峰', '20230008');
INSERT INTO `appuser` VALUES (20, '2026-01-29 22:52:23', 1, '20230010123456', NULL, 2, '13012345678', 'http://localhost:7245/1735794803810/28.jpg', NULL, '郑磊', '20230010');
INSERT INTO `appuser` VALUES (21, '2026-01-30 11:03:51', 0, 'zb123', '873648162@qq.com', 2, '13325422412', 'http://localhost:7245/1776171647298/logo.png', '2026-03-13 00:00:00', '周博', 'zb123');

-- ----------------------------
-- Table structure for askedquestions
-- ----------------------------
DROP TABLE IF EXISTS `askedquestions`;
CREATE TABLE `askedquestions`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '常见问题手册主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `NoAvailCount` int NULL DEFAULT NULL COMMENT '无效次数',
  `AvailCount` int NULL DEFAULT NULL COMMENT '有效次数',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '常见问题手册表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of askedquestions
-- ----------------------------
INSERT INTO `askedquestions` VALUES (2, '2026-01-29 22:52:23', 1, '账户管理', '如何找回忘记的密码？\n\n1. 访问大学考试平台官网。\n2. 点击“登录”按钮旁边的“忘记密码”。\n3. 输入注册时使用的邮箱地址。\n4. 系统会发送一封重置密码的邮件到您的邮箱。\n5. 按照邮件中的指示重置密码。', 3, 47, '如何找回忘记的密码');
INSERT INTO `askedquestions` VALUES (3, '2026-01-29 22:52:23', 1, '考试报名', '如何报名参加考试？\n\n1. 登录大学考试平台。\n2. 进入“考试报名”页面。\n3. 选择要参加的考试科目。\n4. 确认报名信息，包括考试时间和地点。\n5. 支付考试费用（如果需要）。\n6. 报名成功后，系统会发送确认邮件。', 1, 49, '如何报名参加考试');
INSERT INTO `askedquestions` VALUES (9, '2026-01-29 22:52:23', 1, '成绩查询', '如何查询考试成绩？\n\n1. 登录大学考试平台。\n2. 进入“成绩查询”页面。\n3. 选择要查询的考试科目。\n4. 系统会显示您的成绩和排名。', 1, 49, '如何查询考试成绩');
INSERT INTO `askedquestions` VALUES (15, '2026-01-29 22:52:23', 1, '证书管理', '如何下载电子证书？\n\n1. 登录大学考试平台。\n2. 进入“证书管理”页面。\n3. 选择要下载的证书。\n4. 点击“下载证书”按钮。\n5. 保存PDF文件并打印。', 1, 49, '如何下载电子证书');
INSERT INTO `askedquestions` VALUES (19, '2026-01-29 22:52:23', 1, '常见问题', '如何联系技术支持？\n\n1. 在考试平台首页点击“联系我们”或“技术支持”。\n2. 通过在线客服提交问题。\n3. 发送邮件至support@universityexam.com。\n4. 拨打技术支持热线：123-456-7890。', 2, 48, '如何联系技术支持');
INSERT INTO `askedquestions` VALUES (21, '2026-01-29 22:52:23', 1, '考试规则', '考试期间有哪些注意事项？\n\n1. 提前到达考场，携带必要的证件和文具。\n2. 遵守考场纪律，不得作弊。\n3. 保持安静，不要打扰其他考生。\n4. 如有疑问，举手示意监考老师。', 2, 48, '考试期间有哪些注意事项');
INSERT INTO `askedquestions` VALUES (27, '2026-01-29 22:52:23', 1, '考试申诉', '如何提出考试申诉？\n\n1. 登录大学考试平台。\n2. 进入“考试申诉”页面。\n3. 选择需要申诉的考试科目。\n4. 填写申诉表，详细说明申诉理由。\n5. 提交申诉，等待审核结果。', 3, 47, '如何提出考试申诉');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '评论主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `RelativeId` int NULL DEFAULT NULL COMMENT '关联id',
  `HeadImage` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `RootCommentId` int NULL DEFAULT NULL COMMENT '根评论',
  `Content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论内容',
  `NickName` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `SendUserId` int NULL DEFAULT NULL COMMENT '发送用户',
  `TargetUserId` int NULL DEFAULT NULL COMMENT '回复用户',
  `Type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '2026-01-30 22:52:23', 2, 11, 'http://localhost:7245/1735794349630/10.jpg', 0, '还好', '李华', 2, NULL, 'Topic');
INSERT INTO `comment` VALUES (2, '2026-04-14 21:01:40', 21, 13, 'http://localhost:7245/1776171647298/logo.png', 0, '111', '周博', 21, NULL, 'Topic');

-- ----------------------------
-- Table structure for commentlove
-- ----------------------------
DROP TABLE IF EXISTS `commentlove`;
CREATE TABLE `commentlove`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '评论点赞记录主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  `UserId` int NULL DEFAULT NULL COMMENT '点赞人',
  `CommentId` int NULL DEFAULT NULL COMMENT '点赞评论',
  `RelativeId` int NULL DEFAULT NULL COMMENT '关联id',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评论点赞记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of commentlove
-- ----------------------------
INSERT INTO `commentlove` VALUES (1, '2026-02-08 15:12:14', 2, '0', 2, 1, 11);

-- ----------------------------
-- Table structure for couse
-- ----------------------------
DROP TABLE IF EXISTS `couse`;
CREATE TABLE `couse`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '课程内容',
  `CouseTypeId` int NULL DEFAULT NULL COMMENT '课程分类',
  `Cover` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `IsPutaway` tinyint(1) NULL DEFAULT NULL COMMENT '是否上架',
  `Name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `Price` double(18, 6) NULL DEFAULT NULL COMMENT '课程价格',
  `IsFree` bit(1) NULL DEFAULT NULL COMMENT '是否免费',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of couse
-- ----------------------------
INSERT INTO `couse` VALUES (1, '2026-02-02 13:55:00', 1, '<p>Linux作为所有程序员必备技能，真正能深入底层，驱动，硬件的程序员少，从而阻碍了技能上升通道。鉴于此，我们课程涵盖了Linux系统从基础、进阶、高阶、拓展内容，多阶段多层次提升Linux系统技能。无论你是Linux小白，想要系统入门Linux系统，还是对Linux有一定基础的职场员工，想系统地深入Linux技术栈，本课程都适合学习。</p>', 1, 'http://localhost:7245/1738476601536/0.jpg', 1, 'Linux基础入门', 99.000000, b'0');
INSERT INTO `couse` VALUES (2, '2026-02-02 13:55:00', 1, '<p>关系型数据库是一种基于关系模型的数据库管理系统。它使用表格的形式来组织和存储数据，每个表格都有一个主键（或称为唯一标识符）来唯一地标识表中的每一行。通过掌握这些基础知识，你可以更好地理解和操作关系型数据库及其SQL语言。涵盖关系型数据库原理及SQL语言的基础知识</p>', 2, 'http://localhost:7245/1738476590778/0.jpg', 1, '数据库基础与SQL', 149.000000, b'0');
INSERT INTO `couse` VALUES (3, '2026-02-02 13:55:00', 1, '<p>教授成为成功产品经理所需的核心技能和工具产品，经理是一个充满挑战的职业，需要具备多方面的技能和工具。教授成为成功产品经理所需的核心技能和工具</p>', 3, 'http://localhost:7245/1738476579101/15.jpg', 1, '产品经理核心技能', 199.000000, b'1');
INSERT INTO `couse` VALUES (4, '2026-02-02 13:55:00', 1, '<p>深入讲解Java编程语言及其应用开发，Java是一种广泛使用的面向对象的编程语言，由Sun Microsystems公司在1995年首次发布。它以其简洁的语法、面向对象的特性以及平台无关性而闻名，被广泛应用于企业级应用、桌面应用程序和移动应用程序的开发中。Java语言的设计哲学强调“一次编写，到处运行”，这确保了Java程序可以在不同的操作系统和硬件平台上无缝运行。总之，Java作为一种成熟的编程语言，具有强大的功能和广泛的应用领域。掌握Java不仅可以提高软件开发的效率，还可以为未来的职业发展打下坚实的基础。</p>', 4, 'http://localhost:7245/1738476565097/0.jpg', 1, 'Java编程实战', 249.000000, b'0');
INSERT INTO `couse` VALUES (20, '2026-02-02 13:55:00', 1, '<p></p><p>Python机器学习项目实战</p><p>在当今数据驱动的时代，掌握Python机器学习技术对于开发智能应用程序至关重要。本文将引导您通过实战项目，学习如何使用Python进行机器学习建模、数据处理以及模型评估与优化。我们将从零开始，逐步构建一个复杂的机器学习模型，涵盖线性回归、决策树、随机森林、支持向量机等经典算法，并利用K折交叉验证和网格搜索来优化模型性能。同时，我们还将介绍一些实用的库，如NumPy、Pandas、Scikit-learn和TensorFlow，并展示如何在Jupyter Notebook中进行代码编写和数据分析。</p><p>通过本教程，您将能够熟练运用Python进行机器学习项目的开发，解决实际问题，并在数据科学领域取得显著成就。无论您是初学者还是有一定经验的开发者，都能从中获得宝贵的知识和经验。让我们一起踏上Python机器学习的探索之旅，开启智慧的火花吧！</p><p>Python机器学习项目实战</p>', 6, 'http://localhost:7245/1738476086856/11.jpg', 1, 'Python机器学习', 299.000000, b'0');
INSERT INTO `couse` VALUES (21, '2026-04-14 21:54:04', 21, '<p>零基础学习使用UiAutomator 2.0自动化测试，了解框架知识，环境配置，通过学习UI自动化三要素，通过搜索条件（文件、ID、属性等）找到组件（按钮、图标、文本框等），操作组件（点击、长按、滑动、输入等），快速掌握编写Android 自动化测试用例。</p>', 5, 'http://localhost:7245/1776174761343/c0d5ea370705aedf773a604cbb5571ef.jpg', 1, 'Android UiAutomator 2.0 入门实战', 120.000000, b'0');

-- ----------------------------
-- Table structure for couseclock
-- ----------------------------
DROP TABLE IF EXISTS `couseclock`;
CREATE TABLE `couseclock`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程打卡主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `UserId` int NULL DEFAULT NULL COMMENT '用户',
  `CouseId` int NULL DEFAULT NULL COMMENT '课程打卡',
  `ClockTime` datetime NULL DEFAULT NULL COMMENT '打卡时间',
  `CouseStepId` int NULL DEFAULT NULL COMMENT '打卡章节',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程打卡表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of couseclock
-- ----------------------------
INSERT INTO `couseclock` VALUES (1, '2026-02-02 14:43:14', 2, 2, 20, '2026-02-02 14:43:14', 71);

-- ----------------------------
-- Table structure for couseorder
-- ----------------------------
DROP TABLE IF EXISTS `couseorder`;
CREATE TABLE `couseorder`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程订单主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `CouseId` int NULL DEFAULT NULL COMMENT '课程',
  `No` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `PayMoney` double(20, 5) NULL DEFAULT NULL COMMENT '支付金额',
  `PayType` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支付类型',
  `UserId` int NULL DEFAULT NULL COMMENT '下单人',
  `IsReturn` tinyint(1) NULL DEFAULT NULL COMMENT '是否退款',
  `ReturnTime` datetime NULL DEFAULT NULL COMMENT '退款时间',
  `Comment` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价',
  `CommentScore` double(20, 5) NULL DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of couseorder
-- ----------------------------
INSERT INTO `couseorder` VALUES (1, '2026-02-02 14:43:02', 2, 20, 'D20250202144302401334', 299.00000, 'alipay', 2, 0, NULL, NULL, NULL);
INSERT INTO `couseorder` VALUES (2, '2026-04-14 19:35:59', 21, 2, 'D20260414193558416513', 149.00000, 'wechat', 21, 0, NULL, NULL, NULL);
INSERT INTO `couseorder` VALUES (3, '2026-04-14 19:36:32', 21, 4, 'D20260414193632230462', 249.00000, 'card', 21, 0, NULL, NULL, NULL);
INSERT INTO `couseorder` VALUES (4, '2026-04-14 19:38:48', 21, 20, 'D20260414193848551200', 299.00000, 'alipay', 21, 0, NULL, NULL, NULL);
INSERT INTO `couseorder` VALUES (5, '2026-04-14 19:43:37', 21, 1, 'D20260414194337163445', 99.00000, 'alipay', 21, 1, '2026-04-14 19:44:35', NULL, NULL);
INSERT INTO `couseorder` VALUES (7, '2026-04-15 15:16:06', 21, 21, 'D20260415151606363472', 120.00000, 'wechat', 21, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for cousestep
-- ----------------------------
DROP TABLE IF EXISTS `cousestep`;
CREATE TABLE `cousestep`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程章节主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Durtion` double(20, 5) NULL DEFAULT NULL COMMENT '小节时长',
  `VideoUrls` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '小节视频',
  `Name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程小节',
  `CouseId` int NULL DEFAULT NULL COMMENT '所属课程',
  `FileUrls` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '附件',
  `Cover` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '小节封面',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程章节表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cousestep
-- ----------------------------
INSERT INTO `cousestep` VALUES (1, '2026-02-02 14:07:00', 1, 600.50000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'Python基础回顾', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (2, '2026-02-02 14:07:00', 1, 900.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '理解HTML结构', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (3, '2026-02-02 14:07:00', 1, 750.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '使用BeautifulSoup解析网页', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (4, '2026-02-02 14:07:00', 1, 680.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'Scrapy框架入门', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (5, '2026-02-02 14:07:00', 1, 720.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '数据存储与处理', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (6, '2026-02-02 14:07:00', 1, 650.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '应对反爬虫策略', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (7, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '爬虫项目实战一', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (8, '2026-02-02 14:07:00', 1, 710.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '爬虫项目实战二', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (9, '2026-02-02 14:07:00', 1, 730.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '爬虫项目实战三', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (10, '2026-02-02 14:07:00', 1, 740.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '项目总结与优化', 13, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (11, '2026-02-02 14:07:00', 1, 650.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '.NET框架概述', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (12, '2026-02-02 14:07:00', 1, 800.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '构建第一个.NET应用', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (13, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'ASP.NET Core开发', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (14, '2026-02-02 14:07:00', 1, 750.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'Entity Framework Core', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (15, '2026-02-02 14:07:00', 1, 650.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'Web API设计', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (16, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '安全性和认证', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (17, '2026-02-02 14:07:00', 1, 710.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '性能优化技巧', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (18, '2026-02-02 14:07:00', 1, 730.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '部署和维护', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (19, '2026-02-02 14:07:00', 1, 740.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '案例研究', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (20, '2026-02-02 14:07:00', 1, 750.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '项目总结与讨论', 14, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (21, '2026-02-02 14:07:00', 1, 600.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'Linux基础命令介绍', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (22, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '用户和权限管理', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (23, '2026-02-02 14:07:00', 1, 750.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '文件系统与磁盘管理', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (24, '2026-02-02 14:07:00', 1, 680.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '网络配置与故障排除', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (25, '2026-02-02 14:07:00', 1, 720.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '服务部署与管理', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (26, '2026-02-02 14:07:00', 1, 650.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '安全管理策略', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (27, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '备份与恢复策略', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (28, '2026-02-02 14:07:00', 1, 710.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '监控与性能调优', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (29, '2026-02-02 14:07:00', 1, 730.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '自动化运维工具', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (30, '2026-02-02 14:07:00', 1, 740.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '项目实战与总结', 15, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (31, '2026-02-02 14:07:00', 1, 600.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '数据库安全基础', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (32, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '身份验证与访问控制', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (33, '2026-02-02 14:07:00', 1, 750.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '数据加密技术', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (34, '2026-02-02 14:07:00', 1, 680.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '审计与日志管理', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (35, '2026-02-02 14:07:00', 1, 720.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '漏洞检测与修复', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (36, '2026-02-02 14:07:00', 1, 650.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '灾难恢复计划', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (37, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'SQL注入防护', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (38, '2026-02-02 14:07:00', 1, 710.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '数据库防火墙设置', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (39, '2026-02-02 14:07:00', 1, 730.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '最佳实践案例分析', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (40, '2026-02-02 14:07:00', 1, 740.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '综合演练与总结', 16, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (41, '2026-02-02 14:07:00', 1, 600.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '数据驱动决策概述', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (42, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '数据分析工具介绍', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (43, '2026-02-02 14:07:00', 1, 750.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '用户行为分析', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (44, '2026-02-02 14:07:00', 1, 680.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '市场趋势预测', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (45, '2026-02-02 14:07:00', 1, 720.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '产品迭代策略', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (46, '2026-02-02 14:07:00', 1, 650.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'A/B测试方法论', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (47, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '用户体验优化', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (48, '2026-02-02 14:07:00', 1, 710.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '数据可视化技巧', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (49, '2026-02-02 14:07:00', 1, 730.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '案例研究与讨论', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (50, '2026-02-02 14:07:00', 1, 740.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '项目总结与展望', 17, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (51, '2026-02-02 14:07:00', 1, 600.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '微服务架构简介', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (52, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'Spring Boot入门', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (53, '2026-02-02 14:07:00', 1, 750.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '服务发现与注册', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (54, '2026-02-02 14:07:00', 1, 680.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'API网关实现', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (55, '2026-02-02 14:07:00', 1, 720.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '配置中心使用', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (56, '2026-02-02 14:07:00', 1, 650.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '容错处理与断路器', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (57, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '消息队列集成', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (58, '2026-02-02 14:07:00', 1, 710.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '安全与认证机制', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (59, '2026-02-02 14:07:00', 1, 730.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '部署与运维', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (60, '2026-02-02 14:07:00', 1, 740.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '项目实战与总结', 18, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (61, '2026-02-02 14:07:00', 1, 600.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '性能优化基础', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (62, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '内存管理技巧', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (63, '2026-02-02 14:07:00', 1, 750.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', 'UI渲染优化', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (64, '2026-02-02 14:07:00', 1, 680.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '响应速度提升', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (65, '2026-02-02 14:07:00', 1, 720.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '电量消耗优化', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (66, '2026-02-02 14:07:00', 1, 650.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '网络请求优化', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (67, '2026-02-02 14:07:00', 1, 700.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '调试与分析工具', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (68, '2026-02-02 14:07:00', 1, 710.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '代码质量提高', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (69, '2026-02-02 14:07:00', 1, 730.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '实际案例分析', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (70, '2026-02-02 14:07:00', 1, 740.00000, 'http://localhost:7245/1738477918774/20250202_143127.mp4', '项目总结与未来方向', 19, 'http://localhost:7245/1738477918774/20250202_143127.mp4', NULL);
INSERT INTO `cousestep` VALUES (71, '2026-02-02 14:07:00', 1, 18000.00000, 'http://localhost:7245/1776167119392/高木.mp4', '机器学习基础概念', 20, 'http://localhost:7245/1776166908062/高木.mp4', NULL);
INSERT INTO `cousestep` VALUES (82, '2026-04-14 22:00:42', 21, 30000.00000, 'http://localhost:7245/1776175239400/1101220.mp4', 'Android UiAutomator 2.0第一节', 21, 'http://localhost:7245/1776175241798/1101220.mp4', NULL);
INSERT INTO `cousestep` VALUES (83, '2026-04-14 22:01:32', 21, 87187.10000, 'http://localhost:7245/1776175288934/重新开始2.mp4', 'Linux基础入门第一节', 1, 'http://localhost:7245/1776175291231/重新开始2.mp4', NULL);

-- ----------------------------
-- Table structure for cousestudyschedule
-- ----------------------------
DROP TABLE IF EXISTS `cousestudyschedule`;
CREATE TABLE `cousestudyschedule`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程学习进度主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `LastUploadTime` datetime NULL DEFAULT NULL COMMENT '最后上传时间',
  `StudyUserId` int NULL DEFAULT NULL COMMENT '学习人',
  `CouseId` int NULL DEFAULT NULL COMMENT '所属课程',
  `BeginTime` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `Durtion` double(20, 5) NULL DEFAULT NULL COMMENT '学习时长',
  `CouseStepId` int NULL DEFAULT NULL COMMENT '所属章节',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程学习进度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cousestudyschedule
-- ----------------------------
INSERT INTO `cousestudyschedule` VALUES (1, '2026-02-02 14:07:00', 2, '2026-02-02 14:07:00', 2, 20, '2026-02-02 14:07:00', 4001.00000, 71);
INSERT INTO `cousestudyschedule` VALUES (2, '2026-04-14 19:43:44', 21, '2026-04-14 19:43:44', 21, 1, '2026-04-14 19:43:44', 9001.00000, 81);
INSERT INTO `cousestudyschedule` VALUES (3, '2026-04-14 19:44:24', 21, '2026-04-14 19:44:24', 21, 1, '2026-04-14 19:44:24', 1001.00000, 81);
INSERT INTO `cousestudyschedule` VALUES (4, '2026-04-14 19:44:25', 21, '2026-04-14 19:44:25', 21, 1, '2026-04-14 19:44:25', 1001.00000, 81);
INSERT INTO `cousestudyschedule` VALUES (5, '2026-04-14 19:44:26', 21, '2026-04-14 19:44:26', 21, 1, '2026-04-14 19:44:26', 1001.00000, 81);
INSERT INTO `cousestudyschedule` VALUES (6, '2026-04-14 19:44:27', 21, '2026-04-14 19:44:27', 21, 1, '2026-04-14 19:44:27', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (7, '2026-04-14 19:44:29', 21, '2026-04-14 19:44:29', 21, 1, '2026-04-14 19:44:29', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (8, '2026-04-14 19:44:31', 21, '2026-04-14 19:44:31', 21, NULL, '2026-04-14 19:44:31', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (9, '2026-04-14 19:44:33', 21, '2026-04-14 19:44:33', 21, 1, '2026-04-14 19:44:33', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (10, '2026-04-14 19:44:35', 21, '2026-04-14 19:44:35', 21, NULL, '2026-04-14 19:44:35', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (11, '2026-04-14 19:44:37', 21, '2026-04-14 19:44:37', 21, 1, '2026-04-14 19:44:37', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (12, '2026-04-14 19:44:39', 21, '2026-04-14 19:44:39', 21, NULL, '2026-04-14 19:44:39', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (13, '2026-04-14 19:44:41', 21, '2026-04-14 19:44:41', 21, NULL, '2026-04-14 19:44:41', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (14, '2026-04-14 19:44:43', 21, '2026-04-14 19:44:43', 21, 20, '2026-04-14 19:44:43', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (15, '2026-04-14 19:44:45', 21, '2026-04-14 19:44:45', 21, 20, '2026-04-14 19:44:45', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (16, '2026-04-14 19:44:48', 21, '2026-04-14 19:44:48', 21, 20, '2026-04-14 19:44:48', 1001.00000, NULL);
INSERT INTO `cousestudyschedule` VALUES (17, '2026-04-14 20:46:33', 21, '2026-04-14 20:46:32', 21, 20, '2026-04-14 20:46:32', 1.00000, 71);

-- ----------------------------
-- Table structure for cousetype
-- ----------------------------
DROP TABLE IF EXISTS `cousetype`;
CREATE TABLE `cousetype`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '课程分类主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cousetype
-- ----------------------------
INSERT INTO `cousetype` VALUES (1, '2026-02-02 13:51:45', 1, 'Liunx');
INSERT INTO `cousetype` VALUES (2, '2026-02-02 13:51:58', 1, '数据库');
INSERT INTO `cousetype` VALUES (3, '2026-02-02 13:52:04', 1, '产品经理');
INSERT INTO `cousetype` VALUES (4, '2026-02-02 13:52:10', 1, 'Java');
INSERT INTO `cousetype` VALUES (5, '2026-02-02 13:52:16', 1, 'Android');
INSERT INTO `cousetype` VALUES (6, '2026-02-02 13:52:24', 1, 'Python');

-- ----------------------------
-- Table structure for judgmentquestion
-- ----------------------------
DROP TABLE IF EXISTS `judgmentquestion`;
CREATE TABLE `judgmentquestion`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '判断题型主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类',
  `AnalyzeResult` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '解析',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题干',
  `RightAnswer` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正确答案',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '判断题型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of judgmentquestion
-- ----------------------------
INSERT INTO `judgmentquestion` VALUES (1, '2026-02-05 08:58:00', 1, 'HTML简介', 'HTML文档必须以DOCTYPE声明开始，这是告诉浏览器文档使用哪个HTML版本的方式。', 'HTML文档必须以DOCTYPE声明开始。', 'true');
INSERT INTO `judgmentquestion` VALUES (2, '2026-02-05 08:58:00', 1, 'HTML语法', '在HTML中，属性值应该用引号包围，这可以是单引号或双引号。', 'HTML属性值必须用引号包围。', 'true');
INSERT INTO `judgmentquestion` VALUES (3, '2026-02-05 08:58:00', 1, 'HTML元素', '段落、块级容器、短文本容器和文章内容都可以用不同的HTML元素表示。', '所有HTML元素都是块级元素。', 'false');
INSERT INTO `judgmentquestion` VALUES (4, '2026-02-05 08:58:00', 1, 'HTML属性', 'id和class属性用于标识和分组元素，而style属性用于内联样式定义。', 'HTML元素可以有多个id属性。', 'false');
INSERT INTO `judgmentquestion` VALUES (5, '2026-02-05 08:58:00', 1, 'HTML标题', 'HTML支持六种级别的标题，从<h1>到<h6>，其中<h1>是最重要的。', '<h1>是HTML中最不重要的标题标签。', 'false');
INSERT INTO `judgmentquestion` VALUES (6, '2026-02-05 08:58:00', 1, 'HTML段落', '除了<p>标签，<br>和<hr>标签也常用于控制文本格式。', '<p>标签用于定义文本段落。', 'true');
INSERT INTO `judgmentquestion` VALUES (7, '2026-02-05 08:58:00', 1, 'HTML链接', '超链接可以通过<a>标签创建，而<form>标签用于创建交互式表单。', '超链接只能通过<a>标签创建。', 'true');
INSERT INTO `judgmentquestion` VALUES (8, '2026-02-05 08:58:00', 1, 'HTML图片', '除了<img>标签，<video>, <audio>和<iframe>标签也可以嵌入多媒体内容。', '图片只能通过<img>标签嵌入。', 'false');
INSERT INTO `judgmentquestion` VALUES (9, '2026-02-05 08:58:00', 1, 'HTML列表', '无序列表、有序列表和定义列表分别由<ul>, <ol>和<dl>标签创建。', '只有<ul>标签可以创建列表。', 'false');
INSERT INTO `judgmentquestion` VALUES (10, '2026-02-05 08:58:00', 1, 'HTML表格', 'HTML表格结构包括<table>, <thead>, <tbody>和<tfoot>标签。', 'HTML表格必须包含<thead>, <tbody>和<tfoot>标签。', 'false');
INSERT INTO `judgmentquestion` VALUES (11, '2026-02-05 08:58:00', 1, 'CSS简介', 'CSS主要用于描述HTML文档的样式，因此它是一种样式语言。', 'CSS主要用于定义网页的布局和外观。', 'true');
INSERT INTO `judgmentquestion` VALUES (12, '2026-02-05 08:58:00', 1, 'CSS语法', 'CSS语法包含选择器、规则、声明和属性，其中选择器用于指定要应用样式的元素。', 'CSS规则由选择器和声明组成。', 'true');
INSERT INTO `judgmentquestion` VALUES (13, '2026-02-05 08:58:00', 1, 'CSS选择器', 'CSS提供了多种选择器来定位HTML元素，如类选择器、ID选择器、标签选择器和伪类选择器。', 'ID选择器可以用在多个元素上。', 'false');
INSERT INTO `judgmentquestion` VALUES (14, '2026-02-05 08:58:00', 1, 'CSS盒模型', 'CSS盒模型包括元素的内容区、内边距、边框和外边距。', 'CSS盒模型只包括内容区和边框。', 'false');
INSERT INTO `judgmentquestion` VALUES (15, '2026-02-05 08:58:00', 1, 'CSS背景', 'CSS背景属性可以设置背景颜色、图像、重复方式、位置等。', 'CSS背景属性只能设置背景颜色。', 'false');
INSERT INTO `judgmentquestion` VALUES (16, '2026-02-05 08:58:00', 1, 'CSS边框', 'CSS边框样式属性允许你设置边框的样式，如实线、虚线、点线和双线。', 'CSS边框样式可以设置为实线。', 'true');
INSERT INTO `judgmentquestion` VALUES (17, '2026-02-05 08:58:00', 1, 'CSS外边距', '外边距（margin）用于控制元素与其他元素之间的空间。', '外边距（margin）会影响元素内部内容的布局。', 'false');
INSERT INTO `judgmentquestion` VALUES (18, '2026-02-05 08:58:00', 1, 'CSS内边距', '内边距（padding）用于控制元素内容与边框之间的空间。', '内边距（padding）会影响元素与其他元素之间的距离。', 'false');
INSERT INTO `judgmentquestion` VALUES (19, '2026-02-05 08:58:00', 1, 'JavaScript简介', 'JavaScript是一种解释型语言，通常用于网页上的动态效果。', 'JavaScript需要编译后才能在浏览器中运行。', 'false');
INSERT INTO `judgmentquestion` VALUES (20, '2026-02-05 08:58:00', 1, 'JavaScript DOM操作', 'JavaScript可以用来改变HTML文档的结构和内容，这就是所谓的DOM操作。', 'JavaScript不能直接修改HTML文档的结构。', 'false');

-- ----------------------------
-- Table structure for multiplechoicequestion
-- ----------------------------
DROP TABLE IF EXISTS `multiplechoicequestion`;
CREATE TABLE `multiplechoicequestion`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '多选题主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `OptionA` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项A',
  `OptionC` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项C',
  `Type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类',
  `AnalyzeResult` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '解析',
  `OptionD` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项D',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题干',
  `RightAnswer` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正确答案',
  `OptionB` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项B',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '多选题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of multiplechoicequestion
-- ----------------------------
INSERT INTO `multiplechoicequestion` VALUES (1, '2026-02-05 08:58:00', 1, 'DOCTYPE', 'body', 'HTML简介', 'HTML文档必须以DOCTYPE声明开始，这是告诉浏览器文档使用哪个HTML版本的方式。', 'head', '以下哪些是HTML文档的必要组成部分？', 'A,B', 'html');
INSERT INTO `multiplechoicequestion` VALUES (2, '2026-02-05 08:58:00', 1, '空格', '引号', 'HTML语法', '在HTML中，属性值应该用引号包围，这可以是单引号或双引号。', '斜杠', 'HTML属性值应该用什么包围？', 'C', '换行符');
INSERT INTO `multiplechoicequestion` VALUES (3, '2026-02-05 08:58:00', 1, 'p', 'span', 'HTML元素', '段落、块级容器、短文本容器和文章内容都可以用不同的HTML元素表示。', 'article', '以下哪些是HTML中的块级元素？', 'A,B,D', 'div');
INSERT INTO `multiplechoicequestion` VALUES (4, '2026-02-05 08:58:00', 1, 'id', 'style', 'HTML属性', 'id和class属性用于标识和分组元素，而style属性用于内联样式定义。', 'src', '以下哪些是HTML元素的常用属性？', 'A,B,C', 'class');
INSERT INTO `multiplechoicequestion` VALUES (5, '2026-02-05 08:58:00', 1, 'h1', 'h3', 'HTML标题', 'HTML支持六种级别的标题，从<h1>到<h6>，其中<h1>是最重要的。', 'h4', '以下哪些是有效的HTML标题标签？', 'A,B,C,D', 'h2');
INSERT INTO `multiplechoicequestion` VALUES (6, '2026-02-05 08:58:00', 1, 'p', 'hr', 'HTML段落', '除了<p>标签，<br>和<hr>标签也常用于控制文本格式。', 'pre', '以下哪些标签可以用于文本格式化？', 'A,B,C,D', 'br');
INSERT INTO `multiplechoicequestion` VALUES (7, '2026-02-05 08:58:00', 1, 'a', 'button', 'HTML链接', '超链接可以通过<a>标签创建，而<form>标签用于创建交互式表单。', 'form', '以下哪些标签可以用于创建链接或表单？', 'A,D', 'link');
INSERT INTO `multiplechoicequestion` VALUES (8, '2026-02-05 08:58:00', 1, 'img', 'audio', 'HTML图片', '除了<img>标签，<video>, <audio>和<iframe>标签也可以嵌入多媒体内容。', 'iframe', '以下哪些标签可以用于嵌入多媒体内容？', 'A,B,C,D', 'video');
INSERT INTO `multiplechoicequestion` VALUES (9, '2026-02-05 08:58:00', 1, 'ul', 'dl', 'HTML列表', '无序列表、有序列表和定义列表分别由<ul>, <ol>和<dl>标签创建。', 'menu', '以下哪些是HTML中的列表类型？', 'A,B,C', 'ol');
INSERT INTO `multiplechoicequestion` VALUES (10, '2026-02-05 08:58:00', 1, 'table', 'tbody', 'HTML表格', 'HTML表格结构包括<table>, <thead>, <tbody>和<tfoot>标签。', 'tfoot', '以下哪些标签用于构建HTML表格？', 'A,B,C,D', 'thead');
INSERT INTO `multiplechoicequestion` VALUES (11, '2026-02-05 08:58:00', 1, '样式语言', '脚本语言', 'CSS简介', 'CSS主要用于描述HTML文档的样式，因此它是一种样式语言。', '编程语言', 'CSS属于哪种类型的语言？', 'A', '标记语言');
INSERT INTO `multiplechoicequestion` VALUES (12, '2026-02-05 08:58:00', 1, '选择器', '声明', 'CSS语法', 'CSS语法包含选择器、规则、声明和属性，其中选择器用于指定要应用样式的元素。', '属性', '以下哪些是CSS语法的基本组成部分？', 'A,B,C,D', '规则');
INSERT INTO `multiplechoicequestion` VALUES (13, '2026-02-05 08:58:00', 1, '类选择器', '标签选择器', 'CSS选择器', 'CSS提供了多种选择器来定位HTML元素，如类选择器、ID选择器、标签选择器和伪类选择器。', '伪类选择器', '以下哪些是常见的CSS选择器？', 'A,B,C,D', 'ID选择器');
INSERT INTO `multiplechoicequestion` VALUES (14, '2026-02-05 08:58:00', 1, '宽度和高度', '边框', 'CSS盒模型', 'CSS盒模型包括元素的内容区、内边距、边框和外边距。', '位置', 'CSS盒模型包括哪些部分？', 'A,B,C', '边距和填充');
INSERT INTO `multiplechoicequestion` VALUES (15, '2026-02-05 08:58:00', 1, '颜色', '渐变', 'CSS背景', 'CSS背景属性可以设置背景颜色、图像、重复方式、位置等。', '视频', '以下哪些可以作为CSS背景属性的值？', 'A,B,C', '图像');
INSERT INTO `multiplechoicequestion` VALUES (16, '2026-02-05 08:58:00', 1, '实线', '点线', 'CSS边框', 'CSS边框样式属性允许你设置边框的样式，如实线、虚线、点线和双线。', '双线', '以下哪些是CSS边框的样式选项？', 'A,B,C,D', '虚线');
INSERT INTO `multiplechoicequestion` VALUES (17, '2026-02-05 08:58:00', 1, 'margin', 'border', 'CSS外边距', '外边距（margin）用于控制元素与其他元素之间的空间。', 'outline', '以下哪些属性用于控制元素的外部空间？', 'A', 'padding');
INSERT INTO `multiplechoicequestion` VALUES (18, '2026-02-05 08:58:00', 1, 'margin', 'border', 'CSS内边距', '内边距（padding）用于控制元素内容与边框之间的空间。', 'outline', '以下哪些属性用于控制元素的内部空间？', 'B', 'padding');
INSERT INTO `multiplechoicequestion` VALUES (19, '2026-02-05 08:58:00', 1, '解释型语言', '标记语言', 'JavaScript简介', 'JavaScript是一种解释型语言，通常用于网页上的动态效果。', '样式语言', 'JavaScript属于哪种类型的语言？', 'A', '编译型语言');
INSERT INTO `multiplechoicequestion` VALUES (20, '2026-02-05 08:58:00', 1, '变量', '事件', 'JavaScript DOM操作', 'JavaScript可以用来改变HTML文档的结构和内容，这就是所谓的DOM操作。', 'DOM操作', '以下哪些是JavaScript可以执行的操作？', 'A,B,C,D', '函数');

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '问卷主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷标题',
  `LimitMinute` int NULL DEFAULT NULL COMMENT '分钟',
  `BeginTestTime` datetime NULL DEFAULT NULL COMMENT '起始考试时间',
  `SmallTitle` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '小标题',
  `Type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷类型',
  `PublishUserId` int NULL DEFAULT NULL COMMENT '发布人',
  `EndTestTime` datetime NULL DEFAULT NULL COMMENT '截至考试时间',
  `IsTest` tinyint(1) NULL DEFAULT NULL COMMENT '是否考试',
  `IsTimeLimit` tinyint(1) NULL DEFAULT NULL COMMENT '是否限时',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `PublishUserId`(`PublishUserId` ASC) USING BTREE,
  CONSTRAINT `questionnaire_ibfk_1` FOREIGN KEY (`PublishUserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '问卷表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questionnaire
-- ----------------------------
INSERT INTO `questionnaire` VALUES (1, '2026-02-05 08:58:00', 1, 'HTML基础知识测试', 60, NULL, '难度中等适合所有学生测试', 'HTML简介', 1, NULL, NULL, 1);
INSERT INTO `questionnaire` VALUES (4, '2026-02-05 08:58:00', 1, '自动组卷测试', 90, NULL, '自动组卷', 'HTML简介,HTML语法,HTML元素,HTML属性', 1, NULL, NULL, 1);
INSERT INTO `questionnaire` VALUES (5, '2026-02-05 08:58:00', 1, 'HTML基础知识试卷', 60, NULL, '基础小白都可以', 'HTML简介,HTML元素,HTML标题', 1, NULL, NULL, 1);

-- ----------------------------
-- Table structure for questionnairedet
-- ----------------------------
DROP TABLE IF EXISTS `questionnairedet`;
CREATE TABLE `questionnairedet`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '问卷明细主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Score` double(20, 5) NULL DEFAULT NULL COMMENT '分数',
  `QuestionType` int NULL DEFAULT NULL COMMENT '题型枚举',
  `QuestionnaireId` int NULL DEFAULT NULL COMMENT '问卷',
  `QuestionId` int NULL DEFAULT NULL COMMENT '题目',
  `Sort` int NULL DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `QuestionnaireId`(`QuestionnaireId` ASC) USING BTREE,
  CONSTRAINT `questionnairedet_ibfk_1` FOREIGN KEY (`QuestionnaireId`) REFERENCES `questionnaire` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '问卷明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questionnairedet
-- ----------------------------
INSERT INTO `questionnairedet` VALUES (6, '2026-01-02 13:27:05', 1, 4.00000, 1, 1, 2, 1);
INSERT INTO `questionnairedet` VALUES (7, '2026-01-02 13:27:05', 1, 4.00000, 1, 1, 3, 2);
INSERT INTO `questionnairedet` VALUES (8, '2026-01-02 13:27:05', 1, 4.00000, 2, 1, 2, 3);
INSERT INTO `questionnairedet` VALUES (9, '2026-01-02 13:27:05', 1, 4.00000, 2, 1, 3, 4);
INSERT INTO `questionnairedet` VALUES (10, '2026-01-02 13:27:05', 1, 4.00000, 3, 1, 3, 5);
INSERT INTO `questionnairedet` VALUES (11, '2026-01-02 13:27:05', 1, 4.00000, 3, 1, 4, 6);
INSERT INTO `questionnairedet` VALUES (12, '2026-01-02 13:27:05', 1, 10.00000, 4, 1, 1, 7);
INSERT INTO `questionnairedet` VALUES (13, '2026-01-02 13:27:05', 1, 10.00000, 4, 1, 2, 8);
INSERT INTO `questionnairedet` VALUES (20, '2026-01-02 13:27:05', 1, NULL, 1, 4, 2, NULL);
INSERT INTO `questionnairedet` VALUES (21, '2026-01-02 13:27:05', 1, NULL, 1, 4, 5, NULL);
INSERT INTO `questionnairedet` VALUES (22, '2026-01-02 13:27:05', 1, NULL, 1, 4, 6, NULL);
INSERT INTO `questionnairedet` VALUES (23, '2026-01-02 13:27:05', 1, NULL, 1, 4, 7, NULL);
INSERT INTO `questionnairedet` VALUES (24, '2026-01-02 13:27:05', 1, NULL, 1, 4, 8, NULL);
INSERT INTO `questionnairedet` VALUES (25, '2026-01-02 13:27:05', 1, NULL, 1, 4, 9, NULL);
INSERT INTO `questionnairedet` VALUES (26, '2026-01-02 13:27:05', 1, NULL, 1, 4, 10, NULL);
INSERT INTO `questionnairedet` VALUES (27, '2026-01-02 13:27:05', 1, NULL, 2, 4, 8, NULL);
INSERT INTO `questionnairedet` VALUES (28, '2026-01-02 13:27:05', 1, NULL, 2, 4, 6, NULL);
INSERT INTO `questionnairedet` VALUES (29, '2026-01-02 13:27:05', 1, NULL, 2, 4, 14, NULL);
INSERT INTO `questionnairedet` VALUES (30, '2026-01-02 13:27:05', 1, NULL, 2, 4, 2, NULL);
INSERT INTO `questionnairedet` VALUES (31, '2026-01-02 13:27:05', 1, NULL, 2, 4, 16, NULL);
INSERT INTO `questionnairedet` VALUES (32, '2026-01-02 13:27:05', 1, NULL, 2, 4, 17, NULL);
INSERT INTO `questionnairedet` VALUES (33, '2026-01-02 13:27:05', 1, NULL, 2, 4, 5, NULL);
INSERT INTO `questionnairedet` VALUES (34, '2026-01-02 13:27:05', 1, NULL, 2, 4, 20, NULL);
INSERT INTO `questionnairedet` VALUES (35, '2026-01-02 13:27:05', 1, NULL, 2, 4, 7, NULL);
INSERT INTO `questionnairedet` VALUES (36, '2026-01-02 13:27:05', 1, NULL, 2, 4, 11, NULL);
INSERT INTO `questionnairedet` VALUES (37, '2026-01-02 13:27:05', 1, NULL, 3, 4, 10, NULL);
INSERT INTO `questionnairedet` VALUES (38, '2026-01-02 13:27:05', 1, NULL, 3, 4, 18, NULL);
INSERT INTO `questionnairedet` VALUES (39, '2026-01-02 13:27:05', 1, NULL, 3, 4, 17, NULL);
INSERT INTO `questionnairedet` VALUES (40, '2026-01-02 13:27:05', 1, NULL, 3, 4, 13, NULL);
INSERT INTO `questionnairedet` VALUES (41, '2026-01-02 13:27:05', 1, NULL, 3, 4, 15, NULL);
INSERT INTO `questionnairedet` VALUES (42, '2026-01-02 13:27:05', 1, NULL, 3, 4, 7, NULL);
INSERT INTO `questionnairedet` VALUES (43, '2026-01-02 13:27:05', 1, NULL, 3, 4, 16, NULL);
INSERT INTO `questionnairedet` VALUES (44, '2026-01-02 13:27:05', 1, NULL, 3, 4, 1, NULL);
INSERT INTO `questionnairedet` VALUES (45, '2026-01-02 13:27:05', 1, NULL, 3, 4, 3, NULL);
INSERT INTO `questionnairedet` VALUES (46, '2026-01-02 13:27:05', 1, NULL, 3, 4, 8, NULL);
INSERT INTO `questionnairedet` VALUES (62, '2026-01-02 13:27:05', 1, 4.00000, 1, 5, 9, 1);
INSERT INTO `questionnairedet` VALUES (63, '2026-01-02 13:27:05', 1, 4.00000, 1, 5, 7, 2);
INSERT INTO `questionnairedet` VALUES (64, '2026-01-02 13:27:05', 1, 4.00000, 1, 5, 10, 3);
INSERT INTO `questionnairedet` VALUES (65, '2026-01-02 13:27:05', 1, 4.00000, 1, 5, 8, 4);
INSERT INTO `questionnairedet` VALUES (66, '2026-01-02 13:27:05', 1, 4.00000, 1, 5, 5, 5);
INSERT INTO `questionnairedet` VALUES (67, '2026-01-02 13:27:05', 1, 4.00000, 2, 5, 13, 6);
INSERT INTO `questionnairedet` VALUES (68, '2026-01-02 13:27:05', 1, 4.00000, 2, 5, 15, 7);
INSERT INTO `questionnairedet` VALUES (69, '2026-01-02 13:27:05', 1, 4.00000, 2, 5, 11, 8);
INSERT INTO `questionnairedet` VALUES (70, '2026-01-02 13:27:05', 1, 4.00000, 2, 5, 12, 9);
INSERT INTO `questionnairedet` VALUES (71, '2026-01-02 13:27:05', 1, 4.00000, 2, 5, 17, 10);
INSERT INTO `questionnairedet` VALUES (72, '2026-01-02 13:27:05', 1, 4.00000, 3, 5, 13, 11);
INSERT INTO `questionnairedet` VALUES (73, '2026-01-02 13:27:05', 1, 4.00000, 3, 5, 7, 12);
INSERT INTO `questionnairedet` VALUES (74, '2026-01-02 13:27:05', 1, 4.00000, 3, 5, 1, 13);
INSERT INTO `questionnairedet` VALUES (75, '2026-01-02 13:27:05', 1, 4.00000, 3, 5, 12, 14);
INSERT INTO `questionnairedet` VALUES (76, '2026-01-02 13:27:05', 1, 4.00000, 3, 5, 9, 15);

-- ----------------------------
-- Table structure for questionnairerelativehuser
-- ----------------------------
DROP TABLE IF EXISTS `questionnairerelativehuser`;
CREATE TABLE `questionnairerelativehuser`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '问卷指定用户主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `UserId` int NULL DEFAULT NULL COMMENT '用户',
  `QuestionnaireId` int NULL DEFAULT NULL COMMENT '问卷',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '问卷指定用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questionnairerelativehuser
-- ----------------------------

-- ----------------------------
-- Table structure for singlechoisequestion
-- ----------------------------
DROP TABLE IF EXISTS `singlechoisequestion`;
CREATE TABLE `singlechoisequestion`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '单选题主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `AnalyzeResult` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '解析',
  `OptionD` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项D',
  `RightAnswer` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '正确答案',
  `OptionC` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项C',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题干',
  `OptionB` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项B',
  `OptionA` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项A',
  `Type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '单选题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of singlechoisequestion
-- ----------------------------
INSERT INTO `singlechoisequestion` VALUES (1, '2026-02-02 08:58:00', 1, 'HTML文档必须包含一个根元素，通常是<html>标签。', '无', 'A', 'body', 'HTML文档的根元素是什么？', 'head', 'html', 'HTML简介');
INSERT INTO `singlechoisequestion` VALUES (2, '2026-02-02 08:58:00', 1, 'HTML元素由开始标签、内容和结束标签组成。', '注释', 'B', '空元素', '以下哪项不是标准的HTML元素组成部分？', '结束标签', '开始标签', 'HTML元素');
INSERT INTO `singlechoisequestion` VALUES (3, '2026-02-02 08:58:00', 1, 'HTML属性提供了关于元素的额外信息。', 'class', 'C', 'href', '哪个属性用于定义超链接的目标URL？', 'src', 'id', 'HTML属性');
INSERT INTO `singlechoisequestion` VALUES (4, '2026-02-02 08:58:00', 1, '标题标签<h1>到<h6>用于定义文档的标题。', 'h1', 'B', 'p', '哪个标签用于定义最大的标题？', 'h6', 'h2', 'HTML标题');
INSERT INTO `singlechoisequestion` VALUES (5, '2026-02-02 08:58:00', 1, '段落标签<p>用于定义文本段落。', 'div', 'A', 'span', '哪个标签用于定义文本段落？', 'article', 'p', 'HTML段落');
INSERT INTO `singlechoisequestion` VALUES (6, '2026-02-02 08:58:00', 1, '超链接使用<a>标签创建。', 'button', 'C', 'img', '哪个标签用于创建超链接？', 'a', 'link', 'HTML链接');
INSERT INTO `singlechoisequestion` VALUES (7, '2026-02-02 08:58:00', 1, '图片使用<img>标签嵌入。', 'video', 'B', 'audio', '哪个标签用于嵌入图片？', 'iframe', 'img', 'HTML图片');
INSERT INTO `singlechoisequestion` VALUES (8, '2026-02-02 08:58:00', 1, '无序列表使用<ul>标签，列表项使用<li>标签。', 'dl', 'A', 'ol', '哪个标签用于创建无序列表？', 'ul', 'menu', 'HTML列表');
INSERT INTO `singlechoisequestion` VALUES (9, '2026-02-02 08:58:00', 1, '表格使用<table>标签创建。', 'thead', 'C', 'tbody', '哪个标签用于创建表格？', 'table', 'tfoot', 'HTML表格');
INSERT INTO `singlechoisequestion` VALUES (10, '2026-02-02 08:58:00', 1, 'CSS用于描述HTML文档的样式。', '脚本语言', 'B', '标记语言', 'CSS是哪种类型的语言？', '样式语言', '编程语言', 'CSS简介');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '科目主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '科目名称',
  `Cover` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '描述',
  `Grade` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '科目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of subject
-- ----------------------------

-- ----------------------------
-- Table structure for subjectivequestion
-- ----------------------------
DROP TABLE IF EXISTS `subjectivequestion`;
CREATE TABLE `subjectivequestion`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '主观题主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `Content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `AnalyzeResult` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '解释',
  `Type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  `RightAnswer` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正确答案',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '主观题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of subjectivequestion
-- ----------------------------
INSERT INTO `subjectivequestion` VALUES (1, '2026-01-02 08:58:00', 1, 'HTML文档结构', '请详细描述一个标准HTML文档的基本结构，并解释每个部分的作用。', 'HTML文档的基本结构包括DOCTYPE声明、<html>根元素、<head>头部信息和<body>主体内容。DOCTYPE声明告诉浏览器使用哪个HTML版本；<html>是文档的根元素；<head>包含元数据如标题、样式和脚本；<body>包含页面的主要内容。', 'HTML简介', 'HTML文档的基本结构包括DOCTYPE声明、<html>根元素、<head>头部信息和<body>主体内容。DOCTYPE声明告诉浏览器使用哪个HTML版本；<html>是文档的根元素；<head>包含元数据如标题、样式和脚本；<body>包含页面的主要内容。');
INSERT INTO `subjectivequestion` VALUES (2, '2026-01-02 08:58:00', 1, 'HTML元素分类', '请列举并解释HTML中的块级元素和内联元素的区别。', '块级元素在网页中以块的形式显示，通常独占一行，如<p>, <div>, <h1>-<h6>等。内联元素则在同一行内显示，不会独占一行，如<a>, <span>, <img>等。', 'HTML元素', NULL);

-- ----------------------------
-- Table structure for syslabel
-- ----------------------------
DROP TABLE IF EXISTS `syslabel`;
CREATE TABLE `syslabel`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '标签主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '编码',
  `Name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签名称',
  `Sort` int NULL DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '标签表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of syslabel
-- ----------------------------
INSERT INTO `syslabel` VALUES (1, '2026-01-02 08:58:00', 1, 'html-intro', 'HTML简介', 1);
INSERT INTO `syslabel` VALUES (2, '2026-01-02 08:58:00', 1, 'html-syntax', 'HTML语法', 2);
INSERT INTO `syslabel` VALUES (3, '2026-01-02 08:58:00', 1, 'html-elements', 'HTML元素', 3);
INSERT INTO `syslabel` VALUES (4, '2026-01-02 08:58:00', 1, 'html-attributes', 'HTML属性', 4);
INSERT INTO `syslabel` VALUES (5, '2026-01-02 08:58:00', 1, 'html-headings', 'HTML标题', 5);
INSERT INTO `syslabel` VALUES (6, '2026-01-02 08:58:00', 1, 'html-paras', 'HTML段落', 6);
INSERT INTO `syslabel` VALUES (7, '2026-01-02 08:58:00', 1, 'html-links', 'HTML链接', 7);
INSERT INTO `syslabel` VALUES (8, '2026-01-02 08:58:00', 1, 'html-images', 'HTML图片', 8);
INSERT INTO `syslabel` VALUES (9, '2026-01-02 08:58:00', 1, 'html-lists', 'HTML列表', 9);
INSERT INTO `syslabel` VALUES (10, '2026-01-02 08:58:00', 1, 'html-tables', 'HTML表格', 10);
INSERT INTO `syslabel` VALUES (11, '2026-01-02 08:58:00', 1, 'css-intro', 'CSS简介', 11);
INSERT INTO `syslabel` VALUES (12, '2026-01-02 08:58:00', 1, 'css-syntax', 'CSS语法', 12);
INSERT INTO `syslabel` VALUES (13, '2026-01-02 08:58:00', 1, 'css-selectors', 'CSS选择器', 13);
INSERT INTO `syslabel` VALUES (14, '2026-01-02 08:58:00', 1, 'css-boxmodel', 'CSS盒模型', 14);
INSERT INTO `syslabel` VALUES (15, '2026-01-02 08:58:00', 1, 'css-background', 'CSS背景', 15);
INSERT INTO `syslabel` VALUES (16, '2026-01-02 08:58:00', 1, 'css-border', 'CSS边框', 16);
INSERT INTO `syslabel` VALUES (17, '2026-01-02 08:58:00', 1, 'css-margin', 'CSS外边距', 17);
INSERT INTO `syslabel` VALUES (18, '2026-01-02 08:58:00', 1, 'css-padding', 'CSS内边距', 18);
INSERT INTO `syslabel` VALUES (19, '2026-01-02 08:58:00', 1, 'js-intro', 'JavaScript简介', 19);
INSERT INTO `syslabel` VALUES (20, '2026-01-02 08:58:00', 1, 'js-syntax', 'JavaScript语法', 20);
INSERT INTO `syslabel` VALUES (21, '2026-01-02 08:58:00', 1, 'js-events', 'JavaScript事件', 39);
INSERT INTO `syslabel` VALUES (22, '2026-01-02 08:58:00', 1, 'js-dom', 'JavaScript DOM操作', 40);

-- ----------------------------
-- Table structure for sysnotice
-- ----------------------------
DROP TABLE IF EXISTS `sysnotice`;
CREATE TABLE `sysnotice`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '系统通知主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '通知内容',
  `IsPutaway` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统通知表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sysnotice
-- ----------------------------
INSERT INTO `sysnotice` VALUES (1, '2026-01-02 08:58:00', 1, '尊敬的用户，2026年春季学期的考试报名将于2025年1月15日开始，请各位同学提前做好准备，按时报名。', 1, '2026年春季学期考试报名即将开始');
INSERT INTO `sysnotice` VALUES (2, '2026-01-02 08:58:00', 1, '由于系统维护，2026年1月5日18:00至20:00期间，大学考试平台将暂时关闭。请在此期间避免使用平台，给您带来的不便敬请谅解。', 1, '系统维护通知');
INSERT INTO `sysnotice` VALUES (3, '2026-01-02 08:58:00', 1, '2026年秋季学期期末考试成绩已公布，各位同学可以登录平台查询自己的成绩。如有疑问，请联系教务处。', 1, '2026年秋季学期期末考试成绩公布');
INSERT INTO `sysnotice` VALUES (4, '2026-01-02 08:58:00', 1, '根据学校安排，2026年春季学期开学时间为2025年2月20日，请各位同学按时返校报到。', 0, '2026年春季学期开学通知');
INSERT INTO `sysnotice` VALUES (5, '2026-01-02 08:58:00', 1, '大学考试平台将于2026年1月10日进行版本更新，更新后将增加在线考试功能和优化用户体验。', 1, '平台版本更新通知');
INSERT INTO `sysnotice` VALUES (7, '2026-01-02 08:58:00', 1, '为了提高考试安全性，自2025年1月10日起，所有在线考试将启用监考摄像头监控，请各位考生提前准备好设备。', 1, '在线考试安全措施更新');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '考试主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `QuestionnaireId` int NULL DEFAULT NULL COMMENT '问卷',
  `BeginActiveTime` datetime NULL DEFAULT NULL COMMENT '起始有效时间',
  `EndActiveTime` datetime NULL DEFAULT NULL COMMENT '截至有效时间',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `IsRadom` tinyint(1) NULL DEFAULT NULL COMMENT '是否随机打乱',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '考试表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '2026-01-02 13:26:30', 1, 1, '2026-01-02 00:00:00', '2026-02-19 00:00:00', '2026年1月月末HTML测试', 1);
INSERT INTO `test` VALUES (2, '2026-02-02 15:26:56', 1, 5, '2026-02-01 00:00:00', '2026-03-31 00:00:00', '2026年最新HTML测试', 0);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `IsRecommand` tinyint(1) NULL DEFAULT NULL COMMENT '是否推荐',
  `Title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `AuditStatus` int NULL DEFAULT NULL COMMENT '审核状态枚举',
  `ViewCount` int NULL DEFAULT NULL COMMENT '浏览次数',
  `Cover` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `ImageUrls` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '主图',
  `TopicTypeId` int NULL DEFAULT NULL COMMENT '话题类型',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '话题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (4, '2026-02-02 15:07:24', 2, 1, '1文告诉你人工智能纯小白学习路线🔥', 2, 4, 'http://localhost:7245/1738480141540/1️⃣文告诉你人工智能纯小白学习路线🔥_1_Jack Bytes_来自小红书网页版.jpg', 'http://localhost:7245/1738480146234/1️⃣文告诉你人工智能纯小白学习路线🔥_2_Jack Bytes_来自小红书网页版.jpg,http://localhost:7245/1738480147750/1️⃣文告诉你人工智能纯小白学习路线🔥_1_Jack Bytes_来自小红书网页版.jpg', 10, '<p><span style=\"font-family: system-ui, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;, -apple-system, &quot;Segoe UI&quot;, Roboto, Ubuntu, Cantarell, &quot;Noto Sans&quot;, sans-serif, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, Arial, &quot;PingFang SC&quot;, &quot;PingFang TC&quot;, &quot;PingFang HK&quot;, &quot;Microsoft Yahei&quot;, &quot;Microsoft JhengHei&quot;; color: rgb(51, 51, 51); font-size: 16px\">一、编程语言基础 0️⃣ Python 语言学习 - B 站：黑马程序员 Python 教程🚀 1️⃣ Shell 脚本常用命令 - B 站：黑马程序员 Linux 教程☀️ 2️⃣ Pytorch 学习 - B 站：Pytorch 深度学习快速入门🐵 3️⃣ PaddlePaddle 学习（和 Pytorch 二选一） - B 站：PaddlePaddle 实战教程🐶 二、人工智能专业基础 0️⃣ 机器学习 - B 站：吴恩达机器学习🤔 - B 站：李宏毅2023 机器学习课程😇 - 书籍：周志华的西瓜书、李沐的深度学习🤣 1️⃣ 深度学习 - B 站：跟李沐学 AI🥰 三、人工智能专业进阶 0️⃣ 生成式 AI 课程 - 吴恩达：生成式 AI 入门课程视频🐵 - 微软：生成式 AI 初学课程🔥 - 英伟达：生成式 AI 课程🫣 1️⃣ 项目实战 - Github：Ollama、gpt_academic、Dify、open-webui 等等😁 - Kaggle：参加各种比赛屠榜🙃 - 国际会议：参加各种国际会议刷榜😛 四、软件安装 0️⃣ Anaconda ：直接在清华源下载 Anaconda 安装包，一键安装即可，如果不会的可以百度或谷歌。Anaconda 预置了很多科学计算库，并且环境可以迁移，能够极大的提升环境安装的效率。 1️⃣ Vscode：很多博主推荐的是 pycharm，但是我觉得 Vscode 更好用一些，可以自定义非常多功能。具体安装方法可以自行百度或谷歌。 2️⃣ 安装部署 gpt_academic：可以快速精读论文，极大的提高论文阅读效率。感兴趣的话可以点赞收藏关注，后续出手把手部署教程。</span></p>');
INSERT INTO `topic` VALUES (13, '2026-04-14 20:59:58', 21, 1, '🇲🇾 自学编程需要多久？', 2, 8, 'http://localhost:7245/1776171592318/c347ebf1fae7da4df4f8cea492ef391b.jpg', 'http://localhost:7245/1776171595120/c347ebf1fae7da4df4f8cea492ef391b.jpg', 10, '<p><span style=\"font-family: system-ui, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;, -apple-system, &quot;Segoe UI&quot;, Roboto, Ubuntu, Cantarell, &quot;Noto Sans&quot;, sans-serif, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, Arial, &quot;PingFang SC&quot;, &quot;PingFang TC&quot;, &quot;PingFang HK&quot;, &quot;Microsoft Yahei&quot;, &quot;Microsoft JhengHei&quot;; color: rgb(51, 51, 51); font-size: 16px\">很多人问：“自学编程到底需要多久？会不会太难？”其实，时间长短因人而异，关键在于你怎么规划时间和选对学习方法。今天，我就以一个过来人的身份，分享一下我的自学编程时间规划，希望对你有帮助啦！ 第一阶段：基础打底（2~3个月） 刚开始学，重点是弄懂编程的基础概念，比如变量、循环、函数这些。建议从Python入手，语法简单又好理解，适合新手。如果每天学2~3小时，3个月左右就能打好基础。这里推荐使用一些免费的资源，比如W3Schools、Codecademy，或者看YouTube上的教程。 第二阶段：进阶项目（4~6个月） 有了基础之后，接下来就要学会用代码解决实际问题。比如做一个简单的网站、开发一个小工具，甚至爬取网页数据都行。这时候可以学点HTML、CSS、JavaScript或者SQL，结合Python做一些实战项目。通过实际操作，你会发现自己对编程的理解会更深。 每天花3-4小时，坚持4-6个月，你会看到明显的进步。别忘了把做过的项目放到GitHub上，这对以后找工作很有帮助哦！ 第三阶段：专攻领域（3~6个月） 想进入IT行业，最好明确一个方向，比如前端、后端、移动开发或数据分析。如果对前端感兴趣，可以学习React.js；后端的话，试试Node.js或者Laravel；数据分析可以去学Pandas和机器学习相关内容。这个阶段的学习会更深入，但你已经有了基础，所以不用怕。 每天投入4小时左右，3~6个月足够让你掌握一项专长，甚至能接一些简单的freelance项目赚点零花钱！ 关键点：坚持与调整 编程学习不是一蹴而就的过程，可能会遇到卡住不懂、代码跑不通的时候。别灰心，这些都是每个程序员的必经之路。适当调整学习计划，比如加入一些挑战性项目，或和朋友一起学习，都会让你坚持下去！</span></p>');
INSERT INTO `topic` VALUES (15, '2026-04-14 21:09:29', 21, 1, '自学的编程，会被认可吗', 2, 4, 'http://localhost:7245/1776172130604/6d2472904f43100fc0b39739c9c9e6dc.jpg', 'http://localhost:7245/1776172132977/6d2472904f43100fc0b39739c9c9e6dc.jpg', 10, '<p>如果你也在自学编程，可能心里总有一个声音在问：我没有计算机专业的文凭，简历会不会直接被筛掉？面试官会不会连机会都不给我？我曾经也为此焦虑过。每次看到招聘要求上写着“计算机相关专业本科及以上”，心里就会咯噔一下。我会想，自己熬夜写的那些代码，做的那些小项目，在“科班出身”这四个字面前，是不是一文不值？后来我渐渐明白了一件事：行业认可的，从来不是“自学”这个身份，而是自学所证明的能力。自学的第一步，是放弃被“认可”的执念。说实话，刚开始找工作的时候，确实碰过壁。有些公司看到简历上的非科班背景，连面试机会都不给。那时候很沮丧，觉得不公平。但后来我换了个角度想——这不完全是学历的问题，而是我没有给对方足够的理由去忽略我的背景。于是我开始把精力放在“证明”上，而不是抱怨上。真正让人改变看法的，是你能拿出什么。我做的第一件事，是把所有练习代码都整理好，放到GitHub上。不只是放上去，而是写好README，说清楚这个项目是干什么的、用了什么技术、解决了什么问题。后来我开始写技术博客，把自己学到的、踩过的坑都记录下来。不是为了给别人看，而是为了让自己理清思路。没想到，这些文章成了我最好的简历。面试的时候，面试官问我有没有实际项目经验，我没有大厂的实习经历可以讲，但我打开电脑，给他看了我做的那个小型电商网站的后端——虽然简陋，但用户认证、商品展示、购物车、订单处理，每一个模块都是我一行一行代码敲出来的。我还给他看了我的GitHub提交记录，几乎每天都有。他问了我一些系统设计的问题，我结合自己做项目时遇到的实际问题来回答，比如“当时我用这种缓存方案，遇到了数据一致性的问题，后来改成了另一种方式……”我看到他的表情从怀疑变成了感兴趣。后来我意识到，自学者有一个隐形的优势：解决问题的能力。今天的编程行业，可能是最不看重文凭的领域之一。不是因为文凭没用，而是因为这个技术变化太快，没有人能靠大学四年学的东西吃一辈子。所有人都在自学——包括那些科班出身的人，他们毕业后也要不断地学新框架、新语言、新工具。所以“自学”和“科班”的界限，其实比想象中模糊。一个科班生如果毕业后不再学习，三年后可能还不如一个持续自学的非科班。企业要的是能干活、能解决问题的人，而不是一张文凭。最后，我想对还在焦虑的你，说说心里话。果你也是自学者，可能会经历这样的阶段：深夜对着报错信息抓狂，觉得自己什么都不会；看到招聘要求上的“精通”两个字就心虚；面试被拒的时候怀疑自己走错了路。这些我都经历过。但我想告诉你，当你终于调通那个困扰你三天的bug时，当你写出的功能真的上线被人使用时，当面试官说“我们觉得你基础不错”时——那种踏实感，是任何文凭都给不了你的。不要总想着“被认可”。去做一个值得被认可的人。 用你的代码说话，用你的项目证明，用你的热情感染人。这个行业最大的公平就是：它只关心你能不能解决问题，而不太关心你是怎么学会解决问题的。所以，别想太多，继续写代码吧。你的作品，就是你最好的文凭。</p>');
INSERT INTO `topic` VALUES (16, '2026-04-14 21:23:58', 21, 0, '30岁以上程序员还有哪些出路', 2, 4, 'http://localhost:7245/1776172973734/b99891044cc02340c02d60bc7d92b5e9.jpg', 'http://localhost:7245/1776172975748/b99891044cc02340c02d60bc7d92b5e9.jpg', 6, '<p><span style=\"font-family: quote-cjk-patch, Inter, system-ui, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; color: rgb(15, 17, 21); font-size: 16px\">30岁以上的程序员不仅没有被行业淘汰，反而正站在一个价值重估的关键路口。2026年的真实趋势是——市场不再为单纯的“代码量”和“加班时长”买单，但那些具备深度经验、业务理解力和AI驾驭能力的资深开发者，正在成为稀缺资源。传统CRUD开发需求下降了约25%，但AI应用开发需求增长了60%以上，传统软件工程师岗位收缩的同时，AI相关技术岗位需求同比增长超过40%。这意味着，30岁+程序员不是出路变窄了，而是出路的方向发生了根本性转移。AI编程工具的成熟正在催生一个新的可能：独立开发者不再是一个浪漫化的幻想，而是可落地的现实。以前，做一个靠谱的产品需要产品+前端+后端+测试+运维的团队协作，个人只能望洋兴叹。现在，一个有经验的程序员+AI，就能完成70%-80%的完整开发闭环。这意味着，30岁+程序员完全可以考虑跳出“给别人打工”的单一模式，通过接外包、做独立产品、做技术顾问等方式实现收入多元化。2026年的人才趋势报告也印证了这一点：高达71%的职场人对合同制、顾问制等灵活用工模式持开放态度。与此同时，消费品、汽车等传统行业正在加大对数字化营销、用户运营等技术相关岗位的招聘力度，电商总监年薪最高可达200万元，用户增长总监年薪60-150万元——这些岗位欢迎懂技术的业务人才，也是30岁+程序员跨界的好方向。</span></p>');
INSERT INTO `topic` VALUES (17, '2026-04-14 21:26:46', 21, 0, 'Python编程就业技能教学', 1, 1, 'http://localhost:7245/1776173130230/b2ed6d68d1482725850bf668a144000a.jpg', 'http://localhost:7245/1776173132320/b2ed6d68d1482725850bf668a144000a.jpg', 1, '<h2><span style=\"font-family: SimSun\">刷到这篇的你有福啦✨ 同学们们，下课的时候有没有想一项编程就业技能？ 不管有没有基础，只要认真学都能轻松就业 如果你想要下课后，把碎片时间利用起来，试试来学Python编程吧！ 🔥边读书边学就业技能，毕业的时候你感谢现在的你自己的~</span></h2><p><span style=\"font-family: SimSun\">要求如下，跟着学，0基础也能学~ ✅每天2小时 ✅有电脑 ✅肯坚持有毅力</span></p><p><span style=\"font-family: SimSun\">想学的宝子点击左下角按钮(立即咨询)报名留下你的联系方式免费领取资料+实战课~</span></p>');
INSERT INTO `topic` VALUES (18, '2026-04-14 21:29:27', 21, 0, '新手c语言从哪里开始学啊', 2, 3, 'http://localhost:7245/1776173287706/b398b812ddf5c1e1a140801519b42977.jpg', 'http://localhost:7245/1776173290574/b398b812ddf5c1e1a140801519b42977.jpg', 10, '<p><span style=\"font-family: quote-cjk-patch, Inter, system-ui, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; color: rgb(15, 17, 21); font-size: 16px\">我是一名刚接触编程的纯小白，之前完全没有写过代码，甚至连命令行都没怎么碰过，但我的逻辑思维还算不错，中学数学也还过得去，而且我不怕枯燥，愿意花时间从最底层的东西一点一点啃起，因为我知道编程这条路没有捷径，只有扎扎实实把基础打牢，后面才能走得远。我选择C语言作为第一门编程语言，是因为我听过很多前辈说C语言是编程世界的“通用语言”，它不像Python那样帮你做了太多底层的事情，而是让你亲手去管理内存、理解指针、搞懂数据在计算机里到底是怎么流动的，这种“贴近硬件”的特性恰恰是我想要的——我不想只会调用别人封装好的函数，我想真正理解计算机的工作原理。我的诉求很明确：第一，我需要一条清晰、无跳跃的学习路径，最好是经典书籍配合视频课程，因为市面上教程太多太杂，我害怕学到错误或者过时的知识；第二，我需要大量的练习机会，光看不练等于没学，而且练习题最好有详细的解析，让我知道错在哪里；第三，我需要一个能够及时反馈的环境，比如可以在线运行代码的练习平台，或者一个靠谱的社区论坛，当我遇到编译报错或者运行结果不对的时候，能有人或者有资料告诉我问题出在哪里；第四，我希望整个学习过程能够循序渐进，不要一上来就扔给我复杂的指针和数据结构，而是先从变量、数据类型、运算符、流程控制这些最基础的东西开始，每学一个知识点就立刻做对应的练习，确保真正掌握之后再进入下一个环节。综合这些诉求，我认为最适合新手的C语言学习路径应该是这样：第一步，找一本公认的经典入门教材，比如《C Primer Plus》或者《明解C语言》，前者非常详尽适合零基础但比较厚需要耐心，后者图文并茂更适合日本教学风格，两本选其一即可，配合中国大学MOOC上浙大翁恺老师的《C语言程序设计》视频课程，翁恺老师讲得非常透彻且适合初学者，视频和书籍穿插着看，一个知识点从两个角度理解效果更好；第二步，搭建编程环境，新手千万别在配置环境上耗费太多精力，直接安装Dev-C++或者Code::Blocks这种开箱即用的轻量级IDE，等你学到一定程度再考虑VSCode或者命令行编译，同时注册一个在线编程网站如菜鸟工具的在线编译器或者Lightly，方便随时随手写几行小代码测试想法；第三步，按照“基础语法→数组和字符串→函数→指针→结构体→动态内存分配→文件操作”的顺序逐个击破，每个章节学完后，一定要把教材后面的习题全部手敲一遍，不要看答案先自己写，实在写不出来再看解析，然后理解后重新默写一遍；第四步，当基本语法掌握得差不多之后，去洛谷或者Codecademy上找一些难度适中的C语言编程题来刷，每天至少保持写50到100行代码的手感，同时开始在GitHub上建立自己的代码仓库，把每天写的练习代码都传上去，这既是备份也是你学习轨迹的见证；第五步，在学完指针和结构体之后，尝试做一个小项目，比如学生成绩管理系统、通讯录、贪吃蛇游戏的控制台版本，这些项目网上有很多参考代码，但一定要自己独立写出来，遇到bug就去CSDN或者Stack Overflow搜，这个过程会让你把零散的知识点串联起来，真正体会到编程解决实际问题的感觉。我给自己定了一个切实可行的时间表：前两周完成环境搭建和最基本的输入输出、变量类型、运算符的学习，每天学习2-3小时；接下来四周重点攻克流程控制（if、switch、循环）和数组，这个阶段要大量做题，每天保持代码量；再用三周时间啃下C语言最难的指针部分，这是分水岭，指针学懂了C语言就算真正入门了；最后三周学习结构体和文件操作，并完成第一个小项目。我知道这条路不会轻松，一定会遇到无数让人抓狂的编译报错和逻辑bug，但我已经做好了心理准备，每一次报错都是学习的机会，每一次调试都让我对计算机的理解更深一层。我希望能够找到一群同样在学C语言的新手伙伴互相督促，也希望能有前辈在关键节点上指点一下方向，避免走弯路。如果你也在学C语言或者已经走过这条路，非常欢迎你给我建议或者一起交流。</span></p>');
INSERT INTO `topic` VALUES (19, '2026-04-14 21:32:02', 2, 0, '程序员都很有钱吗', 1, 2, 'http://localhost:7245/1776173480924/906f89b128d3554451118836697294d1.jpg', 'http://localhost:7245/1776173482696/906f89b128d3554451118836697294d1.jpg', 6, '<p>为什么一提程序员，大家的反应都是有钱，今天被同事问老公是做什么的，我说程序员，同事第一反应是有钱，生活没压力，谁懂啊，程序员家庭压力也挺大的……</p>');
INSERT INTO `topic` VALUES (20, '2026-04-14 21:34:47', 2, 0, '计算机项目去哪找', 2, 6, 'http://localhost:7245/1776173643058/86270b34b8d444a8d7009b76c8b90c3f.jpg', 'http://localhost:7245/1776173645303/86270b34b8d444a8d7009b76c8b90c3f.jpg', 2, '<p><span style=\"font-family: quote-cjk-patch, Inter, system-ui, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; color: rgb(15, 17, 21); font-size: 16px\">我是一名正在自学C语言的新手，刚学完基础语法和指针，现在急需找到合适的项目来练手，因为我不想一直停留在做课后习题的阶段，我想做一个真正能跑起来、能看到效果的东西，这样既能巩固知识，以后找工作写简历时也有东西可以展示。我的诉求很明确：第一，我需要从易到难的项目清单，最好有分类，这样我知道自己当前的水平该做什么；第二，最好有完整的视频教程或者详细文档跟着做，因为零基础上手一个完整项目如果没有指引很容易卡住放弃；第三，如果我能力再提升一些，也希望了解如何参与真实的大型项目，比如给开源项目贡献代码。根据我查到的资料，适合我这种情况的项目来源主要有这几个方向：一是B站上的实战教程，从易到难有控制台小工具（简易计算器、学生成绩管理系统，适合刚学完语法）、图形界面小游戏（贪吃蛇、俄罗斯方块，需要用EasyX图形库，适合学完指针和结构体之后）、网络/多线程应用（HTTP服务器、多人聊天室，适合学完Socket编程之后），直接在B站搜索“C语言 项目名 实战”就能找到很多免费教程；二是GitHub平台，这是全球最大的开源代码托管平台，我可以先找一些标有“good first issue”或“beginner-friendly”标签的项目，这些专门是为新手准备的入门级任务，具体做法是先浏览GitHub Trending看热门项目，找到感兴趣的后仔细阅读项目的README和CONTRIBUTING文件了解贡献指南，然后从修复小bug或改进文档开始，不用一上来就想着写大功能；三是国内的开源活动，比如“开源之夏”这种专门面向学生的开源贡献活动，会有很多开源社区发布适合新手的项目任务；四是学习平台上的完整项目课程，比如慕课网有450多门实战课，涵盖前端、Java、Python等方向，课程围绕真实企业案例展开，做完就是一个完整的项目作品。我给自己定的计划是：先跟着B站教程做一个贪吃蛇游戏，把图形库和游戏逻辑走通；然后去GitHub找一个C语言相关的开源项目，先从读代码、写文档开始尝试贡献；最后考虑在慕课网上选一门完整的实战课，系统性地做一个能写在简历上的项目。希望这些信息对同样在找项目的自学者有帮助，也欢迎有经验的前辈多指教。</span></p>');
INSERT INTO `topic` VALUES (21, '2026-04-14 21:37:01', 2, 0, 'Python还有必要学吗', 2, 9, 'http://localhost:7245/1776173782552/61ecb9593993ef1b63300e5889ef308f.jpg', 'http://localhost:7245/1776173784498/61ecb9593993ef1b63300e5889ef308f.jpg', 1, '<p>前两天听了一个线下班的试听课 本来以为Python是编程的基础 后来又说这个ai大模型要学什么智能体，RAG，微调，Agent，提示词啥的，给我听的晕晕乎乎的，怎么感觉跟Python没啥关系呢😅</p>');

-- ----------------------------
-- Table structure for topiccollect
-- ----------------------------
DROP TABLE IF EXISTS `topiccollect`;
CREATE TABLE `topiccollect`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题收藏主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `TopicId` int NULL DEFAULT NULL COMMENT '话题',
  `UserId` int NULL DEFAULT NULL COMMENT '收藏人',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '话题收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topiccollect
-- ----------------------------

-- ----------------------------
-- Table structure for topicrecord
-- ----------------------------
DROP TABLE IF EXISTS `topicrecord`;
CREATE TABLE `topicrecord`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题浏览记录主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `TopicTypeId` int NULL DEFAULT NULL COMMENT '话题类型',
  `TopicId` int NULL DEFAULT NULL COMMENT '话题',
  `UserId` int NULL DEFAULT NULL COMMENT '浏览人',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '话题浏览记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topicrecord
-- ----------------------------
INSERT INTO `topicrecord` VALUES (14, '2026-04-14 21:01:30', 21, 10, 13, 21);
INSERT INTO `topicrecord` VALUES (15, '2026-04-14 21:01:33', 21, 10, 13, 21);
INSERT INTO `topicrecord` VALUES (16, '2026-04-14 21:01:37', 21, 10, 13, 21);
INSERT INTO `topicrecord` VALUES (19, '2026-04-14 21:12:23', 21, 10, 4, 21);
INSERT INTO `topicrecord` VALUES (20, '2026-04-14 21:12:27', 21, 10, 15, 21);
INSERT INTO `topicrecord` VALUES (21, '2026-04-14 21:12:48', 21, 10, 13, 21);
INSERT INTO `topicrecord` VALUES (22, '2026-04-14 21:15:01', 21, 10, 4, 21);
INSERT INTO `topicrecord` VALUES (23, '2026-04-14 21:17:56', 21, 10, 13, 21);
INSERT INTO `topicrecord` VALUES (24, '2026-04-14 21:17:58', 21, 10, 4, 21);
INSERT INTO `topicrecord` VALUES (25, '2026-04-14 21:35:39', 2, 10, 15, 2);
INSERT INTO `topicrecord` VALUES (26, '2026-04-14 21:50:47', 21, 10, 18, 21);
INSERT INTO `topicrecord` VALUES (27, '2026-04-15 14:12:17', 21, 2, 20, 21);
INSERT INTO `topicrecord` VALUES (28, '2026-04-15 14:12:21', 21, 6, 16, 21);
INSERT INTO `topicrecord` VALUES (29, '2026-04-15 14:12:27', 21, 6, 19, 21);
INSERT INTO `topicrecord` VALUES (30, '2026-04-15 14:12:31', 21, 10, 15, 21);
INSERT INTO `topicrecord` VALUES (31, '2026-04-15 14:12:38', 21, 10, 13, 21);
INSERT INTO `topicrecord` VALUES (32, '2026-04-15 14:17:13', 21, 2, 20, 21);
INSERT INTO `topicrecord` VALUES (33, '2026-04-15 15:15:49', 21, 10, 13, 21);
INSERT INTO `topicrecord` VALUES (34, '2026-04-15 15:17:52', 21, 1, 21, 21);
INSERT INTO `topicrecord` VALUES (35, '2026-04-16 00:19:32', 21, 6, 16, 21);
INSERT INTO `topicrecord` VALUES (36, '2026-04-16 00:19:34', 21, 6, 19, 21);
INSERT INTO `topicrecord` VALUES (37, '2026-04-16 00:19:37', 21, 1, 21, 21);
INSERT INTO `topicrecord` VALUES (38, '2026-04-16 16:13:58', 21, 1, 21, 21);

-- ----------------------------
-- Table structure for topictype
-- ----------------------------
DROP TABLE IF EXISTS `topictype`;
CREATE TABLE `topictype`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '话题类型主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Cover` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `Name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '话题类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topictype
-- ----------------------------
INSERT INTO `topictype` VALUES (1, '2026-02-02 14:00:00', 1, NULL, 'Python编程技巧', '讨论Python编程中的高级技巧和最佳实践');
INSERT INTO `topictype` VALUES (2, '2026-02-02 14:00:00', 1, NULL, 'Java并发编程', '探讨Java中并发处理的最佳方法与常见问题解决方案');
INSERT INTO `topictype` VALUES (3, '2026-02-02 14:00:00', 1, NULL, '前端框架对比', '分析Vue.js、React和Angular等前端框架的特点与适用场景');
INSERT INTO `topictype` VALUES (4, '2026-02-02 14:00:00', 1, NULL, '数据库优化策略', '分享数据库性能调优的经验和实用技巧');
INSERT INTO `topictype` VALUES (5, '2026-02-02 14:00:00', 1, NULL, '机器学习入门', '介绍机器学习的基本概念、算法以及如何开始自己的第一个项目');
INSERT INTO `topictype` VALUES (6, '2026-02-02 14:00:00', 1, NULL, '移动应用开发趋势', '讨论当前移动应用开发的趋势，如Flutter和Kotlin的使用');
INSERT INTO `topictype` VALUES (7, '2026-02-02 14:00:00', 1, NULL, 'Web安全基础', '讲解Web开发过程中必须知道的安全基础知识和防护措施');
INSERT INTO `topictype` VALUES (8, '2026-02-02 14:00:00', 1, NULL, '云计算服务比较', '对比AWS、Azure和Google Cloud等主流云计算服务平台的优缺点');
INSERT INTO `topictype` VALUES (9, '2026-02-02 14:00:00', 1, NULL, 'DevOps工具链', '介绍构建高效DevOps流程所需的工具和技术栈');
INSERT INTO `topictype` VALUES (10, '2026-02-02 14:00:00', 1, NULL, '编程学习', '如何参与到开源项目中去，以及参与开源的好处和注意事项');

-- ----------------------------
-- Table structure for useranswer
-- ----------------------------
DROP TABLE IF EXISTS `useranswer`;
CREATE TABLE `useranswer`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '用户提交答题主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `Type` int NULL DEFAULT NULL COMMENT '类型',
  `UserId` int NULL DEFAULT NULL COMMENT '用户',
  `SubmitTime` datetime NULL DEFAULT NULL COMMENT '提交时间',
  `BeginTime` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `QuestionnaireId` int NULL DEFAULT NULL COMMENT '问卷',
  `TotalScore` double(20, 5) NULL DEFAULT NULL COMMENT '总得分',
  `TestId` int NULL DEFAULT NULL COMMENT '测试',
  `IsMarking` bit(1) NULL DEFAULT NULL COMMENT '是否阅卷完毕',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `QuestionnaireId`(`QuestionnaireId` ASC) USING BTREE,
  INDEX `UserId`(`UserId` ASC) USING BTREE,
  CONSTRAINT `useranswer_ibfk_1` FOREIGN KEY (`QuestionnaireId`) REFERENCES `questionnaire` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `useranswer_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `appuser` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户提交答题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of useranswer
-- ----------------------------
INSERT INTO `useranswer` VALUES (19, '2026-02-02 15:49:24', 2, NULL, 2, '2026-02-02 15:49:39', '2026-02-02 15:49:24', 5, 20.00000, 2, b'1');
INSERT INTO `useranswer` VALUES (20, '2026-02-02 16:04:21', 3, NULL, 3, '2026-02-02 16:04:36', '2026-02-02 16:04:21', 5, 16.00000, 2, b'1');
INSERT INTO `useranswer` VALUES (21, '2026-02-02 16:11:42', 3, NULL, 3, NULL, '2026-02-02 16:11:42', 5, NULL, 2, NULL);
INSERT INTO `useranswer` VALUES (23, '2026-01-30 11:22:56', 21, NULL, 21, NULL, '2026-01-30 11:22:56', 1, NULL, 1, NULL);

-- ----------------------------
-- Table structure for useranswerdet
-- ----------------------------
DROP TABLE IF EXISTS `useranswerdet`;
CREATE TABLE `useranswerdet`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '用户提交答题明细主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `IsRight` tinyint(1) NULL DEFAULT NULL COMMENT '是否正确',
  `TotalScore` double(20, 5) NULL DEFAULT NULL COMMENT '分值',
  `UserAnswer` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '回答内容',
  `GetScore` double(20, 5) NULL DEFAULT NULL COMMENT '得分',
  `UserAnswerId` int NULL DEFAULT NULL COMMENT '用户提交答题',
  `RightAnswer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正确回答',
  `QuestionId` int NULL DEFAULT NULL COMMENT '题目',
  `QuestionType` int NULL DEFAULT NULL COMMENT '题型枚举枚举',
  `QuestionnaireId` int NULL DEFAULT NULL COMMENT '问卷',
  `Sort` int NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `UserAnswerId`(`UserAnswerId` ASC) USING BTREE,
  INDEX `QuestionnaireId`(`QuestionnaireId` ASC) USING BTREE,
  CONSTRAINT `useranswerdet_ibfk_1` FOREIGN KEY (`UserAnswerId`) REFERENCES `useranswer` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `useranswerdet_ibfk_2` FOREIGN KEY (`QuestionnaireId`) REFERENCES `questionnaire` (`Id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户提交答题明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of useranswerdet
-- ----------------------------
INSERT INTO `useranswerdet` VALUES (73, '2026-02-02 15:49:24', 2, 0, 4.00000, 'true', 0.00000, 19, 'false', 9, 3, 5, 1);
INSERT INTO `useranswerdet` VALUES (74, '2026-02-02 15:49:24', 2, 1, 4.00000, 'true', 4.00000, 19, 'true', 1, 3, 5, 2);
INSERT INTO `useranswerdet` VALUES (75, '2026-02-02 15:49:24', 2, 1, 4.00000, 'true', 4.00000, 19, 'true', 7, 3, 5, 3);
INSERT INTO `useranswerdet` VALUES (76, '2026-02-02 15:49:24', 2, 1, 4.00000, 'true', 4.00000, 19, 'true', 12, 3, 5, 4);
INSERT INTO `useranswerdet` VALUES (77, '2026-02-02 15:49:24', 2, 0, 4.00000, 'A', 0.00000, 19, 'B', 10, 1, 5, 5);
INSERT INTO `useranswerdet` VALUES (78, '2026-02-02 15:49:24', 2, 0, 4.00000, 'B', 0.00000, 19, 'A,B,C,D', 13, 2, 5, 6);
INSERT INTO `useranswerdet` VALUES (79, '2026-02-02 15:49:24', 2, 0, 4.00000, 'true', 0.00000, 19, 'false', 13, 3, 5, 7);
INSERT INTO `useranswerdet` VALUES (80, '2026-02-02 15:49:24', 2, 0, 4.00000, 'B', 0.00000, 19, 'A', 17, 2, 5, 8);
INSERT INTO `useranswerdet` VALUES (81, '2026-02-02 15:49:24', 2, 1, 4.00000, 'A', 4.00000, 19, 'A', 8, 1, 5, 9);
INSERT INTO `useranswerdet` VALUES (82, '2026-02-02 15:49:24', 2, 0, 4.00000, 'B', 0.00000, 19, 'A,B,C', 15, 2, 5, 10);
INSERT INTO `useranswerdet` VALUES (83, '2026-02-02 15:49:24', 2, 1, 4.00000, 'B', 4.00000, 19, 'B', 7, 1, 5, 11);
INSERT INTO `useranswerdet` VALUES (84, '2026-02-02 15:49:24', 2, 0, 4.00000, 'B', 0.00000, 19, 'A', 5, 1, 5, 12);
INSERT INTO `useranswerdet` VALUES (85, '2026-02-02 15:49:24', 2, 0, 4.00000, 'B', 0.00000, 19, 'A', 11, 2, 5, 13);
INSERT INTO `useranswerdet` VALUES (86, '2026-02-02 15:49:24', 2, 0, 4.00000, 'A', 0.00000, 19, 'C', 9, 1, 5, 14);
INSERT INTO `useranswerdet` VALUES (87, '2026-02-02 15:49:24', 2, 0, 4.00000, 'A', 0.00000, 19, 'A,B,C,D', 12, 2, 5, 15);
INSERT INTO `useranswerdet` VALUES (88, '2026-02-02 15:49:24', 3, 0, 4.00000, 'false', 0.00000, 20, 'true', 12, 3, 5, 1);
INSERT INTO `useranswerdet` VALUES (89, '2026-02-02 15:49:24', 3, 0, 4.00000, 'true', 0.00000, 20, 'false', 13, 3, 5, 2);
INSERT INTO `useranswerdet` VALUES (90, '2026-02-02 15:49:24', 3, 0, 4.00000, 'B', 0.00000, 20, 'A,B,C,D', 13, 2, 5, 3);
INSERT INTO `useranswerdet` VALUES (91, '2026-02-02 15:49:24', 3, 0, 4.00000, 'true', 0.00000, 20, 'false', 9, 3, 5, 4);
INSERT INTO `useranswerdet` VALUES (92, '2026-02-02 15:49:24', 3, 0, 4.00000, 'C', 0.00000, 20, 'A,B,C', 15, 2, 5, 5);
INSERT INTO `useranswerdet` VALUES (93, '2026-02-02 15:49:24', 3, 0, 4.00000, 'C', 0.00000, 20, 'A', 17, 2, 5, 6);
INSERT INTO `useranswerdet` VALUES (94, '2026-02-02 15:49:24', 3, 1, 4.00000, 'A', 4.00000, 20, 'A', 11, 2, 5, 7);
INSERT INTO `useranswerdet` VALUES (95, '2026-02-02 15:49:24', 3, 1, 4.00000, 'true', 4.00000, 20, 'true', 1, 3, 5, 8);
INSERT INTO `useranswerdet` VALUES (96, '2026-02-02 15:49:24', 3, 0, 4.00000, 'B', 0.00000, 20, 'A', 8, 1, 5, 9);
INSERT INTO `useranswerdet` VALUES (97, '2026-02-02 15:49:24', 3, 0, 4.00000, 'B', 0.00000, 20, 'A', 5, 1, 5, 10);
INSERT INTO `useranswerdet` VALUES (98, '2026-02-02 15:49:24', 3, 0, 4.00000, 'C', 0.00000, 20, 'B', 7, 1, 5, 11);
INSERT INTO `useranswerdet` VALUES (99, '2026-02-02 15:49:24', 3, 0, 4.00000, 'A', 0.00000, 20, 'A,B,C,D', 12, 2, 5, 12);
INSERT INTO `useranswerdet` VALUES (100, '2026-02-02 15:49:24', 3, 1, 4.00000, 'true', 4.00000, 20, 'true', 7, 3, 5, 13);
INSERT INTO `useranswerdet` VALUES (101, '2026-02-02 15:49:24', 3, 1, 4.00000, 'B', 4.00000, 20, 'B', 10, 1, 5, 14);
INSERT INTO `useranswerdet` VALUES (102, '2026-02-02 15:49:24', 3, 0, 4.00000, 'B', 0.00000, 20, 'C', 9, 1, 5, 15);
INSERT INTO `useranswerdet` VALUES (103, '2026-02-02 15:49:24', 3, 0, 4.00000, 'A', 0.00000, 21, 'B', 10, 1, 5, 1);
INSERT INTO `useranswerdet` VALUES (104, '2026-02-02 15:49:24', 3, 0, 4.00000, 'B', 0.00000, 21, 'A', 8, 1, 5, 2);
INSERT INTO `useranswerdet` VALUES (105, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'A,B,C,D', 12, 2, 5, 3);
INSERT INTO `useranswerdet` VALUES (106, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'A', 11, 2, 5, 4);
INSERT INTO `useranswerdet` VALUES (107, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'true', 1, 3, 5, 5);
INSERT INTO `useranswerdet` VALUES (108, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'B', 7, 1, 5, 6);
INSERT INTO `useranswerdet` VALUES (109, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'false', 9, 3, 5, 7);
INSERT INTO `useranswerdet` VALUES (110, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'A', 5, 1, 5, 8);
INSERT INTO `useranswerdet` VALUES (111, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'A,B,C', 15, 2, 5, 9);
INSERT INTO `useranswerdet` VALUES (112, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'false', 13, 3, 5, 10);
INSERT INTO `useranswerdet` VALUES (113, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'A', 17, 2, 5, 11);
INSERT INTO `useranswerdet` VALUES (114, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'C', 9, 1, 5, 12);
INSERT INTO `useranswerdet` VALUES (115, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'true', 7, 3, 5, 13);
INSERT INTO `useranswerdet` VALUES (116, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'true', 12, 3, 5, 14);
INSERT INTO `useranswerdet` VALUES (117, '2026-02-02 15:49:24', 3, NULL, 4.00000, NULL, NULL, 21, 'A,B,C,D', 13, 2, 5, 15);
INSERT INTO `useranswerdet` VALUES (118, '2026-01-30 11:22:56', 21, 0, 4.00000, 'A,B', 0.00000, 23, 'C', 2, 2, 1, 1);
INSERT INTO `useranswerdet` VALUES (119, '2026-01-30 11:22:56', 21, 0, 10.00000, '123', NULL, 23, NULL, 2, 4, 1, 2);
INSERT INTO `useranswerdet` VALUES (120, '2026-01-30 11:22:56', 21, 0, 4.00000, 'true', 0.00000, 23, 'false', 4, 3, 1, 3);
INSERT INTO `useranswerdet` VALUES (121, '2026-01-30 11:22:56', 21, 0, 10.00000, '123', NULL, 23, 'HTML文档的基本结构包括DOCTYPE声明、<html>根元素、<head>头部信息和<body>主体内容。DOCTYPE声明告诉浏览器使用哪个HTML版本；<html>是文档的根元素；<head>包含元数据如标题、样式和脚本；<body>包含页面的主要内容。', 1, 4, 1, 4);
INSERT INTO `useranswerdet` VALUES (122, '2026-01-30 11:22:56', 21, 0, 4.00000, 'A,B', 0.00000, 23, 'A,B,D', 3, 2, 1, 5);
INSERT INTO `useranswerdet` VALUES (123, '2026-01-30 11:22:56', 21, 0, 4.00000, 'A', 0.00000, 23, 'C', 3, 1, 1, 6);
INSERT INTO `useranswerdet` VALUES (124, '2026-01-30 11:22:56', 21, 0, 4.00000, 'true', 0.00000, 23, 'false', 3, 3, 1, 7);
INSERT INTO `useranswerdet` VALUES (125, '2026-01-30 11:22:56', 21, 0, 4.00000, 'A', 0.00000, 23, 'B', 2, 1, 1, 8);

-- ----------------------------
-- Table structure for useranswerlog
-- ----------------------------
DROP TABLE IF EXISTS `useranswerlog`;
CREATE TABLE `useranswerlog`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '考试日志主键',
  `CreationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CreatorId` int NULL DEFAULT NULL COMMENT '创建人',
  `UserId` int NULL DEFAULT NULL COMMENT '用户',
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '日志',
  `TestId` int NULL DEFAULT NULL COMMENT '测试',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '考试日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of useranswerlog
-- ----------------------------
INSERT INTO `useranswerlog` VALUES (42, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (43, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (44, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (45, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (46, '2026-02-02 15:20:16', 2, 2, '用户提交了答题', 1);
INSERT INTO `useranswerlog` VALUES (47, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 1);
INSERT INTO `useranswerlog` VALUES (48, '2026-02-02 15:20:16', 2, 2, '用户正式开始答题', 2);
INSERT INTO `useranswerlog` VALUES (49, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', NULL);
INSERT INTO `useranswerlog` VALUES (50, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (51, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (52, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (53, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (54, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', NULL);
INSERT INTO `useranswerlog` VALUES (55, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', 2);
INSERT INTO `useranswerlog` VALUES (56, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', 2);
INSERT INTO `useranswerlog` VALUES (57, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', 2);
INSERT INTO `useranswerlog` VALUES (58, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', 2);
INSERT INTO `useranswerlog` VALUES (59, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', 2);
INSERT INTO `useranswerlog` VALUES (60, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (61, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (62, '2026-02-02 15:20:16', 2, 2, '用户正式开始答题', 2);
INSERT INTO `useranswerlog` VALUES (63, '2026-02-02 15:20:16', 2, 2, '用户提交了答题', 2);
INSERT INTO `useranswerlog` VALUES (64, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (65, '2026-02-02 15:20:16', 2, 2, '用户提交了答题', 2);
INSERT INTO `useranswerlog` VALUES (66, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (67, '2026-02-02 15:20:16', 2, 2, '用户提交了答题', 2);
INSERT INTO `useranswerlog` VALUES (68, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (69, '2026-02-02 15:20:16', 2, 2, '用户提交了答题', 2);
INSERT INTO `useranswerlog` VALUES (70, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (71, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (72, '2026-02-02 15:20:16', 2, 2, '用户在答题过程中切换了页面', 2);
INSERT INTO `useranswerlog` VALUES (73, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (74, '2026-02-02 15:20:16', 2, 2, '用户提交了答题', 2);
INSERT INTO `useranswerlog` VALUES (75, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (76, '2026-02-02 15:20:16', 2, 2, '用户正式开始答题', 2);
INSERT INTO `useranswerlog` VALUES (77, '2026-02-02 15:20:16', 2, 2, '用户提交了答题', 2);
INSERT INTO `useranswerlog` VALUES (78, '2026-02-02 15:20:16', 2, 2, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (79, '2026-02-02 15:20:16', 3, 3, '用户正式开始答题', 2);
INSERT INTO `useranswerlog` VALUES (80, '2026-02-02 15:20:16', 3, 3, '用户提交了答题', 2);
INSERT INTO `useranswerlog` VALUES (81, '2026-02-02 15:20:16', 3, 3, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (82, '2026-02-02 15:20:16', 3, 3, '用户正式开始答题', 2);
INSERT INTO `useranswerlog` VALUES (83, '2026-02-02 15:20:16', 3, 3, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (84, '2026-02-02 15:20:16', 3, 3, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (85, '2026-02-02 15:20:16', 3, 3, '用户离开了答题页面', NULL);
INSERT INTO `useranswerlog` VALUES (86, '2026-02-02 15:20:16', 3, 3, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (87, '2026-02-02 15:20:16', 3, 3, '用户离开了答题页面', 2);
INSERT INTO `useranswerlog` VALUES (88, '2026-01-30 11:22:56', 21, 21, '用户正式开始答题', 1);
INSERT INTO `useranswerlog` VALUES (89, '2026-01-30 11:22:58', 21, 21, '用户离开了答题页面', 1);
INSERT INTO `useranswerlog` VALUES (90, '2026-01-30 11:23:29', 21, 21, '用户离开了答题页面', 1);

SET FOREIGN_KEY_CHECKS = 1;
