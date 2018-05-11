package com.OE.dao;

import java.util.List;
import com.OE.Beans.Class;

public interface DaoClass {
	/*获取班级列表*/
	List<Class> getClassList();
	/*通过班级编号，获取班级名称*/
	String getClassName(int class_id);
	/*通过教师编号，获取教师所教班级列表*/
	List<Class> getClassList(String username);
	/*班级列表转换成下拉列表*/
	String getSelect_class_drowdownlist(List<Class> class_list);
	/*教师所教班级的列表*/
	String teachingListToString(List<Class> list);
}
