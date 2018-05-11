package com.OE.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OE.dao.DaoClass;
import com.OE.Beans.Class;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletClass
 */
@WebServlet("/ServletClass")
public class ServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private DaoClass daoClass = DaoFactory.getDaoClassImpl();
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("option");
		if(option.equals("class_list")){
			toDoFindClassList(request,response);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void toDoFindClassList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username  = request.getSession().getAttribute("username").toString();
		List<Class> list = daoClass.getClassList(username);
		String listTable = daoClass.teachingListToString(list);
		request.setAttribute("list", listTable);
		request.getRequestDispatcher("./page/teacher/class_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
