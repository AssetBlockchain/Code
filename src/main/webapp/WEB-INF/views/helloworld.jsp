<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
<script type="text/javascript">
function createAsset()
{
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "./asset", false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
</script>
</head>
<body>
	<center>
		<h2>Hello World</h2>
				

		<span id="createAsset" class="lftBtn mnBtn" onclick="createAsset()">Create Asset</span>
	
		<h2>
			${message} ${name}
		</h2>
	</center>
</body>
</html>