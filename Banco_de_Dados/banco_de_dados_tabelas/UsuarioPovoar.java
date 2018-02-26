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
public class UsuarioPovoar {
    
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
            
            String cadastrar = "INSERT INTO SCHEMAJMARY.USUARIO ( "
                    //+ "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    + "DATA_CADASTRO               ,"
                    + "ID_USUARIO                  ,"                  
                    + "IMAGEM_LOGOTIPO             ," 
                    
                    + "PERMITIRACESSO              ,"
                    + "LOGIN                       ,"
                    + "SENHA                       ,"
                    
                    + "NOMECOMPLETO                 "
                    
                    + ")" 
                    
                    + "VALUES ( "
                    + "?, ?, ?, ?, ?, ?, ? "
                    
                    + ")"
            ;
            
            
            pstm = con.prepareStatement( cadastrar );
            
            pstm.setDate   ( 1, new Date(8,8,1987)                    ); 
            pstm.setInt    ( 2, 1                              ); 
            pstm.setString ( 3, "jmlogo.png"                   ); 
            
            pstm.setBoolean( 4,  true                          ); 
            pstm.setString ( 5,  "cleilson"                    ); 
            pstm.setString ( 6, "23071354"                     ); 
            
            pstm.setString ( 7, "CLEILSON HENRIQUE DE ARAUJO"  );    
                        
            pstm.executeUpdate();
            
            retorno = true;

        } catch ( Exception e ) {
            
            retorno = false;
            JOPM JOptionPaneMod = new JOPM( 2, "criar(), \n"
                + e.getMessage() + "\n", "UsuarioPovoar" );
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
