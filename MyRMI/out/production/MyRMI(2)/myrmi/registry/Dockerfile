# Use a base Java image
FROM openjdk:8-jdk-alpine

# Copy the compiled .class files for the registry into the image

# Set the working directory
WORKDIR /app

# Assuming your Dockerfile is in the root directory of your project:
COPY ./src/main/java/myrmi/registry /app/registry
COPY ./src/main/java/myrmi/demo/demo /app/demo/demo

# Expose the RMI registry port
EXPOSE 8899

# Command to run the RMI registry
CMD ["java", "myrmi.registry.Registry1"]
