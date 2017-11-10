function logIn()
{

	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;

$.ajax({
	  url: "LoginServlet",
	  method: "POST",
	  data: { serviceName: "logIn", username:username,password:password},
		success:function(data) {
			
			if(data=="ERROR")
				{
				alert("Invalid username or password!!");
				window.location="index.html";
				}
			else
				{
					window.location.href = data;
				}
			
			
					/*var loginResult=data;
						if(loginResult=="SUCCESS"){
							alert("login successful!!");
							window.location="index1.html";
						}
						else{
							alert("Invalid username or password!!");
							window.location="index.html";
						}*/
		 },
		 error:function(err){
			 alert(err);
		 }

	});

} 

function checkUserPageAccess()
{


$.ajax({
	  url: "LoginServlet",
	  method: "GET",
	  data: { serviceName: "checkUserPageAccess"},
		success:function(data) {
			
			if(data=="SUCCESS")
				{
					return true;
				}
			else
				{
				alert("UnAuthorized Access to the Page!! Redirecting to Login Page");
				window.location="index.html";
				}
			
			
		 },
		 error:function(err){
			 alert(err);
		 }

	});

} 

function logOut()
{
	
	$.ajax({
		  url: "LoginServlet",
		  method: "GET",
		  data: { serviceName: "logout"},
			success:function(data) {
			
				if(data=="SUCCESS")
				{
					window.location="index.html";
				}
			else
				{
				alert("Session Timed out !! Redirecting to Login Page");
				window.location="index.html";
				}
			
				
							
				
			 },
			 error:function(err){
				 alert(err);
			 }

		});

}