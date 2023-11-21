package lista_bd.model;

public class Projeto {
    String evento;
    String coordenador;
    String campus;
    String titulo;
    String estudante;
    String matricula;
    String cpf;
    String banco;
    String contacorrente;
    String agencia;
    String celular;
    String email;

    public Projeto(String evento, String coordenador, String campus, String titulo, String estudante, String matricula, String cpf, String banco, String contacorrente, String agencia, String celular, String email) {
        this.evento = evento;
        this.coordenador = coordenador;
        this.campus = campus;
        this.titulo = titulo;
        this.estudante = estudante;
        this.matricula = matricula;
        this.cpf = cpf;
        this.banco = banco;
        this.contacorrente = contacorrente;
        this.agencia = agencia;
        this.celular = celular;
        this.email = email;
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
        return matricula;
    }

    public void setN_matricula(String matricula) {
        this.matricula = Projeto.this.matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getN_banco() {
        return banco;
    }

    public void setN_banco(String banco) {
        this.banco = Projeto.this.banco;
    }

    public String getConta_corrente() {
        return contacorrente;
    }

    public void setConta_corrente(String contacorrente) {
        this.contacorrente = Projeto.this.contacorrente;
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
        return email;
    }

    public void setMail(String email) {
        this.email = Projeto.this.email;
    }




    public String getMatricula() {
        return matricula;
    }



    public String getBanco() {
        return matricula;
    }


    public String getContaCorrente() {
        return contacorrente;
    }



    public String getEmail() {
        return email;
    }


}
