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

.popover{

    max-width:400px !important;
} 

img {
    max-height: 400px;
    height: 100% !important;
    max-width: 100% !important;
    margin-bottom: -5px;
} 
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 100%;
	margin: auto;
	max-height: 600px !important;
}
body {

  padding-top: 60px;

}


@media (max-width: 600px) {


  /* Remove any padding from the body */

  body {

    padding-top: 0;

  }

}
</style>



</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="50">

	<form:form name="customer" modelAttribute="customer" method="post" action="/ordersubmitted">
	
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
		

<div class="col-lg-4">
		<%-- <c:forEach items="${today}" var="menu" varStatus="status">
			<c:choose>
				<c:when test="${status.index =='0'}">
					<Legend>Menu for (${menu.day})</Legend>
				</c:when>
			</c:choose>
		</c:forEach> --%>
		<c:forEach items="${today}" var="menu" varStatus="status">
				<c:choose>
					<c:when test="${status.index =='0'}">
					<c:if test="${not empty name}">
					<Legend>Thank you ${name}</Legend>
					</c:if>
					<c:if test="${empty name}">
					<Legend>Thank you ${customer.fullname}</Legend>
					</c:if>
					<Legend>You Ordered the following for ${menu.day} &nbsp;  ${menu.orderDate}</Legend>
					</c:when>
				</c:choose>
			</c:forEach>


         <div class="table-responsive">
			<div class="checkbox">
				<label> <!-- this <label> is attached to <div class="checkbox"> above -->
					<table class="table table-condensed">
						<thead>
							<tr>
								<th> &nbsp; &nbsp; &nbsp; Dish</th>
								<th> &nbsp; &nbsp; &nbsp; Price</th>
								<th> &nbsp; &nbsp; &nbsp; Images</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${today}" var="menu" varStatus="status">
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.name}</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.price}</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="popover" class="btn btn-info btn-xs" rel="popover" data-img="images/${menu.name}.jpg">${menu.name}</a></td>
								</tr>

							</c:forEach>
						</tbody>						
					</table>
					<h2>Total Price</h2>
					<h3>$${price} (Tax: 8.5%)</h3>
					<p>Your order will be ready to pick up on ${today[0].day}
						after 2pm</p>
					<p>Pay at the counter</p>
					<p>Thank you</p>
				</label>
			</div>	<!-- END OF DIV CHECKBOX -->

		</div>	<!-- END OF RESPONSIVE TABLE -->
</div><!--  END OF COL-XS-4 -->



<%-- <c:if test="${empty order.user.userid}">
		<div class="col-md-2">
			<legend>Enter Name to Pick up Order</legend>
			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control" id="name" name="user.name" required>
			</div>
			<input type="checkbox" name="signup" value="signup" id="showButton"> Click this box to finish the sign up process*<br>
			<!-- <input type='button' value='click me' id='showButton'/> -->
			<div id="useriddiv" class="form-group">
				<label for="userid">Set Your User ID (email):</label> <input type="email"
					class="form-control" id="userid" name="user.userid">
			</div>
			<div id="passworddiv"class="form-group">
				<label for="password">Set Your Password:</label> <input type="password"
					class="form-control" id="password" name="user.password">
			</div>
			<h5>Already have an account? <a href="#" class="btn btn-link" role="button">Sign in here</a></h5>
			<h6>*Signing up is not required. Just click the submit button to finish your order. But signing up will help you keep track of all the past and future orders.</h6>
			<script>			
			$("#useriddiv").hide();
			$("#passworddiv").hide();
			$("#showButton").click(function(e){
			     $("#useriddiv").toggle();
			     $("#passworddiv").toggle();
			     if (document.getElementById("showButton").checked){
			    	  $("div input[id = 'userid']").attr('required','required');
			    	  $("div input[id = 'password']").attr('required','required');
			        }
			        else {
			        	  $("div input[id = 'userid']").removeAttr('required');
				    	  $("div input[id = 'password']").removeAttr('required');
				    	  $("div input[id = 'userid']").val('');
				    	  $("div input[id = 'password']").val('');
				    	
			        }
			});
			</script>

		</div> <!--  END OF COL-MD-2 -->
		
</c:if> --%>
 		<div class="col-lg-3">	
	<Legend>Other Day's Menu</Legend>
	
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

		</div>	<!-- END OF COL-XS-4 -->


				
				<script>
		    $('a[rel=popover]').popover({
		            html : true,
		            trigger : 'hover', 
		            placement : 'bottom',   
		            container: 'body',
		            placement: 'right',
		            content : function() {
		                return '<img src="' + $(this).data('img') + '" />';
		            }
		        });
		    </script>	


	</form:form>
</body>

</html>

