package com.lsf.servlet;

import com.lsf.entity.Detail;
import com.lsf.service.DetailService;
import com.lsf.service.impl.DetailServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/6 16:12
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/modify.do")
public class ModifyInfo extends HttpServlet {
    private DetailService detailService = new DetailServiceImpl();

    private List<String> allowType = null;
    private Long maxFileSize = 0L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        allowType = new ArrayList<>();
        String value = config.getServletContext().getInitParameter("AllowType");
        String[] types = value.split(",");
        for (int i = 0; i < types.length; i++) {
            allowType.add(types[i]);
        }
        value = config.getServletContext().getInitParameter("MaxFileSize");
        try {
            maxFileSize = Long.parseLong(value);
        } catch (Exception ex) {
            maxFileSize = 2L * 1024 * 1024;
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("showDetail".equals(op)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Detail detail = detailService.getById(id);
                if (detail != null) {
                    req.setAttribute("detail", detail);
                    req.getRequestDispatcher("/WEB-INF/pages/modifyDetail.jsp").forward(req, resp);
                    return;
                } else {
                    req.setAttribute("msg", "无此编号新闻");
                }
            } catch (Exception e) {
                req.setAttribute("msg", e.getMessage());
            }
            req.getRequestDispatcher("/WEB-INF/pages/detail.jsp").forward(req, resp);
        } else if ("detail".equals(op)) {
            modifyDetail(req, resp);
        } else if ("pic".equals(op)) {
            doPicture(req, resp);
        }
    }

    private void modifyDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String summary = req.getParameter("summary");
        String content = req.getParameter("content");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            int authorId = Integer.parseInt(req.getParameter("authorId"));
            Detail detail = new Detail();
            detail.setCategoryId(categoryId);
            detail.setTitle(title);
            detail.setSummary(summary);
            detail.setContent(content);
            detail.setAuthorId(authorId);
            detail.setModifyDate(new Date());
            int ret = detailService.modifyInfo(detail, id);
            if (ret > 0) {
                req.setAttribute("msg", "新闻【" + id + "】信息修改成功");
            } else {
                req.setAttribute("msg", "信息修改失败");
            }
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
        }
        req.getRequestDispatcher("detail.do").forward(req, resp);
    }

    private void doPicture(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(req)) {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            try {
                List<FileItem> items = upload.parseRequest(req);
                FileItem fileItem = null;
                String id = null;
                for (int i = 0; items != null && i < items.size(); i++) {
                    FileItem item = items.get(i);
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("id")) {
                            id = item.getString();
                        }
                    } else {
                        fileItem = item;
                    }
                }
//                if (id == null || fileItem == null) {
//                    throw new RuntimeException("数据不全");
//                }
                if (!allowType.contains(fileItem.getContentType())) {
                    throw new RuntimeException("图片格式不允许上传");
                }
                if (fileItem.getSize() > maxFileSize) {
                    throw new RuntimeException("图片大小超过限制");
                }
                ServletContext ctx = getServletContext();
                String path = ctx.getRealPath("/imgs/detail");
                File filePath = new File(path);
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                File target = new File(filePath, id + ".jpg");
                fileItem.write(target);
                String picPath = "imgs/detail/" + id + ".jpg";
                File fPath = new File(picPath);
                if (fPath.exists()) {
                    fPath.delete();
                }
                detailService.addPicPath("imgs/detail/" + id + ".jpg", Integer.parseInt(id));
                req.setAttribute("msg", "ok");
            } catch (Exception e) {
                req.setAttribute("msg", e.getMessage());
            }
        } else {
            req.setAttribute("msg", "不是文件上传");
        }
        req.getRequestDispatcher("detail.do").forward(req, resp);
    }
}
