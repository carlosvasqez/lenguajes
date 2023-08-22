package modelo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author usuario
 */
public class BotonEditor extends DefaultCellEditor {

    private final JButton button;
    private final JTable tabla;
    private JDialog dialog = new JDialog();

    public BotonEditor(JCheckBox checkBox, JTable tabla) {
	super(checkBox);
	button = new JButton();
	this.tabla = tabla;
	button.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
		int filaClic = tabla.getSelectedRow();

		if (filaClic != -1) {
		    Object valor = tabla.getValueAt(filaClic, tabla.getColumnCount() - 1);

		    Token token = (Token) valor;
		    
		    dialog.setSize(400, 400);
		    dialog.setLayout(new BorderLayout());

		    ImageIcon icon = new ImageIcon(token.getPathGrafico());
		    JLabel label = new JLabel(icon);

		    JScrollPane scrollPane = new JScrollPane(label);
		    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		    dialog.getContentPane().removeAll();
		    dialog.add(scrollPane, BorderLayout.CENTER);
		    dialog.revalidate();
		    dialog.repaint();

		    dialog.setVisible(true);
		}
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
