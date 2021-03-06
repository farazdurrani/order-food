<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Restaurant Website in Work</title>
</head>
<body>
	<h1>${welcome}</h1>

	<form:form name="reciept" action="/a/markedascompleted">



			
				

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
			
	<Legend>THIS Order HAS BEEN PAID AND PICKED UP</Legend>
	
	<INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
	
	</form:form>
</body>
</html>