package com.lsf.service;

import com.lsf.entity.Category;
import com.lsf.entity.Detail;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:05
 * @see [相关类/方法]
 * @since V1.00
 */
public interface CategoryService {
    /**
     * 添加新闻分类
     * @param category
     * @return
     */
    int addCategory(Category category);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 恢复
     * @param id
     * @return
     */
    int recover(Integer id);

    /**
     * 修改新闻分类信息
     * @param name
     * @param id
     * @return
     */
    int modifyInfo(String name,Integer id);

    /**
     * 查找分类
     * @param state
     * @param num
     * @return
     */
    List<Category> getCategories(Integer state,Integer num);

}
