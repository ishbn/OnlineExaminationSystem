package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.OE.Beans.Department;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoDepartment;

public class DaoDepartmentImpl implements DaoDepartment {

	@Override
	public StringBuffer showall() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		String sql = "SELECT * FROM department";
		
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>学院编号</th><th>学院名称</th><th>操作</th></tr></thead><tbody>");
		
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sb.append("<tr><td>");
				sb.append(rs.getInt("department_id"));
				sb.append("</td><td>");
				sb.append(rs.getString("department_name"));
				sb.append("</td><td>");
				sb.append("<a href='./../../ServletDepartment?option=delete&id=" + rs.getInt("department_id")
						+ "'>删除 </a>");
				sb.append("<a href='./../../ServletTeacher?option=show&id=" + rs.getInt("department_id")
						+ "'>查找</a>&nbsp;");
				sb.append("<a href='updateDepartment.jsp?id=" + rs.getInt("department_id") + "&name=" +rs.getString("department_name")+ "'>修改</a>");
				sb.append("</td></tr>");
			}
			sb.append("</tbody></table>	</div></div></div>");
			DbUtil.close();
		} catch (Exception e) {
		}
		return sb;
	}

	@Override
	public int add(Department xy) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO department (department_name) VALUES (?)";
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		int result = 0;
		try {
			ps.setString(1, xy.getDepartmentName());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbUtil.close();
		return result;
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM department WHERE department_id = '" + id + "'";
		DbUtil.executeUpdate(sql);
	}

	@Override
	public int update(int id, Department xy) {
		String sql = "UPDATE department SET department_name=? WHERE department_id= '" + id + "'";
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		int result = 0;
		try {
			ps.setString(1, xy.getDepartmentName());
			result = ps.executeUpdate();
			System.out.println("result:" + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbUtil.close();
		return result;
	}

	@Override
	public String queryName(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM department WHERE department_id ='" + id + "'";
		String dname = null;
		ResultSet rs = DbUtil.executeQuery(sql);
		try {
			if(rs.next())
			{
				dname=rs.getString("department_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbUtil.close();
		return dname;
	}

	

}
