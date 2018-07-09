<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:template>
    <jsp:body>
        <c:url value="/login" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <c:if test="${param.error != null}">
                <p>
                    Invalid username and password.
                </p>
            </c:if>
            <div class="form-group">
                <label for="username">Username</label>
                <input name="username" id="username" class="form-control" type="text" placeholder="Username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input name="password" id="password" class="form-control" type="password" placeholder="Username">
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Log in</button>
        </form>
    </jsp:body>
</tags:template>