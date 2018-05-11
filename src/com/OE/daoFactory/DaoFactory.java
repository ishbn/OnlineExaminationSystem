package com.OE.daoFactory;

import com.OE.dao.DaoAdmin;
import com.OE.dao.DaoClass;
import com.OE.dao.DaoExam;
import com.OE.dao.DaoExaminationArrangement;
import com.OE.dao.DaoFullingBank;
import com.OE.dao.DaoLogin;
import com.OE.dao.DaoQuestionBank;
import com.OE.dao.DaoSchedule;
import com.OE.dao.DaoScore;
import com.OE.dao.DaoStudent;
import com.OE.dao.DaoSubject;
import com.OE.daoImpl.DaoAdminMessageImpl;
import com.OE.daoImpl.DaoClassImpl;
import com.OE.daoImpl.DaoDepartmentImpl;
import com.OE.daoImpl.DaoExamImpl;
import com.OE.daoImpl.DaoExaminationArrangementImpl;
import com.OE.daoImpl.DaoFullingBankImpl;
import com.OE.daoImpl.DaoLoginImpl;
import com.OE.daoImpl.DaoQuestionBankImpl;
import com.OE.daoImpl.DaoScheduleImpl;
import com.OE.daoImpl.DaoScoreImpl;
import com.OE.daoImpl.DaoStudentImpl;
import com.OE.daoImpl.DaoSubjectImpl;
import com.OE.daoImpl.DaoTeacherImpl;

public class DaoFactory {
	public static DaoLogin getDaoLoginImpl() {
			
			return new DaoLoginImpl();
		}
	
	public static DaoExam getDaoExamImpl() {
		
		return new DaoExamImpl();
	}
	public static DaoClass getDaoClassImpl(){
		return new DaoClassImpl();
	}

	public static DaoSubject getDaoSubjectImpl() {
		return new DaoSubjectImpl();
	}

	public static DaoQuestionBank getDaoQuestionBankImpl() {
		
		return new DaoQuestionBankImpl();
	}

	public static DaoExaminationArrangement getDaoExaminationArrangementImpl() {
		
		return new DaoExaminationArrangementImpl();
	}

	public static DaoScore getDaoScoreImpl() {
		return new DaoScoreImpl();
	}

	public static DaoStudent getDaoStudentImpl() {
		
		return new DaoStudentImpl();
	}

	public static DaoFullingBank getDaoFullingBankImpl() {
		return new DaoFullingBankImpl();
	}

	/**
	 * 徐子颖部分
	 * */
	public static DaoStudent getDaoStudentMessageImpl() {
		return new DaoStudentImpl();
	}
	public static DaoAdmin getDaoAdminMessageImpl() {
		return new DaoAdminMessageImpl();
	}
	public static DaoSchedule getDaoScheduleImpl() {
		return new DaoScheduleImpl();
	}
	
	/**
	 * 徐子颖部分完结
	 * */
	
	
	/**
	 * 作者：蔡镇楚
	 * */
	public static DaoDepartmentImpl getDaoDepartmentImpl() {
		return new DaoDepartmentImpl();
	}
	public static DaoTeacherImpl getDaoTeacherImpl() {
		return new DaoTeacherImpl();
		
	}
	
}
