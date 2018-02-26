/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package municipio;

import municipio_new2.Municipio_New;
import municipio_search.Menu_Pesquisa_Municipio;
import municipio_search.Tabela_Pesquisa_BD_Municipio;
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
public class Municipio_Home_Inicio {
        
    Conjunto_Botoes       Conjunto_Botoes;
    Municipio_Home  Municipio_Home;//Materias_Home; 
    
    public Municipio_Home_Inicio( Municipio_Home Embalagem_Home2 ) {
        
        Municipio_Home = Embalagem_Home2;
        
        inicio();
    }
    
    //////////////////////////////////////////////////////
    public Municipio_Home_Inicio( Municipio_Home Estabelecimento_Home2, JTabbedPane tabHome2 ) {
        
        Municipio_Home = Estabelecimento_Home2;
        
        try { Thread.sleep( 1 ); 
        
            Municipio_Home.pnHome.setLayout( new BorderLayout() );    
        
            /**/Municipio_Home.tabHome = tabHome2;
            //////////////////
            Menu_Pesquisa_Municipio      Menu_Pesquisa                      = new Menu_Pesquisa_Municipio( Municipio_Home, tabHome2 );
            Tabela_Pesquisa_BD_Municipio Tabela_Pesquisa_BD_Estabelecimento = new Tabela_Pesquisa_BD_Municipio( Municipio_Home );
            
            Menu_Pesquisa.tabela(Tabela_Pesquisa_BD_Estabelecimento  );
            Tabela_Pesquisa_BD_Estabelecimento.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD_Estabelecimento, BorderLayout.CENTER  );
            ///////////////////
            
            /**/Municipio_Home.pnHome.add( jp               , BorderLayout.CENTER );    
            
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

        Municipio_Home.pnHome.setLayout( new BorderLayout() );   
        
        Conjunto_Botoes = new Conjunto_Botoes( Municipio_Home.Home );
        eventoBotaoNovo();
        eventoBotaoCancelar();
        eventoBotaoPesquisar();
        
        Municipio_Home.pnHome.add( Conjunto_Botoes           , BorderLayout.NORTH  );
        Municipio_Home.pnHome.add(Municipio_Home.tabHome    , BorderLayout.CENTER );    
        
        addAbaPesquisa();
                 
    }  
    
    // Aba Novo 
    public void addAbaNovo(){      
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
           
            Municipio_Home.Home.ControleTabs.AddTabSemControleNovo(Municipio_Home.tabHome, "Novo", "/utilidades_imagens/novo.gif", new Municipio_New(Municipio_Home) );
        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "addAbaNovo, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();         
    }
    
    // Cancelar Aba Selecionada
    public void cancelar() {                                           
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );                   
            
            Municipio_Home.Home.ControleTabs.removerTabSemControleSelecionado(Municipio_Home.tabHome );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "cancelar(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();     
    }
    
    // Aba Pesquisar
    public void addAbaPesquisa(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            Menu_Pesquisa_Municipio Menu_Pesquisa           = new Menu_Pesquisa_Municipio( Municipio_Home );
            Tabela_Pesquisa_BD_Municipio Tabela_Pesquisa_BD = new Tabela_Pesquisa_BD_Municipio( Municipio_Home );
            
            Menu_Pesquisa.tabela( Tabela_Pesquisa_BD  );
            Tabela_Pesquisa_BD.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD, BorderLayout.CENTER  );
                                    
            Municipio_Home.Home.ControleTabs.AddTabSemControleNovo(Municipio_Home.tabHome, "Pesquisar", "/utilidades_imagens/pesquisar.gif", jp );
        
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
                                                 
                         if( Administrador.mapaComandos.get("CADASTRAR_MUNICIPIO") != null ) {
                                                          
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
                                                 
                         if( Administrador.mapaComandos.get("PESQUISAR_MUNICIPIO") != null ) {
                                                          
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
