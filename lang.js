let isMarathi = false;

function toggleLanguage(event) {
    isMarathi = !isMarathi;

    if (isMarathi) {
        document.getElementById("formTitle").innerText = "आपल्या मातीसाठी शोधा";
        document.getElementById("labelType").innerText = "मातीचा प्रकार:";
        document.getElementById("labelPh").innerText = "pH स्तर:";
        document.getElementById("labelNitrogen").innerText = "नायट्रोजन स्तर:";
        document.getElementById("labelPhosphorus").innerText = "स्फुरद स्तर:";
        document.getElementById("labelPotassium").innerText = "पालाश स्तर:";
        document.getElementById("submitBtn").value = "शोधा";
        event.target.innerText = "Translate to English";
    } else {
        document.getElementById("formTitle").innerText = "Search for Your Soil";
        document.getElementById("labelType").innerText = "Soil Type:";
        document.getElementById("labelPh").innerText = "pH Level:";
        document.getElementById("labelNitrogen").innerText = "Nitrogen Level:";
        document.getElementById("labelPhosphorus").innerText = "Phosphorus Level:";
        document.getElementById("labelPotassium").innerText = "Potassium Level:";
        document.getElementById("submitBtn").value = "Search";
        event.target.innerText = "Translate to Marathi";
    }
}

function showHistory() {
    document.getElementById("historyTable").style.display = "block";
    document.getElementById("cropDataTable").style.display = "none";
}

function showCropData() {
    document.getElementById("historyTable").style.display = "none";
    document.getElementById("cropDataTable").style.display = "block";
}


