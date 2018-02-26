/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login;

import br.com.jmary.home.HomeJApplet;
import usuario_z_control.Control_Usuario_Listar_PorID;
import home_usuario_logado.Home_Setar_Bean_logado;
import java.util.List;
import usuario_z_control.Bean_Usuario;

/**
 *
 * @author CARMAL
 */
public class Home_ControleThread extends Thread {  
    
    public Home_ControleThread( int ID2 ){   
        ID = ID2;
    } 
    
    int ID = 0;
    
    public void run(){  
        synchronized ( this ) {
            
            a( ID ); b(); 

        }  
    }  
    
    private void a( int ID ){  
        synchronized(this) {
            Control_Usuario_Listar_PorID Control = new Control_Usuario_Listar_PorID();
            List<Bean_Usuario> List_Bean_Login = Control.listar( ID );                
            Home_Setar_Bean_logado mostrarLista = new Home_Setar_Bean_logado( List_Bean_Login );
        } 
    }
        
    public void b(){  
        synchronized ( this ) {
            HomeJApplet HomeJApplet = new HomeJApplet();
            HomeJApplet.iniciarJFrame();
        }
 
    } 

    
    /******************Executar Teste*************************************
     * @param args************************
    public static void main(String[] args) {            
          
        ControleThread t1 = new ControleThread(); 
        
        t1.setName("Thread1");   
        
        t1.start();  
    }
    /******************Executar Teste*************************************/
    
}
