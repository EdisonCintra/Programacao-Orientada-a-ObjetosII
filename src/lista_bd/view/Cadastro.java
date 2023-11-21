package lista_bd.view;

import lista_bd.controller.Controller;
import lista_bd.model.Banco;

import javax.swing.*;

public class Cadastro {
    private JPanel panel1;
    private JTextField tfname;
    private JTextField tfusername;
    private JPasswordField tfpass;
    private JPasswordField tfRpass;
    private JButton cadastrarButton;
    private JButton limparButton;
    private JButton voltarButton;

    public Cadastro() {
        JFrame frame = new JFrame("Cadastro");
        frame.setContentPane(panel1);
        frame.setResizable(false);
        frame.setSize(640, 480);
        frame.setLocation(650, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Banco db = Banco.getInstance();
        Controller controller = new Controller();

        cadastrarButton.addActionListener(e -> {
            boolean cadastro = controller.CadastroDeUsuario(tfname.getText(),tfusername.getText(), new String(tfpass.getPassword()),new String(tfRpass.getPassword()));
            if (cadastro){
                tfname.setText(null);
                tfusername.setText(null);
                tfpass.setText(null);
                tfRpass.setText(null);
            }

        });

        limparButton.addActionListener(e -> {
            tfname.setText(null);
            tfusername.setText(null);
            tfpass.setText(null);
            tfRpass.setText(null);
        });

        voltarButton.addActionListener(e -> {
            new Login();
            frame.dispose();
        });
    }
}