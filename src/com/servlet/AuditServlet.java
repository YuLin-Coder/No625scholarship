package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BaseDAO;

public class AuditServlet extends HttpServlet {
	BaseDAO dao = new BaseDAO();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operate = request.getParameter("operate");
		
		//学院审核加载
		if("load".equals(operate)){
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/show.jsp").forward(request,response);
		}
		
		if("yes".equals(operate)){
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.operate("update apply set state=? where id="+id, new Object[]{"学院通过"});
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/show.jsp").forward(request,response);
		}
		if("no".equals(operate)){
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.operate("update apply set state=? where id="+id, new Object[]{"学院未通过"});
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/show.jsp").forward(request,response);
		}
		
		
		//学院公示加载
		if("loadTip".equals(operate)){
			List all = dao.find("select * from apply",null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/tip.jsp").forward(request,response);
		}
		
		//学院公示
		if("tipyes".equals(operate)){
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.operate("update apply set state=? where id="+id, new Object[]{"学院公示通过"});
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/tip.jsp").forward(request,response);
		}
		if("tipno".equals(operate)){
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.operate("update apply set state=? where id="+id, new Object[]{"学院公示未通过"});
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/tip.jsp").forward(request,response);
		}
		
		
		//学校审核加载
		if("school".equals(operate)){
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/school.jsp").forward(request,response);
		}
		
		if("schoolyes".equals(operate)){
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.operate("update apply set state=? where id="+id, new Object[]{"学校通过"});
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/show.jsp").forward(request,response);
		}
		
		if("schoolno".equals(operate)){
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.operate("update apply set state=? where id="+id, new Object[]{"学校未通过"});
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/show.jsp").forward(request,response);
		}
		
		
		
		//学校公示加载
		if("schoolTip".equals(operate)){
			List all = dao.find("select * from apply",null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/schooltip.jsp").forward(request,response);
		}
		//学校公示
		if("schoolTipyes".equals(operate)){
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.operate("update apply set state=? where id="+id, new Object[]{"学校公示通过"});
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/tip.jsp").forward(request,response);
		}
		if("schooTiplno".equals(operate)){
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.operate("update apply set state=? where id="+id, new Object[]{"学校公示未通过"});
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher("/audit/tip.jsp").forward(request,response);
		}
		
	}
}
