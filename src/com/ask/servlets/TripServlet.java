package com.ask.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ask.dao.TripDao;
import com.ask.model.Trip;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TripServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("searchTrip".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			TripDao tripDao= new TripDao();
			String tripId=(String)request.getParameter("tripId");
			String tripDate=(String)request.getParameter("tripDate");
			String tripTime=(String)request.getParameter("tripTime");
			String busNo=(String)request.getParameter("busNo");
			String price=(String)request.getParameter("price");
			List<Trip> trip = new ArrayList<>();
			trip=tripDao.searchTrip(tripId,tripDate,tripTime,busNo,price);
			String result = mapper.writeValueAsString(trip);
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
		if("addTrip".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			HttpSession session=request.getSession(false);
			if(session!=null && session.getAttribute("user")!=null && !("").equalsIgnoreCase((String)session.getAttribute("user")))
			{
			ObjectMapper mapper = new ObjectMapper();
			TripDao tripDao= new TripDao();
			Trip trip = mapper.readValue(request.getParameter("tripRec"), Trip.class);
			String result=tripDao.addTrip(trip);
			response.getWriter().write(result);
		}
		else
		{
			response.getWriter().write("SESSIONTIMEOUT");
		}
		}
		
		else if("updateTrip".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			HttpSession session=request.getSession(false);
			if(session!=null && session.getAttribute("user")!=null && !("").equalsIgnoreCase((String)session.getAttribute("user")))
			{
			TripDao tripDao= new TripDao();
			String tripId=(String)request.getParameter("tripId");
			String tripDate=(String)request.getParameter("tripDate");
			String tripTime=(String)request.getParameter("tripTime");
			String busNo=(String)request.getParameter("busNo");
			String price=(String)request.getParameter("price");
			String result=tripDao.updateTrip(tripId,tripDate,tripTime,busNo,price);
			response.getWriter().write(result);
		}
		else
		{
			response.getWriter().write("SESSIONTIMEOUT");
		}
		}
		
		else if("deleteTrip".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			HttpSession session=request.getSession(false);
			if(session!=null && session.getAttribute("user")!=null && !("").equalsIgnoreCase((String)session.getAttribute("user")))
			{
			TripDao tripDao= new TripDao();
			String tripId=(String)request.getParameter("tripId");
			String result=tripDao.deleteTrip(tripId);
			response.getWriter().write(result);
		}
		else
		{
			response.getWriter().write("SESSIONTIMEOUT");
		}
		}
		else
		{
			response.getWriter().write("Invalid Service Name");
		}
	}
	
}
