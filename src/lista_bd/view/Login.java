package lista_bd.view;

import lista_bd.controller.Controller;

import javax.swing.*;

public class Login {
    private JPanel panel1;
    private JTextField tfuser
            ;
    private JPasswordField tfpass;
    private JButton entrarButton;
    private JButton limparButton;
    private JButton btnRegister;

    public Login() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(panel1);
        frame.setResizable(false);
        frame.setSize(640, 480);
        frame.setLocation(650, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Controller controller = new Controller();


        btnRegister.addActionListener(e -> {
            new Cadastro();
            frame.dispose();
        });

        entrarButton.addActionListener(e ->{
            if (controller.LoginAutentic(tfuser
                    .getText(), new String (tfpass.getPassword()))){
                frame.dispose();
            }
        });

        limparButton.addActionListener(e ->{
            tfuser
                    .setText(null);
            tfpass.setText(null);
        });
    }
}