create table book.user_book_map(
rec_id numeric (10,0) not null comment '对照ID',
user_id numeric (10,0) not null comment '用户ID',
book_id numeric (10,0) not null comment '书本ID'
delete_flag NUMERIC(2) not null default 0 comment '删除标记：0-未删除，1-已删除',
add_time datetime not null default now() comment '添加时间',
CONSTRAINT pk_user_book_map PRIMARY KEY(rec_id)
)comment='用户书籍对照表';