package importar_exportar_search;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fornecedor.Fornecedor;
import gb_evento.Opidentificada;
import gb_evento.Enderecoespacogondula;
import gb_evento.Evento;
import gb_evento.Eventoprodutos;
import gb_evento.Gbcoringa;
import gb_evento.Gbdescricaogrupo;
import gb_evento.Gbdescricaosetor;
import gb_evento.Gbdiassemvenda;
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
import gb_evento.Gbprodutoporfornecedor;
import gb_evento.Gbprodutoporfornecedorcd;
import gb_evento.Gbquantidadevendida;
import gb_evento.Gbra;
import gb_evento.Gbsuply;
import gb_evento.Gbultimaentradadata;
import gb_evento.Gbzris;
import importar_exportar.Importar_Exportar_Home;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import utilidades.Administrador;
import utilidades.DB;
import utilidades.Exportando;
import utilidades.ImportarExportarExcel;
import utilidades.JOPM;

/**
 *
 * @author ana
 */
public class Tabela_Pesquisa_BD_Importar_Exportar extends javax.swing.JPanel {
   
    private Importar_Exportar_Home          Estabelecimento_Home;
    private Menu_Pesquisa_Importar_Exportar Menu_Pesquisa;
        
    private ListSelectionModel               lsmPesquisa;
    public DefaultTableModel                tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "EAN", "DESCRICAO DO MATERIAL", "UBM", "Q.EMB", "GRUPO", "MRP", "EST. CD184", "EST. LOJA", "SUGESTÃO-JM", "PED. ATIVO", "REMESSA", "SAIU CD",  "ENT. LOJA", "CD ONTEM", "CLASSE", "SETOR", "EST. TIPO", "COD. FORNEC.", "NOME FORNEC.", "RA GEROU", "SEÇÃO", "EST. MÍNIMO", "DISPONIBILIDADE", "VENDA ULT. SEM", "VENDA 04 SEM", "VENDA 12 SEM", "  DDE  ", "DIAS SEM VENDAS"           
            } );
    
    public Tabela_Pesquisa_BD_Importar_Exportar( Importar_Exportar_Home Estabelecimento_Home2 ) {
         
        Estabelecimento_Home      = Estabelecimento_Home2;
                
        initComponents();
                 
        tabelaInicio();                        
    }
    
    public void tabela(Menu_Pesquisa_Importar_Exportar Menu_Pesquisa2){ 
                
        try {                
            Menu_Pesquisa = Menu_Pesquisa2;                                    
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabela, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }     
    }
    
    private void tabelaInicio(){
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                    
            tbPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lsmPesquisa = tbPesquisa.getSelectionModel();
            
            tbPesquisa.setAutoCreateRowSorter(true);

            tabelaModoDeSelecao( "Single Selection", false, false, false );
            
            tbPreferedSize(); 
            
            //ExcelAdapter myAd = new ExcelAdapter(tbPesquisa);
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();              
    }
    
    public void tabelaModoDeSelecao( String command, boolean rowCheck, boolean columnCheck, boolean selecionar_celula ) {
        
        if ("Row Selection" == command) {
            tbPesquisa.setRowSelectionAllowed(rowCheck);
            //In MIS mode, column selection allowed must be the
            //opposite of row selection allowed.
            if (!selecionar_celula) {
                tbPesquisa.setColumnSelectionAllowed(!rowCheck);
            }
        } else if ("Column Selection" == command) {
            tbPesquisa.setColumnSelectionAllowed(columnCheck);
            //In MIS mode, row selection allowed must be the
            //opposite of column selection allowed.
            if (!selecionar_celula) {
                tbPesquisa.setRowSelectionAllowed(!columnCheck);
            }

        } else if ("Cell Selection" == command) {
            
            tbPesquisa.setCellSelectionEnabled(selecionar_celula);
        } else if ("Multiple Interval Selection" == command) { 
            tbPesquisa.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            //If cell selection is on, turn it off.
            if (selecionar_celula) {
                selecionar_celula = false;
                tbPesquisa.setCellSelectionEnabled(false);
            }
            //And don't let it be turned back on.
            selecionar_celula = false;
        } else if ("Single Interval Selection" == command) {
            tbPesquisa.setSelectionMode(
                    ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            //Cell selection is ok in this mode.
            selecionar_celula = true;
        } else if ("Single Selection" == command) { 
            tbPesquisa.setSelectionMode(
                    ListSelectionModel.SINGLE_SELECTION);
            //Cell selection is ok in this mode.
            selecionar_celula = true;
            ////////////////////////////////////////////////////////////////////
            lsmPesquisa.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if ( !e.getValueIsAdjusting() ){
                        tbPesquisaLinhaSelecionada();
                        calculoFlutuante();
                    }
                }
                
                /*public void focosLost(ListSelectionEvent e) {
                    if ( !e.getValueIsAdjusting() ){
                        calculoFlutuante();
                    }
                }*/
            });
            
            
        }
    }

    
    private void tbPreferedSize(){        
        try{
            DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(125);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(7).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(85);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(15).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(15).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(16).setPreferredWidth(120);
        tbPesquisa.getColumnModel().getColumn(16).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(17).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(17).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(18).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(18).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(19).setPreferredWidth(130);
        tbPesquisa.getColumnModel().getColumn(19).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(20).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(20).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(20).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(21).setPreferredWidth(130);
        tbPesquisa.getColumnModel().getColumn(21).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(22).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(22).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(22).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(23).setPreferredWidth(120);
        tbPesquisa.getColumnModel().getColumn(23).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(23).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(24).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(24).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(24).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(25).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(25).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(25).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(26).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(26).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(26).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(27).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(27).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(27).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(28).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(28).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(28).setCellRenderer( rendererDireita );
        
            tbPesquisa.setRowHeight(30);
            tbPesquisa.setSelectionBackground(Color.YELLOW);
            tbPesquisa.setSelectionForeground(Color.BLUE);
            
            tbPesquisa.getTableHeader().setReorderingAllowed(true);
            //tbPesquisa.getTableHeader().setResizingAllowed(true);            
            tbPesquisa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        } catch( Exception e ){}
    }
    
    //Query q;
    public void pesquisa(Query q2){
        //q = q2;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();              
    }
    
    String enderecoImportacaoExcel = "";
    public void pesquisaImportacaoExcel( String enderecoExcel2 ){ enderecoImportacaoExcel = enderecoExcel2;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            ImportarExportarExcel ImportarDadosDoExcel = new ImportarExportarExcel();
            
            ImportarDadosDoExcel.Importar( new File( enderecoImportacaoExcel ), tbPesquisa );
            
        } catch( Exception e ){ /*e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();              
    }
    
    String enderecoImportacaoExcelRA = "";
    public void pesquisaImportacaoExcelRA( String enderecoExcel2 ){ enderecoImportacaoExcelRA = enderecoExcel2;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            ImportarExportarExcel_RA ImportarExportarExcel_RA = new ImportarExportarExcel_RA();
            
            ImportarExportarExcel_RA.Importar( new File( enderecoImportacaoExcelRA ), tbPesquisa );
            
        } catch( Exception e ){ /*e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();              
    }
    
    String enderecoExportacaoExcel = "";
    public void pesquisaExportacaoExcel( String enderecoExcel2 ){ enderecoExportacaoExcel = enderecoExcel2;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            ImportarExportarExcel ImportarDadosDoExcel = new ImportarExportarExcel();
            
            if( ImportarDadosDoExcel.Exportar( new File( enderecoExportacaoExcel ), tbPesquisa ) == true ){                              
                          
            }
            else{
                JOPM JOptionPaneMod = new JOPM( 2, "EXPORTAÇÃO, "
                        + "\nA EXPORTAÇÃO não foi realizada com sucesso!"
                        + "\nTente Novamente"
                        + "\n", "EXPORTAÇÃO" );
            }
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();              
    }
                
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbPesquisa = new javax.swing.JTable();

        jScrollPane1.setBorder(null);

        tbPesquisa.setModel(tmPesquisa);
        tbPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPesquisaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbPesquisa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPesquisaKeyReleased
        try{
            
            if ( tbPesquisa.getSelectedRow() != -1){
            
                if( evt.getKeyCode() == KeyEvent.VK_DELETE )  {
                    
                    int linhaC  = tbPesquisa.getSelectedRow();
                    int colunaC = tbPesquisa.getSelectedColumn();
                
                    tbPesquisa.setValueAt( "", linhaC, colunaC );        
                }
            }
        } catch( Exception e ){}
    }//GEN-LAST:event_tbPesquisaKeyReleased
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbPesquisa;
    // End of variables declaration//GEN-END:variables
        
    private List<Evento>         list;
    public void mostrarLista(){  try {   controltbPesquisaLinhaSelecionada = "";
        
        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTO", Evento.class );
        List<Evento> listR = q.getResultList();   
              list = listR;
        
            Exportando Exportando = new Exportando("PREPARANDO LISTA");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( listR.size() );
        
            tmPesquisa = new DefaultTableModel( null, new String[]{ "     ID","DATA INICIAL", "DATA FINAL", "NOME DO EVENTO", "TIPO DO EVENTO" } );
            tbPesquisa.setModel(tmPesquisa);
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
            }else{
                String [] campos = new String[] {null, null};
                for (int i = 0; i < listR.size(); i++){
                    Exportando.pbg.setValue( i );
                    
                    tmPesquisa.addRow(campos);
                    
                    tmPesquisa.setValueAt( listR.get(i).getId()                 , i, 0);
                    
                    try{
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String data_formatada = formatter.format( listR.get(i).getDataInicio() );                    
                        tmPesquisa.setValueAt( data_formatada                   , i, 1); 
                    } catch( Exception e ){}
                    
                    try{
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String data_formatada = formatter.format( listR.get(i).getDataFim() );                    
                        tmPesquisa.setValueAt( data_formatada                   , i, 2); 
                    } catch( Exception e ){}
                    
                    tmPesquisa.setValueAt( listR.get(i).getNomeEvento()         , i, 3);
                    tmPesquisa.setValueAt( listR.get(i).getTipoEvento()         , i, 4);                   
                                        
                }
            }  
            
            tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(300);
            tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(130);
            tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
            Exportando.fechar();
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    private List<Eventoprodutos>  listEventoprodutos;
    public void mostrarListaEventoprodutos( int IDSELECIONADA ){  try {     
        
        Exportando Exportando = new Exportando("PREPARANDO VISUALIZAÇÃO");
        Exportando.setVisible(true);
        Exportando.pbg.setMinimum(0);
            
        controltbPesquisaLinhaSelecionada = "mostrarListaEventoprodutos";
        
        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ?", Eventoprodutos.class );
        q.setParameter( 1, IDSELECIONADA ); 
        List<Eventoprodutos> listR = q.getResultList();   
              listEventoprodutos = listR;
              
        ////////////////////////////////////////////////////////////////////////
        Evento EventolistR2 = null;     
        List<Evento> listR2 = null;
        try{  
            Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTO WHERE ID = ?", Evento.class );
            q2.setParameter( 1, IDSELECIONADA ); 
            listR2 = q2.getResultList();  
            
            for (int i = 0; i < listR2.size(); i++){
                EventolistR2 = listR2.get(i);
            }
            
        } catch( Exception e ){}
        ////////////////////////////////////////////////////////////////////////
                    
            Exportando.pbg.setMaximum( listR.size() );
        
            tmPesquisa = new DefaultTableModel( null, new String[]{ "SETOR", "SAP", "DESCRICAO DO MATERIAL", "MRP", "APOSTA", "DEVOLUTIVA", "VENDA","DATA INICIAL", "DATA FINAL", "TIPO DO EVENTO", "NOME DO EVENTO" } );
            tbPesquisa.setModel(tmPesquisa);
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
            }else{
                String [] campos = new String[] {null, null};
                for (int i = 0; i < listR.size(); i++){
                    Exportando.pbg.setValue( i );
                    
                    tmPesquisa.addRow(campos);
                    
                    try{
                        
                        /*SETOR*/
            List<Gbdescricaosetor> XXGbdescricaosetor = null;
            String descricaosetor="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                    XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", listR.get(i).getItem() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor();                        
                }
                
                        }catch( Exception e ){}
                        /*SETOR*/
                        
                        tmPesquisa.setValueAt( descricaosetor              , i, 0);
                    } catch( Exception e ){}
                    
                    try{                   
                        tmPesquisa.setValueAt( listR.get(i).getItem()           , i, 1); 
                    } catch( Exception e ){}
                    
                    Gbproduto Gbproduto = null;
                    String descriProduto = "";  
                    
                    if ( !listR.get(i).getItem().equals("") ){ 
            
            List<Gbproduto> XXGbProdListaSap = null;
            try {
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listR.get(i).getItem().trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){     
                    descriProduto = XXGbProdListaSap.get(0).getDescricao();
                    Gbproduto = XXGbProdListaSap.get(0);                        
                }
            }catch( Exception e ){}
            
                    }else if( Gbproduto != null ){
            
                        
                    }
                    
                    tmPesquisa.setValueAt( descriProduto, i, 2);
                    
                                        
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
            
                    tmPesquisa.setValueAt( mrp , i, 3);
                  
            //APOSTA
            try{                 
                        String str = listR.get(i).getAdicionalEmCaixas();
                        if( str.equals("") || str.equals("null") ){
                            
                            tmPesquisa.setValueAt( ""                           , i, 4); 
                        }
                        else{
                            
                            tmPesquisa.setValueAt( str                          , i, 4); 
                        }
            } catch( Exception e ){}
            
            //DEVOLUTIVA
            try{                 
                        String str = listR.get(i).getRegional();
                        if( str.equals("null") ){
                            
                            tmPesquisa.setValueAt( ""                           , i, 5); 
                        }
                        else{
                            
                            tmPesquisa.setValueAt( str                          , i, 5); 
                        }
            } catch( Exception e ){}
            
            //VENDA
            try{                 
                        String str = listR.get(i).getCd();
                        if( str.equals("null") ){
                            
                            tmPesquisa.setValueAt( ""                           , i, 6); 
                        }
                        else{
                            
                            tmPesquisa.setValueAt( str                          , i, 6); 
                        }
            } catch( Exception e ){}
                    
                    try{
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String data_formatada = formatter.format( EventolistR2.getDataInicio() );                    
                        tmPesquisa.setValueAt( data_formatada                   , i, 7); 
                    } catch( Exception e ){}
                    
                    try{
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String data_formatada = formatter.format( EventolistR2.getDataFim() );                    
                        tmPesquisa.setValueAt( data_formatada                   , i, 8); 
                    } catch( Exception e ){}
                    
                    try{                   
                        tmPesquisa.setValueAt( EventolistR2.getTipoEvento()     , i, 9); 
                    } catch( Exception e ){}
                    
                    try{                   
                        tmPesquisa.setValueAt( EventolistR2.getNomeEvento()     , i, 10); 
                    } catch( Exception e ){}
                                        
                }
            }  
            
            DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
            rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
            
            tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
                                    
            tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(290);
            tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(45);
            tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(70);
            tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            tbPesquisa.getColumnModel().getColumn(4).setCellRenderer( rendererCentro );
            
            tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(70);
            tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
            tbPesquisa.getColumnModel().getColumn(5).setCellRenderer( rendererCentro );
            
            tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(70);
            tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
            tbPesquisa.getColumnModel().getColumn(6).setCellRenderer( rendererCentro );
            
            tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(85);
            tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(80);
            tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(100);
            tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(200);
            tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
            
            Exportando.fechar();
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void mostrarListaSap( String sap, String tipo, List<String> listSap ){  try {     
        controltbPesquisaLinhaSelecionada = "mostrarListaEventoprodutos";
        
        tmPesquisa = new DefaultTableModel( null, new String[]{ "SETOR", "SAP", "APOSTA (CX)", "DESCRICAO DO MATERIAL", "VIA DE ABASTECIMENTO", "DATA INICIAL", "DATA FINAL", "TIPO DO EVENTO", "NOME DO EVENTO" } );
        tbPesquisa.setModel(tmPesquisa);
        
        while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
        
        Query qEv = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTO", Evento.class );
        List<Evento> listEv = qEv.getResultList();
                
        GregorianCalendar gc              = new GregorianCalendar();
        Date              dataHoje        = gc.getTime();
        List<Evento>      listEvVirgentes = new ArrayList<Evento>();
        for (int i = 0; i < listEv.size(); i++){
            
            if( dataHoje.before( listEv.get(i).getDataFim() ) || dataHoje.equals(listEv.get(i).getDataFim() ) ){
                        
                if( tipo.equals("empromocao") ){ 
                    
                    if( dataHoje.after(listEv.get(i).getDataInicio() ) || dataHoje.equals(listEv.get(i).getDataInicio() ) ){
                        
                        listEvVirgentes.add( listEv.get(i) );
                    }
                    
                } 
                else if( tipo.equals("aindavaientrarempromocao") ){ 
                    
                    if( dataHoje.before( listEv.get(i).getDataInicio() ) ){
                        
                        listEvVirgentes.add( listEv.get(i) );
                    }
                }
                else { 
                        
                    listEvVirgentes.add( listEv.get(i) );                  
                } 
            }
        }
                
        for (int i = 0; i < listEvVirgentes.size(); i++){
                        
            if( tipo.equals("sap") ){ try{
                
                Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ? AND ITEM = ?", Eventoprodutos.class );
                q.setParameter( 1, listEvVirgentes.get(i).getId() ); 
                q.setParameter( 2, sap ); 
                List<Eventoprodutos> listEventoprodutosxx = q.getResultList();
                
                for (int it = 0; it < listEventoprodutosxx.size(); it++){
                    if( listEventoprodutosxx.get(it).getItem().equals(sap)  ){
                        mostrarListaSap2( sap, listEventoprodutosxx, listEvVirgentes.get(i) );
                    }
                }
            } catch( Exception e ){} }
            else if( tipo.equals("nome") ){ try{
                
                Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ? AND DESCRICAO_DO_MATERIAL LIKE ?", Eventoprodutos.class );
                q2.setParameter( 1, listEvVirgentes.get(i).getId() ); 
                q2.setParameter( 2, "%"+sap.toUpperCase()+"%" ); 
                List<Eventoprodutos> listEventoprodutos2 = q2.getResultList();
                      //System.out.println(sap + "  ggggggggggg  "+listEventoprodutos.size());
                mostrarListaSap2( sap, listEventoprodutos2, listEvVirgentes.get(i) ); 
            } catch( Exception e ){} }
            else if( tipo.equals("listasap") ){ try{
                
                for (int ix = 0; ix < listSap.size(); ix++){ 
                    
                    //System.out.println(listSap.get(ix));
                    
                    Query q3 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ? AND ITEM = ?", Eventoprodutos.class );
                    q3.setParameter( 1, listEvVirgentes.get(i).getId() ); 
                    q3.setParameter( 2, listSap.get(ix) ); 
                    List<Eventoprodutos> listEventoprodutos3 = q3.getResultList();
                
                    mostrarListaSap2( sap, listEventoprodutos3, listEvVirgentes.get(i) );
                }         
            } catch( Exception e ){} } 
            else if( tipo.equals("empromocao") ){ try{
                
                Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ?", Eventoprodutos.class );
                q.setParameter( 1, listEvVirgentes.get(i).getId() ); 
                List<Eventoprodutos> listEventoprodutosxx = q.getResultList();
                
                mostrarListaSap2( sap, listEventoprodutosxx, listEvVirgentes.get(i) );
                
            } catch( Exception e ){} }
        }      
       
    } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } }
        
    
    public void mostrarListaSap2( String sap, List<Eventoprodutos> listR, Evento EventolistR2 ){  try {   
                            
        ////////////////////////////////////////////////////////////////////////        
        //Exportando Exportando = new Exportando("PREPARANDO VISUALIZAÇÃO");
        //Exportando.setVisible(true); Exportando.pbg.setMinimum(0); Exportando.pbg.setMaximum( listR.size() );
                                
        //while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
        if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
        }else{
 
            for (int i = 0; i < listR.size(); i++){       //Exportando.pbg.setValue( i );
                         
                ////////////////////////////////////////////////////////////////////////////////   
                /*SETOR*/
            List<Gbdescricaosetor> XXGbdescricaosetor = null;
            String descricaosetor="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdescricaosetor.class, JPAUtil.em());
                                        
                    XXGbdescricaosetor = (List<Gbdescricaosetor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdescricaosetor.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", listR.get(i).getItem() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbdescricaosetor.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    descricaosetor = XXGbdescricaosetor.get(0).getDescricaosetor();                        
                }
                
                        }catch( Exception e ){}
                        /*SETOR*/
                ////////////////////////////////////////////////////////////////////////////////                                     
                String t0=""; try{ t0 = listR.get(i).getItem(); } catch( Exception e ){}
                ////////////////////////////////////////////////////////////////////////////
                String t1=""; 
                try{ 
                    t1 = listR.get(i).getAdicionalEmCaixas();
                    if( t1.equals("") || t1.equals("null") ){
                        t1="";
                    }
                } catch( Exception e ){}
                ////////////////////////////////////////////////////////////////////////////
                Gbproduto Gbproduto = null;
                    String descriProduto = "";  
                    
                    if ( !listR.get(i).getItem().equals("") ){ 
            
            List<Gbproduto> XXGbProdListaSap = null;
            try {
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listR.get(i).getItem().trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){     
                    descriProduto = XXGbProdListaSap.get(0).getDescricao();
                    Gbproduto = XXGbProdListaSap.get(0);                        
                }
            }catch( Exception e ){}
            
                    }else if( Gbproduto != null ){
            
                        
                    }
                ////////////////////////////////////////////////////////////////////////////
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
                ////////////////////////////////////////////////////////////////////////////
                String t4=""; 
                try{ 
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String data_formatada = formatter.format( EventolistR2.getDataInicio() ); 
                    t4 = data_formatada; 
                } catch( Exception e ){}
                ////////////////////////////////////////////////////////////////////////////
                String t5=""; 
                try{ 
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String data_formatada = formatter.format( EventolistR2.getDataFim() ); 
                    t5 = data_formatada; 
                } catch( Exception e ){}
                ////////////////////////////////////////////////////////////////////////////
                String t6=""; try{ t6 = EventolistR2.getTipoEvento(); } catch( Exception e ){}
                ////////////////////////////////////////////////////////////////////////////
                String t7=""; try{ t7 = EventolistR2.getNomeEvento(); } catch( Exception e ){}
                ////////////////////////////////////////////////////////////////////////////
    
                tmPesquisa.addRow(new Object[]{ 
                    descricaosetor,
                    t0, 
                    t1, 
                    descriProduto, 
                    mrp, 
                    t4, 
                    t5, 
                    t6, 
                    t7 
                });
                ////////////////////////////////////////////////////////////////////////////////                                        
            }
        }  
            
            DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
            rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
            
            tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(150);
            tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(85);
            tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
            tbPesquisa.getColumnModel().getColumn(2).setCellRenderer( rendererCentro );
            
            tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(290);
            tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(110);
            tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(85);
            tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
            
            tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(300);
            tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
            
            //Exportando.fechar();
            ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    int contadorNInicial = 0;
    
    public void analiseMostrarListaSap( String layout, String sap, String comando, List<String> listSap ){  try {     
        
        setarCabecalhoTabelaGenerica(); 
        
        Thread.sleep( 1 );
                
        if( comando.equals("sap") ){ try{
                
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
            q.setParameter( 1, sap ); 
            List<Gbproduto> GbprodutoSap = q.getResultList();   
                
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( GbprodutoSap.size() );
                        
            for (int it = 0; it < GbprodutoSap.size(); it++){
                Exportando.pbg.setValue( it );
                
                if( GbprodutoSap.get(it).getMaterial().equals(sap)  ){
                    analiseGenericaTabela( "", GbprodutoSap.get(it) ); 
                }
            }
            
            Exportando.fechar();
        } catch( Exception e ){ } }
        else if( comando.equals("produtoporfornecedor") ){ try{
            
            Thread.sleep( 1 );  try{            
                tmPesquisa = new DefaultTableModel( null, new String[]{ 
                    "MATERIAL", "DESCRICAO DO MATERIAL", "GRP", "MRP", "CÓDIGO FORNECEDOR", "NOME FORNECEDOR", "LEAD TIME" , "MODALIDADE"          
                } );
                while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); } tbPesquisa.setModel(tmPesquisa);
            } catch( Exception e ){} Thread.sleep( 1 );
               
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDOR WHERE MATERIAL = ?", Gbprodutoporfornecedor.class );
            q.setParameter( 1, sap ); 
            List<Gbprodutoporfornecedor> Gbprodutoporfornecedor = q.getResultList();   
                
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( Gbprodutoporfornecedor.size() );
                        
            analiseProdutoPorFornecedor( sap, Gbprodutoporfornecedor ); 
            
            Exportando.fechar();
        } catch( Exception e ){ } }
        /*else if( tipo.equals("mrpT") ){ try{
                
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE COD_MRP = ?", Gbproduto.class );
            q.setParameter( 1, sap ); 
            List<Gbproduto> GbprodutoSap = q.getResultList();

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( GbprodutoSap.size() );
                        
            for (int it = 0; it < GbprodutoSap.size(); it++){
                Exportando.pbg.setValue( it );
                
                analiseMostrarListaSap2Layout( layout, sap, GbprodutoSap ); 
            }
            
            Exportando.fechar();
        } catch( Exception e ){ } }*/
        else if( comando.equals("mrp") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO MRP: "+sap); Exportando.setVisible(true); Exportando.pbg.setMinimum(0);
                                    
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBMRP WHERE MRP = ?", Gbmrp.class );
            q.setParameter( 1, sap ); 
            List<Gbmrp> listTo = q.getResultList();
            
            Exportando.pbg.setMaximum( listTo.size() );

            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
            
            List<Gbproduto> XXGbprodutoListaSap = null;
            try{
                for(int i = 0; i < listTo.size(); i++ ){ 
                    Exportando.pbg.setValue( i );
                
                    XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listTo.get(i).getMaterial() );
                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                        //GbprodutoListaSap.add(XXGbprodutoListaSap.get(0) );
                        analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) );
                    }
            } }catch( Exception e ){}
            
            //analiseMostrarListaSap2Layout( layout, sap, GbprodutoListaSap );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("ean") ){ try{
                
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE EAN = ?", Gbproduto.class );
            q.setParameter( 1, sap ); 
            List<Gbproduto> GbprodutoSap = q.getResultList();

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( GbprodutoSap.size() );
                        
            for (int it = 0; it < GbprodutoSap.size(); it++){
                Exportando.pbg.setValue( it );
                
                if( GbprodutoSap.get(it).getEan().equals(sap)  ){
                    analiseGenericaTabela( "", GbprodutoSap.get(it) ); 
                }
            }
            
            Exportando.fechar();
        } catch( Exception e ){ } }
        else if( comando.equals("grupo") ){ try{
                                        
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE GRUPO = ?", Gbproduto.class );
            q.setParameter( 1, sap ); 
            List<Gbproduto> GbprodutoSap = q.getResultList();
            
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( GbprodutoSap.size() );
                
            //analiseMostrarListaSap2Layout( layout, sap, GbprodutoSap );
            for (int it = 0; it < GbprodutoSap.size(); it++){
                Exportando.pbg.setValue( it );
                
                analiseGenericaTabela( "", GbprodutoSap.get(it) ); 
            }
            
            Exportando.fechar();
        } catch( Exception e ){ } }
        else if( comando.equals("nome") ){ try{
                
            Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE DESCRICAO LIKE ?", Gbproduto.class );
                  q2.setParameter( 1, sap.toUpperCase() ); 
            List<Gbproduto> GbprodutoNome = q2.getResultList();

            contadorNInicial = Menu_Pesquisa.contadorN;
            
            for (int it = 0; it < GbprodutoNome.size(); it++){
                
                analiseGenericaTabela( "", GbprodutoNome.get(it) ); 
                
                if(contadorNInicial != Menu_Pesquisa.contadorN ){ break; }
            }

        } catch( Exception e ){} }
        else if( comando.equals("listasap") ){ try{
            
            List<Gbproduto> GbprodutoListaSapXX = new ArrayList<Gbproduto>();
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
            List<Gbproduto> GbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro_Return_List(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listSap );
                
                for (int iv = 0; iv < GbprodutoListaSap.size(); iv++){
                    
                    boolean bb = false;
                    for (int cc = 0; cc < GbprodutoListaSapXX.size(); cc++){
                        
                        if( GbprodutoListaSapXX.get(cc).getMaterial().equals(GbprodutoListaSap.get(iv).getMaterial() ) ){
                            
                            bb = true;
                            break;
                        }                        
                    }
                    if( bb == false ){
                        
                        GbprodutoListaSapXX.add( GbprodutoListaSap.get(iv) ); 
                    }
                }
            //}  
                
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( GbprodutoListaSapXX.size() );    
            
            //analiseMostrarListaSap2Layout( layout, sap, GbprodutoListaSapXX ); 
            for (int it = 0; it < GbprodutoListaSapXX.size(); it++){
                Exportando.pbg.setValue( it );
                
                analiseGenericaTabela( "", GbprodutoListaSapXX.get(it) ); 
            }    
            
            Exportando.fechar();
        } catch( Exception e ){} } 
        else if( comando.equals("empromocao") ){ try{
            /*    
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ?", Eventoprodutos.class );
            q.setParameter( 1, listEvVirgentes.get(i).getId() ); 
            List<Eventoprodutos> listEventoprodutosxx = q.getResultList();
                
            mostrarListaSap2( sap, listEventoprodutosxx, listEvVirgentes.get(i) );
            */    
        } catch( Exception e ){} }  
        else if( comando.equals("TODOS OS PRODUTOS") ){ try{
                                                    
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO", Gbproduto.class );
            List<Gbproduto> listTo = q.getResultList();
            
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( listTo.size() ); 
                
            //analiseMostrarListaSap2Layout( layout, sap, listTo );
            for (int it = 0; it < listTo.size(); it++){
                Exportando.pbg.setValue( it );
                
                analiseGenericaTabela( "", listTo.get(it) ); 
            } 
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("LISTAR PRODUTOS DUPLICADOS") ){ try{
            setarCabecalhoGenericaTabelaDuplicado();
                                                    
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO", Gbproduto.class );
            List<Gbproduto> listTo = q.getResultList();
            
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( listTo.size() ); 
                
            //analiseMostrarListaSap2Layout( layout, sap, listTo );
            for (int it = 0; it < listTo.size(); it++){
                Exportando.pbg.setValue( it );
                
                genericaTabelaDuplicado( "", listTo.get(it) ); 
            } 
                
            Exportando.fechar();
        } catch( Exception e ){ e.printStackTrace(); } }
        else if( comando.equals("PRODUTOS COM DISPONIBILIDADE BAIXA") ){            
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE DISPONIBILIDADE > ?", Gbproduto.class );
            q.setParameter( 1, 0 ); 
            List<Gbproduto> listTo = q.getResultList();
            
            Exportando.pbg.setMaximum( listTo.size() );
            
            for (int it = 0; it < listTo.size(); it++){
                Exportando.pbg.setValue( it );
                
                
                /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            int disponibilidade = 0;
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", listTo.get(it).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    disponibilidade = XXGbzrisListaSap.get(0).getDisponibilidade();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
                //System.out.println( listTo.get(it).getDescricao() );

/**/                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquelojab141.class, "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", listTo.get(it).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") && XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja() <= disponibilidade ){
   
                    analiseGenericaTabela( "", listTo.get(it) ); 
                }
            }
           
            Exportando.fechar();
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "PRODUTOS COM DISPONIBILIDADE BAIXA, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        }    
        else if( comando.equals("DISPONIBILIDADE BAIXA TOP350/2000/MIX") ){            
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE NOM_CLASSE2 = ? OR ( NOM_CLASSE2 = ? OR NOM_CLASSE2 = ? )", Gbsuply.class );            
            q.setParameter( 1, "TOP350" ); 
            q.setParameter( 2, "TOP2000" ); 
            q.setParameter( 3, "MIX" );            
            List<Gbsuply> listTo = q.getResultList();
            
            Exportando.pbg.setMaximum( listTo.size() );

            for (int it = 0; it < listTo.size(); it++){
                Exportando.pbg.setValue( it );
                
                
            /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            int disponibilidade = 0;
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", listTo.get(it).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    disponibilidade = XXGbzrisListaSap.get(0).getDisponibilidade();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/

/**/           List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquelojab141.class, "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", listTo.get(it).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial().trim(); }catch( Exception e ){}
                
                if( !rbusca.equals("") && XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja() <= disponibilidade ){
                        
                        //System.out.println( "Loja: " + listTo.get(it).getEstoqueLoja() + " - " + "Disponibilidade: "+ listTo.get(it).getDisponibilidade() );
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
                    q2.setParameter( 1, rbusca ); 
                    List<Gbproduto> listToV = q2.getResultList();
                    for (int vv = 0; vv < listToV.size(); vv++){
                
                        analiseGenericaTabela( "", listToV.get(vv) ); 
                    }                         
                }
                //}
            }
            
            Exportando.fechar();
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "DISPONIBILIDADE BAIXA TOP350/200/MIX, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        }
        else if( comando.equals("TOP350/200/MIX 'ZERO'") ){            
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE NOM_CLASSE2 = ? OR ( NOM_CLASSE2 = ? OR NOM_CLASSE2 = ? )", Gbsuply.class );            
            q.setParameter( 1, "TOP350" ); 
            q.setParameter( 2, "TOP2000" ); 
            q.setParameter( 3, "MIX" );            
            List<Gbsuply> listTo = q.getResultList();
            
            Exportando.pbg.setMaximum( listTo.size() );

            for (int it = 0; it < listTo.size(); it++){
                Exportando.pbg.setValue( it );
                
                
            /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            int disponibilidade = 0;
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", listTo.get(it).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    disponibilidade = XXGbzrisListaSap.get(0).getDisponibilidade();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/

/**/           List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquelojab141.class, "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", listTo.get(it).getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial().trim(); }catch( Exception e ){}
                
                if( !rbusca.equals("") && XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja() == 0 ){
                        
                        //System.out.println( "Loja: " + listTo.get(it).getEstoqueLoja() + " - " + "Disponibilidade: "+ listTo.get(it).getDisponibilidade() );
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
                    q2.setParameter( 1, rbusca ); 
                    List<Gbproduto> listToV = q2.getResultList();
                    for (int vv = 0; vv < listToV.size(); vv++){
                
                        analiseGenericaTabela( "", listToV.get(vv) ); 
                    }                         
                }
                //}
            }
            
            Exportando.fechar();
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "DISPONIBILIDADE BAIXA TOP350/200/MIX, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        }
        else if( comando.equals("PRODUTOS LOJA TOP 350") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO TOP'S 350..."); Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                                    
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE NOM_CLASSE2 = ?", Gbsuply.class );
            q.setParameter( 1, "TOP350" ); 
            List<Gbsuply> listTo = q.getResultList();
            
            Exportando.pbg.setMaximum( listTo.size() );
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
            
            List<Gbproduto> XXGbprodutoListaSap = null;
            try{
                for(int i = 0; i < listTo.size(); i++ ){ 
                    Exportando.pbg.setValue( i );
                
                    XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listTo.get(i).getMaterial() );
                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                        analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) ); 
                    }
            } }catch( Exception e ){}
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS LOJA TOP 2000") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO TOP'S 2000..."); Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE NOM_CLASSE2 = ?", Gbsuply.class );
            q.setParameter( 1, "TOP2000" ); 
            List<Gbsuply> listTo = q.getResultList();
            
            Exportando.pbg.setMaximum( listTo.size() );
                
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
            
            List<Gbproduto> XXGbprodutoListaSap = null;
            try{
                for(int i = 0; i < listTo.size(); i++ ){
                    Exportando.pbg.setValue( i );
                
                    XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listTo.get(i).getMaterial() );
                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                        analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) ); 
                    }
                }                      
            }catch( Exception e ){}
                
            Exportando.fechar();
        } catch( Exception e ){ } }
        else if( comando.equals("PRODUTOS LOJA MIX") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO TOP'S 2000..."); Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE NOM_CLASSE2 = ?", Gbproduto.class );
            q.setParameter( 1, "MIX" ); 
            List<Gbproduto> listTo = q.getResultList();
            
            Exportando.pbg.setMaximum( listTo.size() );
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
            
            List<Gbproduto> XXGbprodutoListaSap = null;
            try{
                for(int i = 0; i < listTo.size(); i++ ){
                    Exportando.pbg.setValue( i );
                
                    XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listTo.get(i).getMaterial() );
                    
                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                        analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) ); 
                    }
                }                      
            }catch( Exception e ){}
                
            Exportando.fechar();
        } catch( Exception e ){ } }
        else if( comando.equals("PRODUTOS ZERO NA LOJA E NO CD TOP 350") ){ try{
    
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 = ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ? AND COD_MRP = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "TOP350" ); 
            q.setParameter( 4, "G4" ); 
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } }  
        else if( comando.equals("PRODUTOS ZERO NA LOJA E NO CD TOP 2000") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 = ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ? AND COD_MRP = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "TOP2000" ); 
            q.setParameter( 4, "G4" ); 
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } }
        else if( comando.equals("PRODUTOS ZERO NA LOJA E NO CD MIX") ){ try{
      
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 = ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ? AND COD_MRP = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "MIX" ); 
            q.setParameter( 4, "G4" ); 
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 != ? AND ESTOQUE_LOJA  = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 350") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 != ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "TOP350" );
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 2000") ){ try{
    
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 != ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "TOP2000" );
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO MIX") ){ try{
     
            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 != ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "MIX" );
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 350 SEM PEDIDO (ATIVO)") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 != ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ? AND PEDIDO_CD184_ATIVO = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "TOP350" ); 
            q.setParameter( 4, "0" ); 
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 2000 SEM PEDIDO (ATIVO)") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 != ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ? AND PEDIDO_CD184_ATIVO = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "TOP2000" ); 
            q.setParameter( 4, "0" ); 
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP MIX SEM PEDIDO (ATIVO)") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ESTOQUE_CD_184 != ? AND ESTOQUE_LOJA  = ? AND NOM_CLASSE2 = ? AND PEDIDO_CD184_ATIVO = ?", Gbproduto.class );
            q.setParameter( 1, "0" ); 
            q.setParameter( 2, "0" ); 
            q.setParameter( 3, "MIX" ); 
            q.setParameter( 4, "0" ); 
            List<Gbproduto> listTo = q.getResultList();
                
            analiseMostrarListaSap2Layout( layout, sap, listTo );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS TOP 350 RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE STS_ESTOQUETIPO = ? AND NOM_CLASSE2 = ? AND PEDIDO_CD184_ATIVO = ? AND ESTOQUE_CD_184 != ?", Gbproduto.class );
            q1.setParameter( 1, "RUPTURA" ); 
            q1.setParameter( 2, "TOP350" ); 
            q1.setParameter( 3, "0" ); 
            q1.setParameter( 4, "0" ); 
            List<Gbproduto> listTo1 = q1.getResultList();

            analiseMostrarListaSap2Layout( layout, sap, listTo1 );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS TOP 2000 RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE STS_ESTOQUETIPO = ? AND NOM_CLASSE2 = ? AND PEDIDO_CD184_ATIVO = ? AND ESTOQUE_CD_184 != ?", Gbproduto.class );
            q1.setParameter( 1, "RUPTURA" ); 
            q1.setParameter( 2, "TOP2000" ); 
            q1.setParameter( 3, "0" ); 
            q1.setParameter( 4, "0" ); 
            List<Gbproduto> listTo1 = q1.getResultList();

            analiseMostrarListaSap2Layout( layout, sap, listTo1 );
                
            Exportando.fechar();
        } catch( Exception e ){ } }   
        else if( comando.equals("PRODUTOS MIX RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE STS_ESTOQUETIPO = ? AND NOM_CLASSE2 = ? AND PEDIDO_CD184_ATIVO = ? AND ESTOQUE_CD_184 != ?", Gbproduto.class );
            q1.setParameter( 1, "RUPTURA" ); 
            q1.setParameter( 2, "MIX" ); 
            q1.setParameter( 3, "0" ); 
            q1.setParameter( 4, "0" ); 
            List<Gbproduto> listTo1 = q1.getResultList();

            analiseMostrarListaSap2Layout( layout, sap, listTo1 );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS QUE ENTRARAM NO CD ONTEM") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
                            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE ENTROU_CD184_ONTEM != ?", Gbproduto.class );
            q1.setParameter( 1, "0" ); 
            List<Gbproduto> listTo1 = q1.getResultList();

            analiseMostrarListaSap2Layout( layout, sap, listTo1 );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("TOP 350 DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
            int sapInt = 0; try{ sapInt = Integer.parseInt(sap); } catch( Exception e ){} 
                            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE NOM_CLASSE2 = ? AND ESTOQUE_CD_184 <= ? AND ESTOQUE_LOJA <= ?", Gbproduto.class );
            q1.setParameter( 1, "TOP350" ); 
            q1.setParameter( 2, 0        ); 
            q1.setParameter( 3, sapInt   ); 
            List<Gbproduto> listTo1 = q1.getResultList();

            analiseMostrarListaSap2Layout( layout, sap, listTo1 );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("TOP 2000 DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
            int sapInt = 0; try{ sapInt = Integer.parseInt(sap); } catch( Exception e ){} 
                            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE NOM_CLASSE2 = ? AND ESTOQUE_CD_184 <= ? AND ESTOQUE_LOJA <= ?", Gbproduto.class );
            q1.setParameter( 1, "TOP2000" ); 
            q1.setParameter( 2, 0        ); 
            q1.setParameter( 3, sapInt   ); 
            List<Gbproduto> listTo1 = q1.getResultList();

            analiseMostrarListaSap2Layout( layout, sap, listTo1 );
                
            Exportando.fechar();
        } catch( Exception e ){ } }  
        else if( comando.equals("MIX DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
            int sapInt = 0; try{ sapInt = Integer.parseInt(sap); } catch( Exception e ){} 
                            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE NOM_CLASSE2 = ? AND ESTOQUE_CD_184 <= ? AND ESTOQUE_LOJA <= ?", Gbproduto.class );
            q1.setParameter( 1, "MIX" ); 
            q1.setParameter( 2, 0        ); 
            q1.setParameter( 3, sapInt   ); 
            List<Gbproduto> listTo1 = q1.getResultList();

            analiseMostrarListaSap2Layout( layout, sap, listTo1 );
                
            Exportando.fechar();
        } catch( Exception e ){ } } 
        else if( comando.equals("PRODUTOS QUE DISPARARAM NO RA") ){ try{

            Exportando Exportando = new Exportando("PROCURANDO DADOS...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                                    
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBRA", Gbra.class ); 
            List<Gbra> listTo1 = q1.getResultList();
            
            Exportando.pbg.setMaximum( listTo1.size() );
            
            for (int x = 0; x < listTo1.size(); x++){
                
                Exportando.pbg.setValue( x );
                        
                String sapX = ""; try{ sapX = listTo1.get(x).getMaterial().trim(); }catch( Exception e ){ }
                       
                List<Gbproduto> XXGbProdListaSap = null;
                try {
                    try{ 
                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                        XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sapX );
                    }catch( Exception e ){ }
                
                    String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                    if( !rbusca.equals("") ){                    
                        analiseGenericaTabela( "", XXGbProdListaSap.get(0) ); 
                    }
                    else{

                    }                    
                } catch( Exception e ){ } 
            } 
                    
            Exportando.fechar();
        } catch( Exception e ){ } } 
       
    } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n analiseMostrarListaSap2Layout"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } }
    
    public void analiseMostrarListaSap2Layout( String layout, String sap, List<Gbproduto> listR ){ try { 
        
        if( layout.equals("") ){
            
            analiseMostrarListaSap2( sap, listR );
        }
        else if( layout.equals("gd") ){
            
            analiseMostrarListaSap2DIRETOLOJA( sap, listR );
        }
        else if( layout.equals("listarprodutoporfornecedor") ){
            
            Thread.sleep( 1 );  try{            
                tmPesquisa = new DefaultTableModel( null, new String[]{ 
                    "MATERIAL", "DESCRICAO DO MATERIAL", "GRP", "MRP", "CÓDIGO FORNECEDOR", "NOME FORNECEDOR", "LEAD TIME" , "MODALIDADE"           
                } );
                while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); } tbPesquisa.setModel(tmPesquisa);
            } catch( Exception e ){} Thread.sleep( 1 );
                          
            for (Gbproduto listR1 : listR) { 
                
                Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDOR WHERE MATERIAL = ?", Gbprodutoporfornecedor.class );
                q.setParameter( 1, listR1.getMaterial() ); 
                List<Gbprodutoporfornecedor> Gbprodutoporfornecedor = q.getResultList();  
                
                analiseProdutoPorFornecedor( sap, Gbprodutoporfornecedor );  
            }
        }
    } catch( Exception e ){} }
    
    public void analiseMostrarListaSap2( String sap, List<Gbproduto> listR ){ try { 
        Thread.sleep( 1 );  
        try{
            
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "EAN", "DESCRICAO DO MATERIAL", "E/S", "UBM", "Q.EMB", "GRP", "MRP", "EST. CD001", "EST. CD289", "EST. CD184", "EST. LOJA", "SUGESTÃO", "  DDE  ", "PED. ATIVO", "REM.", "SAIU CD",  "ENT. LOJA", "CD ONTEM", "CLASSE", "SETOR", "EST. TIPO", "COD. FORNEC.", "NOME FORNEC.", "RA", "PONTO EXTRA", "EST. MÍNIMO", "DISPONIBILIDADE", "VENDA ULT. SEM", "VENDA 04 SEM", "VENDA 12 SEM", "DIAS SEM VENDAS", "O.S"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){}
        Thread.sleep( 1 );
                                    
        ////////////////////////////////////////////////////////////////////////      
        if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
        }else{
 
        for (Gbproduto listR1 : listR) {    
            
            String sapTab="";
            try {
                sapTab = listR1.getMaterial();
            }catch( Exception e ){}
            
            String ean="";
            try {
                ean = listR1.getEan();
            }catch( Exception e ){}
            
            String nomeProd="";
            try {
                nomeProd = listR1.getDescricao();
            }catch( Exception e ){}
            
            String qtdEmEs="";
            try {
                qtdEmEs = listR1.getQtdxes();
            }catch( Exception e ){}
            
            String Und="";
            try {
                Und = listR1.getUnd();
            }catch( Exception e ){}

            /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String qtdEmPed="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            String grupo="";
            try {
                grupo = listR1.getGrupo();
                if( grupo.equals("") || grupo.equals("null") ){ grupo=""; }
            }catch( Exception e ){}
            
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001 WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289 WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184 WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBENTROUCDONTEM WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    pontExtra = XXGbraListaSap.get(0).getPeriodo();                        
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
                            "SELECT * FROM SCHEMAJMARY.GBDIASSEMVENDA WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opSemana = XXGbraListaSap.get(0).getOportsemana();                        
                }
            }catch( Exception e ){}
            
            tmPesquisa.addRow(new Object[]{ sapTab, ean, nomeProd, qtdEmEs, Und, qtdEmPed, grupo, mrp, b001, b289, b184, b141, sugestao, dde, ped7D, remessa, saiuCD, entrouLJ, cdOntem, classe, setor, status, codForn, nomForn, ra, pontExtra, EstoqueMinimo, disponibilidade, venda_ultima_semana, venda_4_semanas, venda_12_semanas, ativosv, opSemana });
        }
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(125);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(35);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(35);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(85);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(15).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(15).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(15).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(16).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(16).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(16).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(17).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(17).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(17).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(18).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(18).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(19).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(19).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(20).setPreferredWidth(120);
        tbPesquisa.getColumnModel().getColumn(20).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(21).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(21).setResizable(true);
       
        tbPesquisa.getColumnModel().getColumn(22).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(22).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(23).setPreferredWidth(130);
        tbPesquisa.getColumnModel().getColumn(23).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(23).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(24).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(24).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(24).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(25).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(25).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(25).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(26).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(26).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(26).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(27).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(27).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(27).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(28).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(28).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(28).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(29).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(29).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(29).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(30).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(30).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(30).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(31).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(31).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(31).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(32).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(32).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(32).setCellRenderer( rendererDireita );
            
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void analiseProdutoPorFornecedor( String sap, List<Gbprodutoporfornecedor> listR ){ try { 
                                            
        ////////////////////////////////////////////////////////////////////////      
        if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
        }else{
 
        for (Gbprodutoporfornecedor listR1 : listR) {    
            
            String sapTab="";
            List<Gbproduto> XXGbProdListaSap = null;
            try {
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listR1.getMaterial() );
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
            
            String grupo="";
            try {
                grupo = XXGbProdListaSap.get(0).getGrupo();
            }catch( Exception e ){}
            
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}

            /*ZRIS*/
            String codigoFornecedor="";
            try {                
                
                codigoFornecedor = listR1.getCodigofornecedor();
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            String nomeFornec="";
            List<Fornecedor> XXGbForndListaSap = null;
            try {
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Fornecedor.class, JPAUtil.em());
                                        
                    XXGbForndListaSap = (List<Fornecedor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Fornecedor.class, 
                            "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEOURAZAOSOCIAL = ?", listR1.getCodigofornecedor() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbForndListaSap.get(0).getNomefantasia(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nomeFornec = XXGbForndListaSap.get(0).getNomefantasia();                        
                }
            }catch( Exception e ){}
            
            String leadTime="";
            try {                
                
                leadTime = XXGbForndListaSap.get(0).getLeadtime();                
            }catch( Exception e ){}
            
            String modalidade="";
            try {                
                
                modalidade = XXGbForndListaSap.get(0).getCd();                
            }catch( Exception e ){}
            
                       
            tmPesquisa.addRow(new Object[]{ sapTab, nomeProd, grupo, mrp, codigoFornecedor, nomeFornec, leadTime, modalidade });
        }
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(30);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(43);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(4).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(260);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
            
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
   
    public void analiseDiretoLoja( List<Gbdiretoloja> listR ){ try { 
        
        Thread.sleep( 1 );  try{            
                tmPesquisa = new DefaultTableModel( null, new String[]{ 
                    "MATERIAL", "DESCRICAO DO MATERIAL", "ZRIS", "GERADO", "DDE*", "VENDA 12 S.", "DISP. 15 DIAS", "EST.LOJA", "CLASSE", "CÓDIGO FORNECEDOR", "NOME FORNECEDOR", "PONTO EXTRA", "ELENCO"           
                } );
                while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); } tbPesquisa.setModel(tmPesquisa);
            } catch( Exception e ){} Thread.sleep( 1 );
                                            
        ////////////////////////////////////////////////////////////////////////      
        if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
        }else{
 
        for (Gbdiretoloja listR1 : listR) {    
            
            String sapTab="";
            List<Gbproduto> XXGbProdListaSap = null;
            try {
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                            "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listR1.getMaterial() );
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
                zris = listR1.getInputzris();
            }catch( Exception e ){}
            
            int pedidoGerado=0;
            try {
                pedidoGerado = listR1.getPedidogerado();
            }catch( Exception e ){}
            
            
            
            List<Gbzris> XXGbzrisListaSap = null;
            String dde="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", listR1.getMaterial() );
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
                
                codigoFornecedor = listR1.getFornecedor();
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            String nomeFornec="";
            List<Fornecedor> XXGbForndListaSap = null;
            try {
                try{ 
                    String x = listR1.getFornecedor().toUpperCase().trim();
                    
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
                            "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA WHERE MATERIAL = ?", listR1.getMaterial() );
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
                            "SELECT * FROM SCHEMAJMARY.GBELENCO WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    elenco = XXGbraListaSap.get(0).getPeriodo();                        
                }
            }catch( Exception e ){}
            
                       
            tmPesquisa.addRow(new Object[]{ sapTab, nomeProd, zris, pedidoGerado, dde, venda_12_semanas, disponibilidade, b141, classe, codigoFornecedor, nomeFornec, pontExtra, elenco });
        }
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(260);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
            
        //Exportando.fechar();                                                                                                                                                  
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void analiseMostrarListaSap2DIRETOLOJA( String sap, List<Gbproduto> listR ){ try { 
        Thread.sleep( 1 );  
        try{
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "DESCRICAO DO MATERIAL", "UBM", "Q.EMB", "MRP", "EST. LOJA", "SUGESTÃO", "  DDE  ", "EST. MÍNIMO", "DISPONIBILIDADE", "VENDA ULT. SEM", "VENDA 04 SEM", "VENDA 12 SEM", "CLASSE", "DIAS SEM VENDAS"           
            } );
            tbPesquisa.setModel(tmPesquisa);
        } catch( Exception e ){}
        Thread.sleep( 1 );
                                    
        ////////////////////////////////////////////////////////////////////////      
        if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
        }else{
 
        for (Gbproduto listR1 : listR) {            
            String t0="";
            try {
                t0 = listR1.getMaterial();
            }catch( Exception e ){}
            String t2="";
            try {
                t2 = listR1.getDescricao();
            }catch( Exception e ){}
            String t3="";
            try {
                t3 = listR1.getUnd();
            }catch( Exception e ){}
                        
            /*ZRIS*/
            List<Gbzris> XXGbzrisListaSap = null;
            String t4="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    t4 = XXGbzrisListaSap.get(0).getQtdxemb();                        
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            String t6="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    t6 = XXGbmrpListaSap.get(0).getMrp();                        
                }
                
            }catch( Exception e ){}
            
            int t8 = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                        
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbestoquelojab141.class, "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){
                    //System.out.println( "Loja: " + listTo.get(it).getEstoqueLoja() + " - " + "Disponibilidade: "+ listTo.get(it).getDisponibilidade() );
                    t8 = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            String sugestao ="";
            try {
                sugestao = "";
            }catch( Exception e ){}
            
            int dde = 0;
            try {
                dde = XXGbzrisListaSap.get(0).getDde();
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
            
            /*SUPLAIN*/
            List<Gbsuply> XXGbsuplyListaSap = null;
            String t12="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbsuply.class, JPAUtil.em());
                                        
                    XXGbsuplyListaSap = (List<Gbsuply>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbsuply.class, 
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    t12 = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
            
            int ativosv = 0;
            try {
                
                List<Gbdiassemvenda> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbdiassemvenda.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Gbdiassemvenda>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbdiassemvenda.class, 
                            "SELECT * FROM SCHEMAJMARY.GBDIASSEMVENDA WHERE MATERIAL = ?", listR1.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ativosv = XXGbraListaSap.get(0).getDiassemvenda();                        
                }
                
            }catch( Exception e ){}
            
            tmPesquisa.addRow(new Object[]{ t0, t2, t3, t4, t6, t8, sugestao, dde, EstoqueMinimo, disponibilidade, venda_ultima_semana, venda_4_semanas, venda_12_semanas, t12, ativosv });
        }
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        //tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(5).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(6).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(7).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(85);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(85);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(85);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
                    
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    String filtro = "";
    int    coluna = 1;
    public void filtro( String f, int c ) { filtro = f; coluna = c;    
        try { filtro = f.trim(); } catch( Exception e ){}
        
        new Thread() {   @Override public void run() { try { 
            Exportando Exportando = new Exportando("APLICANDO O FILTRO...");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            
            int numFila=tmPesquisa.getRowCount();
            
            Exportando.pbg.setMaximum( numFila );
            
            Thread.sleep( 1 );
                                                
            for (int i = 0; i < numFila; i++) {     Exportando.pbg.setValue( i );
                
                try{
                    
                    String str = String.valueOf( tmPesquisa.getValueAt(i, coluna) ).trim();  
                    
                    if( !str.equals(filtro) ){
                        
                        tmPesquisa.removeRow( i );       
                        i = -1;
                        //System.out.println( str + "  -------------  " + i + "  " + coluna);
                    }
                } catch( Exception e ){ }
                //System.out.println( "++++++++++++++  " + i + "  " + coluna);
            }

            Exportando.fechar();
            
        } catch( Exception e ){ System.out.println("Exportar - "); e.printStackTrace(); } } }.start();  
    }
    
    String filtroReverso = "";
    int    colunaReverso = 1;
    public void filtroReverso( String f, int c ) { colunaReverso = c;    
        try { filtroReverso = f.trim(); } catch( Exception e ){}
        
        new Thread() {   @Override public void run() { try { 
            Exportando Exportando = new Exportando("APLICANDO O FILTRO...");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            
            int numFila=tmPesquisa.getRowCount();
            
            Exportando.pbg.setMaximum( numFila );
            
            Thread.sleep( 1 );
                                                
            for (int i = 0; i < numFila; i++) {     Exportando.pbg.setValue( i );
                
                try{
                    
                    String str = String.valueOf( tmPesquisa.getValueAt(i, colunaReverso) ).trim();  
                    
                    if( str.equals(filtroReverso) ){
                        
                        tmPesquisa.removeRow( i );       
                        i = -1;
                        //System.out.println( str + "  -------------  " + i + "  " + coluna);
                    }
                } catch( Exception e ){ }
                //System.out.println( "++++++++++++++  " + i + "  " + coluna);
            }

            Exportando.fechar();
            
        } catch( Exception e ){ System.out.println("Exportar - "); e.printStackTrace(); } } }.start();  
    }
    
    String controltbPesquisaLinhaSelecionada = "";
    private void tbPesquisaLinhaSelecionada() { 
        try{
            
            if ( tbPesquisa.getSelectedRow() != -1){
                
                try{
                    
                    if( controltbPesquisaLinhaSelecionada.equals("mostrarListaEventoprodutos") ){
                    
                        Menu_Pesquisa.btExcluirEventoGB.setVisible(false);
                        Menu_Pesquisa.IDSELECIONADA = listEventoprodutos.get( tbPesquisa.getSelectedRow() ).getId();
                        //System.out.println( "---" + Menu_Pesquisa.IDSELECIONADA );
                    }
                    else{
                                  
                        Menu_Pesquisa.btExcluirEventoGB.setVisible(false);
                        Menu_Pesquisa.IDSELECIONADA = list.get( tbPesquisa.getSelectedRow() ).getId();
                        //System.out.println( "---" + Menu_Pesquisa.IDSELECIONADA );
                    }
                }catch( Exception e ){}
                 
                Menu_Pesquisa.btVisualizar.setEnabled(true);
                Menu_Pesquisa.btSelecionar.setEnabled(true);               
                
                if( Administrador.mapaComandos.get("ALTERAR_ESTABELECIMENTO") != null ) {
                    Menu_Pesquisa.btAlterar.setEnabled(true);
                }  
                
                if( Administrador.mapaComandos.get("EXCLUIR_ESTABELECIMENTO") != null ) {
                    Menu_Pesquisa.btExcluir    .setEnabled(true);
                }   
                
                Menu_Pesquisa.btExcluirEventoGB.setEnabled(true);   
                Menu_Pesquisa.btExcluirEventoGB.setVisible(true);  
                
                Menu_Pesquisa.lbFiltro.setVisible(true); 
                
                Menu_Pesquisa.lbExcluir.setVisible(true); 

            } else{
                      
                Menu_Pesquisa.IDSELECIONADA = -1;  
                
                Menu_Pesquisa.btAlterar   .setEnabled(false);
                Menu_Pesquisa.btExcluir   .setEnabled(false);
                Menu_Pesquisa.btVisualizar.setEnabled(false);
                Menu_Pesquisa.btSelecionar.setEnabled(false);
                
                Menu_Pesquisa.btExcluirEventoGB.setEnabled(false);   
                Menu_Pesquisa.btExcluirEventoGB.setVisible(false);
                
                Menu_Pesquisa.lbFiltro.setVisible(false); 
                
                Menu_Pesquisa.lbExcluir.setVisible(false); 
            }
        } catch( Exception e ) {}
    }
    
    private void calculoFlutuante() {                                              
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                  
            if( tbPesquisa.getModel().getRowCount() > 0 ){           
            for( int L_i=0; L_i < tbPesquisa.getRowCount(); L_i++ ){
                
                double est_loja        = 0;
                double sug_loja        = 0;
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
                                        
                    if( strn.trim().equals("EST. LOJA") ){
                        
                        String est_loja_str = "0"; try { est_loja_str = String.valueOf( tbPesquisa.getValueAt( L_i, C_i ) ); } catch( Exception e ){}
                        est_loja            = 0; try{ est_loja = Double.parseDouble(est_loja_str); } catch( Exception e ){}    
                        if( est_loja < 0 ){
                            
                            est_loja = 0;
                        }
                    }
                    else if( strn.trim().equals("SUGESTÃO") ){
                                  
                        String sug_str = "0"; try { sug_str = String.valueOf( tbPesquisa.getValueAt( L_i, C_i ) ); } catch( Exception e ){}
                        sug_loja       = 0;   try{ sug_loja = Double.parseDouble(sug_str); } catch( Exception e ){}     
                    }
                    else if( strn.trim().equals("DISPONIBILIDADE") ){
                        
                        String disp_dia_str  = "0"; try { disp_dia_str = String.valueOf( tbPesquisa.getValueAt( L_i, C_i ) ); } catch( Exception e ){}
                        double disp_dia      = 0; try{ disp_dia = Double.parseDouble(disp_dia_str); } catch( Exception e ){}
                        
                        try{ disponibilidade =  disp_dia/15; } catch( Exception e ){}   
                        if( disponibilidade <= 0 ){
                            
                            disponibilidade = 3;
                        }
                    }
                    else if( strn.trim().equals("DDE") ){
                        
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
    
    
    List<Eventoprodutos> listEventoprodutosFull = new ArrayList();
    public void tableCellRendererCompProVerde( List<Eventoprodutos> listEventoprodutosFull2 ) {    
        
        listEventoprodutosFull = listEventoprodutosFull2;     
                                               
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        tbPesquisa.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    
                String bd = ""; try { bd = String.valueOf( table.getValueAt( row, 0 ) ); } catch( Exception e ){}
                
                String b = "";
                for (int it = 0; it < listEventoprodutosFull.size(); it++){
                    
                    if( listEventoprodutosFull.get(it).getItem().equals(bd)  ){
                        
                        b = bd;
                    }
                }
                //Coloca cor em todas as linhas,COLUNA(3) que tem o valor "Aberto"
                if (bd != null && bd.equals( b )) {//Se Status for igual a "Aberto"
                    comp.setBackground(Color.green);//Preenche a linha de vermelho
                    comp.setForeground(Color.BLACK);//E a fonte de branco
                } else {
                    boolean sel = isSelected;
                    if (sel == true) {
                        comp.setBackground(getBackground());
                        comp.setForeground(getForeground());
                    } else {//Se Status não for "Aberto" 
                        comp.setBackground(Color.WHITE);//Preenche a linha de branco
                        comp.setForeground(new Color(51, 51, 51));//E a fonte de preto
                    }
                    }
                    
                    return comp;
                }
            });
    
    } catch( Exception e ){  } } }.start(); }
    
    List<Eventoprodutos> listEventoprodutosFull3 = new ArrayList();
    public void tableCellRendererCompProAzul( List<Eventoprodutos> listEventoprodutosFull2 ) {    
        
        listEventoprodutosFull3 = listEventoprodutosFull2;     
                                               
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        tbPesquisa.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    
                String bd = ""; try { bd = String.valueOf( table.getValueAt( row, 0 ) ); } catch( Exception e ){}
                
                String b = "";
                for (int it = 0; it < listEventoprodutosFull3.size(); it++){
                    
                    if( listEventoprodutosFull3.get(it).getItem().equals(bd)  ){
                        
                        b = bd;
                    }
                }
                //Coloca cor em todas as linhas,COLUNA(3) que tem o valor "Aberto"
                if (bd != null && bd.equals( b )) {//Se Status for igual a "Aberto"
                    comp.setBackground(Color.ORANGE);//Preenche a linha de vermelho
                    comp.setForeground(Color.BLACK);//E a fonte de branco
                } else {
                    boolean sel = isSelected;
                    if (sel == true) {
                        comp.setBackground(getBackground());
                        comp.setForeground(getForeground());
                    } else {//Se Status não for "Aberto" 
                        comp.setBackground(Color.WHITE);//Preenche a linha de branco
                        comp.setForeground(new Color(51, 51, 51));//E a fonte de preto
                    }
                    }
                    
                    return comp;
                }
            });
    
    } catch( Exception e ){  } } }.start(); }
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int L_i, C_i;
    public void obterListaCalcularDdePromocionalTrintaDias() {                                      

        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            //Exportando Exportandoy = new Exportando("PESQUISANDO \"PROMOCIONAIS\"");
            //Exportandoy.setVisible(true);
            //Exportandoy.pbg.setMinimum(0);
            //Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );  
            
            Query qEv = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTO", Evento.class );
            List<Evento> listEv = qEv.getResultList();
                
            GregorianCalendar gc              = new GregorianCalendar();
            Date              dataHoje        = gc.getTime();
            List<Evento>      listEvVirgentes = new ArrayList<Evento>();
            
            for (int i = 0; i < listEv.size(); i++){
                
                if( dataHoje.before( listEv.get(i).getDataFim() ) || dataHoje.equals(listEv.get(i).getDataFim() ) ){
                    
                    listEvVirgentes.add( listEv.get(i) );
                }
            }
            
            List<Eventoprodutos> listEventoprodutosFull = new ArrayList();
            
            if( tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( L_i=0; L_i < tbPesquisa.getRowCount(); L_i++ ){
                    //Exportandoy.pbg.setValue( L_i );                 
                    
                    String sap = "";
                    
                    for( C_i=0; C_i < tbPesquisa.getColumnCount(); C_i++ ){                       
                                                       
                        String strn = tbPesquisa.getColumnName(C_i);
                    
                        if( strn.trim().equals("MATERIAL") ){
                            
                            try{ 
                                
                                sap = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) ).trim(); 
                                
                                for (int i = 0; i < listEvVirgentes.size(); i++){ try{
                                    
                                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ? AND ITEM = ?", Eventoprodutos.class );
                                    q.setParameter( 1, listEvVirgentes.get(i).getId() ); 
                                    q.setParameter( 2, sap ); 
                                    List<Eventoprodutos> listEventoprodutosxx = q.getResultList();
                
                                    for (int it = 0; it < listEventoprodutosxx.size(); it++){
                                        
                                        listEventoprodutosFull.add( listEventoprodutosxx.get(it) );
                                        /*if( listEventoprodutosxx.get(it).getItem().equals(sap)  ){
                                            
                                        }*/
                                    }
                                    
                                } catch( Exception e ){} }
                                
                            } catch( Exception e ){}
                            
                            break;
                        }
                    }
                    
                }
                
                //Exportandoy.fechar();
            }
            
            calcularDdePromocionalTrintaDias(listEventoprodutosFull);
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    List<Eventoprodutos> listEventoprodutosFull4 = new ArrayList();
    public void calcularDdePromocionalTrintaDias( List<Eventoprodutos> listEventoprodutosFull2 ) {    
        
        listEventoprodutosFull4 = listEventoprodutosFull2;                                                 
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            //Exportando Exportandoy = new Exportando("ANALISANDO SUGESTÃO");
            //Exportandoy.setVisible(true);
            //Exportandoy.pbg.setMinimum(0);
            //Exportandoy.pbg.setMaximum( tbPesquisa.getRowCount() );  
            
            if( tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < tbPesquisa.getRowCount(); L_i++ ){
                    //Exportandoy.pbg.setValue( L_i );                 
                    
                    String estloja = "";
                    String disponi = "";
                    
                    int qtdES  = 0;
                    int qtdPED = 0;
                    
                    int L_iSUG = 0;
                    int C_iSUG = 0;
                    int count  = 0;
                
                    int vcount = 0;
                    
                    boolean booPro = false;
                    
                    for( int C_i=0; C_i < tbPesquisa.getColumnCount(); C_i++ ){ count++;                        
                                                       
                        vcount = count + 1;
                        
                        String str = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) ).trim();
                        
                        String strn = tbPesquisa.getColumnName(C_i);
                                                                    
                        if( strn.trim().equals("MATERIAL") ){
                            
                            /////////////////////////////////////////////////////////////////                   
                            for (int it = 0; it < listEventoprodutosFull4.size(); it++){
                    
                                if( listEventoprodutosFull4.get(it).getItem().equals(str)  ){
                        
                                    booPro = true;
                                }
                            }
                            //////////////////////////////////////////////////////////////// 
                        }
                        else if( booPro == true ){
                            
                            if( strn.trim().equals("E/S") ){ 
                                
                                String v = "0"; try{ v = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) ).trim(); } catch( Exception e ){} 
                                
                                //System.out.println( "strn.trim().equals(\"E/S\"): " +v);
                                
                                if( !v.equals("0") ){
                                    
                                    try{ qtdES = Integer.parseInt(v); } catch( Exception e ){} 
                                }
                            }
                            else if( strn.trim().equals("Q.EMB") ){
                                
                                String v = "0"; try{ v = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) ).trim(); } catch( Exception e ){} 
                                
                                if( !v.equals("0") ){
                                    
                                    try{ qtdPED = Integer.parseInt(v); } catch( Exception e ){} 
                                }
                            }
                            else if( strn.trim().equals("EST. LOJA") ){
                            
                                try{ 
                                
                                    estloja = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) ).trim();    
                                } catch( Exception e ){}
                            }
                            else if( strn.trim().equals("SUGESTÃO") ){
                            
                                L_iSUG = L_i;
                                C_iSUG = C_i;
                            }
                            else if( strn.trim().equals("DISPONIBILIDADE") ){
                                try{ 
                                
                                    disponi = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) ).trim();  
                           
                                } catch( Exception e ){}
                            }
                            else if( vcount > tbPesquisa.getColumnCount() ){
                            
                                try{ 
                            
                                    if( estloja.equals("") || estloja.equals("null") ){ estloja="0"; }
                                    if( disponi.equals("") || disponi.equals("null") ){ disponi="0"; }
                        
                                    int sugeestaoJMA = 0; 
                                    if( estloja.trim().equals("0") && !disponi.trim().equals("0") ) {
                            
                                        //TableColumn v = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumn("");
                                        double disponib1 = 0; try{ disponib1 = Double.parseDouble(disponi); } catch( Exception e ){}
                                        double disponib2 = 0; try{ disponib2 = disponib1*2; } catch( Exception e ){}
                                        
                                        try{ sugeestaoJMA = (int) Math.round(disponib2); } catch( Exception e ){} 
                                        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                        
                                        if( qtdPED > 1 ){
                                            //System.out.println( "qtdPED: " +qtdPED);
                                            
                                            if( sugeestaoJMA < qtdPED ){
                                                
                                                tbPesquisa.setValueAt( qtdPED , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJMA % qtdPED;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJMA / qtdPED; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdPED;
                                                         
                                                         tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG); }                                               
                                            }
                                        }
                                        else if( qtdES > 1 ){
                                            
                                            if( sugeestaoJMA < qtdES ){
                                                
                                                tbPesquisa.setValueAt( qtdES , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJMA % qtdES;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJMA / qtdES; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdES;
                                                         
                                                         tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG); }                                               
                                            }  
                                        }
                                        else{
                                            
                                            tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                        
                                               
                                        
                                    }
                                    else if( !estloja.trim().equals("0") && !disponi.trim().equals("0") ) {
                                    
                                        double estlojab = 0; try{ estlojab = Double.parseDouble(estloja); } catch( Exception e ){}
                                        
                                        double disponib2 = 0; try{ disponib2 = Double.parseDouble(disponi); } catch( Exception e ){}
                                        double disponib = 0; try{ disponib = disponib2*2; } catch( Exception e ){}
                                    
                                        if( estlojab >= 0 ){
                                        
                                            if( estlojab < disponib ){
                                        
                                                double diferenca = disponib - estlojab;
                                        
                                                int sugeestaoJM = 0; try{ sugeestaoJM = (int) Math.round(diferenca); } catch( Exception e ){} 
                                                //String v = String.valueOf( m );
                                        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                        
                                        if( qtdPED > 1 ){
                                            //System.out.println( "qtdPED: " +qtdPED);
                                            
                                            if( sugeestaoJM < qtdPED ){
                                                
                                                tbPesquisa.setValueAt( qtdPED , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJM % qtdPED;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJM / qtdPED; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdPED;
                                                         
                                                         tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG); }                                               
                                            }
                                        }
                                        else if( qtdES > 1 ){
                                            
                                            if( sugeestaoJM < qtdES ){
                                                
                                                tbPesquisa.setValueAt( qtdES , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJM % qtdES;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJM / qtdES; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdES;
                                                         
                                                         tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG); }                                               
                                            }  
                                        }
                                        else{
                                            
                                            tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG);
                                        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            }
                                        }
                                        else {
                                        
                                            int sugeestaoJM = 0; try{ sugeestaoJM = (int) Math.round(disponib); } catch( Exception e ){} 
                                        
                                            tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG );;
                                        }
                                    }
                                } catch( Exception e ){}
                            }
                        }
                    }
                    
                }
                
                //Exportandoy.fechar();
            }  
            
                
    } catch( Exception e ){  } } }.start(); }
    
    Set<String> ordem;
    public void excluirLinhas( Set<String> listaSap ) {    
        
        ordem = new TreeSet<String>(listaSap);  
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            Exportando Exportando = new Exportando("EXCLUIR LISTA SAP");
            
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( tbPesquisa.getModel().getRowCount() );
        
            for (Iterator<String> it = ordem.iterator(); it.hasNext();) {   
                
                String busca = it.next().trim();
                
                int c = 0;
                if( tmPesquisa.getRowCount() > 0 ){
                    c++; Exportando.pbg.setValue( c );
                                      
                    for( int L_i=0; L_i < tmPesquisa.getRowCount(); L_i++ ){
                        
                        for( int C_i=0; C_i < tmPesquisa.getColumnCount(); C_i++ ){ try{
                            
                            String strn = tmPesquisa.getColumnName(C_i);
                            
                            if( strn.trim().equals("MATERIAL") ){
                                
                                String sap = String.valueOf( tmPesquisa.getValueAt(L_i, C_i) ).trim();
                                
                                if( busca.equals(sap) ){
                                    
                                    tmPesquisa.removeRow( L_i );
                                }
                            }
                        } catch( Exception e ){  } }
                    }
                }
            }
            
            Exportando.fechar();
        } catch( Exception e ){  } } }.start(); 
                                               
    }
    
    public void excluirLinhasSemThread( Set<String> listaSap ) {    
        
        ordem = new TreeSet<String>(listaSap);  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            Exportando Exportando = new Exportando("EXCLUIR LISTA SAP");
            
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( tbPesquisa.getModel().getRowCount() );
        
            for (Iterator<String> it = ordem.iterator(); it.hasNext();) {   
                
                String busca = it.next().trim();
                
                int c = 0;
                if( tmPesquisa.getRowCount() > 0 ){
                    c++; Exportando.pbg.setValue( c );
                                      
                    for( int L_i=0; L_i < tmPesquisa.getRowCount(); L_i++ ){
                        
                        for( int C_i=0; C_i < tmPesquisa.getColumnCount(); C_i++ ){ try{
                            
                            String strn = tmPesquisa.getColumnName(C_i);
                            
                            if( strn.trim().equals("MATERIAL") ){
                                
                                String sap = String.valueOf( tmPesquisa.getValueAt(L_i, C_i) ).trim();
                                
                                if( busca.equals(sap) ){
                                    
                                    tmPesquisa.removeRow( L_i );
                                }
                            }
                        } catch( Exception e ){  } }
                    }
                }
            }
            
            Exportando.fechar();
        } catch( Exception e ){  } //} }.start(); 
                                               
    }
    
    /*private void tbPesquisaColunaSelecionada() { 
        try{
            
            if ( tbPesquisa.getSelectedColumn() != -1){
                
                Menu_Pesquisa.tfColunaSelecionada.setText( String.valueOf( tbPesquisa.getSelectedColumn() ) );

            } else{

                Menu_Pesquisa.IDSELECIONADA = -1;  
            }
        } catch( Exception e ) {}
    }*/
    
    
    public void analiseGenerica22222( String comando, String layout, 
            String sap, List<String> listSap, List<Gbproduto> listPro, List<Gbdiretoloja> listGD ){ try{
        try{
            
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "E/S", "UBM", "Q.EMB", "PONTO EXTRA", "ELENCO", "DDE*", "EST. LOJA", "SUGESTÃO", "  DDE  ", "DISPONIBILIDADE", "PED. ATIVO", "REM.", "SAIU CD",  "ENT. LOJA", "VENDA ULT. SEM", "VENDA 04 SEM", "VENDA 12 SEM", "EST. CD001", "EST. CD289", "EST. CD184", "CD ONTEM", "CLASSE", "SETOR", "EST. TIPO", "COD. FORNEC.", "NOME FORNEC.", "RA", "EST. MÍNIMO", "EAN", "GRP",  "DIAS SEM VENDAS", "O.S"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
        
        Thread.sleep( 1 );
        
        if( comando.equals("nome") ){
            
            
        }        
        
    } catch( Exception e ) {} }
    
    public void setarCabecalhoTabelaGenerica(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "E/S", "UBM", "Q.EMB", "PONTO EXTRA", "ELENCO", "DDE*", "EST. LOJA", "GÔNDULA", "SUGESTÃO", "  DDE  ", "QTD_VENDIDA", "UND","DISPONIBILIDADE", "CLASSE", "PED. ATIVO", "REM.", "SAIU CD",  "ENT. LOJA", "VENDA ULT. SEM", "VENDA 04 SEM", "VENDA 12 SEM", "PREÇO CUSTO" ,"CD B001", "CD B289", "CD B184", "CD ONTEM", "COD. FORNEC.", "NOME FORNEC.", "RA", "EST. MÍNIMO", "EAN", "GRP",  "DIAS SEM VENDA", "O.S", "DESCRIÇÃO GRUPO COMPRA", "SETOR", "ALTURA", "FRENTE", "FUNDO", "CLT" ,"OP. IDENTIFICADA"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
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
            String lojaZris="";
            String precoCusto="";
            String cluster="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbzris.class, 
                            "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();      
                    lojaZris = XXGbzrisListaSap.get(0).getEstabelecimento();
                    precoCusto = XXGbzrisListaSap.get(0).getPrecocusto();
                    cluster = XXGbzrisListaSap.get(0).getCluster();
                }
                
            }catch( Exception e ){}
            /*ZRIS*/
            
            /*qtd_Vendida*/
            double qtd_Vendida=0;
            String qtd_VendidaUnd = "";
            try{
                
                List<Gbquantidadevendida> XXGbquantidadevendida = null;
                
                    try{ 
                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbquantidadevendida.class, JPAUtil.em());
                                        
                        XXGbquantidadevendida = (List<Gbquantidadevendida>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbquantidadevendida.class, 
                                       "SELECT * FROM SCHEMAJMARY.GBQUANTIDADEVENDIDA WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBQUANTIDADEVENDIDA.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), lojaZris );
                    }catch( Exception e ){ }
                
                    String rbusca = ""; try{ rbusca = XXGbquantidadevendida.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){
                    try{ 
                        qtd_Vendida = XXGbquantidadevendida.get(0).getQuantidade2();
                        qtd_VendidaUnd = "UND";
                        
                        if(!qtdEmPed.equals("")){
                            int qtde = Integer.parseInt(qtdEmPed);
                            
                            if( qtde > 1 ){
                                
                                if( qtd_Vendida > qtde ){
                            
                                    qtd_Vendida = qtd_Vendida/qtde;
                                    qtd_VendidaUnd = "CX";
                                }
                            }
                        }
                    }catch( Exception e ){}
                }
                /////////////////////////////////
                
            }catch( Exception e ){}
            /*qtd_Vendida*/
            
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
            
            /*String mrp="";
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
                
            }catch( Exception e ){}*/
            /*MRP*/
            String mrp="";
            try {
                
                List<Gbmrp> XXGbmrpListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                        
                    XXGbmrpListaSap = (List<Gbmrp>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbmrp.class, 
                            "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBMRP.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), lojaZris );
                }catch( Exception e ){ }            
                
                String rbusca = ""; try{ rbusca = XXGbmrpListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    mrp = XXGbmrpListaSap.get(0).getMrp();                        
                }
                /////////////////////////////////
                
            }catch( Exception e ){}
            /*MRP*/
            
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
            
            /*String setor="";
            try {
                setor = XXGbsuplyListaSap.get(0).getNomSetor();
                if( setor.equals("") || setor.equals("null") ){ setor=""; }
            }catch( Exception e ){}*/
            
            /*
            String status="";
            try {
                status = XXGbsuplyListaSap.get(0).getStsEstoquetipo();
                if( status.equals("") || status.equals("null") ){ status=""; }
            }catch( Exception e ){}
            */
            
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
            
            String altura=""; String frente=""; String fundo=""; String gondula="";
            try {
                
                List<Enderecoespacogondula> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Enderecoespacogondula.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Enderecoespacogondula>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Enderecoespacogondula.class, 
                            "SELECT * FROM SCHEMAJMARY.ENDERECOESPACOGONDULA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    altura = XXGbraListaSap.get(0).getAltura();     
                    frente = XXGbraListaSap.get(0).getFrente();  
                    fundo = XXGbraListaSap.get(0).getFundo();  
                    
                    try{
                        int al=0; int fr=0; int fu=0; int total = 0;
                        
                        al = Integer.valueOf(altura);
                        fr = Integer.valueOf(frente);
                        fu = Integer.valueOf(fundo);
                        
                        if( al != 0 && fr != 0 && fu != 0 ){   
                            
                            total = al*fr*fu;
                            try{ gondula = String.valueOf(total); }catch( Exception e ){}
                        }
                    }catch( Exception e ){ }
                }
            }catch( Exception e ){}
            
            String opIdentif="";
            try {
                
                List<Opidentificada> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Opidentificada.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Opidentificada>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Opidentificada.class, 
                            "SELECT * FROM SCHEMAJMARY.OPIDENTIFICADA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opIdentif = XXGbraListaSap.get(0).getOpidentificada();                        
                }
            }catch( Exception e ){}
            
            tmPesquisa.addRow(new Object[]{ sapTab, nomeProd, mrp, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, dde, b141, gondula, sugestao, dde, qtd_Vendida, qtd_VendidaUnd,disponibilidade, classe, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas, precoCusto ,b001, b289, b184, cdOntem, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana, descGrupCom, descricaosetor, altura, frente, fundo, cluster ,opIdentif });
        //}
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(35);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(35);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(65);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(15).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(15).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(15).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(16).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(16).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(16).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(17).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(17).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(17).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(18).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(18).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(18).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(19).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(19).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(19).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(20).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(20).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(20).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(21).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(21).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(21).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(22).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(22).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(23).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(23).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(24).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(24).setResizable(true);
       
        tbPesquisa.getColumnModel().getColumn(25).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(25).setResizable(true);
       
        tbPesquisa.getColumnModel().getColumn(26).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(26).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(27).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(27).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(27).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(28).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(28).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(28).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(29).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(29).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(29).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(30).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(30).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(30).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(31).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(31).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(31).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(32).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(32).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(32).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(33).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(33).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(33).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(34).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(34).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(34).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(35).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(35).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(35).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(36).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(36).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(36).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(37).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(37).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(38).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(38).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(39).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(39).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(39).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(40).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(40).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(40).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(41).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(41).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(41).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(42).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(42).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(43).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(43).setResizable(true);
            
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void pedidoSetarCabecalhoTabelaGenerica(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "SETOR", "DESC. GRP COMPRA", "MATERIAL", "DESCRICAO DO MATERIAL", "MODALIDADE", "REGIONAL", "CLASSE", "PONTO EXTRA", "ELENCO", "EST. LOJA", "DISP", "ME80FN PEDIDO GERADO ", "ME80FN QUANTIDADE ENTREGUE", "DOCUMENTO COMPRA", "CÓDIGO FORNECEDOR", "NOME FONECEDOR"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void pedidoAnaliseGenericaTabela( String loja, String sap, Gbproduto Gbproduto2, Gbpedidosativos pedidoAtivoRec ){ try {                                     
   
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
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
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
            
            int b141 = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                   
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b141 = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}

            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
            try {
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                   
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbpedidosativosListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    ped7D = XXGbpedidosativosListaSap.get(0).getPedidoCd184Ativo();                        
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
                        
            String entrouLJ="";
            try {
                entrouLJ = XXGbpedidosativosListaSap.get(0).getPedidoCd184AtivoEntradaLoja();
                if( entrouLJ.equals("null") ){ entrouLJ=""; }
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
            
            /*
            String unidadeBasicaMedida="";
            try {
                unidadeBasicaMedida = pedidoAtivoRec.;
                if( unidadeBasicaMedida.equals("null") ){ unidadeBasicaMedida=""; }
            }catch( Exception e ){}*/
            
            String documentoCompra="";
            try {
                documentoCompra = pedidoAtivoRec.getNumepedido();
            }catch( Exception e ){}
            
            String status="";
            try {
                status = XXGbsuplyListaSap.get(0).getStsEstoquetipo();
                if( status.equals("") || status.equals("null") ){ status=""; }
            }catch( Exception e ){}
            
            String codForn="";
            try {
                codForn = pedidoAtivoRec.getCodfornec();
                if( codForn.equals("") || codForn.equals("null") ){ codForn=""; }
            }catch( Exception e ){}
            
            String nomForn="";
            List<Fornecedor> XXGbForndListaSap = null;
            try {
                try{ 
                    String x = pedidoAtivoRec.getCodfornec().toUpperCase().trim();
                    
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Fornecedor.class, JPAUtil.em());
                                        
                    XXGbForndListaSap = (List<Fornecedor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Fornecedor.class, 
                            "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEOURAZAOSOCIAL = ?", x );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbForndListaSap.get(0).getNomefantasia(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    nomForn = XXGbForndListaSap.get(0).getNomefantasia();                        
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
            
            String disponibilidade="";
            try {
                disponibilidade = String.valueOf(XXGbzrisListaSap.get(0).getDisponibilidade());
                if( disponibilidade.equals("null") ){ disponibilidade=""; }
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
            
            tmPesquisa.addRow(new Object[]{ loja, descricaosetor, descGrupCom, sapTab, nomeProd, "DIRETO LOJA", "DECER", classe, pontExtra, elenco, b141, disponibilidade, ped7D, entrouLJ, documentoCompra, codForn, nomForn});
        //}
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(2).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(15).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(15).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(15).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(16).setPreferredWidth(180);
        tbPesquisa.getColumnModel().getColumn(16).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(16).setCellRenderer( rendererDireita );
                
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void setarCabecalhoPesquisarEmTodasLojas(){        
        try{
//"MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "E/S", "UBM", "Q.EMB", "PONTO EXTRA", "ELENCO", "DDE*", "EST. LOJA", "GÔNDULA", "SUGESTÃO", "  DDE  ", "DISPONIBILIDADE", "CLASSE", "PED. ATIVO", "REM.", "SAIU CD",  "ENT. LOJA", "VENDA ULT. SEM", "VENDA 04 SEM", "VENDA 12 SEM", "PREÇO CUSTO" ,"CD B001", "CD B289", "CD B184", "CD ONTEM", "COD. FORNEC.", "NOME FORNEC.", "RA", "EST. MÍNIMO", "EAN", "GRP",  "DIAS SEM VENDA", "O.S", "DESCRIÇÃO GRUPO COMPRA", "SETOR", "ALTURA", "FRENTE", "FUNDO", "CLT" ,"OP. IDENTIFICADA"a             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "E/S", "UBM", "Q.EMB", "PONTO EXTRA", "ELENCO", "DDE*", "EST. LOJA", "GÔNDULA", "SUGESTÃO", "  DDE  ", "DISPONIBILIDADE", "CLASSE", "PED. ATIVO", "REM.", "SAIU CD",  "ENT. LOJA", "VENDA ULT. SEM", "VENDA 04 SEM", "VENDA 12 SEM", "PREÇO CUSTO", "EST. CD001", "EST. CD289", "EST. CD184", "CD ONTEM", "COD. FORNEC.", "NOME FORNEC.", "RA", "EST. MÍNIMO", "EAN", "GRP",  "DIAS SEM VENDAS", "O.S", "DESCRIÇÃO GRUPO COMPRA", "SETOR", "ALTURA", "FRENTE", "FUNDO", "CLT" ,"OP. IDENTIFICADA"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void pesquisarEmTodasLojas( String loja, String sap, Gbproduto Gbproduto2 ){ try {                                     
   
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
            String precoCusto="";
            String cluster="";
            try {                
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbzris.class, JPAUtil.em());
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbzrisListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    qtdEmPed = XXGbzrisListaSap.get(0).getQtdxemb();    
                    precoCusto = XXGbzrisListaSap.get(0).getPrecocusto();
                    cluster = XXGbzrisListaSap.get(0).getCluster();                    
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
            
            ////////////////////////////////////////////////////////////
            /*String mrp="";
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
            */
            ////////////////////////////////////////////////////////////
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
                                   
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );

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
                                   
                    XXGbpedidosativosListaSap = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
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
            
            /*String setor="";
            try {
                setor = XXGbsuplyListaSap.get(0).getNomSetor();
                if( setor.equals("") || setor.equals("null") ){ setor=""; }
            }catch( Exception e ){}*/
            
            /*
            String status="";
            try {
                status = XXGbsuplyListaSap.get(0).getStsEstoquetipo();
                if( status.equals("") || status.equals("null") ){ status=""; }
            }catch( Exception e ){}*/
            
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
            
            String altura=""; String frente=""; String fundo=""; String gondula="";
            try {
                
                List<Enderecoespacogondula> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Enderecoespacogondula.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Enderecoespacogondula>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Enderecoespacogondula.class, 
                            "SELECT * FROM SCHEMAJMARY.ENDERECOESPACOGONDULA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    altura = XXGbraListaSap.get(0).getAltura();     
                    frente = XXGbraListaSap.get(0).getFrente();  
                    fundo = XXGbraListaSap.get(0).getFundo();  
                    
                    try{
                        int al=0; int fr=0; int fu=0; int total = 0;
                        
                        al = Integer.valueOf(altura);
                        fr = Integer.valueOf(frente);
                        fu = Integer.valueOf(fundo);
                        
                        if( al != 0 && fr != 0 && fu != 0 ){   
                            
                            total = al*fr*fu;
                            try{ gondula = String.valueOf(total); }catch( Exception e ){}
                        }
                    }catch( Exception e ){ }
                }
            }catch( Exception e ){}
            
            String opIdentif="";
            try {
                
                List<Opidentificada> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Opidentificada.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Opidentificada>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Opidentificada.class, 
                            "SELECT * FROM SCHEMAJMARY.OPIDENTIFICADA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opIdentif = XXGbraListaSap.get(0).getOpidentificada();                        
                }
            }catch( Exception e ){}
            
            tmPesquisa.addRow(new Object[]{ loja, sapTab, nomeProd, mrp, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, dde, b141, gondula, sugestao, dde, disponibilidade, classe, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas, precoCusto, b001, b289, b184, cdOntem, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana, descGrupCom, descricaosetor, altura, frente, fundo, cluster, opIdentif });
        //}
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(2).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(35);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(35);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(65);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(15).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(15).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(15).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(16).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(16).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(16).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(17).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(17).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(17).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(18).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(18).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(18).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(19).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(19).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(19).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(20).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(20).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(20).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(21).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(21).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(22).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(22).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(23).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(23).setResizable(true);
       
        tbPesquisa.getColumnModel().getColumn(24).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(24).setResizable(true);
       
        tbPesquisa.getColumnModel().getColumn(25).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(25).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(26).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(26).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(26).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(27).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(27).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(27).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(28).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(28).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(28).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(29).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(29).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(29).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(30).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(30).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(30).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(31).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(31).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(31).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(32).setPreferredWidth(110);
        tbPesquisa.getColumnModel().getColumn(32).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(32).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(33).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(33).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(33).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(34).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(34).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(34).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(35).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(35).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(35).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(36).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(36).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(37).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(37).setResizable(true);
       
        tbPesquisa.getColumnModel().getColumn(38).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(38).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(38).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(39).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(39).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(39).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(40).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(40).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(40).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(41).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(41).setResizable(true);
            
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void inventarioSetarCabecalhoTabelaGenerica(){        
        try{
          
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "SETOR", "EAN", "MATERIAL", "DESCRICAO DO MATERIAL", "ESTOQUE ATUAL", "QTD CONTADA", "DIFERENÇA QTD", "VALOR ESTOQUE ATUAL", "VALOR QTD CONTADA", "DIFERENÇA VALOR", "STATUS"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void inventarioAnaliseGenericaTabela( String sap, Gbproduto Gbproduto2, String estAtual, String qtdCnt, String difer1, String custoAtua, String custCont, String difer2, String status ){ try {                                     
   
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
                        
            tmPesquisa.addRow(new Object[]{ descricaosetor, ean, sapTab, nomeProd, estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status });
        //}
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(4).setCellRenderer( rendererDireita );
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(5).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(6).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(7).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
                    
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
            
    public void setarCabecalhoBazarGenericaTabela(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "SETOR", "DESC. GRP COMPRA", "MATERIAL", "DESCRICAO DO MATERIAL", "MODALIDADE", "MRP", "REGIONAL", "CLASSE", "PONTO EXTRA", "ELENCO", "O.S", "EST. CD B001 (UND)", "EST. CD B184 (UND)", "EST. CD B289 (UND)", "EST.LOJA (UND)", "DISP", "E/S"            
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(15).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(15).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(15).setCellRenderer( rendererDireita );
            
        } catch( Exception e ){} 
    }
    
        String loja      = "";
        String setor     = "";
        String descGrupo = "";
        String sap       = "";
        String desc      = "";
        String modali    = "";
        String mrpT      = "";
        String regio     = "";
        String classe    = "";
        String ponto     = "";
        String elenco    = "";
        String os        = "";
        String estcd001  = ""; 
        String estcd184  = ""; 
        String estcd289  = ""; 
        String estlj     = "";
        String disp      = "";
        String es     = "";
    public void bazarGenericaTabela( int i, String str ){ try { 
                
        try {            
            //String str = separado[i];
            
            switch(i){
                
                case 0:  loja      = str; break;
                case 1:  setor     = str; break;
                case 2:  descGrupo = str; break;  
                case 3:  sap       = str; break;
                case 4:  desc      = str; break; 
                case 5:  modali    = str; break;
                case 6:  mrpT      = str; break;
                case 7:  regio     = str; break;
                case 8:  classe    = str; break;  
                case 9:  ponto     = str; break;
                case 10: elenco    = str; break; 
                case 11: os        = str; break; 
                case 12: estcd001  = str; break; 
                case 13: estcd184  = str; break; 
                case 14: estcd289  = str; break; 
                case 15: estlj     = str; break; 
                case 16: disp      = str; break;  
                case 17: es        = str; break;      
            }
        } catch( Exception e ){ }
        
        if(i==17){
            tmPesquisa.addRow(new Object[]{ loja, setor, descGrupo, sap, desc, modali, mrpT, regio, classe, ponto, elenco, os, estcd001, estcd184, estcd289, estlj, disp, es });
        }
    } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } }
    
    public void setarCabecalhoPedidoAdicional(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "SETOR", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "CLASSE", "PONTO EXTRA", "ELENCO", "EST. CD B001 (UND)", "EST. CD B184 (UND)", "EST. CD B289 (UND)", "EST.LOJA (UND)", "DISP", "E/S", "PEDIDO ADICIONAL (CX)", "RA", "PEDIDO PENDENTE", "UMB", "REMESSA", "SAÍDA", "FORNECIDA"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
           
        } catch( Exception e ){} 
    }
    
    
    public void pedidoAdicional( String[] dados ){ try{

        tmPesquisa.addRow( dados );
    } catch( Exception e ){  } }
    
    public void setarCabecalho_RA_Devolutiva(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "SETOR", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "CLASSE", "PONTO EXTRA", "ELENCO", "EST. CD B001 (UND)", "EST. CD B184 (UND)", "EST. CD B289 (UND)", "EST.LOJA (UND)", "DISP", "E/S", "PLANILHAS RA INTRANET", "PLANILHAS RA ALTERADO", "ANÁLISE DEVOLUTIVA ME80FN", "RA ME80FN", "PEDIDO PENDENTE", "UMB", "REMESSA", "SAÍDA", "FORNECIDA"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
           
        } catch( Exception e ){} 
    }
    
    
    public void ra_devolutiva (String[] dados ){ try{

        tmPesquisa.addRow( dados );
    } catch( Exception e ){  } }
    
    public void setarCabecalho_Lamina_Vt(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "SETOR", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "CLASSE", "PONTO EXTRA", "ELENCO", "EST.LOJA (CX)", "DISPONIBILIDADE       15 DIAS    (CX)", "E/S", "QTD EMBALAGEM APOSTA", "VENDA MÊS ANTERIOR ( CX )", "SUGESTÃO  APOSTA          ( CX )", "VENDA ANO ANTERIOR   ( CX )", "RA ME80FN", "PEDIDO PENDENTE", "UMB", "REMESSA", "SAÍDA", "FORNECIDA", "EST. CD B001 (UND)", "EST. CD B184 (UND)", "EST. CD B289 (UND)"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
           
        } catch( Exception e ){} 
    }
    
    public void setarCabecalhoEventosDevolutiva(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "SETOR", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "CLASSE", "PONTO EXTRA", "ELENCO", "EST. CD B001 (UND)", "EST. CD B184 (UND)", "EST. CD B289 (UND)", "EST.LOJA (UND)", "DISP", "E/S", "APOSTA LOJA", "DEVOLUTIVA PEDIDO GERADO", "QUANTIDADE VENDIDA DURANTE O EVENTO", "RA", "PEDIDO PENDENTE", "UMB", "REMESSA", "SAÍDA", "FORNECIDA"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(55);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(8).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
           
        } catch( Exception e ){} 
    }    
    
    public void setarCabecalhoGenericaTabelaDuplicado(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "EAN", "DESCRICAO DO MATERIAL", "E/S", "UBM"            
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void genericaTabelaDuplicado( String sap, Gbproduto Gbproduto2 ){ try {                                     
   
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
                        
            tmPesquisa.addRow(new Object[]{ sapTab, ean, nomeProd, qtdEmEs, qtdEmEs, Und });
        //}
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(3).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(4).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(5).setCellRenderer( rendererCentro );   
        
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void setarCabecalhoAnaliseRaPedidosZrep(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "SETOR", "DESC. GRUPO COMPRA", "DOC. COMPRA", "CD", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "EST. LOJA", "B184", "B289", "ME80FN PEDIDO PENDENTE", "CLASSE", "PONTO EXTRA", "ELENCO", "DISP", "E/S", "ME80FN QUANTIDADE GERADA", "UNIDADE DE MEDIDA BÁSICA", "SUGESTÃO DE ALTERAÇÃO DA LOJA", "ME80FN QUANTIDADE GERADA (UND)", "SUGESTÃO", "STATUS DISPARO", "OBSERVAÇÃO", "DDE PROJETADO EST. LOJA + DISPARO", "DDE PROJETADO EST. LOJA", "QUANTIDADE GÔNDULA/SEÇÃO", "ESTOQUE MÍNIMO ATUAL", "DDE MÍNIMO ATUAL" ,"SUGESTÃO ESTOQUE MÍNIMO 15 DIAS", "SUGESTÃO ESTOQUE MÍNIMO 10 DIAS", "SUGESTÃO ESTOQUE MÍNIMO 07 DIAS"              
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void analiseRaPedidosZrep( String loja, String sap, Gbproduto Gbproduto2 ){ try {                                     
   
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
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
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
                         
            int b141 = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                   
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );

                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    b141 = XXGbestoquelojab141ListaSap.get(0).getEstoqueLoja();                        
                }
     
            }catch( Exception e ){}
            
            /*7 DIAS ATIVOS*/
            List<Gbpedidosativos> XXGbpedidosativosListaSap = null;
            String ped7D="";
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
                }
                
            }catch( Exception e ){}
            /*7 DIAS ATIVOS*/
            
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
                                    
            List<Gbra> XXGbraListaSapRA = null;
            String ra="";
            try {
                                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbra.class, JPAUtil.em());
                                        
                    XXGbraListaSapRA = (List<Gbra>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbra.class, 
                            "SELECT * FROM SCHEMAJMARY.GBRA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSapRA.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    
                    String t1 = ""; 
                    try{ 
                
                        t1 = XXGbraListaSapRA.get(0).getRaDoDia();
                        if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                        double b  = Double.parseDouble( t1 );
                
                        DecimalFormat decimal = new DecimalFormat( "0.#" );
                                
                        t1 = String.valueOf( decimal.format(b) );
                        ra = t1;
                    } catch( Exception e ){}
                }
                
            }catch( Exception e ){}
            
            String raDocCompra="";
            try {
                raDocCompra = XXGbraListaSapRA.get(0).getNumepedido();
                if( raDocCompra.equals("null") ){ raDocCompra=""; }
            }catch( Exception e ){}
            
            String raCd="";
            try {
                raCd = XXGbraListaSapRA.get(0).getCodfornec();
                if( raCd.equals("null") ){ raCd=""; }
            }catch( Exception e ){}
            
            String raUndMedida="";
            try {
                raUndMedida = XXGbraListaSapRA.get(0).getEmbalagem();
                if( raUndMedida.equals("null") ){ raUndMedida=""; }
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
            
            String disponibilidade="";
            try {
                disponibilidade = String.valueOf(XXGbzrisListaSap.get(0).getDisponibilidade());
                if( disponibilidade.equals("null") ){ disponibilidade=""; }
            }catch( Exception e ){}
            
            String estMinimo="";
            try {
                estMinimo = String.valueOf(XXGbzrisListaSap.get(0).getEstoqueMinimo());
                if( estMinimo.equals("null") ){ disponibilidade=""; }
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
            
            String me80fnQtdUnd="";
            try {
                
                //System.out.println("qtdEmPed: " + qtdEmPed + " - " + "ra: " + ra );
                
                double qtd = 0; double esX = 0; double total = 0; int totalX = 0; String strQtd = ""; String strEs = "";
                
                try{ strQtd = qtdEmPed.trim().replace(",", "."); }catch( Exception e ){}
                try{ strEs  = ra.trim().replace(",", ".");       }catch( Exception e ){}

                try{ qtd = Double.parseDouble(strQtd); }catch( Exception e ){}
                try{ esX = Double.parseDouble(strEs);  }catch( Exception e ){}
                
                try{ total = qtd*esX; }catch( Exception e ){}
                
                try{ totalX = (int) total; }catch( Exception e ){}
                        
                if( totalX != 0 ){   
                            
                    try{ me80fnQtdUnd = String.valueOf(totalX); }catch( Exception e ){}
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String sugestaoX="";
            try {
                
                //System.out.println("qtdEmPed: " + qtdEmPed + " - " + "ra: " + ra );
                
                double dispD = 0; double totalD = 0; int totalX = 0; 

                try{ dispD = Double.parseDouble(disponibilidade); }catch( Exception e ){}
                
                try{ totalX = (int) dispD; }catch( Exception e ){}
                        
                if( b141 < totalX ){   
                            
                    try{ sugestaoX = String.valueOf(totalX-b141); }catch( Exception e ){}
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}     
            
            String statusDisparo="";
            try {

                int sug = 0; 

                try{ sug = Integer.parseInt(sugestaoX); }catch( Exception e ){}
                        
                if( sug != 0 ){   
                            
                    statusDisparo = "DISPARAR";
                }
                else{
                
                    statusDisparo = "NÃO DISPARAR";
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}  
            
            String obsX="";
            try {

                int sug = 0; int me80fn = 0;

                try{ sug = Integer.parseInt(sugestaoX); }catch( Exception e ){}
                try{ me80fn = Integer.parseInt(me80fnQtdUnd);    }catch( Exception e ){}
                        
                if( me80fn > (sug+b141) ){   
                            
                    obsX = "EXCESSO GERADO";
                }
                else{
                
                    obsX = "OK";
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String ddeP="";
            try {

                double dispUmDia = 0; double lojaEDisparo = 0; int ddePrjInt = 0; int me80fn = 0; double ddePrj = 0;

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ me80fn = Integer.parseInt(me80fnQtdUnd);    }catch( Exception e ){}
                
                try{ lojaEDisparo = b141+me80fn; }catch( Exception e ){}   
                               
                try{ ddePrj = lojaEDisparo/dispUmDia; }catch( Exception e ){}

                try{ ddePrjInt =(int) ddePrj; }catch( Exception e ){}
                
                        
                if( ddePrjInt != 0 ){   
                            
                    ddeP = String.valueOf(ddePrjInt);
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String ddeEstAtual="";
            try {

                double dispUmDia = 0; double lojaEDisparo = 0; int ddePrjInt = 0;  double ddePrj = 0;

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ lojaEDisparo = b141; }catch( Exception e ){}   
                               
                try{ ddePrj = lojaEDisparo/dispUmDia; }catch( Exception e ){}

                try{ ddePrjInt =(int) ddePrj; }catch( Exception e ){}
                
                        
                if( ddePrjInt != 0 ){   
                            
                    ddeEstAtual = String.valueOf(ddePrjInt);
                }
                else{
                    ddeEstAtual = "9999";
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String altura=""; String frente=""; String fundo=""; String gondula="";
            try {
                
                List<Enderecoespacogondula> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Enderecoespacogondula.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Enderecoespacogondula>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Enderecoespacogondula.class, 
                            "SELECT * FROM SCHEMAJMARY.ENDERECOESPACOGONDULA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    altura = XXGbraListaSap.get(0).getAltura();     
                    frente = XXGbraListaSap.get(0).getFrente();  
                    fundo = XXGbraListaSap.get(0).getFundo();  
                    
                    try{
                        int al=0; int fr=0; int fu=0; int total = 0;
                        
                        al = Integer.valueOf(altura);
                        fr = Integer.valueOf(frente);
                        fu = Integer.valueOf(fundo);
                        
                        if( al != 0 && fr != 0 && fu != 0 ){   
                            
                            total = al*fr*fu;
                            try{ gondula = String.valueOf(total); }catch( Exception e ){}
                        }
                    }catch( Exception e ){ }
                }
            }catch( Exception e ){}
            
            int EstoqueMinimo = 0;
            try {
                EstoqueMinimo = XXGbzrisListaSap.get(0).getEstoqueMinimo();
            }catch( Exception e ){}
            
            String ddeMinimo="";
            try {

                double dispUmDia = 0; double ddeMinimoX = 0; int ddePrjInt = 0;  

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ ddeMinimoX = EstoqueMinimo/dispUmDia; }catch( Exception e ){}   

                try{ ddePrjInt =(int) ddeMinimoX; }catch( Exception e ){}
                
                        
                if( ddePrjInt != 0 ){   
                            
                    ddeMinimo = String.valueOf(ddePrjInt);
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String ddeSugest10Dias="";
            try {

                double dispUmDia = 0; double dde10 = 0; int ddePrjInt = 0;  

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ dde10 = dispUmDia*10; }catch( Exception e ){}   

                try{ ddePrjInt =(int) dde10; }catch( Exception e ){}
                
                        
                if( ddePrjInt != 0 ){   
                            
                    ddeSugest10Dias = String.valueOf(ddePrjInt);
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String ddeSugest07Dias="";
            try {

                double dispUmDia = 0; double dde7 = 0; int ddePrjInt = 0;  

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ dde7 = dispUmDia*7; }catch( Exception e ){}   

                try{ ddePrjInt =(int) dde7; }catch( Exception e ){}
                
                        
                if( ddePrjInt != 0 ){   
                            
                    ddeSugest07Dias = String.valueOf(ddePrjInt);
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
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
            
            //""SUGESTÃO ESTOQUE MÍNIMO 10 DIAS", "SUGESTÃO ESTOQUE MÍNIMO 07 DIAS"
            
            //"LOJA", "SETOR", "DESC. GRUPO COMPRA", "DOC. COMPRA", "CD", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "EST. LOJA", "ME80FN PEDIDO PENDENTE", "CLASSE", "PONTO EXTRA", "ELENCO", "DISP", "E/S", "ME80FN QUANTIDADE GERADA", "UNIDADE DE MEDIDA BÁSICA", "SUGESTÃO DE ALTERAÇÃO DA LOJA"           
            tmPesquisa.addRow(new Object[]{ loja, descricaosetor, descGrupCom, raDocCompra, raCd, sapTab, nomeProd, mrp, b141, b184, b289, ped7D, classe, pontExtra, elenco, disponibilidade, qtdEmPed,  ra, raUndMedida, "", me80fnQtdUnd, sugestaoX, statusDisparo, obsX, ddeP, ddeEstAtual, gondula, EstoqueMinimo, ddeMinimo, disponibilidade, ddeSugest10Dias, ddeSugest07Dias  });
        //}
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(2).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(9).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(15).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(15).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(15).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(16).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(16).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(16).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(17).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(17).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(17).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(18).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(18).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(18).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(19).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(19).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(19).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(20).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(20).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(20).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(21).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(21).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(21).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(22).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(22).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(22).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(23).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(23).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(23).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(24).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(24).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(24).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(25).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(25).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(25).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(26).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(26).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(26).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(27).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(27).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(27).setCellRenderer( rendererDireita );        
                            
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void setarCabecalhoAnaliseAjusteEstoqueMinimo(){        
        try{
            booMinimo = false;
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "SETOR", "DESC. GRUPO COMPRA", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "DDE ATUAL", "EST. LOJA", "B184", "B289", "CLASSE", "UMB", "E/S", "NOVO MÍNIMO", "DISP",  "QUANTIDADE GÔNDULA / SEÇÃO", "DDE NOVO MÍNIMO", "MÍNIMO ATUAL", "DDE MÍNIMO ATUAL", "SUGESTÃO ESTOQUE MÍNIMO 15 DIAS", "SUGESTÃO ESTOQUE MÍNIMO 10 DIAS", "SUGESTÃO ESTOQUE MÍNIMO 07 DIAS", "COMPLEMENTO SUGERIDO", "SUGESTÃO ESTOQUE MÍNIMO BASE ANALISTA","DDE BASE ANALISTA","PREENCHIMENTO MÁXIMO EM GÔNDULA","DATA ANÁLISE"             
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public boolean booMinimo = false;
    public void analiseAjusteEstoqueMinimo( String loja, String sap, Gbproduto Gbproduto2, int countLinha ){ try {                                     
   
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
                                        
                    XXGbzrisListaSap = (List<Gbzris>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbzris.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBZRIS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
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
                         
            int b141 = 0;
            try {
                
                List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbestoquelojab141.class, JPAUtil.em());
                                   
                    XXGbestoquelojab141ListaSap = (List<Gbestoquelojab141>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbestoquelojab141.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );

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
                            "SELECT * FROM SCHEMAJMARY.GBSUPLY WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbsuplyListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    classe = XXGbsuplyListaSap.get(0).getNomClasse2();                        
                }
                
            }catch( Exception e ){}
            /*SUPLAIN*/
                        
            String disponibilidade="";
            try {
                disponibilidade = String.valueOf(XXGbzrisListaSap.get(0).getDisponibilidade());
                if( disponibilidade.equals("null") ){ disponibilidade=""; }
            }catch( Exception e ){}
            
            String estMinimo="";
            try {
                estMinimo = String.valueOf(XXGbzrisListaSap.get(0).getEstoqueMinimo());
                if( estMinimo.equals("null") ){ estMinimo=""; }
            }catch( Exception e ){}
            
            String ddeAtual="";
            try {
                ddeAtual = String.valueOf(XXGbzrisListaSap.get(0).getDde());
                if( ddeAtual.equals("null") ){ ddeAtual=""; }
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
            
            String sugestaoX="";
            try {
                
                //System.out.println("qtdEmPed: " + qtdEmPed + " - " + "ra: " + ra );
                
                double dispD = 0; double totalD = 0; int totalX = 0; 

                try{ dispD = Double.parseDouble(disponibilidade); }catch( Exception e ){}
                
                try{ totalX = (int) dispD; }catch( Exception e ){}
                        
                if( b141 < totalX ){   
                            
                    try{ sugestaoX = String.valueOf(totalX-b141); }catch( Exception e ){}
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}     
            
            String altura=""; String frente=""; String fundo=""; String gondula="";
            try {
                
                List<Enderecoespacogondula> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Enderecoespacogondula.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Enderecoespacogondula>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Enderecoespacogondula.class, 
                            "SELECT * FROM SCHEMAJMARY.ENDERECOESPACOGONDULA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    altura = XXGbraListaSap.get(0).getAltura();     
                    frente = XXGbraListaSap.get(0).getFrente();  
                    fundo = XXGbraListaSap.get(0).getFundo();  
                    
                    try{
                        int al=0; int fr=0; int fu=0; int total = 0;
                        
                        al = Integer.valueOf(altura);
                        fr = Integer.valueOf(frente);
                        fu = Integer.valueOf(fundo);
                        
                        if( al != 0 && fr != 0 && fu != 0 ){   
                            
                            total = al*fr*fu;
                            try{ gondula = String.valueOf(total); }catch( Exception e ){}
                        }
                    }catch( Exception e ){ }
                }
            }catch( Exception e ){}
            
            int EstoqueMinimo = 0;
            try {
                EstoqueMinimo = XXGbzrisListaSap.get(0).getEstoqueMinimo();
            }catch( Exception e ){}
            
            String ddeMinimo="";
            try {

                double dispUmDia = 0; double ddeMinimoX = 0; int ddePrjInt = 0;  

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ ddeMinimoX = EstoqueMinimo/dispUmDia; }catch( Exception e ){}   

                try{ ddePrjInt =(int) ddeMinimoX; }catch( Exception e ){}
                
                        
                if( ddePrjInt != 0 ){   
                            
                    ddeMinimo = String.valueOf(ddePrjInt);
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String ddeSugest10Dias="";
            try {

                double dispUmDia = 0; double dde10 = 0; int ddePrjInt = 0;  

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ dde10 = dispUmDia*10; }catch( Exception e ){}   

                try{ ddePrjInt =(int) dde10; }catch( Exception e ){}
                
                        
                if( ddePrjInt != 0 ){   
                            
                    ddeSugest10Dias = String.valueOf(ddePrjInt);
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String ddeSugest07Dias="";
            try {

                double dispUmDia = 0; double dde7 = 0; int ddePrjInt = 0;  

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ dde7 = dispUmDia*7; }catch( Exception e ){}   

                try{ ddePrjInt =(int) dde7; }catch( Exception e ){}
                
                        
                if( ddePrjInt != 0 ){   
                            
                    ddeSugest07Dias = String.valueOf(ddePrjInt);
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
            }catch( Exception e ){}
            
            String ddeMinimoAtual = ""; 
            try{  
                ddeMinimoAtual = String.valueOf( "N"+countLinha+"/(O"+countLinha+"/15)" );
                //ddeMinimoAtual = String.valueOf( "SEERRO(L"+countLinha+"/(M"+countLinha+"/15);"+"\"\""+")" );
            }catch( Exception e ){}
            
            double prencMaximo = 0;
            String strprencMaximo = "";
            try{  
                switch(descricaosetor){
                    case "MERCEARIA":  prencMaximo = 60; break;
                    case "BEBIDAS":    prencMaximo = 40; break;
                    case "HIPEL":      prencMaximo = 40; break;
                    case "BAZAR":      prencMaximo = 40; break;
                    case "TEXTIL":     prencMaximo = 40; break;
                    case "ELETRO":     prencMaximo = 50; break;
                    
                    case "PERECIVEIS": prencMaximo = 50; break;
                    case "PER IN NATURA": prencMaximo = 50; break;
                    case "PER INDUSTRILIZADOS": prencMaximo = 50; break;
                }
                strprencMaximo =  String.valueOf( prencMaximo )+"%";
            }catch( Exception e ){}
            
            double sugAnalis = 0;
            String strsugAnalis = "";
            try{  
                
                int qtdgondula = 0;
                
                try{ qtdgondula = Integer.valueOf(gondula); }catch( Exception e ){}
                
                sugAnalis = qtdgondula*(prencMaximo/100);
                                                                     
                int xSugAnalis = 0;
                try{ xSugAnalis =(int) Math.ceil(sugAnalis); }catch( Exception e ){}
                strsugAnalis = String.valueOf( xSugAnalis );
                //System.out.println(qtdgondula+ "  ----------- "+strsugAnalis +" - "+prencMaximo +" - "+sugAnalis);
            }catch( Exception e ){}
            
            String ddeAnalista="";
            try {

                double dispUmDia = 0; double ddeAnalis = 0; double ddePrjInt = 0;  

                try{ dispUmDia = Double.parseDouble(disponibilidade)/15; }catch( Exception e ){}   
                
                try{ ddePrjInt = sugAnalis/dispUmDia; }catch( Exception e ){}   
                
                        
                if( ddePrjInt != 0 ){   
                            
                    int xddePrjInt = 0;
                    try{ xddePrjInt =(int) Math.ceil(ddePrjInt); }catch( Exception e ){}
                    ddeAnalista = String.valueOf(xddePrjInt);
                }
                
                //System.out.println("qtdEmPed: " + qtd + " - " + "ra: " + esX + " - " + "totalX: " + totalX  );
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
            
            //"LOJA", "SETOR", "DESC. GRUPO COMPRA", "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "EST. LOJA", "CLASSE", "UMB", "E/S", "NOVO MÍNIMO", "DISP",  "QUANTIDADE GÔNDULA / SEÇÃO", "DDE NOVO MÍNIMO ", "MÍNIMO ATUAL", "DDE MÍNIMO ATUAL", "SUGESTÃO ESTOQUE MÍNIMO 15 DIAS", "SUGESTÃO ESTOQUE MÍNIMO 10 DIAS", "SUGESTÃO ESTOQUE MÍNIMO 07 DIAS", "STATUS ESTOQUE ATUAL", "DATA ANÁLISE"           
            tmPesquisa.addRow(new Object[]{ loja, descricaosetor, descGrupCom, sapTab, nomeProd, mrp, ddeAtual, b141, b184, b289, classe, Und, qtdEmPed, "", disponibilidade, gondula, ddeMinimoAtual ,EstoqueMinimo, ddeMinimo, disponibilidade, ddeSugest10Dias, ddeSugest07Dias, sugestaoX, strsugAnalis, ddeAnalista, strprencMaximo, ""  });
        //}
        }  
      
    if(booMinimo = false){ booMinimo = true;
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(2).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(90);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(290);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(10).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(75);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(11).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(12).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(13).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(14).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(14).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(14).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(15).setPreferredWidth(45);
        tbPesquisa.getColumnModel().getColumn(15).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(15).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(16).setPreferredWidth(60);
        tbPesquisa.getColumnModel().getColumn(16).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(16).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(17).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(17).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(17).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(18).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(18).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(18).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(19).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(19).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(19).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(20).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(20).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(20).setCellRenderer( rendererDireita );
        
        tbPesquisa.getColumnModel().getColumn(21).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(21).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(21).setCellRenderer( rendererDireita );      
    }                        
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void setarCabecalhoLayoutOPIdent(){        
        try{
             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "SETOR", "MATERIAL", "OP. IDENTIFICADA"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void analiseLayoutOPIdent( String sap, Gbproduto Gbproduto2 ){ try {                                     
   
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
            
            String sapTab   = "";  try { sapTab   = Gbproduto.getMaterial();    }catch( Exception e ){}            
            //String ean      = "";  try { ean      = Gbproduto.getEan();         }catch( Exception e ){}            
            //String nomeProd = "";  try { nomeProd = Gbproduto.getDescricao();   }catch( Exception e ){}            
            //String qtdEmEs  = "";  try { qtdEmEs  = Gbproduto.getQtdxes();      }catch( Exception e ){}          
            //String Und      = "";  try { Und      = Gbproduto.getUnd();         }catch( Exception e ){}

            
            String opIdentif="";
            try {
                
                List<Opidentificada> XXGbraListaSap = null;
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Opidentificada.class, JPAUtil.em());
                                        
                    XXGbraListaSap = (List<Opidentificada>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Opidentificada.class, 
                            "SELECT * FROM SCHEMAJMARY.OPIDENTIFICADA WHERE MATERIAL = ?", Gbproduto.getMaterial() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbraListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    opIdentif = XXGbraListaSap.get(0).getOpidentificada();                        
                }
            }catch( Exception e ){}
            
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
            
            tmPesquisa.addRow(new Object[]{ descricaosetor, sapTab, opIdentif });
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void setarCabecalhoLayoutProXForn(){        
        try{
             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "CÓD. FORNECEDOR_1", "NOME FORNECEDOR_1", "CÓD. FORNECEDOR_2", "NOME FORNECEDOR_2", "CÓD. FORNECEDOR_3", "NOME FORNECEDOR_3", "CÓD. FORNECEDOR_4", "NOME FORNECEDOR_4"              
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void analiseLayoutProXForn( String sap, Gbproduto Gbproduto2 ){ try {                                     
   
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
            
            String sapTab   = "";  try { sapTab   = Gbproduto.getMaterial();    }catch( Exception e ){}            
            //String ean      = "";  try { ean      = Gbproduto.getEan();         }catch( Exception e ){}            
            //String nomeProd = "";  try { nomeProd = Gbproduto.getDescricao();   }catch( Exception e ){}            
            //String qtdEmEs  = "";  try { qtdEmEs  = Gbproduto.getQtdxes();      }catch( Exception e ){}          
            //String Und      = "";  try { Und      = Gbproduto.getUnd();         }catch( Exception e ){}
            
            String codForn1 = "";
            String codForn2 = "";
            String codForn3 = "";
            String codForn4 = "";
            String nomeForn1 = "";
            String nomeForn2 = "";
            String nomeForn3 = "";
            String nomeForn4 = "";
            
            try {
                Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDOR WHERE MATERIAL = ?", Gbprodutoporfornecedor.class );
                q2.setParameter( 1, sapTab ); 
                List<Gbprodutoporfornecedor> listFor = q2.getResultList();
                
                for( int i=0; i<listFor.size(); i++ ) {
                    
                    switch (i) {
                        case 0:
                            codForn1  = listFor.get(i).getCodigofornecedor();
                            nomeForn1 = listFor.get(i).getEstabelecimento();
                            break;
                        case 1:
                            codForn2  = listFor.get(i).getCodigofornecedor();
                            nomeForn2 = listFor.get(i).getEstabelecimento();
                            break;
                        case 2:
                            codForn3  = listFor.get(i).getCodigofornecedor();
                            nomeForn3 = listFor.get(i).getEstabelecimento();
                            break;
                        case 3:
                            codForn4  = listFor.get(i).getCodigofornecedor();
                            nomeForn4 = listFor.get(i).getEstabelecimento();
                            break;
                        default:
                            break;
                    }
                }
            }catch( Exception e ){ }
                        
            tmPesquisa.addRow(new Object[]{ sapTab, codForn1, nomeForn1, codForn2, nomeForn2, codForn3, nomeForn3, codForn4, nomeForn4 });
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(3).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(5).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(7).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void setarCabecalhoLayoutProXFornCD(){        
        try{
             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "CÓD. FORNECEDOR_CD_1", "NOME FORNECEDOR_CD_1", "CÓD. FORNECEDOR_CD_2", "NOME FORNECEDOR_CD_2", "CÓD. FORNECEDOR_CD_3", "NOME FORNECEDOR_CD_3", "CÓD. FORNECEDOR_CD_4", "NOME FORNECEDOR_CD_4"              
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void analiseLayoutProXFornCD( String sap, Gbproduto Gbproduto2 ){ try {                                     
   
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
            
            String sapTab   = "";  try { sapTab   = Gbproduto.getMaterial();    }catch( Exception e ){}            
            //String ean      = "";  try { ean      = Gbproduto.getEan();         }catch( Exception e ){}            
            //String nomeProd = "";  try { nomeProd = Gbproduto.getDescricao();   }catch( Exception e ){}            
            //String qtdEmEs  = "";  try { qtdEmEs  = Gbproduto.getQtdxes();      }catch( Exception e ){}          
            //String Und      = "";  try { Und      = Gbproduto.getUnd();         }catch( Exception e ){}
            
            String codForn1 = "";
            String codForn2 = "";
            String codForn3 = "";
            String codForn4 = "";
            String nomeForn1 = "";
            String nomeForn2 = "";
            String nomeForn3 = "";
            String nomeForn4 = "";
            
            try {
                Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDORCD WHERE MATERIAL = ?", Gbprodutoporfornecedorcd.class );
                q2.setParameter( 1, sapTab ); 
                List<Gbprodutoporfornecedorcd> listFor = q2.getResultList();
                
                for( int i=0; i<listFor.size(); i++ ) {
                    
                    switch (i) {
                        case 0:
                            codForn1  = listFor.get(i).getCodigofornecedor();
                            nomeForn1 = listFor.get(i).getNomefornecedor();
                            break;
                        case 1:
                            codForn2  = listFor.get(i).getCodigofornecedor();
                            nomeForn2 = listFor.get(i).getNomefornecedor();
                            break;
                        case 2:
                            codForn3  = listFor.get(i).getCodigofornecedor();
                            nomeForn3 = listFor.get(i).getNomefornecedor();
                            break;
                        case 3:
                            codForn4  = listFor.get(i).getCodigofornecedor();
                            nomeForn4 = listFor.get(i).getNomefornecedor();
                            break;
                        default:
                            break;
                    }
                }
            }catch( Exception e ){ }
                        
            tmPesquisa.addRow(new Object[]{ sapTab, codForn1, nomeForn1, codForn2, nomeForn2, codForn3, nomeForn3, codForn4, nomeForn4 });
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(3).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(5).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(150);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(7).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(300);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    /*public void setarCabecalhoLayoutCoringa(){        
        try{
             
            tmPesquisa = new DefaultTableModel( null, new String[]{ "MATERIAL", "CORINGA" } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }*/
    
    /*public void analiseLayoutCoringa( String sap, Gbproduto Gbproduto2 ){ try {                                     
   
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
            
            String sapTab   = "";  try { sapTab   = Gbproduto.getMaterial();    }catch( Exception e ){}            
            //String ean      = "";  try { ean      = Gbproduto.getEan();         }catch( Exception e ){}            
            //String nomeProd = "";  try { nomeProd = Gbproduto.getDescricao();   }catch( Exception e ){}            
            //String qtdEmEs  = "";  try { qtdEmEs  = Gbproduto.getQtdxes();      }catch( Exception e ){}          
            //String Und      = "";  try { Und      = Gbproduto.getUnd();         }catch( Exception e ){}
            
            String coringa="";
            try {
                Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBCORINGA WHERE MATERIAL = ?", Gbcoringa.class );
                q2.setParameter( 1, sapTab ); 
                List<Gbcoringa> listFor = q2.getResultList();
                
                for( int i=0; i<listFor.size(); i++ ) {
                    coringa = listFor.get(i).getCoringa();
                    break;
                }
                
            }catch( Exception e ){ }
                        
            tmPesquisa.addRow(new Object[]{ sapTab, coringa });
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
               
        } catch( Exception e ){  } 
    }*/
    
    public void tabelaResultSet( String query ){ try {   
        while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }                   
        DB DB = new DB();
        Connection con = null; try{ con = DB.derby();             }catch(Exception e){}    
        Statement stmt = null; try{ stmt = con.createStatement(); }catch(Exception e){}        
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData(); 

        int columnCount = rsMetaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        
        int contador = 0;
        for (int i = 0; i < columnCount; i++) {
            contador = 1 + i;
            columnNames [i] = rsMetaData.getColumnName(contador);
        }
            
        tmPesquisa = new DefaultTableModel( null, columnNames );
        tbPesquisa.setModel(tmPesquisa);
        
        while (rs.next()) { 
            
            String[] dados = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                contador = 1 + i;
                dados [i] = ( rs.getString(contador) );
            }
            
            tmPesquisa.addRow( dados ); 
        }
        
        JOPM JOptionPaneMod = new JOPM( 2, "CONSULTA SQL, \n"
                + "\n", "FINALIZADA" );
    } catch( Exception e ){ e.printStackTrace(); } }  
    
    
    public void tabelaResultSetComListSap( String query, String tabela, String colunaParaProcurar, String str[]  ){ try {   
        while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }                   
        DB DB = new DB();
        Connection con = null; try{ con = DB.derby();             }catch(Exception e){}    
        Statement stmt = null; try{ stmt = con.createStatement(); }catch(Exception e){}        
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData(); 

        int columnCount = rsMetaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        
        int colunaProcurada = -1;
        
        int contador = 0;
        for (int i = 0; i < columnCount; i++) {
            contador = 1 + i;
            columnNames [i] = rsMetaData.getColumnName(contador);
            
            //////////////////////////
            String sapPROC = ""; try{ sapPROC = rsMetaData.getColumnName(contador); } catch(Exception e){}
            if( sapPROC.equals(colunaParaProcurar) ){
                
                colunaProcurada = i;
            }
        }
            
        tmPesquisa = new DefaultTableModel( null, columnNames );
        tbPesquisa.setModel(tmPesquisa);
        
        if( !str.equals("") ){ for (int xi = 0; xi < str.length; xi++){
            String busca = ""; try{ busca = str[xi].trim(); }catch( Exception e ){ }
            
            boolean novaLinhaBusca = false;
            
            rs = null;
            
            try{
                String queryX = "SELECT * FROM SCHEMAJMARY."+tabela+" WHERE "+ colunaParaProcurar +" = '"+busca+"'";
                DB = new DB();
                con = null; try{ con = DB.derby();             }catch(Exception e){}    
                stmt = null; try{ stmt = con.createStatement(); }catch(Exception e){}        
                rs = stmt.executeQuery(queryX);
            }catch( Exception e ){ }
                                        
            while (rs.next()) { 
                
                novaLinhaBusca = true;
                        
                boolean buscaEncontrada = false;
                //System.out.println("SAP Procurado: " +busca);
                                    
                String[] dados = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    contador = 1 + i;
                    dados [i] = ( rs.getString(contador) );
                
                    ////////////////////////////////
                    if( i == colunaProcurada ){
                    
                        if( rs.getString(contador).equals(busca) ){
                            
                            buscaEncontrada = true;
                        }
                    }
                }
            
                if( buscaEncontrada == true ){
                    tmPesquisa.addRow( dados ); 
                }
            }  
                
            if( novaLinhaBusca == false ){
               String[] dados = new String[columnCount];
               dados [colunaProcurada] = busca;
               
               tmPesquisa.addRow( dados ); 
               //System.out.println("rs != null: ");
            } 

        }}
        
        JOPM JOptionPaneMod = new JOPM( 2, "CONSULTA SQL, \n"
                + "\n", "FINALIZADA" );
    } catch( Exception e ){ e.printStackTrace(); } }   
    
    
    public void consultaTabelaDeLista( boolean inicio, String busca ){ try {   
        
        if( inicio == true ){
            tmPesquisa = new DefaultTableModel( null, new String[]{ "CONSULTA", "INFORMAÇÃO" } );
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }   
            tbPesquisa.setModel(tmPesquisa);
            
            tmPesquisa.addRow(new Object[]{ busca, "" });
        }
        else{
            
            tmPesquisa.addRow(new Object[]{ busca, "" });
        }
    } catch( Exception e ){ e.printStackTrace(); } }    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    String tabela = "";
    String opcaoTabela = "";
    public void consultaTabelaDeLista( String tabela2, String opcaoTabela2 ) {   
        tabela      = tabela2;  
        opcaoTabela = opcaoTabela2;
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
            DB DB = new DB();
            Connection con = DB.derby();
            String query;
            PreparedStatement ps;
            ResultSet rs;
            
            if( tbPesquisa.getModel().getRowCount() > 0 ){ 
                
                String sapProcura = "";
                                                                                
                for( int L_i=0; L_i < tbPesquisa.getRowCount(); L_i++ ){
                    
                    for( int C_i=0; C_i < tbPesquisa.getColumnCount(); C_i++ ){                    
                        
                        String str  = String.valueOf( tbPesquisa.getValueAt(L_i, C_i) ).trim();                        
                        String strn = tbPesquisa.getColumnName(C_i);
                                                                  
                        if( strn.trim().equals("CONSULTA") ){
                            
                            sapProcura = str;
                        }
                        else if( strn.trim().equals("INFORMAÇÃO") ){
                            tbPesquisa.setValueAt("0",L_i, C_i);
                            query = "SELECT * FROM SCHEMAJMARY."+tabela+" WHERE MATERIAL = "+sapProcura;
                            ps = null; try { ps = con.prepareStatement(query); } catch (Exception e) { } 
                            rs = null;         try { rs = ps.executeQuery(); } catch (Exception e) { e.printStackTrace(); }  
                                                        
                            try { 
                                ResultSetMetaData rsMetaData = rs.getMetaData();
                                int columnCount = rsMetaData.getColumnCount();
                                while (rs.next()) {                                                                    
                                    
                                    for (int i = 1; i <= columnCount; i++) {
                                        System.out.println(rs.getObject(i));
                                    }
                                }
                            } catch (Exception e) { e.printStackTrace(); }  
                            /*ResultSetMetaData rsMetaData = rs.getMetaData();
                            int columnCount = rsMetaData.getColumnCount();
                            while (rs.next()) {
                                for (int i = 1; i <= columnCount; i++) {
                                    System.out.println(rsMetaData.getColumnName(i));
                                    tbPesquisa.setValueAt(rs.getObject(i),L_i, C_i);
                                }
                            }*/                            
                        }
                    }
                    
                }
            }            
    } catch( Exception e ){  } } }.start(); }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void setarCabecalhoUltimaEntradaTodasLojas(){        
        try{
//sapTab, nomeProd, mrpT, qtdEmEs, Und, qtdEmPed, pontExtra, elenco, b141, sugestao, dde, disponibilidade, ped7D, remessa, saiuCD, entrouLJ, venda_ultima_semana, venda_4_semanas, venda_12_semanas,  b001, b289, b184, cdOntem, classe, setor, status, codForn, nomForn, ra,  EstoqueMinimo, ean,  grupo, ativosv, opSemana             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "LOJA", "MATERIAL", "ÚLTIMA ENTRADA"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void pesquisarUltimaEntradaTodasLojas( String loja, String sap, Gbproduto Gbproduto2 ){ try {                                     
   
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
            //String ean      = "";  try { ean      = Gbproduto.getEan();         }catch( Exception e ){}            
            //String nomeProd = "";  try { nomeProd = Gbproduto.getDescricao();   }catch( Exception e ){}            
            //String qtdEmEs  = "";  try { qtdEmEs  = Gbproduto.getQtdxes();      }catch( Exception e ){}          
            //String Und      = "";  try { Und      = Gbproduto.getUnd();         }catch( Exception e ){}
                                                    
            List<Gbultimaentradadata> XXGbultimaentradadata = null;
            String ultimaentradadata="";
            try {     
                
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbultimaentradadata.class, JPAUtil.em());
                                        
                    XXGbultimaentradadata = (List<Gbultimaentradadata>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbultimaentradadata.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBULTIMAENTRADADATA WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBULTIMAENTRADADATA.ESTABELECIMENTO = ? )", Gbproduto.getMaterial(), loja.trim() );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbultimaentradadata.get(0).getMaterial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){   
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    ultimaentradadata = formatter.format( XXGbultimaentradadata.get(0).getDataUltimaentrada() );                  
                }
                
            }catch( Exception e ){}
            
            tmPesquisa.addRow(new Object[]{ loja, sapTab, ultimaentradadata });
        }  
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
        
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(95);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(true);
        tbPesquisa.getColumnModel().getColumn(2).setCellRenderer( rendererCentro );   
        
        //Exportando.fechar();
        ////////////////////////////////////////////////////////////////////////    
       
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    public void cabecalhoTabelaX( String[] cabecalho ){ try{
             
        tmPesquisa = new DefaultTableModel( null, cabecalho );
            
        while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
        tbPesquisa.setModel(tmPesquisa);
            
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
            
        for (int i = 0; i < cabecalho.length; i++ ){
               
            String colunaX = cabecalho[ i ];
                
            switch( colunaX ){
                    
                case "MATERIAL": 
                    tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(100);
                    tbPesquisa.getColumnModel().getColumn(0).setResizable(true);
                break;                    
     
                case "CORINGA": 
                    tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(350);
                    tbPesquisa.getColumnModel().getColumn(1).setResizable(true);
                    tbPesquisa.getColumnModel().getColumn(1).setCellRenderer( rendererCentro );
                break;
            }                
        }         
    } catch( Exception e ){} }
    
    public void layoutTabelaX( String[] cabecalho, String sap ){ try {  
        
        String[] dados = new String[ cabecalho.length ];
        
        for (int i = 0; i < cabecalho.length; i++ ){ switch( cabecalho[ i ] ){
            
            case "MATERIAL":                 
                List<Gbproduto> XXGbProdListaSap = null;
                try{    
                    try{ 
                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                        XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sap );
                    }catch( Exception e ){ }
                
                    String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                    if( !rbusca.equals("") ){ 
                        dados [i] = XXGbProdListaSap.get(0).getMaterial(); 
                    }
                    else{
                        dados [i] = sap; //PRODUTO NÃO CADASTRADO
                    }
                }catch( Exception e ){} 
            break; 
            
            case "CORINGA":
                List<Gbcoringa> XXGbGbcoringa = null;
                try {
                    try{ 
                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbcoringa.class, JPAUtil.em());
                                        
                        XXGbGbcoringa = (List<Gbcoringa>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbcoringa.class, 
                                "SELECT * FROM SCHEMAJMARY.GBCORINGA WHERE MATERIAL = ?", sap );
                    }catch( Exception e ){ }
                
                    String rbusca = ""; try{ rbusca = XXGbGbcoringa.get(0).getMaterial(); }catch( Exception e ){}
                
                    if( !rbusca.equals("") ){ 
                        dados [i] = XXGbGbcoringa.get(0).getCoringa(); 
                    }
                }catch( Exception e ){}  
            break;    

        }}
        
        ////////////////////////////////////////////////////////////////////////
        tmPesquisa.addRow( dados ); 
        
    } catch( Exception e ){} }
       
}
