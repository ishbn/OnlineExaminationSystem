package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.OE.Beans.Examination;
import com.OE.Beans.ExaminationArrangement;
import com.OE.Beans.Subject;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoClass;
import com.OE.dao.DaoExam;
import com.OE.dao.DaoSubject;
import com.OE.daoFactory.DaoFactory;

import javafx.scene.chart.PieChart.Data;

import com.OE.Beans.Class;

public class DaoExamImpl implements DaoExam {
	private String sql;
	private ResultSet rs;
	/*查询试题卷列表*/
	@Override
	public List<Examination> getExamList(String username) {
		List<Examination> list = new ArrayList<>();
		
		sql = "select * from exam where tid ='"+username+"'";
		System.out.println(sql);
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				Examination ea = new Examination();
				ea.setExam_id(rs.getInt("exam_id"));
				ea.setExam_name(rs.getString("exam_name"));
				ea.setSubject_id(rs.getInt("subject_id"));
				ea.setTid(rs.getInt("tid"));
				list.add(ea);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/*查询试卷结果装换带HTML的字符串*/
	@Override
	public String toString(List<Examination> list) {
		if(list.isEmpty()){
			return "<h5>没有试卷可维护</h5>";
		}
		StringBuffer sb = new StringBuffer();
		DaoSubject daoSubject = DaoFactory.getDaoSubjectImpl();
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>试题编号</th><th>考试名</th><th>科目</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td>");
			sb.append(""+list.get(i).getExam_id()+" ");
			sb.append("</td><td>");
			sb.append(" "+list.get(i).getExam_name()+" ");
			sb.append("</td><td>");
			sb.append(daoSubject.getSub_Name_by_sub_id(list.get(i).getSubject_id()));
			sb.append("</td><td>");
			sb.append("<button onclick='deleteExam("+list.get(i).getExam_id()+")'>删除</button>");
			sb.append("<a href='ServletExamination?option=modify&exam_id="+list.get(i).getExam_id()+"'>修改</a>");
			sb.append("<a href='page/teacher/exam_question.jsp?subject_id="+list.get(i).getSubject_id()+"&exam_id="+list.get(i).getExam_id()+"'>编辑试题</a><br/>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
	}
	/*显示删除试卷*/
	@Override
	public boolean deleteExam(String exam_id) {
		sql = "delete from exam where exam_id ="+exam_id;
		int n = DbUtil.executeUpdate(sql);
		DbUtil.close();
		if(n == 1)
			return true;
		else
			return false;
	}
	
	
	/*添加考试安排*/
	@Override
	public boolean add(Examination ea) {
		sql = "insert into exam(exam_name,subject_id,tid) values(?,?,?)";
		boolean flag = false;
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		try {
			ps.setString(1, ea.getExam_name());
			ps.setInt(2, ea.getSubject_id());
			ps.setInt(3,  ea.getTid());
			if(ps.executeUpdate()==1){
				flag = true;
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	@Override
	public Examination getExam(String exam_id) {
		sql = "select * from exam where exam_id ="+exam_id;
		Examination  ea = new Examination();
		System.out.println(sql);
		rs = DbUtil.executeQuery(sql);
		try {
			if(rs.next()){
				ea.setExam_id(new Integer(exam_id));
				ea.setExam_name(rs.getString("exam_name"));
				ea.setSubject_id(rs.getInt("subject_id"));
				ea.setTid(rs.getInt("tid"));
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ea;
	}
	@Override
	public boolean update(Examination ea) {
		sql = "update exam "
				+ "set exam_name=?,"
				+ "subject_id=? "
				+ "where exam_id=?";
		
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		boolean flag = false;
		try {
			ps.setString(1, ea.getExam_name());
			ps.setInt(2, ea.getSubject_id());
			ps.setInt(3, ea.getExam_id());
			
			if(ps.executeUpdate() == 1){
				flag = true;
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public String getExamName(Integer exam_id) {
		sql = "SELECT exam_name FROM exam where  exam_id = "+exam_id;
		String name = null;
		rs = DbUtil.executeQuery(sql);
		try {
			if(rs.next()){
				name = rs.getString("exam_name");
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	@Override
	public String getSelect_exam_drowDownList(List<Examination> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <div class='input-group col-md-6'>");
		sb.append("<span class='input-group-addon'>考卷</span>");
		sb.append("<span  class='input-group-addon'>");
		sb.append("<select id='exam_id'  name='exam_id'>");
		for(int i=0;i<list.size();i++){
			sb.append("<option value ="+list.get(i).getExam_id()+">"+list.get(i).getExam_name()+"</option>");
		}
		sb.append("</select></span></div><br/>");	
		return sb.toString();
	}
	@Override
	public String getExamTime(String exam_id, String username) {
		sql = "select * from exam_arrangement,student where exam_id = "+exam_id+" and student.sid="+username+" and exam_arrangement.class_id = student.class_id";
		System.out.println(sql);
		String time = null;
		ResultSet resultSet = DbUtil.executeQuery(sql);
		try {
			if(resultSet.next()){
				ExaminationArrangement examinationArrangement  = new ExaminationArrangement();
				examinationArrangement .setExam_date(examinationArrangement.toBeDate(resultSet.getString("exam_date")));
				examinationArrangement.setTimeLength(resultSet.getInt("timeLength")); 
				time = tobeDeadline(examinationArrangement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
		
		return time;
	}
	private String tobeDeadline(ExaminationArrangement examinationArrangement) {
		long date = examinationArrangement.getExam_date().getTime();
		long len = examinationArrangement.getTimeLength() * 60 * 1000;
		date += len;
		Date dd = new Date(date);
		
		return examinationArrangement.toString(dd);
	}
	@Override
	public boolean ifStart(String exam_id, String username) {
		boolean flag = false;
		sql = "select * from exam_arrangement,student where publish=1 and sid="+username+" and student.class_id= exam_arrangement.class_id and exam_id="+exam_id+" and exam_date <now() AND round((UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(exam_date))/60)<=timeLength";
		System.out.println(sql);
		rs = DbUtil.executeQuery(sql);
		try {
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
		return flag;
	}

}
