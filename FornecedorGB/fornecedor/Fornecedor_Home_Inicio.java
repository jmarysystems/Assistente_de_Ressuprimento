/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fornecedor;

import fornecedor_new.Fornecedor_New;
import fornecedor_search.Menu_Pesquisa_Fornecedor;
import fornecedor_search.Tabela_Pesquisa_BD_Fornecedor;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import utilidades.JOPM;

/**
 *
 * @author pc
 */
public class Fornecedor_Home_Inicio {
        
    Fornecedor_Home Fornecedor_Home;//Materias_Home; 
    
    public Fornecedor_Home_Inicio( Fornecedor_Home Fornecedor_Home2 ) {
        
        Fornecedor_Home = Fornecedor_Home2;
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            Fornecedor_Home.pnHome.setLayout( new BorderLayout() );    
        
            Fornecedor_Home.pnHome.add( Fornecedor_Home.Conjunto_Botoes        , BorderLayout.NORTH  );
            Fornecedor_Home.pnHome.add( Fornecedor_Home.tabHome                , BorderLayout.CENTER );    
        
            addAbaPesquisa();
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    //////////////////////////////////////////////////////
    /*public Fornecedor_Home_Inicio( Fornecedor_Home Fornecedor_Home2, Produto_Por_Fornecedor_New Produto_Por_Fornecedor_New2 ) {
        
        Fornecedor_Home = Fornecedor_Home2;
        
        try { Thread.sleep( 1 ); 
        
            Fornecedor_Home.pnHome.setLayout( new BorderLayout() );    
        
            Fornecedor_Home.tabHome = Fornecedor_Home.Produto_Por_Fornecedor_New.Produto_Por_Fornecedor_Home.tabHome;
            
            Menu_Pesquisa_Fornecedor Menu_Pesquisa                           = new Menu_Pesquisa_Fornecedor( Fornecedor_Home, Fornecedor_Home.Produto_Por_Fornecedor_New );
            Tabela_Pesquisa_BD_Fornecedor Tabela_Pesquisa_BD_Fornecedor = new Tabela_Pesquisa_BD_Fornecedor( Fornecedor_Home );
            
            Menu_Pesquisa.tabela(Tabela_Pesquisa_BD_Fornecedor  );
            Tabela_Pesquisa_BD_Fornecedor.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD_Fornecedor, BorderLayout.CENTER  );
            ///////////////////
            
            Fornecedor_Home.pnHome.add( jp               , BorderLayout.CENTER );    
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } 
    }*/
    ////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    /*public Fornecedor_Home_Inicio( Fornecedor_Home Fornecedor_Home2, Menu_Pesquisa_Produto_Por_Fornecedor Menu_Pesquisa_Produto_Por_Fornecedor2 ) {
        
        Fornecedor_Home = Fornecedor_Home2;
        
        try { Thread.sleep( 1 ); 
        
            Fornecedor_Home.pnHome.setLayout( new BorderLayout() );    
        
            Fornecedor_Home.tabHome = Fornecedor_Home.Menu_Pesquisa_Produto_Por_Fornecedor.Produto_Por_Fornecedor_Home.tabHome;
            
            Menu_Pesquisa_Fornecedor Menu_Pesquisa                           = new Menu_Pesquisa_Fornecedor( Fornecedor_Home, Fornecedor_Home.Menu_Pesquisa_Produto_Por_Fornecedor );
            Tabela_Pesquisa_BD_Fornecedor Tabela_Pesquisa_BD_Fornecedor = new Tabela_Pesquisa_BD_Fornecedor( Fornecedor_Home );
            
            Menu_Pesquisa.tabela(Tabela_Pesquisa_BD_Fornecedor  );
            Tabela_Pesquisa_BD_Fornecedor.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD_Fornecedor, BorderLayout.CENTER  );
            ///////////////////
            
            Fornecedor_Home.pnHome.add( jp               , BorderLayout.CENTER );    
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } 
    }
*/    ////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    /*public Fornecedor_Home_Inicio( Fornecedor_Home Fornecedor_Home2, Menu_Pesquisa_Gestor Menu_Pesquisa_Gestor2 ) {
        
        Fornecedor_Home = Fornecedor_Home2;
        
        try { Thread.sleep( 1 ); 
        
            Fornecedor_Home.pnHome.setLayout( new BorderLayout() );    
        
            Fornecedor_Home.tabHome = Fornecedor_Home.Menu_Pesquisa_Gestor.Gestor_Home.tabHome;
            
            Menu_Pesquisa_Fornecedor      Menu_Pesquisa                 = new Menu_Pesquisa_Fornecedor( Fornecedor_Home, Fornecedor_Home.Menu_Pesquisa_Gestor );
            Tabela_Pesquisa_BD_Fornecedor Tabela_Pesquisa_BD_Fornecedor = new Tabela_Pesquisa_BD_Fornecedor( Fornecedor_Home );
            
            Menu_Pesquisa.tabela(Tabela_Pesquisa_BD_Fornecedor  );
            Tabela_Pesquisa_BD_Fornecedor.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD_Fornecedor, BorderLayout.CENTER  );
           
            
            Fornecedor_Home.pnHome.add( jp               , BorderLayout.CENTER );    
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } 
    }*/
    ////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    /*public Fornecedor_Home_Inicio( Fornecedor_Home Fornecedor_Home2, Gestor_New Gestor_New2 ) {
        
        Fornecedor_Home = Fornecedor_Home2;
        
        try { Thread.sleep( 1 ); 
        
            Fornecedor_Home.pnHome.setLayout( new BorderLayout() );    
        
            Fornecedor_Home.tabHome = Fornecedor_Home.Gestor_New.Gestor_Home.tabHome;
          
            Menu_Pesquisa_Fornecedor      Menu_Pesquisa                 = new Menu_Pesquisa_Fornecedor( Fornecedor_Home, Fornecedor_Home.Gestor_New );
            Tabela_Pesquisa_BD_Fornecedor Tabela_Pesquisa_BD_Fornecedor = new Tabela_Pesquisa_BD_Fornecedor( Fornecedor_Home );
            
            Menu_Pesquisa.tabela(Tabela_Pesquisa_BD_Fornecedor  );
            Tabela_Pesquisa_BD_Fornecedor.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD_Fornecedor, BorderLayout.CENTER  );
            
            
            Fornecedor_Home.pnHome.add( jp               , BorderLayout.CENTER );    
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } 
    }*/
    ////////////////////////////////////////////////////////
    
    // Aba Novo 
    public void addAbaNovo(){      
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
           
            //if( Usuario_Permissoes.booCadastrarfornecedor( Usuario_Logado.ID ) == true ) {
                         
                         Fornecedor_Home.Home.ControleTabs.AddTabSemControleNovo( Fornecedor_Home.tabHome, "Novo", "/utilidades_imagens/novo.gif", new Fornecedor_New(Fornecedor_Home) );
             //}     
             //else{
             //    JOPM JOPM = new JOPM( 2, "USUÁRIO SEM AUTORIZAÇÂO, \n"+ "O USUÁRIO LOGADO NÃO TEM PERMISSÃO PARA REALIZAR ESTA AÇÃO" + "\n", this.getClass().getSimpleName() );
             //}
                    
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "addAbaNovo, \n"
                + e.getMessage() + "\n", "Materias_Home_Inicio" ); } } }.start();         
    }
    
    // Cancelar Aba Selecionada
    public void cancelar() {                                           
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );                   
            
            Fornecedor_Home.Home.ControleTabs.removerTabSemControleSelecionado( Fornecedor_Home.tabHome );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "cancelar(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();     
    }
    
    // Aba Pesquisar
    public void addAbaPesquisa(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            Menu_Pesquisa_Fornecedor Menu_Pesquisa                           = new Menu_Pesquisa_Fornecedor( Fornecedor_Home );
            Tabela_Pesquisa_BD_Fornecedor Tabela_Pesquisa_BD_Fornecedor = new Tabela_Pesquisa_BD_Fornecedor( Fornecedor_Home );
            
            Menu_Pesquisa.tabela(Tabela_Pesquisa_BD_Fornecedor  );
            Tabela_Pesquisa_BD_Fornecedor.tabela( Menu_Pesquisa );
                                             
            JPanel jp = new JPanel();
            jp.setLayout( new BorderLayout() );  
            jp.add      ( Menu_Pesquisa, BorderLayout.NORTH  );
            jp.add      ( Tabela_Pesquisa_BD_Fornecedor, BorderLayout.CENTER  );
                                    
            Fornecedor_Home.Home.ControleTabs.AddTabSemControleNovo( Fornecedor_Home.tabHome, "Pesquisar", "/utilidades_imagens/pesquisar.gif", jp );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "addAbaPesquisa(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();                 
    }
         
}
