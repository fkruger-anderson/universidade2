package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author 07337354959
 */
public class Curso {
    private int codigo;
    private String nome;
    private LocalDate dataCriacao;
    private String descricao;

    private List<Turma> turmas = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();

    private static int contCurso = 0;

    public Curso(String nome, String dataCriacao, String descricao) {
        this.codigo = ++contCurso;
        this.nome = nome;
        this.dataCriacao = LocalDate.parse(dataCriacao, dtf);
        this.descricao = descricao;
    }

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public Turma buscarTurma(int codigo) {
        return turmas.stream()
                     .filter(t -> t.getCodigo() == codigo)
                     .findFirst()
                     .orElse(null);
    }
    
    public boolean criarTurma(String nome, int qtdVagas, String matriculaProf) {
        Professor prof = this.buscarProfessor(matriculaProf);

        if (prof != null) {
            return turmas.add(new Turma(nome, qtdVagas, prof));
        } else {
            System.out.println("Dados não encontrados nesta Universidade.");
            return false;
        }
    }

    public Professor buscarProfessor(String matricula) {
        return professores.stream()
                          .filter(p -> p.getMatricula()
                                        .equalsIgnoreCase(matricula))
                          .findFirst()
                          .orElse(null);
    }
    
    public boolean adicionarProfessor(String dataAdmissao, String nome, String dataNascimento, String CPF) {
        return getProfessores().add(new Professor(this, dataAdmissao, nome, dataNascimento, CPF));
    }

    public Set<Aluno> alunosInscritosCurso() {
        Set<Aluno> alunosInscritos = getTurmas().stream()
                                                .map(Turma::getAlunosMatriculados)
                                                .flatMap(Collection::stream)
                                                .collect(Collectors.toSet());
        
        alunosInscritos.forEach(a -> System.out.printf("%s - %s\n", a.getMatricula(), a.getNome()));
        
        return alunosInscritos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCodigo()).append(" - ").append(getNome())
          .append(", Criação: ").append(getDataCriacao().format(dtf)).append(", ")
          .append(getDescricao()).append("\n")
          .append("\nTurmas:\n")
          .append(turmas.stream().map(Turma::toString).collect(Collectors.joining("\n\n")));

        return sb.toString();
    }
}