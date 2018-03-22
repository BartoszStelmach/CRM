<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="title"/></title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">

</head>

<body>

    <div id="wrapper">
        <div id="header">
            <h2><spring:message code="customer.list.header"/></h2>
        </div>
    </div>

    <!-- Customers image -->
    <img src="${pageContext.request.contextPath}/resources/pictures/customers.jpg" height="10%"/>
    <br>

    <!-- Links for switching the language -->
    <spring:message code="languages"/> <a href="?language=en"><spring:message code="language.english.link"/></a> | <a href="?language=pl"><spring:message code="language.polish.link"/></a>

    <div id="container">
        <div id="content">
            <!-- Button for adding a customer -->
            <input type="button" value="<spring:message code="add.customer.button"/>"
                   onclick="window.location.href='addForm'; return false;"
                   class="button"
            />

            <!-- Link to companies list -->
            <a href="${pageContext.request.contextPath}/company/list"><spring:message code="companies.link"/></a>

            <!-- Search box -->
            <form:form action="search" method="POST">
                <spring:message code="search.customer"/> <input type="text" name="searchName"/>
                <input type="submit" value="<spring:message code="search.button"/>" class="button"/>
            </form:form>

            <!-- Customers table -->
            <table>
                <tr>
                    <th><spring:message code="first.name.column.header"/></th>
                    <th><spring:message code="last.name.column.header"/></th>
                    <th><spring:message code="company.column.header"/></th>
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
                    <!-- Set a "company" link with company id -->
                    <c:url var="companyLink" value="/company/companyCustomers">
                        <c:param name="companyId" value="${customer.company.id}"/>
                    </c:url>

                    <tr>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <!-- Display the company link -->
                        <td>
                            <a href="${companyLink}">${customer.company.name}</a>
                        </td>
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

        <!-- Link to statute.pdf -->
        <p>
            <a href=${pageContext.request.contextPath}/customer/statute><spring:message code="company.statute.link"/></a>
        </p>
    </div>

</body>
</html>
