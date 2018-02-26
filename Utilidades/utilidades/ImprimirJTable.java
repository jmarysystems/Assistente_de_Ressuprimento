package utilidades;

import 		java.awt.Color;
import		java.awt.Graphics;
import 		java.awt.Graphics2D;
import 		java.awt.print.PageFormat;
import 		java.awt.print.Printable;
import 		java.awt.print.PrinterException;
import 		java.awt.print.PrinterJob;
import 		javax.swing.JOptionPane;
import		javax.swing.JTable;
/**
 * Classe que imprime uma JTable
 @author	Guilherme I. Keller
 TODO - Uma melhor explicacao & comentarios.
 */
public
class		ImprimirJTable
implements	Printable{
        private		JTable
        tabela;
        private		PrinterJob
        printerJob;
        private		Graphics
        graphics;
        private		Graphics2D
        graphics2d;
        /**
         * Metodo construtor da classe
         @param		tabela
         		Uma JTable a ser impressa
         */
        public		ImprimirJTable(JTable	tabela){
                this.tabela	=	tabela;
                printerJob	=	printerJob.getPrinterJob();
                printerJob.setPrintable(this);
                printerJob.setJobName("Imprimindo uma JTable");
                if	(printerJob.printDialog())
                {
	                try
	                {
	                        printerJob.print();
	                }
	                catch	(PrinterException ex)
	                {
	                        JOptionPane.showMessageDialog(	null,
	                                        				"Ocorreu um erro durante a impressão. \n"+ex.toString(),
	                                        				"Erro do Sistema",
	                                        				JOptionPane.ERROR_MESSAGE
	                                        			);
	                }
                }
        }
        /**
         * Metodo responsavel pela impressao da JTable
         */
        public 
        int 		print(	Graphics g,
                        		PageFormat pageFormat,
                        		int pageIndex
                        	)
        				throws	PrinterException{
                	graphics2d		=	(Graphics2D)	g;
                	graphics2d.setColor(Color.black);    
                        
            	int 
             	fontHeight		=	graphics2d.getFontMetrics().getHeight();
      	      	int 
             	fontDesent		=	graphics2d.getFontMetrics().getDescent();
             	// leave room for page number
             	double 
             	pageHeight		=	pageFormat.getImageableHeight()-fontHeight;
             	double
             	pageWidth		=	pageFormat.getImageableWidth();
             	double
             	tableWidth		=	(double)	tabela.getColumnModel().getTotalColumnWidth();
             	double
             	scale			=	1;
             	if (tableWidth	>=	pageWidth){
        			scale 	=  pageWidth / tableWidth;
	        	}
             	double 
             	headerHeightOnPage	=	tabela.getTableHeader().getHeight()*scale;
             	double 
             	tableWidthOnPage		=	tableWidth*scale;
             	double
             	oneRowHeight		=	( (tabela.getRowHeight()*2)+ tabela.getRowMargin())*scale;
             	int
             	numRowsOnAPage		=	(int)	((pageHeight-headerHeightOnPage)/oneRowHeight);
             	double 
             	pageHeightForTable	=	oneRowHeight*numRowsOnAPage;
             	int 
             	totalNumPages		=	(int)	Math.ceil( ( (double)tabela.getRowCount() ) / numRowsOnAPage);
             	if	(pageIndex	>=	totalNumPages){
             	        return		NO_SUCH_PAGE;	//No more pages
             	}
             	graphics2d.translate(	pageFormat.getImageableX(),
             	                		pageFormat.getImageableY()
             	                		);
             	graphics2d.translate(	0f,
             	                		headerHeightOnPage
             	                		);
             	graphics2d.translate(	0f,
             	                		-pageIndex*pageHeightForTable
             	                		);
             	// If this piece of the table is smaller than the size available,
             	// clip to the appropriate bounds.
             	if (pageIndex + 1 == totalNumPages){
             	        int
             	        lastRowPrinted		=	numRowsOnAPage * pageIndex;
             	        int 
             	        numRowsLeft		=	tabela.getRowCount() - lastRowPrinted;
             	        graphics2d.setClip(	0,
	             	                        (int)	(pageHeightForTable * pageIndex),
	             	                        (int)	Math.ceil(tableWidthOnPage),
	             	                        (int)	Math.ceil(oneRowHeight * numRowsLeft)
	             	                        );
             	}
             	// else clip to the entire area available.
             	else{    
             	        graphics2d.setClip(	0,
             	                        	(int)	(pageHeightForTable*pageIndex), 
             	                        	(int)	Math.ceil(tableWidthOnPage),
             	                        	(int)	Math.ceil(pageHeightForTable)
             	                        	);        
             	}
             	graphics2d.scale(	scale,
             	                	scale
             	                	);
             	tabela.paint(graphics2d);
             	graphics2d.scale(	1 / scale,
             	                	1 / scale
             	                	);
             	graphics2d.translate(	0f,
             	                		pageIndex * pageHeightForTable
             	                		);
             	graphics2d.translate(	0f,
             	                		-headerHeightOnPage
             	                		);
             	graphics2d.setClip(	0,
             	                		0,
             	                		(int)	Math.ceil(tableWidthOnPage), 
             	                		(int)	Math.ceil(headerHeightOnPage)
             	                		);
             	graphics2d.scale(	scale,
             	                	scale
             	                	);
             	tabela.getTableHeader().paint(graphics2d);	// paint header at top
             	return Printable.PAGE_EXISTS;		// continues printing
        }
}

