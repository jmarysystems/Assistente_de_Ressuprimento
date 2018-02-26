/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados_tabelas;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utilidades.DB;

/**
 *
 * @author AnaMariana
 */
public class EnderecoEspacoGondula {
    
    public static boolean criar() {     
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
            .execute( "CREATE TABLE SCHEMAJMARY.ENDERECOESPACOGONDULA ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    + "ESTABELECIMENTO     VARCHAR(300), "
                    + "MATERIAL            VARCHAR(300), "
                           
                    + "Rua                 VARCHAR(300), "
                    + "Gondula             VARCHAR(300), "
                    + "Prateleira          VARCHAR(300), "
                    + "Altura              VARCHAR(300), "
                    + "Frente              VARCHAR(300), "
                    + "Fundo               VARCHAR(300)  "
                                                          
                    + ")" 
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
        criar();
    }
    /******************Executar Teste*************************************/
    
}
