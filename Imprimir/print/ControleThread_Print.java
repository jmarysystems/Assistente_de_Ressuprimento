/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package print;

import html.Html_Gestor;
import java.awt.print.PrinterJob;
import java.io.File;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.text.html.HTMLEditorKit;
import utilidades.Arquivo_Ou_Pasta;
import utilidades.JOPM;

/**
 *
 * @author Ana
 */
public class ControleThread_Print extends Thread { 
    
    //////////////////////////////////
    PrinterJob  PrinterJob = null;
    JEditorPane htmlPanePrincipal = new JEditorPane();
    /////////////////////////////////
    
    String arq   = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems_Print";
    String arqPS = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems_PrintPS";
    
    int x = 1;
    
    private String arq_html = "";
    
    public String html = "";
    
    public JTable tbPesquisa;
    int controle;
    
    public ControleThread_Print( JTable tbPesquisa2, int controle2, String html2 ){   
        tbPesquisa = tbPesquisa2;
        controle   = controle2;
        html       = html2;
    } 
            
    public void run(){  
        synchronized ( this ) {
            
            html();             
        }  
    }  
    
    public void html(){  
        synchronized ( this ) {
                
            switch( controle ){
                case 1: synchronized ( this ) {
                        Html_Gestor Html_Gestor = new Html_Gestor( this );
                        Html_Gestor.getHtmlGestor();
                    }break;
                        
                case 2: synchronized ( this ) {
                        verificar_se_arq_existe();
                    }break;    
            }                
        }  
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
    
                    if ( Arquivo_Ou_Pasta.criar_Arquivo_Iso_Boo( arq_html, html ) == true ) {
                    
                        synchronized(this) {
                         
                            impressora();
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
            
    public void impressora(){  
        synchronized(this) { 
            
            Impressora Impressora = new Impressora( "" );
            
            PrinterJob = Impressora.Impressora();
            
            if ( PrinterJob != null ) { 
                
                synchronized(this) {
                    
                    setar_PrinterJob();
                }
            } 
        }  
    }
    
    public void setar_PrinterJob(){  
        synchronized(this) { 
            
            synchronized(this) {
                
                PrinterJob.setJobName( "JM"  ); 
                PrinterJob.setCopies( 1 );
            }
            
            synchronized(this) {
                
                setar_JEditorPane();
            }
        }  
    }
    
    public void setar_JEditorPane(){  
        synchronized(this) { 
            
            htmlPanePrincipal.setEditorKit(new HTMLEditorKit());            
            htmlPanePrincipal.setContentType("text/html;charset=UTF-8");
            
            int tmHorizontal = 837; int tmVertical = 555;
            htmlPanePrincipal.setSize( tmHorizontal, tmVertical ); 
            
            try { 
                synchronized(this) {
                
                    synchronized(this) {
                                             
                        try{ htmlPanePrincipal.setPage( new File( arq_html ).toURI().toURL() ); } catch( Exception e ){ e.printStackTrace(); }
                        Thread.sleep( 100 );
                    }
                
                    synchronized(this) {
                    
                        Thread.sleep( 100 );
                        gerar_PS();
                    }
                }
                        
            } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setar_JEditorPane(), \n"+ e.getMessage() + "\n", this.getClass().getSimpleName() ); }
            
        }  
    }
    
    public void gerar_PS(){ 
        synchronized(this) { 
                
            Imprimir SetConf = new Imprimir( PrinterJob, htmlPanePrincipal, "z" + x );  
            //TestPreview TestPreview = new TestPreview(htmlPanePrincipal);
        }  
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
