package com.ask.servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ask.dao.BusDao;
import com.ask.model.Bus;
import com.fasterxml.jackson.databind.ObjectMapper;



public class BusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("displayBus".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			BusDao busDao= new BusDao();
			List<Bus> bus = new ArrayList<>();
			String busNo=(String)request.getParameter("busNo");
			String depoNo=(String)request.getParameter("depoNo");
			String capacity=(String)request.getParameter("capacity");
			String fromStop=(String)request.getParameter("fromStop");
			String toStop=(String)request.getParameter("toStop");
			bus=busDao.displayBus(busNo,depoNo,capacity,fromStop,toStop);
			String result = mapper.writeValueAsString(bus);
			response.getWriter().write(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("addBus".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			BusDao busDao= new BusDao();
			Bus bus = mapper.readValue(request.getParameter("busRec"), Bus.class);
			String result=busDao.addBus(bus); 
			response.getWriter().write(result);
		}
		
		else
		{
			response.getWriter().write("Invalid Service Name");
		}
	}

}