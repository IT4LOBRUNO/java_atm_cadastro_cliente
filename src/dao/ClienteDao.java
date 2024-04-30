package dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import factory.ConnectionFactory;
import model.Cliente;

public class ClienteDao {

	public void create(Cliente cliente) {
		String sql = "INSERT INTO cliente(nome, cpf, endereco, telefone, email, usuario, senha) VALUES(?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getCpf());
			pstm.setString(3, cliente.getEndereco());
			pstm.setString(4, cliente.getTelefone());
			pstm.setString(5, cliente.getEmail());
			pstm.setString(6, cliente.getUsuario());
			pstm.setString(7, cliente.getSenha());


			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checarCliente(String usuario, String senha) {
        String sql = "SELECT nome FROM cliente WHERE usuario = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql)) {
            
            pstm.setString(1, usuario);
            pstm.setString(2, senha);
            try (ResultSet rs = pstm.executeQuery()) {
                
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
