package com.lsf.servlet;

import com.lsf.entity.Paginate;
import com.lsf.entity.User;
import com.lsf.service.State;
import com.lsf.service.UserService;
import com.lsf.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/6 12:44
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/userAdmin.do")
public class UserAdminServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String op = req.getParameter("op");
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (Exception ex) {
        }
        int ret = -1;
        if ("del".equals(op)) {
            ret = service.delete(id);
        } else if ("rec".equals(op)) {
            ret = service.recover(id);
        } else if (user.getState() == (State.SUPER_ADMIN)
                && "deg".equals(op)) {
            ret = service.degrade(id);
        } else if (user.getState() == (State.SUPER_ADMIN)
                && "upg".equals(op)) {
            ret = service.upgrade(id);
        }
        if (ret > 0) {
            req.setAttribute("msg", "操作成功");
        } else if (ret == -1) {
        } else {
            req.setAttribute("msg", "操作失败");
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
        List<User> users = null;
        try {
            users = service.getUsers(pg, word);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
        req.setAttribute("users", users);
        req.setAttribute("pg", pg);
        req.setAttribute("word", word);
        req.getRequestDispatcher("/WEB-INF/pages/userAdmin.jsp").forward(req, resp);
    }
}
