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
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 100%;
      margin: auto;
      max-height: 600px !important;
  }
</style>

</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="50">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/main">Best Food Restaurant</a>
			</div>
<!-- 			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="/res/main">HOME</a></li>
					<li><a href="/res/menu">MENU</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
			</div> -->
							<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="/main">HOME</a></li>
						<li><a href="/menu">MENU</a></li>
						<c:if test="${order.user.signedin}">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Hello, ${order.user.name} <span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="/youraccount">Your Order History</a></li>
									<li><a href="/logout">Logout</a></li>
								</ul></li>
						</c:if>
						<c:if test="${!order.user.signedin}">
						
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
					</ul>
				</div>
			
<br>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="images/biryanee.jpg" alt="Chania" width="460"
							height="345">
						<div class="carousel-caption">
							<h3>
								<a href="/menu" class="btn btn-info" role="button">Enter The Site...</a>
							</h3>
						</div>
					</div>

					<div class="item">
        <img src="images/kababs.jpg" alt="Chania" width="460" height="345">
        <div class="carousel-caption">
							<h3>
								<a href="menu" class="btn btn-info" role="button">Enter The Site...</a>
							</h3>
						</div>
      </div>
    
      <div class="item">
        <img src="images/Nihari.jpg" alt="Flower" width="460" height="345">
        <div class="carousel-caption">
							<h3>
								<a href="menu" class="btn btn-info" role="button">Enter The Site...</a>
							</h3>
						</div>
      </div>

    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
		</div>
<p></p>
  <p></p>
   <p></p>
    <p></p>
     <p></p>
      <p></p>
       <p></p>
	</nav>

</body>
</html>
