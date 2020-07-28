package edu.njucm.book.chart.entity;

import java.io.Serializable;

/**
 * (PicContent)实体类
 *
 * @author makejava
 * @since 2020-03-25 17:19:40
 */
public class PicContent implements Serializable {
/**
 * 可能用的上：
 * @TableId(type = IdType.ASSIGN_UUID) 
 * @JsonFormat(timezone = "GTM+8", pattern = "yyyy-MM-dd HH:mm:ss") 
 * @TableField(exist = false)
 */
    private static final long serialVersionUID = -13716630371953192L;
    
    /**
     * 图片id
     */
    private Long id;
    
    /**
     * 图片的二进制表达
     */
    private byte[] content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public PicContent(Long id, byte[] content) {
        this.id = id;
        this.content = content;
    }

    public PicContent() {
    }
}