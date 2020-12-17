package com.lsf.service.impl;

import com.lsf.dao.CategoryDao;
import com.lsf.dao.DetailDao;
import com.lsf.dao.impl.CategoryDaoImpl;
import com.lsf.dao.impl.DetailDaoImpl;
import com.lsf.entity.Detail;
import com.lsf.entity.Paginate;
import com.lsf.entity.User;
import com.lsf.entity.vo.DetailVo;
import com.lsf.service.CategoryService;
import com.lsf.service.DetailService;
import com.lsf.service.State;
import com.ly.util.jdbc.TransactionManager;

import java.util.List;


/**
 * @author 刘愿
 * @date 2020/12/4 9:07
 * @see [相关类/方法]
 * @since V1.00
 */

public class DetailServiceImpl implements DetailService {
    private DetailDao dao = new DetailDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private TransactionManager tm = new TransactionManager();

    @Override
    public int addDetail(Detail detail) {
        try {
            tm.start();
            int ret = categoryDao.selectId(detail.getCategoryId());
            if (ret == 0) {
                throw new RuntimeException("无此分类编号");
            }
            ret = dao.insert(detail);
            tm.commit();
            return ret;
        } catch (Exception e) {
            tm.rollback();
            throw e;
        }
    }

    @Override
    public DetailVo getById(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public int modifyInfo(Detail detail, Integer id) {
        return dao.updateInfo(detail, id);
    }

    @Override
    public int addPicPath(String picPath, Integer id) {
        return dao.updatePicPath(picPath, id);
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
    public List<DetailVo> getDetails(Paginate page, Integer categoryId, String word, Integer state) {
        try {
            tm.start();
            int records = dao.selectCount(categoryId, word, state);
            page.setRecords(records);
            List<DetailVo> details = dao.selectDetails((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize(), categoryId, word, state);
            tm.commit();
            return details;
        } catch (RuntimeException e) {
            tm.rollback();
            throw e;
        }
    }

    @Override
    public List<DetailVo> getDetails(Integer categoryId, Integer state, Integer num) {
        return dao.selectDetails(categoryId, state, num);
    }
}
