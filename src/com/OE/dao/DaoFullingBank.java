package com.OE.dao;

import java.util.List;

import com.OE.Beans.Exam_Fulling;
import com.OE.Beans.FullingBlank;

public interface DaoFullingBank {

	boolean addFullingBank(FullingBlank fullingBlank);

	boolean deleteFulling(String f_id);

	List<FullingBlank> getFullingBankList(String subject_id);

	String toString(List<FullingBlank> fullingBlanks);

	List<FullingBlank> getFullingBankListInExam(Integer exam_id);

	List<FullingBlank> getFullingBankListNotInExam(Integer integer, Integer integer2);

	String toAddString(List<FullingBlank> fullingBlanks);

	boolean fullingAddToExam(Exam_Fulling exam_Fulling);

	boolean fullingDeleteToExam(Exam_Fulling exam_Fulling);

}
