package lista_bd.view;

import lista_bd.model.Banco;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Projetos {
    private JPanel panel1;
    private JTextField evento;
    private JTextField cpf;
    private JTextField coordenador;
    private JTextField campus;
    private JTextField titulo;
    private JTextField estudante;
    private JTextField matricula;
    private JTextField banco;
    private JTextField contacorrente;
    private JTextField agencia;
    private JTextField celular;
    private JTextField email;
    private JList list1;
    private JButton novoBtn;
    private JButton editarBtn;
    private JButton excluirBtn;
    private JButton limparBtn;
    private JTextField textField13;
    private JButton pesquisaBtn;
    private JLabel label_evento;
    private JLabel label_coordenador;
    private JLabel label_campus;
    private JLabel label_titulo;
    private JLabel label_estudante;
    private JLabel label_matricula;
    private JLabel label_cpf;
    private JLabel label_banco;
    private JLabel label_contacorrente;
    private JLabel label_agencia;
    private JLabel label_celular;
    private JLabel label_email;

    public Projetos() {
        JFrame frame = new JFrame("Projetos");
        frame.setContentPane(panel1);
        frame.setResizable(false);
        frame.setSize(1200, 480);
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        novoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProjetoAoJList();
            }
        });
    }



    //A PARTIR DAQUI

    private void adicionarProjetoAoJList() {
        // Obter os dados dos campos JTextField
        String eventoText = evento.getText();
        String cpfText = cpf.getText();
        String coordenadorText = coordenador.getText();
        String campusText = campus.getText();
        String tituloText = titulo.getText();
        String estudanteText = estudante.getText();
        String matriculaText = matricula.getText();
        String bancoText = banco.getText();
        String contacorrenteText = contacorrente.getText();
        String agenciaText = agencia.getText();
        String celularText = celular.getText();
        String emailText = email.getText();

        // Imprimir os valores dos campos para verificar
        System.out.println("Evento: " + eventoText);
        System.out.println("CPF: " + cpfText);
        System.out.println("Coordenador: " + coordenadorText);
        System.out.println("Campus: " + campusText);
        System.out.println("Titulo: " + tituloText);
        System.out.println("Estudante: " + estudanteText);
        System.out.println("Matrícula: " + matriculaText);
        System.out.println("Banco: " + bancoText);
        System.out.println("Conta Corrente: " + contacorrenteText);
        System.out.println("Agência: " + agenciaText);
        System.out.println("Celular: " + celularText);
        System.out.println("Email: " + emailText);

        // Chamar o método de cadastro no banco de dados para projetos
        Banco.getInstance().CadastroProjeto(
                eventoText, cpfText, coordenadorText, campusText,
                tituloText, estudanteText, matriculaText,
                bancoText, contacorrenteText, agenciaText,
                celularText, emailText
        );

        // Atualizar a JList com os projetos
        atualizarJList();
    }

    private void atualizarJList() {
        // Obter a lista de projetos do banco de dados
        List<String> projetos = Banco.getInstance().getProjetos();

        // Atualizar a JList com os dados obtidos
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String projeto : projetos) {
            listModel.addElement(projeto);
        }

        list1.setModel(listModel);
    }
}
