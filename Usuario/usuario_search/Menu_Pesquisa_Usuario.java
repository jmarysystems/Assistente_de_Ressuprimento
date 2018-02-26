/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario_search;

import controle_acesso_new.Controle_Acesso_New;
import home_usuario_logado.Bean_Usuario_Logado;
import usuario_0.Usuario;
import usuario_0.Usuario_Home;
import usuario_new.Usuario_New;
import java.awt.event.MouseListener;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JTabbedPane;
import jpa.JPAUtil;
import jpa.DAOGenericoJPA;
import utilidades.Administrador;
import utilidades.JOPM;

/**
 *
 * @author AnaMariana
 */
public class Menu_Pesquisa_Usuario extends javax.swing.JPanel {

    Usuario_Home   Usuario_Home;
    public  int    IDSELECIONADA; 
    public Tabela_Pesquisa_BD_Usuario Tabela_Pesquisa_BD_Usuario;
     
    /**
     * Creates new form Cliente
     * @param Usuario_Home2
     */
    public Menu_Pesquisa_Usuario( Usuario_Home Usuario_Home2 ) {
        initComponents();
        
        Usuario_Home = Usuario_Home2;
        
        btSelecionar.setVisible( false );
    }
    
    ////Menu_Pesquisa_Gestor2//////////////////////////////////////////////////////////////////
    public Menu_Pesquisa_Usuario( Usuario_Home Usuario_Home2, JTabbedPane tabHome ) {
        initComponents();
        
        Usuario_Home = Usuario_Home2;
        
        btSelecionar.setVisible( true );
        btSelecionar.setEnabled(false );
        
        //Eventos
        try{
            MouseListener[] MouseListener = btSelecionar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                btSelecionar.removeMouseListener( MouseListener1 );
            }
        
            btSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    ////////////////////////////
                     if( btSelecionar.isEnabled() ) {            
                         if( IDSELECIONADA > 0 ) {
                    
                             Usuario Usuario = new Usuario();

                             Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.USUARIO WHERE ID = ?", Usuario.class );
                             q.setParameter(1, IDSELECIONADA);   
                    
                             List<usuario_0.Usuario> list = q.getResultList();
                    
                             for (int i = 0; i < list.size(); i++){
                                 Usuario = list.get(i);
                             }
                    
                             //System.out.println("Cliente: " + Clientes.getNome() + " - " + IDSELECIONADA  );
                             String str = Usuario.getLogin();
                             //Venda_Home Venda_Home = new Venda_Home( Usuario_Home.Home, Usuario_Home.tabHome, Estabelecimento );
                             
                             //Estabelecimento_Home.Home.ControleTabs.removerTabSemControleSelecionado( Usuario_Home.tabHome );
                             try { Thread.sleep( 3 ); 
                             
                                 //Estabelecimento_Home.Home.ControleTabs.AddTabSemControleNovo( Usuario_Home.tabHome, str, "/utilidades_imagens/novo.gif", Venda_Home );

                             } catch( Exception e ){}
                         }
                         else{
                             JOPM JOptionPaneMod = new JOPM( 2, "Fornecedor Selecionado, \n"
                                     + "Nenhum Fornecedor Selecionado"
                                     + "\nPara continuar Selecione um Fornecedor"
                                     + "\nNa Tabela Abaixo, clicando no botão Pesquisar"    
                                     + "\n", "Fornecedor Selecionado" );
                             tfPesquisa.requestFocus();
                         }
                     }
                    //////////////////////////// 
                }
            });    
        }catch(Exception e ){}
    }
    //////////////////////////////////////////////////////////////////////
    
    
    ////Menu_Pesquisa_Gestor2//////////////////////////////////////////////////////////////////
    public Menu_Pesquisa_Usuario( Usuario_Home Usuario_Home2, Controle_Acesso_New Controle_Acesso_New2 ) {
        initComponents();
        
        Usuario_Home = Usuario_Home2;
        
        btSelecionar.setVisible( true );
        btSelecionar.setEnabled(false );
        
        //Eventos
        try{
            MouseListener[] MouseListener = btSelecionar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                btSelecionar.removeMouseListener( MouseListener1 );
            }
        
            btSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    ////////////////////////////
                     if( btSelecionar.isEnabled() ) {            
                         if( IDSELECIONADA > 0 ) {
                    
                             Usuario Usuario = new Usuario();

                             Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.USUARIO WHERE ID = ?", Usuario.class );
                             q.setParameter(1, IDSELECIONADA);   
                    
                             List<usuario_0.Usuario> list = q.getResultList();
                    
                             for (int i = 0; i < list.size(); i++){
                                 Usuario = list.get(i);
                             }
                    
                             Usuario_Home.Controle_Acesso_New.setarNoJPanelUsuarioSelecionado( Usuario );
                    
                             Usuario_Home.Home.ControleTabs.removerTabSemControleSelecionado( Usuario_Home.tabHome );
                         }
                         else{
                             JOPM JOptionPaneMod = new JOPM( 2, "Fornecedor Selecionado, \n"
                                     + "Nenhum Fornecedor Selecionado"
                                     + "\nPara continuar Selecione um Fornecedor"
                                     + "\nNa Tabela Abaixo, clicando no botão Pesquisar"    
                                     + "\n", "Fornecedor Selecionado" );
                             tfPesquisa.requestFocus();
                         }
                     }
                    //////////////////////////// 
                }
            });    
        }catch(Exception e ){}
    }
    //////////////////////////////////////////////////////////////////////
    
    
    public void tabela(Tabela_Pesquisa_BD_Usuario Tabela_Pesquisa_BD_Estabelecimento2 ){ 
                
        try {                
            Tabela_Pesquisa_BD_Usuario = Tabela_Pesquisa_BD_Estabelecimento2;                                    
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "tabela, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }     
    }
                
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        rbNome = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        btPesquisar = new javax.swing.JButton();
        tfPesquisa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btExcluir = new javax.swing.JButton();
        btVisualizar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btSelecionar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Dados do Usuário");

        jPanel5.setBackground(new java.awt.Color(253, 254, 247));

        jPanel6.setBackground(new java.awt.Color(253, 254, 247));

        rbNome.setBackground(new java.awt.Color(253, 254, 247));
        rbNome.setSelected(true);
        rbNome.setText("Nome Completo");
        rbNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbNomeMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rbNome)
        );

        jPanel1.setBackground(new java.awt.Color(253, 254, 247));

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/exluir.gif"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setEnabled(false);
        btExcluir.setPreferredSize(new java.awt.Dimension(97, 25));
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/olho.png"))); // NOI18N
        btVisualizar.setText("Visualizar");
        btVisualizar.setEnabled(false);
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/alterar.gif"))); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.setEnabled(false);
        btAlterar.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btVisualizar)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAlterar, btExcluir, btVisualizar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAlterar, btVisualizar});

        btSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/selecionar.png"))); // NOI18N
        btSelecionar.setText("Selecionar");
        btSelecionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSelecionar.setPreferredSize(new java.awt.Dimension(96, 25));
        btSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btSelecionarMousePressed(evt);
            }
        });
        btSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyReleased
        if( tfPesquisa.getText().trim().length() > 2 ){
            
            btPesquisar.setEnabled(false);
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
                if( Administrador.mapaComandos.get("PESQUISAR_USUARIO") != null ) {
                    pesquisar(); 
                    btPesquisar.setEnabled(true);
                } 
                else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                        + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                        + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                        + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                        + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
                    btPesquisar.setEnabled(true);
                }            
            } catch( Exception e ){ } } }.start();        
        }
    }//GEN-LAST:event_tfPesquisaKeyReleased

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        btPesquisar.setEnabled(false);
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
            if( Administrador.mapaComandos.get("PESQUISAR_USUARIO") != null ) {
                pesquisar(); 
                btPesquisar.setEnabled(true);
            } 
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
                btPesquisar.setEnabled(true);
            }            
        } catch( Exception e ){ } } }.start();  
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void rbNomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbNomeMouseReleased
        rbNome.setSelected(true);
    }//GEN-LAST:event_rbNomeMouseReleased

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            if( btAlterar.isEnabled() ) { 
                
                alterarOuVisualizar( "Alterar" );    
            }
        } catch( Exception e ){      } } }.start();       
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            if( btVisualizar.isEnabled() ) { 
                
                alterarOuVisualizar( "Visualizar" );  
            }
        } catch( Exception e ){      } } }.start(); 
    }//GEN-LAST:event_btVisualizarActionPerformed

    private void btSelecionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSelecionarMousePressed
          
    }//GEN-LAST:event_btSelecionarMousePressed

    private void btSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSelecionarActionPerformed

    private void pesquisar() {       
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            String tf = "";
            if( !tfPesquisa.getText().trim().equals("") ){
                tf = tfPesquisa.getText().trim();
            }
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.USUARIO WHERE NOMECOMPLETO LIKE ?", usuario_0.Usuario.class );
            if( !tf.equals("") ){
                q.setParameter(1, "%"+tf.toUpperCase()+"%");
            }
            else{
                q.setParameter(1, "%"+""+"%");
            }
            //Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.EMBALAGEM", embalagem.Embalagem.class );
            
            if(q != null) { Tabela_Pesquisa_BD_Usuario.pesquisa(q); }
                                    
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "pesquisar(), \n"
                + e.getMessage() + "\n", "Menu_Pesquisa" );*/ } } }.start(); 
    }    
    
    private void excluir() {  
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            if( btExcluir.isEnabled() ) {
            
                if( IDSELECIONADA > 0 ) {
                    
                    //////////////////////////////////////////////////////////////////////////////////////////
                    usuario_0.Usuario Usuario = new usuario_0.Usuario();
                    
                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.USUARIO WHERE ID = ?", usuario_0.Usuario.class );
                    q.setParameter(1, IDSELECIONADA);   
                    
                    List<usuario_0.Usuario> list = q.getResultList();
                    
                    for (int i = 0; i < list.size(); i++){
                        Usuario = list.get(i);
                    } 
                    
                    DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA( Usuario, JPAUtil.em()); 
                    DAOGenericoJPA.excluir();
                    //////////////////////////////////////////////////////////////////////////////////////////
 
                    btAlterar   .setEnabled(false);
                    btExcluir   .setEnabled(false);
                    btVisualizar.setEnabled(false);
                    pesquisar();
                }
                else{
                    JOPM JOptionPaneMod = new JOPM( 2, "ID SELECIONADA, "
                            + "\nNenhuma matéria selecionada"
                            + "\nSelecione uma matéria"
                            + "\n", "Matéria selecionada" );
                
                    tfPesquisa.requestFocus();
                }
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "excluir(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private void alterarOuVisualizar(String str) {
        try {                       
                if( IDSELECIONADA > 0 ) {
                    
                    usuario_0.Usuario Estabelecimento = new usuario_0.Usuario();
                    
                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.USUARIO WHERE ID = ?", usuario_0.Usuario.class );
                    q.setParameter(1, IDSELECIONADA);   
                    
                    List<usuario_0.Usuario> list = q.getResultList();
                    
                    for (int i = 0; i < list.size(); i++){
                        Estabelecimento = list.get(i);
                    }
                    
                    if( str.equals( "Visualizar") ){
                        
                        Usuario_New New = new Usuario_New( Usuario_Home, Estabelecimento );
                        Usuario_Home.Home.ControleTabs.AddTabSemControleNovo(Usuario_Home.tabHome, "Visualizar", "/utilidades_imagens/olho.png", New );
                        New.btSalvar   .setVisible(false);
                        New.btAtualizar.setVisible(false);
                    }
                    else if( str.equals( "Alterar") ){
                        
                        Usuario_New New = new Usuario_New( Usuario_Home, Estabelecimento );
                        Usuario_Home.Home.ControleTabs.AddTabSemControleNovo(Usuario_Home.tabHome, "Alterar", "/utilidades_imagens/alterar.gif", New );
                    }
                }
                else{
                    JOPM JOptionPaneMod = new JOPM( 2, "ID SELECIONADA, "
                            + "\nNenhuma USUÁRIO selecionada"
                            + "\nSelecione uma matéria"
                            + "\n", "Matéria selecionada" );
                
                    tfPesquisa.requestFocus();
                }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "alterar(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
                    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAlterar;
    public javax.swing.JButton btExcluir;
    private javax.swing.JButton btPesquisar;
    public javax.swing.JButton btSelecionar;
    public javax.swing.JButton btVisualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JRadioButton rbNome;
    public static javax.swing.JTextField tfPesquisa;
    // End of variables declaration//GEN-END:variables
    
}
