/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vizualizar_impressao;

import br.com.jmary.home.Browser.BrowserFX;
import html.Html_Gestor;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import javax.persistence.Query;
import print.ControleThread_Print;
import utilidades.Conjunto_Botoes_Imprimir;
import utilidades.JOPM;

/**
 *
 * @author pc
 */
public class Imprimir_Home_Inicio {
        
    public String            html;
    Conjunto_Botoes_Imprimir Conjunto_Botoes_Imprimir;
    public Imprimir_Home            Imprimir_Home;//Materias_Home; 
    
    public Imprimir_Home_Inicio( Imprimir_Home Imprimir_Home2 ) {
        
        Imprimir_Home = Imprimir_Home2;
        
        inicio();
    }
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            iniciar();
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
                
    }
    
    private void iniciar(){ 
        
        Conjunto_Botoes_Imprimir = new Conjunto_Botoes_Imprimir( Imprimir_Home.Home );
        eventoBotaoImprimir();
        
        Imprimir_Home.jpImprimirSup.setLayout( new BorderLayout() );  
        Imprimir_Home.jpImprimirSup.add( Conjunto_Botoes_Imprimir , BorderLayout.CENTER   );    
        
        Html_Gestor Html_Gestor = new Html_Gestor( this );                 
    }  
    
    // Aba Pesquisar
    public void addAbaVisualizarImpressao(String html2){        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );                     
                        html        = html2;
            BrowserFX   BrowserFX   = new BrowserFX( html, true );
            
            Imprimir_Home.jpImprimirCenter.setViewportView( BrowserFX );    
                     
            Imprimir_Home.Home.ControleTabs.AddTabSemControleNovo( Imprimir_Home.tabQueSeEncontra, "Visualizar Impressão", "/utilidades_imagens/olho.png", Imprimir_Home );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "addAbaVisualizarImpressao, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start();                 
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////
    private void eventoBotaoImprimir(){         
        /*new Thread() {   @Override public void run() {*/ try {     
            //Eventos
            MouseListener[] MouseListener = Conjunto_Botoes_Imprimir.btImprimir.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Conjunto_Botoes_Imprimir.btImprimir.removeMouseListener( MouseListener1 );
            }
            Conjunto_Botoes_Imprimir.btImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {
                                       
                     new Thread() {   @Override public void run() { try { Thread.sleep( 1 );  

                         //PrintHtml PrintHtml =new PrintHtml( html );
                         Query q = null;
                         ControleThread_Print t1 = new ControleThread_Print( Imprimir_Home.tbPesquisa, 1, "" );         
                         t1.setName("ControleThread_Print");           
                         t1.start();
                         
                     } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
                                        
                    Imprimir_Home.Home.ControleTabs.removerTabSemControleSelecionado( Imprimir_Home.tabQueSeEncontra );                    
                }
            });   
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "eventoBotaoNovo(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
}
