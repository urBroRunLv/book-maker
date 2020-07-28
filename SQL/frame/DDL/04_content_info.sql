-- Create table
CREATE TABLE book.content_info
(
  content_id     NUMERIC(10) NOT NULL COMMENT '主键id，内容id',
  chapter_id     NUMERIC(10) NOT NULL COMMENT '章节id（chapter_info表的pk）',
  text_id        NUMERIC(10) comment '文字信息id（text_info表的pk）',
  pic_id         NUMERIC(10) comment '图片信息id（pic_info表的pk）',
  content_name   VARCHAR(512) comment '标题',
  content_type   NUMERIC(2) not null comment '内容类型：1-文字，2-图片，3-图表，4-知识点',
  content_status NUMERIC(2) NOT NULL DEFAULT 0 COMMENT '章节状态，0-正常，1-删除',
  add_time       DATETIME not null default now() comment '添加时间',
  CONSTRAINT pk_content_info PRIMARY KEY(content_id)
) COMMENT='内容表';