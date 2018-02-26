/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados_tabelas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import utilidades.DB;
import utilidades.JOPM;

/**
 *
 * @author AnaMariana
 */
public class AcessoPovoar {
    
    public static boolean criar() {     
        Connection con = null;     
        Statement s;
                
        boolean retorno;
        
        DB DB = new DB();
               
        try {

            con = DB.derby();
            
            retorno = true;
            
        } catch ( Exception e ) {
            
            retorno = false;
            JOPM JOPM = new JOPM( "EXCEÇÃO: " + "No UsuarioPovoar\n"
                    ,e.getMessage()
                    +"\n"
                    ,"UsuarioPovoar"
            );   
        } 

                        
        try{
                            
            PreparedStatement pstm;
            
            String cadastrar = "INSERT INTO SCHEMAJMARY.ACESSO ( "
                    //+ "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO            ,"
                    + "ID_USUARIO               ," 
                    
                    + "COMANDODOACESSO          ,"
                    
                    + "DESCRICAODOACESSO         "
                    
                    + ")" 
                    
                    + "VALUES ( "
                    + "?, ?, ?, ? "
                    
                    + ")"
            ;            
            
            //1 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ACESSO_USUARIO"                      ); 
            pstm.setString ( 4, "Acesso a página de usuários"         );  
                        
            pstm.executeUpdate();
            
            //2 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "CADASTRAR_USUARIO"                      ); 
            pstm.setString ( 4, "Acesso a página de cadastro do usuário" ); 
                        
            pstm.executeUpdate();
            
            //3 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ALTERAR_USUARIO"                      ); 
            pstm.setString ( 4, "Acesso a página de alteração do usuário" ); 
                        
            pstm.executeUpdate();
            
            //4 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "PESQUISAR_USUARIO"                      ); 
            pstm.setString ( 4, "Acesso a página de pesquisar usuários" ); 
                        
            pstm.executeUpdate();
            
            //5 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "EXCLUIR_USUARIO"                      ); 
            pstm.setString ( 4, "Acesso a usuário" ); 
                        
            pstm.executeUpdate();
            
            //6 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "VISUALIZAR_USUARIO"                      ); 
            pstm.setString ( 4, "Acesso a página de visualização do usuário" ); 
            
            pstm.executeUpdate();
            
            ////////////////////////////////////////////////////////////////////
           
            //1 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ACESSO_ACESSO"                          ); 
            pstm.setString ( 4, "Acesso a página de ACESSO"              );  
                        
            pstm.executeUpdate();
            
            //2 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "CADASTRAR_ACESSO"                      ); 
            pstm.setString ( 4, "Acesso a página de cadastro do ACESSO" ); 
                        
            pstm.executeUpdate();
            
            //3 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                        );
            pstm.setInt    ( 2, 1                                         );
            
            pstm.setString ( 3, "ALTERAR_ACESSO"                          ); 
            pstm.setString ( 4, "Acesso a página de alteração do ACESSO" ); 
                        
            pstm.executeUpdate();
            
            //4 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "PESQUISAR_ACESSO"                       ); 
            pstm.setString ( 4, "Acesso a página de pesquisar ACESSO"    ); 
                        
            pstm.executeUpdate();
            
            //5 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "EXCLUIR_ACESSO"                         ); 
            pstm.setString ( 4, "Acesso a página de ACESSO"              ); 
                        
            pstm.executeUpdate();
            
            //6 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "VISUALIZAR_ACESSO"                      ); 
            pstm.setString ( 4, "Acesso a página de visualização do ACESSO" ); 
            
            pstm.executeUpdate();
             
            ////////////////////////////////////////////////////////////////////
           
            //1 CONTROLE ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ACESSO_CONTROLE_ACESSO"                          ); 
            pstm.setString ( 4, "Acesso a página de CONTROLE ACESSO"              );  
                        
            pstm.executeUpdate();
            
            //2 CONTROLE ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "CADASTRAR_CONTROLE_ACESSO"                      ); 
            pstm.setString ( 4, "Acesso a página de cadastro do CONTROLE ACESSO" ); 
                        
            pstm.executeUpdate();
            
            //3 CONTROLE ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                        );
            pstm.setInt    ( 2, 1                                         );
            
            pstm.setString ( 3, "ALTERAR_CONTROLE_ACESSO"                          ); 
            pstm.setString ( 4, "Acesso a página de alteração do CONTROLE ACESSO" ); 
                        
            pstm.executeUpdate();
            
            //4 CONTROLE ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "PESQUISAR_CONTROLE_ACESSO"                       ); 
            pstm.setString ( 4, "Acesso a página de pesquisar CONTROLE ACESSO"    ); 
                        
            pstm.executeUpdate();
            
            //5 CONTROLE ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "EXCLUIR_CONTROLE_ACESSO"                         ); 
            pstm.setString ( 4, "Acesso a página de CONTROLE ACESSO"              ); 
                        
            pstm.executeUpdate();
            
            //6 CONTROLE ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "VISUALIZAR_CONTROLE_ACESSO"                      ); 
            pstm.setString ( 4, "Acesso a página de visualização do CONTROLE ACESSO" ); 
            
            pstm.executeUpdate();
             
            ////////////////////////////////////////////////////////////////////            
            //1 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ACESSO_PAIS"                          ); 
            pstm.setString ( 4, "Acesso a página PAIS"              );  
                        
            pstm.executeUpdate();
            
            //2 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "CADASTRAR_PAIS"                      ); 
            pstm.setString ( 4, "Acesso a página de cadastro de PAIS" ); 
                        
            pstm.executeUpdate();
            
            //3 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                        );
            pstm.setInt    ( 2, 1                                         );
            
            pstm.setString ( 3, "ALTERAR_PAIS"                          ); 
            pstm.setString ( 4, "Acesso a página de alteração do PAIS" ); 
                        
            pstm.executeUpdate();
            
            //4 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "PESQUISAR_PAIS"                       ); 
            pstm.setString ( 4, "Acesso a página de pesquisar PAIS"    ); 
                        
            pstm.executeUpdate();
            
            //5 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "EXCLUIR_PAIS"                         ); 
            pstm.setString ( 4, "Acesso a página de exclusão PAIS"              ); 
                        
            pstm.executeUpdate();
            
            //6 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "VISUALIZAR_PAIS"                      ); 
            pstm.setString ( 4, "Acesso a página de visualização do PAIS" ); 
            
            pstm.executeUpdate();            
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////            
            //1 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ACESSO_UF"                          ); 
            pstm.setString ( 4, "Acesso a página UF"              );  
                        
            pstm.executeUpdate();
            
            //2 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "CADASTRAR_UF"                      ); 
            pstm.setString ( 4, "Acesso a página de cadastro de UF" ); 
                        
            pstm.executeUpdate();
            
            //3 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                        );
            pstm.setInt    ( 2, 1                                         );
            
            pstm.setString ( 3, "ALTERAR_UF"                          ); 
            pstm.setString ( 4, "Acesso a página de alteração do UF" ); 
                        
            pstm.executeUpdate();
            
            //4 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "PESQUISAR_UF"                       ); 
            pstm.setString ( 4, "Acesso a página de pesquisar UF"    ); 
                        
            pstm.executeUpdate();
            
            //5 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "EXCLUIR_UF"                         ); 
            pstm.setString ( 4, "Acesso a página de exclusão UF"              ); 
                        
            pstm.executeUpdate();
            
            //6 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "VISUALIZAR_UF"                      ); 
            pstm.setString ( 4, "Acesso a página de visualização do UF" ); 
            
            pstm.executeUpdate();            
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////            
            //1 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ACESSO_MUNICIPIO"                          ); 
            pstm.setString ( 4, "Acesso a página MUNICIPIO"              );  
                        
            pstm.executeUpdate();
            
            //2 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "CADASTRAR_MUNICIPIO"                      ); 
            pstm.setString ( 4, "Acesso a página de cadastro de MUNICIPIO" ); 
                        
            pstm.executeUpdate();
            
            //3 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                        );
            pstm.setInt    ( 2, 1                                         );
            
            pstm.setString ( 3, "ALTERAR_MUNICIPIO"                          ); 
            pstm.setString ( 4, "Acesso a página de alteração do MUNICIPIO" ); 
                        
            pstm.executeUpdate();
            
            //4 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "PESQUISAR_MUNICIPIO"                       ); 
            pstm.setString ( 4, "Acesso a página de pesquisar MUNICIPIO"    ); 
                        
            pstm.executeUpdate();
            
            //5 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "EXCLUIR_MUNICIPIO"                         ); 
            pstm.setString ( 4, "Acesso a página de exclusão MUNICIPIO"              ); 
                        
            pstm.executeUpdate();
            
            //6 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "VISUALIZAR_MUNICIPIO"                      ); 
            pstm.setString ( 4, "Acesso a página de visualização do MUNICIPIO" ); 
            
            pstm.executeUpdate();            
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////            
            //1 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ACESSO_CEP"                          ); 
            pstm.setString ( 4, "Acesso a página CEP"              );  
                        
            pstm.executeUpdate();
            
            //2 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "CADASTRAR_CEP"                      ); 
            pstm.setString ( 4, "Acesso a página de cadastro de CEP" ); 
                        
            pstm.executeUpdate();
            
            //3 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                        );
            pstm.setInt    ( 2, 1                                         );
            
            pstm.setString ( 3, "ALTERAR_CEP"                          ); 
            pstm.setString ( 4, "Acesso a página de alteração do CEP" ); 
                        
            pstm.executeUpdate();
            
            //4 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "PESQUISAR_CEP"                       ); 
            pstm.setString ( 4, "Acesso a página de pesquisar CEP"    ); 
                        
            pstm.executeUpdate();
            
            //5 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "EXCLUIR_CEP"                         ); 
            pstm.setString ( 4, "Acesso a página de exclusão CEP"              ); 
                        
            pstm.executeUpdate();
            
            //6 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "VISUALIZAR_CEP"                      ); 
            pstm.setString ( 4, "Acesso a página de visualização do CEP" ); 
            
            pstm.executeUpdate();            
            ////////////////////////////////////////////////////////////////////
            
            
            ////////////////////////////////////////////////////////////////////            
            //1 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "ACESSO_ESTABELECIMENTO"                          ); 
            pstm.setString ( 4, "Acesso a página CEP"              );  
                        
            pstm.executeUpdate();
            
            //2 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "CADASTRAR_ESTABELECIMENTO"                      ); 
            pstm.setString ( 4, "Acesso a página de cadastro do ESTABELECIMENTO" ); 
                        
            pstm.executeUpdate();
            
            //3 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                        );
            pstm.setInt    ( 2, 1                                         );
            
            pstm.setString ( 3, "ALTERAR_ESTABELECIMENTO"                          ); 
            pstm.setString ( 4, "Acesso a página de alteração do ESTABELECIMENTO" ); 
                        
            pstm.executeUpdate();
            
            //4 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "PESQUISAR_ESTABELECIMENTO"                       ); 
            pstm.setString ( 4, "Acesso a página de pesquisar ESTABELECIMENTO"    ); 
                        
            pstm.executeUpdate();
            
            //5 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "EXCLUIR_ESTABELECIMENTO"                         ); 
            pstm.setString ( 4, "Acesso a página de exclusão ESTABELECIMENTO"              ); 
                        
            pstm.executeUpdate();
            
            //6 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,8,1987)                       );
            pstm.setInt    ( 2, 1                                        );
            
            pstm.setString ( 3, "VISUALIZAR_ESTABELECIMENTO"                      ); 
            pstm.setString ( 4, "Acesso a página de visualização do ESTABELECIMENTO" ); 
            
            pstm.executeUpdate();            
            ////////////////////////////////////////////////////////////////////
            
            
            retorno = true;

        } catch ( Exception e ) {
            
            retorno = false;
            JOPM JOPM = new JOPM( "EXCEÇÃO: " + "No Acesso\n"
                    ,"\n"
                    +"\n"
                    ,"UsuarioPovoar"
            );
        }             
        
        
        return retorno;
    }
    
    /******************Executar Teste*************************************
     * @param args************************/
    public static void main(String[] args) {            
        criar();
    }
    /******************Executar Teste*************************************/
    
}
