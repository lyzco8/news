package com.lsf.dao.impl;

import com.lsf.dao.CommentDao;
import com.lsf.entity.Comment;
import com.lsf.entity.Detail;
import com.lsf.entity.vo.CommentVo;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:02
 * @see [相关类/方法]
 * @since V1.00
 */
public class CommentDaoImpl implements CommentDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public int insert(Comment comment) {
        String sql = "insert into news_comment values(0,?,?,?,?,?,1)";
        return template.update(sql, true, comment.getNewsId(), comment.getContent(), comment.getAuthorId(),
                comment.getIp(), comment.getCreateDate());
    }

    @Override
    public int updateState(Integer newState, Integer oldState, Integer id) {
        String sql = "update news_comment set state = ? where id = ? and state = ?";
        return template.update(sql, false, newState, id, oldState);
    }

    @Override
    public int updateInfo(String content, Integer id) {
        String sql = "update news_comment set content = ? where id = ?";
        return template.update(sql, false, content, id);
    }

    @Override
    public List<CommentVo> selectComments(Integer start, Integer size, String word, Integer state) {
        String sql = "select nc.*, nu.username,nd.title\n" +
                "from news_comment nc\n" +
                "         left join news_user nu on nc.authorId = nu.id\n" +
                "         inner join news_detail nd on nc.newsId = nd.id where 1=1";
        List<Object> list = new ArrayList<>();
        if (word != null) {
            sql += " and nc.content like concat( '%', ?, '%')";
            list.add(word);
        }
        sql += " and nc.state > ?";
        list.add(state);
        sql += " order by nc.createDate desc limit ?,?";
        list.add(start);
        list.add(size);
        return template.queryList(sql, CommentVo.class, list.toArray());
    }

    @Override
    public int selectCount(String word, Integer state) {
        String sql = "select count(1) from news_comment where 1=1";
        List<Object> list = new ArrayList<>();
        if (word != null) {
            sql += " and content like concat( '%', ?, '%')";
            list.add(word);
        }
        sql += " and state > ?";
        list.add(state);
        return template.queryScale(sql, Integer.class, list.toArray());
    }

    @Override
    public List<CommentVo> selectComments(Integer id, Integer state, Integer num) {
        String sql = "select nc.*, nu.username,nd.title\n" +
                "from news_comment nc\n" +
                "         left join news_user nu on nc.authorId = nu.id\n" +
                "         inner join news_detail nd on nc.newsId = nd.id where 1=1";
        List<Object> list = new ArrayList<>();
        if (id > 0) {
            sql += " and newsId = ?";
            list.add(id);
        }
        sql += " and nc.state > ?";
        list.add(state);
        if (num > 0) {
            sql += " order by nc.createDate desc limit ?";
            list.add(num);
        }
        return template.queryList(sql, CommentVo.class, list.toArray());
    }
}
