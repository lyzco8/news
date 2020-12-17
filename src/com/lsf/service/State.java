package com.lsf.service;

/**
 * @author 刘愿
 * @date 2020/12/6 11:36
 * @see [相关类/方法]
 * @since V1.00
 */
public interface State {
    Integer DELETED = 0;
    Integer NORMAL = 1;
    Integer ADMIN = 2;
    Integer SUPER_ADMIN = 3;
}
