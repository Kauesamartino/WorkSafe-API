
# WorkSafe API

Monitor de Bem-Estar e Saúde no Trabalho

API WorkSafe oferece serviços para coleta, armazenamento e análise de dados relacionados ao bem-estar físico e mental de colaboradores em ambientes híbridos ou remotos. Ela integra autoavaliações, dados de dispositivos vestíveis (wearables), geração de alertas e recomendações personalizadas.

---

## Integrantes

- Kauê Vinicius Samartino da Silva - 559317

- Davi Praxedes Santos Silva - 563719

- João dos Santos Cardoso de Jesus - 560400

---
## Sumário
1. Visão Geral
2. Objetivos Funcionais
3. Arquitetura e Padrões
4. Tecnologias
5. Estrutura de Pastas
6. Endpoints Principais
7. Autenticação e Segurança (JWT)
8. Variáveis de Ambiente
9. Execução Local
10. Execução via Docker
11. Migrações de Banco (Flyway)
12. Testes Automatizados
13. Documentação OpenAPI / Swagger
14. Artefatos para Entrega (Checklist Avaliação)
15. Exemplos de Uso (cURL)
16. Insomnia / Coleção de Testes
17. Boas Práticas Implementadas
18. Próximos Passos / Inovação
19. Troubleshooting
20. Links Videos

---
## 1. Visão Geral
O sistema coleta:
- Autoavaliações de bem-estar.
- Dados de wearables (frequência cardíaca, sono, passos, etc.).
- Gera alertas com base em regras e padrões.
- Produz recomendações para melhoria contínua.

## 2. Objetivos Funcionais
- Cadastro e gerenciamento de usuários.
- Registro de autoavaliações de saúde física e mental.
- Integração de dados de dispositivos vestíveis.
- Emissão de alertas preventivos.
- Recomendação de ações (pausas, ergonomia, atividade física, mindfulness).

## 3. Arquitetura e Padrões
A aplicação segue uma abordagem limpa/hexagonal leve:
- `domain`: Entidades, enums, regras de negócio.
- `application`: Casos de uso / orquestração.
- `interfaces`: Controllers puros (sem anotações Spring) + DTOs.
- `infrastructure`: Adaptadores (REST, persistência JPA, segurança, exceções).

Benefícios:
- Separação de preocupações.
- Facilidade de teste.
- Baixo acoplamento e alta coesão.

## 4. Tecnologias
- Java 21 / Spring Boot 3.5.x
- Spring Web / Spring Data JPA / Spring Validation
- Spring Security (JWT)
- PostgreSQL + Flyway
- OpenAPI (springdoc)
- Maven
- Docker / Docker Compose

## 5. Estrutura de Pastas (resumida)
```
src/main/java/com/worksafe/api/
  application/    -> casos de uso, exceções de aplicação
  domain/         -> entidades, repos, lógica de negócio
  interfaces/     -> controllers puros + DTOs (input/output)
  infrastructure/ -> adaptadores REST, config, persistence, security
src/main/resources/
  application.properties
  db/migration/   -> scripts Flyway (V1__ ...)
src/main/docker/
  Dockerfile
  docker-compose.yaml (Postgres)
insomnia/insomnia_tests.har -> coleção de requisições
```

## 6. Endpoints Principais (prefixo `/api`)
Autenticação:
- POST `/api/auth/login` -> retorna JWT

Usuários (`/api/usuarios`):
- POST `/` criar
- GET `/` listar paginado (?pageSize=&pageNumber=)
- GET `/{cpf}` detalhes
- DELETE `/{cpf}` remover
- PATCH `/{cpf}` desativar

Autoavaliações (`/api/autoavaliacoes`):
- POST `/` criar
- GET `/` listar todas
- GET `/{id}` detalhes

Wearable Data (`/api/wearable-data`):
- POST `/` criar
- GET `/` listar todos
- GET `/{id}` detalhes

Alertas (`/api/alertas`):
- POST `/` criar
- GET `/` listar
- GET `/{id}` detalhes

Recomendações (`/api/recomendacoes`):
- POST `/` criar
- GET `/` listar
- GET `/{id}` detalhes

Observação: Endpoints protegidos exigem header `Authorization: Bearer <token>` (após login). Se ainda não configurado globalmente, ajuste em filtros de segurança.

## 7. Autenticação e Segurança (JWT)
Fluxo:
1. Usuário realiza POST em `/api/auth/login` com credenciais.
2. Sistema valida e devolve `accessToken`, `expiresIn`, etc.
3. Token é usado nos demais endpoints.

Variáveis importantes:
- `jwt.secret` (BASE64 ou chave segura)
- `jwt.expiration` (ms) — padrão 24h.

## 8. Variáveis de Ambiente
Local (dev) já possui valores default em `application.properties`.
Produção usa `application-prod.properties` com placeholders.

Defina (exemplos Windows CMD):
```
set DATASOURCE_URL=jdbc:postgresql://<host>:5432/worksafe
set DATASOURCE_USERNAME=postgres
set DATASOURCE_PASSWORD=********
set JWT_SECRET=7fDMrZ3v2Gg4bV7x1yZPq6Qb9wYp2nM8rRfUkVmXy2E=
set JWT_EXPIRATION=86400000
```
PowerShell:
```
$env:DATASOURCE_URL="jdbc:postgresql://<host>:5432/worksafe"
$env:DATASOURCE_USERNAME="postgres"
$env:DATASOURCE_PASSWORD="********"
$env:JWT_SECRET="7fDMrZ3v2Gg4bV7x1yZPq6Qb9wYp2nM8rRfUkVmXy2E="
$env:JWT_EXPIRATION="86400000"
```

## 9. Execução Local (sem Docker)
Pré-requisitos: JDK 21, Maven, PostgreSQL ativo (porta 5432).

Passos:
```
mvn clean install
mvn spring-boot:run
```
Aplicação em: `http://localhost:8080`

## 10. Execução via Docker
Build imagem multi-stage:
```
docker build -f src/main/docker/Dockerfile -t worksafe-api:latest .
docker run -p 8080:8080 --env JWT_SECRET=... --env JWT_EXPIRATION=... \
  --env DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/worksafe \
  --env DATASOURCE_USERNAME=postgres --env DATASOURCE_PASSWORD=123456 \
  worksafe-api:latest
```
Banco via compose (dev):
```
docker compose -f src/main/docker/docker-compose.yaml up -d
```

## 11. Migrações de Banco (Flyway)
Flyway executa automaticamente na inicialização. Scripts em `src/main/resources/db/migration`. Definir novas versões: `V7__descricao.sql`.

## 12. Testes Automatizados
Executar:
```
mvn test
```
Cobertura adicional (exemplo futuro): Jacoco plugin pode ser adicionado.

## 13. Documentação OpenAPI / Swagger
Após subir a aplicação: `http://localhost:8080/swagger-ui.html`
(OpenAPI JSON em `/v3/api-docs`).

## 14. Artefatos para Entrega (Checklist Avaliação)
- Repositório: (inserir link Git, ex: https://github.com/ORG/WorkSafe-API)
- Deploy Backend: (ex: https://api.worksafe.example.com)
- Deploy Banco: (ex: RDS PostgreSQL / Render / Neon link)
- Instruções de Acesso: neste README (seção 9-13)
- Coleção de Testes: `insomnia/insomnia_tests.har`
- Critérios:
  - Requisitos técnicos & boas práticas (70%)
  - Viabilidade & Inovação (10%)
  - Documentação & Apresentação (20%)

## 15. Exemplos de Uso (cURL)
Login:
```
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"senha"}'
```
Criar usuário:
```
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{"cpf":"12345678900","nome":"Joao","email":"joao@ex.com"}'
```
Listar autoavaliações:
```
curl -H "Authorization: Bearer <TOKEN>" http://localhost:8080/api/autoavaliacoes
```

## 16. Insomnia / Coleção
Importe `insomnia/insomnia_tests.har` no Insomnia para testar endpoints rapidamente.

## 17. Boas Práticas Implementadas
- Separação entre controllers puros e adaptadores REST.
- DTOs para input/output evitando exposição direta de entidades JPA.
- Validação com `jakarta.validation` (@Valid, @NotNull etc.).
- Flyway para versionamento de schema.
- Multi-stage Docker para imagem enxuta.
- Uso de ResponseEntity para controle explícito de respostas.
- Paginação em listagem de usuários.

## 18. Próximos Passos / Inovação
- Motor de recomendação baseado em regras + ML incremental.
- Integração com APIs de wearables reais (Fitbit, Garmin, Apple Health).
- Painel de analytics (dashboard) em tempo real.
- Alertas por canais (e-mail, push, Slack).
- Observabilidade (Micrometer + Prometheus + Grafana).
- Rate limiting / auditoria de acessos.

## 19. Troubleshooting
| Problema | Possível causa | Ação |
|----------|----------------|------|
| Erro ao conectar Postgres | Banco não iniciado | Verificar `docker compose ps` ou serviço local |
| Flyway falha | Script mal formatado | Validar nome Vx__desc.sql e terminar com `;` |
| 401 Unauthorized | Token expirado ou ausente | Refazer login e incluir header Authorization |
| Porta 8080 ocupada | Outro serviço rodando | Alterar `server.port` em properties ou encerrar serviço |

## Links videos
- [Video Apresentação WorkSafe API]()
- [Video Demonstração Funcionalidades]()

## Licença
Definir licença (ex: MIT) — ajustar conforme necessidade.

---
Qualquer dúvida ou sugestão, abra uma issue no repositório.

