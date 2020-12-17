package com.lsf.servlet;

import com.alibaba.fastjson.JSON;
import com.lsf.entity.Category;
import com.lsf.entity.Detail;
import com.lsf.entity.JsonData;
import com.lsf.service.CategoryService;
import com.lsf.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/6 12:43
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/category.do")
public class CategoryAdminServlet extends HttpServlet {
    private CategoryService service = new CategoryServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String op = req.getParameter("op");
        if ("add".equals(op)) {
            doAdd(req, resp);
        } else if ("del".equals(op)) {
            doDel(req, resp);
        } else if ("rec".equals(op)) {
            doRec(req, resp);
        } else if ("modify".equals(op)) {
            doModify(req, resp);
        }
        List<Category> categories = null;
        try {
            categories = service.getCategories(-1, 2147483647);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
        session.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/pages/category.jsp").forward(req, resp);
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        try {
            Category category = new Category();
            category.setName(name);
            category.setCreateDate(new Date());
            int ret = service.addCategory(category);
            if (ret > 0) {
                req.setAttribute("msg", "新闻分类添加成功，编号是【" + ret + "】");
            } else {
                req.setAttribute("msg", "新闻分类添加失败");
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

    private void doModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int ret = service.modifyInfo(name, id);
            if (ret > 0) {
                req.setAttribute("msg", "分类编号【" + id + "】信息修改成功");
            } else {
                req.setAttribute("msg", "信息修改失败");
            }
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
    }

}
