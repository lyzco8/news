package com.lsf.dao.impl;

import com.lsf.dao.DetailDao;
import com.lsf.entity.Detail;
import com.lsf.entity.User;
import com.lsf.entity.vo.DetailVo;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/4 9:03
 * @see [相关类/方法]
 * @since V1.00
 */
public class DetailDaoImpl implements DetailDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public int insert(Detail detail) {
        String sql = "insert into news_detail values(0,?,?,?,?,?,?,?,?,1)";
        return template.update(sql, true, detail.getCategoryId(), detail.getTitle(),
                detail.getSummary(), detail.getContent(), detail.getPicPath(), detail.getAuthorId(),
                detail.getCreateDate(), detail.getModifyDate());
    }

    @Override
    public int updateState(Integer newState, Integer oldState, Integer id) {
        String sql = "update news_detail set state = ? where id = ? and state = ?";
        return template.update(sql, false, newState, id, oldState);
    }

    @Override
    public DetailVo selectById(Integer id) {
        String sql = "select nd.*, nc.name, nu.username\n" +
                "from news_detail nd\n" +
                "         left join news_category nc on nd.categoryId = nc.id\n" +
                "         inner join news_user nu on nd.authorId = nu.id\n" +
                "where nd.id = ?";
        return template.queryOne(sql, DetailVo.class, id);
    }

    @Override
    public int updateInfo(Detail detail, Integer id) {
        String sql = "update news_detail set categoryId = ?,title = ?,summary = ?," +
                "content = ?,authorId = ?,modifyDate = ? where id = ?";
        return template.update(sql, false, detail.getCategoryId(), detail.getTitle(),
                detail.getSummary(), detail.getContent(), detail.getAuthorId(), detail.getModifyDate(), id);
    }

    @Override
    public int updatePicPath(String picPath, Integer id) {
        String sql = "update news_detail set picPath = ? where id = ?";
        return template.update(sql, false, picPath, id);
    }

    @Override
    public List<DetailVo> selectDetails(Integer start, Integer size, Integer categoryId, String word, Integer state) {
        String sql = "select nd.*,nc.name,nu.username from news_detail nd left join news_category nc " +
                " on nd.categoryId = nc.id inner join news_user nu " +
                " on nd.authorId = nu.id where 1=1";
        List<Object> list = new ArrayList<>();
        if (categoryId > 0) {
            sql += " and nd.categoryId = ?";
            list.add(categoryId);
        }
        if (word != null) {
            sql += " and nd.title like concat( '%', ?, '%')";
            list.add(word);
        }
        sql += " and nd.state > ?";
        list.add(state);
        sql += " order by nd.id limit ?,?";
        list.add(start);
        list.add(size);
        return template.queryList(sql, DetailVo.class, list.toArray());
    }

    @Override
    public int selectCount(Integer categoryId, String word, Integer state) {
        String sql = "select count(1) from news_detail where 1=1";
        List<Object> list = new ArrayList<>();
        if (categoryId > 0) {
            sql += " and categoryId = ?";
            list.add(categoryId);
        }
        if (word != null) {
            sql += " and title like concat( '%', ?, '%')";
            list.add(word);
        }
        sql += " and state > ?";
        list.add(state);
        return template.queryScale(sql, Integer.class, list.toArray());
    }

    @Override
    public List<DetailVo> selectDetails(Integer categoryId, Integer state, Integer num) {
        String sql = "select nd.*,nc.name,nu.username from news_detail nd left join news_category nc " +
                " on nd.categoryId = nc.id inner join news_user nu " +
                " on nd.authorId = nu.id where 1=1";
        List<Object> list = new ArrayList<>();
        if (categoryId > 0) {
            sql += " and nd.categoryId = ?";
            list.add(categoryId);
        }
        sql += " and nd.state > ?";
        list.add(state);
        if (num > 0) {
            sql += " order by nd.createDate desc limit ?";
            list.add(num);
        }
        return template.queryList(sql, DetailVo.class, list.toArray());
    }


}
