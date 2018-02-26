/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package home_usuario_logado;

import usuario_z_control.Bean_Usuario;
import java.util.List;

/**
 *
 * @author ana
 */
public class Home_Setar_Bean_logado { //Home_Bean_logado
    Bean_Usuario Bean_UsuarioR;
    
    public Home_Setar_Bean_logado( List<Bean_Usuario> List_Bean_Logado ){
        
        mostrarLista( List_Bean_Logado );
    }
    
    private void mostrarLista( List<Bean_Usuario> Bean_Usuario ){
        
        for (int i = 0; i < Bean_Usuario.size(); i++){
                
            //System.out.println( "List - " + Bean_Usuario.size() + " - 1 - " + List_Bean_Logado.get(i).getNOME() );

            Bean_Usuario_Logado.PERMITIRACESSO                  = ( Bean_Usuario.get(i).isPERMITIRACESSO()       ); 
            Bean_Usuario_Logado.LOGIN                           = ( Bean_Usuario.get(i).getLOGIN()               ); 
            Bean_Usuario_Logado.SENHA                           = ( Bean_Usuario.get(i).getSENHA()               );                                

            Bean_Usuario_Logado.NOMECOMPLETO                    = ( Bean_Usuario.get(i).getNOMECOMPLETO()       );                                           
                
            Bean_Usuario_Logado.ID                              =( Bean_Usuario.get(i).getID()   );
            
            Bean_UsuarioR = Bean_Usuario.get(i);
                        
        }
    }
   
}
