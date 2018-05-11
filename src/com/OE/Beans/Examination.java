package com.OE.Beans;



public class Examination {
	Integer tid,exam_id,subject_id;
	String exam_name;
	
	public Examination() {
		// TODO Auto-generated constructor stub
	}
	
	public String getExam_name() {
		return exam_name;
	}

	public Integer getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}

	public Integer getExam_id() {
		return exam_id;
	}
	public void setExam_id(Integer exam_id) {
		this.exam_id = exam_id;
	}
	

	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}

	
	
}
