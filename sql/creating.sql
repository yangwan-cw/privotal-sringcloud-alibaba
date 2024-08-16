CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `userAccount` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                            `userPassword` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
                            `unionId` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信开放平台id',
                            `mpOpenId` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公众号openId',
                            `userName` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
                            `userAvatar` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
                            `userProfile` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户简介',
                            `userRole` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
                            `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
                            PRIMARY KEY (`id`),
                            KEY `idx_unionId` (`unionId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';


-- 题目表
create table if not exists question
(
    id          bigint auto_increment comment 'id' primary key,
    title       varchar(512)                       null comment '标题',
    content     text                               null comment '内容',
    tags        varchar(1024)                      null comment '标签列表（json 数组）',
    answer      text                               null comment '题目答案',
    submitNum   int      default 0                 not null comment '题目提交数',
    acceptedNum int      default 0                 not null comment '题目通过数',
    judgeCase   text                               null comment '判题用例（json 数组）',
    judgeConfig text                               null comment '判题配置（json 对象）',
    thumbNum    int      default 0                 not null comment '点赞数',
    favourNum   int      default 0                 not null comment '收藏数',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
    ) comment '题目' collate = utf8mb4_unicode_ci;

-- 题目提交表
create table if not exists question_submit
(
    id         bigint auto_increment comment 'id' primary key,
    language   varchar(128)                       not null comment '编程语言',
    code       text                               not null comment '用户代码',
    judgeInfo  text                               null comment '判题信息（json 对象）',
    status     int      default 0                 not null comment '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
    questionId bigint                             not null comment '题目 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_questionId (questionId),
    index idx_userId (userId)
    ) comment '题目提交';



CREATE TABLE `sys_operation_log` (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id, 主键',
                                     `onlyId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成的唯一id',
                                     `log` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作日志',
                                     `api_address`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'api地址',
                                     `package_path`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '包路径',
                                     `status`          smallint(6) NOT NULL                                          DEFAULT '1' COMMENT '状态, 0: 异常, 1: 正常',
                                     `param_json`      text COLLATE utf8mb4_general_ci COMMENT '请求参数json',
                                     `result_json`     text COLLATE utf8mb4_general_ci COMMENT '响应结果json',
                                     `error_msg`       text COLLATE utf8mb4_general_ci COMMENT '错误信息(status=0时, 记录错误信息)',
                                     `cost_time`       mediumtext COLLATE utf8mb4_general_ci COMMENT '耗时 (毫秒)',
                                     `oper_ip`         varchar(50) COLLATE utf8mb4_general_ci                        DEFAULT NULL COMMENT '操作ip地址',
                                     `oper_ip_address` varchar(500) COLLATE utf8mb4_general_ci                       DEFAULT NULL COMMENT '操作ip地址归属地',
                                     `oper_user_name`  varchar(50) COLLATE utf8mb4_general_ci                        DEFAULT NULL COMMENT '操作人用户名: 目前没有做 rdbc 暂时不需要',
                                     `oper_time`       datetime                                                      DEFAULT NULL COMMENT '操作时间',
                                     PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 547
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='操作日志表';



CREATE TABLE `question` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `title` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
                            `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
                            `tags` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签列表（json 数组）',
                            `answer` text COLLATE utf8mb4_unicode_ci COMMENT '题目答案',
                            `submitNum` int(11) NOT NULL DEFAULT '0' COMMENT '题目提交数',
                            `acceptedNum` int(11) NOT NULL DEFAULT '0' COMMENT '题目通过数',
                            `judgeCase` text COLLATE utf8mb4_unicode_ci COMMENT '判题用例（json 数组）',
                            `judgeConfig` text COLLATE utf8mb4_unicode_ci COMMENT '判题配置（json 对象）',
                            `thumbNum` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
                            `favourNum` int(11) NOT NULL DEFAULT '0' COMMENT '收藏数',
                            `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
                            `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
                            PRIMARY KEY (`id`),
                            KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1811034441237827594 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='题目';




CREATE TABLE `question_submit` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `language` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '编程语言',
                                   `code` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户代码',
                                   `judgeInfo` text COLLATE utf8mb4_general_ci COMMENT '判题信息（json 对象）',
                                   `status` int(11) NOT NULL DEFAULT '0' COMMENT '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
                                   `questionId` bigint(20) NOT NULL COMMENT '题目 id',
                                   `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
                                   `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
                                   PRIMARY KEY (`id`),
                                   KEY `idx_questionId` (`questionId`),
                                   KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1816755981115920387 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='题目提交';