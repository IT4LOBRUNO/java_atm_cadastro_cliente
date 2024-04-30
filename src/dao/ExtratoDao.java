package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import factory.ConnectionFactory;
import model.Extrato;

public class ExtratoDao {

	public void create(Extrato extrato) {
		String sql = "INSERT INTO extrato(tipo, valor, saldo) VALUES(?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setString(1, extrato.getTipo());
			pstm.setString(2, extrato.getValor());
			pstm.setString(3, extrato.getSaldo());

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

	public List<Extrato> getExtrato() {
	    String sql = "SELECT * FROM extrato";
	    List<Extrato> extratos = new ArrayList<>();
	    try (Connection conn = ConnectionFactory.createConnectionToMySQL();
	            PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
	            ResultSet rset = pstm.executeQuery()) {

	        while (rset.next()) {
	            Extrato extrato = new Extrato();
	            extrato.setId(rset.getString("id"));
	            extrato.setTipo(rset.getString("tipo"));
	            extrato.setValor(rset.getString("valor"));
	            extrato.setSaldo(rset.getString("saldo"));
	            
	            extratos.add(extrato);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return extratos;
	}
	
	public double obterUltimoSaldo() {
	    String sql = "SELECT saldo FROM extrato ORDER BY id DESC LIMIT 1";
	    double ultimoSaldo = 0.0;
	    
	    try (Connection conn = ConnectionFactory.createConnectionToMySQL();
	         PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
	         ResultSet rset = pstm.executeQuery()) {

	        if (rset.next()) {
	            ultimoSaldo = Double.parseDouble(rset.getString("saldo"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return ultimoSaldo;
	}


}
