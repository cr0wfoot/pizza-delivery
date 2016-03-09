<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="pizzas.title" /></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </head>
    <body>
        <br>
        <div class="container">
            <div class="panel panel-primary">
                <div class="panel-heading"><a href="./basket" class="btn btn-success"><span class="badge">${basket.quantity}</span><i class="glyphicon glyphicon-shopping-cart"></i></a></div>
                <table class="panel-body table table-striped table-bordered table-hover">
                    <thead class="thead-inverse">
                        <tr>
                            <th>#</th>
                            <th><spring:message code="entity.pizza.name" /></th>
                            <th><spring:message code="entity.pizza.type" /></th>
                            <th><spring:message code="entity.pizza.price" /></th>
                            <th></th>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <th></th>
                                <th></th>
                            </sec:authorize>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${pizzas}" >
                        <tr>
                            <th>${p.id}</th>
                            <td>${p.name}</td>
                            <td>${p.type}</td>
                            <td>${p.price}</td>
                            <td>
                                <form:form action="basket-add" method="post">
                                    <input type="hidden" name="id" value="${p.id}"/>
                                    <label for="submitAdd${p.id}" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i></label>
                                    <input id="submitAdd${p.id}" type="submit" name="add" value="add" class="hidden" />
                                </form:form>
                            </td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td>
                                    <form:form action="edit" method="post">
                                        <input type="hidden" name="id" value="${p.id}"/>
                                        <label for="submitEdit${p.id}" class="btn btn-default">...<i class="glyphicon glyphicon-pencil"></i></label>
                                        <input id="submitEdit${p.id}" type="submit" name="edit" value="edit" class="hidden" />
                                    </form:form>
                                </td>
                                <td>
                                    <form:form action="remove" method="post">
                                        <input type="hidden" name="id" value="${p.id}"/>
                                        <label for="submitRemove${p.id}" class="btn btn-default"><i class="glyphicon glyphicon-trash"></i></label>
                                        <input id="submitRemove${p.id}" type="submit" name="remove" value="del" class="hidden" />
                                    </form:form>
                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>      
        </div>
    </body>
</html>