<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title"/></title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2><spring:message code="company.form.header"/></h2>
    </div>
</div>

<div id="container">
    <!-- Form bound with company -->
    <form:form action="save" modelAttribute="company" method="POST">

        <!-- Association with company ID -->
        <form:hidden path="id"/>

        <table style="width:400px">
            <tbody>
            <tr>
                <td><label><spring:message code="name.form.label"/></label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><label><spring:message code="branch.form.label"/></label></td>
                <td><form:input path="branch"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="<spring:message code="save.button"/>" class="save"/> </td>
            </tr>
            </tbody>
        </table>
    </form:form>

    <!-- Back button -->
    <p>
        <input action="action" type="button" onclick="history.go(-1);" value="<spring:message code="back.button"/>" class="button">
    </p>
</div>

</body>
</html>
