/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_barra_menu;

import sistema.Sistema_Home;
import sistema_submenus.Configuracoes;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import utilidades.JOPM;

/**
 *
 * @author Ana
 */
public class Alterar_Seta_Submenu {
    
    Sistema_Home Sistema_Home;
        
    URL       imgURL; 
    ImageIcon icon;
            
    URL       imgURL2;
    ImageIcon icon2;
    
    public Alterar_Seta_Submenu( Sistema_Home Fonema_E_Letra_Home2 ) {
        
        Sistema_Home = Fonema_E_Letra_Home2;
        
        setarUrl_e_ImageIcon();
    }
    
    private void setarUrl_e_ImageIcon(){
        try{
            
            imgURL  = getClass().getResource( "/sistema_imagens/seta_para_baixo.png" );
            icon    = new ImageIcon( imgURL );  
            
            imgURL2 = getClass().getResource( "/sistema_imagens/seta_para_cima.png" );
            icon2   = new ImageIcon( imgURL2 ); 
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "setarUrl_e_ImageIcon(), \n"
                + e.getMessage() + "\n", "Alterar_Seta_Submenu" ); }
    }
    
    private boolean cimabaixo = true; 
    public void tabJanelaSubmenu( JMenu seta_do_menu ){
        try{

            if( cimabaixo == false ){
                cimabaixo = true;
                
                seta_do_menu.setToolTipText( "Ocultar Submenu" );
                seta_do_menu.setIcon( icon2 );
                
                Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) );               
                
            } else if( cimabaixo == true ){
                cimabaixo = false;
                
                seta_do_menu.setToolTipText( "Mostrar Submenu" );
                seta_do_menu.setIcon( icon );

                Sistema_Home.retirarSubmenu();                                                 
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabJanelaSubmenu, \n"
                + e.getMessage() + "\n", "Alterar_Seta_Submenu" ); }
    }
    
}
