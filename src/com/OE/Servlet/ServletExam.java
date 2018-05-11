package com.OE.Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.*;
import com.OE.dao.DaoExam;
import com.OE.dao.DaoQuestionBank;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletExam
 */
@WebServlet("/ServletExam")
public class ServletExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=utf-8");  
	response.setHeader("Cache-control", "no-cache"); 
	
	String exam_id = request.getParameter("course");
	HttpSession session = request.getSession(false);
	String username= (String)session.getAttribute("username");
	
	
	DaoStudent ds=DaoFactory.getDaoStudentImpl();
	DaoQuestionBank daoQuestionBank = DaoFactory.getDaoQuestionBankImpl();
	
	List<Choice> list1= daoQuestionBank.getChoiceListInExam(new Integer(exam_id));
	request.setAttribute("list1",list1);
	String subject_name = ds.subjectname(list1.get(0).getSubject_id());
	ds.copyCaI(new Integer(username),new Integer(exam_id),list1);
	
	DaoExam daoExam = DaoFactory.getDaoExamImpl();
	
	String time = daoExam.getExamTime(exam_id,username);
	//time = "2018-04-28 23:10:27";
	System.out.println(time);
	session.setAttribute("examTime", time);
	session.setAttribute("subject_id", list1.get(0).getSubject_id());
	session.setAttribute("exam_id",new Integer(exam_id));
	
	request.setAttribute("subject_name", subject_name);
    request.getRequestDispatcher("page/student/Topic.jsp").forward(request, response);//跳到选择题考试页面

	/*HttpSession session = request.getSession(false);
	String subjectid=request.getParameter("course");//获取开始科目的id
	Integer subject_id=new Integer(subjectid);
	session.setAttribute("subject_id", subject_id);//建立考试科目id的session
	Integer class_id=(Integer) session.getAttribute("class_id");//获取班级编号
	DaoStudent ds=DaoFactory.getDaoStudentImpl();
	ExaminationArrangement ea=ds.exam(subject_id, class_id);//根据科目id和班级id获取对应的考卷
	session.setAttribute("exam_id",ea.getExam_id());
	if(ea.getPublish()==0) {
		PrintWriter out = response.getWriter();
		out.print("<script>alert('该科目还没发布考卷，请选择其他考试科目进行考试'); window.location='page/student/exam.jsp'</script>");
	}
	else {
		String username= (String)session.getAttribute("username");
		boolean ok=ds.check(ea.getExam_id(),new Integer(username));//判断是否已考过该门科目
		if(ok) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('你已考过这门科目,请选择其他科目考试'); window.location='page/student/exam.jsp'</script>");
		}
		else {
			List<Choice> list1=ds.getChoice(subject_id,class_id);//获取选择题题号
			//List<FullingBlank> list2=ds.getFulling(subject_id,class_id);
			String subject_name=ds.subjectname(subject_id);
			//System.out.println(subject_name); 
			ds.copyCaI(new Integer(username),ea.getExam_id(),list1);//把考卷选择题题号和sid copy到答案表
			//ds.copyFaI(new Integer(username), ea.getExam_id(), list2);
			request.setAttribute("list1",list1);
			//request.setAttribute("list2",list2);
			request.setAttribute("subject_name", subject_name);
		    request.getRequestDispatcher("page/student/Topic.jsp").forward(request, response);//跳到选择题考试页面
		   
		}
		
	} */
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
