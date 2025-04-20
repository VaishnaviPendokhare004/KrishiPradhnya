package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dataEntry")
public class ShowCrop extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve and trim the action parameter first
        String action = request.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            action = "search";
        } else {
            action = action.trim();
        }

        // Set the content type for the response
        response.setContentType("text/html");

        // If the user wants to see history only, perform only the history branch.
        if (action.equalsIgnoreCase("history")) {
            try {
                // Load the MySQL JDBC driver and establish the connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/student", "root", "Vvp555&...");

                // ------------------------------------------------------------
                // Retrieve history data using the stored procedure showhistory()
                List<String[]> historyList = new ArrayList<>();
                try (CallableStatement csHistory = con.prepareCall("{call showhistory()}");
                     ResultSet rsHistory = csHistory.executeQuery()) {
                    while (rsHistory.next()) {
                        String[] row = new String[5];
                        row[0] = rsHistory.getString("stype");
                        row[1] = rsHistory.getString("sph");
                        row[2] = rsHistory.getString("snitrogen");
                        row[3] = rsHistory.getString("sphosphorus");
                        row[4] = rsHistory.getString("spotassium");
                        historyList.add(row);
                    }
                }

                con.close();

                // Set the history data as request attribute
                request.setAttribute("showHistory", historyList);

                // Forward to history.jsp (which will display the history table)
                RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
                rd.forward(request, response);
                return; // Exit as we already forwarded the request
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.getWriter().println("<script>alert('Error: " + e.getMessage() + "');</script>");
            }
        } else {
            // Else branch: process the crop search request.
            // Retrieve form parameters required for crop search
            String typ = request.getParameter("type");
            String ph = request.getParameter("ph");
            String nitro = request.getParameter("nitrogen");
            String phosp = request.getParameter("phosphorus");
            String potas = request.getParameter("potassium");

            try {
                // Load the MySQL JDBC driver and open the connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/student", "root", "Vvp555&...");

                // ------------------------------------------------------------
                // 1. Insert new soil data (for crop search) using stored procedure insertUserSoilData
                try (CallableStatement csInsert = con.prepareCall("{call insertUserSoilData(?,?,?,?,?)}")) {
                    csInsert.setString(1, typ);
                    csInsert.setString(2, ph);
                    csInsert.setString(3, nitro);
                    csInsert.setString(4, phosp);
                    csInsert.setString(5, potas);
                    csInsert.executeUpdate();
                }

                // ------------------------------------------------------------
                // 2. Retrieve crop data that matches the given soil parameters
                List<String[]> cropList = new ArrayList<>();
                String query = "SELECT cname, cseason, cduration FROM cropdata " +
                        "WHERE stype=? AND sph=? AND snitrogen=? AND sphosphorus=? AND spotassium=?;";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, typ);
                    pstmt.setString(2, ph);
                    pstmt.setString(3, nitro);
                    pstmt.setString(4, phosp);
                    pstmt.setString(5, potas);
                    try (ResultSet rsCrop = pstmt.executeQuery()) {
                        while (rsCrop.next()) {
                            String[] row = new String[3];
                            row[0] = rsCrop.getString("cname");
                            row[1] = rsCrop.getString("cseason");
                            row[2] = rsCrop.getString("cduration");
                            cropList.add(row);
                        }
                    }
                }

                // ------------------------------------------------------------
                // 3. Optionally, you may still retrieve history data as well (if desired).
                List<String[]> historyList = new ArrayList<>();
                try (CallableStatement csHistory = con.prepareCall("{call showhistory()}");
                     ResultSet rsHistory = csHistory.executeQuery()) {
                    while (rsHistory.next()) {
                        String[] row = new String[5];
                        row[0] = rsHistory.getString("stype");
                        row[1] = rsHistory.getString("sph");
                        row[2] = rsHistory.getString("snitrogen");
                        row[3] = rsHistory.getString("sphosphorus");
                        row[4] = rsHistory.getString("spotassium");
                        historyList.add(row);
                    }
                }
                con.close();

                // ------------------------------------------------------------
                // 4. Set request attributes with both crop and history data
                request.setAttribute("cropData", cropList);
                request.setAttribute("showHistory", historyList);

                // ------------------------------------------------------------
                // 5. Forward the request to retrieve.jsp (for crop search results)
                RequestDispatcher rd = request.getRequestDispatcher("retrieve.jsp");
                rd.forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.getWriter().println("<script>alert('Error: " + e.getMessage() + "');</script>");
            }
        }
    }
}