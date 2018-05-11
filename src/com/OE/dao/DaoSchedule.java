package com.OE.dao;

import com.OE.Beans.Schedule;

public interface DaoSchedule {
	StringBuffer department();
	StringBuffer teacher(String x);
	StringBuffer subject(String x);
	StringBuffer Class(String x);
	boolean AddSchedult(Schedule sch);
	int FinTea(String tt);
	int FinSub(String ss);
	int FinCla(String cc); 
}
