/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import banco_de_dados.Alterar_Tabela_Qtd_Venda;
import banco_de_dados_tabelas.GBQUANTIDADEVENDIDA;
import controle_acesso_new.Controle_Acesso_New;
import fornecedor.Fornecedor;
import gb_evento.Analisedevolutiva;
import gb_evento.Analiseeventos;
import gb_evento.Enderecoespacogondula;
import gb_evento.Evento;
import gb_evento.Eventoprodutos;
import gb_evento.Gbcoringa;
import gb_evento.Gbddedisp;
import gb_evento.Gbdescricaogrupo;
import gb_evento.Gbdescricaosetor;
import gb_evento.Gbdiassemvenda;
import gb_evento.Gbdiretoloja;
import gb_evento.Gbeansecundario;
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
import gb_evento.Inventariomb52;
import gb_evento.Inventariozlmr06;
import gb_evento.Lof;
import gb_evento.LojasAMb52;
import gb_evento.LojasAZdadoslogCd;
import gb_evento.LojasAZdadoslogLoja;
import gb_evento.LojasAZvtraSort;
import gb_evento.Opidentificada;
import home_usuario_logado.Bean_Usuario_Logado;
import importar_exportar.Importar_Exportar_Home;
import impressao_JM.O1_Imprimir_Tabela_JM_ControleThread;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import print.ControleThread_Print;
import utilidades.Alterar_Propriedades;
import utilidades.Arquivo_Ou_Pasta;
import utilidades.ClipBoard;
import utilidades.Exportando;
import utilidades.ExportarExcelExistenteInventario;
import utilidades.Formatar_Data;
import utilidades.JFileChooserJM;
import utilidades.JFileChooserJMPasta;
import utilidades.JOPM;
import utilidades_imagens.Imagens;

/**
 *
 * @author AnaMariana
 */
public class Menu_Pesquisa_Importar_Exportar extends javax.swing.JPanel {

    Importar_Exportar_Home  Importar_Exportar_Home;
    public  int    IDSELECIONADA; 
    public Tabela_Pesquisa_BD_Importar_Exportar Tabela_Pesquisa_BD_Estabelecimento;
     
    /**
     * Creates new form Cliente
     * @param Estabelecimento_Home2
     */
    public Menu_Pesquisa_Importar_Exportar( Importar_Exportar_Home Estabelecimento_Home2 ) {
        initComponents();
        
        Importar_Exportar_Home = Estabelecimento_Home2;
        
        btSelecionar.setVisible( false );
        
        btExcluirEventoGB.setVisible( false );
        cbEventos.setEnabled(false);
        lbFiltro.setEnabled(false);
        lbExcluir.setVisible( false );
        
        tfSapAnalise1.setVisible(false);
        jLabel41.setVisible(false);
        
        //configurarModoAvancado();  
        jpBasico.setVisible( false );
        chbDefinirPermissoes.setSelected(true );
        disponibilidade();
        
        jpRelatorio.setVisible(false);
    }
    
    private void inicioCBS(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );         
            Color c = new Color( 255, 255, 255 );
        
            cbEventos.setBackground( c );
            cbPesquisarPor2.setBackground( c );           
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicioCBS(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();       
    }
    
    private void disponibilidade(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );         
            
            try{
                Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBDDEDISP", Gbddedisp.class );
                List<Gbddedisp> list = q.getResultList();
            
                for (int i = 0; i < list.size(); i++){
                    
                    tfDde.setText(      list.get(i).getDde()    );
                    tfTop350.setText(   list.get(i).getTop350() );
                    tfTop2000.setText(  list.get(i).getTop2000());
                    tfMix.setText(       list.get(i).getMix()   );
                    tfTotal.setText(     list.get(i).getTotal() );
  
                }
            }catch( Exception e ){}        
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "inicioCBS(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" );*/ } } }.start();       
    }
    
    ////Menu_Pesquisa_Gestor2//////////////////////////////////////////////////////////////////
    public Menu_Pesquisa_Importar_Exportar( Importar_Exportar_Home Estabelecimento_Home2, JTabbedPane tabHome ) {
        initComponents();
        
        Importar_Exportar_Home = Estabelecimento_Home2;
        
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
    }
    //////////////////////////////////////////////////////////////////////
    
    ////Menu_Pesquisa_Gestor2//////////////////////////////////////////////////////////////////
    public Menu_Pesquisa_Importar_Exportar( Importar_Exportar_Home Estabelecimento_Home2, Controle_Acesso_New Controle_Acesso_New2 ) {
        initComponents();
        
        Importar_Exportar_Home = Estabelecimento_Home2;
        
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
    }
    //////////////////////////////////////////////////////////////////////
    
    public void tabela(Tabela_Pesquisa_BD_Importar_Exportar Tabela_Pesquisa_BD_Estabelecimento2 ){ 
                
        try {                
            Tabela_Pesquisa_BD_Estabelecimento = Tabela_Pesquisa_BD_Estabelecimento2;                                    
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

        tfRazao_Social2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        chbDefinirPermissoes = new javax.swing.JCheckBox();
        jpBasico = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbImportacao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbExportacao = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tfPesquisa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btExcluir = new javax.swing.JButton();
        btVisualizar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btSelecionar = new javax.swing.JButton();
        jpAvancado = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpPermissoes1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jpConsultarAnalise = new javax.swing.JPanel();
        jpFiltroSorter4 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        lbAnaliseConsultaMousePressed = new javax.swing.JLabel();
        btAlterar6 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        tfPesquisarPeloNomeProduto = new javax.swing.JTextField();
        cbEventos1 = new javax.swing.JComboBox();
        lbFiltro1 = new javax.swing.JLabel();
        lbFiltro2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbExcluir = new javax.swing.JLabel();
        lbFiltro3 = new javax.swing.JLabel();
        lbFiltro4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel42 = new javax.swing.JLabel();
        btAlterar8 = new javax.swing.JButton();
        tfSapAnalise = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfEanAnalise = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lbExcluir1 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tfEanAnalise1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        lbExcluir2 = new javax.swing.JLabel();
        jpPermissoes3 = new javax.swing.JPanel();
        jpFiltroSorter3 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btAnaliseCadastrar = new javax.swing.JButton();
        lbModeloTabelaXLSX1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbImportacao1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbAnaliseCadastro = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        lbImportacao2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btExcluirParametros = new javax.swing.JButton();
        cbAnaliseParametros = new javax.swing.JComboBox();
        lbModeloTabelaXLSX2 = new javax.swing.JLabel();
        lbModeloTabelaXLSX3 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lbModeloTabelaXLSX4 = new javax.swing.JLabel();
        jpInventario = new javax.swing.JPanel();
        jpFiltroSorter5 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        btAnaliseCadastrar1 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        lbImportacao3 = new javax.swing.JLabel();
        cbAnaliseCadastro1 = new javax.swing.JComboBox();
        tfSapAnalise1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        btExcluirParametros1 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        tfNegativo = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        tfPositivo = new javax.swing.JTextField();
        tfGeral = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jpPermissoes = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rbUnicoComIntervalo = new javax.swing.JRadioButton();
        rbUnico = new javax.swing.JRadioButton();
        rbMultiplo = new javax.swing.JRadioButton();
        jPanel15 = new javax.swing.JPanel();
        chColuna = new javax.swing.JCheckBox();
        chCelula = new javax.swing.JCheckBox();
        chLinha = new javax.swing.JCheckBox();
        jPanel17 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btVisualizar1 = new javax.swing.JButton();
        btVisualizar2 = new javax.swing.JButton();
        btVisualizar3 = new javax.swing.JButton();
        btVisualizar4 = new javax.swing.JButton();
        jpConsulta = new javax.swing.JPanel();
        jpFiltroSorter2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btAlterar4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        cbPesquisarPor2 = new javax.swing.JComboBox();
        cbEventos = new javax.swing.JComboBox();
        btAlterar5 = new javax.swing.JButton();
        tfSap = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbFiltro = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jpPermissoes2 = new javax.swing.JPanel();
        jpFiltroSorter1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btAlterar2 = new javax.swing.JButton();
        tfData_Inicio_Atividades = new javax.swing.JFormattedTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        tfData_Inicio_Atividades1 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        tfNome_Fantasia = new javax.swing.JTextField();
        cbPesquisarPor1 = new javax.swing.JComboBox();
        lbModeloTabelaXLSX = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        btExcluirEventoGB = new javax.swing.JButton();
        btAlterar3 = new javax.swing.JButton();
        btExcluirEventoGB1 = new javax.swing.JButton();
        jpRelatorio = new javax.swing.JPanel();
        btExcluirEventoGB2 = new javax.swing.JButton();
        btAlterar7 = new javax.swing.JButton();
        btExcluirEventoGB3 = new javax.swing.JButton();
        tfDde = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        tfTop350 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tfTop2000 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        tfMix = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();

        tfRazao_Social2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfRazao_Social2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfRazao_Social2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfRazao_Social2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfRazao_Social2MouseExited(evt);
            }
        });
        tfRazao_Social2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfRazao_Social2KeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Pesquisar pelo nome do produto");

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Total:");

        jTextField2.setPreferredSize(new java.awt.Dimension(59, 23));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("DDE / DISPONIBILIDADE");

        chbDefinirPermissoes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chbDefinirPermissoes.setText("Avançado");
        chbDefinirPermissoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chbDefinirPermissoesMousePressed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(253, 254, 247));

        jPanel6.setBackground(new java.awt.Color(253, 254, 247));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/xls.png"))); // NOI18N

        lbImportacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbImportacao.setForeground(new java.awt.Color(0, 102, 0));
        lbImportacao.setText("IMPORTAÇÃO  ");
        lbImportacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbImportacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbImportacaoMousePressed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/xlsx.png"))); // NOI18N

        lbExportacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbExportacao.setForeground(new java.awt.Color(0, 102, 0));
        lbExportacao.setText("EXPORTAÇÃO  ");
        lbExportacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExportacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbExportacaoMousePressed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(0, 102, 0));
        jLabel6.setText("/");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbExportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbExportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbImportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel4)))))
            .addComponent(jLabel5)
            .addComponent(jLabel6)
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, lbImportacao});

        jPanel1.setBackground(new java.awt.Color(253, 254, 247));

        tfPesquisa.setEditable(false);
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
                .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/alterar.gif"))); // NOI18N
        btExcluir.setText("HTML");
        btExcluir.setEnabled(false);
        btExcluir.setPreferredSize(new java.awt.Dimension(97, 25));
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/printv.png"))); // NOI18N
        btVisualizar.setText("Print");
        btVisualizar.setEnabled(false);
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/alterar.gif"))); // NOI18N
        btAlterar.setText("PDF");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAlterar, btVisualizar});

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

        javax.swing.GroupLayout jpBasicoLayout = new javax.swing.GroupLayout(jpBasico);
        jpBasico.setLayout(jpBasicoLayout);
        jpBasicoLayout.setHorizontalGroup(
            jpBasicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBasicoLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpBasicoLayout.setVerticalGroup(
            jpBasicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBasicoLayout.createSequentialGroup()
                .addGroup(jpBasicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpBasicoLayout.createSequentialGroup()
                .addComponent(btSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(253, 254, 247));
        jPanel18.setPreferredSize(new java.awt.Dimension(313, 26));

        lbAnaliseConsultaMousePressed.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbAnaliseConsultaMousePressed.setText("CONSULTAR TODOS OS PRODUTOS / ANÁLISE");
        lbAnaliseConsultaMousePressed.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAnaliseConsultaMousePressed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbAnaliseConsultaMousePressedMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(lbAnaliseConsultaMousePressed, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnaliseConsultaMousePressed, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        btAlterar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/pesquisar.gif"))); // NOI18N
        btAlterar6.setText("Pesquisar");
        btAlterar6.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAlterar6MousePressed(evt);
            }
        });
        btAlterar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar6ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Pesquisar pelo nome do produto");

        tfPesquisarPeloNomeProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfPesquisarPeloNomeProduto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfPesquisarPeloNomeProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfPesquisarPeloNomeProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfPesquisarPeloNomeProdutoMouseExited(evt);
            }
        });
        tfPesquisarPeloNomeProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisarPeloNomeProdutoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisarPeloNomeProdutoKeyReleased(evt);
            }
        });

        cbEventos1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbEventos1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PRODUTOS COM DISPONIBILIDADE BAIXA", "DISPONIBILIDADE BAIXA TOP350/2000/MIX", "TOP350/200/MIX 'ZERO'", "PRODUTOS LOJA TOP 350", "PRODUTOS LOJA TOP 2000", "PRODUTOS LOJA MIX", "TOP 350 DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD", "TOP 2000 DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD", "MIX DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD", "PRODUTOS ZERO NA LOJA E NO CD TOP 350", "PRODUTOS ZERO NA LOJA E NO CD TOP 2000", "PRODUTOS ZERO NA LOJA E NO CD MIX", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 350", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 2000", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO MIX", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 350 SEM PEDIDO (ATIVO)", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 2000 SEM PEDIDO (ATIVO)", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP MIX SEM PEDIDO (ATIVO)", "TODOS OS PRODUTOS ", "PRODUTOS TOP 350 RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO ", "PRODUTOS TOP 2000 RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO", "PRODUTOS MIX RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO", "PRODUTOS QUE ENTRARAM NO CD ONTEM", "PRODUTOS QUE ENTRARAM NO CD ONTEM E NÃO TEM PEDIDO ( 7 DIAS ATIVOS )", "PRODUTOS QUE ENTRARAM NO CD ONTEM E TEM PEDIDO ( 7 DIAS ATIVOS )", "PRODUTOS QUE DISPARARAM NO RA", "IDENTIFICA NA TABELA OS PRODUTOS COM OPORTUNIDADES" }));
        cbEventos1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        cbEventos1.setPreferredSize(new java.awt.Dimension(225, 25));
        cbEventos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEventos1ActionPerformed(evt);
            }
        });

        lbFiltro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/filtro2.png"))); // NOI18N
        lbFiltro1.setToolTipText("FILTRAR COLUNA - PELO ITEM SELECIONADO");
        lbFiltro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltro1MousePressed(evt);
            }
        });

        lbFiltro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/filtro2reverso.png"))); // NOI18N
        lbFiltro2.setToolTipText("EXCLUIR TODOS DA COLUNA = ITEM SELECIONADO");
        lbFiltro2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltro2MousePressed(evt);
            }
        });

        jLabel8.setText("MRP");
        jLabel8.setToolTipText("Pesquisar Pelo MRP");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        lbExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/exluir.gif"))); // NOI18N
        lbExcluir.setToolTipText("Excluir Coluna");
        lbExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbExcluirMousePressed(evt);
            }
        });

        lbFiltro3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/plancheta3.png"))); // NOI18N
        lbFiltro3.setToolTipText("DIRETO LOJA - ANALISAR PEDIDOS ATENDIDOS DO DIA ANTERIOR");
        lbFiltro3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltro3MousePressed(evt);
            }
        });

        lbFiltro4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/bt_marcador.gif"))); // NOI18N
        lbFiltro4.setToolTipText("RELATÓRIOS");
        lbFiltro4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltro4MousePressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PRODUTOS", "CLASSE", "OP.IDENTIFICADA", "DISPONIBILIDADE", "SETOR + OP.IDENTF.", "PEDIDO PENDENTE" }));

        jLabel42.setText("LAYOUT");
        jLabel42.setToolTipText("Pesquisar Pelo MRP");
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel42MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbFiltro2)
                        .addGap(18, 18, 18)
                        .addComponent(lbFiltro1))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEventos1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfPesquisarPeloNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btAlterar6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, 1, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFiltro3)
                            .addComponent(lbFiltro4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFiltro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lbFiltro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbExcluir)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEventos1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFiltro3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel42))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFiltro4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfPesquisarPeloNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btAlterar6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btAlterar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/pesquisar.gif"))); // NOI18N
        btAlterar8.setText("Lista SAP");
        btAlterar8.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAlterar8MousePressed(evt);
            }
        });
        btAlterar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar8ActionPerformed(evt);
            }
        });

        tfSapAnalise.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfSapAnalise.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfSapAnalise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfSapAnaliseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfSapAnaliseMouseExited(evt);
            }
        });
        tfSapAnalise.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSapAnaliseKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("PESQUISAR PELO SAP");
        jLabel19.setToolTipText("LISTAR FORNECEDOR(S) PELO CÓDIGO SAP DO PRODUTO");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel19MousePressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("PESQUISAR PELO EAN");

        tfEanAnalise.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfEanAnalise.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfEanAnalise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfEanAnaliseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfEanAnaliseMouseExited(evt);
            }
        });
        tfEanAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEanAnaliseActionPerformed(evt);
            }
        });
        tfEanAnalise.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEanAnaliseKeyReleased(evt);
            }
        });

        jPanel20.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/star.png"))); // NOI18N
        jLabel20.setToolTipText("SINALIZAR TODOS OS PRODUTOS QUE ESTÃO EM PROMOÇÃO E OS QUE VÃO ENTRAR DA TABELA");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel20MousePressed(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/carrinho.png"))); // NOI18N
        jLabel22.setToolTipText("GERAR SUGESTÃO - BASE DISPONILIDADE PARA 15 DIAS");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel22MousePressed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/novo.gif"))); // NOI18N
        jLabel23.setToolTipText("IMPORTAR LISTA SAP ORDENADA");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel23MousePressed(evt);
            }
        });

        jLabel24.setText("ELENCO");
        jLabel24.setToolTipText("LISTAR ELENCO ");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel24MousePressed(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/104359.png"))); // NOI18N
        jLabel25.setToolTipText("LISTAR TODOS OS PRODUTOS QUE ESTÃO EM PROMOÇÃO E OS QUE VÃO ENTRAR");
        jLabel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel25MousePressed(evt);
            }
        });

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/star2.png"))); // NOI18N
        jLabel26.setToolTipText("SINALIZAR TODOS OS PRODUTOS (QUE AINDA VAI ENTRAR EM PROMOÇÃO) DA TABELA");
        jLabel26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel26MousePressed(evt);
            }
        });

        jLabel27.setText("PO. EXTRA");
        jLabel27.setToolTipText("LISTAR PONTO EXTRA ");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel27MousePressed(evt);
            }
        });

        lbExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/zoom_out.gif"))); // NOI18N
        lbExcluir1.setToolTipText("PROCURAR E EXCLUIR SAP'S DA TABELA");
        lbExcluir1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExcluir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbExcluir1MousePressed(evt);
            }
        });

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/carrinho2.png"))); // NOI18N
        jLabel30.setToolTipText("GERAR SUGESTÃO - 15 DIAS REGULARES / 30 DIAS PROMOCIONAIS ");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel30MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel26))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbExcluir1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(lbExcluir1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tfEanAnalise1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfEanAnalise1.setText("B185");
        tfEanAnalise1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfEanAnalise1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfEanAnalise1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfEanAnalise1MouseExited(evt);
            }
        });
        tfEanAnalise1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEanAnalise1ActionPerformed(evt);
            }
        });
        tfEanAnalise1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEanAnalise1KeyReleased(evt);
            }
        });

        jLabel28.setText("LOJAS");
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel28MousePressed(evt);
            }
        });

        lbExcluir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/ico_tutor.gif"))); // NOI18N
        lbExcluir2.setToolTipText("Listar Produtos Pelo Código do Fornecedor");
        lbExcluir2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExcluir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbExcluir2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpFiltroSorter4Layout = new javax.swing.GroupLayout(jpFiltroSorter4);
        jpFiltroSorter4.setLayout(jpFiltroSorter4Layout);
        jpFiltroSorter4Layout.setHorizontalGroup(
            jpFiltroSorter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter4Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFiltroSorter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpFiltroSorter4Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbExcluir2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpFiltroSorter4Layout.createSequentialGroup()
                        .addComponent(tfEanAnalise1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAlterar8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfSapAnalise, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfEanAnalise)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpFiltroSorter4Layout.setVerticalGroup(
            jpFiltroSorter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter4Layout.createSequentialGroup()
                .addGroup(jpFiltroSorter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpFiltroSorter4Layout.createSequentialGroup()
                        .addGroup(jpFiltroSorter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpFiltroSorter4Layout.createSequentialGroup()
                                .addGroup(jpFiltroSorter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpFiltroSorter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btAlterar8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfEanAnalise1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addGap(1, 1, 1)
                                .addComponent(tfEanAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addGroup(jpFiltroSorter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpFiltroSorter4Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(1, 1, 1)
                                        .addComponent(tfSapAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbExcluir2)))
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpConsultarAnaliseLayout = new javax.swing.GroupLayout(jpConsultarAnalise);
        jpConsultarAnalise.setLayout(jpConsultarAnaliseLayout);
        jpConsultarAnaliseLayout.setHorizontalGroup(
            jpConsultarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultarAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpFiltroSorter4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpConsultarAnaliseLayout.setVerticalGroup(
            jpConsultarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpFiltroSorter4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Consultas ", jpConsultarAnalise);

        jPanel9.setBackground(new java.awt.Color(253, 254, 247));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Cadastro de Análises");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        btAnaliseCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/novo.gif"))); // NOI18N
        btAnaliseCadastrar.setText(" Cadastrar");
        btAnaliseCadastrar.setToolTipText("CADASTRAR UM NOVO EVENTO");
        btAnaliseCadastrar.setPreferredSize(new java.awt.Dimension(97, 27));
        btAnaliseCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAnaliseCadastrarMousePressed(evt);
            }
        });
        btAnaliseCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnaliseCadastrarActionPerformed(evt);
            }
        });

        lbModeloTabelaXLSX1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/modelotabelaxlsx.gif"))); // NOI18N
        lbModeloTabelaXLSX1.setToolTipText("MODELO DE TABELA PARA IMPORTAÇÃO DO EXCEL");
        lbModeloTabelaXLSX1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbModeloTabelaXLSX1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbModeloTabelaXLSX1MousePressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(253, 254, 247));

        lbImportacao1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbImportacao1.setForeground(new java.awt.Color(0, 102, 0));
        lbImportacao1.setText("IMPORTAR TB  ");
        lbImportacao1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbImportacao1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbImportacao1MousePressed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/xls.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImportacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImportacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbAnaliseCadastro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbAnaliseCadastro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ZRIS ALTERAR MRP UBMPEDIDO", "RWBE - ESTOQUE CD 184", "RWBE - ESTOQUE CD 001", "RWBE - ESTOQUE CD 289", "MB52 - ESTOQUE LOJA", "ME80FN - PEDIDOS PENDENTES", "RA DIÁRIO - ME80FN", "MB51 - 101 - PRODUTOS ENTRARAM CD 184 DIA ANTERIOR", "SUPLY CHAIN", "CADASTRAR PRODUTO - SAP - UND - NOME", "LOF", "ATIVO SEM VENDA - MC46", "ZVTRA_MRP", "EAN ZVTRA_SORTIMENTO", "FORNECEDORES", "INVENTARIOMB52", "INVENTARIOZLMR06", "REL_MB52", "REL_ZDADOSLOG_CD", "REL_ZDADOSLOG_LOJA", "REL_ZVTRA_SORTIMENTO", "EVENTO_QTD_POR_EMBALAGEM", "EVENTO_VENDA_MES_ANTERIOR", "EVENTO_VENDA_ANO_ANTERIOR", "Q.EMB.E/S", "PRODUTO POR FORNECEDOR", "PRODUTO POR FORNECEDOR_CD", "EAN/GRUPO", "PLANILHA_RA_INTRANET", "PLANILHA_RA_INTRANET_ALTERADO", "PLANILHA_RA_INTRANET_ME80FN", "DESCRICÃO DO SETOR", "DESCRICÃO DO GRUPO COMPRA", "OPORTUNIDADESEMANA", "ENDERECOESPACOGONDULA", "ULTIMA_ENTRADA", "ELENCO", "PONTOEXTRA", "CORINGA", "OPORTUNIDADE IDENTIFICADA", "QUANTIDADE_VENDIDA", "DIRETO LOJA INPUT ZRIS", "DIRETO LOJA PEDIDO GERADO" }));
        cbAnaliseCadastro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        cbAnaliseCadastro.setPreferredSize(new java.awt.Dimension(225, 30));
        cbAnaliseCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAnaliseCadastroActionPerformed(evt);
            }
        });

        jPanel19.setBackground(new java.awt.Color(253, 254, 247));

        lbImportacao2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbImportacao2.setForeground(new java.awt.Color(0, 102, 0));
        lbImportacao2.setText("IMPORTAR TXT  ");
        lbImportacao2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbImportacao2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbImportacao2MousePressed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/android_24.png"))); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbImportacao2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImportacao2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel17))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btAnaliseCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(62, 62, 62)
                            .addComponent(lbModeloTabelaXLSX1))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbAnaliseCadastro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbModeloTabelaXLSX1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAnaliseCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbAnaliseCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        btExcluirParametros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/exluir.gif"))); // NOI18N
        btExcluirParametros.setText("Excluir");
        btExcluirParametros.setToolTipText("LISTAR TODOS OS PRODUTOS DO EVENTO SELECIONADO");
        btExcluirParametros.setPreferredSize(new java.awt.Dimension(142, 27));
        btExcluirParametros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExcluirParametrosMousePressed(evt);
            }
        });
        btExcluirParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirParametrosActionPerformed(evt);
            }
        });

        cbAnaliseParametros.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbAnaliseParametros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ESTOQUE CD 184", "ESTOQUE CD 001", "ESTOQUE CD 289", "ESTOQUE LOJA", "ME80FN - PEDIDOS PENDENTES", "PRODUTOS ENT. CD ONTEM", "RA DIÁRIO - ME80FN", "ATIVO SEM VENDA - MC43", "ENDERECOESPACOGONDULA", "TODOS PARÂMETROS", "ZRIS MRP UMB DISPONIBILIDADE", "TODOS  PRODUTOS", "FORNECEDORES", "DESCRICÃO DO GRUPO COMPRA", "ZVTRA_MRP", "SUPLY CHAIN", "PRODUTO POR FORNECEDOR", "PRODUTO POR FORNECEDOR_CD", "OPIDENTIFICADA", "ESTOQUE LOJA + DADOS ZRIS", "DIRETO LOJA ZRIZ_PEDIDO" }));
        cbAnaliseParametros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        cbAnaliseParametros.setPreferredSize(new java.awt.Dimension(225, 30));
        cbAnaliseParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAnaliseParametrosActionPerformed(evt);
            }
        });

        lbModeloTabelaXLSX2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/clndr.gif"))); // NOI18N
        lbModeloTabelaXLSX2.setToolTipText("DATA PADRÃO ELENCO");
        lbModeloTabelaXLSX2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbModeloTabelaXLSX2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbModeloTabelaXLSX2MousePressed(evt);
            }
        });

        lbModeloTabelaXLSX3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/linux_24.png"))); // NOI18N
        lbModeloTabelaXLSX3.setToolTipText("CADASTRAR PRODUTO");
        lbModeloTabelaXLSX3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbModeloTabelaXLSX3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbModeloTabelaXLSX3MousePressed(evt);
            }
        });

        jLabel43.setText("CLIPBOARD");
        jLabel43.setToolTipText("COPIAR DA MEMORIA");
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel43MousePressed(evt);
            }
        });

        lbModeloTabelaXLSX4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/view_detail.gif"))); // NOI18N
        lbModeloTabelaXLSX4.setToolTipText("DIRETO LOF - DEIXAR APENAS LOF DA AGENDA");
        lbModeloTabelaXLSX4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbModeloTabelaXLSX4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbModeloTabelaXLSX4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbAnaliseParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcluirParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbModeloTabelaXLSX4)
                .addGap(27, 27, 27)
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addComponent(lbModeloTabelaXLSX3)
                .addGap(18, 18, 18)
                .addComponent(lbModeloTabelaXLSX2)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbModeloTabelaXLSX2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbModeloTabelaXLSX3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbModeloTabelaXLSX4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbAnaliseParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jpFiltroSorter3Layout = new javax.swing.GroupLayout(jpFiltroSorter3);
        jpFiltroSorter3.setLayout(jpFiltroSorter3Layout);
        jpFiltroSorter3Layout.setHorizontalGroup(
            jpFiltroSorter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpFiltroSorter3Layout.setVerticalGroup(
            jpFiltroSorter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter3Layout.createSequentialGroup()
                .addGroup(jpFiltroSorter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpPermissoes3Layout = new javax.swing.GroupLayout(jpPermissoes3);
        jpPermissoes3.setLayout(jpPermissoes3Layout);
        jpPermissoes3Layout.setHorizontalGroup(
            jpPermissoes3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermissoes3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpFiltroSorter3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jpPermissoes3Layout.setVerticalGroup(
            jpPermissoes3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpFiltroSorter3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Cadastros", jpPermissoes3);

        jPanel23.setBackground(new java.awt.Color(253, 254, 247));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Inventário Análises");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        btAnaliseCadastrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/icon.png"))); // NOI18N
        btAnaliseCadastrar1.setText("Resultado Geral");
        btAnaliseCadastrar1.setToolTipText("CADASTRAR UM NOVO EVENTO");
        btAnaliseCadastrar1.setPreferredSize(new java.awt.Dimension(97, 27));
        btAnaliseCadastrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAnaliseCadastrar1MousePressed(evt);
            }
        });
        btAnaliseCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnaliseCadastrar1ActionPerformed(evt);
            }
        });

        jPanel24.setBackground(new java.awt.Color(253, 254, 247));

        lbImportacao3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbImportacao3.setForeground(new java.awt.Color(0, 102, 0));
        lbImportacao3.setText("Análise Parcial");
        lbImportacao3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbImportacao3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbImportacao3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lbImportacao3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(lbImportacao3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbAnaliseCadastro1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbAnaliseCadastro1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PRODUTOS CONTADOS", "PRODUTOS NÃO CONTADOS", "PRODUTOS CONTADOS E NÃO CONTADOS", "PRODUTOS COM DIFERENÇA POSITIVA", "PRODUTOS COM DIFERENÇA NEGATIVA", "PESQUISAR PELO VALOR  DA DIFERENÇA" }));
        cbAnaliseCadastro1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        cbAnaliseCadastro1.setPreferredSize(new java.awt.Dimension(225, 30));
        cbAnaliseCadastro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAnaliseCadastro1ActionPerformed(evt);
            }
        });

        tfSapAnalise1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfSapAnalise1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfSapAnalise1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfSapAnalise1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfSapAnalise1MouseExited(evt);
            }
        });
        tfSapAnalise1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSapAnalise1KeyReleased(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel41.setText("INCLUIR VALOR");
        jLabel41.setToolTipText("LISTAR FORNECEDOR(S) PELO CÓDIGO SAP DO PRODUTO");
        jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel41MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btAnaliseCadastrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfSapAnalise1)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbAnaliseCadastro1, 0, 228, Short.MAX_VALUE)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAnaliseCadastrar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel41)
                .addGap(1, 1, 1)
                .addComponent(tfSapAnalise1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbAnaliseCadastro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        btExcluirParametros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/pesquisar.gif"))); // NOI18N
        btExcluirParametros1.setText("Pesquisar");
        btExcluirParametros1.setToolTipText("LISTAR TODOS OS PRODUTOS DO EVENTO SELECIONADO");
        btExcluirParametros1.setPreferredSize(new java.awt.Dimension(142, 27));
        btExcluirParametros1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExcluirParametros1MousePressed(evt);
            }
        });
        btExcluirParametros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirParametros1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btExcluirParametros1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btExcluirParametros1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jpFiltroSorter5Layout = new javax.swing.GroupLayout(jpFiltroSorter5);
        jpFiltroSorter5.setLayout(jpFiltroSorter5Layout);
        jpFiltroSorter5Layout.setHorizontalGroup(
            jpFiltroSorter5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jpFiltroSorter5Layout.setVerticalGroup(
            jpFiltroSorter5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter5Layout.createSequentialGroup()
                .addGroup(jpFiltroSorter5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tfNegativo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfNegativo.setPreferredSize(new java.awt.Dimension(59, 23));

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Valor Total Negativo:");

        tfPositivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfPositivo.setPreferredSize(new java.awt.Dimension(59, 23));

        tfGeral.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfGeral.setPreferredSize(new java.awt.Dimension(59, 23));

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Valor Total Geral:");

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Valor Total Positivo:");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNegativo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfPositivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfGeral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNegativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfPositivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel25Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel37, jLabel39, jLabel40, tfGeral, tfNegativo, tfPositivo});

        javax.swing.GroupLayout jpInventarioLayout = new javax.swing.GroupLayout(jpInventario);
        jpInventario.setLayout(jpInventarioLayout);
        jpInventarioLayout.setHorizontalGroup(
            jpInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpFiltroSorter5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpInventarioLayout.setVerticalGroup(
            jpInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpFiltroSorter5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Inventario", jpInventario);

        javax.swing.GroupLayout jpPermissoes1Layout = new javax.swing.GroupLayout(jpPermissoes1);
        jpPermissoes1.setLayout(jpPermissoes1Layout);
        jpPermissoes1Layout.setHorizontalGroup(
            jpPermissoes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermissoes1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jpPermissoes1Layout.setVerticalGroup(
            jpPermissoes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Análises", jpPermissoes1);

        jPanel3.setBackground(new java.awt.Color(253, 254, 247));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Modo de Seleção");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
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
                .addContainerGap(414, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btVisualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/printv.png"))); // NOI18N
        btVisualizar1.setText("Print");
        btVisualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizar1ActionPerformed(evt);
            }
        });

        btVisualizar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/printv.png"))); // NOI18N
        btVisualizar2.setText("Visualizar");
        btVisualizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizar2ActionPerformed(evt);
            }
        });

        btVisualizar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/printv2.png"))); // NOI18N
        btVisualizar3.setText("Print JMl");
        btVisualizar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizar3ActionPerformed(evt);
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
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPermissoesLayout.createSequentialGroup()
                .addContainerGap(307, Short.MAX_VALUE)
                .addComponent(btVisualizar4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btVisualizar3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btVisualizar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btVisualizar1)
                .addGap(79, 79, 79))
        );
        jpPermissoesLayout.setVerticalGroup(
            jpPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermissoesLayout.createSequentialGroup()
                .addGroup(jpPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btVisualizar1)
                    .addComponent(btVisualizar2)
                    .addComponent(btVisualizar3)
                    .addComponent(btVisualizar4))
                .addGap(1, 1, 1)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tabela", jpPermissoes);

        jPanel8.setBackground(new java.awt.Color(253, 254, 247));
        jPanel8.setPreferredSize(new java.awt.Dimension(313, 26));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("CONSULTAR TODOS OS PRODUTOS EM PROMOÇÃO");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel12MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        btAlterar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/pesquisar.gif"))); // NOI18N
        btAlterar4.setText("Pesquisar");
        btAlterar4.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAlterar4MousePressed(evt);
            }
        });
        btAlterar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar4ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Pesquisar pelo nome do produto");

        tfNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfNomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfNomeMouseExited(evt);
            }
        });
        tfNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNomeKeyReleased(evt);
            }
        });

        cbPesquisarPor2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbPesquisarPor2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PESQUISAR TODOS EVENTOS" }));
        cbPesquisarPor2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        cbPesquisarPor2.setPreferredSize(new java.awt.Dimension(225, 25));
        cbPesquisarPor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPesquisarPor2ActionPerformed(evt);
            }
        });

        cbEventos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbEventos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        cbEventos.setPreferredSize(new java.awt.Dimension(225, 25));
        cbEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEventosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btAlterar4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbPesquisarPor2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbEventos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbPesquisarPor2, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel14)
                .addGap(3, 3, 3)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAlterar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbPesquisarPor2, jPanel8});

        btAlterar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/pesquisar.gif"))); // NOI18N
        btAlterar5.setText("Lista de SAP");
        btAlterar5.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAlterar5MousePressed(evt);
            }
        });
        btAlterar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar5ActionPerformed(evt);
            }
        });

        tfSap.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfSap.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfSap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfSapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfSapMouseExited(evt);
            }
        });
        tfSap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSapKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("PESQUISAR PELO SAP");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/pesquisar.gif"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });

        lbFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/filtro2.png"))); // NOI18N
        lbFiltro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltroMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpFiltroSorter2Layout = new javax.swing.GroupLayout(jpFiltroSorter2);
        jpFiltroSorter2.setLayout(jpFiltroSorter2Layout);
        jpFiltroSorter2Layout.setHorizontalGroup(
            jpFiltroSorter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter2Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFiltroSorter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpFiltroSorter2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbFiltro))
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(btAlterar5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfSap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jpFiltroSorter2Layout.setVerticalGroup(
            jpFiltroSorter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter2Layout.createSequentialGroup()
                .addGroup(jpFiltroSorter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpFiltroSorter2Layout.createSequentialGroup()
                        .addComponent(btAlterar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpFiltroSorter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addGap(3, 3, 3)
                        .addGroup(jpFiltroSorter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfSap, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpFiltroSorter2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel11, tfSap});

        javax.swing.GroupLayout jpConsultaLayout = new javax.swing.GroupLayout(jpConsulta);
        jpConsulta.setLayout(jpConsultaLayout);
        jpConsultaLayout.setHorizontalGroup(
            jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpFiltroSorter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpConsultaLayout.setVerticalGroup(
            jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jpFiltroSorter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consultar Eventos", jpConsulta);

        jPanel7.setBackground(new java.awt.Color(253, 254, 247));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Cadastrar evento da Tabela ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        btAlterar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/novo.gif"))); // NOI18N
        btAlterar2.setText(" Cadastrar");
        btAlterar2.setToolTipText("CADASTRAR UM NOVO EVENTO");
        btAlterar2.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAlterar2MousePressed(evt);
            }
        });
        btAlterar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar2ActionPerformed(evt);
            }
        });

        tfData_Inicio_Atividades.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        try {
            tfData_Inicio_Atividades.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfData_Inicio_Atividades.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfData_Inicio_Atividades.setPreferredSize(new java.awt.Dimension(185, 24));
        tfData_Inicio_Atividades.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfData_Inicio_AtividadesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfData_Inicio_AtividadesFocusLost(evt);
            }
        });
        tfData_Inicio_Atividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfData_Inicio_AtividadesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfData_Inicio_AtividadesMouseExited(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel46.setText("Data Início do Evento");

        jLabel47.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel47.setText("Data Fim do Evento");

        tfData_Inicio_Atividades1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        try {
            tfData_Inicio_Atividades1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfData_Inicio_Atividades1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfData_Inicio_Atividades1.setPreferredSize(new java.awt.Dimension(185, 24));
        tfData_Inicio_Atividades1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfData_Inicio_Atividades1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfData_Inicio_Atividades1FocusLost(evt);
            }
        });
        tfData_Inicio_Atividades1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfData_Inicio_Atividades1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfData_Inicio_Atividades1MouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Nome do Evento");

        tfNome_Fantasia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome_Fantasia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfNome_Fantasia.setPreferredSize(new java.awt.Dimension(247, 23));
        tfNome_Fantasia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfNome_FantasiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfNome_FantasiaMouseExited(evt);
            }
        });
        tfNome_Fantasia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNome_FantasiaKeyReleased(evt);
            }
        });

        cbPesquisarPor1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbPesquisarPor1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO DE EVENTO", "TABLOIDE", "LAMINA", "VT", "APOSTA" }));
        cbPesquisarPor1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        cbPesquisarPor1.setPreferredSize(new java.awt.Dimension(225, 30));

        lbModeloTabelaXLSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/modelotabelaxlsx.gif"))); // NOI18N
        lbModeloTabelaXLSX.setToolTipText("MODELO DE TABELA PARA IMPORTAÇÃO DO EXCEL");
        lbModeloTabelaXLSX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbModeloTabelaXLSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbModeloTabelaXLSXMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btAlterar2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbModeloTabelaXLSX))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfData_Inicio_Atividades1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfData_Inicio_Atividades, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNome_Fantasia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPesquisarPor1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbModeloTabelaXLSX, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAlterar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(2, 2, 2)
                        .addComponent(tfData_Inicio_Atividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(2, 2, 2)
                        .addComponent(tfNome_Fantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(1, 1, 1)
                        .addComponent(tfData_Inicio_Atividades1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbPesquisarPor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btExcluirEventoGB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/exluir.gif"))); // NOI18N
        btExcluirEventoGB.setText("Excluir Evento");
        btExcluirEventoGB.setToolTipText("EXCLUIR EVENTO SELECIONADO");
        btExcluirEventoGB.setPreferredSize(new java.awt.Dimension(97, 27));
        btExcluirEventoGB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExcluirEventoGBMousePressed(evt);
            }
        });
        btExcluirEventoGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirEventoGBActionPerformed(evt);
            }
        });

        btAlterar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/pesquisar.gif"))); // NOI18N
        btAlterar3.setText("Listar Eventos");
        btAlterar3.setToolTipText("LISTAR TODOS OS EVENTOS CADASTRADOS");
        btAlterar3.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAlterar3MousePressed(evt);
            }
        });
        btAlterar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar3ActionPerformed(evt);
            }
        });

        btExcluirEventoGB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/olho.png"))); // NOI18N
        btExcluirEventoGB1.setText("Listar Produtos");
        btExcluirEventoGB1.setToolTipText("LISTAR TODOS OS PRODUTOS DO EVENTO SELECIONADO");
        btExcluirEventoGB1.setPreferredSize(new java.awt.Dimension(97, 27));
        btExcluirEventoGB1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExcluirEventoGB1MousePressed(evt);
            }
        });
        btExcluirEventoGB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirEventoGB1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btExcluirEventoGB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAlterar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluirEventoGB1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(btAlterar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirEventoGB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirEventoGB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpFiltroSorter1Layout = new javax.swing.GroupLayout(jpFiltroSorter1);
        jpFiltroSorter1.setLayout(jpFiltroSorter1Layout);
        jpFiltroSorter1Layout.setHorizontalGroup(
            jpFiltroSorter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpFiltroSorter1Layout.setVerticalGroup(
            jpFiltroSorter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroSorter1Layout.createSequentialGroup()
                .addGroup(jpFiltroSorter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpFiltroSorter1Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpRelatorio.setBackground(new java.awt.Color(253, 254, 247));

        btExcluirEventoGB2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/novo.gif"))); // NOI18N
        btExcluirEventoGB2.setText("Cadastrar Venda Ev.");
        btExcluirEventoGB2.setToolTipText("EXCLUIR EVENTO SELECIONADO");
        btExcluirEventoGB2.setPreferredSize(new java.awt.Dimension(97, 27));
        btExcluirEventoGB2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExcluirEventoGB2MousePressed(evt);
            }
        });
        btExcluirEventoGB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirEventoGB2ActionPerformed(evt);
            }
        });

        btAlterar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/novo.gif"))); // NOI18N
        btAlterar7.setText("Cadastrar Devolutiva");
        btAlterar7.setToolTipText("LISTAR TODOS OS EVENTOS CADASTRADOS");
        btAlterar7.setPreferredSize(new java.awt.Dimension(97, 27));
        btAlterar7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btAlterar7MousePressed(evt);
            }
        });
        btAlterar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar7ActionPerformed(evt);
            }
        });

        btExcluirEventoGB3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/sql.png"))); // NOI18N
        btExcluirEventoGB3.setText("Gerar Relatório XLS");
        btExcluirEventoGB3.setToolTipText("LISTAR TODOS OS PRODUTOS DO EVENTO SELECIONADO");
        btExcluirEventoGB3.setPreferredSize(new java.awt.Dimension(97, 27));
        btExcluirEventoGB3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExcluirEventoGB3MousePressed(evt);
            }
        });
        btExcluirEventoGB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirEventoGB3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpRelatorioLayout = new javax.swing.GroupLayout(jpRelatorio);
        jpRelatorio.setLayout(jpRelatorioLayout);
        jpRelatorioLayout.setHorizontalGroup(
            jpRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btExcluirEventoGB2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(btAlterar7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluirEventoGB3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpRelatorioLayout.setVerticalGroup(
            jpRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRelatorioLayout.createSequentialGroup()
                .addComponent(btAlterar7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirEventoGB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirEventoGB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpPermissoes2Layout = new javax.swing.GroupLayout(jpPermissoes2);
        jpPermissoes2.setLayout(jpPermissoes2Layout);
        jpPermissoes2Layout.setHorizontalGroup(
            jpPermissoes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermissoes2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpFiltroSorter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jpPermissoes2Layout.setVerticalGroup(
            jpPermissoes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermissoes2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jpPermissoes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPermissoes2Layout.createSequentialGroup()
                        .addComponent(jpRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jpFiltroSorter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cadastrar Eventos", jpPermissoes2);

        javax.swing.GroupLayout jpAvancadoLayout = new javax.swing.GroupLayout(jpAvancado);
        jpAvancado.setLayout(jpAvancadoLayout);
        jpAvancadoLayout.setHorizontalGroup(
            jpAvancadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAvancadoLayout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jpAvancadoLayout.setVerticalGroup(
            jpAvancadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tfDde.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfDde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDde.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("DDE");

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setText("TOP350");

        tfTop350.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfTop350.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTop350.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setText("TOP2000");

        tfTop2000.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfTop2000.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTop2000.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));

        jLabel33.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel33.setText("MIX");

        tfMix.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfMix.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfMix.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel34.setText("DISP. TOTAL");

        tfTotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));

        jLabel35.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/salvar.gif"))); // NOI18N
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel35MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpAvancado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpBasico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbDefinirPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tfDde, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTop350, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTop2000, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMix, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tfDde, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfTop350, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfTop2000, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfMix, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpBasico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chbDefinirPermissoes)
                .addGap(0, 0, 0)
                .addComponent(jpAvancado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyReleased

    }//GEN-LAST:event_tfPesquisaKeyReleased

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            if( btAlterar.isEnabled() ) { 
                
                
            }
        } catch( Exception e ){  } } }.start();       
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            if( btVisualizar.isEnabled() ) { 
                
                ImprimirJTable ImprimirJTable = new ImprimirJTable( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa );
            }
        } catch( Exception e ){      } } }.start(); 
    }//GEN-LAST:event_btVisualizarActionPerformed

    private void btSelecionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSelecionarMousePressed
          
    }//GEN-LAST:event_btSelecionarMousePressed

    private void btSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSelecionarActionPerformed

    private void lbExportacaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExportacaoMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
            if( lbImportacao.isEnabled() == true ) { lbImportacao.setEnabled(false);
                
                JFileChooserJMPasta JFileChooserJMPasta = new JFileChooserJMPasta( "  *.xls/xlsx    -   jmarysystems.blogspot.com.br", new String [] { "XLS" , "XLSX" } );
                String strdevolvida = JFileChooserJMPasta.getString( 2 );
                
                tfPesquisa.setText( strdevolvida );
                
                String exportacao = strdevolvida + "\\" + "Arquivo_Exportado" + ".xls";                
                File f  = new File( exportacao );
                
                File falt = null;              // dinâmico
                int quantidadeDeArquivos = 0;  // dinâmico
                do{ Thread.sleep( 100 ); 
                    if( falt != null ){ f = falt; }
                    
                    if ( !f.exists() ) { 
                        
                        tfPesquisa.setText( f.getCanonicalPath().replace("\\", "/") );
                        Tabela_Pesquisa_BD_Estabelecimento.pesquisaExportacaoExcel( f.getCanonicalPath().replace("\\", "/") );
                        break;
                    }
                    else if ( f.exists() ) {
                        quantidadeDeArquivos++;
                        falt = new File( strdevolvida + "\\" + quantidadeDeArquivos+f.getName() );
                        System.out.println( "f.exists() - "+ strdevolvida + "\\" + quantidadeDeArquivos+f.getName() );
                    }
                    
                }
                while ( f.exists() );                              
                
                lbImportacao.setEnabled(true);
            }             
        } catch( Exception e ){ lbImportacao.setEnabled(true); } } }.start();
    }//GEN-LAST:event_lbExportacaoMousePressed
    
    private void lbImportacaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImportacaoMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
            if( lbImportacao.isEnabled() == true ) { lbImportacao.setEnabled(false);
                
                JFileChooserJM JFileChooserJM = new JFileChooserJM( "  imagens    -   jmarysystems.blogspot.com.br", new String [] { "XLS" , "XLSX" } );
                String strdevolvida = JFileChooserJM.getString( 2 );
                
                tfPesquisa.setText( strdevolvida );
                
                Tabela_Pesquisa_BD_Estabelecimento.pesquisaImportacaoExcel(strdevolvida);
                
                lbImportacao.setEnabled(true);
            }             
        } catch( Exception e ){ lbImportacao.setEnabled(true); } } }.start();
    }//GEN-LAST:event_lbImportacaoMousePressed

    private void chbDefinirPermissoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chbDefinirPermissoesMousePressed
        configurarModoAvancado();
    }//GEN-LAST:event_chbDefinirPermissoesMousePressed

    private void chColunaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chColunaMousePressed
        chLinha.setSelected(false);
        Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Row Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
    }//GEN-LAST:event_chColunaMousePressed

    private void chCelulaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chCelulaMousePressed
        if( chCelula.isEnabled() ){
            
            if( chCelula.isSelected() == false ){
                
                Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Cell Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
            }
            else if( chLinha.isSelected() ){
                
                Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Row Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
            }
            
            else if( chColuna.isSelected() ){
                
                Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Column Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
            }
                        
        }
    }//GEN-LAST:event_chCelulaMousePressed

    private void chLinhaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chLinhaMousePressed
        chColuna.setSelected(false);
        Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Column Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
    }//GEN-LAST:event_chLinhaMousePressed

    private void chColunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chColunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chColunaActionPerformed

    private void rbUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbUnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbUnicoActionPerformed

    private void rbMultiploMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMultiploMousePressed
        if( rbMultiplo.isSelected() == true ){

            chCelula.setEnabled( true );
                        
            rbUnicoComIntervalo.setSelected( false );
            
            rbUnico            .setSelected( true );
            Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Multiple Interval Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
        else{
            
            chCelula           .setEnabled( false );
            chCelula           .setSelected( false );
            
            rbUnico            .setSelected( false );
            rbUnicoComIntervalo.setSelected( false );
            
            Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Multiple Interval Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
    }//GEN-LAST:event_rbMultiploMousePressed

    private void rbUnicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbUnicoMousePressed
        if( rbUnico.isSelected() == true ){
                        
            chCelula.setEnabled( true );
            
            rbMultiplo         .setSelected( false );
            
            rbUnicoComIntervalo.setSelected( true );
            Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Single Interval Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
        else{
            
            chCelula.setEnabled ( true );
            
            rbUnicoComIntervalo.setSelected( false );
            rbMultiplo         .setSelected( false );
            
            Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Single Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }    
        
    }//GEN-LAST:event_rbUnicoMousePressed

    private void rbUnicoComIntervaloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbUnicoComIntervaloMousePressed
        if( rbUnicoComIntervalo.isSelected() == true ){
                        
            chCelula.setEnabled( true );
                        
            rbMultiplo         .setSelected( false );
            
            rbUnico            .setSelected( true );
            Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Single Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
        else{
            
            chCelula.setEnabled ( true );
            
            rbUnico            .setSelected( false );
            rbMultiplo         .setSelected( false );
            
            Tabela_Pesquisa_BD_Estabelecimento.tabelaModoDeSelecao( "Single Interval Selection", 
                    chLinha.isSelected(), chColuna.isSelected(), chCelula.isSelected() );
        }
    }//GEN-LAST:event_rbUnicoComIntervaloMousePressed

    private void rbMultiploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMultiploActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbMultiploActionPerformed

    private void btAlterar2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAlterar2MousePressed
        try{ 
                
            /*DATA INICIO*//////////////////////////////////////////////////////
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            String dataDigitadaNoTF = tfData_Inicio_Atividades.getText().trim();
                     
            Date date_inicio = formatter.parse( dataDigitadaNoTF ); 
                       
            //Estabelecimento.setDataInscJuntaComercial( date_inicio ); 
            /*DATA INICIO*//////////////////////////////////////////////////////
            
            /*DATA FIM*/////////////////////////////////////////////////////////
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            
            String dataDigitadaNoTF2 = tfData_Inicio_Atividades1.getText().trim();
                     
            Date date_fim = formatter2.parse( dataDigitadaNoTF2 ); 
                       
            //Estabelecimento.setDataInscJuntaComercial( date_fim ); 
            /*DATA FIM*/////////////////////////////////////////////////////////
            
            // Se a data inicial for menor que a data final
            String strTipoDeEvento = cbPesquisarPor1.getSelectedItem().toString().trim();
            if( !strTipoDeEvento.equals( "TIPO DE EVENTO" ) && !strTipoDeEvento.equals( "" ) ){
            
                if( date_inicio.before(date_fim) ){
                
                    if( !tfNome_Fantasia.getText().trim().equals("") ){
                    
                        listarTabela( date_inicio, date_fim, strTipoDeEvento, new Evento() );                         
                    
                    }
                    else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "CADASTRAR EVENTO PROMOCIONAL\n"
                            + "\nStatus Do CADASTRO:"
                            + "\nInforme o nome do evento\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                    }
                }
                else{
                
                    Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "CADASTRAR EVENTO PROMOCIONAL\n"
                            + "\nStatus Do CADASTRO:"
                            + "\nA data INICIAL do evento não pode ser menor a data FINAL\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            }
            else{
                
                Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "CADASTRAR EVENTO PROMOCIONAL\n"
                            + "\nStatus Do CADASTRO:"
                            + "\nInforme o tipo do evento\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
        }catch(Exception e ){  
            
            Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "CADASTRAR EVENTO PROMOCIONAL\n"
                            + "\nStatus Do CADASTRO:"
                            + "\nInforme uma data INICIAL e FINAL\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        } 
    }//GEN-LAST:event_btAlterar2MousePressed

    private void btAlterar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterar2ActionPerformed

    private void tfData_Inicio_AtividadesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfData_Inicio_AtividadesFocusGained
        String txtftm = tfData_Inicio_Atividades.getText().trim();
        //System.out.println( "vvvvvv  " + txtftm );
        Formatar_Data F_D = new Formatar_Data( tfData_Inicio_Atividades, txtftm, true );
    }//GEN-LAST:event_tfData_Inicio_AtividadesFocusGained

    private void tfData_Inicio_AtividadesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfData_Inicio_AtividadesFocusLost
        String txtftm = tfData_Inicio_Atividades.getText().trim();
        Formatar_Data F_D = new Formatar_Data( tfData_Inicio_Atividades, txtftm, false );
    }//GEN-LAST:event_tfData_Inicio_AtividadesFocusLost

    private void tfData_Inicio_AtividadesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfData_Inicio_AtividadesMouseEntered
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfData_Inicio_Atividades, true );
    }//GEN-LAST:event_tfData_Inicio_AtividadesMouseEntered

    private void tfData_Inicio_AtividadesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfData_Inicio_AtividadesMouseExited
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfData_Inicio_Atividades, false );
    }//GEN-LAST:event_tfData_Inicio_AtividadesMouseExited

    private void tfData_Inicio_Atividades1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfData_Inicio_Atividades1FocusGained
        String txtftm = tfData_Inicio_Atividades1.getText().trim();
        //System.out.println( "vvvvvv  " + txtftm );
        Formatar_Data F_D = new Formatar_Data( tfData_Inicio_Atividades1, txtftm, true );
    }//GEN-LAST:event_tfData_Inicio_Atividades1FocusGained

    private void tfData_Inicio_Atividades1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfData_Inicio_Atividades1FocusLost
        String txtftm = tfData_Inicio_Atividades1.getText().trim();
        Formatar_Data F_D = new Formatar_Data( tfData_Inicio_Atividades1, txtftm, false );
    }//GEN-LAST:event_tfData_Inicio_Atividades1FocusLost

    private void tfData_Inicio_Atividades1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfData_Inicio_Atividades1MouseEntered
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfData_Inicio_Atividades1, true );
    }//GEN-LAST:event_tfData_Inicio_Atividades1MouseEntered

    private void tfData_Inicio_Atividades1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfData_Inicio_Atividades1MouseExited
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfData_Inicio_Atividades1, false );
    }//GEN-LAST:event_tfData_Inicio_Atividades1MouseExited

    private void tfNome_FantasiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNome_FantasiaMouseEntered
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfNome_Fantasia, true );
    }//GEN-LAST:event_tfNome_FantasiaMouseEntered

    private void tfNome_FantasiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNome_FantasiaMouseExited
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfNome_Fantasia, false );
    }//GEN-LAST:event_tfNome_FantasiaMouseExited

    private void tfNome_FantasiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNome_FantasiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNome_FantasiaKeyReleased

    private void btAlterar3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAlterar3MousePressed
        Tabela_Pesquisa_BD_Estabelecimento.mostrarLista();
        btExcluirEventoGB.setVisible(true);
        jpRelatorio.setVisible(true);
    }//GEN-LAST:event_btAlterar3MousePressed

    private void btAlterar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterar3ActionPerformed

    private void btExcluirEventoGBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirEventoGBMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
              int response = JOptionPane.showConfirmDialog(null, ""
                      + "**CONFIRMAR EXCLUSÃO**"
                      + "\n"
                      + "Deseja Excluir o EVENTO selecionado?");
              
              if( response == JOptionPane.YES_OPTION){
                  
                  excluirEventoGB();
              }
        } catch( Exception e ){  } } }.start();        
    }//GEN-LAST:event_btExcluirEventoGBMousePressed

    private void btExcluirEventoGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirEventoGBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirEventoGBActionPerformed

    private void btExcluirEventoGB1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirEventoGB1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirEventoGB1MousePressed

    private void btExcluirEventoGB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirEventoGB1ActionPerformed
        
        ////////////////////////////////////////////////////////////////////////
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
            btExcluirEventoGB.setVisible(false); 
            jpRelatorio.setVisible(false);
                    
                    Tabela_Pesquisa_BD_Estabelecimento.mostrarListaEventoprodutos( IDSELECIONADA );
        
                } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        ////////////////////////////////////////////////////////////////////////
        
    }//GEN-LAST:event_btExcluirEventoGB1ActionPerformed

    private void btAlterar4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAlterar4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterar4MousePressed

    private void btAlterar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterar4ActionPerformed

    private void tfNomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNomeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeMouseEntered

    private void tfNomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNomeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeMouseExited

    private void tfNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomeKeyReleased
        try{
            
            if( cbPesquisarPor2.getItemCount() > 0 ) {
                
                String str = cbPesquisarPor2.getSelectedItem().toString().trim();
                
                //System.out.println( "cbPaisActionPerformed" );
                if( str.equals( "SELECIONAR UM EVENTO" ) ){
                    
                    
                }
                else if( str.equals( "PESQUISAR TODOS EVENTOS" ) ){
                    
                    if( tfNome.getText().trim().length() > 2 ){
                        
                        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
                        
                            tfSap.setText("");
                                                        
                            Tabela_Pesquisa_BD_Estabelecimento.mostrarListaSap( tfNome.getText().trim(), "nome", new ArrayList<String>() ); 
                            
                        } catch( Exception e ){ } } }.start();        
                    }
                                      
                }               
            }
        }
        catch( Exception e ){ System.out.println( "cbPesquisarPor2 xxxxxxxxxx" ); e.printStackTrace(); }
    }//GEN-LAST:event_tfNomeKeyReleased

    private void btAlterar5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAlterar5MousePressed
        
        ListaSAP ListaSAP = new ListaSAP( this );
        ListaSAP.setVisible(true);
    }//GEN-LAST:event_btAlterar5MousePressed

    private void btAlterar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterar5ActionPerformed

    private void tfRazao_Social2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRazao_Social2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRazao_Social2MouseEntered

    private void tfRazao_Social2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRazao_Social2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRazao_Social2MouseExited

    private void tfRazao_Social2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRazao_Social2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRazao_Social2KeyReleased

    private void tfSapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSapMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSapMouseEntered

    private void tfSapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSapMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSapMouseExited

    private void tfSapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSapKeyReleased
        /*String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            tfSap.setText("");
        }*/
        
        if( tfSap.getText().trim().length() > 2 ){
            
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
                tfNome.setText("");
            } catch( Exception e ){ } } }.start();        
        }
    }//GEN-LAST:event_tfSapKeyReleased

    private void cbPesquisarPor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPesquisarPor2ActionPerformed
        try{
            
            if( cbPesquisarPor2.getItemCount() > 0 ) {
                
                String str = cbPesquisarPor2.getSelectedItem().toString().trim();
                
                //System.out.println( "cbPaisActionPerformed" );
                if( str.equals( "SELECIONAR UM EVENTO" ) ){
                    
                    cbEventos.setEnabled( true );
                    setarCBImportarExportar(false, new Evento() );
                }
                else if( str.equals( "PESQUISAR TODOS EVENTOS" ) ){
                    
                    cbEventos.setEnabled( false );
                    cbEventos.removeAllItems();
                }               
            }
        }
        catch( Exception e ){ System.out.println( "cbPesquisarPor2 xxxxxxxxxx" ); e.printStackTrace(); }
    }//GEN-LAST:event_cbPesquisarPor2ActionPerformed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        try{
            
            if( cbPesquisarPor2.getItemCount() > 0 ) {
                
                String str = cbPesquisarPor2.getSelectedItem().toString().trim();
                
                //System.out.println( "cbPaisActionPerformed" );
                if( str.equals( "SELECIONAR UM EVENTO" ) ){
                    
                    
                }
                else if( str.equals( "PESQUISAR TODOS EVENTOS" ) ){
                    
                    if( !tfSap.getText().trim().equals("") ){
                        Tabela_Pesquisa_BD_Estabelecimento.mostrarListaSap( tfSap.getText().trim(), "sap", new ArrayList<String>() );
                    }
                    else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "PESQUISAR PELO CÓDIGO SAP\n"
                            + "\nStatus Da Pesquisar:"
                            + "\nPara realizar uma pesquisa informe um código SAP\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                    }
                }               
            }
        }
        catch( Exception e ){ System.out.println( "cbPesquisarPor2 xxxxxxxxxx" ); e.printStackTrace(); }
    }//GEN-LAST:event_jLabel11MousePressed

    private void jLabel12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MousePressed
        try{
            
            if( cbPesquisarPor2.getItemCount() > 0 ) {
                
                String str = cbPesquisarPor2.getSelectedItem().toString().trim();
                
                //System.out.println( "cbPaisActionPerformed" );
                if( str.equals( "SELECIONAR UM EVENTO" ) ){
                    
                    
                }
                else if( str.equals( "PESQUISAR TODOS EVENTOS" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.mostrarListaSap( "", "empromocao", new ArrayList<String>() );
                }               
            }
        }
        catch( Exception e ){ System.out.println( "cbPesquisarPor2 xxxxxxxxxx" ); e.printStackTrace(); }
    }//GEN-LAST:event_jLabel12MousePressed

    private void cbEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEventosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEventosActionPerformed

    private void lbFiltroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltroMousePressed
        if ( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedRow() != -1){
            
            int c = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedColumn();
            
            String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedRow(), Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedColumn()) );
            Tabela_Pesquisa_BD_Estabelecimento.filtro( str, c );  
        }
        else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "FILTRA TABELA\n"
                            
                            + "\nPara filtrar dados da tabela 1º selecione uma célula\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_lbFiltroMousePressed

    private void lbModeloTabelaXLSXMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbModeloTabelaXLSXMousePressed
        ////////////////////////////////////////////////////////////////////////
        //AbrirTabInicial.inicio();
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            JpImagem JpImagem = new JpImagem( "MODELO VT LAMINA TABLOIDE.png" );

            Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo TB XLSX VT", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "lbModeloTabelaXLSXMousePressed(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        //////////////////////////////////////////////////////////////////////// 
    }//GEN-LAST:event_lbModeloTabelaXLSXMousePressed

    private void btAnaliseCadastrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAnaliseCadastrarMousePressed
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            String strTipoDeEvento = "";
            try{ strTipoDeEvento = cbAnaliseCadastro.getSelectedItem().toString().trim(); } catch( Exception e ){}
            
        int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR CADASTRO**"
                        + "\n"
                        + strTipoDeEvento
                        + "\n"
                        + "Deseja CADASTRAR O PARÂMETRO SELECIONADO?");              
        if( response == JOptionPane.YES_OPTION){ 
                          
            if( !strTipoDeEvento.equals( "" ) ){
                
                if( strTipoDeEvento.equals( "ZTCONSULTAMERCA PRODUTOS" ) ){
                    
                    cadastrarAnaliseProdutos();
                }
                else if( strTipoDeEvento.equals( "OPORTUNIDADE IDENTIFICADA" ) ){
                    
                    opIdentificada();
                } 
                else if( strTipoDeEvento.equals( "QUANTIDADE_VENDIDA" ) ){
                    
                    excluir_Quantidade_Vendida();
                }
                else if( strTipoDeEvento.equals( "ZVTRA_SORTIMENTO_PRODUTOS" ) ){
                    
                    cadastrarProdutosZVTraSortemento();
                }
                else if( strTipoDeEvento.equals( "PRODUTO POR FORNECEDOR_CD" ) ){
                    try{ 
                        
                        Exportando Exportando = new Exportando("ANALISANDO TABELA E EXCLUINDO LOF");
                        Exportando.setVisible(true);
                        Exportando.pbg.setMinimum(0);
                        Exportando.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() );
                        
                        LinkedHashSet<String> listaSap = new LinkedHashSet<>();
                        
                        if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){                        
                            for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){    
                                
                                Exportando.pbg.setValue( L_i );
                                
                                for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                    String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim();
                                    switch( C_i ){
                                        case 1:  
                                            
                                            boolean boo = false;
                                            
                                            for (Iterator<String> it = listaSap.iterator(); it.hasNext();) {
                                                
                                                String busca = it.next().trim();
                                                if( busca.equals(str.toUpperCase()) ){
                                                    
                                                    boo = true;
                                                    break;     
                                                }
                                            }
                                            
                                            listaSap.add(str.toUpperCase());
                                            
                                            if( boo == true ){
                                                excluirProdutoPorFornecedor2CD(str.toUpperCase()); 
                                            }
                                        break;                                     
                                    }
                                }
                            }
                            
                            Exportando.fechar();
                            cadastrarProdutoPorFornecedorCD();
                        }                        
                    }catch(Exception e ){}
                } 
                else if( strTipoDeEvento.equals( "ULTIMA_ENTRADA" ) ){
                    
                    excluirCadastrarUltimaEntrada();
                } 
                else if( strTipoDeEvento.equals( "CORINGA" ) ){
                    
                   coringa();
                } 
                else if( strTipoDeEvento.equals( "LOF" ) ){
                    
                   lof();
                }
                else if( strTipoDeEvento.equals( "CADASTRAR PRODUTO - SAP - UND - NOME" ) ){
                    
                    cadastrarProdutosSapUndNome();
                }
                else if( strTipoDeEvento.equals( "ALTERAR DESC.  PRODUTO" ) ){
                    
                    alterarProdutosSapUndNome();
                }
                else if( strTipoDeEvento.equals( "INVENTARIOMB52" ) ){
                    
                    cadastrarInventarioMb52();
                }
                else if( strTipoDeEvento.equals( "INVENTARIOZLMR06" ) ){
                    
                    cadastrarInventarioZlmr06();
                }        
                else if( strTipoDeEvento.equals( "OPORTUNIDADESEMANA" ) ){
                    
                    oportSemana();
                } 
                else if( strTipoDeEvento.equals( "EAN ZVTRA_SORTIMENTO" ) ){
                    
                    eanZevtra_sortimento();
                }        
                else if( strTipoDeEvento.equals( "PONTOEXTRA" ) ){
                    
                    cadastrarPonExtra();
                }   
                else if( strTipoDeEvento.equals( "ELENCO" ) ){
                    
                    cadastrarElenco();
                }           
                else if( strTipoDeEvento.equals( "RWBE - ESTOQUE CD 184" ) ){
                    
                    excluirGBProdutosEstoqueCD184();
                    cadastrarAnaliseEstoqueCD184();
                }
                else if( strTipoDeEvento.equals( "PRODUTO POR FORNECEDOR" ) ){
                    try{ 
                        
                        Exportando Exportando = new Exportando("ANALISANDO TABELA E EXCLUINDO LOF");
                        Exportando.setVisible(true);
                        Exportando.pbg.setMinimum(0);
                        Exportando.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() );
                        
                        LinkedHashSet<String> listaSap = new LinkedHashSet<>();
                        
                        if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){                        
                            for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){    
                                
                                Exportando.pbg.setValue( L_i );
                                
                                for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                    String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim();
                                    switch( C_i ){
                                        case 1:  
                                            
                                            boolean boo = false;
                                            
                                            for (Iterator<String> it = listaSap.iterator(); it.hasNext();) {
                                                
                                                String busca = it.next().trim();
                                                if( busca.equals(str.toUpperCase()) ){
                                                    
                                                    boo = true;
                                                    break;     
                                                }
                                            }
                                            
                                            listaSap.add(str.toUpperCase());
                                            
                                            if( boo == true ){
                                                excluirProdutoPorFornecedor2(str.toUpperCase()); 
                                            }
                                        break;                                     
                                    }
                                }
                            
                            }
                            
                            Exportando.fechar();
                            cadastrarProdutoPorFornecedor();
                        }   
                                                
                    }catch(Exception e ){}
                }
                else if( strTipoDeEvento.equals( "RWBE - ESTOQUE CD 001" ) ){
                    
                    excluirGBProdutosEstoqueCD001();
                    cadastrarAnaliseEstoqueCD001();
                }
                else if( strTipoDeEvento.equals( "RWBE - ESTOQUE CD 289" ) ){
                    
                    excluirGBProdutosEstoqueCD289();
                    cadastrarAnaliseEstoqueCD289();
                }
                else if( strTipoDeEvento.equals( "MB52 - ESTOQUE LOJA" ) ){
                      
                    excluirCadastrarAnaliseEstoqueLoja();
                }
                else if( strTipoDeEvento.equals( "SUPLY CHAIN" ) ){
                      
                    excluirCadastrarAnaliseSuplyChain();
                }
                else if( strTipoDeEvento.equals( "ZRIS ALTERAR MRP UBMPEDIDO" ) ){ //ZVTRA_MRP
                      
                    cadastrarAnaliseZris();
                }
                else if( strTipoDeEvento.equals( "ZVTRA_MRP" ) ){ //ZVTRA_MRP
                      
                    cadastrarMRP();
                }
                else if( strTipoDeEvento.equals( "MB51 - 101 - PRODUTOS ENTRARAM CD 184 DIA ANTERIOR" ) ){
                      
                    excluirCadastrarAnaliseCDOntem();
                }    
                else if( strTipoDeEvento.equals( "ME80FN ZREP" ) ){
                      
                    me80fnZrep();
                }           
                else if( strTipoDeEvento.equals( "ME80FN - PEDIDOS PENDENTES" ) ){
                      
                    excluirGBPedidoAtivo();
                    cadastrarAnaliseped_Ativo();
                }     
                else if( strTipoDeEvento.equals( "RA DIÁRIO - ME80FN" ) ){
                      
                    excluirCadastrarAnalise_ra_diario();
                }     
                else if( strTipoDeEvento.equals( "ATIVO SEM VENDA - MC46" ) ){ 
                      
                    excluirCadastrarAtivoSemVenda();
                }
                else if( strTipoDeEvento.equals( "Q.EMB.E/S" ) ){ 
                      
                    cadastrarQtdEmbEs();
                }  
                else if( strTipoDeEvento.equals( "FORNECEDORES" ) ){ 
                      
                    fornecedores();
                } 
                else if( strTipoDeEvento.equals( "EAN/GRUPO" ) ){ 
                      
                    eangrupo();
                } 
                else if( strTipoDeEvento.equals( "DIRETO LOJA INPUT ZRIS" ) ){ 
                      
                    diretoLojaZris();
                } 
                //PLANILHA_RA_INTRANET  -  DEVOLUTIVA_INPUT
                //PLANILHA_RA_INTRANET_ALTERADO  -  DEVOLUTIVA_RECEBIDA
                //PLANILHA_RA_INTRANET_ME80FN
                else if( strTipoDeEvento.equals( "PLANILHA_RA_INTRANET" ) ){ 
                      
                    planilha_ra_intranet();
                }
                else if( strTipoDeEvento.equals( "PLANILHA_RA_INTRANET_ALTERADO" ) ){ 
                      
                    planilha_ra_intranet_alterado();
                } 
                else if( strTipoDeEvento.equals( "PLANILHA_RA_INTRANET_ME80FN" ) ){ 
                      
                    planilha_ra_intranet_dev_me80fn();
                } 
                //EVENTO_QTD_POR_EMBALAGEM
                //EVENTO_VENDA_MES_ANTERIOR
                //EVENTO_VENDA_ANO_ANTERIOR       
                else if( strTipoDeEvento.equals( "EVENTO_QTD_POR_EMBALAGEM" ) ){ 
                      
                    evento_qtd_por_embalagem();
                }
                else if( strTipoDeEvento.equals( "EVENTO_VENDA_MES_ANTERIOR" ) ){ 
                      
                    evento_venda_mes_passado();
                }
                else if( strTipoDeEvento.equals( "EVENTO_VENDA_ANO_ANTERIOR" ) ){ 
                      
                    evento_venda_ano_passado();
                }
                //REL_MB52
                //REL_ZDADOSLOG_CD
                //REL_ZDADOSLOG_LOJA
                //REL_ZVTRA_SORTIMENTO         
                else if( strTipoDeEvento.equals( "REL_MB52" ) ){ 
                      
                    excluirCadastrarLojasAMb52();
                }
                else if( strTipoDeEvento.equals( "REL_ZDADOSLOG_CD" ) ){ 
                      
                    excluirCadastrarLojasAZdadoslogCd();
                }
                else if( strTipoDeEvento.equals( "REL_ZDADOSLOG_LOJA" ) ){ 
                      
                    excluirCadastrarLojasAZdadoslogLoja();
                }
                else if( strTipoDeEvento.equals( "REL_ZVTRA_SORTIMENTO" ) ){ 
                      
                    excluirCadastrarLojasAZvtraSort();
                }
                else if( strTipoDeEvento.equals( "DIRETO LOJA PEDIDO GERADO" ) ){ 
                      
                    diretoLojaGerado();
                }                
                else if( strTipoDeEvento.equals( "DESCRICÃO DO GRUPO COMPRA" ) ){ 
                      
                    descricaoGrupoCompra();
                }   
                else if( strTipoDeEvento.equals( "DESCRICÃO DO SETOR" ) ){ 
                      
                    descricaoSetor();
                }  
                else if( strTipoDeEvento.equals( "ENDERECOESPACOGONDULA" ) ){ 
                      
                    enderecoespacogondula();
                }        
                else{
                        
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "CADASTRO DE ANÁLISES\n"
                        + "\nSelecione o Tipo de cadastro de análise\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
                
            }
            else{
                        
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "CADASTRO DE ANÁLISES\n"
                        + "\nSelecione o Tipo de cadastro de análise\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        }    
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "btAnaliseCadastrarMousePressed(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
    }//GEN-LAST:event_btAnaliseCadastrarMousePressed

    private void btAnaliseCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnaliseCadastrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAnaliseCadastrarActionPerformed

    private void lbModeloTabelaXLSX1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbModeloTabelaXLSX1MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            String strTipoDeEvento = "";
            try{ strTipoDeEvento = cbAnaliseCadastro.getSelectedItem().toString().trim(); } catch( Exception e ){}
                          
            if( !strTipoDeEvento.equals( "" ) ){
                
                if( strTipoDeEvento.equals( "ZTCONSULTAMERCA PRODUTOS" ) ){ //
                    
                    JpImagem JpImagem = new JpImagem( "MODELO ZTCONSULTAMERCA PRODUTOS.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Produtos", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "CORINGA" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO CORINGA.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo CORINGA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                //REL_MB52
                //REL_ZDADOSLOG_CD
                //REL_ZDADOSLOG_LOJA
                //REL_ZVTRA_SORTIMENTO 
                else if( strTipoDeEvento.equals( "REL_MB52" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO_LojasAMb52.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO_Mb52", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "REL_ZDADOSLOG_CD" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO_LojasAZdadoslogCd.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO_ZDADOSLOG_CD", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "REL_ZDADOSLOG_LOJA" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO_LojasAZdadoslogLoja.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO_ZDADOSLOG_LOJA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "REL_ZVTRA_SORTIMENTO" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO_LojasAZvtraSort.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO_LojasAZvtraSort", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "PLANILHA_RA_INTRANET" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO DUAS COLUNAS.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo RA_INTRANET", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "PLANILHA_RA_INTRANET_ALTERADO" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO DUAS COLUNAS.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo RA_INTRANET", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "PLANILHA_RA_INTRANET_ME80FN" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "Modelo_Planilha_Ra_Intranet_ME80FN.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo RA_INTRANET", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "ULTIMA_ENTRADA" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO ULTIMA ENTRADA.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo ÚLTIMA ENTRADA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "OPORTUNIDADE IDENTIFICADA" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO OP IDENTIFICADA.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Op Identif", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "PRODUTO POR FORNECEDOR" ) ){ 
                    
                    JpImagem JpImagem = new JpImagem( "MODELO PRODUTO X FORNECEDOR.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Prod x Fornec", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "PRODUTO POR FORNECEDOR_CD" ) ){ 
                    
                    JpImagem JpImagem = new JpImagem( "MODELO PRODUTO X FORNECEDOR.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Prod x Fornec CD", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "CADASTRAR PRODUTO - SAP - UND - NOME" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO PRODUTO - SAP - UND - NOME.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Prod Sap Und Nome", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "DESCRICÃO DO GRUPO COMPRA" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO DESCRICÃO DO GRUPO COMPRA.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Desc Grupo Comp", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "ENDERECOESPACOGONDULA" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO ENDERECO.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO ENDERECO", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }         
                else if( strTipoDeEvento.equals( "ELENCO" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO ELENCO.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo ELENCO", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "ZVTRA_SORTIMENTO_PRODUTOS" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO ZVTRA_SORTEMENTO.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo ZVTRA_SORT", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "PONTOEXTRA" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO PONTO EXTRA.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO PONTO EXTRA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }    
                else if( strTipoDeEvento.equals( "ZVTRA_MRP" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO ESTAB - SAP - MRP.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO PONTO EXTRA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }          
                else if( strTipoDeEvento.equals( "OPORTUNIDADESEMANA" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO OPORTSEMANA.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO OPORTSEMANA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else if( strTipoDeEvento.equals( "DIRETO LOJA INPUT ZRIS" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO INPUT ZRIS.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO INPUT ZRIS", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }     
                else if( strTipoDeEvento.equals( "DIRETO LOJA PEDIDO GERADO" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO PEDIDO GERADO.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO PEDIDO GERADO", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "EAN/GRUPO" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO EAN.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO OPORTSEMANA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }   
                else if( strTipoDeEvento.equals( "Q.EMB.E/S" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MODELO E-S - UND.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "MODELO OPORTSEMANA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }         
                else if( strTipoDeEvento.equals( "RWBE - ESTOQUE CD 001" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MB52 - ESTOQUE CD B184.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Estoque CD", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "RWBE - ESTOQUE CD 289" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MB52 - ESTOQUE CD B184.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Estoque CD", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "RWBE - ESTOQUE CD 184" ) ){
                    
                    JpImagem JpImagem = new JpImagem( "MB52 - ESTOQUE CD B184.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Estoque CD", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "MB52 - ESTOQUE LOJA" ) ){
                      
                    JpImagem JpImagem = new JpImagem( "MB52 - ESTOQUE CD B184.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Estoque Loja", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "SUPLY CHAIN" ) ){
                      
                    JpImagem JpImagem = new JpImagem( "MODELO SUPLY CHAIN.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Suply Chain", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "ZRIS ALTERAR MRP UBMPEDIDO" ) ){
                      
                    JpImagem JpImagem = new JpImagem( "MODELO ZRIS.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo ZRIS", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }
                else if( strTipoDeEvento.equals( "MB51 - 101 - PRODUTOS ENTRARAM CD 184 DIA ANTERIOR" ) ){
                      
                    JpImagem JpImagem = new JpImagem( "MODELO MB51 - CD ONTEM.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo Entrou CD Ontem", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }     
                else if( strTipoDeEvento.equals( "ME80FN - PEDIDOS PENDENTES" ) ){
                      
                    JpImagem JpImagem = new JpImagem( "MODELO PEDIDOS 7 DIAS ATIVOS.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo PEDIDOS 7 DIAS ATIVOS", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }     
                else if( strTipoDeEvento.equals( "RA DIÁRIO - ME80FN" ) ){ 
                      
                    JpImagem JpImagem = new JpImagem( "MODELO ME80FN - RA.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo RA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }  
                else if( strTipoDeEvento.equals( "FORNECEDORES" ) ){ 
                      
                    JpImagem JpImagem = new JpImagem( "MODELO FORNECEDORES.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo FORNECEDORES", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                }  
                else if( strTipoDeEvento.equals( "ATIVO SEM VENDA - MC46" ) ){ 
                      
                    JpImagem JpImagem = new JpImagem( "MODELO SEM VENDA.png" );
                    Importar_Exportar_Home.Home.ControleTabs.AddTabSemControleNovo(Importar_Exportar_Home.tabHome, "Modelo SEM VENDA", "/utilidades_imagens/modelotabelaxlsx.gif", JpImagem );
                } 
                else{
                        
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "CONSULTAR MODELO DE PREENCHIMENTO\n"
                        + "\nSelecione o Tipo de cadastro de análise\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
                
            }
            else{
                        
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "CONSULTAR MODELO DE PREENCHIMENTO\n"
                        + "\nSelecione o Tipo de cadastro de análise\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
 
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "btAnaliseCadastrarMousePressed(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
    }//GEN-LAST:event_lbModeloTabelaXLSX1MousePressed

    private void btExcluirParametrosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirParametrosMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                  
            if( cbAnaliseParametros.getItemCount() > 0 ) {
                
                String str = cbAnaliseParametros.getSelectedItem().toString().trim();
                if( str.equals( "TODOS  PRODUTOS" ) ){
                    
                    /*int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir todos os Produtos?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBProdutos(); }*/
                    Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "SEM ACESSO\n"
                            + "\nStatus Da Solicitação:"
                            + "\nVocê não tem acesso a este comando\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                } 
                else if( str.equals( "ESTOQUE CD 184" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir ESTOQUE CD 184?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBProdutosEstoqueCD184(); }
                }
                else if( str.equals( "OPIDENTIFICADA" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir OPIDENTIFICADA?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirOpIdentificada(); }
                }//
                else if( str.equals( "ENDERECOESPACOGONDULA" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir ESPAÇO GÔNDULA?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirEnderecoespacogondula(); }
                }       
                else if( str.equals( "ZVTRA_MRP" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir ZVTRA_MRP?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirZVTRA_MRP(); }
                } 
                //ESTOQUE LOJA + DADOS ZRIS 
                else if( str.equals( "ESTOQUE LOJA + DADOS ZRIS" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Excluir ESTOQUE LOJA + DADOS ZRIS?");
              
                    if( response == JOptionPane.YES_OPTION){ 
                        excluirGBProdutosEstoqueLoja();
                        excluirGBZris();
                    }
                } 
                else if( str.equals( "ESTOQUE CD 001" ) ){
                    
                    excluirGBProdutosEstoqueCD001();
                }
                else if( str.equals( "ESTOQUE CD 289" ) ){
                    
                    excluirGBProdutosEstoqueCD289();
                }
                else if( str.equals( "ESTOQUE LOJA" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir ESTOQUE LOJA?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBProdutosEstoqueLoja(); }
                }  
                else if( str.equals( "SUPLY CHAIN" ) ){ 
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados SUPLY CHAIN?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBProdutosSuplyChain(); }
                } 
                else if( str.equals( "DIRETO LOJA ZRIZ_PEDIDO" ) ){ 
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados SUPLY CHAIN?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBDiretoLoja(); }
                } 
                else if( str.equals( "FORNECEDORES" ) ){ 
                    
                    Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "SEM ACESSO\n"
                            + "\nStatus Da Solicitação:"
                            + "\nVocê não tem acesso a este comando\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                    /*int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados FORNECEDOR?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirFornecedores(); }*/
                }
                else if( str.equals( "PRODUTOS ENT. CD ONTEM" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados CD ONTEM?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBProdutosCDOntem(); }
                }        
                else if( str.equals( "ME80FN - PEDIDOS PENDENTES" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados PED. 7 DIAS ATIVOS?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBPedidoAtivo(); }
                }        
                else if( str.equals( "RA DIÁRIO - ME80FN" ) ){ 
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados RA DIÁRIO - ME80FN?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBRaDiario(); }
                }     
                else if( str.equals( "ATIVO SEM VENDA - MC43" ) ){ 
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados ATIVO SEM VENDA - MC43?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirAtivoSemVenda(); }
                }      
                else if( str.equals( "DESCRICÃO DO GRUPO COMPRA" ) ){ 
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados DESCRIÇÃO DO GRUPO DE COMPRA?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirDescricaoGrupoCompra(); }
                }         
                else if( str.equals( "ZRIS MRP UMB DISPONIBILIDADE" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados ZRIS MRP UMB DISPONIBILIDADE?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBZris(); }
                }        
                else if( str.equals( "TODOS PARÂMETROS" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados TODOS PARÂMETROS?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirGBTodosParametros(); }
                }  
                else if( str.equals( "PRODUTO POR FORNECEDOR" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados PRODUTO POR FORNECEDOR?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirProdutoPorFornecedor2TUDO(); }
                } 
                else if( str.equals( "PRODUTO POR FORNECEDOR_CD" ) ){
                    
                    int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR EXCLUSÃO**"
                        + "\n"
                        + "Deseja Excluir dados PRODUTO POR FORNECEDORCD?");
              
                    if( response == JOptionPane.YES_OPTION){ excluirProdutoPorFornecedor2CDTUDO(); }
                }          
                else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "EXCLUIR PARÂMETRO\n"
                            + "\nStatus Da Exclusão:"
                            + "\nSelecione 1º um Parâmetro para excluir\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            }  
        } catch( Exception e ){  } } }.start();
    }//GEN-LAST:event_btExcluirParametrosMousePressed

    private void btExcluirParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirParametrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirParametrosActionPerformed

    private void lbImportacao1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImportacao1MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                                 
            if( lbImportacao.isEnabled() == true ) { lbImportacao.setEnabled(false);
                
                JFileChooserJM JFileChooserJM = new JFileChooserJM( "  imagens    -   jmarysystems.blogspot.com.br", new String [] { "XLS" , "XLSX" } );
                String strdevolvida = JFileChooserJM.getString( 2 );
                
                tfPesquisa.setText( strdevolvida );
                
                Tabela_Pesquisa_BD_Estabelecimento.pesquisaImportacaoExcel(strdevolvida);
                
                lbImportacao.setEnabled(true);
            }             
        } catch( Exception e ){ lbImportacao.setEnabled(true); } } }.start();
    }//GEN-LAST:event_lbImportacao1MousePressed

    private void cbAnaliseCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAnaliseCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAnaliseCadastroActionPerformed

    private void lbAnaliseConsultaMousePressedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnaliseConsultaMousePressedMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            String strTipoDeEvento = "";
            try{ strTipoDeEvento = cbEventos1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        

            if( !strTipoDeEvento.equals( "" ) ){
                
                if( strTipoDeEvento.equals( "TODOS OS PRODUTOS" ) ){ 
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "TODOS OS PRODUTOS", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "DISPONIBILIDADE BAIXA TOP350/2000/MIX" ) ){ 
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "DISPONIBILIDADE BAIXA TOP350/2000/MIX", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "TOP350/200/MIX 'ZERO'" ) ){ 
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "TOP350/200/MIX 'ZERO'", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "IDENTIFICA NA TABELA OS PRODUTOS COM OPORTUNIDADES" ) ){
                    
                    mostraOpIdentificadas();
                } 
                else if( strTipoDeEvento.equals( "PRODUTOS LOJA TOP 350" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS LOJA TOP 350", new ArrayList<String>() );
                } 
                else if( strTipoDeEvento.equals( "PRODUTOS LOJA TOP 2000" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS LOJA TOP 2000", new ArrayList<String>() );
                } 
                else if( strTipoDeEvento.equals( "PRODUTOS LOJA MIX" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS LOJA MIX", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E NO CD TOP 350" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E NO CD TOP 350", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E NO CD TOP 2000" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E NO CD TOP 2000", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E NO CD MIX" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E NO CD MIX", new ArrayList<String>() );
                } 
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO", new ArrayList<String>() );
                } 
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 350" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 350", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 2000" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 2000", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO MIX" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO MIX", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 350 SEM PEDIDO (ATIVO)" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 350 SEM PEDIDO (ATIVO)", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 2000 SEM PEDIDO (ATIVO)" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP 2000 SEM PEDIDO (ATIVO)", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP MIX SEM PEDIDO (ATIVO)" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS ZERO NA LOJA E ESTOQUE CD POSITIVO TOP MIX SEM PEDIDO (ATIVO)", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS TOP 350 RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS TOP 350 RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO", new ArrayList<String>() );
                }
                else if( strTipoDeEvento.equals( "PRODUTOS TOP 2000 RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS TOP 2000 RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO", new ArrayList<String>() );
                }        
                else if( strTipoDeEvento.equals( "PRODUTOS MIX RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS MIX RUPTURA NA LOJA SEM PEDIDO (ATIVO) ESTOQUE CD POSITIVO", new ArrayList<String>() );
                }  
                else if( strTipoDeEvento.equals( "PRODUTOS QUE ENTRARAM NO CD ONTEM" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS QUE ENTRARAM NO CD ONTEM", new ArrayList<String>() );
                } 
                else if( strTipoDeEvento.equals( "PRODUTOS COM DISPONIBILIDADE BAIXA" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS COM DISPONIBILIDADE BAIXA", new ArrayList<String>() );
                }        
                else if( strTipoDeEvento.equals( "TOP 350 DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD" ) ){
                    
                    String recebe = JOptionPane.showInputDialog(null,
                            "TOP 350 DISPONIBILIDADE DO PRODUTO NA LOJA"
                            + "\nFILTRA PELA DISPONIBILIDADE INFORMADA NA LOJA" 
                            + "\nQUE ESTEJA ZERO NO CD"         
                            + "\n\nINFORME A DISPONIBILIDADE DESEJADA PARA PESQUISA"
                    );
                    
                    if(!recebe.equals("")){
                        
                        int t7 = 0; 
                        try{ 
                            
                            t7 = Integer.parseInt(recebe); 
                        
                            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", recebe, "TOP 350 DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD", new ArrayList<String>() );
                        
                        } catch( Exception e ){
                            
                            Class<Imagens> clazzHome = Imagens.class;
                            JOPM JOptionPaneMod = new JOPM( 1, "TOP 350 DISPONIBILIDADE DO PRODUTO NA LOJA\n"
                                + "\nPARA EXECUTAR O FILTRO\n"
                                + "\nINFORME UM VALOR VÁLIDO"
                                ,"Class: " + this.getClass().getName(), 
                                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                        } 
                    }
                    else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "TOP 350 DISPONIBILIDADE DO PRODUTO NA LOJA\n"
                                + "\nPARA EXECUTAR O FILTRO\n"
                                + "\nINFORME UM VALOR VÁLIDO"
                                ,"Class: " + this.getClass().getName(), 
                                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                    }
                }  
                else if( strTipoDeEvento.equals( "TOP 2000 DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD" ) ){
                    
                    String recebe = JOptionPane.showInputDialog(null,
                            "TOP 2000 DISPONIBILIDADE DO PRODUTO NA LOJA"
                            + "\nFILTRA PELA DISPONIBILIDADE INFORMADA NA LOJA" 
                            + "\nQUE ESTEJA ZERO NO CD"         
                            + "\n\nINFORME A DISPONIBILIDADE DESEJADA PARA PESQUISA"
                    );
                    
                    if(!recebe.equals("")){
                        
                        int t7 = 0; 
                        try{ 
                            
                            t7 = Integer.parseInt(recebe); 
                        
                            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", recebe, "TOP 2000 DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD", new ArrayList<String>() );
                        
                        } catch( Exception e ){
                            
                            Class<Imagens> clazzHome = Imagens.class;
                            JOPM JOptionPaneMod = new JOPM( 1, "TOP 2000 DISPONIBILIDADE DO PRODUTO NA LOJA\n"
                                + "\nPARA EXECUTAR O FILTRO\n"
                                + "\nINFORME UM VALOR VÁLIDO"
                                ,"Class: " + this.getClass().getName(), 
                                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                        } 
                    }
                    else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "TOP 2000 DISPONIBILIDADE DO PRODUTO NA LOJA\n"
                                + "\nPARA EXECUTAR O FILTRO\n"
                                + "\nINFORME UM VALOR VÁLIDO"
                                ,"Class: " + this.getClass().getName(), 
                                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                    }
                } 
                else if( strTipoDeEvento.equals( "MIX DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD" ) ){
                    
                    String recebe = JOptionPane.showInputDialog(null,
                            "MIX DISPONIBILIDADE DO PRODUTO NA LOJA"
                            + "\nFILTRA PELA DISPONIBILIDADE INFORMADA NA LOJA" 
                            + "\nQUE ESTEJA ZERO NO CD"         
                            + "\n\nINFORME A DISPONIBILIDADE DESEJADA PARA PESQUISA"
                    );
                    
                    if(!recebe.equals("")){
                        
                        int t7 = 0; 
                        try{ 
                            
                            t7 = Integer.parseInt(recebe); 
                        
                            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", recebe, "MIX DISPONIBILIDADE DO PRODUTO NA LOJA E ZERO NO CD", new ArrayList<String>() );
                        
                        } catch( Exception e ){
                            
                            Class<Imagens> clazzHome = Imagens.class;
                            JOPM JOptionPaneMod = new JOPM( 1, "MIX DISPONIBILIDADE DO PRODUTO NA LOJA\n"
                                + "\nPARA EXECUTAR O FILTRO\n"
                                + "\nINFORME UM VALOR VÁLIDO"
                                ,"Class: " + this.getClass().getName(), 
                                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                        } 
                    }
                    else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "MIX DISPONIBILIDADE DO PRODUTO NA LOJA\n"
                                + "\nPARA EXECUTAR O FILTRO\n"
                                + "\nINFORME UM VALOR VÁLIDO"
                                ,"Class: " + this.getClass().getName(), 
                                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                    }
                }
                else if( strTipoDeEvento.equals( "PRODUTOS QUE DISPARARAM NO RA" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", "", "PRODUTOS QUE DISPARARAM NO RA", new ArrayList<String>() );
                }
                else{                          
                        
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA DE ANÁLISES\n"
                        + "\nSelecione o Tipo de cadastro de análise\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
                
            }
            else{
                        
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA DE ANÁLISES\n"
                        + "\nSelecione o Tipo de CONSULTA de análise\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "lbAnaliseConsultaMousePressedMousePressed(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
    }//GEN-LAST:event_lbAnaliseConsultaMousePressedMousePressed

    private void btAlterar6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAlterar6MousePressed

        //tfPesquisarPeloNomeProdutoControl(); 
        Alterar_Tabela_Qtd_Venda.alterar();
    }//GEN-LAST:event_btAlterar6MousePressed

    private void btAlterar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterar6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterar6ActionPerformed

    private void tfPesquisarPeloNomeProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfPesquisarPeloNomeProdutoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPesquisarPeloNomeProdutoMouseEntered

    private void tfPesquisarPeloNomeProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfPesquisarPeloNomeProdutoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPesquisarPeloNomeProdutoMouseExited

    public int contadorN = 0;
    private void tfPesquisarPeloNomeProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarPeloNomeProdutoKeyReleased
       
    }//GEN-LAST:event_tfPesquisarPeloNomeProdutoKeyReleased

    boolean control = false;
    private void tfPesquisarPeloNomeProdutoControl() {  
        try{
            
            if(control == false){
                
                tfPesquisarPeloNomeProduto();
            }
            else{
                
                emexec = false;
            }
            
        } catch( Exception e ){ e.printStackTrace(); }
    }
    
    boolean emexec = false;
    Exportando Exportando;
    private void tfPesquisarPeloNomeProduto() {                                                       
        try{
            
            control = true;
            emexec  = true;
                                    
            if( tfPesquisarPeloNomeProduto.getText().trim().length() > 2 ){
                
                Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica();
                
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                    tfSapAnalise.setText("");
                    tfEanAnalise.setText("");
                    //Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", tfPesquisarPeloNomeProduto.getText().trim(), "nome", new ArrayList<String>() ); 
                               
                //try{
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE DESCRICAO LIKE ?", Gbproduto.class );
                          q2.setParameter( 1, tfPesquisarPeloNomeProduto.getText().trim().toUpperCase() ); 
                    List<Gbproduto> GbprodutoNome = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( GbprodutoNome.size() );

                    for (int it = 0; it < GbprodutoNome.size(); it++){
                        Exportando.pbg.setValue( it );
                                        
                        Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", GbprodutoNome.get(it) ); 
                        
                        if(emexec == false){
                            tfPesquisarPeloNomeProduto();
                            Exportando.fechar(); break;
                        } 
                    }
                    
                    control = false;
                    Exportando.fechar();

                //} catch( Exception e ){}    
                } catch( Exception e ){ 
                    control = false;
                    e.printStackTrace(); 
                } } }.start();        
            }
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    } 
    
    private void cbEventos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEventos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEventos1ActionPerformed

    private void btAlterar8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAlterar8MousePressed
        ListaSAPAnaliseSet ListaSAPAnalise = new ListaSAPAnaliseSet( "", this );
        ListaSAPAnalise.setVisible(true);
    }//GEN-LAST:event_btAlterar8MousePressed

    private void btAlterar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterar8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterar8ActionPerformed

    private void tfSapAnaliseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSapAnaliseMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSapAnaliseMouseEntered

    private void tfSapAnaliseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSapAnaliseMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSapAnaliseMouseExited

    private void tfSapAnaliseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSapAnaliseKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER )  {
            
            if( !tfSapAnalise.getText().trim().equals("") ){
                new Thread() { @Override public void run() { 
                
                String str = tfSapAnalise.getText().trim();
                
                Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica();
            
                //Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", tfSapAnalise.getText().trim(), "sap", new ArrayList<String>() );
                try{
                    
                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
                    q.setParameter( 1, str ); 
                    List<Gbproduto> GbprodutoSap = q.getResultList();  
                    
                    for (int it = 0; it < GbprodutoSap.size(); it++){
                        
                        Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", GbprodutoSap.get(it) );
                    }
                }catch(Exception e){ e.printStackTrace();}
                
                } }.start();
        }
        else{
                        
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PESQUISAR PELO CÓDIGO SAP\n"
                    + "\nStatus Da Pesquisar:"
                    + "\nPara realizar uma pesquisa informe um código SAP\n"
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), 
            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
        }
    }//GEN-LAST:event_tfSapAnaliseKeyReleased

    int L_i, C_i;
    private void jLabel20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MousePressed
        /*if( !tfSapAnalise.getText().trim().equals("") ){
            
            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap( tfSapAnalise.getText().trim(), "sap", new ArrayList<String>() );
        }
        else{
                        
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PESQUISAR PELO CÓDIGO SAP\n"
                    + "\nStatus Da Pesquisar:"
                    + "\nPara realizar uma pesquisa informe um código SAP\n"
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), 
            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }*/
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            Exportando Exportandoy = new Exportando("PESQUISANDO \"PROMOCIONAIS\"");
            Exportandoy.setVisible(true);
            Exportandoy.pbg.setMinimum(0);
            Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );  
            
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
            
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );                 
                    
                    String sap = "";
                    
                    for( C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){                       
                                                       
                        String strn = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnName(C_i);
                    
                        if( strn.trim().equals("MATERIAL") ){
                            
                            try{ 
                                
                                sap = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim(); 
                                
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
                
                Exportandoy.fechar();
            }
            
            //Colorir_Celula_Tabela Cell = new Colorir_Celula_Tabela(listEventoprodutosFull);
            //Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setDefaultRenderer(Object.class, Cell);
            Tabela_Pesquisa_BD_Estabelecimento.tableCellRendererCompProVerde(listEventoprodutosFull);
            
            
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();
    }//GEN-LAST:event_jLabel20MousePressed

    private void lbFiltro1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltro1MousePressed
        if ( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedRow() != -1){
            
            int c = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedColumn();
            
            String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedRow(), Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedColumn()) );
            Tabela_Pesquisa_BD_Estabelecimento.filtro( str, c );  
        }
        else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "FILTRA TABELA\n"
                            
                            + "\nPara filtrar dados da tabela 1º selecione uma célula\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_lbFiltro1MousePressed

    private void tfEanAnaliseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfEanAnaliseMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEanAnaliseMouseEntered

    private void tfEanAnaliseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfEanAnaliseMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEanAnaliseMouseExited

    private void tfEanAnaliseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEanAnaliseKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER )  { 
            
            if( !tfEanAnalise.getText().trim().equals("") ){
            
            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", tfEanAnalise.getText().trim(), "ean", new ArrayList<String>() );
        }
        else{
                        
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PESQUISAR PELO CÓDIGO EAN\n"
                    + "\nStatus Da Pesquisar:"
                    + "\nPara realizar uma pesquisa informe um código EAN\n"
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), 
            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
            
        }
    }//GEN-LAST:event_tfEanAnaliseKeyReleased

    private void jLabel22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MousePressed
        /*if( !tfEanAnalise.getText().trim().equals("") ){
            
            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap( tfEanAnalise.getText().trim(), "ean", new ArrayList<String>() );
        }
        else{
                        
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PESQUISAR PELO CÓDIGO EAN\n"
                    + "\nStatus Da Pesquisar:"
                    + "\nPara realizar uma pesquisa informe um código EAN\n"
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), 
            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }*/
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            Exportando Exportandoy = new Exportando("ANALISANDO SUGESTÃO");
            Exportandoy.setVisible(true);
            Exportandoy.pbg.setMinimum(0);
            Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );  
            
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );                 
                    
                    String estloja = "";
                    String disponi = "";
                    
                    int qtdES  = 0;
                    int qtdPED = 0;
                    
                    int L_iSUG = 0;
                    int C_iSUG = 0;
                    int count  = 0;
                
                    int vcount = 0;
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ count++;                        
                                                       
                        vcount = count + 1;
                        
                        String strn = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnName(C_i);
                    
                        if( strn.trim().equals("E/S") ){ 
                                
                            String v = "0"; try{ v = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim(); } catch( Exception e ){} 
                                
                            //System.out.println( "strn.trim().equals(\"E/S\"): " +v);
                                
                            if( !v.equals("0") ){
                                    
                                try{ qtdES = Integer.parseInt(v); } catch( Exception e ){} 
                            }
                        }
                        else if( strn.trim().equals("Q.EMB") ){
                                
                            String v = "0"; try{ v = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim(); } catch( Exception e ){} 
                                
                            if( !v.equals("0") ){
                                    
                                try{ qtdPED = Integer.parseInt(v); } catch( Exception e ){} 
                            }
                        }
                        else if( strn.trim().equals("EST. LOJA") ){
                            
                            try{ 
                                
                                estloja = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim();    
                            } catch( Exception e ){}
                        }
                        else if( strn.trim().equals("SUGESTÃO") ){
                            
                            L_iSUG = L_i;
                            C_iSUG = C_i;
                        }
                        else if( strn.trim().equals("DISPONIBILIDADE") ){
                            try{ 
                                
                                disponi = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim();  
                           
                            } catch( Exception e ){}
                        }
                        else if( vcount > Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount() ){
                            
                            try{ 
                            
                                if( estloja.equals("") || estloja.equals("null") ){ estloja="0"; }
                                if( disponi.equals("") || disponi.equals("null") ){ disponi="0"; }
                        
                                if( estloja.trim().equals("0") && !disponi.trim().equals("0") ) {
                            
                                    //TableColumn v = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumn("");
                                    double disponib2 = 0; try{ disponib2 = Double.parseDouble(disponi); } catch( Exception e ){}
                                        
                                    int sugeestaoJMA = 0;
                                    try{ sugeestaoJMA = (int) Math.round(disponib2); } catch( Exception e ){} 
                                    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                        
                                        if( qtdPED > 1 ){
                                            //System.out.println( "qtdPED: " +qtdPED);
                                            
                                            if( sugeestaoJMA < qtdPED ){
                                                
                                                Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( qtdPED , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJMA % qtdPED;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJMA / qtdPED; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdPED;
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG); }                                               
                                            }
                                        }
                                        else if( qtdES > 1 ){
                                            
                                            if( sugeestaoJMA < qtdES ){
                                                
                                                Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( qtdES , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJMA % qtdES;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJMA / qtdES; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdES;
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG); }                                               
                                            }  
                                        }
                                        else{
                                            
                                            Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                            //System.out.println( "sugeestaoJMA: " +sugeestaoJMA);
                                        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                              

                                }
                                else if( !estloja.trim().equals("0") && !disponi.trim().equals("0") ) {
                                    
                                    double estlojab = 0; try{ estlojab = Double.parseDouble(estloja); } catch( Exception e ){}
                                    double disponib = 0; try{ disponib = Double.parseDouble(disponi); } catch( Exception e ){}
                                    
                                    if( estlojab >= 0 ){
                                        
                                        if( estlojab < disponib ){
                                        
                                            double diferenca = disponib - estlojab;
                                        
                                            int sugeestaoJM  = 0; try{ sugeestaoJM = (int) Math.round(diferenca); } catch( Exception e ){} 
                                            //String v = String.valueOf( m );
                                            
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                        
                                        if( qtdPED > 1 ){
                                            //System.out.println( "qtdPED: " +qtdPED);
                                            
                                            if( sugeestaoJM < qtdPED ){
                                                
                                                Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( qtdPED , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJM % qtdPED;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJM / qtdPED; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdPED;
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG); }                                               
                                            }
                                        }
                                        else if( qtdES > 1 ){
                                            
                                            if( sugeestaoJM < qtdES ){
                                                
                                                Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( qtdES , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJM % qtdES;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJM / qtdES; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdES;
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG); }                                               
                                            }  
                                        }
                                        else{
                                            
                                            int sugeestJM  = 0; try{ sugeestJM = (int) Math.round(diferenca); } catch( Exception e ){} 
                                            Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestJM , L_iSUG, C_iSUG);
                                            //System.out.println( "disponib: " + disponib + "estloja: " + estloja + "diferenca: " +diferenca);
                                            
                                        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                        }
                                    }
                                    else {
                                        
                                        int sugeestaoJM = 0; try{ sugeestaoJM = (int) Math.round(disponib); } catch( Exception e ){}  
                                        
                                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG );
                                    }
                                }
                            } catch( Exception e ){}
                        }
                    }
                    
                }
                
                Exportandoy.fechar();
            }
            
            Thread.sleep( 1000 ); 
            // PARA CALCULAR O DDE 30 DIAS DE ITENS PROMOCIONAIS - MÉTODO NÃO UTILIZAD PARA UTILIZAR: LOGO ABAIXO:
            //Tabela_Pesquisa_BD_Estabelecimento.obterListaCalcularDdePromocionalTrintaDias();
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();
        
    }//GEN-LAST:event_jLabel22MousePressed

    private void tfEanAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEanAnaliseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEanAnaliseActionPerformed

    private void cbAnaliseParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAnaliseParametrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAnaliseParametrosActionPerformed

    private void lbFiltro2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltro2MousePressed
        if ( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedRow() != -1){
            
            int c = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedColumn();
            
            String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedRow(), Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedColumn()) );
            Tabela_Pesquisa_BD_Estabelecimento.filtroReverso( str, c );  
        }
        else{
                        
                        Class<Imagens> clazzHome = Imagens.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "FILTRO REVERSO TABELA\n"
                            
                            + "\nPara filtrar dados da tabela 1º selecione uma célula\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_lbFiltro2MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        try{
            
            if( !tfPesquisarPeloNomeProduto.getText().trim().equals("") ){
                
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                    tfSapAnalise.setText("");
                    tfEanAnalise.setText("");
                    
                    String str = tfPesquisarPeloNomeProduto.getText().toUpperCase().trim();
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", str, "mrp", new ArrayList<String>() ); 
                } catch( Exception e ){ } } }.start();        
            }
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    }//GEN-LAST:event_jLabel8MousePressed

    private void lbImportacao2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImportacao2MousePressed
        ListaTXT ListaTXT = new ListaTXT( this, Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa );
        ListaTXT.setVisible(true);
    }//GEN-LAST:event_lbImportacao2MousePressed

    private void lbExcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExcluirMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                  
            int coluna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getSelectedColumn();
            TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( coluna );
            Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
            
        } catch( Exception e ){  } } }.start();
    }//GEN-LAST:event_lbExcluirMousePressed

    private void btVisualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizar1ActionPerformed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                                  
            new ImprimirJTable( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa );
            
        } catch( Exception e ){  } } }.start();
    }//GEN-LAST:event_btVisualizar1ActionPerformed

    private void btVisualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizar2ActionPerformed
        try{
             new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
                    
                    ControleThread_Print t1 = new ControleThread_Print( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, 1, "" );
                    t1.setName("ControleThread_Print");
                    t1.start();

                } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        } catch( Exception e ){ e.printStackTrace(); }
    }//GEN-LAST:event_btVisualizar2ActionPerformed

    private void jLabel23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MousePressed
        ListaSAPAnaliseJM ListaSAPAnaliseJM = new ListaSAPAnaliseJM( "", this );
        ListaSAPAnaliseJM.setVisible(true);
    }//GEN-LAST:event_jLabel23MousePressed

    private void btVisualizar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizar3ActionPerformed
        
        excluirColunas();
        try{
             new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
                    
                    ControleThread_Print t1 = new ControleThread_Print( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, 1, "" );
                    t1.setName("ControleThread_Print");
                    t1.start();

                } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
        } catch( Exception e ){ e.printStackTrace(); }
    }//GEN-LAST:event_btVisualizar3ActionPerformed

    private void btVisualizar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizar4ActionPerformed
        
        imprimirComDadosFor();
    }//GEN-LAST:event_btVisualizar4ActionPerformed

    private void imprimirComDadosFor() {                                              
        
        excluirColunas();
        try{
            
            if( btVisualizar4.isEnabled() ){
                btVisualizar4.setEnabled(false);
                
                try { Thread.sleep( 20 ); } catch( Exception e ){}
            
                String arq   = System.getProperty("user.home") + System.getProperty("file.separator") + "JMarySystems_Print";
            
                Arquivo_Ou_Pasta.deletar_Todos_Arquivos_da_Pasta( new File( arq ) ); 
                try { Thread.sleep( 20 ); } catch( Exception e ){}
            
                 new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
                 
                    Thread.sleep( 1 );
                    Fornecedor Fornecedor = new Fornecedor();
                    try{
                        
                        String busca = JOptionPane.showInputDialog( null, "Qual o código do forneceor?", "CÓDIGO DO FORNECEDOR",JOptionPane.INFORMATION_MESSAGE );
                        if (busca == null || busca.equals("")) {
                            
                        }
                        else{
                            
                            List<Fornecedor> FornecedorListaSap = new ArrayList<Fornecedor>();
                            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Fornecedor.class, JPAUtil.em());
                    
                            List<Fornecedor> XXFornecedorListaSap = null;
                                                                 
                            XXFornecedorListaSap = (List<Fornecedor>) DAOGenericoJPA.getBeansCom_1_Parametro(Fornecedor.class, "SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEOURAZAOSOCIAL = ?", busca );
                    
                            String rbusca = ""; try{ rbusca = XXFornecedorListaSap.get(0).getNomeourazaosocial(); }catch( Exception e ){}
                    
                            if( busca.equals(rbusca) ){
                            
                                Fornecedor = XXFornecedorListaSap.get(0);
                            }
                        }
                    }catch( Exception e ){ }
                    
                    O1_Imprimir_Tabela_JM_ControleThread t1 = new O1_Imprimir_Tabela_JM_ControleThread( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, Fornecedor );
                    t1.setName("ControleThread_Print");
                    t1.start();

                    try { 
                        Thread.sleep( 10000 ); 
                        btVisualizar4.setEnabled(true);
                    } catch( Exception e ){}                    
                } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
            } 
        } catch( Exception e ){ e.printStackTrace(); }
    }
    
    Exportando Exportandoy22;
    private void jLabel24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MousePressed
             
        ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( this );
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            Exportandoy22 = new Exportando("PROCURANDO ELENCO");
            Exportandoy22.setVisible(true);
            Exportandoy22.pbg.setMinimum(0);
            
            Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica(); 
            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBELENCO", Gbelenco.class ); 
            List<Gbelenco> listTo1 = q1.getResultList();
            
            Exportandoy22.pbg.setMaximum( listTo1.size() ); 

            for(int i = 0; i < listTo1.size(); i++ ){
                
                Exportandoy22.pbg.setValue( i );  
                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                
                                    try{
                                        
                                        String busca = listTo1.get(i).getMaterial().trim();
                                        
                                        List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                        try{ 
                                            
                                            XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                                        }catch( Exception e ){ }
                                        
                                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                         
                                        if( busca.equals(rbusca) ){

                                            Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) );
                                        }   
                                        else{
                                                    
                                            String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                            ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                        }
                                    }catch( Exception e ){}
            }
            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
            
            Exportandoy22.fechar();
           
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();

        /*new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            /*Exportandoy22 = new Exportando("IMPORTAR ZRIS - GD");
            Exportandoy22.setVisible(true);
            Exportandoy22.pbg.setMinimum(0);
            Exportandoy22.pbg.setMaximum( 10 );  
            
            Exportandoy22.pbg.setValue( 5 );    
            
            Thread.sleep( 1000 );*/
            /*if( jLabel24.isEnabled() == true ) { jLabel24.setEnabled(false); Exportandoy22.fechar();
                
                JFileChooserJM JFileChooserJM = new JFileChooserJM( "  imagens    -   jmarysystems.blogspot.com.br", new String [] { "XLS" , "XLSX" } );
                String strdevolvida = JFileChooserJM.getString( 2 );
                
                tfPesquisa.setText( strdevolvida );
                
                Tabela_Pesquisa_BD_Estabelecimento.pesquisaImportacaoExcelRA(strdevolvida);
                
                jLabel24.setEnabled(true);
            }            
        } catch( Exception e ){ Exportandoy22.fechar(); jLabel24.setEnabled(true); } } }.start();*/
        
        /*try{
            ListaSAPAnaliseSet ListaSAPAnalise = new ListaSAPAnaliseSet( "gd", this );
            ListaSAPAnalise.setVisible(true);
        } catch( Exception e ){}*/
    }//GEN-LAST:event_jLabel24MousePressed

    private void jLabel25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            String tipo = "aindavaientrarempromocao";    
        
            Exportando Exportandoy = new Exportando("PESQUIS. ITENS \"PROMOCIONAIS\"");
            Exportandoy.setVisible(true);
            Exportandoy.pbg.setMinimum(0);
            Exportandoy.pbg.setMaximum( 100 );
            Exportandoy.pbg.setValue( 75 );
            
            Query qEv = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTO", Evento.class );
            List<Evento> listEv = qEv.getResultList();
                                        
            GregorianCalendar gc              = new GregorianCalendar();
            Date              dataHoje        = gc.getTime();
            List<Evento>      listEvVirgentes = new ArrayList<Evento>();
            
            for (int i = 0; i < listEv.size(); i++){
                
                if( dataHoje.before( listEv.get(i).getDataFim() ) || dataHoje.equals(listEv.get(i).getDataFim() ) ){
                    
                    if( dataHoje.before( listEv.get(i).getDataInicio() ) ){
                        //System.out.println("dataHoje: " + dataHoje + " - " + listEv.get(i).getDataInicio() );
                        
                        listEvVirgentes.add( listEv.get(i) );
                    }
                }
            }
                                      
            List<Gbproduto> GbprodutoListaSapXX = new ArrayList<Gbproduto>();
            
            for (int i = 0; i < listEvVirgentes.size(); i++){ 
                
                try{
                
                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO = ?", Eventoprodutos.class );
                    q.setParameter( 1, listEvVirgentes.get(i).getId() ); 
                    List<Eventoprodutos> listEventoprodutosxx = q.getResultList();
                                                            
                    for (int it = 0; it < listEventoprodutosxx.size(); it++){    
                        
                        Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
                        q2.setParameter( 1, listEventoprodutosxx.get(it).getItem() ); 
                        List<Gbproduto> GbprodutoSap = q2.getResultList(); 
                        
                        for (int rr = 0; rr < GbprodutoSap.size(); rr++){
                            
                            GbprodutoListaSapXX.add( GbprodutoSap.get(rr) );
                        }
                    }
                
                } catch( Exception e ){} 
            }
            
            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap2Layout( "", "", GbprodutoListaSapXX ); 
            
            Exportandoy.fechar();

        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();
    }//GEN-LAST:event_jLabel25MousePressed

    private void jLabel26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MousePressed

        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            Exportando Exportandoy = new Exportando("PESQUISANDO \"PROMOCIONAIS\"");
            Exportandoy.setVisible(true);
            Exportandoy.pbg.setMinimum(0);
            Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );  
            
            Query qEv = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTO", Evento.class );
            List<Evento> listEv = qEv.getResultList();
                
            GregorianCalendar gc              = new GregorianCalendar();
            Date              dataHoje        = gc.getTime();
            List<Evento>      listEvVirgentes = new ArrayList<Evento>();
            
            for (int i = 0; i < listEv.size(); i++){
                
                if( dataHoje.before( listEv.get(i).getDataInicio() ) ){
                    
                    listEvVirgentes.add( listEv.get(i) );
                }
            }
            
            List<Eventoprodutos> listEventoprodutosFull = new ArrayList();
            
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );                 
                    
                    String sap = "";
                    
                    for( C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){                       
                                                       
                        String strn = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnName(C_i);
                    
                        if( strn.trim().equals("MATERIAL") ){
                            
                            try{ 
                                
                                sap = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim(); 
                                
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
                
                Exportandoy.fechar();
            }
            
            //Colorir_Celula_Tabela Cell = new Colorir_Celula_Tabela(listEventoprodutosFull);
            //Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setDefaultRenderer(Object.class, Cell);
            Tabela_Pesquisa_BD_Estabelecimento.tableCellRendererCompProAzul(listEventoprodutosFull);
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();        
        
    }//GEN-LAST:event_jLabel26MousePressed

    ListaSAPNaoCadastrado ListaSAPNaoCadastrado;
    private void jLabel27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MousePressed
        
        ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( this );
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            Exportandoy22 = new Exportando("PROCURANDO PO. EXTRA");
            Exportandoy22.setVisible(true);
            Exportandoy22.pbg.setMinimum(0);
            
            Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica(); 
            
            Query q1 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA", Gbpontoextra.class ); 
            List<Gbpontoextra> listTo1 = q1.getResultList();
            
            Exportandoy22.pbg.setMaximum( listTo1.size() ); 
            

            for(int i = 0; i < listTo1.size(); i++ ){
                
                Exportandoy22.pbg.setValue( i );  
                
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                
                                    try{
                                        
                                        String busca = listTo1.get(i).getMaterial().trim();
                                        
                                        List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                        try{ 
                                            
                                            XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                                        }catch( Exception e ){ }
                                        
                                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                         
                                        if( busca.equals(rbusca) ){

                                            Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) );
                                        }   
                                        else{
                                                    
                                            String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                            ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                        }
                                    }catch( Exception e ){}
            }
            
            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
            
            Exportandoy22.fechar();
           
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "eventoBotaoImprimir(), \n" + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();

        
        /*new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            Exportandoy22 = new Exportando("IMPORTAR ZRIS - GD");
            Exportandoy22.setVisible(true);
            Exportandoy22.pbg.setMinimum(0);
            Exportandoy22.pbg.setMaximum( 10 );  
            
            Exportandoy22.pbg.setValue( 5 );    
            
            Thread.sleep( 1000 );
            if( jLabel24.isEnabled() == true ) { jLabel24.setEnabled(false); Exportandoy22.fechar();
                
                JFileChooserJM JFileChooserJM = new JFileChooserJM( "  imagens    -   jmarysystems.blogspot.com.br", new String [] { "XLS" , "XLSX" } );
                String strdevolvida = JFileChooserJM.getString( 2 );
                
                tfPesquisa.setText( strdevolvida );
                
                Tabela_Pesquisa_BD_Estabelecimento.pesquisaImportacaoExcelRA(strdevolvida);
                
                jLabel24.setEnabled(true);
            }            
        } catch( Exception e ){ Exportandoy22.fechar(); jLabel24.setEnabled(true); } } }.start();*/
    }//GEN-LAST:event_jLabel27MousePressed

    private void lbExcluir1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExcluir1MousePressed
        ListaSAPExcluirSapsTabela ListaSAPExcluirSapsTabela = new ListaSAPExcluirSapsTabela( "", this );
        ListaSAPExcluirSapsTabela.setVisible(true);
    }//GEN-LAST:event_lbExcluir1MousePressed

    private void tfEanAnalise1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfEanAnalise1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEanAnalise1MouseEntered

    private void tfEanAnalise1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfEanAnalise1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEanAnalise1MouseExited

    private void tfEanAnalise1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEanAnalise1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEanAnalise1ActionPerformed

    private void tfEanAnalise1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEanAnalise1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEanAnalise1KeyReleased

    private void jLabel35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MousePressed
        excluirCadastrarddeDisp();
    }//GEN-LAST:event_jLabel35MousePressed

    private void jLabel30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MousePressed
        /*if( !tfEanAnalise.getText().trim().equals("") ){
            
            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap( tfEanAnalise.getText().trim(), "ean", new ArrayList<String>() );
        }
        else{
                        
            Class<Imagens> clazzHome = Imagens.class;
            JOPM JOptionPaneMod = new JOPM( 1, "PESQUISAR PELO CÓDIGO EAN\n"
                    + "\nStatus Da Pesquisar:"
                    + "\nPara realizar uma pesquisa informe um código EAN\n"
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), 
            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }*/
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            Exportando Exportandoy = new Exportando("ANALISANDO SUGESTÃO");
            Exportandoy.setVisible(true);
            Exportandoy.pbg.setMinimum(0);
            Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );  
            
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );                 
                    
                    String estloja = "";
                    String disponi = "";
                    
                    int qtdES  = 0;
                    int qtdPED = 0;
                    
                    int L_iSUG = 0;
                    int C_iSUG = 0;
                    int count  = 0;
                
                    int vcount = 0;
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ count++;                        
                                                       
                        vcount = count + 1;
                        
                        String strn = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnName(C_i);
                    
                        if( strn.trim().equals("E/S") ){ 
                                
                            String v = "0"; try{ v = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim(); } catch( Exception e ){} 
                                
                            //System.out.println( "strn.trim().equals(\"E/S\"): " +v);
                                
                            if( !v.equals("0") ){
                                    
                                try{ qtdES = Integer.parseInt(v); } catch( Exception e ){} 
                            }
                        }
                        else if( strn.trim().equals("Q.EMB") ){
                                
                            String v = "0"; try{ v = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim(); } catch( Exception e ){} 
                                
                            if( !v.equals("0") ){
                                    
                                try{ qtdPED = Integer.parseInt(v); } catch( Exception e ){} 
                            }
                        }
                        else if( strn.trim().equals("EST. LOJA") ){
                            
                            try{ 
                                
                                estloja = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim();    
                            } catch( Exception e ){}
                        }
                        else if( strn.trim().equals("SUGESTÃO") ){
                            
                            L_iSUG = L_i;
                            C_iSUG = C_i;
                        }
                        else if( strn.trim().equals("DISPONIBILIDADE") ){
                            try{ 
                                
                                disponi = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim();  
                           
                            } catch( Exception e ){}
                        }
                        else if( vcount > Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount() ){
                            
                            try{ 
                            
                                if( estloja.equals("") || estloja.equals("null") ){ estloja="0"; }
                                if( disponi.equals("") || disponi.equals("null") ){ disponi="0"; }
                        
                                if( estloja.trim().equals("0") && !disponi.trim().equals("0") ) {
                            
                                    //TableColumn v = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumn("");
                                    double disponib2 = 0; try{ disponib2 = Double.parseDouble(disponi); } catch( Exception e ){}
                                        
                                    int sugeestaoJMA = 0;
                                    try{ sugeestaoJMA = (int) Math.round(disponib2); } catch( Exception e ){} 
                                    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                        
                                        if( qtdPED > 1 ){
                                            //System.out.println( "qtdPED: " +qtdPED);
                                            
                                            if( sugeestaoJMA < qtdPED ){
                                                
                                                Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( qtdPED , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJMA % qtdPED;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJMA / qtdPED; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdPED;
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG); }                                               
                                            }
                                        }
                                        else if( qtdES > 1 ){
                                            
                                            if( sugeestaoJMA < qtdES ){
                                                
                                                Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( qtdES , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJMA % qtdES;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJMA / qtdES; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdES;
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG); }                                               
                                            }  
                                        }
                                        else{
                                            
                                            Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJMA , L_iSUG, C_iSUG);
                                            //System.out.println( "sugeestaoJMA: " +sugeestaoJMA);
                                        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                              

                                }
                                else if( !estloja.trim().equals("0") && !disponi.trim().equals("0") ) {
                                    
                                    double estlojab = 0; try{ estlojab = Double.parseDouble(estloja); } catch( Exception e ){}
                                    double disponib = 0; try{ disponib = Double.parseDouble(disponi); } catch( Exception e ){}
                                    
                                    if( estlojab >= 0 ){
                                        
                                        if( estlojab < disponib ){
                                        
                                            double diferenca = disponib - estlojab;
                                        
                                            int sugeestaoJM  = 0; try{ sugeestaoJM = (int) Math.round(diferenca); } catch( Exception e ){} 
                                            //String v = String.valueOf( m );
                                            
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                        
                                        if( qtdPED > 1 ){
                                            //System.out.println( "qtdPED: " +qtdPED);
                                            
                                            if( sugeestaoJM < qtdPED ){
                                                
                                                Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( qtdPED , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJM % qtdPED;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJM / qtdPED; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdPED;
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG); }                                               
                                            }
                                        }
                                        else if( qtdES > 1 ){
                                            
                                            if( sugeestaoJM < qtdES ){
                                                
                                                Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( qtdES , L_iSUG, C_iSUG);
                                            }
                                            else{
                                                
                                                 try{ 
                                                     
                                                     double vflo = sugeestaoJM % qtdES;
                                                     
                                                     if( vflo == 0 ){
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG);
                                                     }
                                                     else{
                                                         
                                                         int b = 0; try{ b = (int) sugeestaoJM / qtdES; } catch( Exception e ){}
                                                         
                                                         b += 1;
                                                         
                                                         int vfinal = b*qtdES;
                                                         
                                                         Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( vfinal , L_iSUG, C_iSUG);
                                                     }
                                                     
                                                 } catch( Exception e ){ Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG); }                                               
                                            }  
                                        }
                                        else{
                                            int sugeestaoJM2  = 0; try{ sugeestaoJM2 = (int) Math.round(diferenca); } catch( Exception e ){} 
                                            
                                            Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM2 , L_iSUG, C_iSUG);
                                            System.out.println( "disponib: " + disponib + "estloja: " + estloja + "diferenca: " +diferenca);
                                            
                                        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                        }
                                    }
                                    else {
                                        
                                        int sugeestaoJM = 0; try{ sugeestaoJM = (int) Math.round(disponib); } catch( Exception e ){}  
                                        
                                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( sugeestaoJM , L_iSUG, C_iSUG );;
                                    }
                                }
                            } catch( Exception e ){}
                        }
                    }
                    
                }
                
                Exportandoy.fechar();
            }
            
            Thread.sleep( 1000 ); 
            // PARA CALCULAR O DDE 30 DIAS DE ITENS PROMOCIONAIS - MÉTODO NÃO UTILIZAD PARA UTILIZAR: LOGO ABAIXO:
            Tabela_Pesquisa_BD_Estabelecimento.obterListaCalcularDdePromocionalTrintaDias();
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();
    }//GEN-LAST:event_jLabel30MousePressed

    private void jLabel19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MousePressed
        try{
            
            if( !tfSapAnalise.getText().trim().equals("") ){
                
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                    tfPesquisarPeloNomeProduto.setText("");
                    tfEanAnalise.setText("");
                    
                    String str = tfSapAnalise.getText().toUpperCase().trim();
                    
                    Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap(  "", str, "produtoporfornecedor", new ArrayList<String>() ); 
                } catch( Exception e ){ } } }.start();        
            }
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    }//GEN-LAST:event_jLabel19MousePressed

    private void lbFiltro3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltro3MousePressed
        try{
            
            //if( !tfSapAnalise.getText().trim().equals("") ){
                
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                    Exportando = new Exportando("PESQUISANDO PRODUTOS");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    
                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBDIRETOLOJA", Gbdiretoloja.class );
                    List<Gbdiretoloja> Gbdiretoloja = q.getResultList();  

                    Exportando.pbg.setMaximum( 100 );
                    
                    Thread.sleep( 1 );
                      
                    Exportando.pbg.setValue( 50 );
                             
                    Thread.sleep( 50 );
                    Tabela_Pesquisa_BD_Estabelecimento.analiseDiretoLoja( Gbdiretoloja ); 
                    
                    Exportando.fechar();
                } catch( Exception e ){ Exportando.fechar(); } } }.start();        
            //}
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }      
    }//GEN-LAST:event_lbFiltro3MousePressed

    private void lbExcluir2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExcluir2MousePressed
        try{
                                    
            if( !tfSapAnalise.getText().trim().equals("") ){
                
                Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica();
                
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                    Exportando = new Exportando("PESQUISANDO PRODUTOS");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                                    
                    tfPesquisarPeloNomeProduto.setText("");
                    tfEanAnalise.setText("");
                    
                    String str = tfSapAnalise.getText().toUpperCase().trim();
                    
                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDOR WHERE CODIGOFORNECEDOR = ?", Gbprodutoporfornecedor.class );
                    q.setParameter( 1, str ); 
                    List<Gbprodutoporfornecedor> Gbprodutoporfornecedor = q.getResultList();  

                    Exportando.pbg.setMaximum( Gbprodutoporfornecedor.size() );
                    
                    Thread.sleep( 1 );
                    List<Gbproduto> GbprodutoListaSap = new ArrayList<>();
                    DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                      
                    int c = 0;
                    for (Gbprodutoporfornecedor listR1 : Gbprodutoporfornecedor) {  c++;
                        Exportando.pbg.setValue( c );
                        
                        List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                        try{ 
                                            
                            XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", listR1.getMaterial() );
                        }catch( Exception e ){ }
                        
                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                        
                        if( !rbusca.equals("") ){
                            
                            //GbprodutoListaSap.add( XXGbprodutoListaSap.get(0) );
                            Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) );
                        }
                    }
 
                    
                    Exportando.fechar();
                } catch( Exception e ){ Exportando.fechar(); } } }.start();        
            }
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    }//GEN-LAST:event_lbExcluir2MousePressed

    private void lbFiltro4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltro4MousePressed
        Relatorios Relatorios = new Relatorios( "", this );
        Relatorios.setVisible(true);
    }//GEN-LAST:event_lbFiltro4MousePressed

    private void tfPesquisarPeloNomeProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarPeloNomeProdutoKeyPressed
        if( evt.getKeyCode() == KeyEvent.VK_ENTER )  {  
             
            tfPesquisarPeloNomeProdutoControl();
            
        }
    }//GEN-LAST:event_tfPesquisarPeloNomeProdutoKeyPressed

    private void lbModeloTabelaXLSX2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbModeloTabelaXLSX2MousePressed
        ListaData ListaData = new ListaData( this, Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa );
        ListaData.setVisible(true);
    }//GEN-LAST:event_lbModeloTabelaXLSX2MousePressed

    private void btAnaliseCadastrar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAnaliseCadastrar1MousePressed
        String geral = "";  
        try{ geral = tfGeral.getText().trim(); } catch(Exception e) {}
        
        String po = "";  
        try{ po = tfPositivo.getText().trim(); } catch(Exception e) {}
        
        String ne = "";  
        try{ ne = tfNegativo.getText().trim(); } catch(Exception e) {}
        
        ExportarExcelExistenteInventario ExportarExcelExistenteInventario = new ExportarExcelExistenteInventario();
        ExportarExcelExistenteInventario.exportar( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa, "RESULTADO INVENTÁRIO", geral,po,ne  );
    }//GEN-LAST:event_btAnaliseCadastrar1MousePressed

    private void btAnaliseCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnaliseCadastrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAnaliseCadastrar1ActionPerformed

    private void lbImportacao3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImportacao3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbImportacao3MousePressed

    private void cbAnaliseCadastro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAnaliseCadastro1ActionPerformed
        String strTipoDeEvento = "";
        try{ strTipoDeEvento = cbAnaliseCadastro1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
        try{
            
            if( strTipoDeEvento.equals("PESQUISAR PELO VALOR  DA DIFERENÇA") ) {
                
                tfSapAnalise1.setVisible(true);
                jLabel41.setVisible(true);
            }
            else{
                tfSapAnalise1.setVisible(false);
                jLabel41.setVisible(false);
            }
        }
        catch( Exception e ){ System.out.println( "xxxxxxxxxx" ); e.printStackTrace(); }
    }//GEN-LAST:event_cbAnaliseCadastro1ActionPerformed

    double procValor = 0;
    private void btExcluirParametros1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirParametros1MousePressed
        String strTipoDeEvento = "";
        try{ strTipoDeEvento = cbAnaliseCadastro1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
        try{
            
            if( strTipoDeEvento.equals("PESQUISAR PELO VALOR  DA DIFERENÇA") ) {
                
                String x = ""; try{ x = tfSapAnalise1.getText().trim(); }catch( Exception e ){}
                
                if(!x.equals("")){
                    
                    double bn = 0; try{ bn = Double.parseDouble(x); }catch( Exception e ){}
                                                            
                    if(bn > 0){
                        
                        procValor = bn;
                        
                        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                        Tabela_Pesquisa_BD_Estabelecimento.inventarioSetarCabecalhoTabelaGenerica();
                    
                        po = 0;
                        ne = 0;
                        ge = 0;
                    
                        inventarioProdutosNaoContadosProcValor(procValor);
                        inventarioProdutosContadosProcValor(procValor);
                        
                        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "btAnaliseCadastrarMousePressed(), \n"
                            + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
                        }
                    else{
                        Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS INCORRETOS\n"
                    + "\nINFORME OS VALOR POSITIVO\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                    }
                }
                else{
                    
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "PARÂMETROS NÃO INFORMADOS\n"
                    + "\nINFORME OS VALOR DE PESQUISA\n"  
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(), new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            }
            else{
                valores();
            }
        }
        catch( Exception e ){ System.out.println( "xxxxxxxxxx" ); e.printStackTrace(); }
    }//GEN-LAST:event_btExcluirParametros1MousePressed

    private void valores() {                                                  
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            String strTipoDeEvento = "";
            try{ strTipoDeEvento = cbAnaliseCadastro1.getSelectedItem().toString().trim(); } catch( Exception e ){}
            
        int response = JOptionPane.showConfirmDialog(null, ""
                        + "**CONFIRMAR CONSULTA**"
                        + "\n"
                        + strTipoDeEvento
                        + "\n"
                        + "Deseja CONSULTAR O PARÂMETRO SELECIONADO?");              
        if( response == JOptionPane.YES_OPTION){ 
                          
            if( !strTipoDeEvento.equals( "" ) ){//ZVTRA_SORTEMENTO_PRODUTOS
                
                if( strTipoDeEvento.equals( "PRODUTOS CONTADOS" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.inventarioSetarCabecalhoTabelaGenerica();
                    
                    po = 0;
                    ne = 0;
                    ge = 0;
                    inventarioProdutosContados();
                }
                else if( strTipoDeEvento.equals( "PRODUTOS NÃO CONTADOS" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.inventarioSetarCabecalhoTabelaGenerica();
                    
                    po = 0;
                    ne = 0;
                    ge = 0;
                    inventarioProdutosNaoContados();
                }  
                else if( strTipoDeEvento.equals( "PRODUTOS CONTADOS E NÃO CONTADOS" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.inventarioSetarCabecalhoTabelaGenerica();
                    
                    po = 0;
                    ne = 0;
                    ge = 0;
                    inventarioProdutosNaoContados();
                    inventarioProdutosContados();
                }
                else if( strTipoDeEvento.equals( "PRODUTOS COM DIFERENÇA POSITIVA" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.inventarioSetarCabecalhoTabelaGenerica();
                    
                    po = 0;
                    ne = 0;
                    ge = 0;
                    inventarioProdutosNaoContadosDiferenPositiva();
                    inventarioProdutosContadosDiferenPositiva();
                }    
                else if( strTipoDeEvento.equals( "PRODUTOS COM DIFERENÇA NEGATIVA" ) ){
                    
                    Tabela_Pesquisa_BD_Estabelecimento.inventarioSetarCabecalhoTabelaGenerica();
                    
                    po = 0;
                    ne = 0;
                    ge = 0;
                    inventarioProdutosNaoContadosDiferenNegativa();
                    inventarioProdutosContadosDiferenNegativa();
                } 
                else{
                        
                    Class<Imagens> clazzHome = Imagens.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA DE ANÁLISES\n"
                        + "\nSelecione o Tipo de CONSULTA de análise\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
                
            }
            else{
                        
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA DE ANÁLISES\n"
                        + "\nSelecione o Tipo de CONSULTA de análise\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        }    
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "btAnaliseCadastrarMousePressed(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start();
    }
    
    double po = 0;
    double ne = 0;
    double ge = 0;
    DecimalFormat df = new DecimalFormat("#.00");
    private void inventarioProdutosContados() {                                                       
        try{      
                                                        
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06", Inventariozlmr06.class );
                    List<Inventariozlmr06> Inventariozlmr06 = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( Inventariozlmr06.size() );

                    for (int it = 0; it < Inventariozlmr06.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        String estAtual  ="0"; 
                        String qtdCnt    ="0";
                        String difer1    ="0";
                        String custoAtua ="0"; 
                        String custCont  ="0";
                        String difer2    ="0";
                        String status    ="CONTADO";
                                        
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Inventariozlmr06.get(it).getMaterial() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){
                                                                                                
                                List<Inventariomb52> XXGbProdListaSapMb52 = null;
                                try {
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Inventariomb52.class, JPAUtil.em());
                                        
                                        XXGbProdListaSapMb52 = (List<Inventariomb52>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Inventariomb52.class, 
                                            "SELECT * FROM SCHEMAJMARY.INVENTARIOMB52 WHERE MATERIAL = ?", Inventariozlmr06.get(it).getMaterial() );
                                    }catch( Exception e ){ }
                
                                    String rbuscaMb = ""; try{ rbuscaMb = XXGbProdListaSapMb52.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( !rbuscaMb.equals("") ){ 
                                        
                                        estAtual = XXGbProdListaSapMb52.get(0).getEstoque();
                                        qtdCnt   = Inventariozlmr06.get(it).getQtdcontado();
                                        
                                        double atuMb52 = 0; try{ atuMb52 = Double.valueOf(estAtual); }catch( Exception e ){}
                                        double atu06   = 0; try{ atu06   = Double.valueOf(qtdCnt);   }catch( Exception e ){}
                                        
                                        double dif = 0;
                                        if(atu06>0){
                                            
                                            if(atuMb52>atu06){
                                                dif = (atuMb52 - atu06)*-1;
                                            }
                                            else{
                                                dif = atu06 - atuMb52;
                                            }
                                        }
                                        else{
                                            dif = atuMb52 + atu06;
                                        }
                                        difer1 = String.valueOf(dif);
                                        
                                        custoAtua = XXGbProdListaSapMb52.get(0).getCustototal();
                                        custCont   = Inventariozlmr06.get(it).getCustototal();
                                        
                                        double catuX = 0; try{ catuX = Double.valueOf(custoAtua); }catch( Exception e ){}
                                        double catuY = 0; try{ catuY = Double.valueOf(custCont); }catch( Exception e ){}
                                        
                                        double dif2 = 0;
                                        if(catuY>0){
                                            
                                            if(catuX>catuY){
                                                dif2 = (catuX - catuY)*-1;
                                            }
                                            else{
                                                dif2 = catuY - catuX;
                                            }
                                        }
                                        else{
                                            dif2 = catuX + catuY;
                                        }
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            po+=dif2;
                                        }
                                        else{
                                            ne+=dif2;
                                        }
                                    }
                                    else{
                                        
                                        qtdCnt   = Inventariozlmr06.get(it).getQtdcontado();                                        
                                        difer1 = Inventariozlmr06.get(it).getQtdcontado();

                                        custCont   = Inventariozlmr06.get(it).getCustototal();  
                                        
                                        double dif2 = Double.parseDouble(Inventariozlmr06.get(it).getCustototal()); 
                                        
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            po+=dif2;
                                        }
                                        else{
                                            ne+=dif2;
                                        }
                                        
                                    }
                            
                                }catch( Exception e ){}
                                
                                Tabela_Pesquisa_BD_Estabelecimento.inventarioAnaliseGenericaTabela( "", XXGbProdListaSap.get(0), estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status ); 
                            }
                        }catch( Exception e ){}
            
                    }
                    Exportando.fechar();
                                                            
                    tfPositivo.setText( df.format(po) );
                    tfNegativo.setText( df.format(ne) );
                    
                    double x = 0; try{ x = po += ne; } catch( Exception e ){}
                    
                    tfGeral.setText( df.format(x) );
                    
                } catch( Exception e ){ 
                    e.printStackTrace(); 
                } } }.start();        
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    } 
            
    private void inventarioProdutosNaoContados() {                                                       
        try{
                                                                   
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOMB52", Inventariomb52.class );
                    List<Inventariomb52> Inventariomb52 = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( Inventariomb52.size() );

                    for (int it = 0; it < Inventariomb52.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        String estAtual  ="0"; 
                        String qtdCnt    ="0";
                        String difer1    ="0";
                        String custoAtua ="0"; 
                        String custCont  ="0";
                        String difer2    ="0";
                        String status    ="NÃO CONTADO";
                                        
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Inventariomb52.get(it).getMaterial() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){
                                                                                                
                                List<Inventariozlmr06> XXGbProdListaSapMb = null;
                                try {
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Inventariozlmr06.class, JPAUtil.em());
                                        
                                        XXGbProdListaSapMb = (List<Inventariozlmr06>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Inventariozlmr06.class, 
                                            "SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06 WHERE MATERIAL = ?", Inventariomb52.get(it).getMaterial() );
                                    }catch( Exception e ){ }
                
                                    String rbuscaMb = ""; try{ rbuscaMb = XXGbProdListaSapMb.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( rbuscaMb.equals("") ){ 
                                        
                                        estAtual = Inventariomb52.get(it).getEstoque();
                                        qtdCnt   = "0";
                                        
                                        double atuX = 0; try{ atuX = Double.valueOf(estAtual); }catch( Exception e ){}
                                        double atuY = 0; try{ atuY = Double.valueOf(qtdCnt); }catch( Exception e ){}
                                        
                                        double dif = 0;
                                        if(atuY>0){
                                            
                                            if(atuX>atuY){
                                                dif = (atuX - atuY)*-1;
                                            }
                                            else{
                                                dif = atuY - atuX;
                                            }
                                        }
                                        else{
                                            dif = atuX + atuY;
                                        }
                                        difer1 = String.valueOf(dif);
                                        
                                        custoAtua = Inventariomb52.get(it).getCustototal();
                                        custCont   = "0";
                                        
                                        double catuX = 0; try{ catuX = Double.valueOf(custoAtua); }catch( Exception e ){}
                                        double catuY = 0; try{ catuY = Double.valueOf(custCont); }catch( Exception e ){}
                                        
                                        double dif2 = 0;
                                        if(catuY>0){
                                            
                                            if(catuX>catuY){
                                                dif2 = (catuX - catuY)*-1;
                                            }
                                            else{
                                                dif2 = catuY - catuX;
                                            }
                                        }
                                        else{
                                            dif2 = catuX + catuY;
                                        }
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            po+=dif2;
                                        }
                                        else{
                                            ne+=dif2;
                                        }
                                        
                                        Tabela_Pesquisa_BD_Estabelecimento.inventarioAnaliseGenericaTabela( "", XXGbProdListaSap.get(0), estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status ); 
                                    }
                                    /*else{
                                        
                                        qtdCnt = "0";                                        
                                        difer1 = "0";

                                        custCont   = "0";  
                                        
                                        double dif2 = Double.parseDouble(Inventariomb52.get(it).getCustototal()); 
                                        
                                        difer2 = String.valueOf(dif2);
                                        
                                    }*/
                            
                                }catch( Exception e ){}                                
                                
                            }
                        }catch( Exception e ){}
            
                    }
                    Exportando.fechar();
                                                            
                    tfPositivo.setText( df.format(po) );
                    tfNegativo.setText( df.format(ne) );
                    
                    double x = 0; try{ x = po += ne; } catch( Exception e ){}
                    
                    tfGeral.setText( df.format(x) );
                    
                } catch( Exception e ){ 
                    e.printStackTrace(); 
                } } }.start();        
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    } 
   
    private void inventarioProdutosContadosDiferenPositiva() {                                                       
        try{      
                                                        
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                boolean difPositiva = false;
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06", Inventariozlmr06.class );
                    List<Inventariozlmr06> Inventariozlmr06 = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( Inventariozlmr06.size() );

                    for (int it = 0; it < Inventariozlmr06.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        String estAtual  ="0"; 
                        String qtdCnt    ="0";
                        String difer1    ="0";
                        String custoAtua ="0"; 
                        String custCont  ="0";
                        String difer2    ="0";
                        String status    ="CONTADO";
                                        
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Inventariozlmr06.get(it).getMaterial() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){
                                                                                                
                                List<Inventariomb52> XXGbProdListaSapMb52 = null;
                                try {
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Inventariomb52.class, JPAUtil.em());
                                        
                                        XXGbProdListaSapMb52 = (List<Inventariomb52>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Inventariomb52.class, 
                                            "SELECT * FROM SCHEMAJMARY.INVENTARIOMB52 WHERE MATERIAL = ?", Inventariozlmr06.get(it).getMaterial() );
                                    }catch( Exception e ){ }
                
                                    String rbuscaMb = ""; try{ rbuscaMb = XXGbProdListaSapMb52.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( !rbuscaMb.equals("") ){ 
                                        
                                        estAtual = XXGbProdListaSapMb52.get(0).getEstoque();
                                        qtdCnt   = Inventariozlmr06.get(it).getQtdcontado();
                                        
                                        double atuMb52 = 0; try{ atuMb52 = Double.valueOf(estAtual); }catch( Exception e ){}
                                        double atu06   = 0; try{ atu06   = Double.valueOf(qtdCnt);   }catch( Exception e ){}
                                        
                                        double dif = 0;
                                        if(atu06>0){
                                            
                                            if(atuMb52>atu06){
                                                dif = (atuMb52 - atu06)*-1;
                                            }
                                            else{
                                                dif = atu06 - atuMb52;
                                            }
                                        }
                                        else{
                                            dif = atuMb52 + atu06;
                                        }
                                        difer1 = String.valueOf(dif);
                                        
                                        custoAtua = XXGbProdListaSapMb52.get(0).getCustototal();
                                        custCont   = Inventariozlmr06.get(it).getCustototal();
                                        
                                        double catuX = 0; try{ catuX = Double.valueOf(custoAtua); }catch( Exception e ){}
                                        double catuY = 0; try{ catuY = Double.valueOf(custCont); }catch( Exception e ){}
                                        
                                        double dif2 = 0;
                                        if(catuY>0){
                                            
                                            if(catuX>catuY){
                                                dif2 = (catuX - catuY)*-1;
                                            }
                                            else{
                                                dif2 = catuY - catuX;
                                            }
                                        }
                                        else{
                                            dif2 = catuX + catuY;
                                        }
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            difPositiva=true;
                                            po+=dif2;
                                        }
                                        else{
                                            difPositiva=false;
                                            ne+=dif2;
                                        }
                                    }
                                    else{
                                        
                                        qtdCnt   = Inventariozlmr06.get(it).getQtdcontado();                                        
                                        difer1 = Inventariozlmr06.get(it).getQtdcontado();

                                        custCont   = Inventariozlmr06.get(it).getCustototal();  
                                        
                                        double dif2 = Double.parseDouble(Inventariozlmr06.get(it).getCustototal()); 
                                        
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            difPositiva=true;
                                            po+=dif2;
                                        }
                                        else{
                                            difPositiva=false;
                                            ne+=dif2;
                                        }
                                        
                                    }
                            
                                }catch( Exception e ){}
                                
                                if(difPositiva==true){
                                    
                                    Tabela_Pesquisa_BD_Estabelecimento.inventarioAnaliseGenericaTabela( "", XXGbProdListaSap.get(0), estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status ); 
                                }
                            }
                        }catch( Exception e ){}
            
                    }
                    Exportando.fechar();
                                                            
                    tfPositivo.setText( df.format(po) );
                    tfNegativo.setText( df.format(ne) );
                    
                    double x = 0; try{ x = po += ne; } catch( Exception e ){}
                    
                    tfGeral.setText( df.format(x) );
                    
                } catch( Exception e ){ 
                    e.printStackTrace(); 
                } } }.start();        
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    } 
    
    private void inventarioProdutosNaoContadosDiferenPositiva() {                                                       
        try{
                                                                   
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                boolean difPositiva = false;
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOMB52", Inventariomb52.class );
                    List<Inventariomb52> Inventariomb52 = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( Inventariomb52.size() );

                    for (int it = 0; it < Inventariomb52.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        String estAtual  ="0"; 
                        String qtdCnt    ="0";
                        String difer1    ="0";
                        String custoAtua ="0"; 
                        String custCont  ="0";
                        String difer2    ="0";
                        String status    ="NÃO CONTADO";
                                        
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Inventariomb52.get(it).getMaterial() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){
                                                                                                
                                List<Inventariozlmr06> XXGbProdListaSapMb = null;
                                try {
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Inventariozlmr06.class, JPAUtil.em());
                                        
                                        XXGbProdListaSapMb = (List<Inventariozlmr06>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Inventariozlmr06.class, 
                                            "SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06 WHERE MATERIAL = ?", Inventariomb52.get(it).getMaterial() );
                                    }catch( Exception e ){ }
                
                                    String rbuscaMb = ""; try{ rbuscaMb = XXGbProdListaSapMb.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( rbuscaMb.equals("") ){ 
                                        
                                        estAtual = Inventariomb52.get(it).getEstoque();
                                        qtdCnt   = "0";
                                        
                                        double atuX = 0; try{ atuX = Double.valueOf(estAtual); }catch( Exception e ){}
                                        double atuY = 0; try{ atuY = Double.valueOf(qtdCnt); }catch( Exception e ){}
                                        
                                        double dif = 0;
                                        if(atuY>0){
                                            
                                            if(atuX>atuY){
                                                dif = (atuX - atuY)*-1;
                                            }
                                            else{
                                                dif = atuY - atuX;
                                            }
                                        }
                                        else{
                                            dif = atuX + atuY;
                                        }
                                        difer1 = String.valueOf(dif);
                                        
                                        custoAtua = Inventariomb52.get(it).getCustototal();
                                        custCont   = "0";
                                        
                                        double catuX = 0; try{ catuX = Double.valueOf(custoAtua); }catch( Exception e ){}
                                        double catuY = 0; try{ catuY = Double.valueOf(custCont); }catch( Exception e ){}
                                        
                                        double dif2 = 0;
                                        if(catuY>0){
                                            
                                            if(catuX>catuY){
                                                dif2 = (catuX - catuY)*-1;
                                            }
                                            else{
                                                dif2 = catuY - catuX;
                                            }
                                        }
                                        else{
                                            dif2 = catuX + catuY;
                                        }
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            difPositiva=true;
                                            po+=dif2;
                                        }
                                        else{
                                            difPositiva=false;
                                            ne+=dif2;
                                        }
                                        
                                        if(difPositiva==true){
                                            Tabela_Pesquisa_BD_Estabelecimento.inventarioAnaliseGenericaTabela( "", XXGbProdListaSap.get(0), estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status );
                                        }
                                    }
                                    /*else{
                                        
                                        qtdCnt = "0";                                        
                                        difer1 = "0";

                                        custCont   = "0";  
                                        
                                        double dif2 = Double.parseDouble(Inventariomb52.get(it).getCustototal()); 
                                        
                                        difer2 = String.valueOf(dif2);
                                        
                                    }*/
                            
                                }catch( Exception e ){}                                
                                
                            }
                        }catch( Exception e ){}
            
                    }
                    Exportando.fechar();
                                                            
                    tfPositivo.setText( df.format(po) );
                    tfNegativo.setText( df.format(ne) );
                    
                    double x = 0; try{ x = po += ne; } catch( Exception e ){}
                    
                    tfGeral.setText( df.format(x) );
                    
                } catch( Exception e ){ 
                    e.printStackTrace(); 
                } } }.start();        
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    }
    
    private void inventarioProdutosContadosDiferenNegativa() {                                                       
        try{      
                                                        
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                boolean difPositiva = false;
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06", Inventariozlmr06.class );
                    List<Inventariozlmr06> Inventariozlmr06 = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( Inventariozlmr06.size() );

                    for (int it = 0; it < Inventariozlmr06.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        String estAtual  ="0"; 
                        String qtdCnt    ="0";
                        String difer1    ="0";
                        String custoAtua ="0"; 
                        String custCont  ="0";
                        String difer2    ="0";
                        String status    ="CONTADO";
                                        
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Inventariozlmr06.get(it).getMaterial() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){
                                                                                                
                                List<Inventariomb52> XXGbProdListaSapMb52 = null;
                                try {
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Inventariomb52.class, JPAUtil.em());
                                        
                                        XXGbProdListaSapMb52 = (List<Inventariomb52>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Inventariomb52.class, 
                                            "SELECT * FROM SCHEMAJMARY.INVENTARIOMB52 WHERE MATERIAL = ?", Inventariozlmr06.get(it).getMaterial() );
                                    }catch( Exception e ){ }
                
                                    String rbuscaMb = ""; try{ rbuscaMb = XXGbProdListaSapMb52.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( !rbuscaMb.equals("") ){ 
                                        
                                        estAtual = XXGbProdListaSapMb52.get(0).getEstoque();
                                        qtdCnt   = Inventariozlmr06.get(it).getQtdcontado();
                                        
                                        double atuMb52 = 0; try{ atuMb52 = Double.valueOf(estAtual); }catch( Exception e ){}
                                        double atu06   = 0; try{ atu06   = Double.valueOf(qtdCnt);   }catch( Exception e ){}
                                        
                                        double dif = 0;
                                        if(atu06>0){
                                            
                                            if(atuMb52>atu06){
                                                dif = (atuMb52 - atu06)*-1;
                                            }
                                            else{
                                                dif = atu06 - atuMb52;
                                            }
                                        }
                                        else{
                                            dif = atuMb52 + atu06;
                                        }
                                        difer1 = String.valueOf(dif);
                                        
                                        custoAtua = XXGbProdListaSapMb52.get(0).getCustototal();
                                        custCont   = Inventariozlmr06.get(it).getCustototal();
                                        
                                        double catuX = 0; try{ catuX = Double.valueOf(custoAtua); }catch( Exception e ){}
                                        double catuY = 0; try{ catuY = Double.valueOf(custCont); }catch( Exception e ){}
                                        
                                        double dif2 = 0;
                                        if(catuY>0){
                                            
                                            if(catuX>catuY){
                                                dif2 = (catuX - catuY)*-1;
                                            }
                                            else{
                                                dif2 = catuY - catuX;
                                            }
                                        }
                                        else{
                                            dif2 = catuX + catuY;
                                        }
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            difPositiva=true;
                                            po+=dif2;
                                        }
                                        else{
                                            difPositiva=false;
                                            ne+=dif2;
                                        }
                                    }
                                    else{
                                        
                                        qtdCnt   = Inventariozlmr06.get(it).getQtdcontado();                                        
                                        difer1 = Inventariozlmr06.get(it).getQtdcontado();

                                        custCont   = Inventariozlmr06.get(it).getCustototal();  
                                        
                                        double dif2 = Double.parseDouble(Inventariozlmr06.get(it).getCustototal()); 
                                        
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            difPositiva=true;
                                            po+=dif2;
                                        }
                                        else{
                                            difPositiva=false;
                                            ne+=dif2;
                                        }
                                        
                                    }
                            
                                }catch( Exception e ){}
                                
                                if(difPositiva==false){
                                    
                                    Tabela_Pesquisa_BD_Estabelecimento.inventarioAnaliseGenericaTabela( "", XXGbProdListaSap.get(0), estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status ); 
                                }
                            }
                        }catch( Exception e ){}
            
                    }
                    Exportando.fechar();
                                                            
                    tfPositivo.setText( df.format(po) );
                    tfNegativo.setText( df.format(ne) );
                    
                    double x = 0; try{ x = po += ne; } catch( Exception e ){}
                    
                    tfGeral.setText( df.format(x) );
                    
                } catch( Exception e ){ 
                    e.printStackTrace(); 
                } } }.start();        
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    } 
    
    private void inventarioProdutosNaoContadosDiferenNegativa() {                                                       
        try{
                                                                   
                new Thread() {   @Override public void run() { try { Thread.sleep( 100 );
                
                boolean difPositiva = false;
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOMB52", Inventariomb52.class );
                    List<Inventariomb52> Inventariomb52 = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( Inventariomb52.size() );

                    for (int it = 0; it < Inventariomb52.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        String estAtual  ="0"; 
                        String qtdCnt    ="0";
                        String difer1    ="0";
                        String custoAtua ="0"; 
                        String custCont  ="0";
                        String difer2    ="0";
                        String status    ="NÃO CONTADO";
                                        
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Inventariomb52.get(it).getMaterial() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){
                                                                                                
                                List<Inventariozlmr06> XXGbProdListaSapMb = null;
                                try {
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Inventariozlmr06.class, JPAUtil.em());
                                        
                                        XXGbProdListaSapMb = (List<Inventariozlmr06>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Inventariozlmr06.class, 
                                            "SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06 WHERE MATERIAL = ?", Inventariomb52.get(it).getMaterial() );
                                    }catch( Exception e ){ }
                
                                    String rbuscaMb = ""; try{ rbuscaMb = XXGbProdListaSapMb.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( rbuscaMb.equals("") ){ 
                                        
                                        estAtual = Inventariomb52.get(it).getEstoque();
                                        qtdCnt   = "0";
                                        
                                        double atuX = 0; try{ atuX = Double.valueOf(estAtual); }catch( Exception e ){}
                                        double atuY = 0; try{ atuY = Double.valueOf(qtdCnt); }catch( Exception e ){}
                                        
                                        double dif = 0;
                                        if(atuY>0){
                                            
                                            if(atuX>atuY){
                                                dif = (atuX - atuY)*-1;
                                            }
                                            else{
                                                dif = atuY - atuX;
                                            }
                                        }
                                        else{
                                            dif = atuX + atuY;
                                        }
                                        difer1 = String.valueOf(dif);
                                        
                                        custoAtua = Inventariomb52.get(it).getCustototal();
                                        custCont   = "0";
                                        
                                        double catuX = 0; try{ catuX = Double.valueOf(custoAtua); }catch( Exception e ){}
                                        double catuY = 0; try{ catuY = Double.valueOf(custCont); }catch( Exception e ){}
                                        
                                        double dif2 = 0;
                                        if(catuY>0){
                                            
                                            if(catuX>catuY){
                                                dif2 = (catuX - catuY)*-1;
                                            }
                                            else{
                                                dif2 = catuY - catuX;
                                            }
                                        }
                                        else{
                                            dif2 = catuX + catuY;
                                        }
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            difPositiva=true;
                                            po+=dif2;
                                        }
                                        else{
                                            difPositiva=false;
                                            ne+=dif2;
                                        }
                                        
                                        if(difPositiva==false){
                                            Tabela_Pesquisa_BD_Estabelecimento.inventarioAnaliseGenericaTabela( "", XXGbProdListaSap.get(0), estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status );
                                        }
                                    }
                                    /*else{
                                        
                                        qtdCnt = "0";                                        
                                        difer1 = "0";

                                        custCont   = "0";  
                                        
                                        double dif2 = Double.parseDouble(Inventariomb52.get(it).getCustototal()); 
                                        
                                        difer2 = String.valueOf(dif2);
                                        
                                    }*/
                            
                                }catch( Exception e ){}                                
                                
                            }
                        }catch( Exception e ){}
            
                    }
                    Exportando.fechar();
                                                            
                    tfPositivo.setText( df.format(po) );
                    tfNegativo.setText( df.format(ne) );
                    
                    double x = 0; try{ x = po += ne; } catch( Exception e ){}
                    
                    tfGeral.setText( df.format(x) );
                    
                } catch( Exception e ){ 
                    e.printStackTrace(); 
                } } }.start();        
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    }
    
    /////////////////////////////////////////////////////////////////////////////
    
    private void inventarioProdutosContadosProcValor(double valor) {                                                       
        try{      
            boolean valorAcima = false;
                                                        
                /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 100 );
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06", Inventariozlmr06.class );
                    List<Inventariozlmr06> Inventariozlmr06 = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( Inventariozlmr06.size() );

                    for (int it = 0; it < Inventariozlmr06.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        String estAtual  ="0"; 
                        String qtdCnt    ="0";
                        String difer1    ="0";
                        String custoAtua ="0"; 
                        String custCont  ="0";
                        String difer2    ="0";
                        String status    ="CONTADO";
                                        
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Inventariozlmr06.get(it).getMaterial() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){
                                                                                                
                                List<Inventariomb52> XXGbProdListaSapMb52 = null;
                                try {
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Inventariomb52.class, JPAUtil.em());
                                        
                                        XXGbProdListaSapMb52 = (List<Inventariomb52>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Inventariomb52.class, 
                                            "SELECT * FROM SCHEMAJMARY.INVENTARIOMB52 WHERE MATERIAL = ?", Inventariozlmr06.get(it).getMaterial() );
                                    }catch( Exception e ){ }
                
                                    String rbuscaMb = ""; try{ rbuscaMb = XXGbProdListaSapMb52.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( !rbuscaMb.equals("") ){ 
                                        
                                        estAtual = XXGbProdListaSapMb52.get(0).getEstoque();
                                        qtdCnt   = Inventariozlmr06.get(it).getQtdcontado();
                                        
                                        double atuMb52 = 0; try{ atuMb52 = Double.valueOf(estAtual); }catch( Exception e ){}
                                        double atu06   = 0; try{ atu06   = Double.valueOf(qtdCnt);   }catch( Exception e ){}
                                        
                                        double dif = 0;
                                        if(atu06>0){
                                            
                                            if(atuMb52>atu06){
                                                dif = (atuMb52 - atu06)*-1;
                                            }
                                            else{
                                                dif = atu06 - atuMb52;
                                            }
                                        }
                                        else{
                                            dif = atuMb52 + atu06;
                                        }
                                        difer1 = String.valueOf(dif);
                                        
                                        custoAtua = XXGbProdListaSapMb52.get(0).getCustototal();
                                        custCont   = Inventariozlmr06.get(it).getCustototal();
                                        
                                        double catuX = 0; try{ catuX = Double.valueOf(custoAtua); }catch( Exception e ){}
                                        double catuY = 0; try{ catuY = Double.valueOf(custCont); }catch( Exception e ){}
                                        
                                        double dif2 = 0;
                                        if(catuY>0){
                                            
                                            if(catuX>catuY){
                                                dif2 = (catuX - catuY)*-1;
                                            }
                                            else{
                                                dif2 = catuY - catuX;
                                            }
                                        }
                                        else{
                                            dif2 = catuX + catuY;
                                        }
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            po+=dif2;
                                        }
                                        else{
                                            ne+=dif2;
                                        }
                                    }
                                    else{
                                        
                                        qtdCnt   = Inventariozlmr06.get(it).getQtdcontado();                                        
                                        difer1 = Inventariozlmr06.get(it).getQtdcontado();

                                        custCont   = Inventariozlmr06.get(it).getCustototal();  
                                        
                                        double dif2 = Double.parseDouble(Inventariozlmr06.get(it).getCustototal()); 
                                        
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            po+=dif2;
                                            
                                            if(dif2>valor){
                                                valorAcima = true;
                                            }
                                        }
                                        else{
                                            ne+=dif2;
                                            
                                            double pos = dif2*(-1); 
                                            if(pos>valor){
                                                valorAcima = true;
                                            }
                                        }
                                        
                                    }
                            
                                }catch( Exception e ){}
                                
                                if(valorAcima==true){
                                    Tabela_Pesquisa_BD_Estabelecimento.inventarioAnaliseGenericaTabela( "", XXGbProdListaSap.get(0), estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status ); 
                                }
                            }
                        }catch( Exception e ){}
            
                    }
                    Exportando.fechar();
                                                            
                    tfPositivo.setText( df.format(po) );
                    tfNegativo.setText( df.format(ne) );
                    
                    double x = 0; try{ x = po += ne; } catch( Exception e ){}
                    
                    tfGeral.setText( df.format(x) );
                    
                } catch( Exception e ){ 
                    e.printStackTrace(); 
                } //} }.start();        
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    } 
            
    private void inventarioProdutosNaoContadosProcValor(double valor) {                                                       
        try{
            boolean valorAcima = false;
                                                                   
                /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 100 );
                
                    Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOMB52", Inventariomb52.class );
                    List<Inventariomb52> Inventariomb52 = q2.getResultList();
                    
                    Exportando Exportando = new Exportando("PROCURANDO DADOS...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( Inventariomb52.size() );

                    for (int it = 0; it < Inventariomb52.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        String estAtual  ="0"; 
                        String qtdCnt    ="0";
                        String difer1    ="0";
                        String custoAtua ="0"; 
                        String custCont  ="0";
                        String difer2    ="0";
                        String status    ="NÃO CONTADO";
                                        
                        List<Gbproduto> XXGbProdListaSap = null;
                        try {
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                    XXGbProdListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Inventariomb52.get(it).getMaterial() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbProdListaSap.get(0).getMaterial(); }catch( Exception e ){}
                
                            if( !rbusca.equals("") ){
                                                                                                
                                List<Inventariozlmr06> XXGbProdListaSapMb = null;
                                try {
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Inventariozlmr06.class, JPAUtil.em());
                                        
                                        XXGbProdListaSapMb = (List<Inventariozlmr06>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Inventariozlmr06.class, 
                                            "SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06 WHERE MATERIAL = ?", Inventariomb52.get(it).getMaterial() );
                                    }catch( Exception e ){ }
                
                                    String rbuscaMb = ""; try{ rbuscaMb = XXGbProdListaSapMb.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( rbuscaMb.equals("") ){ 
                                        
                                        estAtual = Inventariomb52.get(it).getEstoque();
                                        qtdCnt   = "0";
                                        
                                        double atuX = 0; try{ atuX = Double.valueOf(estAtual); }catch( Exception e ){}
                                        double atuY = 0; try{ atuY = Double.valueOf(qtdCnt); }catch( Exception e ){}
                                        
                                        double dif = 0;
                                        if(atuY>0){
                                            
                                            if(atuX>atuY){
                                                dif = (atuX - atuY)*-1;
                                            }
                                            else{
                                                dif = atuY - atuX;
                                            }
                                        }
                                        else{
                                            dif = atuX + atuY;
                                        }
                                        difer1 = String.valueOf(dif);
                                        
                                        custoAtua = Inventariomb52.get(it).getCustototal();
                                        custCont   = "0";
                                        
                                        double catuX = 0; try{ catuX = Double.valueOf(custoAtua); }catch( Exception e ){}
                                        double catuY = 0; try{ catuY = Double.valueOf(custCont); }catch( Exception e ){}
                                        
                                        double dif2 = 0;
                                        if(catuY>0){
                                            
                                            if(catuX>catuY){
                                                dif2 = (catuX - catuY)*-1;
                                            }
                                            else{
                                                dif2 = catuY - catuX;
                                            }
                                        }
                                        else{
                                            dif2 = catuX + catuY;
                                        }
                                        difer2 = String.valueOf(dif2);
                                        
                                        if(dif2>-1){
                                            po+=dif2;
                                            
                                            if(dif>valor){
                                                valorAcima = true;
                                            }
                                        }
                                        else{
                                            ne+=dif2;
                                            
                                            double pos = dif2*(-1); 
                                            if(pos>valor){
                                                valorAcima = true;
                                            }
                                        }
                                        
                                        if(valorAcima==true){
                                            Tabela_Pesquisa_BD_Estabelecimento.inventarioAnaliseGenericaTabela( "", XXGbProdListaSap.get(0), estAtual, qtdCnt, difer1, custoAtua, custCont, difer2, status ); 
                                        }
                                    }
                                    /*else{
                                        
                                        qtdCnt = "0";                                        
                                        difer1 = "0";

                                        custCont   = "0";  
                                        
                                        double dif2 = Double.parseDouble(Inventariomb52.get(it).getCustototal()); 
                                        
                                        difer2 = String.valueOf(dif2);
                                        
                                    }*/
                            
                                }catch( Exception e ){}                                
                                
                            }
                        }catch( Exception e ){}
            
                    }
                    Exportando.fechar();
                                                            
                    tfPositivo.setText( df.format(po) );
                    tfNegativo.setText( df.format(ne) );
                    
                    double x = 0; try{ x = po += ne; } catch( Exception e ){}
                    
                    tfGeral.setText( df.format(x) );
                    
                } catch( Exception e ){ 
                    e.printStackTrace(); 
                } //} }.start();        
            
        } catch( Exception e ){ System.out.println( "tfPesquisarPeloNomeProdutoKeyReleased" ); }
    }
    
    private void btExcluirParametros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirParametros1ActionPerformed
        
    }//GEN-LAST:event_btExcluirParametros1ActionPerformed

    private void jLabel28MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MousePressed
        PesquisarEmTodasLojas PesquisarEmTodasLojas = new PesquisarEmTodasLojas( "", this );
        PesquisarEmTodasLojas.setVisible(true);
    }//GEN-LAST:event_jLabel28MousePressed

    private void lbModeloTabelaXLSX3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbModeloTabelaXLSX3MousePressed
        ListaProduto ListaProduto = new ListaProduto( this, Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa );
        ListaProduto.setVisible(true);
    }//GEN-LAST:event_lbModeloTabelaXLSX3MousePressed

    private void tfSapAnalise1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSapAnalise1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSapAnalise1MouseEntered

    private void tfSapAnalise1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSapAnalise1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSapAnalise1MouseExited

    private void tfSapAnalise1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSapAnalise1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSapAnalise1KeyReleased

    private void jLabel41MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel41MousePressed

    private void jLabel42MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MousePressed
        ListaSAPLayout ListaSAPLayout = new ListaSAPLayout( "", this );
        ListaSAPLayout.setVisible(true);
    }//GEN-LAST:event_jLabel42MousePressed

    private void btExcluirEventoGB2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirEventoGB2MousePressed
        try { Thread.sleep( 1 ); 
                                                 
              int response = JOptionPane.showConfirmDialog(null, ""
                      + "**CADASTRAR VENDA DO EVENTO**"
                      + "\n"
                      + "Deseja cadastrar devolutiva do EVENTO selecionado?");
              
              if( response == JOptionPane.YES_OPTION){
                  
                  if( IDSELECIONADA > 0 ) {
                  
                      ListaSAPCadastrarDevolutiva ListaSAPAnaliseJM = new ListaSAPCadastrarDevolutiva( IDSELECIONADA, this );
                      ListaSAPAnaliseJM.setVisible(true);
                  }
                  else{
                    JOPM JOptionPaneMod = new JOPM( 2, "ID SELECIONADA, "
                            + "\nNenhum evento selecionado"
                            + "\nSelecione um evento"
                            + "\n", "CADASTRAR VENDA" );
                  }
              }
        } catch( Exception e ){  } 
    }//GEN-LAST:event_btExcluirEventoGB2MousePressed

    private void btExcluirEventoGB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirEventoGB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirEventoGB2ActionPerformed

    private void btAlterar7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAlterar7MousePressed
        try { Thread.sleep( 1 ); 
                                                 
              int response = JOptionPane.showConfirmDialog(null, ""
                      + "**CADASTRAR DEVOLUTIVA**"
                      + "\n"
                      + "Deseja cadastrar devolutiva do EVENTO selecionado?");
              
              if( response == JOptionPane.YES_OPTION){
                  
                  if( IDSELECIONADA > 0 ) {
                  
                      ListaSAPCadastrarDevolutiva ListaSAPCadastrarDevolutiva = new ListaSAPCadastrarDevolutiva( IDSELECIONADA, this );
                      ListaSAPCadastrarDevolutiva.setVisible(true);
                  }
                  else{
                    JOPM JOptionPaneMod = new JOPM( 2, "ID SELECIONADA, "
                            + "\nNenhum evento selecionado"
                            + "\nSelecione um evento"
                            + "\n", "CADASTRAR DEVOLUTIVA" );
                  }
              }
        } catch( Exception e ){  } 
    }//GEN-LAST:event_btAlterar7MousePressed
    
    private void btAlterar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterar7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterar7ActionPerformed

    private void btExcluirEventoGB3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirEventoGB3MousePressed
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
                                                 
              int response = JOptionPane.showConfirmDialog(null, ""
                      + "**GERAR RELATÓRIO**"
                      + "\n"
                      + "Deseja GERAR o relatório do EVENTO selecionado?");
              
              if( response == JOptionPane.YES_OPTION){
                  
                  ListaSAPCadastrarDevolutiva ListaSAPAnaliseJM = new ListaSAPCadastrarDevolutiva( IDSELECIONADA, this );
                  ListaSAPAnaliseJM.relatorioDevolutiva( "ANALISE_DEVOLUTIVA_EVENTOS" );
              }
        } catch( Exception e ){  } //} }.start();
    }//GEN-LAST:event_btExcluirEventoGB3MousePressed

    private void btExcluirEventoGB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirEventoGB3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirEventoGB3ActionPerformed

    private void jLabel43MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MousePressed
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
                                                 
            int response = JOptionPane.showConfirmDialog(null, ""
                + "**PEGAR DADOS COPIADOS DA MEMÓRIA**"
                + "\n"
                + "Deseja GERAR o relatório do EVENTO selecionado?");
              
            if( response == JOptionPane.YES_OPTION){
                
                while ( Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.getRowCount() > 0){ Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.removeRow(0); }    
                  
                ////////////////////////////////////////////////////////////////
                ClipBoard clipX = new ClipBoard();
                String contentsX = clipX.getClipboardContents();  
                
                //System.out.println( "Tudo - "+contents );
                
                StringTokenizer stX=new StringTokenizer(contentsX,"\n");
                
                ///////////////////////////////////////////////////////////////
                int countColuna = 0;
                for( int i=0; stX.hasMoreTokens(); i++ ){               
                                        
                    String rowstring = stX.nextToken();
                    StringTokenizer st2 = new StringTokenizer(rowstring,"\t");
                    for( int j=0; st2.hasMoreTokens(); j++ ){
                        
                        if( i > 0 ){ break; }
                        
                        countColuna += 1;
                        String cellstring = st2.nextToken();
                        System.out.println( "countColuna - " + countColuna + " - " + cellstring);
                    }
                                                                                                    
                }
                //System.out.println( "countColuna - " + countColuna );
                ///////////////////////////////////////////////////////////////
                
//                ClipBoard clip = new ClipBoard();
//                //String contents = clip.getClipboardContents();


                StringTokenizer st1=new StringTokenizer(contentsX,"\n");
                
                boolean primeiraVez = false;                
                for (int i = 0; st1.hasMoreTokens(); i++) {
                                            
                    String rowstring = st1.nextToken();
                    System.out.println( "rowstring - " + rowstring );

                    try{
                        if( primeiraVez == false ){
                            primeiraVez = true;
                        
                            String[] dados = new String[countColuna];
                        
                            StringTokenizer st2 = new StringTokenizer(rowstring,"\t");
                            for( int j=0; st2.hasMoreTokens(); j++ ){
                            
                                String cellstring = ""; try{ cellstring = st2.nextToken(); } catch( Exception e ){ }
                                dados [j] = cellstring;
                                System.out.println( "dados [j] - " + dados [j] );
                            }                        
                        
                            Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa = new DefaultTableModel( null, dados );
                            Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setModel(Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa);
                        
                        }
                        else{
                            
                            String[] dados = new String[countColuna];
                                     dados = rowstring.split("\t");
                            //StringTokenizer st2 = new StringTokenizer(rowstring,"\t");
                            //for( int j=0; j < countColuna; j++ ){//for( int j=0; st2.hasMoreTokens(); j++ ){
                            
                            //    String cellstring = ""; try{ cellstring = st2.nextToken(); } catch( Exception e ){ }
                            //    dados [j] = cellstring;
                            //    System.out.println( "dados [j] - " + dados [j] );
                            //}
                            
                            Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.addRow( dados ); 
                        }
                    } catch( Exception e ){ e.printStackTrace(); }
                                                
                }   

                //
                ////////////////////////////////////////////////////////////////
                  
            }
        } catch( Exception e ){  } //} }.start();
    }//GEN-LAST:event_jLabel43MousePressed

    private void lbModeloTabelaXLSX4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbModeloTabelaXLSX4MousePressed
        ListaLofDireto ListaLofDireto2 = new ListaLofDireto(this);
        ListaLofDireto2.setVisible(true);
    }//GEN-LAST:event_lbModeloTabelaXLSX4MousePressed
    
    private void excluirColunas() {                                              
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
                                  
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){           
            for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){

                for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){
                    
                    String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) );
                    
                    String strn = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnName(C_i);
                    
                    /*if( strn.trim().equals("CLASSE") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;
                    }
                    else if( strn.trim().equals("EAN") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                      
                    }
                    else*/ if( strn.trim().equals("REM.") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                            
                    }
                    else if( strn.trim().equals("SAIU CD") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                            
                    }
                    else if( strn.trim().equals("ENT. LOJA") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                            
                    }
                    else if( strn.trim().equals("SETOR") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                            
                    }
                    else if( strn.trim().equals("COD. FORNEC.") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                            
                    }
                    else if( strn.trim().equals("NOME FORNEC.") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                            
                    }
                    else if( strn.trim().equals("E/S") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                               
                    } 
                    else if( strn.trim().equals("OP. IDENTIFICADA") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                               
                    }
                    else if( strn.trim().equals("DESCRIÇÃO GRUPO COMPRA") ){
                        
                        TableColumn TColuna = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnModel().getColumn( C_i );
                        Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.removeColumn( TColuna );
                        excluirColunas();
                        break;                               
                    }
                } 
            }
        }
            
        } catch( Exception e ){  } //} }.start();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAlterar;
    public javax.swing.JButton btAlterar2;
    public javax.swing.JButton btAlterar3;
    public javax.swing.JButton btAlterar4;
    public javax.swing.JButton btAlterar5;
    public javax.swing.JButton btAlterar6;
    public javax.swing.JButton btAlterar7;
    public javax.swing.JButton btAlterar8;
    public javax.swing.JButton btAnaliseCadastrar;
    public javax.swing.JButton btAnaliseCadastrar1;
    public javax.swing.JButton btExcluir;
    public javax.swing.JButton btExcluirEventoGB;
    public javax.swing.JButton btExcluirEventoGB1;
    public javax.swing.JButton btExcluirEventoGB2;
    public javax.swing.JButton btExcluirEventoGB3;
    public javax.swing.JButton btExcluirParametros;
    public javax.swing.JButton btExcluirParametros1;
    public javax.swing.JButton btSelecionar;
    public javax.swing.JButton btVisualizar;
    public javax.swing.JButton btVisualizar1;
    public javax.swing.JButton btVisualizar2;
    public javax.swing.JButton btVisualizar3;
    public javax.swing.JButton btVisualizar4;
    public javax.swing.JComboBox cbAnaliseCadastro;
    public javax.swing.JComboBox cbAnaliseCadastro1;
    public javax.swing.JComboBox cbAnaliseParametros;
    public javax.swing.JComboBox cbEventos;
    public javax.swing.JComboBox cbEventos1;
    public javax.swing.JComboBox cbPesquisarPor1;
    public javax.swing.JComboBox cbPesquisarPor2;
    public javax.swing.JCheckBox chCelula;
    public javax.swing.JCheckBox chColuna;
    public javax.swing.JCheckBox chLinha;
    public javax.swing.JCheckBox chbDefinirPermissoes;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel jpAvancado;
    private javax.swing.JPanel jpBasico;
    private javax.swing.JPanel jpConsulta;
    private javax.swing.JPanel jpConsultarAnalise;
    private javax.swing.JPanel jpFiltroSorter1;
    private javax.swing.JPanel jpFiltroSorter2;
    private javax.swing.JPanel jpFiltroSorter3;
    private javax.swing.JPanel jpFiltroSorter4;
    private javax.swing.JPanel jpFiltroSorter5;
    private javax.swing.JPanel jpInventario;
    private javax.swing.JPanel jpPermissoes;
    private javax.swing.JPanel jpPermissoes1;
    private javax.swing.JPanel jpPermissoes2;
    private javax.swing.JPanel jpPermissoes3;
    private javax.swing.JPanel jpRelatorio;
    private javax.swing.JLabel lbAnaliseConsultaMousePressed;
    public javax.swing.JLabel lbExcluir;
    public javax.swing.JLabel lbExcluir1;
    public javax.swing.JLabel lbExcluir2;
    private javax.swing.JLabel lbExportacao;
    public javax.swing.JLabel lbFiltro;
    public javax.swing.JLabel lbFiltro1;
    public javax.swing.JLabel lbFiltro2;
    public javax.swing.JLabel lbFiltro3;
    public javax.swing.JLabel lbFiltro4;
    private javax.swing.JLabel lbImportacao;
    private javax.swing.JLabel lbImportacao1;
    private javax.swing.JLabel lbImportacao2;
    private javax.swing.JLabel lbImportacao3;
    private javax.swing.JLabel lbModeloTabelaXLSX;
    private javax.swing.JLabel lbModeloTabelaXLSX1;
    private javax.swing.JLabel lbModeloTabelaXLSX2;
    private javax.swing.JLabel lbModeloTabelaXLSX3;
    private javax.swing.JLabel lbModeloTabelaXLSX4;
    private javax.swing.JRadioButton rbMultiplo;
    private javax.swing.JRadioButton rbUnico;
    private javax.swing.JRadioButton rbUnicoComIntervalo;
    public javax.swing.JFormattedTextField tfData_Inicio_Atividades;
    public javax.swing.JFormattedTextField tfData_Inicio_Atividades1;
    public javax.swing.JTextField tfDde;
    public javax.swing.JTextField tfEanAnalise;
    public javax.swing.JTextField tfEanAnalise1;
    private javax.swing.JTextField tfGeral;
    public javax.swing.JTextField tfMix;
    private javax.swing.JTextField tfNegativo;
    public javax.swing.JTextField tfNome;
    public javax.swing.JTextField tfNome_Fantasia;
    public static javax.swing.JTextField tfPesquisa;
    public javax.swing.JTextField tfPesquisarPeloNomeProduto;
    private javax.swing.JTextField tfPositivo;
    public javax.swing.JTextField tfRazao_Social2;
    public javax.swing.JTextField tfSap;
    public javax.swing.JTextField tfSapAnalise;
    public javax.swing.JTextField tfSapAnalise1;
    public javax.swing.JTextField tfTop2000;
    public javax.swing.JTextField tfTop350;
    public javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables
    
    boolean permitirAcesso = true;
    private void configurarModoAvancado(){
        /*if( permitirAcesso == true ) {
            permitirAcesso = false;
            jpAvancado.setVisible( false );
            jpBasico.setVisible( true );
            chbDefinirPermissoes.setSelected( false );
        } else if( permitirAcesso == false ) {
            permitirAcesso = true;
            jpAvancado.setVisible( true );
            jpBasico.setVisible( false );
            chbDefinirPermissoes.setSelected(true );
        } */    
        if( jpAvancado.isVisible() ) {
            jpAvancado.setVisible( false );
            jpBasico.setVisible( true );
            chbDefinirPermissoes.setSelected( false );
        }
        else{
            jpAvancado.setVisible( true );
            jpBasico.setVisible( false );
            chbDefinirPermissoes.setSelected(true );
        }
    }
    
    private void listarTabela( Date inicio, Date fim, String strTipoDeEvento, Evento Evento ){
        Exportando Exportandov = new Exportando("CADASTRANDO");
        Exportandov.setVisible(true);
        Exportandov.pbg.setMinimum(0);
        Exportandov.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
            try{ Evento.setDataInicio(inicio);                                            }catch(Exception e ){}   
            try{ Evento.setDataFim(fim);                                                  }catch(Exception e ){}          
            try{ Evento.setNomeEvento( tfNome_Fantasia.getText().trim().toUpperCase() );  }catch(Exception e ){}
            try{ Evento.setTipoEvento( strTipoDeEvento.toUpperCase() );                   }catch(Exception e ){}
            try{ Evento.setIdUsuario(  Bean_Usuario_Logado.ID );                          }catch(Exception e ){}         

            
            Evento Evento_Cadastrado = null;
            try{ 
        
                if( verificarRepeticao( Evento ) == false ){
            
                        DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA( Evento, JPAUtil.em());
                        Evento_Cadastrado = (Evento) DAOGenericoJPA.gravanovoOUatualizar();
                    }
                    else{
            
                        Exportandov.fechar();
                        repetido();
                }
                
            }catch(Exception e ){ System.out.println("222"); e.printStackTrace(); }
            
            if( Evento_Cadastrado.getId() != null || Evento_Cadastrado.getId() > 0 ){
            
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandov.pbg.setValue( L_i );   
                    
                    Eventoprodutos Eventoprodutos = new Eventoprodutos();                
                    Eventoprodutos.setIdEvento( Evento_Cadastrado.getId() );
                    
                    boolean b = true;
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                        
                        //String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) );
                        String str = ""; try { str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim(); } catch( Exception e ){}
                        
                        switch( C_i ){
                        
                            case 0:  
                                Eventoprodutos.setItem   (str);          
                                if(str.equals("") ){
                                    
                                    b = false;
                                }
                                break;
                                
                            case 1:  Eventoprodutos.setAdicionalEmCaixas(str);     break;  
                             
                            /*case 0:  Eventoprodutos.setSetor  (str);               break;  
                            case 1:  
                                Eventoprodutos.setItem   (str);          
                                if(str.equals("") ){
                                    
                                    b = false;
                                }
                                break;
                            case 2:  Eventoprodutos.setAdicionalEmCaixas(str);     break;     
                            case 3:  Eventoprodutos.setDescricaoDoMaterial(str);   break;     
 ]]]]]]]]]]]]]]]]]]]]]]]]]] case 4:  Eventoprodutos.setViaDeAbastecimento(str);    break;*/                                                           
                            //case 2:  Eventoprodutos.setLoja   (str);               break;  
                                
                            //case 3:  Eventoprodutos.setQtdEmb (str);               break;  
                            //case 4:  Eventoprodutos.setTipoEmb(str);               break;  
                            //case 5:  Eventoprodutos.setTipoMrp(str);               break;  
                                
                            //case 6:  Eventoprodutos.setViaDeAbastecimento(str);    break;  
                            //case 7:  Eventoprodutos.setRegional(str);              break;  
                            //case 8:  Eventoprodutos.setCd(str);                    break;  
                                
                            //case 9:  Eventoprodutos.setDescricaoDoMaterial(str);   break; 
                            //case 10: Eventoprodutos.setEstoqueLojaCx(str);         break;       
                            //case 11: Eventoprodutos.setVendaMedia12SemanasCx(str); break;  
                                
                            //case 12: Eventoprodutos.setDdeLoja(str);               break; 
                            //case 13: Eventoprodutos.setAdicionalEmCaixas(str);     break;  
                            //case 14: Eventoprodutos.setQtdEmUnidades(str);         break;      
                                
                        }
                    }
                    
                    if( b == true ){
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Eventoprodutos, JPAUtil.em());
                        Eventoprodutos Eventoprodutos_Cadastrado = (Eventoprodutos) DAOGenericoJPA2.gravanovoOUatualizar();
                    }
                    
                }
                
                Exportandov.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastro de Evento\n"
                        + "\nStatus Do Cadastro:"
                        + "\nEvento cadastrado com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR EVENTO PROMOCIONAL, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR EVENTO PROMOCIONAL" );
            }
            
            
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR EVENTO PROMOCIONAL, "
                        + "\nEVENTO Não CADASTRADO"
                        + "\nID EVENTO está igual a (ZERO)."
                        + "\n", "CADASTRAR EVENTO PROMOCIONAL" );
            }
            
        }catch( Exception e ){}
    }
    
    private boolean verificarRepeticao( Evento Evento ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTO", Evento.class );
            List<Evento> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getDataInicio().equals( Evento.getDataInicio() ) ){
                    
                    if( list.get(i).getDataFim().equals( Evento.getDataFim() )  ){
                    
                        if( list.get(i).getTipoEvento().equals( Evento.getTipoEvento() ) ){
                            
                            retorno = true;
                            break;  
                        }
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    private void repetido() {
       JOPM JOptionPaneMod = new JOPM( 2, "EVENTO JÁ CADASTRADO, "
                        + "\nO EVENTO informado já existe cadastrado"
                        + "\nInforme um EVENTO ainda não cadastrado"
                        + "\n", "EVENTO" );
    }

    
    
    private void excluirEventoGB(){  
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            if( btExcluirEventoGB.isEnabled() ) {
            
                if( IDSELECIONADA > 0 ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Eventoprodutos> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.EVENTOPRODUTOS WHERE ID_EVENTO  = ?", Eventoprodutos.class );
                        q.setParameter(1, IDSELECIONADA );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando Exportando = new Exportando("EXCLUINDO EVENTO");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();                      
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    //////////////////////////////////////////////////////////////////////////////////////////
                    Evento Evento = new Evento();
                    
                    Query qx = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.EVENTO WHERE ID = ?", Evento.class );
                    qx.setParameter(1, IDSELECIONADA );   

                    List<Evento> list = qx.getResultList();
                    
                    for (int i = 0; i < list.size(); i++){
                        Evento = list.get(i);
                    } 
                    
                    DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA( Evento, JPAUtil.em()); 
                    DAOGenericoJPA.excluir();
                    //////////////////////////////////////////////////////////////////////////////////////////
 
                    btAlterar   .setEnabled(false);
                    btExcluir   .setEnabled(false);
                    btVisualizar.setEnabled(false);
                    
                    Exportando.fechar();
                }
                else{
                    JOPM JOptionPaneMod = new JOPM( 2, "ID SELECIONADA, "
                            + "\nNenhuma matéria selecionada"
                            + "\nSelecione uma matéria"
                            + "\n", "Matéria selecionada" );
                }
            }
            
            Tabela_Pesquisa_BD_Estabelecimento.mostrarLista();
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "excluir(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private void excluirGBProdutos() {  
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbproduto> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBPRODUTO", Gbproduto.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando Exportando = new Exportando("EXCLUINDO TODOS OS PRODUTOS");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();                      
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
            Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap( "", "", "nome", new ArrayList<String>() ); 
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "excluir(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private void excluirFornecedores() {  
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Fornecedor> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR", Fornecedor.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando Exportando = new Exportando("EXCLUINDO FORNECEDORES");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();                      
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
            //Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap( "", "", "nome", new ArrayList<String>() ); 
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "excluir(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
        
    private void excluirGBProdutosEstoqueCD184() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbestoquecdb184> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB184", Gbestoquecdb184.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ESTOQUE CD 184");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir(); 

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBProdutosEstoqueCD001() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbestoquecdb001> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB001", Gbestoquecdb001.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ESTOQUE CD 001");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir(); 

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBProdutosEstoqueCD289() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbestoquecdb289> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBESTOQUECDB289", Gbestoquecdb289.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ESTOQUE CD 289");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir(); 

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBProdutosEstoqueLoja() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbestoquelojab141> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141", Gbestoquelojab141.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando Exportando = new Exportando("EXCLUINDO ESTOQUE/LOJAS");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();                      
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
            //Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap( "", "", "nome", new ArrayList<String>() ); 
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "excluir(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBProdutosSuplyChain() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbsuply> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBSUPLY", Gbsuply.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PLANILHA SUPLY CHAIN");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                                                
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBDiretoLoja() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbdiretoloja> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBDIRETOLOJA", Gbdiretoloja.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO DIRETO LOJA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                                                
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBDiretoLojaEsp(String forn) {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            System.out.println("XXXXXXXXXX - "+forn);
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbdiretoloja> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBDIRETOLOJA WHERE FORNECEDOR = ?", Gbdiretoloja.class );
                        q.setParameter(1, forn);
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO DIRETO LOJA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                                                
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBProdutosCDOntem() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbentroucdontem> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBENTROUCDONTEM", Gbentroucdontem.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PLANILHA CD ONTEM");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBPedidoAtivo() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbpedidosativos> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS", Gbpedidosativos.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PLAN. 7 DIAS ATIVOS");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBRaDiario() { 
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbra> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBRA", Gbra.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PLAN. RA DIÁRIO");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirDescricaoGrupoCompra() { 
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbdescricaogrupo> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO", Gbdescricaogrupo.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO DESCRIÇÃO GRUPO COMPRA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirAtivoSemVenda() { 
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbdiassemvenda> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBDIASSEMVENDA", Gbdiassemvenda.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ATIVOS SEM VENDA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBZris() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbzris> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBZRIS", Gbzris.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PLAN. ZRIS");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirGBTodosParametros() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                
                    excluirGBProdutosEstoqueCD184();
/**/                excluirGBProdutosEstoqueLoja();
                    excluirGBZris();   
                    excluirAtivoSemVenda();
                    excluirGBRaDiario();
                    excluirGBPedidoAtivo();
                    excluirGBProdutosCDOntem();
                    excluirGBProdutosSuplyChain();
                    
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //setar CB Matéria
    private void setarCBImportarExportar(boolean alterar, Evento Evento) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.EVENTO", Evento.class );
            List<Evento> list = q.getResultList();
            
            if( cbEventos.getItemCount() > 0 ) { cbEventos.removeAllItems(); }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            for (int i = 0; i < list.size(); i++){ 
                String DataInicio = formatter.format( list.get(i).getDataInicio() );
                String DataFim = formatter.format( list.get(i).getDataFim() );
                
                String str = list.get(i).getTipoEvento()+" - "+
                        DataInicio+" - "+ DataFim+" - " + list.get(i).getNomeEvento();
                        
                cbEventos.addItem( new BookInfoEventos( 
                        list.get(i).getId(), str 
                ) );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < cbEventos.getItemCount(); i++){
                
                    BookInfoEventos obj = (BookInfoEventos) cbEventos.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Categoria.getIdMateria(): " + Categoria.getIdMateria() );
                    if( obj.ID == Evento.getId() ){
                    
                        cbEventos.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBPais(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    //ANÁLISES
    private void cadastrarAnaliseProdutos(){
        Exportando Exportandoy = new Exportando("CADASTRANDO");
        Exportandoy.setVisible(true);
        Exportandoy.pbg.setMinimum(0);
        Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );   
                    
                    Gbproduto Gbproduto = new Gbproduto();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim();
                               
                        switch( C_i ){
                        
                            case 0: Gbproduto.setMaterial   (str);   break;  
                            case 1: Gbproduto.setEan        (str);   break;  
                            case 2: Gbproduto.setDescricao  (str);   break;  
                                
                            case 3: Gbproduto.setUnd        (str);   break;  
                            case 4: Gbproduto.setSubgrupo   (str);   break;  
                            case 5: Gbproduto.setGrupo      (str);   break;      
                                
                        }
                    }
                    /*
                    if( verificarRepeticaoAnalise( Gbproduto.getMaterial().trim() ) == false ){
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbproduto, JPAUtil.em());
                        Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                    }*/
                    
                }
                
                Exportandoy.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void cadastrarProdutosZVTraSortemento(){
        Exportando Exportandoy = new Exportando("CADASTRANDO ZV");
        Exportandoy.setVisible(true);
        Exportandoy.pbg.setMinimum(0);
        Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );   
                    
                    Gbproduto Gbproduto = new Gbproduto();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = ""; 
                        try{ 
                            str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim(); 
                            if( str.equals("null") ){ str = ""; }
                        }catch( Exception e ){}
                               
                        switch( C_i ){
                        
                            case 0: Gbproduto.setMaterial   (str);   break;  
                            case 1: Gbproduto.setEan        (str);   break;  
                            case 2: Gbproduto.setDescricao  (str);   break;  
                                
                            case 3: Gbproduto.setUnd        (str);   break;  
                            case 4: Gbproduto.setGrupo      (str);   break;      
                                
                        }
                    }

                    /*if( verificarRepeticaoAnalise( Gbproduto.getMaterial().trim() ) == false ){
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbproduto, JPAUtil.em());
                        Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                    }*/
                    
                }
                
                Exportandoy.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
            
    private Gbproduto verificarRepeticaoAnalise( String material ) {  
       Gbproduto retorno=null;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
                  q.setParameter(1, material );
            List<Gbproduto> list = q.getResultList();
            
           for (Gbproduto list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   retorno = list1;
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
        
    private void cadastrarProdutosSapUndNome(){ 
        Exportando Exportandoy = new Exportando("CADASTRANDO FULL");
        Exportandoy.setVisible(true);
        Exportandoy.pbg.setMinimum(0);
        Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );   
                    
                    Gbproduto Gbproduto = new Gbproduto();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = ""; 
                        try{ 
                            str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).toUpperCase().trim(); 
                            if( str.equals("null") ){ str = ""; }
                        }catch( Exception e ){}
                               
                        switch( C_i ){
                        
                            case 0: Gbproduto.setMaterial   (str);   break;  
                            case 1: Gbproduto.setUnd        (str);   break;  
                            case 2: Gbproduto.setDescricao  (str);   break;     
                        }
                    }

                    Gbproduto GbprodutoZ = verificarRepeticaoAnalise( Gbproduto.getMaterial().trim() );
                    String x = "";  try{ x  = GbprodutoZ.getMaterial().trim(); } catch( Exception e ){}
                    
                    if ( !x.equals("") ) {
                        
                        GbprodutoZ.setUnd(Gbproduto.getUnd());
                        GbprodutoZ.setDescricao( Gbproduto.getDescricao() );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( GbprodutoZ, JPAUtil.em());
                        Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                    }
                    else{
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbproduto, JPAUtil.em());
                        Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                    }
                }
                
                Exportandoy.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void alterarProdutosSapUndNome(){ 
        Exportando Exportandoy = new Exportando("ALTERANDO FULL");
        Exportandoy.setVisible(true);
        Exportandoy.pbg.setMinimum(0);
        Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );   
                    
                    Gbproduto Gbproduto = new Gbproduto();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = ""; 
                        try{ 
                            str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).toUpperCase().trim(); 
                            if( str.equals("null") ){ str = ""; }
                        }catch( Exception e ){}
                               
                        switch( C_i ){
                        
                            case 0: Gbproduto.setMaterial   (str);   break;  
                            case 1: Gbproduto.setUnd        (str);   break;  
                            case 2: Gbproduto.setDescricao  (str);   break;     
                        }
                    }

                    /*if( verificarRepeticaoAnalise( Gbproduto.getMaterial().trim() ) == false ){
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbproduto, JPAUtil.em());
                        Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                    }*/
                    try{
            
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
                        q.setParameter(1, Gbproduto.getMaterial() );
                        List<Gbproduto> list = q.getResultList();
            
                        for (Gbproduto ProdutoList1 : list) {
                            if (ProdutoList1.getMaterial().equals(Gbproduto.getMaterial())) {
                                
                                ProdutoList1.setDescricao(Gbproduto.getDescricao());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( ProdutoList1, JPAUtil.em());
                                Gbproduto Gbproduto_Alterado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                                //System.out.println( "Material Já Cadastrado - " + material );
                                break;
                            } else {
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbproduto, JPAUtil.em());
                                Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                                //System.out.println( "Material Não Cadastrado - " + material );
                            }
                        }
                    }catch(Exception e ){} 
                    
                }
                
                Exportandoy.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirProdutoPorFornecedor2CD(String fornecedor) {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbprodutoporfornecedorcd> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDORCD WHERE CODIGOFORNECEDOR = ?", Gbprodutoporfornecedorcd.class );
                        q.setParameter( 1, fornecedor ); 
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    //Exportandof = new Exportando("EXCLUINDO PROD. X FORN. CD");
                    //Exportandof.setVisible(true);
                    //Exportandof.pbg.setMinimum(0);
                    //Exportandof.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        //Exportandof.pbg.setValue( i );
                        
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir(); 

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    //System.out.println(fornecedor);
                    //Exportandof.fechar();
            }
            
            Thread.sleep( 1 );             
        } catch( Exception e ){ /*Exportandof.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } //} }.start(); 
    }
    
    private void excluirProdutoPorFornecedor2CDTUDO() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbprodutoporfornecedorcd> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDORCD", Gbprodutoporfornecedorcd.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportandof = new Exportando("EXCLUINDO PROD. X FORN. CD");
                    Exportandof.setVisible(true);
                    Exportandof.pbg.setMinimum(0);
                    Exportandof.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportandof.pbg.setValue( i );
                        
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir(); 

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    //System.out.println(fornecedor);
                    Exportandof.fechar();
            }
            
            Thread.sleep( 1 );             
        } catch( Exception e ){ /*Exportandof.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } //} }.start(); 
    }
    
    private void cadastrarProdutoPorFornecedorCD(){ 
        Exportando Exportando_m = new Exportando("PRODUTO POR FORNECEDOR CD");
        Exportando_m.setVisible(true);
        Exportando_m.pbg.setMinimum(0);
        Exportando_m.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportando_m.pbg.setValue( L_i );   
                    
                    Gbprodutoporfornecedorcd Gbprodutoporfornecedorcd = new Gbprodutoporfornecedorcd();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbprodutoporfornecedorcd.setMaterial       (str);   break;                              
                            case 1:  Gbprodutoporfornecedorcd.setCodigofornecedor(str.toUpperCase());  break; 
                            case 2:  Gbprodutoporfornecedorcd.setNomefornecedor(str.toUpperCase());   break;                                
                        }
                    }
                    
                    try{
                
                            if( verificarRepeticaoFornecedorCD( Gbprodutoporfornecedorcd.getMaterial().trim(), Gbprodutoporfornecedorcd.getCodigofornecedor().trim() ) == false ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbprodutoporfornecedorcd, JPAUtil.em());
                                Gbprodutoporfornecedorcd GbprodutoporfornecedorCD_Cadastrado = (Gbprodutoporfornecedorcd) DAOGenericoJPA2.gravanovoOUatualizar();
                            }
                    }catch( Exception e ){}                    
                }
                
                Exportando_m.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private boolean verificarRepeticaoFornecedorCD( String material, String codFor ) {  
       boolean retorno = false;
        try{
            
            Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDORCD WHERE MATERIAL = ? AND CODIGOFORNECEDOR = ?", Gbprodutoporfornecedorcd.class );
                q2.setParameter( 1, material ); 
                q2.setParameter( 2, codFor ); 
            List<Gbprodutoporfornecedorcd> list = q2.getResultList();
            
           for (Gbprodutoporfornecedorcd list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   
                   if (list1.getCodigofornecedor().equals(codFor)) {
                       retorno = true;
                   }
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    Exportando Exportandof = null;
    private void excluirProdutoPorFornecedor2(String fornecedor) {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbprodutoporfornecedor> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDOR WHERE CODIGOFORNECEDOR = ?", Gbprodutoporfornecedor.class );
                        q.setParameter( 1, fornecedor ); 
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    //Exportandof = new Exportando("EXCLUINDO PROD. X FORN.");
                    //Exportandof.setVisible(true);
                    //Exportandof.pbg.setMinimum(0);
                    //Exportandof.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        //Exportandof.pbg.setValue( i );
                        
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir(); 

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    //System.out.println(fornecedor);
                    //Exportandof.fechar();
            }
            
            Thread.sleep( 1 );             
        } catch( Exception e ){ /*Exportandof.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } //} }.start(); 
    }
    
     private void excluirProdutoPorFornecedor2TUDO() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbprodutoporfornecedor> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDOR", Gbprodutoporfornecedor.class );                      
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportandof = new Exportando("EXCLUINDO PROD. X FORN.");
                    Exportandof.setVisible(true);
                    Exportandof.pbg.setMinimum(0);
                    Exportandof.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportandof.pbg.setValue( i );
                        
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir(); 

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    //System.out.println(fornecedor);
                    Exportandof.fechar();
            }
            
            Thread.sleep( 1 );             
        } catch( Exception e ){ /*Exportandof.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } //} }.start(); 
    }
    
    //ANÁLISES
    private void cadastrarProdutoPorFornecedor(){ 
        Exportando Exportando_m = new Exportando("PRODUTO POR FORNECEDOR");
        Exportando_m.setVisible(true);
        Exportando_m.pbg.setMinimum(0);
        Exportando_m.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportando_m.pbg.setValue( L_i );   
                    
                    Gbprodutoporfornecedor Gbprodutoporfornecedor = new Gbprodutoporfornecedor();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbprodutoporfornecedor.setMaterial       (str);   break;                              
                            case 1:  Gbprodutoporfornecedor.setCodigofornecedor(str.toUpperCase());  break; 
                            case 2:  Gbprodutoporfornecedor.setEstabelecimento(str.toUpperCase());   break;                                
                        }
                    }
                    
                    try{
                
                            if( verificarRepeticaoFornecedor( Gbprodutoporfornecedor.getMaterial().trim(), Gbprodutoporfornecedor.getCodigofornecedor().trim() ) == false ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbprodutoporfornecedor, JPAUtil.em());
                                Gbprodutoporfornecedor Gbprodutoporfornecedor_Cadastrado = (Gbprodutoporfornecedor) DAOGenericoJPA2.gravanovoOUatualizar();
                            }
                    }catch( Exception e ){}                    
                }
                
                Exportando_m.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private boolean verificarRepeticaoFornecedor( String material, String codFor ) {  
       boolean retorno = false;
        try{
            
            Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTOPORFORNECEDOR WHERE MATERIAL = ? AND CODIGOFORNECEDOR = ?", Gbprodutoporfornecedor.class );
                q2.setParameter( 1, material ); 
                q2.setParameter( 2, codFor ); 
            List<Gbprodutoporfornecedor> list = q2.getResultList();
            
           for (Gbprodutoporfornecedor list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   
                   if (list1.getCodigofornecedor().equals(codFor)) {
                       retorno = true;
                   }
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
     
    //ANÁLISES
    private void cadastrarAnaliseEstoqueCD184(){ 
        Exportando Exportando_m = new Exportando("CADASTRANDO ESTOQUE CD 184");
        Exportando_m.setVisible(true);
        Exportando_m.pbg.setMinimum(0);
        Exportando_m.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportando_m.pbg.setValue( L_i );   
                    
                    Gbestoquecdb184 Gbestoquecdb184 = new Gbestoquecdb184();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbestoquecdb184.setEstabelecimento(str);   break;
                            case 1:  Gbestoquecdb184.setMaterial   (str);   break;  
                            case 2:  
                                String t16=""; 
                                try{ 
                                    t16 = str;
                                    if( t16.equals("") || t16.equals("null") ){ t16="0"; }
                                } catch( Exception e ){} 
                                
                                String x = t16.replace(".", "");
                                String x2 = x.replace(",", ".");

                                double b = Double.parseDouble(x2);
                                int v = 0; try{ v = (int) b; } catch( Exception e ){} 
                                Gbestoquecdb184.setEstoqueCd184(v);  
                                break;                                     
                        }
                    }
                    
                    try{
                
                            if( !Gbestoquecdb184.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbestoquecdb184, JPAUtil.em());
                                Gbestoquecdb184 Gbestoquecdb184_Cadastrado = (Gbestoquecdb184) DAOGenericoJPA2.gravanovoOUatualizar();
                            }
                    }catch( Exception e ){}                    
                }
                
                Exportando_m.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    //ANÁLISES
    private void cadastrarAnaliseEstoqueCD001(){ 
        Exportando Exportando_m = new Exportando("CADASTRANDO ESTOQUE CD 001");
        Exportando_m.setVisible(true);
        Exportando_m.pbg.setMinimum(0);
        Exportando_m.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportando_m.pbg.setValue( L_i );   
                    
                    Gbestoquecdb001 Gbestoquecdb001 = new Gbestoquecdb001();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbestoquecdb001.setEstabelecimento(str);   break;
                            case 1:  Gbestoquecdb001.setMaterial   (str);   break;  
                            case 2:  
                                String t16=""; 
                                try{ 
                                    t16 = str;
                                    if( t16.equals("") || t16.equals("null") ){ t16="0"; }
                                } catch( Exception e ){} 
                                
                                String x = t16.replace(".", "");
                                String x2 = x.replace(",", ".");

                                double b = Double.parseDouble(x2);
                                int v = 0; try{ v = (int) b; } catch( Exception e ){} 
                                Gbestoquecdb001.setEstoqueCd001(v);  
                                break;                                     
                        }
                    }
                    
                    try{
                
                            if( !Gbestoquecdb001.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbestoquecdb001, JPAUtil.em());
                                Gbestoquecdb001 Gbestoquecdb001_Cadastrado = (Gbestoquecdb001) DAOGenericoJPA2.gravanovoOUatualizar();
                            }
                    }catch( Exception e ){}                    
                }
                
                Exportando_m.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    //ANÁLISES
    private void cadastrarAnaliseEstoqueCD289(){ 
        Exportando Exportando_m = new Exportando("CADASTRANDO ESTOQUE CD 289");
        Exportando_m.setVisible(true);
        Exportando_m.pbg.setMinimum(0);
        Exportando_m.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportando_m.pbg.setValue( L_i );   
                    
                    Gbestoquecdb289 Gbestoquecdb289 = new Gbestoquecdb289();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbestoquecdb289.setEstabelecimento(str);   break;
                            case 1:  Gbestoquecdb289.setMaterial   (str);   break;  
                            case 2:  
                                String t16=""; 
                                try{ 
                                    t16 = str;
                                    if( t16.equals("") || t16.equals("null") ){ t16="0"; }
                                } catch( Exception e ){} 
                                
                                String x = t16.replace(".", "");
                                String x2 = x.replace(",", ".");

                                double b = Double.parseDouble(x2);
                                int v = 0; try{ v = (int) b; } catch( Exception e ){} 
                                Gbestoquecdb289.setEstoqueCd289(v);  
                                break;                                     
                        }
                    }
                    
                    try{
                
                            if( !Gbestoquecdb289.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbestoquecdb289, JPAUtil.em());
                                Gbestoquecdb289 Gbestoquecdb289_Cadastrado = (Gbestoquecdb289) DAOGenericoJPA2.gravanovoOUatualizar();
                            }
                    }catch( Exception e ){}                    
                }
                
                Exportando_m.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarAnaliseEstoqueLoja() {  
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbestoquelojab141> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141", Gbestoquelojab141.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando Exportando = new Exportando("EXCLUINDO ESTOQUE/LOJAS");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();                      
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    
                    cadastrarAnaliseEstoqueLoja();
            }
            
            //Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap( "", "", "nome", new ArrayList<String>() ); 
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "excluir(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    //ANÁLISES
    private void cadastrarAnaliseEstoqueLoja(){ 
        Exportando Exportandob = new Exportando("CADASTRANDO ESTOQUE LOJA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbestoquelojab141 Gbestoquelojab141 = new Gbestoquelojab141();                
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim().toUpperCase();
                               
                        switch( C_i ){
                        
                            case 0:  Gbestoquelojab141.setEstabelecimento(str);   break;
                            case 1:  Gbestoquelojab141.setMaterial   (str);   break;  
                            case 2:  
                                try{
                                    
                                String t16 = ""; 
                                try{ 
                                    t16 = str;
                                    if( t16.equals("") || t16.equals("null") ){ t16="0"; }
                                } catch( Exception e ){} 
                                
                                String x = "";  try{ x  = t16.replace(".", ""); } catch( Exception e ){}
                                String x2 = ""; try{ x2 = x.replace(",", ".");  } catch( Exception e ){} 
                                
                                double b = 0; try{ b = Double.parseDouble(x2); } catch( Exception e ){}
                                int    v = 0; try{ v = (int) b;                } catch( Exception e ){} 
                                
                                Gbestoquelojab141.setEstoqueLoja(v);  
                                
                                } catch( Exception e ){}  
                                break;                                     
                        }
                    }
                    
                    try{
                        
                        if( !Gbestoquelojab141.getMaterial().equals("") ){
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbestoquelojab141, JPAUtil.em());
                            Gbestoquelojab141 Gbestoquelojab141_Cadastrado = (Gbestoquelojab141) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarAnaliseSuplyChain() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbsuply> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBSUPLY", Gbsuply.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PLANILHA SUPLY CHAIN");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                                                
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    
                    cadastrarAnaliseSuplyChain();
            }
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
        
    //ANÁLISES
    private void cadastrarAnaliseSuplyChain(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO PLANILHA SUPLY CHAIN");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbsuply Gbsuply = new Gbsuply();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0: Gbsuply.setEstabelecimento(str);   break;
                            case 1: Gbsuply.setMaterial(str.toUpperCase());                     break;                                 
                            case 2: Gbsuply.setCodFornecedorlof(str.toUpperCase());             break;   
                            case 3: Gbsuply.setNomFornecedorgrupoeconomico(str.toUpperCase());  break;  
                            case 4: 
                                try{ str = str.replace(" ", ""); }catch(Exception e){}
                                Gbsuply.setNomClasse2(str.toUpperCase());                   
                                break; 
                            /*
                                //case 2: Gbsuply.setNomSetor(str.toUpperCase());                     break;
                                
                                case 5:  
                                try{
                                    
                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                List<Gbmrp> XXGbprodutoListaSap = null;
                                
                                try{ 
                                    
                                    XXGbprodutoListaSap = (List<Gbmrp>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbmrp.class, "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ?", Gbsuply.getMaterial() );
                                }catch( Exception e ){ }
                                
                                String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                
                                if( Gbsuply.getMaterial().equals(rbusca) ){
                                    
                                    XXGbprodutoListaSap.get(0).setMrp(str.toUpperCase());
                                    
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXGbprodutoListaSap.get(0), JPAUtil.em());
                                    Gbmrp Gbmrp_Cadastrado = (Gbmrp) DAOGenericoJPA2.gravanovoOUatualizar();
                                }
                                else{
                                    
                                   Gbmrp Gbmrp = new Gbmrp();
                                   Gbmrp.setEstabelecimento(Gbsuply.getEstabelecimento());
                                   Gbmrp.setMaterial(       Gbsuply.getMaterial()       );
                                   Gbmrp.setMrp(            str.toUpperCase()           );
                                   
                                   DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbmrp, JPAUtil.em());
                                   Gbmrp Gbmrp_Cadastrado = (Gbmrp) DAOGenericoJPA2.gravanovoOUatualizar();
                                }
                                          
                                } catch( Exception e ){}  
                                break;     
                            case 6: Gbsuply.setCodFornecedorlof(str.toUpperCase());             break;         
                            case 7: Gbsuply.setStsEstoquetipo(str.toUpperCase());               break; */   
                        }
                    }
                    
                    try{                
                            if( !Gbsuply.getMaterial().equals("") ){

                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbsuply, JPAUtil.em());
                                Gbsuply Gbsuply_Cadastrado = (Gbsuply) DAOGenericoJPA2.gravanovoOUatualizar();                                
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    
    //ANÁLISES
    private void cadastrarAnaliseZris(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO ZRIS MRP QTDXEMB");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbproduto Gbproduto = new Gbproduto();
                    
                    Gbzris Gbzris = new Gbzris();    
                    
                    Gbestoquelojab141 Gbestoquelojab141 = new Gbestoquelojab141();
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim().toUpperCase();
                               
                        switch( C_i2 ){
                        
                            case 0:  
                                Gbestoquelojab141.setEstabelecimento(str);  
                                Gbzris.setEstabelecimento(str.toUpperCase());  
                                break;
                            case 1:  
                                Gbestoquelojab141.setMaterial   (str);
                                Gbzris.setMaterial(str.toUpperCase());       
                                break;  
                            case 2:  
                                String t16=""; 
                                try{ 
                                    t16 = str;
                                    if( t16.equals("") || t16.equals("null") ){ t16="0"; }
                                } catch( Exception e ){} 
                                
                                String xest = t16.replace(".", "");
                                String x2est = xest.replace(",", "."); 
                                
                                double best = Double.parseDouble(x2est);
                                int vest = 0; try{ vest = (int) best; } catch( Exception e ){} 
                                
                                try { 
                                    Gbestoquelojab141.setEstoqueLoja(vest);
                                    
                                    Thread.sleep( 1 ); 
                                    
                                    List<Gbestoquelojab141> XXGbestoquelojab141ListaSap = null;
                                    try{ 
                                        
                                        Query q3 = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBESTOQUELOJAB141 WHERE ESTABELECIMENTO = ? AND ( SCHEMAJMARY.GBESTOQUELOJAB141.MATERIAL=? )", Gbestoquelojab141.class );           
                                        q3.setParameter(1, Gbestoquelojab141.getEstabelecimento() );
                                        q3.setParameter(2, Gbestoquelojab141.getMaterial() );
                                        XXGbestoquelojab141ListaSap = q3.getResultList();
                                    }catch( Exception e ){ }
                                    
                                    String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial().trim(); }catch( Exception e ){}
                                    
                                    if( Gbestoquelojab141.getMaterial().equals(rbusca) ){
                                            
                                        XXGbestoquelojab141ListaSap.get(0).setEstabelecimento(Gbestoquelojab141.getEstabelecimento());
                                        XXGbestoquelojab141ListaSap.get(0).setMaterial(       Gbestoquelojab141.getMaterial());
                                        XXGbestoquelojab141ListaSap.get(0).setEstoqueLoja(    Gbestoquelojab141.getEstoqueLoja());
                                        
                                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXGbestoquelojab141ListaSap.get(0), JPAUtil.em());
                                        Gbestoquelojab141 Gbestoquelojab141_Cadastrado = (Gbestoquelojab141) DAOGenericoJPA2.gravanovoOUatualizar();
                                    }
                                    else{
                                        
                                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbestoquelojab141, JPAUtil.em());
                                        Gbestoquelojab141 Gbestoquelojab141_Cadastrado = (Gbestoquelojab141) DAOGenericoJPA2.gravanovoOUatualizar();
                                    } 
                                    
                                } catch( Exception e ){}  
                                
                                break;      
                            case 3:  
                                try{
                                    
                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbmrp.class, JPAUtil.em());
                                List<Gbmrp> XXGbprodutoListaSap = null;
                                
                                try{ 
                                        
                                    XXGbprodutoListaSap = (List<Gbmrp>) DAOGenericoJPA.getBeansCom_2_Parametro(Gbmrp.class, 
                                        "SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBMRP.ESTABELECIMENTO = ? )", Gbzris.getMaterial(), Gbzris.getEstabelecimento() );
                                }catch( Exception e ){ }  
                                //////////////////////////////////////
                                
                                String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial().trim(); }catch( Exception e ){}
                                String rbusca2 = ""; try{ rbusca2 = XXGbprodutoListaSap.get(0).getEstabelecimento().trim(); }catch( Exception e ){}
                                
                                System.out.println("rbusca - "+rbusca +" - " + "rbusca - "+rbusca2);
                                if( Gbzris.getMaterial().equals(rbusca) && Gbzris.getEstabelecimento().equals(rbusca2) ){
                                    System.out.println("1");
                                    
                                    XXGbprodutoListaSap.get(0).setMrp(str.toUpperCase());
                                    
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXGbprodutoListaSap.get(0), JPAUtil.em());
                                    Gbmrp Gbmrp_Cadastrado = (Gbmrp) DAOGenericoJPA2.gravanovoOUatualizar();
                                }
                                else{
                                    System.out.println("2");
                                    
                                    Gbmrp Gbmrp = new Gbmrp();
                                    Gbmrp.setEstabelecimento(Gbzris.getEstabelecimento());
                                    Gbmrp.setMaterial(       Gbzris.getMaterial()       );
                                    Gbmrp.setMrp(            str.toUpperCase()           );                                   
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbmrp, JPAUtil.em());
                                    Gbmrp Gbmrp_Cadastrado = (Gbmrp) DAOGenericoJPA2.gravanovoOUatualizar();
                                }
                                  
                                } catch( Exception e ){}  
                                break;   
                            case 4:  Gbzris.setQtdxemb(str.toUpperCase());        break;     
                            case 5:  
                                
                                try { 
                                    /*
                                    Thread.sleep( 1 ); 
                                    
                                    List<Gbproduto> XXGbestoquelojab141ListaSap = null;
                                    try{ 
                                        DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                        
                                        XXGbestoquelojab141ListaSap = (List<Gbproduto>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbzris.getMaterial() );
                                    }catch( Exception e ){ }
                                    
                                    String rbusca = ""; try{ rbusca = XXGbestoquelojab141ListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                    
                                    if( Gbzris.getMaterial().equals(rbusca) ){
                                            
                                        XXGbestoquelojab141ListaSap.get(0).setUnd( str.toUpperCase());
                                        
                                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXGbestoquelojab141ListaSap.get(0), JPAUtil.em());
                                        Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                                    }
                                    */
                                } catch( Exception e ){}  
                                          
                                break; 
                            case 6:                                  
                                String bb  = "0"; try{ bb  = str.replace(".", ""); } catch( Exception e ){}
                                String bb2 = "0"; try{ bb2 = bb.replace(",", "."); } catch( Exception e ){}
                                
                                double tt = 0; try{ tt = Double.parseDouble(bb2); } catch( Exception e ){} 
                                int     f = 0; try{ f = (int) Math.ceil(tt); } catch( Exception e ){} 
                                
                                if( f <= 0 ){
                                    Gbzris.setVendaSemanaAnterior( 1 );  
                                }else{ 
                                    Gbzris.setVendaSemanaAnterior( f );  
                                }                                
                                break;  
                            case 7:  
                                String ww = str.replace(".", "");
                                String ww2 = ww.replace(",", "."); 
                                
                                double uu = 0; try{ uu = Double.parseDouble(ww2); } catch( Exception e ){} 
                                int     a = 0; try{ a = (int) Math.ceil(uu); } catch( Exception e ){} 
                                
                                if( a <= 0 ){
                                    Gbzris.setVenda4Semanas( 1 ); 
                                }else{
                                    Gbzris.setVenda4Semanas( a ); 
                                }
                                break;  
                            case 8:  
                                String kk = str.replace(".", "");
                                String kk2 = kk.replace(",", "."); 
                                
                                double hh = 0; try{ hh = Double.parseDouble(kk2); } catch( Exception e ){} 
                                int m = 0; try{ m = (int) Math.ceil(hh); } catch( Exception e ){} 
                                
                                if( m <= 0 ){
                                    Gbzris.setVenda12Semanas( 1 ); 
                                }else{
                                    Gbzris.setVenda12Semanas( m ); 
                                }
                                break;
                            case 9:  
                                if( str.equals("") || str.equals("null") ){ str="0"; }
                                
                                String ss = str.replace(".", "");
                                String ss2 = ss.replace(",", "."); 
                                
                                double fg = 0; try{ fg = Double.parseDouble(ss2); } catch( Exception e ){} 
                                int hb = 0; try{ hb = (int) Math.ceil(fg); } catch( Exception e ){} 
                                
                                Gbzris.setDde( hb );  
                                
                                ///////////////////////////////////////////////////////// 
                                // CÁLCULO DA DISPONIBILIDADE
                                double v1 = (double)( Gbzris.getVendaSemanaAnterior() );
                                double v2 = (double)( Gbzris.getVenda4Semanas()       );
                                double v3 = (double)( Gbzris.getVenda12Semanas()      );
                                
                                double v1b = (v1/7)*15;
                                double v2b = (v2/7)*15;
                                double v3b = (v3/7)*15;
                                
                                if (  v1b >= v2b && v1b >= v3b ){
                                    
                                    double v1x   = Math.ceil( v1b );
                                    Gbzris.setDisponibilidade( (int) v1x );
                                }
                                else if (  v2b >= v1b && v2b >= v3b ){
                                    
                                    double v2x   = Math.ceil( v2b );
                                    Gbzris.setDisponibilidade( (int) v2x );
                                }
                                else if (  v3b >= v1b && v3b >= v2b ){
                                    
                                    double v3x   = Math.ceil( v3b );
                                    Gbzris.setDisponibilidade( (int) v3x );
                                }
                                /////////////////////////////////////////////////////////  
                                break;     
                            
                            case 10:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", "."); 
                                
                                double b = 0;  try{ b = Double.parseDouble(x2); } catch( Exception e ){} 
                                int    v = 0;  try{ v = (int) b; } catch( Exception e ){} 
                                Gbzris.setEstoqueMinimo( v );                   
                                break;  
                            
                            case 11:  
                                String custo = ""; try{ custo = str.trim(); } catch( Exception e ){} 
                                Gbzris.setPrecocusto(custo);  
                                break; 
                                
                            case 12:  
                                String cluster = ""; try{ cluster = str.trim(); } catch( Exception e ){} 
                                Gbzris.setCluster(cluster);  
                                break;     
                        }
                    }
                    
                    try{     
                
                            if( !Gbzris.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbzris, JPAUtil.em());
                                Gbzris Gbzris_Cadastrado = (Gbzris) DAOGenericoJPA2.gravanovoOUatualizar();
                         
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    //cadastrarMb52
    private void cadastrarMb52(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO MB52");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    
                    LojasAMb52 LojasAMb52 = new LojasAMb52();
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = ""; try{ str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim().toUpperCase(); }catch( Exception e ){}
                               
                        switch( C_i2 ){
                        
                            case 0:  
                                LojasAMb52.setLoja(str);  
                                break;
                            case 1:  
                                LojasAMb52.setDep(str);       
                                break;  
                            case 2:  
                                LojasAMb52.setMaterial(str);    
                                break;      
                            case 3:  
                                LojasAMb52.setDescricao(str);  
                                break;   
                            case 4:  
                                LojasAMb52.setUtilizacaoLivre(str);  
                                ////////////////////////////////////////////////
                                String kk = str.replace(".", "");
                                String kk2 = kk.replace(",", "."); 
                                
                                double hh = 0; try{ hh = Double.parseDouble(kk2); } catch( Exception e ){} 
                                //int m = 0; try{ m = (int) Math.ceil(hh); } catch( Exception e ){} 
                                
                                if( hh < 0 ){
                                    LojasAMb52.setStatusEstoque("NEGATIVO");  
                                }else if( hh > 0 ){
                                    LojasAMb52.setStatusEstoque("POSITIVO");  
                                }else{
                                    LojasAMb52.setStatusEstoque("ZERO");  
                                }
                                break;     
                            case 5:  
                                LojasAMb52.setUmb(str);          
                                break; 
                            case 6:                                  
                                LojasAMb52.setValorUtilizacaoLivre(str);                                    
                                break;  
                            case 7:  
                                LojasAMb52.setGrpMerc(str);     
                                break;      
                        }
                    }
                    
                    try{     
                
                            if( !LojasAMb52.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( LojasAMb52, JPAUtil.em());
                                LojasAMb52 LojasAMb52_Cadastrado = (LojasAMb52) DAOGenericoJPA2.gravanovoOUatualizar();
                         
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarLojasAMb52() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<LojasAMb52> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.LOJAS_A_MB52", LojasAMb52.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO LojasAMb52");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    
                    cadastrarMb52();
            }
            
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //cadastrarLojasAZdadoslogCd
    private void cadastrarLojasAZdadoslogCd(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO ZDADOSLOG_CD");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    
                    LojasAZdadoslogCd LojasAZdadoslogCd = new LojasAZdadoslogCd();
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = ""; try{ str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim().toUpperCase(); }catch( Exception e ){}
                               
                        switch( C_i2 ){
                        
                            case 0:  
                                LojasAZdadoslogCd.setLoja(str);  
                                break;
                            case 1:  
                                LojasAZdadoslogCd.setMaterial(str);       
                                break;  
                            case 2:  
                                LojasAZdadoslogCd.setPosDpstZdadoslog(str);    
                                break;      
                            case 3:  
                                LojasAZdadoslogCd.setPerfDistZdadoslog(str);  
                                break;   
                            case 4:  
                                LojasAZdadoslogCd.setFonecedor1Zdadoslog(str);  
                                break;     
                            case 5:  
                                LojasAZdadoslogCd.setFonecedor2Zdadoslog(str);          
                                break;   
                        }
                    }
                    
                    try{     
                
                            if( !LojasAZdadoslogCd.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( LojasAZdadoslogCd, JPAUtil.em());
                                LojasAZdadoslogCd LojasAZdadoslogCd_Cadastrado = (LojasAZdadoslogCd) DAOGenericoJPA2.gravanovoOUatualizar();
                         
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarLojasAZdadoslogCd() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<LojasAZdadoslogCd> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZDADOSLOG_CD", LojasAZdadoslogCd.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ZdadoslogCd");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    
                    cadastrarLojasAZdadoslogCd();
            }
            
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //cadastrarLojasAZdadoslogLoja
    private void cadastrarLojasAZdadoslogLoja(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO ZDADOSLOG_LOJA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    
                    LojasAZdadoslogLoja LojasAZdadoslogLoja = new LojasAZdadoslogLoja();
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = ""; try{ str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim().toUpperCase(); }catch( Exception e ){}
                               
                        switch( C_i2 ){
                        
                            case 0:  
                                LojasAZdadoslogLoja.setLoja(str);  
                                break;
                            case 1:  
                                LojasAZdadoslogLoja.setMaterial(str);       
                                break;  
                            case 2:  
                                LojasAZdadoslogLoja.setGcmZdadoslog(str);    
                                break;       
                            case 3:  
                                LojasAZdadoslogLoja.setFonecedor1Zdadoslog(str);  
                                break;     
                            case 4:  
                                LojasAZdadoslogLoja.setFonecedor2Zdadoslog(str);          
                                break;   
                        }
                    }
                    
                    try{     
                
                            if( !LojasAZdadoslogLoja.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( LojasAZdadoslogLoja, JPAUtil.em());
                                LojasAZdadoslogLoja LojasAZdadoslogLoja_Cadastrado = (LojasAZdadoslogLoja) DAOGenericoJPA2.gravanovoOUatualizar();
                         
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarLojasAZdadoslogLoja() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<LojasAZdadoslogLoja> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZDADOSLOG_LOJA", LojasAZdadoslogLoja.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ZdadoslogLoja");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    
                    cadastrarLojasAZdadoslogLoja();
            }
            
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //cadastrarLojasAZvtraSort
    private void cadastrarLojasAZvtraSort(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO ZVTRA_SORT");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    
                    LojasAZvtraSort LojasAZvtraSort = new LojasAZvtraSort();
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = ""; try{ str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim().toUpperCase(); }catch( Exception e ){}
                               
                        switch( C_i2 ){
                        
                            case 0:  
                                LojasAZvtraSort.setLoja(str);  
                                break;
                            case 1:  
                                LojasAZvtraSort.setMaterial(str);       
                                break;  
                            case 2:  
                                LojasAZvtraSort.setMrpZvtra(str);    
                                break;       
                            case 3:  
                                LojasAZvtraSort.setSmZvtra(str);
                                ////////////////////////////////////////////////
                                String kk = "";  try{ kk  = str.replace(".", ""); } catch( Exception e ){} 
                                String kk2 = ""; try{ kk2 = kk.replace(",", "."); } catch( Exception e ){} 
                                
                                int hh = 0; try{ hh = Integer.parseInt(kk2); } catch( Exception e ){} 

                                if( hh > 0 ){
                                    LojasAZvtraSort.setStatusBloqueio("BLOQUEADO");  
                                }else{
                                    LojasAZvtraSort.setStatusBloqueio("ATIVO");  
                                }
                                break;      
                        }
                    }
                    
                    try{     
                
                            if( !LojasAZvtraSort.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( LojasAZvtraSort, JPAUtil.em());
                                LojasAZvtraSort LojasAZvtraSort_Cadastrado = (LojasAZvtraSort) DAOGenericoJPA2.gravanovoOUatualizar();
                         
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarLojasAZvtraSort() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<LojasAZvtraSort> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.LOJAS_A_ZVTRA_SORT", LojasAZvtraSort.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ZVTRA_SORT");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    
                    cadastrarLojasAZvtraSort();
            }
            
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }

    private void excluirCadastrarAnaliseCDOntem() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbentroucdontem> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBENTROUCDONTEM", Gbentroucdontem.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PLANILHA CD ONTEM");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    
                    cadastrarAnaliseCDOntem();
            }
            
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //ANÁLISES
    /*private void cadastrarAnaliseCDOntem(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO ENTRADA CD");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbentroucdontem Gbentroucdontem = new Gbentroucdontem();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbentroucdontem.setEstabelecimento(str);   break;
                            case 1:  Gbentroucdontem.setMaterial(str.toUpperCase());          break;  
                            case 2: 
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                Gbentroucdontem.setEntrouCd184Ontem(x2.toUpperCase());  
                                break;    
                            
                        }
                    }
                    
                    try{
                        
                        if(  !Gbentroucdontem.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbentroucdontem, JPAUtil.em());
                                Gbentroucdontem Gbproduto_Cadastrado = (Gbentroucdontem) DAOGenericoJPA2.gravanovoOUatualizar();
                             
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }*/
    
    //ANÁLISES
    private void cadastrarAnaliseCDOntem(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO ENTRADA CD");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbentroucdontem Gbentroucdontem = new Gbentroucdontem();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) );
                               
                        switch( C_i2 ){
                        
                            case 0:  Gbentroucdontem.setEstabelecimento(str);          break;
                            case 1:  Gbentroucdontem.setMaterial(str.toUpperCase());   break;  
                            case 2:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                Gbentroucdontem.setEntrouCd184Ontem(x2.toUpperCase());        
                                break;                                  
                        }
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBENTROUCDONTEM WHERE MATERIAL = ?", Gbentroucdontem.class );
                        q.setParameter(1, Gbentroucdontem.getMaterial() );
                        List<Gbentroucdontem> list = q.getResultList();
                        
                        String rbusca = ""; try{ rbusca = list.get(0).getMaterial(); }catch( Exception e ){}
                        
                        if( !rbusca.equals("") ){
                                             
                                String t1 = ""; 
                                try{ 
                                    t1 = Gbentroucdontem.getEntrouCd184Ontem();
                                    if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                                    double b  = Double.parseDouble( t1 );
                                    
                                    String t2 = list.get(0).getEntrouCd184Ontem();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b2 = Double.parseDouble( t2 );
                                    
                                    double x = b + b2;
                                    
                                    t1 = String.valueOf(x);
                                    System.out.println(" Double b - " + t1 );
                                } catch( Exception e ){} 
                                
                                list.get(0).setEntrouCd184Ontem( t1 );
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(0), JPAUtil.em());
                                Gbpedidosativos Gbproduto_Cadastrado = (Gbpedidosativos) DAOGenericoJPA2.gravanovoOUatualizar();
                                //break; 
                        }    
                        else{
                                   
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbentroucdontem, JPAUtil.em());
                            Gbentroucdontem Gbentroucdontem2 = (Gbentroucdontem) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirOportSemana() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbooportsemana> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBOOPORTSEMANA", Gbooportsemana.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO OPORT. SEMANA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirPontoExtra() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbpontoextra> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBPONTOEXTRA", Gbpontoextra.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PONTO EXTRA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluirElenco() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbelenco> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBELENCO", Gbelenco.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ELENCO");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //ANÁLISES
    private void cadastrarAnaliseped_Ativo(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO PEDIDO ATIVO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbpedidosativos Gbpedidosativos = new Gbpedidosativos();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).toUpperCase().trim();
                               
                        switch( C_i2 ){
                        
                            case 0:  Gbpedidosativos.setNumepedido(str);               break;
                            case 1:  Gbpedidosativos.setEstabelecimento(str);          break;
                            case 2:  Gbpedidosativos.setCodfornec(      str);          break;    
                            case 3:  Gbpedidosativos.setMaterial(str);                 break;  
                            case 4:  Gbpedidosativos.setEs(str);                       break;      
                            case 5:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                Gbpedidosativos.setPedidoCd184Ativo(x2.toUpperCase());        
                                break;   
                            case 6:  
                                String y = str.replace(".", "");
                                String y2 = y.replace(",", ".");
                                Gbpedidosativos.setPedidoCd184AtivoRemessa(y2.toUpperCase());  
                                break; 
                            case 7:  
                                String Z = str.replace(".", "");
                                String Z2 = Z.replace(",", ".");
                                Gbpedidosativos.setPedidoCd184AtivoSaiu(Z2.toUpperCase());  
                                break;   
                            case 8:  
                                String ZY = str.replace(".", "");
                                String ZY2 = ZY.replace(",", ".");
                                Gbpedidosativos.setPedidoCd184AtivoEntradaLoja(ZY2.toUpperCase());  
                                break;     
                            
                        }
                    }
                    
                    try{
                        List<Gbpedidosativos> list = null;
                
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbpedidosativos.class, JPAUtil.em());
                                        
                                list = (List<Gbpedidosativos>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbpedidosativos.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBPEDIDOSATIVOS.ESTABELECIMENTO = ? )", Gbpedidosativos.getMaterial(), Gbpedidosativos.getEstabelecimento() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = list.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){   
                        /*
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ?", Gbpedidosativos.class );
                        q.setParameter(1, Gbpedidosativos.getMaterial() );
                        List<Gbpedidosativos> list = q.getResultList();
                        
                        String rbuscaSap = ""; try{ rbuscaSap = list.get(0).getMaterial().trim(); }catch( Exception e ){}
                        
                        String rbuscaLoja = ""; try{ rbuscaLoja = list.get(0).getEstabelecimento().trim(); }catch( Exception e ){}
                        
                        if( !rbuscaSap.equals("") && rbuscaLoja.equals( Gbpedidosativos.getEstabelecimento() ) ){
                        */                     
                                String t1 = ""; 
                                try{ 
                                    t1 = Gbpedidosativos.getPedidoCd184Ativo();
                                    if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                                    double b  = Double.parseDouble( t1 );
                                    
                                    String t2 = list.get(0).getPedidoCd184Ativo();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b2 = Double.parseDouble( t2 );
                                    
                                    double x = b + b2;
                                    
                                    t1 = String.valueOf(x);
                                    System.out.println(" Double b - " + t1 );
                                } catch( Exception e ){} 
                                
                                String t3 = ""; 
                                try{ 
                                    t3 = Gbpedidosativos.getPedidoCd184AtivoRemessa();
                                    if( t3.equals("") || t3.equals("null") ){ t3="0"; }                                    
                                    double b3  = Double.parseDouble( t3 );
                                    System.out.println("\n Double b3 - " + t3 );
                                    
                                    String t2 = list.get(0).getPedidoCd184AtivoRemessa();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b23 = Double.parseDouble( t2 );
                                    System.out.println(" Double b23 - " + t3 );
                                    
                                    double x = b3 + b23;
                                    
                                    t3 = String.valueOf(x);
                                    System.out.println(" Double x - " + t3 );
                                } catch( Exception e ){} 
                                
                                String t4 = ""; 
                                try{ 
                                    t4 = Gbpedidosativos.getPedidoCd184AtivoSaiu();
                                    if( t4.equals("") || t4.equals("null") ){ t4="0"; }                                    
                                    double b3  = Double.parseDouble( t4 );
                                    System.out.println("\n Double b3 - " + t4 );
                                    
                                    String t2 = list.get(0).getPedidoCd184AtivoSaiu();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b23 = Double.parseDouble( t2 );
                                    System.out.println(" Double b23 - " + t4 );
                                    
                                    double x = b3 + b23;
                                    
                                    t4 = String.valueOf(x);
                                    System.out.println(" Double x - " + t4 );
                                } catch( Exception e ){} 
                                
                                String t5 = ""; 
                                try{ 
                                    t5 = Gbpedidosativos.getPedidoCd184AtivoEntradaLoja();
                                    if( t5.equals("") || t5.equals("null") ){ t5="0"; }                                    
                                    double b3  = Double.parseDouble( t5 );
                                    System.out.println("\n Double b3 - " + t5 );
                                    
                                    String t2 = list.get(0).getPedidoCd184AtivoEntradaLoja();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b23 = Double.parseDouble( t2 );
                                    System.out.println(" Double b23 - " + t5 );
                                    
                                    double x = b3 + b23;
                                    
                                    t5 = String.valueOf(x);
                                    System.out.println(" Double x - " + t5 );
                                } catch( Exception e ){} 
                                
                                list.get(0).setPedidoCd184Ativo( t1 );
                                list.get(0).setPedidoCd184AtivoRemessa( t3 );
                                list.get(0).setPedidoCd184AtivoSaiu( t4 );                                                                
                                list.get(0).setPedidoCd184AtivoEntradaLoja( t5 );
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(0), JPAUtil.em());
                                Gbpedidosativos Gbproduto_Cadastrado = (Gbpedidosativos) DAOGenericoJPA2.gravanovoOUatualizar();
                                //break; 
                        }    
                        else{
                                   
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbpedidosativos, JPAUtil.em());
                            Gbpedidosativos Gbproduto_Cadastrado = (Gbpedidosativos) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void quantidade_Vendida(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO QUANTIDADE VENDIDA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{             
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbquantidadevendida Quantidadevendida = new Gbquantidadevendida();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).toUpperCase().trim();
                               
                        switch( C_i2 ){
                        
                            case 0:  Quantidadevendida.setEstabelecimento(str);   break;
                            case 1:  Quantidadevendida.setMaterial(str);          break;
                            case 2:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");                                
                                Quantidadevendida.setQuantidade2( Double.parseDouble(x2) );
                                break;    
                        }
                    }
                    
                    try{
                        List<Gbquantidadevendida> XXGbquantidadevendida = null;
                
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbquantidadevendida.class, JPAUtil.em());
                                        
                                XXGbquantidadevendida = (List<Gbquantidadevendida>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbquantidadevendida.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBQUANTIDADEVENDIDA WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBQUANTIDADEVENDIDA.ESTABELECIMENTO = ? )", Quantidadevendida.getMaterial(), Quantidadevendida.getEstabelecimento() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbquantidadevendida.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){   
                                             
                                double qtd_da_tabela = Quantidadevendida.getQuantidade2();
                                double qtd_da_banco  = XXGbquantidadevendida.get(0).getQuantidade2();
                                try{ 

                                    double nova_quantidade = qtd_da_tabela + qtd_da_banco;
                                    
                                    XXGbquantidadevendida.get(0).setQuantidade2( nova_quantidade );
                                } catch( Exception e ){} 
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXGbquantidadevendida.get(0), JPAUtil.em());
                                Gbquantidadevendida Gbquantidadevendida_Cadastrado = (Gbquantidadevendida) DAOGenericoJPA2.gravanovoOUatualizar();
                                //break; 
                        }    
                        else{
                                   
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Quantidadevendida, JPAUtil.em());
                            Gbquantidadevendida Gbquantidadevendida_Cadastrado = (Gbquantidadevendida) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void ultima_Entrada(){ //Gbquantidadevendida
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO ÚLTIMA ENTRADA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{             
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbultimaentradadata Gbultimaentradadata = new Gbultimaentradadata();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).toUpperCase().trim();
                               
                        switch( C_i2 ){
                        
                            case 0:  Gbultimaentradadata.setEstabelecimento(str);   break;
                            case 1:  Gbultimaentradadata.setMaterial(str);          break;
                            case 2:  
                                try{
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                                    Date dateX = formatter.parse(str);                                 
            
                                    Gbultimaentradadata.setDataUltimaentrada( dateX );    
                                }catch( Exception e ){
                                    e.printStackTrace();
                                    Exportandob.fechar();
                                    /*Class<Imagens> clazzHome = Imagens.class;
                                    JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar ÚLTIMA ENTRADA\n"
                                    + "\nERRO CADASTRAR ÚLTIMA ENTRADA\n"
                                    + "\nOK Para Prosseguir"
                                    ,"Class: " + this.getClass().getName(), 
                                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );*/
                                }
                                break; 
                            case 3:  Gbultimaentradadata.setLof(str);          break;    
                        }
                    }
                    
                    try{
                        List<Gbultimaentradadata> XXGbultimaentradadata = null;
                
                            try{ 
                                DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Gbultimaentradadata.class, JPAUtil.em());
                                        
                                XXGbultimaentradadata = (List<Gbultimaentradadata>) DAOGenericoJPAXX.getBeansCom_2_Parametro(Gbultimaentradadata.class, 
                                                "SELECT * FROM SCHEMAJMARY.GBULTIMAENTRADADATA WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBULTIMAENTRADADATA.ESTABELECIMENTO = ? )", Gbultimaentradadata.getMaterial(), Gbultimaentradadata.getEstabelecimento() );
                            }catch( Exception e ){ }
                
                            String rbusca = ""; try{ rbusca = XXGbultimaentradadata.get(0).getMaterial(); }catch( Exception e ){}
                
                        if( !rbusca.equals("") ){   
                                             
                                Date dateTabela = Gbultimaentradadata.getDataUltimaentrada();
                                Date dateBanco  = XXGbultimaentradadata.get(0).getDataUltimaentrada();
                                try{ 

                                    if( dateBanco.before(dateTabela) ){
                                        
                                        XXGbultimaentradadata.get(0).setDataUltimaentrada( dateTabela );   
                                    }
                                    
                                    XXGbultimaentradadata.get(0).setLof( Gbultimaentradadata.getLof() );   
                                } catch( Exception e ){} 
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( XXGbultimaentradadata.get(0), JPAUtil.em());
                                Gbultimaentradadata Gbultimaentradadata_Cadastrado = (Gbultimaentradadata) DAOGenericoJPA2.gravanovoOUatualizar();
                                //break; 
                        }    
                        else{
                                   
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbultimaentradadata, JPAUtil.em());
                            Gbpedidosativos Gbproduto_Cadastrado = (Gbpedidosativos) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarUltimaEntrada() { //quantidade_Vendida
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbultimaentradadata> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBULTIMAENTRADADATA", Gbultimaentradadata.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ÚLTIMA ENTRADA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    ultima_Entrada();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void excluir_Quantidade_Vendida() { //quantidade_Vendida
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbquantidadevendida> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBQUANTIDADEVENDIDA", Gbquantidadevendida.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO QUANTIDADE VENDIDA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    quantidade_Vendida();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    
    private void excluirCadastrarAnalise_ra_diario() { 
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbra> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBRA", Gbra.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO PLAN. RA DIÁRIO");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    cadastrarAnalise_ra_diario();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //ANÁLISES
    private void me80fnZrep(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO PEDIDO ATIVO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbpedidosativos Gbpedidosativos = new Gbpedidosativos();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) );
                               
                        switch( C_i2 ){
                        
                            case 0:  Gbpedidosativos.setEstabelecimento(str);          break;
                            case 1:  Gbpedidosativos.setMaterial(str.toUpperCase());   break;  
                            case 2:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                Gbpedidosativos.setPedidoCd184Ativo(x2.toUpperCase());        
                                break;   
                            case 3:  
                                String y = str.replace(".", "");
                                String y2 = y.replace(",", ".");
                                Gbpedidosativos.setPedidoCd184AtivoRemessa(y2.toUpperCase());  
                                break; 
                            case 4:  
                                String Z = str.replace(".", "");
                                String Z2 = Z.replace(",", ".");
                                Gbpedidosativos.setPedidoCd184AtivoSaiu(Z2.toUpperCase());  
                                break;   
                            case 5:  
                                String ZY = str.replace(".", "");
                                String ZY2 = ZY.replace(",", ".");
                                Gbpedidosativos.setPedidoCd184AtivoEntradaLoja(ZY2.toUpperCase());  
                                break;     
                            
                        }
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPEDIDOSATIVOS WHERE MATERIAL = ?", Gbpedidosativos.class );
                        q.setParameter(1, Gbpedidosativos.getMaterial() );
                        List<Gbpedidosativos> list = q.getResultList();
                        
                        String rbusca = ""; try{ rbusca = list.get(0).getMaterial(); }catch( Exception e ){}
                        
                        if( !rbusca.equals("") ){
                                             
                                String t1 = ""; 
                                try{ 
                                    t1 = Gbpedidosativos.getPedidoCd184Ativo();
                                    if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                                    double b  = Double.parseDouble( t1 );
                                    
                                    String t2 = list.get(0).getPedidoCd184Ativo();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b2 = Double.parseDouble( t2 );
                                    
                                    double x = b + b2;
                                    
                                    t1 = String.valueOf(x);
                                    System.out.println(" Double b - " + t1 );
                                } catch( Exception e ){} 
                                
                                String t3 = ""; 
                                try{ 
                                    t3 = Gbpedidosativos.getPedidoCd184AtivoRemessa();
                                    if( t3.equals("") || t3.equals("null") ){ t3="0"; }                                    
                                    double b3  = Double.parseDouble( t3 );
                                    System.out.println("\n Double b3 - " + t3 );
                                    
                                    String t2 = list.get(0).getPedidoCd184AtivoRemessa();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b23 = Double.parseDouble( t2 );
                                    System.out.println(" Double b23 - " + t3 );
                                    
                                    double x = b3 + b23;
                                    
                                    t3 = String.valueOf(x);
                                    System.out.println(" Double x - " + t3 );
                                } catch( Exception e ){} 
                                
                                String t4 = ""; 
                                try{ 
                                    t4 = Gbpedidosativos.getPedidoCd184AtivoSaiu();
                                    if( t4.equals("") || t4.equals("null") ){ t4="0"; }                                    
                                    double b3  = Double.parseDouble( t4 );
                                    System.out.println("\n Double b3 - " + t4 );
                                    
                                    String t2 = list.get(0).getPedidoCd184AtivoSaiu();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b23 = Double.parseDouble( t2 );
                                    System.out.println(" Double b23 - " + t4 );
                                    
                                    double x = b3 + b23;
                                    
                                    t4 = String.valueOf(x);
                                    System.out.println(" Double x - " + t4 );
                                } catch( Exception e ){} 
                                
                                String t5 = ""; 
                                try{ 
                                    t5 = Gbpedidosativos.getPedidoCd184AtivoEntradaLoja();
                                    if( t5.equals("") || t5.equals("null") ){ t5="0"; }                                    
                                    double b3  = Double.parseDouble( t5 );
                                    System.out.println("\n Double b3 - " + t5 );
                                    
                                    String t2 = list.get(0).getPedidoCd184AtivoEntradaLoja();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b23 = Double.parseDouble( t2 );
                                    System.out.println(" Double b23 - " + t5 );
                                    
                                    double x = b3 + b23;
                                    
                                    t5 = String.valueOf(x);
                                    System.out.println(" Double x - " + t5 );
                                } catch( Exception e ){} 
                                
                                list.get(0).setPedidoCd184Ativo( t1 );
                                list.get(0).setPedidoCd184AtivoRemessa( t3 );
                                list.get(0).setPedidoCd184AtivoSaiu( t4 );                                                                
                                list.get(0).setPedidoCd184AtivoEntradaLoja( t5 );
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(0), JPAUtil.em());
                                Gbpedidosativos Gbproduto_Cadastrado = (Gbpedidosativos) DAOGenericoJPA2.gravanovoOUatualizar();
                                //break; 
                        }    
                        else{
                                   
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbpedidosativos, JPAUtil.em());
                            Gbpedidosativos Gbproduto_Cadastrado = (Gbpedidosativos) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    //ANÁLISES
    private void cadastrarAnalise_ra_diario(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("RA DIÁRIO - ME80FN");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbra Gbra = new Gbra();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) ).toUpperCase().trim();
                               
                        switch( C_i ){
                        
                            case 0:  Gbra.setNumepedido(str);               break;
                            case 1:  Gbra.setEstabelecimento(str);          break;
                            case 2:  Gbra.setCodfornec(      str);          break;    
                            case 3:  Gbra.setMaterial(str);                 break;  
                            case 4:  Gbra.setEmbalagem(str);                break;  
                            case 5:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                Gbra.setRaDoDia(x2.toUpperCase());        
                                break;                              
                        }                                
                    }
                    
                    try{
                
                            if( !Gbra.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbra, JPAUtil.em());
                                Gbra Gbra_Cadastrado = (Gbra) DAOGenericoJPA2.gravanovoOUatualizar();
                             
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarAtivoSemVenda() { 
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbdiassemvenda> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBDIASSEMVENDA", Gbdiassemvenda.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ATIVOS SEM VENDA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    cadastrarAtivoSemVenda();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    //ANÁLISES
    private void cadastrarAtivoSemVenda(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("ATIVO SEM VENDA - MC46");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbdiassemvenda Gbdiassemvenda = new Gbdiassemvenda();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbdiassemvenda.setEstabelecimento(str);   break;
                            case 1:  Gbdiassemvenda.setMaterial(str.toUpperCase());                 break;  
                            case 2:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                int ic = 0; try{ ic = Integer.parseInt( x2 ); }catch( Exception e ){}
                                Gbdiassemvenda.setDiassemvenda(ic );        
                                break;                              
                        }                                
                    }
                    
                    try{
                
                            if( !Gbdiassemvenda.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbdiassemvenda, JPAUtil.em());
                                Gbdiassemvenda Gbproduto_Cadastrado = (Gbdiassemvenda) DAOGenericoJPA2.gravanovoOUatualizar();
                           
                            }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void cadastrarMRP(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO DE MRP");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbmrp Gbmrp = new Gbmrp();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbmrp.setEstabelecimento(str);   break;
                            case 1:  Gbmrp.setMaterial(str.toUpperCase());                 break;  
                            case 2:  
                                Gbmrp.setMrp(str.toUpperCase() );        
                                break;                              
                        }                                
                    }
                    
                    try{
                            if( !Gbmrp.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbmrp, JPAUtil.em());
                                Gbmrp Gbproduto_Cadastrado = (Gbmrp) DAOGenericoJPA2.gravanovoOUatualizar();
                        
                            }
                    }catch( Exception e ){}  
                    
                    try{
                        
                        Gbmrp Retorno = verificarRepeticaoMrp( Gbmrp.getMaterial(), Gbmrp.getEstabelecimento() );
                        
                        String rbusca = ""; try{ rbusca = Retorno.getMaterial(); }catch( Exception e ){}
                        
                        if( rbusca.equals("") ){
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbmrp, JPAUtil.em());
                            Gbmrp Gbproduto_Cadastrado = (Gbmrp) DAOGenericoJPA2.gravanovoOUatualizar();
                        }

                    }catch( Exception e ){}
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private Gbmrp verificarRepeticaoMrp( String material, String loja ) {  
       Gbmrp retornoGbmrp = null;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBMRP WHERE MATERIAL = ? AND ( SCHEMAJMARY.GBMRP.ESTABELECIMENTO = ? )", Gbmrp.class );
            q.setParameter(1, material );
            q.setParameter(2, loja );
            List<Gbmrp> list = q.getResultList();
            
           for (Gbmrp list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   
                   if (list1.getEstabelecimento().equals(loja)) {
                       retornoGbmrp = list1;
                   }
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retornoGbmrp;
    }
    
    //cadastrarQtdEmbEs();
    private void cadastrarQtdEmbEs(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO DE QTD.E/S");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbproduto Gbproduto = new Gbproduto();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbproduto.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                Gbproduto.setQtdxes(str.toUpperCase() );        
                                break; 
                            case 2:  
                                Gbproduto.setUnd(str.toUpperCase() );        
                                break;     
                        }                                
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
                        q.setParameter(1, Gbproduto.getMaterial() );
                        List<Gbproduto> list = q.getResultList();
            
                        for (int i = 0; i < list.size(); i++){
                
                            if( list.get(i).getMaterial().equals( Gbproduto.getMaterial() ) ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                list.get(i).setQtdxes(Gbproduto.getQtdxes());
                                list.get(i).setUnd(Gbproduto.getUnd());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                                
                            }
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    //cadastrarQtdEmbEs();
    private void eangrupo(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO EAN/GRUPO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbproduto Gbproduto = new Gbproduto();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbproduto.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                Gbproduto.setEan(str.toUpperCase() );        
                                break; 
                            case 2:  
                                Gbproduto.setGrupo(str.toUpperCase() );        
                                break;     
                        }                                
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", Gbproduto.class );
                        q.setParameter(1, Gbproduto.getMaterial() );
                        List<Gbproduto> list = q.getResultList();
            
                        for (int i = 0; i < list.size(); i++){
                
                            if( list.get(i).getMaterial().equals( Gbproduto.getMaterial() ) ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                list.get(i).setEan(Gbproduto.getEan());
                                list.get(i).setGrupo(Gbproduto.getGrupo());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                Gbproduto Gbproduto_Cadastrado = (Gbproduto) DAOGenericoJPA2.gravanovoOUatualizar();
                                
                            }
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void diretoLojaZris(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO GD ZRIS");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                
                String strEsp = String.valueOf( TableModel.getValueAt(0, 0) );
                String g = strEsp.trim().toUpperCase();
                excluirGBDiretoLojaEsp(g);
                                                                                
                for( int L_is=0; L_is < TableModel.getRowCount(); L_is++ ){
                    Exportandob.pbg.setValue( L_is );   
                    
                    Gbdiretoloja Gbdiretoloja = new Gbdiretoloja();                
                    
                    for( int C_if=0; C_if < TableModel.getColumnCount(); C_if++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_is, C_if) );
                               
                        switch( C_if ){
                        
                            case 0:  
                                Gbdiretoloja.setFornecedor(str.toUpperCase()); break;  
                            case 1:  
                                Gbdiretoloja.setMaterial(str.toUpperCase() );        
                                break; 
                            case 2:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                double b  = 0; try{ b  = Double.parseDouble( x2 ); }catch( Exception e ){} 
                                
                                int ic = 0; try{ ic = ( int )b; }catch( Exception e ){}
                                Gbdiretoloja.setInputzris(ic );        
                                break;     
                        }                                
                    }
                    
                    try{
                        
                        if( !Gbdiretoloja.getMaterial().equals("") ){
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbdiretoloja, JPAUtil.em());
                            Gbdiretoloja Gbdiretoloja_Cadastrado = (Gbdiretoloja) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirEvento_qtd_por_embalagem() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Analiseeventos> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.ANALISEEVENTOS", Analiseeventos.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO EVENTO QTD X EMBALAGEM");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                                                
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "EXCLUIR_ANALISEEVENTOS(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //evento_qtd_por_embalagem();
    private void evento_qtd_por_embalagem(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("EVENTO QTD X EMBALAGEM");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );

        excluirEvento_qtd_por_embalagem();  
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                              
                for( int L_is=0; L_is < TableModel.getRowCount(); L_is++ ){
                    Exportandob.pbg.setValue( L_is );   
                    
                    Analiseeventos Analiseeventos = new Analiseeventos();                
                    
                    for( int C_if=0; C_if < TableModel.getColumnCount(); C_if++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_is, C_if) ).trim();
                               
                        switch( C_if ){
                        
                            case 0:  
                                Analiseeventos.setMaterial(str.toUpperCase()); break;  
                            case 1:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                double b  = 0; try{ b  = Double.parseDouble( x2 ); }catch( Exception e ){} 
                                
                                int ic = 0; try{ ic = ( int )b; }catch( Exception e ){}
                                Analiseeventos.setQtdEmbalagAposta(ic);        
                                break;     
                        }                                
                    }
                    
                    try{
                        
                        if( !Analiseeventos.getMaterial().equals("") ){
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Analiseeventos, JPAUtil.em());
                            Analiseeventos Analiseeventos_Cadastrado = (Analiseeventos) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void evento_venda_mes_passado(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("EVENTO VENDA MÊS PASSADO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Analiseeventos Analiseeventos = new Analiseeventos();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) ).trim();
                               
                        switch( C_i ){
                        
                            case 0:  Analiseeventos.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                double b = 0; try{ b = Double.parseDouble( x2 ); }catch( Exception e ){} 
                                
                                int ic = 0; try{ ic = ( int )b; }catch( Exception e ){}
                                Analiseeventos.setVendaMesAnterior(ic );        
                                break;      
                        }                                 
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.ANALISEEVENTOS WHERE MATERIAL = ?", Analiseeventos.class );
                        q.setParameter(1, Analiseeventos.getMaterial() );
                        List<Analiseeventos> list = q.getResultList();
            
                        for (int i = 0; i < list.size(); i++){
                
                            if( list.get(i).getMaterial().equals( Analiseeventos.getMaterial() ) ){                                                       
                            
                                if( Analiseeventos.getVendaMesAnterior()!= 0 ){

                                    int fora = 0; try{ fora = list.get(i).getVendaMesAnterior(); }catch( Exception e ){ }                                           
                                    int dbCadastro = 0; try{ dbCadastro = Analiseeventos.getVendaMesAnterior(); }catch( Exception e ){ }
                                            
                                    int soma = fora + dbCadastro;
                                                 
                                    list.get(i).setVendaMesAnterior(soma );
                                           
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                    Analiseeventos Analisedevolutiva_Cadastrado = (Analiseeventos) DAOGenericoJPA2.gravanovoOUatualizar();             
                                }
                                else{
                                                
                                    list.get(i).setVendaMesAnterior( 0 );
                                            
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                    Analiseeventos Analiseeventos_Cadastrado = (Analiseeventos) DAOGenericoJPA2.gravanovoOUatualizar();   
                                }
                            
                            }
                        }
                    }catch( Exception e ){}                
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    } 
    
    private void evento_venda_ano_passado(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("EVENTO VENDA ANO PASSADO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Analiseeventos Analiseeventos = new Analiseeventos();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) ).trim();
                               
                        switch( C_i ){
                        
                            case 0:  Analiseeventos.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                double b = 0; try{ b = Double.parseDouble( x2 ); }catch( Exception e ){} 
                                
                                int ic = 0; try{ ic = ( int )b; }catch( Exception e ){}
                                Analiseeventos.setVendaAnoAnterior(ic );        
                                break;      
                        }                                 
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.ANALISEEVENTOS WHERE MATERIAL = ?", Analiseeventos.class );
                        q.setParameter(1, Analiseeventos.getMaterial() );
                        List<Analiseeventos> list = q.getResultList();
            
                        for (int i = 0; i < list.size(); i++){
                
                            if( list.get(i).getMaterial().equals( Analiseeventos.getMaterial() ) ){                                                       
                            
                                if( Analiseeventos.getVendaAnoAnterior()!= 0 ){

                                    int fora = 0; try{ fora = list.get(i).getVendaAnoAnterior(); }catch( Exception e ){ }                                           
                                    int dbCadastro = 0; try{ dbCadastro = Analiseeventos.getVendaAnoAnterior(); }catch( Exception e ){ }
                                            
                                    int soma = fora + dbCadastro;
                                                 
                                    list.get(i).setVendaAnoAnterior(soma );
                                           
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                    Analiseeventos Analisedevolutiva_Cadastrado = (Analiseeventos) DAOGenericoJPA2.gravanovoOUatualizar();             
                                }
                                else{
                                                
                                    list.get(i).setVendaAnoAnterior( 0 );
                                            
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                    Analiseeventos Analiseeventos_Cadastrado = (Analiseeventos) DAOGenericoJPA2.gravanovoOUatualizar();   
                                }
                            
                            }
                        }
                    }catch( Exception e ){}                
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    //devolutiva_Input();
    private void planilha_ra_intranet(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("PLANILHA_RA_INTRANET");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );

        excluirdevolutiva_Anterior();  
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                              
                for( int L_is=0; L_is < TableModel.getRowCount(); L_is++ ){
                    Exportandob.pbg.setValue( L_is );   
                    
                    Analisedevolutiva Analisedevolutiva = new Analisedevolutiva();                
                    
                    for( int C_if=0; C_if < TableModel.getColumnCount(); C_if++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_is, C_if) ).trim();
                               
                        switch( C_if ){
                        
                            case 0:  
                                Analisedevolutiva.setMaterial(str.toUpperCase()); break;  
                            case 1:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                double b  = 0; try{ b  = Double.parseDouble( x2 ); }catch( Exception e ){} 
                                
                                int ic = 0; try{ ic = ( int )b; }catch( Exception e ){}
                                
                                if( !str.equals("") ){
                                    Analisedevolutiva.setQtdSolicitada(ic);     
                                }
                                break;     
                        }                                
                    }
                    
                    try{
                        
                        if( !Analisedevolutiva.getMaterial().equals("") ){
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Analisedevolutiva, JPAUtil.em());
                            Analisedevolutiva Analisedevolutiva_Cadastrado = (Analisedevolutiva) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirdevolutiva_Anterior() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Analisedevolutiva> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.ANALISEDEVOLUTIVA", Analisedevolutiva.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ANALISEDEVOLUTIVA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                                                
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                        
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
            
    
    private void diretoLojaGerado(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO GD PEDIDO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbdiretoloja Gbdiretoloja = new Gbdiretoloja();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbdiretoloja.setFornecedor(str.toUpperCase());                 break;  
                            case 1:  
                                Gbdiretoloja.setMaterial(str.toUpperCase() );        
                                break; 
                            case 2:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                double b  = 0; try{ b  = Double.parseDouble( x2 ); }catch( Exception e ){} 
                                
                                int ic = 0; try{ ic = ( int )b; }catch( Exception e ){}
                                Gbdiretoloja.setPedidogerado(ic );        
                                break;      
                        }                                 
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBDIRETOLOJA WHERE MATERIAL = ?", Gbdiretoloja.class );
                        q.setParameter(1, Gbdiretoloja.getMaterial() );
                        List<Gbdiretoloja> list = q.getResultList();
            
                        for (int i = 0; i < list.size(); i++){
                
                            if( list.get(i).getMaterial().equals( Gbdiretoloja.getMaterial() ) ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                list.get(i).setPedidogerado(Gbdiretoloja.getPedidogerado());
                                
                                if( list.get(i).getFornecedor().equals( Gbdiretoloja.getFornecedor() ) ){
                                
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                    Gbdiretoloja Gbdiretoloja_Cadastrado = (Gbdiretoloja) DAOGenericoJPA2.gravanovoOUatualizar();
                                }                               
                            }
                        }
                    }catch( Exception e ){}                
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }       
    
    //devolutiva_Recebida();
    private void planilha_ra_intranet_alterado(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("PLANILHA_RA_INTRANET_ALTERADO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Analisedevolutiva Analisedevolutiva = new Analisedevolutiva();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) ).trim();
                               
                        switch( C_i ){
                        
                            case 0:  Analisedevolutiva.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                double b  = 0; try{ b  = Double.parseDouble( x2 ); }catch( Exception e ){} 
                                
                                int ic = 0; try{ ic = ( int )b; }catch( Exception e ){}
                                Analisedevolutiva.setQtdDevolutiva(ic );        
                                break;      
                        }                                 
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.ANALISEDEVOLUTIVA WHERE MATERIAL = ?", Analisedevolutiva.class );
                        q.setParameter(1, Analisedevolutiva.getMaterial() );
                        List<Analisedevolutiva> list = q.getResultList();
            
                        for (int i = 0; i < list.size(); i++){
                
                            if( list.get(i).getMaterial().equals( Analisedevolutiva.getMaterial() ) ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                list.get(i).setQtdDevolutiva(Analisedevolutiva.getQtdDevolutiva());
                                
                                                               
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                Analisedevolutiva Analisedevolutiva_Cadastrado = (Analisedevolutiva) DAOGenericoJPA2.gravanovoOUatualizar();                             
                            }
                        }
                    }catch( Exception e ){}                
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }   
    
    private void planilha_ra_intranet_dev_me80fn(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("PLANILHA_RA_INTRANET_ALTERADO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Analisedevolutiva Analisedevolutiva = new Analisedevolutiva();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) ).trim();
                               
                        switch( C_i ){
                        
                            case 0:  Analisedevolutiva.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                
                                double b = 0; try{ b = Double.parseDouble( x2 ); }catch( Exception e ){} 
                                
                                int ic = 0; try{ ic = ( int )b; }catch( Exception e ){}
                                Analisedevolutiva.setQtdDevolutivaMe80fn(ic );        
                                break;      
                        }                                 
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.ANALISEDEVOLUTIVA WHERE MATERIAL = ?", Analisedevolutiva.class );
                        q.setParameter(1, Analisedevolutiva.getMaterial() );
                        List<Analisedevolutiva> list = q.getResultList();
            
                        for (int i = 0; i < list.size(); i++){
                
                            if( list.get(i).getMaterial().equals( Analisedevolutiva.getMaterial() ) ){                                                       
                            
                                if( Analisedevolutiva.getQtdDevolutivaMe80fn() != 0 ){

                                    int fora = 0; try{ fora = list.get(i).getQtdDevolutivaMe80fn(); }catch( Exception e ){ }                                           
                                    int dbCadastro = 0; try{ dbCadastro = Analisedevolutiva.getQtdDevolutivaMe80fn(); }catch( Exception e ){ }
                                            
                                    int soma = fora + dbCadastro;
                                                 
                                    list.get(i).setQtdDevolutivaMe80fn( soma );
                                           
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                    Analisedevolutiva Analisedevolutiva_Cadastrado = (Analisedevolutiva) DAOGenericoJPA2.gravanovoOUatualizar();             
                                }
                                else{
                                                
                                    list.get(i).setQtdDevolutivaMe80fn( 0 );
                                            
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                    Analisedevolutiva Analisedevolutiva_Cadastrado = (Analisedevolutiva) DAOGenericoJPA2.gravanovoOUatualizar();   
                                }
                            
                            }
                        }
                    }catch( Exception e ){}                
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }   
    
    //cadastrarQtdEmbEs();
    private void oportSemana(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO OPORT/SEM");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 
            excluirOportSemana();
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbooportsemana Gbooportsemana = new Gbooportsemana();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbooportsemana.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                Gbooportsemana.setOportsemana(str.toUpperCase() );        
                                break;   
                        }                                
                    }
                    
                    try{
                        if( !Gbooportsemana.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbooportsemana, JPAUtil.em());
                                Gbooportsemana Gbooportsemana_Cadastrado = (Gbooportsemana) DAOGenericoJPA2.gravanovoOUatualizar();
                        
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void cadastrarInventarioMb52(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO INVEN/MB52");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 
            excluirInventarioMb52();
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Inventariomb52 Inventariomb52 = new Inventariomb52();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) ).trim();
                               
                        switch( C_i ){
                        
                            case 0:  Inventariomb52.setMaterial(str.toUpperCase());         break;  
                            case 1:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                Inventariomb52.setEstoque(x2.toUpperCase() );         break;     
                            case 2:  
                                String y = str.replace(".", "");
                                String y2 = y.replace(",", ".");
                                Inventariomb52.setCustototal(y2.toUpperCase() );      break;      
                        }                                
                    }
                    
                    try{
                        if( !Inventariomb52.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Inventariomb52, JPAUtil.em());
                                Inventariomb52 Inventariomb52_Cadastrado = (Inventariomb52) DAOGenericoJPA2.gravanovoOUatualizar();
                        
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirInventarioMb52() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Inventariomb52> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.INVENTARIOMB52", Inventariomb52.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO INVEN/MB52");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
        
    private void cadastrarInventarioZlmr06(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO INVEN/ZLMR06");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        excluirInventarioZlmr06();
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Inventariozlmr06 Inventariozlmr06 = new Inventariozlmr06();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).toUpperCase().trim();
                               
                        switch( C_i2 ){
                        
                            case 0:  Inventariozlmr06.setMaterial(str.toUpperCase());         break;  
                            case 1:  
                                String x = str.replace(".", "");
                                String x2 = x.replace(",", ".");
                                Inventariozlmr06.setQtdcontado(x2.toUpperCase() );         
                                break;   
                            case 2:  
                                String y = str.replace(".", "");
                                String y2 = y.replace(",", ".");
                                Inventariozlmr06.setCustototal(y2.toUpperCase() );     
                                break;      
                            
                        }
                    }
                    
                    try{
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06 WHERE MATERIAL = ?", Inventariozlmr06.class );
                        q.setParameter(1, Inventariozlmr06.getMaterial() );
                        List<Inventariozlmr06> list = q.getResultList();
                        
                        String rbuscaSap = ""; try{ rbuscaSap = list.get(0).getMaterial().trim(); }catch( Exception e ){}
                        
                        if( !rbuscaSap.equals("") ){
                                             
                                String t1 = ""; 
                                try{ 
                                    t1 = Inventariozlmr06.getQtdcontado();
                                    if( t1.equals("") || t1.equals("null") ){ t1="0"; }                                    
                                    double b  = Double.parseDouble( t1 );
                                    
                                    String t2 = list.get(0).getQtdcontado();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b2 = Double.parseDouble( t2 );
                                    
                                    double x = b + b2;
                                    
                                    t1 = String.valueOf(x);
                                    System.out.println(" Double b - " + t1 );
                                } catch( Exception e ){} 
                                
                                String t3 = ""; 
                                try{ 
                                    t3 = Inventariozlmr06.getCustototal();
                                    if( t3.equals("") || t3.equals("null") ){ t3="0"; }                                    
                                    double b3  = Double.parseDouble( t3 );
                                    System.out.println("\n Double b3 - " + t3 );
                                    
                                    String t2 = list.get(0).getCustototal();
                                    if( t2.equals("") || t2.equals("null") ){ t2="0"; }  
                                    double b23 = Double.parseDouble( t2 );
                                    System.out.println(" Double b23 - " + t3 );
                                    
                                    double x = b3 + b23;
                                    
                                    t3 = String.valueOf(x);
                                    System.out.println(" Double x - " + t3 );
                                } catch( Exception e ){} 
                                
                                                                
                                list.get(0).setQtdcontado(t1 );
                                list.get(0).setCustototal(t3 );
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(0), JPAUtil.em());
                                Inventariozlmr06 Gbproduto_Inventariozlmr06 = (Inventariozlmr06) DAOGenericoJPA2.gravanovoOUatualizar();
                                //break; 
                        }    
                        else{
                                   
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Inventariozlmr06, JPAUtil.em());
                            Inventariozlmr06 Gbproduto_Inventariozlmr06 = (Inventariozlmr06) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirInventarioZlmr06() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Inventariozlmr06> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.INVENTARIOZLMR06", Inventariozlmr06.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO INVEN/ZLMR06");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
            
    private void eanZevtra_sortimento(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO EAN ZVTRA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 
            excluirEanZevtra_sortimento();
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbeansecundario Gbeansecundario = new Gbeansecundario();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) ).trim();
                               
                        switch( C_i ){
                        
                            case 0:  Gbeansecundario.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                Gbeansecundario.setEan(str.toUpperCase() );        
                                break;   
                        }                                
                    }
                    
                    try{
                        if( !Gbeansecundario.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbeansecundario, JPAUtil.em());
                                Gbeansecundario Gbeansecundario_Cadastrado = (Gbeansecundario) DAOGenericoJPA2.gravanovoOUatualizar();
                        
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }        
    
    private void excluirEanZevtra_sortimento() {  
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            
            if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbeansecundario> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBEANSECUNDARIO", Gbeansecundario.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO EAN SECUNDARIO");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            }
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    private void descricaoGrupoCompra(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO DESC/GRUPO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 
            //excluirDescricaoGrupoCompra();
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbdescricaogrupo Gbdescricaogrupo = new Gbdescricaogrupo();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) );
                               
                        switch( C_i2 ){
                        
                            case 0:  Gbdescricaogrupo.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                Gbdescricaogrupo.setDescricaogrupo(str.toUpperCase() );        
                                break;   
                        }                                
                    }
                    
                    try{
                        
                        Gbdescricaogrupo Xxx = verificarRepeticaoDescGrupoCompra( Gbdescricaogrupo.getMaterial().trim() );
                        if( Xxx != null ){
                            
                            Xxx.setDescricaogrupo( Gbdescricaogrupo.getDescricaogrupo());
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Xxx, JPAUtil.em());
                            Gbdescricaogrupo Gbdescricaogrupo_Cadastrado = (Gbdescricaogrupo) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        else{
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbdescricaogrupo, JPAUtil.em());
                            Gbdescricaogrupo Gbdescricaogrupo_Cadastrado = (Gbdescricaogrupo) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private Gbdescricaogrupo verificarRepeticaoDescGrupoCompra( String material ) {  
       Gbdescricaogrupo retorno = null;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBDESCRICAOGRUPO WHERE MATERIAL = ?", Gbdescricaogrupo.class );
                  q.setParameter(1, material );
            List<Gbdescricaogrupo> list = q.getResultList();
            
           for (Gbdescricaogrupo list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   retorno = list1;
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    private void descricaoSetor(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO DESC/SETOR");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 
            //excluirDescricaoGrupoCompra();
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbdescricaosetor Gbdescricaosetor = new Gbdescricaosetor();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) );
                               
                        switch( C_i2 ){
                        
                            case 0:  Gbdescricaosetor.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                Gbdescricaosetor.setDescricaosetor(str.toUpperCase() );        
                                break;   
                        }                                
                    }
                    
                    try{
                        
                        Gbdescricaosetor Xxx = verificarRepeticaoDescSetor( Gbdescricaosetor.getMaterial().trim() );
                        if( Xxx != null ){
                            
                            Xxx.setDescricaosetor(Gbdescricaosetor.getDescricaosetor());
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Xxx, JPAUtil.em());
                            Gbdescricaosetor Gbdescricaogrupo_Cadastrado = (Gbdescricaosetor) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        else{
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbdescricaosetor, JPAUtil.em());
                            Gbdescricaosetor Gbdescricaogrupo_Cadastrado = (Gbdescricaosetor) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }
            
        }catch( Exception e ){}
    }
    
    private Gbdescricaosetor verificarRepeticaoDescSetor( String material ) {  
       Gbdescricaosetor retorno = null;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBDESCRICAOSETOR WHERE MATERIAL = ?", Gbdescricaosetor.class );
                  q.setParameter(1, material );
            List<Gbdescricaosetor> list = q.getResultList();
            
           for (Gbdescricaosetor list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   retorno = list1;
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    private void lof(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO LOF");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 

            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Lof Lof = new Lof();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = ""; try{ str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim(); }catch(Exception e){}
                               
                        switch( C_i2 ){
                        
                            case 0:  Lof.setLof(str.toUpperCase());          break;  
                            case 1:  Lof.setRazaoSocial(str.toUpperCase());  break;   
                        }                                
                    }
                    
                    try{
                        
                        Lof Xxx = verificarRepeticaoOpLof( Lof.getLof().trim() );
                        if( Xxx != null ){
            
                            //Xxx.setLof(Lof.getLof());
                            Xxx.setRazaoSocial(Lof.getRazaoSocial());
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Xxx, JPAUtil.em());
                            Opidentificada Opidentificada_Cadastrado = (Opidentificada) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        else{
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Lof, JPAUtil.em());
                            Opidentificada Opidentificada_Cadastrado = (Opidentificada) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }
            
        }catch( Exception e ){}
    }
    
    private Lof verificarRepeticaoOpLof( String lofRecebido ) {  
       Lof retorno = null;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.LOF WHERE LOF = ?", Lof.class );
                  q.setParameter(1, lofRecebido );
            List<Lof> list = q.getResultList();
            
           for (Lof list1 : list) {
               if (list1.getLof().equals(lofRecebido)) {
                   retorno = list1;
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    private void excluirLof() { 
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            //if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Lof> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.LOF", Lof.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO LOF");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            //}
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private void opIdentificada(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO OP.IDENTIFICADA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 

            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Opidentificada Opidentificada = new Opidentificada();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim();
                               
                        switch( C_i2 ){
                        
                            case 0:  Opidentificada.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                
                                Opidentificada.setOpidentificada(str.toUpperCase()); 
                                
                                GregorianCalendar gc = new GregorianCalendar();
                                Date dataHoje = gc.getTime(); 
            
                                Opidentificada.setDataIdent(dataHoje);        
                                break;   
                        }                                
                    }
                    
                    try{
                        
                        Opidentificada Xxx = verificarRepeticaoOpidentificada( Opidentificada.getMaterial().trim() );
                        if( Xxx != null ){
                            
                            GregorianCalendar gc = new GregorianCalendar();
                            Date dataHoje = gc.getTime(); 
            
                            Xxx.setDataIdent(dataHoje);
                            Xxx.setOpidentificada(Opidentificada.getOpidentificada());
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Xxx, JPAUtil.em());
                            Opidentificada Opidentificada_Cadastrado = (Opidentificada) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        else{
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Opidentificada, JPAUtil.em());
                            Opidentificada Opidentificada_Cadastrado = (Opidentificada) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }
            
        }catch( Exception e ){}
    }
    
    private Opidentificada verificarRepeticaoOpidentificada( String material ) {  
       Opidentificada retorno = null;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.OPIDENTIFICADA WHERE MATERIAL = ?", Opidentificada.class );
                  q.setParameter(1, material );
            List<Opidentificada> list = q.getResultList();
            
           for (Opidentificada list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   retorno = list1;
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    private void excluirOpIdentificada() { 
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            //if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Opidentificada> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.OPIDENTIFICADA", Opidentificada.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO OPIDENTIFICADA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            //}
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private void coringa(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO CORINGA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 

            excluirCoringa();
////////////////////////////////////////////////////////////////////////////////            
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Gbcoringa Gbcoringa = new Gbcoringa();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim();
                               
                        switch( C_i2 ){
                        
                            case 0:  Gbcoringa.setMaterial(str.toUpperCase());  break;  
                            case 1:  Gbcoringa.setCoringa(str.toUpperCase());   break;   
                        }                                
                    }
                    
                    try{
                        
                        Gbcoringa Xxx = verificarRepeticaoCoringa( Gbcoringa.getMaterial().trim() );
                        if( Xxx != null ){

                            Xxx.setCoringa(Gbcoringa.getCoringa());
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Xxx, JPAUtil.em());
                            Gbcoringa Gbcoringa_Cadastrado = (Gbcoringa) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        else{
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbcoringa, JPAUtil.em());
                            Gbcoringa Gbcoringa_Cadastrado = (Gbcoringa) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }
            
        }catch( Exception e ){}
    }
    
    private Gbcoringa verificarRepeticaoCoringa( String material ) {  
       Gbcoringa retorno = null;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.GBCORINGA WHERE MATERIAL = ?", Gbcoringa.class );
                  q.setParameter(1, material );
            List<Gbcoringa> list = q.getResultList();
            
           for (Gbcoringa list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   retorno = list1;
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    private void excluirCoringa() { 
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
                                       
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbcoringa> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBCORINGA", Gbcoringa.class );                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO GBCORINGA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start(); 
    }
    
    //ZVTRA_MRP
    private void excluirEnderecoespacogondula() { 
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            //if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Enderecoespacogondula> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.ENDERECOESPACOGONDULA", Enderecoespacogondula.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO ESPAÇO GÔNDULA");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            //}
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private void excluirZVTRA_MRP() { 
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            //if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbmrp> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBMRP", Gbmrp.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO Gbmrp");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
            //}
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    private void enderecoespacogondula(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRO ESPAÇO GÔNDULA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 
            //excluirDescricaoGrupoCompra();
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i2=0; L_i2 < TableModel.getRowCount(); L_i2++ ){
                    Exportandob.pbg.setValue( L_i2 );   
                    
                    Enderecoespacogondula Enderecoespacogondula = new Enderecoespacogondula();                
                    
                    for( int C_i2=0; C_i2 < TableModel.getColumnCount(); C_i2++ ){ 
                                                
                        String str = ""; try{ str = String.valueOf( TableModel.getValueAt(L_i2, C_i2) ).trim().toUpperCase(); }catch(Exception e){}
                               
                        switch( C_i2 ){
                        
                            //case 0:  Enderecoespacogondula.setEstabelecimento(str); break;  
                            case 0:  Enderecoespacogondula.setMaterial(str);        break;  
                            //case 2:  Enderecoespacogondula.setRua(str);             break;  
                            //case 3:  Enderecoespacogondula.setGondula(str);         break;  
                            //case 4:  Enderecoespacogondula.setPrateleira(str);      break;  
                            case 1:  Enderecoespacogondula.setAltura(str);          break;  
                            case 2:  Enderecoespacogondula.setFrente(str);          break;  
                            case 3:  Enderecoespacogondula.setFundo(str);           break;  
                        }                                
                    }
                    
                    try{
                        
                        Enderecoespacogondula Xxx = verificarRepeticaoEndereco( Enderecoespacogondula.getMaterial().trim() );
                        if( Xxx != null ){
                            
                            //Xxx.setEstabelecimento( Enderecoespacogondula.getEstabelecimento() );
                            Xxx.setMaterial( Enderecoespacogondula.getMaterial() );
                            //Xxx.setRua( Enderecoespacogondula.getRua() );
                            //Xxx.setGondula( Enderecoespacogondula.getGondula() );
                            //Xxx.setPrateleira( Enderecoespacogondula.getPrateleira() );
                            Xxx.setAltura( Enderecoespacogondula.getAltura() );
                            Xxx.setFrente( Enderecoespacogondula.getFrente() );
                            Xxx.setFundo( Enderecoespacogondula.getFundo() );
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Xxx, JPAUtil.em());
                            Enderecoespacogondula Enderecoespacogondula_Cadastrado = (Enderecoespacogondula) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                        else{
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Enderecoespacogondula, JPAUtil.em());
                            Enderecoespacogondula Enderecoespacogondula_Cadastrado = (Enderecoespacogondula) DAOGenericoJPA2.gravanovoOUatualizar();
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }
            
        }catch( Exception e ){}
    }
    
    private Enderecoespacogondula verificarRepeticaoEndereco( String material ) {  
       Enderecoespacogondula retorno = null;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.ENDERECOESPACOGONDULA WHERE MATERIAL = ?", Enderecoespacogondula.class );
                  q.setParameter(1, material );
            List<Enderecoespacogondula> list = q.getResultList();
            
           for (Enderecoespacogondula list1 : list) {
               if (list1.getMaterial().equals(material)) {
                   retorno = list1;
                   //System.out.println( "Material Já Cadastrado - " + material );
                   break;
               } else {
                   //System.out.println( "Material Não Cadastrado - " + material );
               }
           }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    //cadastrarQtdEmbEs();
    private void cadastrarPonExtra(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRAR PONTO EXTRA");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 
            excluirPontoExtra();
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbpontoextra Gbpontoextra = new Gbpontoextra();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                               
                        switch( C_i ){
                        
                            case 0:  Gbpontoextra.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                Gbpontoextra.setPeriodo(str.toUpperCase() );        
                                break;   
                        }                                
                    }
                    
                    try{
                        if( !Gbpontoextra.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbpontoextra, JPAUtil.em());
                                Gbpontoextra Gbpontoextra_Cadastrado = (Gbpontoextra) DAOGenericoJPA2.gravanovoOUatualizar();
                        
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    //cadastrarQtdEmbEs();
    private void cadastrarElenco(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRAR ELENCO");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ 
            excluirElenco();
            Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Gbelenco Gbelenco = new Gbelenco();                
                    
                    String str1 = "";
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = String.valueOf( TableModel.getValueAt(L_i, C_i) );
                                                                               
                        switch( C_i ){
                        
                            case 0:  Gbelenco.setMaterial(str.toUpperCase());                 break;  
                            case 1:  
                                str1 = str.toUpperCase();        
                                break;     
                            case 2:  
                                Gbelenco.setPeriodo(str1.trim()+"-"+str.trim().toUpperCase() );        
                                break;   
                        }                                
                    }
                    
                    try{
                        if( !Gbelenco.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbelenco, JPAUtil.em());
                                Gbelenco Gbelenco_Cadastrado = (Gbelenco) DAOGenericoJPA2.gravanovoOUatualizar();
                        
                        }
                    }catch( Exception e ){}                  
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }
    
    private void excluirCadastrarddeDisp() { 
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            
            //if( btExcluirParametros.isEnabled() ) {
                                                            
                    /////////////////////////////////////////////////////////////////////////////////////////
                    List<Gbddedisp> listPP1 = new ArrayList();
                    try{
                        
                        Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.GBDDEDISP", Gbddedisp.class );
                        
                        listPP1 = q.getResultList();
                    }catch(Exception e ){} 
                    
                    /////////////////////////////////////
                    Exportando = new Exportando("EXCLUINDO GBDDEDISP");
                    Exportando.setVisible(true);
                    Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( listPP1.size() );
                    ///////////////////////////////////////
                    
                    for (int i = 0; i < listPP1.size(); i++){ 
                        Exportando.pbg.setValue( i );
                        
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( listPP1.get(i), JPAUtil.em()); 
                        DAOGenericoJPA2.excluir();

                    }
                    /////////////////////////////////////////////////////////////////////////////////////////
                    
                    Exportando.fechar();
                    cadastrarddeDisp();
            //}
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); JOPM JOptionPaneMod = new JOPM( 2, "excluirGBProdutosEstoqueCD184(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } } }.start(); 
    }
    
    //ANÁLISES
    private void cadastrarddeDisp(){ 
        //TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRANDO - GBDDEDISP");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( 100 );
        
        try{ Thread.sleep( 1 );
             
            //if( TableModel.getRowCount() > 0 ){
                                                                                
                //for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( 50 );   
                    
                    Gbddedisp Gbddedisp = new Gbddedisp();                
                    
                    //for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                    String dde     = ""; try{ dde     = tfDde.getText().trim();     }catch( Exception e ){}
                    String top350  = ""; try{ top350  = tfTop350.getText().trim();  }catch( Exception e ){}
                    String top2000 = ""; try{ top2000 = tfTop2000.getText().trim(); }catch( Exception e ){}
                    String mix     = ""; try{ mix     = tfMix.getText().trim();     }catch( Exception e ){}
                    String total   = ""; try{ total   = tfTotal.getText().trim();   }catch( Exception e ){}  
                    
                    Gbddedisp.setDde( dde );
                    Gbddedisp.setTop350(top350);
                    Gbddedisp.setTop2000(top2000);
                    Gbddedisp.setMix(mix);
                    Gbddedisp.setTotal(total);
                    //}
                    
                    try{
                
                            //if( !Gbddedisp.getMaterial().equals("") ){
                                //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Gbddedisp, JPAUtil.em());
                                Gbddedisp Gbproduto_Cadastrado = (Gbddedisp) DAOGenericoJPA2.gravanovoOUatualizar();
                           
                            //}
                    }catch( Exception e ){}                  
                //}
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            //}
            /*else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }*/

            
        }catch( Exception e ){}
    }
    
    
    //cadastrarQtdEmbEs();
    private void fornecedores(){ 
        TableModel TableModel = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel();
                
        Exportando Exportandob = new Exportando("CADASTRAR FORNECEDORES");
        Exportandob.setVisible(true);
        Exportandob.pbg.setMinimum(0);
        Exportandob.pbg.setMaximum( TableModel.getRowCount() );
        
        try{ Thread.sleep( 1 );
             
            if( TableModel.getRowCount() > 0 ){
                                                                                
                for( int L_i=0; L_i < TableModel.getRowCount(); L_i++ ){
                    Exportandob.pbg.setValue( L_i );   
                    
                    Fornecedor Fornecedor = new Fornecedor();                
                    
                    for( int C_i=0; C_i < TableModel.getColumnCount(); C_i++ ){ 
                                                
                        String str = ""; try{ str = String.valueOf( TableModel.getValueAt(L_i, C_i) ).toUpperCase().trim(); }catch( Exception e ){}  
                                                                               
                        switch( C_i ){
                        
                            case 0:  Fornecedor.setNomeourazaosocial( str.toUpperCase() );  break;  
                            case 1:  Fornecedor.setNomefantasia(      str.toUpperCase() );  break; 
                            case 2:  Fornecedor.setCd(                str.toUpperCase() );  break;     
                            case 3:  Fornecedor.setFrequencia(        str.toUpperCase() );  break;  
                            case 4:  Fornecedor.setGeracaopedidosra(  str.toUpperCase() );  break;     
                            case 5:  Fornecedor.setLeadtime(          str.toUpperCase() );  break; 
                        }                                
                    }
                    
                    Thread.sleep( 1 );
                    
                    if( verificarRepeticaoFornecedores( Fornecedor ) == false ){
                        
                        if( !Fornecedor.getNomeourazaosocial().equals("") ){
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Fornecedor, JPAUtil.em());
                            Fornecedor Fornecedor_Cadastrado = (Fornecedor) DAOGenericoJPA2.gravanovoOUatualizar();
                        
                            System.out.print(" - " + Fornecedor_Cadastrado.getNomeourazaosocial()+ " - ");
                        }
                    }
                    else{
                        
                        try{
                            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.FORNECEDOR WHERE NOMEOURAZAOSOCIAL = ?", Fornecedor.class );
                            q.setParameter(1, Fornecedor.getNomeourazaosocial() );
                            List<Fornecedor> list = q.getResultList();
            
                           for (int i = 0; i < list.size(); i++){
                
                                if( list.get(i).getNomeourazaosocial().equals( Fornecedor.getNomeourazaosocial() ) ){
                                    //System.out.println(" list.get(i).getMaterial() - " + list.get(i).getMaterial());
                                
                                    //list.get(i).setNomeourazaosocial ( Fornecedor.getNomeourazaosocial() );
                                    //list.get(i).setNomefantasia      ( Fornecedor.getNomefantasia     () );
                                    list.get(i).setCd                ( Fornecedor.getCd               () );  
                                    list.get(i).setFrequencia        ( Fornecedor.getFrequencia       () );
                                    list.get(i).setGeracaopedidosra  ( Fornecedor.getGeracaopedidosra () );
                                    list.get(i).setLeadtime          ( Fornecedor.getLeadtime         () );
                                                                    
                                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( list.get(i), JPAUtil.em());
                                    Fornecedor Fornecedor_Cadastrado = (Fornecedor) DAOGenericoJPA2.gravanovoOUatualizar();
                                    break; 
                                }
                            }
                        }catch( Exception e ){}  
                    
                    }               
                }
                
                Exportandob.fechar();
                //setarCBImportarExportar(false, new Evento() );
                
                Class<Imagens> clazzHome = Imagens.class;
                JOPM JOptionPaneMod = new JOPM( 1, "Cadastrar Análise\n"
                        + "\nAnálise cadastrada com sucesso\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            else{
                
                JOPM JOptionPaneMod = new JOPM( 2, "CADASTRAR ANÁLISE, "
                        + "\nTabela Vazia"
                        + "\nNenhum dado encontrado na tabela."
                        + "\n", "CADASTRAR ANÁLISE" );
            }

            
        }catch( Exception e ){}
    }

    private boolean verificarRepeticaoFornecedores( fornecedor.Fornecedor Fornecedor ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR", fornecedor.Fornecedor.class );
            List<fornecedor.Fornecedor> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getNomeourazaosocial().equals( Fornecedor.getNomeourazaosocial() ) ){
                    
                    retorno = true;
                    break;                    
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
    
    public void mostraOpIdentificadas() {                                      

        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            Exportando Exportandoy = new Exportando("OP. IDENTIFICADAS");
            Exportandoy.setVisible(true);
            Exportandoy.pbg.setMinimum(0);
            Exportandoy.pbg.setMaximum( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount() );  
            
            if( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getModel().getRowCount() > 0 ){
                
                Query qEv = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.OPIDENTIFICADA", Opidentificada.class );
                List<Opidentificada> listOp = qEv.getResultList();
                
                for (int i = 0; i < listOp.size(); i++){
                    //System.out.println("listOp.size() = " + i);
                
                for( int L_i=0; L_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getRowCount(); L_i++ ){
                    Exportandoy.pbg.setValue( L_i );                 
                    
                    int L_iSUG = 0;
                    int C_iSUG = 0;
                    String sku = "";
                    int count  = 0;
                
                    int vcount = 0;
                    
                    for( int C_i=0; C_i < Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount(); C_i++ ){ count++;                        
                                                       
                        vcount = count + 1;
                        
                        String strn = Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnName(C_i);
                        
                        //System.out.println("SKU = " + sku);
                    
                        if( strn.trim().equals("MATERIAL") ){ 
                            
                            String vCell = String.valueOf( Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getValueAt(L_i, C_i) ).trim();
                                
                            if(listOp.get(i).getMaterial().trim().equals( vCell ) ){
                                
                                sku = vCell;
                                //System.out.println("SKU = " + sku);
                            }
                        }
                        else if( strn.trim().equals("DESCRICAO DO MATERIAL") ){ 
                            
                            L_iSUG = L_i;
                            C_iSUG = C_i;
                        }
                        else if( vcount > Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.getColumnCount() ){
                            
                            try{ 
                                if( !sku.equals("") ){ 
                                    Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( listOp.get(i).getOpidentificada(), L_iSUG, C_iSUG);
                                }  
                                else{
                                    Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setValueAt( "", L_iSUG, C_iSUG);
                                }
                            } catch( Exception e ){}
                        }
                    }
                    
                }
                
                }
                
                Exportandoy.fechar();
            }
            
        } catch( Exception e ){ /*JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() );*/ } } }.start();
        
    }  
    
}
