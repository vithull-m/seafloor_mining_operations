# Autonomous Seafloor Mining Drone Management System

A beginner-friendly software-only operations dashboard for managing drones, mining sites, missions, and recorded mineral inventory. It does not control hardware or implement telemetry, AI, GPS, authentication, payments, or blockchain features.

## Stack

- Backend: Java 21, Spring Boot 3, Spring Web, Spring Data JPA, Hibernate, MySQL, Maven
- Frontend: React 19, Vite, JavaScript, Tailwind CSS, Axios, Lucide React

## Folder structure

```text
backend/
  src/main/java/com/seafloor/mining/
    config/ controller/ dto/ entity/ exception/ repository/ service/
  src/main/resources/application.properties
  pom.xml
frontend/
  src/App.jsx
  src/App.css
  src/index.css
  src/services/api.js
  tailwind.config.js
  postcss.config.js
  package.json
```

## MySQL setup

1. Install and start MySQL.
2. Create the database, or let the configured connection create it automatically:

```sql
CREATE DATABASE seafloor_mining;
```

3. Set `DB_USERNAME` and `DB_PASSWORD` in your environment, or update the datasource values locally. Hibernate uses `ddl-auto=update` during development, so tables are created from the entity classes.

## Run the backend

```bash
cd backend
mvn spring-boot:run
```

The API runs at `http://localhost:8080`. The configured CORS policy allows the Vite development server.

## Run the frontend

```bash
cd frontend
npm install
npm run dev
```

Open the URL printed by Vite, normally `http://localhost:5173`. To use another API host, set `VITE_API_URL`, for example `VITE_API_URL=http://localhost:8080/api`.

## REST endpoints

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET | `/api/dashboard/stats` | Dashboard totals and recent activity |
| POST | `/api/auth/register` | Register an operator ID and passcode |
| POST | `/api/auth/login` | Verify operator credentials |
| GET, POST | `/api/drones` | List or create drones |
| GET, PUT, DELETE | `/api/drones/{id}` | Read, update, or delete a drone |
| GET, POST | `/api/mining-sites` | List or create mining sites |
| GET, PUT, DELETE | `/api/mining-sites/{id}` | Read, update, or delete a site |
| GET, POST | `/api/missions` | List or create missions |
| GET, PUT, DELETE | `/api/missions/{id}` | Read, update, or delete a mission |
| GET, POST | `/api/minerals` | List or create mineral records |
| GET, PUT, DELETE | `/api/minerals/{id}` | Read, update, or delete a mineral |

## Postman examples

Create a drone with `POST http://localhost:8080/api/drones`:

```json
{
  "droneName": "Abyss Runner 01",
  "model": "Nereid-X",
  "status": "ACTIVE",
  "operatingDepth": 4200
}
```

Create a site with `POST http://localhost:8080/api/mining-sites`:

```json
{
  "siteName": "Clarion Deep",
  "location": "Clarion-Clipperton Zone",
  "depth": 4600,
  "status": "ACTIVE"
}
```

Create a mission with `POST http://localhost:8080/api/missions` after creating IDs 1 for the drone and site:

```json
{
  "missionName": "Survey Run Alpha",
  "droneId": 1,
  "miningSiteId": 1,
  "startDate": "2026-09-24",
  "status": "PLANNED"
}
```

Create a mineral record with `POST http://localhost:8080/api/minerals`:

```json
{
  "mineralName": "Manganese Nodules",
  "type": "Nodule",
  "quantity": 1250.5,
  "unit": "tonnes"
}
```

A typical mission response includes the related objects:

```json
{
  "id": 1,
  "missionName": "Survey Run Alpha",
  "drone": { "id": 1, "droneName": "Abyss Runner 01" },
  "miningSite": { "id": 1, "siteName": "Clarion Deep" },
  "startDate": "2026-09-24",
  "status": "PLANNED"
}
```

Register an operator with `POST http://localhost:8080/api/auth/register`:

```json
{
  "operatorId": "OC-041",
  "passcode": "deepwater123"
}
```

Login with `POST http://localhost:8080/api/auth/login`:

```json
{
  "operatorId": "OC-041",
  "passcode": "deepwater123"
}
```

The backend stores only a BCrypt hash of the passcode. If an operator ID is not found, the frontend directs the user to registration. If the passcode is incorrect, access is denied.

## Request workflow

The React components call the Axios functions in `frontend/src/services/api.js`. Axios sends JSON to the Spring REST controllers. Controllers validate request DTOs and call services. Services load related entities through Spring Data repositories, then Hibernate maps changes to MySQL tables. Responses return as JSON and update the dashboard or resource view. Validation and missing-record errors are converted by the global exception handler into readable API responses.

## Common errors

- **Connection refused:** start MySQL and the Spring Boot backend, then check port 8080.
- **Access denied for MySQL:** update the username and password in `application.properties`.
- **CORS error:** run the frontend through Vite and keep the backend CORS configuration aligned with the Vite port.
- **Mission validation error:** create a drone and mining site first and send their numeric IDs.
- **No dashboard records:** this is an empty database state; create records through the UI or Postman.

## Verification

```bash
cd backend && mvn test
cd frontend && npm run build
```
