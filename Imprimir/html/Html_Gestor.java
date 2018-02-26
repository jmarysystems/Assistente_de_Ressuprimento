/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package html;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import print.ControleThread_Print;
import vizualizar_impressao.Imprimir_Home_Inicio;

/**
 *
 * @author Ana
 */
public class Html_Gestor {
    
    ControleThread_Print ControleThread_Print;
    
    public Html_Gestor( ControleThread_Print ControleThread_Print2 ) {
        
        ControleThread_Print = ControleThread_Print2;
    }
    
    public Html_Gestor( Imprimir_Home_Inicio Imprimir_Home_Inicio ) {
        
        
    }
            
    public void getHtmlGestor(){
        String retorno = "";
        
        StringBuilder sb = new StringBuilder();
        
        try{ 
            GregorianCalendar gc = new GregorianCalendar();
            Date dataHoje = gc.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String data_formatada = formatter.format( dataHoje );
                
            sb.append( getHtmlGestorInicioHeader() );
            sb.append( getHtmlGestorInicio( data_formatada ) );
            sb.append( getHtmlGestorMeio() );
            sb.append( getHtmlGestorFim() );

        } catch( Exception e){}
        
        retorno = sb.toString();
        
        ControleThread_Print.html = retorno;
        
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            ControleThread_Print.verificar_se_arq_existe();
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "getHtmlGestor(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private String getHtmlGestorInicioHeader(){

        String retorno = "";
        
        try{ StringBuilder sb = new StringBuilder();

            sb.append(
            "<html>"
                    
                + "<head> <title> HOME </title>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
                + "<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\""+ getClass().getResource("/css/estrutura.css").toString() +"\" />"
                + "</head>"
                    
                + "<body>" 
            );
            
            retorno = sb.toString();
            
        } catch( Exception e ){}  
        
        
        return retorno;
    }
    
    private String getHtmlGestorInicio( String dataG ){

        String retorno = "";
        
        try{ StringBuilder sb = new StringBuilder();

            sb.append(
                    
                    "<table WIDTH=100% >"
                    
                        + "<tr>"
                    
                            + "<td>"
                    
                                +"<img src='" + getClass().getResource("/utilidades_imagens/logogb.jpg").toString() + "'" + " width=\"180\" height=\"100\" border=\"0\" ALIGN=BOTTOM title=\"GBARBOSA\" >"                                            
                    
                            + "</td>"
                    
                            + "<td class='txttitulo'>"

                                + "<b>&nbsp;&nbsp;&nbsp;&nbsp;GESTOR DE COMPRAS - GBARBOSA - PRESIDENTE KENNEDY</b>"                                          
                    
                            + "</td>"
                    
                            + "<td align='right' class='txttitulo'>"
                                
                                +"<div>"
                                    +"<table>"
                                        +"<tbody>"
                                            +"<tr>"
                                                +"<td align='center'>"+ "DATA:&nbsp;&nbsp;" +  dataG +"<hr noshade=\"noshade\" size=\"1\">" + "<b>&nbsp;&nbsp;&nbsp;" +  "B141" + "</b>" + "</td>"
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
                                  +"<b> FORNECEDOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + "________________"
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> NOME&nbsp;VENDEDOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + "________________"
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> TELEFONE&nbsp;VENDEDOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + "________________"
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> NOME&nbsp;PROMOTOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + "________________"
                    
                            + "</td>"
                    
                        + "</tr>"
                    
                        + "<tr>"
                    
                            + "<td>"
                                  +"<b> NOME&nbsp;FANTASIA </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + "________________"
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> NOME&nbsp;SUPERVISOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + "________________"
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> TELEFONE&nbsp;SUPERVISOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + "________________"
                    
                            + "</td>"
                    
                            + "<td>"
                                  +"&nbsp;&nbsp;&nbsp;<b> TELEFONE&nbsp;PROMOTOR </b> &nbsp;&nbsp;&nbsp;" 
                            + "</td>"
                    
                            + "<td>"
                                
                                + "&nbsp;" + "________________"
                    
                            + "</td>"
                    
                        + "</tr>"
                    
                    + "</table>" +
                    
                    "<HR SIZE=1 WIDTH=100% NOSHADE>" 
            );
            
            retorno = sb.toString();
            
        } catch( Exception e ){}  
        
        
        return retorno;
    }
    
    public double custoT   = 0;
    
    //<HTML><HEAD><STYLE>	thead { display: table-header-group; } 	tfoot { display: table-footer-group; } </STYLE></HEAD><BODY><table border=0 align="center" width="100%"> <thead>   <tr> 	 <th width=100%>THIS IS THE HEADER </th>  </tr></thead> <tfoot>   <tr> 	 <td width=100%>THIS IS THE footer</td>  </tr></tfoot> <tbody>  <tr>    <td width="100%">	   aki vem todo o resto do conteudo   </td> </tr> </tbody> </table> </BODY></HTML>
    
    private String getHtmlGestorMeio(){
        String retorno = "";
        
        try{ 
            StringBuilder sb = new StringBuilder();
                                                  //a4: 595-842 //width='100%'
            sb.append( "<DIV align=\"center\"><CENTER>" + "<table width='100%' border=\"1\">" );
            
            for( int L_i=0; L_i < ControleThread_Print.tbPesquisa.getRowCount(); L_i++ ){
                
                for( int C_i=0; C_i < ControleThread_Print.tbPesquisa.getColumnCount(); C_i++ ){
                    
                    String str = ControleThread_Print.tbPesquisa.getColumnName(C_i);
                    if(str.trim().equals("DISPONIBILIDADE")){
                        str = "DISP.";
                    }
                    else if( str.trim().equals("DESCRICAO DO MATERIAL") ){
                        str = "DESCRICAO DO MATERIAL";
                    }
                    sb.append( "<th>" + str  + "</th>" );                     
                }
                break;
            }
            
            

        if( ControleThread_Print.tbPesquisa.getModel().getRowCount() > 0 ){           
            for( int L_i=0; L_i < ControleThread_Print.tbPesquisa.getRowCount(); L_i++ ){
                
                int c = L_i; c+=1;
                sb.append( "<tr>" );
                for( int C_i=0; C_i < ControleThread_Print.tbPesquisa.getColumnCount(); C_i++ ){
                    
                    String str = String.valueOf( ControleThread_Print.tbPesquisa.getValueAt(L_i, C_i) );
                    
                    String strn = ControleThread_Print.tbPesquisa.getColumnName(C_i);
                    
                    if( strn.trim().equals("DESCRICAO DO MATERIAL") ){
                        
                        sb.append( "<td align='center' NOWRAP='NOWRAP'>" + str  + "</td>" );
                    }
                    else if( strn.trim().equals("SUGESTÃO-JM") ){
                        
                        sb.append( "<td align='right'>" + str  + "</td>" );                        
                    }
                    else{
                        
                        sb.append( "<td align='center'>" + str  + "</td>" );                        
                    }
                } 
                sb.append( "</tr>" );
            }
        }
                                    
            sb.append( "</table>" + "</CENTER></DIV>" );
            
            
            retorno = sb.toString();
            
        } catch( Exception e ){}         
        
        return retorno;
    }
    
    private String getHtmlGestorFim(){
        String retorno = "";
        
        try{ StringBuilder sb = new StringBuilder();

            sb.append(
                    
                "</body>"                    
           +"</html>"
            );
            
            retorno = sb.toString();
        } catch( Exception e ){}          
        
        return retorno;
    }
    
}
