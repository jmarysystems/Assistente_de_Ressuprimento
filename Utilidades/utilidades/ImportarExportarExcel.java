/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author pc
 */
public class ImportarExportarExcel {
    Workbook wb;
            
    public boolean Importar(File file, JTable tbPesquisa){
        Exportando Exportando = new Exportando("Importando");
        Exportando.setVisible(true);
        
        boolean retorno = false;
        
        DefaultTableModel modeloT = new DefaultTableModel();
        tbPesquisa.setModel(modeloT);
        
        try {
            String name = file.toString();
            int pos = name.lastIndexOf('.');
            String ext = name.substring(pos + 1);
            
            FileInputStream ExcelFileToRead = new FileInputStream(file);
            Workbook obj = null;
            
            if (ext.equals("xlsx")) {
                
                try {
                    
                    obj = new XSSFWorkbook( ExcelFileToRead );
                    System.out.println("Arquivo Recebido: " + "xlsx");
                    
                } catch (IOException ex) { System.out.println("Arquivo Recebido: " + "xlsx"); ex.printStackTrace(); }
            } else if (ext.equals("xls")) {
                try {
                    
                    obj = new HSSFWorkbook( ExcelFileToRead );
                    System.out.println("Arquivo Recebido: " + "xls");
                    
                } catch (IOException ex) { System.out.println("Arquivo Recebido: " + "xls"); ex.printStackTrace(); }
            }
            else{
                System.out.println("Arquivo Recebido, não é compatível.");
            }
            
            //Sheet worksheet = obj.getSheet("Plan1");
            Sheet aba = obj.getSheetAt(0);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( aba.getLastRowNum() );
            
            Row      linha = null;
            Cell     celula;
            Object[] listaCeluladasdaLinha = null;
            
            for( int L_i=0; L_i <= aba.getLastRowNum(); L_i++ ){ Exportando.pbg.setValue( L_i );  
                
                
                try{ 
                    linha                 = aba.getRow(L_i); 
                    listaCeluladasdaLinha = new Object[ linha.getLastCellNum() ]; 
                } catch( Exception e ){}
                    
            if( listaCeluladasdaLinha != null && linha != null ){
                    
                String strLinha = "";
                
                for( int C_i=0; C_i < linha.getLastCellNum(); C_i++ ){ 
                                        
                    celula = linha.getCell(C_i);
                    
                    if(L_i==0){
                        System.out.println( "Linha - " + L_i + " - Coluna - " + C_i + " - " + celula.getStringCellValue() );
                        //strLinha += "| L-" + L_i + " - C-" + C_i + " - " + celula.getStringCellValue();
                        
                        modeloT.addColumn( celula.getStringCellValue() );  
                                                
                    }else{
                        if(celula!=null){                            
                            switch(celula.getCellType()){
                                case Cell.CELL_TYPE_NUMERIC:
                                    try{ listaCeluladasdaLinha[C_i]= celula.getNumericCellValue(); } catch( Exception e ){}
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    try{ listaCeluladasdaLinha[C_i]= celula.getStringCellValue();  } catch( Exception e ){}
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    try{ listaCeluladasdaLinha[C_i]= celula.getBooleanCellValue(); } catch( Exception e ){}
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    try{ listaCeluladasdaLinha[C_i]= celula.getCellFormula();      } catch( Exception e ){}
                                    break;    
                                default:
                                    try{ listaCeluladasdaLinha[C_i]= celula.getDateCellValue();    } catch( Exception e ){}
                                    break;
                            }
                        }
                    }
                    
                    tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(150);
                }
                
                if( L_i!=0 )modeloT.addRow( listaCeluladasdaLinha );
                
                }
                //System.out.println( strLinha );
            }  
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }
        
        Exportando.fechar();
        
        return retorno;
    }
    
    File f;
    public boolean Exportar(File f2, JTable tablaD){ f = f2;
        Exportando Exportando = new Exportando("Exportando");
        Exportando.setVisible(true);
                
        boolean retorno = false;
        
        int numFila=tablaD.getRowCount(), numColumna=tablaD.getColumnCount();   
        Exportando.pbg.setMinimum(0);
        Exportando.pbg.setMaximum(numFila);
        
        if(f.getName().endsWith("xls")){
            wb = new HSSFWorkbook();
        }else{
            wb = new XSSFWorkbook();
        }
        Sheet aba = wb.createSheet("Pruebita");
        
        try {
            for (int i = -1; i < numFila; i++) { 
                Row linha = aba.createRow(i+1);
                
                for (int j = 0; j < numColumna; j++) { 
                    Cell celula = linha.createCell(j);
                    if(i==-1){
                        celula.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                    }else{
                        celula.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
                    }
     
                }
                
                Exportando.pbg.setValue( i );                                
            }
            
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
                OutputStream outputStream = new FileOutputStream( f );
                
                wb.write( outputStream );
                outputStream.flush(); outputStream.close();
            } catch( Exception e ){ System.out.println("Exportar - "); e.printStackTrace(); } } }.start();      
            
            Exportando.fechar();
                    
            retorno = true;
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }
        
        return retorno;
    }
    
}
