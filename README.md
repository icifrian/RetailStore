# Retail Store

SpringBoot application/service that provides a query end point rest such that:

* Accept as input parameters: application date, product identifier, string identifier.
* Return as output data: product identifier, string identifier, rate to apply, application dates and final price to apply.

Use an in-memory database (type h2).

### Requirements

* Java JDK 17 or higher.
* Maven 3.8.1 or higher.


## Configuration and Execution

### In-Memory Database
This project uses an in-memory H2 database. The database is automatically initialized with test data when the application is started.

### Running the Application

* Clones the project repository: git clone URL_REPOSITORY_URL

* Navigate to the project directory: cd project_directory

* Run the application with Maven: mvn spring-boot:run

The application will be available at http://localhost:8080.


### Endpoints
The application exposes the following REST endpoint:

* Price Query: GET /api/prices. It is accessible and testable directly with Swagger at the following URL: ```http://localhost:8080/doc/swagger-ui/index.html```

Parameters:

* applicationDate (application date in yyyy-MMTdd-HH.mm.ss format).
* productId (product identifier)
* brandId (string identifier)

Example of use:
```
curl "http://localhost:8080/api/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1"
```

### Running Tests
To run automated tests, including tests for endpoints, use the following Maven command:
```
mvn test
```

The tests include cases to validate the following requests to the service with sample data:

* Request at 10:00 on the 14th of the day for product 35455 for brand 1 (ZARA).
* Request at 16:00 of the day 14 of product 35455 for brand 1 (ZARA).
* Request at 21:00 of the day 14 of product 35455 for brand 1 (ZARA).
* Request at 10:00 on the 15th of the day of the product 35455 for brand 1 (ZARA).
* Request at 21:00 of the day 16 of product 35455 for brand 1 (ZARA).
