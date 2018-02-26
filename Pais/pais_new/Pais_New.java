package pais_new;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import pais_0.Pais_Home;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import utilidades.Alterar_Propriedades;
import utilidades.Arquivo_Ou_Pasta;
import utilidades.Formatar_Celular;
import utilidades.Formatar_Cep;
import utilidades.Formatar_Cnpj;
import utilidades.Formatar_Data;
import utilidades.Formatar_Telefone;
import utilidades.JFileChooserJM;
import utilidades.JOPM;
import utilidades.ValidarEmail;

/**
 *
 * @author pc
 */
public class Pais_New extends javax.swing.JPanel {
    
    public String icone_do_estabelecimento = "";
    
    Pais_Home              Estabelecimento_Home;
    public Pais_New_Inicio Inicio;

    /**
     * Creates new form Fornecedor_New
     */
    public Pais_New(Pais_Home Estabelecimento_Home2) {
        initComponents();
        
        Estabelecimento_Home       = Estabelecimento_Home2;
        Inicio = new Pais_New_Inicio( this );
        
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public pais_0.Pais Estabelecimento;
    public Pais_New(Pais_Home Estabelecimento_Home2, pais_0.Pais Estabelecimento2 ) {
        initComponents();
        
        Estabelecimento_Home  = Estabelecimento_Home2;
        
        Estabelecimento = Estabelecimento2;
        
        Inicio = new Pais_New_Inicio( this, Estabelecimento2 );
        
    }
    ///////////////////////////////////////////////////////////////////////////

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jpStatus1 = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jpPermitirAcesso1 = new javax.swing.JPanel();
        tfSigla2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tfSigla3 = new javax.swing.JTextField();
        jpPermitirAcesso = new javax.swing.JPanel();
        tfCodigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfNome_PTBR = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfNome_ENG = new javax.swing.JTextField();
        jpIcon = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        jpPermitirAcesso18 = new javax.swing.JPanel();
        tfCadastrado_Por = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        tfData_Cadastro = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Dados do País");

        jpStatus1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(201, 239, 237)));

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/salvar.gif"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setPreferredSize(new java.awt.Dimension(91, 27));
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades_imagens/atualizar.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.setEnabled(false);

        javax.swing.GroupLayout jpStatus1Layout = new javax.swing.GroupLayout(jpStatus1);
        jpStatus1.setLayout(jpStatus1Layout);
        jpStatus1Layout.setHorizontalGroup(
            jpStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpStatus1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAtualizar)
                .addContainerGap())
        );
        jpStatus1Layout.setVerticalGroup(
            jpStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpStatus1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAtualizar)))
        );

        tfSigla2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfSigla2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfSigla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfSigla2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfSigla2MouseExited(evt);
            }
        });
        tfSigla2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSigla2KeyReleased(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setText("Sigla 2");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel28.setText("Sigla 3");

        tfSigla3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfSigla3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfSigla3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfSigla3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfSigla3MouseExited(evt);
            }
        });
        tfSigla3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSigla3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jpPermitirAcesso1Layout = new javax.swing.GroupLayout(jpPermitirAcesso1);
        jpPermitirAcesso1.setLayout(jpPermitirAcesso1Layout);
        jpPermitirAcesso1Layout.setHorizontalGroup(
            jpPermitirAcesso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermitirAcesso1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPermitirAcesso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfSigla2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPermitirAcesso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfSigla3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPermitirAcesso1Layout.setVerticalGroup(
            jpPermitirAcesso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermitirAcesso1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPermitirAcesso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPermitirAcesso1Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(2, 2, 2)
                        .addComponent(tfSigla3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPermitirAcesso1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(2, 2, 2)
                        .addComponent(tfSigla2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tfCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfCodigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfCodigoMouseExited(evt);
            }
        });
        tfCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCodigoKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Código");

        tfNome_PTBR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome_PTBR.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfNome_PTBR.setPreferredSize(new java.awt.Dimension(247, 23));
        tfNome_PTBR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfNome_PTBRMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfNome_PTBRMouseExited(evt);
            }
        });
        tfNome_PTBR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNome_PTBRKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Nome PTBR");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Nome ENG");

        tfNome_ENG.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome_ENG.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfNome_ENG.setPreferredSize(new java.awt.Dimension(247, 23));
        tfNome_ENG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfNome_ENGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfNome_ENGMouseExited(evt);
            }
        });
        tfNome_ENG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNome_ENGKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jpPermitirAcessoLayout = new javax.swing.GroupLayout(jpPermitirAcesso);
        jpPermitirAcesso.setLayout(jpPermitirAcessoLayout);
        jpPermitirAcessoLayout.setHorizontalGroup(
            jpPermitirAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermitirAcessoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPermitirAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(tfCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPermitirAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfNome_PTBR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPermitirAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfNome_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPermitirAcessoLayout.setVerticalGroup(
            jpPermitirAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPermitirAcessoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpPermitirAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPermitirAcessoLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(2, 2, 2)
                        .addComponent(tfNome_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPermitirAcessoLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(2, 2, 2)
                        .addComponent(tfNome_PTBR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPermitirAcessoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(2, 2, 2)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        jpIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpIcon.setPreferredSize(new java.awt.Dimension(100, 100));
        jpIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpIconMousePressed(evt);
            }
        });

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setToolTipText("Incluir / Alterar - Imagem");
        lbIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbIconMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbIconMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpIconLayout = new javax.swing.GroupLayout(jpIcon);
        jpIcon.setLayout(jpIconLayout);
        jpIconLayout.setHorizontalGroup(
            jpIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jpIconLayout.setVerticalGroup(
            jpIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );

        tfCadastrado_Por.setEditable(false);
        tfCadastrado_Por.setBackground(new java.awt.Color(255, 255, 255));
        tfCadastrado_Por.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfCadastrado_Por.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfCadastrado_Por.setPreferredSize(new java.awt.Dimension(247, 23));
        tfCadastrado_Por.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfCadastrado_PorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfCadastrado_PorMouseExited(evt);
            }
        });
        tfCadastrado_Por.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCadastrado_PorKeyReleased(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel52.setText("Cadastrado Por");

        tfData_Cadastro.setEditable(false);
        tfData_Cadastro.setBackground(new java.awt.Color(255, 255, 255));
        tfData_Cadastro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfData_Cadastro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfData_Cadastro.setPreferredSize(new java.awt.Dimension(185, 23));
        tfData_Cadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfData_CadastroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfData_CadastroMouseExited(evt);
            }
        });
        tfData_Cadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfData_CadastroKeyReleased(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel53.setText("Data Cadastro");

        javax.swing.GroupLayout jpPermitirAcesso18Layout = new javax.swing.GroupLayout(jpPermitirAcesso18);
        jpPermitirAcesso18.setLayout(jpPermitirAcesso18Layout);
        jpPermitirAcesso18Layout.setHorizontalGroup(
            jpPermitirAcesso18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPermitirAcesso18Layout.createSequentialGroup()
                .addContainerGap(183, Short.MAX_VALUE)
                .addGroup(jpPermitirAcesso18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfCadastrado_Por, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPermitirAcesso18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfData_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpPermitirAcesso18Layout.setVerticalGroup(
            jpPermitirAcesso18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPermitirAcesso18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpPermitirAcesso18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPermitirAcesso18Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(2, 2, 2)
                        .addComponent(tfData_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPermitirAcesso18Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(2, 2, 2)
                        .addComponent(tfCadastrado_Por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jpPermitirAcesso18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpPermitirAcesso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpPermitirAcesso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpPermitirAcesso18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpPermitirAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpPermitirAcesso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados Básicos", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpStatus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        Inicio.salvar(new pais_0.Pais(), false );
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfData_CadastroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfData_CadastroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfData_CadastroKeyReleased

    private void tfData_CadastroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfData_CadastroMouseExited
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfData_Cadastro, false );
    }//GEN-LAST:event_tfData_CadastroMouseExited

    private void tfData_CadastroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfData_CadastroMouseEntered
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfData_Cadastro, true );
    }//GEN-LAST:event_tfData_CadastroMouseEntered

    private void tfCadastrado_PorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCadastrado_PorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCadastrado_PorKeyReleased

    private void tfCadastrado_PorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCadastrado_PorMouseExited
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfCadastrado_Por, false );
    }//GEN-LAST:event_tfCadastrado_PorMouseExited

    private void tfCadastrado_PorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCadastrado_PorMouseEntered
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfCadastrado_Por, true );
    }//GEN-LAST:event_tfCadastrado_PorMouseEntered

    private void jpIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpIconMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpIconMousePressed

    private void lbIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIconMousePressed

    }//GEN-LAST:event_lbIconMousePressed

    private void lbIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIconMouseClicked
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            JFileChooserJM JFileChooserJM = new JFileChooserJM( "  imagens    -   jmarysystems.blogspot.com.br", new String [] { "PNG" , "JPEG" } );
            String strdevolvida = JFileChooserJM.getString( 2 );

            String home    = System.getProperty("user.dir");
            String endimgs = home + System.getProperty("file.separator") + "Logo_Estabelecimentos";

            File f  = new File( strdevolvida );

            // não precisa copiar para o mesmo lugar
            if( !strdevolvida.equals( endimgs + "\\" + f.getName() ) ){

                Arquivo_Ou_Pasta.copiarArquivo( f, endimgs );
            }

            Image image = null;
            try {

                image = ImageIO.read( new File( strdevolvida ) );

                lbIcon.setIcon(new ImageIcon(image.getScaledInstance(
                    lbIcon.getWidth(), lbIcon.getHeight(), Image.SCALE_DEFAULT)));

        //arquivoOuPastaCopiar( f, image, endimgs );
        InputStream inputStream = new FileInputStream( new File( strdevolvida ) );

        String strComp = enderecoParaCriarArquivo( f, endimgs );

        Arquivo_Ou_Pasta.copiarArquivoInputStream(inputStream, strComp, f, endimgs);

        icone_do_estabelecimento = strComp+f.getName();
        } catch (IOException ex) {}

        //lbIcon.setIcon(new javax.swing.ImageIcon( endimgs + System.getProperty("file.separator") + f.getName() ));

        } catch( Exception e ){ } } }.start();
    }//GEN-LAST:event_lbIconMouseClicked

    private void tfNome_PTBRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNome_PTBRKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNome_PTBRKeyReleased

    private void tfNome_PTBRMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNome_PTBRMouseExited
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfNome_PTBR, false );
    }//GEN-LAST:event_tfNome_PTBRMouseExited

    private void tfNome_PTBRMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNome_PTBRMouseEntered
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfNome_PTBR, true );
    }//GEN-LAST:event_tfNome_PTBRMouseEntered

    private void tfCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoKeyReleased
        String caracteres="0987654321.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            tfCodigo.setText("");
        }
    }//GEN-LAST:event_tfCodigoKeyReleased

    private void tfCodigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoMouseExited
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfCodigo, false );
    }//GEN-LAST:event_tfCodigoMouseExited

    private void tfCodigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoMouseEntered
        Alterar_Propriedades A_P = new Alterar_Propriedades( tfCodigo, true );
    }//GEN-LAST:event_tfCodigoMouseEntered

    private void tfNome_ENGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNome_ENGMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNome_ENGMouseEntered

    private void tfNome_ENGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNome_ENGMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNome_ENGMouseExited

    private void tfNome_ENGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNome_ENGKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNome_ENGKeyReleased

    private void tfSigla2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSigla2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSigla2MouseEntered

    private void tfSigla2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSigla2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSigla2MouseExited

    private void tfSigla2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSigla2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSigla2KeyReleased

    private void tfSigla3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSigla3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSigla3MouseEntered

    private void tfSigla3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSigla3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSigla3MouseExited

    private void tfSigla3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSigla3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSigla3KeyReleased

    int quantidadeDeArquivos = 0;
    /* Cria Um Arquivo Ou Informa Outro Nome Se Existir */
    private String enderecoParaCriarArquivo( File f, String endimgs ) {
        String saida = null;
        
        try{
            saida = endimgs + "\\" +
                    f.getName();            
                    
            /* Criando A Pasta */
            File criar = new File( saida );
            /* Verificando Se A Pasta Existe */
            if ( criar.exists() ) { 
                quantidadeDeArquivos++;
                File falt = new File( endimgs + "\\" + quantidadeDeArquivos+f.getName() );
                enderecoParaCriarArquivo( falt, endimgs );
            }        
            
        } catch ( Exception e ) { } 
        
        return String.valueOf( quantidadeDeArquivos );
    }
    
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAtualizar;
    public javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpIcon;
    private javax.swing.JPanel jpPermitirAcesso;
    private javax.swing.JPanel jpPermitirAcesso1;
    private javax.swing.JPanel jpPermitirAcesso18;
    private javax.swing.JPanel jpStatus1;
    public javax.swing.JLabel lbIcon;
    public javax.swing.JTextField tfCadastrado_Por;
    public javax.swing.JTextField tfCodigo;
    public javax.swing.JTextField tfData_Cadastro;
    public javax.swing.JTextField tfNome_ENG;
    public javax.swing.JTextField tfNome_PTBR;
    public javax.swing.JTextField tfSigla2;
    public javax.swing.JTextField tfSigla3;
    // End of variables declaration//GEN-END:variables

}