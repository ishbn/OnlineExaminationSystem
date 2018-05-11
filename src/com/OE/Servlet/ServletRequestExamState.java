package com.OE.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.dao.DaoExam;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletRequestExamState
 */
@WebServlet("/ServletRequestExamState")
public class ServletRequestExamState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRequestExamState() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String  option = request.getParameter("option");
		
		if(option.equals("ask")){
			toDoAsked(request,response);
		}
	}

	private void toDoAsked(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String exam_id = request.getParameter("exam_id");
		HttpSession session = request.getSession(false);
		String username= (String)session.getAttribute("username");
		System.out.println("status");
		DaoExam daoExam = DaoFactory.getDaoExamImpl();
		
		boolean flag = daoExam.ifStart(exam_id,username);
		String result = null;
		if(flag){
			result = "yes";
			toDoOutPut(response, result);
		}else{
			result = "no";
			toDoOutPut(response, result);
		}
	}
	
	private void toDoOutPut(HttpServletResponse response, String result) throws IOException {
		
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
