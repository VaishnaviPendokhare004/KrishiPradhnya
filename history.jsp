<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your History</title>
    <link rel="stylesheet" type="text/css" href="ctable.css">
</head>
<body>
    <h2>History</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Serial No.</th>
                <th>Soil Type</th>
                <th>Soil PH</th>
                <th>Nitrogen Level</th>
                <th>Phosphorus Level</th>
                <th>Potassium Level</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Retrieve history data set by the servlet in the "showHistory" attribute
                List<String[]> historyData = (List<String[]>) request.getAttribute("showHistory");
                if (historyData != null && !historyData.isEmpty()) {
                    int serialNo = 1;
                    for (String[] row : historyData) {
            %>
                        <tr>
                            <td><%= serialNo++ %></td>
                            <td><%= row[0] %></td>
                            <td><%= row[1] %></td>
                            <td><%= row[2] %></td>
                            <td><%= row[3] %></td>
                            <td><%= row[4] %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="6" style="color: red;">No data available</td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <br>
    <a href="index.jsp">Back</a>
</body>
</html>