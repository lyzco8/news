package com.lsf.dao;

import com.lsf.entity.Detail;
import com.lsf.entity.User;
import com.lsf.entity.vo.DetailVo;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:01
 * @see [相关类/方法]
 * @since V1.00
 */
public interface DetailDao {
    /**
     * 添加新闻
     *
     * @param detail
     * @return
     */
    int insert(Detail detail);

    /**
     * 修改新闻状态
     *
     * @param newState
     * @param oldState
     * @param id
     * @return
     */
    int updateState(Integer newState, Integer oldState, Integer id);

    /**
     * 根据id查找新闻
     *
     * @param id
     * @return
     */
    DetailVo selectById(Integer id);

    /**
     * 修改新闻内容
     *
     * @param detail
     * @param id
     * @return
     */
    int updateInfo(Detail detail, Integer id);

    /**
     * 添加图片路径
     *
     * @param picPath
     * @param id
     * @return
     */
    int updatePicPath(String picPath, Integer id);

    /**
     * 分页查找新闻
     * @param start
     * @param size
     * @param categoryId
     * @param word
     * @param state
     * @return
     */
    List<DetailVo> selectDetails(Integer start, Integer size, Integer categoryId, String word, Integer state);

    /**
     * 新闻记录数
     * @param categoryId
     * @param word
     * @param state
     * @return
     */
    int selectCount(Integer categoryId, String word, Integer state);

    /**
     * 按条数查找新闻
     * @param categoryId
     * @param state
     * @param num
     * @return
     */
    List<DetailVo> selectDetails(Integer categoryId, Integer state, Integer num);
}
