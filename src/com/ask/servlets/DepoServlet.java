package com.ask.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ask.dao.DepoDao;
import com.ask.model.Depo;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DepoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("displayDepo".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			DepoDao depoDao= new DepoDao();
			List<Depo> depo = new ArrayList<>();
			String depoNo=(String)request.getParameter("depoNo");
			String location=(String)request.getParameter("location");
			depo=depoDao.displayDepo(depoNo,location);
			String result = mapper.writeValueAsString(depo);
			response.getWriter().write(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("addDepo".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			DepoDao depoDao= new DepoDao();
			Depo depo = mapper.readValue(request.getParameter("depoRec"), Depo.class);
			String result=depoDao.addDepo(depo);
			response.getWriter().write(result);
		}
		else if("updateDepo".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			DepoDao depoDao= new DepoDao();
			String depoNo=(String)request.getParameter("depoNo");
			String location=(String)request.getParameter("location");
			String result=depoDao.updateDepo(depoNo,location);
			response.getWriter().write(result);
		}
		else if("deleteDepo".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			DepoDao depoDao= new DepoDao();
			String depoNo=(String)request.getParameter("depoNo");
			String result=depoDao.deleteDepo(depoNo);
			response.getWriter().write(result);
		}
		else
		{
			response.getWriter().write("Invalid Service Name");
		}
	}

}
