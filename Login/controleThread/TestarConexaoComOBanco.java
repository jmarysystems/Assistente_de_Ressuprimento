/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleThread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author AnaMariana
 */
public class TestarConexaoComOBanco {
    
    public static boolean criar( String ip_do_servidor ) {     
        Connection con = null;     
        Statement s;
                
        boolean retorno;
        
        String nomeDoBanco = "db";
            String url = "jdbc:derby://" + ip_do_servidor + ":1527/";
            String driver = "org.apache.derby.jdbc.ClientDriver";
            Properties prop = new Properties();
                   prop.put( "user", "cleilson" );
                   prop.put( "password", "23071354" );  
                   
                   /**prop.put( "bootPassword", "a@Simple_More_Than_16_Char_Password" );/**/
               
        try {
            
            Class.forName( driver );        
            con = DriverManager.getConnection( url + nomeDoBanco, prop );
            
            retorno = true;
            
        } catch ( Exception e ) {
            
            retorno = false;
            //JOptionPane.showMessageDialog( null, "Erro1! \n" + e.getMessage().toString() );    
        } 

        try {                     
            
            ResultSet rs;
            s  = con.createStatement();
            rs = s.executeQuery("SELECT * FROM SCHEMAJMARY.TESTE");
            while (rs.next()){
                System.out.println( rs.getInt(1) + " - " + rs.getString(2) );
                break;                
            }
            
        } catch ( Exception e ) {
            
            retorno = false;
            //JOptionPane.showMessageDialog( null, "Erro2! \n" + e.getMessage().toString() );    
        } 
        
        return retorno;
    }
    
    /******************Executar Teste*************************************
     * @param args************************/
    public static void main(String[] args) {            
        criar( "localhost" );
    }
    /******************Executar Teste*************************************/
    
}
