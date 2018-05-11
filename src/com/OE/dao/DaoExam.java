package com.OE.dao;

import java.util.List;

import com.OE.Beans.Examination;

public interface DaoExam {
	/*获取教师所出的卷子*/
	List<Examination> getExamList(String username);
	/*卷子列表转换成戴html的String*/
	String toString(List<Examination> ea);
	/*删除考卷*/
	boolean deleteExam(String exam_id);
	/*添加考卷*/
	boolean add(Examination ea);
	/*通过考卷编号获取考卷*/
	Examination getExam(String exam_id);
	/*更新考卷*/
	boolean update(Examination ea);
	/*通过考卷编号获取考卷名称*/
	String getExamName(Integer exam_id);
	/*考试列表转换成下拉列表*/
	String getSelect_exam_drowDownList(List<Examination> list);
	String getExamTime(String exam_id, String username);
	boolean ifStart(String exam_id, String username);

}
