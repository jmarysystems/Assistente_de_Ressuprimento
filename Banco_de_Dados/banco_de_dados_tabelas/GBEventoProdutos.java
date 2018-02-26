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
public class GBEventoProdutos {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.EVENTOPRODUTOS ( "
                    + "ID            INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS                    
                    + "ID_EVENTO             INTEGER NOT NULL,"                    
                    + "CONSTRAINT EVENTO_ID_EVENTOPRODUTOS FOREIGN KEY (ID_EVENTO) references SCHEMAJMARY.EVENTO (ID),"
                    
                    + "SETOR                      VARCHAR(100)     ,"
                    + "ITEM                       VARCHAR(100)     ,"
                    + "LOJA                       VARCHAR(100)     ,"
                    
                    + "QTD_EMB                    VARCHAR(100)     ,"
                    + "TIPO_EMB                   VARCHAR(100)     ,"
                    + "TIPO_MRP                   VARCHAR(100)     ,"
                    
                    + "VIA_DE_ABASTECIMENTO       VARCHAR(100)     ,"
                    + "REGIONAL                   VARCHAR(100)     ,"
                    + "CD                         VARCHAR(100)     ,"
                    
                    + "DESCRICAO_DO_MATERIAL      VARCHAR(100)     ,"
                    + "ESTOQUE_LOJA_CX            VARCHAR(100)     ,"
                    + "VENDA_MEDIA_12_SEMANAS_CX  VARCHAR(100)     ,"
                    + "DDE_LOJA                   VARCHAR(100)     ,"
                    
                    + "ADICIONAL_EM_CAIXAS        VARCHAR(100)     ,"
                    
                    + "QTD_EM_UNIDADES      VARCHAR(100)     "

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
