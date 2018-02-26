/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import gb_evento.Gbeansecundario;
import gb_evento.Gbproduto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import utilidades.Exportando;

/**
 *
 * @author AnaMariana
 */
public class ListaSAPAnaliseSet extends javax.swing.JFrame {

    ListaSAPNaoCadastrado ListaSAPNaoCadastrado;
    
    Menu_Pesquisa_Importar_Exportar Menu;
    String layout = "";
    /**
     * Creates new form OperacaoRealizada
     * @param layout2
     * @param Menu_Pesquisa_Importar_Exportar2
     */
    public ListaSAPAnaliseSet( String layout2, Menu_Pesquisa_Importar_Exportar Menu_Pesquisa_Importar_Exportar2 ) { 
        initComponents();
        lbAcao.setText("EXPORTAR LISTA SAP...");
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
    private void listaDeProdutos(){
        new Thread() { @Override public void run() {    
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica(); 
            
                while ( cadastrarBoo == false ) {   
                        cadastrarBoo = true;
                        try { Thread.sleep( 1 );
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");
                        
                            //TreeSet<String> listaSap = new TreeSet<>();
                            //HashSet<String> listaSap = new HashSet<>();
                            LinkedHashSet<String> listaSap = new LinkedHashSet<>();
                            
                            String str[] = tpListSap.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
                                    String nome = str[i];
                                //for(String nome:str){ 
                                    //System.out.println(nome);
                                    listaSap.add( nome.trim() );
                                //}   
                                }
                                
                                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                                // ORDENAR LISTA
                                //Set<String> ordem = new TreeSet<>(listaSap);

                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                
                                int c = 0;
                                for (Iterator<String> it = listaSap.iterator(); it.hasNext();) {
                                    try{
                                        
                                        c++;
                                        Exportando.pbg.setValue( c );
                                        
                                        String busca = it.next().trim();
                                        
                                        List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                        try{ 
                                            
                                            XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                                        }catch( Exception e ){ }
                                        
                                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                         
                                        if( busca.equals(rbusca) ){

                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) );
                                        }
                                        else{
                                                    
                                            String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                            ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                        }                                                                              
                                    }catch( Exception e ){}
                                }
                            }
                        
                            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
                            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                            
                            Exportando.fechar();
                            //fechar();                     
                        }catch( Exception e ){ fechar(); }
                    }
            } }.start();
    }
    
    private boolean cadastrarBoo3 = false;
    private void listaDeProdutosEan(){
        new Thread() { @Override public void run() {    
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica(); 
            
                while ( cadastrarBoo3 == false ) {   
                        cadastrarBoo3 = true;
                        try { Thread.sleep( 1 );
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");
                        
                            //TreeSet<String> listaSap = new TreeSet<>();
                            //HashSet<String> listaSap = new HashSet<>();
                            LinkedHashSet<String> listaEan = new LinkedHashSet<>();
                            
                            String str[] = tpListEan.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
                                    String nome = str[i];
                                //for(String nome:str){ 
                                    //System.out.println(nome);
                                    listaEan.add( nome.trim() );
                                //}   
                                }
                                
                                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                                // ORDENAR LISTA
                                //Set<String> ordem = new TreeSet<>(listaSap);

                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                
                                int c = 0;
                                for (Iterator<String> it = listaEan.iterator(); it.hasNext();) {
                                    try{
                                        
                                        c++;
                                        Exportando.pbg.setValue( c );
                                        
                                        String busca = it.next().trim();
                                        
                                        List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                        try{ 
                                            
                                            XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE EAN = ?", busca );
                                        }catch( Exception e ){ }
                                        
                                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getEan(); }catch( Exception e ){}
                                         
                                        if( busca.equals(rbusca) ){

                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) );
                                        }
                                        else{
                                            
                                            List<Gbeansecundario> XXGbprodutoListaSapEan = null;
                                            
                                            try{ 
                                            
                                                XXGbprodutoListaSapEan = (List<Gbeansecundario>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbeansecundario.class, "SELECT * FROM SCHEMAJMARY.GBEANSECUNDARIO WHERE EAN = ?", busca );
                                            }catch( Exception e ){ }
                                            
                                            String rbuscaEan = ""; try{ rbuscaEan = XXGbprodutoListaSapEan.get(0).getEan(); }catch( Exception e ){}
                                            
                                            if( busca.equals(rbuscaEan) ){
                                                
                                                String sapX = XXGbprodutoListaSapEan.get(0).getMaterial();
                                                
                                                try{ 
                                                                                                                                                    
                                                    XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", sapX );
                                                }catch( Exception e ){ }
                                                
                                                if( sapX.equals(XXGbprodutoListaSap.get(0).getMaterial()) ){
                                                    
                                                    Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) );
                                                }  
                                                else{
                                                
                                                    String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                                    ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                                } 
                                                
                                            }  
                                            else{
                                                
                                                String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                                ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                            }                   
                                            
                                        }                                                                              
                                    }catch( Exception e ){}
                                }
                            }
                        
                            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
                            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                            
                            Exportando.fechar();
                            //fechar();                     
                        }catch( Exception e ){ fechar(); }
                    }
            } }.start();
    }
    
    private boolean cadastrarBoo2 = false;
    private void listaDeProdutosPorFornecedor(){
        new Thread() { @Override public void run() {     
            
            /*             
            Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa = new DefaultTableModel( null, new String[]{ 
                "MATERIAL", "DESCRICAO DO MATERIAL", "MRP", "E/S", "UBM", "Q.EMB", "PONTO EXTRA", "ELENCO", "DDE*", "EST. LOJA", "SUGESTÃO", "  DDE  ", "DISPONIBILIDADE", "PED. ATIVO", "REM.", "SAIU CD",  "ENT. LOJA", "VENDA ULT. SEM", "VENDA 04 SEM", "VENDA 12 SEM", "EST. CD001", "EST. CD289", "EST. CD184", "CD ONTEM", "CLASSE", "SETOR", "EST. TIPO", "COD. FORNEC.", "NOME FORNEC.", "RA", "EST. MÍNIMO", "EAN", "GRP",  "DIAS SEM VENDAS", "O.S"           
            } );
             
            while ( Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.getRowCount() > 0){ Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa.removeRow(0); }
            
            Menu.Tabela_Pesquisa_BD_Estabelecimento.tbPesquisa.setModel(Menu.Tabela_Pesquisa_BD_Estabelecimento.tmPesquisa);
            */
                while ( cadastrarBoo2 == false ) {   
                        cadastrarBoo2 = true;
                        try { Thread.sleep( 1 );
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");
                        
                            Set<String> listaSap = new HashSet<String>();
                            
                            String str[] = tpListSap.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                Thread.sleep( 1000 );
                                
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
                                    String nome = str[i];
                                //for(String nome:str){ 
                                    //System.out.println(nome);
                                    listaSap.add( nome.trim() );
                                //}   
                                }
                                
                                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                                // ORDENAR LISTA
                                Set<String> ordem = new TreeSet<String>(listaSap);
                                
                                Thread.sleep( 1 );
                                List<Gbproduto> GbprodutoListaSap = new ArrayList<Gbproduto>();
                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                
                                int c = 0;
                                for (Iterator<String> it = ordem.iterator(); it.hasNext();) {
                                    try{
                                        
                                        c++;
                                        Exportando.pbg.setValue( c );
                                        
                                        String busca = it.next().trim();
                                        
                                        List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                        try{ 
                                            
                                            XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                                        }catch( Exception e ){ }
                                        
                                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                         
                                        if( busca.equals(rbusca) ){
                                            
                                            GbprodutoListaSap.add( XXGbprodutoListaSap.get(0) );
                                            //Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseGenericaTabela( "", XXGbprodutoListaSap.get(0) );
                                        }
                                        else{
                                                    
                                            String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                            ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                            //System.out.println("PRODUTO NÃO CADASTRADO: " + busca );
                                        }                                                                              

                                        //System.out.println("vvvvvv " + XXGbprodutoListaSap.get(0).getMaterial() );
                                    }catch( Exception e ){}
                                }
                                
                                Menu.Tabela_Pesquisa_BD_Estabelecimento.analiseMostrarListaSap2Layout(  "listarprodutoporfornecedor", "", GbprodutoListaSap );
                            }
                        
                            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
                            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                            
                            Exportando.fechar();
                            //fechar();                     
                        }catch( Exception e ){ fechar(); }
                    }
            } }.start();
    }
    
    private boolean cadastrarBoo4 = false;
    private void layoutProdutos(){
        new Thread() { @Override public void run() {    
            
        Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoTabelaGenerica(); 
            
                while ( cadastrarBoo4 == false ) {   
                        cadastrarBoo4 = true;
                        try { Thread.sleep( 1 );
                            Exportando Exportando = new Exportando("PESQUISANDO PRODUTOS");
                        
                            //TreeSet<String> listaSap = new TreeSet<>();
                            //HashSet<String> listaSap = new HashSet<>();
                            LinkedHashSet<String> listaSap = new LinkedHashSet<>();
                            
                            String str[] = tpListSap.getText().split("\n");
                            if( !str.equals("") ){
                                                                
                                Exportando.setVisible(true);
                                Exportando.pbg.setMinimum(0);
                                Exportando.pbg.setMaximum( str.length );
                                
                                fechar(); 
                                
                                for (int i = 0; i < str.length; i++){
                                    Exportando.pbg.setValue( i );
                                    String nome = str[i];
                                //for(String nome:str){ 
                                    //System.out.println(nome);
                                    listaSap.add( nome.trim() );
                                //}   
                                }
                                
                                ListaSAPNaoCadastrado = new ListaSAPNaoCadastrado( Menu );
                                
                                // ORDENAR LISTA
                                //Set<String> ordem = new TreeSet<>(listaSap);

                                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Gbproduto.class, JPAUtil.em());
                                
                                Menu.Tabela_Pesquisa_BD_Estabelecimento.setarCabecalhoGenericaTabelaDuplicado();
                                
                                int c = 0;
                                for (Iterator<String> it = listaSap.iterator(); it.hasNext();) {
                                    try{
                                        
                                        c++;
                                        Exportando.pbg.setValue( c );
                                        
                                        String busca = it.next().trim();
                                        
                                        List<Gbproduto> XXGbprodutoListaSap = null;
                                    
                                        try{ 
                                            
                                            XXGbprodutoListaSap = (List<Gbproduto>) DAOGenericoJPA.getBeansCom_1_Parametro(Gbproduto.class, "SELECT * FROM SCHEMAJMARY.GBPRODUTO WHERE MATERIAL = ?", busca );
                                        }catch( Exception e ){ }
                                        
                                        String rbusca = ""; try{ rbusca = XXGbprodutoListaSap.get(0).getMaterial(); }catch( Exception e ){}
                                         
                                        if( busca.equals(rbusca) ){

                                            Menu.Tabela_Pesquisa_BD_Estabelecimento.genericaTabelaDuplicado( "", XXGbprodutoListaSap.get(0) );
                                        }
                                        else{
                                                    
                                            String txt = ListaSAPNaoCadastrado.tpListSap.getText();
                                            ListaSAPNaoCadastrado.tpListSap.setText( txt+"\n"+busca );
                                        }                                                                              
                                    }catch( Exception e ){}
                                }
                            }
                        
                            String txt = ListaSAPNaoCadastrado.tpListSap.getText().trim();
                            if( !txt.equals("") ){ ListaSAPNaoCadastrado.setVisible(true); }
                            
                            Exportando.fechar();
                            //fechar();                     
                        }catch( Exception e ){ fechar(); }
                    }
            } }.start();
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
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpListSap = new javax.swing.JTextPane();
        tfLogradouro1 = new javax.swing.JTextField();
        tfLogradouro2 = new javax.swing.JTextField();
        tfLogradouro3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpListEan = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        tfLogradouro4 = new javax.swing.JTextField();
        tfLogradouro5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/disquete.png"))); // NOI18N

        lbAcao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAcao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAcao.setText("EXPORTAR LISTA SAP...");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("COLE AQUI - SAP");

        jScrollPane1.setViewportView(tpListSap);

        tfLogradouro1.setEditable(false);
        tfLogradouro1.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro1.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro1.setText("LISTAR PRODUTOS");
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
        });
        tfLogradouro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro1KeyReleased(evt);
            }
        });

        tfLogradouro2.setEditable(false);
        tfLogradouro2.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro2.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro2.setText("PROD. POR FORNECEDOR");
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
        });
        tfLogradouro2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro2KeyReleased(evt);
            }
        });

        tfLogradouro3.setEditable(false);
        tfLogradouro3.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro3.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro3.setText("LAYOUT PRODUTOS");
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
        });
        tfLogradouro3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLogradouro3KeyReleased(evt);
            }
        });

        jScrollPane2.setViewportView(tpListEan);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("COLE AQUI - EAN");

        tfLogradouro4.setEditable(false);
        tfLogradouro4.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro4.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro4.setText("LISTAR PRODUTOS");
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

        tfLogradouro5.setEditable(false);
        tfLogradouro5.setBackground(new java.awt.Color(255, 51, 51));
        tfLogradouro5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro5.setForeground(new java.awt.Color(255, 255, 255));
        tfLogradouro5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLogradouro5.setText("LISTAR POR TABELA EAN ");
        tfLogradouro5.setToolTipText("CONSULTAR LISTA SAP");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAcao, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfLogradouro2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tfLogradouro3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfLogradouro4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tfLogradouro5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfLogradouro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLogradouro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLogradouro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfLogradouro4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfLogradouro5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfLogradouro1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseEntered

    private void tfLogradouro1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro1MouseExited

    private void tfLogradouro1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLogradouro1MousePressed
        listaDeProdutos();
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
        listaDeProdutosPorFornecedor();
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
        layoutProdutos();
    }//GEN-LAST:event_tfLogradouro3MousePressed

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
       listaDeProdutosEan();
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
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro5MousePressed

    private void tfLogradouro5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLogradouro5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLogradouro5KeyReleased

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
            java.util.logging.Logger.getLogger(ListaSAPAnaliseSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaSAPAnaliseSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaSAPAnaliseSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaSAPAnaliseSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ListaSAP().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAcao;
    public javax.swing.JTextField tfLogradouro1;
    public javax.swing.JTextField tfLogradouro2;
    public javax.swing.JTextField tfLogradouro3;
    public javax.swing.JTextField tfLogradouro4;
    public javax.swing.JTextField tfLogradouro5;
    private javax.swing.JTextPane tpListEan;
    private javax.swing.JTextPane tpListSap;
    // End of variables declaration//GEN-END:variables
}
