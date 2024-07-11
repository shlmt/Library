package Registration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Registration.dao.BookDao;
import Registration.dao.StudentDao;
import Registration.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = null;
	private StudentDao studentDao = new StudentDao();
     private BookDao bookDao = new BookDao();
  private static int count = 0;
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException{
    	String path = "M:\\java\\Library\\src\\main\\webapp\\WEB-INF\\classes\\log4j.properties";
    	System.out.println("path->"+path);
    	PropertyConfigurator.configure(path);
    	logger = Logger.getLogger(StudentServlet.class.getName());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Debug loggin");
		logger.info("Info logging");
		logger.warn("Warning logging");
		logger.error("Error logging");
		logger.fatal("fatal logging");
		String name = request.getParameter("name");
//see history of  user books
		if(request.getParameter("type").equals("history")) 
		{
			List dataList = new ArrayList();
			dataList = studentDao.getHistory(name);
	        request.setAttribute("books", dataList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../userHistory.jsp");
			System.out.println("LoginSucess path->"+ requestDispatcher);
			if(requestDispatcher !=null )
	            requestDispatcher.forward(request, response);
		}
//loan book
		else if(request.getParameter("type").equals("loan")) 
		{
			name = request.getParameter("name");
			List dataList = new ArrayList();
			try {
				dataList = bookDao.getListOfBooks();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	        request.setAttribute("name", name);
	        request.setAttribute("books", dataList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../loanBook.jsp");
			System.out.println("LoginSucess path->"+ requestDispatcher);
			if(requestDispatcher !=null )
	            requestDispatcher.forward(request, response);
		}
//student login
		String password = request.getParameter("password");
		try {
		//student exist
		   if(studentDao.isStudent(name,password)) 
		   {
				List dataList = new ArrayList();
				try {
				   dataList = studentDao.getBooksById(name);
				}
				catch(ClassNotFoundException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        request.setAttribute("booksById", dataList);
		        request.setAttribute("name", name);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userPage.jsp");
				System.out.println("LoginSucess path->"+ requestDispatcher);
				if(requestDispatcher !=null )
		            requestDispatcher.forward(request, response);
		   }
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	//not exist
		count++;
		if(count==3) {
			response.sendError(401, "טעית יותר מידי פעמים, חזור שוב פעם אחרת");
		}
		response.sendRedirect("../../userLogin.jsp");
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		try {
			studentDao.delStudent(userName);
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
//extend book
		if(request.getParameter("type").equals("extend")) 
		{
			String bookTitle = request.getParameter("book");
			String name = request.getParameter("name");
			try {
				bookDao.extendBook(bookTitle, name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			List dataList = new ArrayList();
			try {
			   dataList = studentDao.getBooksById(name);
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
	      request.setAttribute("booksById", dataList);
	      request.setAttribute("name", name);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../userPage.jsp");
		System.out.println("LoginSucess path->"+ requestDispatcher);
		if(requestDispatcher !=null )
            requestDispatcher.forward(request, response);
			return;
		}
//delete student
		if(request.getParameter("type").equals("delete")) 
		{
			doDelete(request,response);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../workerPage.jsp");
			System.out.println("LoginSucess path->"+ requestDispatcher);
			if(requestDispatcher !=null )
	            requestDispatcher.forward(request, response);
			return;
		}
//register student
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phoneNum = request.getParameter("phoneNum");
		int type = Integer.parseInt(request.getParameter("role"));
		
		Student student = new Student();
		student.setUserName(userName);
		student.setPassword(password);
		student.setAddress(address);
		student.setPhoneNum(phoneNum);
		student.setType(type);
			
		try {
			studentDao.registerStudent(student);
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		List dataList = new ArrayList();
		try {
		   dataList = studentDao.getListOfStudents();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
        request.setAttribute("usersData", dataList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listUsers.jsp");
		System.out.println("LoginSucess path->"+ requestDispatcher);
		if(requestDispatcher !=null )
            requestDispatcher.forward(request, response);
		
	
	}

}
