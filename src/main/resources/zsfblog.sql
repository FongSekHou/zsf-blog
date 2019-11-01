CREATE DATABASE `zsfblog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `zsfblog`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(64) NOT NULL,
  `gender` tinyint(1) DEFAULT '0' COMMENT '0-男，1-女',
  `birthday` char(10) DEFAULT NULL COMMENT 'format is yyyy-MM-dd',
  `iconpath` varchar(255) DEFAULT '/images/icon/default.jpg',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0-禁用，1-正常',
  `regtime` char(19) DEFAULT NULL COMMENT 'format is yyyy-MM-dd HH:mm:ss',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminname` varchar(16) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-禁用，1-正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `adminname_UNIQUE` (`adminname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_blogtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blogtypename` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `blogtypename_UNIQUE` (`blogtypename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录博客系统的栏目';

CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `completetime` char(19) DEFAULT NULL COMMENT 'format is yyyy-MM-dd HH:mm:ss',
  `status` int(11) DEFAULT NULL COMMENT '0-回收站，1-草稿箱，2-已发表',
  `viewnum` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `likenum` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `commentnum` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `collectnum` int(11) NOT NULL DEFAULT '0' COMMENT '收藏数',
  `user_id` int(11) NOT NULL,
  `blogtype_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_blog_userid_user_id_idx` (`user_id`),
  KEY `fk_blog_blogtypeid_blogtype_id_idx` (`blogtype_id`),
  CONSTRAINT `fk_blog_blogtypeid_blogtype_id` FOREIGN KEY (`blogtype_id`) REFERENCES `t_blogtype` (`id`),
  CONSTRAINT `fk_blog_userid_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_blogimg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imgpath` varchar(255) NOT NULL,
  `blog_id` int(11) DEFAULT NULL COMMENT '允许为空，若为空则对应博客编辑时中途退出或文章被删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `imgpath_UNIQUE` (`imgpath`),
  KEY `fk_img_blogid_blog_id_idx` (`blog_id`),
  CONSTRAINT `fk_img_blogid_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录博客中引用的图片地址';

CREATE TABLE `t_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_collection_userid_user_id_idx` (`user_id`),
  KEY `fk_collection_blogid_blog_id_idx` (`blog_id`),
  CONSTRAINT `fk_collection_blogid_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`),
  CONSTRAINT `fk_collection_userid_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录用户收藏的文章';

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentcontent` varchar(255) NOT NULL,
  `commenttime` char(19) NOT NULL,
  `user_id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  `comment_id` int(11) DEFAULT NULL COMMENT '自关联，回复的评论id，允许为空，若是该字段为空则评论的是文章',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_comment_userid_user_id_idx` (`user_id`),
  KEY `fk_comment_blogid_blog_id_idx` (`blog_id`),
  CONSTRAINT `fk_comment_blogid_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`),
  CONSTRAINT `fk_comment_userid_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `zsfblog`.`t_comment`
ADD INDEX `fk_comment_commentid_comment_id_idx` (`comment_id` ASC) VISIBLE;

ALTER TABLE `zsfblog`.`t_comment`
ADD CONSTRAINT `fk_comment_commentid_comment_id`
  FOREIGN KEY (`comment_id`)
  REFERENCES `zsfblog`.`t_comment` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `read` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未读，1-已读',
  `user_id` int(11) NOT NULL COMMENT '发出点赞或收藏动作的用户id',
  `comment_id` int(11) DEFAULT NULL COMMENT '点赞评论所关联的评论id',
  `blog_id` int(11) DEFAULT NULL COMMENT '点赞博客所关联的博客id',
  `collection_id` int(11) DEFAULT NULL COMMENT '收藏博客的收藏表字段id',
  `noticetime` char(19) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_notice_userid_user_id_idx` (`user_id`),
  KEY `fk_notice_commentid_comment_id_idx` (`comment_id`),
  KEY `fk_notice_blogid_blog_id_idx` (`blog_id`),
  KEY `fk_notice_collectionid_collection_id_idx` (`collection_id`),
  CONSTRAINT `fk_notice_blogid_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notice_collectionid_collection_id` FOREIGN KEY (`collection_id`) REFERENCES `t_collection` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notice_commentid_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notice_userid_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='如果是点赞通知那只有blog_id有值；如果是评论通知则是comment_id有值；如果是收藏通知则是collection_id有值';

/*CREATE TABLE `t_bannedinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL COMMENT '封禁原因',
  `recoverytime` char(19) NOT NULL COMMENT '解禁时间，格式为 yyyy-MM-dd HH:mm:ss',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_ban_userid_user_id_idx` (`user_id`),
  CONSTRAINT `fk_ban_userid_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录被封禁的用户信息';*/

/*CREATE TABLE `t_complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未处理，1-已处理',
  `user_id` int(11) NOT NULL COMMENT '举报的用户',
  `comment_id` int(11) DEFAULT NULL,
  `blog_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_complaint_userid_user_id_idx` (`user_id`),
  KEY `fk_complaint_commentid_comment_id_idx` (`comment_id`),
  KEY `fk_complaint_blogid_blog_id_idx` (`blog_id`),
  CONSTRAINT `fk_complaint_blogid_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`),
  CONSTRAINT `fk_complaint_commentid_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`),
  CONSTRAINT `fk_complaint_userid_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录举报的信息';*/

INSERT INTO `zsfblog`.`t_blogtype` (`blogtypename`) VALUES ('java');
INSERT INTO `zsfblog`.`t_blogtype` (`blogtypename`) VALUES ('javascript');
INSERT INTO `zsfblog`.`t_blogtype` (`blogtypename`) VALUES ('python');
INSERT INTO `zsfblog`.`t_blogtype` (`blogtypename`) VALUES ('其他');