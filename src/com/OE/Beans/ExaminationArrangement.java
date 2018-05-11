package com.OE.Beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExaminationArrangement {
	private Integer arrangement_id,exam_id,class_id,publish,timeLength;
	Date Exam_date;
	public Integer getArrangement_id() {
		return arrangement_id;
	}

	public void setArrangement_id(Integer arrangement_id) {
		this.arrangement_id = arrangement_id;
	}

	public Integer getExam_id() {
		return exam_id;
	}

	public void setExam_id(Integer exam_id) {
		this.exam_id = exam_id;
	}

	

	public Integer getClass_id() {
		return class_id;
	}

	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}
	public Integer getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(Integer timeLength) {
		this.timeLength = timeLength;
	}
	
	public Date getExam_date() {
		return Exam_date;
	}
	public void setExam_date(Date exam_date) {
		Exam_date = exam_date;
	}
	public String toString(Date exam_date2) {
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return myFmt.format(exam_date2);
	}
	
	public Date toBeDate(String dateTime) {
		Date date = new Date();
		DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {    
		           
		      date = format2.parse(dateTime); 
		} catch (Exception e) {    
		           e.printStackTrace();    
		}   
		return date;
	}
}
