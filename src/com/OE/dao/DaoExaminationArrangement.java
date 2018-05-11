package com.OE.dao;

import java.util.List;

import com.OE.Beans.ExaminationArrangement;

public interface DaoExaminationArrangement {
	/*通过教师编号，获取该教师安排的考试安排*/
	List<ExaminationArrangement> getExam_Arrangement_List(String username);
	/*考试安排转换成带html的String*/
	String toString(List<ExaminationArrangement> examinationArrangements);
	/*通过考试安排编号获取考试安排*/
	ExaminationArrangement getExam_Arrangement(Integer arrangement_id);
	/*更新考试安排*/
	boolean update(ExaminationArrangement ea);
	/*发布考试安排*/
	boolean releaseExam_arrange(String arrangement_id);
	/*删除考试安排*/
	boolean deleteExam_arrange(String arrangement_id);
	/*添加考试安排*/
	boolean add_arrange(ExaminationArrangement examinationArrangement);

}
