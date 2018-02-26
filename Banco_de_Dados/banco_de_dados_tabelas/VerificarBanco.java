package banco_de_dados_tabelas;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import utilidades.DB;

/**
 *
 * @author AnaMariana
 */
public class VerificarBanco {
    
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
        if ( criar() == true ) {
            
            System.out.println( "Banco de dados existe     \n"  + System.getProperty("user.home") );
        }
        else{
            System.out.println( "Banco de dados não existe \n"  + System.getProperty("user.home") );
        }
    }
    /******************Executar Teste*************************************/
    
}
