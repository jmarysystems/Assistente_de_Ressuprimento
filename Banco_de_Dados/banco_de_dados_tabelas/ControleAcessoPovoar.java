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
public class ControleAcessoPovoar {
    
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
            JOPM JOPM = new JOPM( "EXCEÇÃO: " + "No ControleAcessoPovoar\n"
                    ,e.getMessage()
                    +"\n"
                    ,"ControleAcessoPovoar"
            );   
        } 

                        
        try{
                            
            PreparedStatement pstm;
            
            String cadastrar = "INSERT INTO SCHEMAJMARY.CONTROLEACESSO ( "
                    //+ "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO            ,"
                    
                    + "ID_USUARIO               ," 
                    
                    + "ID_ACESSO                ,"
                    
                    + "ID_ESTABELECIMENTO        " 
                    
                    + ")" 
                    
                    + "VALUES ( "
                    + "?, ?, ?, ? "
                    
                    + ")"
            ;            
            
            //1 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 1                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //2 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 2                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //3 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 3                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //4 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 4                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //5 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 5                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //6 USUARIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 6                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            ////////////////////////////////////////////////////////////////////
            
            //1 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 7                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //2 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 8                                       );
            pstm.setInt    ( 4, 1                                       );
            
                        
            pstm.executeUpdate();
            
            //3 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 9                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //4 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 10                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //5 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 11                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //6 ACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 12                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            //////////////////////////////////////////////////////////////////
            
            //1 CONTROLEACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 13                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //2 CONTROLEACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 14                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //3 CONTROLEACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 15                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //4 CONTROLEACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 16                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //5 CONTROLEACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 17                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //6 CONTROLEACESSO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 18                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            ////////////////////////////////////////////////////////////////////  
            
            //1 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 19                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //2 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 20                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //3 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 21                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //4 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 22                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //5 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 23                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //6 PAIS
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 24                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            ////////////////////////////////////////////////////////////////////
            
            
            ////////////////////////////////////////////////////////////////////  
            
            //1 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 25                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //2 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 26                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //3 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 27                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //4 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 28                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //5 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 29                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //6 UF
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 30                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            ////////////////////////////////////////////////////////////////////
            
            
            
            ////////////////////////////////////////////////////////////////////  
            
            //1 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 31                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //2 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 32                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //3 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 33                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //4 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 34                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //5 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 35                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //6 MUNICIPIO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 36                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            ////////////////////////////////////////////////////////////////////
            
            
            
            ////////////////////////////////////////////////////////////////////  
            
            //1 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 37                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //2 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 38                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //3 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 39                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //4 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 40                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //5 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 41                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //6 CEP
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 42                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            ////////////////////////////////////////////////////////////////////
            
            
            
            ////////////////////////////////////////////////////////////////////  
            
            //1 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 43                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //2 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 44                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //3 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 45                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //4 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 46                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //5 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 47                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            //6 ESTABELECIMENTO
            pstm = con.prepareStatement( cadastrar );
            pstm.setDate   ( 1, new Date(8,12,2060)                      );
            pstm.setInt    ( 2, 1                                        );
            pstm.setInt    ( 3, 48                                       );
            pstm.setInt    ( 4, 1                                       );
                        
            pstm.executeUpdate();
            
            ////////////////////////////////////////////////////////////////////
            
            retorno = true;

        } catch ( Exception e ) {
            
            retorno = false;
            JOPM JOPM = new JOPM( "EXCEÇÃO: " + "No Acesso\n"
                    ,"\n"
                    +"\n"
                    ,"ControleAcessoPovoar"
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
