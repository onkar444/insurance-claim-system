# Insurance Claims Management Platform — 6-Month Build Plan

## Objective

Build a serious portfolio-grade full-stack insurance claims platform that turns existing Java/Spring Boot experience into practical full-stack, cloud, distributed-systems, and AI capability.

Target stack:

- Backend: Java 17+, Spring Boot, Spring Security, JPA/Hibernate
- Frontend: React + Vite, JavaScript, Material UI or Tailwind
- Database: MySQL
- Testing: JUnit 5, Mockito, Testcontainers
- Cloud: AWS
- Infrastructure: Terraform
- Containers: Docker
- Messaging: Kafka
- Cache: Redis
- Observability: Actuator, Prometheus, Grafana, CloudWatch
- CI/CD: GitHub Actions or Jenkins
- AI: LLM API + RAG/document Q&A

## Operating Rules

1. Build first, read second.
2. Maximum tutorial-following: 30% of study time.
3. Minimum 70% of each session must be hands-on.
4. Do not add a technology until the current feature works.
5. After following a tutorial section, close it and rebuild the feature from memory.
6. Every day must produce a tangible artifact: code, test, diagram, documentation, or deployment.
7. If stuck, debug for 20–30 minutes before searching for the solution.
8. Do not restart the project because the code is imperfect. Refactor it.
9. Keep a `LEARNING_LOG.md` with: what I built, what broke, what I learned, what I still don't understand.
10. At the end of every week, rebuild one important feature without tutorials.

## Daily Session Structure — 2 to 3 Hours

- 15 min: Review yesterday's work and define today's single outcome.
- 90–120 min: Deep implementation.
- 20–30 min: Debug/test/refactor.
- 10–15 min: Commit + learning log.

If you only have 2 hours, reduce reading—not implementation.

---

# PHASE 1 — Frontend Foundations + React
## Week 1 — HTML/CSS Through the Project

### Day 1 — Project Setup
- Create Git repository.
- Create Vite React application.
- Create README.
- Define project requirements.
- Create initial folder structure.
- Run application locally.
- Commit: `chore: initialize claims frontend`

**Done when:** Vite app runs and repository is clean.

### Day 2 — HTML Fundamentals
- Learn semantic HTML.
- Build static claims dashboard structure.
- Use header, nav, main, section, table, button.
- No React logic yet.

**Done when:** Dashboard structure exists without copying markup.

### Day 3 — CSS Box Model
- Learn margin, padding, border, width, height.
- Style dashboard layout.
- Create reusable CSS classes.

**Done when:** You can explain why each spacing rule exists.

### Day 4 — Flexbox
- Learn flex container/item concepts.
- Build sidebar + content layout.
- Build dashboard statistic cards.

**Done when:** Layout works without arbitrary positioning.

### Day 5 — CSS Grid + Tables
- Learn CSS Grid.
- Build claims table.
- Handle columns and alignment.

**Done when:** Table is readable and structured.

### Day 6 — Responsive UI
- Learn media queries.
- Make dashboard usable on laptop/tablet/mobile widths.
- Fix overflow problems.

**Done when:** No horizontal scrolling at common widths.

### Day 7 — Rebuild Day
- Delete the dashboard UI.
- Rebuild the core layout from memory.
- Compare against previous version.
- Record gaps in `LEARNING_LOG.md`.

**Done when:** You can reproduce the structure without a tutorial.

---

# Week 2 — JavaScript Fundamentals

### Day 8 — Variables, Functions, Objects
- `const`, `let`
- arrow functions
- objects
- template literals
- Build claim objects in JavaScript.

### Day 9 — Arrays
- `map`
- `filter`
- `find`
- `some`
- `every`
- Render filtered claims in plain JavaScript.

### Day 10 — Destructuring + Spread
- Object/array destructuring.
- Spread operator.
- Update claim objects immutably.

### Day 11 — Events + Forms
- Event handling.
- Form values.
- Validation.
- Build claim creation form.

### Day 12 — Async JavaScript
- Promises.
- `async/await`.
- `try/catch`.
- Understand asynchronous execution.

### Day 13 — Fetch
- GET data from a public/mock endpoint.
- Handle success/error states.
- Display loading state.

### Day 14 — JavaScript Rebuild
- Rebuild filtering, form handling, and API call without tutorial.
- Review weak areas.

---

# Week 3 — React Fundamentals

### Day 15 — Components
- Break dashboard into components.
- Header, Sidebar, StatsCard, ClaimsTable.

### Day 16 — Props
- Pass claim data and configuration through props.
- Create reusable components.

### Day 17 — useState
- Search.
- Filter.
- Toggle UI.
- Understand state vs normal variables.

### Day 18 — Events + Forms in React
- Controlled inputs.
- Claim creation form.
- Validation.

### Day 19 — useEffect
- Understand lifecycle behavior.
- Fetch claims on page load.
- Avoid unnecessary effects.

### Day 20 — Conditional Rendering
- Loading.
- Empty state.
- Error state.
- Claim status badges.

### Day 21 — Rebuild
- Rebuild the dashboard from a blank component.
- No tutorial.
- Commit a stable version.

---

# Week 4 — React Application Structure

### Day 22 — Routing
- Install/configure React Router.
- Dashboard.
- Claims.
- Policies.
- Claim Details.

### Day 23 — API Layer
- Create central API service.
- Separate API logic from components.

### Day 24 — Claims List
- Fetch claims.
- Search.
- Filter by status.
- Sort.

### Day 25 — Claim Details
- Route parameter.
- Details page.
- Timeline/status history.

### Day 26 — Add Claim
- Form.
- Validation.
- Success/error feedback.

### Day 27 — Edit/Delete
- Edit claim.
- Delete/soft-delete UI.
- Confirmation dialog.

### Day 28 — Frontend MVP Review
- Remove duplicate code.
- Improve folder structure.
- Fix UX issues.
- Rebuild one feature from memory.

**Milestone 1: React-only claims application works.**

---

# PHASE 2 — Spring Boot Backend
## Week 5 — Domain + API

### Day 29 — Backend Setup
- Create Spring Boot project.
- Configure MySQL.
- Configure profiles.

### Day 30 — Domain Modeling
Create:
- Customer
- Policy
- Claim
- ClaimDocument
- ClaimStatus

Define relationships carefully.

### Day 31 — Repositories
- Spring Data JPA repositories.
- Basic queries.
- Database schema review.

### Day 32 — Service Layer
- Business logic.
- DTOs.
- Mapping.

### Day 33 — Claim APIs
Implement:
- POST `/claims`
- GET `/claims`
- GET `/claims/{id}`
- PUT `/claims/{id}`
- DELETE `/claims/{id}`

### Day 34 — Validation + Errors
- Bean Validation.
- Global exception handling.
- Consistent API error response.

### Day 35 — Backend Testing
- Unit tests.
- Controller/service tests.
- Test important business rules.

---

# Week 6 — Connect React + Spring Boot

### Day 36 — CORS + API Integration
- Connect React to backend.
- Remove mock data.

### Day 37 — GET Claims
- Real database-backed claims list.

### Day 38 — Create Claim
- React form → Spring API → MySQL.

### Day 39 — Edit Claim
- Full update flow.

### Day 40 — Delete/Soft Delete
- Implement backend + frontend.

### Day 41 — Search/Filter/Pagination
- Backend pagination.
- Filtering.
- Sorting.

### Day 42 — Integration Review
- Test full flow.
- Fix broken edge cases.

**Milestone 2: Real full-stack CRUD application.**

---

# PHASE 3 — Security + Production Backend
## Week 7 — Authentication

### Day 43 — Spring Security
- SecurityFilterChain.
- Understand authentication vs authorization.

### Day 44 — User Model
- User.
- Role.
- Password hashing.

### Day 45 — JWT Login
- Login endpoint.
- Token generation.
- JWT filter.

### Day 46 — React Login
- Login page.
- Store authentication state safely.
- API authorization header.

### Day 47 — Protected Routes
- React protected routes.
- Redirect unauthenticated users.

### Day 48 — RBAC
Roles:
- CUSTOMER
- CLAIM_ADJUSTER
- ADMIN

### Day 49 — Security Testing
- Unauthorized requests.
- Forbidden requests.
- Role boundaries.

---

# Week 8 — Better Backend Engineering

### Day 50 — DTO Architecture
- Request/response DTOs.
- Never expose entities unnecessarily.

### Day 51 — Auditing
- createdAt.
- updatedAt.
- createdBy.
- updatedBy.

### Day 52 — Transactions
- Understand transaction boundaries.
- Add `@Transactional` where justified.

### Day 53 — Database Indexing
- Identify query patterns.
- Add indexes.
- Compare query performance.

### Day 54 — API Documentation
- OpenAPI/Swagger.
- Document endpoints and security.

### Day 55 — Testing
- Increase unit/integration coverage.
- Add Testcontainers if practical.

### Day 56 — Refactoring
- Remove duplication.
- Review package structure.
- Fix technical debt.

**Milestone 3: Secure, tested full-stack application.**

---

# PHASE 4 — Docker + AWS
## Week 9 — Docker

### Day 57 — Docker Fundamentals
- Image vs container.
- Dockerfile.
- Build Spring Boot image.

### Day 58 — Docker Compose
Run:
- Backend
- MySQL
- Frontend

### Day 59 — Environment Configuration
- Environment variables.
- Secrets strategy.
- Profiles.

### Day 60 — Container Debugging
- Logs.
- Networking.
- Health checks.

### Day 61 — Production Dockerfile
- Multi-stage build where useful.
- Smaller image.
- Non-root user where practical.

### Day 62 — Full Local Stack
Run entire application with Docker.

### Day 63 — Rebuild
Destroy local containers and recreate everything from scratch.

---

# Week 10 — AWS

### Day 64 — AWS Architecture
Design:
- VPC
- public/private subnets
- load balancer
- application
- database

### Day 65 — IAM
- Roles.
- Policies.
- Least privilege.

### Day 66 — RDS
- MySQL RDS.
- Connectivity.
- Security groups.

### Day 67 — EC2
- Deploy backend container.

### Day 68 — S3
- Upload claim documents.
- Configure secure access.

### Day 69 — ALB
- Load balance backend.
- Health checks.

### Day 70 — CloudWatch
- Logs.
- Metrics.
- Alarms.

**Milestone 4: Application deployed on AWS.**

---

# PHASE 5 — Terraform + CI/CD
## Week 11 — Terraform

### Day 71
- Terraform fundamentals.
- Providers.
- Variables.
- Outputs.

### Day 72
- VPC and networking.

### Day 73
- Subnets + route tables.

### Day 74
- Security groups.

### Day 75
- EC2/RDS/S3 resources.

### Day 76
- Modules.
- Separate networking/application/database modules.

### Day 77
- `terraform plan/apply/destroy`.
- Recreate infrastructure.

---

# Week 12 — CI/CD

### Day 78
- Git branching strategy.
- Build pipeline.

### Day 79
- Backend automated tests.

### Day 80
- Docker image build.

### Day 81
- Image registry.

### Day 82
- Deployment pipeline.

### Day 83
- Frontend build/deployment.

### Day 84
- End-to-end pipeline test.

**Milestone 5: Push code → test → build → deploy.**

---

# PHASE 6 — Kafka + Redis + Observability
## Week 13 — Kafka

### Day 85
- Kafka fundamentals.
- Topic, partition, offset.

### Day 86
- Claim submitted event.

### Day 87
- Producer/consumer.

### Day 88
- Consumer groups.

### Day 89
- Retry strategy.

### Day 90
- Dead-letter topic.

### Day 91
- Idempotency + duplicate events.

---

# Week 14 — Redis

### Day 92
- Redis fundamentals.
- Cache-aside pattern.

### Day 93
- Cache claim/policy lookup.

### Day 94
- TTL.

### Day 95
- Cache invalidation.

### Day 96
- Measure cached vs uncached behavior.

### Day 97
- Handle Redis failure.

### Day 98
- Review.

---

# Week 15 — Observability

### Day 99
- Actuator.

### Day 100
- Prometheus.

### Day 101
- Grafana dashboard.

### Day 102
- JVM metrics.

### Day 103
- HTTP latency/error metrics.

### Day 104
- Structured logging.

### Day 105
- Correlation IDs and request tracing concepts.

---

# PHASE 7 — Microservices
## Weeks 16–18

Do NOT split everything just to call it microservices.

### Week 16 — Extraction
Extract:
- Claim Service
- Policy Service

Keep:
- API Gateway
- Authentication strategy
- Database boundaries

### Week 17 — Communication
- REST between services.
- Kafka events.
- Failure scenarios.
- Timeouts.
- Retries.
- Idempotency.

### Week 18 — Distributed Architecture
Learn and implement selectively:
- API Gateway
- Service discovery if justified
- Circuit breaker
- Saga concepts
- Eventual consistency
- Distributed tracing

**Milestone 6: You can explain why the system is microservices instead of merely saying it is.**

---

# PHASE 8 — AI Feature
## Weeks 19–20

### Week 19 — AI Integration

### Day 127
- Learn LLM API basics.
- Structured output.

### Day 128
- Build claim summarization.

### Day 129
- Extract structured information from claim descriptions.

### Day 130
- Identify missing claim information.

### Day 131
- Add AI assistant endpoint.

### Day 132
- Integrate AI UI into React.

### Day 133
- Add validation and failure handling.

### Week 20 — RAG

### Day 134
- Embeddings fundamentals.

### Day 135
- Document ingestion.

### Day 136
- Chunking.

### Day 137
- Vector storage/retrieval.

### Day 138
- Retrieval + generation.

### Day 139
- Policy document Q&A.

### Day 140
- Evaluate answers and hallucinations.

**Milestone 7: Production-oriented AI feature inside your Java application.**

---

# PHASE 9 — Portfolio + Interview Engineering
## Weeks 21–24

### Week 21 — Hardening
- Security review.
- Performance review.
- Database review.
- Error handling.
- Test coverage.
- Configuration cleanup.

### Week 22 — System Design
Create diagrams for:
- High-level architecture.
- Database schema.
- Claim workflow.
- Kafka event flow.
- Deployment architecture.
- AI/RAG architecture.

Practice explaining every major decision.

### Week 23 — Documentation
README must contain:
- Business problem.
- Features.
- Architecture.
- Technology choices.
- Local setup.
- API documentation.
- AWS architecture.
- CI/CD.
- Trade-offs.
- Known limitations.

### Week 24 — Interview Simulation
Be able to answer:
- Why modular monolith first?
- Why microservices later?
- Why Kafka?
- Why Redis?
- How do you prevent duplicate event processing?
- How does JWT work?
- How is authorization enforced?
- Why RDS?
- How does Terraform manage infrastructure?
- How would you scale claims to 10 million users?
- What happens if Kafka is unavailable?
- What happens if Redis is unavailable?
- How do you monitor production?
- How does your RAG pipeline work?
- What are the security risks of your AI feature?

---

# Definition of Done

The project is NOT finished because:

- the GitHub repository exists
- the UI looks good
- the application works on localhost
- you completed a tutorial
- you used 15 technologies

The project is finished when you can:

1. Build the major features without a tutorial.
2. Explain the architecture.
3. Debug failures.
4. Write tests.
5. Deploy it.
6. Recreate infrastructure.
7. Explain trade-offs.
8. Explain why each technology exists.
9. Demonstrate the system live.
10. Defend your design in an interview.

---

# Weekly Scorecard

Every Sunday, score yourself 0–2:

| Category | Score |
|---|---:|
| Coding hours completed | /2 |
| Features completed | /2 |
| Tutorial dependence reduced | /2 |
| Debugging/problem solving | /2 |
| Tests written | /2 |
| Documentation | /2 |
| Understanding | /2 |

Maximum: 14.

Interpretation:

- **12–14:** excellent
- **9–11:** acceptable
- **6–8:** you're drifting
- **<6:** stop adding new technologies and recover

---

# Non-Negotiable Rules

## Do not start another major project.

This project is enough.

## Do not learn Kubernetes yet.

It is deliberately excluded from the first six months. You don't need another infrastructure rabbit hole.

## Do not become a Python developer.

Learn Python only when it directly supports your AI work.

## Do not read entire technical books by default.

Use books as reference material when the project exposes a knowledge gap.

## Do not chase every new AI framework.

Learn durable concepts first.

## Do not optimize for technology count.

Optimize for demonstrated engineering capability.

---

# Your Immediate Next 7 Days

Ignore the rest of this document for now.

Your only target is:

**Day 1:** Vite + repository + requirements  
**Day 2:** HTML dashboard  
**Day 3:** CSS box model  
**Day 4:** Flexbox  
**Day 5:** Grid + table  
**Day 6:** Responsive UI  
**Day 7:** Rebuild from memory

At the end of Day 7 you should have a working frontend dashboard.

Then move forward.

# Final Principle

Your objective is NOT:

> "Learn React."

Your objective is:

> **"Become capable of independently building and operating a production-style full-stack system."**

React, Spring Boot, AWS, Kafka, Redis, Terraform, and AI are tools in service of that objective.

When a technology stops contributing to that objective, stop studying it.
