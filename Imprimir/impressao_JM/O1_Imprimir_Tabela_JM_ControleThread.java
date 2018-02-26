/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package impressao_JM;

import fornecedor.Fornecedor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import utilidades.JOPM;

/**
 *
 * @author Ana
 */
public class O1_Imprimir_Tabela_JM_ControleThread extends Thread { 
    
    public JTable tbPesquisa;
    List<File> lista_Arquivos = new ArrayList();
    
    Fornecedor Fornecedor;
    
    int qtd_linhas_por_pg = 44;
            
    public O1_Imprimir_Tabela_JM_ControleThread( JTable tbPesquisa2, Fornecedor Fornecedor2 ){   
        tbPesquisa = tbPesquisa2;
        
        Fornecedor = Fornecedor2;
    } 
                
    @Override
    public void run(){  
        synchronized ( this ) {
                        
            htmlMeio();    
        }  
    }  
    
    private void htmlMeio(){    synchronized ( this ){ try{ 
                        
        int count = 0;
        
        String        inicioTabela    = "<DIV align=\"center\"><CENTER>" + "<table width='100%' border=\"1\">";
        StringBuilder cabecalholinhas = new StringBuilder();
        StringBuilder linhas          = new StringBuilder();
        String        fimTabela       = "</table>" + "</CENTER></DIV>";
        String        pagina          = "";
        int           numeroPagina    = 0;
                
        if( tbPesquisa.getModel().getRowCount() > 0 ){  
        
            for( int L_i=0; L_i < tbPesquisa.getRowCount(); L_i++ ){ count++;
                
                for( int C_i=0; C_i <tbPesquisa.getColumnCount(); C_i++ ){
                    
                    String str = tbPesquisa.getColumnName(C_i);
                    if(str.trim().equals("DISPONIBILIDADE")){
                        str = "DISP.";
                    }
                    else if( str.trim().equals("ALTURA") ){
                        str = "ALT URA";
                    }
                    else if( str.trim().equals("FRENTE") ){
                        str = "FRE NTE";
                    }
                    else if( str.trim().equals("FUNDO") ){
                        str = "FUN DO";
                    }
                    else if( str.trim().equals("GÔNDULA") ){
                        str = "GÔN DULA";
                    }
                    cabecalholinhas.append( "<th>" + str  + "</th>" );                     
                }
                break;
            }
            
            
        }             

        if( tbPesquisa.getModel().getRowCount() > 0 ){           
            for( int L_i=0; L_i < tbPesquisa.getModel().getRowCount(); L_i++ ){ count++;
                
                linhas.append( "<tr>" );
                for( int C_i=0; C_i < tbPesquisa.getColumnCount(); C_i++ ){
                    
                    String str = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) );
                    if( str.equals("null") ){ str=""; }
                    
                    String strn = tbPesquisa.getColumnName(C_i);
                    
                    if( strn.trim().equals("DESCRICAO DO MATERIAL") ){
                        
                        linhas.append( "<td align='center' NOWRAP='NOWRAP'>" + str  + "</td>" );
                    }
                    else if( strn.trim().equals("SUGESTÃO-JM") ){
                        
                        linhas.append( "<td align='right'>" + str  + "</td>" );                        
                    }
                    else{
                        
                        linhas.append( "<td align='center'>" + str  + "</td>" );                        
                    }
                } 
                linhas.append( "</tr>" );
                
                int td = tbPesquisa.getModel().getRowCount() - 1;
                if( count == qtd_linhas_por_pg || L_i == td ){
                    numeroPagina++;
                    String meio = linhas.toString();
                    
                    count = 0;
                    linhas      = new StringBuilder();
                    
                    pagina = inicioTabela + cabecalholinhas + meio + fimTabela;
                    
                    try { Thread.sleep( 15 ); } catch( Exception e ){}  
                    int qtdLinhas = tbPesquisa.getModel().getRowCount();
                    O2_Criar_Arquivo_retornando Criar_Arquivo_retornando = new O2_Criar_Arquivo_retornando( pagina, qtdLinhas, numeroPagina, qtd_linhas_por_pg, Fornecedor );
                    
                    File f = Criar_Arquivo_retornando.getFile();
                    System.out.println( f.toURI().toURL() +" - "+qtdLinhas );
                    try { Thread.sleep( 15 ); } catch( Exception e ){}
                    
                    lista_Arquivos.add( f );
                }
            }
        }
        
        ////////////////////////////////////////////////////////////////////////
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
                    
                    O3_Imprimir_Aquivos_Html t1 = new O3_Imprimir_Aquivos_Html( lista_Arquivos );
                    t1.setName("O3_Imprimir_Aquivos_Html");
                    t1.start();                   
                } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        ////////////////////////////////////////////////////////////////////////
            
        } catch( Exception e ){} }        
    }
                                            
    /******************Executar Teste*************************************  
     * @param args************************
    public static void main(String[] args) {            
          
        ControleThread_Print t1 = new ControleThread_Print(13); 
        
        t1.setName("ControleThread_Print");   
        
        t1.start();  
    }
    /******************Executar Teste*************************************/
    
}