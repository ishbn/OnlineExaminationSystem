package com.OE.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OE.Beans.Exam_Fulling;
import com.OE.Beans.FullingBlank;
import com.OE.dao.DaoFullingBank;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServeltFullingBank
 */
@WebServlet("/ServletFullingBank")
public class ServletFullingBank extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoFullingBank  daoFullingBank = DaoFactory.getDaoFullingBankImpl();
    private String result = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFullingBank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("option");
		/*添加填空题*/
		if(option.equals("question_fulling_add")){
			toDoAdd(request,response);
		}else if(option.equals("deleteFulling")){
			toDoDeleteFulling(request,response);
		}else if(option.equals("find_fulling")){
			toDoFindFulling(request,response);
		}else if(option.equals("add_to_exam")){
			toDoAddFullingToExam(request,response);
		}else if(option.equals("delete_exam_question")){
			toDoDeleteExamFulling(request,response);
		}
	}

	private void toDoDeleteExamFulling(HttpServletRequest request, HttpServletResponse response) {
		String f_id = request.getParameter("f_id");
		String exam_id = request.getParameter("exam_id");
		Exam_Fulling exam_Fulling = new Exam_Fulling();
		exam_Fulling.setExam_id(new Integer(exam_id));
		exam_Fulling.setF_id(new Integer(f_id));
		boolean flag =daoFullingBank.fullingDeleteToExam(exam_Fulling);
		if(flag){
			System.out.println(flag);
		}
		
	}

	private void toDoAddFullingToExam(HttpServletRequest request, HttpServletResponse response) {
		String f_id = request.getParameter("f_id");
		String exam_id = request.getParameter("exam_id");
		Exam_Fulling exam_Fulling = new Exam_Fulling();
		exam_Fulling.setExam_id(new Integer(exam_id));
		exam_Fulling.setF_id(new Integer(f_id));
		
		boolean flag =daoFullingBank.fullingAddToExam(exam_Fulling);
		if(flag){
			System.out.println(flag);
		}
		
	}

	private void toDoFindFulling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject_id = request.getParameter("subject_id");
		
		List<FullingBlank> fullingBlanks = daoFullingBank.getFullingBankList(subject_id);
		
		String fString = daoFullingBank.toString(fullingBlanks);
		
		request.setAttribute("fString", fString);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./page/teacher/question_bank_show_fulling.jsp?subject_id="+subject_id);
		requestDispatcher.forward(request, response);
	}

	private void toDoDeleteFulling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String f_id = request.getParameter("f_id");
		boolean flag = daoFullingBank.deleteFulling(f_id);
		if(flag){
			toDoFindFulling(request, response);
		}
		
	}

	private void toDoAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String f_question = request.getParameter("f_question");
		String f_answer = request.getParameter("f_answer");
		String f_score = request.getParameter("f_score");
		String subject_id = request.getParameter("subject_id");
		
		FullingBlank fullingBlank = new FullingBlank();
		fullingBlank.setF_question(f_question);
		fullingBlank.setF_answer(f_answer);
		fullingBlank.setF_score(new Integer(f_score));
		fullingBlank.setSubject_id(new Integer(subject_id));
		
		boolean flag = daoFullingBank.addFullingBank(fullingBlank);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./page/teacher/question_bank_add_fulling.jsp");
		requestDispatcher.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
