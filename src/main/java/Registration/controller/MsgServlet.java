package Registration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Registration.dao.MsgDao;
import Registration.dao.StudentDao;
import Registration.model.Msg;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class StudentServlet
 */
public class MsgServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	static Logger logger = null;
	private MsgDao msgDao = new MsgDao();
	private StudentDao studentDao = new StudentDao();

    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public MsgServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException
    {
    	String path = "M:\\java\\Library\\src\\main\\webapp\\WEB-INF\\classes\\log4j.properties";
    	System.out.println("path->"+path);
    	PropertyConfigurator.configure(path);
    	logger = Logger.getLogger(MsgServlet.class.getName());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
//get all msg	
		if(request.getParameter("type").equals("to see click here!")) { 
			List msgs = new ArrayList();
			try {
				msgs = msgDao.getListOfMsgs();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
	        request.setAttribute("msgs", msgs);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../msgs.jsp");
			System.out.println("LoginSucess path->"+ requestDispatcher);
			if(requestDispatcher !=null )
	            requestDispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
//send msg
		String senderName = request.getParameter("senderName");
		String text=request.getParameter("text");
		Msg msg = new Msg(senderName,text);
		try {
			msgDao.registerMsg(msg);
		} 
		catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		List dataList = new ArrayList();
		try {
		dataList = studentDao.getBooksById(senderName);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setAttribute("booksById", dataList);
		request.setAttribute("name", senderName);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../userPage.jsp");
		System.out.println("LoginSucess path->"+ requestDispatcher);
		if(requestDispatcher !=null )
            requestDispatcher.forward(request, response);
	}

}
