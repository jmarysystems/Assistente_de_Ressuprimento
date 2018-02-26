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
public class LOJAS_A_MB52 {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.LOJAS_A_MB52 ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //MB52      
                    + "LOJA                     VARCHAR(10)," 
                    + "DEP                      VARCHAR(10),"   
                    + "MATERIAL                 VARCHAR(30),"                     
                    + "DESCRICAO                VARCHAR(140),"   
                    + "UTILIZACAO_LIVRE         VARCHAR(40),"  
                    + "UMB                      VARCHAR(10),"  
                    + "VALOR_UTILIZACAO_LIVRE   VARCHAR(30),"  
                    + "GRP_MERC                 VARCHAR(20),"  
                    
                    + "STATUS_ESTOQUE           VARCHAR(90) " // POSITIVO, NEGATIVO OU ZERO
                                                          
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
