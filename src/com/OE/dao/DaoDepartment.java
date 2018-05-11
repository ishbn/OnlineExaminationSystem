package com.OE.dao;

import com.OE.Beans.Department;

public interface DaoDepartment {
	public StringBuffer showall();
	public int add(Department xy);
	public void delete(int id);
	public int update(int id,Department xy);
	public String queryName(int id);
}
