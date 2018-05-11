package com.OE.Servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.ExaminationArrangement;
import com.OE.Beans.FullingBlank;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;


/**
 * Servlet implementation class ServletExam1
 */
@WebServlet("/ServletExam1")
public class ServletExam1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletExam1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		String username= (String)session.getAttribute("username");
		Integer subject_id=(Integer)session.getAttribute("subject_id");
		Integer class_id=(Integer) session.getAttribute("class_id");
		Integer exam_id=(Integer) session.getAttribute("exam_id");
		DaoStudent ds=DaoFactory.getDaoStudentImpl();
		List<FullingBlank> list2=ds.getFulling(subject_id,class_id);//获取所考科目考卷的填空题题号
		ds.copyFaI(new Integer(username), exam_id, list2);//复制题号到填空题答案表
		request.setAttribute("list2",list2);
		request.getRequestDispatcher("page/student/Topic1.jsp").forward(request, response);//跳到填空题考试页面
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
