package lista02.controller;
import lista02.model.Banco;
import lista02.view.Login;
import lista02.view.Principal;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    Banco db = Banco.getInstance();
    public boolean LoginAutentic(String user, String pass){
        if (db.autentic(user, pass)){
            new Principal(user);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Credenciais Inválidas");
        }
        return false;
    }

    public boolean CadastroValid(String name, String username, String pass, String rpass){
        ArrayList<String> usuario = new ArrayList<String>();
        usuario.add(name); usuario.add(username); usuario.add(pass); usuario.add(rpass);

        boolean valid_user = db.validUsername(username);
        boolean valid_pass = Objects.equals(pass, rpass);
        boolean valid_empty = true;
        for (String u : usuario) {
            if (Objects.equals(u, "")) {
                valid_empty = false;
                break;
            }
        }
        System.out.println(valid_empty);

        if (!valid_empty){JOptionPane.showMessageDialog(null, "Preencha Todos os Campos");}
        if (!valid_pass){JOptionPane.showMessageDialog(null, "Senhas não Correspondem");}
        if (!valid_user && !Objects.equals(username, "")){JOptionPane.showMessageDialog(null, "Nome de Usuário já existente");}


        return valid_user && valid_pass && valid_empty;
    }

    public boolean CadastroUsuario(String name, String username, String pass, String rpass){
        if (CadastroValid(name, username, pass, rpass)) {
            db.Cadastro(name, username, pass);
            return true;
        }
        return false;
    }

    public Controller() {
    }

    public static void main(String[] args) {
        new Login();
    }
}