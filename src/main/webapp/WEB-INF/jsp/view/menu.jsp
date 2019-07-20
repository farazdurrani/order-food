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
	data-offset="50" onload="dateParser();">

	<form:form name="customer" onsubmit="return checkAnyMenuIsSelectedIfNameIsThere(${fn:length(today)})" modelAttribute="customer" method="post" action="/ordersubmitted">
	
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
				<%-- <div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Hello, ${order.user.name}
    <a dropdown-toggle data-toggle="dropdown"><span class="caret"></span>
								Hello, ${order.user.name}</a>
    <!--<span class="caret"></span> </button> -->
    <ul class="dropdown-menu">
      <li><a href="#">Your Order History</a></li>
      <li><a href="/res/logout">Logout</a></li>
    </ul>
  </div>
						<li><a href="#"><span class="glyphicon glyphicon-user"></span>
								Hello, ${order.user.name}</a></li> --%>
			</div>
		</nav>
		</br>
		</br>
		</br>
		</br>
		</br>
		

<div class="col-md-4">
	<fieldset>
		<c:forEach items="${today}" var="menu" varStatus="status">
			<c:choose>
				<c:when test="${status.index =='0'}">
					<script type="text/javascript">
				function dateParser(){
				 var value = "${menu.orderDate}";
			
				 var words = value.split(" ");
				 document.getElementById("dateid").innerHTML = "Menu for ${menu.day} (" + words[1]  + " " + words[2] + " " + words[5] + ")" ;
				}
				</script>
				<Legend id="dateid"></Legend>
					<span id="selectionError" style="display: none;">You must choose at least one dish from the menu below to proceed further. If you don't want to place an order and just want to sign up, use the Sign Up button at the top right. Thank you</span>
				</c:when>
			</c:choose>
		</c:forEach>
	</fieldset>

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
									<td><form:checkbox path="menus" value="${menu}"
											label="${menu.name}" id="00${status.index}" onclick="countmoney('00${status.index}')" /></td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.price}</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="popover" class="btn btn-info btn-xs" rel="popover" data-img="/images/${menu.name}.jpg">${menu.name}</a></td>
								</tr>

							</c:forEach>
						</tbody>						
					</table>
				</label>
			</div>	<!-- END OF DIV CHECKBOX -->
										<label>Total </label>
                			<label><input value="$0.00" readonly="readonly" type="text" id="total"/></label>
                			</br>
                			<button type="submit" class="btn btn-success">Submit</button>
		</div>	<!-- END OF RESPONSIVE TABLE -->
</div><!--  END OF COL-XS-4 -->



<c:if test="${empty customer.userid}">
		<div class="col-md-4">
			<legend>Enter Name to Pick up Order</legend>
			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control" id="name" name="fullname" required>
			</div>
			<input type="checkbox" name="signup" value="signup" id="showButton"> Click this box to finish the sign up process*<br>
			<!-- <input type='button' value='click me' id='showButton'/> -->
			<div id="useriddiv" class="form-group">
				<label for="userid">Set Your User ID (email):</label> <input type="email"
					class="form-control" id="userid" name="userid">
			</div>
			<div id="passworddiv"class="form-group">
				<label for="password">Set Your Password:</label> <input type="password"
					class="form-control" id="password" name="password">
			</div>			
			<h6>*Signing up is not required. Just click the submit button to finish your order.</h6>
			<h6>*But signing up will help you keep track of all the past and future orders. </h6>
			<h6>*Finishing Signing Up process from this very page will save the last order only.</h6>
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
		
</c:if>
 		<div class="col-md-4">	
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
		

<div class="col-sm-2">	
<a href="http://stackoverflow.com/users/4828463/faraz-durrani">
<img src="http://stackoverflow.com/users/flair/4828463.png?theme=dark" width="208" height="58" alt="profile for Faraz Durrani at Stack Overflow, Q&amp;A for professional and enthusiast programmers" title="profile for Faraz Durrani at Stack Overflow, Q&amp;A for professional and enthusiast programmers">
</a>
</div>	<!-- END OF COL-XS-4 -->
		<script>
			var total = 0;
			function countmoney(x) {
				document.getElementById('selectionError').style.display = "none";
				var value = document.getElementById(x).value;
				var price = value.split('=');
				if (document.getElementById(x).checked) {
					total += parseFloat(price[3]);
				} else if (!document.getElementById(x).checked) {
					total = total - parseFloat(price[3]);
				}
				document.getElementById("total").value = "$" + total.toFixed(2);
			}
		</script>


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
		<script>
			function checkAnyMenuIsSelectedIfNameIsThere(x) {
				var value = document.getElementById('name').value;
				var uncheckboxes = 0;
				 if (value.length > 0) {
					 for (i = 0; i < x; i++) {
						 if (!document.getElementById("00"+i).checked){
							 uncheckboxes = uncheckboxes + 1;
						 } 
						}
				if (uncheckboxes == x){ 
					document.getElementById('selectionError').style.display = "block"; 
					document.getElementById('selectionError').style.background ='#e35152'; 
					return false;
					}
				else { return true;}
					
				 }
			}
		</script>
		<!--  <script>
function whatDayNameIsIt() {
    var d = new Date();
    var weekday = new Array(7);
    weekday[0] = "Sunday";
    weekday[1] = "Monday";
    weekday[2] = "Tuesday";
    weekday[3] = "Wednesday";
    weekday[4] = "Thursday";
    weekday[5] = "Friday";
    weekday[6] = "Saturday";

    var n = weekday[d.getDay()];
    return n;
}
</script> -->

	</form:form>
</body>

</html>

