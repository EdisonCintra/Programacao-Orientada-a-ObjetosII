package lista.model;

import javax.swing.*;
import java.sql.*;

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
            System.out.println("Não Foi Possível Conectar ao Banco de Dados");
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








}