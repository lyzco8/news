package com.lsf.service.impl;

import com.lsf.dao.CategoryDao;
import com.lsf.dao.impl.CategoryDaoImpl;
import com.lsf.entity.Category;
import com.lsf.service.CategoryService;
import com.lsf.service.State;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:06
 * @see [相关类/方法]
 * @since V1.00
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl();

    @Override
    public int addCategory(Category category) {
        return dao.insert(category);
    }

    @Override
    public int delete(Integer id) {
        return dao.updateState(State.DELETED, State.NORMAL, id);
    }

    @Override
    public int recover(Integer id) {
        return dao.updateState(State.NORMAL, State.DELETED, id);
    }

    @Override
    public int modifyInfo(String name, Integer id) {
        return dao.updateInfo(name, id);
    }

    @Override
    public List<Category> getCategories(Integer state, Integer num) {
        return dao.selectCategories(state, num);
    }

}
