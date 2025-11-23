---

# 📘 **loan-insurance-api**

API para cálculo e gestão de cotações de **seguro prestamista**, com autenticação via **Keycloak**, documentação **Swagger** e execução simplificada com **Docker Compose**.

---

## 🚀 **Funcionalidades**

* Cálculo de cotações de seguro prestamista
* CRUD de cotações(Leitura, criação e remoção)
* Persistência em banco relacional
* Autenticação e autorização via **Keycloak**
* Documentação via **Swagger / OpenAPI**
* Execução local via **Docker Compose**

---

## 🏗️ **Arquitetura — Alto Volume / Uso Constante**

Fluxo:

<img width="1205" height="682" alt="Screenshot 2025-11-23 172153" src="https://github.com/user-attachments/assets/b9eef10b-064a-49e2-ab34-528c8d382b0e" />

### **Benefícios**

* Alta performance
* Escalabilidade horizontal
* Alta disponibilidade com Aurora
* Separação entre autenticação, API e dados
* Ideal para uso contínuo e alta volumetria

> Como não foi definida volumetria, adotamos esta arquitetura visando o pior cenário.

---

## 🔹 **Alternativa — Arquitetura Serverless (Lambda + DynamoDB)**

Fluxo:

<img width="1111" height="597" alt="Screenshot 2025-11-23 165354" src="https://github.com/user-attachments/assets/3d52ad83-d39c-4c2d-9412-a23914adaf0b" />

**Boa para uso baixo/intermitente**, podendo encarecer em alto volume.

---

## 🐳 **Como executar com Docker Compose**

### **1) Pré-requisitos**

```
Docker
Docker Compose
Git
```

---

### 2) Baixar o projeto e subir toda a stack

#### 📥 Clonar o repositório

```bash
git clone https://github.com/eduardofrad/loan-insurance-api.git
```

#### 📂 Entrar na pasta

```bash
cd loan-insurance-api
```

#### 🚀 Subir a stack

```bash
docker compose up --build
```

Para derrubar o serviço e subir novamente:

```sh
docker compose down -v
docker compose up --build
```

---

## 🛰️ **Serviços disponíveis**

| Serviço  | URL                                                                                        |
| -------- | ------------------------------------------------------------------------------------------ |
| API      | [http://localhost:8081](http://localhost:8081)                                             |
| Swagger  | [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) |
| Keycloak | [http://localhost:8080](http://localhost:8080)                                             |

---

## 🔐 **Autenticação — Obtendo o Token JWT (Keycloak)**

A API utiliza **Keycloak** para autenticação.
Em produção, recomenda-se utilizar um **API Gateway** para esta função.

---

### 🔸 **Admin**

```sh
curl --request POST \
  --url http://localhost:8080/realms/demo/protocol/openid-connect/token \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data client_id=api-client \
  --data client_secret=itubersecret \
  --data grant_type=password \
  --data username=adminuser \
  --data password=123
```

### 🔸 **Usuário comum**

```sh
curl --request POST \
  --url http://localhost:8080/realms/demo/protocol/openid-connect/token \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data client_id=api-client \
  --data client_secret=itubersecret \
  --data grant_type=password \
  --data username=ituber \
  --data password=123
```

---

### **Extrair o token**

```sh
access_token="eyJhbGciOi..."
```

---

## 🧪 **Testes rápidos via Swagger**

Acesse:

👉 [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

Cole o token JWT no botão **Authorize**.

---

## 🛠️ **Troubleshooting**

### **Ver logs**

```sh
docker compose logs -f api
docker compose logs -f keycloak
```

### **Rebuild total**

```sh
docker compose build --no-cache
docker compose up
```

---

