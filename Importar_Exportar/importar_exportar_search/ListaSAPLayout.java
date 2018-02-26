/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import gb_evento.Gbproduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import utilidades.DB;
import utilidades.Exportando;
import utilidades.JOPM;
import utilidades.ResultSetTableModel;

/**
 *
 * @author AnaMariana
 * SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE='T'


select COLUMNNAME,COLUMNDATATYPE 
FROM sys.systables t, sys.syscolumns 
WHERE TABLEID = REFERENCEID and tablename = 'GBZRIS'
* 
 */
public class ListaSAPLayout extends javax.swing.JFrame {
    
    ListaSAPNaoCadastrado ListaSAPNaoCadastrado;

    Menu_Pesquisa_Importar_Exportar Menu;
    String layout = "";
    /**
     * Creates new form OperacaoRealizada
     * @param layout2
     * @param Menu_Pesquisa_Importar_Exportar2
     */
    public ListaSAPLayout( String layout2, Menu_Pesquisa_Importar_Exportar Menu_Pesquisa_Importar_Exportar2 ) { 
        initComponents();
        lbAcao.setText("LISTAR POR LAYOUT");
        centralizeFrame();

        layout = layout2;
        Menu = Menu_Pesquisa_Importar_Exportar2;
        
        inicio();
    }
    
    private void centralizeFrame() {  
        int x,y;  
        java.awt.Rectangle scr  = this.getGraphicsConfiguration().getBounds();  
        java.awt.Rectangle form = this.getBounds();  
        x = (int) ( scr.getWidth() - form.getWidth() ) / 2;  
        y = (int) ( scr.getHeight()- form.getHeight()) / 2;  
        this.setLocation( x , y );  
    }
    
    private void inicio(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );                     
            DB DB = new DB();
            Connection con = DB.derby();
            String query;
            PreparedStatement ps;
            ResultSet rs;
        
            query = "SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE='T'";
            ps = null; try { ps = con.prepareStatement(query); } catch (Exception e) { } 
            rs = null;         try { rs = ps.executeQuery(); } catch (Exception e) { }   
            if(rs!=null){
                try { 
                    
                    ResultSetMetaData md = rs.getMetaData();
                    int count = md.getColumnCount();
                    
                    /*for (int i = 1; i <= count; i++) {
                      
                        if(i == 1){
                            
                            Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa = new DefaultTableModel( null, new String[]{ 
                                md.getColumnName(i)           
                            } );
        
                            while ( Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.getRowCount() > 0){ Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.removeRow(0); }
                            
                            Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setModel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa);
                        }
                        else{
                            
                            Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.addColumn( md.getColumnName(i) );
                        }
                    }*/
                    
                    if( jComboBox1.getItemCount() > 0 ) { jComboBox1.removeAllItems(); }
                                                            
                    while (rs.next()) {  
                                                                        
                        for (int i = 1; i <= count; i++) {
                            
                            String str = ""; try { str = rs.getString(i); } catch (Exception e) { } 

                            //Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.addRow(new Object[]{ str });
                            jComboBox1.addItem( str );
                        }
                    }
                } catch (Exception e) { } 
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();                 
    }
    
    private boolean cadastrarBoo = false;
    private void tempoDeEspera(){
        
        new Thread() {
            @Override
                public void run() {                    
                    while ( cadastrarBoo == false ) {   
                        cadastrarBoo = true;
                        
        String[] cabecalhoCoringa = new String[]{ "MATERIAL", "CORINGA" };                 
        
        String strTipoDeEvento = "";
        try{ strTipoDeEvento = jComboBox2.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
        if(strTipoDeEvento.equals("OP.IDENTIFICADA")){
            Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoLayoutOPIdent();
        }
        else if(strTipoDeEvento.equals("CORINGA")){
            //Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoLayoutCoringa();
            Menu.Tabela_Pesquisa_BD_Estabelecimento.cabecalhoTabelaX( cabecalhoCoringa );
        }
        else if(strTipoDeEvento.equals("PRODUTO X FORNECEDOR")){
            Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoLayoutProXForn();
        }
        else if(strTipoDeEvento.equals("PRODUTO X FORNECEDOR_CD")){
            Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoLayoutProXFornCD();
        }
                      
                        try { 
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");

                            String str[] = tpListSql.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                                                                                                                
                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
 
                                    String busca = ""; try{ busca = str[i].trim(); }catch( Exception e ){ }
                                    
                                    List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                    try{ 
                                        
                                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                                    }catch( Exception e ){ }
                                    
                                    String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( busca.equals(rbusca) ){
                                        if( !busca.equals("") ){
                                            if(strTipoDeEvento.equals("OP.IDENTIFICADA")){
                                                Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseLayoutOPIdent("", XXGbprodutoListaSap.get(0) );
                                            }
                                            else if(strTipoDeEvento.equals("CORINGA")){
                                                //Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseLayoutCoringa("", XXGbprodutoListaSap.get(0) );
                                                Menu.Tabela_Pesquisa_BD_Estabelecimento.layoutTabelaX( cabecalhoCoringa, XXGbprodutoListaSap.get(0).getMaterial() );
                                            }
                                            else if(strTipoDeEvento.equals("PRODUTO X FORNECEDOR")){
                                                Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseLayoutProXForn("", XXGbprodutoListaSap.get(0) );
                                            }
                                            else if(strTipoDeEvento.equals("PRODUTO X FORNECEDOR_CD")){
                                                Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseLayoutProXFornCD("", XXGbprodutoListaSap.get(0) );
                                            }
                                        }
                                    }
                                    else{
                                        
                                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                    }
                                    
                                }                                
                            }
                            
                            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
                            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                        
                            Exportando.fechar();                     
                        }catch( Exception e ){ fechar(); }
                    }
                }
            }.start();
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

        jLabel2 = new javax.swing.JLabel();
        lbAcao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpListSap = new javax.swing.JTextPane();
        tfLogradouro1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        tfLogradouro2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpListSql = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        tfLogradouro3 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tpListSap1 = new javax.swing.JTextPane();
        tfLogradouro4 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        lbModeloTabelaXLSX1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/disquete.png"))); // NOI18N

        lbAcao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAcao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAcao.setText("CONSULTAR POR LISTA OU TABELA DO BANCO DE DADOS");

        jScrollPane1.setViewportView(tpListSap);

        tfLogradouro1.setEditable(false);
        tfLogradouro1.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro1.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro1.setText("CONSULTAR TABELA");
        tfLogradouro1.setToolTipText("CONSULTAR LISTA SAP");
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfLogradouro1MouseReleased(evt);
            }
        });
        tfLogradouro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OP.IDENTIFICADA", "SUPLY CHAIN", "PRODUTOS", "SETOR ", "SEÇÃO", "PEDIDO PENDENTE", "RA", "PONTO EXTRA", "ELENCO", "ZRIS", "CDB001", "CDB184", "CDB289" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        tfLogradouro2.setEditable(false);
        tfLogradouro2.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro2.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro2.setText("CONSULTAR LISTA");
        tfLogradouro2.setToolTipText("CONSULTAR LISTA SAP");
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfLogradouro2MouseReleased(evt);
            }
        });
        tfLogradouro2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro2KeyReleased(evt);
            }
        });

        tpListSql.setText("SELECT \n    SCHEMAJMARY.GBZRIS.* \nFROM\n    SCHEMAJMARY.GBZRIS, SCHEMAJMARY.GBESTOQUELOJAB141\nWHERE \n\n    SCHEMAJMARY.GBZRIS.MATERIAL = SCHEMAJMARY.GBESTOQUELOJAB141.MATERIAL \nAND\n    SCHEMAJMARY.GBZRIS.DISPONIBILIDADE <= SCHEMAJMARY.GBESTOQUELOJAB141.ESTOQUE_LOJA \nAND \n    SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO\n\n\nAND\n\n    SCHEMAJMARY.GBZRIS.MATERIAL not in (select SCHEMAJMARY.GBESTOQUECDB184.MATERIAL\n                              from SCHEMAJMARY.GBESTOQUECDB184)\n\nAND\n\n    SCHEMAJMARY.GBZRIS.MATERIAL not in (select SCHEMAJMARY.GBESTOQUECDB289.MATERIAL\n                              from SCHEMAJMARY.GBESTOQUECDB289)\n\nAND\n\n    SCHEMAJMARY.GBZRIS.MATERIAL not in (select SCHEMAJMARY.GBESTOQUECDB001.MATERIAL\n                              from SCHEMAJMARY.GBESTOQUECDB001)");
        jScrollPane2.setViewportView(tpListSql);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LISTA SAP / SQL");

        tfLogradouro3.setEditable(false);
        tfLogradouro3.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro3.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro3.setText("CONSULTA SQL");
        tfLogradouro3.setToolTipText("CONSULTAR LISTA SAP");
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfLogradouro3MouseReleased(evt);
            }
        });
        tfLogradouro3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro3KeyReleased(evt);
            }
        });

        tpListSap1.setText("\nSELECT \n    SCHEMAJMARY.GBZRIS.* \nFROM\n    SCHEMAJMARY.GBZRIS, SCHEMAJMARY.GBESTOQUELOJAB141\nWHERE \n\n    SCHEMAJMARY.GBZRIS.MATERIAL = SCHEMAJMARY.GBESTOQUELOJAB141.MATERIAL \nAND\n    SCHEMAJMARY.GBZRIS.DISPONIBILIDADE <= SCHEMAJMARY.GBESTOQUELOJAB141.ESTOQUE_LOJA \nAND \n    SCHEMAJMARY.GBZRIS.ESTABELECIMENTO = SCHEMAJMARY.GBESTOQUELOJAB141.ESTABELECIMENTO\n\n\nAND\n\n    SCHEMAJMARY.GBZRIS.MATERIAL not in (select SCHEMAJMARY.GBESTOQUECDB184.MATERIAL\n                              from SCHEMAJMARY.GBESTOQUECDB184)\n\nAND\n\n    SCHEMAJMARY.GBZRIS.MATERIAL not in (select SCHEMAJMARY.GBESTOQUECDB289.MATERIAL\n                              from SCHEMAJMARY.GBESTOQUECDB289)\n\nAND\n\n    SCHEMAJMARY.GBZRIS.MATERIAL not in (select SCHEMAJMARY.GBESTOQUECDB001.MATERIAL\n                              from SCHEMAJMARY.GBESTOQUECDB001)");
        jScrollPane3.setViewportView(tpListSap1);

        tfLogradouro4.setEditable(false);
        tfLogradouro4.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro4.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro4.setText("CONSULTAR LISTA");
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfLogradouro4MouseReleased(evt);
            }
        });
        tfLogradouro4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro4KeyReleased(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CORINGA", "OP.IDENTIFICADA", "PRODUTO X FORNECEDOR", "PRODUTO X FORNECEDOR_CD", " " }));

        lbModeloTabelaXLSX1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/modelotabelaxlsx.gif"))); // NOI18N
        lbModeloTabelaXLSX1.setToolTipText("CONSULTAR TABELA X LISTA");
        lbModeloTabelaXLSX1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbModeloTabelaXLSX1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbModeloTabelaXLSX1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAcao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox1, 0, 252, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfLogradouro2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfLogradouro3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbModeloTabelaXLSX1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfLogradouro4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbAcao))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbModeloTabelaXLSX1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfLogradouro4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfLogradouro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfLogradouro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfLogradouro1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseEntered

    private void tfLogradouro1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseExited

    private void tfLogradouro1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MousePressed
        //tempoDeEspera();
        consultarPorTabela();
        //fechar();
    }//GEN-LAST:event_tfLogradouro1MousePressed

    private void tfLogradouro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1KeyReleased

    private void tfLogradouro1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseReleased

    private void tfLogradouro2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2MouseEntered

    private void tfLogradouro2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2MouseExited

    private void tfLogradouro2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MousePressed
        tempoDeEspera();
        //fechar(); 
    }//GEN-LAST:event_tfLogradouro2MousePressed

    private void tfLogradouro2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro2MouseReleased

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
        consultaSql();
    }//GEN-LAST:event_tfLogradouro3MousePressed

    private void tfLogradouro3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro3MouseReleased

    private void tfLogradouro3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro3KeyReleased

    private void tfLogradouro4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro4MouseEntered

    private void tfLogradouro4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro4MouseExited

    private void tfLogradouro4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro4MousePressed
        //consultaNaTabela2();
        consultaSqlPorSap();
        /*String strTipoDeEvento = "";
        try{ strTipoDeEvento = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
        if( !strTipoDeEvento.equals( "" ) ){
            
            DB DB = new DB();
            Connection con = DB.derby();
            String query;
            PreparedStatement ps;
            ResultSet rs;
            
            query = "SELECT * FROM SCHEMAJMARY."+strTipoDeEvento+"";
            ps = null; try { ps = con.prepareStatement(query); } catch (Exception e) { } 
            rs = null;         try { rs = ps.executeQuery(); } catch (Exception e) { }   
            
            if(rs!=null){
                try { 
                    
                    ResultSetMetaData md = rs.getMetaData();
                    int count = md.getColumnCount();
                    
                    for (int i = 1; i <= count; i++) {
                        TableColumn c = new TableColumn();   
                        c.setHeaderValue(md.getColumnName(i) );
                        
                        Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.addColumn( md.getColumnName(i) );
                    }

                    while (rs.next()) {  
                                                                        
                        for (int i = 1; i <= count; i++) {
                            
                            String str = ""; try { str = rs.getString(i); } catch (Exception e) { } 

                            Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.addRow(new Object[]{ str });
                        }
                    }
                } catch (Exception e) { } 
            }
        }*/
    }//GEN-LAST:event_tfLogradouro4MousePressed

    private void consultarNaTabela(ResultSet rs){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            Exportando Exportandoy = new Exportando("PESQUISANDO TABELA");
            Exportandoy.setVisible(true);
            Exportandoy.pbg.setMinimum(0);
            Exportandoy.pbg.setMaximum( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );  
            
            int coluna = -1;
            
            if( Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );                 
                    
                    String sap = "";
                    
                    for( int C_i=0; C_i < Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){                       
                                                       
                        String strn = Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnName(C_i);
                    
                        if( strn.trim().equals("MATERIAL") ){
                            
                            coluna = C_i;
                            
                            break;
                        }
                    }
                    
                }
                
                Exportandoy.fechar();
            }
            
            if( coluna > -1 ){/*
                Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.addColumn("");
                final int colunaF = coluna;
                
                Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    
                    String bd = ""; try { bd = String.valueOf( table.getValueAt( row, colunaF ) ); } catch( Exception e ){}
                
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
                } });
            */}
            
            
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();
    }
    
    private void tfLogradouro4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro4MouseReleased

    private void tfLogradouro4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro4KeyReleased

    private void lbModeloTabelaXLSX1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbModeloTabelaXLSX1MousePressed
        String strTipoDeEvento = "";
        try{ strTipoDeEvento = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
        if( !strTipoDeEvento.equals( "" ) ){
            /*
            DB DB = new DB();
            Connection con = DB.derby();
            String query;
            PreparedStatement ps;
            ResultSet rs;
            
            query = "SELECT * FROM SCHEMAJMARY."+strTipoDeEvento+"";
            ps = null; try { ps = con.prepareStatement(query); } catch (Exception e) { } 
            rs = null;         try { rs = ps.executeQuery(); } catch (Exception e) { }  
            */
            String opTab = ""; try { opTab = tpListSap1.getText().trim(); } catch (Exception e) { } 
            
            try { Menu.Tabela_Pesquisa_BD_Estabelecimento.consultaTabelaDeLista( strTipoDeEvento, opTab ); } catch (Exception e) { } 
        }
    }//GEN-LAST:event_lbModeloTabelaXLSX1MousePressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        tpListSap.setText("");
        String tabela = "";
        try{ tabela = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
        if( !tabela.equals( "" ) ){
            
            DB DB = new DB();
            Connection con = DB.derby();
            String query;
            PreparedStatement ps;
            ResultSet rs;
            
            query = "SELECT * FROM SCHEMAJMARY."+tabela+"";
            ps = null; try { ps = con.prepareStatement(query); } catch (Exception e) { } 
            rs = null;         try { rs = ps.executeQuery(); } catch (Exception e) { }                           
            try { 
                
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int columnCount = rsMetaData.getColumnCount();
                for (int i = 1; i<= columnCount; i++){ 
                    
                    String colun = rsMetaData.getColumnName(i);
                    String atual = tpListSap.getText().trim();
                    tpListSap.setText(atual+"\n"+colun);                    
                }
                
                //String queryDin = "SELECT * FROM SCHEMAJMARY."+tabela+"";
                tpListSap1.setText("MATERIAL");  
                //Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setModel(new ResultSetTableModel(rs));
            } catch (Exception e) { } 
        }
        
        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(ListaSAPLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaSAPLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaSAPLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaSAPLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbAcao;
    private javax.swing.JLabel lbModeloTabelaXLSX1;
    public javax.swing.JTextField tfLogradouro1;
    public javax.swing.JTextField tfLogradouro2;
    public javax.swing.JTextField tfLogradouro3;
    public javax.swing.JTextField tfLogradouro4;
    private javax.swing.JTextPane tpListSap;
    private javax.swing.JTextPane tpListSap1;
    private javax.swing.JTextPane tpListSql;
    // End of variables declaration//GEN-END:variables

    public void consultarPorTabela(){  
                
        String strTipoDeEvento = "";
        try{ strTipoDeEvento = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
        if( !strTipoDeEvento.equals( "" ) ){
            
            DB DB = new DB();
            Connection con = DB.derby();
            String query;
            PreparedStatement ps;
            ResultSet rs;
            
            query = "SELECT * FROM SCHEMAJMARY."+strTipoDeEvento+"";
            ps = null; try { ps = con.prepareStatement(query); } catch (Exception e) { } 
            rs = null;         try { rs = ps.executeQuery(); } catch (Exception e) { }                           
            try { Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setModel(new ResultSetTableModel(rs)); } catch (Exception e) { } 
        }
    }
 
    
    public void consultarPorLista(){
                      
        new Thread() {   @Override public void run() { try { 
            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");
            
            DB DB = new DB();
            Connection con = DB.derby();
            String query;
            PreparedStatement ps;
            ResultSet rs;
                            
            String str[] = tpListSap.getText().split("\n");
            if( !str.equals("") ){
                                                                
                Exportando.setVisible(true); Exportando.pbg.setMinimum(0); Exportando.pbg.setMaximum( str.length );
                fechar(); 
            
                Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            
                //for (int i = 0; i < str.length; i++){
                    //Exportando.pbg.setValue( i );
                
                    //String busca = ""; try{ busca = str[i].trim(); }catch( Exception e ){ }
                
                    query = "SELECT "
                                + "SCHEMAJMARY.GBZRIS.ESTABELECIMENTO,"
                                + "SCHEMAJMARY.GBZRIS.MATERIAL,"
                                + "SCHEMAJMARY.GBESTOQUELOJAB141.ESTOQUE_LOJA,"
                                + "SCHEMAJMARY.GBZRIS.DISPONIBILIDADE,"                               
                            + " FROM SCHEMAJMARY.GBZRIS"
                            + "WHERE SCHEMAJMARY.GBZRIS.MATERIAL = SCHEMAJMARY.GBESTOQUELOJAB141.MATERIAL";
                    ps = null; try { ps = con.prepareStatement(query); } catch (Exception e) { } 
                    rs = null;         try { rs = ps.executeQuery(); } catch (Exception e) { }    
                    //if( rs != null ){
                        try { Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setModel(new ResultSetTableModel(rs)); } catch (Exception e) { }      
                    //}
                //}
                
                Exportando.fechar();
           }
        
        } catch( Exception e ){ } } }.start();               
    }
    
    public void consultaSql(){
                      
        new Thread() {   @Override public void run() { try {  
            
            String query = tpListSql.getText().trim();
                                               
            Menu.Tabela_Pesquisa_BD_Estabelecimento.tabelaResultSet(query);
           
        } catch( Exception e ){ e.printStackTrace(); } } }.start();               
    }
    
    public void consultaSqlPorSap(){
                      
        new Thread() {   @Override public void run() { try {  
            
            String tabela = "";
            try{ tabela = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
            if( !tabela.equals( "" ) ){
                
                String query = "SELECT * FROM SCHEMAJMARY."+tabela+"";
                                               
                String str[] = tpListSql.getText().split("\n");
                
                String colunaParaProcurar = tpListSap1.getText().trim();
            
                Menu.Tabela_Pesquisa_BD_Estabelecimento.tabelaResultSetComListSap(query, tabela, colunaParaProcurar, str);
            }
           
        } catch( Exception e ){ e.printStackTrace(); } } }.start();               
    }
    
    private void consultaNaTabela2(){          
        procurarSeSapExiste();        
        new Thread() { @Override public void run() { try { 
            Exportando Exportando = new Exportando("PREPARANDO CONSULTA");
            
            String str[] = tpListSql.getText().split("\n");
            if( !str.equals("") ){
                Exportando.setVisible(true); Exportando.pbg.setMinimum(0);Exportando.pbg.setMaximum( str.length );
                
                boolean inicio = true;
                
                for (int i = 0; i < str.length; i++){
                    Exportando.pbg.setValue( i );
                    
                    Menu.Tabela_Pesquisa_BD_Estabelecimento.consultaTabelaDeLista( inicio, str[i] );
                    inicio = false;
                }                                
            }
            Exportando.fechar();                     
    }catch( Exception e ){ fechar(); } } }.start(); }
    
    private void procurarSeSapExiste(){  
        new Thread() { @Override public void run() { try { 
            
            String str[] = tpListSql.getText().split("\n");
            if( !str.equals("") ){
                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                
                for (String str1 : str) {
                    String busca = "";
                    try {
                        busca = str1.trim();
                    }catch( Exception e ){ }
                    List<Gbproduto> XXGbprodutoListaSap = null;
                    try{ 
                        XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                    }catch( Exception e ){ }
                    String rbusca = "";
                    try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                    if( busca.equals(rbusca) ){

                    }
                    else{
                        String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                        ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                    }
                }                                
            }
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }                    
    }catch( Exception e ){ fechar(); } } }.start(); }
    
}
