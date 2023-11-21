package lista_bd.view;

import lista_bd.model.Banco;
import lista_bd.model.Projeto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

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
    private JTextField txtPesquisa;
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
    private JButton listarBtn;
    private JButton imprimirBtn;


    public Projetos() {



        JList<String> list1;


        JFrame frame = new JFrame("Projetos");
        frame.setContentPane(panel1);
        frame.setResizable(false);
        frame.setSize(1300, 800);
        frame.setLocation(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);




        novoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProjetoAoJList();
            }
        });

        limparBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        excluirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirProjetoDoJList();
            }
        });

        editarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione a chamada do método atualizarProjeto aqui
                editarProjeto();
            }
        });

        listarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione a chamada do método atualizarProjeto aqui
                listarProjetos();
            }
        });


        pesquisaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione a chamada do método atualizarProjeto aqui
                pesquisarProjetos();
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

        Banco.getInstance().CadastroProjeto(
                eventoText, cpfText, coordenadorText, campusText,
                tituloText, estudanteText, matriculaText,
                bancoText, contacorrenteText, agenciaText,
                celularText, emailText
        );

        atualizarJList();
    }



    // ...

    private void atualizarJList() {
        List<String> projetos = Banco.getInstance().getProjetosFromDB();

        projetos = projetos.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String projeto : projetos) {
            listModel.addElement(projeto);
        }

        list1.setModel(listModel);
        list1.revalidate();
        list1.repaint();
    }


    private void excluirProjetoDoJList() {
        int selectedIndex = list1.getSelectedIndex();

        if (selectedIndex != -1) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) list1.getModel();

            String projetoSelecionado = listModel.getElementAt(selectedIndex);

            listModel.removeElementAt(selectedIndex);

            Banco.getInstance().excluirProjeto(projetoSelecionado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um projeto para excluir.");
        }
    }


// ...




    private void limparCampos() {
        evento.setText("");
        cpf.setText("");
        coordenador.setText("");
        campus.setText("");
        titulo.setText("");
        estudante.setText("");
        matricula.setText("");
        banco.setText("");
        contacorrente.setText("");
        agencia.setText("");
        celular.setText("");
        email.setText("");
    }


    private void editarProjeto() {
        int selectedIndex = list1.getSelectedIndex();

        if (selectedIndex != -1) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) list1.getModel();


            String projetoSelecionado = listModel.getElementAt(selectedIndex);

            Projeto projeto = Banco.getInstance().getDetalhesProjeto(projetoSelecionado);

            preencherCampos(projeto);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um projeto para editar.");
        }
    }

    private void preencherCampos(Projeto projeto) {
        evento.setText(projeto.getEvento());
        cpf.setText(projeto.getCpf());
        coordenador.setText(projeto.getCoordenador());
        campus.setText(projeto.getCampus());
        titulo.setText(projeto.getTitulo());
        estudante.setText(projeto.getEstudante());
        matricula.setText(projeto.getMatricula());
        banco.setText(projeto.getBanco());
        contacorrente.setText(projeto.getContaCorrente());
        agencia.setText(projeto.getAgencia());
        celular.setText(projeto.getCelular());
        email.setText(projeto.getEmail());
    }

    private void atualizarProjeto() {
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

        if (matriculaText == null || matriculaText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Matrícula não pode ser nula ou vazia");
            return;
        }

        Projeto projetoAtualizado = new Projeto(
                eventoText, cpfText, coordenadorText, campusText,
                tituloText, estudanteText, matriculaText,
                bancoText, contacorrenteText, agenciaText,
                celularText, emailText
        );

        try {
            Banco.getInstance().atualizarProjeto(projetoAtualizado);

            atualizarJList();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    private void listarProjetos() {
        List<String> projetos = Banco.getInstance().getProjetosFromDB();

        atualizarJList(projetos);
    }


    private void atualizarJList(List<String> projetos) {
        projetos = projetos.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String projeto : projetos) {
            listModel.addElement(projeto);
        }

        list1.setModel(listModel);
        list1.revalidate();
        list1.repaint();
    }

    private void pesquisarProjetos() {
        String termoPesquisa = txtPesquisa.getText().trim();

        if (!termoPesquisa.isEmpty()) {
            List<String> projetosPesquisados = Banco.getInstance().pesquisarProjetos(termoPesquisa);

            atualizarJList(projetosPesquisados);
        }
    }










}
