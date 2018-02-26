/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor_search;

import fornecedor.Fornecedor;
import fornecedor.Fornecedor_Home;
import fornecedor_new.Fornecedor_New;
import java.util.List;
import javax.persistence.Query;
import jpa.JPAUtil;
import jpa.DAOGenericoJPA;
import utilidades.JOPM;

/**
 *
 * @author AnaMariana
 */
public class Menu_Pesquisa_Fornecedor extends javax.swing.JPanel {

    Fornecedor_Home Fornecedor_Home;
    public  int   IDSELECIONADA; 
    public Tabela_Pesquisa_BD_Fornecedor Tabela_Pesquisa_BD_Fornecedor;
     
    /**
     * Creates new form Cliente
     * @param Fornecedor_Home2
     */
    public Menu_Pesquisa_Fornecedor( Fornecedor_Home Fornecedor_Home2 ) {
        initComponents();
        
        Fornecedor_Home = Fornecedor_Home2;
        
        btSelecionar.setVisible( false );
        jScrollPane1.setVisible(false);
    }
    
    ///Produto_Por_Fornecedor_New///////////////////////////////////////////////////////////////////
    /*public Menu_Pesquisa_Fornecedor( Fornecedor_Home Fornecedor_Home2, Produto_Por_Fornecedor_New Produto_Por_Fornecedor_New2 ) {
        initComponents();
        
        Fornecedor_Home = Fornecedor_Home2;
        
        btSelecionar.setVisible( true );
        btSelecionar.setEnabled(false );
    }*/
    //////////////////////////////////////////////////////////////////////
    
    ///Menu_Pesquisa_Produto_Por_Fornecedor///////////////////////////////////////////////////////////////////
    /*public Menu_Pesquisa_Fornecedor( Fornecedor_Home Fornecedor_Home2, Menu_Pesquisa_Produto_Por_Fornecedor Menu_Pesquisa_Produto_Por_Fornecedor2 ) {
        initComponents();
        
        Fornecedor_Home = Fornecedor_Home2;
        
        btSelecionar.setVisible( true );
        btSelecionar.setEnabled(false );
        
        //Eventos
        try{
            MouseListener[] MouseListener = btSelecionar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                btSelecionar.removeMouseListener( MouseListener1 );
            }
        
            btSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    ////////////////////////////
                     if( btSelecionar.isEnabled() ) {            
                         if( IDSELECIONADA > 0 ) {
                    
                             Fornecedor Fornecedor = new Fornecedor();

                             Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE ID = ?", Fornecedor.class );
                             q.setParameter(1, IDSELECIONADA);   
                    
                             List<fornecedor.Fornecedor> list = q.getResultList();
                    
                             for (int i = 0; i < list.size(); i++){
                                 Fornecedor = list.get(i);
                             }
                    
                             Fornecedor_Home.Menu_Pesquisa_Produto_Por_Fornecedor.setarNoJPanelFornecedorSelecionado( Fornecedor );
                    
                             Fornecedor_Home.Home.ControleTabs.removerTabSemControleSelecionado( Fornecedor_Home.tabHome );
                         }
                         else{
                             JOPM JOptionPaneMod = new JOPM( 2, "Fornecedor Selecionado, \n"
                                     + "Nenhum Fornecedor Selecionado"
                                     + "\nPara continuar Selecione um Fornecedor"
                                     + "\nNa Tabela Abaixo, clicando no botão Pesquisar"    
                                     + "\n", "Fornecedor Selecionado" );
                             tfPesquisa.requestFocus();
                         }
                     }
                    //////////////////////////// 
                }
            });    
        }catch(Exception e ){}
    }*/
    //////////////////////////////////////////////////////////////////////
    
    ////Menu_Pesquisa_Gestor2//////////////////////////////////////////////////////////////////
    /*public Menu_Pesquisa_Fornecedor( Fornecedor_Home Fornecedor_Home2, Menu_Pesquisa_Gestor Menu_Pesquisa_Gestor2 ) {
        initComponents();
        
        Fornecedor_Home = Fornecedor_Home2;
        
        btSelecionar.setVisible( true );
        btSelecionar.setEnabled(false );
        
        //Eventos
        try{
            MouseListener[] MouseListener = btSelecionar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                btSelecionar.removeMouseListener( MouseListener1 );
            }
        
            btSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                     if( btSelecionar.isEnabled() ) {            
                         if( IDSELECIONADA > 0 ) {
                    
                             Fornecedor Fornecedor = new Fornecedor();

                             Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE ID = ?", Fornecedor.class );
                             q.setParameter(1, IDSELECIONADA);   
                    
                             List<fornecedor.Fornecedor> list = q.getResultList();
                    
                             for (int i = 0; i < list.size(); i++){
                                 Fornecedor = list.get(i);
                             }
                    
                             Fornecedor_Home.Menu_Pesquisa_Gestor.setarNoJPanelFornecedorSelecionado( Fornecedor );
                    
                             Fornecedor_Home.Home.ControleTabs.removerTabSemControleSelecionado( Fornecedor_Home.tabHome );
                         }
                         else{
                             JOPM JOptionPaneMod = new JOPM( 2, "Fornecedor Selecionado, \n"
                                     + "Nenhum Fornecedor Selecionado"
                                     + "\nPara continuar Selecione um Fornecedor"
                                     + "\nNa Tabela Abaixo, clicando no botão Pesquisar"    
                                     + "\n", "Fornecedor Selecionado" );
                             tfPesquisa.requestFocus();
                         }
                     }

                }
            });    
        }catch(Exception e ){}
    }*/
    //////////////////////////////////////////////////////////////////////
    
    ////Menu_Pesquisa_Gestor2//////////////////////////////////////////////////////////////////
    /*public Menu_Pesquisa_Fornecedor( Fornecedor_Home Fornecedor_Home2, Gestor_New Gestor_New2 ) {
        initComponents();
        
        Fornecedor_Home = Fornecedor_Home2;
        
        btSelecionar.setVisible( true );
        btSelecionar.setEnabled(false );
        
        //Eventos
        try{
            MouseListener[] MouseListener = btSelecionar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                btSelecionar.removeMouseListener( MouseListener1 );
            }
        
            btSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                     if( btSelecionar.isEnabled() ) {            
                         if( IDSELECIONADA > 0 ) {
                    
                             Fornecedor Fornecedor = new Fornecedor();

                             Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE ID = ?", Fornecedor.class );
                             q.setParameter(1, IDSELECIONADA);   
                    
                             List<fornecedor.Fornecedor> list = q.getResultList();
                    
                             for (int i = 0; i < list.size(); i++){
                                 Fornecedor = list.get(i);
                             }
                    
                             Fornecedor_Home.Gestor_New.setarNoJPanelFornecedorSelecionado( Fornecedor );
                    
                             Fornecedor_Home.Home.ControleTabs.removerTabSemControleSelecionado( Fornecedor_Home.tabHome );
                         }
                         else{
                             JOPM JOptionPaneMod = new JOPM( 2, "Fornecedor Selecionado, \n"
                                     + "Nenhum Fornecedor Selecionado"
                                     + "\nPara continuar Selecione um Fornecedor"
                                     + "\nNa Tabela Abaixo, clicando no botão Pesquisar"    
                                     + "\n", "Fornecedor Selecionado" );
                             tfPesquisa.requestFocus();
                         }
                     }
         
                }
            });    
        }catch(Exception e ){}
    }*/
    //////////////////////////////////////////////////////////////////////
    
    public void tabela(Tabela_Pesquisa_BD_Fornecedor Tabela_Pesquisa_BD_Fornecedor2){ 
                
        try {                
            Tabela_Pesquisa_BD_Fornecedor = Tabela_Pesquisa_BD_Fornecedor2;                                    
        } catch( Exception e ){ e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "tabela, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }     
    }
                
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btExcluir = new javax.swing.JButton();
        btVisualizar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpListSap = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cbTipoDePesquisa = new javax.swing.JComboBox();
        btSelecionar = new javax.swing.JButton();
        lbImportacao = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btPesquisar = new javax.swing.JButton();
        tfPesquisa = new javax.swing.JTextField();
        jpPermissoes = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rbUnicoComIntervalo = new javax.swing.JRadioButton();
        rbUnico = new javax.swing.JRadioButton();
        rbMultiplo = new javax.swing.JRadioButton();
        jPanel15 = new javax.swing.JPanel();
        chColuna = new javax.swing.JCheckBox();
        chCelula = new javax.swing.JCheckBox();
        chLinha = new javax.swing.JCheckBox();
        jPanel17 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btVisualizar2 = new javax.swing.JButton();
        btVisualizar4 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Dados do Fornecedor");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/exluir.gif"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setEnabled(false);
        btExcluir.setPreferredSize(new java.awt.Dimension(97, 25));
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/olho.png"))); // NOI18N
        btVisualizar.setText("Visualizar");
        btVisualizar.setEnabled(false);
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/alterar.gif"))); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.setEnabled(false);
        btAlterar.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btVisualizar)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAlterar, btExcluir, btVisualizar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAlterar, btVisualizar});

        jScrollPane1.setViewportView(tpListSap);

        jPanel4.setBackground(new java.awt.Color(253, 254, 247));

        jPanel6.setBackground(new java.awt.Color(253, 254, 247));

        cbTipoDePesquisa.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbTipoDePesquisa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "I D", "CÓDIGO FORNECEDOR", "NOME FANTASIA", "LISTA CÓDIGO FORNECEDOR" }));
        cbTipoDePesquisa.setSelectedIndex(1);
        cbTipoDePesquisa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        cbTipoDePesquisa.setPreferredSize(new java.awt.Dimension(212, 23));
        cbTipoDePesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoDePesquisaActionPerformed(evt);
            }
        });

        btSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/selecionar.png"))); // NOI18N
        btSelecionar.setText("Selecionar");
        btSelecionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSelecionar.setPreferredSize(new java.awt.Dimension(96, 25));
        btSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btSelecionarMousePressed(evt);
            }
        });
        btSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionarActionPerformed(evt);
            }
        });

        lbImportacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbImportacao.setForeground(new java.awt.Color(0, 102, 0));
        lbImportacao.setText("PESQUISAR POR");
        lbImportacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbImportacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbImportacaoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cbTipoDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lbImportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(253, 254, 247));

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Menu", jPanel5);

        jPanel3.setBackground(new java.awt.Color(253, 254, 247));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Modo de Seleção");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        rbUnicoComIntervalo.setText(" Seleção Única com Intervalo");
        rbUnicoComIntervalo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbUnicoComIntervaloMousePressed(evt);
            }
        });

        rbUnico.setSelected(true);
        rbUnico.setText(" Seleção Única ");
        rbUnico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbUnicoMousePressed(evt);
            }
        });
        rbUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbUnicoActionPerformed(evt);
            }
        });

        rbMultiplo.setText("Múltiplo intervalo de Seleção");
        rbMultiplo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbMultiploMousePressed(evt);
            }
        });
        rbMultiplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMultiploActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbUnicoComIntervalo)
                    .addComponent(rbUnico)
                    .addComponent(rbMultiplo))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbMultiplo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbUnico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbUnicoComIntervalo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chColuna.setBackground(new java.awt.Color(255, 255, 255));
        chColuna.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chColuna.setText("Selecionar por Coluna");
        chColuna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chColunaMousePressed(evt);
            }
        });
        chColuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chColunaActionPerformed(evt);
            }
        });

        chCelula.setBackground(new java.awt.Color(255, 255, 255));
        chCelula.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chCelula.setText("Selecionar por Célula");
        chCelula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chCelulaMousePressed(evt);
            }
        });

        chLinha.setBackground(new java.awt.Color(255, 255, 255));
        chLinha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chLinha.setSelected(true);
        chLinha.setText("Selecionar por Linha");
        chLinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chLinhaMousePressed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(253, 254, 247));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Opções da Seleção");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chLinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chColuna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chCelula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 22, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chLinha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chColuna)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chCelula)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btVisualizar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/printv.png"))); // NOI18N
        btVisualizar2.setText("Visualizar");
        btVisualizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizar2ActionPerformed(evt);
            }
        });

        btVisualizar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/printv2.png"))); // NOI18N
        btVisualizar4.setText("Print Pers.");
        btVisualizar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizar4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpPermissoesLayout = new javax.swing.GroupLayout(jpPermissoes);
        jpPermissoes.setLayout(jpPermissoesLayout);
        jpPermissoesLayout.setHorizontalGroup(
            jpPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermissoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btVisualizar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btVisualizar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jpPermissoesLayout.setVerticalGroup(
            jpPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermissoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPermissoesLayout.createSequentialGroup()
                        .addComponent(btVisualizar4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btVisualizar2))
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tabela", jpPermissoes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btVisualizar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizar4ActionPerformed

          //imprimirComDadosFor();
    }//GEN-LAST:event_btVisualizar4ActionPerformed

    private void btVisualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizar2ActionPerformed
       /* try{
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

                ControleThread_Print t1 = new ControleThread_Print( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, 1, "" );
                t1.setName("ControleThread_Print");
                t1.start();

            } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        } catch( Exception e ){ e.printStackTrace(); }*/
    }//GEN-LAST:event_btVisualizar2ActionPerformed

    private void chLinhaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chLinhaMousePressed
        chColuna.setSelected(false);
        Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Column Selection",
            chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
    }//GEN-LAST:event_chLinhaMousePressed

    private void chCelulaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chCelulaMousePressed
        if( chCelula.isEnabled() ){

            if( chCelula.isSelected() == false ){

                Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Cell Selection",
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
            }
            else if( chLinha.isSelected() ){

                Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Row Selection",
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
            }

            else if( chColuna.isSelected() ){

                Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Column Selection",
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
            }

        }
    }//GEN-LAST:event_chCelulaMousePressed

    private void chColunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chColunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chColunaActionPerformed

    private void chColunaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chColunaMousePressed
        chLinha.setSelected(false);
        Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Row Selection",
            chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
    }//GEN-LAST:event_chColunaMousePressed

    private void rbMultiploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMultiploActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbMultiploActionPerformed

    private void rbMultiploMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMultiploMousePressed
        if( rbMultiplo.isSelected() == true ){

            chCelula.setEnabled( true );

            rbUnicoComIntervalo.setSelected( false );

            rbUnico            .setSelected( true );
            Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Multiple Interval Selection",
                chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
        else{

            chCelula           .setEnabled( false );
            chCelula           .setSelected( false );

            rbUnico            .setSelected( false );
            rbUnicoComIntervalo.setSelected( false );

            Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Multiple Interval Selection",
                chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
    }//GEN-LAST:event_rbMultiploMousePressed

    private void rbUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbUnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbUnicoActionPerformed

    private void rbUnicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbUnicoMousePressed
        if( rbUnico.isSelected() == true ){

            chCelula.setEnabled( true );

            rbMultiplo         .setSelected( false );

            rbUnicoComIntervalo.setSelected( true );
            Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Single Interval Selection",
                chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
        else{

            chCelula.setEnabled ( true );

            rbUnicoComIntervalo.setSelected( false );
            rbMultiplo         .setSelected( false );

            Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Single Selection",
                chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
    }//GEN-LAST:event_rbUnicoMousePressed

    private void rbUnicoComIntervaloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbUnicoComIntervaloMousePressed
        if( rbUnicoComIntervalo.isSelected() == true ){

            chCelula.setEnabled( true );

            rbMultiplo         .setSelected( false );

            rbUnico            .setSelected( true );
            Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Single Selection",
                chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
        else{

            chCelula.setEnabled ( true );

            rbUnico            .setSelected( false );
            rbMultiplo         .setSelected( false );

            Tabela_Pesquisa_BD_Fornecedor.tabelaModoDeSelecao( "Single Interval Selection",
                chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
    }//GEN-LAST:event_rbUnicoComIntervaloMousePressed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            alterarOuVisualizar( "Alterar" );
        } catch( Exception e ){      } } }.start();
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            alterarOuVisualizar( "Visualizar" );
        } catch( Exception e ){      } } }.start();
    }//GEN-LAST:event_btVisualizarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void tfPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyReleased
        pesquisar();
    }//GEN-LAST:event_tfPesquisaKeyReleased

    private void btSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSelecionarActionPerformed

    private void btSelecionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSelecionarMousePressed
        try {  /*
            if( btSelecionar.isEnabled() ) {
                if( IDSELECIONADA > 0 ) {

                    Fornecedor Fornecedor = new Fornecedor();

                    Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE ID = ?", Fornecedor.class );
                    q.setParameter(1, IDSELECIONADA);

                    List<fornecedor.Fornecedor> list = q.getResultList();

                    for (int i = 0; i < list.size(); i++){
                        Fornecedor = list.get(i);
                    }

                    Fornecedor_Home.Produto_Por_Fornecedor_New.setarNoJPanelFornecedorSelecionado( Fornecedor );

                    Fornecedor_Home.Home.ControleTabs.removerTabSemControleSelecionado( Fornecedor_Home.tabHome );
                }
                else{
                    JOPM JOptionPaneMod = new JOPM( 2, "Fornecedor Selecionado, \n"
                        + "Nenhum Fornecedor Selecionado"
                        + "\nPara continuar Selecione um Fornecedor"
                        + "\nNa Tabela Abaixo, clicando no botão Pesquisar"
                        + "\n", "Fornecedor Selecionado" );

                    tfPesquisa.requestFocus();
                }
            }
            */
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "alterar(), \n"
            + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start();
    }//GEN-LAST:event_btSelecionarMousePressed

    private void cbTipoDePesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoDePesquisaActionPerformed
        try{
            if( cbTipoDePesquisa.getSelectedItem() != null ) {

                if( cbTipoDePesquisa.getSelectedItem().toString().trim().equals("LISTA CÓDIGO FORNECEDOR") ){

                    jScrollPane1.setVisible(true);
                }
                else{

                    jScrollPane1.setVisible(false);
                    pesquisar();
                }
            }
        }
        catch( Exception e ){}
    }//GEN-LAST:event_cbTipoDePesquisaActionPerformed

    private void lbImportacaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImportacaoMousePressed

    }//GEN-LAST:event_lbImportacaoMousePressed

    private void pesquisar() {       
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            String tf = "";
            if( !tfPesquisa.getText().trim().equals("") ){
                tf = tfPesquisa.getText().trim();
            }
            Query q = null;
            
            if( cbTipoDePesquisa.getSelectedItem() != null ) {
                
                if( cbTipoDePesquisa.getSelectedItem().toString().trim().equals("I D") ){
                   
                    int n = 0;
                    try{
                        n = Integer.parseInt( tf );
                    } catch( Exception e ){}    
                
                    q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE ID = ?", fornecedor.Fornecedor.class );
                    q.setParameter( 1, n );
            
                    if(q != null) { Tabela_Pesquisa_BD_Fornecedor.pesquisa(q); }

                } 
                else if( cbTipoDePesquisa.getSelectedItem().toString().trim().equals("CÓDIGO FORNECEDOR") ){
                    
                    q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEOURAZAOSOCIAL LIKE ?", fornecedor.Fornecedor.class );
                    q.setParameter(1, "%"+tf.toUpperCase()+"%");
            
                    if(q != null) { Tabela_Pesquisa_BD_Fornecedor.pesquisa(q); }
                }
                else if( cbTipoDePesquisa.getSelectedItem().toString().trim().equals("NOME FANTASIA") ){
                    
                    q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEFANTASIA LIKE ?", fornecedor.Fornecedor.class );
                    q.setParameter(1, "%"+tf.toUpperCase()+"%");
            
                    if(q != null) { Tabela_Pesquisa_BD_Fornecedor.pesquisa(q); }
                }
                else if( cbTipoDePesquisa.getSelectedItem().toString().trim().equals("LISTA CÓDIGO FORNECEDOR") ){
                    
                    String str[] = tpListSap.getText().split("\n");
                    
                    Tabela_Pesquisa_BD_Fornecedor.analiseMostrarListaSap( "strList" );
                }
            }
            
        } catch( Exception e ){ /*JOPM JOPM = new JOPM( 2, "pesquisar(), \n"
                + e.getMessage() + "\n", "Menu_Pesquisa" );*/ } } }.start(); 
    }    
    
    private void excluir() {  
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            if( btExcluir.isEnabled() ) {
            
                if( IDSELECIONADA > 0 ) {
                    
                    //////////////////////////////////////////////////////////////////////////////////////////
                    Fornecedor Fornecedor = new Fornecedor();
                    
                    Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE ID = ?", Fornecedor.class );
                    q.setParameter(1, IDSELECIONADA);   
                    
                    List<fornecedor.Fornecedor> list = q.getResultList();
                    
                    for (int i = 0; i < list.size(); i++){
                        Fornecedor = list.get(i);
                    }   
                    
                    DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA( Fornecedor, JPAUtil.em()); 
                    DAOGenericoJPA.excluir();
                    //////////////////////////////////////////////////////////////////////////////////////////
 
                    btAlterar   .setEnabled(false);
                    btExcluir   .setEnabled(false);
                    btVisualizar.setEnabled(false);
                    pesquisar();
                }
                else{
                    JOPM JOptionPaneMod = new JOPM( 2, "ID SELECIONADA, "
                            + "\nNenhuma matéria selecionada"
                            + "\nSelecione uma matéria"
                            + "\n", "Matéria selecionada" );
                
                    tfPesquisa.requestFocus();
                }
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "excluir(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private void alterarOuVisualizar(String str) {
        try {            
            if( btAlterar.isEnabled() ) {            
                if( IDSELECIONADA > 0 ) {
                    
                    Fornecedor Fornecedor = new Fornecedor();
                    
                    Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE ID = ?", Fornecedor.class );
                    q.setParameter(1, IDSELECIONADA);   
                    
                    List<fornecedor.Fornecedor> list = q.getResultList();
                    
                    for (int i = 0; i < list.size(); i++){
                        Fornecedor = list.get(i);
                    }
                    
                    if( str.equals( "Visualizar") ){
                        
                        Fornecedor_New New = new Fornecedor_New( Fornecedor_Home, Fornecedor );
                        Fornecedor_Home.Home.ControleTabs.AddTabSemControleNovo( Fornecedor_Home.tabHome, "Visualizar", "/utilidades_imagens/olho.png", New );
                        New.btSalvar   .setVisible(false);
                        New.btAtualizar.setVisible(false);
                    }
                    else if( str.equals( "Alterar") ){
                        
                        Fornecedor_New New = new Fornecedor_New( Fornecedor_Home, Fornecedor );
                        Fornecedor_Home.Home.ControleTabs.AddTabSemControleNovo( Fornecedor_Home.tabHome, "Alterar", "/utilidades_imagens/alterar.gif", New );
                    }
                }
                else{
                    JOPM JOptionPaneMod = new JOPM( 2, "ID SELECIONADA, "
                            + "\nNenhuma USUÁRIO selecionada"
                            + "\nSelecione uma matéria"
                            + "\n", "Matéria selecionada" );
                
                    tfPesquisa.requestFocus();
                }
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "alterar(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
                  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAlterar;
    public javax.swing.JButton btExcluir;
    private javax.swing.JButton btPesquisar;
    public javax.swing.JButton btSelecionar;
    public javax.swing.JButton btVisualizar;
    private javax.swing.JButton btVisualizar2;
    private javax.swing.JButton btVisualizar4;
    private javax.swing.JComboBox cbTipoDePesquisa;
    private javax.swing.JCheckBox chCelula;
    private javax.swing.JCheckBox chColuna;
    private javax.swing.JCheckBox chLinha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpPermissoes;
    private javax.swing.JLabel lbImportacao;
    private javax.swing.JRadioButton rbMultiplo;
    private javax.swing.JRadioButton rbUnico;
    private javax.swing.JRadioButton rbUnicoComIntervalo;
    private javax.swing.JTextField tfPesquisa;
    public javax.swing.JTextPane tpListSap;
    // End of variables declaration//GEN-END:variables
    
}
