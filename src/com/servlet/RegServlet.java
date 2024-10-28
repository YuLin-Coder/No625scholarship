package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BaseDAO;

public class RegServlet extends HttpServlet {
	BaseDAO dao = new BaseDAO();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "/teacher/show.jsp";
		String operate = request.getParameter("operate");
		if (!"stu".equals(operate)) {
			String teacherno = request.getParameter("teacherno");
			String name = request.getParameter("name");
			String dept = request.getParameter("dept");
			String tel = request.getParameter("tel");

			dao.operate("insert into teacher values(null,?,?,?,?,?)",
					new Object[] { teacherno, name, dept, tel, "111111" });
		}else{
			String stuno = request.getParameter("stuno");
			String name = request.getParameter("name");
			String team = request.getParameter("team");
			String tel = request.getParameter("tel");
			String college = request.getParameter("college");

			dao.operate("insert into stu values(null,?,?,?,?,?,?)",
					new Object[] { stuno, name, college,team,tel,"111111"});
		}
		
		request.setAttribute("reg","ok");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
