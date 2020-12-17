package com.lsf.service;

import com.lsf.entity.Comment;
import com.lsf.entity.Detail;
import com.lsf.entity.Paginate;
import com.lsf.entity.vo.CommentVo;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:04
 * @see [相关类/方法]
 * @since V1.00
 */
public interface CommentService {
    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 修改评论信息
     *
     * @param content
     * @param id
     * @return
     */
    int modifyInfo(String content, Integer id);

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
     * 分页查看所有评论
     *
     * @param page
     * @param word
     * @param state
     * @return
     */
    List<CommentVo> getComments(Paginate page, String word, Integer state);

    /**
     * 按条数查询新闻数量
     * @param id
     * @param state
     * @param num
     * @return
     */
    List<CommentVo> getComments(Integer id, Integer state, Integer num);
}
