package com.ask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ask.model.Ticket;

public class TicketDao {

	public String addTicket(Ticket tic) {
		// TODO Auto-generated method stub
		int eseq=0;
		eseq=getNextTicketIdSeq();
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		String query=" insert into tickettest(ticketNo,busNo,price,passengerId,fromStop,toStop) values(?,?,?,?,?,?) ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, eseq);
			ps.setInt(2, tic.getBusNo());
			ps.setInt(3, tic.getPrice());
			ps.setInt(4, tic.getPassengerId());
			ps.setString(5,tic.getFromStop());
			ps.setString(6, tic.getToStop());
			rs=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Ticket creation failed";
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
		
		
		return "Ticket creation successful";
	}
	
	private int getNextTicketIdSeq()
	{
		int eseq=0;
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select tickettest_seq.nextval from dual ";
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

	public List<Ticket> displayTicket(String ticketNo,String busNo, String price,String passengerId,String fromStop,String toStop) {
		// TODO Auto-generated method stub
		

		List<Ticket> ticketList = new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select ticketNo,busNo,price,passengerId,fromStop,toStop  from tickettest WHERE 1=1  ";
		if(ticketNo!=null && !("".equalsIgnoreCase(ticketNo)))
		{
			query= query +" AND ticketNo = ? ";
		}
		if(busNo!=null && !("".equalsIgnoreCase(busNo)))
		{
			query= query +" AND busNo = ? ";
		}
		if(price!=null && !("".equalsIgnoreCase(price)))
		{
			query= query +" AND price = ? ";
		}
		if(passengerId!=null && !("".equalsIgnoreCase(passengerId)) )
		{
			query= query +" AND passengerId = ? ";
		}
		if(fromStop!=null  && !("".equalsIgnoreCase(fromStop)))
		{
			query= query +" AND fromStop = ? ";
		}
		if(toStop!=null  && !("".equalsIgnoreCase(toStop)))
		{
			query= query +" AND toStop = ? ";
		}
		try {
			int count=0;
			ps=con.prepareStatement(query);
			if(ticketNo!=null && !("".equalsIgnoreCase(ticketNo)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(ticketNo));
				
			}
			if(busNo!=null && !("".equalsIgnoreCase(busNo)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(busNo));
			}
			if(price!=null && !("".equalsIgnoreCase(price)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(price));
				
			}
			if(passengerId!=null && !("".equalsIgnoreCase(passengerId)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(passengerId));
			}
			if(fromStop!=null && !("".equalsIgnoreCase(fromStop)))
			{
				count=count+1;
				ps.setString(count, fromStop);
			}
			if(toStop!=null && !("".equalsIgnoreCase(toStop)))
			{
				count=count+1;
				ps.setString(count, toStop);
			}
			rs=ps.executeQuery();
			while(rs.next())
			{
				Ticket e1=new Ticket();
				e1.setTicketNo(rs.getInt(1));
				e1.setBusNo(rs.getInt(2));
				e1.setPrice(rs.getInt(3));
				e1.setPassengerId(rs.getInt(4));
				e1.setFromStop(rs.getString(5));
			    e1.setToStop(rs.getString(6));
				ticketList.add(e1);
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
		
		return ticketList;
	
	}
	
	public String updateTicket(String ticketNo,String busNo,String passengerId,String price,String fromStop,String toStop) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(ticketNo!=null  && !("".equalsIgnoreCase(ticketNo)))
		{
			
		
			String query=" update tickettest SET ticketNo=ticketNo ";
			if(busNo!=null && !("".equalsIgnoreCase(busNo)))
			{
				query= query +" , busNo = ? ";
			}
			if(price!=null && !("".equalsIgnoreCase(price)))
			{
				query= query +" , price = ? ";
			}
			if(passengerId!=null && !("".equalsIgnoreCase(passengerId)) )
			{
				query= query +" , passengerId = ? ";
			}
			if(fromStop!=null  && !("".equalsIgnoreCase(fromStop)))
			{
				query= query +" , fromStop = ? ";
			}
			if(toStop!=null  && !("".equalsIgnoreCase(toStop)))
			{
				query= query +" , toStop = ? ";
			}
			query= query +" where ticketNo = ? ";
			try {
				int count=0;
				ps=con.prepareStatement(query);
				if(busNo!=null && !("".equalsIgnoreCase(busNo)))
				{
					count=count+1;
					ps.setInt(count, Integer.parseInt(busNo));
				}
				if(price!=null && !("".equalsIgnoreCase(price)))
				{
					count=count+1;
					ps.setInt(count, Integer.parseInt(price));
					
				}
				if(passengerId!=null && !("".equalsIgnoreCase(passengerId)))
				{
					count=count+1;
					ps.setInt(count, Integer.parseInt(passengerId));
				}
				if(fromStop!=null && !("".equalsIgnoreCase(fromStop)))
				{
					count=count+1;
					ps.setString(count, fromStop);
				}
				if(toStop!=null && !("".equalsIgnoreCase(toStop)))
				{
					count=count+1;
					ps.setString(count, toStop);
				}
				count=count+1;
				ps.setInt(count, Integer.parseInt(ticketNo));
				rs=ps.executeUpdate();
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					return "Ticket update failed";
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
			return "Select Ticket to update!!";
		}
		
		return "Ticket Update successful";
			
	}
	public String deleteTicket(String ticketNo) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(ticketNo!=null && !("".equalsIgnoreCase(ticketNo)) )
		{
			
		
			String query=" delete from tickettest where ticketNo= ? ";
				try {
					ps=con.prepareStatement(query);	
					ps.setInt(1, Integer.parseInt(ticketNo));
					rs=ps.executeUpdate();
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						return "Ticket Delete failed";
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
			return "Select Ticket to Delete!!";
		}
		
		return "Ticket Delete successful";
			
	}

}
