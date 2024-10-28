package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BaseDAO;

public class UserServlet extends HttpServlet {
	BaseDAO dao = new BaseDAO();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String role = request.getParameter("role");

		if ("学生".equals(role)) {
			Object[] data = dao.findSingle(
					"select * from stu where stuno=? and pwd=?", new Object[] {
							name, pwd });
			if (data != null) {
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("loginUser", data);
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			} else {
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		} else if ("教师".equals(role)) {
			Object[] data = dao.findSingle(
					"select * from teacher where teacherno=? and pwd=?",
					new Object[] { name, pwd });
			if (data != null) {
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("loginUser", data);
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			} else {
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		}else{
			Object[] data = dao.findSingle(
					"select * from userinfo where name=? and pwd=?",
					new Object[] { name, pwd });
			if (data != null) {
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("loginUser", data);
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			} else {
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		}
	}

}
