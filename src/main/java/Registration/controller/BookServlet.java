package Registration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Registration.dao.BookDao;
import Registration.dao.StudentDao;
import Registration.model.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class StudentServlet
 */
public class BookServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	static Logger logger = null;
	static StudentDao studentDao = new StudentDao();
	private BookDao bookDao = new BookDao();
       
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException
    {
    	String path = "M:\\java\\Library\\src\\main\\webapp\\WEB-INF\\classes\\log4j.properties";
    	System.out.println("path->"+path);
    	PropertyConfigurator.configure(path);
    	logger = Logger.getLogger(BookServlet.class.getName());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("Debug loggin");
		logger.info("Info logging");
		logger.warn("Warning logging");
		logger.error("Error logging");
		logger.fatal("fatal logging");
//find book
		Book b=null;
		try {
			b = bookDao.findBook(request.getParameter("title"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	//not exist
		if(b == null) {
			response.getWriter().append("not found");
		}
	//exist
		else {
			request.setAttribute("title", b.getTitle());
			request.setAttribute("desc", b.getDesc());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../viewBook.jsp");
			System.out.println("LoginSucess path->"+ requestDispatcher);
			if(requestDispatcher !=null )
	            requestDispatcher.forward(request, response);
				return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
//delete book
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String title = req.getParameter("title");
		try {
			bookDao.delBook(title);
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
//delete book
		if(request.getParameter("type").equals("delete")) 
		{
			doDelete(request,response);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../workerPage.jsp");
			System.out.println("LoginSucess path->"+ requestDispatcher);
			if(requestDispatcher !=null )
	            requestDispatcher.forward(request, response);
				return;
		}
//return book		
		else if(request.getParameter("type").equals("return")) 
		{
			String bookTitle = request.getParameter("title");
			String name = request.getParameter("name");
			try {
				bookDao.returnBook(bookTitle);
			} 
			catch (ClassNotFoundException e) {
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
		request.setAttribute("name", name);
	    request.setAttribute("booksById", dataList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../userPage.jsp");
		System.out.println("LoginSucess path->"+ requestDispatcher);
		if(requestDispatcher !=null )
            requestDispatcher.forward(request, response);
			return;
		}
//loan book
		else if(request.getParameter("type").equals("loan")) 
		{
			String bookTitle = request.getParameter("title");
			String name = request.getParameter("name");
			bookDao.loanBook(bookTitle,name);
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
//register book
		String title = request.getParameter("title");
		String desc=request.getParameter("desc");
		Book book = new Book(title,desc);
		
		try {
			bookDao.registerBook(book);
		} 
		catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../workerPage.jsp");
		System.out.println("LoginSucess path->"+ requestDispatcher);
		if(requestDispatcher !=null )
            requestDispatcher.forward(request, response);
	}

}
