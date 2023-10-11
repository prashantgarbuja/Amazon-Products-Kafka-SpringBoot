# Amazon-Products-Kafka-SpringBoot

## Amazon Product Delivery System

This Spring application simulates the delivery of Amazon products using Kafka messaging. It consists of two main components: 
- Product producer - generates fake Amazon products.
- Product consumer - receives and persists the delivered products.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17+
- Apache Maven
- Docker (for running Kafka and Zookeeper in containers)
- Git

### Installing

1. Clone the repository to your local machine:

```bash
git clone https://github.com/prashantgarbuja/Amazon-Products-Kafka-SpringBoot.git
```

2. Navigate to the project directory:

```bash
cd Amazon-Products-Kafka-SpringBoot
```

### Running the Application

#### Using Docker

If you have Docker installed, you can use the provided `docker-compose.yml` file to spin up Kafka and Zookeeper in containers as well as Docker files from product producer and consumer:

```bash
docker-compose up -d
```
Make sure to stop the docker-compose image after usage:
```bash
docker-compose down -v
```
### Usage

1. Start the application using docker.

2. The product producer will start generating fake Amazon products at regular intervals.

3. The product consumer will receive and persist the delivered products.

### Endpoints

- **View Delivered Products**: `GET /products`
  - Retrieves a list of all delivered products.
  ```bash
  http://localhost:9090/products
  ```

- **H2 Console (For Viewing In-Memory Database)**:
  ```bash
  http://localhost:9090/h2-console
  ```
  - JDBC URL: `jdbc:h2:mem:amazon_warehouse`
  - Username: `amazon_warehouse`
  - Password: `amazon_warehouse`

## Built With

- Spring Boot
- Kafka
- H2 Database
- JavaFaker

## Acknowledgments

- The JavaFaker library for generating fake product data.

---

Please make sure to customize the content according to your project's specific details and requirements.
