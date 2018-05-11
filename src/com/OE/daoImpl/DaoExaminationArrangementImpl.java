package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OE.Beans.ExaminationArrangement;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoClass;
import com.OE.dao.DaoExam;
import com.OE.dao.DaoExaminationArrangement;
import com.OE.dao.DaoSubject;
import com.OE.daoFactory.DaoFactory;

public class DaoExaminationArrangementImpl implements DaoExaminationArrangement {
	private String sql;
	private ResultSet rs;
	@Override
	public List<ExaminationArrangement> getExam_Arrangement_List(String username) {
		List<ExaminationArrangement> list = new ArrayList<>();
		sql = "SELECT * FROM exam_arrangement where exam_id in (SELECT exam_id FROM exam where tid = '"+username+"')";
		System.out.println(sql);
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				ExaminationArrangement examinationArrangement = new ExaminationArrangement();
				examinationArrangement.setArrangement_id(rs.getInt("arrangement_id"));
				examinationArrangement.setExam_id(rs.getInt("exam_id"));
				examinationArrangement.setExam_date(examinationArrangement.toBeDate(rs.getString("exam_date")));
				examinationArrangement.setTimeLength(rs.getInt("timeLength"));
				examinationArrangement.setClass_id(rs.getInt("class_id"));
				examinationArrangement.setPublish(rs.getInt("publish"));
				list.add(examinationArrangement);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public String toString(List<ExaminationArrangement> list) {
		if(list.isEmpty()){
			return "<h5>没有考试安排可维护</h5>";
		}
		StringBuffer sb = new StringBuffer();
		DaoExam daoExam = DaoFactory.getDaoExamImpl();
		DaoSubject daoSubject = DaoFactory.getDaoSubjectImpl();
		DaoClass daoClass = DaoFactory.getDaoClassImpl();
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>考卷编号</th><th>考试名</th><th>所属科目</th><th>考试班级</th><th>考试时间</th><th>时长</th><th>发布</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td>");
			sb.append(""+list.get(i).getArrangement_id()+" ");
			sb.append("</td><td>");
			sb.append(" "+daoExam.getExamName(list.get(i).getExam_id())+" ");
			sb.append("</td><td>");
			sb.append("<a href='sad'>"+daoSubject.getSub_Name_by_exam_id(list.get(i).getExam_id())+"</a>");
			sb.append("</td><td>");
			sb.append(" "+daoClass.getClassName(list.get(i).getClass_id()));
			sb.append("</td><td>");
			sb.append(" "+list.get(i).toString(list.get(i).getExam_date()));
			sb.append("</td><td>");
			sb.append(" "+list.get(i).getTimeLength());
			sb.append("</td><td>");
			if(list.get(i).getPublish() == 1)
				sb.append("已发布");
			else
				sb.append("未发布");
			sb.append("</td><td>");
			sb.append("<a href='ServletExaminationArrangement?option=modify&arrangement_id="+list.get(i).getArrangement_id()+"'>修改</a>");
			sb.append("<button onclick='deleteExam_arrangement("+list.get(i).getArrangement_id()+")'>删除</button>");
			sb.append("<button onclick='releaseExam_arrangement("+list.get(i).getArrangement_id()+")'>发布</button><br/>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
	}
	
	@Override
	public ExaminationArrangement getExam_Arrangement(Integer arrangement_id) {
		sql = "select * from exam_arrangement where arrangement_id ="+arrangement_id;
		ExaminationArrangement  ea = new ExaminationArrangement();
		System.out.println(sql);
		rs = DbUtil.executeQuery(sql);
		try {
			if(rs.next()){
				ea.setArrangement_id(arrangement_id);
				ea.setExam_id(rs.getInt("exam_id"));
				ea.setExam_date(ea.toBeDate(rs.getString("exam_date")));
				ea.setTimeLength(rs.getInt("timeLength"));
				ea.setClass_id(rs.getInt("class_id"));
				ea.setPublish(rs.getInt("publish"));
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ea;
	}
	@Override
	public boolean update(ExaminationArrangement ea) {
		sql = "update exam_arrangement "
				+ "set exam_id=?,"
				+ "class_id=?,"
				+ "exam_date=?,"
				+ "timeLength=? "
				+ "where arrangement_id=?";
		
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		boolean flag = false;
		try {
			ps.setInt(1, ea.getExam_id());
			ps.setInt(2, ea.getClass_id());
			ps.setString(3, ea.toString(ea.getExam_date()));
			ps.setInt(4, ea.getTimeLength());
			ps.setInt(5, ea.getArrangement_id());
			
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
	public boolean releaseExam_arrange(String arrangement_id) {
		sql = "update exam_arrangement set publish=1 where arrangement_id='"+arrangement_id+"'";
		boolean flag = false;
		if(DbUtil.executeUpdate(sql) == 1){
			flag = true;
		}
		DbUtil.close();
		return flag;
		
	}

	@Override
	public boolean deleteExam_arrange(String arrangement_id) {
		sql = "delete from exam_arrangement where arrangement_id ="+arrangement_id;
		int n = DbUtil.executeUpdate(sql);
		DbUtil.close();
		if(n == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean add_arrange(ExaminationArrangement ea) {
		sql = "insert into exam_arrangement(exam_id,class_id,exam_date,timeLength,publish) values(?,?,?,?,?)";
		boolean flag = false;
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		try {
			ps.setInt(1, ea.getExam_id());
			ps.setInt(2, ea.getClass_id());
			ps.setString(3, ea.toString(ea.getExam_date()));
			ps.setInt(4, ea.getTimeLength());
			ps.setInt(5, ea.getPublish());
			if(ps.executeUpdate()==1){
				flag = true;
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
