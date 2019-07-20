<!DOCTYPE html>
<html lang="en">
<head>
<title>Restaurant Website in Work</title>
</head>
<body>

	<h1>${welcome}</h1>

	<form:form name="admin_main_page">

	<legend>Orders from Signed Up Accounts</legend>
		<table style="width: 100%">
			<c:forEach items="${orders}" var="customer" varStatus="status">
				<tr>
					<td><a href="/a/reciept/${customer.id}"
						class="btn btn-default" role="button">Order Number # ${customer.id}, Pickup Date = ${customer.menus[0].orderDate}, Name = ${customer.fullname}, Username = ${customer.userid} </a></td>
				</tr>
			</c:forEach>

		</table>

-----------------------------------------------------------------------------------

<legend>Orders from NOT Signed Up Accounts</legend>
		<table style="width: 100%">
			<c:forEach items="${singleorders}" var="customer" varStatus="status">
				<tr>
					<td><a href="/a/recieptofsingleorder/${customer.id}"
						class="btn btn-default" role="button">Order Number # ${customer.id}, Pickup Date = ${customer.menus[0].orderDate }, Name = ${customer.personName} </a></td>
				</tr>
			</c:forEach>

		</table>
		
		-----------------------------------------------------------------------------------

<legend>Closed Orders from Signed Up Accounts</legend>
		<table style="width: 100%">
			<c:forEach items="${closedorders}" var="customer" varStatus="status">
				<tr>
					<td><a href="/a/closedorderreciept/${customer.id}"
						class="btn btn-default" role="button">Order Number # ${customer.id}, Pickup Date = ${customer.menus[0].orderDate }, Name = ${customer.fullname}, Username = ${customer.userid} </a></td>
				</tr>
			</c:forEach>

		</table>
		
	-----------------------------------------------------------------------------------

<legend>Closed Orders from NOT Signed Up Accounts</legend>
		<table style="width: 100%">
			<c:forEach items="${closedsingleorders}" var="customer" varStatus="status">
				<tr>
					<td><a href="/a/closedsingleorderreciept/${customer.id}"
						class="btn btn-default" role="button">Order Number # ${customer.id}, Pickup Date = ${customer.menus[0].orderDate } Name = ${customer.personName} </a></td>
				</tr>
			</c:forEach>

		</table>


	</form:form>
</body>
</html>