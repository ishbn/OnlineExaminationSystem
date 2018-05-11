package com.OE.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.Admin;
import com.OE.Beans.ExaminationArrangement;
import com.OE.Beans.Student;
import com.OE.Beans.User;
import com.OE.dao.DaoLogin;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;
/**
 * Servlet implementation class Servlet
 */
@WebServlet("/ServletStudent")
public class ServletStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletStudent() {
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
		/*徐子颖部分*/
		if(method1.equals("add")){
			toDoAdd(request,response);
		}else if(method1.equals("home")){
			toDoHome(request,response);
		}else if(method1.equals("dele")){
			toDoDelete(request,response);
		}else if(method1.equals("upda")){
			toGiveParaToModifyForm(request,response);
		}else if(method1.equals("update")){
			toDoUpdate(request,response);
			/*徐子颖部分结束*/
		}else if("showInf".equals(method1)) {/*黄俊杰部分*/
			toDoShowStudent(request,response);
		}/*黄俊杰部分结束*/
		else if(method1.equals("showExamList")){
			toDoShowStudentExamList(request,response);
		}
		
			
	}

	private void toDoShowStudentExamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = session.getAttribute("username").toString();
		DaoStudent daoStudent = DaoFactory.getDaoStudentImpl();
		
		String examList = daoStudent.toShowExamSubject(username);
		
		request.setAttribute("examList", examList);
		request.getRequestDispatcher("page/student/studentExamList.jsp").forward(request, response); 
		
	}

	private void toGiveParaToModifyForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sb = request.getParameter("q");
		String sb1 = request.getParameter("w");
		String sb2 = request.getParameter("e");
		String sb3 = request.getParameter("r");
		String sb4 = request.getParameter("t");
		String sb5 = request.getParameter("y");
		request.setAttribute("ms", sb);
		request.getRequestDispatcher("page/admin/studentUpdate.jsp").forward(request, response); 
		
	}

	private void toDoShowStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username= (String)session.getAttribute("username");//获取用户名session
		DaoStudent ds=DaoFactory.getDaoStudentImpl();
		Student student=ds.search(new Integer(username));//获取考生信息的bean对象
		request.setAttribute("student", student);//传递student对象到jsp页面显示考生信息
		request.getRequestDispatcher("page/student/studentIndex.jsp").forward(request, response);
		
	}

	private void toDoUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = false;
		String x = request.getParameter("p");
		String userName = request.getParameter("username");
		String userSex = request.getParameter("usersex");
		String userCard = request.getParameter("usercard");
		String userPhone = request.getParameter("userphone");
		String userClass = request.getParameter("userclass");
		
		int g = Integer.parseInt(userClass);
		int w = Integer.parseInt(x);
		/*System.out.println(g);
		System.out.println(w);*/
		Student stu = new Student();
		stu.setSid(w);
		stu.setSname(userName);
		stu.setSex(userSex);
		stu.setCardNumber(userCard);
		stu.setPhone(userPhone);
		stu.setClass_id(g);

		DaoStudent dw = DaoFactory.getDaoStudentMessageImpl();
		flag = dw.StuUpd(stu);
		if(flag)
		{
			StringBuffer sb = new StringBuffer();
			sb = dw.StuFin();
			request.setAttribute("mes", sb);
			request.getRequestDispatcher("page/admin/studentList.jsp").forward(request, response); 
		}
		else
			System.out.println("修改失败！");
		
	}

	private void toDoDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = false;
		String x = request.getParameter("q");
		DaoStudent dw = DaoFactory.getDaoStudentMessageImpl();
		flag = dw.StuDel(x);
		if(flag)
		{
			StringBuffer sb = new StringBuffer();
			sb = dw.StuFin();
			request.setAttribute("mes", sb);
			request.getRequestDispatcher("page/admin/studentList.jsp").forward(request, response); 
		}
		else
			System.out.println("删除失败！");
		
	}

	private void toDoHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		DaoStudent dq = DaoFactory.getDaoStudentMessageImpl();
		sb = dq.StuFin();
		request.setAttribute("mes", sb);
		request.getRequestDispatcher("page/admin/studentList.jsp").forward(request, response); 
	
	}

	private void toDoAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag,tip;
		String userName = request.getParameter("username");
		String userSex = request.getParameter("usersex");
		String userCard = request.getParameter("usercard");
		String userPhone = request.getParameter("userphone");
		String userClass = request.getParameter("userclass");
		int Class = Integer.parseInt(userClass);
		DaoStudent dw = DaoFactory.getDaoStudentMessageImpl();
		User user = new User();
		Student stu = new Student();
		stu.setSname(userName);
		stu.setSex(userSex);
		stu.setCardNumber(userCard);
		stu.setPhone(userPhone);
		stu.setClass_id(Class);
		flag = dw.StuAdd(stu);
		if(flag)
			{
			StringBuffer sb = new StringBuffer();
			DaoStudent dq = DaoFactory.getDaoStudentMessageImpl();
			sb = dq.StuFin();
			request.setAttribute("mes", sb);
			request.getRequestDispatcher("page/admin/studentList.jsp").forward(request, response); 
			}
		else
			System.out.println("添加失败！");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
