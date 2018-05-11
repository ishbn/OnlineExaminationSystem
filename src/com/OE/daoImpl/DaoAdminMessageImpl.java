package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.OE.Beans.Admin;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoAdmin;

public class DaoAdminMessageImpl implements DaoAdmin{
	public boolean AdmUp(Admin adm)
	{
		String sql = "update admin set adminName=?,sex=?,cardNumber=?,phone=? where admin_id='"+adm.getAdmin_id()+"' ";
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		int result = 0 ;
		try {
			ps.setString(1, adm.getAdminName());
			ps.setString(2, adm.getSex());
			ps.setString(3, adm.getCardNumber());
			ps.setString(4, adm.getPhone());
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
		if(result>0)
		return true;
		else 
		return false;
	}
	public Admin AdmSea(String name)
	{
		String sql = "select * from admin where admin_id="+name+"";
		ResultSet rs = null;
		Admin adm = new Admin();
		try {
			
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				
				adm.setAdminName(rs.getString("adminName"));
				adm.setSex(rs.getString("sex"));
				adm.setCardNumber(rs.getString("cardNumber"));
				adm.setPhone(rs.getString("phone"));
				
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		return adm;
	}

}
