<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/gifts" class="nav-link">Gifts</a>
                    </li>
                    <li class="nav-item active">
                        <a href="#" class="nav-link">Admin panel</a>
                    </li>
                </ul>
                <form class="form-inline" action="${pageContext.request.contextPath}/logout" method="post">
                    <input class="btn btn-outline-primary" type="submit" value="Log out" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </nav>
        <hr/>
        <ul class="nav nav-pills nav-fill">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/admin/gifts" class="nav-link">Gifts</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/admin/questions" class="nav-link">Questions</a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link active">Answers</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/admin/tester" class="nav-link">Tester</a>
            </li>
        </ul>
        <h1>Answers</h1>
    </jsp:body>
</tags:template>
