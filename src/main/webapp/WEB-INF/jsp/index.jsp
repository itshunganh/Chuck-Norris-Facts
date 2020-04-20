<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
    <title>CHUCK NORRIS FACTS</title>
    <style>
        body {
            background-color: #212121;
            border: 1px solid #f5f5f5;

        }
        table, h1 {
            font-family: "Trebuchet MS", sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        h1, h2, form, a {
            color: #FFA726;
            text-align: center;
            padding: 20px;
        }

        th {
            color: #f5f5f5;
            border: 1px solid #121212;
            text-align: center;
            padding: 4px;
        }

        td {
            color: #f5f5f5;
            border: 1px solid #bdbdbd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #323232;
        }
    </style>
</head>
<body>

<h1>CHUCK NORRIS FACTS</h1>

<table>
    <c:forEach var = "row" items = "${hw4}">
        <tr>
            <td>${row.getQuotes()}</td>
            <td>
                <a href="/delete/${row.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="/save">
    <input type="hidden" name="id" value="">
    <input type="hidden" name="quotes" value="${fact}">
    <input type="submit" value="LOAD" style="align-content: center">
</form>

</body>
</html>
