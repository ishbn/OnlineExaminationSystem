package com.OE.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.Student;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletFirst
 */
@WebServlet("/ServletFirst")
public class ServletFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFirst() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int s=0;
		HttpSession session = request.getSession(false); 
		session.setAttribute("s",s);//用于判断考生有没有已确认考试信息
		String username= (String)session.getAttribute("username");//获取用户名session
		DaoStudent ds=DaoFactory.getDaoStudentImpl();
		Student student=ds.search(new Integer(username));//获取考生信息的bean对象
	    String sname=student.getSname();
	    Integer class_id=student.getClass_id();//获取学生所在班级的班级号
	    session.setAttribute("class_id", class_id);//建立班级编号session
		session.setAttribute("student",student);//建立学生session对象用于考生信息确认
		session.setAttribute("sname", sname);//建立学生姓名session对象
		response.sendRedirect("page/student/Whole.html");//进入考试系统的首页
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
