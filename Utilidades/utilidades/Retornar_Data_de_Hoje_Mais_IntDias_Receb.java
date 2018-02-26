/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author ana
 */
public class Retornar_Data_de_Hoje_Mais_IntDias_Receb {

    public static Date get( int dias ) {
        Date retorno;
        
        retorno = somarDatas( dias );
        
        return retorno;
    }
    
    private static Date somarDatas( int dias ) {  
        Date retorno = null;
 
        try {  
            
            GregorianCalendar gc = new GregorianCalendar();
            Date dataHoje = gc.getTime();

            SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
            
            dataHoje.setDate( dataHoje.getDate() + dias ); 
            
            String result = out.format( dataHoje );            
            //System.out.println( result ); 
            
            retorno = dataHoje;
  
        }catch(Exception e) {
            /*JOptionPaneMod JOptionPaneMod = new JOptionPaneMod( 2, "Retornar_Data_de_Hoje_Mais_IntDias_Receb, "
                    + "\n somarDatas( int dias )"
                    + e.getMessage()
                    + "\n", "somarDatas( int dias )" );*/
        }
        
        return retorno;
   }
 
}
