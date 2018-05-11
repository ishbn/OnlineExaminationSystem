package com.OE.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OE.Beans.Admin;
import com.OE.Beans.Choice;
import com.OE.Beans.ExaminationArrangement;
import com.OE.Beans.FullingBlank;
import com.OE.Beans.Student;
import com.OE.Beans.Subject;
import com.OE.Utils.DbUtil;
import com.OE.dao.DaoStudent;

public class DaoStudentImpl implements DaoStudent {
	private String sql=null;
	private ResultSet rs = null;
	@Override
	public String getStudentNameById(Integer sid) {
		sql = "select sname from student where sid = "+sid;
		String name = null;
		rs = DbUtil.executeQuery(sql);
		try {
			if(rs.next()){
				name = rs.getString("sname");
			}
			DbUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * 作者：徐子颖
	 * 
	 * */
	public boolean StuAdd(Student stu)//增加学生信息
	{
		int result=0;
		String sql1 = "INSERT INTO student (sname,sex,cardNumber,phone,class_id) VALUES (?,?,?,?,?)";
		PreparedStatement ps1 = DbUtil.executePreparedStatement(sql1);
 		try {
 			ps1.setString(1, stu.getSname());
 			ps1.setString(2, stu.getSex());
 			ps1.setString(3, stu.getCardNumber());
 			ps1.setString(4, stu.getPhone());
 			ps1.setInt(5, stu.getClass_id());
 			result=ps1.executeUpdate();
 			ps1.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		DbUtil.close();
 		if(result!=0)
 		return true;
 		else 
 		return false;
	}
	public boolean UserAdd()
	{
		
		String sql = "select * from student";
		return true;
	}

	public boolean StuDel(String p)//删除学生信息
    {
    	//int result=0;
    	int result1=0;
    	
    	String sql1 = "delete from student where sid='"+p+"'";
    	result1 = DbUtil.executeUpdate(sql1);
		if(result1>0)
			return true;
		else 
		    return false;
    }
	
	public boolean StuUpd(Student stu)//修改学生信息
    {
    	String sql = "update student set sname=?,sex=?,cardNumber=?,phone=?,class_id=? where sid='"+stu.getSid()+"'";
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		int result = 0 ;
		try {
			ps.setString(1, stu.getSname());
			ps.setString(2, stu.getSex());
			ps.setString(3, stu.getCardNumber());
			ps.setString(4, stu.getPhone());
			ps.setInt(5, stu.getClass_id());
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
		if(result>0)
		return true;
		else 
		return false;
    }
	public StringBuffer StuFin()//查询学生信息
	{
		StringBuffer sb = new StringBuffer();
		String sql = "select * from student";
		sb.append("<div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
		sb.append("<thead><tr><th>编号</th><th>姓名</th><th>性别</th><th>身份证</th><th>电话</th><th>班级</th><th>操作</th></tr></thead><tbody>");
		ResultSet rs = null;
		try {
			rs = DbUtil.executeQuery(sql);
			while(rs.next())
			{
				sb.append("<tr><td>");
				sb.append(rs.getInt("sid"));
				sb.append("</td><td>");
				sb.append(rs.getString("sname"));
				sb.append("</td><td>");
				sb.append(rs.getString("sex"));
				sb.append("</td><td>");
				sb.append(rs.getString("cardNumber"));
				sb.append("</td><td>");
				sb.append(rs.getString("phone"));
				sb.append("</td><td>");
				sb.append(rs.getInt("class_id"));
				sb.append("</td><td>");
				sb.append("<a href='ServletStudent?option=dele&q="+rs.getInt("sid")+"'>删除</a>");
				sb.append("&nbsp;&nbsp;&nbsp;");
				sb.append("<a href='ServletStudent?option=upda&q="+rs.getInt("sid")+"&w="+rs.getString("sname")+"&e="+rs.getString("sex")+"&r="+rs.getString("cardNumber")+"&t="+rs.getString("phone")+"&y="+rs.getInt("class_id")+"'>修改</a>");
				sb.append("</td></tr>");
				
			}
			sb.append("</tbody></table>	</div></div></div></div>");
		} catch (SQLException e) {
			System.out.println("查询出错!");
			e.printStackTrace();
		} finally {
			DbUtil.close();
		}
		
		return sb;
	}
	public boolean AdmUpd(Admin adm)//修改个人信息
    {
    	String sql = "update admin set adminName=?,sex=?,cardNumber=?,phone=? where admin_id='"+adm.getAdmin_id()+"'";
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		int result = 0 ;
		try {
			ps.setString(1, adm.getAdminName());
			ps.setString(2, adm.getSex());
			ps.setString(3, adm.getCardNumber());
			ps.setString(4, adm.getPhone());
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
		if(result>0)
		return true;
		else 
		return false;
    }
	
	
	/**
	 * 徐子颖部分完结
	 * 
	 * */
	
	
	
	/**
	 * 作者：黄俊杰
	 * 
	 * */
	public Student search(Integer username) {
		//System.out.println(username);
		String sql="SELECT * FROM student WHERE sid='"+username+"'";	
		Student s=new Student();
		ResultSet rs=DbUtil.executeQuery(sql);
		try {
			if(rs.next()) {
				s.setCardNumber(rs.getString("cardNumber"));
				//System.out.println(s.getCardNumber());
				s.setSex(rs.getString("sex"));
				s.setSname(rs.getString("sname"));
				s.setPhone(rs.getString("phone"));
				s.setClass_id(rs.getInt("class_id"));
				s.setSid(rs.getInt("sid"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
		return s;
		}
	
	/*
	 * 用于首页内容的展示
	 * */
		@Override
		public String show(Integer username) {
			String sql="SELECT * FROM student WHERE sid='"+username+"'";
			String sname=null;
			ResultSet rs=DbUtil.executeQuery(sql);
			try {
				if(rs.next()) {
					sname=rs.getString("sname");	
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			DbUtil.close();
			return sname;
			
		}
		/*
		 * 更改密码
		 * */

		@Override
		public boolean pwdupdate(Integer username, String password) {
			String	sql="UPDATE user SET password=? WHERE username=?";
			PreparedStatement ps =DbUtil.executePreparedStatement(sql);
			int result = 0;
			 try {
				 ps.setString(1,password);
				 ps.setInt(2,username);
				 result =ps.executeUpdate();
			 }catch(SQLException e)
				{
					e.printStackTrace();
				}
			 if(result!=0)
			 {
				 return true;
			 }
			 DbUtil.close();
			return false;	
		}
		
		
		/*
		 * 获取考试科目名称,返回list对象
		 * */
			@Override
			public List<Subject> getSubjectInfo(Integer class_id) {
				List<Subject> l1=new ArrayList<Subject>();
				String sql="SELECT * FROM `subject` as sub  WHERE subject_id IN ( SELECT  subject_id FROM `schedule` as sch WHERE class_id = '"+class_id+"' AND sch.subject_id = sub.subject_id )";
				ResultSet rs=DbUtil.executeQuery(sql);
				try {
					while(rs.next()) {
						Subject sb=new Subject();
						sb.setSubject_id(rs.getInt("subject_id"));
						sb.setSubject_name(rs.getString("subject_name"));
						l1.add(sb);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}/*
				for(int i=0;i<l1.size();i++) {
					System.out.println(l1.get(i).getSubject_name())
				}*/
				DbUtil.close();
				return l1;
			}
			
			/*
			 * 获取选择题
			 * */
				@Override
				public List<Choice> getChoice(Integer subject_id,Integer class_id) {
					List<Choice> choice=new ArrayList<Choice>();
					String sql="SELECT * FROM choice where c_id IN (SELECT c_id FROM exam_choice where exam_id in	(SELECT exam_id FROM exam_arrangement WHERE subject_id ='"+subject_id+"' AND class_id='"+class_id+"'))";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						while(rs.next()) {
							Choice c=new Choice();
							c.setC_id(rs.getInt("c_id"));
							c.setQuestion(rs.getString("c_question"));
							c.setC_choiceA(rs.getString("c_choiceA"));
							c.setC_choiceB(rs.getString("c_choiceB"));
							c.setC_choiceC(rs.getString("c_choiceC"));
							c.setC_choiceD(rs.getString("c_choiceD"));
							c.setC_answer(rs.getString("c_answer"));
							c.setC_score(rs.getInt("c_score"));
							choice.add(c);
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					DbUtil.close();
					return choice;
				}
				/*
				 * 填空题
				 * */
				@Override
				public List<FullingBlank> getFulling(Integer subject_id,Integer class_id) {
					List<FullingBlank> fulling=new ArrayList<FullingBlank>();
					String sql="SELECT * FROM fulling where f_id IN (SELECT f_id FROM exam_fulling where exam_id in	(SELECT exam_id FROM exam_arrangement WHERE subject_id ='"+subject_id+"' AND class_id='"+class_id+"'))";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						while(rs.next()) {
						FullingBlank fu=new FullingBlank();
						fu.setF_id(rs.getInt("f_id"));
						fu.setF_question(rs.getString("f_question"));
						fu.setF_answer(rs.getString("f_answer"));
						fu.setF_score(rs.getInt("f_score"));
						fulling.add(fu);
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					DbUtil.close();
					return fulling;
				}
			/*
			 * 获取考试科目名
			 * */
				@Override
				public String subjectname(Integer subject_id) {
					String subject_name=null;
					String sql="SELECT * FROM subject WHERE subject_id='"+subject_id+"'";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						while(rs.next()) {
						subject_name=rs.getString("subject_name");
						//System.out.println(subject_name);
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					DbUtil.close();
					return subject_name;
				}
				/*
				 * 获取考生所选科目对应科目的考卷信息
				 * */

				@Override
				public ExaminationArrangement exam(Integer subject_id,Integer class_id) {
					ExaminationArrangement ea=new ExaminationArrangement();
					ea.setExam_id(0);
					String sql="SELECT * from exam_arrangement where class_id='"+class_id+"' AND exam_id in ( SELECT exam_id from exam WHERE subject_id ='"+subject_id+"')";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						while(rs.next()) {
						ea.setArrangement_id(rs.getInt("arrangement_id"));
						ea.setExam_id(rs.getInt("exam_id"));
						ea.setClass_id(class_id);
						ea.setPublish(rs.getInt("publish"));
						ea.setExam_date(rs.getDate("exam_date"));
						ea.setTimeLength(rs.getInt("timeLength"));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					DbUtil.close();
					return ea;
				}
				/*
				 * 复制学生考试卷子的选择题号到答案表
				 * */

				@Override
				public void copyCaI(Integer sid, Integer exam_id, List<Choice> list1) {
					String sql1="DELETE FROM canswer WHERE sid='"+sid+"'AND exam_id='"+exam_id+"'";
					DbUtil.executeUpdate(sql1);
					for(int i=0;i<list1.size();i++) {	
						String sql="INSERT INTO canswer(C_id,sid,exam_id)VALUES(?,?,?)";
						PreparedStatement ps=DbUtil.executePreparedStatement(sql);
						int result=0;
						try {
							ps.setInt(1, list1.get(i).getC_id());
							ps.setInt(2, sid);
							ps.setInt(3, exam_id);
							result =ps.executeUpdate();
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
					}
					 DbUtil.close();	
				}

				/*
				 * 复制学生考试卷子的填空题号到答案表
				 * */
				@Override
				public void copyFaI(Integer sid, Integer exam_id, List<FullingBlank> list2) {
					String sql1="DELETE FROM fanswer WHERE sid='"+sid+"'AND exam_id='"+exam_id+"'";
					DbUtil.executeUpdate(sql1);
					for(int i=0;i<list2.size();i++) {	
						String sql="INSERT INTO fanswer(f_id,sid,exam_id)VALUES(?,?,?)";
						
						
						PreparedStatement ps=DbUtil.executePreparedStatement(sql);
						int result=0;
						try {
							ps.setInt(1, list2.get(i).getF_id());
							ps.setInt(2, sid);
							ps.setInt(3, exam_id);
							result =ps.executeUpdate();
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
					}
					 DbUtil.close();
				}
				/*
				 * 获取考生所做卷子的选择题题号设置成一个list
				 * */

				@Override
				public List<Integer> c_id(Integer sid, Integer exam_id) {
					List<Integer> cidlist=new ArrayList<Integer>();
					String sql="SELECT C_id FROM exam_choice WHERE exam_id='"+exam_id+"'";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						while(rs.next()) {
							Integer a;
							a=rs.getInt("C_id");
							cidlist.add(a);
						}
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
					DbUtil.close();
					return cidlist;
				}
				/*
				 * 获取考生所做卷子的选择题题号设置成一个list
				 * */

				@Override
				public List<Integer> f_id(Integer sid, Integer exam_id) {
					List<Integer> fidlist=new ArrayList<Integer>();
					String sql="SELECT f_id FROM fanswer WHERE sid='"+sid+"' AND exam_id='"+exam_id+"'";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						while(rs.next()) {
							Integer a;
							a=rs.getInt("f_id");
							fidlist.add(a);
						}
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
					DbUtil.close();
					return fidlist;
				}
			/*
			 * 获取考生所做选择题的题号正确答案和所属分值
			 * */
				@Override
				public List<Choice> choice(Integer c_id) {
					List<Choice> choice=new ArrayList<Choice>();
					String sql="SELECT * FROM choice WHERE c_id='"+c_id+"'";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						while(rs.next()) {
							Choice c=new Choice();
							c.setC_answer(rs.getString("c_answer"));
							c.setC_score(rs.getInt("c_score"));
							choice.add(c);
						}	
					}catch(SQLException e) {
						e.printStackTrace();
					}	
					DbUtil.close();
					return choice;
				}
				/*
				 * 获取考生所做填空题的题号正确答案和所属分值
				 * */
				@Override
				public List<FullingBlank> fulling(Integer f_id) {
					List<FullingBlank> fulling=new ArrayList<FullingBlank>();
					String sql="SELECT * FROM fulling WHERE f_id='"+f_id+"'";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						while(rs.next()) {
							FullingBlank f=new FullingBlank();
							f.setF_answer(rs.getString("f_answer"));
							f.setF_score(rs.getInt("f_score"));
							fulling.add(f);
						}	
					}catch(SQLException e) {
						e.printStackTrace();
					}	
					DbUtil.close();
					return fulling;
				}
			/*
			 * 添加成绩到成绩表
			 * */
				@Override
				public void score(Integer sid, Integer sum,Integer exam_id) {
					String sql="INSERT INTO score(sid,grade,exam_id) VALUES(?,?,?)";
					PreparedStatement ps = DbUtil.executePreparedStatement(sql);
					 int result = 0;
					 try {
						 ps.setInt(1, sid);
						 ps.setInt(2, sum);
						 ps.setInt(3, exam_id);
						 result =ps.executeUpdate();
					 }catch(SQLException e)
						{
							e.printStackTrace();
						}
					 DbUtil.close();
				}
			/*
			 * 把考生选择题答案写回到答案表
			 * */
				@Override
				public void choiceanswer(Integer c_id, Integer exam_id, Integer sid,String can) {
					// TODO Auto-generated method stub
					String sql=" UPDATE canswer SET answer=? WHERE C_id='"+c_id+"' AND sid='"+sid+"' And exam_id='"+exam_id+"'";
					PreparedStatement ps = DbUtil.executePreparedStatement(sql);
					int result = 0;
					 try {
						 ps.setString(1, can);
						 result =ps.executeUpdate();
					 }catch(SQLException e)
						{
							e.printStackTrace();
						}
					 DbUtil.close();
					
				}
				/*
				 * 把考生填空题答案写回到答案表
				 * */
				@Override
				public void fullinganswer(Integer f_id, Integer exam_id, Integer sid,String fan) {
					String sql="UPDATE fanswer SET Answer=? WHERE f_id='"+f_id+"'AND sid='"+sid+"'And exam_id='"+exam_id+"'";
					PreparedStatement ps = DbUtil.executePreparedStatement(sql);
					int result = 0;
					try {
						System.out.println(fan);
						 ps.setString(1, fan);
						 result =ps.executeUpdate();
					 }catch(SQLException e)
						{
							e.printStackTrace();
						}
					 DbUtil.close();
				}
/*
 * 检查是否已考科目
 * */

				@Override
				public boolean check(Integer exam_id,Integer sid) {
					String sql="SELECT * FROM score WHERE exam_id='"+exam_id+"' AND sid='"+sid+"'";
					ResultSet rs=DbUtil.executeQuery(sql);
					try {
						if(rs.next()) {
							return true;
						}
					}catch(SQLException e)
					{
						e.printStackTrace();
					}
					return false;
				}	
	
		/**
		 * 黄俊杰完结
		 * */
		@Override
		public String toShowExamSubject(String username) {
			sql = "SELECT exam_arrangement.exam_id,subject_name FROM student,exam_arrangement,exam,`subject` as sub WHERE student.sid='"+username+"' AND publish = 1 AND student.class_id = exam_arrangement.class_id AND exam_arrangement.exam_id = exam.exam_id AND exam.subject_id = sub.subject_id";
			System.out.println(sql);
			rs = DbUtil.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			sb.append("<br><div class='container'><div class='row clearfix'><div class='col-md-12 column'><table class='table table-striped table-bordered'>");
			sb.append("<thead><tr><th>编号</th><th>科目</th><th>操作</th></tr></thead><tbody>");
			
			try {
				while(rs.next()){
					sb.append("<tr><td>");
					sb.append(rs.getString("exam_id"));
					sb.append("</td><td>");
					sb.append(rs.getString("subject_name"));
					sb.append("</td><td>");
					sb.append("<button onclick='toExam("+rs.getString("exam_id")+")'>进入考试</button>");
					sb.append("</td></tr>");
				}
				sb.append("</tbody></table>	</div></div></div></div>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.close();
			
			return sb.toString();
		}
	
}
