# Insurance Claims Management Platform

## Project Overview

A full-stack **Insurance Claims Management Platform** that allows customers to register, manage policies, and submit claims. Claims go through a workflow (submitted → documents uploaded → assessment → approved/rejected → settlement).

**Stack:**

- Backend: Java + Spring Boot
- Frontend: React (Vite)
- Database: MySQL
- Auth: Spring Security + JWT
- Storage: AWS S3
- Deployment: Docker + AWS (later Terraform)
- Observability: Actuator + Prometheus + Grafana
- CI/CD: GitHub Actions/Jenkins

---

## Day-by-Day Plan (Phase 1: MVP — 3 weeks, 2–3 hrs/day)

### Week 1 — Setup & Foundations

**Day 1**

- Initialize Spring Boot project
- Create basic REST endpoint (`/health`)
- Set up Git repo

**Day 2**

- Initialize React project with Vite
- Create simple page, connect to backend `/health`
- Verify frontend-backend communication

**Day 3**

- Design DB schema: `User`, `Policy`, `Claim`
- Implement JPA entities + repositories

**Day 4**

- Implement CRUD endpoints for `Policy`
- Test with Postman

**Day 5**

- Implement CRUD endpoints for `Claim`
- Add basic validation

**Day 6**

- Build React UI: Login page + Dashboard skeleton
- Fetch policies from backend, display list

**Day 7**

- Build React UI: Claims list + Claim details page
- Connect to backend endpoints

---

### Week 2 — Authentication & Workflow

**Day 8**

- Implement Spring Security + JWT
- Add login/register endpoints

**Day 9**

- Secure endpoints with roles: `CUSTOMER`, `CLAIM_ADJUSTER`, `ADMIN`

**Day 10**

- React: Implement login form, JWT storage, protected routes

**Day 11**

- React: Customer dashboard → view policies, claims

**Day 12**

- React: Claim submission form
- Backend: Link claim to customer

**Day 13**

- Admin dashboard → view all claims
- Backend: Assign claim to adjuster

**Day 14**

- Adjuster workflow → approve/reject claim
- Backend: Update claim status

---

### Week 3 — Documents & Deployment

**Day 15**

- Backend: Integrate AWS S3 for document upload
- Endpoint: `/claims/{id}/documents`

**Day 16**

- React: File upload component
- Connect to S3 upload endpoint

**Day 17**

- Backend: Settlement endpoint (dummy)
- React: Settlement view

**Day 18**

- Dockerize backend + frontend
- Run locally with Docker Compose

**Day 19**

- Deploy backend to AWS EC2
- Deploy frontend to AWS Amplify

**Day 20**

- Configure MySQL RDS
- Connect backend to RDS

**Day 21**

- End-to-end test: Register → Policy → Claim → Upload → Assessment → Settlement

---

## Next Phases (after MVP)

- **Phase 2:** Security hardening (refresh tokens, RBAC, auditing)
- **Phase 3:** Kafka for claim events
- **Phase 4:** Redis caching
- **Phase 5:** Observability (Prometheus + Grafana)
- **Phase 6:** Microservices split
- **Phase 7:** AI Claim Assistant (LLM integration)

---

## Mindset Rule

Stop asking: _“What technology should I learn next?”_  
Start asking: _“What capability does my system currently lack?”_

The project becomes your curriculum.
