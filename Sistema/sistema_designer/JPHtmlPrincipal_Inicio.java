/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_designer;

import br.com.jmary.home.Browser.BrowserFX;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import utilidades.JOPM;

/**
 *
 * @author Ana
 */
public class JPHtmlPrincipal_Inicio {
    
    String           url;
    JPHtmlPrincipal  JPHtmlPrincipal;
    
    BarraJEditorPane BarraJEditorPane;    
    
    public JPHtmlPrincipal_Inicio( String url2, JPHtmlPrincipal JPHtmlPrincipal2 ) {

        url              = url2;
        JPHtmlPrincipal  = JPHtmlPrincipal2;
        
        BarraJEditorPane = new BarraJEditorPane( JPHtmlPrincipal.Home, JPHtmlPrincipal );
        
        inicio();
    }       //MediaFX
     
    private void inicio(){
                        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                  
            JPanel p = new JPanel( new BorderLayout() );        
            p.add( BarraJEditorPane, BorderLayout.NORTH );
            
            if( url.endsWith( ".html" ) ){
                
                p.add( new BrowserFX( url ), BorderLayout.CENTER );  
            }
            else if( url.endsWith( ".flv" ) ){
                
                p.add( new BrowserFX( url,true,true ), BorderLayout.CENTER );  
            }
                        
            JPHtmlPrincipal.jSplitPaneHtmlPrincipal.setRightComponent(p);
            
            JPHtmlPrincipal.jSplitPaneHtmlPrincipal.setDividerLocation( 210 );
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "JPHtmlPrincipal_Inicio" ); } } }.start(); 
        
    }
    
}
