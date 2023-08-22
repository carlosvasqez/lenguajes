package modelo;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author usuario
 */
public class BotonEditor extends DefaultCellEditor {

    private JButton button;

    public BotonEditor(JCheckBox checkBox) {
	super(checkBox);
	button = new JButton();
	button.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
		//acion para mostrar diagrrama
		
	    }
	});
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	button.setText((value == null) ? "" : value.toString());
	return button;
    }

    @Override
    public Object getCellEditorValue() {
	return button.getText();
    }
}
