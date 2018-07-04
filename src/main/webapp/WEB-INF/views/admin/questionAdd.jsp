<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:template>
    <jsp:body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Menu</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                    <input class="btn btn-outline-primary" type="submit" value="Log out"/>
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
                <a href="#" class="nav-link active">Questions</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/admin/answers" class="nav-link">Answers</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/admin/tester" class="nav-link">Tester</a>
            </li>
        </ul>
        <hr/>
        <form action="${pageContext.request.contextPath}/admin/questionAdd" method="post">
            <div class="form-group">
                <label for="questionQuestion">Question</label>
                <input name="question" id="questionQuestion" class="form-control" type="text" placeholder="Question">
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </jsp:body>
</tags:template>