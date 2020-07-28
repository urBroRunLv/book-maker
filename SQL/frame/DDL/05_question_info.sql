-- Create table
CREATE TABLE book.question_info
(
    question_id      NUMERIC(10) NOT NULL COMMENT '主键id，段落id',
    content_id       NUMERIC(10) not null comment '知识点id（section_info表的pk）',
    question_type    NUMERIC(2) not null comment '题型：1-选择题，2-填空题，3-简答题',
    question_text_id NUMERIC(10) comment '文字信息id（text_info表的pk）,题干',
    answer_text_id   NUMERIC(10) comment '文字信息id（text_info表的pk）,答案',
    pic_id           NUMERIC(10) comment '图片信息id（pic_info表的pk）附图',
    question_status  NUMERIC(2) NOT NULL DEFAULT 0 COMMENT '题目状态，0-正常，1-删除',
    add_time         DATETIME not null default now() comment '添加时间',
    CONSTRAINT pk_question_info PRIMARY KEY(question_id)
) COMMENT='题目表';