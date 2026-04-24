package br.com.escola.enrollment.adapter.in.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class MatriculaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void deveCriarMatriculaComStatusInicialAtiva() throws Exception {
        Long alunoId = criarAluno();
        Long periodoId = criarPeriodo("2026.3", "2026-02-01", "2026-06-30");
        Long turmaId = criarTurma("TURMA-MAT-A", periodoId);

        String requestBody = """
                {
                  "alunoId": %d,
                  "turmaId": %d,
                  "periodoLetivoId": %d
                }
                """.formatted(alunoId, turmaId, periodoId);

        mockMvc.perform(post("/api/matriculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.alunoId").value(alunoId))
                .andExpect(jsonPath("$.turmaId").value(turmaId))
                .andExpect(jsonPath("$.periodoLetivoId").value(periodoId))
                .andExpect(jsonPath("$.status").value("ATIVA"));
    }

    @Test
    @WithMockUser
    void deveRetornarNotFoundQuandoAlunoNaoExistir() throws Exception {
        Long periodoId = criarPeriodo("2026.4", "2026-08-01", "2026-12-20");
        Long turmaId = criarTurma("TURMA-MAT-B", periodoId);

        String requestBody = """
                {
                  "alunoId": 99999,
                  "turmaId": %d,
                  "periodoLetivoId": %d
                }
                """.formatted(turmaId, periodoId);

        mockMvc.perform(post("/api/matriculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Aluno não encontrado para o id 99999"));
    }

    @Test
    @WithMockUser
    void deveRetornarBadRequestQuandoTurmaNaoPertencerAoPeriodoInformado() throws Exception {
        Long alunoId = criarAluno();
        Long periodoTurma = criarPeriodo("2027.1", "2027-02-01", "2027-06-30");
        Long periodoInvalido = criarPeriodo("2027.2", "2027-08-01", "2027-12-20");
        Long turmaId = criarTurma("TURMA-MAT-C", periodoTurma);

        String requestBody = """
                {
                  "alunoId": %d,
                  "turmaId": %d,
                  "periodoLetivoId": %d
                }
                """.formatted(alunoId, turmaId, periodoInvalido);

        mockMvc.perform(post("/api/matriculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("A turma %d não pertence ao período letivo %d"
                        .formatted(turmaId, periodoInvalido)));
    }

    private Long criarAluno() throws Exception {
        String requestBody = """
                {
                  "nomeCompleto": "Aluno Matricula",
                  "cpf": "%s",
                  "email": "matricula@example.com",
                  "dataNascimento": "2011-04-10"
                }
                """.formatted(cpfAleatorio());

        String responseBody = mockMvc.perform(post("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(responseBody).get("id").asLong();
    }

    private Long criarPeriodo(String nome, String dataInicio, String dataFim) throws Exception {
        String requestBody = """
                {
                  "nome": "%s",
                  "dataInicio": "%s",
                  "dataFim": "%s"
                }
                """.formatted(nome, dataInicio, dataFim);

        String responseBody = mockMvc.perform(post("/api/periodos-letivos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(responseBody).get("id").asLong();
    }

    private Long criarTurma(String codigo, Long periodoId) throws Exception {
        String requestBody = """
                {
                  "codigo": "%s",
                  "nome": "Turma de Matricula",
                  "capacidade": 40,
                  "periodoLetivoId": %d
                }
                """.formatted(codigo, periodoId);

        String responseBody = mockMvc.perform(post("/api/turmas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(responseBody).get("id").asLong();
    }

    private String cpfAleatorio() {
        long cpf = System.nanoTime() % 1_000_000_00000L;
        return String.format("%011d", cpf);
    }
}
