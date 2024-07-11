package Registration.dao;


import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Registration.model.Worker;


public class WorkerDao {
	
	public String CONNECTION_STR = "jdbc:mysql://localhost:3306/studentsnew?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
//register Worker
	public int registerWorker(Worker worker) throws ClassNotFoundException
	{
	     String INSERT_WORKER_SQL = "INSERT INTO workers(name,password,phone)"+
	     " VALUES (?,?,?);";
	
		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database workers");
			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORKER_SQL);
			preparedStatement.setString(1,worker.getName());
			preparedStatement.setString(2,worker.getPassword());
			preparedStatement.setString(3,worker.getPhone());
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//List Of Workers
	public List getListOfWorkers() throws ClassNotFoundException
	{
		List dataList = new ArrayList();
		String SQL = "select * from workers";
    	Statement stmt;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database workers");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
			    System.out.println(rs.getString("name") + " - " + rs.getString("password"));
			    dataList.add(rs.getString("name"));
			    dataList.add(rs.getString("password"));
			}
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

//is Worker?
	public boolean isWorker(String name,String pass) throws ClassNotFoundException
	{
		List dataList = new ArrayList();
		String SQL = "select * from studentsnew.workers where name='"+name+"' && password='"+pass+"'";
		Statement stmt;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database workers");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()) {
				dataList.add(rs.getString("name"));
			}
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		 }
		return !dataList.isEmpty();
	}
	
//Users have book at Home
	public List getUsersHome()
	{
		List dataList = new ArrayList();
		String SQL = "SELECT distinct userName from loan where status=1";
    	Statement stmt;
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database loan");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
			    dataList.add(rs.getString("userName"));
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


