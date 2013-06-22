<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>
<div>
	<h1>Result!</h1>
	<table border="1">
		<tr>
			<td>Bank Code</td>
			<td>RFC</td>
			<td>Name</td>
			<td>Address</td>
			<td>Amount</td>
			<td>Date</td>
			<td>Active</td>
		</tr>
	<s:iterator value="array">
  		<tr>
    		<td><s:property value="bank_code"/></td>
    		<td><s:property value="rfc"/></td>
    		<td><s:property value="name"/></td>
    		<td><s:property value="address"/></td>
    		<td><s:property value="amount"/></td>
    		<td><s:property value="date"/></td>
    		<td><s:property value="active"/></td>
 		 </tr>
	</s:iterator>
	</table>
	<p>
	<a href="<s:url action='index'/>" >Go back!</a>
	</p>
</div>
</body>
</html>