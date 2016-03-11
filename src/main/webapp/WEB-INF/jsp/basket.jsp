<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="basket.title" /></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="navi.jsp" %>
        <br>
        <div class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <spring:message code="basket.total.price" />:
                    <fmt:formatNumber value="${basket.price}" maxFractionDigits="2" />
                </div>
                <table class="panel-body table table-striped table-bordered table-hover">
                    <thead class="thead-inverse">
                        <tr>
                            <th>#</th>
                            <th><spring:message code="entity.pizza.name" /></th>
                            <th><spring:message code="entity.pizza.type" /></th>
                            <th><spring:message code="entity.pizza.price" /></th>
                            <th><spring:message code="entity.pizza.quantity" /></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${basket.pizzas}" >
                        <tr>
                            <th>${p.key.id}</th>
                            <td>${p.key.name}</td>
                            <td>${p.key.type}</td>
                            <td>${p.key.price}</td>
                            <td>${p.value}</td>
                            <td>
                                <form:form action="./basket-del" method="post">
                                    <input type="hidden" name="id" value="${p.key.id}"/>
                                    <label for="submitDel${p.key.id}" class="btn btn-default"><i class="glyphicon glyphicon-minus"></i></label>
                                    <input id="submitDel${p.key.id}" type="submit" name="del" value="del" class="hidden" />
                                </form:form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>   
            <form:form action="${pageContext.request.contextPath}/order/create" method="post" commandName="customerDto">
                
                <spring:message code="entity.address.city" var="_city"/>
                <form:input type="edit" name="city" path="city"  placeholder="${_city}"  />
                <form:errors path="city"/>
                
                <spring:message code="entity.address.street" var="_street"/>
                <form:input type="edit" name="street" path="street"  placeholder="${_street}"  />
                <form:errors path="street"/>
                
                <spring:message code="entity.address.appartment" var="_appartment"/>
                <form:input type="edit" name="appartment" path="appartment"  placeholder="${_appartment}"  />
                <form:errors path="appartment"/>
                
                <input type="submit" name="order" value="<spring:message code="button.make.order" />" />
            </form:form>
        </div>
    </body>
</html>