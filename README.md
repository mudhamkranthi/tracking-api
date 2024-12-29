# Tracking Number Generator API

## Overview

This is a RESTful API designed to generate unique tracking numbers for parcels. It is built using **Spring Boot 3.4.1** and **Java 17**, and it handles high concurrency and scalability while ensuring that the generated tracking numbers are unique.

## Technologies Used

- **Java 17**: The latest LTS version of Java.
- **Spring Boot 3.4.1**: A framework for building production-ready applications with minimal setup.
- **Maven**: Build tool for managing dependencies and building the project.
- **JDK 17**: Ensure you have JDK 17 installed to run the application.

## Features

- **Tracking Number Generation**: Generates a unique tracking number using the provided parameters.
- **Validation**: Validates input parameters before processing the request.
- **Timestamps**: Returns a timestamp for when the tracking number is generated, formatted in RFC 3339 format.

## API Endpoints

### `GET /tracking/next-tracking-number`

This endpoint generates a new unique tracking number based on the following query parameters:

#### Request Parameters:

- **origin_country_id** (required): The origin country code in ISO 3166-1 alpha-2 format (e.g., "MY").
- **destination_country_id** (required): The destination country code in ISO 3166-1 alpha-2 format (e.g., "ID").
- **weight** (required): The weight of the order in kilograms, up to three decimal places (e.g., "1.234").
- **created_at** (required): The order's creation timestamp in RFC 3339 format (e.g., "2018-11-20T19:29:32+08:00").
- **customer_id** (required): The UUID of the customer (e.g., "de619854-b59b-425e-9db4-943979e1bd49").
- **customer_name** (required): The name of the customer (e.g., "RedBox Logistics").
- **customer_slug** (required): The customer's name in slug-case/kebab-case (e.g., "redbox-logistics").

#### Example Request:
```bash
Sample Request:
http://localhost:8080/tracking/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2024-12-29T14:13:10+05:30&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox&customer_slug=redbox-logistics

Sample Response:
{
  "trackingNumber": "29141419832USTJE",
  "createdAt": "2024-12-29T14:14:19+05:30"
}
```

## To Run this Application Locally

1. **Download the ZIP file**:
   - Download the `tracking-api` ZIP file.

2. **Unzip the File**:
   - Unzip the `tracking-api.zip` file.

3. **Navigate to the Project Directory**:
   - Open a terminal or command prompt.
   - Go to the project directory using the following command:

   ```bash
   cd tracking-api
   ```
4. ** Install Dependencies:**
	- mvn install

5. ** Run the Application:: **
	- mvn spring-boot:run

6. ** Test the Application: **
	- We can test the application by using curl or Postman with the following GET request
	
``` 
curl "http://localhost:8080/tracking/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2024-12-29T14:13:10+05:30&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox&customer_slug=redbox-logistics" 
```

	- If curl does not work, we can also test the API by opening our browser and navigating to the following URL:
```
http://localhost:8080/tracking/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2024-12-29T14:13:10+05:30&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox&customer_slug=redbox-logistics
	
```

