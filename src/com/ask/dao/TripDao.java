package com.ask.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.ask.model.Trip;

public class TripDao {

	public String addTrip(Trip trip) {
		// TODO Auto-generated method stub
		int eseq=0;
		eseq=getNextTripIdSeq();
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		String query=" insert into triptest(tripId,tripDate,tripTime,busNo,price) values(?,to_date(?,'YYYY-MM-DD'),?,?,?) ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, eseq);
			ps.setString(2, trip.getTripDate());
			
			ps.setString(3,trip.getTripTime() );
			ps.setInt(4, trip.getBusNo());
			ps.setInt(5,trip.getPrice());
			rs=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Trip creation failed";
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
		
		
		return "Trip creation successful";
	}
	
	private int getNextTripIdSeq()
	{
		int eseq=0;
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select triptest_seq.nextval from dual ";
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

	public List<Trip> searchTrip(String tripId,String tripDate, String tripTime,String busNo,String price) 
	{
		// TODO Auto-generated method stub
		

		List<Trip> tripList = new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=" select t.tripId,to_char(t.tripDate,'YYYY-MM-DD'),t.tripTime,t.busNo,t.price,b.fromStop,b.toStop from triptest t,bustest b WHERE 1=1 AND t.busNo=b.busNo   ";
		if(tripId!=null && !("".equalsIgnoreCase(tripId)))
		{
			query= query +" AND t.tripId = ? ";
		}
		if(tripDate!=null && !("".equalsIgnoreCase(tripDate)))
		{
			query= query +" AND t.tripDate = to_date(?,'YYYY-MM-DD') ";
		}
		if(tripTime!=null && !("".equalsIgnoreCase(tripTime)) )
		{
			query= query +" AND t.tripTime = ? ";
		}
		if(busNo!=null  && !("".equalsIgnoreCase(busNo)))
		{
			query= query +" AND t.busNo = ? ";
		}
		if(price!=null  && !("".equalsIgnoreCase(price)))
		{
			query= query +" AND t.price = ? ";
		}
		try {
			int count=0;
			ps=con.prepareStatement(query);
			if(tripId!=null && !("".equalsIgnoreCase(tripId)))
			{
				count=count+1;
				ps.setString(count, tripId);
				
			}
			if(tripDate!=null && !("".equalsIgnoreCase(tripDate)))
			{
				count=count+1;
				ps.setString(count, tripDate);
			}
			if(tripTime!=null && !("".equalsIgnoreCase(tripTime)))
			{
				count=count+1;
				ps.setInt(count, Integer.parseInt(tripTime));
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
			rs=ps.executeQuery();
			while(rs.next())
			{
				Trip e1=new Trip();
				e1.setTripId(rs.getInt(1));
				e1.setTripDate(rs.getString(2));
				e1.setTripTime(rs.getString(3));
				e1.setBusNo(rs.getInt(4));
			    e1.setPrice(rs.getInt(5));
			    e1.setFromStop(rs.getString(6));
			    e1.setToStop(rs.getString(7));
			    e1.setAvailableSeats(getAvailableSeats(e1.getTripId(),e1.getBusNo()));//availableSeats int
				tripList.add(e1);
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
		
		return tripList;
	
	}
	private int getAvailableSeats(int tripId, int busNo) {
		// TODO Auto-generated method stub

	/*	Connection dbConnection = null;
		CallableStatement callableStatement = null;
		int availseats=0;

		String getAvailableSeatsSql = "{call getAvailableSeats(?,?,?)}";

		try {
			dbConnection = getConnection();
			callableStatement = dbConnection.prepareCall(getAvailableSeatsSql);

			callableStatement.setInt(1, tripId);
			callableStatement.setInt(2, busNo);
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);

			// execute getDBUSERByUserId store procedure
			callableStatement.executeUpdate();

			availseats = callableStatement.getInt(3);
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			try
			{
			if (callableStatement != null) {
				callableStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
			}
			catch(Exception e)
			{
				
			}

		}
*/
		int availseats=0;
		Connection con=getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query="select (select b.capacity from triptest t,bustest b where t.busno=b.busno and t.busno=? and t.tripid=? ) " + 
				" - (select count(tck.ticketno) from triptest t,bustest b,tickettest tck where t.busno=b.busno and t.tripid=tck.tripid and tck.status='Booked' and t.busno=? and t.tripid=?)" + 
				" from dual ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1,busNo);
			ps.setInt(2, tripId);
			ps.setInt(3,busNo);
			ps.setInt(4, tripId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				availseats=rs.getInt(1);
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

		
		
		return availseats;
	}

	public String updateTrip(String tripId,String tripDate, String tripTime,String busNo,String price)
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(tripId!=null  && !("".equalsIgnoreCase(tripId)))
		{
			
		
			String query=" update triptest SET tripId=tripId ";
			if(tripDate!=null && !("".equalsIgnoreCase(tripDate)))
			{
				query= query +" , tripDate = to_date(?,'YYYY-MM-DD') ";
			}
			if(tripTime!=null && !("".equalsIgnoreCase(tripTime)) )
			{
				query= query +" , tripTime = ? ";
			}
			if(busNo!=null  && !("".equalsIgnoreCase(busNo)))
			{
				query= query +" , busNo = ? ";
			}
			if(price!=null  && !("".equalsIgnoreCase(price)))
			{
				query= query +" , price = ? ";
			}
			query= query +" where tripId = ? ";
			try {
				int count=0;
				ps=con.prepareStatement(query);
				if(tripDate!=null && !("".equalsIgnoreCase(tripDate)))
				{
					count=count+1;
					ps.setString(count, tripDate);
				}
				if(tripTime!=null && !("".equalsIgnoreCase(tripTime)))
				{
					count=count+1;
					ps.setString(count, tripTime);
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
				count=count+1;
				ps.setInt(count, Integer.parseInt(tripId));
				rs=ps.executeUpdate();
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					return "Trip update failed";
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
			return "Select Trip to update!!";
		}
		
		return "Trip Update successful";
			
	}	
		
	public String deleteTrip(String tripId) 
	{
		// TODO Auto-generated method stub
		Connection con=getConnection();
		PreparedStatement ps=null;
		int rs=0;
		if(tripId!=null && !("".equalsIgnoreCase(tripId)) )
		{
			
		
			String query=" delete from triptest where tripId = ? ";
				try {
					ps=con.prepareStatement(query);	
					ps.setInt(1, Integer.parseInt(tripId));
					rs=ps.executeUpdate();
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						return "Trip Delete failed";
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
			return "Select Trip to Delete!!";
		}
		
		return "Trip Delete successful";
			
	}
}
