/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_barra_menu;

import acesso.Acesso_Home;
import br.com.jmary.home.Browser.BrowserFX;
import cep.Cep_Home;
import controle_acesso.Controle_Acesso_Home;
import estabelecimento.Estabelecimento_Home;
import home_usuario_logado.Bean_Usuario_Logado;
import importar_exportar.Importar_Exportar_Home;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import sistema.Sistema_Home;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import municipio.Municipio_Home;
import pais_0.Pais_Home;
import sistema_submenus.Cadastros;
import sistema_submenus.Configuracoes;
import uf_0.Uf_Home;
import usuario_0.Usuario_Home;
import utilidades.Administrador;
import utilidades.JOPM;

/**
 *
 * @author AnaMariana
 */
public class Sistema_Barra_Menu extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    
    Sistema_Home  Sistema_Home;
    
    Alterar_Seta_Submenu Alterar_Seta_Submenu;

    /**
     * Creates new form PnHomeDesigner
     * @param Fonema_E_Letra_Home2
     */
    public Sistema_Barra_Menu( Sistema_Home Fonema_E_Letra_Home2 ) {
        initComponents();
        ((BasicInternalFrameUI)Fonema_E_Letra_Frame_Interno.getUI()).setNorthPane(null); //retirar o painel superior 
        
        Sistema_Home  = Fonema_E_Letra_Home2;
        
        //Habilita troca de seta
        Alterar_Seta_Submenu = new Alterar_Seta_Submenu( Sistema_Home );
                        
        inicio();
    }
    
    private void inicio(){
        
        //mnConfiguracoes.setVisible(false);
        
        Sistema_Home.Home.Home_Frame_Interno.setJMenuBar( Fonema_E_Letra_Barra_Menu );               
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fonema_E_Letra_Frame_Interno = new javax.swing.JInternalFrame();
        Fonema_E_Letra_Barra_Menu = new javax.swing.JMenuBar();
        menu_seta = new javax.swing.JMenu();
        menu_Ajuda = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        mnConfiguracoes1 = new javax.swing.JMenu();
        mniUsuario = new javax.swing.JMenuItem();
        mniEstabelecimento = new javax.swing.JMenuItem();
        mnConfiguracoes = new javax.swing.JMenu();
        mniUsuario1 = new javax.swing.JMenuItem();
        mniUsuario2 = new javax.swing.JMenuItem();
        menu_Ajuda1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        Fonema_E_Letra_Frame_Interno.setBorder(null);
        Fonema_E_Letra_Frame_Interno.setVisible(true);

        menu_seta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_imagens/seta_para_cima.png"))); // NOI18N
        menu_seta.setToolTipText("Ocultar Submenu");
        menu_seta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_setaMousePressed(evt);
            }
        });
        Fonema_E_Letra_Barra_Menu.add(menu_seta);

        menu_Ajuda.setText("Ajuda");
        menu_Ajuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_AjudaMousePressed(evt);
            }
        });

        jMenuItem7.setText("JMTRUE");
        jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem7MousePressed(evt);
            }
        });
        menu_Ajuda.add(jMenuItem7);
        menu_Ajuda.add(jSeparator3);

        jMenuItem8.setText("TRANSAÇÕES");
        jMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem8MousePressed(evt);
            }
        });
        menu_Ajuda.add(jMenuItem8);

        Fonema_E_Letra_Barra_Menu.add(menu_Ajuda);

        mnConfiguracoes1.setText("Cadastros");
        mnConfiguracoes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnConfiguracoes1MousePressed(evt);
            }
        });

        mniUsuario.setText("Usuário");
        mniUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mniUsuarioMousePressed(evt);
            }
        });
        mniUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUsuarioActionPerformed(evt);
            }
        });
        mnConfiguracoes1.add(mniUsuario);

        mniEstabelecimento.setText("Estabelecimento");
        mniEstabelecimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mniEstabelecimentoMousePressed(evt);
            }
        });
        mniEstabelecimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEstabelecimentoActionPerformed(evt);
            }
        });
        mnConfiguracoes1.add(mniEstabelecimento);

        Fonema_E_Letra_Barra_Menu.add(mnConfiguracoes1);

        mnConfiguracoes.setText("Configurações");
        mnConfiguracoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnConfiguracoesMousePressed(evt);
            }
        });

        mniUsuario1.setText("Acesso");
        mniUsuario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mniUsuario1MousePressed(evt);
            }
        });
        mniUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUsuario1ActionPerformed(evt);
            }
        });
        mnConfiguracoes.add(mniUsuario1);

        mniUsuario2.setText("Controle Acesso");
        mniUsuario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mniUsuario2MousePressed(evt);
            }
        });
        mniUsuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUsuario2ActionPerformed(evt);
            }
        });
        mnConfiguracoes.add(mniUsuario2);

        Fonema_E_Letra_Barra_Menu.add(mnConfiguracoes);

        menu_Ajuda1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/maismais.gif"))); // NOI18N
        menu_Ajuda1.setText("++");
        menu_Ajuda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_Ajuda1MousePressed(evt);
            }
        });

        jMenuItem1.setText("Pais");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        menu_Ajuda1.add(jMenuItem1);

        jMenuItem2.setText("UF");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        menu_Ajuda1.add(jMenuItem2);

        jMenuItem3.setText("Municipio");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem3MousePressed(evt);
            }
        });
        menu_Ajuda1.add(jMenuItem3);

        jMenuItem4.setText("Cep");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem4MousePressed(evt);
            }
        });
        menu_Ajuda1.add(jMenuItem4);
        menu_Ajuda1.add(jSeparator1);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/novo.gif"))); // NOI18N
        jMenuItem5.setText("Importar / Exportar");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem5MousePressed(evt);
            }
        });
        menu_Ajuda1.add(jMenuItem5);
        menu_Ajuda1.add(jSeparator2);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/clienteos.gif"))); // NOI18N
        jMenuItem6.setText("Fornecedores GB");
        jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem6MousePressed(evt);
            }
        });
        menu_Ajuda1.add(jMenuItem6);

        Fonema_E_Letra_Barra_Menu.add(menu_Ajuda1);

        Fonema_E_Letra_Frame_Interno.setJMenuBar(Fonema_E_Letra_Barra_Menu);

        javax.swing.GroupLayout Fonema_E_Letra_Frame_InternoLayout = new javax.swing.GroupLayout(Fonema_E_Letra_Frame_Interno.getContentPane());
        Fonema_E_Letra_Frame_Interno.getContentPane().setLayout(Fonema_E_Letra_Frame_InternoLayout);
        Fonema_E_Letra_Frame_InternoLayout.setHorizontalGroup(
            Fonema_E_Letra_Frame_InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );
        Fonema_E_Letra_Frame_InternoLayout.setVerticalGroup(
            Fonema_E_Letra_Frame_InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fonema_E_Letra_Frame_Interno)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fonema_E_Letra_Frame_Interno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 85, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menu_setaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_setaMousePressed
        Alterar_Seta_Submenu.tabJanelaSubmenu( this.menu_seta );
    }//GEN-LAST:event_menu_setaMousePressed

    
    private void mniEstabelecimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEstabelecimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniEstabelecimentoActionPerformed

    private void mniEstabelecimentoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniEstabelecimentoMousePressed

        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            if( Administrador.mapaComandos.get("ACESSO_ESTABELECIMENTO") != null ) {
                
                Sistema_Home.adicionarSubmenu(new Cadastros( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("Estabelecimentos", "/sistema_imagens/livroTp.gif", new Estabelecimento_Home( Sistema_Home.Home ) );                   
            }
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }
        } catch( Exception e ){ } } }.start(); 
    }//GEN-LAST:event_mniEstabelecimentoMousePressed

    private void mnConfiguracoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnConfiguracoesMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
            
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_mnConfiguracoesMousePressed

    private void menu_AjudaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_AjudaMousePressed
        //Sistema_Home.adicionarSubmenu(new Submenu2( Sistema_Home ) ); 
        
    }//GEN-LAST:event_menu_AjudaMousePressed

    private void mniUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniUsuarioMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            if( Administrador.mapaComandos.get("ACESSO_USUARIO") != null ) {
                
                Sistema_Home.adicionarSubmenu(new Cadastros( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("Usuários", "/sistema_imagens/livroTp.gif", new Usuario_Home( Sistema_Home.Home ) );                     
            }
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_mniUsuarioMousePressed

    private void mniUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniUsuarioActionPerformed

    private void mniUsuario1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniUsuario1MousePressed
 
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            if( Administrador.mapaComandos.get("ACESSO_ACESSO") != null ) {
                
                Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("Acesso", "/sistema_imagens/livroTp.gif", new Acesso_Home( Sistema_Home.Home ) );                   
            }
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_mniUsuario1MousePressed

    private void mniUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniUsuario1ActionPerformed

    private void mniUsuario2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniUsuario2MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            if( Administrador.mapaComandos.get("ACESSO_CONTROLE_ACESSO") != null ) {
                
                Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("Controle Acesso", "/sistema_imagens/livroTp.gif", new Controle_Acesso_Home( Sistema_Home.Home ) );                   
            }
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_mniUsuario2MousePressed

    private void mniUsuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUsuario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniUsuario2ActionPerformed

    private void mnConfiguracoes1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnConfiguracoes1MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            Sistema_Home.adicionarSubmenu(new Cadastros( Sistema_Home ) ); 
            
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_mnConfiguracoes1MousePressed

    private void menu_Ajuda1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_Ajuda1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_Ajuda1MousePressed

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            if( Administrador.mapaComandos.get("ACESSO_PAIS") != null ) {
                
                //Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("Pais", "/sistema_imagens/livroTp.gif", new Pais_Home( Sistema_Home.Home ) );                   
            }
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            if( Administrador.mapaComandos.get("ACESSO_UF") != null ) {
                
                //Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("UF", "/sistema_imagens/livroTp.gif", new Uf_Home( Sistema_Home.Home ) );                   
            }
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jMenuItem2MousePressed

    private void jMenuItem3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            if( Administrador.mapaComandos.get("ACESSO_MUNICIPIO") != null ) {
                
                //Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("MUNICIPIO", "/sistema_imagens/livroTp.gif", new Municipio_Home( Sistema_Home.Home ) );                   
            }
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jMenuItem3MousePressed

    private void jMenuItem4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            if( Administrador.mapaComandos.get("ACESSO_CEP") != null ) {
                
                //Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("CEP", "/sistema_imagens/livroTp.gif", new Cep_Home( Sistema_Home.Home ) );                   
            }
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jMenuItem4MousePressed

    private void jMenuItem5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            //if( Administrador.mapaComandos.get("ACESSO_CEP") != null ) {
                
                //Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("IMPORTAR/EXPORTAR", "/sistema_imagens/livroTp.gif", new Importar_Exportar_Home( Sistema_Home.Home ) );                   
            /*}
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }*/
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jMenuItem5MousePressed

    private void jMenuItem6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            
            //if( Administrador.mapaComandos.get("ACESSO_CEP") != null ) {
                
                //Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) ); 
                
                Sistema_Home.Home.ControleTabs.AddTabsAoHome("FORNECEDORES", "/sistema_imagens/livroTp.gif", new fornecedor.Fornecedor_Home( Sistema_Home.Home ) );                   
            /*}
            else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                    + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                    + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                    + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                    + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
            }*/
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jMenuItem6MousePressed

    private void jMenuItem7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            String url = getClass().getResource( "/sistema_Html/jmtrue.html" ).toString();
        
            JPanel p = new JPanel( new BorderLayout() );        
            //p.add( BarraJEditorPane, BorderLayout.NORTH );
            
            if( url.endsWith( ".html" ) ){
                
                p.add( new BrowserFX( url ), BorderLayout.CENTER );  
            }
            else if( url.endsWith( ".flv" ) ){
                
                p.add( new BrowserFX( url,true,true ), BorderLayout.CENTER );  
            }
            /*else{
                
                p.add( new BrowserFX( url,true ), BorderLayout.CENTER );  
            }*/
        
            Sistema_Home.Home.ControleTabs.AddTabsAoHome("JMTRUE", "/sistema_imagens/livroTp.gif", p );                   
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jMenuItem7MousePressed

    private void jMenuItem8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            String url = getClass().getResource( "/sistema_Html/BLOG_GB.html" ).toString();
        
            JPanel p = new JPanel( new BorderLayout() );        
            //p.add( BarraJEditorPane, BorderLayout.NORTH );
            
            if( url.endsWith( ".html" ) ){
                
                p.add( new BrowserFX( url ), BorderLayout.CENTER );  
            }
            else if( url.endsWith( ".flv" ) ){
                
                p.add( new BrowserFX( url,true,true ), BorderLayout.CENTER );  
            }
            /*else{
                
                p.add( new BrowserFX( url,true ), BorderLayout.CENTER );  
            }*/
        
            Sistema_Home.Home.ControleTabs.AddTabsAoHome("AJUDA", "/sistema_imagens/livroTp.gif", p );                   
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jMenuItem8MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Fonema_E_Letra_Barra_Menu;
    private javax.swing.JInternalFrame Fonema_E_Letra_Frame_Interno;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenu menu_Ajuda;
    private javax.swing.JMenu menu_Ajuda1;
    private javax.swing.JMenu menu_seta;
    private javax.swing.JMenu mnConfiguracoes;
    private javax.swing.JMenu mnConfiguracoes1;
    private javax.swing.JMenuItem mniEstabelecimento;
    private javax.swing.JMenuItem mniUsuario;
    private javax.swing.JMenuItem mniUsuario1;
    private javax.swing.JMenuItem mniUsuario2;
    // End of variables declaration//GEN-END:variables
            
}
