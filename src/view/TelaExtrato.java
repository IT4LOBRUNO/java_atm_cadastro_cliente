package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import dao.ExtratoDao;
import model.Extrato;
import model.ModeloTabela;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaExtrato extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ArrayList<Extrato> extratos;
    private JButton idVoltar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaExtrato frame = new TelaExtrato();
                    frame.setLocationRelativeTo(frame);
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
    public TelaExtrato() {
        extratos = new ArrayList<>();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 804, 411);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(45, 44, 700, 295);
        contentPane.add(scrollPane);
        ModeloTabela modeloTabela = new ModeloTabela(extratos);
        
        table = new JTable();
        table.setModel(modeloTabela);
        scrollPane.setViewportView(table);
        
        idVoltar = new JButton("Voltar");
        idVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        idVoltar.setBounds(45, 11, 89, 23);
        contentPane.add(idVoltar);
        
        // Obtendo os extratos do banco de dados e atualizando a tabela
        ExtratoDao extratoDao = new ExtratoDao();
        ArrayList<Extrato> extratosDoBanco = (ArrayList<Extrato>) extratoDao.getExtrato();
        setExtratos(extratosDoBanco);
    }
    
    // Método extratos
    public void setExtratos(ArrayList<Extrato> extratos) {
        this.extratos = extratos;
        ModeloTabela modeloTabela = new ModeloTabela(extratos);
        table.setModel(modeloTabela);
    }
}
