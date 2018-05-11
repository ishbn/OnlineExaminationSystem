package com.OE.Servlet;

import com.OE.Beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.ChoiceAnswer;
import com.OE.Beans.FullingBlank;
import com.OE.Beans.FullingBlankAnswer;
import com.OE.dao.DaoExam;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletAnswer
 */
@WebServlet("/ServletAnswer")
public class ServletAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		request.setCharacterEncoding("UTF-8");
		String option=request.getParameter("option");//获取考试类型
		if("choice".equals(option)){
			HttpSession session=request.getSession(false);
			Integer exam_id=(Integer)session.getAttribute("exam_id");
			String username= (String)session.getAttribute("username");
			Integer grade=new Integer(0);
			System.out.println(grade);
			DaoStudent ds=DaoFactory.getDaoStudentImpl();
			
			List <Integer> cidlist=ds.c_id(new Integer(username), exam_id);//获取考生所做卷子的选择题题号设置成一个list
			
			for(int i=0;i<cidlist.size();i++) {
				
				Integer c_id=cidlist.get(i);
				String can=request.getParameter("choice"+c_id);//获取对应题号的内容
				System.out.println(can);
				//System.out.println(can);
				ds.choiceanswer(c_id, exam_id, new Integer(username),can);//把内容填写到对应题号的表中
			    List<Choice> c=ds.choice(c_id);//获取对应题号的正确答案和分值
			    if(can.equals(c.get(0).getC_answer())) {
			    	//进行判分
			    	grade+=c.get(0).getC_score();
			    	}
			    }
			session.setAttribute("grade", grade);//建立成绩的session用于后面填空题判分
			request.getRequestDispatcher("/ServletExam1").forward(request,response);//跳到寻找学生信息的servlet
		}
		else if("fulling".equals(option)) {
			HttpSession session=request.getSession(false);
			Integer exam_id=(Integer)session.getAttribute("exam_id");
			String username= (String)session.getAttribute("username");
			int grade=(int) session.getAttribute("grade");//获取前面选择题得到的成绩
			System.out.println(grade);
			DaoStudent ds=DaoFactory.getDaoStudentImpl();
			List <Integer> fidlist=ds.f_id(new Integer(username), exam_id);//获取考生所做卷子的填空题题题号设置成一个list
			//int j=fidlist.size();
			for(int i=0;i<fidlist.size();i++) {
				Integer f_id=fidlist.get(i);
				String fan=request.getParameter("fulling"+f_id);//获取对应题号的内容
				ds.fullinganswer(f_id, exam_id,new Integer(username),fan);//填写内容到对应题号的表中
				List<FullingBlank> f=ds.fulling(f_id);//获取对应题号填空题的正确答案和对应分值
				if(fan.equals(f.get(0).getF_answer())) {
					grade+=f.get(0).getF_score();
				}//判分
				System.out.println(grade);
			}
			ds.score(new Integer(username), grade,exam_id);//把分数写进成绩表
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html;setchar=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('考试结束，请点击查看成绩查看科目成绩');window.location='ServletScore?option=searchscore' </script>");
			
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
