package com.lsf.dao;

import com.lsf.entity.Comment;
import com.lsf.entity.Detail;
import com.lsf.entity.vo.CommentVo;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:00
 * @see [相关类/方法]
 * @since V1.00
 */
public interface CommentDao {
    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    int insert(Comment comment);

    /**
     * 修改评论状态
     *
     * @param newState
     * @param oldState
     * @param id
     * @return
     */
    int updateState(Integer newState, Integer oldState, Integer id);

    /**
     * 修改评论内容
     *
     * @param content
     * @param id
     * @return
     */
    int updateInfo(String content, Integer id);

    /**
     * 分页查询所有评论
     *
     * @param start
     * @param size
     * @param word
     * @param state
     * @return
     */
    List<CommentVo> selectComments(Integer start, Integer size, String word, Integer state);

    /**
     * 获得评论数量
     * @param word
     * @param state
     * @return
     */
    int selectCount(String word, Integer state);

    /**
     * 按条查询评论
     * @param id
     * @param state
     * @param num
     * @return
     */
    List<CommentVo> selectComments(Integer id,Integer state,Integer num);
}
