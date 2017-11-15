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
		String query=" insert into tickettest(ticketNo,passengerId,tripId,status) values(?,?,?,?) ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, eseq);
			ps.setInt(2, tic.getPassengerId());
			ps.setInt(3, tic.getTripId());
			ps.setString(4,tic.getStatus());
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

	public List<Ticket> displayTicket(String ticketNo,String passengerId,String tripId,String status) {
		// TODO Auto-generated method stub
		

		List<Ticket> ticketList = new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select ticketNo,passengerId,tripId,status  from tickettest WHERE 1=1  ";
		if(ticketNo!=null && !("".equalsIgnoreCase(ticketNo)))
		{
			query= query +" AND ticketNo = ? ";
		}
		if(passengerId!=null && !("".equalsIgnoreCase(passengerId)) )
		{
			query= query +" AND passengerId = ? ";
		}
		if(tripId!=null  && !("".equalsIgnoreCase(tripId)))
		{
			query= query +" AND tripId = ? ";
		}
		if(status!=null  && !("".equalsIgnoreCase(status)))
		{
			query= query +" AND status = ? ";
		}
		try {
			int count=0;
			ps=con.prepareStatement(query);
			if(ticketNo!=null && !("".equalsIgnoreCase(ticketNo)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(ticketNo));
				
			}
			if(passengerId!=null && !("".equalsIgnoreCase(passengerId)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(passengerId));
			}
			if(tripId!=null && !("".equalsIgnoreCase(tripId)))
			{
				count=count+1;
				ps.setString(count, tripId);
			}
			if(status!=null && !("".equalsIgnoreCase(status)))
			{
				count=count+1;
				ps.setString(count, status);
			}
			rs=ps.executeQuery();
			while(rs.next())
			{
				Ticket e1=new Ticket();
				e1.setTicketNo(rs.getInt(1));
				e1.setPassengerId(rs.getInt(2));
				e1.setTripId(rs.getInt(3));
				e1.setStatus(rs.getString(4));
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
	
	public String updateTicket(String ticketNo) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		String status="Cancelled";
		if(ticketNo!=null  && !("".equalsIgnoreCase(ticketNo)))
		{
			
		
			String query=" update tickettest SET ticketNo=ticketNo ";
			if(status!=null  && !("".equalsIgnoreCase(status)))
			{
				query= query +" , status = ? ";
			}
			query= query +" where ticketNo = ? ";
			try {
				int count=0;
				ps=con.prepareStatement(query);
				if(status!=null && !("".equalsIgnoreCase(status)))
				{
					count=count+1;
					ps.setString(count, status);
				}
				count=count+1;
				ps.setInt(count, Integer.parseInt(ticketNo));
				rs=ps.executeUpdate();
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					return "Ticket Cancelation failed";
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
			return "Select Ticket to Cancel!!";
		}
		
		return "Ticket Cancelled successful";
			
	}
	/*public String deleteTicket(String ticketNo) 
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
			
	}*/

}
