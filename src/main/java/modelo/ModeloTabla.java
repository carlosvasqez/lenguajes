package modelo;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author usuario
 */
public class ModeloTabla extends AbstractTableModel {

    private String[] columnas = {"TOKEN", "PATRON", "LEXEMA", "LINEA", "COLUMNA", "GENERAR GRAFICO"};
    private Object[][] dato = {};

    @Override
    public int getRowCount() {
	return dato.length;
    }

    @Override
    public int getColumnCount() {
	return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	return dato[rowIndex][columnIndex];
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

    public void addRow(Object[] filaDato) {
	int cantidadFilas = getRowCount();
	Object[][] nuevoDato = new Object[cantidadFilas + 1][getColumnCount()];

	for (int i = 0; i < cantidadFilas; i++) {
	    nuevoDato[i] = dato[i];
	}

	nuevoDato[cantidadFilas] = filaDato;
	dato = nuevoDato;
	fireTableDataChanged();
    }

}
