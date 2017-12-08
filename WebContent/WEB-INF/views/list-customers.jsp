<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>List Customers</title>

<!-- reference our style sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<style type="text/css">
td, th {
	text-align: left;
	padding-left: 10px;
}

a {
	text-decoration: none;
}

.button-upd {
	background: green;
	color: #fff;
	border-radius: 3px;
	padding: 4px 6px;
}

.button-del {
	background: red;
	color: #fff;
	border-radius: 3px;
	padding: 4px 6px;
}
</style>

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add Customer -->

			<input type="button" value="Add Customer"
				onclick="window.location.href='addCustomer'; return false;"
				class="add-button" />

			<!--  add our html table here -->

			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">

					<!-- construct an update link with customer id -->
					<c:url var="updatelink" value="/customer/update">
						<c:param name="customerId" value="${tempCustomer.id }" />
					</c:url>

					<!-- construct a delete link with customer id -->
					<c:url var="deletelink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id }" />
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td><a class="button-upd" href="${updatelink}">Update</a></td>
						<td><a class="button-del" href="${deletelink}"
							onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>









