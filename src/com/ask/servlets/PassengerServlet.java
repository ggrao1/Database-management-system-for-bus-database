package com.ask.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ask.dao.EmployeeDao;
import com.ask.dao.PassengerDao;
import com.ask.model.Passenger;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Servlet implementation class PassengerServlet
 */

public class PassengerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassengerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("searchPassenger".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			PassengerDao passengerDao= new PassengerDao();
			String passengerId=(String)request.getParameter("passengerId");
			String name=(String)request.getParameter("name");
			String phone=(String)request.getParameter("phone");
			String age=(String)request.getParameter("age");
			List<Passenger> passenger = new ArrayList<>();
			passenger=passengerDao.searchPassenger(passengerId,name,phone,age);
			String result = mapper.writeValueAsString(passenger);
			response.getWriter().write(result);
		}
		else
		{
			response.getWriter().write("Invalid Service Name");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("addPassenger".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			PassengerDao passDao= new PassengerDao();
			Passenger pass = mapper.readValue(request.getParameter("passRec"), Passenger.class);
			String result=passDao.addPassenger(pass);
			response.getWriter().write(result);
		}
		else if("updatePassenger".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			PassengerDao passengerDao= new PassengerDao();
			String passengerId=(String)request.getParameter("passengerId");
			String name=(String)request.getParameter("name");
			String phone=(String)request.getParameter("phone");
			String age=(String)request.getParameter("age");
			String result=passengerDao.updatePassenger(passengerId,name,phone,age);
			response.getWriter().write(result);
		}
		else
		{
			response.getWriter().write("Invalid Service Name");
		}
	}

}
