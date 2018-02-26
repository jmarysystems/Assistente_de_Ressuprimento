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
public class GBZRIS {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.GBZRIS ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS      
                    + "ESTABELECIMENTO     VARCHAR(300), "
                    + "MATERIAL            VARCHAR(300), "
       
                    
                    // * ZRIS
                    // MRP            - NO CADASTRO DO  MRP
                    // ESTOQUE_LOJA   - NO CADASTRO MB52 - ESTOQUE LOJA
                    // UND            - NO CADASTRO DOS PRODUTOS
                    + "QTDXEMB                   VARCHAR(50),         "
                    + "ESTOQUE_MINIMO            INTEGER,             "
                    + "VENDA_SEMANA_ANTERIOR     INTEGER,             "
                    + "VENDA_4_SEMANAS           INTEGER,             "
                    + "VENDA_12_SEMANAS          INTEGER,             "
                    + "DDE                       INTEGER,             "
                    + "DISPONIBILIDADE           INTEGER,             "
                    
                    + "VENDA_SEMANA_ATUAL        INTEGER,             "
                    + "PONTOREABASTECIMENTO      INTEGER,             "
                    + "ESTOQUEESPERADO           INTEGER,             "
                    
                    + "PRECOCUSTO                VARCHAR(40),         "
                    + "CLUSTER                   VARCHAR(10)          "
                                                          
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
