package modelo;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author usuario
 */
public class BotonRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    private JButton button;

    public BotonRenderer() {
	button = new JButton();
	button.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	if (isSelected) {
	    button.setForeground(table.getSelectionForeground());
	    button.setBackground(table.getSelectionBackground());
	} else {
	    button.setForeground(table.getForeground());
	    button.setBackground(UIManager.getColor("Button.background"));
	}

	button.setText((value == null) ? "" : value.toString());
	return button;
    }
}
