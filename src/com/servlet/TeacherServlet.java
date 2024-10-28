package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BaseDAO;

public class TeacherServlet extends HttpServlet {
	BaseDAO dao = new BaseDAO();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "/teacher/show.jsp";
		String operate = request.getParameter("operate");
		if ("add".equals(operate)) {
			String teacherno = request.getParameter("teacherno");
			String name = request.getParameter("name");
			String dept = request.getParameter("dept");
			String tel = request.getParameter("tel");

			dao.operate("insert into teacher values(null,?,?,?,?,?)",
					new Object[] { teacherno, name, dept,tel,"111111"});
		}
		if ("del".equals(operate)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.operate("delete from teacher where id=" + id, null);
		}
		if ("modify".equals(operate)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Object[] data = dao.findSingle("select * from teacher where id="+id, null);
			request.setAttribute("record",data);
			dest = "/teacher/modify.jsp";
		}
		
		if ("update".equals(operate)) {
			String teacherno = request.getParameter("teacherno");
			String name = request.getParameter("name");
			String dept = request.getParameter("dept");
			String tel = request.getParameter("tel");
			String pwd = request.getParameter("pwd");
			int id = Integer.parseInt(request.getParameter("id"));
			dao.operate("update teacher set teacherno=?,name=?,dept=?,pwd=?,tel=? where id=?",
					new Object[] { teacherno, name, dept,pwd,tel,id});
		}
		
		String role =(String)request.getSession().getAttribute("role");
		if("教师".equals(role)){
			Object[] teacher = (Object[])request.getSession().getAttribute("loginUser");
			List all = dao.find("select * from teacher where teacherno=?",new Object[]{teacher[1]});
			request.setAttribute("all", all);
			request.getRequestDispatcher(dest).forward(request,
					response);
		}else{
			List all = dao.find("select * from teacher", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher(dest).forward(request,
					response);
		}

	}}
