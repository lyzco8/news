package com.lsf.dao.impl;

import com.lsf.dao.UserDao;
import com.lsf.entity.Comment;
import com.lsf.entity.User;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/3 16:19
 * @see [相关类/方法]
 * @since V1.00
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public int insert(User user) {
        String sql = "insert into news_user values(0,?,?,?,?,?)";
        return template.update(sql, true, user.getUsername(),
                user.getPassword(), user.getEmail(), user.getScore(), user.getState());
    }

    @Override
    public User selectByUser(User user) {
        String sql = "select * from news_user where username=? and password=? and state>0";
        return template.queryOne(sql, User.class, user.getUsername(), user.getPassword());
    }

    @Override
    public int selectByName(String name, Integer id) {
        String sql = "select count(1) from news_user where username=?";
        List<Object> list = new ArrayList<>();
        if (id > 0) {
            sql += " and id!=?";
            list.add(id);
        }
        return template.queryScale(sql, Integer.class, name, list.toArray());
    }

    @Override
    public int selectByEmail(String email, Integer id) {
        String sql = "select count(1) from news_user where email=?";
        List<Object> list = new ArrayList<>();
        if (id > 0) {
            sql += " and id!=?";
            list.add(id);
        }
        return template.queryScale(sql, Integer.class, email, list.toArray());
    }

    @Override
    public User selectById(Integer id) {
        String sql = "select * from news_user where id=?";
        return template.queryOne(sql, User.class, id);
    }

    @Override
    public int updateScore(Integer id, Integer score) {
        String sql = "update news_user set score=score-? where id=? and score>=?";
        return template.update(sql, false, score, id, score);
    }


    @Override
    public int updateState(Integer id, Integer newState, Integer oldState) {
        String sql = "update news_user set state=? where id=? and state=?";
        return template.update(sql, false, newState, id, oldState);
    }

    @Override
    public List<User> selectUsers(Integer start, Integer size, String word) {
        String sql = "select * from news_user where 1=1";
        List<Object> list = new ArrayList<>();
        if (word != null) {
            sql += " and username like concat( '%', ?, '%')";
            list.add(word);
        }
        sql += " order by id limit ?,?";
        list.add(start);
        list.add(size);
        return template.queryList(sql, User.class, list.toArray());
    }

    @Override
    public int selectCount(String word) {
        String sql = "select count(1) from news_user where 1=1";
        List<Object> list = new ArrayList<>();
        if (word != null) {
            sql += " and username like concat( '%', ?, '%')";
            list.add(word);
        }
        return template.queryScale(sql, Integer.class, list.toArray());
    }

}
