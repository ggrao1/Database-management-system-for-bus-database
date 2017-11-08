function saveDepo()
{
var location=document.getElementById("location").value;


var depoObj={
		"location":location	
};


$.ajax({
	  url: "DepoServlet",
	  method: "POST",
	  data: { serviceName: "addDepo",depoRec:JSON.stringify(depoObj) },
		success:function(data) {
			if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="addDepo.html";
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

function displayDepo5()
{

	var depoNo=document.getElementById("depoNo").value;
	var location=document.getElementById("location").value;
	if(depoNo!=null && depoNo!=undefined && depoNo!="" && depoNo.trim()!="")
	{
		var chkrslt=isNaN(depoNo);
		if(chkrslt==true)
			{
			alert("Depot No.  cant be in text format. Enter valid number");
			}
		  
	}

$.ajax({
	  url: "DepoServlet",
	  method: "GET",
	  data: { serviceName: "displayDepo", depoNo:depoNo,location:location},
		success:function(data) {

				$("#deporesltTable").empty();
				$("#deporesltTable").append( "<th> Depo Id </th><th> Location </th>");
					var depoArray=JSON.parse(data);
					for(i=0;i<depoArray.length;i++)
					{
						var depo=depoArray[i];
						$("#deporesltTable").append('<tr><td>'+" "+depo.depoNo+" "+'</td><td>'+" "+depo.location+" "+'</td></tr>');
					}

		 },
		 error:function(err){
			 alert(err);
		 }

	});

} 

function displayDepo3()
{

$.ajax({
	  url: "DepoServlet",
	  method: "GET",
	  data: { serviceName: "displayDepo" },
		success:function(data) {

				$("#deposeldiv").empty();
				$("#a1").append("<option value=\"\"> Depot </option>");
				$("#deposeldiv").append( "<select id=\"depoNo\"></select>");
				$("#depoNo").append("<option value=\"\">Select Depot</option>");
					var depoArray=JSON.parse(data);
					for(i=0;i<depoArray.length;i++)
					{
						var depo=depoArray[i];
						$("#depoNo").append("<option value=\""+depo.depoNo+"\">| "+depo.depoNo+"</option>");
					}
					
			    

		 },
		 error:function(err){
			 alert(err);
		 }
	});
}


function updateDepo()
{

	var depoNo=document.getElementById("depoNo").value;
	var location=document.getElementById("location").value;
	if(depoNo!=null && depoNo!=undefined && depoNo!="" && depoNo.trim()!="")
	{
		var chkrslt=isNaN(depoNo);
		if(chkrslt==true)
			{
			alert("Depot No.  cant be in text format. Enter valid number");
			} 
	}
	$.ajax({
		  url: "DepoServlet",
		  method: "POST",
		  data: { serviceName: "updateDepo",depoNo:depoNo,location:location },
			success:function(data) {
				if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="updateDepo.html";
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


function deleteDepo()
{

	var depoNo=document.getElementById("depoNo").value;
	if(depoNo!=null && depoNo!=undefined && depoNo!="" && depoNo.trim()!="")
	{
		var chkrslt=isNaN(depoNo);
		if(chkrslt==true)
			{
			alert("Depot No.  cant be in text format. Enter valid number");
			} 
	}
	$.ajax({
		  url: "DepoServlet",
		  method: "POST",
		  data: { serviceName: "deleteDepo",depoNo:depoNo },
			success:function(data) {
				if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="deleteDepo.html";
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