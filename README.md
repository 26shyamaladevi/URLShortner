# URL Shortener

A URL shortener is a web application that takes long URLs and generates shorter, more manageable URLs. This project is a URL shortener implemented in Java using Spring Boot.

## Features

- Generate short URLs for long input URLs.
- Store the original URL and corresponding short URL in a database.
- Redirect users from short URLs to their original long URLs.
- Handle URL expiration after a certain duration.
- Validate and handle input errors.

## Database

Start the MySQL database using Docker Compose,

```
docker-compose up -d
```

To stop MySql database container use the following command,

```
docker-compose down
```

## Run Application

```
mvn spring-boot:run
```

To use the URL shortener, follow these steps:

1. Access the application through the defined URL, e.g., `http://localhost:8080`.
2. To shorten a URL, make a POST request to `/link` with the input parameter containing the long URL. The application will generate a short URL and return it. (You can use POSTMAN)
3. To access the original URL, use the generated short URL in your browser or make a GET request using generated short URL. The application will redirect you to the original URL.
