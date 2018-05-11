package com.OE.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OE.Beans.Choice;
import com.OE.Beans.Exam_choice;
import com.OE.Beans.FullingBlank;
import com.OE.dao.DaoFullingBank;
import com.OE.dao.DaoQuestionBank;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletQuestionBank
 */
@WebServlet("/ServletQuestionBank")
public class ServletQuestionBank extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoQuestionBank dqb = DaoFactory.getDaoQuestionBankImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletQuestionBank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		toDoDispatch(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void toDoDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("option");
		if(option.equals("questions_add")){
			/*添加题目到题库*/
			toDoQuestion_add(request,response);
		}else if(option.equals("find")){
			/*查询相关科目题库题目*/
			toDoQuestionFind(request,response);
		}else if(option.equals("delete_from_bank")){
			/*删除相关科目题库题目*/
			toDoQuestionDelete(request,response);
		}else if(option.equals("find_question_not_in_exam")){
			/*查询相关科目没有选中到相关试卷的题目*/
			toDoQuestionFindNotInExam(request,response);
		}else if(option.equals("find_exam_question")){
			/*显示相关试卷的题目*/
			toDoExamShow(request,response);
		}else if(option.equals("add_to_exam")){
			/*添加相关题目到相关试卷*/
			toDoQuestionAddToExam(request,response);
		}else if(option.equals("delete_exam_question")){
			/*删除相关试卷的题目*/
			toDoDeleteExamQuestion(request,response);
		}
	}

	private void toDoQuestionDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String c_id = request.getParameter("c_id");
			boolean flag = dqb.delteQuestionFromBank(c_id);
			if(flag){
				toDoQuestionFind(request, response);
			}
	}

	private void toDoDeleteExamQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exam_id = request.getParameter("exam_id");
		String c_id = request.getParameter("c_id");
		
		boolean flag = dqb.deleteQuestion(exam_id,c_id);
		if(flag){
			toDoExamShow(request, response);
		}
		
	}

	private void toDoQuestionAddToExam(HttpServletRequest request, HttpServletResponse response) {
		
		String c_id = request.getParameter("c_id");
		String exam_id = request.getParameter("exam_id");
		System.out.println("toDoQuestionAddToExam c_id="+c_id+"exam_id="+exam_id);
		Exam_choice ec = new Exam_choice();
		ec.setC_id(new Integer(c_id));
		ec.setExam_id(new Integer(exam_id));
		
		boolean flag = dqb.add_question_to_exam(ec);
		if(flag){
			System.out.println(flag);
		}
		
	}

	private void toDoExamShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exam_id = request.getParameter("exam_id");
		List<Choice> choices = dqb.getChoiceListInExam(new Integer(exam_id));
		String question = dqb.examShowtoString(choices);
		request.setAttribute("question", question);
		
		DaoFullingBank dfb = DaoFactory.getDaoFullingBankImpl();
		List<FullingBlank> fullingBlanks = dfb.getFullingBankListInExam(new Integer(exam_id));
		String fullingBank = dfb.toString(fullingBlanks);
		request.setAttribute("fullingBank", fullingBank);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/teacher/exam_question_show.jsp?exam_id="+exam_id);
		requestDispatcher.forward(request, response);
		
		
	}

	private void toDoQuestionFindNotInExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subject_id = request.getParameter("subject_id");
		String exam_id = request.getParameter("exam_id");
		System.out.println("toDoQuestionFindNotInExam subject_id="+subject_id+"exam_id="+exam_id);
		List<Choice> choices = dqb.getChoiceListNotInExam(new Integer(subject_id),new Integer(exam_id));
		String question = dqb.toAddString(choices);
		request.setAttribute("question", question);
	    DaoFullingBank dfb =DaoFactory.getDaoFullingBankImpl();
	    List<FullingBlank> fullingBlanks = dfb.getFullingBankListNotInExam(new Integer(subject_id),new Integer(exam_id));
	    String fString = dfb.toAddString(fullingBlanks);
	    request.setAttribute("fString", fString);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./page/teacher/exam_question_bank_show.jsp?subject_id="+subject_id+"&exam_id="+exam_id);
		requestDispatcher.forward(request, response);
		
	}

	private void toDoQuestion_add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subject_id,question,c_choiceA,c_choiceB,c_choiceC,c_choiceD,c_answer,c_score;
		
		subject_id = request.getParameter("subject_id");
		question = request.getParameter("c_question");
		c_choiceA = request.getParameter("c_choiceA");
		c_choiceB = request.getParameter("c_choiceB");
		c_choiceC = request.getParameter("c_choiceC");
		c_choiceD = request.getParameter("c_choiceD");
		c_answer = request.getParameter("c_answer");
		c_score = request.getParameter("c_score");
		
		Choice choice = new Choice();
		choice.setQuestion(question);
		choice.setC_choiceA(c_choiceA);
		choice.setC_choiceB(c_choiceB);
		choice.setC_choiceC(c_choiceC);
		choice.setC_choiceD(c_choiceD);
		choice.setC_answer(c_answer);
		choice.setC_score(new Integer(c_score));
		choice.setSubject_id(new Integer(subject_id));
		
		boolean flag = dqb.add_question_to_bank(choice);
		
		if(flag){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./page/teacher/question_bank_add.jsp");
			requestDispatcher.forward(request, response);
			
		}
		
	}

	private void toDoQuestionFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject_id = request.getParameter("subject_id");
		List<Choice> choices = dqb.getChoiceListBySubject(new Integer(subject_id));
		String question = dqb.toString(choices);
		request.setAttribute("question", question);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./page/teacher/question_bank_show.jsp?subject_id="+subject_id);
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
