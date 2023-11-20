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


    public List<String> getProjetosFromDB() {
        List<String> projetos = new ArrayList<>();
        Connection connection = getInstance().getCon();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT titulo FROM projetos");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String tituloProjeto = rs.getString("titulo");
                if (tituloProjeto != null && !tituloProjeto.isEmpty()) {
                    projetos.add(tituloProjeto);
                }
            }

            //System.out.println("Projetos obtidos do banco de dados: " + projetos); => so pra saber se está adicionando no Banco
        } catch (SQLException e) {
            System.out.println("Erro ao obter projetos do banco de dados");
            e.printStackTrace();
        }

        return projetos;
    }



    public void excluirProjeto(String titulo) {
        Connection connection = getInstance().getCon();

        try {
            String sql = "DELETE FROM projetos WHERE titulo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, titulo);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Projeto excluído com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir projeto. \nTente novamente mais tarde.");
            e.printStackTrace();
        }
    }


    public Projeto getDetalhesProjeto(String titulo) {
        Connection connection = getInstance().getCon();

        try {
            String sql = "SELECT * FROM projetos WHERE titulo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, titulo);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Obter os dados do projeto do ResultSet
                String evento = rs.getString("evento");
                String cpf = rs.getString("cpf");
                String coordenador = rs.getString("coordenador");
                String campus = rs.getString("campus");
                String tituloProjeto = rs.getString("titulo");
                String estudante = rs.getString("estudante");
                String matricula = rs.getString("matricula");
                String banco = rs.getString("banco");
                String contacorrente = rs.getString("contacorrente");
                String agencia = rs.getString("agencia");
                String celular = rs.getString("celular");
                String email = rs.getString("email");

                // Criar e retornar um objeto Projeto com os dados
                return new Projeto(evento, cpf, coordenador, campus, tituloProjeto,
                        estudante, matricula, banco, contacorrente, agencia, celular, email );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizarProjeto(Projeto projeto) {
        Connection connection = getInstance().getCon();

        try {
            // Verificar se algum campo obrigatório está nulo ou vazio
            if (projeto.getMatricula() == null || projeto.getMatricula().isEmpty()) {
                throw new IllegalArgumentException("Matrícula não pode ser nula ou vazia");
            }

            // Adicione verificações para outros campos obrigatórios, se necessário

            // Restante do código para atualização
            String sql = "UPDATE projetos SET evento = ?, cpf = ?, coordenador = ?, campus = ?, " +
                    "titulo = ?, estudante = ?, matricula = ?, banco = ?, " +
                    "contacorrente = ?, agencia = ?, celular = ?, email = ? WHERE titulo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, projeto.getEvento());
            statement.setString(2, projeto.getCpf());
            statement.setString(3, projeto.getCoordenador());
            statement.setString(4, projeto.getCampus());
            statement.setString(5, projeto.getTitulo());
            statement.setString(6, projeto.getEstudante());
            statement.setString(7, projeto.getMatricula());
            statement.setString(8, projeto.getBanco());  // Corrigido aqui
            statement.setString(9, projeto.getContaCorrente());
            statement.setString(10, projeto.getAgencia());
            statement.setString(11, projeto.getCelular());
            statement.setString(12, projeto.getEmail());

            // Condição WHERE
            statement.setString(13, projeto.getTitulo());

            // Executar a atualização no banco de dados
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<String> pesquisarProjetos(String termoPesquisa) {
        List<String> projetosPesquisados = new ArrayList<>();
        Connection connection = getInstance().getCon();

        try {
            // Utilize a cláusula WHERE para buscar projetos que contenham o termo de pesquisa no título
            String sql = "SELECT titulo FROM projetos WHERE titulo LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + termoPesquisa + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String tituloProjeto = rs.getString("titulo");
                projetosPesquisados.add(tituloProjeto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar projetos no banco de dados");
            e.printStackTrace();
        }

        return projetosPesquisados;
    }


}