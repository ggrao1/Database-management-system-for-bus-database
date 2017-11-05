package com.ask.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ask.dao.LoginDao;
import com.ask.model.Login;
import com.fasterxml.jackson.databind.ObjectMapper;



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
		if("logIn".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			LoginDao depoDao= new LoginDao();
			List<Login> depo = new ArrayList<>();
			String username =(String)request.getParameter("username");
			String password =(String)request.getParameter("password");
			depo=depoDao.displayDepo(username,password);
			String result = mapper.writeValueAsString(depo);
			response.getWriter().write(result);
		}
	}

}
