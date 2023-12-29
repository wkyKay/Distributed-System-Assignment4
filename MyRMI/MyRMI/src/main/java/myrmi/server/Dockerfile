# Use a base Java image
FROM openjdk:8-jdk-alpine

# Copy the server classes and any needed resources into the image
COPY /path/to/server/classes /app
# If there are any resource files like properties files, copy them as well
# COPY /path/to/resources /app/resources

COPY ./src/main/java/myrmi/server /app/server
COPY ./src/main/java/myrmi/demo/demo /app/demo/demo
# Set the working directory
WORKDIR /app

# Expose the server port (replace 1234 with your server's port)
EXPOSE 3000

# Command to start the server
CMD ["java", "myrmi.demo.demo.Server"]
