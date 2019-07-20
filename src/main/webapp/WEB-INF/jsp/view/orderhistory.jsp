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

.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 100%;
	margin: auto;
	max-height: 600px !important;
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
	data-offset="50">



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
		</div>
	</nav>
	</br>
	</br>
	</br>
	</br>
	</br>


	<div class="col-lg-4">
		<c:forEach items="${today}" var="menu" varStatus="status">
			<c:choose>
				<c:when test="${status.index =='0'}">
					<Legend>ORDER HISTORY</Legend>
					<Legend>You Ordered the following for
						${today[status.index].day}</Legend>
				</c:when>
			</c:choose>
		</c:forEach>


		<div class="table-responsive">
			<div class="checkbox">
				<label> <!-- this <label> is attached to <div class="checkbox"> above -->
					<table class="table table-condensed">
						<thead>
							<tr>
								<th>&nbsp; &nbsp; &nbsp; Dish</th>
								<th>&nbsp; &nbsp; &nbsp; Price</th>
								<th>&nbsp; &nbsp; &nbsp; Images</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${today}" var="menu" varStatus="status">
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.name}</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.price}</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="popover"
										class="btn btn-info btn-xs" rel="popover"
										data-img="images/${menu.name}.jpg">${menu.name}</a></td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					<h2>Total Price</h2>
					<h3>$${price} (Tax: 8.5%)</h3>
					<p>Your order will be ready to pick up on ${today[0].day} after
						2pm</p>
					<p>Pay at the counter</p>
					<p>Thank you</p>
				</label>
			</div>
			<!-- END OF DIV CHECKBOX -->

		</div>
		<!-- END OF RESPONSIVE TABLE -->
	</div>
	<!--  END OF COL-XS-4 -->

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



</body>

</html>

