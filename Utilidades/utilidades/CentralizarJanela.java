/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import javax.swing.JFrame;

/**
 *
 * @author Padrão
 */
public class CentralizarJanela {
    
    public CentralizarJanela( JFrame JF2 ){
        JF = JF2;
        inicio();
    }
    
    JFrame JF;
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                    
            centralizeFrame( JF );
       
        } catch( Exception e ){} } }.start();

    }
    
    private void centralizeFrame( JFrame JF ) {  
        int x,y;  
        java.awt.Rectangle scr  = JF.getGraphicsConfiguration().getBounds();  
        java.awt.Rectangle form = JF.getBounds();  
        x = (int) ( scr.getWidth() - form.getWidth() ) / 2;  
        y = (int) ( scr.getHeight()- form.getHeight()) / 2;  
        JF.setLocation( x , y );  
    }
    
}


