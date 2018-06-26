<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:template>
    <jsp:body>
        <h1>Hello <c:out value="${ name }"/></h1>
        <p>This is just an example page.</p>
        <p>Try to add name param to query. Something like this: hello?name=Bob</p>
        <c:forEach var="gift" items="${gifts}">
            <c:out value="${gift.toString()}"/> <br/>
        </c:forEach>
        <hr/>
        <form action="/addGift">
            <input type="text" name="name" placeholder="Name">
            <input type="text" name="desc" placeholder="Description">
            <input type="url" name="url" placeholder="URL">
            <br/>
            <input type="submit">
        </form>
    </jsp:body>
</tags:template>
