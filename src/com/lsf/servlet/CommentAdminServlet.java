package com.lsf.servlet;

import com.alibaba.fastjson.JSON;
import com.lsf.entity.Comment;
import com.lsf.entity.Detail;
import com.lsf.entity.JsonData;
import com.lsf.entity.Paginate;
import com.lsf.entity.vo.CommentVo;
import com.lsf.service.CommentService;
import com.lsf.service.DetailService;
import com.lsf.service.impl.CommentServiceImpl;
import com.lsf.service.impl.DetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/6 12:48
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/comment.do")
public class CommentAdminServlet extends HttpServlet {
    private CommentService service = new CommentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String op = req.getParameter("op");
        if ("del".equals(op)) {
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
        int state = -1;
        try {
            state = Integer.parseInt(req.getParameter("state"));
        } catch (Exception e) {
        }
        List<CommentVo> comments = null;
        try {
            comments = service.getComments(pg, word, state);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
        session.setAttribute("comments", comments);
        req.setAttribute("pg", pg);
        req.setAttribute("word", word);
        req.getRequestDispatcher("/WEB-INF/pages/comment.jsp").forward(req, resp);
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int ret = service.delete(id);
            if (ret > 0) {
                req.setAttribute("msg", "评论【" + id + "】删除成功");
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
                req.setAttribute("msg", "评论【" + id + "】恢复成功");
            } else {
                req.setAttribute("msg", "恢复失败");
            }
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
    }

}
