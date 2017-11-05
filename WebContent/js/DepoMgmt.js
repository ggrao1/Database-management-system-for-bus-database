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

function displayDepo()
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

