FROM openjdk:17-oracle

WORKDIR /app

COPY ./ ./

# Make the mvnw script executable
RUN chmod +x mvnw
RUN ./mvnw clean compile
# Build the application

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["./mvnw", "spring-boot:run"]