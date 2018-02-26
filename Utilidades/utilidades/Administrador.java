/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import home_usuario_logado.Bean_Usuario_Logado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Query;
import jpa.JPAUtil;

/**
 *
 * @author AnaMariana
 */
public class Administrador {
    
    public static HashMap<String,Boolean> mapaComandos;
    
    public static void setarAcessoUsuario() {           
    
        try { 
            
            ////////////////////////////////////////////////////////////////////
            List<acesso.Acesso> listAcessoTotal = new ArrayList<>();
            
            Query qf = JPAUtil.em().createNativeQuery("SELECT SCHEMAJMARY.ACESSO.* FROM "
                    + "SCHEMAJMARY.ACESSO, SCHEMAJMARY.CONTROLEACESSO WHERE "
                    + "SCHEMAJMARY.ACESSO.ID = SCHEMAJMARY.CONTROLEACESSO.ID_ACESSO AND "
                    + "SCHEMAJMARY.CONTROLEACESSO.ID_USUARIO=?", acesso.Acesso.class );
            
            qf.setParameter(1, Bean_Usuario_Logado.ID );
            listAcessoTotal = qf.getResultList();
            ////////////////////////////////////////////////////////////////////
            
            System.out.println( "////////////////////////////////////////////////////////////////////" );
            System.out.println( "Acessos deste Usuário: " + listAcessoTotal.size() );
            ////////////////////////////////////////////////////////////////////
            mapaComandos = new HashMap<String, Boolean>();
            for (int it = 0; it < listAcessoTotal.size(); it++){
                
                mapaComandos.put( listAcessoTotal.get(it).getComandodoacesso(), true );
                System.out.println( listAcessoTotal.get(it).getComandodoacesso() );
            }
            System.out.println( "////////////////////////////////////////////////////////////////////" );
            ////////////////////////////////////////////////////////////////////
                
        }catch( Exception e ){}
    }     
    
////////////////////////////////////////////////////////////////////////////////    
    public static boolean acesso( String cmd ) { boolean retorno = false;            
    
        try { 
                
            ////////////////////////////////////////////////////////////////////
            Query qx = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.USUARIO WHERE ID = ?", usuario_0.Usuario.class );
            qx.setParameter(1, Bean_Usuario_Logado.ID );
            
            List<usuario_0.Usuario> listUser = qx.getResultList();
            ////////////////////////////////////////////////////////////////////   
                                                
            ////////////////////////////////////////////////////////////////////           
            List<controle_acesso.Controleacesso> listTotal = new ArrayList<controle_acesso.Controleacesso>();
            
            for (int i = 0; i < listUser.size(); i++){

                Query qy = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.CONTROLEACESSO WHERE ID_USUARIO = ?", controle_acesso.Controleacesso.class );
                qy.setParameter(1, listUser.get(i).getId() );
                
                List<controle_acesso.Controleacesso> listParcial = qy.getResultList();
                
                for (int x = 0; x < listParcial.size(); x++){
                    listTotal.add( (controle_acesso.Controleacesso) qy.getResultList().get(x) );
                }
            }            
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////
            List<acesso.Acesso> listAcessoTotal = new ArrayList<acesso.Acesso>();
            
            for (int ic = 0; ic < listTotal.size(); ic++){
                
                Query qf = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.ACESSO WHERE ID = ?", acesso.Acesso.class );
                qf.setParameter(1, listTotal.get(ic).getIdAcesso() );
                
                List<acesso.Acesso> listAcessoParcial = qf.getResultList();
                
                for (int x = 0; x < listAcessoParcial.size(); x++){
                    listAcessoTotal.add( (acesso.Acesso) qf.getResultList().get(x) );
                }
            }
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////
            for (int it = 0; it < listAcessoTotal.size(); it++){
                
                if( listAcessoTotal.get(it).getComandodoacesso().equals(cmd) ){
                    
                    retorno = true;
                    break;
                }
            }
            ////////////////////////////////////////////////////////////////////
                
        }catch( Exception e ){}
            
        return retorno;
    } 
    
    public static void main(String[] args) { 
   
    }
}
