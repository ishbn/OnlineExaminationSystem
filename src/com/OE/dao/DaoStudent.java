package com.OE.dao;

import java.util.List;

import com.OE.Beans.Admin;
import com.OE.Beans.Choice;
import com.OE.Beans.ExaminationArrangement;
import com.OE.Beans.FullingBlank;
import com.OE.Beans.Student;
import com.OE.Beans.Subject;

public interface DaoStudent {

	String getStudentNameById(Integer sid);
	
	/**
	 * 作者：徐子颖
	 * */
	boolean StuAdd(Student stu);
	boolean StuDel(String p);
	boolean StuUpd(Student stu);
	StringBuffer StuFin();
	boolean AdmUpd(Admin adm);
	/**
	 * 徐子颖部分完结
	 * */
	
	
	/**
	 * 作者：黄俊杰
	 * */
	
	/*通过学号查找学生的姓名*/
	String show(Integer username);
	/*通过学号查找学生的全部信息*/
     Student search(Integer username);  
     /*通过班级号查找出考试科目名称*/
     List<Subject> getSubjectInfo(Integer class_id);
     /*修改学生密码*/
     boolean pwdupdate(Integer username,String password);
     /*获取选择题题目*/
     List<Choice> getChoice(Integer subject_id,Integer class_id);
     /*获取填空题题目*/
     List<FullingBlank> getFulling(Integer subject_id,Integer class_id);
     /*获取考试科目名*/
     String subjectname(Integer subject_id);
     /*获取考卷id*/
     ExaminationArrangement exam(Integer subject_id,Integer class_id );
     /*复制选择题考题信息到答案表*/
     void copyCaI(Integer sid,Integer exam_id,List<Choice> list1);
     /*复制填空题考题信息到答案表*/
     void copyFaI(Integer sid,Integer exam_id,List<FullingBlank> list2);
     /*获取对应学号考卷id的选择题题号*/
     List<Integer> c_id(Integer sid,Integer exam_id);
     /*获取对应学号考卷id的填空题题号*/
     List<Integer> f_id(Integer sid,Integer exam_id);
     /*获取选择题题信息用来评分*/
     List<Choice> choice(Integer c_id);
     /*获取填空题信息用来评分*/
     List<FullingBlank>fulling(Integer f_id);
     /*添加分数到成绩表*/
     void score(Integer sid,Integer sum,Integer exam_id);
     /*添加选择题考试答案到考试答案表*/
     void choiceanswer(Integer c_id,Integer exam_id,Integer sid,String can);
     /*添加填空题考试答案到考试答案表*/
     void fullinganswer(Integer c_id,Integer exam_id,Integer sid,String fan);
    
     /*判断考生有没有考过所选科目*/
     boolean check(Integer exam_id,Integer sid);
     /**
      * 黄俊杰完结
      * */

	String toShowExamSubject(String username);
	
	
}
