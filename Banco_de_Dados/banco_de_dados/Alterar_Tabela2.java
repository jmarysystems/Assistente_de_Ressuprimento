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
public class Alterar_Tabela2 {
    
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
            .execute( "ALTER TABLE SCHEMAJMARY.GBPEDIDOSATIVOS "
                    
                    + "ADD COLUMN NUMEPEDIDO VARCHAR(50),"
                    + "ADD COLUMN ES         VARCHAR(10),"
                    + "ADD COLUMN CODFORNEC  VARCHAR(15)"
                                        
                    + "" 
            );
            
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
