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

import com.OE.Beans.Subject;
import com.OE.dao.DaoStudent;
import com.OE.dao.DaoSubject;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletSubject
 */
@WebServlet("/ServletSubject")
public class ServletSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private DaoSubject daoSubject = DaoFactory.getDaoSubjectImpl();
      private String result = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String  option = request.getParameter("option");
		if(option.equals("find")){
			toDoFind(request,response);
		}else if(option.equals("findSubjectListByteacher")){
			toDoFindSubjectListByTeacher(request,response);
		}else if("subjectlist".equals(option)) {/*黄俊杰部分*/
			//确认信息后显示考试科目下拉列表
			toDoShowSujectList(request,response);
		}
	}

	private void toDoShowSujectList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		int s=(int) session.getAttribute("s");
		s=1;
		session.setAttribute("s", s);
		
		String username = session.getAttribute("username").toString();
		DaoStudent daoStudent = DaoFactory.getDaoStudentImpl();
		
		String examList = daoStudent.toShowExamSubject(username);
		
		request.setAttribute("examList", examList);
		request.getRequestDispatcher("page/student/studentExamList.jsp").forward(request, response); 
		
		
		/*
		Integer class_id=(Integer) session.getAttribute("class_id");
		DaoStudent ds=DaoFactory.getDaoStudentImpl();
		List<Subject> list=ds.getSubjectInfo(class_id);//接收赋予考试科目名的list对象
		//request.setAttribute("list",list);//传递list对象到页面
		session.setAttribute("list", list);
		response.sendRedirect("page/student/exam.jsp");//传递list对象到页面
		*/
		
	}

	

	private void toDoFindSubjectListByTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username  = request.getSession().getAttribute("username").toString();
		String class_id  = request.getParameter("class_id");
		List<Subject> subjects = daoSubject.getSub_List(username,class_id);
		String sub_str = daoSubject.gradeListtoString(subjects);
		request.setAttribute("sub_str", sub_str);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/page/teacher/subject_list.jsp?class_id="+class_id);
		rDispatcher.forward(request, response);
		
	}

	private void toDoFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username  = request.getSession().getAttribute("username").toString();
		List<Subject> sub_list = daoSubject.getSub_List(username);
		
		String sub_str = daoSubject.toString(sub_list);
		
		request.setAttribute("sub_str", sub_str);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/page/teacher/subject_list.jsp");
		rDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
