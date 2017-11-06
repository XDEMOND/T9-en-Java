
package Practica3;
import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yatss
 */
public class Predictor extends javax.swing.JFrame {

    Expresion expresion;
    int selector;
    private PrintWriter pw;
    private FileWriter fw;
    private String txt; // texto del archivo
    
    public Predictor() throws FileNotFoundException {
        initComponents();
        expresion = new Expresion();
        selector = 0;
        try{
            FileReader fl = new FileReader("T9.txt");
            BufferedReader bufLec = new BufferedReader(fl);

            String cadRec = bufLec.readLine();
  
            while(cadRec != null){
                StringTokenizer stDic = new StringTokenizer(cadRec);
                txt += stDic.nextToken() + "\n";        // palabra del archivo        
            
                cadRec = bufLec.readLine();
            }
            bufLec.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "LO SENTIMOS ALGO FALLO");
        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtAPalabra = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAPredictor = new javax.swing.JTextArea();
        btnAgregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adivinador de palabras");

        txtAPalabra.setColumns(20);
        txtAPalabra.setForeground(new java.awt.Color(0, 102, 102));
        txtAPalabra.setRows(5);
        txtAPalabra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAPalabraKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtAPalabra);

        txtAPredictor.setColumns(20);
        txtAPredictor.setRows(5);
        txtAPredictor.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtAPredictor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtAPredictorMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtAPredictor);

        btnAgregar.setFont(new java.awt.Font("Kalinga", 1, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar palabras");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel1)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAPalabraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAPalabraKeyReleased

        String cadRec = "";
        String dic = "";
        String predic = "";
        
        try {
            FileReader fl = new FileReader("T9.txt");
            BufferedReader bufLec = new BufferedReader(fl);

            cadRec = bufLec.readLine();
            
            while(cadRec != null){
                StringTokenizer stDic = new StringTokenizer(cadRec);
                dic = stDic.nextToken();        // palabra del archivo        
                
                String palabra = getLastTkn (txtAPalabra.getText());  // ultima palabra del textArea
                
                if (expresion.validarExp(dic.toLowerCase(), palabra.toLowerCase()))
                    predic += dic + "\n";
               
                cadRec = bufLec.readLine();
            }
            bufLec.close();
            txtAPredictor.setText(predic);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, " ERROR INESPERADO");
        }
    }//GEN-LAST:event_txtAPalabraKeyReleased

    private void txtAPredictorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAPredictorMouseReleased
        
        selector++;        
        if (selector == 2){
            
            int recorte = txtAPalabra.getText().length() - getLastTkn(txtAPalabra.getText()).length();
      
            txtAPalabra.setText(txtAPalabra.getText().substring(0, recorte) + txtAPredictor.getSelectedText());
            selector = 0;
        }
    }//GEN-LAST:event_txtAPredictorMouseReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            fw = new FileWriter("T9.txt");
            pw = new PrintWriter(fw);
            String palabra = getLastTkn(txtAPalabra.getText());
            
            
                pw.print(txt);
                pw.println(palabra);                
                pw.close();
                txt += palabra + "\n";
                JOptionPane.showMessageDialog(null, "Cadena: " + palabra + " agregada correctamente :)");
        } catch (IOException ex) {
            Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_btnAgregarActionPerformed

    private String getLastTkn (String l){
        StringTokenizer st = new StringTokenizer(l);
        String tkn = "";        
        while (st.hasMoreTokens()) {
            tkn = st.nextToken();
        }
        return tkn;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Predictor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Predictor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Predictor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Predictor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Predictor().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtAPalabra;
    private javax.swing.JTextArea txtAPredictor;
    // End of variables declaration//GEN-END:variables
}
