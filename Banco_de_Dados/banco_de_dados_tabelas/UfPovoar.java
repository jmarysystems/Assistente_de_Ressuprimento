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
public class UfPovoar {
    
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
            
            String cadastrar = "INSERT INTO SCHEMAJMARY.UF ( "
                    //+ "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO              ,"
                    
                    + "ID_USUARIO                 ,"     
                                        
                    + "ID_PAIS                    ,"
                    
                    + "NOME                       ,"
                    + "CODIGO_IBGE                ,"
                    + "SIGLA                       "
                    
                    + ")" 
                    
                    + "VALUES ( "
                    + "?, ?, ?, ?, ?, ? "
                    
                    + ")"
            ;
            
            
            pstm = con.prepareStatement( cadastrar );
            
            ////////////////////////////////////////////////////////////////////
            //DADOS BÁSICOS
            pstm.setDate   ( 1,  new Date(8,8,1987)                             ); 
            pstm.setInt    ( 2,  1                                              ); 
            
            pstm.setInt    ( 3,  1                                              ); 
            
            pstm.setString ( 4,  "CEARA"                                        );             
            pstm.setString ( 5,  "23"                                           ); 
            pstm.setString ( 6,  "CE"                                           ); 
                        
            pstm.executeUpdate();
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////
            pstm.setDate   ( 1,  new Date(8,8,1987)                             ); 
            pstm.setInt    ( 2,  1                                              );            
            pstm.setInt    ( 3,  1                                              ); 
            
            pstm.setString ( 4,  "ACRE"                                         );             
            pstm.setString ( 5,  "12"                                           ); 
            pstm.setString ( 6,  "AC"                                           );                       
            pstm.executeUpdate();
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////
            pstm.setDate   ( 1,  new Date(8,8,1987)                             ); 
            pstm.setInt    ( 2,  1                                              );            
            pstm.setInt    ( 3,  1                                              ); 
            
            pstm.setString ( 4,  "ALAGOAS"                                      );             
            pstm.setString ( 5,  "27"                                           ); 
            pstm.setString ( 6,  "AL"                                           );                       
            pstm.executeUpdate();
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////
            pstm.setDate   ( 1,  new Date(8,8,1987)                             ); 
            pstm.setInt    ( 2,  1                                              );            
            pstm.setInt    ( 3,  1                                              ); 
            
            pstm.setString ( 4,  "AMAZONAS"                                     );             
            pstm.setString ( 5,  "13"                                           ); 
            pstm.setString ( 6,  "AM"                                           );                       
            pstm.executeUpdate();
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////
            pstm.setDate   ( 1,  new Date(8,8,1987)                             ); 
            pstm.setInt    ( 2,  1                                              );            
            pstm.setInt    ( 3,  1                                              ); 
            
            pstm.setString ( 4,  "AMAPÁ"                                        );             
            pstm.setString ( 5,  "16"                                           ); 
            pstm.setString ( 6,  "AP"                                           );                       
            pstm.executeUpdate();
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////
            pstm.setDate   ( 1,  new Date(8,8,1987)                             ); 
            pstm.setInt    ( 2,  1                                              );            
            pstm.setInt    ( 3,  1                                              ); 
            
            pstm.setString ( 4,  "BAHIA"                                        );             
            pstm.setString ( 5,  "29"                                           ); 
            pstm.setString ( 6,  "BH"                                           );                       
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
