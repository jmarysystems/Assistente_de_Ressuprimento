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
import javax.swing.JTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author pc
 */
public class ExportarExcelExistente {   
    
    JTable tablaD;
    String saida;
    String modelo;
    
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
    
    
    private void abrirExcel() throws IOException{
        String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + saida+".xlsx";     
        java.awt.Desktop.getDesktop().open( new File( aplicativo ) ); 

    }
    
}
