/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.util.regex.Pattern;

/**
 *
 * @author ana
 */
public class ValidarCpf {

    /** Realiza a validação do CPF. 
     * 
     * @param cpf
     * @return  true se o CPF é válido e false se não é válido 
     */
    public static boolean  CPF( String cpf ) {
        cpf = cpf.replaceAll(Pattern.compile("\\s").toString(), "");
        cpf = cpf.replaceAll(Pattern.compile("\\D").toString(), "");
        
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || 
            cpf.equals("22222222222") || cpf.equals("33333333333") || 
            cpf.equals("44444444444") || cpf.equals("55555555555") || 
            cpf.equals("66666666666") || cpf.equals("77777777777") || 
            cpf.equals("88888888888") || cpf.equals("99999999999") || 
            (cpf.length() != 11)) return(false);
    
        if ( cpf.equals("") ) {
            return false;
        } 
        
        int d1         = 0;
        int d2         = 0;
        int digito1    = 0;
        int digito2    = 0;
        int resto      = 0;
        int digitoCPF     ;

        String nDigResult;

        for (int i = 1; i < cpf.length() - 1; i++) {
            digitoCPF = Integer.valueOf(cpf.substring(i - 1, i)).intValue();

            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.  
            d1 = d1 + (11 - i) * digitoCPF;

            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.  
            d2 = d2 + (12 - i) * digitoCPF;
        }

        //Primeiro resto da divisão por 11.  
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.  
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        //Digito verificador do CPF que está sendo validado.  
        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        //Concatenando o primeiro resto com o segundo.  
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.  
        return nDigVerific.equals(nDigResult);
    }
// Use este trecho para testar a classe 
//    }
    public static void main(String[] args) {
        if( CPF("111.111.111-11") == true ){
            
            System.out.println( "true" );
        }
        else{
            System.out.println( "false" );
        }
    }
}
