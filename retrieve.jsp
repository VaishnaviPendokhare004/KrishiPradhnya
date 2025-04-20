<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crop Data Retrieval</title>
    <link rel="stylesheet" type="text/css" href="ctable.css">
</head>
<body>
    <h2>Crop Data</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Serial No.</th>
                <th>Crop Name</th>
                <th>Crop Season</th>
                <th>Crop Duration</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Retrieve crop data set by the servlet in the "cropData" attribute
                List<String[]> cropData = (List<String[]>) request.getAttribute("cropData");
                if (cropData != null && !cropData.isEmpty()) {
                    int serialNo = 1;
                    for (String[] row : cropData) {
            %>
                        <tr>
                            <td><%= serialNo++ %></td>
                            <td><%= row[0] %></td>
                            <td><%= row[1] %></td>
                            <td><%= row[2] %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                        <tr>
                            <td colspan="4" style="color: red;">No data available</td>
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