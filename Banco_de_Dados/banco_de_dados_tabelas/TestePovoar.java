/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados_tabelas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
import utilidades.DB;

/**
 *
 * @author AnaMariana
 */
public class TestePovoar {
    
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
            
            String cadastrar = "INSERT INTO SCHEMAJMARY.TESTE " + "( TESTE1, TESTE2, TESTE3, TESTE4 ) " + "VALUES ( ?, ?, ?, ? )";            
            
            pstm = con.prepareStatement( cadastrar );
            pstm.setString( 1, "TESTE1" ); //System.out.println( "cadastrando TESTE1" );  
            pstm.setString( 2, "TESTE2" ); //System.out.println( "cadastrando TESTE2" );  
            pstm.setString( 3, "TESTE3" ); //System.out.println( "cadastrando TESTE3" );  
            pstm.setString( 4, "TESTE4" ); //System.out.println( "cadastrando TESTE4" );  
            pstm.executeUpdate();
            
                        
            s  = con.createStatement();                                                                                                   
            s.execute( "INSERT INTO SCHEMAJMARY.TESTE (TESTE1, TESTE2, TESTE3, TESTE4) VALUES ( 'TESTE12', 'TESTE22', 'TESTE32', 'TESTE42' )" );
            
            
            retorno = true;

        } catch ( Exception e ) {
            
            retorno = false;
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage().toString() );    
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
