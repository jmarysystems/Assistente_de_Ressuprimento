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
public class Cep {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.CEP ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                         
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO                     DATE                  ,"
                    
                    + "ID_USUARIO                        INTEGER NOT NULL,"                    
                    + "CONSTRAINT USUARIO_ID_CEP       FOREIGN KEY (ID_USUARIO) references SCHEMAJMARY.USUARIO (ID),"

                    + "ID_PAIS                         INTEGER NOT NULL,"                    
                    + "CONSTRAINT PAIS_ID_CEP    FOREIGN KEY (ID_PAIS) references SCHEMAJMARY.PAIS (ID),"
                    
                    + "ID_UF                           INTEGER NOT NULL,"                    
                    + "CONSTRAINT UF_ID_CEP      FOREIGN KEY (ID_UF) references SCHEMAJMARY.UF (ID),"
                    
                    + "ID_MUNICIPIO                    INTEGER NOT NULL,"                    
                    + "CONSTRAINT MUNICIPIO_ID_CEP  FOREIGN KEY (ID_MUNICIPIO) references SCHEMAJMARY.MUNICIPIO (ID),"

                    + "CEP                       VARCHAR(300) NOT NULL ,"
                    
                    + "LOGRADOURO                VARCHAR(300) NOT NULL ,"
                    + "COMPLEMENTO               VARCHAR(300)          ,"
                    + "BAIRRO                    VARCHAR(300) NOT NULL  "                                    
                                                                                
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
