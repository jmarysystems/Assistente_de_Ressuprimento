/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package importar_exportar;

import controle_acesso_new.Controle_Acesso_New;
import importar_exportar_search.Menu_Pesquisa_Importar_Exportar;
import importar_exportar_search.Tabela_Pesquisa_BD_Importar_Exportar;
import home_usuario_logado.Bean_Usuario_Logado;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import utilidades.Administrador;
import utilidades.Conjunto_Botoes;
import utilidades.JOPM;

/**
 *
 * @author pc
 */
public class Importar_Exportar_Home_Inicio {
        
    Conjunto_Botoes       Conjunto_Botoes;
    Importar_Exportar_Home  Estabelecimento_Home;//Materias_Home; 
    
    public Importar_Exportar_Home_Inicio( Importar_Exportar_Home Embalagem_Home2 ) {
        
        Estabelecimento_Home = Embalagem_Home2;
        
        inicio();
    }
    
    //////////////////////////////////////////////////////
    public Importar_Exportar_Home_Inicio( Importar_Exportar_Home Estabelecimento_Home2, JTabbedPane tabHome2 ) {
        
        Estabelecimento_Home = Estabelecimento_Home2;
        
        try { Thread.sleep( 1 ); 
        
            Estabelecimento_Home.pnHome.setLayout( new BorderLayout() );    
        
            /**/Estabelecimento_Home.tabHome = tabHome2;
            //////////////////
            Menu_Pesquisa_Importar_Exportar      Menu_Pesquisa                      = new Menu_Pesquisa_Importar_Exportar( Estabelecimento_Home, tabHome2 );
            Tabela_Pesquisa_BD_Importar_Exportar Tabela_Pesquisa_BD_Estabelecimento = new Tabela_Pesquisa_BD_Importar_Exportar( Estabelecimento_Home );
            
            Menu_Pesquisa.tabela(Tabela_Pesquisa_BD_Estabelecimento  );
            Tabela_Pesquisa_BD_Estabelecimento.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD_Estabelecimento, BorderLayout.CENTER  );
            ///////////////////
            
            /**/Estabelecimento_Home.pnHome.add( jp               , BorderLayout.CENTER );    
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } 
    }
    ////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    public Importar_Exportar_Home_Inicio( Importar_Exportar_Home Estabelecimento_Home2, Controle_Acesso_New Controle_Acesso_New2 ) {
        
        Estabelecimento_Home = Estabelecimento_Home2;
        
        try { Thread.sleep( 1 ); 
        
            Estabelecimento_Home.pnHome.setLayout( new BorderLayout() );    
        
            /**/Estabelecimento_Home.tabHome = Estabelecimento_Home.Controle_Acesso_New.Controle_Acesso_Home.tabHome;
            //////////////////
            Menu_Pesquisa_Importar_Exportar      Menu_Pesquisa                      = new Menu_Pesquisa_Importar_Exportar( Estabelecimento_Home, Estabelecimento_Home.Controle_Acesso_New );
            Tabela_Pesquisa_BD_Importar_Exportar Tabela_Pesquisa_BD_Estabelecimento = new Tabela_Pesquisa_BD_Importar_Exportar( Estabelecimento_Home );
            
            Menu_Pesquisa.tabela(Tabela_Pesquisa_BD_Estabelecimento  );
            Tabela_Pesquisa_BD_Estabelecimento.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD_Estabelecimento, BorderLayout.CENTER  );
            ///////////////////
            
            /**/Estabelecimento_Home.pnHome.add( jp               , BorderLayout.CENTER );    
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } 
    }
    ////////////////////////////////////////////////////////

        
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            iniciar();
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
                
    }
    
    private void iniciar(){

        Estabelecimento_Home.pnHome.setLayout( new BorderLayout() );   
        
        Conjunto_Botoes = new Conjunto_Botoes( Estabelecimento_Home.Home );
        Conjunto_Botoes.btNovo.setVisible(false);
        eventoBotaoNovo();
        eventoBotaoCancelar();
        eventoBotaoPesquisar();
        
        Estabelecimento_Home.pnHome.add( Conjunto_Botoes           , BorderLayout.NORTH  );
        Estabelecimento_Home.pnHome.add(Estabelecimento_Home.tabHome    , BorderLayout.CENTER );    
        
        addAbaPesquisa();
                 
    }  
    
    // Aba Novo 
    public void addAbaNovo(){      
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
           
            //Estabelecimento_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Novo", "/utilidades_imagens/novo.gif", new Estabelecimento_New(Importar_Exportar_Home) );
        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "addAbaNovo, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();         
    }
    
    // Cancelar Aba Selecionada
    public void cancelar() {                                           
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );                   
            
            Estabelecimento_Home.Home.ControleTabs.removerTabSemControleSelecionado(Estabelecimento_Home.tabHome );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "cancelar(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();     
    }
    
    // Aba Pesquisar
    public void addAbaPesquisa(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            Menu_Pesquisa_Importar_Exportar Menu_Pesquisa           = new Menu_Pesquisa_Importar_Exportar( Estabelecimento_Home );
            Tabela_Pesquisa_BD_Importar_Exportar Tabela_Pesquisa_BD = new Tabela_Pesquisa_BD_Importar_Exportar( Estabelecimento_Home );
            
            Menu_Pesquisa.tabela( Tabela_Pesquisa_BD  );
            Tabela_Pesquisa_BD.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD, BorderLayout.CENTER  );
                                    
            Estabelecimento_Home.Home.ControleTabs.AddTabSemControleNovo(Estabelecimento_Home.tabHome, "Pesquisar", "/utilidades_imagens/pesquisar.gif", jp );
        
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "addAbaPesquisa(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();                 
    }
        
    /////////////////////////////////////////////////////////////////////////////////////
    private void eventoBotaoNovo(){         
        /*new Thread() {   @Override public void run() {*/ try {     
            //Eventos
            MouseListener[] MouseListener = Conjunto_Botoes.btNovo.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Conjunto_Botoes.btNovo.removeMouseListener( MouseListener1 );
            }
            Conjunto_Botoes.btNovo.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {
                     /*
                     Conjunto_Botoes.btNovo.setEnabled(false);
                     new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                         
                         if( Administrador.acesso("CADASTRAR_ESTABELECIMENTO") == true ) {
                                                          
                             addAbaNovo();
                             Conjunto_Botoes.btNovo.setEnabled(true);
                         } 
                         else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                                     + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                                     + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                                     + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                                     + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
                                Conjunto_Botoes.btNovo.setEnabled(true);
                         }            
                     } catch( Exception e ){ } } }.start(); */
                     Conjunto_Botoes.btNovo.setEnabled(false);
                     new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
                         if( Administrador.mapaComandos.get("CADASTRAR_ESTABELECIMENTO") != null ) {
                                                          
                             addAbaNovo();
                             Conjunto_Botoes.btNovo.setEnabled(true);
                         } 
                         else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                                     + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                                     + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                                     + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                                     + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
                                Conjunto_Botoes.btNovo.setEnabled(true);
                         }            
                     } catch( Exception e ){ } } }.start();            
                }
            });   
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "eventoBotaoNovo(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void eventoBotaoCancelar(){         
        /*new Thread() {   @Override public void run() {*/ try {     
            //Eventos
            MouseListener[] MouseListener = Conjunto_Botoes.btCancelar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Conjunto_Botoes.btCancelar.removeMouseListener( MouseListener1 );
            }
            Conjunto_Botoes.btCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    cancelar();
                }
            });   
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "eventoBotaoCancelar(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void eventoBotaoPesquisar(){         
        /*new Thread() {   @Override public void run() {*/ try {     
            //Eventos
            MouseListener[] MouseListener = Conjunto_Botoes.btPesquisar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Conjunto_Botoes.btPesquisar.removeMouseListener( MouseListener1 );
            }
            Conjunto_Botoes.btPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {
                    /*
                    Conjunto_Botoes.btPesquisar.setEnabled(false);
                     new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                         
                         if( Administrador.acesso("PESQUISAR_ESTABELECIMENTO") == true ) {
                                                          
                             addAbaPesquisa();
                             Conjunto_Botoes.btPesquisar.setEnabled(true);
                         } 
                         else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                                     + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                                     + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                                     + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                                     + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
                                Conjunto_Botoes.btPesquisar.setEnabled(true);
                         }            
                     } catch( Exception e ){ } } }.start();  
                     */
                     
                     Conjunto_Botoes.btPesquisar.setEnabled(false);
                     new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
                         if( Administrador.mapaComandos.get("PESQUISAR_ESTABELECIMENTO") != null ) {
                                                          
                             addAbaPesquisa();
                             Conjunto_Botoes.btPesquisar.setEnabled(true);
                         } 
                         else{ JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, "
                                     + "\nO USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO"
                                     + "\nUSUÁRIO LOGADO: " + Bean_Usuario_Logado.NOMECOMPLETO
                                     + "\nSOLICITE AUTORIZAÇÃO AO ADMINISTRADOR"
                                     + "\n", "USUÁRIO SEM AUTORIZAÇÂO" );
                                Conjunto_Botoes.btPesquisar.setEnabled(true);
                         }            
                     } catch( Exception e ){ } } }.start();
                      
                }
            });   
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "eventoBotaoPesquisar(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
         
}
