package Registration.dao;


import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Registration.model.Student;


public class StudentDao {
	
	public String CONNECTION_STR = "jdbc:mysql://localhost:3306/studentsnew?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

//registerStudent
	public int registerStudent(Student student) throws ClassNotFoundException
	{
		String INSERT_STUDENT_SQL = "INSERT INTO StudentSimple(userName,pass,address,phone,type)"+
				" VALUES (?,?,?,?,?);";
		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database students");
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);
			preparedStatement.setString(1,student.getUserName());
			preparedStatement.setString(2,student.getPassword());
			preparedStatement.setString(3,student.getAddress());
			preparedStatement.setString(4,student.getPhoneNum());
			preparedStatement.setInt(5,student.getType());

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//List Of Students
	public List getListOfStudents() throws ClassNotFoundException
	{
		List dataList = new ArrayList();
		String SQL = "select * from StudentSimple";
    	Statement stmt;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database students");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
			    System.out.println(rs.getString("userName") + " - " + rs.getString("pass"));
			    dataList.add(rs.getString("userName"));
			    dataList.add(rs.getString("pass"));
			    if(rs.getInt("type")==1)
				    dataList.add("student");
			    else
				    dataList.add("teacher");


			}
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

//get Books By Id
	public List getBooksById(String name) throws ClassNotFoundException, ParseException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		int result = 0;
		List dataList = new ArrayList();
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database students");
			String SQL = "select bookName,extend,getDate,s.type from studentsnew.loan l join studentsimple s on l.userName=s.userName where s.userName='"+name+"' and l.status=1";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next())
			{
			    dataList.add(rs.getString("bookName"));
			    Calendar calendar = Calendar.getInstance();
			    Date dateOfOrder = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("getDate"));
			    calendar.setTime(dateOfOrder);
			    int extend = rs.getInt("extend");
			    calendar.add(Calendar.DAY_OF_YEAR, 14*rs.getInt("type")+7*extend);
			    Date date = calendar.getTime();
			    dataList.add(date);
			}
			
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

//delete student	
	public int delStudent(String userName) throws ClassNotFoundException
	{
		String SQL="select id from studentsnew.studentsimple where userName=\""+userName+"\"";
		Class.forName("com.mysql.cj.jdbc.Driver");
		int id=-1;
		int result = 0;
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database students");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);             
			if (rs.next()) {
			    id = rs.getInt("id");
			}
			rs.close();
			stmt.close();
			if(id==-1)
				return -1;
			else {
				SQL="delete from studentsnew.studentsimple where id=\""+id+"\"";
				PreparedStatement preparedStatement = connection.prepareStatement(SQL);
				System.out.println(preparedStatement);
				
				result = preparedStatement.executeUpdate();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//is Student?
	public boolean isStudent(String name,String pass) throws ClassNotFoundException
	{
		List dataList = new ArrayList();
		String SQL = "select * from studentsnew.studentsimple where userName='"+name+"' && pass='"+pass+"'";
		Statement stmt;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
		        System.out.println("Connected to the database students");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()) {
				dataList.add(rs.getString("userName"));
			}
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return !dataList.isEmpty();
	}

//get History by userName
	public List getHistory(String name)
	{
		List dataList = new ArrayList();
		String SQL = "select bookName,getDate from loan where userName='"+name+"'";
    	Statement stmt;
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database loan");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				System.out.println(rs.getString("bookName"));
			    dataList.add(rs.getString("bookName"));
			    dataList.add(rs.getString("getDate"));
			}
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

}



