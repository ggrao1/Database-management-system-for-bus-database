function saveEmployee()
{

var empName=document.getElementById("empName").value;
var empDob=document.getElementById("empDob").value;
var empSex=document.getElementById("empSex").value;
var busNo=document.getElementById("busNo").value;
var empSalary=document.getElementById("empSalary").value;


var empObj={
		"empName":empName,"empDob":empDob,"empSex":empSex,"busNo":busNo,"empSalary":empSalary
};


$.ajax({
	  url: "EmployeeServlet",
	  method: "POST",
	  data: { serviceName: "addEmployee",empRec:JSON.stringify(empObj) },
		success:function(data) {
			if(data.indexOf("SESSIONTIMEOUT")!=-1)
			{
				alert("Session Timeout!! Redirecting to login page");
				window.location="index.html";
			}
			else if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="addEmp.html";
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

function searchEmployee()
{
	var empId=document.getElementById("empId").value;
	var empName=document.getElementById("empName").value;
	var empDob=document.getElementById("empDob").value;
	var empSex=document.getElementById("empSex").value;
	var busNo=document.getElementById("busNo").value;
	var empSalary=document.getElementById("empSalary").value;
	
	if(empId!=null && empId!=undefined && empId!="" && empId.trim()!="")
	{
		var chkrslt=isNaN(empId);
		if(chkrslt==true)
			{
			alert("Employee Id cant be in text format. Enter valid number");
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
	if(empSalary!=null && empSalary!=undefined && empSalary!="" && empSalary.trim()!="")
	{
		var chkrslt=isNaN(empSalary);
		if(chkrslt==true)
			{
			alert("Employee salary cant be in text format. Enter valid number");
			}
		  
	}


$.ajax({
	  url: "EmployeeServlet",
	  method: "GET",
	  data: { serviceName: "searchEmployee",empId:empId,empName:empName,empDob:empDob,empSex:empSex,busNo:busNo,empSalary:empSalary },
		success:function(data) {

				$("#empresltTable").empty();
				$("#empresltTable").append( "<th>Employee Id</th><th>Employee Name</th><th>Date Of Birth</th><th>Bus No.</th><th>Sex</th><th>Salary</th>");
					var empArray=JSON.parse(data);
					for(i=0;i<empArray.length;i++)
					{
						var emp=empArray[i];
						$("#empresltTable").append('<tr><td>'+emp.empId+'</td><td>'+emp.empName+'</td><td>'+emp.empDob+'</td><td>'+emp.busNo+'</td><td>'+emp.empSex+'</td><td>'+emp.empSalary+'</td></tr>');
					}

		 },
		 error:function(err){
			 alert(err);
		 }

	});
} 



function displayEmployee()
{
$.ajax({
	  url: "EmployeeServlet",
	  method: "GET",
	  data: { serviceName: "searchEmployee" },
		success:function(data) {

				$("#empiddiv").empty();
				$("#empiddiv").append("<select id=\"empId\"></select>");
				$("#empId").append("<option value=\"\">Select Employee Id</option>");
					var empArray=JSON.parse(data);
					for(i=0;i<empArray.length;i++)
					{
						var emp=empArray[i];
						$("#empId").append("<option value=\""+emp.empId+"\">"+emp.empId+"</option>");
					}
		 },
		 error:function(err){
			 alert(err);
		 }

	});
}

function displayEmployee2()
{
$.ajax({
	  url: "EmployeeServlet",
	  method: "GET",
	  data: { serviceName: "searchEmployee" },
		success:function(data) {

				$("#empiddiv").empty();
				$("#empiddiv").append("<select id=\"empId\"></select>");
				$("#empId").append("<option value=\"\">Select Employee Id</option>");
				$("#a1").append( "<option value=\"\"> Employee Id | Employee Name | Date Of Birth | Bus No. | Sex | Salary |");
					var empArray=JSON.parse(data);
					for(i=0;i<empArray.length;i++)
					{
						var emp=empArray[i];
						$("#empId").append("<option value=\""+emp.empId+"\"> | "+emp.empId+" | "+emp.empName+" | "+emp.empDob+" | "+emp.busNo+" | "+emp.empSex+" | "+emp.empSalary+" | </option>");
					}
		 },
		 error:function(err){
			 alert(err);
		 }

	});
}


function updateEmployee()
{
var empId=document.getElementById("empId").value;;
var empName=document.getElementById("empName").value;;
var empDob=document.getElementById("empDob").value;
var empSex=document.getElementById("empSex").value;
var busNo=document.getElementById("busNo").value;
var empSalary=document.getElementById("empSalary").value;

if(empId!=null && empId!=undefined && empId!="" && empId.trim()!="")
{
	var chkrslt=isNaN(empId);
	if(chkrslt==true)
		{
		alert("Employee Id cant be in text format. Enter valid number");
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
if(empSalary!=null && empSalary!=undefined && empSalary!="" && empSalary.trim()!="")
{
	var chkrslt=isNaN(empSalary);
	if(chkrslt==true)
		{
		alert("Employee salary cant be in text format. Enter valid number");
		}	  
}
$.ajax({
	  url: "EmployeeServlet",
	  method: "POST",
	  data: { serviceName: "updateEmployee",empId:empId,empName:empName,empDob:empDob,empSex:empSex,busNo:busNo,empSalary:empSalary },
		success:function(data) {
			if(data.indexOf("SESSIONTIMEOUT")!=-1)
			{
				alert("Session Timeout!! Redirecting to login page");
				window.location="index.html";
			}
			else if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="updateEmp.html";
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

function deleteEmp()
{
var empId=document.getElementById("empId").value;;

if(empId!=null && empId!=undefined && empId!="" && empId.trim()!="")
{
	var chkrslt=isNaN(empId);
	if(chkrslt==true)
		{
		alert("Employee Id cant be in text format. Enter valid number");
		}
	  
}

$.ajax({
	  url: "EmployeeServlet",
	  method: "POST",
	  data: { serviceName: "deleteEmployee",empId:empId },
		success:function(data) {
			if(data.indexOf("SESSIONTIMEOUT")!=-1)
			{
				alert("Session Timeout!! Redirecting to login page");
				window.location="index.html";
			}
			else if(data.indexOf("failed")==-1)
				{
					alert(data);
					window.location="deleteEmp.html";
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
