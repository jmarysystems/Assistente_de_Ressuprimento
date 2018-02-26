/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import utilidades.Arquivo_Ou_Pasta;

/**
 *
 * @author AnaMariana
 */
public class CriarBancoDeDados {
    
    public static boolean criar( String endereco_db ) {     
        Connection con = null;        
        boolean retorno;
        
        Arquivo_Ou_Pasta.criarPasta( System.getProperty("user.home"), "JMarySystems" );
        //String endereco_db = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems";       
        
        String nomeDoBanco = "db_fonema_e_letra";
        String url = "jdbc:derby:" + endereco_db + "/";
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";        
        Properties prop = new Properties();
                   prop.put( "create", "true" ); 
                   prop.put( "user", "cleilson" );
                   prop.put( "password", "23071354" ); 
                   
                   /**prop.put( "dataEncryption", "true" ); 
                   prop.put( "bootPassword", "a@Simple_More_Than_16_Char_Password" ); 
                   prop.put( "encryptionAlgorithm", "Blowfish/CBC/NoPadding" );/**/ 
                                                                         
        try {
            
            Class.forName( driver );
            con = DriverManager.getConnection( url + nomeDoBanco, prop );
            
            retorno = true;
   
        } catch ( Exception e ) {
            
            retorno = false;
            //JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage().toString() );    
        } 
                    
            //DatabaseMetaData dbmd = con.getMetaData();  
            //ResultSet rs = dbmd.getSchemas(); // Retorna o Schema do banco  
            //System.out.println( rs );
        
        return retorno;
    }
    
    /******************Executar Teste*************************************
     * @param args************************/
    public static void main(String[] args) {            
        criar( System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems" );
    }
    /******************Executar Teste*************************************/
    
}
