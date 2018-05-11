package com.OE.Servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.OE.Beans.Examination;
import com.OE.Beans.ExaminationArrangement;
import com.OE.Beans.Subject;
import com.OE.dao.DaoExam;
import com.OE.dao.DaoSubject;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletExamination
 */
@WebServlet("/ServletExamination")
public class ServletExamination extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoExam daoExam = DaoFactory.getDaoExamImpl();
    private String result="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletExamination() {
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
		if(option.equals("find")){
			/*查找相关考卷*/
			toDoFind(request,response);
		}else if(option.equals("add")){
			/*添加相关的考卷*/
			toDoAdd(request,response);
		}else if(option.equals("update")){
			/*更新相关的考卷*/
			toDoUpdate(request,response);
		}else if(option.equals("examDelete")){
			/*删除相关的考卷*/
			toDoDelete(request,response);
		}else if(option.equals("modify")){
			/*查询相关考卷，并修改它*/
			toDoModify(request,response);
		}else if(option.equals("showAddForm")){
			/*显示添加考卷表单*/
			toDoShowAddForm(request,response);
		}
		
		
	}

	private void toDoAdd(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		String username  = request.getSession().getAttribute("username").toString();
		String exam_name = request.getParameter("exam_name");
		String subject_id = request.getParameter("subject_id");
		Examination ea = new Examination();
		ea.setExam_name(exam_name);
		ea.setSubject_id(new Integer(subject_id));
		ea.setTid(new Integer(username));
		boolean flag = daoExam.add(ea);
		if(flag){
			toDoFind(request, response);
		}
	}
	
	

	private void toDoShowAddForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username  = request.getSession().getAttribute("username").toString();
		DaoSubject ds = DaoFactory.getDaoSubjectImpl();
		List<Subject> list = ds.getSub_List(username);
		String sub_str = ds.getSelect_subject_drowdownlist(list);
		request.setAttribute("subject_list", sub_str);
		RequestDispatcher rd = request.getRequestDispatcher("/page/teacher/exam_add.jsp");
		rd.forward(request, response);
	}

	private void toDoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username  = request.getSession().getAttribute("username").toString();
		String exam_id = request.getParameter("exam_id");
		
		Examination ea = daoExam.getExam(exam_id);
		
		DaoSubject daoSubject = DaoFactory.getDaoSubjectImpl();
		List<Subject> list = daoSubject.getSub_List(username);
		String select_subject = daoSubject.getSelect_subject_drowdownlist(list);
		
		request.setAttribute("ea", ea);
		request.setAttribute("select_subject", select_subject);
		RequestDispatcher rd = request.getRequestDispatcher("./page/teacher/exam_modify.jsp");
		rd.forward(request, response);
		
	}

	private void toDoDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String exam_id = request.getParameter("exam_id");
		System.out.println("exam_id="+exam_id);
		boolean hadDelete = daoExam.deleteExam(exam_id);
		
		if(hadDelete){
			result = "删除成功";
		}else {
			result = "删除失败";
		}
		toDoOutPut(response);
		
	}

	private void toDoUpdate(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String exam_id = request.getParameter("exam_id");
		String exam_name = request.getParameter("exam_name");
		String subject_id = request.getParameter("subject_id");
		
		
		Examination ea = new Examination();
		ea.setExam_id(new Integer(exam_id));
		ea.setExam_name(exam_name);
		ea.setSubject_id(new Integer(subject_id));
		
		System.out.println("update");
		boolean flag = daoExam.update(ea);
		if(flag){
			toDoFind(request, response);
		}
		
	}

	
	private void toDoFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username  = request.getSession().getAttribute("username").toString();
		List<Examination> ea = daoExam.getExamList(username);
		String examStr = daoExam.toString(ea);
		request.setAttribute("exam", examStr);
		RequestDispatcher rd = request.getRequestDispatcher("/page/teacher/exam_list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void toDoOutPut(HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=utf-8");  
		response.setHeader("Cache-control", "no-cache");  
		 
		ServletOutputStream outputStream = response.getOutputStream();  
	    outputStream.write(result.getBytes("utf-8"));  
	    outputStream.flush();  
	    outputStream.close();  
		
	}
	

}
