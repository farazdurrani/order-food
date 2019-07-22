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

	<form:form name="adminsignup" modelAttribute="admin" method="post"
		action="signin">


		<!-- <input type='button' value='click me' id='showButton'/> -->
		<div id="userid" class="form-group">
			<label for="userid">Admin ID :</label> <input type="text"
				class="form-control" id="userid" name="username" required>
		</div>
		<div id="password" class="form-group">
			<label for="password">Password:</label> <input type="password"
				class="form-control" id="password" name="password" required>
		</div>

		<button type="submit" class="btn btn-info">Submit</button>


	</form:form>
</body>
</html>