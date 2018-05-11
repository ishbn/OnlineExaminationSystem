package com.OE.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.Schedule;
import com.OE.dao.DaoSchedule;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletSchedule
 */
@WebServlet("/ServletSchedule")
public class ServletSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method1 = request.getParameter("option");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); 
		response.setCharacterEncoding("utf-8"); 
		if(method1.equals("department"))
		{
			toDoshowDepartmentList(request,response);
		}else if(method1.equals("teacher")){
			toDoShowTeacherList(request,response);
		}else if(method1.equals("schedule")){
			toDoArrangeClass(request,response);
		}
	}

	private void toDoArrangeClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = false;
		String tt = request.getParameter("tt");
		String ss = request.getParameter("ss");
		String cc = request.getParameter("cc");
		int t = Integer.parseInt(tt);
		int s = Integer.parseInt(ss);
		int c = Integer.parseInt(cc);
		
		DaoSchedule dw = DaoFactory.getDaoScheduleImpl();
		Schedule sch = new Schedule();
		sch.setTid(t);
		sch.setSubject_id(s);
		sch.setClass_id(c);
		flag = dw.AddSchedult(sch);
		if(flag)
		{
			StringBuffer sb = new StringBuffer();
			sb = dw.department();
			request.setAttribute("mes", sb);
			request.getRequestDispatcher("page/admin/department.jsp").forward(request, response);
		}
		else
		{
			System.out.println("任课失败！");
		}
		
	}

	private void toDoShowTeacherList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DaoSchedule dw = DaoFactory.getDaoScheduleImpl();
		String x = request.getParameter("p");
		StringBuffer sb = new StringBuffer();
		StringBuffer ab = new StringBuffer();
		StringBuffer cb = new StringBuffer();
		sb = dw.teacher(x);
		ab = dw.subject(x);
		cb = dw.Class(x);
		HttpSession session = request.getSession(false); 
		
		session.setAttribute("tea", sb);
		session.setAttribute("sub", ab);
		session.setAttribute("cla", cb);
		response.sendRedirect("page/admin/schedule.jsp");
		
	}

	private void toDoshowDepartmentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoSchedule dw = DaoFactory.getDaoScheduleImpl();
		StringBuffer sb = new StringBuffer();
		sb = dw.department();
		request.setAttribute("mes", sb);
		request.getRequestDispatcher("page/admin/department.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
