package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OE.Beans.Exam_Fulling;
import com.OE.Beans.FullingBlank;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoFullingBank;
import com.OE.dao.DaoSubject;
import com.OE.daoFactory.DaoFactory;

public class DaoFullingBankImpl implements DaoFullingBank {
	private String sql = null;
	private ResultSet rs = null;
	/*添加填空题*/
	@Override
	public boolean addFullingBank(FullingBlank fullingBlank) {
		sql = "insert into fulling(f_question,f_answer,f_score,subject_id) values(?,?,?,?)";
		int n=-1;
		PreparedStatement preparedStatement = DbUtil.executePreparedStatement(sql);
		try {
			preparedStatement.setString(1, fullingBlank.getF_question());
			preparedStatement.setString(2, fullingBlank.getF_answer());
			preparedStatement.setInt(3, fullingBlank.getF_score());
			preparedStatement.setInt(4, fullingBlank.getSubject_id());
			 n = preparedStatement.executeUpdate();
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(n==1)
			return true;
		else
			return false;
	}
	/*删除填空题*/
	@Override
	public boolean deleteFulling(String f_id) {
		sql = "delete from fulling where f_id = "+f_id;
		int n;
		n = DbUtil.executeUpdate(sql);
		if(n>0){
			return true;
		}else{
			return false;
		}
		
	}
	/*查询某一学科的填空题*/
	@Override
	public List<FullingBlank> getFullingBankList(String subject_id) {
		sql = "select * from fulling where subject_id = "+subject_id;
		List<FullingBlank> fullingBlanks = new ArrayList<>();
		
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				FullingBlank fullingBlank  =new FullingBlank();
				fullingBlank.setF_id(rs.getInt("f_id"));
				fullingBlank.setF_question(rs.getString("f_question"));
				fullingBlank.setF_answer(rs.getString("f_answer"));
				fullingBlank.setF_score(rs.getInt("f_score"));
				fullingBlank.setSubject_id(rs.getInt("subject_id"));
				fullingBlanks.add(fullingBlank);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fullingBlanks;
	}
	/*填空题转换*/
	@Override
	public String toString(List<FullingBlank> list) {
		if(list.isEmpty()){
			return "<h5>没有填空题</h5>";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>题目</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td><strong>");
			sb.append(list.get(i).getF_id());
			sb.append("</strong>&nbsp;&nbsp;&nbsp;&nbsp;");
			sb.append(list.get(i).getF_question());
			sb.append("<br><strong>Answer:</strong>&nbsp;");
			sb.append(list.get(i).getF_answer());
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<strong>分值:</strong>&nbsp;");
			sb.append(list.get(i).getF_score());
			sb.append("</td><td>");
			sb.append("<button onclick='deleteFulling("+list.get(i).getF_id()+")'>删除</button>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>");
		return sb.toString();
	}
	@Override
	public List<FullingBlank> getFullingBankListInExam(Integer exam_id) {
		sql = "select * from fulling where f_id in (select f_id from exam_fulling where exam_id= "+exam_id+")";
		List<FullingBlank> fullingBlanks = new ArrayList<>();
		
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				FullingBlank fullingBlank  =new FullingBlank();
				fullingBlank.setF_id(rs.getInt("f_id"));
				fullingBlank.setF_question(rs.getString("f_question"));
				fullingBlank.setF_answer(rs.getString("f_answer"));
				fullingBlank.setF_score(rs.getInt("f_score"));
				fullingBlank.setSubject_id(rs.getInt("subject_id"));
				fullingBlanks.add(fullingBlank);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fullingBlanks;
	}
	@Override
	public List<FullingBlank> getFullingBankListNotInExam(Integer subject_id, Integer exam_id) {
		sql = "select * from fulling where subject_id="+subject_id+" and f_id not in (select f_id from exam_fulling where exam_id= "+exam_id+")";
		List<FullingBlank> fullingBlanks = new ArrayList<>();
		
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				FullingBlank fullingBlank  =new FullingBlank();
				fullingBlank.setF_id(rs.getInt("f_id"));
				fullingBlank.setF_question(rs.getString("f_question"));
				fullingBlank.setF_answer(rs.getString("f_answer"));
				fullingBlank.setF_score(rs.getInt("f_score"));
				fullingBlank.setSubject_id(rs.getInt("subject_id"));
				fullingBlanks.add(fullingBlank);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fullingBlanks;
	}
	@Override
	public String toAddString(List<FullingBlank> list) {
		if(list.isEmpty()){
			return "<h5>没有填空题</h5>";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>题目</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td><strong>");
			sb.append(list.get(i).getF_id());
			sb.append("</strong>&nbsp;&nbsp;&nbsp;&nbsp;");
			sb.append(list.get(i).getF_question());
			sb.append("<br><strong>Answer:</strong>&nbsp;");
			sb.append(list.get(i).getF_answer());
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<strong>分值:</strong>&nbsp;");
			sb.append(list.get(i).getF_score());
			sb.append("</td><td>");
			sb.append("<button onclick='fullingAddtoExam("+list.get(i).getF_id()+")'>添加</button>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div>");
		return sb.toString();
	}
	@Override
	public boolean fullingAddToExam(Exam_Fulling exam_Fulling) {
		sql = "insert into exam_fulling(exam_id,f_id) values("+exam_Fulling.getExam_id()+","+exam_Fulling.getF_id()+")";
		int n = DbUtil.executeUpdate(sql);
		if(n>=1){
			return true;
		}else{
			return false;
		}
		
		
	}
	@Override
	public boolean fullingDeleteToExam(Exam_Fulling exam_Fulling) {
		sql = "delete from exam_fulling where f_id = "+exam_Fulling.getF_id()+" and exam_id="+exam_Fulling.getExam_id();
		int n = DbUtil.executeUpdate(sql);
		if(n >=1){
			return true;
		}else{
			return false;
		}
		
	}

}
