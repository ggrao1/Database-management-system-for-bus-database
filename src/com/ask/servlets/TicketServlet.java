package com.ask.servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ask.dao.TicketDao;
import com.ask.model.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;



public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("displayTicket".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			TicketDao ticketDao= new TicketDao();
			List<Ticket> ticket = new ArrayList<>();
			String ticketNo=(String)request.getParameter("ticketNo");
			String busNo=(String)request.getParameter("busNo");
			String passengerId=(String)request.getParameter("passengerId");
			String price=(String)request.getParameter("price");
			String fromStop=(String)request.getParameter("fromStop");
			String toStop=(String)request.getParameter("toStop");
			ticket=ticketDao.displayTicket(ticketNo,busNo,price,passengerId,fromStop,toStop);
			String result = mapper.writeValueAsString(ticket);
			response.getWriter().write(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("addTicket".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			TicketDao ticketDao= new TicketDao();
			Ticket ticket = mapper.readValue(request.getParameter("ticketRec"), Ticket.class);
			String result=ticketDao.addTicket(ticket); 
			response.getWriter().write(result);
		}
		
		else
		{
			response.getWriter().write("Invalid Service Name");
		}
	}

}
