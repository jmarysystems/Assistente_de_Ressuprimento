package print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;
import javax.swing.JEditorPane;
import utilidades.Arquivo_Ou_Pasta;
import utilidades.JOPM;

public class Imprimir {
    
    String      NomeDocRec = "";
    JEditorPane JEditorPane;
    PrinterJob  pJob;

    public Imprimir( PrinterJob pJob2, JEditorPane JEditorPane2, String nomeDoc ) { 
        JEditorPane = JEditorPane2; pJob = pJob2; NomeDocRec = nomeDoc;
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
    
        synchronized(this) {
            
            PropriedadesDaImpressão();
        }
        
        synchronized(this) {
            
            imprimirComPropriedadesEConfigurações(); 
        }
        
    } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "SetandoConfiguracoes( final PrinterJob pJob, final JEditorPane JEditorPane2, final String nomeDoc ), \n"+ e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); }
    
    protected void PropriedadesDaImpressão() {
        try{               
            numeroDeCopias =  1;        
            lados = Sides.ONE_SIDED;
            cor = Chromaticity.COLOR;
            nomeDoUsuario = "JMary - Systems, jmarysystems@gmail.com";
            papel = MediaSizeName.ISO_A4;                                 
            //impressora = "Doro PDF Writer";        
            perguntarQualImpressora = false;
            orientacaoDoPapel = OrientationRequested.LANDSCAPE;             
        
            tamanhoPapelHorizontal = 842;
            tamanhoPapelVertical = 595;        
            margemSuperior = 20;
            margemEsquerda = 10;
            tmPapelMenosMargemDireita = tamanhoPapelHorizontal - 5; 
            tmPapelMenosMargemInferior = tamanhoPapelVertical - 20;
       
            //jeditorPane.setContentType( "text/html;charset=UTF-8" );
            //jeditorPane.setEditable(false);
            //int tmHorizontal = (int) tmPapelMenosMargemDireita; int tmVertical = (int) tmPapelMenosMargemInferior;
            //jeditorPane.setSize( tmHorizontal, tmVertical );      
            //url = jeditorPane.getPage();            
        } catch( Exception e ) { }
    }
        
    public void imprimirComPropriedadesEConfigurações() { 
        /*
        javax.print.attribute.HashPrintRequestAttributeSet aset = new javax.print.attribute.HashPrintRequestAttributeSet();
        //aset.add(new Destination(new java.net.URI("file:e:/temp/out.ps")));
        //aset.add(new MediaPrintableArea(xmargin, ymargin, w - 2*xmargin, h - 2*ymargin, MediaPrintableArea.INCH));
        aset.add( MediaSizeName.ISO_A4 ); 
        aset.add( OrientationRequested.LANDSCAPE ); 
        aset.add( Chromaticity.COLOR ); 
        aset.add( Sides.ONE_SIDED ); 
        aset.add( PrintQuality.NORMAL ); 
    
        Printable pTable = new Print( tp, PrinterJob, aset ); 
    
        Paper papertamanhoDoPapelEMargens;
        double margemSuperior, margemEsquerda, tmPapelMenosMargemDireita, tmPapelMenosMargemInferior, tamanhoPapelHorizontal, tamanhoPapelVertical;
        tamanhoPapelHorizontal     = 842;
        tamanhoPapelVertical       = 595;        
        margemSuperior             = 25;
        margemEsquerda             = 10;
        tmPapelMenosMargemDireita  = tamanhoPapelHorizontal - 5; 
        tmPapelMenosMargemInferior = tamanhoPapelVertical   - 40;
    
        PageFormat pFormat = PrinterJob.getPageFormat( aset ); // pJob.pageDialog(aset);  
        papertamanhoDoPapelEMargens = new Paper(); papertamanhoDoPapelEMargens.setSize( tamanhoPapelHorizontal, tamanhoPapelVertical ); 
        papertamanhoDoPapelEMargens.setImageableArea( margemEsquerda, margemSuperior, tmPapelMenosMargemDireita, tmPapelMenosMargemInferior ); pFormat.setPaper( papertamanhoDoPapelEMargens ); 
        PrinterJob.defaultPage( pFormat );
        */
        
        pJob.setJobName( NomeDocRec  ); 
        pJob.setCopies( numeroDeCopias );
        
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet(); 
        aset.add( papel ); aset.add( orientacaoDoPapel ); aset.add( cor ); aset.add( lados ); 
                
        PageFormat pFormat = pJob.getPageFormat( aset ); // pJob.pageDialog(aset);  
        papertamanhoDoPapelEMargens = new Paper(); papertamanhoDoPapelEMargens.setSize( tamanhoPapelHorizontal, tamanhoPapelVertical ); 
        papertamanhoDoPapelEMargens.setImageableArea( margemEsquerda, margemSuperior, tmPapelMenosMargemDireita, tmPapelMenosMargemInferior ); pFormat.setPaper( papertamanhoDoPapelEMargens ); 
        pJob.defaultPage( pFormat ); 
        
        synchronized(this) {
            
            synchronized(this) {
                Printable pTable  = new Print( JEditorPane, pJob, aset );    
                int numberOfPages = 0; try{ numberOfPages = getNumberOfPages( pTable, pFormat ); } catch(Exception ex){}
                System.out.println( "NÚMERO DE PÁGINAS: " + numberOfPages );
                
                pJob.setPrintable( pTable, pFormat );
            }
            try {  
            
                synchronized(this) {

                    pJob.print( aset );             
                }
            
            } catch (Exception e) { e.printStackTrace(); JOPM JOPM = new JOPM( 2, "imprimirComPropriedadesEConfigurações( PrinterJob pJob, String NomeDoc ), \n"+ e.getMessage() + "\n", this.getClass().getSimpleName() );}
        
            synchronized(this) {
                
                String arq   = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems_Print" + System.getProperty("file.separator")   + NomeDocRec + ".html";
                Arquivo_Ou_Pasta.deletar( new File( arq   ) );                    
            }
        }
    } 
    
    public int getNumberOfPages( Printable pr, PageFormat p ) {
        int n=0; 
        try {
            
            Graphics graphics = new java.awt.image.BufferedImage(2,2,java.awt.image.BufferedImage.TYPE_INT_RGB).getGraphics();  
            while(pr.print( graphics, p, n ) == pr.PAGE_EXISTS){
                n++; 
            } 
        } catch(Exception ex) {ex.printStackTrace();}
        return n;
    }

    public static Paper papertamanhoDoPapelEMargens;
    public static double margemSuperior, margemEsquerda, tmPapelMenosMargemDireita, tmPapelMenosMargemInferior, tamanhoPapelHorizontal, tamanhoPapelVertical;
    // PropriedadesDaImpressão
    //public static URL url;                                
    public MediaSizeName papel;
    public int numeroDeCopias;
    public static String impressora = "Doro PDF Writer";
    public Sides lados;
    public Chromaticity cor;
    public String nomeDoUsuario;
    public static boolean perguntarQualImpressora;
    public OrientationRequested orientacaoDoPapel; // PropriedadesDaImpressão  
  
}