package com.ask.servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			
			
			HttpSession session=request.getSession(false);
			if(session!=null && session.getAttribute("user")!=null && !("").equalsIgnoreCase((String)session.getAttribute("user")))
			{
				ObjectMapper mapper = new ObjectMapper();
				BusDao busDao= new BusDao();
				Bus bus = mapper.readValue(request.getParameter("busRec"), Bus.class);
				String result=busDao.addBus(bus); 
				response.getWriter().write(result);
			}
			else
			{
				response.getWriter().write("SESSIONTIMEOUT");
			}
			
			
		}
		else if("updateBus".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			HttpSession session=request.getSession(false);
			if(session!=null && session.getAttribute("user")!=null && !("").equalsIgnoreCase((String)session.getAttribute("user")))
			{
			BusDao busDao= new BusDao();
			String busNo=(String)request.getParameter("busNo");
			String depoNo=(String)request.getParameter("depoNo");
			String capacity=(String)request.getParameter("capacity");
			String fromStop=(String)request.getParameter("fromStop");
			String toStop=(String)request.getParameter("toStop");
			String result=busDao.updateBus(busNo,depoNo,capacity,fromStop,toStop);
			response.getWriter().write(result);
			}
			else
			{
				response.getWriter().write("SESSIONTIMEOUT");
			}
		}
		else if("deleteBus".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			HttpSession session=request.getSession(false);
			if(session!=null && session.getAttribute("user")!=null && !("").equalsIgnoreCase((String)session.getAttribute("user")))
			{
			BusDao busDao= new BusDao();
			String busNo=(String)request.getParameter("busNo");
			String result=busDao.deleteBus(busNo);
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
