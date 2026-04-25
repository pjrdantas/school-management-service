# Como usar os testes no Postman (passo a passo)

Se você nunca usou collection no Postman, siga exatamente esta ordem.

## 1) Suba o backend localmente

No terminal, na raiz do projeto:

```bash
SPRING_SECURITY_USER_NAME=admin SPRING_SECURITY_USER_PASSWORD=admin ./mvnw spring-boot:run
```

A API deve ficar em `http://localhost:8080`.

---

## 2) Abra o Postman e importe os 2 arquivos

No Postman:

1. Clique em **Import** (canto superior esquerdo).
2. Vá em **Upload Files**.
3. Selecione estes dois arquivos:
   - `docs/postman/school-management-sprint1-subtasks.postman_collection.json`
   - `docs/postman/school-management-local.postman_environment.json`
4. Clique em **Import**.

---

## 3) Ative o environment correto

No canto superior direito do Postman (dropdown de environment):

1. Selecione **School Management - Local**.
2. Confirme os valores:
   - `baseUrl = http://localhost:8080`
   - `auth_user = admin`
   - `auth_password = admin`

---

## 4) Rode os requests na ordem certa

Na collection **School Management - Sprint 1 Subtasks**:

### Passo A — Pasta `00 - Setup`

Execute os requests dessa pasta **de cima para baixo** clicando em **Send**.

Isso cria dados base e salva variáveis automaticamente:
- `alunoId`
- `periodoLetivoId`
- `turmaId`

> Sem esse passo, os testes das outras pastas podem falhar.

### Passo B — Pasta `01 - Aluno`

Execute os cenários de cadastro e consulta de aluno.

### Passo C — Pasta `02 - Catálogo Acadêmico`

Execute criação/consulta de período e turma.

### Passo D — Pasta `03 - Matrícula`

Execute criação de matrícula e filtros.

---

## 5) Como ver se passou ou falhou

Após clicar em **Send**:

- Aba **Test Results**: mostra cada assert.
- Verde = passou.
- Vermelho = falhou.

Também pode usar o **Runner**:

1. Abra a collection.
2. Clique em **Run**.
3. Selecione o environment **School Management - Local**.
4. Clique em **Run School Management - Sprint 1 Subtasks**.

---

## Problemas comuns (e solução rápida)

### 401 Unauthorized
- Confirme se a API foi iniciada com:
  - `SPRING_SECURITY_USER_NAME=admin`
  - `SPRING_SECURITY_USER_PASSWORD=admin`
- Confirme se o environment selecionado é **School Management - Local**.

### 404 / conexão recusada
- API não está rodando.
- Confirme `http://localhost:8080/actuator/health` no navegador ou Postman.

### Variáveis vazias (`{{alunoId}}`, etc.)
- Execute novamente a pasta `00 - Setup`.

### Falha em `KAN-37 - Rejeitar turma duplicada`
- Esse cenário depende de criação prévia na própria execução.
- Rode a pasta `02 - Catálogo Acadêmico` completa em sequência.

---

## Mapeamento para subtasks

- `KAN-14` (endpoint cadastro aluno): `01 - Aluno / KAN-14 - Cadastrar aluno válido`.
- `KAN-31` (modelagem entidade aluno): validado por payload/resposta de aluno na pasta `01 - Aluno`.
- `KAN-32` (migration aluno): validado por persistência + consulta por ID em `00 - Setup` e `01 - Aluno`.
- `KAN-49` (consulta aluno por identificador): `01 - Aluno / KAN-49 - Buscar aluno por ID`.
- `KAN-18` (endpoints acadêmicos): pastas de período e turma em `02 - Catálogo Acadêmico`.
- `KAN-35` (entidade período letivo): criação e consulta de período.
- `KAN-36` (entidade turma): criação e consulta de turma.
- `KAN-37` (migrations acadêmicas): persistência de período/turma em `00 - Setup` e `02`.
- `KAN-20` (caso de uso matrícula): `03 - Matrícula / KAN-20 - Criar matrícula válida`.
- `KAN-40` (entidade matrícula): validado por resposta da criação (`id`, vínculos e `status`).
- `KAN-41` (estado inicial matrícula): assert de `status = ATIVA`.
- `KAN-42` (validação aluno/turma/período): cenários de `404` e `400` na pasta `03 - Matrícula`.
- `KAN-22` (filtro por status): `03 - Matrícula / KAN-22 - Filtrar por status`.
- `KAN-44` (filtro por aluno): `03 - Matrícula / KAN-44 - Filtrar por aluno`.
- `KAN-45` (filtro por turma): `03 - Matrícula / KAN-45 - Filtrar por turma`.
- `KAN-46` (filtro por período letivo): `03 - Matrícula / KAN-46 - Filtrar por período letivo`.
