package entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 *
 * @author 07337354959
 */
public class Turma {
    private int codigo;
    private String nome;
    private int qtdVagas;
    private Professor professor;

    private List<Aluno> alunosMatriculados = new ArrayList<>();
    private List<Nota> notas = new ArrayList<>();

    private static int contTurma = 0;

    public Turma(String nome, int qtdVagas, Professor professor) {
        this.codigo = ++contTurma;
        this.nome = nome;
        this.qtdVagas = qtdVagas;
        this.professor = professor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdVagas() {
        return qtdVagas;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }
    
    public List<Nota> getNotas() {
        return notas;
    }

    public Aluno buscarAlunoMatriculadoTurma(String matricula) {
        return getAlunosMatriculados().stream()
                                      .filter(a -> a.getMatricula().equalsIgnoreCase(matricula))
                                      .findFirst()
                                      .orElse(null);
    }

    public boolean matricularAlunoTurma(Aluno aluno) {
        if (getAlunosMatriculados().size() + 1 <= getQtdVagas() && !getAlunosMatriculados().contains(aluno)) {
            return getAlunosMatriculados().add(aluno);
            } else {
                System.out.println("Excedeu o limite de inscritos da turma, ou o aluno já encontra-se matriculado.");
                return false;
            }
    }

    public boolean registrarNotaAluno(String matricula, Double nota) {
        Aluno aluno = buscarAlunoMatriculadoTurma(matricula);
        if (!aluno.equals(null)) {
            return getNotas().add(new Nota(aluno, nota));
        } else {
            System.out.println("Dados não encontrados nesta Universidade.");
            return false;
        }
    }

    public Map<Aluno, Double> calcMediaAlunos() {
        Map<Aluno, Double> medias = getNotas().stream()
                                              .collect(Collectors.groupingBy(a -> a.getAluno(),
                                                      Collectors.averagingDouble(Nota::getNota)));
        
        return medias;
    }
    
    public Map<Aluno, Double> mediasOrdemAlfabetica() {
        Map<Aluno, Double> ordemAlfabetica = calcMediaAlunos().entrySet()
                                                              .stream()
                                                              .sorted((o1, o2)->o1.getKey().getNome().
                                                                      compareTo(o2.getKey().getNome()))
                                                              .collect(Collectors.toMap(
                                                                      Entry::getKey, 
                                                                      Entry::getValue, 
                                                                      (e1, e2) -> e1, 
                                                                      LinkedHashMap::new));
        
        System.out.println("Médias por aluno - Ordem alfabética:");
        for (Entry<Aluno, Double> notas : ordemAlfabetica.entrySet()) {
            System.out.printf("%s - %.2f\n", notas.getKey().getNome(), notas.getValue());
        }
        
        return ordemAlfabetica;
    }
    
    public Map<Aluno, Double> mediasOrdemDesempenho() {
        Map<Aluno, Double> ordemDesempenhoa = calcMediaAlunos().entrySet()
                                                               .stream()
                                                               .sorted((o1, o2)->o2.getValue().
                                                                       compareTo(o1.getValue()))
                                                               .collect(Collectors.toMap(
                                                                       Entry::getKey, 
                                                                       Entry::getValue, 
                                                                       (e1, e2) -> e1, 
                                                                       LinkedHashMap::new));
        
        System.out.println("Médias por aluno - Por desempenho:");
        for (Map.Entry<Aluno, Double> notas : ordemDesempenhoa.entrySet()) {
            System.out.printf("%s - %.2f\n", notas.getKey().getNome(), notas.getValue());
        }
        
        return ordemDesempenhoa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCodigo()).append(" - ").append(getNome())
          .append(", Qtd vagas: ").append(qtdVagas)
          .append(", Professor(a): ").append(professor.getMatricula()).append(" - ").append(professor.getNome())
          .append(":\n").append(getAlunosMatriculados().stream().map(Pessoa::toString).collect(Collectors.joining("\n")));
        return sb.toString();
    }
}