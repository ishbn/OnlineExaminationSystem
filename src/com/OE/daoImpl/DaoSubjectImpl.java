package com.OE.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OE.Beans.Subject;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoSubject;
import com.OE.daoFactory.DaoFactory;

public class DaoSubjectImpl implements DaoSubject {
	String sql;
	ResultSet rs;
	/*查询科目列表*/
	@Override
	public List<Subject> getSub_List(String username) {
		List<Subject> subjects = new ArrayList<>();
		sql = "SELECT * FROM `subject` as sub WHERE sub.subject_id in( SELECT distinct  sub.subject_id FROM  `schedule` as sch WHERE tid='"+username+"' AND sch.subject_id = sub.subject_id )";
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				Subject subject = new Subject();
				subject.setSubject_id(rs.getInt("subject_id"));
				subject.setSubject_name(rs.getString("subject_name"));
				subjects.add(subject);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}
	@Override
	public String getSub_Name_by_exam_id(Integer exam_id) {
		sql = "SELECT subject_name FROM `subject` as sub where subject_id = (SELECT subject_id FROM exam where exam.exam_id = '"+exam_id+"' AND exam.subject_id = sub.subject_id) ";
		rs = DbUtil.executeQuery(sql);
		String name = null;
		try {
			if(rs.next()){
				name = rs.getString("subject_name");
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	@Override
	public String getSelect_subject_drowdownlist(List<Subject> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <div class='input-group col-md-6'>");
		sb.append("<span class='input-group-addon'>科目列表</span>");
		sb.append("<span id='subject' class='input-group-addon'>");
		sb.append("<select id='subject_id'  name='subject_id'>");
		for(int i=0;i<list.size();i++){
			sb.append("<option value ="+list.get(i).getSubject_id()+">"+list.get(i).getSubject_name()+"</option>");
		}
		sb.append("</select></span></div><br/>");	
		return sb.toString();
	}
	@Override
	public String getSub_Name_by_sub_id(Integer subject_id) {
		sql = "SELECT subject_name FROM `subject` as sub where subject_id ="+ subject_id;
		rs = DbUtil.executeQuery(sql);
		String name = null;
		try {
			if(rs.next()){
				name = rs.getString("subject_name");
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	@Override
	public String toString(List<Subject> list) {
		if(list.isEmpty()){
			return "<h5>没有科目的题库可维护</h5>";
		}
		StringBuffer sb = new StringBuffer();
		DaoSubject daoSubject = DaoFactory.getDaoSubjectImpl();
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>科目编号</th><th>科目</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td>");
			sb.append(""+list.get(i).getSubject_id()+" ");
			sb.append("</td><td>");
			sb.append(" "+list.get(i).getSubject_name()+" ");
			sb.append("</td><td>");
			sb.append("<a href='./page/teacher/question_bank.jsp?subject_id="+list.get(i).getSubject_id()+"'>选择题题库管理</a><br/>");
			sb.append("<a href='./page/teacher/question_bank_fulling.jsp?subject_id="+list.get(i).getSubject_id()+"'>填空题题库管理</a><br/>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
	}
	@Override
	public String gradeListtoString(List<Subject> list) {
		if(list.isEmpty()){
			return "<h5>没有科目的成绩可维护</h5>";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>科目编号</th><th>科目</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td>");
			sb.append(""+list.get(i).getSubject_id()+" ");
			sb.append("</td><td>");
			sb.append(" "+list.get(i).getSubject_name()+" ");
			sb.append("</td><td>");
			sb.append("<button onclick='findGrade("+list.get(i).getSubject_id()+")'>查看成绩</button><br/>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
	}
	@Override
	public List<Subject> getSub_List(String username, String class_id) {
		List<Subject> subjects = new ArrayList<>();
		sql = "SELECT * FROM `subject` as sub WHERE sub.subject_id in( SELECT distinct  sub.subject_id FROM  `schedule` as sch WHERE tid='"+username+"' AND class_id ="+class_id+" AND  sch.subject_id = sub.subject_id )";
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				Subject subject = new Subject();
				subject.setSubject_id(rs.getInt("subject_id"));
				subject.setSubject_name(rs.getString("subject_name"));
				subjects.add(subject);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

}
