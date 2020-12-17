package com.lsf.servlet;

import com.lsf.entity.Detail;
import com.lsf.entity.Paginate;
import com.lsf.entity.vo.DetailVo;
import com.lsf.service.DetailService;
import com.lsf.service.impl.DetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * @author 刘愿
 * @date 2020/12/5 15:28
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/detail.do")
public class DetailAdminServlet extends HttpServlet {
    private DetailService service = new DetailServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String op = req.getParameter("op");
        if ("showAdd".equals(op)) {
            req.getRequestDispatcher("/WEB-INF/pages/addDetail.jsp").forward(req, resp);
        } else if ("add".equals(op)) {
            doAdd(req, resp);
        } else if ("del".equals(op)) {
            doDel(req, resp);
        } else if ("rec".equals(op)) {
            doRec(req, resp);
        }
        Paginate pg = new Paginate();
        try {
            pg.setPageNo(Integer.parseInt(req.getParameter("pno")));
        } catch (Exception ex) {
            pg.setPageNo(1);
        }
        try {
            pg.setPageSize(Integer.parseInt(req.getParameter("psize")));
        } catch (Exception ex) {
            pg.setPageSize(1);
        }
        String word = req.getParameter("word");
        int categoryId = 0;
        int state = -1;
        try {
            categoryId = Integer.parseInt(req.getParameter("categoryId"));
            state = Integer.parseInt(req.getParameter("state"));
        } catch (Exception e) {
        }
        List<DetailVo> details = null;
        try {
            details = service.getDetails(pg, categoryId, word, state);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
        session.setAttribute("detail", details);
        req.setAttribute("pg", pg);
        req.setAttribute("categoryId", categoryId);
        req.setAttribute("word", word);
        req.getRequestDispatcher("/WEB-INF/pages/detail.jsp").forward(req, resp);
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String summary = req.getParameter("summary");
        String content = req.getParameter("content");
        try {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            int authorId = Integer.parseInt(req.getParameter("authorId"));
            Detail detail = new Detail();
            detail.setCategoryId(categoryId);
            detail.setTitle(title);
            detail.setSummary(summary);
            detail.setContent(content);
            detail.setAuthorId(authorId);
            detail.setCreateDate(new Date());
            int ret = service.addDetail(detail);
            if (ret > 0) {
                req.setAttribute("msg", "新闻添加成功，编号是【" + ret + "】");
            } else {
                req.setAttribute("msg", "新闻添加失败");
            }
        } catch (RuntimeException ex) {
            req.setAttribute("msg", ex.getMessage());
        }
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int ret = service.delete(id);
            if (ret > 0) {
                req.setAttribute("msg", "新闻【" + id + "】删除成功");
            } else {
                req.setAttribute("msg", "删除失败");
            }
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
    }

    private void doRec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int ret = service.recover(id);
            if (ret > 0) {
                req.setAttribute("msg", "新闻【" + id + "】恢复成功");
            } else {
                req.setAttribute("msg", "恢复失败");
            }
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
    }

}
