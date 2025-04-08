# Tasky 📝
### by Lennin Steven Sabogal Prieto

A simple full-stack task management web app using Java Servlets, JSP, PostgreSQL, and Docker — built as a final project for OOP2025.

---

## 🚀 Live App
🔗 [https://finalprojectoop2021.onrender.com/login.jsp](https://finalprojectoop2021.onrender.com/login.jsp)

---

## 🛠 Tech Stack

- **Frontend:** JSP (Java Server Pages)
- **Backend:** Java Servlets + JDBC
- **Database:** PostgreSQL (hosted on Render)
- **Build Tool:** Maven
- **Server:** Apache Tomcat 10
- **Containerization:** Docker
- **Environment Management:** `.env` or system environment variables

---

## 🐳 Run Locally with Docker

### 1. Clone the project
```bash
git clone https://github.com/YOUR-USERNAME/FinalProjectOOP2021.git
cd FinalProjectOOP2021
```

### 2. Set environment variables (optional)
Create a `.env` file or export manually:
```bash
export DB_USER=your_db_user
export DB_PASSWORD=your_db_password
```

### 3. Build the `.war` file with Maven
```bash
mvn clean package
```

Make sure the `.war` is generated in `target/FinalProjectOOP2021-1.0.war`.

### 4. Build Docker image (use platform flag if on Apple Silicon)
```bash
docker buildx build \
  --platform linux/amd64 \
  -t finalprojectoop2021 .
  
  docker buildx build \
   --platform linux/amd64 \
   -t lennissp/finalprojectoop2021:latest \
   --push .

```

### 5. Run the app
```bash
docker run -d -p 8080:8080 \
  -e DB_USER=$DB_USER \
  -e DB_PASSWORD=$DB_PASSWORD \
  finalprojectoop2021
```

Then open your browser:  
[http://localhost:8080](http://localhost:8080)

---

## 📦 Deployment Notes (Render)

- PostgreSQL is hosted via Render.
- App is deployed using Docker with Render’s web service.
- `.war` is deployed to `/usr/local/tomcat/webapps/ROOT.war`.

---

## 📺 Video Cheatsheet

If I forget how to work with the Docker image, I can revisit this:
[https://www.youtube.com/watch?v=hWSHtHasJUI&t=467s](https://www.youtube.com/watch?v=hWSHtHasJUI&t=467s)

---

## 🔒 Security Notes

- Passwords are hashed before storage.
- Sensitive config (e.g., DB credentials) must be stored using environment variables or `.env` (never hardcoded or pushed).

---

## ✅ Features Implemented

- ✅ User registration and login
- ✅ Password encryption
- ✅ Task creation and edition
- ✅ Due dates and completion toggle
- ✅ Flash messages and dashboard metrics
- ✅ PostgreSQL support with Docker deployment

---

> “Built from scratch to understand how the web works under the hood — no frameworks, just Java 💪”