package com.lsf.servlet;


import com.lsf.entity.Paginate;
import com.lsf.entity.User;
import com.lsf.entity.vo.CommentVo;
import com.lsf.entity.vo.DetailVo;
import com.lsf.service.CommentService;
import com.lsf.service.DetailService;
import com.lsf.service.UserService;
import com.lsf.service.impl.CommentServiceImpl;
import com.lsf.service.impl.DetailServiceImpl;
import com.lsf.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 主页数据处理Servlet
 * @author 刘愿
 * @date 2020/12/4 9:33
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();
    private DetailService detailService = new DetailServiceImpl();
    private CommentService commentService = new CommentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String op = req.getParameter("op");
        if ("showreg".equals(op)) {
            req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
        } else if ("login".equals(op)) {
            doLogin(req, resp);
        } else if ("reg".equals(op)) {
            doReg(req, resp);
        } else if ("logoff".equals(op)) {
            session.removeAttribute("user");
            Cookie ck = new Cookie("name", "");
            resp.addCookie(ck);
            session.invalidate();
            req.setAttribute("msg", "已安全退出");
        } else if ("comm".equals(op)) {
            doGetComm(req, resp);
            req.getRequestDispatcher("/WEB-INF/pages/article.jsp").forward(req, resp);
            return;
        }
        Paginate pg = new Paginate();
        try {
            pg.setPageNo(Integer.parseInt(req.getParameter("pno")));
        } catch (Exception ex) {
            pg.setPageNo(1);
        }
        pg.setPageSize(20);
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
            details = detailService.getDetails(pg, categoryId, word, state);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
        req.setAttribute("detail", details);
        req.setAttribute("pg", pg);
        req.setAttribute("categoryId", categoryId);
        req.setAttribute("word", word);
        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = new User();
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        if (name == null || name.trim().equals("") || pass == null || pass.trim().equals("")) {
            req.setAttribute("msg", "登陆信息不完整");
            req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
            return;
        }
        user.setUsername(name.trim());
        user.setPassword(pass.trim());
        try {
            User temp = service.login(user);
            if (temp == null) {
                req.setAttribute("msg", "用户未注册，请先注册");
                req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
            } else {
                Date now = new Date();
                SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
                session.setAttribute("user", temp);
                session.setAttribute("now", smt.format(now));
                req.setAttribute("msg", "登录成功");
            }
        } catch (Exception ex) {
            req.setAttribute("msg", "登陆异常：" + ex.getMessage());
        }
    }

    private void doReg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        String email = req.getParameter("email");
        try {
            int state = Integer.parseInt(req.getParameter("state"));
            if (name == null || !name.matches("[a-zA-Z0-9\u4e00-\u9fa5]{2,8}")) {
                throw new RuntimeException("姓名不合要求");
            }
            if (pass == null || !pass.equals(repass)) {
                throw new RuntimeException("密码不合要求");
            }
            if (email == null || !email.matches("\\w+@\\w+\\.\\w+")) {
                throw new RuntimeException("Email不合要求");
            }
            User temp = new User();
            temp.setUsername(name);
            temp.setPassword(pass);
            temp.setEmail(email);
            temp.setScore(0);
            temp.setState(state);
            try {
                int ret = service.registerUser(temp);
                if (ret > 0) {
                    req.setAttribute("msg", "用户注册成功");
                    req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
                    return;
                } else {
                    req.setAttribute("msg", "用户注册失败");
                }
            } catch (Exception ex) {
                req.setAttribute("msg", ex.getMessage());
            }
        } catch (RuntimeException rex) {
            req.setAttribute("msg", rex.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }

    /**
     * 根据新闻id查找对应的新闻内容
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doGetComm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            DetailVo detail = detailService.getById(id);
            if (detail != null) {
                req.setAttribute("detail", detail);
            } else {
                req.setAttribute("msg", "无此条新闻");
            }
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
    }
}



