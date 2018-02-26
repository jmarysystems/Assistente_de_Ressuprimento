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
public class Pais {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.PAIS ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                         
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO              DATE                  ,"
                    
                    + "ID_USUARIO                 INTEGER NOT NULL,"                    
                    + "CONSTRAINT USUARIO_ID_PAIS FOREIGN KEY (ID_USUARIO) references SCHEMAJMARY.USUARIO (ID),"
                    
                    + "IMAGEM_LOGOTIPO            VARCHAR(300)          ,"
                    
                    + "CODIGO                     INTEGER NOT NULL      ,"
                    
                    + "NOME_PTBR                  VARCHAR(300) NOT NULL ,"
                    + "NOME_EN                    VARCHAR(300)          ,"
                    + "SIGLA2                     VARCHAR(300)          ,"
                    + "SIGLA3                     VARCHAR(300)           "
                                                                                
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
