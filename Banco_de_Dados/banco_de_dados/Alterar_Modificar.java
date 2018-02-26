/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utilidades.DB;

/**
 *
 * @author AnaMariana
 */
public class Alterar_Modificar {
    
    public static boolean alterar() {     
        Connection con = null;     
        Statement s;
                
        boolean retorno;
        
        DB DB = new DB();
               
        try {

            con = DB.derby();
            
            retorno = true;
            
        } catch ( Exception e ) {
            
            retorno = false;
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage() );    
        } 

        try {                     
            
            s = con.createStatement(); 
            
            s       
            .execute( "ALTER TABLE SCHEMAJMARY.LOJAS_A_MB52 "
                    
                    // ALTER PHOTO_FORMAT SET DATA TYPE VARCHAR(30);
                    + "ALTER MATERIAL SET DATA TYPE VARCHAR(600)"
                                        
                    + "" 
            );
            
            JOptionPane.showMessageDialog( null, "OK! \n" + "EXECUTADO" ); 
        } catch ( Exception e ) {
            
            retorno = false;
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage() );    
        } 
        
        return retorno;
    }
    
    /******************Executar Teste*************************************
     * @param args************************/
    public static void main(String[] args) {            
        alterar();
    }
    /******************Executar Teste*************************************/
    
}
