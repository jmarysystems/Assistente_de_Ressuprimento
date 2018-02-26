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
public class ANALISELOJASA {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.ANALISELOJASA ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //MB52      
                    + "LOJA           VARCHAR(5),"  
                    + "MATERIAL       VARCHAR(25),"   
                    + "DEP.           VARCHAR(5),"   
                    + "DESCRIÇÃO      VARCHAR(120),"   
                    + "EST_LOJA       VARCHAR(40),"  
                    + "UMB            VARCHAR(7),"  
                    + "VALOR_EST_LOJA VARCHAR(20),"  
                    + "GRP_MERC.      VARCHAR(20),"   
                    
                    //ZVTRA_SORTIMENTO
                    + "MRP_ZVTRA VARCHAR(5),"
                    + "SM_ZVTRA  VARCHAR(5),"
                    + "STATUS_BLOQUEIO VARCHAR(5),"
                    
                    //ZDADOSLOG
                    + "POS_DPST_ZDADOSLOG    VARCHAR(30),"
                    + "PERF_DIST_ZDADOSLOG   VARCHAR(15),"
                    + "GCM_ZDADOSLOG         VARCHAR(10),"
                    + "FONECEDOR_1_ZDADOSLOG VARCHAR(20),"
                    + "FONECEDOR_2_ZDADOSLOG VARCHAR(20),"
                    
//////////////////////////////////////////AS 3 DE CIMA É PARA CRIAR AS TABELAS    
                    
                    //ME80FN 
                    + "PED_ATIVO_15_DIAS_ME80FN   VARCHAR(45) "      
                    
                    //RWBE - APENAS DOS ESTOCADOS
                    + "B184_RWBE       VARCHAR(15),"
                    + "B289_RWBE       VARCHAR(15),"                                                                      
                       
                    //ZRIS
                    //O QUE TEM ESTOQUE POSITIVO VERIFICAR STATUS DO ESTOQUE NA ZRIS
                                                          
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
