/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vizualizar_impressao;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLEditorKit;
import print.Imprimir;
import utilidades.Arquivo_Ou_Pasta;
import utilidades.JOPM;

/**
 *
 * @author Usuario
 */
public class PrintHtml {
    
    static String html = "";   
    
    String arquivo   = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems_Print" + System.getProperty("file.separator");
    String arquivoPS = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems_PrintPS" + System.getProperty("file.separator");
    int x = 1;
    
    public PrintHtml( String html2 ) { 
        html = html2; 
        
        criar();
        iniciar();
    }
    
    private void criar() { /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );  

        Arquivo_Ou_Pasta.criarPasta( System.getProperty("user.home"), "JMarySystems_Print" );
        Arquivo_Ou_Pasta.criarPasta( System.getProperty("user.home"), "JMarySystems_PrintPS" );
        
    } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } /*} }.start();*/ }
    
    // excluindoArquivos 
    private void iniciar(){ new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
           
            if ( !new File( arquivo+ "z" + x + ".html" ).exists() ) { x++;
                Arquivo_Ou_Pasta.criar_Arquivo_Iso( arquivo+ "z" + x + ".html", html );
                
                printPage(); 
            }
            else{ JOPM JOPM = new JOPM( 2, "iniciar(), \n"+ "O Arquivo Já existe"+ arquivo+ "z" + x + ".html" + "\n", this.getClass().getSimpleName() ); }        
                                
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "excluindoArquivos, \n"+ e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();         
    }
    
    private void printPage() { try {  Runnable printTask = new Runnable() { @Override public void run() { 
                
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            /*********/PrinterJob   pJob = PrinterJob.getPrinterJob();
            /*********/             pJob.setJobName( "Bueno"  ); 
            /*********/             pJob.setCopies( 1 );
            /*********/try { pJob.setPrintService( printService ); } catch (PrinterException ex) {}
            
            //
            JEditorPane htmlPanePrincipal = new JEditorPane();
            htmlPanePrincipal.setEditorKit(new HTMLEditorKit());            
            htmlPanePrincipal.setContentType("text/html;charset=UTF-8");

            
            try { htmlPanePrincipal.setPage( new File( arquivo+ "z" + x + ".html" ).toURI().toURL() ); } catch (Exception e) { e.printStackTrace(); }
            
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                aset.add( Chromaticity.COLOR ); aset.add( MediaSizeName.ISO_A4 ); aset.add( Sides.ONE_SIDED ); aset.add( OrientationRequested.LANDSCAPE ); 
                /*********/PageFormat pFormat = pJob.getPageFormat( aset );                
                try {                    
                    /*********/pJob.defaultPage( pFormat );
                    Imprimir setConf = new Imprimir ( pJob, htmlPanePrincipal, "Bueno" );  
                    //htmlPanePrincipal.print( null, null, false, printService, aset, false);
                } catch (Exception e) { e.printStackTrace(); }
            }
        };
        new Thread(printTask).start(); } catch ( Exception e) { e.printStackTrace(); }
    }
    
}
