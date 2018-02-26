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
public class GBEvento {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.EVENTO ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS
                    + "DATA_INICIO             DATE                NOT NULL,"  
                    + "DATA_FIM                DATE                NOT NULL,"  
                    
                    + "NOME_EVENTO             VARCHAR(300)        NOT NULL,"  
                    + "TIPO_EVENTO             VARCHAR(300)        NOT NULL,"     
                    
                    + "ID_USUARIO             INTEGER NOT NULL,"                    
                    + "CONSTRAINT USUARIO_ID_EVENTO FOREIGN KEY (ID_USUARIO) references SCHEMAJMARY.USUARIO (ID),"
                    
                    + "OBSERVACAO              VARCHAR(300)           "  
                                      
                    + ")" 
            );
            
        } catch ( Exception e ) {
            
            retorno = false;
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage().toString() );    
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
