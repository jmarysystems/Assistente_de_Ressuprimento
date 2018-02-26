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
public class Controle_Acesso {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.CONTROLEACESSO ( "
                    + "ID            INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO          DATE            ,"
                    
                    + "ID_USUARIO             INTEGER NOT NULL,"                    
                    + "CONSTRAINT USUARIO_ID_CONTROLEACESSO FOREIGN KEY (ID_USUARIO) references SCHEMAJMARY.USUARIO (ID),"
                    
                    + "ID_ACESSO             INTEGER NOT NULL,"                    
                    + "CONSTRAINT ACESSO_ID_CONTROLEACESSO FOREIGN KEY (ID_ACESSO) references SCHEMAJMARY.ACESSO (ID),   "
                    
                    + "ID_ESTABELECIMENTO             INTEGER NOT NULL,"                    
                    + "CONSTRAINT ESTABELECIMENTO_ID_CONTROLEACESSO FOREIGN KEY (ID_ESTABELECIMENTO) references SCHEMAJMARY.ESTABELECIMENTO (ID) "

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
