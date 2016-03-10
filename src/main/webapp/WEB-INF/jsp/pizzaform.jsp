<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="pizzaform.title" /></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </head>
    <body>
        <br>
        <div class="container">
            <div class="panel panel-info">
                <div class="panel-heading"><spring:message code="pizzaform.message" /></div>
                <div class="panel-body">
                    <form:form action="save" method="post" class="form-inline navbar-form navbar-left">
                        <input type="hidden" name="id" value="${pizza.id}"/>
                        <div class="form-group">
                            <spring:message code="entity.pizza.name" var="_name"/>
                            <label class="sr-only" for="inputPizzaName">${_name}</label>
                            <input type="text" name="name" value="${pizza.name}" class="form-control" placeholder="${_name}" id="inputPizzaName" />
                        </div>
                        <div class="form-group">
                            <spring:message code="entity.pizza.type" var="_type"/>
                            <label class="sr-only" for="inputPizzaType">${_type}</label>
                            <select class="form-control" name="type">
                                <c:forEach var="pt" items="${pizzaTypes}" >
                                    <option>${pt}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group">
                            <spring:message code="entity.pizza.price" var="_price"/>
                            <input type="text" name="price" value="${pizza.price}" class="form-control" placeholder="${_price}" id="inputPizzaPrice" />
                            <span class="input-group-addon">$</span>
                        </div>
                        <input type="submit" name="save" value="<spring:message code="button.save" />" class="btn btn-default"/>
                    </form:form>   
                </div>
            </div>
        </div>
    </body>
</html>