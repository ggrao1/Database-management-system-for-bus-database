package com.ask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ask.model.Employee;

public class EmployeeDao {

	public String addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		int eseq=0;
		eseq=getNextEmpIdSeq();
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		String query=" insert into emptest(empId,empName,empDob,empsex,busNo,empsalary) values(?,?,to_date(?,'YYYY-MM-DD'),?,?,?) ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, eseq);
			ps.setString(2, emp.getEmpName());
			ps.setString(3, emp.getEmpDob());
			ps.setString(4, emp.getEmpSex());
			ps.setInt(5,emp.getBusNo());
			ps.setInt(6, emp.getEmpSalary());
			rs=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Employee creation failed";
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e){
				
			}
		}
		
		
		return "Employee creation successful";
	}
	
	private int getNextEmpIdSeq()
	{
		int eseq=0;
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select emptest_seq.nextval from dual ";
		try {
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next())
			{
				eseq=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e){
				
			}
		}
		
		return eseq;
	}
	
	private Connection getConnection()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		  
		//step2 create  the connection object  
		Connection con = null;
		try {
			con = DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","system","grao123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return con;
	}

	public List<Employee> searchEmployee(String empId,String empName, String empDob,String empSex,String busNo,String empSalary) 
	{
		// TODO Auto-generated method stub
		

		List<Employee> empList = new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select empId,empName,to_char(empDob,'YYYY-MM-DD'),empSex,busNo,empSalary  from emptest WHERE 1=1  ";
		if(empName!=null && !("".equalsIgnoreCase(empName)))
		{
			query= query +" AND empName = ? ";
		}
		if(empDob!=null && !("".equalsIgnoreCase(empDob)))
		{
			query= query +" AND empDob = to_date(?,'YYYY-MM-DD') ";
		}
		if(empSex!=null && !("".equalsIgnoreCase(empSex)))
		{
			query= query +" AND empSex = ? ";
		}
		if(empSalary!=null && !("".equalsIgnoreCase(empSalary)) )
		{
			query= query +" AND empSalary = ? ";
		}
		if(empId!=null  && !("".equalsIgnoreCase(empId)))
		{
			query= query +" AND empId = ? ";
		}
		if(busNo!=null  && !("".equalsIgnoreCase(busNo)))
		{
			query= query +" AND busNo = ? ";
		}
		try {
			int count=0;
			ps=con.prepareStatement(query);
			if(empName!=null && !("".equalsIgnoreCase(empName)))
			{
				count=count+1;
				ps.setString(count, empName);
				
			}
			if(empDob!=null && !("".equalsIgnoreCase(empDob)))
			{
				count=count+1;
				ps.setString(count, empDob);
			}
			if(empSex!=null && !("".equalsIgnoreCase(empSex)))
			{
				count=count+1;
				ps.setString(count, empSex);
				
			}
			if(empSalary!=null && !("".equalsIgnoreCase(empSalary)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(empSalary));
			}
			if(empId!=null && !("".equalsIgnoreCase(empId)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(empId));
			}
			if(busNo!=null && !("".equalsIgnoreCase(busNo)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(busNo));
			}
			rs=ps.executeQuery();
			while(rs.next())
			{
				Employee e1=new Employee();
				e1.setEmpId(rs.getInt(1));
				e1.setEmpName(rs.getString(2));
				e1.setEmpDob(rs.getString(3));
				e1.setEmpSex(rs.getString(4));
				e1.setBusNo(rs.getInt(5));
			    e1.setEmpSalary(rs.getInt(6));
				empList.add(e1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e){
				
			}
		}
		
		return empList;
	
	}
	public String updateEmployee(String empId,String empName,String empDob,String empSex,String busNo,String empSalary) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(empId!=null  && !("".equalsIgnoreCase(empId)))
		{
			
		
			String query=" update emptest SET empId=empId ";
			if(empName!=null && !("".equalsIgnoreCase(empName)))
			{
				query= query +" , empName = ? ";
			}
			if(empDob!=null && !("".equalsIgnoreCase(empDob)))
			{
				query= query +" , empDob = to_date(?,'YYYY-MM-DD') ";
			}
			if(empSex!=null && !("".equalsIgnoreCase(empSex)))
			{
				query= query +" , empSex = ? ";
			}
			if(empSalary!=null && !("".equalsIgnoreCase(empSalary)) )
			{
				query= query +" , empSalary = ? ";
			}
			if(busNo!=null  && !("".equalsIgnoreCase(busNo)))
			{
				query= query +" , busNo = ? ";
			}
			query= query +" where empId = ? ";
			try {
				int count=0;
				ps=con.prepareStatement(query);
				if(empName!=null && !("".equalsIgnoreCase(empName)))
				{
					count=count+1;
					ps.setString(count, empName);
				}
				if(empDob!=null && !("".equalsIgnoreCase(empDob)))
				{
					count=count+1;
					ps.setString(count, empDob);
				}
				if(empSex!=null && !("".equalsIgnoreCase(empSex)))
				{
					count=count+1;
					ps.setString(count, empSex);	
				}
				if(empSalary!=null && !("".equalsIgnoreCase(empSalary)))
				{
					count=count+1;
					ps.setInt(count, Integer.parseInt(empSalary));
				}
				if(busNo!=null && !("".equalsIgnoreCase(busNo)))
				{
					count=count+1;
					ps.setInt(count, Integer.parseInt(busNo));
				}
				count=count+1;
				ps.setInt(count, Integer.parseInt(empId));
				rs=ps.executeUpdate();
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					return "Employee update failed";
				}
				finally
				{
					try
					{
						if(ps!=null)
							ps.close();
						if(con!=null)
							con.close();
					}
					catch(Exception e){
				
					}
				}
				
			}
		else {
			return "Select Employee to update!!";
		}
		
		return "Employee Update successful";
			
	}	
		
	public String deleteEmployee(String empId) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(empId!=null && !("".equalsIgnoreCase(empId)) )
		{
			
		
			String query=" delete from emptest where empId = ? ";
				try {
					ps=con.prepareStatement(query);	
					ps.setInt(1, Integer.parseInt(empId));
					rs=ps.executeUpdate();
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						return "Employee Delete failed";
					}
					finally
					{
						try
						{
							if(ps!=null)
								ps.close();
							if(con!=null)
								con.close();
						}
						catch(Exception e){
					
						}
					}
				
			}
		
				
			
		else {
			return "Select Employee to Delete!!";
		}
		
		return "Employee Delete successful";
			
	}
}
