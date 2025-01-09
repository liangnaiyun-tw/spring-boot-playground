# BlogServer


## Features

- Centralized configuration management using Spring Cloud Config Server.
- Separate property files for shared and project-specific configurations.
- Environment-specific configurations (e.g., `-dev` for development).

## Configuration Repository Structure

The configuration files are stored in a Git repository named `demo-configuration-repository`. Below is the directory structure and purpose of the configuration files:


### File Details

1. **application.properties**
    - Contains shared configurations applicable to all projects and environments.

2. **jwt-dev.properties**
    - Contains JWT-related configurations specific to the development environment.

3. **mysql-dev.properties**
    - Contains MySQL-related configurations specific to the development environment.

4. **`${project name}-dev.properties`**
    - Contains project-specific configurations for the development environment. Replace `${project name}` with the actual name of your project.

## Setup Instructions

### Prerequisites

- Java 17 or later
- Maven 3.8 or later
- A Git repository for storing the configuration files

### Steps to Run

1. **Clone the Configuration Repository**  
   Ensure your Git repository is accessible and has the required configuration files in the structure mentioned above.

2. **Set Up the Config Server**  
   The Spring Cloud Config Server should point to the Git repository. Update the `application.properties` in the Config Server project with the following:

   ```properties
    spring.application.name=demo-config-server
    server.port=8888
    spring.cloud.config.server.git.uri=https://github.com/liangnaiyun-tw/spring-boot-playground.git
    spring.cloud.config.server.git.search-paths=demo-configuration-repository
   ```


3. **Run the Config Server**
    Start the Spring Cloud Config Server to make configuration files available to your applications.

4. **Run the BlogServer Application**
Point the BlogServer to the Config Server by updating its `application.properties`:
    
    ```properties
   # spring config server
     spring.config.import=optional:configserver:http://localhost:8888/
     management.endpoints.web.exposure.include=*
     spring.cloud.config.name=${spring.application.name}, jwt, mysql
     spring.profiles.active=dev
   ```