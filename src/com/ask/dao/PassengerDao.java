package com.ask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ask.model.Passenger;

public class PassengerDao {

	public String addPassenger(Passenger pass) {
		// TODO Auto-generated method stub
		int eseq=0;
		eseq=getNextPassengerIdSeq();
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		String query=" insert into passengertest(passengerId,name,phone,age) values(?,?,?,?) ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, eseq);
			ps.setString(2, pass.getName());
			ps.setString(3, pass.getPhone());
			ps.setInt(4, pass.getAge());
			rs=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Passenger creation failed";
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
		
		
		return "Passenger creation successful";
	}
	
	private int getNextPassengerIdSeq()
	{
		int eseq=0;
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select passengertest_seq.nextval from dual ";
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

	public List<Passenger> searchPassenger(String passengerId,String name, String phone,String age) {
		// TODO Auto-generated method stub
		

		List<Passenger> passList = new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select passengerId,name,phone,age  from passengertest WHERE 1=1  ";
		if(passengerId!=null && !("".equalsIgnoreCase(passengerId)))
		{
			query= query +" AND passengerId = ? ";
		}
		if(name!=null && !("".equalsIgnoreCase(name)))
		{
			query= query +" AND name = ? ";
		}
		if(phone!=null && !("".equalsIgnoreCase(phone)) )
		{
			query= query +" AND phone = ? ";
		}
		if(age!=null  && !("".equalsIgnoreCase(age)))
		{
			query= query +" AND age = ? ";
		}
		try {
			int count=0;
			ps=con.prepareStatement(query);
			if(passengerId!=null && !("".equalsIgnoreCase(passengerId)))
			{
				count=count+1;
				ps.setString(count, passengerId);
				
			}
			if(name!=null && !("".equalsIgnoreCase(name)))
			{
				count=count+1;
				ps.setString(count, name);
			}
			if(phone!=null && !("".equalsIgnoreCase(phone)))
			{
				count=count+1;
				ps.setString(count, phone);
				
			}
			if(age!=null && !("".equalsIgnoreCase(age)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(age));
			}
			rs=ps.executeQuery();
			while(rs.next())
			{
				Passenger e1=new Passenger();
				e1.setPassengerId(rs.getInt(1));
				e1.setName(rs.getString(2));
				e1.setPhone(rs.getString(3));
				e1.setAge(rs.getInt(4));
				passList.add(e1);
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
		
		return passList;
	
	}

}
