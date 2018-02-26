/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema;

import sistema_barra_menu.Sistema_Barra_Menu;
import sistema_submenus.Configuracoes;
import utilidades.JOPM;

/**
 *
 * @author pc
 */
public class Sistema_Home_Inicio {
    
    Sistema_Home Sistema_Home;
    
    public Sistema_Home_Inicio( Sistema_Home Fonema_E_Letra_Home2 ) {
        
        Sistema_Home = Fonema_E_Letra_Home2;
        
        inicio();
    }
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            // Trocar Menu do Home pelo Menu Sistema_Barra_Menu
            Sistema_Barra_Menu Sistema_Barra_Menu = new Sistema_Barra_Menu( Sistema_Home );
            Sistema_Home.adicionarSubmenu(new Configuracoes( Sistema_Home ) );
            
        } catch( Exception e ){e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Sistema_Home_Inicio" ); } } }.start(); 
                
    }
    
}
