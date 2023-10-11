# Define base docker image
FROM openjdk:17-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project descriptor files
COPY pom.xml .

# Copy the Maven Wrapper files (mvnw and mvnw.cmd) to the container
COPY mvnw .
COPY .mvn .mvn

# Make the Maven Wrapper executable
RUN chmod +x mvnw

# Copy the application source code
COPY src ./src

# Build the application using the Maven Wrapper
RUN ./mvnw package -DskipTests

# Use a smaller base image for runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/product-producer-0.0.1-SNAPSHOT.jar producer.jar

# Runs java application
CMD ["java", "-jar", "producer.jar"]