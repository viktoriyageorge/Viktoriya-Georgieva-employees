# 👥 Employee Collaboration App (Spring Boot)

A Spring Boot REST API that identifies the pair of employees who have worked together on common projects for the longest period of time, based on CSV input.

---

## 💡 Features

- Upload CSV file through frontend
- Supports multiple date formats
- Calculates days of overlapping work on shared projects
- Returns the pair of employees with the most collaboration time

---

## 📁 CSV Format
```
EmpID, ProjectID, DateFrom, DateTo
143, 12, 2013-11-01, 2014-01-05
218, 10, 2012-05-16, NULL
143, 10, 2009-01-01, 2011-04-27
```

- `DateTo` can be `NULL` (interpreted as today)
- Supported date formats (configurable via `application.properties`):
  - `yyyy-MM-dd`, `dd/MM/yyyy`, `MM-dd-yyyy`, `dd MMM yyyy`, `dd.MM.yyyy`, `yyyy/MM/dd`

---

## 🚀 Technologies

- Java 17
- Spring Boot 3
- Maven
- REST API
- Vanilla HTML/CSS/JavaScript frontend

---

## 🛠️ Run the Backend

```
mvn clean install
java -jar target/employee-collaboration-0.0.1-SNAPSHOT.jar
```
The API will be available at:
POST http://localhost:8080/api/employees/upload
! CORS is enabled for local development in WebConfig.java

---

## 🎨 Frontend

Located in src/main/resources/static/
Contains index.html, styles.css, and script.js
Uploads the CSV file and displays the result in a styled table

---

## ✅ Testing

Start the backend (localhost:8080)
Open index.html in Chrome (double-click or serve via Spring Boot)
Select a valid CSV file
Click Upload
The result will be displayed in the table
