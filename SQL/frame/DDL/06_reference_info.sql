-- Create table
CREATE TABLE book.reference_info
(
    reference_id      NUMERIC(10) NOT NULL COMMENT '主键id，参考文献id',
    book_id           NUMERIC(10) NOT NULL COMMENT '书本id（book_info表的pk）',
    author_name       varchar(50) not null comment '作者',
    reference_name    varchar(255) not null comment '文献名',
    reference_type    varchar(5) not null comment '文献类型：专著[M]，论文集[C]，报纸文章[N]，期刊文章[J]，学位论文[D]，报告[R]，标准[S]，专利[P]，论文集中的析出文献[A]',
    version           varchar(50) not null comment '版本号',
    publish_place     varchar(50) comment '出版地',
    publish_year      varchar(10) comment '出版年',
    add_time          datetime not null default now() comment '添加时间',
    CONSTRAINT pk_reference_info PRIMARY KEY(reference_id)
) COMMENT='参考文献表';