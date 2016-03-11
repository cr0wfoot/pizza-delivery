<nav class="navbar navbar-default">
    <div class="container">
        <ul class="nav navbar-nav">
            <sec:authorize access="isAuthenticated()">
                <li><a href="${pageContext.request.contextPath}/user/profile" >profile</a></li>
            </sec:authorize>
                <li><a href="${pageContext.request.contextPath}/pizza/pizzas" >pizzas</a></li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="${pageContext.request.contextPath}/order/orders" >orders</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><form:form action="${pageContext.request.contextPath}/logout" method="post">
                    <input type="submit" value="logout" />
                </form:form></li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <li><a href="${pageContext.request.contextPath}/user/registration" >registration</a></li>
                <li><a href="${pageContext.request.contextPath}/user/profile" >sign in</a></li>
            </sec:authorize>
        </ul>
        <a href="${pageContext.request.contextPath}/pizza/basket" class="btn btn-success" style="float:right;">
            <span class="badge">${basket.quantity}</span><i class="glyphicon glyphicon-shopping-cart"></i>
        </a>
    </div>
</nav>
