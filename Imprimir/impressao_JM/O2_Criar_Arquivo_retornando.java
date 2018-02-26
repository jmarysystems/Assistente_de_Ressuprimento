/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package impressao_JM;

import estabelecimento.Estabelecimento;
import fornecedor.Fornecedor;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import utilidades.Arquivo_Ou_Pasta;

/**
 *
 * @author Ana
 */
public class O2_Criar_Arquivo_retornando { 
    
    int x = 1;
    
    private String arq_html = "";
    
    String arq   = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems_Print";
    String arqPS = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems_PrintPS";
    
    String pagina = "";
    File retorno; 
    
    int numeroPagina = 0;
        
    public O2_Criar_Arquivo_retornando( String pagina2, int qtdLinha2, int numeroPagina2, int qtd_linhas_por_pg, Fornecedor Fornecedor2 ) {  
        
        pagina       = pagina2;    
        numeroPagina = numeroPagina2;
        dataHoje();
        qtsPagias(qtdLinha2, qtd_linhas_por_pg);
        htmlInicio();
        dadosDaLoja(Fornecedor2);        
    }
    
    public File getFile() {  
    
       synchronized(this) {  
           
           verificar_se_arq_existe(); 
       }    
       // Agora o diretório está vazio, restando apenas deletá-lo.  
       return retorno;  
    } 
    
    public void verificar_se_arq_existe(){  
        synchronized(this) {  
            if ( new File( arq ).exists() == false ) { x++;
    
                if ( Arquivo_Ou_Pasta.criarPasta( System.getProperty("user.home"), "JMarySystems_Print" ) == true ) {
                    
                    synchronized(this) {
                        
                        verificar_se_arqPS_existe();
                    }
                }
                else{
                    
                    verificar_se_arq_existe();
                }
            }    
            else{
                
                synchronized(this) {
                    
                    verificar_se_arqPS_existe();
                }
            }
        }  
    }
    
    public void verificar_se_arqPS_existe(){  
        synchronized(this) {  
            if ( new File( arqPS ).exists() == false ) { x++;
    
                if ( Arquivo_Ou_Pasta.criarPasta( System.getProperty("user.home"), "JMarySystems_PrintPS" ) == true ) {
                    
                    synchronized(this) {
                        
                        verificar_se_arq_html_existe();
                    }
                }
                else{
                    
                    verificar_se_arqPS_existe();
                }
            }    
            else{
                
                synchronized(this) {
                    
                     verificar_se_arq_html_existe();
                }
            }     
        }  
    } 
    
    public void verificar_se_arq_html_existe(){  
        synchronized(this) {  
            
            arq_html = arq + System.getProperty("file.separator") + "z" + x + ".html";
            
            synchronized(this) {
                
                if ( new File( arq_html ).exists() == false ) { 
                    
                    String arquivoASerC = html_inicio + dadosDaLoja + pagina + html_fim;
    
                    if ( Arquivo_Ou_Pasta.criar_Arquivo_Iso_Boo(arq_html, arquivoASerC ) == true ) {
                    
                        synchronized(this) {
                         
                            retorno = new File( arq_html );
                        }
                    }
                    else{
                    
                        verificar_se_arq_html_existe();
                    }                
                }    
                else{ 
                
                    x++;                
                    verificar_se_arq_html_existe();
                }   
            }
        }  
    } 
    
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
    String        dataHoje;
    String        html_inicio;
    String        dadosDaLoja;
    int           qtdPaginas = 0;
    String        html_fim         = "</body> </html>";
    
    private void dataHoje(){  
        synchronized ( this ) { try{
                
            GregorianCalendar gc       = new GregorianCalendar();
            Date dataHojeC             = gc.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            dataHoje                   = formatter.format( dataHojeC );      
        } catch( Exception e){} }
    }
    
    private void htmlInicio(){
        synchronized ( this ){ try{ StringBuilder sb = new StringBuilder();

            sb.append(
            "<html>"
                    
                + "<head> <title> HOME </title>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
                + "<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\""+ getClass().getResource("/css/estrutura.css").toString() +"\" />"
                + "</head>"
                    
                + "<body>" 
            );
            
            html_inicio = sb.toString();
            
        } catch( Exception e ){} }  
    }
    
    private void dadosDaLoja(Fornecedor Fornecedor){
        
        /*GESTOR DE COMPRAS - GBARBOSA - PRESIDENTE KENNEDY - B141*/
            List<Estabelecimento> XXGbdescricaosetor = null;
            String enderecoLoja="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Estabelecimento.class, JPAUtil.em());
                                        
                    XXGbdescricaosetor = (List<Estabelecimento>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Estabelecimento.class, 
                            "SELECT * FROM SCHEMAJMARY.ESTABELECIMENTO WHERE NOME_FANTASIA = ?", "B141" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getNomeFantasia(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    enderecoLoja = XXGbdescricaosetor.get(0).getRazaoSocial();
                }
                
            }catch( Exception e ){}
            /*GESTOR DE COMPRAS - GBARBOSA - PRESIDENTE KENNEDY - B141*/
        
        synchronized ( this ){ try{ StringBuilder sb = new StringBuilder();
            sb.append(                    
                    "<table WIDTH=100% >"
                    
                        + "<tr>"
                    
                            + "<td>"
                    
                                +"<img src='" + getClass().getResource("/utilidades_imagens/logogb.jpg").toString() + "'" + " width=\"160\" height=\"90\" border=\"0\" ALIGN=BOTTOM title=\"GBARBOSA\" >"                                            
                    
                            + "</td>"
                    
                            + "<td class='txttitulo'>"

                                + "<b>&nbsp;&nbsp;&nbsp;&nbsp;"+enderecoLoja+"</b>"                                          
                    
                            + "</td>"
                    
                            + "<td align='right' class='txttitulo'>"
                                
                                +"<div>"
                                    +"<table>"
                                        +"<tbody>"
                                            +"<tr>"
                                                +"<td align='center'>"+ "DATA:&nbsp;&nbsp;" +  dataHoje +"<hr noshade=\"noshade\" size=\"1\">" + "<b>&nbsp;&nbsp;&nbsp;" +  "PAG. "+ numeroPagina + " / " + qtdPaginas + "</b>" + "</td>"
                                            +"</tr>"
                                        +"</tbody>"
                                    +"</table>"
                                +"</div>"
                    
                            + "</td>"
                    
                        + "</tr>"
                    
                    + "</table>"
                    
                    + "<HR SIZE=1 WIDTH=100% NOSHADE>" +
                    
                    "<table>"
                    
                        + "<tr>"
                    
                            + "<td>"
                                  +"<b> CÓD. FORNECEDOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + Fornecedor.getNomeourazaosocial()
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> RAZÃO&nbsp;SOCIAL/NOME&nbsp;FANTASIA </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + Fornecedor.getNomefantasia()
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> CD/ABASTECIMENTO </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + Fornecedor.getCd()
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> NOME&nbsp;PROMOTOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + Fornecedor.getNomepromotor()
                    
                            + "</td>"
                    
                        + "</tr>"
                    
                        + "<tr>"
                    
                            + "<td>"
                                  +"<b> LEAD&nbsp;TIME </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + Fornecedor.getLeadtime()
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> GERAÇÃO&nbsp;PEDIDOS&nbsp;RA </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + Fornecedor.getGeracaopedidosra()
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> FREQUÊNCIA </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + Fornecedor.getFrequencia()
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> TELEFONE&nbsp;PROMOTOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + Fornecedor.getTelefonepromotor()
                    
                            + "</td>"
                    
                        + "</tr>"
                    
                    + "</table>" +
                    
                    "<HR SIZE=1 WIDTH=100% NOSHADE>" 
            );
            
            dadosDaLoja = sb.toString();
            
        } catch( Exception e ){} }
    }
    
    private void qtsPagias(int qtdLinha2, int qtd_linhas_por_pg2){
        synchronized ( this ){ try{ 
            
            int count = 0;
            
            if( qtdLinha2 > 0 ){  
                for( int L_i=0; L_i < qtdLinha2; L_i++ ){ count++;
                
                    int td = qtdLinha2 - 1;
                    if( count == qtd_linhas_por_pg2 || L_i == td ){
                        count = 0;
                        
                        qtdPaginas++;  
                        //System.out.println( "qtdPaginas: "+qtdPaginas +" - qtdLinhas: "+qtdLinha2 );
                    }
                }
            }
        } catch( Exception e ){} }  
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /******************Executar Teste*************************************  
     * @param args************************
    public static void main(String[] args) {            
          
        ControleThread_Print t1 = new ControleThread_Print(13); 
        
        t1.setName("ControleThread_Print");   
        
        t1.start();  
    }
    /******************Executar Teste*************************************/
    
}
