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
public class GBDiretoLoja {
    
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
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage().toString() );    
        } 

        try {                     
            
            s = con.createStatement(); 
            
            s       
            .execute( "CREATE TABLE SCHEMAJMARY.GBDIRETOLOJA ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS      
                    // PRODUTOS ZTCONSULTAMERCA
                    + "FORNECEDOR          VARCHAR(300), "
                    + "MATERIAL            VARCHAR(300), "
       
                    
                    //MB52 - ESTOQUE LOJA
                    + "INPUTZRIS           INTEGER,  "
                    + "PEDIDOGERADO        INTEGER   "
                                                          
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
