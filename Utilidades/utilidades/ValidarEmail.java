/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ana
 */
public class ValidarEmail {

    /** Realiza a validação do CPF. 
     * 
     * @param str
     * @return  
     */
    
    public static boolean  validar( String str ) {
        
        boolean retorno = false;  
        
        if ( !str.equals("") && str.length() > 0 ) {  
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);  
            Matcher matcher = pattern.matcher(str);  
            if (matcher.matches()) {  
                retorno = true ;  // true  
            } 
        } 
        
        return retorno;  
        
    }
    
    public static void emailInvalido() {
       JOPM JOptionPaneMod = new JOPM( 2, "EMAIL, "
                        + "\nO Campo EMAIL não se está se referindo a um EMAIL válido"
                        + "\nInforme um EMAIL válido"
                        + "\n", "EMAIL" );
   }
    
// Use este trecho para testar a classe 
    public static void main(String[] args) {
        if( validar( "xxxgmail.com" ) == true ){
            
            System.out.println( "true" );
        }
        else{
            System.out.println( "false" );
        }
    }
}
