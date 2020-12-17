package com.lsf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 刘愿
 * @date 2020/12/3 15:53
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class Detail {
    private int id;
    private int categoryId;
    private String title;
    private String summary;
    private String content;
    private String picPath;
    private Integer authorId;
    private Date createDate;
    private Date modifyDate;
    private Integer state;
}
