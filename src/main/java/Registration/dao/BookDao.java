package Registration.dao;


import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.crypto.Data;

import Registration.model.Book;


public class BookDao {
	
	
	public String CONNECTION_STR = "jdbc:mysql://localhost:3306/studentsnew?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

//register book
	public int registerBook(Book book) throws ClassNotFoundException
	{
     String INSERT_STUDENT_SQL = "INSERT INTO books(title,descr)"+
     " VALUES (?,?);";
		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
		if (connection != null) {
            System.out.println("Connected to the database books");
        }
		             
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);
		preparedStatement.setString(1,book.getTitle());
		preparedStatement.setString(2,book.getDesc());
		System.out.println(preparedStatement);
		result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//delete book
	public int delBook(String title) throws ClassNotFoundException 
	{
		String SQL="select id from studentsnew.books where title=\""+title+"\"";
		Class.forName("com.mysql.cj.jdbc.Driver");
		int id=-1;
		int result = 0;
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) {
	            System.out.println("Connected to the database books");
	        }
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
				SQL="delete from studentsnew.books where id=\""+id+"\"";
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
	
//get list of books
	public List getListOfBooks() throws ClassNotFoundException{
		List dataList = new ArrayList();
		String SQL = "select title, descr from Books";
    	Statement stmt;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) {
	            System.out.println("Connected to the database books");
	        }
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
			    dataList.add(rs.getString("title"));
			    dataList.add(rs.getString("descr"));
			}
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
//find book
	public Book findBook(String title) throws ClassNotFoundException
	{
		Book b = null;
		String SQL = "select title, descr from Books where title='"+title+"'";
    	Statement stmt;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) {
	            System.out.println("Connected to the database books");
	        }
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			if (rs.next()) {
			     b = new Book(rs.getString("title"),rs.getString("descr"));
			}
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
//return book
	public int returnBook(String title) throws ClassNotFoundException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");

		int result = 0;
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) {
	            System.out.println("Connected to the database books");
	        }
			String SQL="update studentsnew.loan set status=0 where bookName='"+title+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(SQL);
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
//loan book
	public int loanBook(String title, String name){
		int result = 0;
		int status = -1;
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) {
	            System.out.println("Connected to the database books");
	        }
		//האם קיים כזה ספר
			String SQL = "select id from books where title='"+title+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			if (!rs.next()) {
				rs.close();
				stmt.close();
			    return -1;
			}
			rs.close();
			stmt.close();
		//האם הוא מושאל כבר
			SQL = "select status from loan where bookName='"+title+"'";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(SQL);
			if (rs.next()) {
			    status = rs.getInt("status");
			}
			rs.close();
			stmt.close();
			if(status==1) {
				return -1;
			}
		//השאלה בפועל
			SQL="INSERT INTO loan(userName,bookName,getDate,status)"+" VALUES (?,?,?,?);";
	
			PreparedStatement preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,title);
			preparedStatement.setDate(3, new Date(Calendar.getInstance().getTime().getTime()));
			preparedStatement.setInt(4,1);
			
			System.out.println(preparedStatement);
			
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	
//extendBook
	public int extendBook(String title, String name) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		int result = 0;
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_STR,"root","1234");
			if (connection != null) 
	            System.out.println("Connected to the database loan");
			String SQL="update studentsnew.loan set extend=extend+1 where bookName='"+title+"' and userName='"+name+"' and extend!=2";
			PreparedStatement preparedStatement = connection.prepareStatement(SQL);
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}


