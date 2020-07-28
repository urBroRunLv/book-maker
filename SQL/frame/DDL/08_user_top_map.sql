-- Create table
CREATE TABLE book.user_top_map
(
    rec_id     DECIMAL(10) NOT NULL COMMENT '主键id，对照id',
    user_id    DECIMAL comment '用户id',
	chat_id    DECIMAL comment '评论id',
    add_time    DATETIME not null default now() comment '添加时间',
    CONSTRAINT pk_user_top_map PRIMARY KEY(rec_id)
) COMMENT='用户点赞对照表';