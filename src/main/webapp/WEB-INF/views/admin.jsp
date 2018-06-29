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
        <ul class="nav nav-pills nav-fill" id="pills-tab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="pills-gifts" data-toggle="pill" href="#gifts" role="tab">Gifts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="pills-questions" data-toggle="pill" href="#questions" role="tab">Questions</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="pills-answers" data-toggle="pill" href="#answers" role="tab">Answers</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="pills-tester" data-toggle="pill" href="#tester" role="tab">Tester</a>
            </li>
        </ul>

        <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active" id="gifts" role="tabpanel">gifts</div>
            <div class="tab-pane fade" id="questions" role="tabpanel">questions</div>
            <div class="tab-pane fade" id="answers" role="tabpanel">answers</div>
            <div class="tab-pane fade" id="tester" role="tabpanel">tester</div>
        </div>
    </jsp:body>
</tags:template>
