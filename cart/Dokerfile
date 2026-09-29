# =========================
# Stage 1: Build project
# =========================
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom trước để Docker tận dụng cache dependency
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build WAR
RUN mvn clean package -DskipTests


# =========================
# Stage 2: Run Tomcat
# =========================
FROM tomcat:10.1-jdk17-temurin

# Xóa ứng dụng mặc định của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file WAR đã build vào Tomcat
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Tomcat chạy cổng 8080
EXPOSE 8080

# Khởi động Tomcat
CMD ["catalina.sh", "run"]
