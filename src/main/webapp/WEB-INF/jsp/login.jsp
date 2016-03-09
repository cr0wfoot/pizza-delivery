<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="login.title" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </head>
    <body><br>
        <div class="container">
            <form:form action="login" method="post" class="form-signin">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p><spring:message code="login.invalid" />.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p><spring:message code="login.successful" />.</p>
                    </div>
                </c:if>
                <div class="input-group input-sm">
                    <spring:message code="entity.user.login" var="u.login"/>
                    <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="${u.login}" required>
                </div>
                <div class="input-group input-sm">
                    <spring:message code="entity.user.pass" var="u.pass"/>
                    <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                    <input type="password" class="form-control" id="password" name="password" placeholder="${u.pass}" required>
                </div>

                <div class="form-actions">
                    <input type="submit" class="btn btn-block btn-primary btn-default" value="<spring:message code="button.log.in" />">
                </div>
            </form:form>
        </div>
    </body>
</html>