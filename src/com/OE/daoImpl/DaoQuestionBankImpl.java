package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OE.Beans.Choice;
import com.OE.Beans.Exam_choice;
import com.OE.Beans.FullingBlank;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoQuestionBank;

public class DaoQuestionBankImpl implements DaoQuestionBank {
	private String sql;
	private ResultSet rs;
	/*查询所属考卷的填空题*/
	@Override
	public List<FullingBlank> getFullingBlankList(String exam_id) {
		sql = "select * from fulling where exam_id ="+exam_id;
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				FullingBlank fullingBlank =new FullingBlank();
				fullingBlank.setF_id(rs.getInt("f_id"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*添加填空题到题库*/
	@Override
	public boolean add_question_to_bank(Choice choice) {
		sql ="insert into choice(c_question,c_choiceA,c_choiceB,c_choiceC,c_choiceD,c_answer,c_score,subject_id) values(?,?,?,?,?,?,?,?)";
		boolean flag = false;
		PreparedStatement preparedStatement = DbUtil.executePreparedStatement(sql);
		try {
			preparedStatement.setString(1, choice.getQuestion());
			preparedStatement.setString(2, choice.getC_choiceA());
			preparedStatement.setString(3, choice.getC_choiceB());
			preparedStatement.setString(4, choice.getC_choiceC());
			preparedStatement.setString(5, choice.getC_choiceD());
			preparedStatement.setString(6, choice.getC_answer());
			preparedStatement.setString(7, choice.getC_score().toString());
			preparedStatement.setString(8, choice.getSubject_id().toString());
			if(preparedStatement.executeUpdate()==1){
				flag = true;
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	/*获取某一科题库的所有选择题目*/
	@Override
	public List<Choice> getChoiceListBySubject(Integer subject_id) {
		sql="select * from choice where subject_id = '"+subject_id+"' order by c_id desc";
		List<Choice> list = new ArrayList<>();
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				Choice choice = new Choice();
				choice.setC_id(rs.getInt("c_id"));
				choice.setQuestion(rs.getString("c_question"));
				choice.setC_choiceA(rs.getString("c_choiceA"));
				choice.setC_choiceB(rs.getString("c_choiceB"));
				choice.setC_choiceC(rs.getString("c_choiceC"));
				choice.setC_choiceD(rs.getString("c_choiceD"));
				choice.setC_answer(rs.getString("c_answer"));
				choice.setC_score(new Integer(rs.getString("c_score")));
				choice.setSubject_id(subject_id);
				list.add(choice);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String toString(List<Choice> list) {
		if(list.isEmpty()){
			return "<h5>题库为空</h5>";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>题目</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td><strong>");
			sb.append(list.get(i).getC_id());
			sb.append("</strong>&nbsp;&nbsp;&nbsp;&nbsp;");
			sb.append(list.get(i).getQuestion());
			sb.append("<br><strong>A:</strong>");
			sb.append(list.get(i).getC_choiceA());
			sb.append("<br><strong>B:</strong>");
			sb.append(list.get(i).getC_choiceB());
			sb.append("<br><strong>C:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceC());
			sb.append("<br><strong>D:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceD());
			sb.append("<br><strong>Answer:</strong>&nbsp;");
			sb.append(list.get(i).getC_answer());
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<strong>分值:</strong>&nbsp;");
			sb.append(list.get(i).getC_score());
			
			sb.append("</td><td>");
			sb.append("<button onclick='deleteQuestion("+list.get(i).getC_id()+")'>删除</button>");
			
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
	}

	@Override
	public List<Choice> getChoiceListNotInExam(Integer subject_id, Integer exam_id) {
		sql="select * from choice where subject_id='"+subject_id+"' AND c_id not in (select c_id from exam_choice where exam_id='"+exam_id+"')";
		List<Choice> list = new ArrayList<>();
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				Choice choice = new Choice();
				choice.setC_id(rs.getInt("c_id"));
				choice.setQuestion(rs.getString("c_question"));
				choice.setC_choiceA(rs.getString("c_choiceA"));
				choice.setC_choiceB(rs.getString("c_choiceB"));
				choice.setC_choiceC(rs.getString("c_choiceC"));
				choice.setC_choiceD(rs.getString("c_choiceD"));
				choice.setC_answer(rs.getString("c_answer"));
				choice.setC_score(new Integer(rs.getString("c_score")));
				choice.setSubject_id(subject_id);
				list.add(choice);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String toAddString(List<Choice> list) {
		if(list.isEmpty()){
			return "<h5>题库为空</h5>";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>题目</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td><strong>");
			sb.append(list.get(i).getC_id());
			sb.append("</strong>&nbsp;&nbsp;&nbsp;&nbsp;");
			sb.append(list.get(i).getQuestion());
			sb.append("<br><strong>A:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceA());
			sb.append("<br><strong>B:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceB());
			sb.append("<br><strong>C:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceC());
			sb.append("<br><strong>D:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceD());
			sb.append("<br><strong>Answer:</strong>&nbsp;");
			sb.append(list.get(i).getC_answer());
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<strong>分值:</strong>&nbsp;");
			sb.append(list.get(i).getC_score());
			
			sb.append("</td><td>");
			sb.append("<button onclick='addtoExam("+list.get(i).getC_id()+")'>添加</button>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
	}

	@Override
	public List<Choice> getChoiceListInExam(Integer exam_id) {
		sql="select * from choice where c_id in (select c_id from exam_choice where exam_id='"+exam_id+"')";
		List<Choice> list = new ArrayList<>();
		rs = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				Choice choice = new Choice();
				choice.setC_id(rs.getInt("c_id"));
				choice.setQuestion(rs.getString("c_question"));
				choice.setC_choiceA(rs.getString("c_choiceA"));
				choice.setC_choiceB(rs.getString("c_choiceB"));
				choice.setC_choiceC(rs.getString("c_choiceC"));
				choice.setC_choiceD(rs.getString("c_choiceD"));
				choice.setC_answer(rs.getString("c_answer"));
				choice.setC_score(new Integer(rs.getString("c_score")));
				choice.setSubject_id(rs.getInt("subject_id"));
				list.add(choice);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean add_question_to_exam(Exam_choice ec) {
		sql=" insert into exam_choice(c_id,exam_id) values('"+ec.getC_id()+"','"+ec.getExam_id()+"')";
		int n = DbUtil.executeUpdate(sql);
		if(n==1){
			return true;
		}else
			return false;
	}

	@Override
	public String examShowtoString(List<Choice> list) {
		if(list.isEmpty()){
			return "<h5>题库为空</h5>";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>题目</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td><strong>");
			sb.append(list.get(i).getC_id());
			sb.append("</strong>&nbsp;&nbsp;&nbsp;&nbsp;");
			sb.append(list.get(i).getQuestion());
			sb.append("<br><strong>A:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceA());
			sb.append("<br><strong>B:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceB());
			sb.append("<br><strong>C:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceC());
			sb.append("<br><strong>D:</strong>&nbsp;");
			sb.append(list.get(i).getC_choiceD());
			sb.append("<br><strong>Answer:</strong>&nbsp;");
			sb.append(list.get(i).getC_answer());
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<strong>分值:</strong>&nbsp;");
			sb.append(list.get(i).getC_score());
			
			sb.append("</td><td>");
			sb.append("<button onclick='deleteQuestion("+list.get(i).getC_id()+")'>删除</button>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
	}

	@Override
	public boolean deleteQuestion(String exam_id, String c_id) {
		sql ="delete from exam_choice where exam_id ='"+exam_id+"' and c_id='"+c_id+"'";
		int n = DbUtil.executeUpdate(sql);
		if(n==1)
			return true;
		else
			return false;
	}

	@Override
	public boolean delteQuestionFromBank(String c_id) {
		sql= "delete from choice where c_id="+c_id;
		int n = DbUtil.executeUpdate(sql);
		if(n==1)
			return true;
		else
			return false;
	}

}
