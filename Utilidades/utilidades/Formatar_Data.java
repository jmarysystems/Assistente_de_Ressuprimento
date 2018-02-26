/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author pc
 */
public class Formatar_Data {
            
    ///////////////////////////////////////////////////////////////////////////
    private JFormattedTextField ftm;
    private boolean             b;
    private String              txtftm;
    public Formatar_Data( JFormattedTextField ftm2, String x2, boolean b2 ){ ftm = ftm2; b = b2; txtftm = x2;
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            if( b == true ){ // setarFocusGainedData
                
                if( txtftm.trim().equals("") || txtftm.trim().equals("   /    /") ){   
            
                    try {
                
                        MaskFormatter mascaraTelefone = new MaskFormatter("##/##/####");
                        mascaraTelefone.setValidCharacters("0123456789");
                
                        ftm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascaraTelefone ) );                
                    } catch ( Exception e ) {
                        JOPM JOptionPaneMod = new JOPM( 2, "Erro ao setar Máscara, "
                            +"\n" + e.toString()
                                    , "Erro ao setar Máscara" );
                            ftm.requestFocus();
                    }
                    ftm.setText( "" );            
                }
            }
            else{  if( txtftm.length() < 10 ){ ftm.setText( "" ); } }
                        
        } catch( Exception e ){ } } }.start();                 
    }
    ///////////////////////////////////////////////////////////////////////////
    
}
