/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import utilidades.Exportando;

/**
 *
 * @author AnaMariana
 */
public class ListaTXT extends javax.swing.JFrame {

    Menu_Pesquisa_Importar_Exportar Menu;
    JTable tbPesquisa;
    /**
     * Creates new form OperacaoRealizada
     * @param Menu_Pesquisa_Importar_Exportar2
     * @param tbPesquisa2
     */
    public ListaTXT( Menu_Pesquisa_Importar_Exportar Menu_Pesquisa_Importar_Exportar2, JTable tbPesquisa2 ) { 
        initComponents();
        lbAcao.setText("EXPORTAR LISTA SAP...");
        centralizeFrame();

        tbPesquisa = tbPesquisa2;
        Menu       = Menu_Pesquisa_Importar_Exportar2;
    }
    
    private void centralizeFrame() {  
        int x,y;  
        java.awt.Rectangle scr  = this.getGraphicsConfiguration().getBounds();  
        java.awt.Rectangle form = this.getBounds();  
        x = (int) ( scr.getWidth() - form.getWidth() ) / 2;  
        y = (int) ( scr.getHeight()- form.getHeight()) / 2;  
        this.setLocation( x , y );  
    }
    
    private boolean cadastrarBoo = false;
    private void tempoDeEspera(){
        new Thread() {
            @Override
                public void run() {                    
                    while ( cadastrarBoo == false ) {   
                        cadastrarBoo = true;
                        try { Thread.sleep( 1 );
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");
                        
                            List<String> listaSap = new ArrayList<String>();
                            
                            String str[] = tpListSap.getText().split("\n");
                            if( !str.equals("") ){
                                DefaultTableModel tmPesquisa = new DefaultTableModel();
                                tbPesquisa.setModel(tmPesquisa);
                                try{
                                    while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
                                    tmPesquisa = new DefaultTableModel( null, new String[]{ 
                                        "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX"          
                                    } );
                                    tbPesquisa.setModel(tmPesquisa);
                                    
                                    DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
                                    rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
                                    DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
                                    rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
                                    tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
                                    tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererDireita );
            
                                    tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(2).setCellRenderer( rendererDireita );
            
                                    tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(3).setCellRenderer( rendererDireita );
            
                                    tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(4).setCellRenderer( rendererDireita );
        
                                    tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(5).setCellRenderer( rendererDireita );
        
                                    tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(6).setCellRenderer( rendererDireita );
        
                                    tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(7).setCellRenderer( rendererDireita );
        
                                    tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
                                    tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
         
                                    tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita ); 
                                    
                                    tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita ); 
                                    
                                    tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita ); 
                                    
                                    tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita ); 
                                    
                                    tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(100);
                                    tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
                                    tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita ); 
                                }catch( Exception e ){}
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                Thread.sleep( 1000 );
                                
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
                                    String linha = str[i].trim();
                                //for(String nome:str){ 
                                    StringBuilder t0 = new StringBuilder(); StringBuilder t1 = new StringBuilder(); StringBuilder t2 = new StringBuilder();
                                    StringBuilder t3 = new StringBuilder(); StringBuilder t4 = new StringBuilder(); StringBuilder t5 = new StringBuilder();
                                    StringBuilder t6 = new StringBuilder(); StringBuilder t7 = new StringBuilder(); StringBuilder t8 = new StringBuilder();
                                    StringBuilder t9 = new StringBuilder(); StringBuilder t10 = new StringBuilder(); StringBuilder t11 = new StringBuilder(); 
                                    StringBuilder t12 = new StringBuilder(); StringBuilder t13 = new StringBuilder(); StringBuilder t14 = new StringBuilder(); 
                                    
                                    int b = 0;
                                    String anterior = "x";
                                    
                                    char c[] = linha.toCharArray();
                                    String v = "";
                                    for (int r = 0; r < c.length; r++){  
                                        
                                        v = String.valueOf( c[r] ).trim();
                                        if( v.equals("") ){
                                            
                                            if( !anterior.equals("") ){
                                                
                                                b++;
                                                //System.out.println( anterior );
                                            }
                                        }
                                        else if( !v.equals("") && b == 0 ){   t0.append( v ); }
                                        else if( !v.equals("") && b == 1 ){   t1.append( v ); }
                                        else if( !v.equals("") && b == 2 ){   t2.append( v ); }
                                        else if( !v.equals("") && b == 3 ){   t3.append( v ); }
                                        else if( !v.equals("") && b == 4 ){   t4.append( v ); }
                                        else if( !v.equals("") && b == 5 ){   t5.append( v ); }
                                        else if( !v.equals("") && b == 6 ){   t6.append( v ); }
                                        else if( !v.equals("") && b == 7 ){   t7.append( v ); }
                                        else if( !v.equals("") && b == 8 ){   t8.append( v ); }
                                        else if( !v.equals("") && b == 9 ){   t9.append( v ); }
                                        else if( !v.equals("") && b == 10 ){ t10.append( v ); }
                                        else if( !v.equals("") && b == 11 ){ t11.append( v ); }
                                        else if( !v.equals("") && b == 12 ){ t12.append( v ); }
                                        else if( !v.equals("") && b == 13 ){ t13.append( v ); }
                                        else if( !v.equals("") && b == 14 ){ t14.append( v ); }
                                        
                                        anterior = v;
                                    }
                                                                                                            
                                    tmPesquisa.addRow(new Object[]{ t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14 });
                                    //System.out.println( xc + " - " + xf );
                                    //listaSap.add( nome.trim() );
                                //}   
                                }
                                
                                //Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap( "", "listasap", listaSap );
                            }
                        
                            Exportando.fechar();
                            //fechar();                     
                        }catch( Exception e ){ fechar(); }
                    }
                }
            }.start();
    }
    
    public void fechar(){ 
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lbAcao = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpListSap = new javax.swing.JTextPane();
        tfLogradouro1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/disquete.png"))); // NOI18N

        lbAcao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAcao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAcao.setText("EXPORTAR DADOS DE CADASTRO PARA TABELA JM...");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("COLE AQUI");

        jScrollPane1.setViewportView(tpListSap);

        tfLogradouro1.setEditable(false);
        tfLogradouro1.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro1.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro1.setText("EXPORTAR");
        tfLogradouro1.setToolTipText("CONSULTAR LISTA SAP");
        tfLogradouro1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro1.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro1MousePressed(evt);
            }
        });
        tfLogradouro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAcao, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbAcao))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfLogradouro1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseEntered

    private void tfLogradouro1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseExited

    private void tfLogradouro1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MousePressed
        tempoDeEspera();
    }//GEN-LAST:event_tfLogradouro1MousePressed

    private void tfLogradouro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1KeyReleased

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaTXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaTXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaTXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaTXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ListaSAP().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAcao;
    public javax.swing.JTextField tfLogradouro1;
    private javax.swing.JTextPane tpListSap;
    // End of variables declaration//GEN-END:variables
}
