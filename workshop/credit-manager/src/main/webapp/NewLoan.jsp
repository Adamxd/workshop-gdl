<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Loan</title>
</head>
<body>
<div>
	<s:form action="CreateLoan">
		<s:textfield name="rfc" label="RFC" maxlength="10" requiredLabel="true"/>
		<s:textfield name="fname" label="Name" requiredLabel="true"/>
		<s:textfield name="lname" label="Last Name"  requiredLabel="true"/>
		<s:textfield name="address" label="Address" requiredLabel="true"/>
		<s:textfield name="amount" label="Amount" requiredLabel="true"/>
		<s:submit value="Submit"/>
	</s:form>
</div>
<p>
	<a href="<s:url action='index'/>" >Go back!</a>
</p>
</body>
</html>