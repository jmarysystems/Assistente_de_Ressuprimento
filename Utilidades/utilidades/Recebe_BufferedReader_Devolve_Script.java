/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author pc
 */
public class Recebe_BufferedReader_Devolve_Script {
    
    public static String iniciar( BufferedReader codigoFonte ) {
        String linha;                                              //String letra = null;   
        StringBuilder recebe = new StringBuilder();         
        try {             
            while ( ( linha = codigoFonte.readLine() ) != null ) { //while ( ( ( linha = codigoFonte.read()) != -1 ) ){
                recebe.append( linha );                            //recebe.append( line + "\n" );     
            }  
        } catch (IOException e) { e.printStackTrace(); }       
        return recebe.toString();  
    } 
    
    public static void main( String[] args ) {
        try{
            // Enviando String
            /*String str = "This is a String ~ GoGoGo";
            InputStream inputStream = new ByteArrayInputStream( str.getBytes() );
            BufferedReader br = new BufferedReader( new InputStreamReader( inputStream, "ISO-8859-1" ) ); // "UTF-8", "UTF-16", "ISO-8859-1" ou "US-ASCII"
            System.out.println( iniciar( br ) );   
            */
            
            // Enviando Arquivo dentro do JAR 
            /*InputStream inputStream =  Recebe_BufferedReader_Devolve_Script.class.getResourceAsStream( "teste"+".html" );
            BufferedReader br = new BufferedReader( new InputStreamReader( inputStream, "ISO-8859-1" ) ); // "UTF-8", "UTF-16", "ISO-8859-1" ou "US-ASCII"
            System.out.println( iniciar( br ) ); 
            */
            
            // Enviando Arquivo de fora do JAR 
            String file = System.getProperty( "user.home" ) + System.getProperty( "file.separator" ) + "t" + ".txt";
            File ff = new File( file );
            if( ff.exists() ) {
                InputStream inputStream = new FileInputStream( file );
                BufferedReader br = new BufferedReader( new InputStreamReader( inputStream, "ISO-8859-1" ) ); // "UTF-8", "UTF-16", "ISO-8859-1" ou "US-ASCII"
                System.out.println( iniciar( br ) );            
            }
                   
        }catch( Exception e ){ e.printStackTrace(); }
    }
    
}
