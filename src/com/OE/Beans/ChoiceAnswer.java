package com.OE.Beans;

public class ChoiceAnswer {
	Integer ca_id,c_id,sid,exam_id;
	String answer;
	public ChoiceAnswer() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getExam_id() {
		return exam_id;
	}



	public void setExam_id(Integer exam_id) {
		this.exam_id = exam_id;
	}



	public Integer getCa_id() {
		return ca_id;
	}
	public void setCa_id(Integer ca_id) {
		this.ca_id = ca_id;
	}
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
