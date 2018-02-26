/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import gb_evento.Gbproduto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import utilidades.Exportando;

/**
 *
 * @author AnaMariana
 */
public class ListaSAPNaoCadastrado extends javax.swing.JFrame {

    Menu_Pesquisa_Importar_Exportar Menu;
    /**
     * Creates new form OperacaoRealizada
     * @param Menu_Pesquisa_Importar_Exportar2
     */
    public ListaSAPNaoCadastrado( Menu_Pesquisa_Importar_Exportar Menu_Pesquisa_Importar_Exportar2 ) { 
        initComponents();
        lbAcao.setText("PRODUTOS NÃO CADASTRADOS...");
        centralizeFrame();

        Menu = Menu_Pesquisa_Importar_Exportar2;
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
                        
                            Set<String> listaSap = new HashSet<String>();
                            
                            String str[] = tpListSap.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                Thread.sleep( 1000 );
                                
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
                                    String nome = str[i];
                                //for(String nome:str){ 
                                    //System.out.println(nome);
                                    listaSap.add( nome.trim() );
                                //}   
                                }
                                
                                // ORDENAR LISTA
                                Set<String> ordem = new TreeSet<String>(listaSap);
                                
                                Thread.sleep( 1 );
                                List<Gbproduto> GbprodutoListaSap = new ArrayList<Gbproduto>();
                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                
                                int c = 0;
                                for (Iterator<String> it = ordem.iterator(); it.hasNext();) {
                                    try{
                                        
                                        c++;
                                        Exportando.pbg.setValue( c );
                                        
                                        String busca = it.next().trim();
                                        
                                        List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                        try{ 
                                            
                                            XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                                        }catch( Exception e ){ }
                                        
                                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                         
                                        if( busca.equals(rbusca) ){
                                            
                                            GbprodutoListaSap.add( XXGbprodutoListaSap.get(0) );
                                        }
                                        else{
                                            
                                            System.out.println("PRODUTO NÃO CADASTRADO: " + busca );
                                        }                                                                              

                                        //System.out.println("vvvvvv " + XXGbprodutoListaSap.get(0).getMaterial() );
                                    }catch( Exception e ){}
                                }
                                
                                Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap2Layout( "", "", GbprodutoListaSap ); 
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
        lbAcao.setText("PRODUTOS NÃO CADASTRADOS...");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LISTA SAP");

        jScrollPane1.setViewportView(tpListSap);

        tfLogradouro1.setEditable(false);
        tfLogradouro1.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro1.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro1.setText("PRODUTOS");
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
                .addComponent(lbAcao, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 162, Short.MAX_VALUE)))
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
        //tempoDeEspera();
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
            java.util.logging.Logger.getLogger(ListaSAPNaoCadastrado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaSAPNaoCadastrado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaSAPNaoCadastrado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaSAPNaoCadastrado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    public javax.swing.JTextPane tpListSap;
    // End of variables declaration//GEN-END:variables
}
