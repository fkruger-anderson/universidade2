package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 07337354959
 */
public class Universidade {
    private String nome;
    private String CNPJ;
    
    private List<Curso> cursos = new ArrayList<>();
    private List<Aluno> alunos = new ArrayList<>();

    public Universidade(String nome, String CNPJ) {
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Curso criarCurso(String nome, String dataCriacao, String descricao) {
        Curso curso = new Curso(nome, dataCriacao, descricao);
        cursos.add(curso);
        return curso;
    }

    public Curso buscarCurso(int codigo) {
        return cursos.stream()
                     .filter(c -> c.getCodigo() == codigo)
                     .findFirst()
                     .orElse(null);
    }
    
    public boolean matricularAlunoUni(String nome, String dataNascimento, String CPF) {
        return alunos.add(new Aluno(nome, dataNascimento, CPF));
    }
    
    public Aluno buscarAluno(String matricula) {
        return getAlunos().stream()
                          .filter(a -> a.getMatricula().equalsIgnoreCase(matricula))
                          .findFirst()
                          .orElse(null);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Universidade: ").append(getNome())
          .append("; CNPJ: ").append(getCNPJ())
          .append("\n\nCursos: \n").append(cursos.stream()
                                                 .map(Curso::toString)
                                                 .collect(Collectors.joining("\n\n")));
        return sb.toString();
    }
}