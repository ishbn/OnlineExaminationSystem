package com.OE.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OE.Beans.Score;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoScore;
import com.OE.dao.DaoStudent;
import com.OE.daoFactory.DaoFactory;

public class DaoScoreImpl implements DaoScore {
	private String sql=null;
	private ResultSet rs = null;
	@Override
	public List<Score> getScoreList(String class_id, String subject_id) {
		sql = "SELECT * from score where exam_id in ("
				+ "SELECT exam_id from exam where subject_id='"+subject_id+"' and exam_id in ("
				+ "SELECT exam_id from exam_arrangement where class_id = '"+class_id+"')) "
				+ "AND sid in (SELECT sid FROM student where class_id='"+class_id+"') ORDER BY sid";
		System.out.println(sql);
		List<Score> scores = new ArrayList<Score>();
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				Score score = new Score();
				score.setScore_id(rs.getInt("score_id"));
				score.setExam_id(rs.getInt("exam_id"));
				score.setGrade(rs.getInt("grade"));
				score.setSid(rs.getInt("sid"));
				scores.add(score);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return scores;
	}
	@Override
	public String toString(List<Score> list) {
		if(list.isEmpty()){
			return "<h5>没有成绩可查看</h5>";
		}
		StringBuffer sb = new StringBuffer();
		DaoStudent daoStudent = DaoFactory.getDaoStudentImpl();
		
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>学号</th><th>姓名</th><th>成绩</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td>");
			sb.append(list.get(i).getSid());
			sb.append("</td><td>");
			sb.append(daoStudent.getStudentNameById(list.get(i).getSid()));
			sb.append("</td><td>");
			sb.append(list.get(i).getGrade());
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
		
	}
	
	/*
	 * 获取考生所有科目的成绩
	 * */

	@Override
	public StringBuffer findscore(Integer sid) {
		String sql="SELECT grade,subject_name  from exam,score,`subject` as sub where sid ='"+sid+"' AND exam.exam_id =score.exam_id AND exam.subject_id = sub.subject_id";
		ResultSet rs=null;
		rs=DbUtil.executeQuery(sql);
		StringBuffer sb=new StringBuffer();
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>科目</th><th>成绩</th></tr></thead><tbody>");
		
		try {
			while(rs.next()) {
				System.out.println(rs.getString("subject_name"));
				System.out.println(rs.getString("grade"));
				sb.append("<tr><td>");
				sb.append(rs.getString("subject_name"));
				sb.append("</td><td>");
				sb.append(rs.getInt("grade"));
				sb.append("</td></td>");
			}
			sb.append("</tbody></table>	</div></div></div>");
			DbUtil.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return sb;
		
	}
}
