package com.lsf.service.impl;

import com.lsf.dao.UserDao;
import com.lsf.dao.impl.UserDaoImpl;
import com.lsf.entity.Paginate;
import com.lsf.entity.User;
import com.lsf.service.State;
import com.lsf.service.UserService;
import com.lsf.utils.Md5Util;
import com.ly.util.jdbc.TransactionManager;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/3 16:22
 * @see [相关类/方法]
 * @since V1.00
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    private TransactionManager tm = new TransactionManager();

    @Override
    public int registerUser(User user) {
        try {
            tm.start();
            int ret = dao.selectByName(user.getUsername(), 0);
            if (ret > 0) {
                throw new RuntimeException("用户名[" + user.getUsername() + "]已存在！");
            }
            ret = dao.selectByEmail(user.getEmail(), 0);
            if (ret > 0) {
                throw new RuntimeException("Email[" + user.getEmail() + "]已存在！");
            }
            user.setPassword(Md5Util.getMD5(user.getPassword()));
            ret = dao.insert(user);
            tm.commit();
            return ret;
        } catch (RuntimeException ex) {
            tm.rollback();
            throw ex;
        }
    }

    @Override
    public User login(User user) {
        user.setPassword(Md5Util.getMD5(user.getPassword()));
        return dao.selectByUser(user);
    }

    @Override
    public List<User> getUsers(Paginate page, String word) {
        try {
            tm.start();
            int records = dao.selectCount(word);
            page.setRecords(records);
            List<User> users = dao.selectUsers((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize(), word);
            tm.commit();
            return users;
        } catch (RuntimeException e) {
            tm.rollback();
            throw e;
        }
    }

    @Override
    public int getByName(String name, Integer id) {
        return dao.selectByName(name, id);
    }

    @Override
    public int getByEmail(String email, Integer id) {
        return dao.selectByEmail(email, id);
    }

    @Override
    public int delete(Integer id) {
        return dao.updateState(id, State.DELETED, State.NORMAL);
    }

    @Override
    public int recover(Integer id) {
        return dao.updateState(id, State.NORMAL, State.DELETED);
    }

    @Override
    public int degrade(Integer id) {
        return dao.updateState(id, State.NORMAL, State.ADMIN);
    }

    @Override
    public int upgrade(Integer id) {
        return dao.updateState(id, State.ADMIN, State.NORMAL);
    }

}
