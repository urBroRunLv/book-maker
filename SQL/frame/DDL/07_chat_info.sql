-- Create table
CREATE TABLE book.chat_info
(
    chat_id         NUMERIC(10) NOT NULL COMMENT '主键id，讨论id',
    chapter_id      NUMERIC(10) NOT NULL COMMENT '章节id（chapter_info表的pk）',
	link_chat_id    NUMERIC(10) not null default 0 comment '关联评论id，0为初始评论',
    user_id          varchar(50) not null comment '评论作者(user_info表的pk)',
    content         varchar(512) comment '内容',
	top_num         NUMERIC(10) not null default 0 comment '点赞数',
    add_time        datetime not null default now() comment '添加时间',
    CONSTRAINT pk_chat_info PRIMARY KEY(chat_id)
) COMMENT='讨论表';