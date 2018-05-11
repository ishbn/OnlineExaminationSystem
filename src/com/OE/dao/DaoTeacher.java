package com.OE.dao;

import com.OE.Beans.Teacher;

public interface DaoTeacher {
	public StringBuffer showTc(int did);
	public int addTc(int did,Teacher tc);
	public void deleteTc(int id);
	public int updateTc(int id,Teacher tc);
	public Teacher getTeacherByUsername(String username);
}
