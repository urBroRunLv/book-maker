-- Create table
CREATE TABLE book.BOOK_INFO
(
  book_id     NUMERIC(10) NOT NULL COMMENT '主键ID，书本ID',
  book_name   VARCHAR(512) NOT NULL COMMENT '书本名称',
  book_status NUMERIC(2) NOT NULL DEFAULT 0 COMMENT '书本状态，0-正常，1-删除',
  book_pic    NUMERIC(10) NOT NULL COMMENT '封面图片id',
  add_time    DATETIME not null default now() comment '添加时间',
  CONSTRAINT pk_BOOK_INFO PRIMARY KEY(book_id)
) COMMENT='书本表';