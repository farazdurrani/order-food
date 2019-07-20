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

	<form:form name="signup" modelAttribute="customer" method="post" action="/youraccount"> <!-- action="/menu" -->

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
		
		<p> Thank you ${customer.fullname} for signing up. Your account info is saved.</p>
		<p> Your userid is: ${customer.userid} </p>
		<p> Your userid and password will be emailed to you for your records</p>


		<button type="submit" class="btn btn-success">Click here to goto your accounts page</button>
		<a href="/menu" class="btn btn-info" role="button">Or click here to check menus</a>
	</form:form>
</body>

</html>
