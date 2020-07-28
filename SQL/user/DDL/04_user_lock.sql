create table book.user_lock(
rec_id int(10) not null comment '对照ID',
user_id numeric (10,0) not null comment '用户ID',
fail_time numeric (10,0) not null comment '操作失败时间'
CONSTRAINT pk_user_lock PRIMARY KEY(rec_id)
)comment='用户锁定表';