-- 数据库1数据
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

insert into goods(`name`,num) values("商品1",2);
insert into goods(`name`,num) values("商品2",3);
insert into goods(`name`,num) values("商品3",6);
insert into goods(`name`,num) values("商品4",20);
insert into goods(`name`,num) values("商品5",221);
insert into goods(`name`,num) values("商品6",9);
insert into goods(`name`,num) values("商品7",1);

-- 数据库2数据
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

insert into goods(`name`,num) values("goods1",2);
insert into goods(`name`,num) values("goods2",3);
insert into goods(`name`,num) values("goods3",6);
insert into goods(`name`,num) values("goods4",20);
insert into goods(`name`,num) values("goods5",221);
insert into goods(`name`,num) values("goods6",9);
insert into goods(`name`,num) values("goods7",1);