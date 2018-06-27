<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:template>
    <jsp:body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Menu</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/" class="nav-link">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a href="#" class="nav-link">Gifts</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin" class="nav-link">Admin panel</a>
                    </li>
                </ul>
            </div>
        </nav>
        <hr/>
        List of all gifts: <br/>
        <c:forEach var="gift" items="${gifts}">
            <c:out value="${gift.toString()}"/> <br/>
        </c:forEach>
    </jsp:body>
</tags:template>
