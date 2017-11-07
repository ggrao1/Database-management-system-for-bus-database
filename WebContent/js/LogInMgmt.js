function logIn()
{

	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;

$.ajax({
	  url: "LoginServlet",
	  method: "GET",
	  data: { serviceName: "logIn", username:username,password:password},
		success:function(data) {

				$("#error").empty();
				
					var loginResult=data;
						if(loginResult=="SUCCESS"){
							alert("login successful!!");
							window.location="index1.html";
						}
						else{
							$("#error").append( "Invalid username or password!!");
						}
					
		 },
		 error:function(err){
			 alert(err);
		 }

	});

} 

