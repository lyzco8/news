package com.lsf.service;

import com.lsf.entity.Paginate;
import com.lsf.entity.User;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/3 16:20
 * @see [相关类/方法]
 * @since V1.00
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    int registerUser(User user);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 分页查询所有用户
     *
     * @param page
     * @param word
     * @return
     */
    List<User> getUsers(Paginate page, String word);

    /**
     * 按姓名查询用户
     *
     * @param name
     * @param id
     * @return
     */
    int getByName(String name, Integer id);

    /**
     * 按Email查询用户
     *
     * @param email
     * @param id
     * @return
     */
    int getByEmail(String email, Integer id);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 恢复
     *
     * @param id
     * @return
     */
    int recover(Integer id);

    /**
     * 降级
     *
     * @param id
     * @return
     */
    int degrade(Integer id);

    /**
     * 升级
     *
     * @param id
     * @return
     */
    int upgrade(Integer id);

}
