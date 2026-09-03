# Insurance Claims Management Platform — Fast Execution Plan

## 1. Objective

Build a production-style full-stack Insurance Claims Management Platform while using the project itself as the curriculum.

The goal is **not** to collect knowledge about 15 technologies.

The goal is to reach this state:

> "I can build, debug, test, deploy, explain, and extend this system without following a tutorial."

You are an experienced Java/Spring developer becoming stronger in full-stack development and cloud/production engineering.

---

# 2. Final Technology Roadmap

## Core MVP

- Java 17+
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Spring Security + JWT
- React + Vite
- JavaScript ES6+
- REST APIs
- Git / GitHub
- JUnit 5 + Mockito
- Integration testing / Testcontainers

## Infrastructure

- Docker
- Docker Compose
- AWS EC2
- AWS RDS MySQL
- AWS S3
- AWS IAM
- AWS CloudWatch
- Terraform
- CI/CD

## Production Engineering

- Spring Boot Actuator
- Prometheus
- Grafana
- Structured logging / ELK later
- Redis
- Kafka

## Advanced Architecture

- Modular monolith first
- Selective microservices later
- API Gateway/service discovery only when justified

## AI — Last

- LLM integration
- Embeddings
- Vector database
- RAG
- AI Claim Assistant
- Policy-document Q&A

**Important:** Do NOT build all of this simultaneously.

---

# 3. Architecture Strategy

Start as a **modular monolith**.

Initial flow:

React
|
v
Spring Boot
|
+---- User
+---- Policy
+---- Claim
+---- Settlement
+---- Document
|
v
MySQL

AWS S3 will later store claim documents.

Only after the monolith is working and the domain boundaries are understood should services potentially be extracted.

Possible later architecture:

React
|
v
API Gateway
|
+---- Customer/Policy Service
+---- Claim Service
+---- Assessment Service
+---- Notification Service
|
v
Kafka

Supporting infrastructure:

- MySQL / RDS
- Redis
- S3
- Prometheus
- Grafana
- Docker
- Terraform
- CI/CD

---

# 4. The 21-Day MVP

The first objective is simple:

> Build and deploy a working insurance claims application in 21 days.

Do not think about the six-month roadmap while executing the current day.

---

## WEEK 1 — Application Foundation

### Day 1 — Spring Boot Foundation

Build:

- Spring Boot application
- Maven project
- Java 17+
- Spring Web
- `GET /health`

Expected response:

```json
{
  "status": "UP"
}
```

Also:

- Create Git repository
- Create README
- Make first meaningful commit

Suggested structure:

```text
insurance-claims-platform/
├── backend/
├── frontend/
├── docs/
└── README.md
```

### Day 1 Definition of Done

You can:

- Start the Spring Boot application
- Call `/health`
- Explain what a controller does
- Explain how Maven builds the application
- Commit the project to Git

---

### Day 2 — React Foundation

Build:

- Vite React application
- Basic page
- Call Spring Boot `/health`
- Display backend health status

You should understand:

- Components
- JSX
- Props
- `useState`
- `useEffect`
- API calls
- Basic conditional rendering

Do NOT spend days learning frontend theory.

---

### Day 3 — Database Foundation

Create initial domain model:

### User

- id
- name
- email
- password
- role

### Policy

- id
- policyNumber
- customerId
- policyType
- premium
- coverageAmount
- startDate
- endDate
- status

### Claim

- id
- claimNumber
- policyId
- customerId
- description
- amount
- status
- createdAt
- updatedAt

Use:

- JPA entities
- repositories
- MySQL
- proper relationships

---

### Day 4 — Policy CRUD

Build:

- Create policy
- Get policy
- Get all policies
- Update policy
- Delete/deactivate policy

Use:

- Controller
- Service
- Repository
- DTOs

Do not put business logic inside controllers.

---

### Day 5 — Claim CRUD + Validation

Build:

- Create claim
- Get claim
- Get all claims
- Update claim
- Delete/deactivate claim

Add:

- Bean Validation
- Global exception handling
- Proper HTTP status codes
- DTOs

### Required tests

At minimum:

- Create valid claim
- Reject invalid claim
- Claim not found
- Update claim
- Delete/deactivate claim

Use:

- JUnit
- Mockito

---

### Day 6 — React Dashboard

Build:

- Login page skeleton
- Dashboard
- Policy list
- Policy details
- Basic navigation

Do not obsess over CSS.

Use a UI library or utility framework if useful.

Focus on:

- Component design
- State
- API integration
- Loading state
- Error state

---

### Day 7 — Claims UI

Build:

- Claims list
- Claim details
- Claim status
- Claim creation form

### WEEK 1 DEFINITION OF DONE

You should have:

```text
React
   |
   v
Spring Boot REST API
   |
   v
MySQL
```

with working:

- Policy CRUD
- Claim CRUD
- Validation
- Exception handling
- Basic tests
- React integration

---

# WEEK 2 — Security + Business Workflow

## Day 8 — Spring Security + JWT

Build:

- Register
- Login
- Password hashing
- JWT generation
- JWT validation
- Security filter
- Protected endpoints

Understand:

- Authentication
- Authorization
- JWT
- SecurityFilterChain
- PasswordEncoder
- AuthenticationManager

---

## Day 9 — Roles

Create:

- CUSTOMER
- CLAIM_ADJUSTER
- ADMIN

Example authorization:

```text
CUSTOMER
    -> View own policies
    -> Submit claim
    -> View own claims

CLAIM_ADJUSTER
    -> View assigned claims
    -> Assess claim
    -> Approve/reject claim

ADMIN
    -> Manage users
    -> Assign claims
    -> View everything
```

Use method-level authorization where appropriate.

---

## Day 10 — React Authentication

Implement:

- Login form
- JWT handling
- Protected routes
- Logout
- Auth state

Understand:

- Why protected routes exist
- How frontend authentication differs from backend authorization
- What happens when JWT expires
- Why storing tokens carelessly is a security risk

Do not blindly copy token-storage patterns from tutorials.

---

## Day 11 — Customer Dashboard

Customer should be able to:

- See policies
- See claims
- View claim status
- Start a new claim

---

## Day 12 — Claim Submission Workflow

Build:

```text
Customer
   |
   v
Submit Claim
   |
   v
PENDING
```

Add business rules.

Examples:

- Policy must exist
- Policy must be active
- Claim amount cannot be invalid
- Customer can only claim against their own policy

This is where the application starts becoming a real business system rather than CRUD practice.

---

## Day 13 — Admin Dashboard

Admin can:

- View claims
- View customers
- Assign claim to adjuster
- View claim status

Example:

```text
PENDING
   |
   v
ASSIGNED
```

---

## Day 14 — Adjuster Workflow

Adjuster can:

- View assigned claims
- Review claim
- Approve
- Reject
- Add assessment/reason

Example:

```text
PENDING
   |
   v
ASSIGNED
   |
   +----> APPROVED
   |
   +----> REJECTED
```

### WEEK 2 DEFINITION OF DONE

You should have:

- JWT authentication
- RBAC
- Protected React routes
- Customer workflow
- Admin workflow
- Adjuster workflow
- Real claim business rules

---

# WEEK 3 — Documents + Deployment

## Day 15 — AWS S3

Implement claim document storage.

Flow:

```text
React
  |
  v
Spring Boot
  |
  v
AWS S3
```

Store:

- document metadata in MySQL
- actual file in S3

Do not store large files directly inside MySQL.

Learn:

- S3 bucket
- IAM
- Access policies
- Object keys
- Presigned URLs where appropriate

---

## Day 16 — React File Upload

Build:

- File picker
- Upload progress/state
- Error handling
- Document list
- Document download/view

Validate:

- file type
- file size
- claim ownership/authorization

---

## Day 17 — Settlement

Build settlement functionality.

Example:

```text
APPROVED
   |
   v
SETTLEMENT_PENDING
   |
   v
SETTLED
```

Create:

- settlement entity
- settlement endpoint
- settlement view
- basic business rules

---

## Day 18 — Docker

Dockerize:

- Spring Boot backend
- React frontend
- MySQL

Use Docker Compose for local development.

Expected:

```text
docker compose up
```

should start the required services.

Understand:

- image
- container
- Dockerfile
- port mapping
- environment variables
- volumes
- networks
- Docker Compose

---

## Day 19 — Integration Testing + Hardening

Before deployment, stop adding features.

Test the entire application locally.

Verify:

- Registration
- Login
- JWT
- Customer access
- Admin access
- Adjuster access
- Policy CRUD
- Claim creation
- Claim assignment
- Approval/rejection
- Document upload
- Settlement

Fix:

- CORS
- validation
- authorization bugs
- database issues
- frontend error handling
- configuration problems

This day exists because deploying broken software is not progress.

---

## Day 20 — AWS Deployment

Deploy manually first.

Suggested infrastructure:

```text
Internet
   |
   v
EC2
   |
   +---- Spring Boot
   |
   +---- React/static frontend if appropriate

EC2
   |
   +---- RDS MySQL

Spring Boot
   |
   +---- S3
```

Use:

- EC2
- RDS
- S3
- IAM
- Security Groups
- CloudWatch

### Important

Do this manually before Terraform.

Reason:

> First understand the infrastructure. Then automate it.

Terraform should reproduce infrastructure you already understand.

---

## Day 21 — End-to-End Validation

Run a real user journey:

```text
Customer registers
      |
      v
Customer logs in
      |
      v
Customer views policy
      |
      v
Customer submits claim
      |
      v
Customer uploads documents
      |
      v
Admin assigns adjuster
      |
      v
Adjuster reviews claim
      |
      v
Adjuster approves/rejects
      |
      v
Settlement processed
      |
      v
Customer sees final status
```

Capture screenshots.

Update README.

Document:

- architecture
- API endpoints
- database design
- authentication
- deployment
- AWS resources
- Docker setup
- known limitations

---

# 5. The Most Important Learning Rule

## Tutorial → Close → Rebuild → Extend

This is mandatory for topics where you use tutorials.

Do NOT do:

```text
Watch video
   ↓
Copy code
   ↓
"It works"
   ↓
Move on
```

Do:

```text
Watch / Read
      ↓
Implement
      ↓
Close tutorial
      ↓
Rebuild from memory
      ↓
Change something
      ↓
Debug
      ↓
Explain it
```

Example:

After learning React delete functionality:

1. Close the tutorial.
2. Implement delete yourself.
3. Add confirmation.
4. Add loading state.
5. Handle backend errors.
6. Refresh/update the UI.
7. Explain why the state changes.

That is actual learning.

---

# 6. Daily Operating System

Target:

## 2–3 hours/day

### 15 minutes

Review yesterday.

Ask:

- What did I build?
- What did I forget?
- What bug did I solve?

### 90–120 minutes

BUILD.

No passive learning.

### 20–30 minutes

- Test
- Debug
- Refactor
- Review

### 10–15 minutes

Commit + learning log.

---

# 7. The 70/20/10 Rule

Use your time approximately like this:

### 70% — Building

Writing code.

Debugging.

Testing.

Designing.

Deploying.

### 20% — Targeted Learning

Read/watch only what the current problem requires.

### 10% — Review

Notes.

Recall.

Architecture diagrams.

Explaining concepts.

---

# 8. Information Consumption Rules

You have access to:

- O'Reilly books
- YouTube
- Blogs
- Documentation
- Courses
- AI
- GitHub

These are useful.

But they can become procrastination disguised as learning.

Before opening a book/course, ask:

> "What am I going to build with this?"

If there is no immediate answer:

**Backlog it.**

Do not simultaneously study:

- Kafka
- Kubernetes
- Terraform
- React
- AI
- Python
- Microservices
- Redis

while the current feature is unfinished.

---

# 9. Debugging Rule

When something breaks:

### First 20–30 minutes

Try to solve it yourself.

Use:

- logs
- stack traces
- debugger
- browser DevTools
- database queries
- documentation you already know

Then search/ask for help.

The objective is not to avoid help.

The objective is to develop debugging ability.

---

# 10. Definition of Done

A feature is NOT done because:

- the tutorial works
- the code compiles
- ChatGPT generated it
- Postman returned 200

A feature is done when you can:

1. Build it.
2. Explain it.
3. Debug it.
4. Test it.
5. Modify it.
6. Rebuild it without the tutorial.

---

# 11. Testing Strategy

Testing should NOT be postponed until the end.

For every meaningful backend feature, add tests.

## Unit tests

Use:

- JUnit 5
- Mockito

Test:

- business rules
- validation behavior
- service logic
- error scenarios

## Integration tests

Use:

- Spring Boot test
- Testcontainers
- MySQL container

Eventually verify:

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Real MySQL container
```

This demonstrates real engineering capability.

---

# 12. Backend Engineering Standards

Do not build a toy CRUD application.

Use:

```text
Controller
    ↓
Service
    ↓
Repository
```

Prefer:

```text
Request DTO
    ↓
Validation
    ↓
Service
    ↓
Entity
    ↓
Repository
```

and:

```text
Entity
    ↓
Service
    ↓
Response DTO
```

Also implement over time:

- Global exception handling
- Validation
- Transactions
- Auditing
- Pagination
- Filtering
- Sorting
- Database indexes
- Proper HTTP status codes
- Logging
- Security
- Configuration management

---

# 13. Security Hardening — After MVP

Once the MVP works, improve:

- DTO validation
- RBAC
- JWT expiration
- Refresh tokens
- CORS configuration
- CSRF understanding
- Password hashing
- Secrets management
- IAM least privilege
- S3 access control
- File validation
- Rate limiting
- Audit logging
- Security tests

Do not confuse "JWT works" with "application is secure."

---

# 14. Terraform Phase

Order matters.

Use:

```text
Manual AWS
    ↓
Understand infrastructure
    ↓
Terraform
    ↓
terraform plan
    ↓
terraform apply
    ↓
Recreate infrastructure
```

Terraform should eventually provision:

- VPC
- subnets
- route tables
- Internet Gateway
- security groups
- EC2
- RDS
- S3
- IAM

Use modules once the basics are clear.

Do not start by copying a giant Terraform module from GitHub.

---

# 15. Kafka Phase

Only introduce Kafka when the application has a real event-driven use case.

Example:

```text
Claim Approved
      |
      v
Kafka Event
      |
      +---- Notification
      |
      +---- Settlement
      |
      +---- Audit
```

Learn:

- producer
- consumer
- topic
- partition
- offset
- consumer group
- delivery semantics
- retry
- dead-letter topic
- idempotency

Do not use Kafka merely because it appears on a job description.

---

# 16. Redis Phase

Introduce Redis where caching actually helps.

Good candidates:

- policy lookup
- claim lookup
- frequently requested reference data

Learn:

- cache-aside
- TTL
- invalidation
- cache stampede
- consistency tradeoffs

---

# 17. Observability Phase

Add:

- Spring Boot Actuator
- Prometheus
- Grafana
- structured logging
- correlation IDs

Understand:

```text
Metrics
Logs
Traces
```

At minimum, monitor:

- request count
- response time
- error rate
- JVM metrics
- database connection pool
- CPU/memory

---

# 18. Microservices Phase

Do NOT immediately split:

```text
User Service
Policy Service
Claim Service
Document Service
Settlement Service
Notification Service
```

That is resume-driven architecture.

Instead ask:

> "What problem does splitting this service solve?"

Possible eventual extraction:

```text
Claim Service
     |
     +---- Kafka
     |
     +---- Notification Service
```

or:

```text
Policy Service
Claim Service
Settlement Service
```

Extract only when the boundaries make sense.

---

# 19. AI Phase

AI comes AFTER the core platform works.

Potential features:

## AI Claim Assistant

Customer asks:

> "What documents do I need for this claim?"

AI answers based on policy/product rules.

## Claim Classification

Input:

```text
Claim description
```

Output:

```text
Claim category
Risk level
Required documents
```

## Policy Q&A

User uploads policy documents.

System:

```text
Documents
    ↓
Chunking
    ↓
Embeddings
    ↓
Vector DB
    ↓
Retriever
    ↓
LLM
    ↓
Answer
```

This becomes a real RAG use case instead of a generic chatbot.

---

# 20. Python Strategy

Do NOT become a Python developer just because AI is popular.

Learn only the Python required for:

- AI APIs
- embeddings
- RAG experiments
- data processing
- ML libraries when necessary

Your core leverage remains:

```text
Java
Spring Boot
System Design
Cloud
AWS
Distributed Systems
React
AI Integration
```

Python is a supporting tool, not a career detour.

---

# 21. Six-Month Progression

## Phase 1 — MVP

Weeks 1–3

Build the working application.

## Phase 2 — Production Hardening

Weeks 4–6

Add:

- better security
- testing
- pagination
- filtering
- indexes
- auditing
- error handling
- documentation

## Phase 3 — Cloud + Infrastructure

Weeks 7–9

Learn:

- AWS deeper
- Docker
- Terraform
- CI/CD

## Phase 4 — Distributed Systems

Weeks 10–13

Learn:

- Kafka
- Redis
- observability

## Phase 5 — Architecture

Weeks 14–17

Experiment with selective microservices.

## Phase 6 — AI

Weeks 18–20

Add:

- LLM
- embeddings
- vector database
- RAG
- AI claim assistant

## Phase 7 — Interview / Portfolio Hardening

Weeks 21–24

Prepare:

- system design
- architecture explanation
- AWS questions
- Java/Spring questions
- SQL
- Kafka
- Redis
- Docker
- Terraform
- React
- AI architecture

---

# 22. Weekly Rebuild Challenge

Every week:

Take one feature you built earlier.

Close:

- tutorials
- ChatGPT
- documentation

Then rebuild it.

Example:

```text
Week 1:
Rebuild Claim CRUD from memory.

Week 2:
Rebuild JWT security from memory.

Week 3:
Rebuild S3 upload from memory.
```

If you cannot rebuild it, you don't own the knowledge yet.

---

# 23. Git Discipline

Use meaningful commits.

Examples:

```text
feat: create spring boot health endpoint
feat: initialize react application
feat: add policy entity and repository
feat: implement policy crud
feat: add claim validation
test: add claim service unit tests
feat: implement jwt authentication
feat: add role based authorization
feat: integrate s3 document upload
build: dockerize application
```

Avoid:

```text
final
final2
changes
working
latest
test
abc
```

---

# 24. Project README Must Eventually Explain

## What

What problem does the system solve?

## Architecture

How does data flow?

## Tech Stack

Why each technology exists.

## Authentication

How JWT works.

## Database

Entities and relationships.

## Claim Workflow

Business state transitions.

## AWS

Which services are used and why.

## Docker

How to run locally.

## Terraform

How infrastructure is provisioned.

## Kafka

Which events exist and why.

## Redis

What is cached and why.

## Observability

What is monitored.

## AI

What problem AI solves and how RAG works.

---

# 25. Portfolio-Level Claim Workflow

The final system should demonstrate:

```text
Customer
   |
   | Register/Login
   v
Customer Dashboard
   |
   | Select Policy
   v
Submit Claim
   |
   | Upload Documents
   v
Claim = PENDING
   |
   v
Admin
   |
   | Assign
   v
Adjuster
   |
   | Review
   +----------+
   |          |
   v          v
APPROVED   REJECTED
   |
   v
Settlement
   |
   v
Customer
```

This is the core business story.

---

# 26. Rules You Must Not Break

### Rule 1

Do not start another major course while the current project phase is unfinished.

### Rule 2

Do not add technology because it looks good on a resume.

### Rule 3

Do not blindly copy AI-generated code.

### Rule 4

Do not skip testing because the feature "works."

### Rule 5

Do not deploy before local integration is stable.

### Rule 6

Do not start with microservices.

### Rule 7

Do not start AI before the core application works.

### Rule 8

Do not spend hours polishing CSS when backend/application behavior is incomplete.

### Rule 9

Do not confuse reading with learning.

### Rule 10

Every day must produce a tangible artifact.

---

# 27. The Current Mission

Ignore everything after Day 1 for now.

Your immediate mission is:

```text
Create project
    ↓
Spring Boot
    ↓
GET /health
    ↓
Run successfully
    ↓
Git commit
```

When that works, move to Day 2.

Do NOT:

- research Kafka
- study Kubernetes
- redesign the architecture
- read an AI book
- start another React course
- build microservices
- write Terraform

Not yet.

---

# 28. Day 1 Checklist

- [ ] Create project directory
- [ ] Initialize Git
- [ ] Create Spring Boot Maven application
- [ ] Configure Java 17+
- [ ] Add Spring Web
- [ ] Create `/health`
- [ ] Run application
- [ ] Test endpoint
- [ ] Create README
- [ ] Commit changes
- [ ] Be able to explain the implementation

### Final Day 1 Test

Without looking at the code, answer:

1. What is Spring Boot?
2. What does `@RestController` do?
3. What does `@GetMapping` do?
4. What is Maven responsible for?
5. Why does the application run on a port?
6. What happens when `/health` is called?
7. How would you add another endpoint?

If you cannot answer these, spend another 20 minutes understanding them.

---

# 29. Success Metric

The ultimate metric is NOT:

> "How many technologies did I learn?"

It is:

> "How much software can I build independently?"

Target progression:

```text
Tutorial dependent
       ↓
Tutorial assisted
       ↓
Documentation assisted
       ↓
Independent implementation
       ↓
Independent architecture
       ↓
Able to teach/explain it
```

That is the transformation this project is designed to produce.

---

# START NOW

## Day 1

Build:

**Spring Boot → `/health` → Git commit**

Nothing else.

Once it works, move to Day 2.
