-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ershoushujiaoyipingtai
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `yonghu_id` int NOT NULL COMMENT '创建用户',
  `address_name` varchar(200) NOT NULL COMMENT '收货人 ',
  `address_phone` varchar(200) NOT NULL COMMENT '电话 ',
  `address_dizhi` varchar(200) NOT NULL COMMENT '地址 ',
  `isdefault_types` int NOT NULL COMMENT '是否默认地址 ',
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 show3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COMMENT='收货地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (6,6,'小杨','13639296999','遵义师范学院图书馆二楼',1,'2025-06-12 08:32:36','2025-06-12 08:32:36','2025-06-12 08:32:36'),(7,7,'小李','13639296999','遵义师范学院',2,'2025-06-18 01:38:15','2025-06-18 01:38:14','2025-06-18 01:38:15');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_recommendation`
--

DROP TABLE IF EXISTS `book_recommendation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_recommendation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `yonghu_id` int NOT NULL COMMENT '被推荐用户ID',
  `tushu_id` int NOT NULL COMMENT '推荐书籍ID',
  `score` decimal(5,2) DEFAULT NULL COMMENT '推荐得分',
  `recommend_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '推荐生成时间',
  PRIMARY KEY (`id`),
  KEY `yonghu_id` (`yonghu_id`),
  KEY `tushu_id` (`tushu_id`),
  CONSTRAINT `book_recommendation_ibfk_1` FOREIGN KEY (`yonghu_id`) REFERENCES `yonghu` (`id`),
  CONSTRAINT `book_recommendation_ibfk_2` FOREIGN KEY (`tushu_id`) REFERENCES `tushu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='个性化推荐结果表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_recommendation`
--

LOCK TABLES `book_recommendation` WRITE;
/*!40000 ALTER TABLE `book_recommendation` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_recommendation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_tag`
--

DROP TABLE IF EXISTS `book_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tushu_id` int NOT NULL COMMENT '书籍ID',
  `tag` varchar(50) NOT NULL COMMENT '标签',
  PRIMARY KEY (`id`),
  KEY `tushu_id` (`tushu_id`),
  CONSTRAINT `book_tag_ibfk_1` FOREIGN KEY (`tushu_id`) REFERENCES `tushu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='图书标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_tag`
--

LOCK TABLES `book_tag` WRITE;
/*!40000 ALTER TABLE `book_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `yonghu_id` int DEFAULT NULL COMMENT '所属用户',
  `tushu_id` int DEFAULT NULL COMMENT '图书',
  `buy_number` int DEFAULT NULL COMMENT '购买数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COMMENT='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (14,6,6,1,'2025-06-12 08:33:30',NULL,'2025-06-12 08:33:30'),(15,7,7,1,'2025-06-18 01:37:34',NULL,'2025-06-18 01:37:34'),(17,6,8,1,'2025-06-19 09:07:26',NULL,'2025-06-19 09:07:26');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `yonghu_id` int DEFAULT NULL COMMENT '提问用户',
  `chat_issue` varchar(200) DEFAULT NULL COMMENT '问题',
  `issue_time` timestamp NULL DEFAULT NULL COMMENT '问题时间 Search111',
  `chat_reply` varchar(200) DEFAULT NULL COMMENT '回复',
  `reply_time` timestamp NULL DEFAULT NULL COMMENT '回复时间 Search111',
  `zhuangtai_types` int DEFAULT NULL COMMENT '状态',
  `chat_types` int DEFAULT NULL COMMENT '数据类型',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='用户反馈';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (3,4,'gfdg ','2025-06-11 08:29:52',NULL,NULL,1,1,'2025-06-11 08:29:53');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (4,'轮播图','http://localhost:8080/ershoushujiaoyipingtai/upload/1749706314928.jpg'),(5,'图片','http://localhost:8080/ershoushujiaoyipingtai/upload/1749706331476.jpg'),(6,'图3','http://localhost:8080/ershoushujiaoyipingtai/upload/1749706345890.jpg');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dictionary`
--

DROP TABLE IF EXISTS `dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dictionary` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_code` varchar(200) DEFAULT NULL COMMENT '字段',
  `dic_name` varchar(200) DEFAULT NULL COMMENT '字段名',
  `code_index` int DEFAULT NULL COMMENT '编码',
  `index_name` varchar(200) DEFAULT NULL COMMENT '编码名字  Search111 ',
  `super_id` int DEFAULT NULL COMMENT '父字段id',
  `beizhu` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3 COMMENT='字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary`
--

LOCK TABLES `dictionary` WRITE;
/*!40000 ALTER TABLE `dictionary` DISABLE KEYS */;
INSERT INTO `dictionary` VALUES (1,'sex_types','性别类型',1,'男',NULL,NULL,'2025-03-02 06:42:10'),(2,'sex_types','性别类型',2,'女',NULL,NULL,'2025-03-02 06:42:10'),(3,'isdefault_types','是否默认地址',1,'否',NULL,NULL,'2025-06-11 11:38:00'),(4,'isdefault_types','是否默认地址',2,'是',NULL,NULL,'2025-06-11 11:38:05'),(5,'shangxia_types','上下架',1,'上架',NULL,NULL,'2025-06-11 11:39:02'),(6,'shangxia_types','上下架',2,'下架',NULL,NULL,'2025-06-11 11:39:00'),(7,'tushu_types','图书类型',1,'考试专区',NULL,NULL,'2025-06-11 11:38:57'),(8,'tushu_types','图书类型',2,'文学艺术',NULL,NULL,'2025-06-11 11:38:54'),(9,'tushu_types','图书类型',3,'教材教辅',NULL,NULL,'2025-06-11 11:38:51'),(10,'chat_types','数据类型',1,'问题',NULL,NULL,'2025-06-11 11:38:48'),(11,'chat_types','数据类型',2,'回复',NULL,NULL,'2025-06-11 11:38:46'),(12,'zhuangtai_types','状态',1,'未回复',NULL,NULL,'2025-06-11 11:38:42'),(13,'zhuangtai_types','状态',2,'已回复',NULL,NULL,'2025-06-11 11:38:40'),(15,'tushu_order_types','订单类型',2,'退款',NULL,NULL,'2025-06-11 11:38:37'),(16,'tushu_order_types','订单类型',3,'已支付',NULL,NULL,'2025-06-11 11:38:33'),(17,'tushu_order_types','订单类型',4,'已发货',NULL,NULL,'2025-06-11 11:38:30'),(18,'tushu_order_types','订单类型',5,'已收货',NULL,NULL,'2025-06-11 11:38:27'),(19,'tushu_order_payment_types','订单支付类型',1,'现金',NULL,NULL,'2025-06-11 11:38:24'),(22,'tushuqiugou_types','图书求购状态',1,'未完成',NULL,NULL,'2025-06-11 11:38:19'),(23,'tushuqiugou_types','图书求购状态',2,'已完成',NULL,NULL,'2025-06-11 11:38:16'),(24,'news_types','公告类型',1,'平台规则',NULL,'','2025-06-12 05:30:34'),(25,'news_types','公告类型',2,'交易指南',NULL,'','2025-06-12 08:00:10'),(26,'news_types','公告类型',3,'温馨小贴士',NULL,'','2025-06-12 08:00:43');
/*!40000 ALTER TABLE `dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `news_name` varchar(200) DEFAULT NULL COMMENT '公告标题 Search111  ',
  `news_photo` varchar(200) DEFAULT NULL COMMENT '公告图片 ',
  `news_types` int NOT NULL COMMENT '公告类型 Search111',
  `news_content` text COMMENT '公告详情 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show1 show2 nameShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COMMENT='公告信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (6,'【规则升级】书籍品相描述规范全面优化，买卖更透明！交易更安心！','http://localhost:8080/ershoushujiaoyipingtai/upload/1749715829682.jpg',2,'<p>亲爱的书友们：</p><p><br></p><p>为提升交易体验，减少因书籍描述不符产生的纠纷，平台现对 《书籍品相描述规范》 进行重要升级！ 即日起（202X年X月X日），所有卖家发布书籍时，必须严格按照以下四级标准选择并详细描述品相：</p><p><br></p><p> 全新品相标准（适用“全新未拆封/未使用”）</p><p><br></p><p>• 书籍塑封完整，无拆封痕迹。</p><p><br></p><p>• 内页崭新，无任何翻阅、划线、笔记、折痕。</p><p><br></p><p>• 必须标注来源（如图：实体店/线上采购订单截图等）。</p><p><br></p><p> 优质品相标准（适用于九品及以上）</p><p><br></p><p>• 【九五品】接近全新： 轻微阅读痕迹，封面可能极轻微自然存放痕迹，内页无笔迹、无缺页、无严重折角或开胶。</p><p><br></p><p>• 【九品】良好： 阅读痕迹较明显，可有少量自然泛黄、轻微封面磨损或压痕。内页允许不超过10处铅笔划痕/折角（需详细说明位置），无墨笔记号。</p><p><br></p><p>• 必须实拍至少 3 张清晰照片：①封面全景 ②内页随机页 ③瑕疵特写（如有）。</p><p><br></p><p> 良好品相标准（适用于八品至八五品）</p><p><br></p><p>• 【八五品】中等： 使用痕迹较明显，封面/书脊可能有中轻度磨损、小面积污渍或标签残留。内页可有少量（&lt;20%）铅笔或可擦笔记号、部分自然泛黄，不影响阅读。</p><p><br></p><p>• 【八品】合格： 有明显磨损或污渍，封面/书角卷曲。内页允许有不可擦笔迹（非大面积涂画或遮挡文字），少量缺角或开胶。</p><p><br></p><p>• 必须对描述的所有瑕疵进行清晰特写拍照！否则商品将被系统驳回。</p><p><br></p><p> 普通/瑕疵品相标准（八品以下）</p><p><br></p><p>• 严重磨损、大面积污渍、水渍变形、开胶散页、多页笔迹涂画、缺页等显著瑕疵。</p><p><br></p><p>• 强制要求： 必须在商品标题和详情最开头显著位置注明 【瑕疵书】或【处理品】，并上传完整瑕疵情况实拍图/视频（不少于5张图）。</p><p><br></p><p><br></p><p>------</p><p>重要提醒与违规处理</p><p><br></p><p>1. 严禁货不对板： 买家收货后发现实际品相显著低于描述等级（如宣称“九品”实为严重涂画），卖家需承担退货运费并全额退款。情节严重者将封号处理。</p><p><br></p><p>2. 严查模糊照片/隐瞒瑕疵： 平台将抽查照片清晰度。用网络图代替实拍图、故意遮挡瑕疵等行为将被商品下架、扣除信用分，多次违规将限制上架权限。</p><p><br></p><p>3. 买家善用举证： 如收到书后发现问题，请在签收48小时内拍摄清晰视频+照片证据联系客服处理！</p>','2025-06-12 08:12:44'),(7,'【手把手教学】轻松完成交易！下单、支付、发货全流程指南（附地址/充值详解）','http://localhost:8080/ershoushujiaoyipingtai/upload/1749716207179.jpg',2,'<p>亲爱的书友们：</p><p><br></p><p>为帮助您更顺畅地完成二手书交易，平台特整理 「买家下单支付」与「卖家发货收款」 核心操作指南！重点功能包含 地址管理、账户充值与多方式支付</p><p><br></p><p>------</p><p><br></p><p> 一、买家必看：如何下单并支付？</p><p><br></p><p>选好书 → 提交订单</p><p><br></p><p>• 点击“立即购买”或“加入购物车批量结算”。</p><p><br></p><p>• 在订单确认页 【选择/新增收货地址】：</p><p><br></p><p>地址填写入口：点击“管理地址” → 添加新地址（支持姓名、电话、详细门牌号）。</p><p> 小技巧：可设置多个常用地址（如家/公司），下单时一键切换！</p><p><br></p><p>选择支付方式 </p><p><br></p><p>• 方式一：平台账户余额支付</p><p><br></p><p>▪ 需预先充值：进入【我的钱包】→【充值】→ 输入金额 → 选择 微信/支付宝 扫码付款 → 秒到账！</p><p>▪ 余额充足时，勾选“余额支付”即可完成扣款。</p><p><br></p><p>• 方式二：第三方快捷支付（无需充值，直接扣款）</p><p><br></p><p>▪ 勾选 “微信支付” 或 “支付宝” → 自动生成付款码 → 扫码完成支付！</p><p>▪ 支持信用卡/花呗/零钱等渠道（以您付款账户设置为准）。</p><p><br></p><p> 支付成功 → 坐等收货</p><p><br></p><p>• 支付完成后，可在 【我的订单】 实时查看发货状态。</p><p><br></p><p>• 卖家发货后，系统自动推送物流跟踪信息！</p><p><br></p><p><br></p><p>------</p><p><br></p><p>二、卖家必看：如何收款并发货？</p><p><br></p><p>收到订单 → 处理发货</p><p><br></p><p>• 买家付款后，您会收到 系统通知+短信提醒。</p><p><br></p><p>• 进入 【卖家中心-待发货订单】 → 填写快递单号 → 点击“确认发货”。</p><p> 注：务必使用平台认可物流（如顺丰/京东/中通等），否则可能影响赔付！</p><p><br></p><p>买家收货 → 款项自动到账</p><p><br></p><p>• 买家签收后，款项将 自动存入您的平台钱包。</p><p><br></p><p>• 若买家未主动确认，系统将在 签收后第7天自动结算。</p><p><br></p><p>随时提现 </p><p><br></p><p>• 进入 【我的钱包】→【提现】 → 绑定本人支付宝/微信 → 输入金额 → 提交审核（24小时内到账）。</p>','2025-06-12 08:18:16'),(8,'守护书籍，传递价值——二手书清洁、防损与交易安心指南','http://localhost:8080/ershoushujiaoyipingtai/upload/1749716642585.jpg',3,'<p>亲爱的书友们，</p><p><br></p><p>每一本二手书都承载着独特的故事与价值。为确保书籍在流转中保持良好状态，提升买卖双方体验，平台特此分享实用贴士：</p><p><br></p><p><br></p><p>------</p><p><br></p><p>一、书籍清洁养护篇</p><p><br></p><p>1. 基础除尘：</p><p><br></p><p>&nbsp;◦ 使用干净软毛刷（如化妆刷、毛笔）沿书脊向书口方向轻柔刷去封面、书口及内页浮尘。</p><p><br></p><p>&nbsp;◦ 避免湿布擦拭，防止纸张变形或字迹晕染。</p><p><br></p><p>2. 污渍处理：</p><p><br></p><p>&nbsp;◦ 轻度污渍（如指印）： 用高级绘图橡皮擦（非普通橡皮）轻轻单向擦拭。</p><p><br></p><p>&nbsp;◦ 油渍/胶渍： 在污渍背面垫吸水纸，正面用棉签蘸取少量无水酒精（浓度95%以上）点涂，迅速吸干。</p><p><br></p><p>&nbsp;◦ 注意：酒精仅适用于光滑封面，内页或特殊纸张慎用！</p><p><br></p><p>3. 潮气与异味：</p><p><br></p><p>&nbsp;◦ 将书放入密封箱/袋，加入食品级干燥剂或活性炭包，静置3-5天。</p><p><br></p><p>&nbsp;◦ 轻度霉斑可用消毒纸巾轻压（避免摩擦），通风晾干。</p><p><br></p><p>&nbsp;◦ 严重霉变、虫蛀书籍不建议交易，避免健康风险。</p><p><br></p><p><br></p><p>------</p><p><br></p><p>二、打包防损指南</p><p><br></p><p>1. 包装核心：</p><p><br></p><p>&nbsp;◦ 书籍放入自封袋防潮，再以硬纸板夹护封面封底。</p><p><br></p><p>&nbsp;◦ 箱内空隙用气泡膜/揉皱报纸填实，避免运输中晃动。</p><p><br></p><p>2. 快递选择：</p><p><br></p><p>&nbsp;◦ 单本书选用加厚快递文件袋（建议纸板衬底）。</p><p><br></p><p>&nbsp;◦ 多本书籍务必使用硬质五层瓦楞纸箱，尺寸与书本匹配，勿过度挤压。</p><p><br></p><p>3. 加固技巧：</p><p><br></p><p>&nbsp;◦ 书角与书脊处额外包裹气泡膜角垫。</p><p><br></p><p>&nbsp;◦ 箱体外部用胶带井字形缠绕（尤其封合处），提升抗压性。</p><p><br></p><p><br></p><p>------</p><p><br></p><p>三、交易安心提醒</p><p><br></p><p>1. 卖家必读：</p><p><br></p><p>&nbsp;◦ 发货前务必拍摄书籍全景、书脊、瑕疵特写及快递面单同框照片，留存至少7天。</p><p><br></p><p>&nbsp;◦ 贵重书籍建议录制装箱封箱全过程视频，作为纠纷凭证。</p><p><br></p><p>2. 买家必读：</p><p><br></p><p>&nbsp;◦ 签收时当场检查包裹是否破损，如外箱明显变形、浸水，立即拍照并拒收。</p><p><br></p><p>&nbsp;◦ 开箱后若发现品相严重不符（如水渍、涂写未说明），请于签收后48小时内上传带时间水印的证据照片联系客服。</p><p><br></p><p>3. 平台保障：</p><p><br></p><p>&nbsp;◦ 因卖家未如实描述（如隐瞒破损、虫蛀）导致的退货，平台将强制卖家承担退货运费。</p><p><br></p><p>&nbsp;◦ 因运输损毁且卖家打包合规的订单，买方可申请平台运费险赔付（需提供物流红章证明）。</p><p><br></p><p><br></p><p>------</p><p><br></p><p>细微之举，传递信任。您对书籍的每一分用心，都是对阅读生态的守护。</p>','2025-06-12 08:24:24');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,1,'admin','users','管理员','bubuh4fpkes1ebxzsc73lizoq2jnz79m','2022-03-16 06:53:54','2025-06-19 10:06:13'),(2,1,'a1','yonghu','用户','1ikb2akrqz4n11iazikeaxvbz1k9r8a2','2022-03-16 06:57:28','2022-03-16 08:57:48'),(3,2,'a2','yonghu','用户','m26trdx33qvc5of2sywcty25l5vwho9t','2022-03-16 07:34:35','2022-03-16 08:50:16'),(4,4,'xiaoqiang','yonghu','用户','kekfnqfn4pix60yy1d9ekujparff2izw','2025-06-11 08:29:26','2025-06-11 09:43:46'),(5,5,'xiaomei','yonghu','用户','yohggwrtwjqrh6v8y5sr3bcv6rfuhog8','2025-06-11 12:29:13','2025-06-11 13:29:13'),(6,6,'123','yonghu','用户','734y3eah57u2krsqqyggwax7t3cwj687','2025-06-12 05:29:26','2025-06-19 10:10:37'),(7,7,'111','yonghu','用户','cay4moj2ibmcx1d6evo11dtdmcqqredj','2025-06-18 00:59:05','2025-06-18 01:59:06');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tushu`
--

DROP TABLE IF EXISTS `tushu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tushu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `yonghu_id` int DEFAULT NULL COMMENT '用户',
  `tushu_name` varchar(200) DEFAULT NULL COMMENT '图书名称  Search111 ',
  `tushu_photo` varchar(200) DEFAULT NULL COMMENT '图书图片',
  `tushu_zuozhe` varchar(200) DEFAULT NULL COMMENT '作者',
  `tushu_chubanshe` varchar(200) DEFAULT NULL COMMENT '出版社',
  `tushu_types` int DEFAULT NULL COMMENT '图书类型 Search111',
  `tushu_kucun_number` int DEFAULT NULL COMMENT '图书库存',
  `tushu_old_money` decimal(10,2) DEFAULT NULL COMMENT '图书原价 ',
  `tushu_new_money` decimal(10,2) DEFAULT NULL COMMENT '现价',
  `tushu_clicknum` int DEFAULT NULL COMMENT '点击次数 ',
  `shangxia_types` int DEFAULT NULL COMMENT '是否上架 ',
  `tushu_delete` int DEFAULT NULL COMMENT '逻辑删除',
  `tushu_content` text COMMENT '图书简介 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间  show1 show2 photoShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COMMENT='图书';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tushu`
--

LOCK TABLES `tushu` WRITE;
/*!40000 ALTER TABLE `tushu` DISABLE KEYS */;
INSERT INTO `tushu` VALUES (1,2,'图书名称1','http://localhost:8080/ershoushujiaoyipingtai/upload/tushu1.jpg','作者1','出版社1',3,96,625.25,495.97,313,1,2,'图书简介1','2025-06-11 11:39:18'),(2,2,'图书名称2','http://localhost:8080/ershoushujiaoyipingtai/upload/tushu2.jpg','作者2','出版社2',2,97,782.22,261.04,480,1,2,'图书简介2','2025-06-11 11:39:21'),(3,3,'图书名称3','http://localhost:8080/ershoushujiaoyipingtai/upload/tushu3.jpg','作者3','出版社3',2,102,653.34,70.24,69,1,2,'图书简介3','2025-06-11 11:39:24'),(4,1,'图书名称4','http://localhost:8080/ershoushujiaoyipingtai/upload/tushu4.jpg','作者4','出版社4',1,104,997.02,45.84,263,1,2,'图书简介4','2025-06-11 11:39:28'),(5,1,'图书名称5','http://localhost:8080/ershoushujiaoyipingtai/upload/tushu5.jpg','作者5','出版社5',1,105,601.80,407.86,193,1,2,'图书简介5','2025-06-11 11:39:30'),(6,6,'2023版毛概','http://localhost:8080/ershoushujiaoyipingtai/upload/1749716907910.jpg','编写组','高等教育出版社',3,1,25.00,5.00,7,1,1,'<p _msttexthash=\"63850228\" _msthash=\"131\">少量笔记痕迹，九成新，</p>','2025-06-12 08:30:42'),(7,6,'老人与海','http://localhost:8080/ershoushujiaoyipingtai/upload/1750207449782.jpg','海明威','人民教育出版社',2,1,68.00,14.00,6,1,1,'<p _msttexthash=\"16478436\" _msthash=\"519\">喜欢就下丹</p>','2025-06-18 00:46:18'),(8,6,'四级试卷','http://localhost:8080/ershoushujiaoyipingtai/upload/1750320254262.jpg','333','人民教育出版社',1,5,25.00,5.00,3,1,1,'<p _msttexthash=\"4603768\" _msthash=\"146\">全新</p>','2025-06-19 08:05:02');
/*!40000 ALTER TABLE `tushu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tushu_liuyan`
--

DROP TABLE IF EXISTS `tushu_liuyan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tushu_liuyan` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tushu_id` int DEFAULT NULL COMMENT '图书',
  `yonghu_id` int DEFAULT NULL COMMENT '用户',
  `tushu_liuyan_text` text COMMENT '留言内容',
  `reply_text` text COMMENT '回复内容',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '留言时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '回复时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COMMENT='图书留言';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tushu_liuyan`
--

LOCK TABLES `tushu_liuyan` WRITE;
/*!40000 ALTER TABLE `tushu_liuyan` DISABLE KEYS */;
INSERT INTO `tushu_liuyan` VALUES (7,6,6,'有实物图吗',NULL,'2025-06-12 08:31:28',NULL,'2025-06-12 08:31:28'),(8,7,7,'111',NULL,'2025-06-18 01:02:38',NULL,'2025-06-18 01:02:38');
/*!40000 ALTER TABLE `tushu_liuyan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tushu_order`
--

DROP TABLE IF EXISTS `tushu_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tushu_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tushu_order_uuid_number` varchar(200) DEFAULT NULL COMMENT '订单号',
  `address_id` int DEFAULT NULL COMMENT '送货地址 ',
  `tushu_id` int DEFAULT NULL COMMENT '图书',
  `yonghu_id` int DEFAULT NULL COMMENT '用户',
  `buy_number` int DEFAULT NULL COMMENT '购买数量',
  `tushu_order_courier_number` varchar(200) DEFAULT NULL COMMENT '快递单号',
  `tushu_order_courier_name` varchar(200) DEFAULT NULL COMMENT '快递公司',
  `tushu_order_true_price` decimal(10,2) DEFAULT NULL COMMENT '实付价格',
  `tushu_order_types` int DEFAULT NULL COMMENT '订单类型',
  `tushu_order_payment_types` int DEFAULT NULL COMMENT '支付类型',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COMMENT='图书订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tushu_order`
--

LOCK TABLES `tushu_order` WRITE;
/*!40000 ALTER TABLE `tushu_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `tushu_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tushuqiugou`
--

DROP TABLE IF EXISTS `tushuqiugou`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tushuqiugou` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `yonghu_id` int DEFAULT NULL COMMENT '用户',
  `tushuqiugou_name` varchar(200) DEFAULT NULL COMMENT '图书名称  Search111 ',
  `tushuqiugou_photo` varchar(200) DEFAULT NULL COMMENT '图书图片',
  `tushuqiugou_zuozhe` varchar(200) DEFAULT NULL COMMENT '作者',
  `tushuqiugou_chubanshe` varchar(200) DEFAULT NULL COMMENT '出版社',
  `tushu_types` int DEFAULT NULL COMMENT '图书类型 Search111',
  `tushuqiugou_types` int DEFAULT NULL COMMENT '图书求购状态',
  `tushuqiugou_content` text COMMENT '图书详情 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间show2 photoShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='图书求购';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tushuqiugou`
--

LOCK TABLES `tushuqiugou` WRITE;
/*!40000 ALTER TABLE `tushuqiugou` DISABLE KEYS */;
INSERT INTO `tushuqiugou` VALUES (6,6,'我生活的故事','http://localhost:8080/ershoushujiaoyipingtai/upload/1750207281340.jpg','海伦凯勒','民主与建设出版社',2,2,'<p>111</p>','2025-06-18 00:43:08');
/*!40000 ALTER TABLE `tushuqiugou` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_behavior_log`
--

DROP TABLE IF EXISTS `user_behavior_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_behavior_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `yonghu_id` int NOT NULL COMMENT '用户ID',
  `tushu_id` int NOT NULL COMMENT '书籍ID',
  `behavior_type` enum('view','favorite','order','comment') NOT NULL COMMENT '行为类型',
  `behavior_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '行为时间',
  PRIMARY KEY (`id`),
  KEY `yonghu_id` (`yonghu_id`),
  KEY `tushu_id` (`tushu_id`),
  CONSTRAINT `user_behavior_log_ibfk_1` FOREIGN KEY (`yonghu_id`) REFERENCES `yonghu` (`id`),
  CONSTRAINT `user_behavior_log_ibfk_2` FOREIGN KEY (`tushu_id`) REFERENCES `tushu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户行为日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_behavior_log`
--

LOCK TABLES `user_behavior_log` WRITE;
/*!40000 ALTER TABLE `user_behavior_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_behavior_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tag`
--

DROP TABLE IF EXISTS `user_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `yonghu_id` int NOT NULL COMMENT '用户ID',
  `tag` varchar(50) NOT NULL COMMENT '标签',
  `score` decimal(5,2) DEFAULT '1.00' COMMENT '用户对该标签的兴趣得分',
  PRIMARY KEY (`id`),
  KEY `yonghu_id` (`yonghu_id`),
  CONSTRAINT `user_tag_ibfk_1` FOREIGN KEY (`yonghu_id`) REFERENCES `yonghu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户兴趣标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tag`
--

LOCK TABLES `user_tag` WRITE;
/*!40000 ALTER TABLE `user_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','管理员','2025-05-15 16:00:00');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yonghu`
--

DROP TABLE IF EXISTS `yonghu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yonghu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) DEFAULT NULL COMMENT '账户',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `yonghu_name` varchar(200) DEFAULT NULL COMMENT '用户姓名 Search111 ',
  `yonghu_phone` varchar(200) DEFAULT NULL COMMENT '用户手机号',
  `yonghu_id_number` varchar(200) DEFAULT NULL COMMENT '用户身份证号',
  `yonghu_photo` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `sex_types` int DEFAULT NULL COMMENT '性别 Search111',
  `yonghu_email` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `new_money` decimal(10,2) DEFAULT NULL COMMENT '余额 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yonghu`
--

LOCK TABLES `yonghu` WRITE;
/*!40000 ALTER TABLE `yonghu` DISABLE KEYS */;
INSERT INTO `yonghu` VALUES (5,'xiaomei','123456','xiaomei','13368585371','430181200010102250',NULL,2,'1196326361@qq.com',0.00,'2025-06-11 12:29:05'),(6,'123','123','ttt','12345678999','111111111111111111','http://localhost:8080/ershoushujiaoyipingtai/upload/1750207112027.jpg',1,'1196326361@qq.com',290.00,'2025-06-12 05:29:18'),(7,'111','111','小李','13639296999','522123200405162384',NULL,2,'43292957@foxmail.com',100.00,'2025-06-18 00:58:44');
/*!40000 ALTER TABLE `yonghu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ershoushujiaoyipingtai'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-27 11:54:03
