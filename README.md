# ClassRoom - Online Learning Platform

ClassRoom is an online learning platform that provides students with access to video classes. The application allows users to log in, browse courses, and watch educational videos.

## Features

- User authentication using Spring Security
- Course management with detailed descriptions and thumbnails
- Video management linked to courses
- Responsive design using HTML, CSS, and JavaScript
- Data storage using MySQL for user and course data
- Media assets (thumbnails and videos) stored in Supabase

## Technologies Used

- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Spring Boot
- **Database:** MySQL
- **Storage:** Supabase
- **Dependencies:** 
  - Spring Web
  - Lombok
  - Thymeleaf
  - Spring Security
  - Spring Data JPA
  - Spring DevTools
  - **MySQL Connector:** For MySQL database connectivity

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 21
- MySQL Server
- Supabase account for media storage
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/surendraguna/ClassRoom.git
   ```
2. Set up the MySQL database:
   - Create a new database in MySQL.
   - Update the `application.properties` file with your database connection details.
3. Set up Superbase:
   - Create a project on Supabase and configure the storage settings for thumbnails and video links.
4. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
5. Open your browser and navigate to `http://localhost:8080`.

## Usage

Here are some of the main functionalities available to users of the ClassRoom platform:

- **Registration:** New users can sign up to create an account.
- **Login:** Registered users can log in to access the course library.
- **Browse Courses:** Users can explore various courses and select one to view its details.
- **Watch Videos:** Click on a video link to start watching and enhance your learning experience.

## Contributing  

Contributions are welcome! If you would like to contribute to this project, please fork the repository and submit a pull request. For feature requests, bug reports, or questions, open an issue in the GitHub repository.
