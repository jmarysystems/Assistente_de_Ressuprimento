/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema_incluir;

import br.com.jmary.home.Home;

/**
 *
 * @author ana
 */
public class Incluir_Materia {
    
    public Incluir_Materia( Home Home2 ) {
        
        Home = Home2;
        
        incluir();
    }
    
    Home Home;
    
    boolean um = false;
    private void incluir(){
        
        new Thread() {   
            @Override
            public void run() {
                while ( um == false ) {     um = true;
                    try { Thread.sleep(1); 
                    
                        //Home.ControleTabs.AddTabsAoHome( "Materias", "/sistema_imagens/livroTp.gif", new MateriaView() );  
                    }catch(Exception e) { }
                }
            }
        }.start(); /* Fim Thread */ 
        um = false;
    }
    
}
