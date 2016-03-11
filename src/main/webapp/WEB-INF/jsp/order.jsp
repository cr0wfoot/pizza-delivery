<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="order.title" /></title>
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
                    <spring:message code="order.message" />
                </div>
                <div class="panel-body"> 
                    <form:form action="newstate" method="post">
                        №: ${order.id}<br>
                        <input type="hidden" name="id" value="${order.id}"/>
                        <spring:message code="entity.order.state" />: ${order.state}<br>
                        <select class="form-control" name="state">
                            <option selected='selected'></option>
                            <c:forEach var="os" items="${orderStates}" >
                                <option>${os}</option>
                            </c:forEach>
                        </select><br>
                        <spring:message code="entity.order.price" />: ${order.price}<br>
                        <div class="form-actions">
                            <input type="submit" class="btn btn-block btn-primary btn-default" value="<spring:message code="button.save" />"/>
                        </div>
                    </form:form><br>
                    <c:forEach var="od" items="${order.details}" >
                        ${od}<br>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>