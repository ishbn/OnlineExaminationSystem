package com.OE.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.Teacher;
import com.OE.dao.DaoTeacher;
import com.OE.daoFactory.DaoFactory;

@WebServlet("/ServletTeacher")
public class ServletTeacher extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServletTeacher() {
		// TODO Auto-generated constructor stub
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("option");
		if("add".equals(action))
		{
			add(request,response);
		}
		else if("delete".equals(action))
		{
			delete(request,response);
		}
		else if("show".equals(action))
		{
			show(request,response);
		}
		else if("update".equals(action))
		{
			update(request,response);
		}else if(action.equals("find")){
			toDoFindTeacherByUsername(request,response);
		}else if(action.equals("updatebyTeacher")){
			toDoUpdateByTeacher(request,response);
		}
	}
	
	
	private void toDoUpdateByTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher tc=new Teacher(); 
		String oid=request.getParameter("o");
		int q=Integer.parseInt(oid);
		tc.setTname(request.getParameter("tname"));
		tc.setSex(request.getParameter("sex"));
		tc.setCardNumber(request.getParameter("cardnumber"));
		tc.setTitle(request.getParameter("title"));
		tc.setPhone(request.getParameter("phone"));
		DaoTeacher teacherDao=DaoFactory.getDaoTeacherImpl();
		teacherDao.updateTc(q, tc);
		toDoFindTeacherByUsername(request,response);
		
	}
	private void toDoFindTeacherByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		DaoTeacher daoTeacher = DaoFactory.getDaoTeacherImpl();
		Teacher teacher = daoTeacher.getTeacherByUsername(username);
		request.setAttribute("teacher", teacher);
		
		request.getRequestDispatcher("page/teacher/teacherInformation.jsp").forward(request, response);
		
	}
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher tc=new Teacher(); 
		/*String name=request.getParameter("tname");
		System.out.println(name);*/
		tc.setTname(request.getParameter("tname"));
		tc.setSex(request.getParameter("sex"));
		tc.setCardNumber(request.getParameter("cardnumber"));
		tc.setTitle(request.getParameter("title"));
		tc.setPhone(request.getParameter("phone"));
		String d=request.getParameter("id");
		int did=Integer.parseInt(d);
		DaoTeacher teacherDao=DaoFactory.getDaoTeacherImpl();		
		teacherDao.addTc(did,tc);
		show(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Teacher tc=new Teacher(); 
		String oid=request.getParameter("o");
		int q=Integer.parseInt(oid);
		tc.setTname(request.getParameter("tname"));
		tc.setSex(request.getParameter("sex"));
		tc.setCardNumber(request.getParameter("cardnumber"));
		tc.setTitle(request.getParameter("title"));
		tc.setPhone(request.getParameter("phone"));
		DaoTeacher teacherDao=DaoFactory.getDaoTeacherImpl();
		teacherDao.updateTc(q, tc);
		show(request, response);
		
	}
	public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String did=request.getParameter("id");
		int d=Integer.parseInt(did);
		String Dname=DaoFactory.getDaoDepartmentImpl().queryName(d);
		
		System.out.println(Dname);
		StringBuffer sb= DaoFactory.getDaoTeacherImpl().showTc(d);
		HttpSession session = request.getSession();
		session.setAttribute("departmentname", Dname);
		session.setAttribute("departmentid", d);
		session.setAttribute("result", sb);
		request.getRequestDispatcher("page/admin/teacherHome.jsp").forward(request, response);
		/*response.sendRedirect("page/admin/teacherHome.jsp");*/
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String tid = request.getParameter("name");
		int g=Integer.parseInt(tid);
		DaoFactory.getDaoTeacherImpl().deleteTc(g);
		show(request,response);
	}
}
