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
				
					var depoArray=JSON.parse(data);
					for(i=0;i<depoArray.length;i++)
					{
						var depo=depoArray[i];
						if(depo.username==username && depo.password==password){
							alert("login successful!!")
						}
						else{
							$("#error").append( "Invalid username or password!!");
						}
					}
		 },
		 error:function(err){
			 alert(err);
		 }

	});

} 

