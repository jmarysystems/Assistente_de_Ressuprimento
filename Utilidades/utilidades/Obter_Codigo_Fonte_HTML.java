/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author AnaMariana
 */
public class Obter_Codigo_Fonte_HTML {
    
    public static String codigoFonte( String url ) { 
        String retorno = "";            
            try { 
                URL banco = new URL( url );
                HttpsURLConnection conexão = (HttpsURLConnection) banco.openConnection();
                
                try { 
                    BufferedReader obterCódigoFonte = new BufferedReader( new InputStreamReader( conexão.getInputStream() ) ); 
                    StringBuilder recebe = new StringBuilder(); 
                    int letra;
                    while ( ( (letra = obterCódigoFonte.read()) != -1) ){
                    recebe.append( (char) letra ); 
                }            
                retorno = recebe.toString();
                }catch( Exception e ){ }
            }catch( Exception e ){}
            
            return retorno;
    }     
    
    public static void main(String[] args) { 
        String url = "https://www.ib2.bradesco.com.br/ibpflogin/identificacao.jsf?AGN="+
                    "2572"+
                    "&CTA="+
                    "00019769"+
                    "&TPCONTA="+""+"&DIGCTA="+
                    "6"+
                    "&ORIGEM="+"";
        String codigoFonte = codigoFonte( url ); 
        System.out.println( codigoFonte );        
    }
}
