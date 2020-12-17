package com.lsf.entity;

import lombok.Data;

/**
 * @author 刘愿
 * @date 2020/12/3 15:53
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private int score;
    private int state;
}
