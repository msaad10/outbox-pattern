# Outbox Pattern with Kafka

This project demonstrates the implementation of the **Outbox Pattern** using **Spring Boot** and **Apache Kafka**. The Outbox Pattern ensures reliable message delivery by storing events in a database table (Outbox) and then publishing them to Kafka in a fault-tolerant manner.

## Features

- **Outbox Pattern**: Reliable event storage and processing.
- **Kafka Integration**: Produces and consumes messages using Apache Kafka.
- **Spring Boot**: Simplified application development with dependency injection and scheduling.
- **Database Interaction**: Stores and retrieves unprocessed messages from the Outbox table.
- **Scheduled Task**: Periodically processes unprocessed messages and publishes them to Kafka.

## Project Structure

### `src/main/java/com/outbox/pattern/mapper/order/Mapper.java`
- Contains methods to map between `OrderRequestDto`, `Order`, and `Outbox` entities.
- Converts `Order` entities to JSON payloads for Kafka messages.

### `src/main/java/com/outbox/pattern/consumer/KafkaConsumer.java`
- Listens to messages from the Kafka topic `unprocessed-orders`.
- Logs the consumed messages for further processing.

### `src/main/java/com/outbox/pattern/producer/KafkaProducer.java`
- Sends messages to a Kafka topic.
- Handles success and failure scenarios for message delivery.

### `src/main/java/com/outbox/pattern/scheduler/KafkaScheduler.java`
- Scheduled task that retrieves unprocessed messages from the Outbox table.
- Publishes messages to Kafka and marks them as processed in the database.

### `src/main/java/com/outbox/pattern/repository/OutboxRepository.java`
- Repository interface for interacting with the Outbox table in the database.
- Provides a method to fetch unprocessed messages.

### `src/main/java/com/outbox/pattern/entity/Order.java`
- Represents the `Order` entity with fields like `name`, `customerId`, `productType`, `quantity`, and `price`.

### `src/main/java/com/outbox/pattern/entity/Outbox.java`
- Represents the `Outbox` entity with fields like `aggregateId`, `payload`, `createdAt`, and `processed`.

### `src/main/java/com/outbox/pattern/dto/OrderRequestDto.java`
- Data Transfer Object (DTO) for creating new orders.

## Prerequisites

- **Java 17** or higher
- **Apache Kafka** (running locally or on a server)
- **Maven** for dependency management
- **PostgreSQL** or any other relational database
