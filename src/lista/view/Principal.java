package lista.view;

import lista.model.Banco;

import javax.swing.*;

public class Principal {
    private JButton sairButton;
    private JPanel panel1;
    private JLabel txt_user;
    private JMenuItem cadastrar;
    private JMenuBar menuBar;
    private JMenu gestao;
    private JMenu produtos;
    private JMenuBar jmenu;
    String name;

    public Principal(String user) {
        JFrame frame = new JFrame("Principal");
        frame.setContentPane(panel1);
        frame.setResizable(false);
        frame.setLocation(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setVisible(true);

        Banco db = Banco.getInstance();
        txt_user.setText("Usuário: " + db.user_name(user));

        sairButton.addActionListener(e->{
            new Login();
            frame.dispose();
        });



        cadastrar.addActionListener(e -> {
            new Projetos();
            frame.dispose();
        });





    }


}
