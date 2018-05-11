package com.OE.dao;

import java.util.List;

import com.OE.Beans.Subject;

public interface DaoSubject {
	/*通过教师编号,获取教师所教科目*/
	List<Subject> getSub_List(String username);
	/*通过考卷编号,获取科目名称*/
	String getSub_Name_by_exam_id(Integer exam_id);
	/*将科目列表转换成下拉列表*/
	String getSelect_subject_drowdownlist(List<Subject> list);
	/*通过科目编号获取科目名称*/
	String getSub_Name_by_sub_id(Integer subject_id);
	/*题库学科列表转换成带html的表格*/
	String toString(List<Subject> sub_list);
	/*查看班级成绩科目列表*/
	String gradeListtoString(List<Subject> subjects);
	/*查询教师教某一班级的所有科目*/
	List<Subject> getSub_List(String username, String class_id);

}
