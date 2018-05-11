package com.OE.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OE.Beans.Score;
import com.OE.dao.DaoScore;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

/**
 * Servlet implementation class ServletScore
 */
@WebServlet("/ServletScore")
public class ServletScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoScore daoScore = DaoFactory.getDaoScoreImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletScore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		if(option.equals("showClassGrade")){
			toDoShowClassGrade(request,response);
		}else if("searchscore".equals(option)) {
			toShowStudentScore(request,response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void toShowStudentScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*获取考生所有科目成绩*/
		HttpSession session=request.getSession();
		String username= (String)session.getAttribute("username");
		DaoScore ds=DaoFactory.getDaoScoreImpl();
		StringBuffer sb=ds.findscore(new Integer(username));
		if(sb.length()>0) {
			System.out.println("hahahh");
		}
		request.setAttribute("sb", sb);
		//session.setAttribute("sb", sb);
		request.getRequestDispatcher("page/student/score.jsp").forward(request,response);
		
	}

	private void toDoShowClassGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ShowClassGrade");
		String class_id = request.getParameter("class_id");
		String subject_id = request.getParameter("subject_id");
		List<Score> scores = daoScore.getScoreList(class_id,subject_id);
		String gradeList = daoScore.toString(scores);
		request.setAttribute("gradeList", gradeList);
		request.getRequestDispatcher("./page/teacher/grade_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
