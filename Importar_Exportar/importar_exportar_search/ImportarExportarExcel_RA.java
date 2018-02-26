/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import utilidades.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author pc
 */
public class ImportarExportarExcel_RA {
    Workbook wb;
    
    JTable tbPesquisa;
            
    public boolean Importar(File file, JTable tbPesquisa2){
        tbPesquisa = tbPesquisa2;
        Exportando Exportando = new Exportando("Importando");
        Exportando.setVisible(true);
        
        boolean retorno = false;
        
        DefaultTableModel modeloT = new DefaultTableModel();
        tbPesquisa.setModel(modeloT);
        
        tbPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tbPesquisa.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if ( !e.getValueIsAdjusting() ){
                        calculoFlutuante();
                    }
                }
        });
        
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
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
                        
                        String strCell = celula.getStringCellValue();
                        
                        System.out.println( "Linha - " + L_i + " - Coluna - " + C_i + " - STRCELL: " + strCell );//celula.getStringCellValue() );
                        //strLinha += "| L-" + L_i + " - C-" + C_i + " - " + celula.getStringCellValue();
                        
                        if( strCell.trim().equals("Tipo de MRP") ){
                            
                            strCell = "MRP";
                        }
                        else if( strCell.trim().equals("Fator conv.UM E/S") ){
                            
                            strCell = "UM E/S";
                        }
                        else if( strCell.trim().equals("UM pedido") ){
                            
                            strCell = "UM PED";
                        }
                        else if( strCell.trim().equals("Fator UM Pedido") ){
                            
                            strCell = "F. UM PED";
                        }
                        else if( strCell.trim().equals("Utilização livre") ){
                            
                            strCell = "EST_LOJA";
                        }
                        else if( strCell.trim().equals("Dias Estoque Atual") ){
                            
                            strCell = "DDE_";
                        }
                        else if( strCell.trim().equals("Venda Média 4 Sem.") ){
                            
                            strCell = "V_MÉDIA 4 SEM";
                        }
                        else if( strCell.trim().equals("Venda Média 12 Sem.") ){
                            
                            strCell = "V_MÉDIA 12 SEM";
                        }
                        else if( strCell.trim().equals("Estoque seg.mínimo") ){
                            
                            strCell = "EST. MÍNIMO";
                        }
                        
                        modeloT.addColumn( strCell );  
                                                
                    }else{
                        if(celula!=null){
                            switch(celula.getCellType()){
                                case Cell.CELL_TYPE_NUMERIC:
                                    try{ listaCeluladasdaLinha[C_i]= celula.getNumericCellValue(); } catch( Exception e ){}
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    try{ 
                                        listaCeluladasdaLinha[C_i]= celula.getStringCellValue();  
                                        
                                        String strn = tbPesquisa.getColumnName(C_i).trim();
                                        switch( strn ){
                                            
                                            case "EST_LOJA": 
                                                try{
                                                    String str = linha.getCell(C_i).getStringCellValue();
                                                    String ww = str.replace(".", "");
                                                    String ww2 = ww.replace(",", "."); 
                                                
                                                    listaCeluladasdaLinha[C_i] = ww2;
                                                } catch( Exception e ){}
                                            break; 
                                        }
                                        
                                    } catch( Exception e ){}
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
                    
                    String strn = tbPesquisa.getColumnName(C_i).trim();
                    switch( strn ){
                        
                        case "Loja":  
                            try{ tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(40); } catch( Exception e ){}
                            break;
                        case "Mercadoria":  
                            try{ tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(98); } catch( Exception e ){}
                            break;    
                        case "Texto breve material":  
                            try{ tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(290); } catch( Exception e ){}
                            break; 
                        case "MRP":  
                            try{ tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(40); } catch( Exception e ){}
                            break;  
                        case "UM E/S":  
                            try{ tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(55); } catch( Exception e ){}
                            break;
                        case "UM PED":  
                            try{ tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(55); } catch( Exception e ){}
                            break; 
                        case "F. UM PED":  
                            try{ tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(60); } catch( Exception e ){}
                            break;
                        case "EST_LOJA":  
                            try{ 
                                tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(85); 
                                tbPesquisa.getColumnModel().getColumn(C_i).setResizable(false);
                                tbPesquisa.getColumnModel().getColumn(C_i).setCellRenderer( rendererCentro );
                                
                            } catch( Exception e ){}
                            break;   
                        case "DDE_":  
                            try{ 
                                tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(55); 
                                tbPesquisa.getColumnModel().getColumn(C_i).setResizable(false);
                                tbPesquisa.getColumnModel().getColumn(C_i).setCellRenderer( rendererCentro );
                            } catch( Exception e ){}
                            break; 
                        case "Sugerido":  
                            try{ 
                                tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(65); 
                                tbPesquisa.getColumnModel().getColumn(C_i).setResizable(false);
                                tbPesquisa.getColumnModel().getColumn(C_i).setCellRenderer( rendererCentro );
                            } catch( Exception e ){}
                            break; 
                        case "Requisições compra":  
                            try{ 
                                tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(110); 
                                tbPesquisa.getColumnModel().getColumn(C_i).setResizable(false);
                                tbPesquisa.getColumnModel().getColumn(C_i).setCellRenderer( rendererDireita );
                            } catch( Exception e ){}
                            break;  
                        case "V_MÉDIA 4 SEM":  
                            try{ 
                                tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(90); 
                                tbPesquisa.getColumnModel().getColumn(C_i).setResizable(false);
                                tbPesquisa.getColumnModel().getColumn(C_i).setCellRenderer( rendererDireita );
                            } catch( Exception e ){}
                            break; 
                        case "V_MÉDIA 12 SEM":  
                            try{ 
                                tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(90); 
                                tbPesquisa.getColumnModel().getColumn(C_i).setResizable(false);
                                tbPesquisa.getColumnModel().getColumn(C_i).setCellRenderer( rendererDireita );
                            } catch( Exception e ){}
                            break; 
                        case "EST. MÍNIMO":  
                            try{ 
                                tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(90); 
                                tbPesquisa.getColumnModel().getColumn(C_i).setResizable(false);
                                tbPesquisa.getColumnModel().getColumn(C_i).setCellRenderer( rendererDireita );
                            } catch( Exception e ){}
                            break;     
                        default:
                            try{ 
                                tbPesquisa.getColumnModel().getColumn(C_i).setPreferredWidth(80);  
                                tbPesquisa.getColumnModel().getColumn(C_i).setCellRenderer( rendererDireita );
                            } catch( Exception e ){}
                            break;    
                    }
                    
                }
                
                if( L_i!=0 )modeloT.addRow( listaCeluladasdaLinha );
                
                }
                //System.out.println( strLinha );
            }  
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }
        
        Exportando.fechar();
        
        //sinalizar_promocionais( tbPesquisa );
        
        return retorno;
    }
    
    private void calculoFlutuante() {                                              
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
        System.out.println( " +vcount + " );
                                  
            if( tbPesquisa.getModel().getRowCount() > 0 ){           
            for( int L_i=0; L_i < tbPesquisa.getRowCount(); L_i++ ){
                
                double est_loja        = 0;
                double sug_loja        = 0;
                double v12Sem   = 0;
                double disponibilidade = 0;

                int L_iDDE = -1;
                int C_iDDE = -1;
                int count  = 0;
                
                int vcount = 0;
                for( int C_i=0; C_i < tbPesquisa.getColumnCount(); C_i++ ){ count++; 
                
                    vcount = count + 1;
                    //System.out.println( "vcount " +vcount + "tbPesquisa.getColumnCount() - " + tbPesquisa.getColumnCount() );
                    
                    String str = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) );
                    
                    String strn = tbPesquisa.getColumnName(C_i);
                                        
                    if( strn.trim().equals("EST_LOJA") ){
                        
                        String est_loja_str = "0"; try { est_loja_str = String.valueOf( tbPesquisa.getValueAt( L_i, C_i ) ); } catch( Exception e ){}
                        est_loja            = 0; try{ est_loja = Double.parseDouble(est_loja_str); } catch( Exception e ){}    
                        if( est_loja < 0 ){
                            
                            est_loja = 0;
                        }
                    }
                    else if( strn.trim().equals("Requisições compra") ){
                                  
                        String sug_str = "0"; try { sug_str = String.valueOf( tbPesquisa.getValueAt( L_i, C_i ) ); } catch( Exception e ){}
                        sug_loja       = 0;   try{ sug_loja = Double.parseDouble(sug_str); } catch( Exception e ){}     
                    }
                    else if( strn.trim().equals("V_MÉDIA 12 SEM") ){
                        
                        String v12dias_str  = "0"; try { v12dias_str = String.valueOf( tbPesquisa.getValueAt( L_i, C_i ) ); } catch( Exception e ){}
                        v12Sem              =   0; try{ v12Sem = Double.parseDouble(v12dias_str); } catch( Exception e ){}
                        
                        if( v12Sem <= 0 ){
                            
                            v12Sem = 1;
                        }
                        
                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        double disp_dia      = v12Sem/7;
                        
                        try{ disponibilidade =  disp_dia; } catch( Exception e ){}   
                        if( disponibilidade <= 0 ){
                            
                            disponibilidade = 3;
                        }
                    }
                    else if( strn.trim().equals("DDE_") ){
                        
                        L_iDDE = L_i;
                        C_iDDE = C_i;
                        //System.out.println( "L_iDDE " +L_iDDE + " -- tbC_iDDE - " + C_iDDE );
                    }
                    else if( vcount > tbPesquisa.getColumnCount() ){
                        
                        double newDDE = 0;
                                
                        double somaProd = est_loja + sug_loja;
                        if( somaProd > 0 ){
                            
                            newDDE = Math.round(  ( est_loja + sug_loja ) / disponibilidade );
                        }

                        try{ tbPesquisa.setValueAt( newDDE, L_iDDE, C_iDDE ); } catch( Exception e ){} 
                        //System.out.println( "COLUNA " +strn + " - EST. LOJA - " + est_loja + " - SUGESTÃO-JM - " + sug_loja + " - DISPONIBILIDADE - " + disponibilidade );
                    }
                } 
            }
        }
            
        } catch( Exception e ){  } } }.start();
    }
    
}
