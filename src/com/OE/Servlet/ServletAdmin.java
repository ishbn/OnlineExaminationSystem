package com.OE.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.Admin;
import com.OE.Beans.User;
import com.OE.dao.DaoAdmin;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletAdmin
 */
@WebServlet("/ServletAdmin")
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String method1 = request.getParameter("option");
		if(method1.equals("adminup"))
		{
			HttpSession session = request.getSession(false); 
			String result = (String) session.getAttribute("username");
			if(result!=null)
			{
				
				DaoAdmin dw = DaoFactory.getDaoAdminMessageImpl();
				Admin adm = new Admin();
				adm = dw.AdmSea(result);
				String a = adm.getAdminName();
				String b = adm.getSex();
				String c = adm.getCardNumber();
				String d = adm.getPhone();
				session.setAttribute("q1", a);
				session.setAttribute("q2", b);
				session.setAttribute("q3", c);
				session.setAttribute("q4", d);
				request.getRequestDispatcher("page/admin/adminMessage.jsp").forward(request, response); 
			}
			
		}
		else if(method1.equals("adminupdate"))
		{
			String x = request.getParameter("p");
			String adminName = request.getParameter("adminname");
			String adminSex = request.getParameter("adminsex");
			String adminNumber = request.getParameter("adminnumber");
			String adminPhone = request.getParameter("adminphone");

			Admin adm = new Admin();
			int g =  Integer.parseInt(x);
			adm.setAdmin_id(g);
			adm.setAdminName(adminName);
			adm.setSex(adminSex);
			adm.setCardNumber(adminNumber);
			adm.setPhone(adminPhone);
			DaoAdmin dw = DaoFactory.getDaoAdminMessageImpl();
			boolean flag = dw.AdmUp(adm);
			if(flag)
			{
				StringBuffer sb = new StringBuffer();
				DaoStudent dq = DaoFactory.getDaoStudentMessageImpl();
				sb = dq.StuFin();
				request.setAttribute("mes", sb);
				request.getRequestDispatcher("page/admin/studentList.jsp").forward(request, response); 
			}
			else
			{
				System.out.println("修改失败！");
			}
			
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
