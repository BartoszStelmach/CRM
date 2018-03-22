<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title"/></title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">

</head>

<body>

    <div id="wrapper">
        <div id="header">
            <h2><spring:message code="company.list.header"/></h2>
        </div>
    </div>

    <!-- Companies image -->
    <img src="${pageContext.request.contextPath}/resources/pictures/companies.jpg" height="10%"/>
    <br>

    <!-- Links for switching the language -->
    <spring:message code="languages"/> <a href="?language=en"><spring:message code="language.english.link"/></a> | <a href="?language=pl"><spring:message code="language.polish.link"/></a>

    <div id="container">
        <div id="content">
            <!-- Button for adding a company -->
            <input type="button" value="<spring:message code="add.company.button"/>"
                   onclick="window.location.href='addForm'; return false;"
                   class="button"
            />

            <!-- Link to customers list -->
            <a href="${pageContext.request.contextPath}/customer/list"><spring:message code="customers.link"/></a>

            <!-- Search box -->
            <form:form action="search" method="POST">
                <spring:message code="search.company"/> <input type="text" name="searchName"/>
                <input type="submit" value="<spring:message code="search.button"/>" class="button"/>
            </form:form>

            <!-- Companies table -->
            <table>
                <tr>
                    <th><spring:message code="name.column.header"/></th>
                    <th><spring:message code="branch.column.header"/></th>
                    <th><spring:message code="action.column.header"/></th>
                </tr>

                <!-- Companies loop -->
                <c:forEach var="company" items="${companies}">
                    <!-- Set an "update" link with company id -->
                    <c:url var="updateLink" value="/company/updateForm">
                        <c:param name="companyId" value="${company.id}"/>
                    </c:url>
                    <!-- Set a "delete" link with company id -->
                    <c:url var="deleteLink" value="/company/delete">
                        <c:param name="companyId" value="${company.id}"/>
                    </c:url>
                    <!-- Set a "company" link with company id -->
                    <c:url var="companyLink" value="/company/companyCustomers">
                        <c:param name="companyId" value="${company.id}"/>
                    </c:url>

                    <tr>
                        <!-- Display the company link -->
                        <td>
                            <a href="${companyLink}">${company.name}</a>
                        </td>
                        <td>${company.branch}</td>
                        <!-- Display the update and delete links -->
                        <td>
                            <a href="${updateLink}"><spring:message code="update.link"/></a>
                            |
                            <a href="${deleteLink}"
                               onclick="if (!(confirm('<spring:message code="company.delete.confirmation"/>'))) return false"><spring:message code="delete.link"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
