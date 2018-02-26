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
public class Excluir_Tabela {
    
    public static boolean excluir() {     
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
            .execute( "DROP TABLE SCHEMAJMARY.LOJAS_A_ZDADOSLOG " );
            
            JOptionPane.showMessageDialog( null, "TABLE SCHEMAJMARY.LOJAS_A_ZDADOSLOG \n" + "EXCLUIDA" );  
            
        } catch ( Exception e ) {
            
            retorno = false;
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage() );    
        } 
        
        return retorno;
    }
    
    /******************Executar Teste*************************************
     * @param args************************/
    public static void main(String[] args) {            
        excluir();
    }
    /******************Executar Teste*************************************/
    
}
