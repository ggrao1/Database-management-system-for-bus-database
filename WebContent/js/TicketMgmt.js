function saveTicket()
{
var passengerId=document.getElementById("passengerId").value;
var busNo=document.getElementById("busNo").value;
var price=document.getElementById("price").value;
var fromStop=document.getElementById("fromStop").value;
var toStop=document.getElementById("toStop").value;

var ticketObj={
		"busNo":busNo,"passengerId":passengerId,"price":price,"fromStop":fromStop,"toStop":toStop
};


$.ajax({
	  url: "TicketServlet",
	  method: "POST",
	  data: { serviceName: "addTicket",ticketRec:JSON.stringify(ticketObj) },
		success:function(data) {
			if(data.indexOf("failed")==-1)
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

function displayTicket()
{

	var ticketNo=document.getElementById("ticketNo").value;
	var busNo=document.getElementById("busNo").value;
	var price=document.getElementById("price").value;
	var passengerId=document.getElementById("passengerId").value;
	var fromStop=document.getElementById("fromStop").value;
	var toStop=document.getElementById("toStop").value;
	if(ticketNo!=null && ticketNo!=undefined && ticketNo!="" && ticketNo.trim()!="")
	{
		var chkrslt=isNaN(ticketNo);
		if(chkrslt==true)
			{
			alert("Ticket No.  cant be in text format. Enter valid number");
			}  
	}
	if(price!=null && price!=undefined && price!="" && price.trim()!="")
	{
		var chkrslt=isNaN(price);
		if(chkrslt==true)
			{
			alert("price  cant be in text format. Enter valid number");
			}  
	}
	if(busNo!=null && busNo!=undefined && busNo!="" && busNo.trim()!="")
	{
		var chkrslt=isNaN(busNo);
		if(chkrslt==true)
			{
			alert("Bus No.  cant be in text format. Enter valid number");
			}  
	}
	if(passengerId!=null && passengerId!=undefined && passengerId!="" && passengerId.trim()!="")
	{
		var chkrslt=isNaN(passengerId);
		if(chkrslt==true)
			{
			alert("passengerId cant be in text format. Enter valid number");
			}  
	}

$.ajax({
	  url: "TicketServlet",
	  method: "GET",
	  data: { serviceName: "displayTicket",ticketNo:ticketNo, busNo:busNo,passengerId:passengerId,price:price,fromStop:fromStop,toStop:toStop},
		success:function(data) {

				$("#ticketresltTable").empty();
				$("#ticketresltTable").append( "<th> Ticket No. </th><th> Bus No. </th><th> Passenger ID </th><th> price </th><th> From Location </th><th> To Location </th>");
					var ticketArray=JSON.parse(data);
					for(i=0;i<ticketArray.length;i++)
					{
						var ticket=ticketArray[i];
						$("#ticketresltTable").append('<tr><td>'+" "+ticket.ticketNo+" "+'</td><td>'+" "+ticket.busNo+" "+'</td><td>'+" "+ticket.passengerId+" "+'</td><td>'+" "+ticket.price+" "+'</td><td>'+" "+ticket.fromStop+" "+'</td><td>'+" "+ticket.toStop+" "+'</td></tr>');
					}

		 },
		 error:function(err){
			 alert(err);
		 }

	});

} 

function displayBus()
{

	

$.ajax({
	  url: "BusServlet",
	  method: "GET",
	  data: { serviceName: "displayBus" },
		success:function(data) {

				$("#busdiv").empty();
				$("#busdiv").append( "<select id=\"busNo\"></select>");
				$("#busNo").append("<option value=\"\">Select Bus No.</option>");
					var busArray=JSON.parse(data);
					for(i=0;i<busArray.length;i++)
					{
						var bus=busArray[i];
						$("#busNo").append("<option value=\""+bus.busNo+"\">"+bus.busNo+"</option>");
					}
		 },
		 error:function(err){
			 alert(err);
		 }
	});

} 


function displayPassenger()
{

$.ajax({
	  url: "PassengerServlet",
	  method: "GET",
	  data: { serviceName: "searchPassenger" },
		success:function(data) {

				$("#passengerdiv").empty();
				$("#passengerdiv").append("<select id=\"passengerId\"></select>" );
				$("#passengerId").append("<option value=\"\">Select Passenger Id</option>");
					var passArray=JSON.parse(data);
					for(i=0;i<passArray.length;i++)
					{
						var pass=passArray[i];
						$("#passengerId").append("<option value=\""+pass.passengerId+"\">"+pass.passengerId+"</option>");
					}

		 },
		 error:function(err){
			 alert(err);
		 }

	});
}



function displayTicket1()
{

$.ajax({
	  url: "TicketServlet",
	  method: "GET",
	  data: { serviceName: "displayTicket" },
		success:function(data) {

				$("#ticketdiv").empty();
				$("#ticketdiv").append("<select id=\"ticketNo\"></select>" );
				$("#ticketNo").append("<option value=\"\">Select Ticket No</option>");
					var ticketArray=JSON.parse(data);
					for(i=0;i<ticketArray.length;i++)
					{
						var ticket=ticketArray[i];
						$("#ticketNo").append("<option value=\""+ticket.ticketNo+"\">"+ticket.ticketNo+"</option>");
					}

		 },
		 error:function(err){
			 alert(err);
		 }

	});

} 

function displayTicket4()
{

$.ajax({
	  url: "TicketServlet",
	  method: "GET",
	  data: { serviceName: "displayTicket" },
		success:function(data) {

				$("#ticketdiv").empty();
				$("#a1").append( "<option value=\"\"> Ticket No. | Bus No. | Passenger Id | Price | From Location. | To Location |");
				$("#ticketdiv").append("<select id=\"ticketNo\"></select>" );
				$("#ticketNo").append("<option value=\"\">Select Ticket</option>");
					var ticketArray=JSON.parse(data);
					for(i=0;i<ticketArray.length;i++)
					{
						var ticket=ticketArray[i];
						$("#ticketNo").append("<option value=\""+ticket.ticketNo+"\">| "+ticket.ticketNo+" | "+ticket.busNo+" | "+ticket.passengerId+" | "+ticket.price+" | "+ticket.fromStop+" | "+ticket.toStop+" | </option>");
					}

		 },
		 error:function(err){
			 alert(err);
		 }
	});
}

function updateTicket()
{

	var ticketNo=document.getElementById("ticketNo").value;
	var busNo=document.getElementById("busNo").value;
	var price=document.getElementById("price").value;
	var passengerId=document.getElementById("passengerId").value;
	var fromStop=document.getElementById("fromStop").value;
	var toStop=document.getElementById("toStop").value;
	if(ticketNo!=null && ticketNo!=undefined && ticketNo!="" && ticketNo.trim()!="")
	{
		var chkrslt=isNaN(ticketNo);
		if(chkrslt==true)
			{
			alert("Ticket No.  cant be in text format. Enter valid number");
			}  
	}
	if(price!=null && price!=undefined && price!="" && price.trim()!="")
	{
		var chkrslt=isNaN(price);
		if(chkrslt==true)
			{
			alert("price  cant be in text format. Enter valid number");
			}  
	}
	if(busNo!=null && busNo!=undefined && busNo!="" && busNo.trim()!="")
	{
		var chkrslt=isNaN(busNo);
		if(chkrslt==true)
			{
			alert("Bus No.  cant be in text format. Enter valid number");
			}  
	}
	if(passengerId!=null && passengerId!=undefined && passengerId!="" && passengerId.trim()!="")
	{
		var chkrslt=isNaN(passengerId);
		if(chkrslt==true)
			{
			alert("passengerId cant be in text format. Enter valid number");
			}  
	}
	$.ajax({
		  url: "TicketServlet",
		  method: "POST",
		  data: { serviceName: "updateTicket",ticketNo:ticketNo, busNo:busNo,passengerId:passengerId,price:price,fromStop:fromStop,toStop:toStop},
			success:function(data) {
				if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="updateTicket.html";
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

function deleteTicket()
{

	var ticketNo=document.getElementById("ticketNo").value;
	if(ticketNo!=null && ticketNo!=undefined && ticketNo!="" && ticketNo.trim()!="")
	{
		var chkrslt=isNaN(ticketNo);
		if(chkrslt==true)
			{
			alert("Ticket No.  cant be in text format. Enter valid number");
			}  
	}
	$.ajax({
		  url: "TicketServlet",
		  method: "POST",
		  data: { serviceName: "deleteTicket",ticketNo:ticketNo},
			success:function(data) {
				if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="deleteTicket.html";
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
