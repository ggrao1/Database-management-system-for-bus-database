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
			if(data.indexOf("failed")==-1)
				{
					alert(data);
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
						$("#busresltTable").append('<tr><td>'+" "+bus.busNo+" "+'</td><td>'+" "+bus.capacity+" "+'</td><td>'+" "+bus.depoNo+" "+'</td><td>'+" "+bus.fromStop+" "+'</td><td>'+" "+bus.toStop+" "+'</td></tr>');
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
						$("#depoNo").append("<option value=\""+depo.depoNo+"\">"+depo.location+"</option>");
					}
					
			    

		 },
		 error:function(err){
			 alert(err);
		 }

	});

}


