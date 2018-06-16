<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tags:template>
    <jsp:body>
        <h1>Index</h1>
        <p>Check out <a href="<c:url value="/hello"/>">this</a>.</p>
    </jsp:body>
</tags:template>
