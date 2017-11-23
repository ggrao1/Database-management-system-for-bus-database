function saveBus()
{
var depoNo=document.getElementById("depoNo").value;
var capacity=document.getElementById("capacity").value;
var fromStop=document.getElementById("fromStop").value;
var toStop=document.getElementById("toStop").value;

var busObj={
		"depoNo":depoNo,"capacity":capacity,"fromStop":fromStop,"toStop":toStop
};



$.ajax({
	  url: "BusServlet",
	  method: "POST",
	  data: { serviceName: "addBus",busRec:JSON.stringify(busObj) },
		success:function(data) {
			if(data.indexOf("SESSIONTIMEOUT")!=-1)
			{
				alert("Session Timeout!! Redirecting to login page");
				window.location="index.html";
			}
			
			
			else if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="addBus.html";
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

function displayBus1()
{

	var busNo=document.getElementById("busNo").value;
	var depoNo=document.getElementById("depoNo").value;
	var capacity=document.getElementById("capacity").value;
	var fromStop=document.getElementById("fromStop").value;
	var toStop=document.getElementById("toStop").value;
	if(depoNo!=null && depoNo!=undefined && depoNo!="" && depoNo.trim()!="")
	{
		var chkrslt=isNaN(depoNo);
		if(chkrslt==true)
			{
			alert("Depo No.  cant be in text format. Enter valid number");
			}  
	}
	if(capacity!=null && capacity!=undefined && capacity!="" && capacity.trim()!="")
	{
		var chkrslt=isNaN(capacity);
		if(chkrslt==true)
			{
			alert("capacity  cant be in text format. Enter valid number");
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

$.ajax({
	  url: "BusServlet",
	  method: "GET",
	  data: { serviceName: "displayBus", busNo:busNo,depoNo:depoNo,capacity:capacity,fromStop:fromStop,toStop:toStop},
		success:function(data) {

				$("#busresltTable").empty();
				$("#busresltTable").append( "<th> Bus No. </th><th> Depot No. </th><th> Capacity </th><th> From Location </th><th> To Location </th>");
					var busArray=JSON.parse(data);
					for(i=0;i<busArray.length;i++)
					{
						var bus=busArray[i];
						$("#busresltTable").append('<tr><td>'+" B-"+bus.busNo+" "+'</td><td>'+" D-"+bus.depoNo+" "+'</td><td>'+" "+bus.capacity+" "+'</td><td>'+" "+bus.fromStop+" "+'</td><td>'+" "+bus.toStop+" "+'</td></tr>');
					}

		 },
		 error:function(err){
			 alert(err);
		 }
	});

} 



function displayDepo()
{

$.ajax({
	  url: "DepoServlet",
	  method: "GET",
	  data: { serviceName: "displayDepo" },
		success:function(data) {

				$("#deposeldiv").empty();
				$("#deposeldiv").append( "<select id=\"depoNo\"></select>");
				$("#depoNo").append("<option value=\"\">Select Depot</option>");
					var depoArray=JSON.parse(data);
					for(i=0;i<depoArray.length;i++)
					{
						var depo=depoArray[i];
						$("#depoNo").append("<option value=\""+depo.depoNo+"\">| D-"+depo.depoNo+" | "+depo.location+"</option>");
					}
		 },
		 error:function(err){
			 alert(err);
		 }

	});

}


function displayBus3()
{
	
$.ajax({
	  url: "BusServlet",
	  method: "GET",
	  data: { serviceName: "displayBus" },
		success:function(data) {

				$("#busdiv").empty();
				$("#busdiv").append( "<select id=\"busNo\"></select>");
				$("#busNo").append("<option value=\"\">Select Bus No.</option>");
				$("#a1").append( "<option value=\"\"> Bus No. | Depot No. | Capacity | From Location. | To Location |");
					var busArray=JSON.parse(data);
					for(i=0;i<busArray.length;i++)
					{
						var bus=busArray[i];
						$("#busNo").append("<option value=\""+bus.busNo+"\">| B-"+bus.busNo+" | D-"+bus.depoNo+" | "+bus.capacity+" | "+bus.fromStop+" | "+bus.toStop+" | "+"</option>");
					}
		 },
		 error:function(err){
			 alert(err);
		 }
	});

}

function updateBus1()
{

	var busNo=document.getElementById("busNo").value;
	var depoNo=document.getElementById("depoNo").value;
	var capacity=document.getElementById("capacity").value;
	var fromStop=document.getElementById("fromStop").value;
	var toStop=document.getElementById("toStop").value;
	if(depoNo!=null && depoNo!=undefined && depoNo!="" && depoNo.trim()!="")
	{
		var chkrslt=isNaN(depoNo);
		if(chkrslt==true)
			{
			alert("Depo No.  cant be in text format. Enter valid number");
			}  
	}
	if(capacity!=null && capacity!=undefined && capacity!="" && capacity.trim()!="")
	{
		var chkrslt=isNaN(capacity);
		if(chkrslt==true)
			{
			alert("capacity  cant be in text format. Enter valid number");
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

	$.ajax({
		  url: "BusServlet",
		  method: "POST",
		  data: { serviceName: "updateBus",busNo:busNo,depoNo:depoNo,capacity:capacity,fromStop:fromStop,toStop:toStop },
			success:function(data) {
				if(data.indexOf("SESSIONTIMEOUT")!=-1)
				{
					alert("Session Timeout!! Redirecting to login page");
					window.location="index.html";
				}
				else if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="updateBus.html";
					}
				else
					{
					alert("Trriger violated");
					}
			 },
			 error:function(err){
				 alert(err);
			 }

		});

	} 


function deleteBus8()
{

	var busNo=document.getElementById("busNo").value;
	if(busNo!=null && busNo!=undefined && busNo!="" && busNo.trim()!="")
	{
		var chkrslt=isNaN(busNo);
		if(chkrslt==true)
			{
			alert("Bus No.  cant be in text format. Enter valid number");
			} 
	}
	$.ajax({
		  url: "BusServlet",
		  method: "POST",
		  data: { serviceName: "deleteBus",busNo:busNo },
			success:function(data) {
				if(data.indexOf("SESSIONTIMEOUT")!=-1)
				{
					alert("Session Timeout!! Redirecting to login page");
					window.location="index.html";
				}
				else if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="deleteBus.html";
					}
				else
					{
					alert("Delete failed");
					}
			 },
			 error:function(err){
				 alert(err);
			 }

		});

	}
