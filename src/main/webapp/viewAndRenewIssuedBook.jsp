<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Issued Books & Renew</title>
        <style>
            table {
                border-collapse: collapse;
                width: 90%;
                margin: 20px auto;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
            }
            th {
                background-color: #f0f0f0;
            }
            .msg {
                text-align: center;
                font-weight: bold;
            }
            .success {
                color: green;
            }
            .error {
                color: red;
            }
            button {
                padding: 6px 12px;
            }
        </style>
    </head>
    <body>

        <h2 style="text-align:center;">My Issued Books</h2>

        <c:if test="${not empty message}">
            <div class="msg success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="msg error">${error}</div>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Book Name</th>
                    <th>Author</th>
                    <th>Issue Date</th>
                    <th>Due Date</th>
                    <th>Renew Count</th>
                    <th>Renew</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${issuedBooks}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <td>${book.author}</td>
                        <td>${book.issueDate}</td>
                        <td>${book.dueDate}</td>
                        <td>${book.renewCount}</td>
                        <td>
                            <form method="post" action="RenewBookServlet">
                                <input type="hidden" name="id" value="${book.id}" />
                                <button type="submit">Renew</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div style="text-align:center; margin-top:20px;">
            <a href="studentDashboard.jsp">⬅ Back to Dashboard</a>
        </div>

    </body>
</html>
