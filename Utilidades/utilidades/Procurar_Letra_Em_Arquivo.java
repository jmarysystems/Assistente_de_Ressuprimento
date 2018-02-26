package utilidades;


import java.io.File;
import java.io.InputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc
 */
public class Procurar_Letra_Em_Arquivo {
    
    public void x( File arquivo ) { 
         try {
             InputStream inputStream = getClass().getResourceAsStream( arquivo.getPath() );

             String r = "ggg";//Recebe_InputStream_Devolve_String.para_String( inputStream );
             
             for ( int i=0; i< r.length(); i++ ) {  
                 char c = r.charAt(i);  
                 //Character.isLetter( c ); 
                 //Character.isDigit( c ) );
                 //if( i > 17727 && i < 18591 ){ System.out.print( c ); }
                 c( c, i ); 
             }  
             
         } catch ( Exception e){}
         
     }
    
    char y = '2';
    int  p = 0;
    StringBuilder recebe = new StringBuilder();
    StringBuilder posicao = new StringBuilder();
    public void c( char c, int i ) {        
        if( y == c ){            
            recebe.append( c );     
            posicao.append( i + " - " );
            p++;
            if( p > 3 ){
                System.out.println( recebe );
                System.out.println( posicao );
            }
            switch( p ) {
                case 1: y = '8'; break;
                case 2: y = '2';  break;
                case 3: y = '1'; break;
            }
        }else{
            y = '2';
            p = 0;
            recebe = new StringBuilder();
            posicao = new StringBuilder();
        }        
    }
    
    /***************************************************************************/
    
    public static void main(String[] args){
        new Procurar_Letra_Em_Arquivo().x( new File( "p.png" ) );
    }
    
}
