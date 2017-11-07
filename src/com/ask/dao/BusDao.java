package com.ask.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ask.model.Bus;

public class BusDao {

	public String addBus(Bus bus) {
		// TODO Auto-generated method stub
		int dseq=0;
		int rs=0;
		dseq=getNextIdSeq();
		Connection con=getConnection();
		PreparedStatement ps=null;
		String query=" insert into bustest(busNo,capacity,depoId,fromStop,toStop) values(?,?,?,?,?) ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, dseq);
			ps.setInt(2, bus.getCapacity());
			ps.setInt(3, bus.getDepoNo());
			ps.setString(4, bus.getFromStop());
			ps.setString(5, bus.getToStop());
			rs=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Bus creation failed";
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
		
		
		return "Bus creation successful";
	}
	
	private int getNextIdSeq()
	{
		int eseq=0;
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select bustest_seq.nextval from dual ";
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

	public List<Bus> displayBus(String busNo,String depoId,String capacity,String fromStop,String toStop) {
		// TODO Auto-generated method stub
		

		List<Bus> busList = new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select busNo,depoId,capacity,fromStop,toStop from bustest where 1=1  ";
		if(busNo!=null && !("".equalsIgnoreCase(busNo)) )
		{
			query= query +" AND busNo = ? ";
		}
		if(depoId!=null && !("".equalsIgnoreCase(depoId)))
		{
			query= query +" AND depoId = ? ";
		}
		if(capacity!=null && !("".equalsIgnoreCase(capacity)))
		{
			query= query +" AND capacity = ? ";
		}
		if(fromStop!=null && !("".equalsIgnoreCase(fromStop)))
		{
			query= query +" AND fromStop = ? ";
		}
		if(toStop!=null && !("".equalsIgnoreCase(toStop)))
		{
			query= query +" AND toStop = ? ";
		}
		try {
			int count=0;
			ps=con.prepareStatement(query);
			if(busNo!=null && !("".equalsIgnoreCase(busNo)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(busNo));
			}
			if(depoId!=null && !("".equalsIgnoreCase(depoId)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(depoId));
			}
			if(capacity!=null && !("".equalsIgnoreCase(capacity)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(capacity));
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
				Bus e1=new Bus();
				e1.setBusNo(rs.getInt(1));
				e1.setDepoNo(rs.getInt(2));
				e1.setCapacity(rs.getInt(3));
				e1.setFromStop(rs.getString(4));
				e1.setToStop(rs.getString(5));
				busList.add(e1);
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
		
		return busList;
	
	}
	
	
	public String updateBus(String busNo,String depoNo,String capacity,String fromStop,String toStop) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(busNo!=null  && !("".equalsIgnoreCase(busNo)))
		{
			
		
			String query=" update bustest SET busNo=busNo ";
			if(depoNo!=null && !("".equalsIgnoreCase(depoNo)))
			{
				query= query +" , depoid = ? ";
			}
			if(capacity!=null && !("".equalsIgnoreCase(capacity)))
			{
				query= query +" , capacity = ? ";
			}
			if(fromStop!=null && !("".equalsIgnoreCase(fromStop)))
			{
				query= query +" , fromStop = ? ";
			}
			if(toStop!=null && !("".equalsIgnoreCase(toStop)))
			{
				query= query +" , toStop = ? ";
			}
			query= query +" where busNo = ? ";
			try {
				int count=0;
				ps=con.prepareStatement(query);
				if(depoNo!=null && !("".equalsIgnoreCase(depoNo)))
				{
					count=count+1;
					ps.setInt(count, Integer.parseInt(depoNo));
				}
				if(capacity!=null && !("".equalsIgnoreCase(capacity)))
				{
					count=count+1;
					ps.setInt(count, Integer.parseInt(capacity));
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
				ps.setInt(count, Integer.parseInt(busNo));
				rs=ps.executeUpdate();
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					return "Bus update failed";
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
			return "Select Bus to update!!";
		}
		
		return "Bus Update successful";
			
	}
	
	
	public String deleteBus(String busNo) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(busNo!=null && !("".equalsIgnoreCase(busNo)) )
		{
			
		
			String query=" delete from bustest where busNo= ? ";
				try {
					ps=con.prepareStatement(query);	
					ps.setInt(1, Integer.parseInt(busNo));
					rs=ps.executeUpdate();
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						return "Bus Delete failed";
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
			return "Select Bus to Delete!!";
		}
		
		return "Bus Delete successful";
			
	}

}
