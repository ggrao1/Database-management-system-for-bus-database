function savePassenger()
{

var name=document.getElementById("name").value;
var phone=document.getElementById("phone").value;
var age=document.getElementById("age").value;


var passObj={
		"name":name,"phone":phone,"age":age
};


$.ajax({
	  url: "PassengerServlet",
	  method: "POST",
	  data: { serviceName: "addPassenger",passRec:JSON.stringify(passObj) },
		success:function(data) {
			if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="addPassenger.html";
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

function searchPassenger()
{
	var passengerId=document.getElementById("passengerId").value;
	var name=document.getElementById("name").value;
	var phone=document.getElementById("phone").value;
	var age=document.getElementById("age").value;
	
	if(passengerId!=null && passengerId!=undefined && passengerId!="" && passengerId.trim()!="")
	{
		var chkrslt=isNaN(passengerId);
		if(chkrslt==true)
			{
			alert("Passenger Id cant be in text format. Enter valid number");
			}
		  
	}
	if(age!=null && age!=undefined && age!="" && age.trim()!="")
	{
		var chkrslt=isNaN(age);
		if(chkrslt==true)
			{
			alert("age cant be in text format. Enter valid number");
			}
	}

$.ajax({
	  url: "PassengerServlet",
	  method: "GET",
	  data: { serviceName: "searchPassenger",passengerId:passengerId,name:name,phone:phone,age:age },
		success:function(data) {

				$("#passresltTable").empty();
				$("#passresltTable").append( "<th>Passenger Id</th><th>Passenger Name</th><th>Passenger Phone</th><th>Passenger Age</th>");
					var passArray=JSON.parse(data);
					for(i=0;i<passArray.length;i++)
					{
						var pass=passArray[i];
						$("#passresltTable").append('<tr><td>'+pass.passengerId+'</td><td>'+pass.name+'</td><td>'+pass.phone+'</td><td>'+pass.age+'</td></tr>');
					}

		 },
		 error:function(err){
			 alert(err);
		 }

	});
} 

function displayPassenger6()
{
$.ajax({
	  url: "PassengerServlet",
	  method: "GET",
	  data: { serviceName: "searchPassenger" },
		success:function(data) {

				$("#passengerdiv").empty();
				$("#a1").append( "<option value=\"\"> Passenger Id | Passenger Name | Passenger Phone | Passenger Age |");
				$("#passengerdiv").append("<select id=\"passengerId\"></select>" );
				$("#passengerId").append("<option value=\"\">Select Passenger </option>");
					var passArray=JSON.parse(data);
					for(i=0;i<passArray.length;i++)
					{
						var pass=passArray[i];
						$("#passengerId").append("<option value=\""+pass.passengerId+"\">"+pass.passengerId+" | "+pass.name+" | "+pass.phone+" | "+pass.age+"</option>");
					}
		 },
		 error:function(err){
			 alert(err);
		 }

	});
}

function updatePassenger()
{
	var passengerId=document.getElementById("passengerId").value;
	var name=document.getElementById("name").value;
	var phone=document.getElementById("phone").value;
	var age=document.getElementById("age").value;
	
	if(passengerId!=null && passengerId!=undefined && passengerId!="" && passengerId.trim()!="")
	{
		var chkrslt=isNaN(passengerId);
		if(chkrslt==true)
			{
			alert("Passenger Id cant be in text format. Enter valid number");
			}
		  
	}
	if(age!=null && age!=undefined && age!="" && age.trim()!="")
	{
		var chkrslt=isNaN(age);
		if(chkrslt==true)
			{
			alert("age cant be in text format. Enter valid number");
			}
	}
	$.ajax({
		  url: "PassengerServlet",
		  method: "POST",
		  data: { serviceName: "updatePassenger",passengerId:passengerId,name:name,phone:phone,age:age },
			success:function(data) {
				if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="updatePassenger.html";
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



function deletePassenger()
{

	var busNo=document.getElementById("busNo").value;
	if(busNo!=null && busNo!=undefined && busNo!="" && busNo.trim()!="")
	{
		var chkrslt=isNaN(busNo);
		if(chkrslt==true)
			{
			alert("Passenger No.  cant be in text format. Enter valid number");
			} 
	}
	$.ajax({
		  url: "PassengerServlet",
		  method: "POST",
		  data: { serviceName: "deletePassenger",busNo:busNo },
			success:function(data) {
				if(data.indexOf("failed")==-1)
					{
						alert(data);
						window.location="deletePassenger.html";
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