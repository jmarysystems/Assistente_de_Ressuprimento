/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fornecedor_search;

import fornecedor.Fornecedor;
import fornecedor.Fornecedor_Home;
import java.awt.Color;
import java.util.List;
import javax.persistence.Query;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import utilidades.Alinhar_Tabela_Centro;
import utilidades.JOPM;

/**
 *
 * @author ana
 */
public class Tabela_Pesquisa_BD_Fornecedor extends javax.swing.JPanel {
   
    private Fornecedor_Home             Fornecedor_Home;
    private Menu_Pesquisa_Fornecedor    Menu_Pesquisa;
        
    private List<fornecedor.Fornecedor> list;
    private ListSelectionModel          lsmPesquisa;
    
    public DefaultTableModel            tmPesquisa = new DefaultTableModel( null, new String[]{ "  ID","CÓDIGO FORNECEDOR","NOME FANTASIA", "ABASTECIMENTO", "LEAD TIME","NOME VENDEDOR","TELEFONE VENDEDOR", "E-MAIL VENDEDOR","NOME SUPERVISOR","TELEFONE SUPERVISOR", "E-MAIL SUPERVISOR", "NOME PROMOTOR", "TELEFONE PROMOTOR", "E-MAIL PROMOTOR" } );
    
    public Tabela_Pesquisa_BD_Fornecedor( Fornecedor_Home Fornecedor_Home2 ) {
         
        Fornecedor_Home      = Fornecedor_Home2;
                
        initComponents();
                 
        tabelaInicio();                        
    }
    
    public void tabela(Menu_Pesquisa_Fornecedor Menu_Pesquisa2){ 
                
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
            TableCellRenderer tcrC = new Alinhar_Tabela_Centro();
            
            tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(false);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( tcrC );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(130);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(false);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(130);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
            
            tbPesquisa.setRowHeight(30);
            tbPesquisa.setSelectionBackground(Color.YELLOW);
            tbPesquisa.setSelectionForeground(Color.BLUE);
            
            tbPesquisa.getTableHeader().setReorderingAllowed(false);
            tbPesquisa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            
        } catch( Exception e ){}
    }
    
    Query q;
    public void pesquisa(Query q2){
        q = q2;
        new Thread() {   @Override public void run() { try { 
            
            setarCabecalhoTabelaGenerica();
            
            Thread.sleep( 1 ); 
            
            list = q.getResultList();
            mostrarLista( list );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();              
    }
    
    public void analiseMostrarListaSap( String comando ){
        
        setarCabecalhoTabelaGenerica(); 
        
        switch (comando) {
            case "strList":
                try{
                    
                    String str[] = Menu_Pesquisa.tpListSap.getText().split("\n");
                    
                    if( str.length > -1 ){
                        
                        for (int i = 0; i < str.length; i++){
                            
                            String busca = ""; try{ busca = str[i].trim(); }catch( Exception e ){ }
                            
                            try{ 
                                q = null;
                                
                                q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEOURAZAOSOCIAL = ?", fornecedor.Fornecedor.class );
                                q.setParameter(1, busca);
                                List<Fornecedor> FornecedorSap = q.getResultList(); 
                                
                                analiseGenericaTabela( "", FornecedorSap.get(0) );
                                
                            }catch( Exception e ){ }
                        }
                    }
                    
                } catch( Exception e ){ }
                break;
            case "listasap":
                try{
                    
                    
                    
                } catch( Exception e ){ }
                break;
        }
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbPesquisa;
    // End of variables declaration//GEN-END:variables
        
    public void mostrarLista( List<fornecedor.Fornecedor> listR ){  try {                    
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
            }else{
                String [] campos = new String[] {null, null};
                for (int i = 0; i < listR.size(); i++){
                    tmPesquisa.addRow(campos);
                    
                    tmPesquisa.setValueAt( listR.get(i).getId()                 , i, 0);
                    tmPesquisa.setValueAt( listR.get(i).getNomeourazaosocial()  , i, 1);
                    tmPesquisa.setValueAt( listR.get(i).getNomefantasia()       , i, 2);
                    tmPesquisa.setValueAt( listR.get(i).getCd()                 , i, 3);
                    tmPesquisa.setValueAt( listR.get(i).getLeadtime()           , i, 4);                  
                    tmPesquisa.setValueAt( listR.get(i).getNomevendedor()       , i, 5);                    
                    tmPesquisa.setValueAt( listR.get(i).getTelefonevendedor()   , i, 6);
                    tmPesquisa.setValueAt( listR.get(i).getPrazodepagamento()   , i, 7);
                    tmPesquisa.setValueAt( listR.get(i).getNomesupervisor()     , i, 8);
                    tmPesquisa.setValueAt( listR.get(i).getTelefonesupervisor() , i, 9);
                    tmPesquisa.setValueAt( listR.get(i).getPrazodeentrega()     , i, 10);
                    tmPesquisa.setValueAt( listR.get(i).getNomepromotor()       , i, 11);
                    tmPesquisa.setValueAt( listR.get(i).getTelefonepromotor()   , i, 12);
                    tmPesquisa.setValueAt( listR.get(i).getEmailpromotor()      , i, 13);
                     
                    
                    TableCellRenderer tcrC = new Alinhar_Tabela_Centro();
            
            tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(false);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( tcrC );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(130);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(false);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(130);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
                }
            }    
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    private void tbPesquisaLinhaSelecionada() { 
        try{
            
            if ( tbPesquisa.getSelectedRow() != -1){
                           
                Menu_Pesquisa.IDSELECIONADA = list.get( tbPesquisa.getSelectedRow() ).getId();
                                
                /*if( Usuario_Permissoes.booAlterarfornecedor( Usuario_Logado.ID ) == true ) {*/ Menu_Pesquisa.btAlterar.setEnabled(true);  //}
                //System.out.println( "* "+Usuario_Logado.ID+ " - "+Usuario_Permissoes.booAlterarusuario( Usuario_Logado.ID ) );
                /*if( Usuario_Permissoes.booExcluirfornecedor( Usuario_Logado.ID ) == true ) {*/ Menu_Pesquisa.btExcluir    .setEnabled(true); //}
                Menu_Pesquisa.btVisualizar.setEnabled(true);
                Menu_Pesquisa.btSelecionar.setEnabled(true);
            } else{
                      
                Menu_Pesquisa.IDSELECIONADA = -1;  
                
                Menu_Pesquisa.btAlterar   .setEnabled(false);
                Menu_Pesquisa.btExcluir   .setEnabled(false);
                Menu_Pesquisa.btVisualizar.setEnabled(false);
                Menu_Pesquisa.btSelecionar.setEnabled(false);
            }
        } catch( Exception e ) {}
    }
    
    public void setarCabecalhoTabelaGenerica(){        
        try{             
            tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "  ID","CÓDIGO FORNECEDOR","NOME FANTASIA", "ABASTECIMENTO", "LEAD TIME","NOME VENDEDOR","TELEFONE VENDEDOR", "E-MAIL VENDEDOR","NOME SUPERVISOR","TELEFONE SUPERVISOR", "E-MAIL SUPERVISOR", "NOME PROMOTOR", "TELEFONE PROMOTOR", "E-MAIL PROMOTOR"           
            } );
            
            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            
            tbPesquisa.setModel(tmPesquisa);
            
        } catch( Exception e ){} 
    }
    
    public void analiseGenericaTabela( String sap, Fornecedor Fornecedor2 ){ try {  
        
        Fornecedor Fornecedor = Fornecedor2;
        
        if ( !sap.equals("") ){ 
            
            List<Fornecedor> XXGbProdListaSap = null;
            try {
                try{ 
                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Fornecedor.class, JPAUtil.em());
                                        
                    XXGbProdListaSap = (List<Fornecedor>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Fornecedor.class, 
                            "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEOURAZAOSOCIAL = ?", sap );
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getNomeourazaosocial(); }catch( Exception e ){}
                
                if( !rbusca.equals("") ){                    
                    Fornecedor = XXGbProdListaSap.get(0);                        
                }
            }catch( Exception e ){}
            
        }else if( Fornecedor != null ){
            
            tmPesquisa.addRow(new Object[]{ 
                Fornecedor.getId(),
                Fornecedor.getNomeourazaosocial(),
                Fornecedor.getNomefantasia(),
                Fornecedor.getCd(),
                Fornecedor.getLeadtime(),
                Fornecedor.getNomevendedor(),
                Fornecedor.getTelefonevendedor(),
                Fornecedor.getPrazodepagamento(),
                Fornecedor.getNomesupervisor(),
                Fornecedor.getTelefonesupervisor(),
                Fornecedor.getPrazodeentrega(),
                Fornecedor.getNomepromotor(),
                Fornecedor.getTelefonepromotor(),
                Fornecedor.getEmailpromotor()
            });
        }
        
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(80);
        tbPesquisa.getColumnModel().getColumn(0).setResizable(false);
        tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( rendererCentro );
            
        tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(130);
        tbPesquisa.getColumnModel().getColumn(1).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbPesquisa.getColumnModel().getColumn(2).setResizable(false);
        
        tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(3).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(4).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(5).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(6).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(6).setResizable(false);
            
        tbPesquisa.getColumnModel().getColumn(7).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(7).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(8).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(8).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(9).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(9).setResizable(true);
            
        tbPesquisa.getColumnModel().getColumn(10).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(10).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(11).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(11).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(12).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(12).setResizable(true);
        
        tbPesquisa.getColumnModel().getColumn(13).setPreferredWidth(160);
        tbPesquisa.getColumnModel().getColumn(13).setResizable(true);
        
    } catch( Exception e ) {} }
        
        
}
