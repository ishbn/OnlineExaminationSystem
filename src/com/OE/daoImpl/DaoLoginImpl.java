package com.OE.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.OE.Utils.DbUtil;
import com.OE.dao.DaoLogin;

public class DaoLoginImpl implements DaoLogin {
	private String result="";
	@Override
	public String check(String username, String password) {
		String sql = "select type from user where username='"+username+"' and password='"+password+"'";
		System.out.println(sql);
		ResultSet rs = DbUtil.executeQuery(sql);
		try {
			if(rs.next()){
				result = rs.getString("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
