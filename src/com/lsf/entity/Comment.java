package com.lsf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 刘愿
 * @date 2020/12/3 15:52
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class Comment {
    private Integer id;
    private Integer newsId;
    private String content;
    private Integer authorId;
    private String ip;
    private Date createDate;
    private Integer state;
}
