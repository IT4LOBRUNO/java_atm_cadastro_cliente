package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClienteDao;
import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TelaAbrirConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JLabel idUser;
	private JLabel idSenha;
	private JLabel idConfirmar;
	private JTextField txtUser;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAbrirConta frame = new TelaAbrirConta();
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
	public TelaAbrirConta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idNome = new JLabel("Nome:");
		idNome.setBounds(68, 37, 70, 14);
		contentPane.add(idNome);
		
		JLabel idCpf = new JLabel("CPF:");
		idCpf.setBounds(68, 62, 70, 14);
		contentPane.add(idCpf);
		
		JLabel idEndereco = new JLabel("Endereço:");
		idEndereco.setBounds(68, 87, 70, 14);
		contentPane.add(idEndereco);
		
		JLabel idTelefone = new JLabel("Telefone:");
		idTelefone.setBounds(68, 112, 70, 14);
		contentPane.add(idTelefone);
		
		txtNome = new JTextField();
		txtNome.setBounds(148, 34, 412, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(148, 59, 412, 20);
		contentPane.add(txtCpf);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(148, 84, 412, 20);
		contentPane.add(txtEndereco);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(148, 109, 412, 20);
		contentPane.add(txtTelefone);
		
		JLabel idEmail = new JLabel("E-Mail:");
		idEmail.setBounds(68, 137, 70, 14);
		contentPane.add(idEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(148, 134, 412, 20);
		contentPane.add(txtEmail);
		
		JButton btnNewButton = new JButton("Concluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSenha.getText().equals(txtConfirmar.getText())) {
					
					ClienteDao clienteDao = new ClienteDao();
					Cliente cliente = new Cliente();
					
					cliente.setNome(txtNome.getText());
					cliente.setCpf(txtCpf.getText());
					cliente.setEndereco(txtEndereco.getText());
					cliente.setTelefone(txtTelefone.getText());
					cliente.setEmail(txtEmail.getText());
					cliente.setUsuario(txtUser.getText());
					cliente.setSenha(txtSenha.getText());
					
					clienteDao.create(cliente);
					
					
					JOptionPane.showMessageDialog(btnNewButton, "Usuário " + txtUser.getText()+" criado");
		            dispose(); // Fechar a janela se as senhas coincidirem
		        } else {
		        	JOptionPane.showMessageDialog(btnNewButton, "Senha diferente da confirmação", "Cheque a senha", JOptionPane.WARNING_MESSAGE);
		        }
				
			}
		});
		btnNewButton.setBounds(258, 330, 103, 40);
		contentPane.add(btnNewButton);
		
		idUser = new JLabel("Usuário:");
		idUser.setBounds(68, 190, 70, 14);
		contentPane.add(idUser);
		
		idSenha = new JLabel("Senha:");
		idSenha.setBounds(68, 218, 70, 14);
		contentPane.add(idSenha);
		
		idConfirmar = new JLabel("Confirmar Senha:");
		idConfirmar.setBounds(19, 246, 119, 14);
		contentPane.add(idConfirmar);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(148, 187, 412, 20);
		contentPane.add(txtUser);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(148, 212, 412, 20);
		contentPane.add(txtSenha);
		
		txtConfirmar = new JPasswordField();
		txtConfirmar.setBounds(148, 240, 412, 20);
		contentPane.add(txtConfirmar);
	}
}
