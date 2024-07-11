package Registration.dao;


import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import Registration.model.Msg;


public class MsgDao {
	
	public String CONNECTION_STR = "jdbc:mysql://localhost:3306/studentsnew?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//register msg	
	public int registerMsg(Msg msg) throws ClassNotFoundException
	{
     String INSERT_STUDENT_SQL = "INSERT INTO msgs(senderName,text)"+
     " VALUES (?,?);";     

		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) {
	            System.out.println("Connected to the database msgs");
	        }
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);
			preparedStatement.setString(1,msg.getSenderName());
			preparedStatement.setString(2,msg.getText());
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	 
//get List Of Msgs
	public List getListOfMsgs() throws ClassNotFoundException
	{
		List dataList = new ArrayList();
		
		String SQL = "select senderName, text from Msgs";
    	Statement stmt;
		Class.forName("com.mysql.cj.jdbc.Driver");
			
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) {
	            System.out.println("Connected to the database msgs");
	        }
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
			    dataList.add(rs.getString("senderName"));
			    dataList.add(rs.getString("text"));
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


