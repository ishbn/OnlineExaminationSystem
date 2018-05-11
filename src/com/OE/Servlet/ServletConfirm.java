package com.OE.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletConfirm
 */
@WebServlet("/ServletConfirm")
public class ServletConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		int s=(int) session.getAttribute("s");
		/*
		 * 判断是否已确认考试信息
		 * */
		if(s==0) {
			response.sendRedirect("page/student/studentconfirm.jsp");
			return;
		}
		/*已确认身份的考生跳过身份认证直接选取科目考试
		 * */
		if(s==1) {
			request.getRequestDispatcher("/ServletSubject?option=subjectlist").forward(request,response);//跳到选择考试科目的servlet
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
