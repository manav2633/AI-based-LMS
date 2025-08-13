# 📚 Learning Management System (LMS)

A comprehensive **Spring Boot-based Learning Management System** designed to provide a complete online learning experience with **video learning**, **assignment management**, **exam portal**, and **role-based dashboards** for teachers, students, and admins.

---

## 🚀 Features

### 🔹 Core Modules
- 🎥 **Video Learning** – Stream and manage course videos
- 📝 **Assignments** – Upload, submit, and grade assignments
- 🧾 **Exam Portal** – Conduct MCQ, descriptive, and timed exams
- 📊 **Multi-Role Dashboards**:
  - **Student Dashboard** – View courses, attempt exams, submit assignments
  - **Teacher Dashboard** – Create courses, upload videos, review assignments, set exams
  - **Admin Dashboard** – Manage users, courses, and platform settings

---

## 🛠 Tech Stack

**Backend:**
- Java
- Spring Boot
- Spring Security (Authentication & Authorization)
- MySQL (Database)
- Hibernate / JPA

**Frontend:**
- HTML5
- CSS3
- JavaScript (ES6+)

**Build Tool:**
- Maven

---

## 📂 Project Structure

```
LMS-Project/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/lms/
│   │   │   ├── controller/     # REST controllers for all modules
│   │   │   ├── model/          # Entity classes (User, Course, Assignment, etc.)
│   │   │   ├── repository/     # JPA repositories
│   │   │   ├── service/        # Business logic layer
│   │   │   ├── config/         # Security and application configurations
│   │   │   └── LmsApplication.java
│   │   │
│   │   ├── resources/
│   │   │   ├── static/         # Frontend files (HTML, CSS, JS)
│   │   │   ├── templates/      # Thymeleaf templates (if used)
│   │   │   └── application.properties
│   │   │
│   │   └── webapp/             # Additional web resources
│   │
│   └── test/                   # Unit and integration tests
│
├── pom.xml                     # Maven dependencies
├── README.md                   # Project documentation
└── .gitignore                  # Git ignore file
```

---

## ⚙️ Installation & Setup

### Prerequisites
- Java 17 or higher
- MySQL 8.0+
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/lms-spring-boot.git
cd lms-spring-boot
```

### 2️⃣ Database Setup
Create a MySQL database:
```sql
CREATE DATABASE lms_db;
```

### 3️⃣ Configure Database Connection
Update `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/lms_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Configuration
server.port=8080

# File Upload Configuration
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=50MB
```

### 4️⃣ Install Dependencies
```bash
mvn clean install
```

### 5️⃣ Run the Application
```bash
mvn spring-boot:run
```

The application will be available at: **http://localhost:8080**

---

## 🔐 Default User Credentials

| Role    | Username  | Password | Access Level |
|---------|-----------|----------|--------------|
| Admin   | admin     | admin123 | Full system access |
| Teacher | teacher01 | pass123  | Course & assignment management |
| Student | student01 | pass123  | Course enrollment & submissions |

*Note: Change default passwords after first login for security.*

---

## 📡 API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/auth/login` | User login |
| POST   | `/api/auth/register` | User registration |
| POST   | `/api/auth/logout` | User logout |

### Course Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/api/courses` | Get all courses |
| POST   | `/api/courses` | Create new course |
| GET    | `/api/courses/{id}` | Get course by ID |
| PUT    | `/api/courses/{id}` | Update course |
| DELETE | `/api/courses/{id}` | Delete course |

### Assignment Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/api/assignments` | Get all assignments |
| POST   | `/api/assignments` | Create assignment |
| GET    | `/api/assignments/{id}` | Get assignment details |
| POST   | `/api/assignments/{id}/submit` | Submit assignment |

### Exam Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/api/exams` | Get all exams |
| POST   | `/api/exams` | Create new exam |
| GET    | `/api/exams/{id}` | Get exam details |
| POST   | `/api/exams/{id}/attempt` | Attempt exam |

---

## 🎯 Key Features Explained

### Video Learning Module
- Upload and stream educational videos
- Progress tracking for students
- Video categorization by subjects/courses
- Playback speed controls and bookmarking

### Assignment System
- File upload support (PDF, DOC, images)
- Deadline management with notifications
- Automated plagiarism detection
- Grading system with feedback

### Exam Portal
- Multiple question types (MCQ, True/False, Descriptive)
- Timed examinations with auto-submit
- Randomized question pools
- Instant result generation for objective questions

### Dashboard Features
- **Student Dashboard**: Course progress, upcoming deadlines, grades
- **Teacher Dashboard**: Class management, assignment review, gradebook
- **Admin Dashboard**: User management, system analytics, reports

---

## 🔧 Development Setup

### Running in Development Mode
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Running Tests
```bash
mvn test
```

### Building for Production
```bash
mvn clean package
java -jar target/lms-application.jar
```

---

## 📱 Frontend Integration

The frontend files are located in `src/main/resources/static/`:
- **HTML files**: Main pages and templates
- **CSS files**: Styling and responsive design
- **JavaScript files**: Dynamic functionality and API calls

### Key Frontend Components
- Login/Registration forms
- Course catalog and video player
- Assignment submission interface
- Exam taking interface
- Dashboard components for each user role

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add some amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Code Style Guidelines
- Follow Java naming conventions
- Add JavaDoc comments for public methods
- Write unit tests for new features
- Ensure responsive design for frontend components

---

## 📋 To-Do / Future Enhancements

- [ ] Real-time chat/messaging system
- [ ] Mobile app integration
- [ ] Advanced analytics dashboard
- [ ] Integration with external LTI tools
- [ ] Multi-language support
- [ ] Offline content access
- [ ] AI-powered content recommendations

---

## 🐛 Known Issues

- File upload size limitation (currently 50MB)
- Video streaming optimization needed for large files
- Email notification system requires SMTP configuration

---

**Happy Learning! 🎓**
