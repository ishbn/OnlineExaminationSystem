package com.OE.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletPassword
 */
@WebServlet("/ServletPassword")
public class ServletPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		/*修改密码*/
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;setchar=UTF-8");

		String newpwd = request.getParameter("newpwd");
		String newpwdrepeat=request.getParameter("newpwdrepeat");
		if(newpwd.equals(newpwdrepeat)) {
			HttpSession session = request.getSession(false); 
			String username= (String)session.getAttribute("username");
			DaoStudent ds=DaoFactory.getDaoStudentImpl();
			boolean ok=ds.pwdupdate(new Integer(username), newpwd);
			if(ok) {
				session.setAttribute("password", newpwd);
				PrintWriter out = response.getWriter();
				out.print("<script>alert('密码修改成功'); window.location='page/student/changePassword.jsp'</script>");
			}
			
		}
		else {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('两次输入的密码不一致'); window.location='page/student/changePassword.jsp'</script>");
			out.flush();
			out.close();
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
