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
public class GBSUPLY {
    
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
            .execute( "CREATE TABLE SCHEMAJMARY.GBSUPLY ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS      
                    // PRODUTOS ZTCONSULTAMERCA
                    + "ESTABELECIMENTO     VARCHAR(300), "
                    + "MATERIAL            VARCHAR(300), "
                           
                    // PRODUTOS SUPLY CHAIN
                    + "Nom_Area                         VARCHAR(300), "
                    + "Nom_Setor                        VARCHAR(300), "
                    + "Nom_Secao                        VARCHAR(300), "
                    + "Nom_Grupo                        VARCHAR(300), "
                    + "Nom_SubGrupo                     VARCHAR(300), "
                    + "Nom_FornecedorGrupoEconomico     VARCHAR(300), "
                    + "Nom_Classe2                      VARCHAR(300), "                  
                    + "Cod_FornecedorLOF                VARCHAR(300), "
                    + "Sts_FonecedorTop                 VARCHAR(300), "         
                    + "Sts_EstoqueTipo                  VARCHAR(300)  "
                                                          
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
