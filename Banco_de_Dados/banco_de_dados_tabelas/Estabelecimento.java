/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados_tabelas;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utilidades.DB;

/**
 *
 * @author AnaMariana
 */
public class Estabelecimento {
    
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
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage() );    
        } 

        try {                     
            
            s = con.createStatement(); 
            
            s       
            .execute( "CREATE TABLE SCHEMAJMARY.ESTABELECIMENTO ( "
                    + "ID          INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                         
                    //DADOS BÁSICOS
                    + "DATA_CADASTRO              DATE                  ,"
                    
                    + "ID_USUARIO                 INTEGER NOT NULL,"                    
                    + "CONSTRAINT USUARIO_ID_ESTABELECIMENTO FOREIGN KEY (ID_USUARIO) references SCHEMAJMARY.USUARIO (ID),"
                    
                    + "OBSERVACAO                 VARCHAR(900)          ,"
                    
                    + "IMAGEM_LOGOTIPO            VARCHAR(300)          ,"
                    + "RAZAO_SOCIAL               VARCHAR(300) NOT NULL ,"
                    + "NOME_FANTASIA              VARCHAR(300)          ,"
                    + "CNPJ                       VARCHAR(300) NOT NULL ,"
                    
                    + "INSCRICAO_ESTADUAL         VARCHAR(300)          ,"
                    + "INSCRICAO_ESTADUAL_ST      VARCHAR(300)          ,"
                    + "INSCRICAO_MUNICIPAL        VARCHAR(300)          ,"
                    + "INSCRICAO_JUNTA_COMERCIAL  VARCHAR(300)          ,"
                    + "DATA_INSC_JUNTA_COMERCIAL  DATE                  ,"
                    + "SUFRAMA                    VARCHAR(300)          ,"      
                    
                    + "TIPO                       VARCHAR(300)          ,"
                    
                    + "DATA_INICIO_ATIVIDADES     DATE                  ,"                   
                    
                    //CONTATO
                    + "CONTATO                    VARCHAR(300)          ,"  
                    + "TELEFONE                   VARCHAR(300)          ,"
                    + "FONE2                      VARCHAR(300)          ,"
                    + "FONE3                      VARCHAR(300)          ,"
                                                          
                    + "EMAIL                      VARCHAR(300)          ,"
                    + "OUTROEMAIL                 VARCHAR(300)          ,"
                    + "FACEBOOK                   VARCHAR(300)          ,"
                                    
                    //FISCAL
                    + "CRT                        VARCHAR(300)          ,"
                    + "TIPO_REGIME                VARCHAR(300)          ,"
                    + "ALIQUOTA_PIS               FLOAT                 ,"
                    + "ALIQUOTA_COFINS            FLOAT                 ,"
                    + "ALIQUOTA_SAT               FLOAT                 ,"
                    
                    //ENDEREÇO
                    + "CEP                   VARCHAR(300)  ,"
                    + "LOGRADOURO            VARCHAR(300)  ,"
                    + "NUMERO                VARCHAR(300)  ,"
                    + "COMPLEMENTO           VARCHAR(300)  ,"
                    
                    + "BAIRRO                     VARCHAR(300)          ,"                    
                    + "CODIGO_IBGE_CIDADE         VARCHAR(300)          ,"                    
                    + "CODIGO_IBGE_UF             VARCHAR(300)          ,"
                    + "CIDADE                     INTEGER NOT NULL      ,"   
                    + "ESTADO                     INTEGER NOT NULL      ,"   
                    + "PAIS                       INTEGER NOT NULL      ,"   
                    
                    //OUTROS
                    + "CODIGO_TERCEIROS           VARCHAR(300)          ,"
                    + "CODIGO_GPS                 VARCHAR(300)          ,"
                    + "CODIGO_CNAE_PRINCIPAL      VARCHAR(300)          ,"   
                    + "CEI                        VARCHAR(300)          ,"                                     
                    + "FPAS                       VARCHAR(300)           "
                                                            
                    + ")" 
            );
            
        } catch ( Exception e ) {
            
            retorno = false;
            JOptionPane.showMessageDialog( null, "Erro! \n" + e.getMessage() );    
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
