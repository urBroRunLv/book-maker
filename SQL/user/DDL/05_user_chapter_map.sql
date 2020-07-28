create table book.user_chapter_map(
rec_id int(10) not null comment '对照ID',
user_id numeric (10,0) not null comment '用户ID',
chapter_id numeric (10,0) not null comment '章节ID'
delete_flag NUMERIC(2) not null default 0 comment '删除标记：0-未删除，1-已删除',
add_time datetime not null default now() comment '添加时间',
CONSTRAINT pk_user_chapter_map PRIMARY KEY(rec_id)
)comment='用户章节对照表';