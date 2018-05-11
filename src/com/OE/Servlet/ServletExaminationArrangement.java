package com.OE.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OE.Beans.Examination;
import com.OE.Beans.ExaminationArrangement;
import com.OE.dao.DaoClass;
import com.OE.dao.DaoExam;
import com.OE.Beans.Class;
import com.OE.dao.DaoExaminationArrangement;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletExaminationArrangement
 */
@WebServlet("/ServletExaminationArrangement")
public class ServletExaminationArrangement extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoExaminationArrangement daoExaminationArrangement = DaoFactory.getDaoExaminationArrangementImpl();
    private String result = null;
     /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletExaminationArrangement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		toDoDispatch(request,response);
	}
	
	private void toDoDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("option");
		if(option.equals("find")){
			toDoFind(request,response);
		}else if(option.equals("modify")){
			toDoModify(request,response);
		}else if(option.equals("update")){
			toDoUpdate(request,response);
		}else if(option.equals("release")){
			toDoRelease(request,response);
		}else if(option.equals("examDelete")){
			toDoDelete(request,response);
		}else if(option.equals("add")){
			toDoAdd(request,response);
		}else if(option.equals("showAddForm")){
			toDoShowAddForm(request,response);
		}
		
	}

	private void toDoShowAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username  = request.getSession().getAttribute("username").toString();
		System.out.println("add_arrange_Form");
		
		DaoExam daoExam = DaoFactory.getDaoExamImpl();
		List<Examination> list =daoExam.getExamList(username);
		String select_exam = daoExam.getSelect_exam_drowDownList(list);
		
		DaoClass daoClass = DaoFactory.getDaoClassImpl();
		List<Class> class_list = daoClass.getClassList(username);
		String select_class = daoClass.getSelect_class_drowdownlist(class_list);
		
		request.setAttribute("select_exam", select_exam);
		request.setAttribute("select_class", select_class);
		RequestDispatcher rd = request.getRequestDispatcher("/page/teacher/exam_arrange_add.jsp");
		rd.forward(request, response);
		
	}

	private void toDoAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String exam_id = request.getParameter("exam_id");
		String class_id = request.getParameter("class_id");
		String timeLength = request.getParameter("timeLength");
		String date = request.getParameter("exam_date");
		
		ExaminationArrangement examinationArrangement = new ExaminationArrangement();
		examinationArrangement.setExam_id(new Integer(exam_id));
		examinationArrangement.setClass_id(new Integer(class_id));
		examinationArrangement.setPublish(0);
		examinationArrangement.setExam_date(examinationArrangement.toBeDate(date));
		examinationArrangement.setTimeLength(new Integer(timeLength));
		
		boolean flags1 = daoExaminationArrangement.add_arrange(examinationArrangement);
		
		if(flags1){
			toDoFind(request, response);
		}else{
			result = "添加失败";
			toDoOutPut(response);
		}
		
		
	}

	private void toDoDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String arrangement_id = request.getParameter("arrangement_id");
		System.out.println("exam_id="+arrangement_id);
		boolean hadDelete = daoExaminationArrangement.deleteExam_arrange(arrangement_id);
		
		if(hadDelete){
			result = "删除成功";
		}else {
			result = "删除失败";
		}
		toDoOutPut(response);
		
	}

	private void toDoRelease(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String arrangement_id = request.getParameter("arrangement_id");
		System.out.println("release");
		boolean flag = daoExaminationArrangement.releaseExam_arrange(arrangement_id);
		if(flag){
			result = "发布成功";
		}else{
			result = "发布失败";
		}
		toDoOutPut(response);
	}

	private void toDoUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String arrangement_id = request.getParameter("arrangement_id");
		String exam_id = request.getParameter("exam_id");
		String class_id = request.getParameter("class_id");
		String timeLength = request.getParameter("timeLength");
		String date = request.getParameter("exam_date");
		
		
		ExaminationArrangement ea = new ExaminationArrangement();
		ea.setArrangement_id(new Integer(arrangement_id));
		ea.setExam_id(new Integer(exam_id));
		ea.setClass_id(new Integer(class_id));
		ea.setExam_date(ea.toBeDate(date));
		ea.setTimeLength(new Integer(timeLength));
		ea.setPublish(0);
		
		System.out.println("update");
		boolean flag = daoExaminationArrangement.update(ea);
		if(flag){
			result = "更新成功";
		}else{
			result = "更新失败";
		}
		toDoOutPut(response);
		
	}

	private void toDoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("modify");
		String username  = request.getSession().getAttribute("username").toString();
		String arrangement_id = request.getParameter("arrangement_id");
		System.out.println(arrangement_id);
		ExaminationArrangement examinationArrangement = daoExaminationArrangement.getExam_Arrangement(new Integer(arrangement_id));
		
		DaoExam daoExam = DaoFactory.getDaoExamImpl();
		List<Examination> list =daoExam.getExamList(username);
		String select_exam = daoExam.getSelect_exam_drowDownList(list);
		
		DaoClass daoClass = DaoFactory.getDaoClassImpl();
		List<Class> class_list = daoClass.getClassList(username);
		String select_class = daoClass.getSelect_class_drowdownlist(class_list);
		
		request.setAttribute("select_exam", select_exam);
		request.setAttribute("select_class", select_class);
		request.setAttribute("examinationArrangement", examinationArrangement);
		RequestDispatcher rd = request.getRequestDispatcher("/page/teacher/exam_arrange_modify.jsp");
		rd.forward(request, response);
		
	}

	private void toDoFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username  = request.getSession().getAttribute("username").toString();
		
		List<ExaminationArrangement> examinationArrangements = daoExaminationArrangement.getExam_Arrangement_List(username);
		String exam_arrange_list = daoExaminationArrangement.toString(examinationArrangements);
		request.setAttribute("exam_arrange_list", exam_arrange_list);
		RequestDispatcher rd = request.getRequestDispatcher("/page/teacher/exam_arrange_list.jsp");
		rd.forward(request, response);
		
	}
	private void toDoOutPut(HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=utf-8");  
		response.setHeader("Cache-control", "no-cache");  
		 
		ServletOutputStream outputStream = response.getOutputStream();  
	    outputStream.write(result.getBytes("utf-8"));  
	    outputStream.flush();  
	    outputStream.close();  
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
