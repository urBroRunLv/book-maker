drop table if exists 'user_info';
CREATE TABLE book.USER_INFO(
user_id NUMERIC(10) NOT NULL COMMENT '主键,用户id',
user_name VARCHAR(20) NOT NULL COMMENT '用户名',
user_password VARCHAR(30) NOT NULL COMMENT '用户密码',
user_phone VARCHAR(24) not null COMMENT '用户电话',
parent_user_id NUMERIC(10) not null COMMENT'隶属用户id',
user_type NUMERIC(1) NOT NULL COMMENT '用户身份，0-系统管理员，1-主编，2-编委',
add_time datetime not null default now() comment '添加时间',
constraint pk_user_info PRIMARY KEY(user_id)
)COMMENT='用户表';

-- user_phone唯一索引
ALTER TABLE user_info ADD UNIQUE (user_phone);