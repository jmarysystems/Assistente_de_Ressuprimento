package estabelecimento_search;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import estabelecimento.Estabelecimento_Home;
import java.awt.Color;
import java.util.List;
import javax.persistence.Query;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import jpa.JPAUtil;
import utilidades.Administrador;
import utilidades.Alinhar_Tabela_Centro;
import utilidades.JOPM;

/**
 *
 * @author ana
 */
public class Tabela_Pesquisa_BD_Estabelecimento extends javax.swing.JPanel {
   
    private Estabelecimento_Home          Estabelecimento_Home;
    private Menu_Pesquisa_Estabelecimento Menu_Pesquisa;
        
    private List<estabelecimento.Estabelecimento> list;
    private ListSelectionModel               lsmPesquisa;
    private final DefaultTableModel          tmPesquisa = new DefaultTableModel( null, new String[]{ "     ID","RAZÃO SOCIAL", "NOME FANTASIA", "CNPJ", "TELEFONE", "CONTATO"} );
    
    public Tabela_Pesquisa_BD_Estabelecimento( Estabelecimento_Home Estabelecimento_Home2 ) {
         
        Estabelecimento_Home      = Estabelecimento_Home2;
                
        initComponents();
                 
        tabelaInicio();                        
    }
    
    public void tabela(Menu_Pesquisa_Estabelecimento Menu_Pesquisa2){ 
                
        try {                
            Menu_Pesquisa = Menu_Pesquisa2;                                    
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabela, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }     
    }
    
    private void tabelaInicio(){
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                    
            tbPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lsmPesquisa = tbPesquisa.getSelectionModel();
            lsmPesquisa.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (! e.getValueIsAdjusting()){
                        tbPesquisaLinhaSelecionada();
                    }
                }
            });
            
            tbPesquisa.setAutoCreateRowSorter(true);
            
            tbPreferedSize(); 
            
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.ESTABELECIMENTO", estabelecimento.Estabelecimento.class );
            q.setMaxResults(10);
            list = q.getResultList();
            mostrarLista( list );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();              
    }
    
    private void tbPreferedSize(){        
        try{
            TableCellRenderer tcrC = new Alinhar_Tabela_Centro();
            
            tbPesquisa.getColumnModel().getColumn(0).setPreferredWidth(55);
            tbPesquisa.getColumnModel().getColumn(0).setResizable(false);
            tbPesquisa.getColumnModel().getColumn(0).setCellRenderer( tcrC );
            
            tbPesquisa.getColumnModel().getColumn(1).setPreferredWidth(230);
            tbPesquisa.getColumnModel().getColumn(1).setResizable(false);
            
            tbPesquisa.getColumnModel().getColumn(2).setPreferredWidth(160);
            tbPesquisa.getColumnModel().getColumn(2).setResizable(false);
            
            tbPesquisa.getColumnModel().getColumn(3).setPreferredWidth(160);
            tbPesquisa.getColumnModel().getColumn(3).setResizable(false);
            
            tbPesquisa.getColumnModel().getColumn(4).setPreferredWidth(160);
            tbPesquisa.getColumnModel().getColumn(4).setResizable(false);
            
            tbPesquisa.getColumnModel().getColumn(5).setPreferredWidth(160);
            tbPesquisa.getColumnModel().getColumn(5).setResizable(true);
        
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
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            list = q.getResultList();
            mostrarLista( list );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();              
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
        
    public void mostrarLista( List<estabelecimento.Estabelecimento> listR ){  try {                    

            while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }
            if ( listR.isEmpty() ){ //JOptionPane.showMessageDialog( null , "Nenhum dado encontrado!"); 
            }else{
                String [] campos = new String[] {null, null};
                for (int i = 0; i < listR.size(); i++){
                    tmPesquisa.addRow(campos);
                    
                    try{ tmPesquisa.setValueAt( listR.get(i).getId()                  , i, 0); }catch( Exception e ){}
                    try{ tmPesquisa.setValueAt( listR.get(i).getRazaoSocial()         , i, 1); }catch( Exception e ){}
                    try{ tmPesquisa.setValueAt( listR.get(i).getNomeFantasia()        , i, 2); }catch( Exception e ){}
                    try{ tmPesquisa.setValueAt( listR.get(i).getCnpj()                , i, 3); }catch( Exception e ){}
                    try{ tmPesquisa.setValueAt( listR.get(i).getTelefone()            , i, 4); }catch( Exception e ){}
                    try{ tmPesquisa.setValueAt( listR.get(i).getContato()             , i, 5); }catch( Exception e ){}
                }
            }    
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "mostrarLista( List<materias_control.Materia> list2 ), \n"
                + e.getMessage() + "\n", "Tabela_Pesquisa_BD_Usuario" );*/ } 
    }
    
    private void tbPesquisaLinhaSelecionada() { 
        try{
            
            if ( tbPesquisa.getSelectedRow() != -1){
                           
                Menu_Pesquisa.IDSELECIONADA = list.get( tbPesquisa.getSelectedRow() ).getId();
                
                Menu_Pesquisa.btVisualizar.setEnabled(true);
                Menu_Pesquisa.btSelecionar.setEnabled(true);               
                
                if( Administrador.mapaComandos.get("ALTERAR_ESTABELECIMENTO") != null ) {
                    Menu_Pesquisa.btAlterar.setEnabled(true);
                }  
                
                if( Administrador.mapaComandos.get("EXCLUIR_ESTABELECIMENTO") != null ) {
                    Menu_Pesquisa.btExcluir    .setEnabled(true);
                }             

            } else{
                      
                Menu_Pesquisa.IDSELECIONADA = -1;  
                
                Menu_Pesquisa.btAlterar   .setEnabled(false);
                Menu_Pesquisa.btExcluir   .setEnabled(false);
                Menu_Pesquisa.btVisualizar.setEnabled(false);
                Menu_Pesquisa.btSelecionar.setEnabled(false);
            }
        } catch( Exception e ) {}
    }
        
}
