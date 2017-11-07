package com.ask.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ask.dao.LoginDao;
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
			LoginDao loginDao= new LoginDao();
			String loginResult = "ERROR";
			String username =(String)request.getParameter("username");
			String password =(String)request.getParameter("password");
			loginResult=loginDao.login(username,password);
			response.getWriter().write(loginResult);
		}
	}

}
