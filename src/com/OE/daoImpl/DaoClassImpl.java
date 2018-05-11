package com.OE.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OE.dao.DaoClass;
import com.OE.dao.DaoSubject;
import com.OE.daoFactory.DaoFactory;
import com.OE.Beans.Class;
import com.OE.Utils.DbUtil;
public class DaoClassImpl implements DaoClass {
	String sql;
	ResultSet rs;
	/*查询班级列表*/
	@Override
	public List<Class> getClassList() {
		List<Class> class_list  = new ArrayList<>();
		sql = "select * from class";
		rs  = DbUtil.executeQuery(sql);
		
		try {
			while(rs.next()){
				Class class1 = new Class();
				class1.setClass_id(rs.getInt("class_id"));
				class1.setClass_name(rs.getString("class_name"));
				class_list.add(class1);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return class_list;
	}
	@Override
	public String getClassName(int class_id) {
		sql = "select class_name from class where class_id="+class_id;
		rs = DbUtil.executeQuery(sql);
		String name = "";
		try {
			if (rs.next()) {
				name = rs.getString("class_name");
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	@Override
	public List<Class> getClassList(String username) {
		List<Class> class_list  = new ArrayList<>();
		sql = "SELECT * FROM class where class_id in (SELECT class_id from `schedule` as sch where tid='"+username+"' AND sch.class_id = class.class_id)";
		System.out.println(sql);
		rs  = DbUtil.executeQuery(sql);
		try {
			while(rs.next()){
				Class class1 = new Class();
				class1.setClass_id(rs.getInt("class_id"));
				class1.setClass_name(rs.getString("class_name"));
				class_list.add(class1);
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return class_list;
	}
	@Override
	public String getSelect_class_drowdownlist(List<Class> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <div class='input-group col-md-6'>");
		sb.append("<span class='input-group-addon'>班级列表</span>");
		sb.append("<span id='startingCity' class='input-group-addon'>");
		sb.append("<select id='class_id'  name='class_id'>");
		for(int i=0;i<list.size();i++){
			sb.append("<option value ="+list.get(i).getClass_id()+">"+list.get(i).getClass_name()+"</option>");
		}
		sb.append("</select></span></div><br/>");	
		return sb.toString();
	}
	@Override
	public String teachingListToString(List<Class> list) {
		if(list.isEmpty()){
			return "<h5>目前还没任教班级</h5>";
		}
		StringBuffer sb = new StringBuffer();
		DaoSubject daoSubject = DaoFactory.getDaoSubjectImpl();
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>班级编号</th><th>班级名称</th><th>操作</th></tr></thead><tbody>");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr><td>");
			sb.append(""+list.get(i).getClass_id()+" ");
			sb.append("</td><td>");
			sb.append(" "+list.get(i).getClass_name()+" ");
			sb.append("</td><td>");
			sb.append("<a href='ServletSubject?option=findSubjectListByteacher&class_id="+list.get(i).getClass_id()+"'>进入查看</a><br/>");
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>	</div></div></div>");
		return sb.toString();
	}

}
