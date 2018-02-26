/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.awt.Color;

/**
 *
 * @author pc
 */
public class Alterar_Propriedades {
            
    ///////////////////////////////////////////////////////////////////////////
    private javax.swing.JComponent c;
    private boolean    b;
    public Alterar_Propriedades( javax.swing.JComponent c2, boolean b2 ){ c = c2; b = b2;
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            if( b == true ){
                
                c.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
                c.setForeground(new java.awt.Color(0, 0, 204));
        
                c.setBackground(Color.YELLOW);
                c.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true)); 
            }
            else{
                
                c.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
                c.setForeground(new java.awt.Color(0, 0, 0));
        
                c.setBackground(Color.WHITE);        
                c.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));   
            }
                        
        } catch( Exception e ){ } } }.start();                 
    }
    ///////////////////////////////////////////////////////////////////////////
    
}
