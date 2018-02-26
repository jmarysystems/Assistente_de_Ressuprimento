/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados_tabelas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utilidades.DB;
import utilidades.JOPM;

/**
 *
 * @author AnaMariana
 */
public class EstabelecimentoPovoar {
    
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
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage().toString() );    
        } 

                        
        try{
                
            
            PreparedStatement pstm;
            
            String cadastrar = "INSERT INTO SCHEMAJMARY.ESTABELECIMENTO ( "
                    //+ "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO                              ,"
                    
                    + "ID_USUARIO                                 ,"                    
                                        
                    + "OBSERVACAO                                 ,"
                    
                    + "IMAGEM_LOGOTIPO                            ,"
                    + "RAZAO_SOCIAL                               ,"
                    + "NOME_FANTASIA                              ,"
                    + "CNPJ                                       ,"
                    
                    + "INSCRICAO_ESTADUAL                         ,"
                    + "INSCRICAO_ESTADUAL_ST                      ,"
                    + "INSCRICAO_MUNICIPAL                        ,"
                    + "INSCRICAO_JUNTA_COMERCIAL                  ,"
                    + "DATA_INSC_JUNTA_COMERCIAL                  ,"
                    + "SUFRAMA                                    ,"      
                    
                    + "TIPO                                       ,"
                    
                    + "DATA_INICIO_ATIVIDADES                     ,"                   
                    
                    //CONTATO
                    + "CONTATO                                    ,"  
                    + "TELEFONE                                   ,"
                    + "FONE2                                      ,"
                    + "FONE3                                      ,"
                                                          
                    + "EMAIL                                      ,"
                    + "OUTROEMAIL                                 ,"
                    + "FACEBOOK                                   ,"
                                    
                    //FISCAL
                    + "CRT                                        ,"
                    + "TIPO_REGIME                                ,"
                    + "ALIQUOTA_PIS                               ,"
                    + "ALIQUOTA_COFINS                            ,"
                    + "ALIQUOTA_SAT                               ,"
                    
                    //ENDEREÇO
                    + "CEP                                        ,"
                    + "LOGRADOURO                                 ,"
                    + "NUMERO                                     ,"
                    + "COMPLEMENTO                                ,"
                    
                    + "BAIRRO                                     ,"                    
                    + "CODIGO_IBGE_CIDADE                         ,"                    
                    + "CODIGO_IBGE_UF                             ,"
                    + "CIDADE                                     ,"
                    + "ESTADO                                     ,"
                    + "PAIS                                       ,"
                    
                    //OUTROS
                    + "CODIGO_TERCEIROS                           ,"
                    + "CODIGO_GPS                                 ,"
                    + "CODIGO_CNAE_PRINCIPAL                      ,"   
                    + "CEI                                        ,"                                     
                    + "FPAS                                        "
                    
                    + ")" 
                    
                    + "VALUES ( "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + "?, ?                         "
                    
                    + ")"
            ;
            
            
            pstm = con.prepareStatement( cadastrar );
            
            ////////////////////////////////////////////////////////////////////
            //DADOS BÁSICOS
            pstm.setDate   ( 1,  new Date(8,8,1987)                             ); 
            pstm.setInt    ( 2,  1                                              ); 
            
            pstm.setString ( 3,  "Empresa fornecedora do Sistema"               ); 
            
            pstm.setString ( 4, "jmlogo.png"                                    ); 
            pstm.setString ( 5,  "Jmarysystems - Cleilson Henrique de Araujo"   ); 
            pstm.setString ( 6,  "jmary"                                        ); 
            pstm.setString ( 7,  "21.488.219/0001-51"                           );
            
            pstm.setString ( 8,  ""                                             );
            pstm.setString ( 9,  ""                                             );
            pstm.setString ( 10, ""                                             );
            pstm.setString ( 11, ""                                             );
            pstm.setDate   ( 12, new Date(8,8,1987)                             );
            pstm.setString ( 13, ""                                             );
            
            pstm.setString ( 14, ""                                             );
            
            pstm.setDate   ( 15, new Date(8,8,1987)                             );
            
            //CONTATO
            pstm.setString ( 16, ""                                             );
            pstm.setString ( 17, ""                                             );
            pstm.setString ( 18, ""                                             );
            pstm.setString ( 19, ""                                             );
            
            pstm.setString ( 20, ""                                             );
            pstm.setString ( 21, ""                                             );
            pstm.setString ( 22, ""                                             );
            
            //FISCAL
            pstm.setString ( 23, ""                                             );
            pstm.setString ( 24, ""                                             );
            pstm.setFloat  ( 25, 0                                              );
            pstm.setFloat  ( 26, 0                                              );
            pstm.setFloat  ( 27, 0                                              );
            
            //ENDEREÇO
            pstm.setString ( 28, ""                                             );
            pstm.setString ( 29, ""                                             );
            pstm.setString ( 30, ""                                             );
            pstm.setString ( 31, ""                                             );
            
            pstm.setString ( 32, ""                                             );
            pstm.setString ( 33, ""                                             );
            pstm.setString ( 34, ""                                             );
            pstm.setInt    ( 35, 1                                              );
            pstm.setInt    ( 36, 1                                              );
            pstm.setInt    ( 37, 1                                              );
            
            //OUTROS
            pstm.setString ( 38, ""                                             );
            pstm.setString ( 39, ""                                             );
            pstm.setString ( 40, ""                                             );
            pstm.setString ( 41, ""                                             );
            pstm.setString ( 42, ""                                             );

                        
            pstm.executeUpdate();
            ////////////////////////////////////////////////////////////////////
            
            
            retorno = true;

        } catch ( Exception e ) {
            
            retorno = false;
            JOPM JOptionPaneMod = new JOPM( 2, "criar(), \n"
                + e.getMessage() + "\n", "UsuarioPovoar" );
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
