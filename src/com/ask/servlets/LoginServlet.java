package com.ask.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ask.dao.LoginDao;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if("checkUserPageAccess".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			HttpSession session=request.getSession(false);
			if(session!=null && session.getAttribute("user")!=null && !("").equalsIgnoreCase((String)session.getAttribute("user")))
			{
				response.getWriter().write("SUCCESS");
			}
			else
			{
				response.getWriter().write("ERROR");
			}
			
		}
		else if("logout".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			HttpSession session=request.getSession(false);
			if(session!=null && session.getAttribute("user")!=null && !("").equalsIgnoreCase((String)session.getAttribute("user")))
			{
				session.invalidate();
				response.getWriter().write("SUCCESS");
			}
			else
			{
				response.getWriter().write("ERROR");
			}
			
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if("logIn".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			LoginDao loginDao= new LoginDao();
			String loginResult = "ERROR";
			String username =(String)request.getParameter("username");
			String password =(String)request.getParameter("password");
			loginResult=loginDao.login(username,password);
			//response.getWriter().write(loginResult);
			if("SUCCESS".equals(loginResult))
			{
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(15*60);
				String encodedURL = response.encodeRedirectURL(request.getContextPath() + "/index1.html");
				System.out.println(encodedURL); 
				//response.sendRedirect(encodedURL);
				response.getWriter().write(encodedURL);
			}
			else
			{
				/*RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
				PrintWriter out= response.getWriter();
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(request, response);*/
				response.getWriter().write(loginResult);
			}
			
			
		}
		
		
		
		
	}

}
