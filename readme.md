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



## Global Architecture

High-level structure of the system:

- SPA frontend communicating with a REST API

- Session-based authentication via OAuth2

## Backend architecture 

The backend follows Domain-Driven Design principles:



- **domain**, **application**, **infrastructure** and **presentation** layers  

- Business logic encapsulated in **domain aggregates and services**  

- Database schema with relational models (hibernate)

## Frontend Architecture

Modular Angular project following Nx-like structure, aiming for scalability:

 - **Feature-based**, attempting modular built and separation

 - **Shared modules** for reusable components and services

 - **Core module** for global services (Auth)

 - **Services** interact with REST API



## Engineering Decisions


- Dynamic content in the dashboard via database-driven API instead of hardcoding

- Hierarchical course structure in the database to support nested topics and dynamic navigation

- **Session-based authentication** instead of JWT to simplify CSRF protection and server-side session invalidation

- **Double submit cookie pattern** for CSRF in SPA context  

- Separated **PostgreSQL (prod)** and **H2 (dev)** for faster local development  


## Error Handling

- Exception handlers (`@RestControllerAdvice`) mapping errors to structured HTTP responses  

- Validation errors returned with consistent error format

## Challenges & Solutions

**Problem:** Keeping navbar navigation and content dynamic instead of hardcoded  

**Solution:** Introduced a structured REST API for data management  

**Result:** Dashboard content modification

