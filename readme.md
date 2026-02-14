# Learning-website



🔗 Live demo: https://toawall.pl



## Overview

A course-structured learning website with dynamically added content in the dashboard, supporting hierarchical courses and rich-text lessons.



## Tech Stack

**Frontend:** Angular, Tailwind, Quill

**Backend:** Spring Boot  

**Database:** PostgreSQL, H2 

**Security:** Sessions, Oauth2 (google), CORS, CSRF (double-cookie pattern) 

**Deployment:** Docker



## Architecture

High-level structure of the system:



- SPA frontend communicating with a REST API

- Authentication via OAuth2

- Database schema with relational models (hibernate)



## Backend architecture 

The backend follows Domain-Driven Design principles:



- Clear separation of **domain**, **application**, and **infrastructure** layers  

- Business logic encapsulated in **domain services and aggregates**  

- Use-case–oriented application services



## Frontend Architecture

Modular Angular project following Nx-like structure:

 - **Feature-based**

 - **Shared modules** for reusable components, pipes, and services

 - **Core module** for global services (Auth)

 - **Services** interact with REST API



## Engineering Decisions


- Database interactions in admin dashboard for content modiciation instead of hardcoding

- Designed courses structure hierarchically in the database with bounded context separations

- Used **session based auth** for protection instead of JWT to simplify security

- Implemented **double submit cookie pattern** for CSRF in SPA context  

- Separated **PostgreSQL (prod)** and **H2 (dev)** for faster local development  


## Error Handling

- Custom domain exceptions for business rule violations  

- Global exception handler (`@ControllerAdvice`) mapping errors to structured HTTP responses  

- Validation errors returned with consistent error format for the SPA



## Challenges & Solutions

**Problem:** Keeping navbar navigation and content dynamic instead of hardcoded  

**Solution:** Introduced a structured REST API for data retrieval  

**Result:** Navigation updates automatically when courses change

