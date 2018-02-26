/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login_control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utilidades.DB;
import utilidades.JOPM;

/**
 *
 * @author AnaMariana
 */
public class Login_Control_Listar {
    
    PreparedStatement pstm;
    ResultSet rs;
    
    private String consultar = "SELECT * FROM SCHEMAJMARY.USUARIO WHERE LOGIN LIKE ?" ;
                
    public List<Login_Bean> listar ( String nome ){
        List<Login_Bean> listarJavaBD = new ArrayList();        
        try {
            
            DB DB = new DB();
            Connection con = DB.derby(); //con.close();
            
            pstm = con.prepareStatement(consultar);
            pstm.setString(1, nome);//SELECT * FROM CLIENTES WHERE NOME LIKE claudio
            rs = pstm.executeQuery();
            Login_Bean Bean_LoginNew;       
            while(rs.next()) {
                Bean_LoginNew = new Login_Bean();                
                
                Bean_LoginNew.setLOGIN( rs.getString( "LOGIN") );  
                Bean_LoginNew.setSENHA( rs.getString( "SENHA") );                
                
                Bean_LoginNew.setID( rs.getInt( "ID" ) );
                
                listarJavaBD.add( Bean_LoginNew );                
            }
            
        } catch ( Exception e ) {
            //JOptionPaneMod JOPM = new JOPM( 2, "listar(), \n"
                //+ e.getMessage() + "\n", "Login_Control_Listar" );
        }
        
        return listarJavaBD;
    }
    
}
