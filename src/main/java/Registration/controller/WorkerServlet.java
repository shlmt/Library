package Registration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Registration.dao.WorkerDao;
import Registration.model.Worker;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class WorkerServlet
 */
public class WorkerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = null;
	private WorkerDao workerDao = new WorkerDao();
       
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public WorkerServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException{
    	String path = "M:\\java\\Library\\src\\main\\webapp\\WEB-INF\\classes\\log4j.properties";
    	System.out.println("path->"+path);
    	PropertyConfigurator.configure(path);
    	logger = Logger.getLogger(WorkerServlet.class.getName());
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
//worker login
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		try {
		   if(workerDao.isWorker(name,password)) 
		   {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/workerPage.jsp");
				System.out.println("LoginSucess path->"+ requestDispatcher);
				if(requestDispatcher !=null )
		            requestDispatcher.forward(request, response);
		   }
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	//not exist
		response.sendRedirect("../../workerLogin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
//users that have books at home
		if(request.getParameter("type").equals("to see click here!")) { 
			List  users= workerDao.getUsersHome();
	        request.setAttribute("users", users);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../bookAtHome.jsp");
			System.out.println("LoginSucess path->"+ requestDispatcher);
			if(requestDispatcher !=null )
	            requestDispatcher.forward(request, response);
		}
//registrate worker
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		
		Worker worker = new Worker();
		worker.setName(name);
		worker.setPassword(password);
		worker.setPhone(phone);
			
		try {
			workerDao.registerWorker(worker);
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		List dataList = new ArrayList();
		try {
		   dataList = workerDao.getListOfWorkers();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
        request.setAttribute("workerData", dataList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listWorker.jsp");
		System.out.println("LoginSucess path->"+ requestDispatcher);
		if(requestDispatcher !=null )
            requestDispatcher.forward(request, response);
	}

}
