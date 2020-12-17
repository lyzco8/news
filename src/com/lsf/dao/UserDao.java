package com.lsf.dao;

import com.lsf.entity.User;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/3 16:19
 * @see [相关类/方法]
 * @since V1.00
 */
public interface UserDao {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     *查询用户
     * @param user
     * @return
     */
    User selectByUser(User user);

    /**
     * 按姓名查询用户
     *
     * @param name
     * @param id
     * @return
     */
    int selectByName(String name, Integer id);

    /**
     * 按Email查询用户
     *
     * @param email
     * @param id
     * @return
     */
    int selectByEmail(String email, Integer id);

    /**
     * 按用户id查询用户信息
     * @param id
     * @return
     */
    User selectById(Integer id);

    /**
     *修改用户积分
     * @param id
     * @param score
     * @return
     */
    int updateScore(Integer id,Integer score);

    /**
     * 修改用户状态
     * @param id
     * @param newState
     * @param oldState
     * @return
     */
    int updateState(Integer id,Integer newState,Integer oldState);

    /**
     * 分页查询所有用户
     * @param start
     * @param size
     * @param word
     * @return
     */
    List<User> selectUsers(Integer start, Integer size, String word);

    /**
     * 获得用户数量
     * @param word
     * @return
     */
    int selectCount(String word);
}
