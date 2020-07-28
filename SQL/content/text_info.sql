-- Create table
CREATE TABLE book.text_info
(
    text_id     NUMERIC(10) NOT NULL COMMENT '主键id，文字id',
    text_detail longtext comment '文字详情',
    add_time    DATETIME not null default now() comment '添加时间',
    CONSTRAINT pk_text_info PRIMARY KEY(text_id)
) COMMENT='文字表';