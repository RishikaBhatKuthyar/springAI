# 🤖 SpringAI API

This is a Spring Boot RESTful API that integrates with OpenAI-compatible models (via [Groq API](https://groq.com)) using Spring AI. It includes endpoints to generate jokes and structured responses (like book recommendations) based on user input.

---

## 🚀 High-Level Overview

### ✅ Features
- 🔧 Spring Boot application with Maven
- 🤖 Integration with Spring AI
- 🎭 Generate jokes using LLaMA 3 models
- 📚 Get structured JSON book responses
- 🔐 Supports API key-based configuration

---

## 🌐 API Endpoints (GET)

| Endpoint | Description |
|---------|-------------|
| `http://localhost:8080/api/v1/joke` | Generates a joke about engineers |
| `http://localhost:8080/api/v1/books` | Returns book recommendations in plain text |
| `http://localhost:8080/api/v1/books/json` | Returns structured JSON of books |

---

## 🔑 OpenAI Configuration (Groq with LLaMA 3)

`application.properties`:

```properties
spring.ai.openai.base-url=https://api.groq.com/openai
spring.ai.openai.chat.options.model=llama3-8b-8192
spring.ai.openai.api-key=<YOUR_API_KEY>
