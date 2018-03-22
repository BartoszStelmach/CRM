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
            <h2><spring:message code="customer.form.header"/></h2>
        </div>
    </div>

    <div id="container">
        <!-- Form bound with customer -->
        <form:form action="save" modelAttribute="customer" method="POST">

            <!-- Association with customer ID -->
            <form:hidden path="id"/>

            <table style="width:400px">
                <tbody>
                    <tr>
                        <td><label><spring:message code="first.name.form.label"/></label></td>
                        <td><form:input path="firstName"/></td>
                    </tr>
                    <tr>
                        <td><label><spring:message code="last.name.form.label"/></label></td>
                        <td><form:input path="lastName"/></td>
                    </tr>
                    <tr>
                        <td><label><spring:message code="email.form.label"/></label></td>
                        <td><form:input path="email"/></td>
                    </tr>
                    <tr>
                        <td><label><spring:message code="company.form.label"/></label></td>
                        <td>
                            <form:select path="company.id">
                                <select:options items="${companies}" itemValue="id" itemLabel="name"/>
                            </form:select>
                        </td>
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
            <input action="action" type="button" onclick="history.go(-1);" value="<spring:message code="back.button"/>" class="button"/>
        </p>
    </div>
</body>
</html>
