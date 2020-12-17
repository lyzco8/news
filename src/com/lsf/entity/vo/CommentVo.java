package com.lsf.entity.vo;

import com.lsf.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 刘愿
 * @date 2020/12/10 10:26
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentVo extends Comment {
    private String username;
    private String title;
}
