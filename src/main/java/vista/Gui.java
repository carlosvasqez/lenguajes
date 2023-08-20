package vista;

import com.formdev.flatlaf.FlatDarkLaf;
import controlador.Analizador;
import controlador.Cargador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author usuario
 */
public class Gui extends javax.swing.JFrame {

    /**
     * Creates new form Gui
     */
    public Gui() {
	initComponents();
	tabulacionPersonalizada();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JToolBar jToolBar1 = new javax.swing.JToolBar();
        javax.swing.JToolBar.Separator jSeparator3 = new javax.swing.JToolBar.Separator();
        botonArchivo = new javax.swing.JButton();
        javax.swing.JToolBar.Separator jSeparator1 = new javax.swing.JToolBar.Separator();
        botonAyuda = new javax.swing.JButton();
        javax.swing.JToolBar.Separator jSeparator2 = new javax.swing.JToolBar.Separator();
        botonAcerdaDe = new javax.swing.JButton();
        javax.swing.JToolBar jToolBar2 = new javax.swing.JToolBar();
        javax.swing.Box.Filler filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        botonAnalizar = new javax.swing.JButton();
        javax.swing.JToolBar.Separator jSeparator4 = new javax.swing.JToolBar.Separator();
        botonGenerarReporte = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        textPaneEditor = new javax.swing.JTextPane();
        panelLineaEditor = new javax.swing.JPanel();
        panelLineaError = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        textPaneError = new javax.swing.JTextPane();
        javax.swing.JLabel labelError = new javax.swing.JLabel();
        javax.swing.JToolBar jToolBar3 = new javax.swing.JToolBar();
        javax.swing.Box.Filler filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        labelFilaColumna = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator3);

        botonArchivo.setText("Archivo");
        botonArchivo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonArchivo.setFocusable(false);
        botonArchivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonArchivo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonArchivoActionPerformed(evt);
            }
        });
        jToolBar1.add(botonArchivo);
        jToolBar1.add(jSeparator1);

        botonAyuda.setText("Ayuda");
        botonAyuda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonAyuda.setFocusable(false);
        botonAyuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAyuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAyudaActionPerformed(evt);
            }
        });
        jToolBar1.add(botonAyuda);
        jToolBar1.add(jSeparator2);

        botonAcerdaDe.setText("Acerca de");
        botonAcerdaDe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonAcerdaDe.setFocusable(false);
        botonAcerdaDe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAcerdaDe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAcerdaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAcerdaDeActionPerformed(evt);
            }
        });
        jToolBar1.add(botonAcerdaDe);

        jToolBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar2.setRollover(true);
        jToolBar2.add(filler1);

        botonAnalizar.setText("Analizar");
        botonAnalizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonAnalizar.setFocusable(false);
        botonAnalizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAnalizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnalizarActionPerformed(evt);
            }
        });
        jToolBar2.add(botonAnalizar);
        jToolBar2.add(jSeparator4);

        botonGenerarReporte.setText("Generar Reporte");
        botonGenerarReporte.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonGenerarReporte.setFocusable(false);
        botonGenerarReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGenerarReporte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGenerarReporteActionPerformed(evt);
            }
        });
        jToolBar2.add(botonGenerarReporte);

        jScrollPane1.setViewportView(textPaneEditor);

        panelLineaEditor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelLineaEditorLayout = new javax.swing.GroupLayout(panelLineaEditor);
        panelLineaEditor.setLayout(panelLineaEditorLayout);
        panelLineaEditorLayout.setHorizontalGroup(
            panelLineaEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLineaEditorLayout.setVerticalGroup(
            panelLineaEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelLineaError.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelLineaErrorLayout = new javax.swing.GroupLayout(panelLineaError);
        panelLineaError.setLayout(panelLineaErrorLayout);
        panelLineaErrorLayout.setHorizontalGroup(
            panelLineaErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );
        panelLineaErrorLayout.setVerticalGroup(
            panelLineaErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(textPaneError);

        labelError.setText("ERROR:");

        jToolBar3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar3.setRollover(true);
        jToolBar3.add(filler2);

        labelFilaColumna.setText("fila:columna");
        labelFilaColumna.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar3.add(labelFilaColumna);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelLineaError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLineaEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelError)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelLineaEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(labelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelLineaError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnalizarActionPerformed
	// TODO add your handling code here:
	Analizador analizador = new Analizador(textPaneEditor);
	analizador.analizar();
    }//GEN-LAST:event_botonAnalizarActionPerformed

    private void botonGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGenerarReporteActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_botonGenerarReporteActionPerformed

    private void botonArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonArchivoActionPerformed
	// TODO add your handling code here:
	Cargador cargadorArchivo = new Cargador(textPaneEditor);
	cargadorArchivo.cargarArchivo();
    }//GEN-LAST:event_botonArchivoActionPerformed

    private void botonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAyudaActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_botonAyudaActionPerformed

    private void botonAcerdaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAcerdaDeActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_botonAcerdaDeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	
	 */
	try {
	    UIManager.setLookAndFeel(new FlatDarkLaf());
	} catch (Exception ex) {
	    System.err.println("Failed to initialize LaF");
	}

//	try {
//	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//		if ("Nimbus".equals(info.getName())) {
//		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//		    break;
//		}
//	    }
//	} catch (ClassNotFoundException ex) {
//	    java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//	} catch (InstantiationException ex) {
//	    java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//	} catch (IllegalAccessException ex) {
//	    java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
//	    java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new Gui().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAcerdaDe;
    private javax.swing.JButton botonAnalizar;
    private javax.swing.JButton botonArchivo;
    private javax.swing.JButton botonAyuda;
    private javax.swing.JButton botonGenerarReporte;
    private javax.swing.JLabel labelFilaColumna;
    private javax.swing.JPanel panelLineaEditor;
    private javax.swing.JPanel panelLineaError;
    private javax.swing.JTextPane textPaneEditor;
    private javax.swing.JTextPane textPaneError;
    // End of variables declaration//GEN-END:variables

    private void tabulacionPersonalizada() {
	textPaneEditor.addKeyListener(new KeyListener() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		// No se necesita implementar esto
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
		    e.consume(); // Evita que el JTextPane maneje la tabulación por defecto
		    insertarTabulacion(textPaneEditor, 8); // Inserta una tabulación de 8 espacios
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		// No se necesita implementar esto
	    }
	});
    }

    private void insertarTabulacion(JTextPane textPane, int espacios) {
	Document document = textPane.getDocument();
	try {
	    document.insertString(textPane.getCaretPosition(), " ".repeat(espacios), null);
	} catch (BadLocationException e) {
	    e.printStackTrace();
	}
    }
}
