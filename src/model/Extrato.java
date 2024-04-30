package model;

public class Extrato {
	protected String id, tipo, valor, saldo;
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public String getSaldo() {
		return saldo;
	}


	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	

	public Extrato() {
		
	}


	public Extrato(String id, String tipo, String valor, String saldo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.saldo = saldo;
	}

}
