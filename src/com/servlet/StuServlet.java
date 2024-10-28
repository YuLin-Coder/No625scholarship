package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BaseDAO;
import java.util.*;
public class StuServlet extends HttpServlet {
	BaseDAO dao = new BaseDAO();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "/stu/show.jsp";
		String operate = request.getParameter("operate");
		if ("add".equals(operate)) {
			String stuno = request.getParameter("stuno");
			String name = request.getParameter("name");
			String team = request.getParameter("team");
			String tel = request.getParameter("tel");
			String college = request.getParameter("college");

			dao.operate("insert into stu values(null,?,?,?,?,?,?)",
					new Object[] { stuno, name, college,team,tel,"111111"});
		}
		if ("del".equals(operate)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.operate("delete from stu where id=" + id, null);
		}
		if ("modify".equals(operate)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Object[] data = dao.findSingle("select * from stu where id="+id, null);
			request.setAttribute("record",data);
			dest = "/stu/modify.jsp";
		}
		
		if ("update".equals(operate)) {
			String stuno = request.getParameter("stuno");
			String name = request.getParameter("name");
			String team = request.getParameter("team");
			String tel = request.getParameter("tel");
			String college = request.getParameter("college");
			String pwd = request.getParameter("pwd");
			int id = Integer.parseInt(request.getParameter("id"));
			dao.operate("update stu set stuno=?,name=?,college=?,team=?,pwd=?,tel=? where id=?",
					new Object[] { stuno, name, college, team,pwd,tel,id});
		}
		
		String role =(String)request.getSession().getAttribute("role");
		if("学生".equals(role)){
			Object[] stu = (Object[])request.getSession().getAttribute("loginUser");
			List all = dao.find("select * from stu where stuno=?",new Object[]{stu[1]});
			request.setAttribute("all", all);
			request.getRequestDispatcher(dest).forward(request,
					response);
		}else{
			List all = dao.find("select * from stu", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher(dest).forward(request,
					response);
		}

	}
}
