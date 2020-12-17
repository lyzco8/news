package com.lsf.service.impl;

import com.lsf.dao.CommentDao;
import com.lsf.dao.DetailDao;
import com.lsf.dao.UserDao;
import com.lsf.dao.impl.CommentDaoImpl;
import com.lsf.dao.impl.DetailDaoImpl;
import com.lsf.dao.impl.UserDaoImpl;
import com.lsf.entity.Comment;
import com.lsf.entity.Detail;
import com.lsf.entity.Paginate;
import com.lsf.entity.vo.CommentVo;
import com.lsf.service.CommentService;
import com.lsf.service.State;
import com.ly.util.jdbc.TransactionManager;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:06
 * @see [相关类/方法]
 * @since V1.00
 */
public class CommentServiceImpl implements CommentService {
    private CommentDao dao = new CommentDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private TransactionManager tm = new TransactionManager();

    @Override
    public int addComment(Comment comment) {
        try {
            tm.start();
            int ret = dao.insert(comment);
            if (ret == 0) {
                throw new RuntimeException("评论添加失败");
            }
            ret = userDao.updateScore(comment.getAuthorId(), -10);
            if (ret == 0) {
                throw new RuntimeException("积分修改失败");
            }
            tm.commit();
            return ret;
        } catch (RuntimeException e) {
            tm.rollback();
            throw e;
        }
    }

    @Override
    public int modifyInfo(String content, Integer id) {
        return dao.updateInfo(content, id);
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
    public List<CommentVo> getComments(Paginate page, String word, Integer state) {
        try {
            tm.start();
            int records = dao.selectCount(word, state);
            page.setRecords(records);
            List<CommentVo> comments = dao.selectComments((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize(), word, state);
            tm.commit();
            return comments;
        } catch (RuntimeException e) {
            tm.rollback();
            throw e;
        }
    }

    @Override
    public List<CommentVo> getComments(Integer id, Integer state, Integer num) {
        return dao.selectComments(id, state, num);
    }
}
