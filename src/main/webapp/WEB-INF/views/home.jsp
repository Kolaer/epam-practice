<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tags:template>
    <jsp:body>
        <h1>Hello <c:out value="${ name }"/></h1>
        <p>This is just an example page.</p>
        <p>Try to add name param to query. Something like this: hello?name=Bob</p>
    </jsp:body>
</tags:template>
