package com.lsf.dao;

import com.lsf.entity.Category;
import com.lsf.entity.Detail;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:01
 * @see [相关类/方法]
 * @since V1.00
 */
public interface CategoryDao {
    /**
     * 添加新闻分类
     * @param category
     * @return
     */
    int insert(Category category);

    /**
     * 修改新闻分类状态
     * @param newState
     * @param oldState
     * @param id
     * @return
     */
    int updateState(Integer newState, Integer oldState,Integer id);

    /**
     * 修改新闻内容
     * @param name
     * @param id
     * @return
     */
    int updateInfo(String name,Integer id);

    /**
     * 查找分类
     * @param state
     * @param num
     * @return
     */
    List<Category> selectCategories(Integer state,Integer num);

    /**
     * 按分类编号查找
     * @param id
     * @return
     */
    int selectId(Integer id);
}
