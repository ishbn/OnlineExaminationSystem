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

import com.OE.Beans.Choice;
import com.OE.Beans.ExaminationArrangement;
import com.OE.Beans.FullingBlank;
import com.OE.Beans.Student;
import com.OE.Beans.Subject;
import com.OE.dao.DaoLogin;
import com.OE.dao.DaoScore;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletStudent1
 */
@WebServlet("/ServletStudent1")
public class ServletStudent1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletStudent1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("option");
		//登陆
		if("login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			DaoLogin dl = DaoFactory.getDaoLoginImpl();
			String type = dl.check(username,password);
			if(type.equals("admin")){
				HttpSession session = request.getSession(false); 
				session.setAttribute("username", username);
				response.sendRedirect("page/admin/adminIndex.jsp");
			}else if(type.equals("student")){
				HttpSession session = request.getSession(false); 
				session.setAttribute("username", username);//记住学生学号
				session.setAttribute("password", password);//记住学生密码
				request.getRequestDispatcher("/ServletStudent1?option=first").forward(request,response);//跳到寻找学生信息的servlet
			}else if(type.equals("teacher")){
				
				HttpSession session = request.getSession(false); 
				session.setAttribute("username", username);
				response.sendRedirect("page/teacher/teacherIndex.jsp");
			}
			else if(type.equals("")){
				response.setContentType("text/html;setchar=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('用户名或密码有错，请再次输入'); window.location='login.jsp'</script>");
				
			}
		}
		//显示首页
		else if("first".equals(action)) {
			int s=0;
			HttpSession session = request.getSession(false); 
			session.setAttribute("s",s);//用于判断考生有没有已确认考试信息
			String username= (String)session.getAttribute("username");//获取用户名session
			DaoStudent ds=DaoFactory.getDaoStudentImpl();
			Student student=ds.search(new Integer(username));//获取考生信息的bean对象
		    String sname=student.getSname();
		    Integer class_id=student.getClass_id();//获取学生所在班级的班级号
		    session.setAttribute("class_id", class_id);//建立班级编号session
			session.setAttribute("student",student);//建立学生session对象用于考生信息确认
			session.setAttribute("sname", sname);//建立学生姓名session对象
			response.sendRedirect("page/student/Whole.html");//进入考试系统的首页
		}
		//查看考生信息
		else if("student".equals(action)) {
			HttpSession session = request.getSession(false);
			String username= (String)session.getAttribute("username");//获取用户名session
			DaoStudent ds=DaoFactory.getDaoStudentImpl();
			Student student=ds.search(new Integer(username));//获取考生信息的bean对象
			//Integer classid=student.getClass_id();//获取考生所在班级的班级ID
			//session.setAttribute("class_id", classid);//建立班级编号session
		//	session.setAttribute("sname",student.getSname());//获得学生姓名，并建立session对象
			
			request.setAttribute("student", student);//传递student对象到jsp页面显示考生信息
			request.getRequestDispatcher("page/student/studentIndex.jsp").forward(request, response);
			//response.sendRedirect("page/student/Whole.html");//进入考试系统的首页
		}
		//考生确认信息
		else if("confirm".equals(action)) {
			HttpSession session = request.getSession(false);
			int s=(int) session.getAttribute("s");
			/*
			 * 判断是否已确认考试信息
			 * */
			if(s==0) {
				response.sendRedirect("page/student/studentconfirm.jsp");
				return;
			}
			/*已确认身份的考生跳过身份认证直接选取科目考试
			 * */
			if(s==1) {
				request.getRequestDispatcher("/ServletStudent1?option=subject").forward(request,response);//跳到选择考试科目的servlet
			}
		}
		//获取成绩
		else if("score".equals(action)) {
			/*获取考生所有科目成绩*/
			HttpSession session=request.getSession();
			String username= (String)session.getAttribute("username");
			DaoScore ds=DaoFactory.getDaoScoreImpl();
			StringBuffer sb=ds.findscore(new Integer(username));
			if(sb.length()>0) {
				System.out.println("查询失败");
			}
			request.setAttribute("sb", sb);
			//session.setAttribute("sb", sb);
			request.getRequestDispatcher("page/student/score.jsp").forward(request,response);
			//response.sendRedirect("page/student/score.jsp");
			
		}
		//更改密码
		else if("changePwd".equals(action)) {
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html;setchar=UTF-8");
			String newpwd = request.getParameter("newpwd");
			String newpwdrepeat=request.getParameter("newpwdrepeat");
			if(newpwd.equals(newpwdrepeat)) {
				HttpSession session = request.getSession(false); 
				String username= (String)session.getAttribute("username");
				DaoStudent ds=DaoFactory.getDaoStudentImpl();
				boolean ok=ds.pwdupdate(new Integer(username), newpwd);
				if(ok) {
					session.setAttribute("password", newpwd);
					PrintWriter out = response.getWriter();
					out.print("<script>alert('密码修改成功'); window.location='page/student/changePassword.jsp'</script>");
				}
				
			}
			else {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('两次输入的密码不一致'); window.location='page/student/changePassword.jsp'</script>");
				out.flush();
				out.close();
			}
			
		}
		//打印选择题试题
		else if("choicetopic".equals(action)) {
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html;setchar=UTF-8");
			HttpSession session = request.getSession(false);
			String subjectid=request.getParameter("course");//获取开始科目的id
			Integer subject_id=Integer.parseInt(subjectid);
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
				
			}
		}
		//在下拉列表显示考试科目
		else if("subject".equals(action)) {
			HttpSession session = request.getSession(false);
			int s=(int) session.getAttribute("s");
			s=1;
			session.setAttribute("s", s);
			Integer class_id=(Integer) session.getAttribute("class_id");
			DaoStudent ds=DaoFactory.getDaoStudentImpl();
			List<Subject> list=ds.getSubjectInfo(class_id);//接收赋予考试科目名的list对象
			//request.setAttribute("list",list);//传递list对象到页面
			session.setAttribute("list", list);
			response.sendRedirect("page/student/exam.jsp");//传递list对象到页面
			//request.getRequestDispatcher("page/student/exam.jsp").forward(request, response);
		}
		//选择题交卷后的一系列操作
		else if("choice".equals(action)) {
			request.setCharacterEncoding("UTF-8");
				HttpSession session=request.getSession(false);
				Integer exam_id=(Integer)session.getAttribute("exam_id");
				String username= (String)session.getAttribute("username");
				Integer grade=0;
				System.out.println(grade);
				DaoStudent ds=DaoFactory.getDaoStudentImpl();
				List <Integer> cidlist=ds.c_id(new Integer(username), exam_id);//获取考生所做卷子的选择题题号设置成一个list
				//List <Integer> fidlist=ds.f_id(new Integer(username), exam_id);
				for(int i=0;i<cidlist.size();i++) {
					
					Integer c_id=cidlist.get(i);
					String can=request.getParameter(c_id+"");//获取对应题号的内容
					//System.out.println(can);
					ds.choiceanswer(c_id, exam_id, new Integer(username),can);//把内容填写到对应题号的表中
				    List<Choice> c=ds.choice(c_id);//获取对应题号的正确答案和分值
				    if(can.equals(c.get(0).getC_answer())) {
				    	//进行判分
				    	grade+=c.get(0).getC_score();
				    }
				    session.setAttribute("grade", grade);//建立成绩的session用于后面填空题判分
					request.getRequestDispatcher("/ServletStudent1?option=fullingtopic").forward(request,response);//跳到寻找学生信息的servlet
			}		
		}
		//填空题交卷后的一系列操作
		else if("fulling".equals(action)) {
			HttpSession session=request.getSession(false);
			Integer exam_id=(Integer)session.getAttribute("exam_id");
			String username= (String)session.getAttribute("username");
			int grade=(int) session.getAttribute("grade");//获取前面选择题得到的成绩
			System.out.println(grade);
			DaoStudent ds=DaoFactory.getDaoStudentImpl();
			List <Integer> fidlist=ds.f_id(new Integer(username), exam_id);//获取考生所做卷子的填空题题题号设置成一个list
			int j=fidlist.size();
			for(int i=0;i<fidlist.size();i++) {
				Integer f_id=fidlist.get(i);
				String fan=request.getParameter(f_id+"");//获取对应题号的内容
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
			out.print("<script>alert('考试结束，请点击查看成绩查看科目总成绩');window.location='page/student/exam.jsp' </script>");
			
		}
		//打印填空题题目
		else if("fullingtopic".equals(action)) {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
