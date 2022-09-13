package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author 07337354959
 */
public abstract class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private String CPF;

    public Pessoa(String nome, String dataNascimento, String CPF) {
        this.nome = nome;
        this.dataNascimento = LocalDate.parse(dataNascimento, dtf);
        this.CPF = CPF;
    }
    
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.dataNascimento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.CPF, other.CPF)) {
            return false;
        }
        return Objects.equals(this.dataNascimento, other.dataNascimento);
    }

    @Override
    public String toString() {
        return String.format("%s, Data de Nasc.: %s, CPF: %s",
                getNome(),
                getDataNascimento().format(dtf),
                getCPF());
    }
}
