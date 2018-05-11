package com.OE.dao;

import java.util.List;

import com.OE.Beans.Score;

public interface DaoScore {

	List<Score> getScoreList(String class_id, String subject_id);

	String toString(List<Score> scores);

	StringBuffer findscore(Integer integer);

}
