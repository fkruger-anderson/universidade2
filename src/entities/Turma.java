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
    private List<Aluno> alunos;
    private Professor professor;
    private List<Nota> notas;

    public Turma(int codigo, String nome, int qtdVagas, Professor professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.qtdVagas = qtdVagas;
        this.alunos = new ArrayList<>();
        this.professor = professor;
        this.notas = new ArrayList<>();
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

    public List<Aluno> getAlunos() {
        return alunos;
    }
    
    public List<Nota> getNotas() {
        return notas;
    }

    public Aluno buscarAluno(String matricula) {
        return getAlunos().stream()
                          .filter(a -> a.getMatricula().equalsIgnoreCase(matricula))
                          .findFirst()
                          .orElse(null);
    }

    public boolean matricularAluno(Aluno aluno) {
        if (getAlunos().size() + 1 <= getQtdVagas() && !getAlunos().contains(aluno)) {
            return getAlunos().add(aluno);
            } else {
                System.out.println("Excedeu o limite de inscritos da turma, ou o aluno já encontra-se matriculado.");
                return false;
            }
    }

    public boolean registrarNotaAluno(String matricula, Double nota) {
        if (!buscarAluno(matricula).equals(null)) {
            return getNotas().add(new Nota(buscarAluno(matricula), nota));
        } else {
            System.out.println("Dados não encontrados nesta Universidade.");
            return false;
        }
    }

    public Map<Aluno, Double> calcMediaAlunos() {
        Map<Aluno, Double> medias = getNotas().stream()
                                              .collect(Collectors.groupingBy(a -> a.getAluno(),
                                                      Collectors.averagingDouble(Nota::getNota)));
        
        /*System.out.println("Médias por aluno:");
        for (Map.Entry<String, Double> notas : medias.entrySet()) {
            System.out.printf("%s - %.2f\n", notas.getKey(), notas.getValue());
        }*/
        
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
          .append(":\n").append(getAlunos().stream().map(Pessoa::toString).collect(Collectors.joining("\n")));
        return sb.toString();
    }
}