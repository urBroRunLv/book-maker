-- Create table
CREATE TABLE book.chapter_info
(
  chapter_id     NUMERIC(10) NOT NULL COMMENT '主键id，章节id',
  book_id        NUMERIC(10) NOT NULL COMMENT '书本id（book_info表的pk）',
  chapter_name   VARCHAR(512) NOT NULL COMMENT '章节名称',
  chapter_no     NUMERIC(5) NOT NULL comment '章节序号',
  add_time       DATETIME not null default now() comment '添加时间',
  CONSTRAINT pk_chapter_info PRIMARY KEY(chapter_id)
) COMMENT='章节表';