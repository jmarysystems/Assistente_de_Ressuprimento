/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author AnaMariana
 */
public class ObterNumeroAleatorio {
    
    public static String gerar(){
        String retorno ="";
        
        int um = 0;
        int dois = 0;
        int tres = 0;
        int quatro = 0;
        
        for( int i = 0; i < 9; i++ ) { 
            
            int ii = ( int ) ( 1 + ( Math.random() * 9 ) );
            switch( i ){
                case 1: um = ii; break;
                case 2: dois = ii; break;
                case 3: tres = ii; break;
                case 4: quatro = ii; 
                                                     
                    retorno = String.valueOf( um + "" + dois + "" + tres + "" + quatro );
                break;
             }
        }        
        return retorno.trim();
    }
    
    public static void main( String [] args ){
        String str = "";
        str = gerar();
        System.out.println( str );
    }
    
}
