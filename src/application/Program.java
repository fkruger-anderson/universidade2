package application;

import entities.Universidade;

public class Program {
    public static void main(String[] args) {
        Universidade uni1 = new Universidade("UDESC", "01.768.417.0001/61");
        uni1.criarCurso("Engenharia de Software", "01/10/2010", "Curso para formar desenvolvedores");
        uni1.buscarCurso(1).adicionarProfessor("01/01/2020", "Fernando", "01/05/1970", "016.958.969-20");
        uni1.buscarCurso(1).adicionarProfessor("01/01/2020", "Matheus", "01/05/1970", "016.958.969-20");

        uni1.matricularAlunoUni("Anderson", "15/03/1993", "073.373.549-59");
        uni1.matricularAlunoUni("Gabriela", "15/03/1993", "073.373.549-59");
        uni1.matricularAlunoUni("Jaqueline", "15/03/1993", "073.373.549-59");
        uni1.matricularAlunoUni("Ricardo", "15/03/1993", "073.373.549-59");
        uni1.matricularAlunoUni("João", "15/03/1993", "073.373.549-59");

        uni1.buscarCurso(1).criarTurma("Programação I", 30, "P20201");
        
        uni1.buscarCurso(1).buscarTurma(1).matricularAlunoTurma(uni1.buscarAluno("A19931"));
        uni1.buscarCurso(1).buscarTurma(1).matricularAlunoTurma(uni1.buscarAluno("A19932"));
        uni1.buscarCurso(1).buscarTurma(1).matricularAlunoTurma(uni1.buscarAluno("A19933"));
        uni1.buscarCurso(1).buscarTurma(1).matricularAlunoTurma(uni1.buscarAluno("A19934"));
        uni1.buscarCurso(1).buscarTurma(1).matricularAlunoTurma(uni1.buscarAluno("A19935"));

        uni1.buscarCurso(1).criarTurma("Programação II", 30, "P20202");
        
        uni1.buscarCurso(1).buscarTurma(2).matricularAlunoTurma(uni1.buscarAluno("A19931"));
        uni1.buscarCurso(1).buscarTurma(2).matricularAlunoTurma(uni1.buscarAluno("A19932"));
        uni1.buscarCurso(1).buscarTurma(2).matricularAlunoTurma(uni1.buscarAluno("A19933"));
        uni1.buscarCurso(1).buscarTurma(2).matricularAlunoTurma(uni1.buscarAluno("A19934"));
        uni1.buscarCurso(1).buscarTurma(2).matricularAlunoTurma(uni1.buscarAluno("A19935"));

        System.out.println(uni1);
        
        uni1.buscarCurso(1).buscarTurma(2).registrarNotaAluno("A19931", 8.0);
        uni1.buscarCurso(1).buscarTurma(2).registrarNotaAluno("A19932", 7.0);
        uni1.buscarCurso(1).buscarTurma(2).registrarNotaAluno("A19933", 5.0);
        uni1.buscarCurso(1).buscarTurma(2).registrarNotaAluno("A19934", 9.0);
        uni1.buscarCurso(1).buscarTurma(2).registrarNotaAluno("A19931", 5.0);
        uni1.buscarCurso(1).buscarTurma(2).registrarNotaAluno("A19931", 6.0);

        uni1.buscarCurso(1).buscarTurma(2).calcMediaAlunos();
        System.out.println();
        uni1.buscarCurso(1).buscarTurma(2).mediasOrdemAlfabetica();
        System.out.println();
        uni1.buscarCurso(1).buscarTurma(2).mediasOrdemDesempenho();
        System.out.println();
        uni1.buscarCurso(1).alunosInscritosCurso();

    }
}