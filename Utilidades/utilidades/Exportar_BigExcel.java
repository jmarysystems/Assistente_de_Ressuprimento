/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
public class Exportar_BigExcel {
    JTable tablaD;
    String saida;
    String modelo;
        
    private static final String XML_ENCODING = "UTF-8";
    
    public void exportar(JTable tablaD2, String saida2, String modelo2) throws Exception { tablaD = tablaD2; saida = saida2; modelo = modelo2;
    
        Exportando Exportando = new Exportando("PROCURANDO DADOS...");
        Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
    
        String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + saida +  ".xlsx";     
        Arquivo_Ou_Pasta.deletar(new File(aplicativo));    
        
        String aplicativo2 = System.getProperty("user.dir") + System.getProperty("file.separator") + "template" +  ".xlsx";     
        Arquivo_Ou_Pasta.deletar(new File(aplicativo2));    
    
        //String excelTemporario = "MODELO_RELATORIO_DE_ACOMPANHAMENTO_DO_ESTOQUE_LOJA_CDS.xlsx";
        String excelModelo = "template.xlsx";

        // Etapa 1. Criar um arquivo de modelo. As folhas de configuração e objetos em nível de pasta de trabalho, como
        // estilos de célula, formatos de número, etc.
        /*
        InputStream fs = new FileInputStream(modelo+".xlsx");       
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sheet = wb.getSheetAt(0);  
        */
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Base");
        
                                
        Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
///////////////////////////////////////////////////////////////////////////////////////////
        XSSFDataFormat fmt = wb.createDataFormat();
        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style1.setDataFormat(fmt.getFormat("0.0%"));
        styles.put("percent", style1);
        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style2.setDataFormat(fmt.getFormat("0.0X"));
        styles.put("coeff", style2);
        XSSFCellStyle style3 = wb.createCellStyle();
        style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style3.setDataFormat(fmt.getFormat("$#,##0.00"));
        styles.put("currency", style3);
        XSSFCellStyle style4 = wb.createCellStyle();
        style4.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style4.setDataFormat(fmt.getFormat("mmm dd"));
        styles.put("date", style4);
        XSSFCellStyle style5 = wb.createCellStyle();
        XSSFFont headerFont = wb.createFont();
        headerFont.setBold(true);
        style5.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style5.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style5.setFont(headerFont);
        styles.put("header", style5);
        
///////////////////////////////////////////////////////////////////////////////////////////
                
        // nome da entrada zip que contém os dados da folha, por exemplo /xl/worksheets/sheet1.xml
        String sheetRef = sheet.getPackagePart().getPartName().getName();
        // salvar o modelo
        FileOutputStream os = new FileOutputStream(excelModelo);
        wb.write(os);
        os.close();
        
//////////////////////////////////////////////////////////////////////////////////////////        
        // Etapa 2. Gerar arquivo XML.
        File xml_Com_Os_Dados = File.createTempFile("sheet", ".xml");
        Writer fw = new OutputStreamWriter(new FileOutputStream(xml_Com_Os_Dados) ); //XML_ENCODING);
        ///////
        //generate(fw, styles);
        /////////////////////////////////////////////////////////////////////////////////
        Random rnd = new Random();
        Calendar calendar = Calendar.getInstance();
        SpreadsheetWriter sw = new SpreadsheetWriter(fw);
        sw.beginSheet();
                
        ////////////////////////////////////////////////////////////////////////////////
        int numLinhas=tablaD.getRowCount(), numColunas=tablaD.getColumnCount(); 
        Exportando.pbg.setMaximum( numLinhas );
        ////////////////////////////////////////////////////////////////////////////////
        //insert header row
        sw.insertRow(0);
        ////////////////////////////////////////////////////////////////////////////////
        /*
        sw.createCell(0, "Title",    styles.get("header").getIndex() );
        sw.createCell(1, "% Change", styles.get("header").getIndex() );
        sw.createCell(2, "Ratio",    styles.get("header").getIndex() );
        sw.createCell(3, "Expenses", styles.get("header").getIndex() );
        sw.createCell(4, "Date",     styles.get("header").getIndex() );
        */
        for (int coluna = 0; coluna < numColunas; coluna++) { 
            
            sw.createCell( coluna, tablaD.getColumnName(coluna),    styles.get("header").getIndex() );            
        }
        ////////////////////////////////////////////////////////////////////////////////
        sw.endRow();
        //write data rows
        for (int linha = 0; linha < numLinhas; linha++) { int linhaParaescrever = linha+1; Exportando.pbg.setValue( linha );   
        
            sw.insertRow(linhaParaescrever);
            /*
            sw.createCell(0, "Hello, " + i + "!");
            sw.createCell(1, (double)rnd.nextInt(100)/100, styles.get("percent").getIndex());
            sw.createCell(2, (double)rnd.nextInt(10)/10, styles.get("coeff").getIndex());
            sw.createCell(3, rnd.nextInt(10000), styles.get("currency").getIndex());
            sw.createCell(4, calendar, styles.get("date").getIndex());
            */
            ////////////////////////////////////////////////////////////////////////////////
            for (int coluna = 0; coluna < numColunas; coluna++) { 
                
                sw.createCell( coluna, String.valueOf(tablaD.getValueAt(linha, coluna)) );
            }
            ////////////////////////////////////////////////////////////////////////////////
            sw.endRow();
            calendar.roll(Calendar.DAY_OF_YEAR, 1);
        }
        sw.endSheet();
        ////////////////////////////////////////////////////////////////////////////////
        //////
        fw.close();
/////////////////////////////////////////////////////////////////////////////////////////  

        // Passo 3. Substitua a entrada do modelo pelos dados gerados
        FileOutputStream out = new FileOutputStream(saida+".xlsx");
        substitute(new File(excelModelo), xml_Com_Os_Dados, sheetRef.substring(1), out);
        out.close();
        
        ////////////////////////////////////////////////////////////////////////
        try{ 
            Exportando.fechar();  
            Thread.sleep( 10 );
            abrirExcel(); 
        } catch(Exception e) {}
        ///////////////////////////////////////////////////////////////////////
    }

    /**
     *
     * @param excelModelo o arquivo de modelo
     * @param xml_Com_Os_Dados o arquivo XML com os dados da planilha
     * @param entrada o nome da folha entrada para substituir, por exemplo xl / worksheets / sheet1.xml
     * @param out fluxo para escrever o resultado para
     */
	private static void substitute(File excelModelo, File xml_Com_Os_Dados, String entry, OutputStream out) throws IOException {
        ZipFile zip = new ZipFile(excelModelo);
        ZipOutputStream zos = new ZipOutputStream(out);
        @SuppressWarnings("unchecked")
        Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
        while (en.hasMoreElements()) {
            ZipEntry ze = en.nextElement();
            if(!ze.getName().equals(entry)){
                zos.putNextEntry(new ZipEntry(ze.getName()));
                InputStream is = zip.getInputStream(ze);
                copyStream(is, zos);
                is.close();
            }
        }
        zos.putNextEntry(new ZipEntry(entry));
        InputStream is = new FileInputStream(xml_Com_Os_Dados);
        copyStream(is, zos);
        is.close();
        zos.close();
    }
    private static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] chunk = new byte[1024];
        int count;
        while ((count = in.read(chunk)) >=0 ) {
          out.write(chunk,0,count);
        }
    }
    /**
     * Writes spreadsheet data in a Writer.
     * (YK: in future it may evolve in a full-featured API for streaming data in Excel)
     */
    public static class SpreadsheetWriter {
        private final Writer _out;
        private int _rownum;
        public SpreadsheetWriter(Writer out){
            _out = out;
        }
        public void beginSheet() throws IOException {
            _out.write("<?xml version=\"1.0\" encoding=\""+XML_ENCODING+"\"?>" +
                    "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">" );
            _out.write("<sheetData>\n");
        }
        public void endSheet() throws IOException {
            _out.write("</sheetData>");
            _out.write("</worksheet>");
        }
        /**
         * Insert a new row
         *
         * @param rownum 0-based row number
         */
        public void insertRow(int rownum) throws IOException {
            _out.write("<row r=\""+(rownum+1)+"\">\n");
            this._rownum = rownum;
        }
        /**
         * Insert row end marker
         */
        public void endRow() throws IOException {
            _out.write("</row>\n");
        }
        public void createCell(int columnIndex, String value, int styleIndex) throws IOException {
            String ref = new CellReference(_rownum, columnIndex).formatAsString();
            _out.write("<c r=\""+ref+"\" t=\"inlineStr\"");
            if(styleIndex != -1) _out.write(" s=\""+styleIndex+"\"");
            _out.write(">");
            _out.write("<is><t>"+value+"</t></is>");
            _out.write("</c>");
        }
        public void createCell(int columnIndex, String value) throws IOException {
            createCell(columnIndex, value, -1);
        }
        public void createCell(int columnIndex, double value, int styleIndex) throws IOException {
            String ref = new CellReference(_rownum, columnIndex).formatAsString();
            _out.write("<c r=\""+ref+"\" t=\"n\"");
            if(styleIndex != -1) _out.write(" s=\""+styleIndex+"\"");
            _out.write(">");
            _out.write("<v>"+value+"</v>");
            _out.write("</c>");
        }
        public void createCell(int columnIndex, double value) throws IOException {
            createCell(columnIndex, value, -1);
        }
        public void createCell(int columnIndex, Calendar value, int styleIndex) throws IOException {
            createCell(columnIndex, DateUtil.getExcelDate(value, false), styleIndex);
        }
    }
    
    /*
    private boolean cadastrarBoo = false;   
    public void exportar(JTable tablaD2, String saida2, String modelo2){ tablaD = tablaD2; saida = saida2; modelo = modelo2;
        new Thread() { @Override public void run() {   
            
                while ( cadastrarBoo == false ) {   
                        cadastrarBoo = true;
                        try { 
                            
                            String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + saida +  ".xlsx";     
                            Arquivo_Ou_Pasta.deletar(new File(aplicativo));
                            
                            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                                                               
                            FileInputStream fileIn = null;
                            FileOutputStream fileOut = null;                            
                            
                            try{
                                
                                fileIn = new FileInputStream(modelo);
                                XSSFWorkbook wb = new XSSFWorkbook(fileIn); 
                                XSSFSheet aba = wb.getSheetAt(0); 

                                int numFila=tablaD.getRowCount(), numColumna=tablaD.getColumnCount();  
                                
                                Exportando.pbg.setMaximum( numFila );
                                                                                    
                                XSSFCell cell;  
                                for (int i = -1; i < numFila; i++) { 
                                    int linhaParaescrever = i+2;
                                    XSSFRow linha = aba.getRow(linhaParaescrever);
                                    //XSSFRow linha = aba.createRow(i+1);
                                    
                                    for (int j = 0; j < numColumna; j++) { 
                                        
                                        cell = linha.getCell(j); 
                                        if (cell == null) cell = linha.createCell(j);
                                        
                                        try{ cell.setCellType(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){}
                                        try{ cell.setCellValue(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){}
                                        
                                        if(i==-1){
                                            cell.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                                        }else{
                                            cell.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
                                        }
                                    }
                                    Exportando.pbg.setValue( i );                                
                                }
                                                                
                                wb.setForceFormulaRecalculation(true);
                                fileOut = new FileOutputStream(saida+".xlsx");
                                wb.write(fileOut);
                                
                                try{
                                    fileOut.close(); 
                                    fileIn.close(); 
                                } catch(Exception e) {}
                                
                                abrirExcel();
                            } catch(Exception e) {}
                                                                    
                            Exportando.fechar();                   
                        }catch( Exception e ){ e.printStackTrace(); }
                    }
            } }.start();
    }
    */
    
    private void abrirExcel() throws IOException{
        String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + saida+".xlsx";     
        java.awt.Desktop.getDesktop().open( new File( aplicativo ) ); 

    }
    
}
