/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login;

import banco_de_dados.ControleThread_db;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import utilidades.Arquivo_Ou_Pasta;
import utilidades.DB_Bean;
import utilidades.JOPM;

/**
 *
 * @author Cleilson
 */
public class LoginDerby_Inicio extends Thread {  
    
    LoginDerby LoginDerby;
    String     endereco_db;
    
    public LoginDerby_Inicio( LoginDerby LoginDerby2, String endereco_db2 ){   
        endereco_db = endereco_db2;
        LoginDerby  = LoginDerby2;
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
                            + "Não foi possível criar a pasta do endereço IP" + "\n", "LoginDerby_Inicio" );
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
                    
                }catch( Exception e ){
                    
                    JOPM JOptionPaneMod = new JOPM( 2, "b(), \n"
                            + "Não foi possível ler o arquivo do endereço db_conf" + "\n", "LoginDerby_Inicio" );
                }
                
                ControleThread_db t1 = new ControleThread_db( System.getProperty("user.dir") + System.getProperty("file.separator") + "JMarySystems" );        
                t1.setName("ControleThread_db");         
                t1.start(); 
            }
            else{
                try {
                    
                    criarArquivo();
                    
                } catch ( Exception e ) {
                    
                    JOPM JOptionPaneMod = new JOPM( 2, "b(), \n"
                            + "Não foi possível criar o arquivo db_conf.properties" + "\n", "ControleThread_Login" );
                }
            }
        }    
    } 
    
    private void criarArquivo(){
        
        Arquivo_Ou_Pasta.criarPasta( System.getProperty("user.dir"), "JMarySystems" );
                
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );                   
            
            Arquivo_Ou_Pasta.criar_Arquivo_Iso( System.getProperty("user.dir") + System.getProperty("file.separator") + "JMarySystems" + System.getProperty("file.separator") + "db_conf" + ".properties", 
                    "tipo_de_conexao" + " = " + "alone" + "\r\n" +
                    "tipoDeBanco"     + " = " + "derby"     + "\r\n" +
                    "driver"          + " = " + "org.apache.derby.jdbc.EmbeddedDriver"          + "\r\n" +        
                    "nomeDoBanco"     + " = " + "db_fonema_e_letra"     + "\r\n" +                
                    //"usuario"         + " = " + usuario         + "\r\n" +                      
                    //"senha"           + " = " + senha           + "\r\n" +                                 
                    "ip_do_servidor"  + " = " + "127.0.0.1"  + "\r\n" +                           
                    "porta_servidor"  + " = " + "1527"  + "\r\n"  + 
                    "endereco_db"     + " = " + System.getProperty("user.dir") + System.getProperty("file.separator") + "JMarySystems"
            );
            
            ControleThread_db t1 = new ControleThread_db( System.getProperty("user.dir") + System.getProperty("file.separator") + "JMarySystems" );        
            t1.setName("ControleThread_db");         
            t1.start(); 
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "criarArquivo(), \n"
                + e.getMessage() + "\n", "LoginDerby_Inicio" ); } } }.start();
    }
            
}
