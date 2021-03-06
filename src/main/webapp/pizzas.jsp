<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <h1>List of all pizzas</h1>
    <table class="table">
        <thead class="thead-inverse">
        <tr>
            <th scope="row">id</th>
            <td>Name</td>
            <td>Price</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pizzas}" var="pizza">
            <tr>
                <th scope="row"><c:out value="${pizza.id}" /></th>
                <td><c:out value="${pizza.name}" /></td>
                <td><c:out value="${pizza.price}" /></td>
                <td>
                    <form id="${pizza.id}"  method="POST">
                        <input id="id" name="id" type="hidden" value="${pizza.id}"/>
                        <input class="btn btn-mini btn-danger " type="submit" value="Delete"/>
                        <a class="btn btn-mini " href="pizzas/remove/1">Remove</a>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>