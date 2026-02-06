# Grails 3 ETF POC Application

## Overview

This is a simple **Grails 3 proof-of-concept (POC)** application.

It's intentionally minimal and API-focused. It demonstrates a common enterprise pattern:

**Controller → Service → GORM Domain → Relational Database**

It also includes a **one-to-many domain relationship**:

* `EtfListing` (parent)
* `EtfPricePoint` (child)

The backend database is **PostgreSQL**, typically run via Docker.

---

## Architecture

```
REST Controller
      ↓
Service Layer
      ↓
GORM Domain Models
      ↓
PostgreSQL Database
```

### Domain Model

* **EtfListing**

    * Represents an ETF
    * Has many `EtfPricePoint` records

* **EtfPricePoint**

    * Represents historical NAV data
    * Belongs to an `EtfListing`

---

## Prerequisites

* Java 8
* Docker (recommended)
* Gradle Wrapper (included in repo)
* PostgreSQL client (optional)

> A local Grails installation is **not required**. The project uses the Gradle wrapper.

---

## Running PostgreSQL (Docker)

From the project root:

```bash
docker compose up -d
```

This starts a PostgreSQL container with:

* Database: `etfpoc`
* Username: `etfpoc`
* Password: `etfpoc`
* Port: `5432`

---

## Configuration

Database configuration is defined in:

```
grails-app/conf/application.yml
```

Key settings:

```yaml
dataSource:
  driverClassName: org.postgresql.Driver
  dialect: org.hibernate.dialect.PostgreSQLDialect
  username: etfpoc
  password: etfpoc
```

Development environment:

```yaml
environments:
  development:
    dataSource:
      dbCreate: update
      url: jdbc:postgresql://localhost:5432/etfpoc
```

---

## Running the Application

Clean and start the app using the Gradle wrapper:

```bash
./gradlew clean
./gradlew bootRun
```

On Windows:

```bash
gradlew clean
gradlew bootRun
```

The application will start on:

```
http://localhost:8080
```

BootStrap data will automatically seed sample ETFs and price history.

---

## API Endpoints

### Get all ETFs

```
GET /api/etfs
```

Returns a list of ETFs with recent price points.

### Get ETF by symbol

```
GET /api/etfs/{symbol}
```

Example:

```
GET /api/etfs/VFV
```

Returns ETF details and historical price points.

---

## Project Structure

```
grails-app/
├── controllers/
│   └── EtfListingController.groovy
├── services/
│   └── EtfListingService.groovy
├── domain/
│   ├── EtfListing.groovy
│   └── EtfPricePoint.groovy
└── init/
    └── BootStrap.groovy
```

---

## Purpose of This POC

This project is intentionally designed to exercise areas relevant to migration:

* GORM domain modeling
* Domain relationships (one-to-many)
* Transactional services
* JSON REST controllers
* Hibernate/PostgreSQL integration
* Gradle dependency management

It serves as a realistic but manageable test case for automated upgrade or conversion tools.
