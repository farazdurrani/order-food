<!DOCTYPE html>
<html lang="en">
<head>
<title>Restaurant Website in Work</title>
</head>
<body>
	<h1>${welcome}</h1>

	<form:form name="reciept" action="/a/markedsingleorderascompleted">



			
				

					<Legend>Order for ${currentorder.personName}</Legend>

					<Legend>${menu.userid} Ordered the following for ${currentorder.menus[0].day} &nbsp; ${currentorder.menus[0].orderDate }</Legend>
			
			

			<input name="id" type="hidden" value="${currentorder.id}"/>

			<div class="checkbox">
				<label> <!-- this <label> is attached to <div class="checkbox"> above -->
					<table>
						<thead>
							<tr>
								<th> &nbsp; &nbsp;Dish</th>
								<th> &nbsp; &nbsp;Price</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${currentorder.menus}" var="menu" varStatus="status">
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.name}</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.price}</td>
								</tr>

							</c:forEach>
						</tbody>						
					</table>
					<h2>Total Price</h2>
					<h3>$${price} Including Tax (8.5%)</h3>
				</label>
			</div>	<!-- END OF DIV CHECKBOX -->

	<button type="submit" class="btn btn-success">Mark as Picked Up (close the order)</button>
	<INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
	
	</form:form>
</body>
</html>