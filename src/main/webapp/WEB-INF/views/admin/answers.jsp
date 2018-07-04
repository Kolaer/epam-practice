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
        <hr/>
        <ul id="pagination" class="pagination-sm"></ul>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#Gift</th>
                <th scope="col">#Question</th>
                <th scope="col">Yes</th>
                <th scope="col">No</th>
                <th scope="col">Idk</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="answer" items="${answers}">
                <tr>
                    <th scope="row">
                        <a href="${pageContext.request.contextPath}/admin/gift?id=${answer.gift.id}">
                            <c:out value="${answer.gift.id}"/>
                        </a>
                    </th>
                    <th scope="row">
                        <a href="${pageContext.request.contextPath}/admin/question?id=${answer.question.id}">
                            <c:out value="${answer.question.id}"/>
                        </a>
                    </th>
                    <td>
                        <c:out value="${answer.answerYes}"/>
                    </td>
                    <td>
                        <c:out value="${answer.answerNo}"/>
                    </td>
                    <td>
                        <c:out value="${answer.answerIdk}"/>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/answer?giftId=${answer.gift.id}&questionId=${answer.question.id}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.1/jquery.twbsPagination.min.js"></script>
        <script>
            if(${pageCount} > 0) {
                $('#pagination').twbsPagination({
                    totalPages: ${pageCount},
                    startPage: ${thisPage + 1},
                    visiblePages: 7,
                    onPageClick: function (event, page) {
                        page = page - 1;
                        if (window.location.search.indexOf("offset="+page.toString()) === -1) {
                            window.location = "/admin/answers?offset=" + page.toString();
                        }
                    }
                });
            }
        </script>
    </jsp:body>
</tags:template>
