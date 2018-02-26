/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados_tabelas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utilidades.DB;
import utilidades.JOPM;

/**
 *
 * @author AnaMariana
 */
public class CepPovoar {
    
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

                        
        try{
                
            
            PreparedStatement pstm;
            
            String cadastrar = "INSERT INTO SCHEMAJMARY.CEP ( "
                    //+ "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO              ,"                    
                    + "ID_USUARIO                 ,"  
                    
                    + "ID_PAIS                    ,"                                        
                    + "ID_UF                      ,"
                    + "ID_MUNICIPIO               ,"
                    
                    + "CEP                        ,"
                    
                    + "LOGRADOURO                 ,"
                    + "COMPLEMENTO                ,"
                    + "BAIRRO                      "                   
                    
                    + ")" 
                    
                    + "VALUES ( "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ? "
                    
                    + ")"
            ;
            
            
            pstm = con.prepareStatement( cadastrar );
            
            ////////////////////////////////////////////////////////////////////
            //DADOS BÁSICOS
            pstm.setDate   ( 1,  new Date(8,8,1987)                             ); 
            pstm.setInt    ( 2,  1                                              ); 
            
            pstm.setInt    ( 3,  1                                              );             
            pstm.setInt    ( 4,  1                                              ); 
            pstm.setInt    ( 5,  1                                              ); 
            
            pstm.setString ( 6,  "60871580"                                     );
            
            pstm.setString ( 7,  "AVENIDA PALESTINA"                            ); 
            pstm.setString ( 8,  "REGIONAL VI"                                  ); 
            pstm.setString ( 9,  "MESSEJANA"                                    ); 
                        
            pstm.executeUpdate();
            ////////////////////////////////////////////////////////////////////
            
            
            retorno = true;

        } catch ( Exception e ) {
            
            retorno = false;
            JOPM JOptionPaneMod = new JOPM( 2, "criar(), \n"
                + e.getMessage() + "\n", "UfPovoar" );
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
