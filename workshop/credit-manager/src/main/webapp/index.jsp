<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RFC</title>
</head>
<body>
	<div>
		<s:form action="ProcessRequest">
			<s:textfield name="rfc" label="RFC" maxlength="10" requiredLabel="true"/>
			<s:select label="Operation"
       			name="option"
       			list="#{'01':'Display', '02':'Get New Loan', '03':'Payment', '04':'Close Loan'}"
       			value="selectedMonth"
      			 required="true"
			/>
			<s:submit value="Query"/>
		</s:form>	
	</div>
</body>
</html>