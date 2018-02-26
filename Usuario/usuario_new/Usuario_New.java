package usuario_new;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import usuario_0.Usuario_Home;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import utilidades.Alterar_Propriedades;
import utilidades.Arquivo_Ou_Pasta;
import utilidades.JFileChooserJM;

/**
 *
 * @author pc
 */
public class Usuario_New extends javax.swing.JPanel {
    
    public String icone_do_estabelecimento = "";
    
    Usuario_Home              Estabelecimento_Home;
    public Usuario_New_Inicio Inicio;

    /**
     * Creates new form Fornecedor_New
     */
    public Usuario_New(Usuario_Home Estabelecimento_Home2) {
        initComponents();
        
        Estabelecimento_Home       = Estabelecimento_Home2;
        Inicio = new Usuario_New_Inicio( this );
        
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public usuario_0.Usuario Estabelecimento;
    public Usuario_New(Usuario_Home Estabelecimento_Home2, usuario_0.Usuario Estabelecimento2 ) {
        initComponents();
        
        Estabelecimento_Home  = Estabelecimento_Home2;
        
        Estabelecimento = Estabelecimento2;
        
        Inicio = new Usuario_New_Inicio( this, Estabelecimento2 );
        
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
        jpIcon = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        jpPermitirAcesso18 = new javax.swing.JPanel();
        tfCadastrado_Por = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        tfData_Cadastro = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        chPermitirAcessar = new javax.swing.JCheckBox();
        jpPermitirAcesso6 = new javax.swing.JPanel();
        tfLogin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        pfSenha = new javax.swing.JPasswordField();
        pfRepetirSenha = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        tfNome_Completo = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Dados do Usuário");

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

        chPermitirAcessar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chPermitirAcessar.setSelected(true);
        chPermitirAcessar.setText("Permitir que este usuário acesse o sistema");
        chPermitirAcessar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chPermitirAcessarMousePressed(evt);
            }
        });
        chPermitirAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chPermitirAcessarActionPerformed(evt);
            }
        });

        jpPermitirAcesso6.setBackground(new java.awt.Color(221, 219, 242));

        tfLogin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfLogin.setPreferredSize(new java.awt.Dimension(209, 23));
        tfLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfLoginMouseExited(evt);
            }
        });
        tfLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLoginKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Repita a senha");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Definir senha");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Login (nome de usuário)");

        pfSenha.setPreferredSize(new java.awt.Dimension(209, 23));
        pfSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pfSenhaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pfSenhaMouseExited(evt);
            }
        });
        pfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfSenhaKeyPressed(evt);
            }
        });

        pfRepetirSenha.setPreferredSize(new java.awt.Dimension(209, 23));
        pfRepetirSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pfRepetirSenhaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pfRepetirSenhaMouseExited(evt);
            }
        });
        pfRepetirSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfRepetirSenhaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpPermitirAcesso6Layout = new javax.swing.GroupLayout(jpPermitirAcesso6);
        jpPermitirAcesso6.setLayout(jpPermitirAcesso6Layout);
        jpPermitirAcesso6Layout.setHorizontalGroup(
            jpPermitirAcesso6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermitirAcesso6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPermitirAcesso6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPermitirAcesso6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpPermitirAcesso6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPermitirAcesso6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(jpPermitirAcesso6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pfRepetirSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jpPermitirAcesso6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pfRepetirSenha, pfSenha, tfLogin});

        jpPermitirAcesso6Layout.setVerticalGroup(
            jpPermitirAcesso6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPermitirAcesso6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPermitirAcesso6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel21))
                .addGap(2, 2, 2)
                .addGroup(jpPermitirAcesso6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfRepetirSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpPermitirAcesso6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pfRepetirSenha, pfSenha, tfLogin});

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chPermitirAcessar, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jpPermitirAcesso6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chPermitirAcessar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpPermitirAcesso6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel54.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel54.setText("Nome Completo");

        tfNome_Completo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome_Completo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfNome_Completo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfNome_CompletoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfNome_CompletoMouseExited(evt);
            }
        });
        tfNome_Completo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNome_CompletoActionPerformed(evt);
            }
        });
        tfNome_Completo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNome_CompletoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNome_Completo, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(3, 3, 3)
                .addComponent(tfNome_Completo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpPermitirAcesso18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        Inicio.salvar(new usuario_0.Usuario(), false );
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLoginKeyReleased
      
    }//GEN-LAST:event_tfLoginKeyReleased

    private void tfLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLoginMouseExited
        Alterar_Propriedades alt_p = new Alterar_Propriedades(tfLogin, false);
    }//GEN-LAST:event_tfLoginMouseExited

    private void tfLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfLoginMouseEntered
        Alterar_Propriedades alt_p = new Alterar_Propriedades(tfLogin, true);
    }//GEN-LAST:event_tfLoginMouseEntered

    private void chPermitirAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chPermitirAcessarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chPermitirAcessarActionPerformed

    private void chPermitirAcessarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chPermitirAcessarMousePressed

    }//GEN-LAST:event_chPermitirAcessarMousePressed

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
    
    private void tfNome_CompletoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNome_CompletoMouseEntered
        Alterar_Propriedades alt_p = new Alterar_Propriedades(tfNome_Completo, true);
    }//GEN-LAST:event_tfNome_CompletoMouseEntered

    private void tfNome_CompletoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNome_CompletoMouseExited
        Alterar_Propriedades alt_p = new Alterar_Propriedades(tfNome_Completo, false);
    }//GEN-LAST:event_tfNome_CompletoMouseExited

    private void tfNome_CompletoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNome_CompletoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNome_CompletoKeyReleased

    private void tfNome_CompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNome_CompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNome_CompletoActionPerformed

    private void pfSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfSenhaKeyPressed

    }//GEN-LAST:event_pfSenhaKeyPressed

    private void pfSenhaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pfSenhaMouseEntered
        Alterar_Propriedades alt_p = new Alterar_Propriedades(pfSenha, true);
    }//GEN-LAST:event_pfSenhaMouseEntered

    private void pfSenhaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pfSenhaMouseExited
        Alterar_Propriedades alt_p = new Alterar_Propriedades(pfSenha, false);
    }//GEN-LAST:event_pfSenhaMouseExited

    private void pfRepetirSenhaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pfRepetirSenhaMouseEntered
        Alterar_Propriedades alt_p = new Alterar_Propriedades(pfRepetirSenha, true);
    }//GEN-LAST:event_pfRepetirSenhaMouseEntered

    private void pfRepetirSenhaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pfRepetirSenhaMouseExited
        Alterar_Propriedades alt_p = new Alterar_Propriedades(pfRepetirSenha, false);
    }//GEN-LAST:event_pfRepetirSenhaMouseExited

    private void pfRepetirSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfRepetirSenhaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pfRepetirSenhaKeyPressed

        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAtualizar;
    public javax.swing.JButton btSalvar;
    public javax.swing.JCheckBox chPermitirAcessar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpIcon;
    private javax.swing.JPanel jpPermitirAcesso18;
    private javax.swing.JPanel jpPermitirAcesso6;
    private javax.swing.JPanel jpStatus1;
    public javax.swing.JLabel lbIcon;
    public javax.swing.JPasswordField pfRepetirSenha;
    public javax.swing.JPasswordField pfSenha;
    public javax.swing.JTextField tfCadastrado_Por;
    public javax.swing.JTextField tfData_Cadastro;
    public javax.swing.JTextField tfLogin;
    public javax.swing.JTextField tfNome_Completo;
    // End of variables declaration//GEN-END:variables

}