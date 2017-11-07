package com.ask.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ask.dao.EmployeeDao;
import com.ask.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class EmployeeServlet
 */

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("searchEmployee".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			EmployeeDao empDao= new EmployeeDao();
			String empId=(String)request.getParameter("empId");
			String empName=(String)request.getParameter("empName");
			String empDob=(String)request.getParameter("empDob");
			String empSex=(String)request.getParameter("empSex");
			String busNo=(String)request.getParameter("busNo");
			String empSalary=(String)request.getParameter("empSalary");
			List<Employee> emp = new ArrayList<>();
			emp=empDao.searchEmployee(empId,empName,empDob,empSex,busNo,empSalary);
			String result = mapper.writeValueAsString(emp);
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
		if("addEmployee".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			ObjectMapper mapper = new ObjectMapper();
			EmployeeDao empDao= new EmployeeDao();
			Employee emp = mapper.readValue(request.getParameter("empRec"), Employee.class);
			String result=empDao.addEmployee(emp);
			response.getWriter().write(result);
		}
		
		else if("updateEmployee".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			EmployeeDao empDao= new EmployeeDao();
			String empId=(String)request.getParameter("empId");
			String empName=(String)request.getParameter("empName");
			String empDob=(String)request.getParameter("empDob");
			String empSex=(String)request.getParameter("empSex");
			String busNo=(String)request.getParameter("busNo");
			String empSalary=(String)request.getParameter("empSalary");
			String result=empDao.updateEmployee(empId,empName,empDob,empSex,busNo,empSalary);
			response.getWriter().write(result);
		}
		
		else if("deleteEmployee".equalsIgnoreCase(request.getParameter("serviceName")))
		{
			EmployeeDao empDao= new EmployeeDao();
			String empId=(String)request.getParameter("empId");
			String result=empDao.deleteEmployee(empId);
			response.getWriter().write(result);
		}
		else
		{
			response.getWriter().write("Invalid Service Name");
		}
	}
	
}
