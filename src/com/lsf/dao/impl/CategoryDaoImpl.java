package com.lsf.dao.impl;

import com.lsf.dao.CategoryDao;
import com.lsf.entity.Category;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:02
 * @see [相关类/方法]
 * @since V1.00
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public int insert(Category category) {
        String sql = "insert into news_category values(0,?,?,1)";
        return template.update(sql, true, category.getName(), category.getCreateDate());
    }

    @Override
    public int updateState(Integer newState, Integer oldState, Integer id) {
        String sql = "update news_category set state = ? where id = ? and state = ?";
        return template.update(sql, false, newState, id, oldState);
    }

    @Override
    public int updateInfo(String name, Integer id) {
        String sql = "update news_category set name = ? where id = ?";
        return template.update(sql, false, name, id);
    }

    @Override
    public List<Category> selectCategories(Integer state, Integer num) {
        String sql = "select * from news_category where state > ? order by id limit ?";
        return template.queryList(sql, Category.class, state, num);
    }

    @Override
    public int selectId(Integer id) {
        String sql = "select count(1) from news_category where id = ?";
        return template.queryScale(sql,Integer.class,id);
    }

}
