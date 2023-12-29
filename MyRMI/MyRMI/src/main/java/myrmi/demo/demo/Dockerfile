# Use a base Java image
FROM openjdk:8-jdk-alpine

# Copy the client classes into the image
COPY ./src/main/java/myrmi/demo/demo /app/demo/demo

# Set the working directory
WORKDIR /app

# No need to expose a port unless the client also acts as a server

# Command to start the client
CMD ["java", "myrmi.demo.demo.Client"]
