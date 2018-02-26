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
public class Fornecedor {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.FORNECEDOR ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"

                    + "NOMEOURAZAOSOCIAL    VARCHAR(300)  ,"
                    + "NOMEFANTASIA         VARCHAR(300)  ,"
                    + "NOMESUPERVISOR       VARCHAR(300)  ,"
                    + "TELEFONESUPERVISOR   VARCHAR(300)  ,"
                    + "NOMEVENDEDOR         VARCHAR(300)  ,"
                    + "TELEFONEVENDEDOR     VARCHAR(300)  ,"
                    + "PRAZODEENTREGA       VARCHAR(300)  ,"
                    + "PRAZODEPAGAMENTO     VARCHAR(300)  ,"
                    
                    + "TIPODEPESSOA         VARCHAR(300)  ,"
                    + "CNPJ                 VARCHAR(300)  ,"
                    
                    + "CD                   VARCHAR(300)  ,"
                    + "FREQUENCIA           VARCHAR(300)  ,"
                    + "LEADTIME             VARCHAR(300)  ,"
                    + "GERACAOPEDIDOSRA     VARCHAR(300)  ,"
                    + "NOMEPROMOTOR         VARCHAR(300)  ,"
                    + "TELEFONEPROMOTOR     VARCHAR(300)  ,"
                    + "EMAILPROMOTOR        VARCHAR(300)  ,"
                    
                    + "QTDMINIMAPALLET      VARCHAR(300)  ,"
                    + "QTDMINIMACAIXA       VARCHAR(300)  ,"
                    + "VALORMINIMO          VARCHAR(300)  ,"
                    + "OBS                  VARCHAR(300)   "
                                        
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
