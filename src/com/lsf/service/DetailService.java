package com.lsf.service;

import com.lsf.entity.Detail;
import com.lsf.entity.Paginate;
import com.lsf.entity.User;
import com.lsf.entity.vo.DetailVo;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:04
 * @see [相关类/方法]
 * @since V1.00
 */
public interface DetailService {
    /**
     * 添加新闻
     *
     * @param detail
     * @return
     */
    int addDetail(Detail detail);

    /**
     * 根据id查找新闻
     *
     * @param id
     * @return
     */
    DetailVo getById(Integer id);

    /**
     * 修改新闻信息
     *
     * @param detail
     * @param id
     * @return
     */
    int modifyInfo(Detail detail, Integer id);

    /**
     * 添加新闻图片
     *
     * @param picPath
     * @param id
     * @return
     */
    int addPicPath(String picPath, Integer id);

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
     * 分页查找新闻
     *
     * @param page
     * @param categoryId
     * @param word
     * @param state
     * @return
     */
    List<DetailVo> getDetails(Paginate page, Integer categoryId, String word, Integer state);

    /**
     * 按条数查找新闻
     * @param categoryId
     * @param state
     * @param num
     * @return
     */
    List<DetailVo> getDetails(Integer categoryId, Integer state, Integer num);
}
