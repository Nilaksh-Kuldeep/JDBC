# JDBC Project

This project demonstrates Java Database Connectivity (JDBC) with MySQL.

## Requirements
- Java JDK
- MySQL Server
- MySQL Connector/J JAR

## Setup
1. Create the MySQL database and table using `schema.sql`.
2. Add the MySQL Connector/J JAR file to the `lib` folder.
3. Update database credentials in `App.java` if needed.
4. Run the project.

## Example
```bash
javac -cp "lib/*:src" -d bin src/App.java
java -cp "bin:lib/*" App
```

## Notes
- The default database is `college`
- The default table is `student`
- The JDBC URL uses:
  `jdbc:mysql://localhost:3306/college?useSSL=false&serverTimezone=UTC`
