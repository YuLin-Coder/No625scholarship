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

public class ApplyServlet extends HttpServlet {
	BaseDAO dao = new BaseDAO();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dest = "/apply/show.jsp";
		String operate = request.getParameter("operate");
		SmartUpload su = new SmartUpload();
		su.initialize(getServletConfig(), request, response);

		if ("add".equals(operate)) {
			try {
				su.upload();
				String path = getServletContext().getRealPath("/upload");
				su.save(path);
				String doc = su.getFiles().getFile(0).getFileName();
				String stuno = su.getRequest().getParameter("stuno");
				String name = su.getRequest().getParameter("name");
				String college = su.getRequest().getParameter("college");
				String team = su.getRequest().getParameter("team");
				String type = su.getRequest().getParameter("type");
				dao.operate("insert into apply values(null,?,?,?,?,?,?,?,?,?)",
						new Object[] { stuno,name,college,team,doc,"未审",new Date(),type,0});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("del".equals(operate)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.operate("delete from apply where id=" + id, null);
		}
		if ("modify".equals(operate)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Object[] data = dao.findSingle("select * from apply where id="
					+ id, null);
			request.setAttribute("record", data);
			dest = "/apply/modify.jsp";
		}

		if ("update".equals(operate)) {
			try {
				su.upload();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			String doc = su.getFiles().getFile(0).getFileName();
			String stuno = su.getRequest().getParameter("stuno");
			String name = su.getRequest().getParameter("name");
			String college = su.getRequest().getParameter("college");
			String team = su.getRequest().getParameter("team");
			String type = su.getRequest().getParameter("type");
			int num = Integer.parseInt(su.getRequest().getParameter("num"));
			int id = Integer.parseInt(su.getRequest().getParameter("id"));
			if (!"".equals(doc)) {
				try {
					String path = getServletContext().getRealPath("/upload");
					su.save(path);
					dao.operate("update apply set stuno=?,name=?,college=?,team=?,doc=?,type=?,num=? where id=?",
							new Object[] { stuno,name,college,team, doc,type,num, id });
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				dao.operate("update apply set stuno=?,name=?,college=?,team=? where id=?",
						new Object[] { stuno,name,college,team, id });
			}

		}

		if("select".equals(operate)){
			int id = Integer.parseInt(request.getParameter("id"));
			Object[] stu = (Object[])request.getSession().getAttribute("loginUser");
			
			dao.operate("delete from record where stuno=? and stuname=? and aid=?", new Object[]{stu[1],stu[2],id});
			dao.operate("insert into record values(null,?,?,?)", new Object[]{stu[1],stu[2],id});
			

			request.setAttribute("select","已投票!");
			
			/*计算得票数的百分比*/
			
			//1.获取班级人数
			Object[] team = dao.findSingle("select count(id) from stu where team=?", new Object[]{stu[4]}); 
			int total = ((Long)team[0]).intValue();
			
			//2.获取该申请投票的人数并更新票数
			Object[] record = dao.findSingle("select count(id) from record where aid=?", new Object[]{id}); 
			int num = ((Long)record[0]).intValue();
			dao.operate("update apply set num=? where id=?", new Object[]{num,id});
		
			
			//3.获取得票率
			float tickets = (float)num;
			float percent = tickets/total;
		
			//如果全班人50%通过,则投票通过
			if(percent>=0.5){
			   dao.operate("update apply set state=? where id=?", new Object[]{"投票通过",id});
			}
			
		}
		
		String role =(String)request.getSession().getAttribute("role");
		if("学生".equals(role)){
			Object[] stu = (Object[])request.getSession().getAttribute("loginUser");
			List all = dao.find("select * from apply where team=?",new Object[]{stu[4]});
			request.setAttribute("all", all);
			request.getRequestDispatcher(dest).forward(request,
					response);
		}else{
			List all = dao.find("select * from apply", null);
			request.setAttribute("all", all);
			request.getRequestDispatcher(dest).forward(request,
					response);
		}
	}
}
