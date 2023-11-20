package lista_bd.model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    static final String url = "jdbc:mysql://localhost:3306/poo2_lista2";
    static final String user = "root";
    static final String pass = "";
    static Banco instance;
    private Connection con;

    public static Banco getInstance() {
        if (instance == null) {
            instance = new Banco();
        }
        return instance;
    }

    public Connection getCon() {
        return con;
    }

    private Banco() {
        try {
            this.con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Não Foi Possível Conectar ao Banco de Dados!!!");
            e.printStackTrace();
        }
    }

    public boolean validUsername(String username) {
        Banco db = Banco.getInstance();
        Connection connection = db.getCon();

        try {
            PreparedStatement statement = connection.prepareStatement("select * from usuarios where user_name = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return !rs.next();

        } catch (SQLException e) {
            System.out.println("Erro ao consultar");
            return false;
        }
    }

    public void Cadastro(String _name, String _username, String _pass) {
        Banco db = Banco.getInstance();
        Connection connection = db.getCon();

        try {
            String sql = "INSERT INTO usuarios (name, user_name, pass) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, _name);
            statement.setString(2, _username);
            statement.setString(3, _pass);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário " + _username + " cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário. \nTente novamente mais tarde.");
        }
    }

    public boolean autentic(String user, String pass) {
        Banco db = Banco.getInstance();
        Connection connection = db.getCon();

        try {
            PreparedStatement statement = connection.prepareStatement("select * from usuarios where user_name = ? and pass = ?");
            statement.setString(1, user);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error");
            return false;
        }
    }

    public String user_name(String user) {
        Banco db = Banco.getInstance();
        Connection connection = db.getCon();
        String name = null;

        try {
            PreparedStatement statement = connection.prepareStatement("select name from usuarios where user_name = ?");
            statement.setString(1, user);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                name = rs.getString("name");
            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
        return name;
    }



    ///A PARTIR DAQUI


    public void CadastroProjeto(String evento, String cpf, String coordenador, String campus,
                                String titulo, String estudante, String matricula,
                                String banco, String contacorrente, String agencia,
                                String celular, String email) {
        Banco db = Banco.getInstance();
        Connection connection = db.getCon();

        try {
            // Verificar se algum campo está vazio
            if (camposEstaoVazios(evento, cpf, coordenador, campus, titulo, estudante, matricula, banco, contacorrente, agencia, celular, email)) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de cadastrar");
                return;
            }

            String sql = "INSERT INTO projetos (evento, cpf, coordenador, campus, titulo, estudante, matricula, banco, contacorrente, agencia, celular, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, evento);
            statement.setString(2, cpf);
            statement.setString(3, coordenador);
            statement.setString(4, campus);
            statement.setString(5, titulo);
            statement.setString(6, estudante);
            statement.setString(7, matricula);
            statement.setString(8, banco);
            statement.setString(9, contacorrente);
            statement.setString(10, agencia);
            statement.setString(11, celular);
            statement.setString(12, email);

            // Executar a inserção no banco de dados
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Projeto cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar projeto. \nTente novamente mais tarde.");
            e.printStackTrace();
        }
    }

    private boolean camposEstaoVazios(String... campos) {
        for (String campo : campos) {
            if (campo.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public List<String> getProjetos() {
        List<String> projetos = new ArrayList<>();
        Connection connection = getInstance().getCon();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT titulo FROM projetos");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String tituloProjeto = rs.getString("titulo");
                projetos.add(tituloProjeto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter projetos do banco de dados");
            e.printStackTrace();
        }

        return projetos;
    }













}