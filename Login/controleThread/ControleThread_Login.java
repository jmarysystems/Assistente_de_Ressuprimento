/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import login.LoginDerby;
import utilidades.DB_Bean;
import utilidades.JOPM;

/**
 *
 * @author Cleilson
 */
public class ControleThread_Login extends Thread {  
    
    LoginDerby LoginDerby;
    
    public ControleThread_Login( LoginDerby LoginDerby2 ){   
        LoginDerby = LoginDerby2;
    } 
            
    public void run(){  
        synchronized ( this ) {
            
            LoginDerby.btConectar.setEnabled( false );
                    
            a();  

            LoginDerby.btConectar.setEnabled( true );
        }  
    }  
    
    private void a(){  
        synchronized(this) {

            // System.getProperty("user.dir") + System.getProperty("file.separator") );
            String pastaDoIp = System.getProperty("user.dir") + System.getProperty("file.separator") + "JMarySystems";
            
            File f = new File( pastaDoIp );
            
            if( f.exists() ){
                
                b();
            }
            else{
                
                try{
                    f.mkdir();
                
                    b();
                }catch( Exception e ){
                    
                    JOPM JOptionPaneMod = new JOPM( 2, "a(), \n"
                            + "Não foi possível criar a pasta do endereço IP" + "\n", "ControleThread_Login" );
                }
            }
        } 
    }
        
    public void b(){  
        synchronized(this) {

            // System.getProperty("user.dir") + System.getProperty("file.separator") );
            String db_conf = System.getProperty("user.dir") + System.getProperty("file.separator") + "JMarySystems"
                    + System.getProperty("file.separator") + "db_conf" + ".properties";
            
            File f = new File( db_conf );
            
            if( f.exists() ){
                
                try{
                    
                    InputStream inputStream =  new FileInputStream( db_conf );
                    BufferedReader br = new BufferedReader( new InputStreamReader( inputStream, "ISO-8859-1" ) ); // "UTF-8", "UTF-16", "ISO-8859-1" ou "US-ASCII"
                    Properties props = new Properties();
                    props.load( br );  
                    
                    DB_Bean.tipo_de_conexao = props.getProperty("tipo_de_conexao");
                    DB_Bean.tipoDeBanco     = props.getProperty("tipoDeBanco");       
                    DB_Bean.driverAlone     = props.getProperty("driver"); 
                    DB_Bean.driverNetwork   = props.getProperty("driver");
                    DB_Bean.nomeDoBanco     = props.getProperty("nomeDoBanco");                       
                    //DB_Bean.usuario         = props.getProperty("usuario");                            
                    //DB_Bean.senha           = props.getProperty("senha");                                   
                    DB_Bean.ip_do_servidor  = props.getProperty("ip_do_servidor");                              
                    DB_Bean.porta_servidor  = props.getProperty("porta_servidor");   
                    DB_Bean.endereco_db     = props.getProperty("endereco_db");
                    
                                       
                                        
                    if( !DB_Bean.tipo_de_conexao.equals("alone") ){
                        
                        DB_Bean.urlAlone = "jdbc:"+DB_Bean.tipoDeBanco+":" + DB_Bean.endereco_db + "/" + DB_Bean.nomeDoBanco+";" + "user="+DB_Bean.usuario+";" + "password="+DB_Bean.senha+"";
                        
                        LoginDerby.verificarLogin( "" );
                    }
                    else{
                        
                        DB_Bean.urlNetwork      = "jdbc:"+DB_Bean.tipoDeBanco+"://" + DB_Bean.ip_do_servidor + ":"+DB_Bean.porta_servidor+"/" + DB_Bean.nomeDoBanco+";" + "user="+DB_Bean.usuario+";" + "password="+DB_Bean.senha+"";
                        LoginDerby.verificarLogin( "" );
                    }
                    
                    
                }catch( Exception e ){
                    
                    JOPM JOptionPaneMod = new JOPM( 2, "b(), \n"
                            + "Não foi possível ler o arquivo do endereço db_conf" + "\n", "ControleThread_Login" );
                }
            }
            else{
                try {
                    
                    LoginDerby.configurarConexao();
                    
                } catch ( Exception e ) {
                    
                    JOPM JOptionPaneMod = new JOPM( 2, "b(), \n"
                            + "Não foi possível criar o arquivo db_conf.properties" + "\n", "ControleThread_Login" );
                }
            }
        }    
    } 
            
}
