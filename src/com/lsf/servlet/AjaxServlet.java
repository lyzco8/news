package com.lsf.servlet;

import com.alibaba.fastjson.JSON;
import com.lsf.entity.*;
import com.lsf.entity.vo.CommentVo;
import com.lsf.entity.vo.DetailVo;
import com.lsf.service.CategoryService;
import com.lsf.service.CommentService;
import com.lsf.service.DetailService;
import com.lsf.service.UserService;
import com.lsf.service.impl.CategoryServiceImpl;
import com.lsf.service.impl.CommentServiceImpl;
import com.lsf.service.impl.DetailServiceImpl;
import com.lsf.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.*;

/**
 * Ajax集中处理
 *
 * @author 刘愿
 * @date 2020/12/8 19:44
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/doAjax")
public class AjaxServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private DetailService detailService = new DetailServiceImpl();
    private CommentService commentService = new CommentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("checkMsg".equals(op)) {
            doCheckMsg(req, resp);
        } else if ("getCate".equals(op)) {
            doGetCate(req, resp);
        } else if ("getDetailsByNum".equals(op)) {
            doGetDetailsByNum(req, resp);
        } else if ("getDetailsByComm".equals(op)) {
            doGetDetailsByComm(req, resp);
        }  else if ("addComm".equals(op)) {
            doAdd(req, resp);
        } else if ("getCommByNum".equals(op)) {
            doGetCommByNum(req, resp);
        }
    }

    /**
     * 注册信息检查
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doCheckMsg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String str = "";
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        int retName = 0;
        int retEmail = 0;
        if (username != null) {
            retName = userService.getByName(username, 0);
        }
        if (email != null) {
            retEmail = userService.getByEmail(email, 0);
        }
        Map<String, Object> map = new HashMap<>();
        JsonData<Map<String, Object>> data = new JsonData<>();
        if (retName > 0) {
            map.put("name", retName);
        }
        if (retEmail > 0) {
            map.put("email", retEmail);
        }
        data.setData(map);
        str = JSON.toJSONString(data);
        out.write(str);
        out.close();
    }

    /**
     * 获取分类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doGetCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JsonData<List<Category>> data = new JsonData<>();
        String str = "";
        try {
            int state = Integer.parseInt(req.getParameter("state"));
            int num = Integer.parseInt(req.getParameter("num"));
            List<Category> categories = categoryService.getCategories(state, num);
            if (categories.size() == 0) {
                data.setErrorCode(1);
                data.setMessage("分类为空");
            } else {
                data.setErrorCode(0);
                data.setData(categories);
            }
        } catch (RuntimeException e) {
            data.setErrorCode(2);
            data.setMessage(e.getMessage());
        }
        str = JSON.toJSONString(data);
        out.write(str);
        out.close();
    }

    /**
     * 根据数量查找新闻
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doGetDetailsByNum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JsonData<List<DetailVo>> data = new JsonData<>();
        String str = "";
        try {
            int id = Integer.parseInt(req.getParameter("categoryId"));
            int state = Integer.parseInt(req.getParameter("state"));
            int num = Integer.parseInt(req.getParameter("num"));
            List<DetailVo> details = detailService.getDetails(id, state, num);
            if (details.size() == 0) {
                data.setErrorCode(1);
                data.setMessage("新闻为空");
            } else {
                data.setErrorCode(0);
                data.setData(details);
            }
        } catch (RuntimeException e) {
            data.setErrorCode(2);
            data.setMessage(e.getMessage());
        }
        str = JSON.toJSONString(data);
        out.write(str);
        out.close();
    }

    /**
     * 查询有评论的新闻
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doGetDetailsByComm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JsonData<List<Detail>> data = new JsonData<>();
        String str = "";
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int state = Integer.parseInt(req.getParameter("state"));
            int num = Integer.parseInt(req.getParameter("num"));
            List<CommentVo> comments = commentService.getComments(id, state, num);
            if (comments.size() == 0) {
                data.setErrorCode(1);
                data.setMessage("新闻为空");
            } else {
                List<Detail> list = new ArrayList<>();
                for (int i = 0; i < comments.size(); i++) {
                    Detail detail = detailService.getById(comments.get(i).getNewsId());
                    list.add(detail);
                }
                data.setErrorCode(0);
                data.setData(list);
            }
        } catch (RuntimeException e) {
            data.setErrorCode(2);
            data.setMessage(e.getMessage());
        }
        str = JSON.toJSONString(data);
        out.write(str);
        out.close();
    }

    /**
     * 添加评论
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JsonData<String> data = new JsonData<>();
        String content = req.getParameter("content");
        String str = "";
        try {
            int newsId = Integer.parseInt(req.getParameter("newsId"));
            int authorId = Integer.parseInt(req.getParameter("authorId"));
            Comment comment = new Comment();
            comment.setNewsId(newsId);
            comment.setContent(content);
            comment.setAuthorId(authorId);
            comment.setIp(InetAddress.getLocalHost().getHostAddress());
            comment.setCreateDate(new Date());
            int ret = commentService.addComment(comment);
            if (ret > 0) {
                data.setData("评论成功，积分+10");
                data.setErrorCode(0);
            } else {
                data.setErrorCode(1);
                data.setMessage("评论添加失败");
            }
        } catch (RuntimeException ex) {
            req.setAttribute("msg", ex.getMessage());
        }
        str = JSON.toJSONString(data);
        out.write(str);
        out.close();
    }

    /**
     * 按数量查找评论
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doGetCommByNum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JsonData<List<CommentVo>> data = new JsonData<>();
        String str = "";
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int state = Integer.parseInt(req.getParameter("state"));
            int num = Integer.parseInt(req.getParameter("num"));
            List<CommentVo> commentVos = commentService.getComments(id, state, num);
            if (commentVos.size() == 0) {
                data.setErrorCode(1);
                data.setMessage("评论为空");
            } else {
                data.setErrorCode(0);
                data.setData(commentVos);
            }
        } catch (RuntimeException e) {
            data.setErrorCode(2);
            data.setMessage(e.getMessage());
        }
        str = JSON.toJSONString(data);
        out.write(str);
        out.close();
    }
}
