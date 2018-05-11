package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.OE.Beans.Schedule;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoSchedule;

public class DaoScheduleImpl implements DaoSchedule {

	@Override
	public StringBuffer department() {
		StringBuffer sb = new StringBuffer();
		String sql = "select * from department";
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>编号</th><th>学院</th><th>操作</th></tr></thead><tbody>");
		
		ResultSet rs = null;
		try {
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				sb.append("<tr><td>");
				sb.append(rs.getInt("department_id"));
				sb.append("</td><td>");
				sb.append(rs.getString("department_name"));
				sb.append("</td><td>");
				sb.append("<a href='ServletSchedule?option=teacher&p="+rs.getInt("department_id")+"'>进入</a>");
				sb.append("</td></tr>");
			}
			sb.append("</tbody></table>	</div></div></div>");

		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		return sb;
	}
	

	@Override
	public StringBuffer teacher(String x) {
		StringBuffer sb = new StringBuffer();
		String sql = "select * from teacher where department_id = "+x+"";
		ResultSet rs = null;
		try {
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				sb.append("<option value="+rs.getInt("tid")+">");
				sb.append(rs.getString("tname"));
				sb.append("</option>");
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		return sb;
	}


	@Override
	public StringBuffer subject(String x) {
		StringBuffer sb = new StringBuffer();
		String sql = "select * from subject";
		ResultSet rs = null;
		try {
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				sb.append("<option value="+rs.getInt("subject_id")+">");
				sb.append(rs.getString("subject_name"));
				sb.append("</option>");
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		return sb;
	}


	@Override
	public StringBuffer Class(String x) {
		StringBuffer sb = new StringBuffer();
		String sql = "select * from class where department_id = "+x+"";
		ResultSet rs = null;
		try {
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				sb.append("<option value="+rs.getInt("class_id")+" >");
				sb.append(rs.getString("class_name"));
				sb.append("</option>");
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		return sb;
	}


	@Override
	public boolean AddSchedult(Schedule sch) {
		int result=0;
		String sql1 = "INSERT INTO schedule (tid,subject_id,class_id) VALUES (?,?,?)";
		PreparedStatement ps1 = DbUtil.executePreparedStatement(sql1);
 		try {
 			ps1.setInt(1, sch.getTid());
 			ps1.setInt(2, sch.getSubject_id());
 			ps1.setInt(3, sch.getClass_id());
 			result=ps1.executeUpdate();
 			ps1.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		DbUtil.close();
 		if(result!=0)
 		return true;
 		else 
 		return false;
	}


	@Override
	public int FinTea(String tt) {
		int sb = 0;
		String sql = "select tid from teacher where tname = "+tt+"";
		ResultSet rs = null;
		try {
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				sb = rs.getInt("tid");
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		return sb;
	}


	@Override
	public int FinSub(String ss) {
		int sb = 0;
		String sql = "select subject_id from subject where subject_name = "+ss+"";
		ResultSet rs = null;
		try {
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				sb = rs.getInt("subject_id");
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		return sb;
	}


	@Override
	public int FinCla(String cc) {
		int sb = 0;
		String sql = "select class_id from class where class_name = "+cc+"";
		ResultSet rs = null;
		try {
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				sb = rs.getInt("class_id");
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		return sb;
	}

}
