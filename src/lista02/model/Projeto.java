package lista02.model;

public class Projeto {
    String evento;
    String coordenador;
    String campus;
    String titulo;
    String estudante;
    String n_matricula;
    String cpf;
    String n_banco;
    String conta_corrente;
    String agencia;
    String celular;
    String mail;

    public Projeto(String evento, String coordenador, String campus, String titulo, String estudante, String n_matricula, String cpf, String n_banco, String conta_corrente, String agencia, String celular, String mail) {
        this.evento = evento;
        this.coordenador = coordenador;
        this.campus = campus;
        this.titulo = titulo;
        this.estudante = estudante;
        this.n_matricula = n_matricula;
        this.cpf = cpf;
        this.n_banco = n_banco;
        this.conta_corrente = conta_corrente;
        this.agencia = agencia;
        this.celular = celular;
        this.mail = mail;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstudante() {
        return estudante;
    }

    public void setEstudante(String estudante) {
        this.estudante = estudante;
    }

    public String getN_matricula() {
        return n_matricula;
    }

    public void setN_matricula(String n_matricula) {
        this.n_matricula = n_matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getN_banco() {
        return n_banco;
    }

    public void setN_banco(String n_banco) {
        this.n_banco = n_banco;
    }

    public String getConta_corrente() {
        return conta_corrente;
    }

    public void setConta_corrente(String conta_corrente) {
        this.conta_corrente = conta_corrente;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
