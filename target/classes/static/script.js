const fileInput = document.getElementById("fileInput");
const fileNameLabel = document.getElementById("fileName");
const uploadBtn = document.getElementById("uploadBtn");

fileInput.addEventListener("change", () => {
    fileNameLabel.textContent = fileInput.files[0]?.name || "No file selected";
});

uploadBtn.addEventListener("click", () => {
    const file = fileInput.files[0];
    if (!file) {
        alert("Please select a file first.");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch("http://localhost:8080/api/employees/upload", {
        method: "POST",
        body: formData
    })
        .then(response => {
            if (!response.ok) throw new Error("Upload failed");
            return response.json();
        })
        .then(data => displayResult(data))
        .catch(error => alert("Error: " + error.message));
});

function displayResult(result) {
    const table = document.getElementById("resultTable");
    const tbody = table.querySelector("tbody");
    tbody.innerHTML = "";

    const row = document.createElement("tr");
    row.innerHTML = `
        <td>${result.empId1}</td>
        <td>${result.empId2}</td>
        <td>${result.daysWorkedTogether}</td>
    `;
    tbody.appendChild(row);
    table.style.display = "table";
}
