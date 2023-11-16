package lista02.view;

import lista02.controller.Controller;
import lista02.model.Banco;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Cadastro {
    private JPanel panel1;
    private JTextField tf_name;
    private JTextField tf_username;
    private JPasswordField tf_pass;
    private JPasswordField tf_rpass;
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
            boolean cadastro = controller.CadastroUsuario(tf_name.getText(),tf_username.getText(), new String(tf_pass.getPassword()),new String(tf_rpass.getPassword()));
            if (cadastro){
                tf_name.setText(null);
                tf_username.setText(null);
                tf_pass.setText(null);
                tf_rpass.setText(null);
            }

        });

        limparButton.addActionListener(e -> {
            tf_name.setText(null);
            tf_username.setText(null);
            tf_pass.setText(null);
            tf_rpass.setText(null);
        });

        voltarButton.addActionListener(e -> {
            new Login();
            frame.dispose();
        });
    }
}