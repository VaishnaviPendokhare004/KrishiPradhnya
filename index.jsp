<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Soil Selection</title>
    <link rel="stylesheet" type="text/css" href="./style.css">
</head>
<body>
    <h1 id="formTitle">Welcome to Soil Data Selection!</h1>

    <form action="dataEntry" method="post">
        <!-- Soil Type Selection -->
        <label for="type" id="labelType">Soil Type:</label>
        <select id="stype" name="type" required>
            <option value="">Select Soil Type</option>
            <option value="sandy">Sandy soil</option>
            <option value="clay">Clay soil</option>
            <option value="silt">Silt soil</option>
            <option value="loamy">Loamy soil</option>
            <option value="peaty">Peaty soil</option>
            <option value="chalky">Chalky soil</option>
            <option value="alluvial">Alluvial soil</option>
            <option value="black">Black soil</option>
            <option value="red">Red soil</option>
        </select>
        <br><br>

        <!-- pH Level Selection -->
        <label for="ph" id="labelPh">pH Level:</label>
        <select id="sph" name="ph" required>
            <option value="">Select pH Level</option>
            <option value="0 to 3.5">Less Than 3.5 (Ultra-acidic)</option>
            <option value="3.5 to 4.4">3.5 to 4.4 (Extremely acidic)</option>
            <option value="4.5 to 5.0">4.5 to 5.0 (Very strongly acidic)</option>
            <option value="5.1 to 5.5">5.1 to 5.5 (Strongly acidic)</option>
            <option value="5.6 to 6.0">5.6 to 6.0 (Moderately acidic)</option>
            <option value="6.1 to 6.5">6.1 to 6.5 (Slightly acidic)</option>
            <option value="6.6 to 7.3">6.6 to 7.3 (Neutral)</option>
            <option value="7.4 to 7.8">7.4 to 7.8 (Slightly alkaline)</option>
            <option value="7.9 to 8.4">7.9 to 8.4 (Moderately alkaline)</option>
            <option value="8.5 to 9.0">8.5 to 9.0 (Strongly alkaline)</option>
            <option value="9.0 to 10.0">More Than 9.0 (Very strongly alkaline)</option>
        </select>
        <br><br>

        <!-- Nitrogen Level Selection -->
        <label for="nitrogen" id="labelNitrogen">Nitrogen Level:</label>
        <select id="snitrogen" name="nitrogen" required>
            <option value="">Select Nitrogen Level</option>
            <option value="Low">Low (0 to 15 mg/kg)</option>
            <option value="Moderate">Moderate (16 to 40 mg/kg)</option>
            <option value="High">High (Above 40 mg/kg)</option>
        </select>
        <br><br>

        <!-- Phosphorus Level Selection -->
        <label for="phosphorus" id="labelPhosphorus">Phosphorus Level:</label>
        <select id="sphosphorus" name="phosphorus" required>
            <option value="">Select Phosphorus Level</option>
            <option value="Low">Low (0 to 15 mg/kg)</option>
            <option value="Moderate">Moderate (16 to 40 mg/kg)</option>
            <option value="High">High (Above 40 mg/kg)</option>
        </select>
        <br><br>

        <!-- Potassium Level Selection -->
        <label for="potassium" id="labelPotassium">Potassium Level:</label>
        <select id="spotassium" name="potassium" required>
            <option value="">Select Potassium Level</option>
            <option value="Low">Low (0 to 120 mg/kg)</option>
            <option value="Moderate">Moderate (121 to 240 mg/kg)</option>
            <option value="High">High (Above 240 mg/kg)</option>
        </select>
        <br><br>

        <!-- Buttons with action parameter -->
        <input type="submit" name="action" value="Search">
        <br><br>
        <input type="submit" name="action" value="history">
    </form>
</body>
</html>