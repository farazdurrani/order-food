<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Restaurant Website in Work</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="http://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
</style>


</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="50">

	<form:form id="customer" name="customer" modelAttribute="customer"
		method="post" action="/signin">

		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/main">Best Food Restaurant</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="/main">HOME</a></li>
						<li><a href="/menu">MENU</a></li>
						<c:if test="${customer.signedin}">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Hello, ${customer.fullname}
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a href="/youraccount">Your Order History</a></li>
									<li><a href="/logout">Logout</a></li>
								</ul></li>
						</c:if>
						<c:if test="${!customer.signedin}">

							<!-- NEW STUFF  -->
							<c:if test="${fn:length(customer.menus)>0}">
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#"><span
										class="glyphicon glyphicon-shopping-cart"></span><span
										class="items">${cartsize}</span><span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="#">All the ${cartsize} orders you have
												placed during this session is your resposibility to pick up
												on their respective dates</a></li>
										<li><a href="/killsession">Click here to kill the
												session. You will be able to place orders again.</a></li>
									</ul></li>
							</c:if>
							<!-- NEW STUFF  -->

							<li><a href="/signup"><span
									class="glyphicon glyphicon-user"></span> Sign Up</a></li>
							<li><a href="/signin"><span
									class="glyphicon glyphicon-log-in"></span> Login</a></li>
						</c:if>

					</ul>
				</div>

			</div>
		</nav>
		</br>
		</br>
		</br>
		</br>
		</br>
		<div class="col-md-2">
			<c:if test="${not empty loginerror}">
				<p>${loginerror}</p>
			</c:if>

			<div id="userid" class="form-group">
				<label for="userid">User ID (email):</label> <input type="email"
					class="form-control" id="userid" name="userid" required>
			</div>
			<div id="password" class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>

			<button type="submit" class="btn btn-info">Submit</button>
		</div>
		<!--  END OF COL-MD-2 -->

	</form:form>
</body>

</html>