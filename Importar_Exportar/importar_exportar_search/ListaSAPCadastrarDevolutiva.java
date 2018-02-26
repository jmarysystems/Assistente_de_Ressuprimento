/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import gb_evento.Eventoprodutos;
import gb_evento.Gbdescricaosetor;
import gb_evento.Gbelenco;
import gb_evento.Gbestoquecdb001;
import gb_evento.Gbestoquecdb184;
import gb_evento.Gbestoquecdb289;
import gb_evento.Gbestoquelojab141;
import gb_evento.Gbmrp;
import gb_evento.Gbooportsemana;
import gb_evento.Gbpedidosativos;
import gb_evento.Gbpontoextra;
import gb_evento.Gbproduto;
import gb_evento.Gbra;
import gb_evento.Gbsuply;
import gb_evento.Gbzris;
import java.util.List;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import utilidades.Exportando;
import utilidades.ExportarExcelExistente;
import utilidades.JOPM;
import utilidades_imagens.Imagens;

/**
 *
 * @author AnaMariana
 */
public class ListaSAPCadastrarDevolutiva extends javax.swing.JFrame {

    Menu_Pesquisa_Importar_Exportar Menu;
    int IDSELECIONADA = 0;
    /**
     * Creates new form OperacaoRealizada
     * @param IDSELECIONADA2
     * @param Menu_Pesquisa_Importar_Exportar2
     */
    public ListaSAPCadastrarDevolutiva( int IDSELECIONADA2, Menu_Pesquisa_Importar_Exportar Menu_Pesquisa_Importar_Exportar2 ) { 
        initComponents();
        lbAcao.setText("CADASTRAR: DEVOLUTIVA / VENDA");
        centralizeFrame();

        Menu = Menu_Pesquisa_Importar_Exportar2;
        IDSELECIONADA = IDSELECIONADA2;
    }
    
    private void centralizeFrame() {  
        int x,y;  
        java.awt.Rectangle scr  = this.getGraphicsConfiguration().getBounds();  
        java.awt.Rectangle form = this.getBounds();  
        x = (int) ( scr.getWidth() - form.getWidth() ) / 2;  
        y = (int) ( scr.getHeight()- form.getHeight()) / 2;  
        this.setLocation( x , y );  
    }
            
    public void fechar(){ 
        this.dispose();
    }
    
    private void cadastrarDevolutiva(){        
        new Thread() {
            @Override
                public void run() {                    
                        try { 
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");

                            String str[] = tpListLojaSap.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                    
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
 
                                    String linha = str[i].trim();
                                    StringBuilder sap = new StringBuilder(); StringBuilder qtd = new StringBuilder();
                                    
                                    int cuntP = 0;
                                    
                                    int b = 0;
                                    String anterior = "x";
                                    
                                    char c[] = linha.toCharArray();
                                    String v = "";
                                    for (int r = 0; r < c.length; r++){  
                                        
                                        v = String.valueOf( c[r] ).trim();
                                        if( v.equals("") ){
                                            
                                            if( !anterior.equals("") ){
                                                
                                                b++;
                                            }
                                        }
                                        else if( !v.equals("") && b == 0 ){
                                            
                                            sap.append( v ); 
                                        }
                                        else if( !v.equals("") && b == 1 ){  
                                            
                                            qtd.append( v ); 
                                        }
                                    }
                                                                                                            
                                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                                                                                                            
                                    List<Eventoprodutos> XXEventoprodutos = null;
                                    String idEvento=""; try{ idEvento = String.valueOf(IDSELECIONADA); }catch( Exception e ){ }
                                    try {     
                
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Eventoprodutos.class, JPAUtil.em());
                                            XXEventoprodutos = (List<Eventoprodutos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Eventoprodutos.class, 
                                                    "SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ? AND ( SCHEMAJMARY.EVENTOPRODUTOS.ITEM = ? )", idEvento, busca );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXEventoprodutos.get(0).getItem(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){ 
                                            
                                            String t1 = ""; 
                                            try{ 
                                                t1 = qtd.toString().trim();
                                                if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                                                double bc  = Double.parseDouble( t1 );
                                    
                                                String t2 = XXEventoprodutos.get(0).getRegional();
                                                if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                                double b2 = Double.parseDouble( t2 );
                                    
                                                double x = bc + b2;
                                    
                                                t1 = String.valueOf(x);
                                                System.out.println(" Double b - " + t1 );
                                            } catch( Exception e ){} 
                                            
                                            String xx = "";
                                            try{ xx = qtd.toString().trim(); } catch( Exception e ){} 
                                            if( xx.equals("*") ){ 
                                                
                                                XXEventoprodutos.get(0).setRegional( "" );
                                             
                                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXEventoprodutos.get(0), JPAUtil.em());
                                                Eventoprodutos Eventoprodutos_Cadastrado = (Eventoprodutos) DAOGenericoJPA2.gravanovoOUatualizar(); 
                                            }  
                                            else{
                                                
                                                XXEventoprodutos.get(0).setRegional( t1.replace(".", ",") );
                                             
                                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXEventoprodutos.get(0), JPAUtil.em());
                                                Eventoprodutos Eventoprodutos_Cadastrado = (Eventoprodutos) DAOGenericoJPA2.gravanovoOUatualizar(); 
                                            }                                            
                                                                                                                                     
                                        }
                                        else{
                                            System.out.println("NÃO CADASTRADO");
                                        }
                                    }catch( Exception e ){}                                    
                                }                                
                            }
                        
                            Exportando.fechar();                     
                        }catch( Exception e ){ fechar(); }
                }
            }.start();
    }
    
    private void cadastrarVenda(){        
        new Thread() {
            @Override
                public void run() {                    
                        try { 
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");

                            String str[] = tpListLojaSap.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                    
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
 
                                    String linha = str[i].trim();
                                    StringBuilder sap = new StringBuilder(); StringBuilder qtd = new StringBuilder();
                                    
                                    int b = 0;
                                    String anterior = "x";
                                    
                                    char c[] = linha.toCharArray();
                                    String v = "";
                                    for (int r = 0; r < c.length; r++){  
                                        
                                        v = String.valueOf( c[r] ).trim();
                                        if( v.equals("") ){
                                            
                                            if( !anterior.equals("") ){
                                                
                                                b++;
                                            }
                                        }
                                        else if( !v.equals("") && b == 0 ){
                                            
                                            sap.append( v ); 
                                        }
                                        else if( !v.equals("") && b == 1 ){  
                                            
                                            qtd.append( v ); 
                                        }
                                    }
                                                                                                            
                                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                                    
                                    //System.out.println( busca + " - " + qtd + " - " + IDSELECIONADA);
                                    
                                    List<Eventoprodutos> XXEventoprodutos = null;
                                    String idEvento=""; try{ idEvento = String.valueOf(IDSELECIONADA); }catch( Exception e ){ }
                                    try {     
                
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Eventoprodutos.class, JPAUtil.em());
                                            XXEventoprodutos = (List<Eventoprodutos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Eventoprodutos.class, 
                                                    "SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ? AND ( SCHEMAJMARY.EVENTOPRODUTOS.ITEM = ? )", idEvento, busca );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXEventoprodutos.get(0).getItem(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){ 

                                            String t1 = ""; 
                                            try{ 
                                                t1 = qtd.toString().trim();
                                                if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                                                double bc  = Double.parseDouble( t1 );
                                    
                                                String t2 = XXEventoprodutos.get(0).getCd();
                                                if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                                double b2 = Double.parseDouble( t2 );
                                    
                                                double x = bc + b2;
                                    
                                                t1 = String.valueOf(x);
                                                System.out.println(" Double b - " + t1 );
                                            } catch( Exception e ){} 
                                            
                                            String xx = "";
                                            try{ xx = qtd.toString().trim(); } catch( Exception e ){} 
                                            if( xx.equals("*") ){ 
                                                
                                                XXEventoprodutos.get(0).setCd( "" );
                                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXEventoprodutos.get(0), JPAUtil.em());
                                                Eventoprodutos Eventoprodutos_Cadastrado = (Eventoprodutos) DAOGenericoJPA2.gravanovoOUatualizar(); 
                                            }  
                                            else{
                                                
                                                XXEventoprodutos.get(0).setCd( t1.replace(".", ",") );
                                             
                                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXEventoprodutos.get(0), JPAUtil.em());
                                                Eventoprodutos Eventoprodutos_Cadastrado = (Eventoprodutos) DAOGenericoJPA2.gravanovoOUatualizar();
                                            }                                                                                                                                                                                                                           
                                                
                                        }
                                        else{
                                            System.out.println("NÃO CADASTRADO");
                                        }
                                    }catch( Exception e ){}                                    
                                }                                
                            }
                        
                            Exportando.fechar();                     
                        }catch( Exception e ){ fechar(); }
                }
            }.start();
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
        tpListLojaSap = new javax.swing.JTextPane();
        tfLogradouro1 = new javax.swing.JTextField();
        tfLogradouro2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/disquete.png"))); // NOI18N

        lbAcao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAcao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAcao.setText("CADASTRAR: DEVOLUTIVA / VENDA");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LISTA SAP - QTD");

        jScrollPane1.setViewportView(tpListLojaSap);

        tfLogradouro1.setEditable(false);
        tfLogradouro1.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro1.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro1.setText("CADASTRAR DEVOLUTIVA");
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

        tfLogradouro2.setEditable(false);
        tfLogradouro2.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro2.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro2.setText("CADASTRAR VENDA EVENTO");
        tfLogradouro2.setToolTipText("CONSULTAR LISTA SAP");
        tfLogradouro2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro2.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro2MousePressed(evt);
            }
        });
        tfLogradouro2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAcao, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfLogradouro2, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLogradouro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 133, Short.MAX_VALUE)))
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
        cadastrarDevolutiva();
    }//GEN-LAST:event_tfLogradouro1MousePressed

    private void tfLogradouro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1KeyReleased

    private void tfLogradouro2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2MouseEntered

    private void tfLogradouro2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2MouseExited

    private void tfLogradouro2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MousePressed
        cadastrarVenda();
    }//GEN-LAST:event_tfLogradouro2MousePressed

    private void tfLogradouro2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2KeyReleased

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
            java.util.logging.Logger.getLogger(ListaSAPCadastrarDevolutiva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaSAPCadastrarDevolutiva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaSAPCadastrarDevolutiva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaSAPCadastrarDevolutiva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    public javax.swing.JTextField tfLogradouro2;
    private javax.swing.JTextPane tpListLojaSap;
    // End of variables declaration//GEN-END:variables

    ListaSAPNaoCadastrado ListaSAPNaoCadastrado;
    Exportando Exportando;
    String planModelo = "";
    public void relatorioDevolutiva( String planModelo2 ){ planModelo = planModelo2;    
        new Thread() {   @Override public void run() {
                
        fechar(); 
        
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoEventosDevolutiva();
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
            
            String loja = ""; try{ loja = JOptionPane.showInputDialog( "Qual a loja?" ).toUpperCase(); }catch( Exception e ){ }
                                                        
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ?", Eventoprodutos.class );
            q.setParameter( 1, IDSELECIONADA );
            List<Eventoprodutos> list = q.getResultList();   
            if( list.size() > -1 && !loja.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( list.size() );
                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
             
                for (int i = 0; i < list.size(); i++){
                    Exportando.pbg.setValue( i );
                    
                    String busca = ""; try{ busca = list.get(i).getItem(); }catch( Exception e ){ }
                                    
                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                    try{ 
                                        
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                    if( busca.equals(rbusca) ){
                        if( !busca.equals("") ){
                                            
                            relatorioDevolutiva2( loja, XXGbprodutoListaSap.get(0), list.get(i) );                                                 
                        }
                    }     
                    else{
                                        
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                }  
                                
                habilitado = true;
            }
            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ Exportando.fechar(); fechar(); }
                        
        if(habilitado==true){
               
            String saida = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
            ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, planModelo, "MODELO_ANALISE_DEVOLUTIVA_EVENTOS.xlsx" );
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
      
        } }.start();
    }
    
    private void relatorioDevolutiva2( String loja, Gbproduto Gbproduto, Eventoprodutos Eventoprodutos ){ 
              
        try{    
            
             /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            if( !qtdEmPed.equals("") ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = Gbproduto.getMaterial(); }catch( Exception e ){}
            String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
                    
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
            
            String ra="";
            try {
                
                List<Gbra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ra = XXGbraListaSap.get(0).getRaDoDia();                        
                }
                
            }catch( Exception e ){}
            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
            String pedUmb="";
            String pedRem="";
            String pedSai="";
            String pedForn="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7D   = XXGbpedidosativosListaSap.get(0).getPedidoCd184Ativo();  
                    pedUmb  = XXGbpedidosativosListaSap.get(0).getEs(); 
                    pedRem  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa(); 
                    pedSai  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                    pedForn = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
            
            //Analisedevolutiva
            String aposta   = ""; try{ aposta = String.valueOf( Eventoprodutos.getAdicionalEmCaixas()); }catch( Exception e ){}
            String devolutiva = ""; 
            try{ 
                devolutiva = String.valueOf( Eventoprodutos.getRegional()); 
                if( devolutiva.equals("null") ){ devolutiva = ""; }
            }catch( Exception e ){}
            String venda   = ""; try{ 
                venda = String.valueOf( Eventoprodutos.getCd()); 
                if( venda.equals("null") ){ venda = ""; }
            }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                    
                    int contador = 0;
                    int columnCount = 24;
                    
                    String[] dados = new String[columnCount];
                    
                    dados [contador] = loja; contador++;
                    dados [contador] = descricaosetor; contador++;
                    dados [contador] = zsap; contador++;
                    dados [contador] = Gbproduto.getDescricao(); contador++;
                    dados [contador] = mrp; contador++;
                    
                    dados [contador] = classe; contador++;
                    dados [contador] = pontExtra; contador++;
                    dados [contador] = elenco; contador++;                                                  
                    dados [contador] = String.valueOf(b001); contador++;
                    dados [contador] = String.valueOf(b184); contador++;
                    
                    dados [contador] = String.valueOf(b289); contador++;                    
                    dados [contador] = String.valueOf(esto); contador++;                    
                    dados [contador] = zdisp; contador++;
                    dados [contador] = qtdEmPed; contador++;
                    
                    dados [contador] = aposta; contador++;
                    dados [contador] = devolutiva; contador++;
                    dados [contador] = venda; contador++;
                    
                    dados [contador] = ra; contador++;
                    dados [contador] = ped7D; contador++;
                    dados [contador] = pedUmb; contador++;
                    dados [contador] = pedRem; contador++;
                    dados [contador] = pedSai; contador++;
                    
                    dados [contador] = pedForn; contador++;
                    
                    //Menu.Tabela_Pesquisa_BD_Estabelecimento.eventosDevolutiva(dados);  
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.addRow( dados );
                
        } } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    }
    
}
