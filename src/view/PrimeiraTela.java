package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ClienteDao;

public class PrimeiraTela extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JPasswordField txtPass;
    private JButton btnNovoUser;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrimeiraTela frame = new PrimeiraTela();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PrimeiraTela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Usuário:");
        lblNewLabel.setBounds(98, 60, 59, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Senha:");
        lblNewLabel_1.setBounds(98, 104, 59, 14);
        contentPane.add(lblNewLabel_1);

        txtNome = new JTextField();
        txtNome.setBounds(181, 57, 86, 20);
        contentPane.add(txtNome);
        txtNome.setColumns(10);

        txtPass = new JPasswordField();
        txtPass.setBounds(181, 101, 86, 20);
        contentPane.add(txtPass);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtNome.getText();
                String password = new String(txtPass.getPassword());

                ClienteDao dao = new ClienteDao();

                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    if (dao.checarCliente(username, password)) {
                        TelaPrincipal telaPrincipal = new TelaPrincipal(username);
                        dispose();
                        telaPrincipal.setLocationRelativeTo(null);
                        telaPrincipal.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira usuário e senha");
                }
            }
        });

        btnEntrar.setBounds(181, 161, 89, 23);
        contentPane.add(btnEntrar);

        btnNovoUser = new JButton("Abrir conta");
        btnNovoUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                TelaAbrirConta telaAbrirConta = new TelaAbrirConta();
                telaAbrirConta.setLocationRelativeTo(telaAbrirConta);
                telaAbrirConta.setVisible(true);
            }
        });

        btnNovoUser.setBounds(171, 206, 116, 23);
        contentPane.add(btnNovoUser);
    }

}
