<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${company.name}</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>${company.name}</h2>
    </div>
</div>

<!-- Links for switching the language -->
<spring:message code="languages"/> <a href="?language=en"><spring:message code="language.english.link"/></a> <a href="?language=pl"><spring:message code="language.polish.link"/></a>

<div id="container">
    <div id="content">

        <!-- Link to customers list -->
        <a href="${pageContext.request.contextPath}/customer/list"><spring:message code="customers.link"/></a>

        <!-- Link to companies list -->
        <a href="${pageContext.request.contextPath}/company/list"><spring:message code="companies.link"/></a>

        <br/><br/>

        <!-- Search box -->
        <form:form action="searchCompanyCustomers" modelAttribute="company" method="POST">
            <spring:message code="search.customer"/> <input type="text" name="searchName"/>
            <input type="submit" value="<spring:message code="search.button"/>" class="button"/>
        </form:form>

        <!-- Customers table -->
        <table>
            <tr>
                <th><spring:message code="first.name.column.header"/></th>
                <th><spring:message code="last.name.column.header"/></th>
                <th><spring:message code="email.column.header"/></th>
                <th><spring:message code="action.column.header"/></th>
            </tr>

            <!-- Customers loop -->
            <c:forEach var="customer" items="${customers}">
                <!-- Set an "update" link with customer id -->
                <c:url var="updateLink" value="/customer/updateForm">
                    <c:param name="customerId" value="${customer.id}"/>
                </c:url>
                <!-- Set a "delete" link with customer id -->
                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${customer.id}"/>
                </c:url>
                <tr>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.email}</td>
                    <!-- Display the update and delete links -->
                    <td>
                        <a href="${updateLink}"><spring:message code="update.link"/></a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('<spring:message code="customer.delete.confirmation"/>'))) return false"><spring:message code="delete.link"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>