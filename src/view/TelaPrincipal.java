package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.ExtratoDao;
import model.Extrato;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel txtSaldo;
    private double saldo = 0;
    private JTextField txtValor;
    private JLabel txtBemVindo;
    private String username;

    /**
     * Create the frame.
     */
    public TelaPrincipal(String username) { //Dor de cabeça
        this.username = username; //Pega o nome do usuário
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 280, 405);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(15, 130, 23));
        contentPane.setForeground(new Color(0, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        txtBemVindo = new JLabel("Bem Vindo, " + username);
        txtBemVindo.setBounds(10, 11, 244, 14);
        contentPane.add(txtBemVindo);
        
        JButton btnDeposito = new JButton("Depositar");
        btnDeposito.setBounds(60, 151, 141, 46);
        btnDeposito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double valorDepositado = Double.parseDouble(txtValor.getText());
                if(valorDepositado > 0) {
                    saldo += valorDepositado;
                    txtSaldo.setText("Saldo: $" + Double.toString(saldo));
                    
                    ExtratoDao extratoDao = new ExtratoDao();
                    Extrato extrato = new Extrato();
                    extrato.setTipo("Deposito");
                    extrato.setValor(Double.toString(valorDepositado));
                    extrato.setSaldo(Double.toString(saldo));
                    extratoDao.create(extrato);
                    
                    JOptionPane.showMessageDialog(btnDeposito, "O valor de $" + valorDepositado + " foi depositado com sucesso!");
                } else if(valorDepositado == 0) {
                    JOptionPane.showMessageDialog(btnDeposito, "Valor não pode ser igual a ZERO", "Error", JOptionPane.ERROR_MESSAGE);
                } else if(valorDepositado < 0) {
                    JOptionPane.showMessageDialog(btnDeposito, "Valor não pode ser NEGATIVO", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnDeposito);
        
        txtValor = new JTextField();
        txtValor.setBounds(60, 78, 141, 62);
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(txtValor);
        
        JButton btnSaque = new JButton("Sacar");
        btnSaque.setBounds(60, 208, 141, 46);
        btnSaque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double valorSacado = Double.parseDouble(txtValor.getText());
                if(valorSacado > 0) {
                    if(valorSacado <= saldo) {
                        saldo -= valorSacado;
                        txtSaldo.setText("Saldo: $" + Double.toString(saldo));
                        
                        ExtratoDao extratoDao = new ExtratoDao();
                        Extrato extrato = new Extrato();
                        
                        extrato.setTipo("Saque");
                        extrato.setValor(Double.toString(valorSacado));
                        extrato.setSaldo(Double.toString(saldo));
                        extratoDao.create(extrato);
                        
                        JOptionPane.showMessageDialog(btnSaque, "O valor de $" + valorSacado + " foi sacado com sucesso!");

                        
                    } else {
                        JOptionPane.showMessageDialog(btnSaque, "Saldo Insuficiente");
                    }
                } else if(valorSacado == 0) {
                    JOptionPane.showMessageDialog(btnSaque, "Valor não pode ser igual a ZERO", "Error", JOptionPane.ERROR_MESSAGE);
                } else if(valorSacado < 0) {
                    JOptionPane.showMessageDialog(btnSaque, "Valor não pode ser NEGATIVO", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnSaque);
        
        JButton btnExtrato = new JButton("Extrato");
        btnExtrato.setBounds(60, 265, 141, 46);
        btnExtrato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaExtrato telaExtrato = new TelaExtrato();
                telaExtrato.setVisible(true);
            }
        });
        
        contentPane.add(btnExtrato);
        
        ExtratoDao extratoDao = new ExtratoDao();
        
        txtSaldo = new JLabel("Saldo: $" + Double.toString(extratoDao.obterUltimoSaldo()));
        txtSaldo.setBounds(36, 322, 188, 33);
        txtSaldo.setFont(new Font("Arial", Font.BOLD, 15));
        contentPane.add(txtSaldo);
    }
}
