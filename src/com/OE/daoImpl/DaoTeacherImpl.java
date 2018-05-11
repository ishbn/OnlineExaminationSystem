package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.OE.Beans.Teacher;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoTeacher;

public class DaoTeacherImpl implements DaoTeacher {

	@Override
	public StringBuffer showTc(int did) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>教师id</th><th>姓名</th><th>性别</th><th>身份证号</th><th>职称</th><th>电话</th><th>操作</th></tr></thead><tbody>");
		
		String sql = "SELECT * FROM teacher where department_id="+did+"";
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sb.append("<tr><td>");
				sb.append(rs.getInt("tid"));
				sb.append("</td><td>");
				sb.append(rs.getString("tname"));
				sb.append("</td><td>");
				sb.append(rs.getString("sex"));
				sb.append("</td><td>");
				sb.append(rs.getString("cardnumber"));
				sb.append("</td><td>");
				sb.append(rs.getString("title"));
				sb.append("</td><td>");
				sb.append(rs.getString("phone"));
				sb.append("</td><td>");
				sb.append("<a href='./ServletTeacher?option=delete&name=" + rs.getInt("tid")+ "&id=" + did+ "'>删除 </a>");
				sb.append("<a href='page/admin/updateTeacher.jsp?name=" + rs.getInt("tid") + "&id=" + did+ ""
						+ "&tn="+rs.getString("tname")+"&ts="+rs.getString("sex")+"&tc="+rs.getString("cardnumber")+""
								+ "&tt="+rs.getString("title")+"&tp="+rs.getString("phone")+"'>修改</a>");
				sb.append("</td></tr>");
			}
			sb.append("</tbody></table>	</div></div></div>");
			DbUtil.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sb;
	}

	@Override
	public int addTc(int did,Teacher tc) {
		String sql = "INSERT INTO teacher (tname,sex,cardnumber,title,phone,department_id) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		int result = 0;
		try {
			ps.setString(1, tc.getTname());
			ps.setString(2, tc.getSex());
			ps.setString(3, tc.getCardNumber());
			ps.setString(4, tc.getTitle());
			ps.setString(5, tc.getPhone());
			ps.setInt(6, did);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbUtil.close();
		return result;
	}

	@Override
	public void deleteTc(int id) {
		String sql = "DELETE FROM teacher WHERE tid = '" + id + "'";
		DbUtil.executeUpdate(sql);
	}

	@Override
	public int updateTc(int id, Teacher tc) {
		String sql = "UPDATE teacher SET tname=?,sex=?,cardnumber=?,title=?,phone=? WHERE tid= '" + id + "'";
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		int result = 0;
		try {
			ps.setString(1, tc.getTname());
			ps.setString(2, tc.getSex());
			ps.setString(3, tc.getCardNumber());
			ps.setString(4, tc.getTitle());
			ps.setString(5, tc.getPhone());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
		return result;
	}

	@Override
	public Teacher getTeacherByUsername(String username) {
		String sql = "select * from teacher where tid="+username;
		ResultSet resultSet = DbUtil.executeQuery(sql);
		Teacher teacher  = new Teacher();
		try {
			while(resultSet.next()){
				teacher.setTid(resultSet.getInt("tid"));
				teacher.setSex(resultSet.getString("sex"));
				teacher.setCardNumber(resultSet.getString("cardNumber"));
				teacher.setPhone(resultSet.getString("phone"));
				teacher.setTitle(resultSet.getString("title"));
				teacher.setTname(resultSet.getString("tname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
		
		return teacher;
	}

}
