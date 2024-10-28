package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BaseDAO;
import com.jspsmart.upload.SmartUpload;

public class NoticeServlet extends HttpServlet {
	BaseDAO dao = new BaseDAO();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "/notice/show.jsp";
		String operate = request.getParameter("operate");
		SmartUpload su = new SmartUpload();
		su.initialize(getServletConfig(), request, response);

		if ("add".equals(operate)) {
			try {
				su.upload();
				String path = getServletContext().getRealPath("/upload");
				su.save(path);
				String doc = su.getFiles().getFile(0).getFileName();
				String title = su.getRequest().getParameter("title");

				dao.operate("insert into notice values(null,?,?,?)",
						new Object[] { title, doc, new Date() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("del".equals(operate)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.operate("delete from notice where id=" + id, null);
		}
		if ("modify".equals(operate)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Object[] data = dao.findSingle("select * from notice where id="
					+ id, null);
			request.setAttribute("data", data);
			dest = "/notice/modify.jsp";
		}

		if ("update".equals(operate)) {
			try {
				su.upload();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			String title = su.getRequest().getParameter("title");
			int id = Integer.parseInt(su.getRequest().getParameter("id"));
			String doc = su.getFiles().getFile(0).getFileName();
			if (!"".equals(doc)) {
				try {
					String path = getServletContext().getRealPath("/upload");
					su.save(path);
					dao.operate("update notice set title=?,doc=? where id=?",
							new Object[] { title, doc, id });
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				dao.operate("update notice set title=? where id=?",
						new Object[] { title, id });
			}

		}

		List all = dao.find("select * from notice", null);
		request.setAttribute("all", all);
		request.getRequestDispatcher(dest).forward(request, response);
	}
}
