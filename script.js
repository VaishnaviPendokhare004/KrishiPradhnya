document.getElementById('soilTestForm').addEventListener('submit', function(e) {
    e.preventDefault();

    // Get form values
    const soilType = document.getElementById('soil-type').value;
    const phLevel = parseFloat(document.getElementById('ph-level').value);
    const nitrogen = parseInt(document.getElementById('nitrogen').value);
    const phosphorus = parseInt(document.getElementById('phosphorus').value);
    const potassium = parseInt(document.getElementById('potassium').value);

    // Display results
    document.getElementById('result-soil-type').textContent = soilType;
    document.getElementById('result-ph').textContent = phLevel;
    document.getElementById('result-nitrogen').textContent = nitrogen + " ppm";
    document.getElementById('result-phosphorus').textContent = phosphorus + " ppm";
    document.getElementById('result-potassium').textContent = potassium + " ppm";

    // Generate recommendations
    const recommendations = generateRecommendations(soilType, phLevel, nitrogen, phosphorus, potassium);
    document.getElementById('recommendations').innerHTML = '<strong>Recommendations:</strong>${recommendations};'

    // Show results and hide form
    document.getElementById('soilTestForm').style.display = 'none';
    document.getElementById('results').style.display = 'block';
});

document.getElementById('retry-btn').addEventListener('click', function() {
    document.getElementById('soilTestForm').reset();
    document.getElementById('soilTestForm').style.display = 'block';
    document.getElementById('results').style.display = 'none';
});

function generateRecommendations(soilType, phLevel, nitrogen, phosphorus, potassium) {
    let rec = [];

    // pH-based recommendations
    if (phLevel < 6.0) {
        rec.push("Add lime to increase pH.");
    } else if (phLevel > 7.5) {
        rec.push("Add sulfur to lower pH.");
    } else {
        rec.push("pH is optimal for most crops.");
    }

    // Nutrient-based recommendations
    if (nitrogen < 20) rec.push("Apply nitrogen-rich fertilizer.");
    if (phosphorus < 15) rec.push("Add phosphorus (bone meal or rock phosphate).");
    if (potassium < 20) rec.push("Use potassium-rich fertilizers (wood ash or potash).");

    // Soil type advice
    if (soilType === 'clay') {
        rec.push("Improve drainage with organic matter.");
    } else if (soilType === 'sandy') {
        rec.push("Add compost to retain moisture.");
    }

    return rec.join(" ");
}