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
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/gifts" class="nav-link">Gifts</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin" class="nav-link">Admin panel</a>
                    </li>
                </ul>
            </div>
        </nav>
        <hr/>
        Here is our guess:
        <ul class="list-unstyled">
            <li>${gift.name}</li>
            <li>${gift.description}</li>
            <li>
                <a href="${gift.url}">
                        ${gift.url}
                </a>
            </li>
        </ul>
        <a href="${pageContext.request.contextPath}/gameSuccess">I like it</a> <br/>
        <a href="${pageContext.request.contextPath}/gameFail">Try again</a>
    </jsp:body>
</tags:template>
