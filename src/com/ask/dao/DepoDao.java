package com.ask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ask.model.Depo;

public class DepoDao {

	public String addDepo(Depo depo) {
		// TODO Auto-generated method stub
		int dseq=0;
		int rs=0;
		dseq=getNextDepoIdSeq();
		Connection con=getConnection();
		PreparedStatement ps=null;
		String query=" insert into depotest(depoid,location) values(?,?) ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, dseq);
			ps.setString(2, depo.getLocation());
			rs=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Depo creation failed";
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
		
		
		return "Depo creation successful";
	}
	
	private int getNextDepoIdSeq()
	{
		int eseq=0;
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select depotest_seq.nextval from dual ";
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

	public List<Depo> displayDepo(String depoNo,String location) {
		// TODO Auto-generated method stub
		

		List<Depo> depoList = new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select depoId,location  from depotest where 1=1  ";
		if(depoNo!=null && !("".equalsIgnoreCase(depoNo)) )
		{
			query= query +" AND depoId = ? ";
		}
		if(location!=null && !("".equalsIgnoreCase(location)))
		{
			query= query +" AND location = ? ";
		}
		try {
			int count=0;
			ps=con.prepareStatement(query);
			if(depoNo!=null && !("".equalsIgnoreCase(depoNo)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(depoNo));
			}
			if(location!=null && !("".equalsIgnoreCase(location)))
			{
				count=count+1;
				ps.setString(count, location);
			}
			
			rs=ps.executeQuery();
			while(rs.next())
			{
				Depo e1=new Depo();
				e1.setDepoNo(rs.getInt(1));
				e1.setLocation(rs.getString(2));
				depoList.add(e1);
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
		
		return depoList;
	
	}
	
	public String updateDepo(String depoNo,String location) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(depoNo!=null && !("".equalsIgnoreCase(depoNo)) )
		{
			
		
			String query="";
			
			if(location!=null && !("".equalsIgnoreCase(location)))
			{
				if("".equalsIgnoreCase(query))
				{
					query=query+" update depotest set location = ? ";
				}
				else
				{
					query= query +" , location = ? ";
				}
				
			}
			
			if(!("".equalsIgnoreCase(query)))
				
			{
				
				
				query= query +" where depoId= ? ";
				try {
					int count=0;
					ps=con.prepareStatement(query);	
					if(location!=null && !("".equalsIgnoreCase(location)))
					{
						count=count+1;
						ps.setString(count, location);
					}
					count=count+1;
					ps.setInt(count, Integer.parseInt(depoNo));
					rs=ps.executeUpdate();
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						return "Depot update failed";
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
		
				
			}
		else {
			return "Select Depot to update!!";
		}
		
		return "Depot Update successful";
			
	}

	
	public String deleteDepo(String depoNo) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(depoNo!=null && !("".equalsIgnoreCase(depoNo)) )
		{
			
		
			String query=" delete from depotest where depoId= ? ";
				try {
					ps=con.prepareStatement(query);	
					ps.setInt(1, Integer.parseInt(depoNo));
					rs=ps.executeUpdate();
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						return "Depot Delete failed";
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
			return "Select Depot to Delete!!";
		}
		
		return "Depot Delete successful";
			
	}
}
