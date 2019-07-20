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
.popover {
	max-width: 400px !important;
}

img {
	max-height: 400px;
	height: 100% !important;
	max-width: 100% !important;
	margin-bottom: -5px;
}

body {
	padding-top: 60px;
}

@media ( max-width : 600px) {
	/* Remove any padding from the body */
	body {
		padding-top: 0;
	}
}
</style>



</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="50" onload="dateParser();"> 

	<form:form name="customer" modelAttribute="customer" method="post" action="/signupcomplete">

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
					</ul>
				</div>
				
			</div>
		</nav>
		</br>
		</br>
		</br>
		</br>
		</br>
		<div class="col-md-4">
			<c:forEach items="${customer.menus}" var="menu" varStatus="status">
				<c:choose>
					<c:when test="${status.index =='0'}">
					<Legend>Thank you ${customer.fullname}</Legend>
					<script type="text/javascript">
				function dateParser(){
				 var value = "${menu.orderDate}";
			
				 var words = value.split(" ");
				 document.getElementById("dateid").innerHTML = "You ordered the following for ${menu.day} (" + words[1]  + " " + words[2] + " " + words[5] + ")" ;
				}
				</script>
				<Legend id="dateid"></Legend>
					</c:when>
				</c:choose>
			</c:forEach>


			<div class="table-responsive">
				<div class="checkbox">

					<table class="table table-condensed">
						<thead>
							<tr>
								<th>Dish</th>
								<th>Price</th>
								<th>Images</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${customer.menus}" var="menu" varStatus="status">
								<tr>
									<td>${menu.name}</td>
									<td>${menu.price}</td>
									<td><a id="popover" class="btn btn-info btn-xs"
										rel="popover" data-img="images/${menu.name}.jpg">${menu.name}</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>



					<h2>Total Price</h2>
					<h3>$${price} (Tax: 8.5%)</h3>
					<p>Your order will be ready to pick up on ${customer.menus[0].day}
						after 2pm</p>
					<p>Pay at the counter</p>
					<p>Thank you</p>

				</div> <!-- END OF CHECKBOX -->
				
			</div><!-- END OF RESPONSIVE TABLE -->
			
</div><!--  END OF COL-XS-4 -->

<c:if test="${empty customer.userid}">

		<div class="col-md-2">

			<input type="checkbox" name="signup" value="signup" id="showButton"> Click this box to finish the sign up process*<br>
			<!-- <input type='button' value='click me' id='showButton'/> -->
			<div id="useriddiv" class="form-group">
				<label for="userid">User ID (email):</label> <input type="email"
					class="form-control" id="userid" name="userid">
			</div>
			<div id="passworddiv"class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password">
			</div>
			<button type="submit" id="submitbutton" class="btn btn-success">Submit</button>
			<h6>*Signing up is not required. Just click the submit button to finish your order.</h6>
			<h6>*But signing up will help you keep track of all the past and future orders. </h6>
			<h6>*Finishing Signing Up process from this very page will save the last order only.</h6>
			<script>			
			$("#useriddiv").hide();
			$("#passworddiv").hide();
			$("#submitbutton").hide();
			$("#showButton").click(function(e){
			     $("#useriddiv").toggle();
			     $("#passworddiv").toggle();
			     $("#submitbutton").toggle();
			     if (document.getElementById("showButton").checked){
			    	  $("div input[id = 'userid']").attr('required','required');
			    	  $("div input[id = 'password']").attr('required','required');
			        }
			        else {
			        	  $("div input[id = 'userid']").removeAttr('required');
				    	  $("div input[id = 'password']").removeAttr('required');			          
			        }
			});
			</script>

		</div> <!--  END OF COL-MD-2 -->
<!-- 		<div class="col-md-2">

			<input type="checkbox" name="signup" value="signup" id="showButton"> Click this box to finish the sign up process*<br>
			<input type='button' value='click me' id='showButton'/>
			<div id="userid" class="form-group">
				<label for="userid">User ID (email):</label> <input type="email"
					class="form-control" id="userid" name="user.userid">
			</div>
			<div id="password"class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="user.password">
			</div>
			<button type="submit" id="submitbutton" class="btn btn-success">Submit</button>
			<h5>Already have an account? <a href="#" class="btn btn-link" role="button">Sign in here</a></h5>
			<h6>*Signing up is not required. Just click the submit button to finish your order. But signing up will help you keep track of all the past and future orders.</h6>
			<script>			
			$("#userid").hide();
			$("#password").hide();
			$("#showButton").click(function(e){
			     $("#userid").toggle();
			     $("#password").toggle();
			});
			</script>

		</div>  END OF COL-MD-2 -->
		
</c:if>

<c:if test="${not empty customer.userid && !alreadySignedUp}">
   <div class="col-lg-4">
			
			
			<h5>Thank you for signing up.</h5>
			<h5>Your username is: ${customer.userid}</h5>
			<h5>Next time when you want to order something</h5>
			<h5>First Sign in then goto menus to choose food</h5>


		</div> <!--  END OF COL-MD-2 -->
</c:if>

			


					<div class="col-lg-2">	
	<Legend>Check Menus Again</Legend>
	
	<div class="table-responsive">
	<table class="table table-condensed">
	<tbody>
							<c:forEach items="${other}" var="days" varStatus="status">
								<tr>
									
									<td><a href="/menu/${days}" class="btn btn-default" role="button">Check ${days} menu</a></td>

								</tr>
								
							</c:forEach>
	</tbody>		
	
	</table>  <!-- END OF TABLE TABLE-CONDENSED -->
	</div> 	<!-- END OF TABLE RESPONSIVE -->

			<script>
				$('a[rel=popover]').popover({
					html : true,
					trigger : 'hover',
					placement : 'bottom',
					container : 'body',
					placement : 'right',
					content : function() {
						return '<img src="' + $(this).data('img') + '" />';
					}
				});
			</script>
	</form:form>
</body>

</html>