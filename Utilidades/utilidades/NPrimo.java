/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

/**
 *
 * @author pc
 */
public class NPrimo {
    
    public static boolean nPrimo(int n){
        boolean retorno = false;

        int cont=0;
        for (int i=1; i<=n; i++ ){ //System.out.println( "N: "+n +" I: " + i  );
            if( n%i == 0 ) { cont=cont+1; }
        }
        if (cont==2){ retorno = true; //System.out.println( n );
        }else{ retorno = false; /*System.out.println("o numero" +n+ " não é primo");*/ }   
        
        return retorno;        
    }
    
    public static void main( String args[] ){
        if( nPrimo(11) == true )
        System.out.println( 11 );
    }
    
}
