<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

	<form:form name="signup" modelAttribute="customer" method="post" action="/signupcomplete">

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
								data-toggle="dropdown" href="#">Hello, ${customer.fullname} <span
									class="caret"></span></a>
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
									class="glyphicon glyphicon-shopping-cart"></span><span class="items">${cartsize}</span><span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#">All the ${cartsize} orders you have placed during this session is your resposibility to pick up on their respective dates</a></li>
									<li><a href="/killsession">Click here to kill the session. You will be able to place orders again.</a></li>
								</ul></li>
							</c:if>	
						<!-- NEW STUFF  -->
						
							<li><a href="/signup"><span
									class="glyphicon glyphicon-user"></span> Sign Up</a></li>
							<li><a href="/signin"><span
									class="glyphicon glyphicon-log-in"></span> Login</a></li>
						</c:if>
						<%-- <c:if test="${empty order.user.userid}">
							<li><a href="/res/signup"><span
									class="glyphicon glyphicon-user"></span> Sign Up</a></li>
							<li><a href="/res/signin"><span
									class="glyphicon glyphicon-log-in"></span> Login</a></li>
						</c:if>
						<c:if test="${not empty order.user.userid}">
						<c:if test="${!notSignedInYet}">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Hello, ${order.user.userid} <span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="/res/youraccount">Your Order History</a></li>
									<li><a href="/res/logout">Logout</a></li>
								</ul></li>
						</c:if>
						<c:if test="${notSignedInYet}">
							<li><a href="/res/signup" onclick="clearForm()"><span
									class="glyphicon glyphicon-user" onclick="clearForm()" ></span> Sign Up</a></li>
							<li><a href="/res/signin"><span
									class="glyphicon glyphicon-log-in"></span> Login</a></li>
						</c:if>						
						</c:if> --%>
						<%-- <c:if test="${not empty order.user.userid}">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Hello, ${order.user.name} <span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="/res/youraccount">Your Order History</a></li>
									<li><a href="/res/logout">Logout</a></li>
								</ul></li>

						</c:if> --%>
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
			
			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control" id="name" name="fullname" required>
			</div>
			
			<!-- <input type='button' value='click me' id='showButton'/> -->
			<div id="userid" class="form-group">
				<label for="userid">User ID (email):</label> <input type="email"
					class="form-control" id="userid" name="userid" required>
			</div>
			<div id="password"class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>
			<h6>Signing up is not required. But signing up will help you keep track of all the past and future orders.</h6>
			<h6>You will be automatically signed after you hit the submit button</h6>

<button type="submit" class="btn btn-info">Submit.</button>
		</div> <!--  END OF COL-MD-2 -->




		
	</form:form>
</body>

</html>