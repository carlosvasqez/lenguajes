package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author usuario
 */
public class ModeloTabla extends AbstractTableModel {

    private String[] columnas = {"TOKEN", "PATRON", "LEXEMA", "FILAS", "COLUMNA", "GRAFICO"};
    private List<Token> listaTokens = new ArrayList<>();

    @Override
    public String getColumnName(int column) {
	return columnas[column];
    }

    @Override
    public int getRowCount() {
	return listaTokens.size();
    }

    @Override
    public int getColumnCount() {
	return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	switch (columnIndex) {
	    case 0:
		return listaTokens.get(rowIndex).getTokenEnum().toString();
	    case 1:
		return listaTokens.get(rowIndex).getPatron();
	    case 2:
		return listaTokens.get(rowIndex).getLexena();
	    case 3:
		return listaTokens.get(rowIndex).getFila();
	    case 4:
		return listaTokens.get(rowIndex).getColumna();
	    case 5:
		return listaTokens.get(rowIndex);
	    default:
		throw new AssertionError();
	}
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
	if (columnIndex == 5) {
	    return JButton.class;
	}
	return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return columnIndex == 5;
    }

    public void agregarFila(Token token) {
	listaTokens.add(token);
	fireTableDataChanged();
    }

    public void limpiarTabla() {
	listaTokens.clear();
	fireTableDataChanged();
    }

//    @Override
//    public String getColumnName(int column) {
//	return columnas[column];
//    }
//
//    @Override
//    public int getRowCount() {
//	return dato.length;
//    }
//
//    @Override
//    public int getColumnCount() {
//	return columnas.length;
//    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//	return dato[rowIndex][columnIndex];
//    }
//
//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//	if (columnIndex == 5) {
//	    return JButton.class;
//	}
//	return String.class;
//    }
//
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//	return columnIndex == 5;
//    }
//
//    public void agregarFila(Object[] filaDato) {
//	int cantidadFilas = getRowCount();
//	Object[][] nuevoDato = new Object[cantidadFilas + 1][getColumnCount()];
//
//	for (int i = 0; i < cantidadFilas; i++) {
//	    nuevoDato[i] = dato[i];
//	}
//
//	nuevoDato[cantidadFilas] = filaDato;
//	dato = nuevoDato;
//	fireTableDataChanged();
//    }
//
//    public void limpiarTabla() {
//	Object[][] datoLimpio = {};
//	dato = datoLimpio;
//	fireTableDataChanged();
//    }
}
