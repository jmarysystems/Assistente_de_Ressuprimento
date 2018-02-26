/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import fornecedor.Fornecedor;
import gb_evento.Analisedevolutiva;
import gb_evento.LojasAZvtraSort;
import gb_evento.Analiseeventos;
import gb_evento.Gbdescricaogrupo;
import gb_evento.Gbdescricaosetor;
import gb_evento.Gbdiassemvenda;
import gb_evento.LojasAZdadoslogLoja;
import gb_evento.Gbdiretoloja;
import gb_evento.Gbelenco;
import gb_evento.Gbentroucdontem;
import gb_evento.Gbestoquecdb001;
import gb_evento.Gbestoquecdb184;
import gb_evento.Gbestoquecdb289;
import gb_evento.Gbestoquelojab141;
import gb_evento.Gbmrp;
import gb_evento.Gbooportsemana;
import gb_evento.Gbpedidosativos;
import gb_evento.Gbpontoextra;
import gb_evento.Gbproduto;
import gb_evento.Gbra;
import gb_evento.Gbsuply;
import gb_evento.Gbultimaentradadata;
import gb_evento.Gbzris;
import gb_evento.Lof;
import gb_evento.LojasAMb52;
import gb_evento.LojasAZdadoslogCd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilidades.Arquivo_Ou_Pasta;
import utilidades.DB;
import utilidades.Exportando;
import utilidades.ExportarExcelAjusteMinimo;
import utilidades.ExportarExcelExistente;
import utilidades.ExportarExcelExistentePedido;
import utilidades.ExportarExcelGenerico;
import utilidades.Exportar_BigExcel;
import utilidades.JOPM;
import utilidades_imagens.Imagens;

/**
 *
 * @author AnaMariana
 */
public class Relatorios extends javax.swing.JFrame {

    ListaSAPNaoCadastrado ListaSAPNaoCadastrado;
    
    Menu_Pesquisa_Importar_Exportar Menu;
    String layout = "";
    /**
     * Creates new form OperacaoRealizada
     * @param layout2
     * @param Menu_Pesquisa_Importar_Exportar2
     */
    public Relatorios( String layout2, Menu_Pesquisa_Importar_Exportar Menu_Pesquisa_Importar_Exportar2 ) { 
        initComponents();
        lbAcao.setText("RELATÓRIOS DINÂMICOS");
        centralizeFrame();

        layout = layout2;
        Menu = Menu_Pesquisa_Importar_Exportar2;
    }
    
    private void centralizeFrame() {  
        int x,y;  
        java.awt.Rectangle scr  = this.getGraphicsConfiguration().getBounds();  
        java.awt.Rectangle form = this.getBounds();  
        x = (int) ( scr.getWidth() - form.getWidth() ) / 2;  
        y = (int) ( scr.getHeight()- form.getHeight()) / 2;  
        this.setLocation( x , y );  
    }
    
    private boolean cadastrarBoo = false;
    private void relatorio_zris_x_pedido(){
        new Thread() { @Override public void run() {    
            
                while ( cadastrarBoo == false ) {   
                        cadastrarBoo = true;
                        try { Thread.sleep( 1 );
                            fechar(); 
                            
                            String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + "ZRIS X PEDIDO GERADO" +  ".xlsx";     
                            Arquivo_Ou_Pasta.deletar(new File(aplicativo));
                            
                            Thread.sleep( 1000 );
                            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                                   
                            
                            FileInputStream fileIn = null;
                            FileOutputStream fileOut = null;
                            
                            
                            try{
                                
                                fileIn = new FileInputStream("MODELO ZRIS X PEDIDO GERADO.xlsx");
                                XSSFWorkbook wb = new XSSFWorkbook(fileIn); 
                                Thread.sleep( 1000 );
                                XSSFSheet aba = wb.getSheetAt(0); 
                                                                
                                
                                Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBDIRETOLOJA", Gbdiretoloja.class );
                                List<Gbdiretoloja> Gbdiretoloja = q.getResultList(); 
                                
                                
                                Exportando.pbg.setMaximum( Gbdiretoloja.size() );
                                                                                    
                                XSSFCell cell;  
                                for ( int i = 0; i<Gbdiretoloja.size(); i++) { Thread.sleep( 1 );
                                    Exportando.pbg.setValue( i );
                                    
                                    try{ 
                                        int linhaParaescrever = i+2;
                                        XSSFRow linha = aba.getRow(linhaParaescrever);
                                    
                                        if (linha != null){
////////////////////////////////////////      
                                        String sapTab="";
            List<Gbproduto> XXGbProdListaSap = null;
            try {
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbdiretoloja.get(i).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    sapTab = XXGbProdListaSap.get(0).getMaterial();                        
                }
            }catch( Exception e ){}
            
            String nomeProd="";
            try {
                nomeProd = XXGbProdListaSap.get(0).getDescricao();
            }catch( Exception e ){}
            
            int zris=0;
            try {
                zris = Gbdiretoloja.get(i).getInputzris();
            }catch( Exception e ){}
            
            int pedidoGerado=0;
            try {
                pedidoGerado = Gbdiretoloja.get(i).getPedidogerado();
            }catch( Exception e ){}
            
            
            
            List<Gbzris> XXGbzrisListaSap = null;
            String dde="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", Gbdiretoloja.get(i).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    dde = String.valueOf(XXGbzrisListaSap.get(0).getDde());                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/

            String venda_12_semanas="";
            try {
                venda_12_semanas = String.valueOf(XXGbzrisListaSap.get(0).getVenda12Semanas());
                if( venda_12_semanas.equals("null") ){ venda_12_semanas=""; }
            }catch( Exception e ){}

            String disponibilidade="";
            try {
                disponibilidade = String.valueOf(XXGbzrisListaSap.get(0).getDisponibilidade());
                if( disponibilidade.equals("null") ){ disponibilidade=""; }
            }catch( Exception e ){}
            
            int b141 = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", Gbdiretoloja.get(i).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b141 = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}

            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbdiretoloja.get(i).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }                
            }catch( Exception e ){}
            /*SUPLAIN*/

            /*ZRIS*/
            String codigoFornecedor="";
            try {                
                
                codigoFornecedor = Gbdiretoloja.get(i).getFornecedor();
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            String nomeFornec="";
            List<Fornecedor> XXGbForndListaSap = null;
            try {
                try{ 
                    String x = Gbdiretoloja.get(i).getFornecedor().toUpperCase().trim();
                    
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Fornecedor.class, JPAUtil.em());
                                        
                    XXGbForndListaSap = (List<Fornecedor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Fornecedor.class, 
                            "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEOURAZAOSOCIAL = ?", x );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbForndListaSap.get(0).getNomefantasia(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nomeFornec = XXGbForndListaSap.get(0).getNomefantasia();                        
                }
            }catch( Exception e ){}    
            
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", XXGbProdListaSap.get(0).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", XXGbProdListaSap.get(0).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
////////////////////////////////////////                                        
                                            for ( int c = 0; c<14; c++) {   
                                        
                                            
                                                cell = linha.getCell(c);         
                                                if (cell == null) 
                                                    cell = linha.createCell(c); 
                                                
                                                    try{ cell.setCellType(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){System.out.println( "-1: " + e.getMessage()  ); e.printStackTrace();}
                                                    try{ cell.setCellValue(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){System.out.println( "0: " + e.getMessage()  ); e.printStackTrace();}

                                                    if(c==0){ 
                                                        try{
                                                        GregorianCalendar gc = new GregorianCalendar();
                                                        Date dataHoje = gc.getTime();
                                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                                        
                                                        String data_formatada = formatter.format( dataHoje );
                                                        
                                                        cell.setCellValue( data_formatada );
                                                        }catch(Exception e){}
                                                    }
                                                    else if(c==1){ cell.setCellValue( sapTab ); } 
                                                    else if(c==2){ cell.setCellValue( nomeProd ); } 
                                                    else if(c==3){ String v = String.valueOf( b141 ); cell.setCellValue( v ); } 
                                                    else if(c==4){ String v = String.valueOf( dde ); cell.setCellValue( v ); } 
                                                    else if(c==5){ String v = String.valueOf( venda_12_semanas ); cell.setCellValue( v ); } 
                                                    else if(c==6){ cell.setCellValue( disponibilidade ); } 
                                                    else if(c==7){ cell.setCellValue( classe ); } 
                                                    else if(c==8){ 
                                                        try{ cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC); }catch(Exception e){}
                                                        try{ cell.setCellValue(XSSFCell.CELL_TYPE_NUMERIC); }catch(Exception e){}                                                        
                                                        cell.setCellValue( zris ); 
                                                    } 
                                                    else if(c==9){ 
                                                        try{ cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC); }catch(Exception e){}
                                                        try{ cell.setCellValue(XSSFCell.CELL_TYPE_NUMERIC); }catch(Exception e){}
                                                        cell.setCellValue( pedidoGerado ); 
                                                    } 
                                                    else if(c==10){ cell.setCellValue( codigoFornecedor ); } 
                                                    else if(c==11){ cell.setCellValue( nomeFornec ); } 
                                                    else if(c==12){ cell.setCellValue( pontExtra ); } 
                                                    else if(c==13){ cell.setCellValue( elenco ); } 

                                            }
                                        }
                                    
                                    }catch(Exception e){System.out.println( "1: " + e.getMessage()  ); e.printStackTrace();}
                                }
                                
                                wb.setForceFormulaRecalculation(true);
                                fileOut = new FileOutputStream("ZRIS X PEDIDO GERADO.xlsx");
                                wb.write(fileOut);
                                
                                try{
                                    fileOut.close(); 
                                    fileIn.close(); 
                                } catch(Exception e) {}
                                
                                abrirExcel();
                            } catch(Exception e) {                                
                                System.out.println( "2: " + e.getMessage()  ); e.printStackTrace();
                            }
        
                                                            
                            Exportando.fechar();
                            //fechar();                     
                        }catch( Exception e ){ 
                            /*Exportando.fechar();*/ fechar(); 
                            System.out.println( "2: " + e.getMessage()  ); 
                            e.printStackTrace();
                        }
                    }
            } }.start();
    }
    
    private void abrirExcel() throws IOException{
        String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + "ZRIS X PEDIDO GERADO" +  ".xlsx";     
        java.awt.Desktop.getDesktop().open( new File( aplicativo ) ); 

    }
        
    public void fechar(){ 
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfLogradouro8 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbAcao = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tfLogradouro1 = new javax.swing.JTextField();
        tfLogradouro7 = new javax.swing.JTextField();
        tfLogradouro13 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tpListSapLoja = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        tfLogradouro5 = new javax.swing.JTextField();
        tfLogradouro6 = new javax.swing.JTextField();
        tfLogradouro9 = new javax.swing.JTextField();
        tfLogradouro10 = new javax.swing.JTextField();
        tfLogradouro11 = new javax.swing.JTextField();
        tfLogradouro12 = new javax.swing.JTextField();
        tfLogradouro14 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        tfLogradouro2 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        tfPa = new javax.swing.JTextField();
        tfLogradouro4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpListSaps = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        rbLisaps = new javax.swing.JRadioButton();
        tfLogradouro3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpListLojas = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        rbLojas = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        cbAnaliseLojas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        tfLogradouro8.setEditable(false);
        tfLogradouro8.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro8.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro8.setText("DISPONIBILIDADE BAIXA CD -");
        tfLogradouro8.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro8.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro8MousePressed(evt);
            }
        });
        tfLogradouro8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro8KeyReleased(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/catalogo_70.png"))); // NOI18N

        lbAcao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAcao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAcao.setText("RELATÓRIOS DINÂMICOS");

        tfLogradouro1.setEditable(false);
        tfLogradouro1.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro1.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro1.setText("ZRIS X PEDIDO GERADO");
        tfLogradouro1.setToolTipText("NÃO INFORME SAP'S APENAS CLICK AQUI PARA GERAR O RELATÓRIO");
        tfLogradouro1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro1.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro1MousePressed(evt);
            }
        });
        tfLogradouro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro1KeyReleased(evt);
            }
        });

        tfLogradouro7.setEditable(false);
        tfLogradouro7.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro7.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro7.setText("ANÁLISE DE DEVOLUTIVA RA");
        tfLogradouro7.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro7.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro7MousePressed(evt);
            }
        });
        tfLogradouro7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro7KeyReleased(evt);
            }
        });

        tfLogradouro13.setEditable(false);
        tfLogradouro13.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro13.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro13.setText("ANÁLISE DE EVENTOS");
        tfLogradouro13.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro13.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro13MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro13MousePressed(evt);
            }
        });
        tfLogradouro13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro13KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tfLogradouro7, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tfLogradouro13, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfLogradouro1, tfLogradouro7});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jScrollPane3.setViewportView(tpListSapLoja);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("LOJA - LISTA SAP ");

        tfLogradouro5.setEditable(false);
        tfLogradouro5.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro5.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro5.setText("ANÁLISE  - ESTOQUE MÍNIMO");
        tfLogradouro5.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro5.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro5MousePressed(evt);
            }
        });
        tfLogradouro5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro5KeyReleased(evt);
            }
        });

        tfLogradouro6.setEditable(false);
        tfLogradouro6.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro6.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro6.setText("DISPONIBILIDADE BAIXA CD -");
        tfLogradouro6.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro6.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro6MousePressed(evt);
            }
        });
        tfLogradouro6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro6KeyReleased(evt);
            }
        });

        tfLogradouro9.setEditable(false);
        tfLogradouro9.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro9.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro9.setText("DISPONIBILIDADE BAIXA CD +");
        tfLogradouro9.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro9.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro9MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro9MousePressed(evt);
            }
        });
        tfLogradouro9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro9KeyReleased(evt);
            }
        });

        tfLogradouro10.setEditable(false);
        tfLogradouro10.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro10.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro10.setText("RELATÓRIO FULL");
        tfLogradouro10.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro10.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro10MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro10MousePressed(evt);
            }
        });
        tfLogradouro10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro10KeyReleased(evt);
            }
        });

        tfLogradouro11.setEditable(false);
        tfLogradouro11.setBackground(new java.awt.Color(0, 51, 204));
        tfLogradouro11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro11.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro11.setText("AN. PED. ADIC. DISP. BAIXA");
        tfLogradouro11.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro11.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro11MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro11MousePressed(evt);
            }
        });
        tfLogradouro11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro11KeyReleased(evt);
            }
        });

        tfLogradouro12.setEditable(false);
        tfLogradouro12.setBackground(new java.awt.Color(0, 51, 204));
        tfLogradouro12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro12.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro12.setText("AN. PED. ADICIONAL FULL");
        tfLogradouro12.setToolTipText("NA COLUNA  LISTA SAP - LOJA INFORME AS LOJAS E OS SAP'S NA MESMA LINHA: EX.: B185 1099350");
        tfLogradouro12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro12.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro12MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro12MousePressed(evt);
            }
        });
        tfLogradouro12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro12KeyReleased(evt);
            }
        });

        tfLogradouro14.setEditable(false);
        tfLogradouro14.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro14.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro14.setText("RELATÓRIO MB52 / ZVTRASORT.");
        tfLogradouro14.setToolTipText("MB52 - ZVTRA_SORTIMENTO - ME80FN/LOJA - ZDADOSLOG/LOJA - ZRIS - EST.CD - ME80FN/CD - ZDADOSLOG/CD");
        tfLogradouro14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro14.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro14MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro14MousePressed(evt);
            }
        });
        tfLogradouro14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro14KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfLogradouro5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfLogradouro6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tfLogradouro9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tfLogradouro10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tfLogradouro11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tfLogradouro12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tfLogradouro14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        tfLogradouro2.setEditable(false);
        tfLogradouro2.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro2.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro2.setText("PEDIDO GERADO X ENTREGUE");
        tfLogradouro2.setToolTipText("NFORME OS CÓDIGOS DOS  FORNECEDORES NA COLUNA LISTA SAP'S  E NA COLUNA LISTA LOJAS AS LOJAS");
        tfLogradouro2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro2.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro2MousePressed(evt);
            }
        });
        tfLogradouro2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro2KeyReleased(evt);
            }
        });

        tfPa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPaKeyReleased(evt);
            }
        });

        tfLogradouro4.setEditable(false);
        tfLogradouro4.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro4.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro4.setText("GERAR RELATÓRIO");
        tfLogradouro4.setToolTipText("CONSULTAR LISTA SAP");
        tfLogradouro4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro4.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro4MousePressed(evt);
            }
        });
        tfLogradouro4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro4KeyReleased(evt);
            }
        });

        jScrollPane2.setViewportView(tpListSaps);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("LISTA SAPS");

        rbLisaps.setText("LISTA SAP");
        rbLisaps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbLisapsMousePressed(evt);
            }
        });

        tfLogradouro3.setEditable(false);
        tfLogradouro3.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro3.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro3.setText("ANÁLISE RA - PEDIDOS ZREP");
        tfLogradouro3.setToolTipText("INFORME OS SAP'S DAS PLANLHAS DE RA RECEBIDAS DA INTRANET NA COLUNA LISTA SAP'S");
        tfLogradouro3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogradouro3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tfLogradouro3.setPreferredSize(new java.awt.Dimension(247, 23));
        tfLogradouro3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLogradouro3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLogradouro3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfLogradouro3MousePressed(evt);
            }
        });
        tfLogradouro3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro3KeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(tpListLojas);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("LISTA LOJAS");

        rbLojas.setText("LOJAS");
        rbLojas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbLojasMousePressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BAZAR", "ELETRO", "PERECÍVEIS", "HIPEL BEBIDAS MERCEARIA" }));

        cbAnaliseLojas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATÓRIO INDEPENDENTE DISPONIBILIDADE E DO ESTOQUE DO CD", "RELATÓRIO DISPONIBILIDADE BAIXA TODOS SETORES EM RUPTURA NO CD", "RELATÓRIO DISPONIBILIDADE BAIXA TODOS SETORES CD POSITIVO", "RELATÓRIO DISPONIBILIDADE BAIXA POR SETOR EM RUPTURA NO CD ", "RELATÓRIO DISPONIBILIDADE ALTA POR SETOR EM RUPTURA NO CD ", "RELATÓRIO DISPONIBILIDADE BAIXA POR SETOR  CD POSITIVO", "LISTAR PRODUTOS POR SETOR " }));

        jLabel1.setText("RUPTURA CD      <");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(rbLisaps)
                                    .addGap(28, 28, 28)
                                    .addComponent(rbLojas))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(tfPa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLogradouro2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(tfLogradouro3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfLogradouro4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbAnaliseLojas, javax.swing.GroupLayout.Alignment.LEADING, 0, 460, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfLogradouro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLogradouro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbLisaps)
                            .addComponent(rbLojas))
                        .addGap(4, 4, 4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAnaliseLojas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogradouro4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, tfPa});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lbAcao, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(733, 733, 733))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbAcao))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        setBounds(0, 0, 952, 563);
    }// </editor-fold>//GEN-END:initComponents

    private void tfLogradouro1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseEntered

    private void tfLogradouro1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseExited

    private void tfLogradouro1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MousePressed
        relatorio_zris_x_pedido();
    }//GEN-LAST:event_tfLogradouro1MousePressed

    private void tfLogradouro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1KeyReleased

    private void tfLogradouro2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2MouseEntered

    private void tfLogradouro2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2MouseExited

    private void tfLogradouro2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MousePressed
  
        pedidoGeradoXEntrgue();
    }//GEN-LAST:event_tfLogradouro2MousePressed

    private void tfLogradouro2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2KeyReleased

    private void tfLogradouro3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro3MouseEntered

    private void tfLogradouro3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro3MouseExited

    private void tfLogradouro3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro3MousePressed
  
        analiseRaPedidosZrep();
    }//GEN-LAST:event_tfLogradouro3MousePressed

    private void tfLogradouro3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro3KeyReleased

    private void tfPaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPaKeyReleased
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            tfPa.setText("");
        }
    }//GEN-LAST:event_tfPaKeyReleased

    private void rbLisapsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbLisapsMousePressed
        
    }//GEN-LAST:event_rbLisapsMousePressed

    private void rbLojasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbLojasMousePressed
        
    }//GEN-LAST:event_rbLojasMousePressed

    private void tfLogradouro4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro4MouseEntered

    private void tfLogradouro4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro4MouseExited

    private void tfLogradouro4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro4MousePressed
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            String strTipoDeEvento = "";
            try{ strTipoDeEvento = cbAnaliseLojas.getSelectedItem().toString().trim(); } catch( Exception e ){}
            
            int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR RELATÓRIO**"
                        + "\n"
                        + strTipoDeEvento
                        + "\n"
                        + "GERAR RELATÓRIO COM PARÂMETROS INFORMADOS?");  
            
            if( response == JOptionPane.YES_OPTION){ 
                
                if( strTipoDeEvento.equals( "RELATÓRIO DISPONIBILIDADE BAIXA POR SETOR EM RUPTURA NO CD" ) ){ 
                    
                        if( !tfPa.getText().trim().equals("") ){
                            int paRuptura = 0;
                            try{ paRuptura = Integer.parseInt(tfPa.getText().trim()); } catch( Exception e ){}
                
                            String strTipoDeEvento2 = "";
                            try{ strTipoDeEvento2 = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
                            //
                            switch (strTipoDeEvento2) {
                                case "BAZAR":
                                    oportunidadeDispBaixaPorSetorRupturaCD("BAZAR",paRuptura);
                                break;
                                case "HIPEL BEBIDAS MERCEARIA":
                                    oportunidadeDispBaixaPorSetorRupturaCD("HIPEL BEBIDAS MERCEARIA",paRuptura);
                                break;
                                case "PERECÍVEIS":
                                    oportunidadeDispBaixaPorSetorRupturaCD("PERECÍVEIS",paRuptura);
                                break; 
                                case "ELETRO":
                                    oportunidadeDispBaixaPorSetorRupturaCD("ELETRO",paRuptura);
                                break;     
                            }           
                        }          
                        else{
                
                            JOPM JOptionPaneMod = new JOPM( 2, "RUPTURA CD, "
                                + "\nINFORME UM PARÂMETRO DE RUPTURA NO CD "
                                + "\nInforme um NÚMERO válido"
                                + "\n", "RUPTURA CD" );
                        }
                }
                else if( strTipoDeEvento.equals( "RELATÓRIO DISPONIBILIDADE ALTA POR SETOR EM RUPTURA NO CD" ) ){
                    
                    if( !tfPa.getText().trim().equals("") ){
                            int paRuptura = 0;
                            try{ paRuptura = Integer.parseInt(tfPa.getText().trim()); } catch( Exception e ){}
                
                            String strTipoDeEvento2 = "";
                            try{ strTipoDeEvento2 = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
                            //
                            switch (strTipoDeEvento2) {
                                case "BAZAR":
                                    oportunidadeDispAltaPorSetorRupturaCD("BAZAR",paRuptura);
                                break;
                                case "HIPEL BEBIDAS MERCEARIA":
                                    oportunidadeDispAltaPorSetorRupturaCD("HIPEL BEBIDAS MERCEARIA",paRuptura);
                                break;
                                case "PERECÍVEIS":
                                    oportunidadeDispAltaPorSetorRupturaCD("PERECÍVEIS",paRuptura);
                                break; 
                                case "ELETRO":
                                    oportunidadeDispAltaPorSetorRupturaCD("ELETRO",paRuptura);
                                break;     
                            }           
                        }          
                        else{
                
                            JOPM JOptionPaneMod = new JOPM( 2, "RUPTURA CD, "
                                + "\nINFORME UM PARÂMETRO DE RUPTURA NO CD "
                                + "\nInforme um NÚMERO válido"
                                + "\n", "RUPTURA CD" );
                        }
                }
                else if( strTipoDeEvento.equals( "RELATÓRIO DISPONIBILIDADE BAIXA POR SETOR  CD POSITIVO" ) ){
                    
                    if( !tfPa.getText().trim().equals("") ){
                            int paRuptura = 0;
                            try{ paRuptura = Integer.parseInt(tfPa.getText().trim()); } catch( Exception e ){}
                
                            String strTipoDeEvento2 = "";
                            try{ strTipoDeEvento2 = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
                            //
                            switch (strTipoDeEvento2) {
                                case "BAZAR":
                                    oportunidadeDispBaixaPorSetorCDPositivo("BAZAR",paRuptura);
                                break;
                                case "HIPEL BEBIDAS MERCEARIA":
                                    oportunidadeDispBaixaPorSetorCDPositivo("HIPEL BEBIDAS MERCEARIA",paRuptura);
                                break;
                                case "PERECÍVEIS":
                                    oportunidadeDispBaixaPorSetorCDPositivo("PERECÍVEIS",paRuptura);
                                break; 
                                case "ELETRO":
                                    oportunidadeDispBaixaPorSetorCDPositivo("ELETRO",paRuptura);
                                break;     
                            }           
                        }          
                        else{
                
                            JOPM JOptionPaneMod = new JOPM( 2, "RUPTURA CD, "
                                + "\nINFORME UM PARÂMETRO DE RUPTURA NO CD "
                                + "\nInforme um NÚMERO válido"
                                + "\n", "RUPTURA CD" );
                        }
                }  
                else if( strTipoDeEvento.equals( "LISTAR PRODUTOS POR SETOR" ) ){
                    
                    String strTipoDeEvento2 = "";
                            try{ strTipoDeEvento2 = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
                            
                            listarPorSetor(strTipoDeEvento2);      
                } 
                else if( strTipoDeEvento.equals( "RELATÓRIO DISPONIBILIDADE BAIXA TODOS SETORES EM RUPTURA NO CD" ) ){ 
                    
                        if( !tfPa.getText().trim().equals("") ){
                            int paRuptura = 0;
                            try{ paRuptura = Integer.parseInt(tfPa.getText().trim()); } catch( Exception e ){}
                
                            oportunidadeDispBaixaPorSetorRupturaCDTodosSetoresNovo("TODOS_SETORES",paRuptura);        
                        }          
                        else{
                
                            JOPM JOptionPaneMod = new JOPM( 2, "RUPTURA CD, "
                                + "\nINFORME UM PARÂMETRO DE RUPTURA NO CD "
                                + "\nInforme um NÚMERO válido"
                                + "\n", "RUPTURA CD" );
                        }
                }       
                else if( strTipoDeEvento.equals( "RELATÓRIO INDEPENDENTE DISPONIBILIDADE E DO ESTOQUE DO CD" ) ){ 
                    
                        /*if( !tfPa.getText().trim().equals("") ){
                            int paRuptura = 0;
                            try{ paRuptura = Integer.parseInt(tfPa.getText().trim()); } catch( Exception e ){}*/
                
                            oportunidadeIndependenteDispIndependenteEstoqueCDTodosSetoresNovo("TODOS_SETORES",paRuptura);        
                        /*}          
                        else{
                
                            JOPM JOptionPaneMod = new JOPM( 2, "RUPTURA CD, "
                                + "\nINFORME UM PARÂMETRO DE RUPTURA NO CD "
                                + "\nInforme um NÚMERO válido"
                                + "\n", "RUPTURA CD" );
                        }*/
                }
                else if( strTipoDeEvento.equals( "RELATÓRIO DISPONIBILIDADE BAIXA TODOS SETORES CD POSITIVO" ) ){ 
                    
                        if( !tfPa.getText().trim().equals("") ){
                            int paRuptura = 0;
                            try{ paRuptura = Integer.parseInt(tfPa.getText().trim()); } catch( Exception e ){}
                
                            oportunidadeDispBaixaPorSetorCDPositivoTodosSetoresNovo("TODOS_SETORES",paRuptura);        
                        }          
                        else{
                
                            JOPM JOptionPaneMod = new JOPM( 2, "RUPTURA CD, "
                                + "\nINFORME UM PARÂMETRO DE RUPTURA NO CD "
                                + "\nInforme um NÚMERO válido"
                                + "\n", "RUPTURA CD" );
                        }
                }
            }            
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "btAnaliseCadastrarMousePressed(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();        
    }//GEN-LAST:event_tfLogradouro4MousePressed

    private void tfLogradouro4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro4KeyReleased

    private void tfLogradouro5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro5MouseEntered

    private void tfLogradouro5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro5MouseExited

    private void tfLogradouro5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro5MousePressed
        
        if( !tpListSapLoja.getText().trim().equals("") ){
                    
            analiseAjusteEstoqueMinimo();
        }
        else{
            Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "INFORMAÇÕES INCOMPLETAS\n"
                        + "\nINFORME OS DADOS NECESSÁRIOS PARA A CONSULTA\n"
                        + "\nEXEM.: LOJA SAP\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_tfLogradouro5MousePressed

    private void tfLogradouro5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro5KeyReleased

    private void tfLogradouro7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro7MouseEntered

    private void tfLogradouro7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro7MouseExited

    private void tfLogradouro7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro7MousePressed
        new Thread() { @Override public void run() {
            try{
                
                analise_RA_devolutiva( "ANALISE_DEVOLUTIVA_RA" );
            } catch( Exception e ){ e.printStackTrace(); }
        } }.start();      
    }//GEN-LAST:event_tfLogradouro7MousePressed

    private void tfLogradouro7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro7KeyReleased

    private void tfLogradouro6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro6KeyReleased

    private void tfLogradouro6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro6MousePressed
        new Thread() { @Override public void run() {
            try{
                
                if( !tpListSapLoja.getText().trim().equals("") ){
                    
                    disponilidadeBaixaZeroNoCD( "TODOS_SETORES" );
                }
                else{
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "INFORMAÇÕES INCOMPLETAS\n"
                        + "\nINFORME OS DADOS NECESSÁRIOS PARA A CONSULTA\n"
                        + "\nEXEM.: LOJA SAP\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
                
            } catch( Exception e ){ e.printStackTrace(); }
        } }.start();
    }//GEN-LAST:event_tfLogradouro6MousePressed

    private void tfLogradouro6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro6MouseExited

    private void tfLogradouro6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro6MouseEntered

    private void tfLogradouro8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro8MouseEntered

    private void tfLogradouro8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro8MouseExited

    private void tfLogradouro8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro8MousePressed

    private void tfLogradouro8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro8KeyReleased

    private void tfLogradouro9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro9MouseEntered

    private void tfLogradouro9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro9MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro9MouseExited

    private void tfLogradouro9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro9MousePressed
        new Thread() { @Override public void run() {
            try{
                                
                if( !tpListSapLoja.getText().trim().equals("") ){
                    
                    disponilidadeBaixaPositivoNoCD( "TODOS_SETORES" );
                }
                else{
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "INFORMAÇÕES INCOMPLETAS\n"
                        + "\nINFORME OS DADOS NECESSÁRIOS PARA A CONSULTA\n"
                        + "\nEXEM.: LOJA SAP\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            } catch( Exception e ){ e.printStackTrace(); }
        } }.start();
    }//GEN-LAST:event_tfLogradouro9MousePressed

    private void tfLogradouro9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro9KeyReleased

    private void tfLogradouro10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro10MouseEntered

    private void tfLogradouro10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro10MouseExited

    private void tfLogradouro10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro10MousePressed
        new Thread() { @Override public void run() {
            try{
                                
                if( !tpListSapLoja.getText().trim().equals("") ){
                    
                    relatorioFull( "TODOS_SETORES" );
                    
                }
                else{
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "INFORMAÇÕES INCOMPLETAS\n"
                        + "\nINFORME OS DADOS NECESSÁRIOS PARA A CONSULTA\n"
                        + "\nEXEM.: LOJA SAP\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            } catch( Exception e ){ e.printStackTrace(); }
        } }.start();
    }//GEN-LAST:event_tfLogradouro10MousePressed

    private void tfLogradouro10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro10KeyReleased

    private void tfLogradouro11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro11MouseEntered

    private void tfLogradouro11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro11MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro11MouseExited

    private void tfLogradouro11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro11MousePressed
        new Thread() { @Override public void run() {
            try{
                                
                if( !tpListSapLoja.getText().trim().equals("") ){
                    
                    pedidoAdicionalDispBaixa( "PEDIDO_ADICIONAL" );
                }
                else{
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "INFORMAÇÕES INCOMPLETAS\n"
                        + "\nINFORME OS DADOS NECESSÁRIOS PARA A CONSULTA\n"
                        + "\nEXEM.: LOJA SAP\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            } catch( Exception e ){ e.printStackTrace(); }
        } }.start();
    }//GEN-LAST:event_tfLogradouro11MousePressed

    private void tfLogradouro11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro11KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro11KeyReleased

    private void tfLogradouro12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro12MouseEntered

    private void tfLogradouro12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro12MouseExited

    private void tfLogradouro12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro12MousePressed
        new Thread() { @Override public void run() {
            try{
                
                if( !tpListSapLoja.getText().trim().equals("") ){
                    
                    pedidoAdicionalFull( "PEDIDO_ADICIONAL" );
                }
                else{
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "INFORMAÇÕES INCOMPLETAS\n"
                        + "\nINFORME OS DADOS NECESSÁRIOS PARA A CONSULTA\n"
                        + "\nEXEM.: LOJA SAP\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            } catch( Exception e ){ e.printStackTrace(); }
        } }.start();
    }//GEN-LAST:event_tfLogradouro12MousePressed

    private void tfLogradouro12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro12KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro12KeyReleased

    private void tfLogradouro13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro13MouseEntered

    private void tfLogradouro13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro13MouseExited

    private void tfLogradouro13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro13MousePressed
        new Thread() { @Override public void run() {
            try{
                
                analise_eventos( "ANALISE_VT_LAMINA_TABOIDE" );
            } catch( Exception e ){ e.printStackTrace(); }
        } }.start();  
    }//GEN-LAST:event_tfLogradouro13MousePressed

    private void tfLogradouro13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro13KeyReleased

    private void tfLogradouro14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro14MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro14MouseEntered

    private void tfLogradouro14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro14MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro14MouseExited

    private void tfLogradouro14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro14MousePressed
        new Thread() { @Override public void run() {
            try{
                
                if( !tpListSapLoja.getText().trim().equals("") ){
                    
                    relatorioMb52ZvtraSortimento( "RELATORIO_DE_ACOMPANHAMENTO_DO_ESTOQUE_LOJA_CDS" );
                }
                else{
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "INFORMAÇÕES INCOMPLETAS\n"
                        + "\nINFORME OS DADOS NECESSÁRIOS PARA A CONSULTA\n"
                        + "\nEXEM.: LOJA SAP\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            } catch( Exception e ){ e.printStackTrace(); }
        } }.start();
    }//GEN-LAST:event_tfLogradouro14MousePressed

    private void tfLogradouro14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro14KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro14KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Relatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Relatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Relatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Relatorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ListaSAP().setVisible(true);
            }
        });
    }
    
    public void analiseGenericaTabela( String sap, Gbproduto Gbproduto2 ){ try {                                     
   
        Gbproduto Gbproduto = Gbproduto2;
                
        if ( !sap.equals("") ){ 
            
            List<Gbproduto> XXGbProdListaSap = null;
            try {
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    Gbproduto = XXGbProdListaSap.get(0);                        
                }
            }catch( Exception e ){}
            
        }else if( Gbproduto != null ){
 
        //for (Gbproduto listR1 : listR) {    
            
            String sapTab   = "";  try { sapTab   = Gbproduto.getMaterial();    }catch( Exception e ){}            
            String ean      = "";  try { ean      = Gbproduto.getEan();         }catch( Exception e ){}            
            String nomeProd = "";  try { nomeProd = Gbproduto.getDescricao();   }catch( Exception e ){}            
            String qtdEmEs  = "";  try { qtdEmEs  = Gbproduto.getQtdxes();      }catch( Exception e ){}          
            String Und      = "";  try { Und      = Gbproduto.getUnd();         }catch( Exception e ){}

            /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            String grupo = "";  try { grupo = Gbproduto.getGrupo();  }catch( Exception e ){}
            
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            
            int b141 = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b141 = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            
            String sugestao ="";
            try {
                sugestao = "";
            }catch( Exception e ){}
            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                        
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpedidosativos.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7D = XXGbpedidosativosListaSap.get(0).getPedidoCd184Ativo();                        
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
            
            String remessa="";
            try {
                remessa = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa();
                if( remessa.equals("null") ){ remessa=""; }
            }catch( Exception e ){}
            
            String saiuCD="";
            try {
                saiuCD = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                if( saiuCD.equals("null") ){ saiuCD=""; }
            }catch( Exception e ){}
            
            String entrouLJ="";
            try {
                entrouLJ = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                if( entrouLJ.equals("null") ){ entrouLJ=""; }
            }catch( Exception e ){}
            
            int dde = 0;
            try {
                dde = XXGbzrisListaSap.get(0).getDde();
            }catch( Exception e ){}
            
            String cdOntem="";
            try {
                
                List<Gbentroucdontem> XXGbentroucdontemListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbentroucdontem.class, JPAUtil.em());
                                        
                    XXGbentroucdontemListaSap = (List<Gbentroucdontem>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbentroucdontem.class, 
                            "SELECT * FROM SCHEMAJMARY.GBENTROUCDONTEM WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbentroucdontemListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    cdOntem = XXGbentroucdontemListaSap.get(0).getEntrouCd184Ontem();                        
                }
                
            }catch( Exception e ){}
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            String setor="";
            try {
                setor = XXGbsuplyListaSap.get(0).getNomSetor();
                if( setor.equals("") || setor.equals("null") ){ setor=""; }
            }catch( Exception e ){}
            
            String status="";
            try {
                status = XXGbsuplyListaSap.get(0).getStsEstoquetipo();
                if( status.equals("") || status.equals("null") ){ status=""; }
            }catch( Exception e ){}
            
            String codForn="";
            try {
                codForn = XXGbsuplyListaSap.get(0).getCodFornecedorlof();
                if( codForn.equals("") || codForn.equals("null") ){ codForn=""; }
            }catch( Exception e ){}
            
            String nomForn="";
            try {
                nomForn = XXGbsuplyListaSap.get(0).getNomFornecedorgrupoeconomico();
                if( nomForn.equals("") || nomForn.equals("null") ){ nomForn=""; }
            }catch( Exception e ){}
            
            String ra="";
            try {
                
                List<Gbra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ra = XXGbraListaSap.get(0).getRaDoDia();                        
                }
                
            }catch( Exception e ){}
            
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            
            int EstoqueMinimo = 0;
            try {
                EstoqueMinimo = XXGbzrisListaSap.get(0).getEstoqueMinimo();
            }catch( Exception e ){}
            
            String disponibilidade="";
            try {
                disponibilidade = String.valueOf(XXGbzrisListaSap.get(0).getDisponibilidade());
                if( disponibilidade.equals("null") ){ disponibilidade=""; }
            }catch( Exception e ){}
            
            String venda_ultima_semana="";
            try {
                venda_ultima_semana = String.valueOf(XXGbzrisListaSap.get(0).getVendaSemanaAnterior());
                if( venda_ultima_semana.equals("null") ){ venda_ultima_semana=""; }
            }catch( Exception e ){}
            
            String venda_4_semanas="";
            try {
                venda_4_semanas = String.valueOf(XXGbzrisListaSap.get(0).getVenda4Semanas());
                if( venda_4_semanas.equals("null") ){ venda_4_semanas=""; }
            }catch( Exception e ){}
            
            String venda_12_semanas="";
            try {
                venda_12_semanas = String.valueOf(XXGbzrisListaSap.get(0).getVenda12Semanas());
                if( venda_12_semanas.equals("null") ){ venda_12_semanas=""; }
            }catch( Exception e ){}   
            
            int ativosv = 0;
            try {
                
                List<Gbdiassemvenda> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdiassemvenda.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdiassemvenda>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdiassemvenda.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDIASSEMVENDA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ativosv = XXGbraListaSap.get(0).getDiassemvenda();                        
                }
                
            }catch( Exception e ){}
            
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            
            //tmPesquisa.addRow(new Object[]{ sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, dde, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana, descGrupCom });
        //}
        }  
  
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbAnaliseLojas;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAcao;
    private javax.swing.JRadioButton rbLisaps;
    private javax.swing.JRadioButton rbLojas;
    public javax.swing.JTextField tfLogradouro1;
    public javax.swing.JTextField tfLogradouro10;
    public javax.swing.JTextField tfLogradouro11;
    public javax.swing.JTextField tfLogradouro12;
    public javax.swing.JTextField tfLogradouro13;
    public javax.swing.JTextField tfLogradouro14;
    public javax.swing.JTextField tfLogradouro2;
    public javax.swing.JTextField tfLogradouro3;
    public javax.swing.JTextField tfLogradouro4;
    public javax.swing.JTextField tfLogradouro5;
    public javax.swing.JTextField tfLogradouro6;
    public javax.swing.JTextField tfLogradouro7;
    public javax.swing.JTextField tfLogradouro8;
    public javax.swing.JTextField tfLogradouro9;
    private javax.swing.JTextField tfPa;
    private javax.swing.JTextPane tpListLojas;
    private javax.swing.JTextPane tpListSapLoja;
    private javax.swing.JTextPane tpListSaps;
    // End of variables declaration//GEN-END:variables

    private void relatorio_g1g4g6Geral2(){ 
        try {
            String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + "G1 G4 G6 GERAL" +  ".xlsx";     
            Arquivo_Ou_Pasta.deletar(new File(aplicativo));
        
            FileInputStream fileIn = null;
            FileOutputStream fileOut = null;
            
            XSSFWorkbook wb  = null;
            XSSFSheet    aba = null; 
            
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            try {
                Thread.sleep( 1000 );                
                Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                
                fileIn = new FileInputStream("MODELO G1 G4 G6 GERAL.xlsx");
                wb = new XSSFWorkbook(fileIn); 
                aba = wb.getSheetAt(0);  
            }catch( Exception e ){ }
            
            
        }catch( Exception e ){ }
    }
    
    private boolean cadastrarBoo2 = false;
    private void relatorio_g1g4g6Geral(){
        new Thread() { @Override public void run() {    
            
                while ( cadastrarBoo2 == false ) {   
                        cadastrarBoo2 = true;
                        try { Thread.sleep( 1 );
                            fechar(); 
                            
                            String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + "G1 G4 G6 GERAL" +  ".xlsx";     
                            Arquivo_Ou_Pasta.deletar(new File(aplicativo));
                            
                            Thread.sleep( 1000 );
                            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                                   
                            
                            FileInputStream fileIn = null;
                            FileOutputStream fileOut = null;
                            
                            
                            try{
                                
                                fileIn = new FileInputStream("MODELO G1 G4 G6 GERAL.xlsx");
                                XSSFWorkbook wb = new XSSFWorkbook(fileIn); 
                                Thread.sleep( 1000 );
                                XSSFSheet aba = wb.getSheetAt(0); 
                                                                
                                
                                //Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBMRP WHERE MRP = ? OR ( MRP = ? OR MRP = ? )", Gbmrp.class );
                                Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBMRP WHERE MRP = ?", Gbmrp.class );
                                q.setParameter( 1, "G6" ); 
                                //q.setParameter( 1, "G1" ); 
                                //q.setParameter( 2, "G4" ); 
                                //q.setParameter( 3, "G6" ); 
                                List<Gbmrp> Gbmrp = q.getResultList(); 
                                
                                
                                Exportando.pbg.setMaximum( Gbmrp.size() );
                                                                                    
                                XSSFCell cell;  
                                for ( int i = 0; i<Gbmrp.size(); i++) { Thread.sleep( 1 );
                                    Exportando.pbg.setValue( i );
                                    
                                    try{ 
                                        int linhaParaescrever = i+2;
                                        XSSFRow linha = aba.getRow(linhaParaescrever);
                                    
                                        if (linha != null){
                                    
                                            for ( int c = 0; c<14; c++) {   
                                        
                                                
                                                cell = linha.getCell(c);         
                                                if (cell == null) 
                                                    cell = linha.createCell(c); 
                                                
                                                    try{ cell.setCellType(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){System.out.println( "-1: " + e.getMessage()  ); e.printStackTrace();}
                                                    try{ cell.setCellValue(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){System.out.println( "0: " + e.getMessage()  ); e.printStackTrace();}
                            
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                        
                                                    Gbproduto Gbproduto = new Gbproduto();
                                                    List<Gbproduto> XXGbProdListaSap = null;
                                                    
                                                    try {
                                                        try{ 
                                                            
                                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                                            XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbmrp.get(i).getMaterial() );
                                                        }catch( Exception e ){ }
                                                        
                                                        String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                                        if( !rbusca.equals("") ){                    
                                                            Gbproduto = XXGbProdListaSap.get(0);                        
                                                        }
                                                    }catch( Exception e ){}
                                                    
                                                    String sapTab   = "";  try { sapTab   = Gbproduto.getMaterial();    }catch( Exception e ){}            
                                                    String ean      = "";  try { ean      = Gbproduto.getEan();         }catch( Exception e ){}            
                                                    String nomeProd = "";  try { nomeProd = Gbproduto.getDescricao();   }catch( Exception e ){}            
                                                    String qtdEmEs  = "";  try { qtdEmEs  = Gbproduto.getQtdxes();      }catch( Exception e ){}          
                                                    String Und      = "";  try { Und      = Gbproduto.getUnd();         }catch( Exception e ){}
                                                    
            /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            /*SETOR*/
            List<Gbdescricaosetor> XXGbdescricaosetor = null;
            String descricaosetor="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                    XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor();                        
                }
                
            }catch( Exception e ){}
            /*SETOR*/
            
            String grupo = "";  try { grupo = Gbproduto.getGrupo();  }catch( Exception e ){}
            
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            
            int b141 = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b141 = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            
            String sugestao ="";
            try {
                sugestao = "";
            }catch( Exception e ){}
            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                        
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpedidosativos.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7D = XXGbpedidosativosListaSap.get(0).getPedidoCd184Ativo();                        
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
            
            String remessa="";
            try {
                remessa = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa();
                if( remessa.equals("null") ){ remessa=""; }
            }catch( Exception e ){}
            
            String saiuCD="";
            try {
                saiuCD = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                if( saiuCD.equals("null") ){ saiuCD=""; }
            }catch( Exception e ){}
            
            String entrouLJ="";
            try {
                entrouLJ = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                if( entrouLJ.equals("null") ){ entrouLJ=""; }
            }catch( Exception e ){}
            
            int dde = 0;
            try {
                dde = XXGbzrisListaSap.get(0).getDde();
            }catch( Exception e ){}
            
            String cdOntem="";
            try {
                
                List<Gbentroucdontem> XXGbentroucdontemListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbentroucdontem.class, JPAUtil.em());
                                        
                    XXGbentroucdontemListaSap = (List<Gbentroucdontem>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbentroucdontem.class, 
                            "SELECT * FROM SCHEMAJMARY.GBENTROUCDONTEM WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbentroucdontemListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    cdOntem = XXGbentroucdontemListaSap.get(0).getEntrouCd184Ontem();                        
                }
                
            }catch( Exception e ){}
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            String setor="";
            try {
                setor = XXGbsuplyListaSap.get(0).getNomSetor();
                if( setor.equals("") || setor.equals("null") ){ setor=""; }
            }catch( Exception e ){}
            
            String status="";
            try {
                status = XXGbsuplyListaSap.get(0).getStsEstoquetipo();
                if( status.equals("") || status.equals("null") ){ status=""; }
            }catch( Exception e ){}
            
            String codForn="";
            try {
                codForn = XXGbsuplyListaSap.get(0).getCodFornecedorlof();
                if( codForn.equals("") || codForn.equals("null") ){ codForn=""; }
            }catch( Exception e ){}
            
            String nomForn="";
            try {
                nomForn = XXGbsuplyListaSap.get(0).getNomFornecedorgrupoeconomico();
                if( nomForn.equals("") || nomForn.equals("null") ){ nomForn=""; }
            }catch( Exception e ){}
            
            String ra="";
            try {
                
                List<Gbra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ra = XXGbraListaSap.get(0).getRaDoDia();                        
                }
                
            }catch( Exception e ){}
            
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            
            int EstoqueMinimo = 0;
            try {
                EstoqueMinimo = XXGbzrisListaSap.get(0).getEstoqueMinimo();
            }catch( Exception e ){}
            
            String disponibilidade="";
            try {
                disponibilidade = String.valueOf(XXGbzrisListaSap.get(0).getDisponibilidade());
                if( disponibilidade.equals("null") ){ disponibilidade=""; }
            }catch( Exception e ){}
            
            String venda_ultima_semana="";
            try {
                venda_ultima_semana = String.valueOf(XXGbzrisListaSap.get(0).getVendaSemanaAnterior());
                if( venda_ultima_semana.equals("null") ){ venda_ultima_semana=""; }
            }catch( Exception e ){}
            
            String venda_4_semanas="";
            try {
                venda_4_semanas = String.valueOf(XXGbzrisListaSap.get(0).getVenda4Semanas());
                if( venda_4_semanas.equals("null") ){ venda_4_semanas=""; }
            }catch( Exception e ){}
            
            String venda_12_semanas="";
            try {
                venda_12_semanas = String.valueOf(XXGbzrisListaSap.get(0).getVenda12Semanas());
                if( venda_12_semanas.equals("null") ){ venda_12_semanas=""; }
            }catch( Exception e ){}   
            
            int ativosv = 0;
            try {
                
                List<Gbdiassemvenda> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdiassemvenda.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdiassemvenda>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdiassemvenda.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDIASSEMVENDA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ativosv = XXGbraListaSap.get(0).getDiassemvenda();                        
                }
                
            }catch( Exception e ){}
            
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            
            //tmPesquisa.addRow(new Object[]{ sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, dde, b141, sugestao, 
            //dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana, descGrupCom, descricaosetor });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
           
                                                    if(c==0){ 
                                                        try{
                                                        GregorianCalendar gc = new GregorianCalendar();
                                                        Date dataHoje = gc.getTime();
                                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                                        
                                                        String data_formatada = formatter.format( dataHoje );
                                                        
                                                        cell.setCellValue( data_formatada );
                                                        }catch(Exception e){}
                                                    }
                                                    else if(c==1){ String v = String.valueOf( descricaosetor ); cell.setCellValue( v ); }
                                                    else if(c==2){ String v = String.valueOf( descGrupCom ); cell.setCellValue( v ); } 
                                                    else if(c==3){ String v = String.valueOf( sapTab ); cell.setCellValue( v ); } 
                                                    else if(c==4){ String v = String.valueOf( nomeProd ); cell.setCellValue( v ); } 
                                                    else if(c==5){ String v = String.valueOf( mrp ); cell.setCellValue( v ); } 
                                                    else if(c==6){ String v = String.valueOf( qtdEmEs ); cell.setCellValue( v ); } 
                                                    else if(c==7){ String v = String.valueOf( Und ); cell.setCellValue( v ); } 
                                                    else if(c==8){ String v = String.valueOf( qtdEmPed ); cell.setCellValue( v ); } 
                                                    else if(c==9){ String v = String.valueOf( pontExtra ); cell.setCellValue( v ); } 
                                                    else if(c==10){ String v = String.valueOf( elenco ); cell.setCellValue( v ); } 
                                                    else if(c==11){ String v = String.valueOf( dde ); cell.setCellValue( v ); } 
                                                    else if(c==12){ String v = String.valueOf( b141 ); cell.setCellValue( v ); } 
                                                    else if(c==13){ String v = String.valueOf( sugestao ); cell.setCellValue( v ); } 
                                                    else if(c==14){ String v = String.valueOf( disponibilidade ); cell.setCellValue( v ); } 
                                                    else if(c==15){ String v = String.valueOf( ped7D ); cell.setCellValue( v ); } 
                                                    else if(c==16){ String v = String.valueOf( remessa ); cell.setCellValue( v ); } 
                                                    else if(c==17){ String v = String.valueOf( saiuCD ); cell.setCellValue( v ); } 
                                                    else if(c==18){ String v = String.valueOf( entrouLJ ); cell.setCellValue( v ); } 
                                                    else if(c==19){ String v = String.valueOf( venda_ultima_semana ); cell.setCellValue( v ); } 
                                                    else if(c==20){ String v = String.valueOf( venda_4_semanas ); cell.setCellValue( v ); } 
                                                    else if(c==21){ String v = String.valueOf( venda_12_semanas ); cell.setCellValue( v ); } 
                                                    else if(c==22){ String v = String.valueOf( b001 ); cell.setCellValue( v ); } 
                                                    else if(c==23){ String v = String.valueOf( b289 ); cell.setCellValue( v ); } 
                                                    else if(c==24){ String v = String.valueOf( b184 ); cell.setCellValue( v ); } 
                                                    else if(c==25){ String v = String.valueOf( cdOntem ); cell.setCellValue( v ); } 
                                                    else if(c==26){ String v = String.valueOf( status ); cell.setCellValue( v ); } 
                                                    else if(c==27){ String v = String.valueOf( codForn ); cell.setCellValue( v ); } 
                                                    else if(c==28){ String v = String.valueOf( nomForn ); cell.setCellValue( v ); } 
                                                    else if(c==29){ String v = String.valueOf( ra ); cell.setCellValue( v ); } 
                                                    else if(c==30){ String v = String.valueOf( EstoqueMinimo ); cell.setCellValue( v ); } 
                                                    else if(c==31){ String v = String.valueOf( ean ); cell.setCellValue( v ); } 
                                                    else if(c==32){ String v = String.valueOf( grupo ); cell.setCellValue( v ); } 
                                                    else if(c==33){ String v = String.valueOf( ativosv ); cell.setCellValue( v ); } 
                                                    else if(c==34){ String v = String.valueOf( opSemana ); cell.setCellValue( v ); } 

                                            }
                                        }                                    
                                    }catch(Exception e){System.out.println( "1: " + e.getMessage()  ); e.printStackTrace();}
                                    
                                }
                                
                                wb.setForceFormulaRecalculation(true);
                                fileOut = new FileOutputStream("G1 G4 G6 GERAL.xlsx");
                                wb.write(fileOut);
                                
                                try{
                                    fileOut.close(); 
                                    fileIn.close(); 
                                } catch(Exception e) {}
                                
                                abrirExcelg1g4g6();
                            } catch(Exception e) {                                
                                System.out.println( "2: " + e.getMessage()  ); e.printStackTrace();
                            }
        
                                                            
                            Exportando.fechar();
                            //fechar();                     
                        }catch( Exception e ){ 
                            /*Exportando.fechar();*/ fechar(); 
                            System.out.println( "2: " + e.getMessage()  ); 
                            e.printStackTrace();
                        }
}
///////////////////                
            } }.start();
    }
    
    private void abrirExcelg1g4g6() throws IOException{
        String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + "G1 G4 G6 GERAL" +  ".xlsx";     
        java.awt.Desktop.getDesktop().open( new File( aplicativo ) ); 

    }
   
    private void Exportar(JTable tablaD){
            String aplicativo = System.getProperty("user.dir") + System.getProperty("file.separator") + "G1 G4 G6 GERAL" +  ".xlsx";     
            Arquivo_Ou_Pasta.deletar(new File(aplicativo));
                                
            try {
                fileIn = new FileInputStream("MODELO G1 G4 G6 GERAL.xlsx");
                wb = new XSSFWorkbook(fileIn); 
                aba = wb.getSheetAt(0);  
            }catch( Exception e ){ }
            
        Exportando = new Exportando("Exportando");
        Exportando.setVisible(true);
        
        int numFila=tablaD.getRowCount(), numColumna=tablaD.getColumnCount();   
        Exportando.pbg.setMinimum(0);
        Exportando.pbg.setMaximum(numFila);
                
        try {
            for (int i = -1; i < numFila; i++) { 
                int linhaParaescrever = i+2;
                XSSFRow linha = aba.getRow(linhaParaescrever);
                
                for (int j = 0; j < numColumna; j++) { 
                    XSSFCell celula = linha.getCell(j); 
                    if (celula == null) celula = linha.createCell(j);

                    if(i==-1){
                        celula.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                    }else{
                        if ( !String.valueOf(tablaD.getValueAt(i, j)).equals("null") )
                        celula.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
                    }
     
                }
                
                Exportando.pbg.setValue( i );                                
            }
            
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
                wb.setForceFormulaRecalculation(true);
                fileOut = new FileOutputStream("G1 G4 G6 GERAL.xlsx");
                wb.write(fileOut);
                
                try{
                    fileOut.close(); 
                    fileIn.close(); 
                } catch(Exception e) {}
                
                abrirExcelg1g4g6();
            } catch( Exception e ){ System.out.println("Exportar - "); e.printStackTrace(); } } }.start();      
            
            Exportando.fechar();

        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }

    }
    
    //RELATÓRIO DISPONIBILIDADE BAIXA POR SETOR EM RUPTURA NO CD 
    String op = "";
    int paRuptura = 0;
    private void oportunidadeDispBaixaPorSetorRupturaCD( String op2, int paRuptura2){ op = op2; paRuptura = paRuptura2;
        new Thread() { @Override public void run() { 
            
        String opX = op;
        int paRupturaX = paRuptura;
            
        Exportando = new Exportando(opX);
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
            
        try{                                                              
                
            List<Gbzris> XXGbListaZrisLojas = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(rbLojas.isSelected() == true && rbLisaps.isSelected() == false ){
                
                String str[] = tpListLojas.getText().split("\n");
                
                String str1 = ""; try{ str1 = str[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                
                            List<Gbzris> XXGbListaZrisTemp = new ArrayList();
                                
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBZRIS WHERE ESTABELECIMENTO = ?", Gbzris.class );           
                            q5.setParameter(1, loja );
                            XXGbListaZrisTemp = q5.getResultList();

                            for (int x = 0; x < XXGbListaZrisTemp.size(); x++){
                                    
                                XXGbListaZrisLojas.add( XXGbListaZrisTemp.get(x) );
                            }
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            else if(rbLojas.isSelected() == true && rbLisaps.isSelected() == true ){
                
                String str[]     = tpListLojas.getText().split("\n"); 
                String strSaps[] = tpListSaps.getText().split("\n");         
                
                String str1     = ""; try{ str1     = str[0];     }catch( Exception e ){}
                String strSaps1 = ""; try{ strSaps1 = strSaps[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") && !strSaps1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                    
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int y = 0; y < strSaps.length; y++){ 
                                    
                                    try{  
                                        
                                        String strSap = strSaps[y].trim();
                                        
                                        List<Gbzris> XXGbListaZrisTempSap = null;
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                                            XXGbListaZrisTempSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", strSap, loja );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXGbListaZrisTempSap.get(0).getMaterial(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){                    
                                            XXGbListaZrisLojas.add( XXGbListaZrisTempSap.get(0) );               
                                        }
                                        
                                    } catch( Exception e ){e.printStackTrace();System.out.println("two");}    
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////                                
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListaZrisLojas.size());
                
                for (int i = 0; i < XXGbListaZrisLojas.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                            
                    ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", XXGbListaZrisLojas.get(i).getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
                    ////////////////////////////////////////////////////////////////////
                    
                    boolean auth = false;
                    
                    try{
                        switch (opX) {
                            case "BAZAR":
                                if( descricaosetor.equals("BAZAR") ){       auth = true; }
                                else if( descricaosetor.equals("TEXTIL") ){ auth = true; }
                                break;
                            case "HIPEL BEBIDAS MERCEARIA":
                                if( descricaosetor.equals("HIPEL") ){          auth = true; }
                                else if( descricaosetor.equals("MERCEARIA") ){ auth = true; }
                                else if( descricaosetor.equals("BEBIDAS") ){   auth = true; }
                                break;
                            case "PERECÍVEIS":
                                if( descricaosetor.equals("PER IN NATURA") ){             auth = true; }
                                else if( descricaosetor.equals("PER INDUSTRIALIZADOS") ){ auth = true; }
                                else if( descricaosetor.equals("PERECÍVEIS") ){ auth = true; }
                                break;
                            case "ELETRO":
                                if( descricaosetor.equals("ELETRO") ){ auth = true; }
                                break;
                        }
                    } catch( Exception e ){}
                    
                    if(auth==true){
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListaZrisLojas.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                            /*
                            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
                            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
                            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                            */
                            String zloja = ""; try{ zloja = XXGbListaZrisLojas.get(i).getEstabelecimento(); }catch( Exception e ){}
                            String zsap  = ""; try{ zsap  = XXGbListaZrisLojas.get(i).getMaterial(); }catch( Exception e ){}
                            String zdisp = ""; try{ zdisp = String.valueOf( XXGbListaZrisLojas.get(i).getDisponibilidade() ); }catch( Exception e ){}
                            String es    = ""; try{ es    = String.valueOf( XXGbListaZrisLojas.get(i).getQtdxemb() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), zloja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
                                if(esto <= XXGbListaZrisLojas.get(i).getDisponibilidade()){             
                                                                                                            
                                    if(mrp.equals("G1")){
                                        
                                        if(b001 < paRupturaX){
                                                                                        
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B001"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b001); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                   
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                        }
                                    }
                                    else if(mrp.equals("G4")){
                                        
                                        if(b184 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B184"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b184); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B184" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }
                                    else if(mrp.equals("G6")){
                                        
                                        if(b289 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B289"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++;
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b289); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}               
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B289" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }                                    
                                }                                                           
                        }
                    }
                }  
                
                String saida = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
                String entrada = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Modelos" + 
                System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
                //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
                ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
                ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, opX, "MODELO OPORTUNIDADE.xlsx" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
    
    private void oportunidadeDispBaixaPorSetorRupturaCDTodosSetores( String op2, int paRuptura2){ op = op2; paRuptura = paRuptura2;
        new Thread() { @Override public void run() { 
            
        String opX = op;
        int paRupturaX = paRuptura;
            
        Exportando = new Exportando(opX);
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
            
        try{                                                              
                
            List<Gbzris> XXGbListaZrisLojas = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(rbLojas.isSelected() == true && rbLisaps.isSelected() == false ){
                
                String str[] = tpListLojas.getText().split("\n");
                
                String str1 = ""; try{ str1 = str[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                
                            List<Gbzris> XXGbListaZrisTemp = new ArrayList();
                                
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBZRIS WHERE ESTABELECIMENTO = ?", Gbzris.class );           
                            q5.setParameter(1, loja );
                            XXGbListaZrisTemp = q5.getResultList();

                            for (int x = 0; x < XXGbListaZrisTemp.size(); x++){
                                    
                                XXGbListaZrisLojas.add( XXGbListaZrisTemp.get(x) );
                            }
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            else if(rbLojas.isSelected() == true && rbLisaps.isSelected() == true ){
                
                String str[]     = tpListLojas.getText().split("\n"); 
                String strSaps[] = tpListSaps.getText().split("\n");         
                
                String str1     = ""; try{ str1     = str[0];     }catch( Exception e ){}
                String strSaps1 = ""; try{ strSaps1 = strSaps[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") && !strSaps1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                    
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int y = 0; y < strSaps.length; y++){ 
                                    
                                    try{  
                                        
                                        String strSap = strSaps[y].trim();
                                        
                                        List<Gbzris> XXGbListaZrisTempSap = null;
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                                            XXGbListaZrisTempSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", strSap, loja );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXGbListaZrisTempSap.get(0).getMaterial(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){                    
                                            XXGbListaZrisLojas.add( XXGbListaZrisTempSap.get(0) );               
                                        }
                                        
                                    } catch( Exception e ){e.printStackTrace();System.out.println("two");}    
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////                                
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListaZrisLojas.size());
                
                for (int i = 0; i < XXGbListaZrisLojas.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                            
                    ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", XXGbListaZrisLojas.get(i).getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
                    ////////////////////////////////////////////////////////////////////
                    
                    boolean auth = true;
                    
                    /*try{
                        switch (opX) {
                            case "BAZAR":
                                if( descricaosetor.equals("BAZAR") ){       auth = true; }
                                else if( descricaosetor.equals("TEXTIL") ){ auth = true; }
                                break;
                            case "HIPEL BEBIDAS MERCEARIA":
                                if( descricaosetor.equals("HIPEL") ){          auth = true; }
                                else if( descricaosetor.equals("MERCEARIA") ){ auth = true; }
                                else if( descricaosetor.equals("BEBIDAS") ){   auth = true; }
                                break;
                            case "PERECÍVEIS":
                                if( descricaosetor.equals("PER IN NATURA") ){             auth = true; }
                                else if( descricaosetor.equals("PER INDUSTRIALIZADOS") ){ auth = true; }
                                else if( descricaosetor.equals("PERECÍVEIS") ){ auth = true; }
                                break;
                            case "ELETRO":
                                if( descricaosetor.equals("ELETRO") ){ auth = true; }
                                break;
                        }
                    } catch( Exception e ){}*/
                    
                    if(auth==true){
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListaZrisLojas.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                            /*
                            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
                            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
                            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                            */
                            String zloja = ""; try{ zloja = XXGbListaZrisLojas.get(i).getEstabelecimento(); }catch( Exception e ){}
                            String zsap  = ""; try{ zsap  = XXGbListaZrisLojas.get(i).getMaterial(); }catch( Exception e ){}
                            String zdisp = ""; try{ zdisp = String.valueOf( XXGbListaZrisLojas.get(i).getDisponibilidade() ); }catch( Exception e ){}
                            String es    = ""; try{ es    = String.valueOf( XXGbListaZrisLojas.get(i).getQtdxemb() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), zloja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
                                if(esto <= XXGbListaZrisLojas.get(i).getDisponibilidade()){             
                                                                                                            
                                    if(mrp.equals("G1")){
                                        
                                        if(b001 < paRupturaX){
                                                                                        
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B001"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b001); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                   
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                        }
                                    }
                                    else if(mrp.equals("G4")){
                                        
                                        if(b184 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B184"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b184); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B184" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }
                                    else if(mrp.equals("G6")){
                                        
                                        if(b289 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B289"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++;
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b289); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}               
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B289" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }                                    
                                }                                                           
                        }
                    }
                }  
                
                String saida = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
                String entrada = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Modelos" + 
                System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
                //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
                ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
                ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, opX, "MODELO OPORTUNIDADE.xlsx" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
    
    private void oportunidadeIndependenteDispIndependenteEstoqueCDTodosSetoresNovo( String op2, int paRuptura2){ op = op2; paRuptura = paRuptura2;
        new Thread() { @Override public void run() { 
            
        String opX = op;
        int paRupturaX = paRuptura;
            
        Exportando = new Exportando(opX);
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
            
        try{                                                              
                
            List<Gbzris> XXGbListaZrisLojas = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(rbLojas.isSelected() == true && rbLisaps.isSelected() == false ){
                
                String str[] = tpListLojas.getText().split("\n");
                
                String str1 = ""; try{ str1 = str[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                
                            List<Gbzris> XXGbListaZrisTemp = new ArrayList();
                                
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBZRIS WHERE ESTABELECIMENTO = ?", Gbzris.class );           
                            q5.setParameter(1, loja );
                            XXGbListaZrisTemp = q5.getResultList();

                            for (int x = 0; x < XXGbListaZrisTemp.size(); x++){
                                    
                                XXGbListaZrisLojas.add( XXGbListaZrisTemp.get(x) );
                            }
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            else if(rbLojas.isSelected() == true && rbLisaps.isSelected() == true ){
                
                String str[]     = tpListLojas.getText().split("\n"); 
                String strSaps[] = tpListSaps.getText().split("\n");         
                
                String str1     = ""; try{ str1     = str[0];     }catch( Exception e ){}
                String strSaps1 = ""; try{ strSaps1 = strSaps[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") && !strSaps1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                    
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int y = 0; y < strSaps.length; y++){ 
                                    
                                    try{  
                                        
                                        String strSap = strSaps[y].trim();
                                        
                                        List<Gbzris> XXGbListaZrisTempSap = null;
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                                            XXGbListaZrisTempSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", strSap, loja );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXGbListaZrisTempSap.get(0).getMaterial(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){                    
                                            XXGbListaZrisLojas.add( XXGbListaZrisTempSap.get(0) );               
                                        }
                                        
                                    } catch( Exception e ){e.printStackTrace();System.out.println("two");}    
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////                                
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListaZrisLojas.size());
                
                for (int i = 0; i < XXGbListaZrisLojas.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                            
                    ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", XXGbListaZrisLojas.get(i).getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
                    ////////////////////////////////////////////////////////////////////
                    
                    boolean auth = true;
                    
                    /*try{
                        switch (opX) {
                            case "BAZAR":
                                if( descricaosetor.equals("BAZAR") ){       auth = true; }
                                else if( descricaosetor.equals("TEXTIL") ){ auth = true; }
                                break;
                            case "HIPEL BEBIDAS MERCEARIA":
                                if( descricaosetor.equals("HIPEL") ){          auth = true; }
                                else if( descricaosetor.equals("MERCEARIA") ){ auth = true; }
                                else if( descricaosetor.equals("BEBIDAS") ){   auth = true; }
                                break;
                            case "PERECÍVEIS":
                                if( descricaosetor.equals("PER IN NATURA") ){             auth = true; }
                                else if( descricaosetor.equals("PER INDUSTRIALIZADOS") ){ auth = true; }
                                else if( descricaosetor.equals("PERECÍVEIS") ){ auth = true; }
                                break;
                            case "ELETRO":
                                if( descricaosetor.equals("ELETRO") ){ auth = true; }
                                break;
                        }
                    } catch( Exception e ){}*/
                    
                    if(auth==true){
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListaZrisLojas.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                            /*
                            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
                            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
                            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                            */
                            String zloja = ""; try{ zloja = XXGbListaZrisLojas.get(i).getEstabelecimento(); }catch( Exception e ){}
                            String zsap  = ""; try{ zsap  = XXGbListaZrisLojas.get(i).getMaterial(); }catch( Exception e ){}
                            String zdisp = ""; try{ zdisp = String.valueOf( XXGbListaZrisLojas.get(i).getDisponibilidade() ); }catch( Exception e ){}
                            String es    = ""; try{ es    = String.valueOf( XXGbListaZrisLojas.get(i).getQtdxemb() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), zloja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
                                //if(esto <= XXGbListaZrisLojas.get(i).getDisponibilidade()){             
                                                                                                            
                                    //if(mrpT.equals("G1")){
                                        
                                        /*if(b001 < paRupturaX){
                                            if(b184 < paRupturaX){
                                                if(b289 < paRupturaX){*/
                                                                                        
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            //Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B001"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,mrp); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b001); }catch( Exception e ){}
                                            String estcd184   = ""; try{ estcd184   = String.valueOf(b184); }catch( Exception e ){}
                                            String estcd289   = ""; try{ estcd289   = String.valueOf(b289); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++; 
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd184); x++; 
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd289); x++; 
                                            
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                                                             
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                        //}}}
                                    //}
                                    /*else if(mrpT.equals("G4")){
                                        
                                        if(b184 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B184"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b184); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B184" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }
                                    else if(mrpT.equals("G6")){
                                        
                                        if(b289 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B289"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++;
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b289); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}               
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B289" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }*/                                    
                                //}                                                           
                        }
                    }
                }  
                
                String saida = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
                String entrada = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Modelos" + 
                System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
                //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
                ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
                ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, opX, "MODELO OPORTUNIDADE.xlsx" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
    
    private void oportunidadeDispBaixaPorSetorRupturaCDTodosSetoresNovo( String op2, int paRuptura2){ op = op2; paRuptura = paRuptura2;
        new Thread() { @Override public void run() { 
            
        String opX = op;
        int paRupturaX = paRuptura;
            
        Exportando = new Exportando(opX);
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
            
        try{                                                              
                
            List<Gbzris> XXGbListaZrisLojas = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(rbLojas.isSelected() == true && rbLisaps.isSelected() == false ){
                
                String str[] = tpListLojas.getText().split("\n");
                
                String str1 = ""; try{ str1 = str[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                
                            List<Gbzris> XXGbListaZrisTemp = new ArrayList();
                                
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBZRIS WHERE ESTABELECIMENTO = ?", Gbzris.class );           
                            q5.setParameter(1, loja );
                            XXGbListaZrisTemp = q5.getResultList();

                            for (int x = 0; x < XXGbListaZrisTemp.size(); x++){
                                    
                                XXGbListaZrisLojas.add( XXGbListaZrisTemp.get(x) );
                            }
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            else if(rbLojas.isSelected() == true && rbLisaps.isSelected() == true ){
                
                String str[]     = tpListLojas.getText().split("\n"); 
                String strSaps[] = tpListSaps.getText().split("\n");         
                
                String str1     = ""; try{ str1     = str[0];     }catch( Exception e ){}
                String strSaps1 = ""; try{ strSaps1 = strSaps[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") && !strSaps1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                    
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int y = 0; y < strSaps.length; y++){ 
                                    
                                    try{  
                                        
                                        String strSap = strSaps[y].trim();
                                        
                                        List<Gbzris> XXGbListaZrisTempSap = null;
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                                            XXGbListaZrisTempSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", strSap, loja );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXGbListaZrisTempSap.get(0).getMaterial(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){                    
                                            XXGbListaZrisLojas.add( XXGbListaZrisTempSap.get(0) );               
                                        }
                                        
                                    } catch( Exception e ){e.printStackTrace();System.out.println("two");}    
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////                                
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListaZrisLojas.size());
                
                for (int i = 0; i < XXGbListaZrisLojas.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                            
                    ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", XXGbListaZrisLojas.get(i).getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
                    ////////////////////////////////////////////////////////////////////
                    
                    boolean auth = true;
                    
                    /*try{
                        switch (opX) {
                            case "BAZAR":
                                if( descricaosetor.equals("BAZAR") ){       auth = true; }
                                else if( descricaosetor.equals("TEXTIL") ){ auth = true; }
                                break;
                            case "HIPEL BEBIDAS MERCEARIA":
                                if( descricaosetor.equals("HIPEL") ){          auth = true; }
                                else if( descricaosetor.equals("MERCEARIA") ){ auth = true; }
                                else if( descricaosetor.equals("BEBIDAS") ){   auth = true; }
                                break;
                            case "PERECÍVEIS":
                                if( descricaosetor.equals("PER IN NATURA") ){             auth = true; }
                                else if( descricaosetor.equals("PER INDUSTRIALIZADOS") ){ auth = true; }
                                else if( descricaosetor.equals("PERECÍVEIS") ){ auth = true; }
                                break;
                            case "ELETRO":
                                if( descricaosetor.equals("ELETRO") ){ auth = true; }
                                break;
                        }
                    } catch( Exception e ){}*/
                    
                    if(auth==true){
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListaZrisLojas.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                            /*
                            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
                            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
                            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                            */
                            String zloja = ""; try{ zloja = XXGbListaZrisLojas.get(i).getEstabelecimento(); }catch( Exception e ){}
                            String zsap  = ""; try{ zsap  = XXGbListaZrisLojas.get(i).getMaterial(); }catch( Exception e ){}
                            String zdisp = ""; try{ zdisp = String.valueOf( XXGbListaZrisLojas.get(i).getDisponibilidade() ); }catch( Exception e ){}
                            String es    = ""; try{ es    = String.valueOf( XXGbListaZrisLojas.get(i).getQtdxemb() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), zloja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
                                if(esto <= XXGbListaZrisLojas.get(i).getDisponibilidade()){             
                                                                                                            
                                    //if(mrpT.equals("G1")){
                                        
                                        if(b001 < paRupturaX){
                                            if(b184 < paRupturaX){
                                                if(b289 < paRupturaX){
                                                                                        
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            //Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B001"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,mrp); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b001); }catch( Exception e ){}
                                            String estcd184   = ""; try{ estcd184   = String.valueOf(b184); }catch( Exception e ){}
                                            String estcd289   = ""; try{ estcd289   = String.valueOf(b289); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++; 
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd184); x++; 
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd289); x++; 
                                            
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                                                             
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                        }}}
                                    //}
                                    /*else if(mrpT.equals("G4")){
                                        
                                        if(b184 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B184"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b184); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B184" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }
                                    else if(mrpT.equals("G6")){
                                        
                                        if(b289 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B289"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++;
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b289); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}               
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B289" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }*/                                    
                                }                                                           
                        }
                    }
                }  
                
                String saida = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
                String entrada = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Modelos" + 
                System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
                //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
                ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
                ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, opX, "MODELO OPORTUNIDADE.xlsx" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
    
    private void oportunidadeDispBaixaPorSetorCDPositivoTodosSetoresNovo( String op2, int paRuptura2){ op = op2; paRuptura = paRuptura2;
        new Thread() { @Override public void run() { 
            
        String opX = op;
        int paRupturaX = paRuptura;
            
        Exportando = new Exportando(opX);
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
            
        try{                                                              
                
            List<Gbzris> XXGbListaZrisLojas = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(rbLojas.isSelected() == true && rbLisaps.isSelected() == false ){
                
                String str[] = tpListLojas.getText().split("\n");
                
                String str1 = ""; try{ str1 = str[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                
                            List<Gbzris> XXGbListaZrisTemp = new ArrayList();
                                
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBZRIS WHERE ESTABELECIMENTO = ?", Gbzris.class );           
                            q5.setParameter(1, loja );
                            XXGbListaZrisTemp = q5.getResultList();

                            for (int x = 0; x < XXGbListaZrisTemp.size(); x++){
                                    
                                XXGbListaZrisLojas.add( XXGbListaZrisTemp.get(x) );
                            }
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            else if(rbLojas.isSelected() == true && rbLisaps.isSelected() == true ){
                
                String str[]     = tpListLojas.getText().split("\n"); 
                String strSaps[] = tpListSaps.getText().split("\n");         
                
                String str1     = ""; try{ str1     = str[0];     }catch( Exception e ){}
                String strSaps1 = ""; try{ strSaps1 = strSaps[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") && !strSaps1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                    
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int y = 0; y < strSaps.length; y++){ 
                                    
                                    try{  
                                        
                                        String strSap = strSaps[y].trim();
                                        
                                        List<Gbzris> XXGbListaZrisTempSap = null;
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                                            XXGbListaZrisTempSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", strSap, loja );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXGbListaZrisTempSap.get(0).getMaterial(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){                    
                                            XXGbListaZrisLojas.add( XXGbListaZrisTempSap.get(0) );               
                                        }
                                        
                                    } catch( Exception e ){e.printStackTrace();System.out.println("two");}    
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////                                
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListaZrisLojas.size());
                
                for (int i = 0; i < XXGbListaZrisLojas.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                            
                    ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", XXGbListaZrisLojas.get(i).getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
                    ////////////////////////////////////////////////////////////////////
                    
                    boolean auth = true;
                    
                    /*try{
                        switch (opX) {
                            case "BAZAR":
                                if( descricaosetor.equals("BAZAR") ){       auth = true; }
                                else if( descricaosetor.equals("TEXTIL") ){ auth = true; }
                                break;
                            case "HIPEL BEBIDAS MERCEARIA":
                                if( descricaosetor.equals("HIPEL") ){          auth = true; }
                                else if( descricaosetor.equals("MERCEARIA") ){ auth = true; }
                                else if( descricaosetor.equals("BEBIDAS") ){   auth = true; }
                                break;
                            case "PERECÍVEIS":
                                if( descricaosetor.equals("PER IN NATURA") ){             auth = true; }
                                else if( descricaosetor.equals("PER INDUSTRIALIZADOS") ){ auth = true; }
                                else if( descricaosetor.equals("PERECÍVEIS") ){ auth = true; }
                                break;
                            case "ELETRO":
                                if( descricaosetor.equals("ELETRO") ){ auth = true; }
                                break;
                        }
                    } catch( Exception e ){}*/
                    
                    if(auth==true){
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListaZrisLojas.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                            /*
                            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
                            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
                            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                            */
                            String zloja = ""; try{ zloja = XXGbListaZrisLojas.get(i).getEstabelecimento(); }catch( Exception e ){}
                            String zsap  = ""; try{ zsap  = XXGbListaZrisLojas.get(i).getMaterial(); }catch( Exception e ){}
                            String zdisp = ""; try{ zdisp = String.valueOf( XXGbListaZrisLojas.get(i).getDisponibilidade() ); }catch( Exception e ){}
                            String es    = ""; try{ es    = String.valueOf( XXGbListaZrisLojas.get(i).getQtdxemb() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), zloja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
                                if(esto <= XXGbListaZrisLojas.get(i).getDisponibilidade()){             
                                                                                                            
                                    //if(mrpT.equals("G1")){
                                        
                                        if( (b001 > paRupturaX) || (b184 > paRupturaX) || (b289 > paRupturaX) ){
                                                                                        
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            //Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B001"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,mrp); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b001); }catch( Exception e ){}
                                            String estcd184   = ""; try{ estcd184   = String.valueOf(b184); }catch( Exception e ){}
                                            String estcd289   = ""; try{ estcd289   = String.valueOf(b289); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++; 
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd184); x++; 
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd289); x++; 
                                            
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                                                             
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                        }
                                    //}
                                    /*else if(mrpT.equals("G4")){
                                        
                                        if(b184 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B184"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b184); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B184" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }
                                    else if(mrpT.equals("G6")){
                                        
                                        if(b289 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B289"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++;
                                            
                                            String estcd001   = ""; try{ estcd001   = String.valueOf(b289); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}               
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B289" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }*/                                    
                                }                                                           
                        }
                    }
                }  
                
                String saida = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
                String entrada = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Modelos" + 
                System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
                //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
                ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
                ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, opX, "MODELO OPORTUNIDADE.xlsx" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
        
    private void oportunidadeDispBaixaPorSetorCDPositivo( String op2, int paRuptura2){ op = op2; paRuptura = paRuptura2;
        new Thread() { @Override public void run() { 
            
        String opX = op;
        int paRupturaX = paRuptura;
            
        Exportando = new Exportando(opX);
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
            
        try{                                                              
                
            List<Gbzris> XXGbListaZrisLojas = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(rbLojas.isSelected() == true && rbLisaps.isSelected() == false ){
                
                String str[] = tpListLojas.getText().split("\n");
                
                String str1 = ""; try{ str1 = str[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                
                            List<Gbzris> XXGbListaZrisTemp = new ArrayList();
                                
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBZRIS WHERE ESTABELECIMENTO = ?", Gbzris.class );           
                            q5.setParameter(1, loja );
                            XXGbListaZrisTemp = q5.getResultList();

                            for (int x = 0; x < XXGbListaZrisTemp.size(); x++){
                                    
                                XXGbListaZrisLojas.add( XXGbListaZrisTemp.get(x) );
                            }
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            else if(rbLojas.isSelected() == true && rbLisaps.isSelected() == true ){
                
                String str[]     = tpListLojas.getText().split("\n"); 
                String strSaps[] = tpListSaps.getText().split("\n");         
                
                String str1     = ""; try{ str1     = str[0];     }catch( Exception e ){}
                String strSaps1 = ""; try{ strSaps1 = strSaps[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") && !strSaps1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                    
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int y = 0; y < strSaps.length; y++){ 
                                    
                                    try{  
                                        
                                        String strSap = strSaps[y].trim();
                                        
                                        List<Gbzris> XXGbListaZrisTempSap = null;
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                                            XXGbListaZrisTempSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", strSap, loja );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXGbListaZrisTempSap.get(0).getMaterial(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){                    
                                            XXGbListaZrisLojas.add( XXGbListaZrisTempSap.get(0) );               
                                        }
                                        
                                    } catch( Exception e ){e.printStackTrace();System.out.println("two");}    
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////                                
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListaZrisLojas.size());
                
                for (int i = 0; i < XXGbListaZrisLojas.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                            
                    ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", XXGbListaZrisLojas.get(i).getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
                    ////////////////////////////////////////////////////////////////////
                    
                    boolean auth = false;
                    
                    try{
                        switch (opX) {
                            case "BAZAR":
                                if( descricaosetor.equals("BAZAR") ){       auth = true; }
                                else if( descricaosetor.equals("TEXTIL") ){ auth = true; }
                                break;
                            case "HIPEL BEBIDAS MERCEARIA":
                                if( descricaosetor.equals("HIPEL") ){          auth = true; }
                                else if( descricaosetor.equals("MERCEARIA") ){ auth = true; }
                                else if( descricaosetor.equals("BEBIDAS") ){   auth = true; }
                                break;
                            case "PERECÍVEIS":
                                if( descricaosetor.equals("PER IN NATURA") ){             auth = true; }
                                else if( descricaosetor.equals("PER INDUSTRIALIZADOS") ){ auth = true; }
                                else if( descricaosetor.equals("PERECÍVEIS") ){ auth = true; }
                                break;
                            case "ELETRO":
                                if( descricaosetor.equals("ELETRO") ){ auth = true; }
                                break;
                        }
                    } catch( Exception e ){}
                    
                    if(auth==true){
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListaZrisLojas.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                            /*
                            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
                            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
                            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                            */
                            String zloja = ""; try{ zloja = XXGbListaZrisLojas.get(i).getEstabelecimento(); }catch( Exception e ){}
                            String zsap  = ""; try{ zsap  = XXGbListaZrisLojas.get(i).getMaterial(); }catch( Exception e ){}
                            String zdisp = ""; try{ zdisp = String.valueOf( XXGbListaZrisLojas.get(i).getDisponibilidade() ); }catch( Exception e ){}
                            String es    = ""; try{ es    = String.valueOf( XXGbListaZrisLojas.get(i).getQtdxemb() ); }catch( Exception e ){}
                            
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), zloja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
                                if(esto <= XXGbListaZrisLojas.get(i).getDisponibilidade()){             
                                                                                                            
                                    if(mrp.equals("G1")){
                                        
                                        if(b001 > paRupturaX){
                                                                                        
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B001"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b001); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                   
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                        }
                                    }
                                    else if(mrp.equals("G4")){
                                        
                                        if(b184 > paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B184"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b184); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B184" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }
                                    else if(mrp.equals("G6")){
                                        
                                        if(b289 > paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B289"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++;
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b289); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}               
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B289" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }                                    
                                }                                                           
                        }
                    }
                }  
                
                String saida = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
                String entrada = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Modelos" + 
                System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
                //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
                ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
                ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, opX, "MODELO OPORTUNIDADE.xlsx" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
        
    private void exporarExcel(JTable tablaD, String saida, String entrada){                
            Arquivo_Ou_Pasta.deletar(new File(saida));
                                
            try {
                fileIn = new FileInputStream(entrada);
                wb = new XSSFWorkbook(fileIn); 
                aba = wb.getSheetAt(0);  
            }catch( Exception e ){ }
            
        Exportando = new Exportando("EXPORT FOR EXCEL");
        Exportando.setVisible(true);
        
        int numFila=tablaD.getRowCount(), numColumna=tablaD.getColumnCount();   
        Exportando.pbg.setMinimum(0);
        Exportando.pbg.setMaximum(numFila);
                
        try {
            for (int i = -1; i < numFila; i++) { 
                int linhaParaescrever = i+2;
                XSSFRow linha = aba.getRow(linhaParaescrever);
                
                for (int j = 0; j < numColumna; j++) { 
                    XSSFCell celula = linha.getCell(j); 
                    if (celula == null) celula = linha.createCell(j);

                    if(i==-1){
                        celula.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                    }else{
                        if ( !String.valueOf(tablaD.getValueAt(i, j)).equals("null") )
                        celula.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
                    }
     
                }
                
                Exportando.pbg.setValue( i );                                
            }
            
            /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );
                wb.setForceFormulaRecalculation(true);
                fileOut = new FileOutputStream(saida);
                wb.write(fileOut);
                
                try{
                    fileOut.close(); 
                    fileIn.close(); 
                } catch(Exception e) {}
                
                abrirArquivoExcel("BAZAR");
            } catch( Exception e ){ System.out.println("Exportar - "); e.printStackTrace(); } //} }.start();      
            
            Exportando.fechar();

        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }

    }
    
    //RELATÓRIO DISPONIBILIDADE ALTA POR SETOR EM RUPTURA NO CD 
    private void oportunidadeDispAltaPorSetorRupturaCD( String op2, int paRuptura2){ op = op2; paRuptura = paRuptura2;
        new Thread() { @Override public void run() { 
            
        String opX = op;
        int paRupturaX = paRuptura;
            
        Exportando = new Exportando(opX);
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
            
        try{                                                              
                
            List<Gbzris> XXGbListaZrisLojas = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(rbLojas.isSelected() == true && rbLisaps.isSelected() == false ){
                
                String str[] = tpListLojas.getText().split("\n");
                
                String str1 = ""; try{ str1 = str[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                
                            List<Gbzris> XXGbListaZrisTemp = new ArrayList();
                                
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBZRIS WHERE ESTABELECIMENTO = ?", Gbzris.class );           
                            q5.setParameter(1, loja );
                            XXGbListaZrisTemp = q5.getResultList();

                            for (int x = 0; x < XXGbListaZrisTemp.size(); x++){
                                    
                                XXGbListaZrisLojas.add( XXGbListaZrisTemp.get(x) );
                            }
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            else if(rbLojas.isSelected() == true && rbLisaps.isSelected() == true ){
                
                String str[]     = tpListLojas.getText().split("\n"); 
                String strSaps[] = tpListSaps.getText().split("\n");         
                
                String str1     = ""; try{ str1     = str[0];     }catch( Exception e ){}
                String strSaps1 = ""; try{ strSaps1 = strSaps[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") && !strSaps1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                    
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int y = 0; y < strSaps.length; y++){ 
                                    
                                    try{  
                                        
                                        String strSap = strSaps[y].trim();
                                        
                                        List<Gbzris> XXGbListaZrisTempSap = null;
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                                            XXGbListaZrisTempSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", strSap, loja );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXGbListaZrisTempSap.get(0).getMaterial(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){                    
                                            XXGbListaZrisLojas.add( XXGbListaZrisTempSap.get(0) );               
                                        }
                                        
                                    } catch( Exception e ){e.printStackTrace();System.out.println("two");}    
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////                                
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListaZrisLojas.size());
                
                for (int i = 0; i < XXGbListaZrisLojas.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                            
                    ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", XXGbListaZrisLojas.get(i).getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
                    ////////////////////////////////////////////////////////////////////
                    
                    boolean auth = false;
                    
                    try{
                        switch (opX) {
                            case "BAZAR":
                                if( descricaosetor.equals("BAZAR") ){       auth = true; }
                                else if( descricaosetor.equals("TEXTIL") ){ auth = true; }
                                break;
                            case "HIPEL BEBIDAS MERCEARIA":
                                if( descricaosetor.equals("HIPEL") ){          auth = true; }
                                else if( descricaosetor.equals("MERCEARIA") ){ auth = true; }
                                else if( descricaosetor.equals("BEBIDAS") ){   auth = true; }
                                break;
                            case "PERECÍVEIS":
                                if( descricaosetor.equals("PER IN NATURA") ){             auth = true; }
                                else if( descricaosetor.equals("PER INDUSTRIALIZADOS") ){ auth = true; }
                                else if( descricaosetor.equals("PERECÍVEIS") ){ auth = true; }
                                break;
                            case "ELETRO":
                                if( descricaosetor.equals("ELETRO") ){ auth = true; }
                                break;
                        }
                    } catch( Exception e ){}
                    
                    if(auth==true){
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListaZrisLojas.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                            /*
                            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
                            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
                            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                            */
                            String zloja = ""; try{ zloja = XXGbListaZrisLojas.get(i).getEstabelecimento(); }catch( Exception e ){}
                            String zsap  = ""; try{ zsap  = XXGbListaZrisLojas.get(i).getMaterial(); }catch( Exception e ){}
                            String zdisp = ""; try{ zdisp = String.valueOf( XXGbListaZrisLojas.get(i).getDisponibilidade() ); }catch( Exception e ){}
                            String es    = ""; try{ es    = String.valueOf( XXGbListaZrisLojas.get(i).getQtdxemb() ); }catch( Exception e ){}
                            
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), zloja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
                                if(esto > XXGbListaZrisLojas.get(i).getDisponibilidade()){             
                                                                                                            
                                    if(mrp.equals("G1")){
                                        
                                        if(b001 < paRupturaX){
                                                                                        
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B001"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b001); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                   
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                        }
                                    }
                                    else if(mrp.equals("G4")){
                                        
                                        if(b184 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B184"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b184); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++;  
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B184" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }
                                    else if(mrp.equals("G6")){
                                        
                                        if(b289 < paRupturaX){
                                            
                                            int x = 0;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zloja); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"B289"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++;
                                            
                                            String estcd   = ""; try{ estcd   = String.valueOf(b289); }catch( Exception e ){}
                                            String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}               
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,es); x++;  
                                            //String linha = bazar.getDescricaosetor() +" - " + descGrupCom + " - " + listProduto.getMaterial() + " - " + listProduto.getDescricao() + " - " + "ESTOCADO" + " - " + "B289" + " - " +"DECER" + " - " + classe + " - " + pontExtra + " - " + elenco + " - " + opSemana;
                                        }
                                    }                                    
                                }                                                           
                        }
                    }
                }  
                
                String saida = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
                String entrada = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Modelos" + 
                System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
                //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
                ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
                ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, opX, "MODELO OPORTUNIDADE.xlsx" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
     
    XSSFSheet    aba = null; 
    XSSFWorkbook wb  = null;
    FileOutputStream fileOut = null;
    FileInputStream fileIn = null;
    Exportando Exportando = null;
        
    private void abrirArquivoExcel(String arq) throws IOException{
        String aplicativo = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + arq +  ".xlsx";     
        java.awt.Desktop.getDesktop().open( new File( aplicativo ) ); 

    }
    
    
    private void pedidoGeradoXEntrgue(){ 
        new Thread() { @Override public void run() { 
            
        Exportando = new Exportando("GERADO X ENTREGUE");
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.pedidoSetarCabecalhoTabelaGenerica();
            
        try{                                                              
                
            List<Gbpedidosativos> XXGbListappPedLojas = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(rbLojas.isSelected() == true && rbLisaps.isSelected() == false ){
                
                String str[] = tpListLojas.getText().split("\n");
                
                String str1 = ""; try{ str1 = str[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                
                            List<Gbpedidosativos> XXGbListaZrisTemp = new ArrayList();
                                
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE ESTABELECIMENTO = ?", Gbpedidosativos.class );           
                            q5.setParameter(1, loja );
                            XXGbListaZrisTemp = q5.getResultList();

                            for (int x = 0; x < XXGbListaZrisTemp.size(); x++){
                                    
                                XXGbListappPedLojas.add( XXGbListaZrisTemp.get(x) );
                            }
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            else if(rbLojas.isSelected() == true && rbLisaps.isSelected() == true ){
                
                String str[]     = tpListLojas.getText().split("\n"); 
                String strSaps[] = tpListSaps.getText().split("\n");         
                
                String str1     = ""; try{ str1     = str[0];     }catch( Exception e ){}
                String strSaps1 = ""; try{ strSaps1 = strSaps[0]; }catch( Exception e ){}
                                
                if( !str1.equals("") && !strSaps1.equals("") ){
                    
                    //System.out.println("str.length; - "+str.length);
                        
                    Exportando.pbg.setMaximum(str.length);

                    for (int i = 0; i < str.length; i++){ Exportando.pbg.setValue( i );
                            
                        try{         
                                
                            String loja = str[i].toUpperCase().trim();
                                    
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int y = 0; y < strSaps.length; y++){ 
                                    
                                    try{  
                                        
                                        String strSap = strSaps[y].trim();
                                        
                                        List<Gbpedidosativos> XXGbListaZrisTempSap = null;
                                        try{ 
                                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                        
                                            XXGbListaZrisTempSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", strSap, loja );
                                        }catch( Exception e ){ }
                
                                        String rbusca = ""; try{ rbusca = XXGbListaZrisTempSap.get(0).getMaterial(); }catch( Exception e ){}
                
                                        if( !rbusca.equals("") ){                    
                                            XXGbListappPedLojas.add( XXGbListaZrisTempSap.get(0) );               
                                        }
                                        
                                    } catch( Exception e ){e.printStackTrace();System.out.println("two");}    
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////                                
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
                    }
                    
                    habilitado = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListappPedLojas.size());
                
                
                for (int i = 0; i < XXGbListappPedLojas.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListappPedLojas.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                                 
                            String str = XXGbListappPedLojas.get(i).getPedidoCd184AtivoEntradaLoja();
                            String x  = str.replace(".", "");
                            String x2 = x.replace(",", ".");
                            double b2 = Double.parseDouble( x2 );
                            
                            if(b2 == 0){
                                
                                Menu.Tabela_Pesquisa_BD_Estabelecimento.pedidoAnaliseGenericaTabela( XXGbListappPedLojas.get(i).getEstabelecimento(),"", Gbproduto, XXGbListappPedLojas.get(i) );
                            }
                        }
                }  
                
                ExportarExcelExistentePedido ExportarExcelExistentePedido = new ExportarExcelExistentePedido();
                ExportarExcelExistentePedido.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, "PEDIDOS GERADOS E NÃO RECEBIDOS" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
    
    private void analiseRaPedidosZrep(){ 
        new Thread() { @Override public void run() { 
            
        Exportando = new Exportando("ANÁLISE RA - ZREP");
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoAnaliseRaPedidosZrep();
            
        try{                                                              
                
            List<Gbra> XXGbListGbra = new ArrayList();
                
            boolean habilitado = false;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            try{ 
                                
                Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBRA", Gbra.class );           
                XXGbListGbra = q5.getResultList();
                
                Exportando.pbg.setMaximum( XXGbListGbra.size() );

                String strSaps[] = tpListSaps.getText().split("\n");
                
                if( !strSaps.equals("") ){
                    
                    ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                    
                    for (int x = 0; x < XXGbListGbra.size(); x++){
                        Exportando.pbg.setValue( x );
                        
                        Gbproduto Gbproduto = null;
                        
                        String sap = ""; try{ sap = XXGbListGbra.get(x).getMaterial().trim(); }catch( Exception e ){ }
                       
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                            else{
                                        
                                String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+sap );
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                                
                            Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseRaPedidosZrep( XXGbListGbra.get(x).getEstabelecimento(),"", Gbproduto );
                           
                        }
                    }
                    
                    String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
                    if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                    habilitado = true;
                }                            
                                    
            } catch( Exception e ){e.printStackTrace();System.out.println("two");} 
            
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListGbra.size());
                
                Set<String> listaSap = new HashSet<String>();
                                                    
                ////////////////////////////////////////////////////////////////////
                String str[] = tpListSaps.getText().split("\n");
                if( !str.equals("") ){
                            
                    Exportando.pbg.setMaximum( str.length );
                            
                    for (int z = 0; z < str.length; z++){
                                
                        Exportando.pbg.setValue( z );
                        String nome = ""; try{ nome = str[z].trim(); }catch( Exception e ){} 
                        
                        listaSap.add( nome );
                    }
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.excluirLinhasSemThread(listaSap);
                } 
                
                ExportarExcelGenerico ExportarExcelExistenteRA = new ExportarExcelGenerico();
                ExportarExcelExistenteRA.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, "RA PRODUTOS NÃO LISTADOS", "MODELO RA PRODUTOS NÃO LISTADOS" );
                
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
    
    
    private void listarPorSetor( String op2){ op = op2; 
        new Thread() { @Override public void run() { 
            
        String opX = op;
            
        Exportando = new Exportando(opX);
        Exportando.setVisible(true);            
        Exportando.pbg.setMinimum(0);
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica();
            
        try{                                                              
                
            List<Gbdescricaosetor> XXGbListaGbdescricaosetor = new ArrayList();
                
            boolean habilitado = true;
                  
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////     
                        try{             
                            Query q5 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR", Gbdescricaosetor.class );           
                            XXGbListaGbdescricaosetor = q5.getResultList();

                            habilitado = true;
                            
                        } catch( Exception e ){e.printStackTrace();System.out.println("two");}               
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            if(habilitado==true){
                
                Exportando.pbg.setMaximum(XXGbListaGbdescricaosetor.size());
                
                for (int i = 0; i < XXGbListaGbdescricaosetor.size(); i++) {                                                                                   
                    try{ Exportando.pbg.setValue( i ); }catch(Exception e){}
                                                            
                    ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", XXGbListaGbdescricaosetor.get(i).getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
                    ////////////////////////////////////////////////////////////////////
                    
                    boolean auth = false;
                    
                    try{
                        switch (opX) {
                            case "BAZAR":
                                if( descricaosetor.equals("BAZAR") ){       auth = true; }
                                else if( descricaosetor.equals("TEXTIL") ){ auth = true; }
                                break;
                            case "HIPEL BEBIDAS MERCEARIA":
                                if( descricaosetor.equals("HIPEL") ){          auth = true; }
                                else if( descricaosetor.equals("MERCEARIA") ){ auth = true; }
                                else if( descricaosetor.equals("BEBIDAS") ){   auth = true; }
                                break;
                            case "PERECÍVEIS":
                                if( descricaosetor.equals("PER IN NATURA") ){             auth = true; }
                                else if( descricaosetor.equals("PER INDUSTRIALIZADOS") ){ auth = true; }
                                else if( descricaosetor.equals("PERECÍVEIS") ){ auth = true; }
                                break;
                            case "ELETRO":
                                if( descricaosetor.equals("ELETRO") ){ auth = true; }
                                break;
                        }
                    } catch( Exception e ){}
                    
                    if(auth==true){
                                                    
                        ////////////////////////////////////////////////////////////////////
                        Gbproduto Gbproduto = null;
                        
                        String sap = XXGbListaGbdescricaosetor.get(i).getMaterial().trim();
                        
                        //System.out.println("ESTOU AQUI - "+XXGbListaZrisLojas.get(i).getEstabelecimento()+" - "+sap+" - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
                                                                                                     
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                    "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){                    
                                Gbproduto = XXGbProdListaSap.get(0);                        
                            }
                        }catch( Exception e ){}
            
                        if( Gbproduto != null ){
                                              
                            Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela("", Gbproduto);
                        }
                    }
                }  
                /*
                String saida = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Gerados" + 
                System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
                String entrada = System.getProperty("user.dir") + 
                System.getProperty("file.separator") + "Relatorios" +
                System.getProperty("file.separator") + "Modelos" + 
                System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
                //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
                ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
                ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, opX );
                */
            }
            else{
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            Exportando.fechar();
                                    
            } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                    + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
            
        } }.start();
    }
    
    private void analiseAjusteEstoqueMinimo(){
        
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoAnaliseAjusteEstoqueMinimo();
        
        new Thread() {
            @Override
                public void run() {  
                    
                    boolean habilitado = false;
                    
                    int countLinha = 3;
                    
                        try { 
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");
                                                        
                            String str[] = tpListSapLoja.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                //fechar(); 
                                
                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
 
                                    String linha = str[i].trim();
                                    StringBuilder loja = new StringBuilder(); StringBuilder sap = new StringBuilder();
                                    
                                    int cuntP = 0;
                                    
                                    int b = 0;
                                    String anterior = "x";
                                    
                                    char c[] = linha.toCharArray();
                                    String v = "";
                                    for (int r = 0; r < c.length; r++){  
                                        
                                        v = String.valueOf( c[r] ).replace("/", ".").trim();
                                        if( v.equals("") ){
                                            
                                            if( !anterior.equals("") ){
                                                
                                                cuntP = 0;
                                                b++;
                                                //System.out.println( anterior );
                                            }
                                        }
                                        else if( !v.equals("") && b == 0 ){
                                            
                                            if( v.equals(".") ){
                                                cuntP++;
                                                
                                                if( cuntP < 2 ){
                                                    loja.append( v ); 
                                                }
                                            }    
                                            else if( cuntP < 2 ){
                                                
                                                loja.append( v ); 
                                            }
                                        }
                                        else if( !v.equals("") && b == 1 ){  
                                            
                                            if( v.equals(".") ){
                                                cuntP++;
                                                
                                                if( cuntP < 2 ){
                                                    sap.append( v ); 
                                                }
                                            }    
                                            else if( cuntP < 2 ){
                                                
                                                sap.append( v ); 
                                            }
                                        }
                                    }
                                    
                                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                                    
                                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                    try{ 
                                        
                                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                                    }catch( Exception e ){ }
                                    
                                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( busca.equals(rbusca) ){
                                        if( !busca.equals("") ){
                                            
                                            String lj = ""; try{ lj = loja.toString().toUpperCase().trim(); }catch( Exception e ){ }
                                                    
                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseAjusteEstoqueMinimo( lj,"", XXGbprodutoListaSap.get(0), countLinha );
                                            countLinha++;
                                        }
                                    }
                                    else{
                                        
                                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                    }
                                    
                                }    
                                
                                habilitado = true;
                            }
                            
                            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
                            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
                            Exportando.fechar();                     
                        }catch( Exception e ){ fechar(); }
                        
                        if(habilitado==true){
                            
                            ExportarExcelAjusteMinimo ExportarExcelAjusteMinimo = new ExportarExcelAjusteMinimo();
                            ExportarExcelAjusteMinimo.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, "AJUSTE DE ESTOQUE MÍNIMO", "MODELO AJUSTE DE ESTOQUE MÍNIMO" );
                        }
                        else{
                            Class<Imagens> clazzHome = Imagens.class;
                            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                                    + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                                    + "\nOK Para Prosseguir"
                                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                        }
                }
            }.start();
    }
    
    private void disponilidadeBaixaZeroNoCD( String planModelo ){ 
        
        String[] tmLayout = { "LOJA", "SETOR", "DESC. GRP COMPRA", "MATERIAL", "DESCRICAO DO MATERIAL", 
                             "MODALIDADE", "MRP", "REGIONAL", "CLASSE", "OPORTUNIDADE IDENTIFICADA", "PONTO EXTRA", 
                             "ELENCO", "O.S", "EST.LOJA (UND)", "DISP", "E/S", 
                             "EST. CD B184 (UND)", "FORNECEDOR 1 CD B184", "NOME FORNECEDOR 1 CD B184", "FORNECEDOR 2 CD B184", "NOME FORNECEDOR 2 CD B184", 
                             "PERFIL DISTRIBUIÇÃO CD B184", "ENDEREÇO      CD B184 ", "DATA ÚLTIMA ENTRADA CD B184", "LOF FORNECEDOR ÚLTIMA ENTRADA CD B184", "NOME FORNECEDOR ÚLTIMA ENTRADA CD B184", 
                             "EST. CD B289 (UND)", "FORNECEDOR 1 CD B289", "NOME FORNECEDOR 1 CD B289", "FORNECEDOR 2 CD B289", "NOME FORNECEDOR 2 CD B289", 
                             "PERFIL DISTRIBUIÇÃO CD B289", "ENDEREÇO      CD B289 ", "DATA ÚLTIMA ENTRADA CD B289", "LOF FORNECEDOR ÚLTIMA ENTRADA CD B289", "NOME FORNECEDOR ÚLTIMA ENTRADA CD B289"            
            };
        
        try{
                         
            Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa = new DefaultTableModel( null, tmLayout );
            
            while ( Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.getRowCount() > 0){ Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.removeRow(0); }
            
            Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setModel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa);
        } catch( Exception e ){}     
            
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
                                                        
            String str[] = tpListSapLoja.getText().split("\n");
            if( !str.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( str.length );
                                
                //fechar(); 
                                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                for (int i = 0; i < str.length; i++){
                    Exportando.pbg.setValue( i );
 
                    String linha = str[i].trim();
                    StringBuilder loja = new StringBuilder(); StringBuilder sap = new StringBuilder();
                                    
                    int cuntP = 0;
                                    
                    int b = 0;
                    String anterior = "x";
                                    
                    char c[] = linha.toCharArray();
                    String v = "";
                    for (int r = 0; r < c.length; r++){  
                                        
                        v = String.valueOf( c[r] ).replace("/", ".").trim();
                        if( v.equals("") ){
                                            
                            if( !anterior.equals("") ){
                                                
                                cuntP = 0;
                                b++;
                                //System.out.println( anterior );
                            }
                        }
                        else if( !v.equals("") && b == 0 ){
                                            
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    loja.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                loja.append( v ); 
                            }
                        }
                        else if( !v.equals("") && b == 1 ){  
                                          
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    sap.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                sap.append( v ); 
                            }
                        }
                    }
                                    
                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                                    
                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                    try{ 
                                        
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                    if( busca.equals(rbusca) ){
                        if( !busca.equals("") ){
                                            
                            String lj = ""; try{ lj = loja.toString().toUpperCase().trim(); }catch( Exception e ){ }
                              
                            disponilidadeBaixaZeroNoCD2( lj, XXGbprodutoListaSap.get(0), tmLayout.length );                            
                        }
                    }
                    else{
                                        
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                                    
                }    
                                
                habilitado = true;
            }
                            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ fechar(); }
                        
        if(habilitado==true){
               
            String saida = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
            ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, planModelo, "MODELO OPORTUNIDADE_2.xlsx" );
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
    }
    
    private void disponilidadeBaixaZeroNoCD2( String loja, Gbproduto Gbproduto, int tmLayout ){ 
              
        try{    
            
             /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            if( !qtdEmPed.equals("") ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = Gbproduto.getMaterial(); }catch( Exception e ){}
            String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
//////////////////            
            if(esto <= XXGbzrisListaSap.get(0).getDisponibilidade()){ 
//////////////////

            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
            
//////////////////            
            if(b001 == 0){ if(b184 == 0){ if(b289 == 0){
//////////////////            

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
                                          
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBMRP.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }            
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                /////////////////////////////////
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
                        
            /*LOJAS_A_ZDADOSLOG_CD_B184*/
            String posicao_B184 = "";
            String perfil_B184 = "";
            String zdlFONECEDOR_1_CD_B184 = "";
            String zdlFONECEDOR_2_CD_B184 = "";
            String zdl_Status_CD_B184 = "";
            try {
                
                List<LojasAZdadoslogCd> XXLojasAZvtraSort = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(LojasAZdadoslogCd.class, JPAUtil.em());
                                        
                    XXLojasAZvtraSort = (List<LojasAZdadoslogCd>) DAOGenericoJPAXX.getBeansCom_2_Parametro(LojasAZdadoslogCd.class, 
                            "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD WHERE MATERIAL = ? AND ( SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD.LOJA = ? )", Gbproduto.getMaterial(), "B184" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXLojasAZvtraSort.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    posicao_B184            = XXLojasAZvtraSort.get(0).getPosDpstZdadoslog();
                    if( posicao_B184.equals("NULL") ){posicao_B184 = "";}
                    
                    perfil_B184             = XXLojasAZvtraSort.get(0).getPerfDistZdadoslog();
                    if( perfil_B184.equals("NULL") ){perfil_B184 = "";}
                    
                    zdlFONECEDOR_1_CD_B184 = XXLojasAZvtraSort.get(0).getFonecedor1Zdadoslog();
                    if( zdlFONECEDOR_1_CD_B184.equals("NULL") ){zdlFONECEDOR_1_CD_B184 = "";}
                    
                    zdlFONECEDOR_2_CD_B184 = XXLojasAZvtraSort.get(0).getFonecedor2Zdadoslog();  
                    if( zdlFONECEDOR_2_CD_B184.equals("NULL") ){zdlFONECEDOR_2_CD_B184 = "";}
                    
                    String x = (zdlFONECEDOR_1_CD_B184.trim() + zdlFONECEDOR_2_CD_B184).trim();
                    
                    if( x.equals("") ){
                        
                        zdl_Status_CD_B184 = "SEM LOF";
                    }
                    else if( x.equals("NULL") ){
                        
                        zdl_Status_CD_B184 = "SEM LOF";
                    }  
                    else if( x.equals("NULLNULL") ){
                        
                        zdl_Status_CD_B184 = "SEM LOF";
                    }
                    else{
                        
                        zdl_Status_CD_B184 = "OK";
                    }
                }
                else{
                    
                    zdl_Status_CD_B184 = "SEM LOF";
                }
     
            }catch( Exception e ){}
            /*LOJAS_A_ZDADOSLOG_CD_B184*/
            
            /*LOJAS_A_ZDADOSLOG_CD_B289*/
            String posicao_B289 = "";
            String perfil_B289 = "";
            String zdlFONECEDOR_1_CD_B289 = "";
            String zdlFONECEDOR_2_CD_B289 = "";
            String zdl_Status_CD_B289 = "";
            try {
                
                List<LojasAZdadoslogCd> XXLojasAZvtraSort = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(LojasAZdadoslogCd.class, JPAUtil.em());
                                        
                    XXLojasAZvtraSort = (List<LojasAZdadoslogCd>) DAOGenericoJPAXX.getBeansCom_2_Parametro(LojasAZdadoslogCd.class, 
                            "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD WHERE MATERIAL = ? AND ( SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD.LOJA = ? )", Gbproduto.getMaterial(), "B289" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXLojasAZvtraSort.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    posicao_B289            = XXLojasAZvtraSort.get(0).getPosDpstZdadoslog(); 
                    perfil_B289             = XXLojasAZvtraSort.get(0).getPerfDistZdadoslog(); 
                    zdlFONECEDOR_1_CD_B289 = XXLojasAZvtraSort.get(0).getFonecedor1Zdadoslog();  
                    zdlFONECEDOR_2_CD_B289 = XXLojasAZvtraSort.get(0).getFonecedor2Zdadoslog();  
                    
                    String x = (zdlFONECEDOR_1_CD_B289.trim() + zdlFONECEDOR_2_CD_B289).trim();
                    
                    if( x.equals("") ){
                        
                        zdl_Status_CD_B289 = "SEM LOF";
                    }
                    else if( x.equals("NULL") ){
                        
                        zdl_Status_CD_B289 = "SEM LOF";
                    }   
                    else if( x.equals("NULLNULL") ){
                        
                        zdl_Status_CD_B289 = "SEM LOF";
                    }
                    else{
                        
                        zdl_Status_CD_B289 = "OK";
                    }
                }
                else{
                    
                    zdl_Status_CD_B289 = "SEM LOF";
                }
     
            }catch( Exception e ){}
            /*LOJAS_A_ZDADOSLOG_CD_B289*/
            
            /*NOME FORNECEDOR 1 B184*/
            String nome_ZdlFONECEDOR_1_CD_B184 = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", zdlFONECEDOR_1_CD_B184 );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_ZdlFONECEDOR_1_CD_B184 = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
            /*NOME FORNECEDOR 1 B184*/
            
            /*NOME FORNECEDOR 2 B184*/
            String nome_ZdlFONECEDOR_2_CD_B184 = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", zdlFONECEDOR_2_CD_B184 );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_ZdlFONECEDOR_2_CD_B184 = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
            /*NOME FORNECEDOR 2 B184*/
            
            /*NOME FORNECEDOR 1 B289*/
            String nome_ZdlFONECEDOR_1_CD_B289 = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", zdlFONECEDOR_1_CD_B289 );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_ZdlFONECEDOR_1_CD_B289 = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
            /*NOME FORNECEDOR 1 B289*/
            
            /*NOME FORNECEDOR 2 B289*/
            String nome_ZdlFONECEDOR_2_CD_B289 = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", zdlFONECEDOR_2_CD_B289 );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_ZdlFONECEDOR_2_CD_B289 = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
            /*NOME FORNECEDOR 2 B289*/
            
            /*ÚLTIMA ENTRADA B184*/
            List<Gbultimaentradadata> XXGbultimaentradadata = null;
            String ultimaentradadataB184="";
            String ultimaentradadataB184Lof="";
            try {     
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbultimaentradadata.class, JPAUtil.em());
                                        
                    XXGbultimaentradadata = (List<Gbultimaentradadata>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbultimaentradadata.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBULTIMAENTRADADATA WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBULTIMAENTRADADATA.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), "B184" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbultimaentradadata.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){   
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    ultimaentradadataB184 = formatter.format( XXGbultimaentradadata.get(0).getDataUltimaentrada() );        
                    ultimaentradadataB184Lof =  XXGbultimaentradadata.get(0).getLof();
                    //System.out.println("ultimaentradadataB184Lof - "+ ultimaentradadataB184Lof + " - " +ultimaentradadataB184);
                }
                
            }catch( Exception e ){}
            /*ÚLTIMA ENTRADA B184*/
            
            /*ÚLTIMA ENTRADA B289*/
            List<Gbultimaentradadata> XXGbultimaentradadata2 = null;
            String ultimaentradadataB289="";
            String ultimaentradadataB289Lof = "";
            try {     
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbultimaentradadata.class, JPAUtil.em());
                                        
                    XXGbultimaentradadata2 = (List<Gbultimaentradadata>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbultimaentradadata.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBULTIMAENTRADADATA WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBULTIMAENTRADADATA.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), "B289" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbultimaentradadata2.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){   
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    ultimaentradadataB289 = formatter.format( XXGbultimaentradadata2.get(0).getDataUltimaentrada() );    
                    
                    //System.out.println("ultimaentradadataB184Lof - "+ ultimaentradadataB289Lof + " - " +ultimaentradadataB289);
                    ultimaentradadataB289Lof =  XXGbultimaentradadata2.get(0).getLof();                   
                }
                
            }catch( Exception e ){}
            /*ÚLTIMA ENTRADA B289*/
            
            /*NOME FORNECEDOR ÚLTIMA ENTRADA B289*/
            String nome_FONECEDOR_Ultima_Entrada_B289 = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", ultimaentradadataB289Lof );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_FONECEDOR_Ultima_Entrada_B289 = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
             /*NOME FORNECEDOR ÚLTIMA ENTRADA B289*/
             
             /*NOME FORNECEDOR ÚLTIMA ENTRADA B184*/
            String nome_FONECEDOR_Ultima_Entrada_B184 = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", ultimaentradadataB184Lof );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_FONECEDOR_Ultima_Entrada_B184 = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
             /*NOME FORNECEDOR ÚLTIMA ENTRADA B184*/
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            String opIdentif="";     
            try {
                
                if( mrp.trim().equals("G1") ){
                    
                    opIdentif = "MRP G1";
                }
                else if( mrp.trim().equals("G3") ){
                    
                    opIdentif = "MRP G3";
                }
                else if( mrp.trim().equals("G4") ){
                    
                    if( nome_ZdlFONECEDOR_1_CD_B184.trim().equals("") && nome_ZdlFONECEDOR_2_CD_B184.trim().equals("") ){
                        
                        opIdentif = "SEM LOF: CD B184";
                    }
                    else if( perfil_B184.trim().equals("") ){
                        
                        opIdentif = "SEM PERFIL DE DISTRIBUIÇÃO PARA O CD B184";
                    }  
                    else if( perfil_B184.trim().equals("ZB1") ){
                        
                        opIdentif = "ESTOCADO PARA LOJAS E CROSS PARA O CD B184";
                    }
                    else if( posicao_B184.trim().equals("") ){
                        
                        opIdentif = "SEM ENDEREÇO NO CD B184";
                    }
                    else if( ultimaentradadataB184.trim().equals("") ){
                        
                        opIdentif = "SEM ENTRADA HÁ 3 MESES NO CD B184";
                    }
                    else{
                        
                        opIdentif = "RUPTURA";
                    }
                }
                else if( mrp.trim().equals("G6") ){
                    
                    if( nome_ZdlFONECEDOR_1_CD_B289.trim().equals("") && nome_ZdlFONECEDOR_2_CD_B289.trim().equals("") ){
                        
                        opIdentif = "SEM LOF: CD B289";
                    }
                    else if( perfil_B289.trim().equals("") ){
                        
                        opIdentif = "SEM PERFIL DE DISTRIBUIÇÃO PARA O CD B289";
                    }
                    else if( perfil_B289.trim().equals("ZB1") ){
                        
                        opIdentif = "ESTOCADO PARA LOJAS E CROSS PARA O CD B289";
                    }
                    else if( posicao_B289.trim().equals("") ){
                        
                        opIdentif = "SEM ENDEREÇO NO CD B289";
                    }
                    else if( ultimaentradadataB289.trim().equals("") ){
                        
                        opIdentif = "SEM ENTRADA HÁ 3 MESES NO CD B289";
                    }
                    else{
                        
                        opIdentif = "RUPTURA";
                    }
                }
                else if( mrp.trim().equals("S2") ){
                    
                    if( nome_ZdlFONECEDOR_1_CD_B289.trim().equals("") && nome_ZdlFONECEDOR_2_CD_B289.trim().equals("") &&
                        nome_ZdlFONECEDOR_1_CD_B184.trim().equals("") && nome_ZdlFONECEDOR_2_CD_B184.trim().equals("") ){
                        
                        opIdentif = "SEM LOF: CD B184 E NO CD B289";
                    }
                    else if( perfil_B289.trim().equals("") && perfil_B184.trim().equals("") ){
                        
                        opIdentif = "SEM PERFIL DE DISTRIBUIÇÃO PARA O CD B184 E O CD B289";
                    }
                    else if( perfil_B289.trim().equals("ZB1") && perfil_B184.trim().equals("ZB1") ){
                        
                        opIdentif = "ESTOCADO PARA LOJAS E CROSS PARA O CD B184 E O CD B289";
                    }
                    else if( posicao_B184.trim().equals("") && posicao_B289.trim().equals("") ){
                        
                        opIdentif = "SEM ENDEREÇO PARA O CD B184 E O CD B289";
                    }
                    else if( ultimaentradadataB184.trim().equals("") && ultimaentradadataB289.trim().equals("") ){
                        
                        opIdentif = "SEM ENTRADA HÁ 3 MESES NO CD B184 E O CD B289";
                    }
                    else{
                        
                        opIdentif = "RUPTURA";
                    }
                }
                
                /*
                statusEstoque= ""; try{ statusEstoque = LojasAMb52.getStatusEstoque(); }catch( Exception e ){}  
                /*
                List<Opidentificada> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Opidentificada.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Opidentificada>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Opidentificada.class, 
                            "SELECT * FROM SCHEMAJMARY.OPIDENTIFICADA WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opIdentif = XXGbraListaSap.get(0).getOpidentificada();                        
                }
                
                if( statusBloqueioM.trim().equals("BLOQUEADO") ){
                    
                    System.out.println("BLOQUEADO - "+statusEstoque);
                    opIdentif = "BLOQUEADO";
                }
                else if( statusBloqueioM.trim().equals("ATIVO") ){
                        
                    System.out.println("ATIVO - "+statusEstoque);
                    if( statusEstoque.trim().equals("POSITIVO") ){
                        
                        opIdentif = "ATIVO - COM ESTOQUE POSITIVO";
                    }
                    else if( statusEstoque.trim().equals("ZERO") || statusEstoque.trim().equals("NEGATIVO") ){
                                                
                        if( statusPed.trim().equals("COM PEDIDO") ){
                            
                            opIdentif = "ATIVO - EM RUPTURA - COM PEDIDO";
                        }
                        else if( statusPed.trim().equals("SEM PEDIDO") ){                                                     
                            
                            System.out.println("ATIVO - "+statusEstoque +" - "+statusPed);
                            if( mrpSM.trim().equals("ND") ){
                                
                                opIdentif = "ATIVO - EM RUPTURA - SEM PEDIDO - COM MRP ND";
                            }
                            else if( mrpSM.trim().equals("") ){
                                
                                opIdentif = "ATIVO - EM RUPTURA - SEM PEDIDO - SEM MRP";
                            }
                            else{
                                
                                if( zdl_Status_Loja.trim().equals("SEM LOF") ){
                                
                                    opIdentif = "ATIVO - EM RUPTURA - SEM PEDIDO - SEM LOF"; 
                                }
                                else if( zdl_Status_Loja.trim().equals("OK") ){
                                
                                    opIdentif = "ATIVO - EM RUPTURA - SEM PEDIDO - COM LOF";    
                                }
                            }
                        }
                    }
                }
                */
            }catch( Exception e ){ e.printStackTrace(); }
             
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
                                                                                                                                                                               
                    /*                                                                    
                    int x = 0;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,loja); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,mrp); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                    String estcd001   = ""; try{ estcd001   = String.valueOf(b001); }catch( Exception e ){}
                    String estcd184   = ""; try{ estcd184   = String.valueOf(b184); }catch( Exception e ){}
                    String estcd289   = ""; try{ estcd289   = String.valueOf(b289); }catch( Exception e ){}
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++; 
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd184); x++; 
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd289); x++; 
                                            
                    String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                                                             
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,qtdEmPed); x++; */ 
                    String estcd001 = ""; try{ estcd001 = String.valueOf(b001); }catch( Exception e ){}
                    String estcd184 = ""; try{ estcd184 = String.valueOf(b184); }catch( Exception e ){}
                    String estcd289 = ""; try{ estcd289 = String.valueOf(b289); }catch( Exception e ){}
                    
                    String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){} 
                    
                    //System.out.println( "Tamanho Layout: " + tmLayout );                    
                    int contador = 0;
                    int columnCount = tmLayout;
                    
                    String[] dados = new String[columnCount];
                    
                    dados [contador] = loja; contador++;
                    dados [contador] = descricaosetor; contador++;
                    dados [contador] = descGrupCom; contador++;
                    dados [contador] = zsap; contador++;
                    dados [contador] = Gbproduto.getDescricao(); contador++;
                    
                    dados [contador] = "ESTOCADO"; contador++;
                    dados [contador] = mrp; contador++;
                    dados [contador] = "DECER"; contador++;                                                  
                    dados [contador] = classe; contador++;

                    dados [contador] = opIdentif; contador++;  
                    
                    dados [contador] = pontExtra; contador++;
                    
                    dados [contador] = elenco; contador++;                    
                    dados [contador] = opSemana; contador++;                                                       
                    dados [contador] = estloja; contador++;                    
                    dados [contador] = zdisp; contador++;                    
                    dados [contador] = qtdEmPed; contador++;

                    dados [contador] = estcd184; contador++;                                     
                    dados [contador] = zdlFONECEDOR_1_CD_B184; contador++;
                    dados [contador] = nome_ZdlFONECEDOR_1_CD_B184; contador++;
                    dados [contador] = zdlFONECEDOR_2_CD_B184; contador++;
                    dados [contador] = nome_ZdlFONECEDOR_2_CD_B184; contador++;
                    
                    dados [contador] = perfil_B184; contador++;                    
                    dados [contador] = posicao_B184; contador++;                                        
                    dados [contador] = ultimaentradadataB184; contador++;
                    dados [contador] = ultimaentradadataB184Lof; contador++;
                    dados [contador] = nome_FONECEDOR_Ultima_Entrada_B184; contador++;
                                        
                    dados [contador] = estcd289; contador++;                                     
                    dados [contador] = zdlFONECEDOR_1_CD_B289; contador++;
                    dados [contador] = nome_ZdlFONECEDOR_1_CD_B289; contador++;
                    dados [contador] = zdlFONECEDOR_2_CD_B289; contador++;
                    dados [contador] = nome_ZdlFONECEDOR_2_CD_B289; contador++;
                    
                    dados [contador] = perfil_B289; contador++;                    
                    dados [contador] = posicao_B289; contador++;  
                    dados [contador] = ultimaentradadataB289; contador++;
                    dados [contador] = ultimaentradadataB289Lof; contador++;
                    dados [contador] = nome_FONECEDOR_Ultima_Entrada_B289; // ÚLTIMO SEM CONTADOR 
                                        
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.addRow( dados );
                    //Menu.Tabela_Pesquisa_BD_Estabelecimento.pedidoAdicional(dados);
                }}}                               
            }                                                           
                
        } } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    } 
    
    private void disponilidadeBaixaPositivoNoCD( String planModelo ){ 
        
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
                                                        
            String str[] = tpListSapLoja.getText().split("\n");
            if( !str.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( str.length );
                                
                //fechar(); 
                                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                for (int i = 0; i < str.length; i++){
                    Exportando.pbg.setValue( i );
 
                    String linha = str[i].trim();
                    StringBuilder loja = new StringBuilder(); StringBuilder sap = new StringBuilder();
                                    
                    int cuntP = 0;
                                    
                    int b = 0;
                    String anterior = "x";
                                    
                    char c[] = linha.toCharArray();
                    String v = "";
                    for (int r = 0; r < c.length; r++){  
                                        
                        v = String.valueOf( c[r] ).replace("/", ".").trim();
                        if( v.equals("") ){
                                            
                            if( !anterior.equals("") ){
                                                
                                cuntP = 0;
                                b++;
                                //System.out.println( anterior );
                            }
                        }
                        else if( !v.equals("") && b == 0 ){
                                            
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    loja.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                loja.append( v ); 
                            }
                        }
                        else if( !v.equals("") && b == 1 ){  
                                          
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    sap.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                sap.append( v ); 
                            }
                        }
                    }
                                    
                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                                    
                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                    try{ 
                                        
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                    if( busca.equals(rbusca) ){
                        if( !busca.equals("") ){
                                            
                            String lj = ""; try{ lj = loja.toString().toUpperCase().trim(); }catch( Exception e ){ }
                              
                            disponilidadeBaixaPositivoNoCD2( lj, XXGbprodutoListaSap.get(0) );                            
                        }
                    }
                    else{
                                        
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                                    
                }    
                                
                habilitado = true;
            }
                            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ fechar(); }
                        
        if(habilitado==true){
               
            String saida = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
            ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, planModelo, "MODELO OPORTUNIDADE.xlsx" );
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
    }
    
    private void disponilidadeBaixaPositivoNoCD2( String loja, Gbproduto Gbproduto ){ 
              
        try{    
            
             /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            if( XXGbzrisListaSap != null ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = Gbproduto.getMaterial(); }catch( Exception e ){}
            String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
                    
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
            if(esto <= XXGbzrisListaSap.get(0).getDisponibilidade()){             
                
                if( (b001 > 0) ||(b184 > 0) || (b289 > 0) ){ 
                                                                                        
                    int x = 0;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,loja); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,mrp); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                    String estcd001   = ""; try{ estcd001   = String.valueOf(b001); }catch( Exception e ){}
                    String estcd184   = ""; try{ estcd184   = String.valueOf(b184); }catch( Exception e ){}
                    String estcd289   = ""; try{ estcd289   = String.valueOf(b289); }catch( Exception e ){}
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++; 
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd184); x++; 
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd289); x++; 
                                            
                    String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                                                             
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,qtdEmPed); x++;  
                }                              
            }                                                           
                
        } } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    } 
    
    private void relatorioFull( String planModelo ){ 
        
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
                                                        
            String str[] = tpListSapLoja.getText().split("\n");
            if( !str.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( str.length );
                                
                //fechar(); 
                                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                for (int i = 0; i < str.length; i++){
                    Exportando.pbg.setValue( i );
 
                    String linha = str[i].trim();
                    StringBuilder loja = new StringBuilder(); StringBuilder sap = new StringBuilder();
                                    
                    int cuntP = 0;
                                    
                    int b = 0;
                    String anterior = "x";
                                    
                    char c[] = linha.toCharArray();
                    String v = "";
                    for (int r = 0; r < c.length; r++){  
                                        
                        v = String.valueOf( c[r] ).replace("/", ".").trim();
                        if( v.equals("") ){
                                            
                            if( !anterior.equals("") ){
                                                
                                cuntP = 0;
                                b++;
                                //System.out.println( anterior );
                            }
                        }
                        else if( !v.equals("") && b == 0 ){
                                            
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    loja.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                loja.append( v ); 
                            }
                        }
                        else if( !v.equals("") && b == 1 ){  
                                          
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    sap.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                sap.append( v ); 
                            }
                        }
                    }
                                    
                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                                    
                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                    try{ 
                                        
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                    if( busca.equals(rbusca) ){
                        if( !busca.equals("") ){
                                            
                            String lj = ""; try{ lj = loja.toString().toUpperCase().trim(); }catch( Exception e ){ }
                              
                            relatorioFull2( lj, XXGbprodutoListaSap.get(0) );                            
                        }
                    }
                    else{
                                        
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                                    
                }    
                                
                habilitado = true;
            }
                            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ fechar(); }
                        
        if(habilitado==true){
               
            String saida = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
            ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, planModelo, "MODELO OPORTUNIDADE.xlsx" );
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
    }
    
    private void relatorioFull2( String loja, Gbproduto Gbproduto ){ 
              
        try{    
            
             /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            if( XXGbzrisListaSap != null ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = Gbproduto.getMaterial(); }catch( Exception e ){}
            String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
                    
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*DESCRIÇÃO GRUPO COMPRA*/
            String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            /*DESCRIÇÃO GRUPO COMPRA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                        
                    int x = 0;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,loja); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descricaosetor); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,descGrupCom); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zsap); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,Gbproduto.getDescricao()); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"ESTOCADO"); x++;
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,mrp); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,"DECER"); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,classe); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,pontExtra); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,elenco); x++;
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,opSemana); x++; 
                                            
                    String estcd001   = ""; try{ estcd001   = String.valueOf(b001); }catch( Exception e ){}
                    String estcd184   = ""; try{ estcd184   = String.valueOf(b184); }catch( Exception e ){}
                    String estcd289   = ""; try{ estcd289   = String.valueOf(b289); }catch( Exception e ){}
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd001); x++; 
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd184); x++; 
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estcd289); x++; 
                                            
                    String estloja = ""; try{ estloja = String.valueOf(esto); }catch( Exception e ){}                                                             
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,estloja); x++;  
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,zdisp); x++; 
                                            
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.bazarGenericaTabela(x,qtdEmPed); x++;                                                        
                
        } } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    }
    
    private void pedidoAdicionalDispBaixa( String planModelo ){ 
        
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoPedidoAdicional();
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
                                                        
            String str[] = tpListSapLoja.getText().split("\n");
            if( !str.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( str.length );
                                
                //fechar(); 
                                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                for (int i = 0; i < str.length; i++){
                    Exportando.pbg.setValue( i );
 
                    String linha = str[i].trim();
                    StringBuilder loja = new StringBuilder(); StringBuilder sap = new StringBuilder();
                                    
                    int cuntP = 0;
                                    
                    int b = 0;
                    String anterior = "x";
                                    
                    char c[] = linha.toCharArray();
                    String v = "";
                    for (int r = 0; r < c.length; r++){  
                                        
                        v = String.valueOf( c[r] ).replace("/", ".").trim();
                        if( v.equals("") ){
                                            
                            if( !anterior.equals("") ){
                                                
                                cuntP = 0;
                                b++;
                                //System.out.println( anterior );
                            }
                        }
                        else if( !v.equals("") && b == 0 ){
                                            
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    loja.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                loja.append( v ); 
                            }
                        }
                        else if( !v.equals("") && b == 1 ){  
                                          
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    sap.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                sap.append( v ); 
                            }
                        }
                    }
                                    
                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                                    
                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                    try{ 
                                        
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                    if( busca.equals(rbusca) ){
                        if( !busca.equals("") ){
                                            
                            String lj = ""; try{ lj = loja.toString().toUpperCase().trim(); }catch( Exception e ){ }
                              
                            pedidoAdicionalDispBaixa2( lj, XXGbprodutoListaSap.get(0) );                            
                        }
                    }
                    else{
                                        
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                                    
                }    
                                
                habilitado = true;
            }
                            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ fechar(); }
                        
        if(habilitado==true){
               
            String saida = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
            ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, planModelo, "MODELO_PEDIDO_ADICIONAL.xlsx" );
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
    }
    
    private void pedidoAdicionalDispBaixa2( String loja, Gbproduto Gbproduto ){ 
              
        try{    
            
             /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            if( !qtdEmPed.equals("") ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = Gbproduto.getMaterial(); }catch( Exception e ){}
            String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
                    
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
            
            String ra="";
            try {
                
                List<Gbra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ra = XXGbraListaSap.get(0).getRaDoDia();                        
                }
                
            }catch( Exception e ){}
            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
            String pedUmb="";
            String pedRem="";
            String pedSai="";
            String pedForn="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7D   = XXGbpedidosativosListaSap.get(0).getPedidoCd184Ativo();  
                    pedUmb  = XXGbpedidosativosListaSap.get(0).getEs(); 
                    pedRem  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa(); 
                    pedSai  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                    pedForn = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
            if(esto <= XXGbzrisListaSap.get(0).getDisponibilidade()){             
                
                if( (b001 > 0) ||(b184 > 0) || (b289 > 0) ){ 
                    
                    int contador = 0;
                    int columnCount = 22;
                    
                    String[] dados = new String[columnCount];
                    
                    dados [contador] = loja; contador++;
                    dados [contador] = descricaosetor; contador++;
                    dados [contador] = zsap; contador++;
                    dados [contador] = Gbproduto.getDescricao(); contador++;
                    dados [contador] = mrp; contador++;
                    
                    dados [contador] = classe; contador++;
                    dados [contador] = pontExtra; contador++;
                    dados [contador] = elenco; contador++;                                                  
                    dados [contador] = String.valueOf(b001); contador++;
                    dados [contador] = String.valueOf(b184); contador++;
                    
                    dados [contador] = String.valueOf(b289); contador++;                    
                    dados [contador] = String.valueOf(esto); contador++;                    
                    dados [contador] = zdisp; contador++;
                    dados [contador] = qtdEmPed; contador++;
                    dados [contador] = ""; contador++;
                    
                    dados [contador] = ra; contador++;
                    dados [contador] = ped7D; contador++;
                    dados [contador] = pedUmb; contador++;
                    dados [contador] = pedRem; contador++;
                    dados [contador] = pedSai; contador++;
                    
                    dados [contador] = pedForn; contador++;
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.pedidoAdicional(dados);
                }                              
            }                                                           
                
        } } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    } 
    
    private void pedidoAdicionalFull( String planModelo ){ //analise_devolutiva
        
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoPedidoAdicional();
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
                                                        
            String str[] = tpListSapLoja.getText().split("\n");
            if( !str.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( str.length );
                                
                //fechar(); 
                                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                for (int i = 0; i < str.length; i++){
                    Exportando.pbg.setValue( i );
 
                    String linha = str[i].trim();
                    StringBuilder loja = new StringBuilder(); StringBuilder sap = new StringBuilder();
                                    
                    int cuntP = 0;
                                    
                    int b = 0;
                    String anterior = "x";
                                    
                    char c[] = linha.toCharArray();
                    String v = "";
                    for (int r = 0; r < c.length; r++){  
                                        
                        v = String.valueOf( c[r] ).replace("/", ".").trim();
                        if( v.equals("") ){
                                            
                            if( !anterior.equals("") ){
                                                
                                cuntP = 0;
                                b++;
                                //System.out.println( anterior );
                            }
                        }
                        else if( !v.equals("") && b == 0 ){
                                            
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    loja.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                loja.append( v ); 
                            }
                        }
                        else if( !v.equals("") && b == 1 ){  
                                          
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    sap.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                sap.append( v ); 
                            }
                        }
                    }
                                    
                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                                    
                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                    try{ 
                                        
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                    if( busca.equals(rbusca) ){
                        if( !busca.equals("") ){
                                            
                            String lj = ""; try{ lj = loja.toString().toUpperCase().trim(); }catch( Exception e ){ }
                              
                            pedidoAdicionalFull2( lj, XXGbprodutoListaSap.get(0) );                            
                        }
                    }
                    else{
                                        
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                                    
                }    
                                
                habilitado = true;
            }
                            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ fechar(); }
                        
        if(habilitado==true){
               
            String saida = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
            ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, planModelo, "MODELO_PEDIDO_ADICIONAL.xlsx" );
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
    }
            
    private void pedidoAdicionalFull2( String loja, Gbproduto Gbproduto ){ 
              
        try{    
            
             /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            if( !qtdEmPed.equals("") ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = Gbproduto.getMaterial(); }catch( Exception e ){}
            String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
                    
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
            
            String ra="";
            try {
                
                List<Gbra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ra = XXGbraListaSap.get(0).getRaDoDia();                        
                }
                
            }catch( Exception e ){}
            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
            String pedUmb="";
            String pedRem="";
            String pedSai="";
            String pedForn="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7D   = XXGbpedidosativosListaSap.get(0).getPedidoCd184Ativo();  
                    pedUmb  = XXGbpedidosativosListaSap.get(0).getEs(); 
                    pedRem  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa(); 
                    pedSai  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                    pedForn = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
            //if(esto <= XXGbzrisListaSap.get(0).getDisponibilidade()){             
                
                //if( (b001 > 0) ||(b184 > 0) || (b289 > 0) ){ 
                    
                    int contador = 0;
                    int columnCount = 22;
                    
                    String[] dados = new String[columnCount];
                    
                    dados [contador] = loja; contador++;
                    dados [contador] = descricaosetor; contador++;
                    dados [contador] = zsap; contador++;
                    dados [contador] = Gbproduto.getDescricao(); contador++;
                    dados [contador] = mrp; contador++;
                    
                    dados [contador] = classe; contador++;
                    dados [contador] = pontExtra; contador++;
                    dados [contador] = elenco; contador++;                                                  
                    dados [contador] = String.valueOf(b001); contador++;
                    dados [contador] = String.valueOf(b184); contador++;
                    
                    dados [contador] = String.valueOf(b289); contador++;                    
                    dados [contador] = String.valueOf(esto); contador++;                    
                    dados [contador] = zdisp; contador++;
                    dados [contador] = qtdEmPed; contador++;
                    dados [contador] = ""; contador++;
                    
                    dados [contador] = ra; contador++;
                    dados [contador] = ped7D; contador++;
                    dados [contador] = pedUmb; contador++;
                    dados [contador] = pedRem; contador++;
                    dados [contador] = pedSai; contador++;
                    
                    dados [contador] = pedForn; contador++;
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.pedidoAdicional(dados);
                //}                              
            //}                                                           
                
        } } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    } 
    
    private void analise_RA_devolutiva( String planModelo ){ 
        
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalho_RA_Devolutiva();
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
            
            String lj = ""; try{ lj = JOptionPane.showInputDialog( "Qual a loja?" ).toUpperCase(); }catch( Exception e ){ }
                       
            //Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.ANALISEDEVOLUTIVA", Analisedevolutiva.class );
            //List<Analisedevolutiva> list = q.getResultList();   
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBRA", Gbra.class );
            List<Gbra> list = q.getResultList();  
            if( list.size() > -1 && !lj.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( list.size() );
                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
             
                for (int i = 0; i < list.size(); i++){
                    Exportando.pbg.setValue( i );
                    
                    String busca = ""; try{ busca = list.get(i).getMaterial(); }catch( Exception e ){ }
                                    
                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                    try{ 
                                        
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                    if( busca.equals(rbusca) ){
                        if( !busca.equals("") ){
                                            
                            analise_RA_devolutiva2( lj, XXGbprodutoListaSap.get(0) );                                                 
                        }
                    }
                    else{
                                        
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                                         
                }  
                                
                habilitado = true;
            }
            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ fechar(); }
                        
        if(habilitado==true){
               
            String saida = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
            ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, planModelo, "MODELO_ANALISE_DEVOLUTIVA_RA.xlsx" );
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
    }
    
    private void analise_RA_devolutiva2( String loja, Gbproduto Gbproduto ){ 
              
        try{    
            
             /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            if( !qtdEmPed.equals("") ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = Gbproduto.getMaterial(); }catch( Exception e ){}
            String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
                    
            /*EST. LOJA*/
            int esto = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
            
            String ra="";
            try {
                
                List<Gbra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                       

                    String t1 = ""; 
                    try{ 
                
                        t1 = XXGbraListaSap.get(0).getRaDoDia();
                        if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                        double b  = Double.parseDouble( t1 );
                
                        DecimalFormat decimal = new DecimalFormat( "0.#" );
                                
                        t1 = String.valueOf( decimal.format(b) );
                        ra = t1;
                    } catch( Exception e ){}
                }
                
            }catch( Exception e ){}
            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
            String pedUmb="";
            String pedRem="";
            String pedSai="";
            String pedForn="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){        
                    
                    String t1 = ""; 
                    try{ 
                
                        t1 = XXGbpedidosativosListaSap.get(0).getPedidoCd184Ativo();
                        if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                        double b  = Double.parseDouble( t1 );
                
                        DecimalFormat decimal = new DecimalFormat( "0.#" );
                                
                        t1 = String.valueOf( decimal.format(b) );
                        ped7D = t1;
                    } catch( Exception e ){}

                    pedUmb  = XXGbpedidosativosListaSap.get(0).getEs(); 
                    pedRem  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa(); 
                    pedSai  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                    pedForn = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
            
            //Analisedevolutiva
            String planilha_ra_intranet   = ""; 
            String planilha_ra_intranet_alterado = ""; 
            String planilha_ra_me80fn = ""; 
            try {
                
                List<Analisedevolutiva> XXGbraListaSap = null;  
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Analisedevolutiva.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Analisedevolutiva>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Analisedevolutiva.class, 
                            "SELECT * FROM SCHEMAJMARY.ANALISEDEVOLUTIVA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
 
                    try{ 
                        planilha_ra_intranet = String.valueOf( XXGbraListaSap.get(0).getQtdSolicitada() ); 
                        if( planilha_ra_intranet.equals("null") ){ planilha_ra_intranet = ""; }
                    }catch( Exception e ){}  
                    
                    try{ 
                        planilha_ra_intranet_alterado = String.valueOf( XXGbraListaSap.get(0).getQtdDevolutiva()); 
                        if( planilha_ra_intranet_alterado.equals("null") ){ planilha_ra_intranet_alterado = ""; }
                    }catch( Exception e ){}
                    
                    try{ 
                        planilha_ra_me80fn = String.valueOf( XXGbraListaSap.get(0).getQtdDevolutivaMe80fn()); 
                        if( planilha_ra_me80fn.equals("null") ){ planilha_ra_me80fn = ""; }
                    }catch( Exception e ){}
                }
                
            }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                    
                    int contador = 0;
                    int columnCount = 24;
                    
                    String[] dados = new String[columnCount];
                    
                    dados [contador] = loja; contador++;
                    dados [contador] = descricaosetor; contador++;
                    dados [contador] = zsap; contador++;
                    dados [contador] = Gbproduto.getDescricao(); contador++;
                    dados [contador] = mrp; contador++;
                    
                    dados [contador] = classe; contador++;
                    dados [contador] = pontExtra; contador++;
                    dados [contador] = elenco; contador++;                                                  
                    dados [contador] = String.valueOf(b001); contador++;
                    dados [contador] = String.valueOf(b184); contador++;
                    
                    dados [contador] = String.valueOf(b289); contador++;                    
                    dados [contador] = String.valueOf(esto); contador++;                    
                    dados [contador] = zdisp; contador++;
                    dados [contador] = qtdEmPed; contador++;
                    
                    dados [contador] = planilha_ra_intranet; contador++;
                    dados [contador] = planilha_ra_intranet_alterado; contador++;
                    dados [contador] = planilha_ra_me80fn; contador++;
                    
                    dados [contador] = ra; contador++;
                    dados [contador] = ped7D; contador++;
                    dados [contador] = pedUmb; contador++;
                    dados [contador] = pedRem; contador++;
                    dados [contador] = pedSai; contador++;
                    
                    dados [contador] = pedForn; contador++;
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.ra_devolutiva(dados);                                                     
                
        } } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    }
    
    private void analise_eventos( String planModelo ){ 
        
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalho_Lamina_Vt();
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
            
            String lj = ""; try{ lj = JOptionPane.showInputDialog( "Qual a loja?" ).toUpperCase(); }catch( Exception e ){ }
                                                        
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.ANALISEEVENTOS", Analiseeventos.class );
            List<Analiseeventos> list = q.getResultList();   
            if( list.size() > -1 && !lj.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( list.size() );
                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
             
                for (int i = 0; i < list.size(); i++){
                    Exportando.pbg.setValue( i );
                    
                    String busca = ""; try{ busca = list.get(i).getMaterial(); }catch( Exception e ){ }
                                    
                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                    try{ 
                                        
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                    if( busca.equals(rbusca) ){
                        if( !busca.equals("") ){
                                            
                            analise_eventos2( lj, XXGbprodutoListaSap.get(0) );                                                 
                        }
                    }
                    else{
                                        
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                                         
                }  
                                
                habilitado = true;
            }
            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ fechar(); }
                        
        if(habilitado==true){
               
            String saida = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            ExportarExcelExistente ExportarExcelExistente = new ExportarExcelExistente();
            ExportarExcelExistente.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, planModelo, "MODELO_ANALISE_VT_LAMINA_TABOIDE.xlsx" );
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
    }
    
    private void analise_eventos2( String loja, Gbproduto Gbproduto ){ 
              
        try{    
            
             /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            if( !qtdEmPed.equals("") ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = Gbproduto.getMaterial(); }catch( Exception e ){}
            String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
            String t15 = ""; 
            try{ 
                
                t15 = zdisp;
                if( t15.equals("") || t15.equals("null") ){ t15="0"; }                                    
                double b  = Double.parseDouble( t15 );
                
                String t2 = qtdEmPed.replace(",", ".");;
                if( t2.equals("") || t2.equals("null") ){ t2="1"; }  
                double b2 = Double.parseDouble( t2 );
                double x = b / b2;
                
                DecimalFormat decimal = new DecimalFormat( "0.#" );
                
                t15 = String.valueOf( decimal.format(x) );
                zdisp = t15;
            } catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
                    
            /*EST. LOJA*/
            String esto = "";
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = String.valueOf( XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja() );                        
                }
     
            }catch( Exception e ){}
            
            String t14 = ""; 
            try{ 
                
                t14 = esto;
                if( t14.equals("") || t14.equals("null") ){ t14="0"; }                                    
                double b  = Double.parseDouble( t14 );
                
                String t2 = qtdEmPed.replace(",", ".");;
                if( t2.equals("") || t2.equals("null") ){ t2="1"; }  
                double b2 = Double.parseDouble( t2 );
                double x = b / b2;
                
                DecimalFormat decimal = new DecimalFormat( "0.#" );
                
                t14 = String.valueOf( decimal.format(x) );
                esto = t14;
            } catch( Exception e ){}
            /*EST. LOJA*/
            
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            /*OPORTUNIDADE SEMANA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
            
            String ra="";
            try {
                
                List<Gbra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    
                    String t1 = ""; 
                    try{ 
                
                        t1 = XXGbraListaSap.get(0).getRaDoDia();
                        if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                        double b  = Double.parseDouble( t1 );
                
                        DecimalFormat decimal = new DecimalFormat( "0.#" );
                                
                        t1 = String.valueOf( decimal.format(b) );
                        ra = t1;
                    } catch( Exception e ){} 
                }
                
            }catch( Exception e ){}
            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
            String pedUmb="";
            String pedRem="";
            String pedSai="";
            String pedForn="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                  
                    
                    String t1 = ""; 
                    try{ 
                
                        t1 = XXGbpedidosativosListaSap.get(0).getPedidoCd184Ativo();
                        if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                        double b  = Double.parseDouble( t1 );
                
                        DecimalFormat decimal = new DecimalFormat( "0.#" );
                                
                        t1 = String.valueOf( decimal.format(b) );
                        ped7D = t1;
                    } catch( Exception e ){}                     
                    
                    pedUmb  = XXGbpedidosativosListaSap.get(0).getEs(); 
                    pedRem  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa(); 
                    pedSai  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                    pedForn = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
            
            //Analisedevolutiva
            String es_Aposta   = ""; 
            String venda_mes_anterior = ""; 
            String venda_ano_anterior = ""; 
            try {
                
                List<Analiseeventos> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Analiseeventos.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Analiseeventos>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Analiseeventos.class, 
                            "SELECT * FROM SCHEMAJMARY.ANALISEEVENTOS WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
 
                    try{ 
                        es_Aposta = String.valueOf( XXGbraListaSap.get(0).getQtdEmbalagAposta()); 
                        if( es_Aposta.equals("null") ){ es_Aposta = ""; }
                    }catch( Exception e ){}  
                    
                    try{ 
                        venda_mes_anterior = String.valueOf( XXGbraListaSap.get(0).getVendaMesAnterior()); 
                        if( venda_mes_anterior.equals("null") ){ venda_mes_anterior = ""; }
                    }catch( Exception e ){}
                    
                    try{ 
                        venda_ano_anterior = String.valueOf( XXGbraListaSap.get(0).getVendaAnoAnterior()); 
                        if( venda_ano_anterior.equals("null") ){ venda_ano_anterior = ""; }
                    }catch( Exception e ){}
                }
                
            }catch( Exception e ){}
            
            //cálculo das unidades em CX
            String t1 = ""; 
            try{ 
                
                t1 = venda_mes_anterior;
                if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                double b  = Double.parseDouble( t1 );
                
                String t2 = qtdEmPed.replace(",", ".");;
                if( t2.equals("") || t2.equals("null") ){ t2="1"; }  
                double b2 = Double.parseDouble( t2 );
                double x = b / b2;
                
                DecimalFormat decimal = new DecimalFormat( "0.#" );
                                
                t1 = String.valueOf( decimal.format(x) );
                venda_mes_anterior = t1;
            } catch( Exception e ){} 
            
            //cálculo das unidades em CX
            String t12 = ""; 
            try{ 
                
                t12 = venda_ano_anterior;
                if( t12.equals("") || t12.equals("null") ){ t12="0"; }                                    
                double b  = Double.parseDouble( t12 );
                
                String t2 = qtdEmPed.replace(",", ".");;
                if( t2.equals("") || t2.equals("null") ){ t2="1"; }  
                double b2 = Double.parseDouble( t2 );
                double x = b / b2;
                
                DecimalFormat decimal = new DecimalFormat( "0.#" );
                
                t12 = String.valueOf( decimal.format(x) );
                venda_ano_anterior = t12;
            } catch( Exception e ){} 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                    
                    int linha = 0;
                    int columnCount = 25;
                    
                    String[] dados = new String[columnCount];
                    
                    dados [linha] = loja; linha++;
                    dados [linha] = descricaosetor; linha++;
                    dados [linha] = zsap; linha++;
                    dados [linha] = Gbproduto.getDescricao(); linha++;
                    dados [linha] = mrp; linha++;
                    
                    dados [linha] = classe; linha++;
                    dados [linha] = pontExtra; linha++;
                    dados [linha] = elenco; linha++;                                                  
                                       
                    dados [linha] = String.valueOf(esto); linha++;                    
                    dados [linha] = zdisp; linha++;
                    dados [linha] = qtdEmPed; linha++;
                    
                    dados [linha] = es_Aposta; linha++;
                    dados [linha] = venda_mes_anterior; linha++;
                    dados [linha] = ""; linha++;
                    dados [linha] = venda_ano_anterior; linha++;
                    
                    dados [linha] = ra; linha++;
                    dados [linha] = ped7D; linha++;
                    dados [linha] = pedUmb; linha++;
                    dados [linha] = pedRem; linha++;
                    dados [linha] = pedSai; linha++;                    
                    dados [linha] = pedForn; linha++;
                    
                    dados [linha] = String.valueOf(b001); linha++;
                    dados [linha] = String.valueOf(b184); linha++;                    
                    dados [linha] = String.valueOf(b289); linha++; 
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.addRow( dados );                                                   
                
        } } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    }
    
    private void relatorioMb52ZvtraSortimento( String saida ){ //analise_devolutiva
        
         try{
            
            Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "SETOR", "SEÇÃO", "LOJA", "DEP.", "MATERIAL", "DESCRICAO DO MATERIAL", "UTILIZAÇÃO LIVRE", "UMB", "VALOR UTILIZAÇÃO LIVRE", "PREÇO CUSTO", "OPORTUNIDADE IDENTIFICADA", "DATA ÚLTIMA ENTRADA", "CÓDIGO FORNECEDOR ÚLTIMA ENTRADA", "NOME FORNECEDOR ÚLTIMA ENTRADA" , "GRP MERC.", "CLASSE", "STATUS ESTOQUE", "MRP", "SM", "STATUS PRODUTO", "PEDIDO PENDENTE LOJA", "STATUS PEDIDO PENDENTE", "GCM LOJA", "FONECEDOR_1  LOJA ZDADOSLOG", "NOME FORNECEDOR 1 LOJA ZDADOSLOG" ,"FONECEDOR_2  LOJA ZDADOSLOG", "NOME FORNECEDOR 2 LOJA ZDADOSLOG" ,"STATUS LOF LOJA", "DDE*", "STATUS DISPONIBILIDADE", "DISP.", "VENDA 12 SEMANAS", "ESTOQUE MÍNIMO", "EST. B184 ESTOCADOS", "PEDIDO PENDENTE B184 15_DIAS_ME80FN", "POS_DPST B184", "PERF_DIST. B184", "FONECEDOR_1  B184", "FONECEDOR_2  B184", "STATUS LOF CD B184", "EST. B289 ESTOCADOS", "PEDIDO PENDENTE B289 15_DIAS_ME80FN", "POS_DPST B289", "PERF_DIST. B289", "FONECEDOR_1  B289", "FONECEDOR_2  B289", "STATUS LOF CD B289", "PONTO EXTRA", "ELENCO"         
            } );
            
            while ( Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.getRowCount() > 0){ Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.removeRow(0); }
            
            Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setModel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa);
        } catch( Exception e ){}
         
        boolean habilitado = false;
                                                
        try { 
            Exportando = new Exportando("PESQUISANDO PRODUTOS");
                                                        
            String str[] = tpListSapLoja.getText().split("\n");
            if( !str.equals("") ){
                                                                
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( str.length );
                                
                //fechar(); 
                                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(LojasAMb52.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                for (int i = 0; i < str.length; i++){
                    Exportando.pbg.setValue( i );
 
                    String linha = str[i].trim();
                    StringBuilder loja = new StringBuilder(); StringBuilder sap = new StringBuilder();
                                    
                    int cuntP = 0;
                                    
                    int b = 0;
                    String anterior = "x";
                                    
                    char c[] = linha.toCharArray();
                    String v = "";
                    for (int r = 0; r < c.length; r++){  
                                        
                        v = String.valueOf( c[r] ).replace("/", ".").trim();
                        if( v.equals("") ){
                                            
                            if( !anterior.equals("") ){
                                                
                                cuntP = 0;
                                b++;
                                //System.out.println( anterior );
                            }
                        }
                        else if( !v.equals("") && b == 0 ){
                                            
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    loja.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                loja.append( v ); 
                            }
                        }
                        else if( !v.equals("") && b == 1 ){  
                                          
                            if( v.equals(".") ){
                                cuntP++;
                                                
                                if( cuntP < 2 ){
                                    sap.append( v ); 
                                }
                            }    
                            else if( cuntP < 2 ){
                                                
                                sap.append( v ); 
                            }
                        }
                    }
                                    
                    String busca = ""; try{ busca = sap.toString().trim(); }catch( Exception e ){ }
                           
                    /*
                    List<LojasAMb52> XXGbprodutoListaSap = null;
                    String lj = "";
                    
                    try{ 
                        try{ lj = loja.toString().toUpperCase().trim(); }catch( Exception e ){ }
                                        
                        XXGbprodutoListaSap = (List<LojasAMb52>) DAOGenericoJPA.getBeansCom_1_Parametro(LojasAMb52.class, "SELECT * FROM SCHEMAJMARY.LOJAS_A_MB52 WHERE MATERIAL = ?", busca );
                                                                
                    }catch( Exception e ){ }
                    */
                    /*MB52*/
                    List<LojasAMb52> XXGbprodutoListaSap = null;
                    String lj = "";
                    try {
                
                        try{ lj = loja.toString().toUpperCase().trim(); }catch( Exception e ){ }
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(LojasAMb52.class, JPAUtil.em());
                                   
                            XXGbprodutoListaSap = (List<LojasAMb52>) DAOGenericoJPAXX.getBeansCom_2_Parametro(LojasAMb52.class, 
                                                "SELECT * FROM SCHEMAJMARY.LOJAS_A_MB52 WHERE MATERIAL = ? AND ( SCHEMAJMARY.LOJAS_A_MB52.LOJA = ? )", busca, lj );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( busca.equals(rbusca) ){
                            
                            if( !busca.equals("") ){                                          
                                                          
                                relatorioMb52ZvtraSortimento2( lj, XXGbprodutoListaSap.get(0) );                            
                            }
                        }
                        else{
                                        
                            String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                            ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                        }
                    }catch( Exception e ){}
                    /*MB52*/                                                                                         
                }    
                                
                habilitado = true;
            }
                            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
            Exportando.fechar();                     
        }catch( Exception e ){ fechar(); }
                        
        if(habilitado==true){
               
            String saida2 = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Gerados" + 
            System.getProperty("file.separator") + "BAZAR" +  ".xlsx"; 
            
            String entrada = System.getProperty("user.dir") + 
            System.getProperty("file.separator") + "Relatorios" +
            System.getProperty("file.separator") + "Modelos" + 
            System.getProperty("file.separator") + "MODELO BAZAR" +  ".xlsx";
            
            //exporarExcel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, entrada);
            Exportar_BigExcel Exportar_BigExcel = new Exportar_BigExcel();
            try{
                Exportar_BigExcel.exportar( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, saida, "MODELO_RELATORIO_DE_ACOMPANHAMENTO_DO_ESTOQUE_LOJA_CDS" );
            }catch( Exception e ){ e.printStackTrace(); }
        }
        else{
            
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                + "\nINFORME OS PARÂMETROS DE PESQUISA\n"  
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
    }
            
    private void relatorioMb52ZvtraSortimento2( String loja, LojasAMb52 LojasAMb52 ){ 
              
        try{    
            
            /*EST. LOJA*/
            int esto = 0;
            boolean boo = false;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", LojasAMb52.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    esto = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja(); 
                    boo = true;
                }
     
            }catch( Exception e ){}
            /*EST. LOJA*/
            
             /*ZRIS*/
             boolean tZris = false;
             
            List<Gbzris> XXGbzrisListaSap = null;
            String dde="";
            String stDisp="";
            String disp="";
            String ven12="";
            String estMinimo="";
            String zrisCusto="";
            
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", LojasAMb52.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){     
                    tZris = true;
                    
                    dde = String.valueOf( XXGbzrisListaSap.get(0).getDde() );                                                              
                    disp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); 
                    ven12 = String.valueOf( XXGbzrisListaSap.get(0).getVenda12Semanas() );
                    estMinimo = String.valueOf( XXGbzrisListaSap.get(0).getEstoqueMinimo() );
                    zrisCusto = String.valueOf( XXGbzrisListaSap.get(0).getPrecocusto() );
                            
                    if( boo==true ){
                        
                        if( esto <= XXGbzrisListaSap.get(0).getDisponibilidade() ){
                            
                            stDisp = "BAIXA";
                        }
                        else if( XXGbzrisListaSap.get(0).getDisponibilidade() < 40 ){
                            stDisp = "OK";
                        }
                        else if(  (XXGbzrisListaSap.get(0).getDisponibilidade() > 40) && (XXGbzrisListaSap.get(0).getDisponibilidade() < 60) ){
                            stDisp = "ATENÇÃO";
                        }
                        else{
                            
                            stDisp = "ALTA";
                        }
                    }
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
////////if( !qtdEmPed.equals("") ){
            
            /*
            System.out.println("Loja - "+XXGbListaZrisLojas.get(i).getEstabelecimento());
            System.out.println("SAP - "+XXGbListaZrisLojas.get(i).getMaterial());
            System.out.println("Disponibilidade - "+XXGbListaZrisLojas.get(i).getDisponibilidade());
            */
            String zsap  = ""; try{ zsap  = LojasAMb52.getMaterial(); }catch( Exception e ){}
            //String zdisp = ""; try{ zdisp = String.valueOf( XXGbzrisListaSap.get(0).getDisponibilidade() ); }catch( Exception e ){}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

            ////////////////////////////////////////////////////////////////////
                    List<Gbdescricaosetor> XXGbdescricaosetor = null;
                    String descricaosetor="";
                    try {    
                
                        try{ 
                            DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                            XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                                "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                        }catch( Exception e ){ }
                
                        String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){ descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor(); }
                    }catch( Exception e ){}
           ////////////////////////////////////////////////////////////////////
           
           String descGrupCom="";
            try {
                
                List<Gbdescricaogrupo> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaogrupo.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdescricaogrupo>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaogrupo.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descGrupCom = XXGbraListaSap.get(0).getDescricaogrupo();                        
                }
            }catch( Exception e ){}
            ////////////////////////////////////////////////////////////////////
                                           
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            /*MRP*/
            
            /*PONTO EXTRA*/
            String pontExtra="";
            try {
                
                List<Gbpontoextra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpontoextra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbpontoextra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbpontoextra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*PONTO EXTRA*/
            
            /*ELENCO*/
            String elenco="";
            try {
                
                List<Gbelenco> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbelenco.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbelenco>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbelenco.class, 
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            /*ELENCO*/
            
            /*OPORTUNIDADE SEMANA*/
            /*String opSemana="";
            try {
                
                List<Gbooportsemana> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbooportsemana.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbooportsemana>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbooportsemana.class, 
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}*/
            /*OPORTUNIDADE SEMANA*/
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String classe="";

            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            /*EST. CD'S*/
            /*int b001 = 0;
            try {
                
                List<Gbestoquecdb001> XXGbestoquecdb001ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb001.class, JPAUtil.em());
                                        
                    XXGbestoquecdb001ListaSap = (List<Gbestoquecdb001>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb001.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb001ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b001 = XXGbestoquecdb001ListaSap.get(0).getEstoqueCd001();                        
                }
                
            }catch( Exception e ){}*/
            
            int b289 = 0;
            try {
                
                List<Gbestoquecdb289> XXGbestoquecdb289ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb289.class, JPAUtil.em());
                                        
                    XXGbestoquecdb289ListaSap = (List<Gbestoquecdb289>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb289.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb289ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b289 = XXGbestoquecdb289ListaSap.get(0).getEstoqueCd289();                        
                }
                
            }catch( Exception e ){}
            
            int b184 = 0;
            try {
                
                List<Gbestoquecdb184> XXGbestoquecdb184ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquecdb184.class, JPAUtil.em());
                                        
                    XXGbestoquecdb184ListaSap = (List<Gbestoquecdb184>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquecdb184.class, 
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquecdb184ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b184 = XXGbestoquecdb184ListaSap.get(0).getEstoqueCd184();                        
                }
                
            }catch( Exception e ){}
            /*EST. CD'S*/
            
            /*String ra="";
            try {
                
                List<Gbra> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ra = XXGbraListaSap.get(0).getRaDoDia();                        
                }
                
            }catch( Exception e ){}*/
            
            /*7 DIAS ATIVOS LOJA*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String statusPed="";
            
            String ped7D="";
            //String pedUmb="";
            //String pedRem="";
            //String pedSai="";
            //String pedForn="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", LojasAMb52.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7D     = XXGbpedidosativosListaSap.get(0).getNumepedido();
                    statusPed = "COM PEDIDO";
                    //pedUmb  = XXGbpedidosativosListaSap.get(0).getEs(); 
                    //pedRem  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa(); 
                    //pedSai  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                    //pedForn = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                }
                else{
                    
                    statusPed = "SEM PEDIDO";
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS LOJA*/
            
            /*7 DIAS ATIVOS CD184*/
            List<Gbpedidosativos> XXGbpedidosativosListaSapCD184 = null;
            String statusPedCD184="";
            
            String ped7DCD184="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSapCD184 = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", LojasAMb52.getMaterial(), "B184" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSapCD184.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7DCD184     = XXGbpedidosativosListaSapCD184.get(0).getNumepedido();
                    statusPedCD184 = "COM PEDIDO";
                    //pedUmb  = XXGbpedidosativosListaSap.get(0).getEs(); 
                    //pedRem  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa(); 
                    //pedSai  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                    //pedForn = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                }
                else{
                    
                    statusPedCD184 = "SEM PEDIDO";
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS CD184*/
            
            /*7 DIAS ATIVOS CD184*/
            List<Gbpedidosativos> XXGbpedidosativosListaSapCD289 = null;
            String statusPedCD289="";
            
            String ped7DCD289="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSapCD289 = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", LojasAMb52.getMaterial(), "B289" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSapCD289.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7DCD289     = XXGbpedidosativosListaSapCD289.get(0).getNumepedido();
                    statusPedCD289 = "COM PEDIDO";
                    //pedUmb  = XXGbpedidosativosListaSap.get(0).getEs(); 
                    //pedRem  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoRemessa(); 
                    //pedSai  = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoSaiu();
                    //pedForn = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                }
                else{
                    
                    statusPedCD289 = "SEM PEDIDO";
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS CD289*/
            
            /*MB52*/
            /*List<LojasAMb52> XXLojasAMb52 = null;
            String mbDep="";
            String mbDesc="";
            String mbEstoque="";
            String mbUbm="";
            String valorEstoque="";
            String grupo="";
            String statusEstoque="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(LojasAMb52.class, JPAUtil.em());
                                   
                    XXLojasAMb52 = (List<LojasAMb52>) DAOGenericoJPAXX.getBeansCom_2_Parametro(LojasAMb52.class, 
                                                "SELECT * FROM SCHEMAJMARY.LOJAS_A_MB52 WHERE MATERIAL = ? AND ( SCHEMAJMARY.LOJAS_A_MB52.LOJA = ? )", LojasAMb52.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mbDep         = XXLojasAMb52.get(0).getDep();  
                    mbDesc        = XXLojasAMb52.get(0).getDescricao(); 
                    mbEstoque     = XXLojasAMb52.get(0).getUtilizacaoLivre(); 
                    mbUbm         = XXLojasAMb52.get(0).getUmb();
                    valorEstoque  = XXLojasAMb52.get(0).getValorUtilizacaoLivre();
                    grupo         = XXLojasAMb52.get(0).getGrpMerc();
                    statusEstoque = XXLojasAMb52.get(0).getStatusEstoque();
                }
                
            }catch( Exception e ){}*/
            /*MB52*/
            
            /*ZVTRA_SORTIMENTO*/
            String mrpSM = "";
            String sm = "";
            String statusBloqueioM = "";
            try {
                
                List<LojasAZvtraSort> XXLojasAZvtraSort = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(LojasAZvtraSort.class, JPAUtil.em());
                                        
                    XXLojasAZvtraSort = (List<LojasAZvtraSort>) DAOGenericoJPAXX.getBeansCom_2_Parametro(LojasAZvtraSort.class, 
                            "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZVTRA_SORT WHERE MATERIAL = ? AND ( SCHEMAJMARY.LOJAS_A_ZVTRA_SORT.LOJA = ? )", LojasAMb52.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXLojasAZvtraSort.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                      
                    try{ 
                        if( !mrp.equals("") ){ 
                            mrpSM = mrp;
                        }
                        else{
                            mrpSM = XXLojasAZvtraSort.get(0).getMrpZvtra();
                            if( mrpSM.equals("NULL") ){ mrpSM = "SEM MRP"; }
                        }
                    }catch( Exception e ){}
                     
                    sm = XXLojasAZvtraSort.get(0).getSmZvtra();                                 
                    
                    if( sm.equals("NULL") ){
                        
                        sm = "";
                    }
                    
                    statusBloqueioM = XXLojasAZvtraSort.get(0).getStatusBloqueio();  
                }
                else{
                    
                    sm = "SEM STATUS";
                }
     
            }catch( Exception e ){}
            /*ZVTRA_SORTIMENTO*/
            
            /*LOJAS_A_ZDADOSLOG_LOJA*/
            String cCM_ZDADOSLOG_lOJA = "";
            String zdlFONECEDOR_1_ZDADOSLOG_LOJA = "";
            String zdlFONECEDOR_2_ZDADOSLOG_LOJA = "";
            String zdl_Status_Loja = "";
            try {
                
                List<LojasAZdadoslogLoja> XXLojasAZvtraSort = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(LojasAZdadoslogLoja.class, JPAUtil.em());
                                        
                    XXLojasAZvtraSort = (List<LojasAZdadoslogLoja>) DAOGenericoJPAXX.getBeansCom_2_Parametro(LojasAZdadoslogLoja.class, 
                            "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZDADOSLOG_LOJA WHERE MATERIAL = ? AND ( SCHEMAJMARY.LOJAS_A_ZDADOSLOG_LOJA.LOJA = ? )", LojasAMb52.getMaterial(), loja );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXLojasAZvtraSort.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    cCM_ZDADOSLOG_lOJA            = XXLojasAZvtraSort.get(0).getGcmZdadoslog(); 
                    zdlFONECEDOR_1_ZDADOSLOG_LOJA = XXLojasAZvtraSort.get(0).getFonecedor1Zdadoslog();  
                    zdlFONECEDOR_2_ZDADOSLOG_LOJA = XXLojasAZvtraSort.get(0).getFonecedor2Zdadoslog();  
                    
                    String x = (zdlFONECEDOR_1_ZDADOSLOG_LOJA.trim() + zdlFONECEDOR_2_ZDADOSLOG_LOJA).trim();
                    
                    if( x.equals("") ){
                        
                        zdl_Status_Loja = "SEM LOF";
                    }
                    else if( x.equals("NULL") ){
                        
                        zdl_Status_Loja = "SEM LOF";
                    } 
                    else if( x.equals("NULLNULL") ){
                        
                        zdl_Status_Loja = "SEM LOF";
                    }
                    else{
                        
                        zdl_Status_Loja = "OK";
                    }
                }
                else{
                    
                    zdl_Status_Loja = "SEM LOF";
                }
     
            }catch( Exception e ){}
            /*LOJAS_A_ZDADOSLOG_LOJA*/
            
            /*NOME zdlFONECEDOR_1_ZDADOSLOG_LOJA*/
            String nome_zdlFONECEDOR_1_ZDADOSLOG_LOJA = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", zdlFONECEDOR_1_ZDADOSLOG_LOJA );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_zdlFONECEDOR_1_ZDADOSLOG_LOJA = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
             /*NOME zdlFONECEDOR_1_ZDADOSLOG_LOJA*/
             
             /*NOME zdlFONECEDOR_2_ZDADOSLOG_LOJA*/
            String nome_zdlFONECEDOR_2_ZDADOSLOG_LOJA = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", zdlFONECEDOR_2_ZDADOSLOG_LOJA );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_zdlFONECEDOR_2_ZDADOSLOG_LOJA = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
             /*NOME zdlFONECEDOR_2_ZDADOSLOG_LOJA*/
            
            /*LOJAS_A_ZDADOSLOG_CD_B184*/
            String posicao_B184 = "";
            String perfil_B184 = "";
            String zdlFONECEDOR_1_CD_B184 = "";
            String zdlFONECEDOR_2_ZDADOSLOG_CD_B184 = "";
            String zdl_Status_CD_B184 = "";
            try {
                
                List<LojasAZdadoslogCd> XXLojasAZvtraSort = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(LojasAZdadoslogCd.class, JPAUtil.em());
                                        
                    XXLojasAZvtraSort = (List<LojasAZdadoslogCd>) DAOGenericoJPAXX.getBeansCom_2_Parametro(LojasAZdadoslogCd.class, 
                            "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD WHERE MATERIAL = ? AND ( SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD.LOJA = ? )", LojasAMb52.getMaterial(), "B184" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXLojasAZvtraSort.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    posicao_B184            = XXLojasAZvtraSort.get(0).getPosDpstZdadoslog();
                    if( posicao_B184.equals("NULL") ){posicao_B184 = "";}
                    
                    perfil_B184             = XXLojasAZvtraSort.get(0).getPerfDistZdadoslog();
                    if( perfil_B184.equals("NULL") ){perfil_B184 = "";}
                    
                    zdlFONECEDOR_1_CD_B184 = XXLojasAZvtraSort.get(0).getFonecedor1Zdadoslog();
                    if( zdlFONECEDOR_1_CD_B184.equals("NULL") ){zdlFONECEDOR_1_CD_B184 = "";}
                    
                    zdlFONECEDOR_2_ZDADOSLOG_CD_B184 = XXLojasAZvtraSort.get(0).getFonecedor2Zdadoslog();  
                    if( zdlFONECEDOR_2_ZDADOSLOG_CD_B184.equals("NULL") ){zdlFONECEDOR_2_ZDADOSLOG_CD_B184 = "";}
                    
                    String x = (zdlFONECEDOR_1_CD_B184.trim() + zdlFONECEDOR_2_ZDADOSLOG_CD_B184).trim();
                    
                    if( x.equals("") ){
                        
                        zdl_Status_CD_B184 = "SEM LOF";
                    }
                    else if( x.equals("NULL") ){
                        
                        zdl_Status_CD_B184 = "SEM LOF";
                    }  
                    else if( x.equals("NULLNULL") ){
                        
                        zdl_Status_CD_B184 = "SEM LOF";
                    }
                    else{
                        
                        zdl_Status_CD_B184 = "OK";
                    }
                }
                else{
                    
                    zdl_Status_CD_B184 = "SEM LOF";
                }
     
            }catch( Exception e ){}
            /*LOJAS_A_ZDADOSLOG_CD_B184*/
            
            /*LOJAS_A_ZDADOSLOG_CD_B289*/
            String posicao_B289 = "";
            String perfil_B289 = "";
            String zdlFONECEDOR_1_CD_B289 = "";
            String zdlFONECEDOR_2_ZDADOSLOG_CD_B289 = "";
            String zdl_Status_CD_B289 = "";
            try {
                
                List<LojasAZdadoslogCd> XXLojasAZvtraSort = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(LojasAZdadoslogCd.class, JPAUtil.em());
                                        
                    XXLojasAZvtraSort = (List<LojasAZdadoslogCd>) DAOGenericoJPAXX.getBeansCom_2_Parametro(LojasAZdadoslogCd.class, 
                            "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD WHERE MATERIAL = ? AND ( SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD.LOJA = ? )", LojasAMb52.getMaterial(), "B289" );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXLojasAZvtraSort.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    posicao_B289            = XXLojasAZvtraSort.get(0).getPosDpstZdadoslog(); 
                    perfil_B289             = XXLojasAZvtraSort.get(0).getPerfDistZdadoslog(); 
                    zdlFONECEDOR_1_CD_B289 = XXLojasAZvtraSort.get(0).getFonecedor1Zdadoslog();  
                    zdlFONECEDOR_2_ZDADOSLOG_CD_B289 = XXLojasAZvtraSort.get(0).getFonecedor2Zdadoslog();  
                    
                    String x = (zdlFONECEDOR_1_CD_B289.trim() + zdlFONECEDOR_2_ZDADOSLOG_CD_B289).trim();
                    
                    if( x.equals("") ){
                        
                        zdl_Status_CD_B289 = "SEM LOF";
                    }
                    else if( x.equals("NULL") ){
                        
                        zdl_Status_CD_B289 = "SEM LOF";
                    }   
                    else if( x.equals("NULLNULL") ){
                        
                        zdl_Status_CD_B289 = "SEM LOF";
                    }
                    else{
                        
                        zdl_Status_CD_B289 = "OK";
                    }
                }
                else{
                    
                    zdl_Status_CD_B289 = "SEM LOF";
                }
     
            }catch( Exception e ){}
            /*LOJAS_A_ZDADOSLOG_CD_B289*/
                                    
////////////////////////////////////////////////////////////////////////////////]
            List<Gbultimaentradadata> XXGbultimaentradadata = null;
            String ultimaentradadataDataLof="SEM ENTRADA IDENTIFICADA";
            String ultimaentradadataLOJALof="";
            try {     
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbultimaentradadata.class, JPAUtil.em());
                                        
                    XXGbultimaentradadata = (List<Gbultimaentradadata>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbultimaentradadata.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBULTIMAENTRADADATA WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBULTIMAENTRADADATA.ESTABELECIMENTO = ? )", LojasAMb52.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbultimaentradadata.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){   
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    ultimaentradadataDataLof = formatter.format( XXGbultimaentradadata.get(0).getDataUltimaentrada() );
                    ultimaentradadataLOJALof = XXGbultimaentradadata.get(0).getLof().trim();
                }
                
            }catch( Exception e ){}
            
            /*NOME FORNECEDOR ÚLTIMA ENTRADA B289*/
            String nome_FONECEDOR_Ultima_Entrada_LOJA = "";
            try {
                
                List<Lof> XXGbestoqueLof = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Lof.class, JPAUtil.em());
                                        
                    XXGbestoqueLof = (List<Lof>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Lof.class, 
                            "SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", ultimaentradadataLOJALof );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoqueLof.get(0).getRazaoSocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nome_FONECEDOR_Ultima_Entrada_LOJA = XXGbestoqueLof.get(0).getRazaoSocial();                        
                }
                
            }catch( Exception e ){}
             /*NOME FORNECEDOR ÚLTIMA ENTRADA B289*/
////////////////////////////////////////////////////////////////////////////////

        String opIdentif="";
        String statusEstoque="";      
            try {
                
                statusEstoque= ""; try{ statusEstoque = LojasAMb52.getStatusEstoque(); }catch( Exception e ){}  
                /*
                List<Opidentificada> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Opidentificada.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Opidentificada>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Opidentificada.class, 
                            "SELECT * FROM SCHEMAJMARY.OPIDENTIFICADA WHERE MATERIAL = ?", LojasAMb52.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opIdentif = XXGbraListaSap.get(0).getOpidentificada();                        
                }*/
                
                if( statusBloqueioM.trim().equals("BLOQUEADO") ){
                    
                    //System.out.println("BLOQUEADO - "+statusEstoque);
                    opIdentif = "BLOQUEADO";
                }
                else if( statusBloqueioM.trim().equals("ATIVO") ){
                        
                    //System.out.println("ATIVO - "+statusEstoque);
                    if( statusEstoque.trim().equals("POSITIVO") ){
                        
                        opIdentif = "ATIVO - COM ESTOQUE POSITIVO";
                    }
                    else if( statusEstoque.trim().equals("ZERO") || statusEstoque.trim().equals("NEGATIVO") ){
                                                
                        if( statusPed.trim().equals("COM PEDIDO") ){
                            
                            opIdentif = "ATIVO - EM RUPTURA - COM PEDIDO";
                        }
                        else if( statusPed.trim().equals("SEM PEDIDO") ){                                                     
                            
                            //System.out.println("ATIVO - "+statusEstoque +" - "+statusPed);
                            if( mrpSM.trim().equals("ND") ){
                                
                                opIdentif = "ATIVO - EM RUPTURA - SEM PEDIDO - COM MRP ND";
                            }
                            else if( mrpSM.trim().equals("") || mrpSM.trim().equals("SEM MRP") ){
                                
                                opIdentif = "ATIVO - EM RUPTURA - SEM PEDIDO - SEM MRP";
                            }
                            else{
                                
                                if( zdl_Status_Loja.trim().equals("SEM LOF") ){
                                
                                    opIdentif = "ATIVO - EM RUPTURA - SEM PEDIDO - SEM LOF"; 
                                }
                                else if( zdl_Status_Loja.trim().equals("OK") ){
                                
                                    opIdentif = "ATIVO - EM RUPTURA - SEM PEDIDO - COM LOF";    
                                }
                            }
                        }
                    }
                }
                
            }catch( Exception e ){ e.printStackTrace(); }
            
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                                
                                                                                                                                                                
            //if(esto <= XXGbzrisListaSap.get(0).getDisponibilidade()){             
                
                //if( (b001 > 0) ||(b184 > 0) || (b289 > 0) ){ 
                    
                    int contador = 0;
                    int columnCount = 50;
                    
                    String[] dados = new String[columnCount];
                                                             
                    dados [contador] = descricaosetor; contador++;    
                    dados [contador] = descGrupCom; contador++; 
                    dados [contador] = loja; contador++;
                    dados [contador] = LojasAMb52.getDep(); contador++;
                    dados [contador] = zsap; contador++;
                                        
                    dados [contador] = LojasAMb52.getDescricao(); contador++;
                    dados [contador] = LojasAMb52.getUtilizacaoLivre(); contador++;
                    dados [contador] = LojasAMb52.getUmb(); contador++;
                    dados [contador] = LojasAMb52.getValorUtilizacaoLivre(); contador++;
                    
                    dados [contador] = zrisCusto; contador++;
                    
                    dados [contador] = opIdentif; contador++;
                    dados [contador] = ultimaentradadataDataLof; contador++;
                    dados [contador] = ultimaentradadataLOJALof; contador++;
                    dados [contador] = nome_FONECEDOR_Ultima_Entrada_LOJA; contador++;
                                        
                    dados [contador] = LojasAMb52.getGrpMerc(); contador++;
                    
                    dados [contador] = classe; contador++;                    
                    dados [contador] = statusEstoque; contador++;
                    dados [contador] = mrpSM; contador++;
                    dados [contador] = sm; contador++;
                    dados [contador] = statusBloqueioM; contador++;
                    
                    dados [contador] = ped7D; contador++;
                    dados [contador] = statusPed; contador++;
                    dados [contador] = cCM_ZDADOSLOG_lOJA; contador++;
                    
                    dados [contador] = zdlFONECEDOR_1_ZDADOSLOG_LOJA; contador++;
                    dados [contador] = nome_zdlFONECEDOR_1_ZDADOSLOG_LOJA; contador++;                    
                    dados [contador] = zdlFONECEDOR_2_ZDADOSLOG_LOJA; contador++;
                    dados [contador] = nome_zdlFONECEDOR_2_ZDADOSLOG_LOJA; contador++;   
                    
                    dados [contador] = zdl_Status_Loja; contador++;                                        
                    dados [contador] = dde; contador++;
                    dados [contador] = stDisp; contador++;
                    dados [contador] = disp; contador++;
                    dados [contador] = ven12; contador++;
                    
                    dados [contador] = estMinimo; contador++;      
                    
                    dados [contador] = String.valueOf(b184); contador++;
                    dados [contador] = ped7DCD184; contador++;
                    dados [contador] = posicao_B184; contador++;
                    dados [contador] = perfil_B184; contador++;
                    dados [contador] = zdlFONECEDOR_1_CD_B184; contador++;                    
                    dados [contador] = zdlFONECEDOR_2_ZDADOSLOG_CD_B184; contador++;
                    dados [contador] = zdl_Status_CD_B184; contador++;      
                    
                    dados [contador] = String.valueOf(b289); contador++;    
                    dados [contador] = ped7DCD289; contador++;
                    dados [contador] = posicao_B289; contador++;
                    dados [contador] = perfil_B289; contador++;                    
                    dados [contador] = zdlFONECEDOR_1_CD_B289; contador++;
                    dados [contador] = zdlFONECEDOR_2_ZDADOSLOG_CD_B289; contador++;
                    dados [contador] = zdl_Status_CD_B289; contador++;  
                    
                    dados [contador] = pontExtra; contador++;
                    dados [contador] = elenco; contador++;
                                                                                                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.pedidoAdicional(dados);
                //}                              
            //}                                                           
                
////////} 
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "Importar(File file, JTable tbPesquisa)(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }  
    } 
    
    public void disponilidadeBaixaZeroNoCD3( String query ){ try {   
        Exportando = new Exportando("AGUARDANDO SQL");                                          
        Exportando.setVisible(true);
        Exportando.pbg.setMinimum(0);
        Exportando.pbg.setMaximum( 100 );
        Exportando.pbg.setValue(50);
                
        tpListSapLoja.setText("");
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoBazarGenericaTabela();                
        DB DB = new DB();
        Connection con = null; try{ con = DB.derby();             }catch(Exception e){}    
        Statement stmt = null; try{ stmt = con.createStatement(); }catch(Exception e){}        
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) { 
            
            String atual = tpListSapLoja.getText().trim();
            tpListSapLoja.setText(atual+"\n"+ rs.getString(2) +" "+rs.getString(1) );
        }
        
        Exportando.fechar();
    } catch( Exception e ){ e.printStackTrace(); } }   
    
    
        
}
