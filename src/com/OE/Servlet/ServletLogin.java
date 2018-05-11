package com.OE.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.dao.DaoLogin;
import com.OE.daoFactory.DaoFactory;


/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DaoLogin dl = DaoFactory.getDaoLoginImpl();
		String type = dl.check(username,password);
		if(type.equals("admin")){
			HttpSession session = request.getSession(false); 
			session.setAttribute("username", username);
			response.sendRedirect("page/admin/adminIndex.jsp");
		}else if(type.equals("student")){
			HttpSession session = request.getSession(false); 
			session.setAttribute("username", username);//记住学生学号
			//session.setAttribute("password", password);//记住学生密码
			request.getRequestDispatcher("/ServletFirst").forward(request,response);//跳到寻找学生信息的servlet
			//response.sendRedirect("page/student/studentHome.jsp");
			
		}else if(type.equals("teacher")){
			
			HttpSession session = request.getSession(false); 
			session.setAttribute("username", username);
			response.sendRedirect("page/teacher/teacherIndex.jsp");
		}
		else{
			response.sendRedirect("login.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
