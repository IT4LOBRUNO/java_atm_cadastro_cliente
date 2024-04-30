package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {
	
	private static final String[] colunas = {
		"ID", "Tipo", "Valor", "Saldo"	
	};
	
	private ArrayList<Extrato> extratos;
	
	public ModeloTabela(ArrayList<Extrato> extratos) {
		super();
		this.extratos = extratos;
	}

	@Override
	public int getRowCount() {
		return extratos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Extrato extrato = extratos.get(rowIndex);
		if(columnIndex == 0) {
			return extrato.getId();
		}else if(columnIndex == 1) {
			return extrato.getTipo();
		}else if(columnIndex == 2) {
			return extrato.getValor();
		}else if(columnIndex == 3) {
			return extrato.getSaldo();
		} else {
			return null;
		}
	}
	
	public String getColumnName(int column) {
		return colunas[column];
	}


}
