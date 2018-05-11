package com.OE.dao;

import java.util.List;

import com.OE.Beans.Choice;
import com.OE.Beans.Exam_choice;
import com.OE.Beans.FullingBlank;

public interface DaoQuestionBank {
	List<FullingBlank> getFullingBlankList(String exam_id);

	boolean add_question_to_bank(Choice choice);

	List<Choice> getChoiceListBySubject(Integer integer);

	String toString(List<Choice> choices);

	List<Choice> getChoiceListNotInExam(Integer integer, Integer integer2);

	String toAddString(List<Choice> choices);

	List<Choice> getChoiceListInExam(Integer integer);

	boolean add_question_to_exam(Exam_choice ec);

	String examShowtoString(List<Choice> choices);

	boolean deleteQuestion(String exam_id, String c_id);

	boolean delteQuestionFromBank(String c_id);
	
	
}
