package com.OE.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.Department;
import com.OE.dao.DaoDepartment;
import com.OE.daoFactory.DaoFactory;

@WebServlet("/ServletDepartment")
public class ServletDepartment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServletDepartment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		toDoAction(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void toDoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("option");

		if ("add".equals(action)) {
			add(request, response);
		} else if ("delete".equals(action)) {
			delete(request, response);
		} else if ("show".equals(action)) {
			show(request, response);
		} else if ("update".equals(action)) {
			update(request, response);
		} 
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Department xy = new Department();
		xy.setDepartmentName(request.getParameter("department_name"));

		DaoDepartment departmentDao = DaoFactory.getDaoDepartmentImpl();
		departmentDao.add(xy);
		StringBuffer sb = DaoFactory.getDaoDepartmentImpl().showall();

		HttpSession session = request.getSession();
		session.setAttribute("result", sb);
		response.sendRedirect("page/admin/departmentHome.jsp");
	}


	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Department xy = new Department();
		String id = request.getParameter("p");
		int g = Integer.parseInt(id);
		xy.setDepartmentName(request.getParameter("department_name"));
		DaoDepartment departmentDao = DaoFactory.getDaoDepartmentImpl();
		departmentDao.update(g, xy);
		StringBuffer sb = DaoFactory.getDaoDepartmentImpl().showall();

		HttpSession session = request.getSession();
		session.setAttribute("result", sb);
		response.sendRedirect("page/admin/departmentHome.jsp");
	}

	public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuffer sb = DaoFactory.getDaoDepartmentImpl().showall();

		HttpSession session = request.getSession();
		session.setAttribute("result", sb);
		response.sendRedirect("page/admin/departmentHome.jsp");
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		int g = Integer.parseInt(id);
		DaoFactory.getDaoDepartmentImpl().delete(g);
		StringBuffer sb = DaoFactory.getDaoDepartmentImpl().showall();

		HttpSession session = request.getSession();
		session.setAttribute("result", sb);
		response.sendRedirect("page/admin/departmentHome.jsp");
	}

}
