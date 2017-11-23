var trip1;
function saveTrip()
{

var tripDate=document.getElementById("tripDate").value;
var tripTime=document.getElementById("tripTime").value;
var busNo=document.getElementById("busNo").value;
var price=document.getElementById("price").value;


var tripObj={
		"tripDate":tripDate,"tripTime":tripTime,"busNo":busNo,"price":price
};


$.ajax({
	  url: "TripServlet",
	  method: "POST",
	  data: { serviceName: "addTrip",tripRec:JSON.stringify(tripObj) },
		success:function(data) {
			if(data.indexOf("SESSIONTIMEOUT")!=-1)
			{
				alert("Session Timeout!! Redirecting to login page");
				window.location="index.html";
			}
			else if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="addTrip.html";
				}
			else
				{
				alert("Save failed");
				}
		 },
		 error:function(err){
			 alert(err);
		 }

	});

}  


function searchtrip9()
{
	var tripId=document.getElementById("tripId").value;
	var tripDate=document.getElementById("tripDate").value;
	var tripTime=document.getElementById("tripTime").value;
	var busNo=document.getElementById("busNo").value;
	var price=document.getElementById("price").value;
	
	if(tripId!=null && tripId!=undefined && tripId!="" && tripId.trim()!="")
	{
		var chkrslt=isNaN(tripId);
		if(chkrslt==true)
			{
			alert("Trip Id cant be in text format. Enter valid number");
			}
		  
	}
	if(busNo!=null && busNo!=undefined && busNo!="" && busNo.trim()!="")
	{
		var chkrslt=isNaN(busNo);
		if(chkrslt==true)
			{
			alert("bus no cant be in text format. Enter valid number");
			}
		  
	}
	if(price!=null && price!=undefined && price!="" && price.trim()!="")
	{
		var chkrslt=isNaN(price);
		if(chkrslt==true)
			{
			alert("Price cant be in text format. Enter valid number");
			}
	}


$.ajax({
	  url: "TripServlet",
	  method: "GET",
	  data: { serviceName: "searchTrip",tripId:tripId,tripDate:tripDate,tripTime:tripTime,busNo:busNo,price:price },
		success:function(data) {

				$("#tripresultTable").empty();
				$("#tripresultTable").append( "<th> Trip Id </th><th> Trip Date </th><th> Trip Time </th><th> Bus No. </th><th> Price </th><th> From Stop </th><th> To Stop </th><th> Available Seats </th><th>Action</th>");
					var tripArray=JSON.parse(data);
					for(i=0;i<tripArray.length;i++)
					{
						var trip=tripArray[i];
						if(trip.availableSeats>0){
						$("#tripresultTable").append('<tr><td>'+"TP-"+trip.tripId+'</td><td>'+trip.tripDate+'</td><td>'+trip.tripTime+'</td><td>'+"B-"+trip.busNo+'</td><td>'+trip.price+'</td><td>'+trip.fromStop+'</td><td>'+trip.toStop+'</td><td>'+trip.availableSeats+'</td><td><button class="w3-button w3-grey" id="bookticketBtn" onclick="bookTicket('+trip.tripId+','+trip.busNo+')">Book</button></td></tr>');
						}
						else{
							$("#tripresultTable").append('<tr><td>'+"TP-"+trip.tripId+'</td><td>'+trip.tripDate+'</td><td>'+trip.tripTime+'</td><td>'+"B-"+trip.busNo+'</td><td>'+trip.price+'</td><td>'+trip.fromStop+'</td><td>'+trip.toStop+'</td><td>0</td><td><b style="color:red;">FULL</b></td></tr>');
						}
					}
		 },
		 error:function(err){
			 alert(err);
		 }

	});
} 

function searchtrip()
{
	var tripId=document.getElementById("tripId").value;
	var tripDate=document.getElementById("tripDate").value;
	var tripTime=document.getElementById("tripTime").value;
	var busNo=document.getElementById("busNo").value;
	var price=document.getElementById("price").value;
	
	if(tripId!=null && tripId!=undefined && tripId!="" && tripId.trim()!="")
	{
		var chkrslt=isNaN(tripId);
		if(chkrslt==true)
			{
			alert("Trip Id cant be in text format. Enter valid number");
			}
		  
	}
	if(busNo!=null && busNo!=undefined && busNo!="" && busNo.trim()!="")
	{
		var chkrslt=isNaN(busNo);
		if(chkrslt==true)
			{
			alert("bus no cant be in text format. Enter valid number");
			}
		  
	}
	if(price!=null && price!=undefined && price!="" && price.trim()!="")
	{
		var chkrslt=isNaN(price);
		if(chkrslt==true)
			{
			alert("Price cant be in text format. Enter valid number");
			}
	}


$.ajax({
	  url: "TripServlet",
	  method: "GET",
	  data: { serviceName: "searchTrip",tripId:tripId,tripDate:tripDate,tripTime:tripTime,busNo:busNo,price:price },
		success:function(data) {

				$("#tripresultTable").empty();
				$("#tripresultTable").append( "<th>Trip Id</th><th>Trip Date</th><th>Trip Time</th><th>Bus No.</th><th>Price</th>");
					var tripArray=JSON.parse(data);
					for(i=0;i<tripArray.length;i++)
					{
						var trip=tripArray[i];
						$("#tripresultTable").append('<tr><td>'+"TP-"+trip.tripId+'</td><td>'+trip.tripDate+'</td><td>'+trip.tripTime+'</td><td>'+"B-"+trip.busNo+'</td><td>'+trip.price+'</td></tr>');
					}

		 },
		 error:function(err){
			 alert(err);
		 }

	});
} 

function displayTrip()
{
$.ajax({
	  url: "TripServlet",
	  method: "GET",
	  data: { serviceName: "searchTrip" },
		success:function(data) {

				$("#tripdiv").empty();
				$("#tripdiv").append("<select id=\"tripId\"></select>");
				$("#tripId").append("<option value=\"\">Select TripId</option>");
					var tripArray=JSON.parse(data);
					for(i=0;i<tripArray.length;i++)
					{
						var trip=tripArray[i];
						$("#tripId").append("<option value=\""+trip.tripId+"\">TP-"+trip.tripId+"</option>");
					}
		 },
		 error:function(err){
			 alert(err);
		 }

	});
}

function displayTrip5()
{
$.ajax({
	  url: "TripServlet",
	  method: "GET",
	  data: { serviceName: "searchTrip" },
		success:function(data) {

				$("#tripdiv").empty();
				$("#tripdiv").append("<select id=\"tripId\"></select>");
				$("#a1").append("| TripId | Trip Date | Trip Time | Bus No. | Price |")
				$("#tripId").append("<option value=\"\">Select Trip..</option>");
					var tripArray=JSON.parse(data);
					for(i=0;i<tripArray.length;i++)
					{
						var trip=tripArray[i];
						$("#tripId").append("<option value=\""+trip.tripId+"\">| TP-"+trip.tripId+" | "+trip.tripDate+" | "+trip.tripTime+"  | B-"+trip.busNo+" | "+trip.price+" | </option>");
					}
		 },
		 error:function(err){
			 alert(err);
		 }

	});
}

function updateTrip()
{
var tripId=document.getElementById("tripId").value;;
var tripDate=document.getElementById("tripDate").value;;
var tripTime=document.getElementById("tripTime").value;
var busNo=document.getElementById("busNo").value;
var price=document.getElementById("price").value;

if(tripId!=null && tripId!=undefined && tripId!="" && tripId.trim()!="")
{
	var chkrslt=isNaN(tripId);
	if(chkrslt==true)
		{
		alert("Trip Id cant be in text format. Enter valid number");
		}
	  
}
if(busNo!=null && busNo!=undefined && busNo!="" && busNo.trim()!="")
{
	var chkrslt=isNaN(busNo);
	if(chkrslt==true)
		{
		alert("bus no cant be in text format. Enter valid number");
		}
	  
}
if(price!=null && price!=undefined && price!="" && price.trim()!="")
{
	var chkrslt=isNaN(price);
	if(chkrslt==true)
		{
		alert("Trip salary cant be in text format. Enter valid number");
		}	  
}
$.ajax({
	  url: "TripServlet",
	  method: "POST",
	  data: { serviceName: "updateTrip",tripId:tripId,tripDate:tripDate,tripTime:tripTime,busNo:busNo,price:price },
		success:function(data) {
			if(data.indexOf("SESSIONTIMEOUT")!=-1)
			{
				alert("Session Timeout!! Redirecting to login page");
				window.location="index.html";
			}
			else if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="updateTrip.html";
				}
			else
				{
				alert("Save failed");
				}
		 },
		 error:function(err){
			 alert(err);
		 }
	});
} 

function deleteTrip()
{
var tripId=document.getElementById("tripId").value;;

if(tripId!=null && tripId!=undefined && tripId!="" && tripId.trim()!="")
{
	var chkrslt=isNaN(tripId);
	if(chkrslt==true)
		{
		alert("Trip Id cant be in text format. Enter valid number");
		}
	  
}

$.ajax({
	  url: "TripServlet",
	  method: "POST",
	  data: { serviceName: "deleteTrip",tripId:tripId },
		success:function(data) {
			if(data.indexOf("SESSIONTIMEOUT")!=-1)
			{
				alert("Session Timeout!! Redirecting to login page");
				window.location="index.html";
			}
			else if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="deleteTrip.html";
				}
			else
				{
				alert("Save failed");
				}
		 },
		 error:function(err){
			 alert(err);
		 }
	});
}


function bookTicket(tripId,busNo){
	$("#outPopUp").empty();
	$("#outPopUp").append("<table><tr><td></td><td><div id=\"a1\"></div></td></tr><tr><td><tr><td><label class=\"lab1\" for=\"passengerId\">Select Passenger</label></td><td><div id=\"passengerdiv\"></div></td></tr><tr><td></td><td><button class=\"w3-button w3-black\" id=\"updatebtn\" onclick=\"confirmBooking("+tripId+","+busNo+")\">Confirm</button></td></tr></table>");

	$("#passengerdiv").empty();
	$("#a1").append( "<option value=\"\"> Passenger Id | Passenger Name | Passenger Phone | Passenger Age |");
	$("#passengerdiv").append("<select id=\"passengerId\"></select>" );
	$("#passengerId").append("<option value=\"\">Select Passenger </option>");
	for(i=0;i<passArray.length;i++)
	{
		var pass=passArray[i];
		$("#passengerId").append("<option value=\""+pass.passengerId+"\">P-"+pass.passengerId+" | "+pass.name+" | "+pass.phone+" | "+pass.age+"</option>");
	}
}



var passArray=[];
function displayPassenger12()
{
$.ajax({
	  url: "PassengerServlet",
	  method: "GET",
	  data: { serviceName: "searchPassenger" },
		success:function(data) {

				
					passArray=JSON.parse(data);
					
		 },
		 error:function(err){
			 alert(err);
		 }

	});
}




function confirmBooking(tripId,busNo){
	var passengerId=document.getElementById("passengerId").value;
	var status="Booked";
	//alert(tripId+","+busNo+","+passengerId);
	var ticketObj={
			"passengerId":passengerId,"tripId":tripId,"status":status
	};
	$.ajax({
		  url: "TicketServlet",
		  method: "POST",
		  data: { serviceName: "addTicket",ticketRec:JSON.stringify(ticketObj) },
			success:function(data) {
				if(data.indexOf("SESSIONTIMEOUT")!=-1)
				{
					alert("Session Timeout!! Redirecting to login page");
					window.location="index.html";
				}
				else if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="addTicket.html";
					}
				else
					{
					alert("Save failed");
					}
			 },
			 error:function(err){
				 alert(err);
			 }
		});
}
