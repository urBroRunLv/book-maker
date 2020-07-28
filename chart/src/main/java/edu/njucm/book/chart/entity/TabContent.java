package edu.njucm.book.chart.entity;

import java.io.Serializable;
/**
 * (TabContent)实体类
 *
 * @author huanghao
 * @since 2020-03-30 14:29:44
 */


public class TabContent implements Serializable {
/**
 * 可能用的上：
 * @TableId(type = IdType.ASSIGN_UUID) 
 * @JsonFormat(timezone = "GTM+8", pattern = "yyyy-MM-dd HH:mm:ss") 
 * @TableField(exist = false)
 */
    private static final long serialVersionUID = -93456258052041759L;
    
    /**
     * 表格id
     */
    private Long id;
    
    /**
     * 表格的html内容
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TabContent(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}