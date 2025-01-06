# Spring Boot Nacos Configuration Example

This project demonstrates how to use **Spring Boot** to fetch and manage configurations from **Nacos**. It provides a simple implementation of integrating Nacos as a configuration center for a Spring Boot application.

## Features

- Fetch dynamic configurations from Nacos
- Support for centralized configuration management
- Easy setup with Spring Boot and Nacos integration

## Prerequisites

Before running the project, ensure you have the following:

1. **JDK 8 or later**
2. **Maven 3.6+**
3. A running instance of **Nacos Server** (version 2.x recommended)

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd <repository-folder>
```

### 2. Configure Nacos Server

Update the `application.properties` file (or `application.yml`) with your Nacos server details:

```properties
spring.application.name=nacos-demo
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
spring.cloud.nacos.config.namespace=<your-namespace-id>  # Optional
spring.cloud.nacos.config.group=DEFAULT_GROUP           # Optional
spring.cloud.nacos.config.file-extension=properties     # Optional
spring.profiles.active=dev								# Optional
```

### 3. Start the Application

Use the following command to run the application:

```bash
mvn spring-boot:run
```

### 4. Verify Configuration Fetching

- Log in to your Nacos console (default: `http://127.0.0.1:8848/nacos`).

- Add or modify a configuration file for your application (e.g., `nacos-demo-dev.properties`).

- The application will automatically fetch and update the configuration if dynamic refresh is enabled.

  ![image-20250106215415757](C:\Users\Liang Nai Yun\AppData\Roaming\Typora\typora-user-images\image-20250106215415757.png)

## How It Works

1. **Nacos Client Integration**: Spring Boot connects to the Nacos server using the `spring-cloud-starter-alibaba-nacos-config` dependency.
2. **Configuration Fetching**: Application properties are managed centrally in Nacos and fetched at startup.
3. **Dynamic Refresh**: With the `@RefreshScope` annotation, beans can dynamically update their values when configurations change in Nacos.

## Dependencies

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    <version>2023.0.3.2</version>
</dependency>
```

## Example Usage

### Access Configuration Properties

Inject properties from Nacos into your beans using `@Value`:

```java
@RestController
@RequestMapping("/appInfo")
@RefreshScope
public class AppInfoController {

    @Value("${spring.application.version}")
    private String version;

    @GetMapping("/version")
    public String getVersion(){
        return version;
    }
}
```

## Version Compatibility Notes

- There are two dependencies to consider
  - **nacos-config-spring-boot-starter**: Compatible with Spring Boot 3.0.X and 3.1.X
  - **spring-cloud-starter-alibaba-nacos-config**: Compatible with Spring Boot 3.2.X or later.

## References

- https://blog.csdn.net/LDY1016/article/details/136502907

