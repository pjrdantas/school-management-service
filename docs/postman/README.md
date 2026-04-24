# Testes no Postman para subtasks da Sprint 1

Este diretório contém uma collection do Postman para validar os endpoints do backend conforme as subtasks do Kanban compartilhadas.

## Arquivos

- `school-management-sprint1-subtasks.postman_collection.json`
- `school-management-local.postman_environment.json`

## Pré-requisitos

1. Subir a aplicação localmente na porta `8080`.
2. Definir credenciais de Basic Auth fixas (o projeto protege os endpoints):

```bash
SPRING_SECURITY_USER_NAME=admin SPRING_SECURITY_USER_PASSWORD=admin ./mvnw spring-boot:run
```

3. Importar no Postman:
   - a collection;
   - o environment local.

## Como executar

1. Selecione o environment `School Management - Local`.
2. Execute a pasta `00 - Setup` para gerar os IDs base (`alunoId`, `periodoLetivoId`, `turmaId`, `matriculaId`).
3. Execute as demais pastas por contexto:
   - `01 - Aluno`
   - `02 - Catálogo Acadêmico`
   - `03 - Matrícula`
4. Para rodar tudo automaticamente, use o **Collection Runner**.

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

## Observações

- A collection usa variáveis de ambiente para reaproveitar IDs entre requests.
- Os testes validam status HTTP, estrutura mínima do payload e regras críticas do fluxo.
