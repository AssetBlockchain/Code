<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
<script type="text/javascript">
function createAsset()
{
	var http = new XMLHttpRequest();
	var url = "./asset";
	var params = "color=red&carat=24";
	http.open("POST", url, true);

	//Send the proper header information along with the request
	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	       // alert(http.responseText);
	    }
	}
	http.send(params);
}
</script>
</head>
<body>
	<center>
		

		<span id="createAsset" class="lftBtn mnBtn" onclick="createAsset()">Create Asset</span>
	${message}
	</center>
</body>
</html>